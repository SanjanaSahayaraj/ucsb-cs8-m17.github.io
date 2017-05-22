---
layout: examHandoutNoName
num: e01
ready: true
desc: "Legal Cheat Sheet"
exam_date: 2016-10-19 12:30:00.00-7
---

<div style="font-size:90%; font-family: Arial Narrow, sans-serif;" markdown="1">

# class `java.util.ArrayList<E>`

The following excerpts from the javadoc for `java.util.ArrayList<E>` may be
helpful to you in completing this exam. <br> For hints on `.equals()`, see other side.

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

<div style="margin-top: 2em; margin-bottom: 2em;">
&nbsp;
</div>

</div>

<h2 class="page-break-before">Hints for `.equals()`</h2>

For summary of `java.util.ArrayList<E>`, see other side.


As a reminder, a properly written overridden `.equals()` method:

* Takes an `Object o` as its parameter
* Returns a `boolean` value
* Checks each of the following conditions, and for each, returns an appropriate result:
   * Does `o` refer to the same object as the one on which the .equals method was invoked? 
   * Is `o` a null reference?
   * If `o` an instance of some other class, i.e. `getClass() != o.getClass()`
   * Typecasts `o` to an instance of the current class, and then compares values appropriately.



