(ns model_thinking.tipping_points
  (:use [incanter core]))
"We discuss two types of tipping points:

  1. Direct Tips:
     Occur when a particular action tips that same dimension or variable. In
     the context of a war, a battle might tip the winning side from one to the
     other. The variable causes itself to tip.

  2. Contextual Tips:
     Something changes in the environment that makes it possible for something
     to happen. Something in the environment (an external force) causes it to
     move from one state to another.

The four states (classes) a system may be in follow:

  1. Sable (equilibrium?)
  2. Periodic
  3. Random
  4. Complex

A tipping point may cause the environment to move from one to any other, and one
may define a tip as a change in the likelihood of outcomes (not kinks)."

(defn Percolation-Model
  "This is a simple model used often in Physics and various other fields. In it,
  we define the probability P to be the probability that any of a set of squares
  gets filled in. Water can percolate only across consecutive, filled-in squares
  (whether they be horizontal, vertical, or diagonal to each other).
  
  When the probability is below a particular threshold, the ground will not
  percolate efficiently because there will not be enough consecutive, filled in
  squares.  But, once the probability because at least the threshold, the ground
  readily percolates.  This threshold is the tipping point of the model."
  
  ([] '()))

(defn R_0
  "Basic Reproduction Number: How quickly a disease will spread."
  
  ([τ c a]
   ($= c * τ / a)))

(defn Diffusion-Model
  "Two people meet, one has some disease and the other doesn't. This model
  predicts the probability that the other person will become infected. This
  function accepts three parameters:
  
    1. N   := Total number of people.
    2. W_t := Number of people with the disease, at time t.
    3. τ   := Probability of any two people meeting. 
    4. c   := Contact rate (i.e. how often people meet).
  
  It is important to note this this is a diffusion model, it does not have a
  tipping point."

  ([N W_t τ c]
   (let [P_infected ($= W_t / N)
         P_healthy ($= (N - W_t) / N)
         number_of_contacts ($= N * c)]
     ($= W_t + number_of_contacts * τ * P_infected * P_healthy))))

(defn SIS
  "Susceptible-Infected-Susceptible, from Epidemiology. In this model, you are
  susceptible to a disease, become infected, and then are susceptible again
  (say, if the disease mutates). There is also an SIR
  (Susceptible-Infected-Recovered) model that describes when you will never get
  the disease again.
  
  The first four parameters are the same as those for the Diffusion Model, but
  there exists a fifth parameter:
  
    5. a := Rate at which people are cured (healed, recovered).
  
  If people heal faster than they become infected, the disease will eventually
  die off and disappear.
  
  If (c * τ - a > 0), then the disease will spread. Otherwise, it will die off.
  This may be written in any one of the three, equivalent, following ways:
  
    1. c * τ - a > 0
    2. c * τ > a
    3. (c * τ) / a > 1
  
  If the above propositions pass, then the disease will spread.  The third
  alternative forms what is known as the Basic Reproduction Number, R_0.
  Therefore, if (R_0 > 1), the disease will spread.  This is because, in the
  simplified version of the full equation, each successive time step increases
  the number of currently infected individuals:
  
    W_t+1 = W_t + (some positive value)
  
  Notice here that we have a tipping point: 1.  If R_0 is less than 1, the
  disease does not spread; if it is greater than or equal to 1, it does."
  
  ([N W_t τ c a]
   (let [cured ($= a * W_t)]
     (- (Diffusion-Model N W_t τ c) cured))))
 
(defn diversity-index
  "Out of the distribution, determines approximately in how many ways the
  tipping point can go. This measures the degree of uncertainty, and is used
  often in Social Science and related fields.
  
  The diversity index explains the number of types that the tipping point is
  likely to take."
  
  ([distribution]
   (/ 1 (apply + (map #(* % %) distribution)))))

(defn entropy
  "Another measure of the degree of uncertainty, which is used often in Physics
  and Information Theory.
  
  Out of the possible outcomes {A,B,C,D}, the tipping point will be in either
  {A,B} or {C,D}.  Let us assume that we select {A,B}, then the tipping point
  will lie in either {A} or {B}.
  
  Entropy explains the amount of additional information needed to identify the
  actual type that a tipping point takes."
  
  ([distribution]
   (- (apply + (map #(* % (log2 %)) distribution)))))

