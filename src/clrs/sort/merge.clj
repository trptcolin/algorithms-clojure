(ns clrs.sort.merge
  (:refer-clojure :exclude [sort merge]))

(defn merge
  ([a b] (merge < a b))
  ([order a b]
    (loop [acc []
           a a
           b b]
      (if (or (empty? a) (empty? b))
            (concat acc a b)
          (let [[winner loser] (if (order (first a) (first b))
                                   [a b]
                                   [b a])]
            (recur (conj acc (first winner))
                   loser
                   (rest winner)))))))

(defn sort
  ([coll] (sort < coll))
  ([order coll]
    (let [n (count coll)]
      (if (= 1 n)
        coll
        (let [[a b] (map (partial sort order)
                         (split-at (int (/ n 2)) coll))]
          (merge order a b))))))

(defn reverse-sort
  ([coll] (sort > coll)))
