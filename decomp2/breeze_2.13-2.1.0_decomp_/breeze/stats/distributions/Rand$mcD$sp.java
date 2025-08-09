package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public interface Rand$mcD$sp extends Rand {
   // $FF: synthetic method
   static double get$(final Rand$mcD$sp $this) {
      return $this.get();
   }

   default double get() {
      return this.get$mcD$sp();
   }

   // $FF: synthetic method
   static double get$mcD$sp$(final Rand$mcD$sp $this) {
      return $this.get$mcD$sp();
   }

   default double get$mcD$sp() {
      return this.draw$mcD$sp();
   }

   // $FF: synthetic method
   static double sample$(final Rand$mcD$sp $this) {
      return $this.sample();
   }

   default double sample() {
      return this.sample$mcD$sp();
   }

   // $FF: synthetic method
   static double sample$mcD$sp$(final Rand$mcD$sp $this) {
      return $this.sample$mcD$sp();
   }

   default double sample$mcD$sp() {
      return this.get$mcD$sp();
   }

   // $FF: synthetic method
   static DenseVector samplesVector$(final Rand$mcD$sp $this, final int size, final ClassTag m) {
      return $this.samplesVector(size, m);
   }

   default DenseVector samplesVector(final int size, final ClassTag m) {
      return this.samplesVector$mcD$sp(size, m);
   }

   // $FF: synthetic method
   static DenseVector samplesVector$mcD$sp$(final Rand$mcD$sp $this, final int size, final ClassTag m) {
      return $this.samplesVector$mcD$sp(size, m);
   }

   default DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      DenseVector result = new DenseVector(m.newArray(size));
      int index$macro$2 = 0;

      for(int limit$macro$4 = size; index$macro$2 < limit$macro$4; ++index$macro$2) {
         result.update(index$macro$2, BoxesRunTime.boxToDouble(this.draw$mcD$sp()));
      }

      return result;
   }

   // $FF: synthetic method
   static Rand flatMap$(final Rand$mcD$sp $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Rand flatMap(final Function1 f) {
      return this.flatMap$mcD$sp(f);
   }

   // $FF: synthetic method
   static Rand flatMap$mcD$sp$(final Rand$mcD$sp $this, final Function1 f) {
      return $this.flatMap$mcD$sp(f);
   }

   default Rand flatMap$mcD$sp(final Function1 f) {
      return new FlatMappedRand(this, f);
   }

   // $FF: synthetic method
   static Rand map$(final Rand$mcD$sp $this, final Function1 f) {
      return $this.map(f);
   }

   default Rand map(final Function1 f) {
      return this.map$mcD$sp(f);
   }

   // $FF: synthetic method
   static Rand map$mcD$sp$(final Rand$mcD$sp $this, final Function1 f) {
      return $this.map$mcD$sp(f);
   }

   default Rand map$mcD$sp(final Function1 f) {
      return new MappedRand(this, f);
   }

   // $FF: synthetic method
   static void foreach$(final Rand$mcD$sp $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      this.foreach$mcD$sp(f);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final Rand$mcD$sp $this, final Function1 f) {
      $this.foreach$mcD$sp(f);
   }

   default void foreach$mcD$sp(final Function1 f) {
      f.apply$mcVD$sp(this.get$mcD$sp());
   }

   // $FF: synthetic method
   static Rand filter$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.filter(p);
   }

   default Rand filter(final Function1 p) {
      return this.filter$mcD$sp(p);
   }

   // $FF: synthetic method
   static Rand filter$mcD$sp$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.filter$mcD$sp(p);
   }

   default Rand filter$mcD$sp(final Function1 p) {
      return this.condition$mcD$sp(p);
   }

   // $FF: synthetic method
   static Rand withFilter$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.withFilter(p);
   }

   default Rand withFilter(final Function1 p) {
      return this.withFilter$mcD$sp(p);
   }

   // $FF: synthetic method
   static Rand withFilter$mcD$sp$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.withFilter$mcD$sp(p);
   }

   default Rand withFilter$mcD$sp(final Function1 p) {
      return this.condition$mcD$sp(p);
   }

   // $FF: synthetic method
   static Rand condition$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.condition(p);
   }

   default Rand condition(final Function1 p) {
      return this.condition$mcD$sp(p);
   }

   // $FF: synthetic method
   static Rand condition$mcD$sp$(final Rand$mcD$sp $this, final Function1 p) {
      return $this.condition$mcD$sp(p);
   }

   default Rand condition$mcD$sp(final Function1 p) {
      return new SinglePredicateRand$mcD$sp(this, p);
   }
}
