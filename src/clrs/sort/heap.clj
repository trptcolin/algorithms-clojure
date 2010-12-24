(ns clrs.sort.heap
  (:refer-clojure :exclude [sort]))

(defn swap-at [coll index-1 index-2]
  (assoc (vec coll)
         index-1 (nth coll index-2)
         index-2 (nth coll index-1)))

(defn max-heapify [coll index]
  (let [left-index       (dec (* (inc index) 2))
        right-index      (+ left-index 1)
        existing-nodes   (map
                           (fn [index] [index (nth coll index)])
                           (filter
                             (fn [index] (< index (count coll)))
                             [left-index right-index index]))
        index-of-largest (if (seq existing-nodes)
                             (first (apply max-key last existing-nodes))
                             index)]
    (if (= index-of-largest index)
        coll
        (recur (swap-at coll index index-of-largest)
               index-of-largest))))

(defn build-max-heap [coll]
  (reduce max-heapify
          coll
          (range (quot (inc (count coll)) 2) -1 -1)))

(defn sort [starting-coll]
  (let [start 0
        starting-coll (build-max-heap starting-coll)]
    (apply concat
      (reduce
        (fn [[heap sorted-coll] index]
          (let [coll (swap-at heap start index)
                current-heap  (max-heapify (drop-last coll) start)]
            [current-heap (cons (last coll) sorted-coll)]))
        [starting-coll ()]
        (range (dec (count starting-coll)) (dec start) -1)))))
