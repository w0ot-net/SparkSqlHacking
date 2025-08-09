package algebra.lattice;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EaaB\u0006\r!\u0003\r\t!\u0005\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u000f\u0002!\t\u0001S\u0004\u0006\u00172A\t\u0001\u0014\u0004\u0006\u00171A\t!\u0014\u0005\u0006;\u001a!\tA\u0018\u0005\u0006?\u001a!)\u0001\u0019\u0005\u0006a\u001a!)!\u001d\u0005\n\u0003\u00031\u0011\u0011!C\u0005\u0003\u0007\u0011\u0001\u0002R3N_J<\u0017M\u001c\u0006\u0003\u001b9\tq\u0001\\1ui&\u001cWMC\u0001\u0010\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0013?M\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\u0007\u0005s\u0017\u0010E\u0002\u001b7ui\u0011\u0001D\u0005\u000391\u0011Q\u0001T8hS\u000e\u0004\"AH\u0010\r\u0001\u0011I\u0001\u0005\u0001Q\u0001\u0002\u0003\u0015\r!\t\u0002\u0002\u0003F\u0011!e\u0005\t\u0003)\rJ!\u0001J\u000b\u0003\u000f9{G\u000f[5oO\"\"qDJ\u00154!\t!r%\u0003\u0002)+\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#fK\u0017-\u001d\t!2&\u0003\u0002-+\u0005\u0019\u0011J\u001c;2\t\u0011r#G\u0006\b\u0003_Ij\u0011\u0001\r\u0006\u0003cA\ta\u0001\u0010:p_Rt\u0014\"\u0001\f2\u000b\r\"Tg\u000e\u001c\u000f\u0005Q)\u0014B\u0001\u001c\u0016\u0003\u0011auN\\42\t\u0011r#GF\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0002\"\u0001F\u001e\n\u0005q*\"\u0001B+oSR\fA!\\3fiR\u0019QdP!\t\u000b\u0001\u0013\u0001\u0019A\u000f\u0002\u0003\u0005DQA\u0011\u0002A\u0002u\t\u0011AY\u0001\u0005U>Lg\u000eF\u0002\u001e\u000b\u001aCQ\u0001Q\u0002A\u0002uAQAQ\u0002A\u0002u\t1![7q)\ri\u0012J\u0013\u0005\u0006\u0001\u0012\u0001\r!\b\u0005\u0006\u0005\u0012\u0001\r!H\u0001\t\t\u0016luN]4b]B\u0011!DB\n\u0005\r9\u000bV\u000b\u0005\u0002\u0015\u001f&\u0011\u0001+\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007i\u0011F+\u0003\u0002T\u0019\t\tB)Z'pe\u001e\fgNR;oGRLwN\\:\u0011\u0005i\u0001\u0001C\u0001,\\\u001b\u00059&B\u0001-Z\u0003\tIwNC\u0001[\u0003\u0011Q\u0017M^1\n\u0005q;&\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001M\u0003\u0015\t\u0007\u000f\u001d7z+\t\tG\r\u0006\u0002cUB\u0019!\u0004A2\u0011\u0005y!G!\u0003\u0011\tA\u0003\u0005\tQ1\u0001\"Q\u0011!gE\u001a52\u000b\rR3f\u001a\u00172\t\u0011r#GF\u0019\u0006GQ*\u0014NN\u0019\u0005I9\u0012d\u0003C\u0003l\u0011\u0001\u000f!-\u0001\u0002fm\"\u0012\u0001\"\u001c\t\u0003)9L!a\\\u000b\u0003\r%tG.\u001b8f\u0003!1'o\\7C_>dWC\u0001:v)\t\u00198\u0010E\u0002\u001b\u0001Q\u0004\"AH;\u0005\u0013\u0001J\u0001\u0015!A\u0001\u0006\u0004\t\u0003\u0006B;'of\fTa\t\u0016,q2\nD\u0001\n\u00183-E*1\u0005N\u001b{mE\"AE\f\u001a\u0017\u0011\u0015a\u0018\u00021\u0001~\u0003\u0011\u0011wn\u001c7\u0011\u0007iqH/\u0003\u0002\u0000\u0019\t!!i\\8m\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0001\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tY!W\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0010\u0005%!AB(cU\u0016\u001cG\u000f"
)
public interface DeMorgan extends Logic {
   static DeMorgan fromBool(final Bool bool) {
      return DeMorgan$.MODULE$.fromBool(bool);
   }

   static DeMorgan apply(final DeMorgan ev) {
      return DeMorgan$.MODULE$.apply(ev);
   }

   static Object complement(final Object x, final Logic ev) {
      return DeMorgan$.MODULE$.complement(x, ev);
   }

   // $FF: synthetic method
   static Object meet$(final DeMorgan $this, final Object a, final Object b) {
      return $this.meet(a, b);
   }

   default Object meet(final Object a, final Object b) {
      return this.and(a, b);
   }

   // $FF: synthetic method
   static Object join$(final DeMorgan $this, final Object a, final Object b) {
      return $this.join(a, b);
   }

   default Object join(final Object a, final Object b) {
      return this.or(a, b);
   }

   // $FF: synthetic method
   static Object imp$(final DeMorgan $this, final Object a, final Object b) {
      return $this.imp(a, b);
   }

   default Object imp(final Object a, final Object b) {
      return this.or(this.not(a), b);
   }

   // $FF: synthetic method
   static int meet$mcI$sp$(final DeMorgan $this, final int a, final int b) {
      return $this.meet$mcI$sp(a, b);
   }

   default int meet$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.meet(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long meet$mcJ$sp$(final DeMorgan $this, final long a, final long b) {
      return $this.meet$mcJ$sp(a, b);
   }

   default long meet$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.meet(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static int join$mcI$sp$(final DeMorgan $this, final int a, final int b) {
      return $this.join$mcI$sp(a, b);
   }

   default int join$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.join(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long join$mcJ$sp$(final DeMorgan $this, final long a, final long b) {
      return $this.join$mcJ$sp(a, b);
   }

   default long join$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.join(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static int imp$mcI$sp$(final DeMorgan $this, final int a, final int b) {
      return $this.imp$mcI$sp(a, b);
   }

   default int imp$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.imp(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long imp$mcJ$sp$(final DeMorgan $this, final long a, final long b) {
      return $this.imp$mcJ$sp(a, b);
   }

   default long imp$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.imp(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   static void $init$(final DeMorgan $this) {
   }
}
