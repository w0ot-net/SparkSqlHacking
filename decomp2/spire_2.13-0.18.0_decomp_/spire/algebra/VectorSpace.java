package spire.algebra;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006#\u0002!\tAU\u0004\u0006/*A\t\u0001\u0017\u0004\u0006\u0013)A\t!\u0017\u0005\u0006K\u0016!\tA\u001a\u0005\u0006O\u0016!)\u0001\u001b\u0005\t\u007f\u0016\t\t\u0011\"\u0003\u0002\u0002\tYa+Z2u_J\u001c\u0006/Y2f\u0015\tYA\"A\u0004bY\u001e,'M]1\u000b\u00035\tQa\u001d9je\u0016\u001c\u0001!F\u0002\u0011;\u0011\u001a2\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\r\te.\u001f\t\u00051eY2%D\u0001\u000b\u0013\tQ\"BA\u0004D\u001b>$W\u000f\\3\u0011\u0005qiB\u0002\u0001\u0003\u0006=\u0001\u0011\ra\b\u0002\u0002-F\u0011\u0001%\u0005\t\u0003%\u0005J!AI\n\u0003\u000f9{G\u000f[5oOB\u0011A\u0004\n\u0003\nK\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011A\u0012\u0015\u0007I\u001dRC'\u000f \u0011\u0005IA\u0013BA\u0015\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rZCFL\u0017\u000f\u0005Ia\u0013BA\u0017\u0014\u0003\rIe\u000e^\u0019\u0005I=\u001aDC\u0004\u00021g5\t\u0011G\u0003\u00023\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GU2\u0004h\u000e\b\u0003%YJ!aN\n\u0002\t1{gnZ\u0019\u0005I=\u001aD#M\u0003$umjDH\u0004\u0002\u0013w%\u0011AhE\u0001\u0006\r2|\u0017\r^\u0019\u0005I=\u001aD#M\u0003$\u007f\u0001\u0013\u0015I\u0004\u0002\u0013\u0001&\u0011\u0011iE\u0001\u0007\t>,(\r\\32\t\u0011z3\u0007F\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0015\u0003\"A\u0005$\n\u0005\u001d\u001b\"\u0001B+oSR\faa]2bY\u0006\u0014X#\u0001&\u0011\u0007-s5E\u0004\u0002\u0019\u0019&\u0011QJC\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005KA\u0003GS\u0016dGM\u0003\u0002N\u0015\u0005!A-\u001b<s)\rY2+\u0016\u0005\u0006)\u000e\u0001\raG\u0001\u0002m\")ak\u0001a\u0001G\u0005\ta-A\u0006WK\u000e$xN]*qC\u000e,\u0007C\u0001\r\u0006'\r)!,\u0018\t\u0003%mK!\u0001X\n\u0003\r\u0005s\u0017PU3g!\tq6-D\u0001`\u0015\t\u0001\u0017-\u0001\u0002j_*\t!-\u0001\u0003kCZ\f\u0017B\u00013`\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0001,A\u0003baBd\u00170F\u0002jY:$\"A[=\u0011\ta\u00011.\u001c\t\u000391$QAH\u0004C\u0002}\u0001\"\u0001\b8\u0005\u0013=<\u0001\u0015!A\u0001\u0006\u0004y\"!\u0001*)\r9<\u0013o];xc\u0015\u00193\u0006\f:.c\u0011!sf\r\u000b2\u000b\r*d\u0007^\u001c2\t\u0011z3\u0007F\u0019\u0006GiZd\u000fP\u0019\u0005I=\u001aD#M\u0003$\u007f\u0001C\u0018)\r\u0003%_M\"\u0002\"\u0002>\b\u0001\bQ\u0017!\u0001,)\u0005\u001da\bC\u0001\n~\u0013\tq8C\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u0001B!!\u0002\u0002\f5\u0011\u0011q\u0001\u0006\u0004\u0003\u0013\t\u0017\u0001\u00027b]\u001eLA!!\u0004\u0002\b\t1qJ\u00196fGR\u0004"
)
public interface VectorSpace extends CModule {
   static VectorSpace apply(final VectorSpace V) {
      return VectorSpace$.MODULE$.apply(V);
   }

   Field scalar();

   // $FF: synthetic method
   static Object divr$(final VectorSpace $this, final Object v, final Object f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final Object f) {
      return this.timesl(this.scalar().reciprocal(f), v);
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final VectorSpace $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcF$sp$(final VectorSpace $this) {
      return $this.scalar$mcF$sp();
   }

   default Field scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcI$sp$(final VectorSpace $this) {
      return $this.scalar$mcI$sp();
   }

   default Field scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcJ$sp$(final VectorSpace $this) {
      return $this.scalar$mcJ$sp();
   }

   default Field scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Object divr$mcD$sp$(final VectorSpace $this, final Object v, final double f) {
      return $this.divr$mcD$sp(v, f);
   }

   default Object divr$mcD$sp(final Object v, final double f) {
      return this.divr(v, BoxesRunTime.boxToDouble(f));
   }

   // $FF: synthetic method
   static Object divr$mcF$sp$(final VectorSpace $this, final Object v, final float f) {
      return $this.divr$mcF$sp(v, f);
   }

   default Object divr$mcF$sp(final Object v, final float f) {
      return this.divr(v, BoxesRunTime.boxToFloat(f));
   }

   // $FF: synthetic method
   static Object divr$mcI$sp$(final VectorSpace $this, final Object v, final int f) {
      return $this.divr$mcI$sp(v, f);
   }

   default Object divr$mcI$sp(final Object v, final int f) {
      return this.divr(v, BoxesRunTime.boxToInteger(f));
   }

   // $FF: synthetic method
   static Object divr$mcJ$sp$(final VectorSpace $this, final Object v, final long f) {
      return $this.divr$mcJ$sp(v, f);
   }

   default Object divr$mcJ$sp(final Object v, final long f) {
      return this.divr(v, BoxesRunTime.boxToLong(f));
   }

   static void $init$(final VectorSpace $this) {
   }
}
