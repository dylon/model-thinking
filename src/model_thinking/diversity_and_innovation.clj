(ns model_thinking.diversity_and_innovation
  (:use [incanter core]))
"Problem Solving: How individuals and teams go about solving problems.  In this
unit, there are two main themes:
  1. Diversity
  2. Power of Recombination

The first topic discussed is that of perspectives.  A perspective is how you
represent a problem.

The second topic discussed is that of heuristics.  Heuristics are how you \"move
on the landscape\" of the function.  Examples include picking random points and
choosing the best of those chosen, as well as hill climbing.  Popular heuristics
include:
  1. Do the Opposite
  2. Big Rocks First!
  3. Little Rocks First! (e.g. SRTF)

People have perspectives and heuristics.  Teams are groups of people who
generally approach problem solving better than any individual of the team,
alone, because there exists diversity among the members of the team.  Each
member brings with him his own perspectives and heuristics, and together they
form a proposed solution.

The intersection of the local optima of each of the member of a team forms the
local optima for that team.  The only local optima on which a team can get stuck
are local optimima shared by all of its members.

What make teams great?
  1. Diversity (most important!)
  2. Communication
  3. Error (in interpreting the value of a solution)

After we discuss teams, we shall discuss recombination.  Multiple (different)
solutions to (sub)problems may be recombined to form an even better solution.
Diversity brings about innovation, and recombination improves upon that
innovation.

Given n ideas, by recombining k of them, we have (n choose k) possible
recombinations.  This may be why we can sustain growth: we have so many ideas
that there are more possible recombinations than we can perform.  This is known
as the theory of Recombinant Growth (new ideas are developed all the time).

There is a notion of the \"Gifts of Athena\", which describes how the advent of
technologies (universities, books, the Internet, etc.) have allowed ideas to
transfer more easily from one area to the next.  This is how new ideas,
technologies, and policies come about.

DEFINITIONS:
  1. Problem:
    You take an action, a, and there is a function, F, that gives the value to
    you of that action F(a).  The variable, a, represents a proposed soluion to
    a problem, and the function, F, returns how good that solution is.
  2. Perspective:
    A representation (encoding) of the set of all possible solutions.
  3. Local Optima (Minima):
    An action, a, such that neighboring actions have lower values.  Better
    perspectives have fewer local optima (worse perspectives have many local
    optima).  The best perspectives have concave functions such that there is
    only one local optimum: the global optimum.
  4. Savant Existence Theorem:
    For any problem there exists a perspective that creates a Mt. Fuji Landscape
    (in fact, it is often the case that many do)
  5. Bad Perspectives:
    For N alternatives, there are N! ways to create one dimensional landscapes.
  6. Hillclimb:
    Move locally to better points.
  7. No Free Lunch (Wolpert & McCready):
    All algorithms that search the same number of points with the goal of
    locating the maximum value of a function defined on a finite set perform
    exactly the same when averaged over all possible functions.  In other words,
    unless you know something about the problem being solved, no algorithm or
    heuristic performs better than any other.
  8. Exaptation:
    Technology developed for one purpose gets used for another (unintented)
    purpose.  This is an idea from Biology."



