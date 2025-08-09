package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract class AbstractDouble2IntSortedMap extends AbstractDouble2IntMap implements Double2IntSortedMap {
   private static final long serialVersionUID = -1773560792952436569L;

   protected AbstractDouble2IntSortedMap() {
   }

   public DoubleSortedSet keySet() {
      return new KeySet();
   }

   public IntCollection values() {
      return new ValuesCollection();
   }

   protected class KeySet extends AbstractDoubleSortedSet {
      public boolean contains(double k) {
         return AbstractDouble2IntSortedMap.this.containsKey(k);
      }

      public int size() {
         return AbstractDouble2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractDouble2IntSortedMap.this.clear();
      }

      public DoubleComparator comparator() {
         return AbstractDouble2IntSortedMap.this.comparator();
      }

      public double firstDouble() {
         return AbstractDouble2IntSortedMap.this.firstDoubleKey();
      }

      public double lastDouble() {
         return AbstractDouble2IntSortedMap.this.lastDoubleKey();
      }

      public DoubleSortedSet headSet(double to) {
         return AbstractDouble2IntSortedMap.this.headMap(to).keySet();
      }

      public DoubleSortedSet tailSet(double from) {
         return AbstractDouble2IntSortedMap.this.tailMap(from).keySet();
      }

      public DoubleSortedSet subSet(double from, double to) {
         return AbstractDouble2IntSortedMap.this.subMap(from, to).keySet();
      }

      public DoubleBidirectionalIterator iterator(double from) {
         return new KeySetIterator(AbstractDouble2IntSortedMap.this.double2IntEntrySet().iterator(new AbstractDouble2IntMap.BasicEntry(from, 0)));
      }

      public DoubleBidirectionalIterator iterator() {
         return new KeySetIterator(Double2IntSortedMaps.fastIterator(AbstractDouble2IntSortedMap.this));
      }
   }

   protected static class KeySetIterator implements DoubleBidirectionalIterator {
      protected final ObjectBidirectionalIterator i;

      public KeySetIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public double nextDouble() {
         return ((Double2IntMap.Entry)this.i.next()).getDoubleKey();
      }

      public double previousDouble() {
         return ((Double2IntMap.Entry)this.i.previous()).getDoubleKey();
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }

      public boolean hasPrevious() {
         return this.i.hasPrevious();
      }
   }

   protected class ValuesCollection extends AbstractIntCollection {
      public IntIterator iterator() {
         return new ValuesIterator(Double2IntSortedMaps.fastIterator(AbstractDouble2IntSortedMap.this));
      }

      public boolean contains(int k) {
         return AbstractDouble2IntSortedMap.this.containsValue(k);
      }

      public int size() {
         return AbstractDouble2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractDouble2IntSortedMap.this.clear();
      }
   }

   protected static class ValuesIterator implements IntIterator {
      protected final ObjectBidirectionalIterator i;

      public ValuesIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public int nextInt() {
         return ((Double2IntMap.Entry)this.i.next()).getIntValue();
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }
   }
}
