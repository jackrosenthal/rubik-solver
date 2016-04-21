 Rubik's Cube Solver: Project Plan
===================================
Team SIGSEGV: Jack Rosenthal, Ariel Shlosberg, Nicholas Lantz

Introduction
------------
The Rubik's Cube, a colored puzzle cube with rotating sides, is a commonly
studied problem in Math & CS. For the CSCI-306 final project, team SIGSEGV
will be developing a 3D-solver application using the LWJGL graphics library.

Our project has three main challenges:
1. Designing a data structure to represent the cube in memory, and methods
   to operate on this structure: rotation and face selection.
2. Creating a 3D graphical display for the cube
3. Designing an "operator" algorithm to work on the cube and solve it.

Testing Strategy
----------------
Due to the nature of the project, only one main unit is testable, the data
structure for the cube and it's methods. However, given through testing of the
cube's methods, the remainder of the project should be manageable through
algorithmic correctness.

The cube's main method, do rotation will be tested, to the death.

Development Strategy
--------------------
The cube's data structure will be the primary development goal for Part I, as
everything else depends on it. This obviously follows that the graphics and
algorithm operator will be primarily part of Part II.

For the algorithm development, we can work on solving the cube incrementally,
solving a single layer at a time.

Furthermore, for a project of this scope, we will not be using Eclipse's build
system, but instead the popular Gradle build system. This leaves each of us
free to develop in the editor of our choice.

Rubric
------
Dr. Rader has asked us to provide a similar rubric to the "classmate
evaluation" rubric on the assignment page. Here is our proposed rubric:

    (5) Program is functional (despite our team name)
    (5) Visual appeal of the program - 3D display functionality
    (20) Our Challenge - cube can be solved
    (10) Random "set of problems" - equivalent to shuffling provides random
         cube
