(ns model_thinking.test.networks
  (:use model_thinking.networks
        midje.sweet))

(let [edges 100
      vertices 50
      degree (average-degree-of-network edges vertices)]
  (fact
    degree => 4))

