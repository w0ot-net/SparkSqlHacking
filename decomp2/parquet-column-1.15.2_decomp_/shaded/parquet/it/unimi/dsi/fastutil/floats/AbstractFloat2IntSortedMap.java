package shaded.parquet.it.unimi.dsi.fastutil.floats;

import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract class AbstractFloat2IntSortedMap extends AbstractFloat2IntMap implements Float2IntSortedMap {
   private static final long serialVersionUID = -1773560792952436569L;

   protected AbstractFloat2IntSortedMap() {
   }

   public FloatSortedSet keySet() {
      return new KeySet();
   }

   public IntCollection values() {
      return new ValuesCollection();
   }

   protected class KeySet extends AbstractFloatSortedSet {
      public boolean contains(float k) {
         return AbstractFloat2IntSortedMap.this.containsKey(k);
      }

      public int size() {
         return AbstractFloat2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractFloat2IntSortedMap.this.clear();
      }

      public FloatComparator comparator() {
         return AbstractFloat2IntSortedMap.this.comparator();
      }

      public float firstFloat() {
         return AbstractFloat2IntSortedMap.this.firstFloatKey();
      }

      public float lastFloat() {
         return AbstractFloat2IntSortedMap.this.lastFloatKey();
      }

      public FloatSortedSet headSet(float to) {
         return AbstractFloat2IntSortedMap.this.headMap(to).keySet();
      }

      public FloatSortedSet tailSet(float from) {
         return AbstractFloat2IntSortedMap.this.tailMap(from).keySet();
      }

      public FloatSortedSet subSet(float from, float to) {
         return AbstractFloat2IntSortedMap.this.subMap(from, to).keySet();
      }

      public FloatBidirectionalIterator iterator(float from) {
         return new KeySetIterator(AbstractFloat2IntSortedMap.this.float2IntEntrySet().iterator(new AbstractFloat2IntMap.BasicEntry(from, 0)));
      }

      public FloatBidirectionalIterator iterator() {
         return new KeySetIterator(Float2IntSortedMaps.fastIterator(AbstractFloat2IntSortedMap.this));
      }
   }

   protected static class KeySetIterator implements FloatBidirectionalIterator {
      protected final ObjectBidirectionalIterator i;

      public KeySetIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public float nextFloat() {
         return ((Float2IntMap.Entry)this.i.next()).getFloatKey();
      }

      public float previousFloat() {
         return ((Float2IntMap.Entry)this.i.previous()).getFloatKey();
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
         return new ValuesIterator(Float2IntSortedMaps.fastIterator(AbstractFloat2IntSortedMap.this));
      }

      public boolean contains(int k) {
         return AbstractFloat2IntSortedMap.this.containsValue(k);
      }

      public int size() {
         return AbstractFloat2IntSortedMap.this.size();
      }

      public void clear() {
         AbstractFloat2IntSortedMap.this.clear();
      }
   }

   protected static class ValuesIterator implements IntIterator {
      protected final ObjectBidirectionalIterator i;

      public ValuesIterator(ObjectBidirectionalIterator i) {
         this.i = i;
      }

      public int nextInt() {
         return ((Float2IntMap.Entry)this.i.next()).getIntValue();
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }
   }
}
