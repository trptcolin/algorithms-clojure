(ns clrs.sort.insertion
  (:refer-clojure :exclude [sort]))

(defn insert
  ([order coll n] (insert order coll () n))
  ([order head tail n]
   (if (or (empty? head)
           ((complement order) n (last head)))
     (concat head (cons n tail))
     (recur order (butlast head) (cons (last head) tail) n))))

(defn sort
  ([coll] (sort < coll))
  ([order coll]
   (reduce (fn [coll-sorted n] (insert order coll-sorted n))
           []
           coll)))

(defn reverse-sort
  ([coll] (sort > coll)))
