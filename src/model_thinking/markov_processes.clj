(ns model_thinking.markov_processes
  (:use [incanter core]))
"Markov Processes state the following:
  Entities move between states according to transition probabilities.

Markov Convergence Theorem:
  As long four assumptions hold, a Markov process converges to a unique,
  equilibrium distribution. Those four assumptions are:
    1. Finite number of states
    2. Fixed transition probabilities
    3. Transitionability from one state to any other
    4. Not a simple cycle
  
  This means that the initial state doesn't matter (it doesn't matter where it
  begins), because as the number of transitions approaches infinity, the system
  will always converge to the same, unique, equilibrium distribution (of that
  system).

  This means that history doesn't matter, only the immediately preceding state.
  
  This means that intervening to force one state to change to another doesn't
  matter either (in the long run), because the system will still tend to the
  same equilibrium distribution.

  Some things to note:
    1. While intervening may not matter in the long run, it can make a
       difference for a while (yet, it is still temporary).
    2. The most effective way to change the resulting equilibrium is to alter
       the transitions probabilities.

  Therefore, to take an action to make things better in the long run, we must
  either show that it is not a Markov process, or more likely, show that the
  action will change the transition probabilities and not just the state. 

States:
  Entities move between those states according to transition probabilities.

Equilibrium Point:
  Nothing changes.

Statistical Equilibrium:
  World keeps churning but the distribution of types stays the same.

Example:
  Every year, 25% of people with standard phones switch to smart phones, and 5%
  of people with smart phones switch to standard phones.  In equilibrium, what
  percentage of people will have standard phones?

  A = [.75 .05], u = [percentage with standard phones] = [ p ]
      [.25 .95]      [ percentage with smart phones  ] = [1-p]

  .75p + .05 (1-p) = p <=> p = 1/6 = .1667 = 16.67%"

