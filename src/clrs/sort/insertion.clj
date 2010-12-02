(ns clrs.sort.insertion
  (:refer-clojure :exclude [sort]))

(defn insert
  ([coll n] (insert coll () n))
  ([head tail n]
   (if (or (empty? head)
           (> n (last head)))
     (concat head (cons n tail))
     (recur (butlast head) (cons (last head) tail) n))))

(defn sort
  ([coll]
   (sort coll []))
  ([coll-remaining coll-sorted]
   (if (empty? coll-remaining)
     coll-sorted
     (recur (rest coll-remaining)
            (insert coll-sorted (first coll-remaining))))))

