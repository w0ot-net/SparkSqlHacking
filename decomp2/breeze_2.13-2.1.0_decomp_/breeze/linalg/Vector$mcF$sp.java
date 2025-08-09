package breeze.linalg;

import scala.Function1;
import scala.Function2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public interface Vector$mcF$sp extends Vector, VectorLike$mcF$sp {
   // $FF: synthetic method
   static DenseVector toDenseVector$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toDenseVector(cm);
   }

   default DenseVector toDenseVector(final ClassTag cm) {
      return this.toDenseVector$mcF$sp(cm);
   }

   // $FF: synthetic method
   static DenseVector toDenseVector$mcF$sp$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toDenseVector$mcF$sp(cm);
   }

   default DenseVector toDenseVector$mcF$sp(final ClassTag cm) {
      return DenseVector$.MODULE$.apply$mFc$sp(this.toArray$mcF$sp(cm));
   }

   // $FF: synthetic method
   static float[] toArray$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toArray(cm);
   }

   default float[] toArray(final ClassTag cm) {
      return this.toArray$mcF$sp(cm);
   }

   // $FF: synthetic method
   static float[] toArray$mcF$sp$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toArray$mcF$sp(cm);
   }

   default float[] toArray$mcF$sp(final ClassTag cm) {
      float[] result = (float[])cm.newArray(this.length());

      for(int i = 0; i < this.length(); ++i) {
         result[i] = this.apply$mcIF$sp(i);
      }

      return result;
   }

   // $FF: synthetic method
   static Vector toVector$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toVector(cm);
   }

   default Vector toVector(final ClassTag cm) {
      return this.toVector$mcF$sp(cm);
   }

   // $FF: synthetic method
   static Vector toVector$mcF$sp$(final Vector$mcF$sp $this, final ClassTag cm) {
      return $this.toVector$mcF$sp(cm);
   }

   default Vector toVector$mcF$sp(final ClassTag cm) {
      return Vector$.MODULE$.apply$mFc$sp(this.toArray$mcF$sp(cm));
   }

   // $FF: synthetic method
   static Vector padTo$(final Vector$mcF$sp $this, final int len, final float elem, final ClassTag cm) {
      return $this.padTo(len, elem, cm);
   }

   default Vector padTo(final int len, final float elem, final ClassTag cm) {
      return this.padTo$mcF$sp(len, elem, cm);
   }

   // $FF: synthetic method
   static Vector padTo$mcF$sp$(final Vector$mcF$sp $this, final int len, final float elem, final ClassTag cm) {
      return $this.padTo$mcF$sp(len, elem, cm);
   }

   default Vector padTo$mcF$sp(final int len, final float elem, final ClassTag cm) {
      return Vector$.MODULE$.apply$mFc$sp((float[]).MODULE$.padTo$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcF$sp(cm)), len, BoxesRunTime.boxToFloat(elem), cm));
   }

   // $FF: synthetic method
   static boolean exists$(final Vector$mcF$sp $this, final Function1 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function1 f) {
      return this.exists$mcF$sp(f);
   }

   // $FF: synthetic method
   static boolean exists$mcF$sp$(final Vector$mcF$sp $this, final Function1 f) {
      return $this.exists$mcF$sp(f);
   }

   default boolean exists$mcF$sp(final Function1 f) {
      return this.valuesIterator().exists(f);
   }

   // $FF: synthetic method
   static boolean forall$(final Vector$mcF$sp $this, final Function1 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function1 f) {
      return this.forall$mcF$sp(f);
   }

   // $FF: synthetic method
   static boolean forall$mcF$sp$(final Vector$mcF$sp $this, final Function1 f) {
      return $this.forall$mcF$sp(f);
   }

   default boolean forall$mcF$sp(final Function1 f) {
      return this.valuesIterator().forall(f);
   }

   // $FF: synthetic method
   static Object fold$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      return this.fold$mcF$sp(z, op);
   }

   // $FF: synthetic method
   static Object fold$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.fold$mcF$sp(z, op);
   }

   default Object fold$mcF$sp(final Object z, final Function2 op) {
      return this.valuesIterator().fold(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      return this.foldLeft$mcF$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldLeft$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.foldLeft$mcF$sp(z, op);
   }

   default Object foldLeft$mcF$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      return this.foldRight$mcF$sp(z, op);
   }

   // $FF: synthetic method
   static Object foldRight$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op) {
      return $this.foldRight$mcF$sp(z, op);
   }

   default Object foldRight$mcF$sp(final Object z, final Function2 op) {
      return this.valuesIterator().foldRight(z, op);
   }

   // $FF: synthetic method
   static Object reduce$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      return this.reduce$mcF$sp(op);
   }

   // $FF: synthetic method
   static Object reduce$mcF$sp$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduce$mcF$sp(op);
   }

   default Object reduce$mcF$sp(final Function2 op) {
      return this.valuesIterator().reduce(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduceLeft(op);
   }

   default Object reduceLeft(final Function2 op) {
      return this.reduceLeft$mcF$sp(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$mcF$sp$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduceLeft$mcF$sp(op);
   }

   default Object reduceLeft$mcF$sp(final Function2 op) {
      return this.valuesIterator().reduceLeft(op);
   }

   // $FF: synthetic method
   static Object reduceRight$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduceRight(op);
   }

   default Object reduceRight(final Function2 op) {
      return this.reduceRight$mcF$sp(op);
   }

   // $FF: synthetic method
   static Object reduceRight$mcF$sp$(final Vector$mcF$sp $this, final Function2 op) {
      return $this.reduceRight$mcF$sp(op);
   }

   default Object reduceRight$mcF$sp(final Function2 op) {
      return this.valuesIterator().reduceRight(op);
   }

   // $FF: synthetic method
   static Vector scan$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan(z, op, cm, cm1);
   }

   default Vector scan(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return this.scan$mcF$sp(z, op, cm, cm1);
   }

   // $FF: synthetic method
   static Vector scan$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return $this.scan$mcF$sp(z, op, cm, cm1);
   }

   default Vector scan$mcF$sp(final Object z, final Function2 op, final ClassTag cm, final ClassTag cm1) {
      return Vector$.MODULE$.apply(.MODULE$.scan$extension(scala.Predef..MODULE$.genericArrayOps(this.toArray$mcF$sp(cm)), z, op, cm1));
   }

   // $FF: synthetic method
   static Vector scanLeft$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft(z, op, cm1);
   }

   default Vector scanLeft(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanLeft$mcF$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanLeft$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanLeft$mcF$sp(z, op, cm1);
   }

   default Vector scanLeft$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(this.valuesIterator().scanLeft(z, op).toArray(cm1));
   }

   // $FF: synthetic method
   static Vector scanRight$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight(z, op, cm1);
   }

   default Vector scanRight(final Object z, final Function2 op, final ClassTag cm1) {
      return this.scanRight$mcF$sp(z, op, cm1);
   }

   // $FF: synthetic method
   static Vector scanRight$mcF$sp$(final Vector$mcF$sp $this, final Object z, final Function2 op, final ClassTag cm1) {
      return $this.scanRight$mcF$sp(z, op, cm1);
   }

   default Vector scanRight$mcF$sp(final Object z, final Function2 op, final ClassTag cm1) {
      return Vector$.MODULE$.apply(((IterableOnceOps)this.toScalaVector().scanRight(z, op)).toArray(cm1));
   }
}
