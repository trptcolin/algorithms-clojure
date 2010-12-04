(ns clrs.sort.merge-spec
  (:require [clrs.sort.merge :as msort])
  (:use [speclj.core]))

(describe "merge"
  (it "works with both empty"
    (should= () (msort/merge () ())))

  (it "works with one empty"
    (should= '(1 2) (msort/merge '(1 2) ())))

  (it "works with the other empty"
    (should= '(1 2) (msort/merge '() '(1 2))))

  (it "works with one item in each"
    (should= '(1 2) (msort/merge '(1) '(2))))

  (it "works with multiple items"
    (should= '(1 2 3 4 5) (msort/merge '(1 3 5) '(2 4))))

  (it "works as reverse merge"
    (should= '(5 4 3 2 1) (msort/merge > '(5 3 1) '(4 2)))))

(describe "sort"
  (it "works with an empty list"
    (should= () (msort/sort ())))

  (it "works if already sorted"
    (should= '(1 2 3 4 5) (msort/sort '(1 2 3 4 5))))

  (it "works if nearly sorted"
    (should= '(1 2 3 4 5) (msort/sort '(2 1 3 4 5)))
    (should= '(1 2 3 4 5) (msort/sort '(1 2 3 5 4))))

  (it "works if janked"
    (should= '(1 2 3 4 5) (msort/sort '(5 4 3 2 1)))))

(describe "reverse-sort"
  (it "works if sorted"
    (should= '(5 4 3 2 1) (msort/reverse-sort '(5 4 3 2 1))))

  (it "works if nearly sorted"
    (should= '(5 4 3 2 1) (msort/reverse-sort '(4 5 3 2 1)))
    (should= '(5 4 3 2 1) (msort/reverse-sort '(5 4 3 1 2))))

  (it "works if janked"
    (should= '(5 4 3 2 1) (msort/reverse-sort '(1 2 3 4 5)))))

(run-specs)
