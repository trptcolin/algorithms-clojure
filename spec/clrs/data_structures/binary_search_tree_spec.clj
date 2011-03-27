(ns clrs.data-structures.binary-search-tree-spec
  (:require [clrs.data-structures.binary-search-tree :as bst])
  (:use [speclj.core]))

(describe "BST"
  (describe "search"
    (it "finds nothing in a fresh tree"
      (should= nil (-> []
                       (bst/search 42))))
    (it "finds the root element"
      (should= 42 (-> [42]
                      (bst/search 42))))
    (it "finds the left element"
      (should= 40 (-> [42 40]
                      (bst/search 40))))
    (it "finds the right element"
      (should= 44 (-> [42 40 44]
                      (bst/search 44))))
    (it "finds an element deeper in the tree"
      (should= 39 (-> [42 40 44 38 41 nil nil 37 39]
                      (bst/search 39))))
    (it "doesn't find missing elements"
      (should= nil (-> [42 40 44 38 41 nil nil 37 39]
                      (bst/search 46)))))
  (describe "minimum"
    (it "has none for a fresh tree"
      (should= nil (-> [] (bst/minimum))))
    (it "is the root for a one-node tree"
      (should= 1 (-> [1] (bst/minimum))))
    (it "gets the left node"
      (should= 4 (-> [5 4 6] (bst/minimum))))
    (it "recurses to the left"
      (should= 1 (-> [5
                      4 6
                      3 nil nil nil
                      2 nil nil nil nil nil nil nil
                      1]
                     (bst/minimum))))
  (describe "maximum"
    (it "has none for a fresh tree"
      (should= nil (-> [] (bst/maximum))))
    (it "is the root for a one-node tree"
      (should= 1 (-> [1] (bst/maximum))))
    (it "gets the left node"
      (should= 6 (-> [5 4 6] (bst/maximum))))
    (it "recurses to the right"
      (should= 8 (-> [5
                      4 6
                      3 nil nil 7
                      2 nil nil nil nil nil nil 8
                      1]
                     (bst/maximum)))))
    )

  )
