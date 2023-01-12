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
6) `public static boolean CalculateRunningTime(int, int, int)` - this function takes as arguments three integers: n, seed, bound - for the first function, it creates n files with bound and seed random number of lines and then calling each function 3,4,5 and calculating running time of each of them, at the end - it prints the results and returns true on success, if error occurreds it prints error message and returns false.

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
in this part of the assignment one of the purposes of the same usage of 3,4,5 functions in `Ex2_1` is comparing running time and deciding which approach is better., 
let's discuss running timings of those function and see what is the best approach, We performed four experiments, we will present the results and explain them<br/<br/>

input: (n,seed,bound) = 100-100-1000
![First_Expiriment](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/100-100-1000.jpg)<br/><br/>
input: (n,seed,bound) = 1000-100-1000
![Second_Expiriment](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/1000-100-1000.jpg)<br/><br/>
input: (n,seed,bound) = 10000-100-1000
![Third_Expiriment](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/10000-100-1000.jpg)<br/><br/>
input: (n,seed,bound) = 100000-100-1000
![Fourth_Expiriment](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/100000-100-1000.jpg)<br/>

as we can see, using `public static int getNumOfLinesThreads(String[])` is the best option, because of the lowers running times in all four expiriments. what about the second and the third places, we don't have a decisive answer - because at 100,000 files the threadpool is the worst option but in 100 files regular calculation is the worst.

### Explenations:
as we saw at the results, using only single thread(s) is the best option to get lowest running times - the reason for that may be that if the system (JVM) using only one thread each time to calculate so every thread has more system reasurses such as CPU speed (in Ghz) or more Ram memmory available (in GB) and thus every thread can "finish his job" faster, what about threadpool and manually calculating (without any threads) - if we'll look at the 100,000 files results there we'll see that the threadpool is the worst option but if we'll loot at the 100 files results we'll see that the manuall option is the worst, the reason for that may be that if we have too many files (tasks) and too many threads that are "doing their jobs" simulltanly - so its logical that every thread will get less system resources (because ours home-computers has limited resoures, at the end...) and the reasong for the changing (that threadpool is better in one case but no the best at other case) is that the running time may depend on the diffculty to calculate many times at the same time and as we can see at some cases manuall is better than threadpool.  

## UML-Diagram:
![UML_PART_A](https://github.com/AviRahimov/OOP_Assignment3/blob/master/EX2/pictures/UML_PartA.png)
<br/>
as we can see at this diagram, the class `Ex2_1` uses the classes `Thread_NumOfLines` and `ThreadPool_NumOfLines`, in other words, there is a dependency between them. In addition, we can see that `ThreadPool_NumOfLines` implements the `Callable<V>` interface and the `Thread_NumOfLines` is extends (inheritance) JAVA's `Thread` which also is extending `Runnable` interface.

## Part B:

There are 4 classes in this part and we will explain all the classes.

### TaskType:
Enum class that representing 3 different priorities for some task for example:
** priority number 1: ** Computational task
** priority number 2: ** IO-Bound Task
** priority number 3: ** Unknown Task

this is a definition for some tasks but, the priorities for the tasks can be between 1-10 such that 10 us the lowest priority and is the greatest.

### Task:
**Class Fields:**
1) `Callable<T> operation` - this type is a callable task that the thread execute, Callable is used for Threadpool and not for single thread because if we using a single thread then we need to use the Runnable interface and not the Callable, we are going to talk about the Callable and the Runnable interfaces later when we talk about the adapter class.
2) `TaskType type` - the task type like compu..., IO, etc.

**Object's Constructor:**
We buikd two different constructors:
1) A private constructor that takes as an arguments Callable operation and a task type.
2) The second constructor is public and it's take a Callable operation as an argument.

**Class Functions:**
1) `createTask` - create a new task with a task type by calling the private constructor.
2) `createTask` - create a new task by calling the public constructor.
3) `call` - Computes a result, or throws an exception if unable to do so. return a generic type that represent the computed result.
4) `compareTo` - comparing between two different tasks by their priorities.
5) `getPrior` - getting the current task priority.

### CustomExecutor:

### FutureToRunAdapter:

## UML-Diagram:
** UML for Part B: **
![image](https://user-images.githubusercontent.com/98770917/211952123-c4a876db-3377-402f-95fa-3526a121d7ca.png)


## How to use
To clone this project all you need to do is to copy the [_project URL_](https://github.com/AviRahimov/OOP_Assignment2.git) to git bash and to change the current working directory to the location where you want the cloned directory.

Using the command: `"git clone https://github.com/AviRahimov/OOP_Assignment2.git <location-to-clone>"`, then the cloning proccess will start.
