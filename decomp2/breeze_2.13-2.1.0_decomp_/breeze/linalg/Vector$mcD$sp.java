package breeze.linalg;

import scala.Function1;
import scala.Function2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public interface Vector$mcD$sp extends Vector, VectorLike$mcD$sp {
   // $FF: synthetic method
   static DenseVector toDenseVector$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toDenseVector(cm);
   }

   default DenseVector toDenseVector(final ClassTag cm) {
      return this.toDenseVector$mcD$sp(cm);
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcD$sp$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toDenseVector$mcD$sp(cm);
   }

   default DenseVector toDenseVector$mcD$sp(final ClassTag cm) {
      return DenseVector$.MODULE$.apply$mDc$sp(this.toArray$mcD$sp(cm));
   }

   // $FF: synthetic method
   static double[] toArray$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toArray(cm);
   }

   default double[] toArray(final ClassTag cm) {
      return this.toArray$mcD$sp(cm);
   }

   // $FF: synthetic method
   static double[] toArray$mcD$sp$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toArray$mcD$sp(cm);
   }

   default double[] toArray$mcD$sp(final ClassTag cm) {
      double[] result = (double[])cm.newArray(this.length());

      for(int i = 0; i < this.length(); ++i) {
         result[i] = this.apply$mcID$sp(i);
      }

      return result;
   }

   // $FF: synthetic method
   static Vector toVector$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toVector(cm);
   }

   default Vector toVector(final ClassTag cm) {
      return this.toVector$mcD$sp(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcD$sp$(final Vector$mcD$sp $this, final ClassTag cm) {
      return $this.toVector$mcD$sp(cm);
   }

   default Vector toVector$mcD$sp(final ClassTag cm) {
      return Vector$.MODULE$.apply$mDc$sp(this.toArray$mcD$sp(cm));
   }

   // $FF: synthetic method
   static Vector padTo$(final Vector$mcD$sp $this, final int len, final double elem, final ClassTag cm) {
      return $this.padTo(len, elem, cm);
   }

   default Vector padTo(final int len, final double elem, final ClassTag cm) {
      return this.padTo$mcD$sp(len, elem, cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcD$sp$(final Vector$mcD$sp $this, final int len, final double elem, final ClassTag cm) {
      return $this.padTo$mcD$sp(len, elem, cm);
   }

   default Vector padTo$mcD$sp(final int len, final double elem, final ClassTag cm) {
      return Vector$.MODULE$.apply$mDc$sp((double[]).MODULE$.padTo$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcD$sp(cm)), len, BoxesRunTime.boxToDouble(elem), cm));
   }

   // $FF: synthetic method
   static boolean exists$(final Vector$mcD$sp $this, final Function1 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function1 f) {
      return this.exists$mcD$sp(f);
   }

   // $FF: synthetic method
   static boolean exists$mcD$sp$(final Vector$mcD$sp $this, final Function1 f) {
      return $this.exists$mcD$sp(f);
   }

   default boolean exists$mcD$sp(final Function1 f) {
      return this.valuesIterator().exists(f);
   }

   // $FF: synthetic method
   static boolean forall$(final Vector$mcD$sp $this, final Function1 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function1 f) {
      return this.forall$mcD$sp(f);
   }

   // $FF: synthetic method
   static boolean forall$mcD$sp$(final Vector$mcD$sp $this, final Function1 f) {
      return $this.forall$mcD$sp(f);
   }

   default boolean forall$mcD$sp(final Function1 f) {
      return this.valuesIterator().forall(f);
   }

   // $FF: synthetic method
   static Object fold$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      return this.fold$mcD$sp(z, op);
   }

   // $FF: synthetic method
   static Object fold$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.fold$mcD$sp(z, op);
   }

   default Object fold$mcD$sp(final Object z, final Function2 op) {
      return this.valuesIterator().fold(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      return this.foldLeft$mcD$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcD$sp(z, op);
   }

   default Object foldLeft$mcD$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      return this.foldRight$mcD$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op) {
      return $this.foldRight$mcD$sp(z, op);
   }

   default Object foldRight$mcD$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldRight(z, op);
   }

   // $FF: synthetic method
   static Object reduce$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      return this.reduce$mcD$sp(op);
   }

   // $FF: synthetic method
   static Object reduce$mcD$sp$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduce$mcD$sp(op);
   }

   default Object reduce$mcD$sp(final Function2 op) {
      return this.valuesIterator().reduce(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduceLeft(op);
   }

   default Object reduceLeft(final Function2 op) {
      return this.reduceLeft$mcD$sp(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcD$sp$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduceLeft$mcD$sp(op);
   }

   default Object reduceLeft$mcD$sp(final Function2 op) {
      return this.valuesIterator().reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceRight$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduceRight(op);
   }

   default Object reduceRight(final Function2 op) {
      return this.reduceRight$mcD$sp(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcD$sp$(final Vector$mcD$sp $this, final Function2 op) {
      return $this.reduceRight$mcD$sp(op);
   }

   default Object reduceRight$mcD$sp(final Function2 op) {
      return this.valuesIterator().reduceRight(op);
   }

   // $FF: synthetic method
   static Vector scan$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan(z, op, cm, cm1);
   }

   default Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan$mcD$sp(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scan$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcD$sp(z, op, cm, cm1);
   }

   default Vector scan$mcD$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$.MODULE$.apply(.MODULE$.scan$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcD$sp(cm)), z, op, cm1));
   }

   // $FF: synthetic method
   static Vector scanLeft$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft(z, op, cm1);
   }

   default Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft$mcD$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcD$sp(z, op, cm1);
   }

   default Vector scanLeft$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(this.valuesIterator().scanLeft(z, op).toArray(cm1));
   }

   // $FF: synthetic method
   static Vector scanRight$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight(z, op, cm1);
   }

   default Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight$mcD$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcD$sp$(final Vector$mcD$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcD$sp(z, op, cm1);
   }

   default Vector scanRight$mcD$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(((IterableOnceOps)this.toScalaVector().scanRight(z, op)).toArray(cm1));
   }
}
