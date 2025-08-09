package org.apache.commons.collections4;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.SequencesComparator;

public class ListUtils {
   private ListUtils() {
   }

   public static List emptyIfNull(List list) {
      return list == null ? Collections.emptyList() : list;
   }

   public static List defaultIfNull(List list, List defaultList) {
      return list == null ? defaultList : list;
   }

   public static List intersection(List list1, List list2) {
      List<E> result = new ArrayList();
      List<? extends E> smaller = list1;
      List<? extends E> larger = list2;
      if (list1.size() > list2.size()) {
         smaller = list2;
         larger = list1;
      }

      HashSet<E> hashSet = new HashSet(smaller);

      for(Object e : larger) {
         if (hashSet.contains(e)) {
            result.add(e);
            hashSet.remove(e);
         }
      }

      return result;
   }

   public static List subtract(List list1, List list2) {
      ArrayList<E> result = new ArrayList();
      HashBag<E> bag = new HashBag(list2);

      for(Object e : list1) {
         if (!bag.remove(e, 1)) {
            result.add(e);
         }
      }

      return result;
   }

   public static List sum(List list1, List list2) {
      return subtract(union(list1, list2), intersection(list1, list2));
   }

   public static List union(List list1, List list2) {
      ArrayList<E> result = new ArrayList(list1.size() + list2.size());
      result.addAll(list1);
      result.addAll(list2);
      return result;
   }

   public static List select(Collection inputCollection, Predicate predicate) {
      return (List)CollectionUtils.select(inputCollection, predicate, new ArrayList(inputCollection.size()));
   }

   public static List selectRejected(Collection inputCollection, Predicate predicate) {
      return (List)CollectionUtils.selectRejected(inputCollection, predicate, new ArrayList(inputCollection.size()));
   }

   public static boolean isEqualList(Collection list1, Collection list2) {
      if (list1 == list2) {
         return true;
      } else if (list1 != null && list2 != null && list1.size() == list2.size()) {
         Iterator<?> it1 = list1.iterator();
         Iterator<?> it2 = list2.iterator();
         Object obj1 = null;
         Object obj2 = null;

         while(true) {
            if (it1.hasNext() && it2.hasNext()) {
               obj1 = it1.next();
               obj2 = it2.next();
               if (obj1 == null) {
                  if (obj2 == null) {
                     continue;
                  }
               } else if (obj1.equals(obj2)) {
                  continue;
               }

               return false;
            }

            return !it1.hasNext() && !it2.hasNext();
         }
      } else {
         return false;
      }
   }

   public static int hashCodeForList(Collection list) {
      if (list == null) {
         return 0;
      } else {
         int hashCode = 1;

         for(Object obj : list) {
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
         }

         return hashCode;
      }
   }

   public static List retainAll(Collection collection, Collection retain) {
      List<E> list = new ArrayList(Math.min(collection.size(), retain.size()));

      for(Object obj : collection) {
         if (retain.contains(obj)) {
            list.add(obj);
         }
      }

      return list;
   }

   public static List removeAll(Collection collection, Collection remove) {
      List<E> list = new ArrayList();

      for(Object obj : collection) {
         if (!remove.contains(obj)) {
            list.add(obj);
         }
      }

      return list;
   }

   public static List synchronizedList(List list) {
      return Collections.synchronizedList(list);
   }

   public static List unmodifiableList(List list) {
      return UnmodifiableList.unmodifiableList(list);
   }

   public static List predicatedList(List list, Predicate predicate) {
      return PredicatedList.predicatedList(list, predicate);
   }

   public static List transformedList(List list, Transformer transformer) {
      return TransformedList.transformingList(list, transformer);
   }

   public static List lazyList(List list, Factory factory) {
      return LazyList.lazyList(list, factory);
   }

   public static List lazyList(List list, Transformer transformer) {
      return LazyList.lazyList(list, transformer);
   }

   public static List fixedSizeList(List list) {
      return FixedSizeList.fixedSizeList(list);
   }

   public static int indexOf(List list, Predicate predicate) {
      if (list != null && predicate != null) {
         for(int i = 0; i < list.size(); ++i) {
            E item = (E)list.get(i);
            if (predicate.evaluate(item)) {
               return i;
            }
         }
      }

      return -1;
   }

   public static List longestCommonSubsequence(List a, List b) {
      return longestCommonSubsequence(a, b, DefaultEquator.defaultEquator());
   }

   public static List longestCommonSubsequence(List a, List b, Equator equator) {
      if (a != null && b != null) {
         if (equator == null) {
            throw new NullPointerException("Equator must not be null");
         } else {
            SequencesComparator<E> comparator = new SequencesComparator(a, b, equator);
            EditScript<E> script = comparator.getScript();
            LcsVisitor<E> visitor = new LcsVisitor();
            script.visit(visitor);
            return visitor.getSubSequence();
         }
      } else {
         throw new NullPointerException("List must not be null");
      }
   }

   public static String longestCommonSubsequence(CharSequence a, CharSequence b) {
      if (a != null && b != null) {
         List<Character> lcs = longestCommonSubsequence((List)(new CharSequenceAsList(a)), (List)(new CharSequenceAsList(b)));
         StringBuilder sb = new StringBuilder();

         for(Character ch : lcs) {
            sb.append(ch);
         }

         return sb.toString();
      } else {
         throw new NullPointerException("CharSequence must not be null");
      }
   }

   public static List partition(List list, int size) {
      if (list == null) {
         throw new NullPointerException("List must not be null");
      } else if (size <= 0) {
         throw new IllegalArgumentException("Size must be greater than 0");
      } else {
         return new Partition(list, size);
      }
   }

   private static final class LcsVisitor implements CommandVisitor {
      private final ArrayList sequence = new ArrayList();

      public LcsVisitor() {
      }

      public void visitInsertCommand(Object object) {
      }

      public void visitDeleteCommand(Object object) {
      }

      public void visitKeepCommand(Object object) {
         this.sequence.add(object);
      }

      public List getSubSequence() {
         return this.sequence;
      }
   }

   private static final class CharSequenceAsList extends AbstractList {
      private final CharSequence sequence;

      public CharSequenceAsList(CharSequence sequence) {
         this.sequence = sequence;
      }

      public Character get(int index) {
         return this.sequence.charAt(index);
      }

      public int size() {
         return this.sequence.length();
      }
   }

   private static class Partition extends AbstractList {
      private final List list;
      private final int size;

      private Partition(List list, int size) {
         this.list = list;
         this.size = size;
      }

      public List get(int index) {
         int listSize = this.size();
         if (index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
         } else if (index >= listSize) {
            throw new IndexOutOfBoundsException("Index " + index + " must be less than size " + listSize);
         } else {
            int start = index * this.size;
            int end = Math.min(start + this.size, this.list.size());
            return this.list.subList(start, end);
         }
      }

      public int size() {
         return (int)Math.ceil((double)this.list.size() / (double)this.size);
      }

      public boolean isEmpty() {
         return this.list.isEmpty();
      }
   }
}
