---
layout: lab
num: lab02
ready: false
desc: "FtoC and CtoF with test cases"
assigned: 2017-08-17 09:30:00.00-7
due: 2017-08-23 16:45:00.00-7
submit_cs_pnum: 770
---

In this lab, you'll practice:

* Creating a file that has some functions in it
* Testing those functions interactively at the Python prompt
* Creating a file of automatic test cases for those functions
* Running those test cases
* Submitting your functions and test cases to submit.cs for grading


# Step 0: Install pytest for your account (or on your machine)

This lab is one that you may find you need to do on the CSIL machines.  You need to start by logging in and typing
this command at at terminal session (the Unix Terminal prompt)

```
pip3 install --user pytest
```

The output should look something like this:

```
```

# Step 1: Warmup--experiencing floating point inaccuracy

Bring up a terminal window, and just type `python3`.  This should give you the Python Shell Prompt (`>>>`) where you can type in some expressions and see the resulting values.

Type in the `import math`, followed by `math.sqrt(2)`.  It should look like this:

```
>>> import math
>>> math.sqrt(2)
1.4142135623730951
>>> 
```

The value we get back is the square root of 2, which is an irrational number&mdash;its decimal representation goes on forever.  Unfortunately, real world computing devices typically store numbers with a finite number of decimal places&dagger;.   So, the representation we see for the square root of two, $$ \sqrt{2} $$ is, in fact an approximation.

<div style="font-size:80%">
(&dagger;&nbsp;Technically, "binary places", or "binary digits" rather than "decimal places". For purposes of this discussion it amounts to the same thing.  Also, some computer systems do work with "symbolic" representations of numeric quantities e.g retaning $$ \sqrt{2} $$ or $$\pi$$ as symbolic values rather than as numerical approximations. On those systems, you can exact results, without losing precision, at the expense of speed.  We won't discuss that kind of software in this course.)
</div>


We can see this if we multiply `math.sqrt(2)` by itself.  Try it:

```
>>> math.sqrt(2) * math.sqrt(2)
2.0000000000000004
>>> 
```

See that pesky `4` digit in the ten quadrillionths place?   My goodness, we are really, really close to 2.0, but if we ask whether the values are equal, Python says no:

```
>>> math.sqrt(2) * math.sqrt(2)== 2.0
False
```

In fact, Python is very clear about the difference between `2.0` and `math.sqrt(2) * math.sqrt(2)`, and can even tell us 
how big that difference is.  The `4` digit is only the tip of the very, very, very small iceberg:

```
>>> math.sqrt(2) * math.sqrt(2)- 2.0
4.440892098500626e-16
>>> 
```

This fact is going to be annoying to us many times.   One consequence is that <strong>when we test software involving floating point numbers, we must allow for some inaccuracy</strong>.   This "allowable inaccuracy" is sometimes called the <em>tolerance</em>, and it might be a small value such as `0.001` (1x10<sup>-3</sup>, or `0.000001` (1x10<sup>-6</sup>).

In Python, we can write `0.001` as `1e-03`, and `0.000001` as `1e-06`.  (The lowercase `e` is the way that Python represents scientific notation.)

You can see that Python, by default, formats numbers in this notation once the fifth decimal place is reached:

```
>>> 0.01
0.01
>>> 0.0001
0.0001
>>> 0.00001
1e-05
>>> 0.000001
1e-06
>>>
```


We'll come back to that idea later in this lab.


# Step 1: Make a `~/cs8/lab02` folder

In previous labs, you should already have created folders
`~/cs8/lab00` and `~/cs8/lab01`.  You are now going to create folder
`~/cs8/lab02` for the files for this lab.

In general, its probably a good idea to keep your work for this class
under `~/cs8`, in folders called `lab00`, `lab01`, `lab02` etc.

This isn't exactly *required* (no-one is going to check), but it's
probably a good habit to develop.  Here's why: all the rest of the
instructions will be written based on the assumption that you did
things this way.  And if you continue to take CS courses at UCSB,
you'll often find that's the case from one course to the next.

So, I'd strongly encourage you to do it. 


# Step 2: Create a file called `convert.py`

The contents of `convert.py` should be as shown below.  This contains
two Python function definitions that, at the moment are incorrect.

Choose "File => New File" in IDLE to bring up an "untitled" window, then copy and paste this code into that window.

Note that the formulas for converting between Celsius and Fahrenheit are incorrect.  That's deliberate, so just go with it for now.  We'll fix those at a later step.

```
def fToC(fTemp):
    return fTemp - 32

def cToF(cTemp):
    return cTemp + 32
``` 
      
# Step 3: Test your code by hand

To test this code, we'll first do what many programmers do: test the code by hand.  

That is, we'll run the file in IDLE, and then enter some function calls in the Python shell to see what results we get.  These two functions are supposed to convert between Fahrenheit where water freezes at 32 degrees, and Celsius where it freezes at 0 degrees.  Let's see if they work properly.

Use the Run Module command to run the code, and then try entering these function calls at the Python Shell prompt.  You should see output similar to what is shown below:

```
>>> fToC(32)
0
>>> cToF(0)
32
>>> 
```

As you can see, for those two particular values, the function appears to return the correct answer&mdash;32 degrees fahrenheit is indeed 0 degrees celsius, and 0 degrees celsius is indeed 32 degrees fahrenheit.  

So clearly, testing with a single value is not enough.  Indeed, if we test with another well known value, 212 Fahrenheit and 100 Celsius (the boiling point of water), we see that the output is incorrect:

```
>>> fToC(212)
180
>>> cToF(100)
132
>>> 
```

One of the problems with testing by hand is that it is tedious, and folks have a tendency to skip it.  So, experienced programmers have learned that its generally a better idea to automate the process of testing.     We'll learn how to do that next.   We'll see that when we set up these four tests, two of them will pass, and two of them will fail.

# Step 4: Setting up automated tests

Add this line at the top of your convert.py file:

```
import pytest
```

Then, add the following code to your `convert.py` file after the function definitions for `ftoC` and `cToF` that defines four automated tests:

```
def test_fToC_freezing():
   assert fToC(32.0)==pytest.approx(0.0) 

def test_cToF_freezing():
   assert cToF(0.0)==pytest.approx(32.0) 

def test_fToC_boiling():
   assert fToC(212.0)==pytest.approx(100.0) 

def test_cToF_boiling():
   assert cToF(100.0)==pytest.approx(212.0) 
```

These are automated tests that use a module known as `pytest`.  When defining tests using the `pytest` module, we typically define functions that:

* have names that start with `test_` or end with `_test`
* end with exactly one `assert` statement&mdash;that is, the keyword `assert` followed by a boolean expresssion.  

If the expresssion after `assert` is true, the test passes, otherwise it fails

We are using `pytext.approx()` here because any time you are testing with floating point numbers, we have to be aware that there may be some inaccuracy, as we discussed earlier.  

(Recall our discussion of what happens when you multiple `math.sqrt(2.0)` by itself.  Here, its probably overkill since we aren't using any irrational numbers, but it is still safer to always use some way of approximating equality when dealing with floating point.)

You can click the plus to open a dropdown showing what your entire file should look like at this point:

<div id="info" data-role="collapsible" data-collapsed="true" markdown="1">

## Contents of `convert.py` so far

```
import pytest

def fToC(fTemp):
    return fTemp - 32

def cToF(cTemp):
    return cTemp + 32
    
def test_fToC_freezing():
   assert fToC(32.0)==pytest.approx(0.0) 

def test_cToF_freezing():
   assert cToF(0.0)==pytest.approx(32.0) 

def test_fToC_boiling():
   assert fToC(212.0)==pytest.approx(100.0) 

def test_cToF_boiling():
   assert cToF(100.0)==pytest.approx(212.0) 

```


</div>

After entering this, save the file and use "Run Module" to make sure there are no error messages (red output in the Python Shell Window.).  If not, then you are ready for the next step.
            
# Step 5: Running the test cases
        
Running the test cases requires us to go <em>outside of IDLE</em> back to the terminal shell prompt.  

Your current directory needs to be the same one that your convert.py file is stored in.    That should be `~/cs8/lab02`, but if it isn't, then fix things so that the `convert.py` file is in that directory, and you are in that directory.   If you need help, ask for assistance.

You should be able to type the `ls` command  (or on Windows, `dir`) at the terminal shell prompt and see the `convert.py` file listed:

```
your-prompt-here $ ls
convert.py
your-prompt-here $ 
```

When that's the case, try this command:

```
python3 -m pytest convert.py
```

You should see output like this.  It may be a little overwhelming at first, but don't let it intimidate you. Once you know what you are looking for, it is very easy to read.    After the output, there is a guide to understanding it.


```text
169-231-175-204:lab02 pconrad$ python3 -m pytest convert.py
==================================== test session starts ====================================
platform darwin -- Python 3.6.2, pytest-3.2.1, py-1.4.34, pluggy-0.4.0
rootdir: /Users/pconrad/github/ucsb-cs8/Lecture5_0816/lab02, inifile:
collected 4 items                                                                            

convert.py ..FF

========================================= FAILURES ==========================================
_____________________________________ test_fToC_boiling _____________________________________

    def test_fToC_boiling():
>      assert fToC(212.0)==pytest.approx(100.0)
E      assert 180.0 == 100.0 ± 1.0e-04
E       +  where 180.0 = fToC(212.0)
E       +  and   100.0 ± 1.0e-04 = <function approx at 0x1026c40d0>(100.0)
E       +    where <function approx at 0x1026c40d0> = pytest.approx

convert.py:16: AssertionError
_____________________________________ test_cToF_boiling _____________________________________

    def test_cToF_boiling():
>      assert cToF(100.0)==pytest.approx(212.0)
E      assert 132.0 == 212.0 ± 2.1e-04
E       +  where 132.0 = cToF(100.0)
E       +  and   212.0 ± 2.1e-04 = <function approx at 0x1026c40d0>(212.0)
E       +    where <function approx at 0x1026c40d0> = pytest.approx

convert.py:19: AssertionError
============================ 2 failed, 2 passed in 0.03 seconds =============================
169-231-175-204:lab02 pconrad$ 
```

Ok, let's now break down this output.

# Step 6: Understanding the output of pytest

Here's how to understand `pytest` output. 

1. <b>Get the big picture first from the <tt>convert.py ..FF</tt> line</b>. 

   Look for a line near the beginning that has the name of your file, followed by a list of either dots, 
   letter 'E' or letter 'F' characters, like this one:

   ```
   convert.py ..FF
   ```
   
   In this case, the `.` characters are tests that passed.  If you have four tests, the ideal thing you'd want to see is <tt>convert.py&nbsp;....</tt> which means that four tests for <tt>convert.py</tt> all passed. 
   
   Instead, here, we see `..FF`, which means we had two test failures.   Later in the output, we'll see more detail about
   each of those failures.
   
2. <b>Understand the overall structure of the output.</b>

   The rest of the output will be divided into sections, one for each
   failure.  Here is what it look like with the
   details taken out so that you can see the big picture more easily:
   

   ```text
   ==================================== test session starts ====================================
   (blah blah here that you can ignore)
   
   convert.py ..FF
   ========================================= FAILURES ==========================================
   _____________________________________ test_fToC_boiling _____________________________________

   ...
   (details about the first test case failure are here)
   ...
   
   _____________________________________ test_cToF_boiling _____________________________________

   ...
   (details about the second test case failure are here)
   ...
   
   ========================= 2 failed, 2 passed in 0.03 seconds =============================
   ```
   
   Note that the last line gives us a nice summary: `2 failed, 2 passed in 0.03 seconds`.   We now know that 
   we need to focus on the two failures.   And the headers tell us which test cases failed, namely `test_fToC_boiling`
   and `test_cToF_boiling`.   So let's focus on those next, by first looking in detail at the first one:
 
3. <b>Focus in on the first test case failure.</b>
 
   Let's focus just on the detailed output for the first test case failure,
   `test_fToC_boiling`.  What does all of the detailed output mean?

   <style>
   div.explain-pytest table * td:first-of-type { width: 35em; font-size: 90%; }
   </style>
   
   In general, its a breakdown of why the assertion turned out to be false, showing every step in the calculation.  Let's break it down one line at a time:
   
   <div class="explain-pytest">
   
   | line of output | meaning |
   |----------------|----------|
   | `def test_fToC_boiling():` |  first line of the failing test case |
   |`E      assert 180.0 == 100.0 ± 1.0e-04` | This is the assertion that turned out not to be true |
   |`E       +  where 180.0 = fToC(212.0)` | This tells us why `180.0` was on the left hand side of the `==`.  It was the result of computing `ftoC(212.0)` |
   |`E       +  and   100.0 ± 1.0e-04 = <function approx at 0x1026c40d0>(100.0)` | This tells us why `100.0 ± 1.0e-04` was on the right hand side of the `==`.  It shows the expected value (`100.0`) and the tolerance (` ± 1.0e-04`).
   |`E       +    where <function approx at 0x1026c40d0> = pytest.approx` | This tells us that `pytest.approx` was used to calculate the tolerance. |
   | &nbsp; | &nbsp; |
   |`convert.py:16: AssertionError` | This shows which line in the file `convert.py` had the failed assertion, namely, line 16.  That helps us find the error faster if we are dealing with a large file of code.|
   
   </div>

   If we look at this, we can see that amidst all of the clutter is the
   crucial fact that `fToC(212.0)` returned `180.0` when what we were
   expecting was `100.0`, with a tolerance of `± 1.0e-04`.
            
# Step 7: Fixing the code

So, if you have failing test cases, the thing to do is fix the code so
that the test cases pass.

To do that you'll need to correct the forumla.

Keep in mind that in Python:

* The `*` symbol is used for multiplication.  In algebra, we can write
  `1.8x` to mean `1.8` multiplied by `x`, however, this does not work
  in Python.  In Python you must write `1.8 * x` if you want to
  multiply the variable `x` by 1.8.

* The `+` and `-` symbols are used for addition and subtraction

* The `/` symbol is used for division, e.g '9/5' means nine
  divided by five.  

Also, the order of operations in Python is that multiplication and
division are done before addition and subtraction. Some examples: 

* If `x` is 5, then `x + 2 * 3` gives us 11, not 21.  The
  multiplication is perfomed before the addition.

* If `x` is 16, then `x - 6 / 2` gives us 13, not 5.  The division is
  performed before the subtraction.

* If you want to force the addition or subtraction to be done first,
  you must use parentheses, e. g. `(x + 2) * 3` or `(x - 6) / 2`

When you replace `return ftemp - 32.0` with the correct formula for
converting a Fahrenheit temperature to Celsius, you should leave out
the comment that says `# TODO: Fix this line of code `.

You'll also want to replace the similar line in the cToF function.

When you have the test cases passing, try running the pytest command again&mdash;remembering that:

* it <b>must be done from the terminal shell</b>, NOT the Python shell.
* the current working directory of that terminal session must be
   the directory/folder where your `convert.py` file is located

```
python3 -m pytest convert.py
```

When you see four passing tests, your output will look like this. Also, the last line will be a pleasant shade of green instead of an angry looking red.

```
169-231-175-204:lab02 pconrad$ python3 -m pytest convert.py
======================== test session starts =========================
platform darwin -- Python 3.6.2, pytest-3.2.1, py-1.4.34, pluggy-0.4.0
rootdir: /Users/pconrad/github/ucsb-cs8/Lecture5_0816/lab02, inifile:
collected 4 items                                                     

convert.py ....

====================== 4 passed in 0.01 seconds ======================
169-231-175-204:lab02 pconrad$ 
```

At that point, you are ready to submit your work to submit.cs

# Step 8: Submit your `convert.py` file to submit.cs

To submit your file to submit.cs, you can visit this page:

<https://submit.cs.ucsb.edu/form/project/{{page.submit_cs_pnum}}/submission>

Navigate to that page, and upload your `convert.py` file.

Or, if you are working on the ECI/CSIL/lab linux systems, you can also submit at the command line with this command, provided you are in the correct folder/diretory:


<tt>~submit/submit -p {{page.submit_cs_pnum}} convert.py</tt>


The first time you use this method, it will ask for your email address: use your full umail address (e.g. `cgaucho@umail.ucsb.edu`).  For password, use the password that you enter for the submit.cs system.    You may save these credentials if you don't want to have to type them in every time.

