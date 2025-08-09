package org.apache.spark.util.collection;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class PrimitiveVector$mcJ$sp extends PrimitiveVector {
   public long[] _array$mcJ$sp;

   public long[] _array$mcJ$sp() {
      return this._array$mcJ$sp;
   }

   public long[] _array() {
      return this._array$mcJ$sp();
   }

   public void _array$mcJ$sp_$eq(final long[] x$1) {
      this._array$mcJ$sp = x$1;
   }

   public void _array_$eq(final long[] x$1) {
      this._array$mcJ$sp_$eq(x$1);
   }

   public long apply(final int index) {
      return this.apply$mcJ$sp(index);
   }

   public long apply$mcJ$sp(final int index) {
      .MODULE$.require(index < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements());
      return this._array()[index];
   }

   public void $plus$eq(final long value) {
      this.$plus$eq$mcJ$sp(value);
   }

   public void $plus$eq$mcJ$sp(final long value) {
      if (this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() == this._array().length) {
         this.resize$mcJ$sp(this._array().length * 2);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this._array()[this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()] = value;
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() + 1);
   }

   public long[] array() {
      return this.array$mcJ$sp();
   }

   public long[] array$mcJ$sp() {
      return this._array();
   }

   public PrimitiveVector trim() {
      return this.trim$mcJ$sp();
   }

   public PrimitiveVector trim$mcJ$sp() {
      return this.resize$mcJ$sp(this.size());
   }

   public PrimitiveVector resize(final int newLength) {
      return this.resize$mcJ$sp(newLength);
   }

   public PrimitiveVector resize$mcJ$sp(final int newLength) {
      this._array_$eq(this.copyArrayWithLength$mcJ$sp(newLength));
      if (newLength < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()) {
         this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(newLength);
      }

      return this;
   }

   public long[] toArray() {
      return this.toArray$mcJ$sp();
   }

   public long[] toArray$mcJ$sp() {
      return this.copyArrayWithLength$mcJ$sp(this.size());
   }

   public long[] copyArrayWithLength(final int length) {
      return this.copyArrayWithLength$mcJ$sp(length);
   }

   public long[] copyArrayWithLength$mcJ$sp(final int length) {
      long[] copy = (long[])this.org$apache$spark$util$collection$PrimitiveVector$$evidence$1.newArray(length);
      scala.collection.ArrayOps..MODULE$.copyToArray$extension(.MODULE$.genericArrayOps(this._array()), copy);
      return copy;
   }

   public boolean specInstance$() {
      return true;
   }

   public PrimitiveVector$mcJ$sp(final int initialSize, final ClassTag evidence$1) {
      super(initialSize, evidence$1);
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements = 0;
      this._array_$eq(evidence$1.newArray(initialSize));
   }
}
