# Object Oriented Programming - Third(2) Assignment

Authors:
* [Lior Vinman](https://github.com/liorvi35 "Lior Vinman")
* [Avraham Rahimov](https://github.com/AviRahimov "Avraham Rahimov")

This project is about using system's _threads_ for various usages such as processing files and count how many lines there are in a singe file and [AVI RAHIMOV - add here about usages of threads in part2].


This assignment is divided into two pats:
1) the first part - mainly is about generating semi-random text files with random number of lines and then calculate this number of lines with three approaches - counting one by one, using threads and finally using thread-pools. 
2) the second part - [AVI RAHIMOV - describe here about second part].

## Part A:

### Ex2_1:
this is the main class of this part (i.e. other classes that will be described are helper-classes).

**Class Fields:**
class have no fields !

**Object's Constructor:**
class doesn't have written constructor but there is a default constructor that inherited from `Object()`.

**Class Functions:**

1) `public static String[] createTextFiles(int, int, int)` - this function takes as argument three integers: _n_, _seed_, _bound_ and creates _n_ text files with randomly generated (using _seed_ for better random scattering) umber of lines under _bound_ - it returns an array with filenames of files that has been created.
2) `public static void deleteTextFiles(String[])` - this function takes as argument a string array that contains with filenames that should be deleted, and deletes them.
3) `public static int getNumOfLines(String[])` - this function takes as argument a string array that contains filenames, the function is counting and returning the total number of lines in all files - it counts manually.
4) `public static int getNumOfLinesThreads(String[])` - this function takes as argument a string array that contains filenames, the function is also counting the total number of lines for each file, but now using (new) thread for each file.
5) `public static int getNumOfLinesThreadPool(String[])` - .this function takes as argument a string array that contains filenames, this function (third in a row!) is counting the total number of lines in all files but now using thread pool.

### Thread_NumOfLines:
this class is a helper class for performing fourth function from `Ex2_1` it extends JAVA's `Thread`.

**Class Fields:**

1) `private String name` - string for holding a file name.
2) `private int total` - integer for holding the total number of lines in current file.

**Object's Constructor:**
There is a constructor that receives a string name to set the name of the file and initialize the total to be 0.

**Class Methods:**

1) `public int getTotal()` - standard getter method for second field (total). 
2) `public void run()` - this method is counting number of lines in single file using threads.

### ThreadPool_NumOfLines:
this class is a helper class for performing fifth function from `Ex2_1` it implements `Callabble<Integer>` JAVA's interface.

**Class Fields:**

1) `private String name` - string for holding a file name.

**Object's Constructor:**
There is a constructor that receives a string name to set the name of the file.

**Class Methods:**

1) `public Integer call()` - this method is counting number of lines in single file using thread-pool.

## Timings
in this part of the assignment one of the purposes of the same usage of 3,4,5 functions in `Ex2_1` is comparing running time and deciding which approach is better, for that we created another helper-class.

### RunningTime:
this class is helper-class for calculating and comparing the running time of functions 3,4,5. usage: `RunningTime.CalculateRunningTime(int, int, int)` in yours main function.

**Class Fields:**
class have no fields !

**Object's Constructor:**
class doesn't have written constructor but there is a default constructor that inherited from `Object()`.

**Class Functions:**
1) `public static boolean CalculateRunningTime(int, int, int)` - this function takes as arguments three integers: n, seed, bound - for the first function in `Ex2_1`, it creates n files with bound and seed random number of lines and then calling each function 3,4,5 and calculating running time of each of them, at the end - it prints the results and returns true on success, if error accuses it prints error message and returns false.

now let's discuss running timings of those function and see what is the best approach, We performed four experiments, we will present the results and explain them:
![First_Expiriment](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/100-100-1000.jpg)



## How to use
To clone this project all you need to do is to copy the [_project URL_](https://github.com/AviRahimov/OOP_Assignment2.git) to git bash and to change the current working directory to the location where you want the cloned directory.

Using the command: `"git clone https://github.com/AviRahimov/OOP_Assignment2.git <location-to-clone>"`, then the cloning proccess will start.
