# Standard Coding Style for Team SIGSEGV

## Squirly braces

The opening squirly braces should be on the line they specify the scope for.
There should always be a space after anything before the opening squirly brace.

Good:

    for (int i = 0; i < 10; i++) {

Bad:

    for (int i = 0; i < 10; i++){
                               ^~~~ Missing a required space

Bad:

    for (int i = 0; i < 10; i++)
    {
    ^~~~ Needs to go on line above

Closing braces should be on a line of their own and properly indented:

Good:

    if (i == 3) {
        i += 4;
        j--;
    }
    else {
        j += 2;
        i++;
    }

Bad:

    if (i == 3) {
        i += 4;
        j--;
    } else {
    ^~~~ Needs to be on it's own line
        j += 2;
        i++;
    }

This helps keep the scope visible of each block, without sacrificing vertical
space.

If squirly braces can be avoided, they should be, so as long as it is still
clear the scope of what the code is doing. If the statement can be put on
the same line cleanly, it should be.

Good:

    if (i == 3) i++;
    for (int j = 0; j < 10; j++)
        fireMissle(j, i);

Bad:

    if (i == 3) {
        i++;            <~~~ Only a single statement, no braces!
    }
    for (int j = 0; j < 10; j++) {
        fireMissle(j, i);           <~~~ Same here!
    }


## Keywords & Method calling

Keywords are always followed by a single space before anything required in
parentheses. Methods being called should never have a space following.

Good:

    for (int i = 0; i < 10; i++) {
        callMethod(i);
        fireMissle(i);
    }

Bad:

    for(int i = 0; i < 10; i++) {
       ^~~~~~ Missing required space
        callMethod (i);
                  ^~~~~~ Erroneous space
        fireMissile(i);
    }

## Variables and methods

Generally, methods should be simple enough they will only need a few member
variables. As such, you should only name variables _short enough, but it is
clear what they do_. So a variable name of `currentIndex` inside the scope of a
small for loop is bad, as `i` says just as much about the variable.

Methods should be named _short enough, but it is clear what they do_. For
example, a method name of `doMissleFire` is dumb, as obviously methods _do
something_, so instead, name it `missleFire`.

When accessing member variables of an object, you should always use `this.` to
show that you are accessing the object. If a member variable of an object
can be accessed through a getter or setter, then you should use that, as
the getters and setters can then be used for things like bounds checking.

Methods on an object which take no parameters and return nothing should be
avoided.

## Whitespace

Spaces and newlines are the only whitespace allowed. The indentation width
should be 4 spaces, except when aligning multiline statements.

No erroneous whitespace should be used, such as at the end of a line or on
blank lines.

Good:

    if (i == 3) {
    ␣␣␣␣i += 4;
    ␣␣␣␣inDataShifter("Input string",
    ␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣p.getData(42),
    ␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣4.9
    ␣␣␣␣␣␣␣␣);
    }

Bad:

    if (i == 3) {
    ⇥   i += 4;
    ^~~~ Hard tabs should not be used
    ␣␣␣inDataShifter("Input string",␣
    ^~~~ Indentation of 3 spaces    ^~~~ Erroneous space at end of line
    ␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣p.getData(42),
    ␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣␣4.9
    ␣␣␣␣␣␣␣␣);
    ␣␣␣␣
    ^~~~ Erroneous spaces on empty line
    }

Blank lines should be added to the file between methods, and otherwise to
indicate a separate part of the code.

Good:

    ...code...

    /* This does something completely different */
    ...code...

Bad:

    ...code...

    ...more of the same stuff...

## Comments

`//` comments should be used only for single line comments, and `/* ... */`
can be used for either. When using `/* ... */` for multiline comments, make `*`
on the left side of the comment for indicating the scope of the comment.

There should always be a whitespace separating a `//` or `/*` and it's
associated text.

Commented out code should be removed before doing a git commit.

Good:

    ...code...

    // This code tests the functionality of ...
    ...code...

    /* This is some complex code that needs a multiline comment
     * Here is how it works:
     *      123456789012345678901234567890
     *      ^^^         ^^^         ^^^
     *      pqr-------->pqr-------->pqr-->
     */

Bad:

    //Hello
      ^~~~ Missing space before words
    // doMethod();
       ^~~~ Commented out code
    // This is a multiline
    // comment
       ^~~~ Use /* ... */
    /* Multiline
       comment */
    ^          ^~~~ Goes on next line
    \~~~ Needs a star

Comments should always be on their own line.

Good:

    /* Kills the enemy */
    fireMissle(j, i);

Bad:

    fireMissle(j, i);       // Kills the enemy

## Display size

All methods should generally fit on 2 standard ISO terminal screens (`80x24`),
meaning the last character on each line should have an index no greater than
79.

If a method is so long that it needs to be more than 48 lines long (excluding
comments), you should rethink how to write the method using helper methods.
