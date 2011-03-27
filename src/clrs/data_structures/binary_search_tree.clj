(ns clrs.data-structures.binary-search-tree)

(defn left [index]
  (+ (* 2 index) 1))

(defn right [index]
  (+ (* 2 index) 2))

(defn make [elements]
  (into {} (map-indexed vector elements)))

(defn search
  ([tree element] (search tree 0 element))
  ([tree index element]
   (let [this-value (get tree index)]
     (if (or (nil? this-value) (= element this-value))
         this-value
         (if (< element this-value)
           (recur tree (left index) element)
           (recur tree (right index) element))))))

(defn- extrema [tree index best-so-far next-element-strategy]
  (if-let [this-value (get tree index)]
    (recur tree
           (next-element-strategy index)
           this-value next-element-strategy)
    best-so-far))

(defn minimum [tree]
  (extrema tree 0 nil left))

(defn maximum [tree]
  (extrema tree 0 nil right))

(defn insert
 ([tree new-element]
  (insert tree 0 new-element))
 ([tree index new-element]
  (if-let [this-value (get tree index)]
    (if (< new-element this-value)
      (recur tree (left index) new-element)
      (recur tree (right index) new-element))
    (assoc tree index new-element))))
