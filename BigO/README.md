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
* `O(1)`
* `O(log n)`
* `O(n)`
* `O(n * log n)`
* `O(n^2)`
* `O(2^n)`
* `O(n!)`

Where `n` is the input size of function/algorithm.

## O(1)
Algorithm takes constant time. Amount of operations are the same despite the input size. There are no loops.
It could be pushing to an array, getting an element by index, adding child element, etc.
```java
    int[] array = {1, 2, 3, 4, 5};
    System.out.println(array[0]);
```
## O(log n)
Logarithmic time. Usually searching algorithms take such time, if input is sorted.
```java
    // TODO: add search example
```

## O(n)
Linear time. It's just `for` or `while` loops through `n` elements
```java
    int[] array = {1, 2, 3, 4, 5};
    for (int item : array) {
        System.out.println(item);
    }
```
## O(n * log n)
Log linear - usually sorting operations

## O(n^2)
Quadratic algorithms. Every element in a collection needs to be compared to any other element of the array. Two nested loops.
```java
    int[] array = {1, 2, 3, 4, 5};
    for (int i : array) {
        for (int j : array) {
            System.out.println(String.format("[%d, %d]", i, j));
        }
    }
```
## O(2^n)
Exponential algorithms. Recursive functions which solves the problem of size `n`.

## O(n!)
Factorial growth. Separate loop for every element in the collection.
```java
    public static void main(String[] args) {
        System.out.println(factorial(13));
    }

    private static long factorial(long n) {
        if (n <= 1) {
            return 1;
        }

        // please don't do this in your production code
        long result = n;
        for (int i = 0; i < n; i++) {
            result = n * factorial(n - 1);
        }
        return result;
    }
```
# Rules to calculate time complexity
1. Always worst case
2. Remove constants
3. Different inputs should have different variables. `O(n + m)` for consecutive loops, `O(n * m)` for nested loops.
4. Drop non-dominant terms

# What can cause time complexity?
* Operations (`+`, `-`, `*`, `/`, etc)
* Comparisons (`>`, `<`, `==`, etc)
* Looping (`for`, `while`)
* Outside functions call (`function()`)

# What can cause space complexity?
* Variables
* Data Structures
* Function calls (stack overflow in recursion)
* Allocations

# Big O cheat sheet
There is a [Big O cheat sheet page](https://www.bigocheatsheet.com/) where [@ericdrowell](https://github.com/ericdrowell) put together examples of Big O notation.

It contains nice chart of Big O complexity, examples of how much time insertions, deletions, search or access could take, and how complex are sorting algorithms.
