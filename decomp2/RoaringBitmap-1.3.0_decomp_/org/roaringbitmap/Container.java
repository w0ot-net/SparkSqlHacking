package org.roaringbitmap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import org.roaringbitmap.buffer.MappeableContainer;

public abstract class Container implements Iterable, Cloneable, Externalizable, WordStorage {
   public static final String[] ContainerNames = new String[]{"bitmap", "array", "run"};

   public static Container rangeOfOnes(int start, int last) {
      int arrayContainerOverRunThreshold = 2;
      int cardinality = last - start;
      return (Container)(cardinality <= 2 ? new ArrayContainer(start, last) : new RunContainer(start, last));
   }

   public abstract Container add(int var1, int var2);

   public abstract Container add(char var1);

   public abstract Container and(ArrayContainer var1);

   public abstract Container and(BitmapContainer var1);

   public Container and(Container x) {
      if (x instanceof ArrayContainer) {
         return this.and((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.and((BitmapContainer)x) : this.and((RunContainer)x);
      }
   }

   public abstract Container and(RunContainer var1);

   protected abstract int andCardinality(ArrayContainer var1);

   protected abstract int andCardinality(BitmapContainer var1);

   protected abstract int andCardinality(RunContainer var1);

   public int andCardinality(Container x) {
      if (this.isEmpty()) {
         return 0;
      } else if (x.isEmpty()) {
         return 0;
      } else if (x instanceof ArrayContainer) {
         return this.andCardinality((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.andCardinality((BitmapContainer)x) : this.andCardinality((RunContainer)x);
      }
   }

   public int xorCardinality(Container other) {
      return this.getCardinality() + other.getCardinality() - 2 * this.andCardinality(other);
   }

   public abstract Container andNot(ArrayContainer var1);

   public abstract Container andNot(BitmapContainer var1);

   public Container andNot(Container x) {
      if (x instanceof ArrayContainer) {
         return this.andNot((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.andNot((BitmapContainer)x) : this.andNot((RunContainer)x);
      }
   }

   public abstract Container andNot(RunContainer var1);

   public Container orNot(Container x, int endOfRange) {
      return endOfRange < 65536 ? this.or(x.not(0, endOfRange).iremove(endOfRange, 65536)) : this.or(x.not(0, 65536));
   }

   public abstract void clear();

   public abstract Container clone();

   public abstract boolean isEmpty();

   public abstract boolean isFull();

   public abstract boolean contains(char var1);

   public abstract boolean contains(int var1, int var2);

   public boolean contains(Container subset) {
      if (subset instanceof RunContainer) {
         return this.contains((RunContainer)subset);
      } else if (subset instanceof ArrayContainer) {
         return this.contains((ArrayContainer)subset);
      } else {
         return subset instanceof BitmapContainer ? this.contains((BitmapContainer)subset) : false;
      }
   }

   protected abstract boolean contains(RunContainer var1);

   protected abstract boolean contains(ArrayContainer var1);

   protected abstract boolean contains(BitmapContainer var1);

   public abstract void deserialize(DataInput var1) throws IOException;

   public abstract void fillLeastSignificant16bits(int[] var1, int var2, int var3);

   public abstract Container flip(char var1);

   public abstract int getArraySizeInBytes();

   public abstract int getCardinality();

   public String getContainerName() {
      if (this instanceof BitmapContainer) {
         return ContainerNames[0];
      } else {
         return this instanceof ArrayContainer ? ContainerNames[1] : ContainerNames[2];
      }
   }

   public abstract void forEach(char var1, IntConsumer var2);

   public abstract void forAll(int var1, RelativeRangeConsumer var2);

   public abstract void forAllFrom(char var1, RelativeRangeConsumer var2);

   public abstract void forAllUntil(int var1, char var2, RelativeRangeConsumer var3);

   public abstract void forAllInRange(char var1, char var2, RelativeRangeConsumer var3);

   public abstract PeekableCharIterator getReverseCharIterator();

   public abstract PeekableCharIterator getCharIterator();

   public abstract PeekableCharRankIterator getCharRankIterator();

   public abstract ContainerBatchIterator getBatchIterator();

   public abstract int getSizeInBytes();

   public abstract Container iadd(int var1, int var2);

   public abstract Container iand(ArrayContainer var1);

   public abstract Container iand(BitmapContainer var1);

   public Container iand(Container x) {
      if (x instanceof ArrayContainer) {
         return this.iand((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.iand((BitmapContainer)x) : this.iand((RunContainer)x);
      }
   }

   public abstract Container iand(RunContainer var1);

   public abstract Container iandNot(ArrayContainer var1);

   public abstract Container iandNot(BitmapContainer var1);

   public Container iandNot(Container x) {
      if (x instanceof ArrayContainer) {
         return this.iandNot((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.iandNot((BitmapContainer)x) : this.iandNot((RunContainer)x);
      }
   }

   public abstract Container iandNot(RunContainer var1);

   public Container iorNot(Container x, int endOfRange) {
      return endOfRange < 65536 ? this.ior(x.not(0, endOfRange).iremove(endOfRange, 65536)) : this.ior(x.not(0, 65536));
   }

   public abstract Container inot(int var1, int var2);

   public abstract boolean intersects(ArrayContainer var1);

   public abstract boolean intersects(BitmapContainer var1);

   public boolean intersects(Container x) {
      if (x instanceof ArrayContainer) {
         return this.intersects((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.intersects((BitmapContainer)x) : this.intersects((RunContainer)x);
      }
   }

   public abstract boolean intersects(RunContainer var1);

   public abstract boolean intersects(int var1, int var2);

   public abstract Container ior(ArrayContainer var1);

   public abstract Container ior(BitmapContainer var1);

   public Container ior(Container x) {
      if (x instanceof ArrayContainer) {
         return this.ior((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.ior((BitmapContainer)x) : this.ior((RunContainer)x);
      }
   }

   public abstract Container ior(RunContainer var1);

   public abstract Container iremove(int var1, int var2);

   public abstract Container ixor(ArrayContainer var1);

   public abstract Container ixor(BitmapContainer var1);

   public Container ixor(Container x) {
      if (x instanceof ArrayContainer) {
         return this.ixor((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.ixor((BitmapContainer)x) : this.ixor((RunContainer)x);
      }
   }

   public abstract Container ixor(RunContainer var1);

   public Container lazyIOR(Container x) {
      if (this instanceof ArrayContainer) {
         if (x instanceof ArrayContainer) {
            return ((ArrayContainer)this).lazyor((ArrayContainer)x);
         } else {
            return x instanceof BitmapContainer ? this.ior((BitmapContainer)x) : ((RunContainer)x).lazyor((ArrayContainer)this);
         }
      } else if (this instanceof RunContainer) {
         if (x instanceof ArrayContainer) {
            return ((RunContainer)this).ilazyor((ArrayContainer)x);
         } else {
            return x instanceof BitmapContainer ? this.ior((BitmapContainer)x) : this.ior((RunContainer)x);
         }
      } else if (x instanceof ArrayContainer) {
         return ((BitmapContainer)this).ilazyor((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? ((BitmapContainer)this).ilazyor((BitmapContainer)x) : ((BitmapContainer)this).ilazyor((RunContainer)x);
      }
   }

   public Container lazyOR(Container x) {
      if (this instanceof ArrayContainer) {
         if (x instanceof ArrayContainer) {
            return ((ArrayContainer)this).lazyor((ArrayContainer)x);
         } else {
            return x instanceof BitmapContainer ? ((BitmapContainer)x).lazyor((ArrayContainer)this) : ((RunContainer)x).lazyor((ArrayContainer)this);
         }
      } else if (this instanceof RunContainer) {
         if (x instanceof ArrayContainer) {
            return ((RunContainer)this).lazyor((ArrayContainer)x);
         } else {
            return x instanceof BitmapContainer ? ((BitmapContainer)x).lazyor((RunContainer)this) : this.or((RunContainer)x);
         }
      } else if (x instanceof ArrayContainer) {
         return ((BitmapContainer)this).lazyor((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? ((BitmapContainer)this).lazyor((BitmapContainer)x) : ((BitmapContainer)this).lazyor((RunContainer)x);
      }
   }

   public abstract Container limit(int var1);

   public abstract Container not(int var1, int var2);

   abstract int numberOfRuns();

   public abstract Container or(ArrayContainer var1);

   public abstract Container or(BitmapContainer var1);

   public Container or(Container x) {
      if (x instanceof ArrayContainer) {
         return this.or((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.or((BitmapContainer)x) : this.or((RunContainer)x);
      }
   }

   public abstract Container or(RunContainer var1);

   public abstract int rank(char var1);

   public abstract Container remove(int var1, int var2);

   public abstract Container remove(char var1);

   public abstract Container repairAfterLazy();

   public abstract Container runOptimize();

   public abstract char select(int var1);

   public abstract void serialize(DataOutput var1) throws IOException;

   public abstract int serializedSizeInBytes();

   public abstract MappeableContainer toMappeableContainer();

   public abstract void trim();

   public abstract void writeArray(DataOutput var1) throws IOException;

   public abstract void writeArray(ByteBuffer var1);

   public abstract Container xor(ArrayContainer var1);

   public abstract Container xor(BitmapContainer var1);

   public Container xor(Container x) {
      if (x instanceof ArrayContainer) {
         return this.xor((ArrayContainer)x);
      } else {
         return x instanceof BitmapContainer ? this.xor((BitmapContainer)x) : this.xor((RunContainer)x);
      }
   }

   public abstract Container xor(RunContainer var1);

   public abstract BitmapContainer toBitmapContainer();

   public abstract void copyBitmapTo(long[] var1, int var2);

   public abstract int nextValue(char var1);

   public abstract int previousValue(char var1);

   public abstract int nextAbsentValue(char var1);

   public abstract int previousAbsentValue(char var1);

   public abstract int first();

   public abstract int last();

   protected void assertNonEmpty(boolean condition) {
      if (condition) {
         throw new NoSuchElementException("Empty " + this.getContainerName());
      }
   }
}
