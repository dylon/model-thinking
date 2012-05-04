(ns model_thinking.test.path_dependence
  (:use model_thinking.path_dependence
        midje.sweet))

(let [model [:blue :red]
      odds-of-blue (odds-of-picking :blue model)]

  (fact
    odds-of-blue => 1/2)
  
  (let [probability (/ (* 1 2 1 2) (* 2 3 4 5))]
    (#_ (The probabilities should be the same, regardless the order of the
         parameters.))

    (let [polya-p (Polya-P model [:red :red :blue :blue])]
      (fact
        polya-p => probability))
  
    (let [polya-p (Polya-P model [:red :blue :red :blue])]
      (fact
        polya-p => probability))
    
    (let [polya-p (Polya-P model [:blue :blue :red :red])]
      (fact
        polya-p => probability))
    
    (let [polya-p (Polya-P model [:blue :red :blue :red])]
      (fact
        polya-p => probability))))

(let [model (Bernoulli 2 :red :blue)
      odds-of-red (odds-of-picking :red model)
      odds-of-blue (odds-of-picking :blue model)]

  (fact
    odds-of-red => 1/2)

  (fact
    odds-of-blue => 1/2))

(let [model (Bernoulli {:red 2
                        :blue 3})
      odds-of-red (odds-of-picking :red model)
      odds-of-blue (odds-of-picking :blue model)]

  (fact
    odds-of-red => 2/5)

  (fact
    odds-of-blue => 3/5)
  
  (let [color :blue
        model (Polya model color)
        odds-of-red (odds-of-picking :red model)
        odds-of-blue (odds-of-picking :blue model)]
    
    (fact
      odds-of-red => 2/6)
    
    (fact
      odds-of-blue => 4/6))

  (let [color :red
        model (Polya model color)
        odds-of-red (odds-of-picking :red model)
        odds-of-blue (odds-of-picking :blue model)]
    
    (fact
      odds-of-red => 3/6)
    
    (fact
      odds-of-blue => 3/6))
  
  (let [color :blue
        model (Balance model color)
        odds-of-red (odds-of-picking :red model)
        odds-of-blue (odds-of-picking :blue model)]
    
    (fact
      odds-of-red => 3/6)
    
    (fact
      odds-of-blue => 3/6))
  
  (let [color :red
        model (Balance model color)
        odds-of-red (odds-of-picking :red model)
        odds-of-blue (odds-of-picking :blue model)]
    
    (fact
      odds-of-red => 2/6)
    
    (fact
      odds-of-blue => 4/6))
  
  (let [periods 10
        cardinality (count model)
        additional-elements (/ (* periods (+ 1 periods)) 2)
        cardinality (+ cardinality additional-elements)
        model (Sway model periods)]
    (fact
      (count model) => cardinality))) 

(let [check (fn [results index]
              (when (< index (count results))
                (fact
                  (Tent-Map (results (dec index))) => (roughly (results index)))
                (recur results (inc index))))]

  (let [results [0.4321
                 0.8642
                 0.2716
                 0.5432
                 0.9136
                 0.1728
                 0.3456
                 0.6912
                 0.6176
                 0.7648
                 0.4704
                 0.9408
                 0.1184
                 0.2368]]
    (check results 1))
  
  (let [results [0.4322
                 0.8644
                 0.2712
                 0.5424
                 0.9152
                 0.1696
                 0.3392
                 0.6784
                 0.6432
                 0.7136
                 0.5728
                 0.8544
                 0.2912
                 0.5824]]
    (check results 1)))

(let [model (shuffle
              (cons :electric (repeat 5 :gas)))]
  
  (let [model (Gas-Electric model :electric)
        odds-of-electric (odds-of-picking :electric model)
        odds-of-gas (odds-of-picking :gas model)]
    (fact
      odds-of-electric => 2/8)
    (fact
      odds-of-gas => 6/8))
  
  (let [model (Gas-Electric model :gas)
        odds-of-electric (odds-of-picking :electric model)
        odds-of-gas (odds-of-picking :gas model)]
    (fact
      odds-of-electric => 1/16)
    (fact
      odds-of-gas => 15/16)))

