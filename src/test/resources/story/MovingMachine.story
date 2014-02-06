!-- Scenarios --

Scenario: 0. Application runs and read user input 

Given there is a grid of 4 x 4
And the machine is on square 2, 1, pointing at W
When the machine receives the command MRMMLMLMMLM 
Then it will move to square 1, 1 pointing to E


Scenario: 1. Make the machine 1 move to place xxx

Given there is a grid of 4 x 4
And the machine is on square 2, 1, pointing at W
When the machine receives the command MRMMLMLMMLM 
Then it will move to square 1, 1 pointing to E
