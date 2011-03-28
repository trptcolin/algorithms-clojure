(ns clrs.data-structures.binary-search-tree)

(defn left [index]
  (+ (* 2 index) 1))

(defn right [index]
  (+ (* 2 index) 2))

(defn value [node]
  (:value node))

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

;(defn delete
; ([tree element]
;  (delete tree 0 element))
; ([tree index element]
;  (if-let [this-value (get tree index)]
;    (cond (= this-value element)
;            (dissoc tree index)
;          (< this-value element)
;            (delete tree (right index) element)
;          :else
;            (delete tree (left index) element))
;
;    tree)
;  ))

