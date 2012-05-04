(ns model_thinking.path_dependence)

"Basic Urn Model:
  Urn contains balls of various colors.  The outcome equals the color of the
  ball selected.

Bernoulli Model:
  The simplest Urn model in which there is a fixed number of balls in the Urn.
  As well, the probability of drawing a ball of any color on each draw is
  independent of the previously drawn balls, because each time a ball is drawn,
  it is put back in the Urn.

Polya Process:
  Begin with an earn with two balls: one red and one blue.select a ball, put it
  back, and add another that is the same color as the selected ball.  This will
  result in a path-dependent model, because the probability of selecting a ball
  of any color upon each successive draw depends on the colors of the previously
  selected balls.

  Result 1:
    Any probability of red balls is an equilibrium and all are equally likely.
  
  Result 2:
    All sequences of events (i.e. outcomes) are equally likely.

Balancing Process:
  When we select one ball, we put it back and add a ball of the opposite color.

  Result:
    The balancing process converges to equal percentages of the two colors of
    balls.

Period Outcomes:
  Color of ball in a given period.

Equliibrium:
  Percentage of red balls in long run.

Path Dependent:
  Outcome probabilities depend upon the sequence of past outcomes.

Path Dependent Outcomes:
  Color of ball in a given period depends on the path.

Path Depenent Equilibrium:
  Percentage of red balls in the long run depends on the path.

History can matter at each moment but not have any impact in the long run.
  Examples:
    1. Manifest Destiny
    2. Railroads

PHAT Dependent:
  Outcome probabilities depend upon past outcomes, but not their order.  This
  differs from Path dependence in that consecutive outcomes do not depend on the
  order of preceding outcomes.

  Polya is PHAT Dependent, not Path Dependent:
    Outcome probabilities do not depend upon the order in which outcomes
    occurred.  They only depend on the set of outcomes that occurred.

The Sway Process:
  In period t, add a ball of same color as selected ball and add
    2^(t-s) - 2^(t-s-1)
  balls of the color chosen in each period s < t.

  Example:
    In period 1: Select a blue ball and add another blue ball.
    In period 2: Select a red ball, add another red ball, and add an additional
      blue ball for the blue ball selected in period 1.
    In period 3: Select a blue ball, add another blue ball, add a red ball for
      the one drawn in period 2, and add another blue ball for the one drawn in
      period 1.
    In period 4: Select a red ball, add another red ball, add a blue ball for
      the one drawn in period 3, add a red ball for the one drawn in period 2,
      and add another blue ball for the one drawn in period 1.

  If the past takes on more weight over time then you can get full path
  dependence.

Recursive Function Characteristics:
  Outcome at time t, x(t)
  Outcome function F: X -> X
  Example: F(X) = x + 2 => F(2) = 1 + 2 = 3

Chaos:
  Extreme Sensitivity To Initial Condisionts (ESTIC)

  If initial points x and x' differ by even a tiny amount after many iterations
  of the outcome function, they differ by arbitrary amounts.  In other words,
  the initial value matter a lot!

Independent:
  Outcome doesn't depend on starting point or what happens along the way.

Initial Conditions:
  Outcome depends on starting state.

Increasing Returns:
  The more an individual does something, the more others will want to, too.

  With the Gas/Electric model, it is possible to have increasing returns without
  path dependence.  With the Symbiots model, it is possible to have path
  dependence, but not have increasing returns.

Externalities:
  Interdependencies between decisions.

  Example: China and Russia trading for nuclear weapons; they become happy while
  the US and many others become sad.

  Positive externalities lead to fewer path dependencies than negative
  externalities."

(defn odds-of-picking [color model]
  (let [color-count (->> model (filter #(= color %)) count)
        total-count (count model)]
    (/ color-count total-count)))

(defn Bernoulli

  ([size color]
   (repeat size color))

  ([per-color color & colors]
   (let [head (Bernoulli per-color color)]
     (shuffle
       (if (empty? colors) head
         (let [tail (apply Bernoulli per-color colors)]
           (reduce conj tail head))))))
  
  ([quantities]
   (if (empty? quantities) '()
     (->> (seq quantities)
          (map reverse)
          (map #(apply Bernoulli %))
          flatten
          shuffle))))

(defn Polya 
  ([model color]
   (shuffle
     (cons color model)))

  ([model]
    (let [color (rand-nth model)]
      (Polya model color))))

(defn Polya-P
 "Returns the probability that the colors will be selected in the given order,
 upon successive draws of the Polya process."
   
 ([model colors cumulative-probability]
  (if (empty? colors) cumulative-probability
    (let [color (first colors)
          colors (rest colors)
          current-probability (odds-of-picking color model)
          cumulative-probability (* cumulative-probability current-probability)
          model (Polya model color)]
      (recur model colors cumulative-probability))))

 ([model colors]
   (Polya-P model colors 1)))

;(defn Polya-D
  ;"Returns the diversity index of the expanded Polya process."
  ;([model colors]
   ;(let [colors (reduce #(apply conj ))])))

(defn Balance
  "Designed to be used on a model with binary colors :red and :blue"
  ([model color]
   (shuffle
     (cons (if (= color :red) :blue :red) model)))
  ([model]
   (let [color (rand-nth model)]
     (Balance model color))))

(defn Sway
  ([model periods drawn]
   (if (zero? periods) model
     (let [color (rand-nth model)]
       (recur
         (->> (cons color model)
              (list drawn)
              flatten
              shuffle)
         (dec periods)
         (cons color drawn)))))
  ([model periods]
   (Sway model periods '())))

(defn- probability? [x]
  (and
    (>= x 0.0)
    (<= x 1.0)))

(defn Tent-Map [X]
  {:pre [(probability? X)]
   :post [(probability? %)]}
  "An example of a chaos model, if the inputs X differ slightly, very different
  results will be returned."
  (if (< X 1/2)
    (* 2 X)
    (- 2 (* 2 X))))

(defn- vehicle-category? [category]
  (let [categories #{:gas :electric}]
    (categories category)))

(defn Gas-Electric
  ([model category]
   {:pre [(every? vehicle-category? model)
          (vehicle-category? category)]
    :post [(every? vehicle-category? %)]}
   (shuffle
     (cond
       (= category :gas) (apply conj model (repeat 10 :gas))
       (= category :electric) (conj model :gas :electric)))))

(defn- symbiot? [color]
  (let [symbiots #{:red :green :yellow :blue}]
    (symbiots color)))

(defn Symbiots
  "This is essentially just the Polya process, renamed.  Let R* be the set of
  actions corresponding to :red and :green, and let B* be the set of actions
  corresponding to :blue and :yellow.  If an element of R* is picked, another
  element of R* will be added.  If an element of B* is picked, another element
  of B* will be added.
  
  Not only that, but each action, individually, is a Balance process.  At a high
  level, abstract view, this appears to be a Polya process; at a low level, more
  concrete view, this appears to be a piecewise function of Balance processes."
  ([model color]
   {:pre [(every? symbiot? model)
          (symbiot? color)]
    :post [(every? symbiot? %)]}
   (shuffle
     (case color
       :red (cons :green model)
       :green (cons :red model)
       :blue (cons :yellow model)
       :yellow (cons :blue model)))))

;(defn rank-by-externalities [labels externalities]
  ;(defn pair-wise-score
    ;([a b]
     ;((externalities a) b))
    ;([a b & features]
     ;(loop [score (pair-wise-score a b)
            ;c (first )]))
    ;([a]
     ;(pair-wise-score a a)))
  ;(def score (memoize (fn ([a] (score a a))))))

