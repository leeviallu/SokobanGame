
# Sokoban

In this project I created a game known as Sokoban. The game tracks the time you spend on the levels and saves the best results to a file.


## Screenshots
<img src="https://github.com/leeviallu/SokobanGame/blob/main/gameplay.png" width="200" height="200" />

## Creating own levels
The game already has few maps for you to try, but if you want to try other maps, you can easily add them by editing the code.

Below is shown a snippet from Levels class which has initial levels for game.
```java
    String[][] initialLevels = {
            {
                    "#######",
                    "#. # .#",
                    "# $@$ #",
                    "#  #  #",
                    "#######"
            },
            {
                    "#####",
                    "#@  ##",
                    "#.$* #",
                    "#  # #",
                    "#    #",
                    "######"

            },
            {
                    "######",
                    "#@   ##",
                    "# $$  #",
                    "# #. .#",
                    "#     #",
                    "#######"
            },
            {
                    "  #### ",
                    "###  # ",
                    "#@ .$##",
                    "#   $ #",
                    "# #.  #",
                    "#     #",
                    "#######"
            },
            {
                    "#######",
                    "#@ ..##",
                    "# #$ ##",
                    "# #   #",
                    "#  $# #",
                    "#  *  #",
                    "#######"
            }
    };
```

