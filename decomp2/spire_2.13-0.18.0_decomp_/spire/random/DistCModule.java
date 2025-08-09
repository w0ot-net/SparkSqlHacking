package spire.random;

import algebra.ring.CommutativeRing;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;

@ScalaSignature(
   bytes = "\u0006\u0005\t4qAC\u0006\u0011\u0002\u0007\u0005\u0001\u0003C\u00032\u0001\u0011\u0005!\u0007C\u00037\u0001\u0019\rq\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003J\u0001\u0011\u0005!\nC\u0003L\u0001\u0011\u0005A\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003U\u0001\u0011\u0005S\u000bC\u0003Y\u0001\u0011\u0005\u0011\fC\u0003_\u0001\u0011\u0005sLA\u0006ESN$8)T8ek2,'B\u0001\u0007\u000e\u0003\u0019\u0011\u0018M\u001c3p[*\ta\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0007E!sfE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007\u0003B\r\u001d=5j\u0011A\u0007\u0006\u000375\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001e5\t91)T8ek2,\u0007cA\u0010!E5\t1\"\u0003\u0002\"\u0017\t!A)[:u!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0002!\u0019\u0001\u0014\u0003\u0003Y\u000b\"a\n\u0016\u0011\u0005MA\u0013BA\u0015\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aE\u0016\n\u00051\"\"aA!osB\u0019q\u0004\t\u0018\u0011\u0005\rzC!\u0002\u0019\u0001\u0005\u00041#!A&\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0004CA\n5\u0013\t)DC\u0001\u0003V]&$\u0018aA1mOV\t\u0001\b\u0005\u0003\u001a9\tr\u0013AB:dC2\f'/F\u0001<!\rad)\f\b\u0003{\u0011s!AP\"\u000f\u0005}\u0012U\"\u0001!\u000b\u0005\u0005{\u0011A\u0002\u001fs_>$h(C\u0001\u000f\u0013\tYR\"\u0003\u0002F5\u00059\u0001/Y2lC\u001e,\u0017BA$I\u0005\u0015\u0019%+\u001b8h\u0015\t)%$\u0001\u0003{KJ|W#\u0001\u0010\u0002\tAdWo\u001d\u000b\u0004=5{\u0005\"\u0002(\u0006\u0001\u0004q\u0012!\u0001=\t\u000bA+\u0001\u0019\u0001\u0010\u0002\u0003e\faA\\3hCR,GC\u0001\u0010T\u0011\u0015qe\u00011\u0001\u001f\u0003\u0015i\u0017N\\;t)\rqbk\u0016\u0005\u0006\u001d\u001e\u0001\rA\b\u0005\u0006!\u001e\u0001\rAH\u0001\u0007i&lWm\u001d7\u0015\u0007yQF\fC\u0003\\\u0011\u0001\u0007Q&A\u0001l\u0011\u0015i\u0006\u00021\u0001\u001f\u0003\u00051\u0018A\u0002;j[\u0016\u001c(\u000fF\u0002\u001fA\u0006DQ!X\u0005A\u0002yAQaW\u0005A\u00025\u0002"
)
public interface DistCModule extends CModule {
   CModule alg();

   // $FF: synthetic method
   static CommutativeRing scalar$(final DistCModule $this) {
      return $this.scalar();
   }

   default CommutativeRing scalar() {
      return Dist$.MODULE$.cRing(this.alg().scalar());
   }

   // $FF: synthetic method
   static Dist zero$(final DistCModule $this) {
      return $this.zero();
   }

   default Dist zero() {
      return Dist$.MODULE$.constant(this.alg().zero());
   }

   // $FF: synthetic method
   static Dist plus$(final DistCModule $this, final Dist x, final Dist y) {
      return $this.plus(x, y);
   }

   default Dist plus(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().plus(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist negate$(final DistCModule $this, final Dist x) {
      return $this.negate(x);
   }

   default Dist negate(final Dist x) {
      return new DistFromGen((g) -> this.alg().negate(x.apply(g)));
   }

   // $FF: synthetic method
   static Dist minus$(final DistCModule $this, final Dist x, final Dist y) {
      return $this.minus(x, y);
   }

   default Dist minus(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().minus(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist timesl$(final DistCModule $this, final Dist k, final Dist v) {
      return $this.timesl(k, v);
   }

   default Dist timesl(final Dist k, final Dist v) {
      return new DistFromGen((g) -> {
         Object var4 = k.apply(g);
         return this.alg().timesl(var4, v.apply(g));
      });
   }

   // $FF: synthetic method
   static Dist timesr$(final DistCModule $this, final Dist v, final Dist k) {
      return $this.timesr(v, k);
   }

   default Dist timesr(final Dist v, final Dist k) {
      return new DistFromGen((g) -> this.alg().timesr(v.apply(g), k.apply(g)));
   }

   static void $init$(final DistCModule $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
