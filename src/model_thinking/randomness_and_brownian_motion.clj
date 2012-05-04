(ns model_thinking.randomness_and_brownian_motion)

"Paradox of Skill:
  If a large number of highly skilled individuals compete with each other, the
  outcome is largely determined by luck, not skill, which is humorously
  paradoxical.

Binary Random Walk Model:

  Result 1:
    After N (even number) of flips, you should expect to be at 0 (the expected
    value is zero).
  
  Result 2:
    For any number K, a random walk will pass both -K and +K an infinite number
    of times.

  Result 3:
    For any number K, a random walk will have a streak of K heads (and K tails)
    an infinite number of times.

  Regression to the Mean:
    A group that did well for a short time, should be average in the long run.

Normal Random Walk:
  Normal random walks follow a normal (Gaussian) distribution, meaning that
  small walks --- those with values close to the mean of the bell curve --- will
  be frequent.  Larger walks (say, those between one and two standard deviations
  from the mean) will not be rare.  Large jumps, however, such as those that
  fall outside of three standard deviations from the mean, will only occur 0.26%
  of the time.  However, we still expect some huge jumps to occur (very
  rarely).

Efficient Market Hypothesis:
  Prices reflect all relevant information.  Therefore, it's impossible to beat
  the market.

  The efficient market hypothesis is associated with the idea of a \"random
  walk\", which is a term loosely used in the finance literature to characterize
  a price series where all subsequent price changes represent random departures
  from previous prices.  The logic of the random walk idea is that if the flow
  of information is unimpeded and information is immediately reflected in stock
  prices, then tomorrow's price change will reflect only tomorrow's news and
  will be independent of the price changes today.  But news is by definition
  unpredictable and, thus, resulting price changes must be unpredictable and
  random.

Finite Memory Random Walk:
  ...
  V_10 = X_10 + X_9 + X_8 + X_7 + X_6
  V_11 = X_11 + X_10 + X_9 + X_8 + X_7
  V_12 = X_12 + X_11 + X_10 + X_9 + X_8
  ...
"

(defn outcome [luck skill a]
  "A is a weight that determines how important the random variable luck is.  The
  higher the value of a, the more important luck is in the outcome of some
  event; the lower the value of a, the less important luck is and the more
  important skill is in the outcome of an event."
  {:pre [(>= a 0.0) (<= a 1.0)]}
  (+ (* a luck) (* (- 1.0 a) skill)))

