(ns clrs.data-structures.binary-search-tree)

(defn left [index]
  (+ (* 2 index) 1))

(defn right [index]
  (+ (* 2 index) 2))

(defn search
  ([elements element] (search elements 0 element))
  ([elements index element]
   (let [this-value (get elements index)]
     (if (or (nil? this-value) (= element this-value))
         this-value
         (if (< element this-value)
           (recur elements (left index) element)
           (recur elements (right index) element))))))

(defn- extrema [elements index extrema-so-far next-element-strategy]
  (if-let [this-value (get elements index)]
    (recur elements
           (next-element-strategy index)
           this-value next-element-strategy)
    extrema-so-far))

(defn minimum [elements]
  (extrema elements 0 nil left))

(defn maximum [elements]
  (extrema elements 0 nil right))

