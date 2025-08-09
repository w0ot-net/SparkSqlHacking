package org.apache.spark.util.collection;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class PrimitiveVector$mcD$sp extends PrimitiveVector {
   public double[] _array$mcD$sp;

   public double[] _array$mcD$sp() {
      return this._array$mcD$sp;
   }

   public double[] _array() {
      return this._array$mcD$sp();
   }

   public void _array$mcD$sp_$eq(final double[] x$1) {
      this._array$mcD$sp = x$1;
   }

   public void _array_$eq(final double[] x$1) {
      this._array$mcD$sp_$eq(x$1);
   }

   public double apply(final int index) {
      return this.apply$mcD$sp(index);
   }

   public double apply$mcD$sp(final int index) {
      .MODULE$.require(index < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements());
      return this._array()[index];
   }

   public void $plus$eq(final double value) {
      this.$plus$eq$mcD$sp(value);
   }

   public void $plus$eq$mcD$sp(final double value) {
      if (this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() == this._array().length) {
         this.resize$mcD$sp(this._array().length * 2);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this._array()[this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()] = value;
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(this.org$apache$spark$util$collection$PrimitiveVector$$_numElements() + 1);
   }

   public double[] array() {
      return this.array$mcD$sp();
   }

   public double[] array$mcD$sp() {
      return this._array();
   }

   public PrimitiveVector trim() {
      return this.trim$mcD$sp();
   }

   public PrimitiveVector trim$mcD$sp() {
      return this.resize$mcD$sp(this.size());
   }

   public PrimitiveVector resize(final int newLength) {
      return this.resize$mcD$sp(newLength);
   }

   public PrimitiveVector resize$mcD$sp(final int newLength) {
      this._array_$eq(this.copyArrayWithLength$mcD$sp(newLength));
      if (newLength < this.org$apache$spark$util$collection$PrimitiveVector$$_numElements()) {
         this.org$apache$spark$util$collection$PrimitiveVector$$_numElements_$eq(newLength);
      }

      return this;
   }

   public double[] toArray() {
      return this.toArray$mcD$sp();
   }

   public double[] toArray$mcD$sp() {
      return this.copyArrayWithLength$mcD$sp(this.size());
   }

   public double[] copyArrayWithLength(final int length) {
      return this.copyArrayWithLength$mcD$sp(length);
   }

   public double[] copyArrayWithLength$mcD$sp(final int length) {
      double[] copy = (double[])this.org$apache$spark$util$collection$PrimitiveVector$$evidence$1.newArray(length);
      scala.collection.ArrayOps..MODULE$.copyToArray$extension(.MODULE$.genericArrayOps(this._array()), copy);
      return copy;
   }

   public boolean specInstance$() {
      return true;
   }

   public PrimitiveVector$mcD$sp(final int initialSize, final ClassTag evidence$1) {
      super(initialSize, evidence$1);
      this.org$apache$spark$util$collection$PrimitiveVector$$_numElements = 0;
      this._array_$eq(evidence$1.newArray(initialSize));
   }
}
