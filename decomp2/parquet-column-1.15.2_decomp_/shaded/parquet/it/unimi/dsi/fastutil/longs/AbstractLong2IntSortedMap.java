package shaded.parquet.it.unimi.dsi.fastutil.longs;

import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract class AbstractLong2IntSortedMap extends AbstractLong2IntMap implements Long2IntSortedMap {
   private static final long serialVersionUID = -1773560792952436569L;

   protected AbstractLong2IntSortedMap() {
   }

   public LongSortedSet keySet() {
      return new KeySet();
   }

   public IntCollection values() {
      return new ValuesCollection();
   }

   protected class KeySet extends AbstractLongSortedSet {
      public boolean contains(long k) {
         return AbstractLong2IntSortedMap.this.containsKey(k);
      }

      public int size() {
         return AbstractLong2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractLong2IntSortedMap.this.clear();
      }

      public LongComparator comparator() {
         return AbstractLong2IntSortedMap.this.comparator();
      }

      public long firstLong() {
         return AbstractLong2IntSortedMap.this.firstLongKey();
      }

      public long lastLong() {
         return AbstractLong2IntSortedMap.this.lastLongKey();
      }

      public LongSortedSet headSet(long to) {
         return AbstractLong2IntSortedMap.this.headMap(to).keySet();
      }

      public LongSortedSet tailSet(long from) {
         return AbstractLong2IntSortedMap.this.tailMap(from).keySet();
      }

      public LongSortedSet subSet(long from, long to) {
         return AbstractLong2IntSortedMap.this.subMap(from, to).keySet();
      }

      public LongBidirectionalIterator iterator(long from) {
         return new KeySetIterator(AbstractLong2IntSortedMap.this.long2IntEntrySet().iterator(new AbstractLong2IntMap.BasicEntry(from, 0)));
      }

      public LongBidirectionalIterator iterator() {
         return new KeySetIterator(Long2IntSortedMaps.fastIterator(AbstractLong2IntSortedMap.this));
      }
   }

   protected static class KeySetIterator implements LongBidirectionalIterator {
      protected final ObjectBidirectionalIterator i;

      public KeySetIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public long nextLong() {
         return ((Long2IntMap.Entry)this.i.next()).getLongKey();
      }

      public long previousLong() {
         return ((Long2IntMap.Entry)this.i.previous()).getLongKey();
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
         return new ValuesIterator(Long2IntSortedMaps.fastIterator(AbstractLong2IntSortedMap.this));
      }

      public boolean contains(int k) {
         return AbstractLong2IntSortedMap.this.containsValue(k);
      }

      public int size() {
         return AbstractLong2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractLong2IntSortedMap.this.clear();
      }
   }

   protected static class ValuesIterator implements IntIterator {
      protected final ObjectBidirectionalIterator i;

      public ValuesIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public int nextInt() {
         return ((Long2IntMap.Entry)this.i.next()).getIntValue();
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }
   }
}
