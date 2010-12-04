(ns clrs.sort.insertion-spec
  (:require [clrs.sort.insertion :as isort])
  (:use [speclj.core]))

(describe "insert"
  (it "works from an empty list"
    (should= '(1) (isort/insert < '() 1)))

  (it "works at start"
    (should= '(1 2 3) (isort/insert < '(2 3) 1)))

  (it "works at end"
    (should= '(1 2 3) (isort/insert < '(1 2) 3)))

  (it "works in middle"
    (should= '(1 2 3) (isort/insert < '(1 3) 2)))

  (it "works with a different ordering"
    (should= '(3 2 1) (isort/insert > '(3 1) 2))))

(describe "sort"
  (it "works if already sorted"
    (should= '(1 2 3 4 5) (isort/sort '(1 2 3 4 5))))

  (it "works if nearly sorted"
    (should= '(1 2 3 4 5) (isort/sort '(2 1 3 4 5)))
    (should= '(1 2 3 4 5) (isort/sort '(1 2 3 5 4))))

  (it "works if janked"
    (should= '(1 2 3 4 5) (isort/sort '(5 4 3 2 1)))))

(describe "reverse-sort"
  (it "works if sorted"
    (should= '(5 4 3 2 1) (isort/reverse-sort '(5 4 3 2 1))))

  (it "works if nearly sorted"
    (should= '(5 4 3 2 1) (isort/reverse-sort '(4 5 3 2 1)))
    (should= '(5 4 3 2 1) (isort/reverse-sort '(5 4 3 1 2))))

  (it "works if janked"
    (should= '(5 4 3 2 1) (isort/reverse-sort '(1 2 3 4 5)))))

(run-specs)
