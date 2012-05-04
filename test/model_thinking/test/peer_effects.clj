(ns model_thinking.test.peer_effects
  (:use [model_thinking peer_effects]
        [midje sweet]))

;;; There are 5 friends contemplating whether to wear purple hats.  Each of
;;; their thresholds regarding whether one will wear a purple hat depends on a
;;; certain number of the others wearing a purple hat.
(let [thresholds [0 1 2 2 2]
      collective (granovetter thresholds)]
  (fact
    collective => [true true true true true]))

(let [thresholds [0 1 2 3 4]
      collective (granovetter thresholds)]
  (fact
    collective => [true true true true true]))

;;; Suppose that there are ten people who have the following thresholds for
;;; joining a volunteer project: Two will volunteer even if no one else does.
;;; Six require five others.  And two will volunteer so long as anyone else
;;; does.  How many people will volunteer total?
(let [thresholds [0 0 5 5 5 5 5 5 1 1]
      collective (granovetter thresholds)]
  (fact
    collective => [true true false false false false false false true true]))

