(ns model_thinking.test.tipping_points
  (:use model_thinking.tipping_points
        midje.sweet))

(let [distribution [1]]
  
  (comment
    There is only one type that the tipping point can take)
  (fact
    (diversity-index distribution) => 1)
  
  (comment
    Out of the 1 type, we need 0 additional pieces of information to determine
    which type the tipping point takes)
  (fact
    (entropy distribution) => 0.0))

(let [distribution [1/2 1/2]]

  (comment
    There are two types that the tipping point is likely to take)
  (fact
    (diversity-index distribution) => 2)

  (comment
    Out of the two types, we need only 1 additional piece of information to
    determine which of them the tipping point takes)
  (fact
    (entropy distribution) => 1.0))

(let [distribution [1/4 1/4 1/4 1/4]]

  (comment
    There are four types that the tipping point is likely to take)
  (fact
    (diversity-index distribution) => 4)

  (comment
    Out of the four types, we need only 2 additional pieces of information to
    determine which of them the tipping point takes)
  (fact
    (entropy distribution) => 2.0))

(let [distribution [1/2 1/3 1/6]]
  (fact
    (diversity-index distribution) => 36/14))

(let [distribution [1/2 1/6 1/6 1/6]]
  (fact
    (diversity-index distribution) => 3))

