package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.Container;
import org.roaringbitmap.ContainerBatchIterator;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.WordStorage;

public abstract class MappeableContainer implements Iterable, Cloneable, Externalizable, WordStorage {
   public static String[] ContainerNames = new String[]{"mappeablebitmap", "mappeablearray", "mappeablerun"};

   public static MappeableContainer rangeOfOnes(int start, int last) {
      int arrayContainerOverRunThreshold = 2;
      int cardinality = last - start;
      return (MappeableContainer)(cardinality <= 2 ? new MappeableArrayContainer(start, last) : new MappeableRunContainer(start, last));
   }

   public abstract MappeableContainer add(int var1, int var2);

   public abstract MappeableContainer add(char var1);

   public abstract boolean isEmpty();

   public abstract boolean isFull();

   public abstract void orInto(long[] var1);

   public abstract void andInto(long[] var1);

   public abstract void removeFrom(long[] var1);

   public abstract MappeableContainer and(MappeableArrayContainer var1);

   public abstract MappeableContainer and(MappeableBitmapContainer var1);

   protected MappeableContainer and(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.and((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.and((MappeableRunContainer)x) : this.and((MappeableBitmapContainer)x);
      }
   }

   protected abstract int andCardinality(MappeableArrayContainer var1);

   protected abstract int andCardinality(MappeableBitmapContainer var1);

   protected abstract int andCardinality(MappeableRunContainer var1);

   public int xorCardinality(MappeableContainer other) {
      return this.getCardinality() + other.getCardinality() - 2 * this.andCardinality(other);
   }

   public int andCardinality(MappeableContainer x) {
      if (this.isEmpty()) {
         return 0;
      } else if (x.isEmpty()) {
         return 0;
      } else if (x instanceof MappeableArrayContainer) {
         return this.andCardinality((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableBitmapContainer ? this.andCardinality((MappeableBitmapContainer)x) : this.andCardinality((MappeableRunContainer)x);
      }
   }

   public abstract MappeableContainer and(MappeableRunContainer var1);

   public abstract MappeableContainer andNot(MappeableArrayContainer var1);

   public abstract MappeableContainer andNot(MappeableBitmapContainer var1);

   protected MappeableContainer andNot(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.andNot((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.andNot((MappeableRunContainer)x) : this.andNot((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer andNot(MappeableRunContainer var1);

   public MappeableContainer orNot(MappeableContainer x, int endOfRange) {
      return endOfRange < 65536 ? this.or(x.not(0, endOfRange).iremove(endOfRange, 65536)) : this.or(x.not(0, 65536));
   }

   public MappeableContainer iorNot(MappeableContainer x, int endOfRange) {
      return endOfRange < 65536 ? this.ior(x.not(0, endOfRange).iremove(endOfRange, 65536)) : this.ior(x.not(0, 65536));
   }

   public abstract void clear();

   public abstract MappeableContainer clone();

   public abstract boolean contains(char var1);

   public boolean contains(MappeableContainer subset) {
      if (subset instanceof MappeableRunContainer) {
         return this.contains((MappeableRunContainer)subset);
      } else if (subset instanceof MappeableArrayContainer) {
         return this.contains((MappeableArrayContainer)subset);
      } else {
         return subset instanceof MappeableBitmapContainer ? this.contains((MappeableBitmapContainer)subset) : false;
      }
   }

   protected abstract boolean contains(MappeableRunContainer var1);

   protected abstract boolean contains(MappeableArrayContainer var1);

   protected abstract boolean contains(MappeableBitmapContainer var1);

   public abstract boolean intersects(int var1, int var2);

   public abstract boolean contains(int var1, int var2);

   public abstract void fillLeastSignificant16bits(int[] var1, int var2, int var3);

   public abstract MappeableContainer flip(char var1);

   protected abstract int getArraySizeInBytes();

   public abstract int getCardinality();

   public String getContainerName() {
      if (this instanceof MappeableBitmapContainer) {
         return ContainerNames[0];
      } else {
         return this instanceof MappeableArrayContainer ? ContainerNames[1] : ContainerNames[2];
      }
   }

   public abstract CharIterator getReverseCharIterator();

   public abstract PeekableCharIterator getCharIterator();

   public abstract ContainerBatchIterator getBatchIterator();

   public abstract void forEach(char var1, IntConsumer var2);

   public abstract int getSizeInBytes();

   public abstract MappeableContainer iadd(int var1, int var2);

   public abstract MappeableContainer iand(MappeableArrayContainer var1);

   public abstract MappeableContainer iand(MappeableBitmapContainer var1);

   protected MappeableContainer iand(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.iand((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.iand((MappeableRunContainer)x) : this.iand((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer iand(MappeableRunContainer var1);

   public abstract MappeableContainer iandNot(MappeableArrayContainer var1);

   public abstract MappeableContainer iandNot(MappeableBitmapContainer var1);

   protected MappeableContainer iandNot(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.iandNot((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.iandNot((MappeableRunContainer)x) : this.iandNot((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer iandNot(MappeableRunContainer var1);

   public abstract MappeableContainer inot(int var1, int var2);

   public abstract boolean intersects(MappeableArrayContainer var1);

   public abstract boolean intersects(MappeableBitmapContainer var1);

   public boolean intersects(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.intersects((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableBitmapContainer ? this.intersects((MappeableBitmapContainer)x) : this.intersects((MappeableRunContainer)x);
      }
   }

   public abstract boolean intersects(MappeableRunContainer var1);

   public abstract MappeableContainer ior(MappeableArrayContainer var1);

   public abstract MappeableContainer ior(MappeableBitmapContainer var1);

   protected MappeableContainer ior(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.ior((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.ior((MappeableRunContainer)x) : this.ior((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer ior(MappeableRunContainer var1);

   public abstract MappeableContainer iremove(int var1, int var2);

   protected abstract boolean isArrayBacked();

   public abstract MappeableContainer ixor(MappeableArrayContainer var1);

   public abstract MappeableContainer ixor(MappeableBitmapContainer var1);

   protected MappeableContainer ixor(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.ixor((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.ixor((MappeableRunContainer)x) : this.ixor((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer ixor(MappeableRunContainer var1);

   public MappeableContainer lazyIOR(MappeableContainer x) {
      if (this instanceof MappeableArrayContainer) {
         if (x instanceof MappeableArrayContainer) {
            return ((MappeableArrayContainer)this).lazyor((MappeableArrayContainer)x);
         } else {
            return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)x).lazyor((MappeableArrayContainer)this) : ((MappeableRunContainer)x).lazyor((MappeableArrayContainer)this);
         }
      } else if (this instanceof MappeableRunContainer) {
         if (x instanceof MappeableArrayContainer) {
            return ((MappeableRunContainer)this).ilazyor((MappeableArrayContainer)x);
         } else {
            return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)x).lazyor((MappeableRunContainer)this) : this.ior((MappeableRunContainer)x);
         }
      } else if (x instanceof MappeableArrayContainer) {
         return ((MappeableBitmapContainer)this).ilazyor((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)this).ilazyor((MappeableBitmapContainer)x) : ((MappeableBitmapContainer)this).ilazyor((MappeableRunContainer)x);
      }
   }

   public MappeableContainer lazyOR(MappeableContainer x) {
      if (this instanceof MappeableArrayContainer) {
         if (x instanceof MappeableArrayContainer) {
            return ((MappeableArrayContainer)this).lazyor((MappeableArrayContainer)x);
         } else {
            return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)x).lazyor((MappeableArrayContainer)this) : ((MappeableRunContainer)x).lazyor((MappeableArrayContainer)this);
         }
      } else if (this instanceof MappeableRunContainer) {
         if (x instanceof MappeableArrayContainer) {
            return ((MappeableRunContainer)this).lazyor((MappeableArrayContainer)x);
         } else {
            return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)x).lazyor((MappeableRunContainer)this) : this.or((MappeableRunContainer)x);
         }
      } else if (x instanceof MappeableArrayContainer) {
         return ((MappeableBitmapContainer)this).lazyor((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableBitmapContainer ? ((MappeableBitmapContainer)this).lazyor((MappeableBitmapContainer)x) : ((MappeableBitmapContainer)this).lazyor((MappeableRunContainer)x);
      }
   }

   public abstract MappeableContainer limit(int var1);

   public abstract MappeableContainer not(int var1, int var2);

   abstract int numberOfRuns();

   public abstract MappeableContainer or(MappeableArrayContainer var1);

   public abstract MappeableContainer or(MappeableBitmapContainer var1);

   protected MappeableContainer or(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.or((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.or((MappeableRunContainer)x) : this.or((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer or(MappeableRunContainer var1);

   public abstract int rank(char var1);

   public abstract MappeableContainer remove(int var1, int var2);

   public abstract MappeableContainer remove(char var1);

   public abstract MappeableContainer repairAfterLazy();

   public abstract MappeableContainer runOptimize();

   public abstract char select(int var1);

   public abstract int serializedSizeInBytes();

   public abstract Container toContainer();

   public abstract void trim();

   protected abstract void writeArray(DataOutput var1) throws IOException;

   protected abstract void writeArray(ByteBuffer var1);

   public abstract MappeableContainer xor(MappeableArrayContainer var1);

   public abstract MappeableContainer xor(MappeableBitmapContainer var1);

   protected MappeableContainer xor(MappeableContainer x) {
      if (x instanceof MappeableArrayContainer) {
         return this.xor((MappeableArrayContainer)x);
      } else {
         return x instanceof MappeableRunContainer ? this.xor((MappeableRunContainer)x) : this.xor((MappeableBitmapContainer)x);
      }
   }

   public abstract MappeableContainer xor(MappeableRunContainer var1);

   public abstract MappeableBitmapContainer toBitmapContainer();

   public abstract int first();

   public abstract int last();

   public abstract int nextValue(char var1);

   public abstract int previousValue(char var1);

   public abstract int nextAbsentValue(char var1);

   public abstract int previousAbsentValue(char var1);

   protected void assertNonEmpty(boolean condition) {
      if (condition) {
         throw new NoSuchElementException("Empty " + this.getContainerName());
      }
   }
}
