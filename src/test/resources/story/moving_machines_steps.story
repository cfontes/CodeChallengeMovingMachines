Scenario: 0. Make the machine 1 move
Given one

Scenario: 1. Make the machine 1 move
Given there is a grid of the size 4 and 4
And there is a machine on square 2 , 1 , pointing at W
When the machine receives the command MRMMLMLMMLM 
Then it will move to square 1 , 1 pointing to E