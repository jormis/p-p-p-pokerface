(ns p-p-p-pokerface)

; 2015-07-16
(defn suit [card]
  (let [[rank-var suit-var] (seq card)]
    (str suit-var)))

; 2015-07-16
(defn rank [card]
  (let [[rank-var suit-var] (seq card)
    replacements { \A 14, \K 13, \Q 12, \J 11 \T 10 }]
    (cond
      (Character/isDigit rank-var) (Integer/valueOf (str rank-var)) 
      :else (get replacements rank-var))))

(defn pair? [hand] 
  nil)

(defn three-of-a-kind? [hand]
  nil)

(defn four-of-a-kind? [hand]
  nil)

(defn flush? [hand]
  nil)

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
