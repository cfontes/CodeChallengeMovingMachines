Scenario: 0. Make the machine 1 move
Given there is a grid of the size 4 and 4
And there is a machine on square 1 , 3 , pointing at N
When the machine receives the command RMRMLMLM
Then it will move to square 3 , 3 pointing to N


Scenario: 1. Make the machine 1 move
Given there is a grid of the size 4 and 4
And there is a machine on square 2 , 1 , pointing at W
When the machine receives the command RRMRMLMLM
Then it will move to square 0 , 1 pointing to S