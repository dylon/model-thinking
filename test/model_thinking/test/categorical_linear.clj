(ns model_thinking.test.categorical_linear
  (:use [model_thinking categorical_linear]
        [incanter core]
        [midje sweet]))

;;; R-squared

(let [pear   [1 100]  ; fruit
      cake   [2 250]  ; dessert
      apple  [1 90 ]  ; fruit
      banana [1 110]  ; fruit
      pie    [2 350]] ; dessert
  (fact
    (R-squared [pear cake apple banana pie]) => (roughly 0.902)))

;;; Linear Models

(let [grades-and-shoe-sizes [[1 1]
                             [2 5]
                             [4 9]]]
  (fact
    (let [linear-model (linear-model 2 0) ; a guess
          approximations (map linear-model (map first grades-and-shoe-sizes))
          values-and-approximations (map conj grades-and-shoe-sizes approximations)]
      (R-squared values-and-approximations #'variance #'squared-error) => >= 0.90))
  
  (fact
    (let [linear-model (linear-model (/ 8 3) -1)
          approximations (map linear-model (map first grades-and-shoe-sizes))
          values-and-approximations (map conj grades-and-shoe-sizes approximations)]
      (R-squared values-and-approximations #'variance #'squared-error) => >= 0.95)))

