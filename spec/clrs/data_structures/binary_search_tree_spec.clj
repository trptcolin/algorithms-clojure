(ns clrs.data-structures.binary-search-tree-spec
  (:require [clrs.data-structures.binary-search-tree :as bst])
  (:use [speclj.core]))

(describe "search"
  (it "finds nothing in a fresh tree"
    (should= nil (-> (bst/make [])
                     (bst/search 42))))
  (it "finds the root element"
    (should= 42 (-> (bst/make [42])
                    (bst/search 42))))
  (it "finds the left element"
    (should= 40 (-> (bst/make [42 40])
                    (bst/search 40))))
  (it "finds the right element"
    (should= 44 (-> (bst/make [42 40 44])
                    (bst/search 44))))
  (it "finds an element deeper in the tree"
    (should= 39 (-> (bst/make [42 40 44 38 41 nil nil 37 39])
                    (bst/search 39))))
  (it "doesn't find missing elements"
    (should= nil (-> (bst/make [42 40 44 38 41 nil nil 37 39])
                    (bst/search 46)))))
(describe "minimum"
  (it "has none for a fresh tree"
    (should= nil (-> (bst/make [])
                     (bst/minimum))))
  (it "is the root for a one-node tree"
    (should= 1 (-> (bst/make [1])
                   (bst/minimum))))
  (it "gets the left node"
    (should= 4 (-> (bst/make [5 4 6])
                   (bst/minimum))))
  (it "recurses to the left"
    (should= 1 (-> [5
                    4 6
                    3 nil nil nil
                    2 nil nil nil nil nil nil nil
                    1]
                   (bst/make)
                   (bst/minimum))))
(describe "maximum"
  (it "has none for a fresh tree"
    (should= nil (-> [] (bst/make) (bst/maximum))))
  (it "is the root for a one-node tree"
    (should= 1 (-> [1] (bst/make) (bst/maximum))))
  (it "gets the left node"
    (should= 6 (-> [5 4 6] (bst/make) (bst/maximum))))
  (it "recurses to the right"
    (should= 8 (-> [5
                    4 6
                    3 nil nil 7
                    2 nil nil nil nil nil nil 8
                    1]
                   (bst/make)
                   (bst/maximum)))))

(describe "insert"
  (it "round-trips from a fresh tree"
    (should= 1 (-> []
                   (bst/make)
                   (bst/insert 1)
                   (bst/search 1))))
  (it "round-trips from the left of a populated tree"
    (should= 1 (-> [42 3]
                   (bst/make)
                   (bst/insert 1)
                   (bst/search 1))))
  (it "round-trips from the right of a populated tree"
    (should= 43 (-> [42
                      3 44
                      1]
                   (bst/make)
                   (bst/insert 43)
                   (bst/search 43)))))
)

