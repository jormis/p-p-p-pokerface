(ns p-p-p-pokerface)


; 2015-07-16
(defn suit [card]
  (let [[rank-var suit-var] (seq card)]
    (str suit-var)))

; 2015-07-16 \\
(defn rank [card]
  (let [[rank-var suit-var] (seq card)
    replacements { \A 14, \K 13, \Q 12, \J 11 \T 10 }]
    (cond
      (Character/isDigit rank-var) (Integer/valueOf (str rank-var)) 
      :else (get replacements rank-var))))

; Ok: need to count rank frequencies. Accept only one pair.
(defn pair? [hand] 
  (== (count (filter (fn [x] (== x 2)) (vals (frequencies (map rank hand))))) 1))

(defn three-of-a-kind? [hand]
  (== (count (filter (fn [x] (== x 3)) (vals (frequencies (map rank hand))))) 1))

(defn four-of-a-kind? [hand]
  (== (count (filter (fn [x] (== x 4)) (vals (frequencies (map rank hand))))) 1))

(defn flush? [hand]
  (== (count (filter (fn [x] (== x 5)) (vals (frequencies (map suit hand))))) 1))

(defn full-house? [hand]
  (and (pair? hand) (three-of-a-kind? hand)))

(defn two-pairs? [hand]
  (or (== (count (filter (fn [x] (== x 2)) (vals (frequencies (map rank hand))))) 2)
    (four-of-a-kind? hand)))

(defn straight? [hand]
  (let [sorted-hand (sort (map rank hand))
      sorted-hand-with-ace-as-one (sort (map (fn [x] (if (== 14 x) 1 x)) (map rank hand)))
      lowest-rank (first sorted-hand)
      lowest-rank-with-ace-as-one (first sorted-hand-with-ace-as-one)]
    (or (= sorted-hand (range lowest-rank (+ lowest-rank 5)))
      (= sorted-hand-with-ace-as-one 
        (range lowest-rank-with-ace-as-one (+ lowest-rank-with-ace-as-one 5))))))

(defn straight-flush? [hand]
  (and (straight? hand) (flush? hand)))

; For value().
(defn high-card? [hand]
  true)

(defn value [hand]
	(let [checkers #{[high-card? 0]  [pair? 1]
                 [two-pairs? 2]  [three-of-a-kind? 3]
                 [straight? 4]   [flush? 5]
                 [full-house? 6] [four-of-a-kind? 7]
                 [straight-flush? 8]}]
    	; filter evaluates/runs the first item in a (sequence) pair (data is code) for the hand and if true returns the value
    	; (second), otherwise false. Only non-false items are then map-seconded and finally max is taken.
    	(apply max (map second (filter (fn [x] (if ((first x) hand) (second x) false)) checkers)))))

; Höpönlöpönlöpön, mene hakemaan kaupasta karkkia.