(ns model_thinking.economic_growth
  (:use [incanter core]))

(defn Rule-of-72
  "Divide the growth rate into 72 and it tells you (approximately) time for GDP
  to DOUBLE."

  ([growth-rate]
   ($= (72 / 100) / growth-rate)))

(defn continuous-compounding
  "Model used to calculate interest and other such things. What is interesting
  is that if you take this model as n approaches infinity, you get Euler's
  constant.  Below, r is the rate of growth, t is the time elapsed, and n
  converts r into an equivalent growth rate in the desired units.  Can you say,
  \"O.D.E.\"?

  For example, if your bank compounds interest at a rate of 0.1 percent every
  year, and they implement a system of continuous compounding which is
  calculated daily, r = 0.1 and n = 365 (365 days in a year).  Likewise, if they
  compute it hourly, the interest per hour would be 0.1 / (365 * 24), because
  there are 24 hours in a day, and typically 365 days in a year (note: this is
  converting from a yearly growth rate to an hourly one)."

  ([r t n]
   ($= (1 + r / n) ** (n * t)))

  ([r t]
   (continuous-compounding r t 1.0)))

(defn exponential-compounding
  "Simple way to calculate compound formulae (e.g. interest), where n is
  sufficiently close to infinity.  It is calculated as Euler's constant raised
  to the power of the product of the rate of growth and the time elapsed."

  ([r t]
   (exp (* r t))))

(defn investment
  "
  PARAMETERS:
    1. L_t := Workers at time t
    2. M_t := Machines at time t
    3. s   := Savings rate"
  ([L_t M_t s]
   ($= (sqrt L_t) * (sqrt M_t) * s)))

(defn depreciation
  "
  PARAMETERS:
    1. M_t := Machines at time t
    2. d   := Depreciation rate (the rate at which the machines wear out)"
  ([M_t d]
   ($= M_t * d)))

(defn net-output
  "Determines the net output of domestic products for the current period."

  ([L_t M_t s d]
   (let [investment (investment L_t M_t s)
         depreciation (depreciation M_t d)]
     ($= M_t + investment - depreciation))))

(defn GDP
  "Gross Domestic Product per capita, of the corrent period to the previous
  period."

  ([L_t M_t s d]
   (let [M_t+1 (net-output L_t M_t s d)]
     ($= (sqrt M_t) * (sqrt M_t+1) - M_t))))

(defn growth-rate
  "Determines how much an economy has grown from one period to the next."

  ([L_t M_t s d]
   (let [GDP (GDP L_t M_t s d)]
     ($= GDP / M_t))))

;;; To determine the equlibrium point, you must determine where the investment
;;; is equivalent to the depreciation, or
;;;
;;;   equlibrium <=> (investment = depreciation)
;;;
;;; Then, solve for M_t.

(defn Simple-Growth-Model
  "At some point in this model, because there is no innovation, growth stops. In
  other words, growth ceases without innovation.

  PARAMETERS:
    1. L_t := Workers at time t
    2. M_t := Machines at time t
    3. O_t := Output of Coconuts at time t
    4. E_t := Number consumed at time t
    5. I_t := Number invested at time t
    6. s   := Savings rate
    7. d   := Depreciation rate (the rate at which the machines wear out)

  ASSUMPTIONS:
    1. Output is increasing and concave in labor and machines.
       O_t = sqrt(L_t) * sqrt(M_t)
    2. Output is consumed or invested.
       O_t = E_t + I_t  and  I_t = s * O_t
    3. Machines can be built, but depreciate.
       M_t+1 = M_t + I_t - d * M_t

  DEFINITIONS:
    Concave
      The first machine is worth more than the second, the second more than the
      third, the third more than the fourth, etc.
    Diminishing Returns to Scale
      Economists' term for Concave, above."

  ([L_t M_t O_t E_t I_t s d]
   '()))

(defn Solow-Model
  "Growth can continue with changes to technology (innovation). This gives rise
  to the question, \"Where does innovation come from?\", and to the Engogenous
  Growth model, which states:

    \"Labor can go to output or technology/idea creation.\"

  In other words, investment can go to labor versus research, etc.

  PARAMETERS:
    1. L_t := Labor at time t
    2. K_t := Capital at time t
    3. A_t := Technology at time t
    4. O_t := Output

  EQUATION:
    O_t = A_t * K_t^β * L_t^(1-β)

  NOTE:
    If β=1/2, then this is just the square root function.
    If β>1/2, then capital matters a little bit more.
    If β<1/2, then capital matters a little bit less.

  DEFINITIONS:
    Innovation Multiplier
      Labor and capital => more productive
      Incentives to invest in more capital

  QUESTIONS:
    1. If the output doubles from two, is the effect additive (i.e. 2+2) or is
       the effect multiplicative (i.e. 2*2 = 2^2)?")

