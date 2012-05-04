(ns model_thinking.lyapunov_functions)

"If a model can be described in terms of a Lyapunov function, then it is
guaranteed to go to equilibrium (the converse is not necessarily true).

Let F(x) be a Lyapunov function.  Then, there exist three conditions which must
be met:
  1. It has an optimum value (e.g. a maximum in Economic models or a minimum in
     Physics models).
  2. There exists some positive constant k, such that for all x_t+1 != x_t,
     F(x_t+1) > F(x_t) + k. In other words, if the point x_t is not fixed such
     that a successive point x_t+1 != x_t, then F(x_t+1) increases by at least k
     over F(x_t).
     a. My note, not from class: This may need to reversed for finding minima:
        F(x_t+1) < F(x_t) - k
  3. A some point, equilibrium will arise such that x_t = x_t+1

It is important to note that without the minimum value k, we could wind up with
Zeno's paradox, in which the value continues increasing at a decreasing rate,
and thus never reaches its goal.

An example of a Lyapunov function would be coworkers freely exchanging chairs.
In order for any two coworkers to exchange their chairs, each must believe
obtaining the other's chair will make him happier. Therefore, the total
happiness will increase each time an exchange is made, since an exchange made by
person A with person B is unlikely to affect the happiness of person C.  If we
let the total happiness be the Lyapunov function, this model will eventually
converge to a point in which everyone is happy. It may be a local optima (e.g.
if person C wants person A's chair, but person A does not want person C's chair,
no exchange will be made thus leaving them both at approximately the same level
of happiness as if no exchange was attempted), but nonetheless, it will
converge."

