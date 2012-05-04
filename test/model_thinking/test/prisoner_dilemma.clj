(ns model_thinking.test.prisoner_dilemma
  (:use model_thinking.prisoner_dilemma
        midje.sweet))

(let [b 10
      c 2]
  
  (comment Letting a shopper with fewer groceries cut in line, in Los Angelas)
  (comment p, here, is the probability that we meet again) 
  (let [p 1/1000
        cooperate? (directly-cooperate? c b p)]
    (fact
      cooperate? => false))
  
  (comment Letting a shopper with fewer groceries cut in line, in Iowa City)
  (comment p, here, is the probability that we meet again) 
  (let [p 1/2
        cooperate? (directly-cooperate? c b p)]
    (fact
      cooperate? => true)))

(let [b 5
      c 2
      k 2
      cooperate? (network-cooperate? c b k)]
    (fact
      cooperate? => true))

(let [b 5
      c 3
      k 2
      cooperate? (network-cooperate? c b k)]
  (fact
    cooperate? => false))

(let [j 0
      β 0.6]
  
  (let [X [0 1 1 1 1 1 1 1 1 1]
        payoff (collective-payoff j β X)]
    (fact
      payoff => (roughly 5.4)))
  
  (let [X [1 1 1 1 1 1 1 1 1 1]
        payoff (collective-payoff j β X)]
    (fact
      payoff => (roughly 5.0)))
  
  (comment
    It is typically better in one's interest if one does not cooperate while
    everyone else does.)
  (let [X (vec (repeat 10 1))
        cooperate? (collectively-cooperate? j β X)]
    (fact
      cooperate? => false)))

(let [cod-count 25
      cod-consumed 20
      cod-remaining (common-pool-resource cod-count cod-consumed)]

  (comment
    Eating 20 cod per period allows the system to remain balanced, so the diet
    can be sustained without depleting the cod supply)
  (fact
    cod-remaining => 25)
  
  (let [cod-count cod-remaining
        cod-consumed 21
        cod-remaining (common-pool-resource cod-count cod-consumed)]

    (comment
      Eating 21 cod per period, however, results in the next period having a
      population of cod less than the consumption demand [16 < 20], so the
      population cannot be sustained and the cod population will soon be
      depleted)
    (fact
      cod-remaining => 16)))

