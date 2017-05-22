<div style="font-size:85%; font-family: Arial Narrow, sans-serif;" markdown="1">

# class `java.util.ArrayList<E>`

The following excerpts from the javadoc for `java.util.ArrayList<E>` may be
helpful to you in completing this exam. 

## Inheritance Hierarchy (complete)

```
java.lang.Object
  java.util.AbstractCollection<E>
    java.util.AbstractList<E>
      java.util.ArrayList<E>
```

<div markdown="1" style="font-size: 80%; font-family: Arial Narrow, sans-serif;"
   class="hanging-indent-table">

| All Implemented Interfaces: | `Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess` |
| Direct Known Subclasses: | `AttributeList, RoleList, RoleUnresolvedList` |

</div>

## Constructors (complete)

<div markdown="1" class="hanging-indent-table">

| `ArrayList()` | Constructs an empty list with an initial capacity of ten.
| `ArrayList(Collection<? extends E> c)` | Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator. |
| `ArrayList(int initialCapacity)` |Constructs an empty list with the specified initial capacity. |

</div>

## Most important methods, with brief description

<div markdown="1" class="hanging-indent-table">

| `boolean` | `add(E e)` | Appends the specified element to the end of this list. |
| `void` | `add(int index, E element)` | Inserts the specified element at the specified position in this list. |
| `void` | `clear()` | Removes all of the elements from this list.|
| `E` | `get(int index)` | Returns the element at the specified position in this list. |
| `int` | `indexOf(Object o)` | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `boolean` | `isEmpty()` | Returns true if this list contains no elements. |
| `int`	|  `lastIndexOf(Object o)` | Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `E` | `remove(int index)` | Removes the element at the specified position in this list.|
| `boolean` | `remove(Object o)` | Removes the first occurrence of the specified element from this list, if it is present. |
| `E` | `set(int index, E element)` | Replaces the element at the specified position in this list with the specified element. |
| `int` | `size()` | Returns the number of elements in this list. |

</div>

## Additional methods, listed by method signature only.

<div markdown="1" class="hanging-indent-table">

| `boolean addAll(Collection<? extends E> c)` | `boolean	addAll(int index, Collection<? extends E> c)` |
| `Object   clone()` |  `boolean  contains(Object o)` |
| `void	   ensureCapacity(int minCapacity)` | `void forEach(Consumer<? super E> action)` |
| `Iterator<E> iterator()` | `ListIterator<E>  listIterator()` |
| `ListIterator<E> listIterator(int index)` | `boolean removeAll(Collection<?> c)` |
| `boolean removeIf(Predicate<? super E> filter)` | `protected void removeRange(int fromIndex, int toIndex)` |
| `void replaceAll(UnaryOperator<E> operator)` | `boolean retainAll(Collection<?> c)` |
| `void	sort(Comparator<? super E> c)` | `Spliterator<E>  spliterator()` |
| `List<E> subList(int fromIndex, int toIndex)` | `Object[] toArray()` |
| `<T> T[] toArray(T[] a)` | `void    trimToSize()` |

</div>


<p style="font-size:80%">(The two columns are not signficant; they they are divided into two columns
only for space reasons)</p>

<div markdown="1" >

| Inherited from | methods | more methods |
|----------------|---------|--------------|
| <strong>`class java.util.AbstractList`</strong> | `bool equals(Object o)` | `int hashCode()` |
| <strong>`class java.util.AbstractCollection`</strong> | `boolean containsAll(Collection<?> c)` | `String toString()` |
| <strong>`class java.lang.Object` </strong>| `void finalize() throws Throwable` <br> `final Class<?> getClass()` <br> `final void notify()` <br > `final void notifyAll()` | `final void wait()`<br>&nbsp;&nbsp;`throws InterruptedException` <br > `final void wait(long timeout)`<br>&nbsp;&nbsp;`throws InterruptedException`,<br >`final void wait(long timeout,int nanos)`<br>&nbsp;&nbsp;`throws InterruptedException`|

</div>

</div>

<p class="page-break-before">
</p>

# interface `java.lang.Comparable<T>`

<dl>
<dt markdown="1"> `int compareTo(T o)`
</dt>
<dd>Compares this object with the specified object for order.
</dd>
</dl>

# SortDemo01.java

This program compiles and runs, with the output as shown.

{% highlight java linenos %}
import java.util.ArrayList;
import java.util.Collections;
public class SortDemo1 {
  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<Integer>();
    
    int [] values = new int [] {45,32,87,12,92,16};
    for (int i: values) { 
      nums.add(i); 
    }
    Collections.sort(nums);
    System.out.println("nums=" + nums);
  }
}
{% endhighlight %}

Output:

```
$ java SortDemo1
nums=[12, 16, 32, 45, 87, 92]
$ 
```

# SortDemo02.java

This code compiles (provided the implemenatation of `arrayToArrayList` is
correct.)  The output is as shown.

{% highlight java linenos %}
import java.util.ArrayList;
import java.util.Collections;
public class SortDemo2 {

  public static ArrayList<Integer> arrayToArrayList(int [] array) {

  // The code for this method is a question on the exam
  
  }

  public static void main(String[] args) {
    ArrayList<ArrayList<Integer>> factorizations = new ArrayList<ArrayList<Integer>>();

    factorizations.add(arrayToArrayList(new int [] {3,3,5})); // factors of 45
    factorizations.add(arrayToArrayList(new int [] {2,2,2,2,2})); // factors of 32
    factorizations.add(arrayToArrayList(new int [] {3,29}));    // factors of 87
    factorizations.add(arrayToArrayList(new int [] {2,2,3}));  // factors of 12
    factorizations.add(arrayToArrayList(new int [] {2,2,23})); // factors of 92
    factorizations.add(arrayToArrayList(new int [] {2,2,2,2})); // factors of 16
    // Collections.sort(factorizations);
    System.out.println("factorizations=" + factorizations);
  }
}
{% endhighlight %}

Output:
```
$ java SortDemo2
factorizations=[[3, 3, 5], [2, 2, 2, 2, 2], [3, 29], [2, 2, 3], [2, 2, 23], [2, 2, 2, 2]]
$ 
```
