(ns clrs.sort.insertion
  (:refer-clojure :exclude [sort]))

(defn insert
  ([coll n order] (insert coll () n order))
  ([head tail n order]
   (if (or (empty? head)
           ((complement order) n (last head)))
     (concat head (cons n tail))
     (recur (butlast head) (cons (last head) tail) n order))))

(defn sort
  ([coll] (sort coll [] <))
  ([coll order] (sort coll [] order))
  ([coll-remaining coll-sorted order]
   (if (empty? coll-remaining)
     coll-sorted
     (recur (rest coll-remaining)
            (insert coll-sorted (first coll-remaining) order)
            order))))

(defn reverse-sort
  ([coll] (sort coll [] >)))
