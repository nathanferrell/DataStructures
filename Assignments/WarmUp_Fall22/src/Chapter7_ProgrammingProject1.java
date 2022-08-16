/*

Delivers Programming Project 1, Chapter 7, from BJP/5e.

Objective: add two integer numbers that are too large to be represented  as  int
or long primitives.

The largest int number that Java can represent is 2,147,483,647.  If we add 1 to
it, we expect to get  2,147,483,648; however in  Java we'll  get -2,147,483,648.
That's  because  the  language  has  no  way  to represent a  number larger than
2,147,483,647.  Any arithmetic operations would result to larger numbers,  yield
spurious values. We call them overflow errors.

Overflow errors occur when we use long primitives as well. The largest value we
can represent with a long primitive is  9,223,372,036,854,775,807. That's a very
big number indeed, about nine million trillions.  But even adding one to it will
result to a negative value instead of 9,223,372,036,854,775,808.

To handle numbers larger than the largest int or long, we must represent them as
Strings. And then we must find a way to add strings

"9223372036854775807"  and  "1" to produce string  "9223372036854775808". Notice
that if we simply executed

    "9223372036854775807" + "1"

we will get "92233720368547758071".

Let's start with simple operations: if we add "1" and "1" we want to get "2"; if
we add "3" and "4" we want to get "7"; etc. If we add "8" and "9" we want to get
"17". So we need to explore what it means to add two single-digit numbers.

When we add two single-digit numbers  we essentially produce  two values:  their
sum and their carry.  If the operands  (the numbers we are adding together)  are
sufficiently small, their carry is  0. For example, the carry for  3+5 is  0 and
their sum is 8. But for 9+8, the carry is 1 and the sum is 7. In essence, when
we add numbers with multiple digit each, we add pairwise digits together with
the carry from the addition of the previous digits. For example, adding the
numbers 28 and 39 involves the following steps.

    initialize carry to 0
    start with the least significant digits  // rightmost digits
    add 8 + 9 + carry, results to:
        carry = 1
        sum = 7
    move to the next pair of digits to the left
    add 2 + 3 + carry, results to:
        carry = 0
        sum = 6
    // keep moving left-wise, repeating the process above
    return:
        last carry
        sum digits from most to least significant
        // 0 6 7 = 28+39

The process above suggests that there is  a loop  to execute as long as the  two
numbers have digits to add.

 */

public class Chapter7_ProgrammingProject1 {

}  // class Chapter7_ProgrammingProject1