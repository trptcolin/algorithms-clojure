(ns clrs.data-structures.binary-search-tree-spec
  (:require [clrs.data-structures.binary-search-tree :as bst])
  (:use [speclj.core]))

(describe "node"
  (it "with no children"
    (should= 42 (:value (bst/node 42))))

  (it "with left and right children"
    (let [node (bst/node 42
                         :left (bst/node 7)
                         :right (bst/node 100))]
      (should= 42 (:value node))
      (should= 7 (:value (:left node)))
      (should= 100 (:value (:right node))))))

(describe "make"
  (it "provides a simple construction mechanism"
    (should= (bst/node 5
                       :left (bst/node 4
                                       :left (bst/node 3
                                                       :left (bst/node 2
                                                                       :left (bst/node 1))))
                       :right (bst/node 6))
             (bst/make 5 4 6 3 2 1))))

(describe "search"
  (it "finds nothing in a fresh tree"
    (should= nil (-> (bst/node nil)
                     (bst/search 42)
                     :value)))

  (it "finds the root element"
    (should= 42 (-> (bst/node 42)
                    (bst/search 42)
                    :value)))

  (it "finds the left element"
    (should= 40 (-> (bst/node 42 :left (bst/node 40))
                    (bst/search 40)
                    :value)))

  (it "finds the right element"
    (should= 44 (-> (bst/node 42 :left (bst/node 40) :right (bst/node 44))
                    (bst/search 44)
                    :value)))

  (it "finds an element deeper in the tree"
    (should= 39 (-> (bst/make 42 40 44 38 37 39)
                    (bst/search 39)
                    :value)))

  (it "doesn't find missing elements"
    (should= nil (-> (bst/make 42 40 44 38 37 39)
                     (bst/search 46)
                     :value))))

(describe "minimum"
  (it "has none for a fresh tree"
    (should= nil (-> (bst/node nil)
                     (bst/minimum))))

  (it "is the root for a one-node tree"
    (should= 1 (-> (bst/node 1)
                   (bst/minimum))))

  (it "gets the left node"
    (should= 4 (-> (bst/node 5 :left (bst/node 4) :right (bst/node 6))
                   (bst/minimum))))

  (it "recurses to the left"
    (should= 1 (-> (bst/make 5 4 6 3 2 1)
                   (bst/minimum)))))

(describe "maximum"
  (it "has none for a fresh tree"
    (should= nil (-> (bst/node nil) (bst/maximum))))

  (it "is the root for a one-node tree"
    (should= 1 (-> (bst/node 1) (bst/maximum))))

  (it "gets the left node"
    (should= 6 (-> (bst/node 5 :left (bst/node 4) :right (bst/node 6))
                   (bst/maximum))))

  (it "recurses to the right"
    (should= 8 (-> (bst/make 5 1 2 3 4 6 7 8)
                   (bst/maximum)))))

(describe "insert"
  (it "round-trips from a fresh tree"
    (should= 1 (-> (bst/node nil)
                   (bst/insert 1)
                   (bst/search 1)
                   :value)))

  (it "round-trips from the left"
    (should= 1 (-> (bst/node 42
                             :left (bst/node 3))
                   (bst/insert 1)
                   (bst/search 1)
                   :value)))

  (it "round-trips from the right"
    (should= 43 (-> (bst/make 42 3 1 44)
                    (bst/insert 43)
                    (bst/search 43)
                    :value)))

  (it "round-trips from the middle"
    (let [tree (-> (bst/make 42 3 1 6 44)
                   (bst/insert 5))]
      (should= 5 (:value (bst/search tree 5)))
      (should= 6 (:value (bst/search tree 6))))))

(describe "make"
  (it "provides a simple construction mechanism"
    (should= (bst/node 5
                       :left (bst/node 4
                                       :left (bst/node 3
                                                       :left (bst/node 2
                                                                       :left (bst/node 1))))
                       :right (bst/node 6))
             (bst/make 5 4 6 3 2 1))))

(describe "delete"
  (it "blows away a node without children"
    (should= nil (-> (bst/make 42)
                     (bst/delete 42)
                     (bst/search 42)
                     :value)))

  (it "blows away the root node with one child on the left"
    (let [tree (-> (bst/make 42 1)
                   (bst/delete 42))]
      (should= nil (:value (bst/search tree 42)))
      (should= 1 (:value (bst/search tree 1)))))

  (it "blows away the root node with one child on the right"
    (let [tree (-> (bst/make 42 100)
                   (bst/delete 42))]
      (should= nil (:value (bst/search tree 42)))
      (should= 100 (:value (bst/search tree 100)))))

  (it "blows away an inner node with one child on the left"
    (let [tree (-> (bst/make 42 100 99)
                   (bst/delete 100))]
      (should= nil (:value (bst/search tree 100)))
      (should= 99 (:value (bst/search tree 99)))))

  (it "blows away an inner node with one child on the right"
    (let [tree (-> (bst/make 42 99 100)
                   (bst/delete 99))]
      (should= nil (:value (bst/search tree 99)))
      (should= 100 (:value (bst/search tree 100)))))

  (it "blows away the root node with two children"
    (let [tree (-> (bst/make 42 1 100)
                   (bst/delete 42))]
      (should= nil (:value (bst/search tree 42)))
      (should= 1 (:value (bst/search tree 1)))
      (should= 100 (:value (bst/search tree 100)))))

  (it "blows away an inner node with two children"
    (let [tree (-> (bst/make 15
                             5 16
                             3 12 20
                             10 13 18 23
                             6 7)
                   (bst/delete 5))]
      (should= nil (:value (bst/search tree 5)))
      (should= 6 (:value (:left tree))))))



