package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class FlatMappedRand$mcII$sp extends FlatMappedRand implements Rand$mcI$sp {
   public final Rand rand$mcI$sp;
   public final Function1 func$mcII$sp;

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

   public Rand map(final Function1 f) {
      return Rand$mcI$sp.map$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand$mcI$sp.map$mcI$sp$(this, f);
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

   public Rand rand$mcI$sp() {
      return this.rand$mcI$sp;
   }

   public Rand rand() {
      return this.rand$mcI$sp();
   }

   public Function1 func$mcII$sp() {
      return this.func$mcII$sp;
   }

   public Function1 func() {
      return this.func$mcII$sp();
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public int draw$mcI$sp() {
      return ((Rand)this.func().apply(BoxesRunTime.boxToInteger(this.rand().draw$mcI$sp()))).draw$mcI$sp();
   }

   public Rand flatMap(final Function1 f) {
      return this.flatMap$mcI$sp(f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return new FlatMappedRand(this.rand(), (x) -> $anonfun$flatMap$5(this, f, BoxesRunTime.unboxToInt(x)));
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcII$sp();
   }

   public Function1 copy$default$2$mcII$sp() {
      return this.func();
   }

   public boolean specInstance$() {
      return true;
   }

   // $FF: synthetic method
   public static final Rand $anonfun$flatMap$5(final FlatMappedRand$mcII$sp $this, final Function1 f$7, final int x) {
      return (Rand)f$7.apply(BoxesRunTime.boxToInteger(((Rand)$this.func().apply(BoxesRunTime.boxToInteger(x))).draw$mcI$sp()));
   }

   public FlatMappedRand$mcII$sp(final Rand rand$mcI$sp, final Function1 func$mcII$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcI$sp = rand$mcI$sp;
      this.func$mcII$sp = func$mcII$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
