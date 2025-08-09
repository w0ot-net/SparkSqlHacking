package spire.algebra;

import algebra.ring.CommutativeRing;
import algebra.ring.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\t\u0013!\u0003\r\ta\u0006\u0005\u0006k\u0001!\tA\u000e\u0005\u0006u\u00011\u0019a\u000f\u0005\u0006y\u00011\u0019!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u0007\u0002!\tA\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u0002!\tE\u0014\u0005\u0006#\u0002!\tA\u0015\u0005\u0006+\u0002!\tA\u0016\u0005\u00065\u0002!\teW\u0004\u0006=JA\ta\u0018\u0004\u0006#IA\t\u0001\u0019\u0005\u0006Y6!\t!\u001c\u0005\u0006]6!\ta\u001c\u0005\bu6\t\t\u0011\"\u0003|\u0005!Q\u0016\t\\4fEJ\f'BA\n\u0015\u0003\u001d\tGnZ3ce\u0006T\u0011!F\u0001\u0006gBL'/Z\u0002\u0001+\tARe\u0005\u0003\u00013}q\u0003C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"aA!osB!\u0001%I\u0012,\u001b\u0005\u0011\u0012B\u0001\u0012\u0013\u0005Y\u0011\u0016N\\4BgN|7-[1uSZ,\u0017\t\\4fEJ\f\u0007C\u0001\u0013&\u0019\u0001!QA\n\u0001C\u0002\u001d\u0012\u0011AV\t\u0003Qe\u0001\"AG\u0015\n\u0005)Z\"a\u0002(pi\"Lgn\u001a\t\u000351J!!L\u000e\u0003\u0007%sG\u000fE\u00020e\rr!\u0001\t\u0019\n\u0005E\u0012\u0012a\u00029bG.\fw-Z\u0005\u0003gQ\u0012AAU5oO*\u0011\u0011GE\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003]\u0002\"A\u0007\u001d\n\u0005eZ\"\u0001B+oSR\faA^3di>\u0014X#\u0001\u0018\u0002\rM\u001c\u0017\r\\1s+\u0005q\u0004cA\u0018@W%\u0011\u0001\t\u000e\u0002\u0006\u0007JKgnZ\u0001\u0005u\u0016\u0014x.F\u0001$\u0003\ryg.Z\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005\r2\u0005\"B$\u0007\u0001\u0004\u0019\u0013!\u0001<\u0002\tAdWo\u001d\u000b\u0004G)[\u0005\"B$\b\u0001\u0004\u0019\u0003\"\u0002'\b\u0001\u0004\u0019\u0013!A<\u0002\u000b5Lg.^:\u0015\u0007\rz\u0005\u000bC\u0003H\u0011\u0001\u00071\u0005C\u0003M\u0011\u0001\u00071%A\u0003uS6,7\u000fF\u0002$'RCQaR\u0005A\u0002\rBQ\u0001T\u0005A\u0002\r\na\u0001^5nKNdGcA\u0012X3\")\u0001L\u0003a\u0001W\u0005\t!\u000fC\u0003H\u0015\u0001\u00071%A\u0004ge>l\u0017J\u001c;\u0015\u0005\rb\u0006\"B/\f\u0001\u0004Y\u0013!\u00018\u0002\u0011i\u000bEnZ3ce\u0006\u0004\"\u0001I\u0007\u0014\u00075\tG\r\u0005\u0002\u001bE&\u00111m\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017AA5p\u0015\u0005I\u0017\u0001\u00026bm\u0006L!a\u001b4\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005y\u0016!B1qa2LXC\u00019t)\r\tX\u000f\u001f\t\u0004A\u0001\u0011\bC\u0001\u0013t\t\u0015!xB1\u0001(\u0005\u0005\t\u0005\"\u0002<\u0010\u0001\b9\u0018a\u0002<fGR|'\u000f\r\t\u0004_I\u0012\b\"B=\u0010\u0001\bq\u0014aB:dC2\f'\u000fM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002yB\u0019Q0!\u0001\u000e\u0003yT!a 5\u0002\t1\fgnZ\u0005\u0004\u0003\u0007q(AB(cU\u0016\u001cG\u000f"
)
public interface ZAlgebra extends RingAssociativeAlgebra$mcI$sp {
   static ZAlgebra apply(final Ring vector0, final CommutativeRing scalar0) {
      return ZAlgebra$.MODULE$.apply(vector0, scalar0);
   }

   Ring vector();

   CommutativeRing scalar();

   // $FF: synthetic method
   static Object zero$(final ZAlgebra $this) {
      return $this.zero();
   }

   default Object zero() {
      return this.vector().zero();
   }

   // $FF: synthetic method
   static Object one$(final ZAlgebra $this) {
      return $this.one();
   }

   default Object one() {
      return this.vector().one();
   }

   // $FF: synthetic method
   static Object negate$(final ZAlgebra $this, final Object v) {
      return $this.negate(v);
   }

   default Object negate(final Object v) {
      return this.vector().negate(v);
   }

   // $FF: synthetic method
   static Object plus$(final ZAlgebra $this, final Object v, final Object w) {
      return $this.plus(v, w);
   }

   default Object plus(final Object v, final Object w) {
      return this.vector().plus(v, w);
   }

   // $FF: synthetic method
   static Object minus$(final ZAlgebra $this, final Object v, final Object w) {
      return $this.minus(v, w);
   }

   default Object minus(final Object v, final Object w) {
      return this.vector().minus(v, w);
   }

   // $FF: synthetic method
   static Object times$(final ZAlgebra $this, final Object v, final Object w) {
      return $this.times(v, w);
   }

   default Object times(final Object v, final Object w) {
      return this.vector().times(v, w);
   }

   // $FF: synthetic method
   static Object timesl$(final ZAlgebra $this, final int r, final Object v) {
      return $this.timesl(r, v);
   }

   default Object timesl(final int r, final Object v) {
      return this.timesl$mcI$sp(r, v);
   }

   // $FF: synthetic method
   static Object fromInt$(final ZAlgebra $this, final int n) {
      return $this.fromInt(n);
   }

   default Object fromInt(final int n) {
      return this.vector().fromInt(n);
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcI$sp$(final ZAlgebra $this) {
      return $this.scalar$mcI$sp();
   }

   default CommutativeRing scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Object timesl$mcI$sp$(final ZAlgebra $this, final int r, final Object v) {
      return $this.timesl$mcI$sp(r, v);
   }

   default Object timesl$mcI$sp(final int r, final Object v) {
      return this.vector().times(this.vector().fromInt(r), v);
   }

   static void $init$(final ZAlgebra $this) {
   }
}
