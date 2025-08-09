package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194\u0001BD\b\u0011\u0002\u0007\u0005qb\u0005\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\u0006m\u0001!\t!\u000e\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00061\u0002!\t!\u0017\u0005\u00069\u0002!\t%\u0018\u0005\u0006A\u0002!\t%\u0019\u0002\u0011\u00032<WM\u0019:bS\u000eL5OR5fY\u0012T!\u0001E\t\u0002\t5\fG\u000f\u001b\u0006\u0002%\u0005)1\u000f]5sKN\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\rY\u0002f\u000b\b\u00039\u0015r!!H\u0012\u000f\u0005y\u0011S\"A\u0010\u000b\u0005\u0001\n\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003II!\u0001J\t\u0002\u000f\u0005dw-\u001a2sC&\u0011aeJ\u0001\ba\u0006\u001c7.Y4f\u0015\t!\u0013#\u0003\u0002*U\t)a)[3mI*\u0011ae\n\t\u0003Y5j\u0011aD\u0005\u0003]=\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0004CA\u000b3\u0013\t\u0019dC\u0001\u0003V]&$\u0018\u0001\u0002>fe>,\u0012aK\u0001\u0004_:,\u0017\u0001\u00029mkN$2aK\u001d<\u0011\u0015QD\u00011\u0001,\u0003\u0005\t\u0007\"\u0002\u001f\u0005\u0001\u0004Y\u0013!\u00012\u0002\r9,w-\u0019;f)\tYs\bC\u0003;\u000b\u0001\u00071&A\u0003nS:,8\u000fF\u0002,\u0005\u000eCQA\u000f\u0004A\u0002-BQ\u0001\u0010\u0004A\u0002-\n1\u0001]8x)\rYci\u0012\u0005\u0006u\u001d\u0001\ra\u000b\u0005\u0006y\u001d\u0001\r\u0001\u0013\t\u0003+%K!A\u0013\f\u0003\u0007%sG/A\u0003uS6,7\u000fF\u0002,\u001b:CQA\u000f\u0005A\u0002-BQ\u0001\u0010\u0005A\u0002-\n1\u0001Z5w)\rY\u0013K\u0015\u0005\u0006u%\u0001\ra\u000b\u0005\u0006y%\u0001\raK\u0001\u0006]J|w\u000e\u001e\u000b\u0004WU3\u0006\"\u0002\u001e\u000b\u0001\u0004Y\u0003\"B,\u000b\u0001\u0004A\u0015!A6\u0002\t\u0019\u0004xn\u001e\u000b\u0004Wi[\u0006\"\u0002\u001e\f\u0001\u0004Y\u0003\"\u0002\u001f\f\u0001\u0004Y\u0013a\u00024s_6Le\u000e\u001e\u000b\u0003WyCQa\u0018\u0007A\u0002!\u000b\u0011A\\\u0001\u000bMJ|W\u000eR8vE2,GCA\u0016c\u0011\u0015yV\u00021\u0001d!\t)B-\u0003\u0002f-\t1Ai\\;cY\u0016\u0004"
)
public interface AlgebraicIsField extends Field {
   // $FF: synthetic method
   static Algebraic zero$(final AlgebraicIsField $this) {
      return $this.zero();
   }

   default Algebraic zero() {
      return Algebraic$.MODULE$.Zero();
   }

   // $FF: synthetic method
   static Algebraic one$(final AlgebraicIsField $this) {
      return $this.one();
   }

   default Algebraic one() {
      return Algebraic$.MODULE$.One();
   }

   // $FF: synthetic method
   static Algebraic plus$(final AlgebraicIsField $this, final Algebraic a, final Algebraic b) {
      return $this.plus(a, b);
   }

   default Algebraic plus(final Algebraic a, final Algebraic b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static Algebraic negate$(final AlgebraicIsField $this, final Algebraic a) {
      return $this.negate(a);
   }

   default Algebraic negate(final Algebraic a) {
      return a.unary_$minus();
   }

   // $FF: synthetic method
   static Algebraic minus$(final AlgebraicIsField $this, final Algebraic a, final Algebraic b) {
      return $this.minus(a, b);
   }

   default Algebraic minus(final Algebraic a, final Algebraic b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static Algebraic pow$(final AlgebraicIsField $this, final Algebraic a, final int b) {
      return $this.pow(a, b);
   }

   default Algebraic pow(final Algebraic a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static Algebraic times$(final AlgebraicIsField $this, final Algebraic a, final Algebraic b) {
      return $this.times(a, b);
   }

   default Algebraic times(final Algebraic a, final Algebraic b) {
      return a.$times(b);
   }

   // $FF: synthetic method
   static Algebraic div$(final AlgebraicIsField $this, final Algebraic a, final Algebraic b) {
      return $this.div(a, b);
   }

   default Algebraic div(final Algebraic a, final Algebraic b) {
      return a.$div(b);
   }

   // $FF: synthetic method
   static Algebraic nroot$(final AlgebraicIsField $this, final Algebraic a, final int k) {
      return $this.nroot(a, k);
   }

   default Algebraic nroot(final Algebraic a, final int k) {
      return a.nroot(k);
   }

   // $FF: synthetic method
   static Algebraic fpow$(final AlgebraicIsField $this, final Algebraic a, final Algebraic b) {
      return $this.fpow(a, b);
   }

   default Algebraic fpow(final Algebraic a, final Algebraic b) {
      throw new UnsupportedOperationException("unsupported operation");
   }

   // $FF: synthetic method
   static Algebraic fromInt$(final AlgebraicIsField $this, final int n) {
      return $this.fromInt(n);
   }

   default Algebraic fromInt(final int n) {
      return Algebraic$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Algebraic fromDouble$(final AlgebraicIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default Algebraic fromDouble(final double n) {
      return Algebraic$.MODULE$.apply(n);
   }

   static void $init$(final AlgebraicIsField $this) {
   }
}
