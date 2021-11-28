# Array
Organizes items sequentially - one after another in memory.
If all you need is to store some data and iterate through it one by one, arrays are the best choice.

|Index|Item|
|:---|:---|
|0|Some item at index 0|
|1|Some item at index 1|
|2|Some item at index 2|
|3|Some item at index 3|
|4|Some item at index 4|

# Time complexity of basic operations

|Operation|BigO|
|:---|:---|
|Access|`O(1)`|
|Search|`O(n)`|
|Insertion|`O(n)`|
|Deletion|`O(n)`|

## Access
TBF

## Search
[MyArray contains() method](https://github.com/prokudinalex/InterviewCheatSheet/blob/824d0e9834ba273095155f541e4b2c251a90ed70/DataStructures/Arrays/MyArray/src/main/java/com/prokudin/array/MyArray.java#L42-L60)
```java
    public boolean contains(Object value) {
        for (Object item : items) { // here comes O(n)
            if (value == null && item == null) {
                return true;
            }
            if (value != null && value.equals(item)) {
                return true;
            }
        }
        return false;
    }
```


## Insertion
TBF

## Deletion
TBF

