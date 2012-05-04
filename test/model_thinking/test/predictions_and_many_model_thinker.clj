(ns model_thinking.test.predictions_and_many_model_thinker
  (:use model_thinking.predictions_and_many_model_thinker
        midje.sweet))

(let [S [50000 30000 43000]
      θ 41050 
      Crowd-Squared-Error (Crowd-Squared-Error S θ)]
  (fact
    (< Crowd-Squared-Error 5000) => true))

