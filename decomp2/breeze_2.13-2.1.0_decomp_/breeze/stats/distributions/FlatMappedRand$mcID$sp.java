package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class FlatMappedRand$mcID$sp extends FlatMappedRand implements Rand$mcD$sp {
   public final Rand rand$mcI$sp;
   public final Function1 func$mcID$sp;

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

   public Rand rand$mcI$sp() {
      return this.rand$mcI$sp;
   }

   public Rand rand() {
      return this.rand$mcI$sp();
   }

   public Function1 func$mcID$sp() {
      return this.func$mcID$sp;
   }

   public Function1 func() {
      return this.func$mcID$sp();
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double draw$mcD$sp() {
      return ((Rand)this.func().apply(BoxesRunTime.boxToInteger(this.rand().draw$mcI$sp()))).draw$mcD$sp();
   }

   public Rand flatMap(final Function1 f) {
      return this.flatMap$mcD$sp(f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return new FlatMappedRand(this.rand(), (x) -> $anonfun$flatMap$4(this, f, BoxesRunTime.unboxToInt(x)));
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcID$sp();
   }

   public Function1 copy$default$2$mcID$sp() {
      return this.func();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final Rand $anonfun$flatMap$4(final FlatMappedRand$mcID$sp $this, final Function1 f$6, final int x) {
      return (Rand)f$6.apply(BoxesRunTime.boxToDouble(((Rand)$this.func().apply(BoxesRunTime.boxToInteger(x))).draw$mcD$sp()));
   }

   public FlatMappedRand$mcID$sp(final Rand rand$mcI$sp, final Function1 func$mcID$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcI$sp = rand$mcI$sp;
      this.func$mcID$sp = func$mcID$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
