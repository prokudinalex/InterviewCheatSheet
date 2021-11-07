# Big O asymptotic analysis
Big O can help to analyze how well the problem is solved.
It can be used to distinguish bad code from good code. 

## What is good code?
* Readable
* Scalable

Big O notation measures how the code can scale. How long an algorithm takes to run.

Because execution of the function could take different amount of time on different machines, Big O notation measures not exact **wall clock** time of the algorithm. 
It measures **amount of operations** which the algorithm performs.

# Complexity
Big O simply means how much does function or algorithm slow down if we grow it's input size. 
Here are examples of Big O measures:
* O(1)
* O(log n)
* O(n)
* O(n * log n)
* O(n^2)
* O(2^n)
* O(n!)

Where `n` is the input size of function/algorithm.

## O(1)
Algorithm takes constant time. Amount of operations are the same despite the input size. There are no loops.

## O(log n)
Logarithmic time. Usually searching algorithms take such time, if input is sorted.

## O(n)
Linear time. It's just `for` or `while` loops through `n` elements

## O(n * log n)
Log linear - usually sorting operations

## O(n^2)
Quadratic algorithms. Every element in a collection needs to be compared to any other element of the array. Two nested loops.

## O(2^n)
Exponential algorithms. Recursive functions which solves the problem of size `n`.

## O(n!)
Factorial time - separate loop for every element in the collection.