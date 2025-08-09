package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Private;
import scala.Function1;
import scala.Function2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ManifestFactory;
import scala.runtime.BoxesRunTime;

@Private
public class OpenHashSet$mcI$sp extends OpenHashSet {
   public final OpenHashSet.Hasher hasher$mcI$sp;
   public int[] _data$mcI$sp;

   public OpenHashSet.Hasher hasher$mcI$sp() {
      return this.hasher$mcI$sp;
   }

   public OpenHashSet.Hasher hasher() {
      return this.hasher$mcI$sp();
   }

   public int[] _data$mcI$sp() {
      return this._data$mcI$sp;
   }

   public int[] _data() {
      return this._data$mcI$sp();
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data$mcI$sp = x$1;
   }

   public void _data_$eq(final int[] x$1) {
      this._data$mcI$sp_$eq(x$1);
   }

   public boolean contains(final int k) {
      return this.contains$mcI$sp(k);
   }

   public boolean contains$mcI$sp(final int k) {
      return this.getPos$mcI$sp(k) != OpenHashSet$.MODULE$.INVALID_POS();
   }

   public void add(final int k) {
      this.add$mcI$sp(k);
   }

   public void add$mcI$sp(final int k) {
      this.addWithoutResize$mcI$sp(k);
      this.rehashIfNeeded$mcI$sp(k, OpenHashSet$.MODULE$.org$apache$spark$util$collection$OpenHashSet$$grow(), OpenHashSet$.MODULE$.org$apache$spark$util$collection$OpenHashSet$$move());
   }

   public OpenHashSet union(final OpenHashSet other) {
      return this.union$mcI$sp(other);
   }

   public OpenHashSet union$mcI$sp(final OpenHashSet other) {
      Iterator iterator = other.iterator();

      while(iterator.hasNext()) {
         this.add$mcI$sp(BoxesRunTime.unboxToInt(iterator.next()));
      }

      return this;
   }

   public boolean keyExistsAtPos(final int k, final int pos) {
      return this.keyExistsAtPos$mcI$sp(k, pos);
   }

   public boolean keyExistsAtPos$mcI$sp(final int k, final int pos) {
      return BoxesRunTime.boxToInteger(this._data()[pos]).equals(BoxesRunTime.boxToInteger(k));
   }

   public int addWithoutResize(final int k) {
      return this.addWithoutResize$mcI$sp(k);
   }

   public int addWithoutResize$mcI$sp(final int k) {
      int pos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash$mcI$sp(k)) & this._mask();

      for(int delta = 1; this._bitset().get(pos); ++delta) {
         if (this.keyExistsAtPos$mcI$sp(k, pos)) {
            return pos;
         }

         pos = pos + delta & this._mask();
      }

      this._data()[pos] = k;
      this._bitset().set(pos);
      this._size_$eq(this._size() + 1);
      return pos | OpenHashSet$.MODULE$.NONEXISTENCE_MASK();
   }

   public void rehashIfNeeded(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehashIfNeeded$mcI$sp(k, allocateFunc, moveFunc);
   }

   public void rehashIfNeeded$mcI$sp(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      if (this._size() > this._growThreshold()) {
         this.rehash$mcI$sp(k, allocateFunc, moveFunc);
      }
   }

   public int getPos(final int k) {
      return this.getPos$mcI$sp(k);
   }

   public int getPos$mcI$sp(final int k) {
      int pos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash$mcI$sp(k)) & this._mask();

      for(int delta = 1; this._bitset().get(pos); ++delta) {
         if (this.keyExistsAtPos$mcI$sp(k, pos)) {
            return pos;
         }

         pos = pos + delta & this._mask();
      }

      return OpenHashSet$.MODULE$.INVALID_POS();
   }

   public int getValue(final int pos) {
      return this.getValue$mcI$sp(pos);
   }

   public int getValue$mcI$sp(final int pos) {
      return this._data()[pos];
   }

   public int getValueSafe(final int pos) {
      return this.getValueSafe$mcI$sp(pos);
   }

   public int getValueSafe$mcI$sp(final int pos) {
      .MODULE$.assert(this._bitset().get(pos));
      return this._data()[pos];
   }

   public void rehash(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      this.rehash$mcI$sp(k, allocateFunc, moveFunc);
   }

   public void rehash$mcI$sp(final int k, final Function1 allocateFunc, final Function2 moveFunc) {
      int newCapacity = this._capacity() * 2;
      .MODULE$.require(newCapacity > 0 && newCapacity <= OpenHashSet$.MODULE$.MAX_CAPACITY(), () -> {
         double var10000 = this.org$apache$spark$util$collection$OpenHashSet$$loadFactor;
         return "Can't contain more than " + (int)(var10000 * (double)OpenHashSet$.MODULE$.MAX_CAPACITY()) + " elements";
      });
      allocateFunc.apply$mcVI$sp(newCapacity);
      BitSet newBitset = new BitSet(newCapacity);
      int[] newData = (int[])this.org$apache$spark$util$collection$OpenHashSet$$evidence$1.newArray(newCapacity);
      int newMask = newCapacity - 1;

      for(int oldPos = 0; oldPos < this.capacity(); ++oldPos) {
         if (this._bitset().get(oldPos)) {
            int key = this._data()[oldPos];
            int newPos = this.org$apache$spark$util$collection$OpenHashSet$$hashcode(this.hasher().hash$mcI$sp(key)) & newMask;
            int i = 1;
            boolean keepGoing = true;

            while(keepGoing) {
               if (!newBitset.get(newPos)) {
                  newData[newPos] = key;
                  newBitset.set(newPos);
                  moveFunc.apply$mcVII$sp(oldPos, newPos);
                  keepGoing = false;
               } else {
                  newPos = newPos + i & newMask;
                  ++i;
               }
            }
         }
      }

      this._bitset_$eq(newBitset);
      this._data_$eq(newData);
      this._capacity_$eq(newCapacity);
      this._mask_$eq(newMask);
      this._growThreshold_$eq((int)(this.org$apache$spark$util$collection$OpenHashSet$$loadFactor * (double)newCapacity));
   }

   public boolean specInstance$() {
      return true;
   }

   public OpenHashSet$mcI$sp(final int initialCapacity, final double loadFactor, final ClassTag evidence$1) {
      Object var14;
      label76: {
         label80: {
            super(initialCapacity, loadFactor, evidence$1);
            .MODULE$.require(initialCapacity <= OpenHashSet$.MODULE$.MAX_CAPACITY(), OpenHashSet::org$apache$spark$util$collection$OpenHashSet$$$anonfun$new$1);
            .MODULE$.require(initialCapacity >= 0, OpenHashSet::org$apache$spark$util$collection$OpenHashSet$$$anonfun$new$2);
            .MODULE$.require(loadFactor < (double)1.0F, OpenHashSet::org$apache$spark$util$collection$OpenHashSet$$$anonfun$new$3);
            .MODULE$.require(loadFactor > (double)0.0F, OpenHashSet::org$apache$spark$util$collection$OpenHashSet$$$anonfun$new$4);
            ClassTag var6 = scala.reflect.package..MODULE$.classTag(this.org$apache$spark$util$collection$OpenHashSet$$evidence$1);
            ManifestFactory.LongManifest var10001 = scala.reflect.ClassTag..MODULE$.Long();
            if (var10001 == null) {
               if (var6 == null) {
                  break label80;
               }
            } else if (var10001.equals(var6)) {
               break label80;
            }

            label81: {
               ManifestFactory.IntManifest var11 = scala.reflect.ClassTag..MODULE$.Int();
               if (var11 == null) {
                  if (var6 == null) {
                     break label81;
                  }
               } else if (var11.equals(var6)) {
                  break label81;
               }

               label82: {
                  ManifestFactory.DoubleManifest var12 = scala.reflect.ClassTag..MODULE$.Double();
                  if (var12 == null) {
                     if (var6 == null) {
                        break label82;
                     }
                  } else if (var12.equals(var6)) {
                     break label82;
                  }

                  label54: {
                     ManifestFactory.FloatManifest var13 = scala.reflect.ClassTag..MODULE$.Float();
                     if (var13 == null) {
                        if (var6 == null) {
                           break label54;
                        }
                     } else if (var13.equals(var6)) {
                        break label54;
                     }

                     var14 = new OpenHashSet$Hasher$mcI$sp();
                     break label76;
                  }

                  var14 = new OpenHashSet.FloatHasher();
                  break label76;
               }

               var14 = new OpenHashSet.DoubleHasher();
               break label76;
            }

            var14 = new OpenHashSet.IntHasher();
            break label76;
         }

         var14 = new OpenHashSet.LongHasher();
      }

      this.hasher$mcI$sp = (OpenHashSet.Hasher)var14;
      this.org$apache$spark$util$collection$OpenHashSet$$_capacity = this.org$apache$spark$util$collection$OpenHashSet$$nextPowerOf2(initialCapacity);
      this.org$apache$spark$util$collection$OpenHashSet$$_mask = this._capacity() - 1;
      this.org$apache$spark$util$collection$OpenHashSet$$_size = 0;
      this.org$apache$spark$util$collection$OpenHashSet$$_growThreshold = (int)(loadFactor * (double)this._capacity());
      this.org$apache$spark$util$collection$OpenHashSet$$_bitset = new BitSet(this._capacity());
      this._data_$eq(evidence$1.newArray(this._capacity()));
   }

   public OpenHashSet$mcI$sp(final int initialCapacity, final ClassTag evidence$2) {
      this(initialCapacity, 0.7, evidence$2);
   }

   public OpenHashSet$mcI$sp(final ClassTag evidence$3) {
      this(64, evidence$3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
