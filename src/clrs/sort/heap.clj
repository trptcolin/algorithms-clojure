(ns clrs.sort.heap
  (:refer-clojure :exclude [sort]))

(defn swap-at [coll i j]
  (assoc (vec coll) i (nth coll j)
                    j (nth coll i)))

(defn max-heapify [coll index]
  (let [left-index          (dec (* (inc index) 2))
        right-index         (+ left-index 1)
        get-index-and-value (fn [index] [index (nth coll index nil)])
        child-nodes         (filter (fn [[i x]] x)
                                    (map get-index-and-value
                                         [left-index right-index]))
        index-of-largest    (first (apply max-key last
                                                  (get-index-and-value index)
                                                  child-nodes))]
    (if (= index-of-largest index)
        coll
        (recur (swap-at coll index index-of-largest) index-of-largest))))

(defn downto [start end]
  (range start (dec end) -1))

(defn build-max-heap [coll]
  (reduce max-heapify coll (downto (quot (inc (count coll)) 2) 0)))

(defn sort [starting-coll]
  (let [starting-coll (build-max-heap starting-coll)]
    (apply concat
      (reduce
        (fn [[heap sorted-coll] index]
          (let [coll (swap-at heap 0 index)
                current-heap  (max-heapify (drop-last coll) 0)]
            [current-heap (cons (last coll) sorted-coll)]))
        [starting-coll ()]
        (downto (dec (count starting-coll)) 0)))))
