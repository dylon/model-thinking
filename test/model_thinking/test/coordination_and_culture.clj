(ns model_thinking.test.coordination_and_culture
  (:use model_thinking.coordination_and_culture
        midje.sweet))

(let [leader   (list 5 3 2 1 1)
      follower (list 5 1 3 3 1)
      probability-of-interaction (probability-of-interaction leader follower)]
  (fact
    probability-of-interaction => 40/100))

(let [leader   (list 7 3 2 1 6 3)
      follower (list 4 3 6 1 6 2)
      probability-of-interaction (probability-of-interaction leader follower)]
  (fact
    probability-of-interaction => 50/100))

