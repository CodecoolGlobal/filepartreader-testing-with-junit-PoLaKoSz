# Assignment: FilePartReader testing with JUnit

In this assignment, we will work with files, because we can't have enough file readers. :) Your job is to implement 2
classes and cover them with tests.

## Implementation

### FilePartReader class

It has one constructor :
- it sets the class' instance variables to some invalid default value

#### It has three instance methods:

- public void **setup** ()
  - it's parameters are:
    - `filePath` as a String
    - `fromLine` as an Integer
    - `toLine` as an Integer
  - it throws an `IllegalArgumentException` :
    - if `toLine` is smaller than `fromLine`
    - if `fromLine` is smaller than 1
- public String **read** ()
  - opens the file on `filePath`, and gives back it's content as a String
  - it doesn't catch the exception being raised, if the file isn't present on `filePath`, we can expect an `IOException`
- public String **readLines** ():
  - reads the file with **read** ()
  - it gives back every line from it's content between `fromLine` and `toLine` (both of them are included), and returns
  these lines as a String. Take care because if `fromLine` is 1, it means the very first row in the file. Also, if
  `fromLine` is 1 and `toLine` is 1 also, we will read only the very first line.

### FileWordAnalyzer class

It has one constructor :
- it's parameter is a **FilePartReader** object

#### It has three instance methods:

- public List **getWordsOrderedAlphabetically** ():
  - calls FilePartReader.readLines ()
  - returns the words ordered by alphabetically as an ArrayList
- public List **getWordsContainingSubstring** (String `subString` ):
  - calls **FilePartReader.readLines** ()
  - returns the words which contains the `subString`
- public List **getStringsWhichPalindromes** ():
  - calls **FilePartReader.readLines** ()
  - returns the words from the String which are palindrome

## Testing

When you are ready, your job is to cover your code with tests, and make an assertion for all the statements in the
bullet points. When testing **FilePartReader** class, you can have a test file with which you can test the read method.

## GitHub classroom

Please find the relevant GitHub classroom invitation here:

**https://classroom.github.com/a/PevOlA3W**

Use Maven for your application (we provided a sample pom.xml in the base repository).
