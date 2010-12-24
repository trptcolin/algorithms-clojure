(ns clrs.sort.heap-spec
  (:require [clrs.sort.heap :as hsort])
  (:use [speclj.core]))

(describe "max-heapify"
  (it "works in a trivial case"
    (should= [] (hsort/max-heapify [] 1)))
  (it "works where actions are required"
    (should= [16
              14 10
              8 7 9 3
              2 4 1]
             (hsort/max-heapify [16
                                 4 10
                                 14 7 9 3
                                 2 8 1]
                                1))))

(describe "build-max-heap"
  (it "builds a max-heap out of an unsorted array"
    (should= [16 14 10 8 7 9 3 2 4 1]
             (hsort/build-max-heap [4 1 3 2 16 9 10 14 8 7]))))


(describe "sort"
  (it "works if already sorted"
    (should= '(1 2 3 4 5) (hsort/sort '(1 2 3 4 5))))

  (it "works for an even number of elements"
    (should= '(1 2 3 4 5 6) (hsort/sort '(1 6 2 5 4 3))))

  (it "works if nearly sorted"
    (should= '(1 2 3 4 5) (hsort/sort '(2 1 3 4 5)))
    (should= '(1 2 3 4 5) (hsort/sort '(1 2 3 5 4))))

  (it "works if janked"
    (should= '(1 2 3 4 5) (hsort/sort '(5 4 3 2 1)))))

