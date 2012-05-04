(ns model_thinking.colonel_blotto)

"Colonel Blotto:
  1. Two players, each with T troops.
  2. N fronts (s.t. T >> N)
  3. Actions: allocations of troops across fronts
  4. Payoffs: number of fronts won
  5. The player who wins the most fronts wins the game
  6. The player with the most troops on a front wins that fronts

To win, one should frequently, randomly change one's strategy.

Claim: As the number of fronts increases, the country needs a larger relative
resource advantage to guarantee victory.

Novel Insight: If you're the weaker player, you want to add dimensions.

Country A has 44 troops and country B has 70 troops.  How many fronts are needed
so that B cannot guarantee at least a tie?  If B allocates her troops evenly
across the seven fronts, then player A could win by placing eleven troops on
each of four fronts.  If there are only 5 fronts, player B can put 14 troops on
each of the fronts and player A can put 15 on two fronts and 14 on one,
resulting in a tie.  With three fronts, B can place 23 on two fronts and 24 on
the third, so A cannot win.  Therefore, there must be at least 7 fronts.

Colonel Blotto [vs.] Skill/Luck:
  1. Dimensionality := If players are making high dimensional strategic
     decisions then it's more Blotto-like.
  2. Zero Sum := If actions are only good relative to other actions (invading
     from the east) then it may be more Blotto-like."



