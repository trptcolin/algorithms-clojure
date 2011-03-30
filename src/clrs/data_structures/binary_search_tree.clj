(ns clrs.data-structures.binary-search-tree)

(defn node [value & {:keys [left right] :as children}]
  (assoc children :value value))

(defn- next-element [current-node search-value]
  (if (< search-value (:value current-node))
    (:left current-node)
    (:right current-node)))

(defn search
  ([node search-value]
   (if (or (nil? (:value node)) (= search-value (:value node)))
     node
     (recur (next-element node search-value)
            search-value))))

(defn- extrema [node best-so-far next-element-strategy]
  (if-let [this-value (:value node)]
    (recur (next-element-strategy node)
           this-value
           next-element-strategy)
    best-so-far))

(defn minimum [node]
  (extrema node nil :left))

(defn maximum [node]
  (extrema node nil :right))

(defn next-direction [current-node search-value]
  (if (< search-value (:value current-node))
    :left
    :right))

(defn insert [root-node new-element]
  (loop [path []]
    (let [this-node (get-in root-node path)]
      (if-let [this-value (:value this-node)]
        (recur (conj path (next-direction this-node new-element)))
        (assoc-in root-node (conj path :value) new-element)))))

(defn make [element & more]
  (loop [node (node element), more more]
    (if (seq more)
      (recur (insert node (first more)) (rest more))
      node)))

(declare delete)
(defn join [left right]
  (node (minimum right)
        :left left
        :right (delete right (minimum right))))

(defn delete [tree element]
  (let [left      (:left tree)
        right     (:right tree)]
    (cond (nil? (:value tree))
            nil
          (< element (:value tree))
            (node (:value tree)
                  :left (delete left element)
                  :right right)
          (> element (:value tree))
            (node (:value tree)
                  :left left
                  :right (delete right element))
          (nil? left)
            right
          (nil? right)
            left
          :else
            (join left right))))

