(ns clrs.sort.insertion
  (:refer-clojure :exclude [sort]))

(defn insert [coll n]
  (loop [start coll end '()]
    (if (or (empty? start)
            (> n (last start)))
      (concat start (list n) end)
      (recur (butlast start) (cons (last start) end)))))

(defn sort
  ([coll-remaining coll-sorted]
   (if (empty? coll-remaining)
     coll-sorted
     (recur (rest coll-remaining)
            (insert coll-sorted (first coll-remaining)))))
  ([coll]
   (sort coll '())))

