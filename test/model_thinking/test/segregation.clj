(ns model_thinking.test.segregation
  (:use [model_thinking segregation]
        [incanter core]
        [midje sweet]))

;;; Consider a city consisting of four city blocks of equal population.  One
;;; block consists of all rich people.  Onee block consists of all poor people,
;;; and two blocks consist of half rich and half poor people.  What is the index
;;; of dissimilarity?
(let [blocks (matrix [[2 0]
                      [0 2]
                      [1 1]
                      [1 1]]) 
      index (measure-of-dissimilarity blocks)]
  (fact index => (roughly 0.5)))

;;; There are equal populations of 40 rich people and 40 poor people,
;;; distributed across 8, equally populated blocks.  In each block, half the
;;; population consists of rich people and half of poor people. 
(let [blocks (matrix [[5 5]
                      [5 5]
                      [5 5]
                      [5 5]
                      [5 5]
                      [5 5]
                      [5 5]
                      [5 5]]) 
      index (measure-of-dissimilarity blocks)]
  (fact index => (roughly 0.00)))

;;; There are equal populations of 40 rich people and 40 poor people,
;;; distributed across 8 blocks.  The 8 blocks are entirely segregated such that
;;; 4 of them consist entirely of rich people and 4 of them consist entirely of
;;; poor people.
(let [blocks (matrix [[10  0]
                      [10  0]
                      [10  0]
                      [10  0]
                      [ 0 10]
                      [ 0 10]
                      [ 0 10]
                      [ 0 10]])
      index (measure-of-dissimilarity blocks)]
  (fact index => (roughly 1.00)))

;;; There are 150 rich people and 90 poor people distributed across 24 blocks.
;;; 12 of the blocks consist entirely of 10 rich people each, 6 of the blocks
;;; consist entirely of 10 poor people each, and 6 of the blocks consist equally
;;; of 5 rich people and 5 poor people.
(let [blocks (matrix [[ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [ 10  0]
                      [  0 10]
                      [  0 10]
                      [  0 10]
                      [  0 10]
                      [  0 10]
                      [  0 10]
                      [  5  5]   
                      [  5  5]   
                      [  5  5]   
                      [  5  5]   
                      [  5  5]   
                      [  5  5]])
      index (measure-of-dissimilarity blocks)]
  (fact index => (roughly 0.80))) 

