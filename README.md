Code Challenge - Moving Machines
==============================

[![Build Status](https://travis-ci.org/cfontes/CodeChallengeMovingMachines.svg)](https://travis-ci.org/cfontes/CodeChallengeMovingMachines)

Implementation in Java of a code challenge, where the objective was to make "machines" receive commands from a file and
move on a map using them.

* They have to move concurrenlty
* If they crash into each other the app should stop
* When spinning, the machine stay on the same cell.

The commands come in the format

    4 4
    1 3 N
    RMRMLMLM
    2 1 W
    MRMRMRMM

Where the first line represents the size of the map in movable squares.

The Second line is the declaration of a machine. This one would be in position {1,3} pointing North (Up)

The Third line are the commands to move it.

The possible commands are:

* L (spin 90 degrees left)
* R (spin 90 degrees right)
* M (move forward).



