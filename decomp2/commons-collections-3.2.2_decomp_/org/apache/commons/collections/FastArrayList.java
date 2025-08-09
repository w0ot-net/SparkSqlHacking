package org.apache.commons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FastArrayList extends ArrayList {
   protected ArrayList list = null;
   protected boolean fast = false;

   public FastArrayList() {
      this.list = new ArrayList();
   }

   public FastArrayList(int capacity) {
      this.list = new ArrayList(capacity);
   }

   public FastArrayList(Collection collection) {
      this.list = new ArrayList(collection);
   }

   public boolean getFast() {
      return this.fast;
   }

   public void setFast(boolean fast) {
      this.fast = fast;
   }

   public boolean add(Object element) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.add(element);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.add(element);
         }
      }
   }

   public void add(int index, Object element) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            temp.add(index, element);
            this.list = temp;
         }
      } else {
         synchronized(this.list) {
            this.list.add(index, element);
         }
      }

   }

   public boolean addAll(Collection collection) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.addAll(collection);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.addAll(collection);
         }
      }
   }

   public boolean addAll(int index, Collection collection) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.addAll(index, collection);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.addAll(index, collection);
         }
      }
   }

   public void clear() {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            temp.clear();
            this.list = temp;
         }
      } else {
         synchronized(this.list) {
            this.list.clear();
         }
      }

   }

   public Object clone() {
      FastArrayList results = null;
      if (this.fast) {
         results = new FastArrayList(this.list);
      } else {
         synchronized(this.list) {
            results = new FastArrayList(this.list);
         }
      }

      results.setFast(this.getFast());
      return results;
   }

   public boolean contains(Object element) {
      if (this.fast) {
         return this.list.contains(element);
      } else {
         synchronized(this.list) {
            return this.list.contains(element);
         }
      }
   }

   public boolean containsAll(Collection collection) {
      if (this.fast) {
         return this.list.containsAll(collection);
      } else {
         synchronized(this.list) {
            return this.list.containsAll(collection);
         }
      }
   }

   public void ensureCapacity(int capacity) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            temp.ensureCapacity(capacity);
            this.list = temp;
         }
      } else {
         synchronized(this.list) {
            this.list.ensureCapacity(capacity);
         }
      }

   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof List)) {
         return false;
      } else {
         List lo = (List)o;
         if (this.fast) {
            ListIterator li1 = this.list.listIterator();
            ListIterator li2 = lo.listIterator();

            while(true) {
               if (li1.hasNext() && li2.hasNext()) {
                  Object o1 = li1.next();
                  Object o2 = li2.next();
                  if (o1 == null) {
                     if (o2 == null) {
                        continue;
                     }
                  } else if (o1.equals(o2)) {
                     continue;
                  }

                  return false;
               }

               return !li1.hasNext() && !li2.hasNext();
            }
         } else {
            synchronized(this.list) {
               ListIterator li1 = this.list.listIterator();
               ListIterator li2 = lo.listIterator();

               while(true) {
                  if (li1.hasNext() && li2.hasNext()) {
                     Object o1 = li1.next();
                     Object o2 = li2.next();
                     if (o1 == null) {
                        if (o2 == null) {
                           continue;
                        }
                     } else if (o1.equals(o2)) {
                        continue;
                     }

                     return false;
                  }

                  return !li1.hasNext() && !li2.hasNext();
               }
            }
         }
      }
   }

   public Object get(int index) {
      if (this.fast) {
         return this.list.get(index);
      } else {
         synchronized(this.list) {
            return this.list.get(index);
         }
      }
   }

   public int hashCode() {
      if (this.fast) {
         int hashCode = 1;

         for(Object o : this.list) {
            hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
         }

         return hashCode;
      } else {
         synchronized(this.list) {
            int hashCode = 1;

            for(Object o : this.list) {
               hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
            }

            return hashCode;
         }
      }
   }

   public int indexOf(Object element) {
      if (this.fast) {
         return this.list.indexOf(element);
      } else {
         synchronized(this.list) {
            return this.list.indexOf(element);
         }
      }
   }

   public boolean isEmpty() {
      if (this.fast) {
         return this.list.isEmpty();
      } else {
         synchronized(this.list) {
            return this.list.isEmpty();
         }
      }
   }

   public Iterator iterator() {
      return (Iterator)(this.fast ? new ListIter(0) : this.list.iterator());
   }

   public int lastIndexOf(Object element) {
      if (this.fast) {
         return this.list.lastIndexOf(element);
      } else {
         synchronized(this.list) {
            return this.list.lastIndexOf(element);
         }
      }
   }

   public ListIterator listIterator() {
      return (ListIterator)(this.fast ? new ListIter(0) : this.list.listIterator());
   }

   public ListIterator listIterator(int index) {
      return (ListIterator)(this.fast ? new ListIter(index) : this.list.listIterator(index));
   }

   public Object remove(int index) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            Object result = temp.remove(index);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.remove(index);
         }
      }
   }

   public boolean remove(Object element) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.remove(element);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.remove(element);
         }
      }
   }

   public boolean removeAll(Collection collection) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.removeAll(collection);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.removeAll(collection);
         }
      }
   }

   public boolean retainAll(Collection collection) {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            boolean result = temp.retainAll(collection);
            this.list = temp;
            return result;
         }
      } else {
         synchronized(this.list) {
            return this.list.retainAll(collection);
         }
      }
   }

   public Object set(int index, Object element) {
      if (this.fast) {
         return this.list.set(index, element);
      } else {
         synchronized(this.list) {
            return this.list.set(index, element);
         }
      }
   }

   public int size() {
      if (this.fast) {
         return this.list.size();
      } else {
         synchronized(this.list) {
            return this.list.size();
         }
      }
   }

   public List subList(int fromIndex, int toIndex) {
      return (List)(this.fast ? new SubList(fromIndex, toIndex) : this.list.subList(fromIndex, toIndex));
   }

   public Object[] toArray() {
      if (this.fast) {
         return this.list.toArray();
      } else {
         synchronized(this.list) {
            return this.list.toArray();
         }
      }
   }

   public Object[] toArray(Object[] array) {
      if (this.fast) {
         return this.list.toArray(array);
      } else {
         synchronized(this.list) {
            return this.list.toArray(array);
         }
      }
   }

   public String toString() {
      StringBuffer sb = new StringBuffer("FastArrayList[");
      sb.append(this.list.toString());
      sb.append("]");
      return sb.toString();
   }

   public void trimToSize() {
      if (this.fast) {
         synchronized(this) {
            ArrayList temp = (ArrayList)this.list.clone();
            temp.trimToSize();
            this.list = temp;
         }
      } else {
         synchronized(this.list) {
            this.list.trimToSize();
         }
      }

   }

   private class SubList implements List {
      private int first;
      private int last;
      private List expected;

      public SubList(int first, int last) {
         this.first = first;
         this.last = last;
         this.expected = FastArrayList.this.list;
      }

      private List get(List l) {
         if (FastArrayList.this.list != this.expected) {
            throw new ConcurrentModificationException();
         } else {
            return l.subList(this.first, this.last);
         }
      }

      public void clear() {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               this.get(temp).clear();
               this.last = this.first;
               FastArrayList.this.list = temp;
               this.expected = temp;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               this.get(this.expected).clear();
            }
         }

      }

      public boolean remove(Object o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               boolean r = this.get(temp).remove(o);
               if (r) {
                  --this.last;
               }

               FastArrayList.this.list = temp;
               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).remove(o);
            }
         }
      }

      public boolean removeAll(Collection o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               List sub = this.get(temp);
               boolean r = sub.removeAll(o);
               if (r) {
                  this.last = this.first + sub.size();
               }

               FastArrayList.this.list = temp;
               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).removeAll(o);
            }
         }
      }

      public boolean retainAll(Collection o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               List sub = this.get(temp);
               boolean r = sub.retainAll(o);
               if (r) {
                  this.last = this.first + sub.size();
               }

               FastArrayList.this.list = temp;
               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).retainAll(o);
            }
         }
      }

      public int size() {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).size();
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).size();
            }
         }
      }

      public boolean isEmpty() {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).isEmpty();
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).isEmpty();
            }
         }
      }

      public boolean contains(Object o) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).contains(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).contains(o);
            }
         }
      }

      public boolean containsAll(Collection o) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).containsAll(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).containsAll(o);
            }
         }
      }

      public Object[] toArray(Object[] o) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).toArray(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).toArray(o);
            }
         }
      }

      public Object[] toArray() {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).toArray();
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).toArray();
            }
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (FastArrayList.this.fast) {
            return this.get(this.expected).equals(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).equals(o);
            }
         }
      }

      public int hashCode() {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).hashCode();
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).hashCode();
            }
         }
      }

      public boolean add(Object o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               boolean r = this.get(temp).add(o);
               if (r) {
                  ++this.last;
               }

               FastArrayList.this.list = temp;
               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).add(o);
            }
         }
      }

      public boolean addAll(Collection o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               boolean r = this.get(temp).addAll(o);
               if (r) {
                  this.last += o.size();
               }

               FastArrayList.this.list = temp;
               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).addAll(o);
            }
         }
      }

      public void add(int i, Object o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               this.get(temp).add(i, o);
               ++this.last;
               FastArrayList.this.list = temp;
               this.expected = temp;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               this.get(this.expected).add(i, o);
            }
         }

      }

      public boolean addAll(int i, Collection o) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               boolean r = this.get(temp).addAll(i, o);
               FastArrayList.this.list = temp;
               if (r) {
                  this.last += o.size();
               }

               this.expected = temp;
               return r;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).addAll(i, o);
            }
         }
      }

      public Object remove(int i) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               Object o = this.get(temp).remove(i);
               --this.last;
               FastArrayList.this.list = temp;
               this.expected = temp;
               return o;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).remove(i);
            }
         }
      }

      public Object set(int i, Object a) {
         if (FastArrayList.this.fast) {
            synchronized(FastArrayList.this) {
               ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
               Object o = this.get(temp).set(i, a);
               FastArrayList.this.list = temp;
               this.expected = temp;
               return o;
            }
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).set(i, a);
            }
         }
      }

      public Iterator iterator() {
         return new SubListIter(0);
      }

      public ListIterator listIterator() {
         return new SubListIter(0);
      }

      public ListIterator listIterator(int i) {
         return new SubListIter(i);
      }

      public Object get(int i) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).get(i);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).get(i);
            }
         }
      }

      public int indexOf(Object o) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).indexOf(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).indexOf(o);
            }
         }
      }

      public int lastIndexOf(Object o) {
         if (FastArrayList.this.fast) {
            return this.get(this.expected).lastIndexOf(o);
         } else {
            synchronized(FastArrayList.this.list) {
               return this.get(this.expected).lastIndexOf(o);
            }
         }
      }

      public List subList(int f, int l) {
         if (FastArrayList.this.list != this.expected) {
            throw new ConcurrentModificationException();
         } else {
            return FastArrayList.this.new SubList(this.first + f, f + l);
         }
      }

      private class SubListIter implements ListIterator {
         private List expected;
         private ListIterator iter;
         private int lastReturnedIndex = -1;

         public SubListIter(int i) {
            this.expected = FastArrayList.this.list;
            this.iter = SubList.this.get(this.expected).listIterator(i);
         }

         private void checkMod() {
            if (FastArrayList.this.list != this.expected) {
               throw new ConcurrentModificationException();
            }
         }

         List get() {
            return SubList.this.get(this.expected);
         }

         public boolean hasNext() {
            this.checkMod();
            return this.iter.hasNext();
         }

         public Object next() {
            this.checkMod();
            this.lastReturnedIndex = this.iter.nextIndex();
            return this.iter.next();
         }

         public boolean hasPrevious() {
            this.checkMod();
            return this.iter.hasPrevious();
         }

         public Object previous() {
            this.checkMod();
            this.lastReturnedIndex = this.iter.previousIndex();
            return this.iter.previous();
         }

         public int previousIndex() {
            this.checkMod();
            return this.iter.previousIndex();
         }

         public int nextIndex() {
            this.checkMod();
            return this.iter.nextIndex();
         }

         public void remove() {
            this.checkMod();
            if (this.lastReturnedIndex < 0) {
               throw new IllegalStateException();
            } else {
               this.get().remove(this.lastReturnedIndex);
               SubList.this.last--;
               this.expected = FastArrayList.this.list;
               this.iter = this.get().listIterator(this.lastReturnedIndex);
               this.lastReturnedIndex = -1;
            }
         }

         public void set(Object o) {
            this.checkMod();
            if (this.lastReturnedIndex < 0) {
               throw new IllegalStateException();
            } else {
               this.get().set(this.lastReturnedIndex, o);
               this.expected = FastArrayList.this.list;
               this.iter = this.get().listIterator(this.previousIndex() + 1);
            }
         }

         public void add(Object o) {
            this.checkMod();
            int i = this.nextIndex();
            this.get().add(i, o);
            SubList.this.last++;
            this.expected = FastArrayList.this.list;
            this.iter = this.get().listIterator(i + 1);
            this.lastReturnedIndex = -1;
         }
      }
   }

   private class ListIter implements ListIterator {
      private List expected;
      private ListIterator iter;
      private int lastReturnedIndex = -1;

      public ListIter(int i) {
         this.expected = FastArrayList.this.list;
         this.iter = this.get().listIterator(i);
      }

      private void checkMod() {
         if (FastArrayList.this.list != this.expected) {
            throw new ConcurrentModificationException();
         }
      }

      List get() {
         return this.expected;
      }

      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public Object next() {
         this.lastReturnedIndex = this.iter.nextIndex();
         return this.iter.next();
      }

      public boolean hasPrevious() {
         return this.iter.hasPrevious();
      }

      public Object previous() {
         this.lastReturnedIndex = this.iter.previousIndex();
         return this.iter.previous();
      }

      public int previousIndex() {
         return this.iter.previousIndex();
      }

      public int nextIndex() {
         return this.iter.nextIndex();
      }

      public void remove() {
         this.checkMod();
         if (this.lastReturnedIndex < 0) {
            throw new IllegalStateException();
         } else {
            this.get().remove(this.lastReturnedIndex);
            this.expected = FastArrayList.this.list;
            this.iter = this.get().listIterator(this.lastReturnedIndex);
            this.lastReturnedIndex = -1;
         }
      }

      public void set(Object o) {
         this.checkMod();
         if (this.lastReturnedIndex < 0) {
            throw new IllegalStateException();
         } else {
            this.get().set(this.lastReturnedIndex, o);
            this.expected = FastArrayList.this.list;
            this.iter = this.get().listIterator(this.previousIndex() + 1);
         }
      }

      public void add(Object o) {
         this.checkMod();
         int i = this.nextIndex();
         this.get().add(i, o);
         this.expected = FastArrayList.this.list;
         this.iter = this.get().listIterator(i + 1);
         this.lastReturnedIndex = -1;
      }
   }
}
