package breeze.linalg;

import scala.Function1;
import scala.Function2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public interface Vector$mcI$sp extends Vector, VectorLike$mcI$sp {
   // $FF: synthetic method
   static DenseVector toDenseVector$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toDenseVector(cm);
   }

   default DenseVector toDenseVector(final ClassTag cm) {
      return this.toDenseVector$mcI$sp(cm);
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcI$sp$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toDenseVector$mcI$sp(cm);
   }

   default DenseVector toDenseVector$mcI$sp(final ClassTag cm) {
      return DenseVector$.MODULE$.apply$mIc$sp(this.toArray$mcI$sp(cm));
   }

   // $FF: synthetic method
   static int[] toArray$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toArray(cm);
   }

   default int[] toArray(final ClassTag cm) {
      return this.toArray$mcI$sp(cm);
   }

   // $FF: synthetic method
   static int[] toArray$mcI$sp$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toArray$mcI$sp(cm);
   }

   default int[] toArray$mcI$sp(final ClassTag cm) {
      int[] result = (int[])cm.newArray(this.length());

      for(int i = 0; i < this.length(); ++i) {
         result[i] = this.apply$mcII$sp(i);
      }

      return result;
   }

   // $FF: synthetic method
   static Vector toVector$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toVector(cm);
   }

   default Vector toVector(final ClassTag cm) {
      return this.toVector$mcI$sp(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcI$sp$(final Vector$mcI$sp $this, final ClassTag cm) {
      return $this.toVector$mcI$sp(cm);
   }

   default Vector toVector$mcI$sp(final ClassTag cm) {
      return Vector$.MODULE$.apply$mIc$sp(this.toArray$mcI$sp(cm));
   }

   // $FF: synthetic method
   static Vector padTo$(final Vector$mcI$sp $this, final int len, final int elem, final ClassTag cm) {
      return $this.padTo(len, elem, cm);
   }

   default Vector padTo(final int len, final int elem, final ClassTag cm) {
      return this.padTo$mcI$sp(len, elem, cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcI$sp$(final Vector$mcI$sp $this, final int len, final int elem, final ClassTag cm) {
      return $this.padTo$mcI$sp(len, elem, cm);
   }

   default Vector padTo$mcI$sp(final int len, final int elem, final ClassTag cm) {
      return Vector$.MODULE$.apply$mIc$sp((int[]).MODULE$.padTo$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcI$sp(cm)), len, BoxesRunTime.boxToInteger(elem), cm));
   }

   // $FF: synthetic method
   static boolean exists$(final Vector$mcI$sp $this, final Function1 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function1 f) {
      return this.exists$mcI$sp(f);
   }

   // $FF: synthetic method
   static boolean exists$mcI$sp$(final Vector$mcI$sp $this, final Function1 f) {
      return $this.exists$mcI$sp(f);
   }

   default boolean exists$mcI$sp(final Function1 f) {
      return this.valuesIterator().exists(f);
   }

   // $FF: synthetic method
   static boolean forall$(final Vector$mcI$sp $this, final Function1 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function1 f) {
      return this.forall$mcI$sp(f);
   }

   // $FF: synthetic method
   static boolean forall$mcI$sp$(final Vector$mcI$sp $this, final Function1 f) {
      return $this.forall$mcI$sp(f);
   }

   default boolean forall$mcI$sp(final Function1 f) {
      return this.valuesIterator().forall(f);
   }

   // $FF: synthetic method
   static Object fold$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      return this.fold$mcI$sp(z, op);
   }

   // $FF: synthetic method
   static Object fold$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.fold$mcI$sp(z, op);
   }

   default Object fold$mcI$sp(final Object z, final Function2 op) {
      return this.valuesIterator().fold(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      return this.foldLeft$mcI$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcI$sp(z, op);
   }

   default Object foldLeft$mcI$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      return this.foldRight$mcI$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op) {
      return $this.foldRight$mcI$sp(z, op);
   }

   default Object foldRight$mcI$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldRight(z, op);
   }

   // $FF: synthetic method
   static Object reduce$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      return this.reduce$mcI$sp(op);
   }

   // $FF: synthetic method
   static Object reduce$mcI$sp$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduce$mcI$sp(op);
   }

   default Object reduce$mcI$sp(final Function2 op) {
      return this.valuesIterator().reduce(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduceLeft(op);
   }

   default Object reduceLeft(final Function2 op) {
      return this.reduceLeft$mcI$sp(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcI$sp$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduceLeft$mcI$sp(op);
   }

   default Object reduceLeft$mcI$sp(final Function2 op) {
      return this.valuesIterator().reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceRight$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduceRight(op);
   }

   default Object reduceRight(final Function2 op) {
      return this.reduceRight$mcI$sp(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcI$sp$(final Vector$mcI$sp $this, final Function2 op) {
      return $this.reduceRight$mcI$sp(op);
   }

   default Object reduceRight$mcI$sp(final Function2 op) {
      return this.valuesIterator().reduceRight(op);
   }

   // $FF: synthetic method
   static Vector scan$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan(z, op, cm, cm1);
   }

   default Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan$mcI$sp(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scan$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcI$sp(z, op, cm, cm1);
   }

   default Vector scan$mcI$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$.MODULE$.apply(.MODULE$.scan$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcI$sp(cm)), z, op, cm1));
   }

   // $FF: synthetic method
   static Vector scanLeft$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft(z, op, cm1);
   }

   default Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft$mcI$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcI$sp(z, op, cm1);
   }

   default Vector scanLeft$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(this.valuesIterator().scanLeft(z, op).toArray(cm1));
   }

   // $FF: synthetic method
   static Vector scanRight$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight(z, op, cm1);
   }

   default Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight$mcI$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcI$sp$(final Vector$mcI$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcI$sp(z, op, cm1);
   }

   default Vector scanRight$mcI$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(((IterableOnceOps)this.toScalaVector().scanRight(z, op)).toArray(cm1));
   }
}
