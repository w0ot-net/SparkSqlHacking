package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public interface Rand$mcI$sp extends Rand {
   // $FF: synthetic method
   static int get$(final Rand$mcI$sp $this) {
      return $this.get();
   }

   default int get() {
      return this.get$mcI$sp();
   }

   // $FF: synthetic method
   static int get$mcI$sp$(final Rand$mcI$sp $this) {
      return $this.get$mcI$sp();
   }

   default int get$mcI$sp() {
      return this.draw$mcI$sp();
   }

   // $FF: synthetic method
   static int sample$(final Rand$mcI$sp $this) {
      return $this.sample();
   }

   default int sample() {
      return this.sample$mcI$sp();
   }

   // $FF: synthetic method
   static int sample$mcI$sp$(final Rand$mcI$sp $this) {
      return $this.sample$mcI$sp();
   }

   default int sample$mcI$sp() {
      return this.get$mcI$sp();
   }

   // $FF: synthetic method
   static DenseVector samplesVector$(final Rand$mcI$sp $this, final int size, final ClassTag m) {
      return $this.samplesVector(size, m);
   }

   default DenseVector samplesVector(final int size, final ClassTag m) {
      return this.samplesVector$mcI$sp(size, m);
   }

   // $FF: synthetic method
   static DenseVector samplesVector$mcI$sp$(final Rand$mcI$sp $this, final int size, final ClassTag m) {
      return $this.samplesVector$mcI$sp(size, m);
   }

   default DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      DenseVector result = new DenseVector(m.newArray(size));
      int index$macro$2 = 0;

      for(int limit$macro$4 = size; index$macro$2 < limit$macro$4; ++index$macro$2) {
         result.update(index$macro$2, BoxesRunTime.boxToInteger(this.draw$mcI$sp()));
      }

      return result;
   }

   // $FF: synthetic method
   static Rand flatMap$(final Rand$mcI$sp $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Rand flatMap(final Function1 f) {
      return this.flatMap$mcI$sp(f);
   }

   // $FF: synthetic method
   static Rand flatMap$mcI$sp$(final Rand$mcI$sp $this, final Function1 f) {
      return $this.flatMap$mcI$sp(f);
   }

   default Rand flatMap$mcI$sp(final Function1 f) {
      return new FlatMappedRand(this, f);
   }

   // $FF: synthetic method
   static Rand map$(final Rand$mcI$sp $this, final Function1 f) {
      return $this.map(f);
   }

   default Rand map(final Function1 f) {
      return this.map$mcI$sp(f);
   }

   // $FF: synthetic method
   static Rand map$mcI$sp$(final Rand$mcI$sp $this, final Function1 f) {
      return $this.map$mcI$sp(f);
   }

   default Rand map$mcI$sp(final Function1 f) {
      return new MappedRand(this, f);
   }

   // $FF: synthetic method
   static void foreach$(final Rand$mcI$sp $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      this.foreach$mcI$sp(f);
   }

   // $FF: synthetic method
   static void foreach$mcI$sp$(final Rand$mcI$sp $this, final Function1 f) {
      $this.foreach$mcI$sp(f);
   }

   default void foreach$mcI$sp(final Function1 f) {
      f.apply$mcVI$sp(this.get$mcI$sp());
   }

   // $FF: synthetic method
   static Rand filter$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.filter(p);
   }

   default Rand filter(final Function1 p) {
      return this.filter$mcI$sp(p);
   }

   // $FF: synthetic method
   static Rand filter$mcI$sp$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.filter$mcI$sp(p);
   }

   default Rand filter$mcI$sp(final Function1 p) {
      return this.condition$mcI$sp(p);
   }

   // $FF: synthetic method
   static Rand withFilter$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.withFilter(p);
   }

   default Rand withFilter(final Function1 p) {
      return this.withFilter$mcI$sp(p);
   }

   // $FF: synthetic method
   static Rand withFilter$mcI$sp$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.withFilter$mcI$sp(p);
   }

   default Rand withFilter$mcI$sp(final Function1 p) {
      return this.condition$mcI$sp(p);
   }

   // $FF: synthetic method
   static Rand condition$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.condition(p);
   }

   default Rand condition(final Function1 p) {
      return this.condition$mcI$sp(p);
   }

   // $FF: synthetic method
   static Rand condition$mcI$sp$(final Rand$mcI$sp $this, final Function1 p) {
      return $this.condition$mcI$sp(p);
   }

   default Rand condition$mcI$sp(final Function1 p) {
      return new SinglePredicateRand$mcI$sp(this, p);
   }
}
