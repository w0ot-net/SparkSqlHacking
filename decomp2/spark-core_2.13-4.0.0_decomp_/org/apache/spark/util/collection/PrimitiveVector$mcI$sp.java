package org.apache.spark.util.collection;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class PrimitiveVector$mcI$sp extends PrimitiveVector {
   public int[] _array$mcI$sp;

   public int[] _array$mcI$sp() {
      return this._array$mcI$sp;
   }

   public int[] _array() {
      return this._array$mcI$sp();
   }

   public void _array$mcI$sp_$eq(final int[] x$1) {
      this._array$mcI$sp = x$1;
   }

   public void _array_$eq(final int[] x$1) {
      this._array$mcI$sp_$eq(x$1);
   }

   public int apply(final int index) {
      return this.apply$mcI$sp(index);
   }

   public int apply$mcI$sp(final int index) {
      .MODULE$.require(index < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements());
      return this._array()[index];
   }

   public void $plus$eq(final int value) {
      this.$plus$eq$mcI$sp(value);
   }

   public void $plus$eq$mcI$sp(final int value) {
      if (this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() == this._array().length) {
         this.resize$mcI$sp(this._array().length * 2);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this._array()[this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()] = value;
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() + 1);
   }

   public int[] array() {
      return this.array$mcI$sp();
   }

   public int[] array$mcI$sp() {
      return this._array();
   }

   public PrimitiveVector trim() {
      return this.trim$mcI$sp();
   }

   public PrimitiveVector trim$mcI$sp() {
      return this.resize$mcI$sp(this.size());
   }

   public PrimitiveVector resize(final int newLength) {
      return this.resize$mcI$sp(newLength);
   }

   public PrimitiveVector resize$mcI$sp(final int newLength) {
      this._array_$eq(this.copyArrayWithLength$mcI$sp(newLength));
      if (newLength < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()) {
         this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(newLength);
      }

      return this;
   }

   public int[] toArray() {
      return this.toArray$mcI$sp();
   }

   public int[] toArray$mcI$sp() {
      return this.copyArrayWithLength$mcI$sp(this.size());
   }

   public int[] copyArrayWithLength(final int length) {
      return this.copyArrayWithLength$mcI$sp(length);
   }

   public int[] copyArrayWithLength$mcI$sp(final int length) {
      int[] copy = (int[])this.org$apache$spark$util$collection$PrimitiveVector$$evidence$1.newArray(length);
      scala.collection.ArrayOps..MODULE$.copyToArray$extension(.MODULE$.genericArrayOps(this._array()), copy);
      return copy;
   }

   public boolean specInstance$() {
      return true;
   }

   public PrimitiveVector$mcI$sp(final int initialSize, final ClassTag evidence$1) {
      super(initialSize, evidence$1);
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements = 0;
      this._array_$eq(evidence$1.newArray(initialSize));
   }
}
