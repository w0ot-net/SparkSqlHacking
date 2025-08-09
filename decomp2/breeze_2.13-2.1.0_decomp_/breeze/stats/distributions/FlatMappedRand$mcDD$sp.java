package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class FlatMappedRand$mcDD$sp extends FlatMappedRand implements Rand$mcD$sp {
   public final Rand rand$mcD$sp;
   public final Function1 func$mcDD$sp;

   public double get() {
      return Rand$mcD$sp.get$(this);
   }

   public double get$mcD$sp() {
      return Rand$mcD$sp.get$mcD$sp$(this);
   }

   public double sample() {
      return Rand$mcD$sp.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand$mcD$sp.sample$mcD$sp$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand$mcD$sp.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand$mcD$sp.samplesVector$mcD$sp$(this, size, m);
   }

   public Rand map(final Function1 f) {
      return Rand$mcD$sp.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand$mcD$sp.map$mcD$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand$mcD$sp.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand$mcD$sp.foreach$mcD$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand$mcD$sp.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand$mcD$sp.filter$mcD$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand$mcD$sp.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand$mcD$sp.withFilter$mcD$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand$mcD$sp.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand$mcD$sp.condition$mcD$sp$(this, p);
   }

   public Rand rand$mcD$sp() {
      return this.rand$mcD$sp;
   }

   public Rand rand() {
      return this.rand$mcD$sp();
   }

   public Function1 func$mcDD$sp() {
      return this.func$mcDD$sp;
   }

   public Function1 func() {
      return this.func$mcDD$sp();
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double draw$mcD$sp() {
      return ((Rand)this.func().apply(BoxesRunTime.boxToDouble(this.rand().draw$mcD$sp()))).draw$mcD$sp();
   }

   public Rand flatMap(final Function1 f) {
      return this.flatMap$mcD$sp(f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return new FlatMappedRand(this.rand(), (x) -> $anonfun$flatMap$2(this, f, BoxesRunTime.unboxToDouble(x)));
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Rand copy$default$1$mcD$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcDD$sp();
   }

   public Function1 copy$default$2$mcDD$sp() {
      return this.func();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final Rand $anonfun$flatMap$2(final FlatMappedRand$mcDD$sp $this, final Function1 f$4, final double x) {
      return (Rand)f$4.apply(BoxesRunTime.boxToDouble(((Rand)$this.func().apply(BoxesRunTime.boxToDouble(x))).draw$mcD$sp()));
   }

   public FlatMappedRand$mcDD$sp(final Rand rand$mcD$sp, final Function1 func$mcDD$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcD$sp = rand$mcD$sp;
      this.func$mcDD$sp = func$mcDD$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
