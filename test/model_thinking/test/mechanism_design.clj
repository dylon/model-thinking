(ns model_thinking.test.mechanism_design
  (:use model_thinking.mechanism_design
        midje.sweet))

(comment
  Molly is a graphic designer who makes advertisements.  She can either make
  high quality advertisements or poor quality advertisements.  If she puts forth
  effort e=1, she makes high quality advertisements with probability=0.75 .  If
  she puts forth effort e=0, she makes high quality advertisements with
  probability=0.25 .  The cost of 1 unit of effort = 0.5 .  She is paid M if the
  advertisement is high quality and N if the advertisement is poor quality.  For
  which of the following M and N will she put forth effort e=1?)
(let [p_1 3/4
      p_0 1/4
      c 1/5]
  
  (let [M 1/2
        N 1/4]

    (comment
      The amount of money she will receive if the advertisement is high
      quality is not enough to get her to put forth a good effort)
    (fact
      (incentive-compatible-income? c p_1 M) => false)

    (comment
      The amount of money she will receive if the advertisement is low quality
      is not enough for her to put for an effort)
    (fact
      (incentive-compatible-income? c p_0 N) => false))
  
  (let [M 8/10
        N 0]

    (comment
      She will receive enough compensation to put forth a good effort if the
      advertisement is of good quality)
    (fact
      (incentive-compatible-income? c p_1 M) => true)

    (comment
      She will not receive enough compensation to put forth an effort should the
      advertisement not be good quality)
    (fact
      (incentive-compatible-income? c p_0 N) => false))
  
  (let [M 2
        N 1/2]

    (comment
      She will receive enough compensation to put forth a good effort if the
      advertisement is of a good quality)
    (fact
      (incentive-compatible-income? c p_1 M) => true)

    (comment
      She will receive enough compensation to put forth a good effort if the
      advertisement is bad quality)
    (fact
      (incentive-compatible-income? c p_0 N) => true))
  
  (let [M 0
        N 8/10]

    (comment
      She will not receive enough compensation to put forth a good effort if the
      advertisement is a good quality)
    (fact
      (incentive-compatible-income? c p_1 M) => false)

    (comment
      She will receive enough compensation to put forth a good effort if the
      advertisement is bad quality)
    (fact
      (incentive-compatible-income? c p_0 N) => true)))

(let [other-bid 60
      your-bid 80
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]
  (fact
    net => 20))

(let [other-bid 75
      your-bid 80
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]
  (fact
    net => 5))

(let [other-bid 85
      your-bid 80
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]
  (fact
    net => 0))

(let [other-bid 60
      your-bid 90
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]

  (comment
    Even though you bid 90, you value the item at 80 and pay 60)
  (fact
    net => 20))

(let [other-bid 75
      your-bid 90
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]
  (fact
    net => 5))

(let [other-bid 85
      your-bid 90
      your-value 80
      net (net-value-of-second-highest-bid other-bid your-bid your-value)]
  (fact
    net => -5))

(let [Cost 80
      V0 40
      V1 50
      V2 20
      V [V0 V1 V2]
      i 0
      cost_i (Clarke-Groves-Vickery-Pivot-Mechanism Cost V i)]
  (fact
    cost_i => 10))

(comment
  Three workers are deciding whether to buy a new couch for the lounge.  The
  couch would cost $700.  Person 1 values a new couch at $100, Person 2 values a
  new couch at $500, and Person 3 values a new couch at $250.  Using the
  Clarke-Groves-Vickrey mechanism, do they buy the couch?  How much does each
  person pay?)
(let [Cost 700
      V1 100
      V2 500
      V3 250
      V [V1 V2 V3]
      C1 (Clarke-Groves-Vickery-Pivot-Mechanism Cost V 0)
      C2 (Clarke-Groves-Vickery-Pivot-Mechanism Cost V 1)
      C3 (Clarke-Groves-Vickery-Pivot-Mechanism Cost V 2)
      Clarke-Groves-Vickrey-Costs-per-Individual [C1 C2 C3]
      do-project? (do-project? Cost Clarke-Groves-Vickrey-Costs-per-Individual)]
  (fact
    C1 => 0)
  (fact
    C2 => 350)
  (fact
    C3 => 100)
  (comment
    They should choose not to purchase the couch, because the cumulative
    expected cost for all the individuals is less than the cost of the couch)
  (fact
    do-project? => false))

