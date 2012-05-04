(ns model_thinking.game_of_life
  "This game was developed by a Cambridge mathematician named, John Conway, who
  specializes in the field of Group Theory.  It uses a Go board, which is
  partitioned and on which dark and light squares are placed.  This game belongs
  to a class of models known as Cellular Automata models.
  
  1. Light Square --> Dead or off
  2. Dark Square --> Alive or on
  
  Rules:
    1. If you're currently off, you can only come on if exactly three of your
       neighbors are on.
    2. If you're currently on, and there's fewer than two neighbors on (0 or 1),
       you die of boredom because there is noting going on.
    3. If you're currently on, and have more than three neighbors on, you die of
       suffocation because there are too many people around who consequently are
       using up too many resources for you to survive.
    4. If you are currently alive and, two or three, of your neighbors are
       alive, then you may stay alive.
  
  We begin the game by seeding the board.  If none of the rules for life
  persist, every cell will die off.
  
  NOTE: The figures that \"glide\" across the space are known as \"gliders\"
  
  From the Game of Life, we get:
    1. Self Organization:
       Patterns appear without a designer (gliders, blinkers, glider guns, etc.)
    2. Emergence:
       Functionalities appear: gliders, glider guns, counters, computers
    3. Logic Right:
       Simple rules produce incredible phenomena")

