package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class MappedRand$mcDI$sp extends MappedRand implements Rand$mcI$sp {
   public final Rand rand$mcD$sp;
   public final Function1 func$mcDI$sp;

   public int get() {
      return Rand$mcI$sp.get$(this);
   }

   public int get$mcI$sp() {
      return Rand$mcI$sp.get$mcI$sp$(this);
   }

   public int sample() {
      return Rand$mcI$sp.sample$(this);
   }

   public int sample$mcI$sp() {
      return Rand$mcI$sp.sample$mcI$sp$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand$mcI$sp.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand$mcI$sp.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand$mcI$sp.flatMap$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand$mcI$sp.flatMap$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand$mcI$sp.foreach$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand$mcI$sp.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand$mcI$sp.filter$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand$mcI$sp.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand$mcI$sp.withFilter$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand$mcI$sp.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand$mcI$sp.condition$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand$mcI$sp.condition$mcI$sp$(this, p);
   }

   public Rand rand$mcD$sp() {
      return this.rand$mcD$sp;
   }

   public Rand rand() {
      return this.rand$mcD$sp();
   }

   public Function1 func$mcDI$sp() {
      return this.func$mcDI$sp;
   }

   public Function1 func() {
      return this.func$mcDI$sp();
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public int draw$mcI$sp() {
      return this.func().apply$mcID$sp(this.rand().draw$mcD$sp());
   }

   public Rand map(final Function1 f) {
      return this.map$mcI$sp(f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return new MappedRand(this.rand(), (x) -> $anonfun$map$3(this, f, BoxesRunTime.unboxToDouble(x)));
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Rand copy$default$1$mcD$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcDI$sp();
   }

   public Function1 copy$default$2$mcDI$sp() {
      return this.func();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final Object $anonfun$map$3(final MappedRand$mcDI$sp $this, final Function1 f$9, final double x) {
      return f$9.apply(BoxesRunTime.boxToInteger($this.func().apply$mcID$sp(x)));
   }

   public MappedRand$mcDI$sp(final Rand rand$mcD$sp, final Function1 func$mcDI$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcD$sp = rand$mcD$sp;
      this.func$mcDI$sp = func$mcDI$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
