package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aa\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u0006]\u0001!\ta\f\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006E\u0002!\ta\u0019\u0005\u0006c\u0002!\tA\u001d\u0002\u0011\u000f\u0016t'i\\8m\rVt7\r^5p]NT!\u0001C\u0005\u0002\u000f1\fG\u000f^5dK*\t!\"A\u0004bY\u001e,'M]1\u0004\u0001U\u0011QBG\n\u0005\u00019!2\u0006\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0004+YAR\"A\u0004\n\u0005]9!a\b\"pk:$W\r\u001a&pS:\u001cV-\\5mCR$\u0018nY3Gk:\u001cG/[8ogB\u0011\u0011D\u0007\u0007\u0001\t\u0015Y\u0002A1\u0001\u001d\u0005\u00059UCA\u000f&#\tq\u0012\u0005\u0005\u0002\u0010?%\u0011\u0001\u0005\u0005\u0002\b\u001d>$\b.\u001b8h!\r)\"\u0005J\u0005\u0003G\u001d\u0011qaR3o\u0005>|G\u000e\u0005\u0002\u001aK\u0011)aE\u0007b\u0001O\t\t\u0011)\u0005\u0002\u001fQA\u0011q\"K\u0005\u0003UA\u00111!\u00118z!\r)B\u0006G\u0005\u0003[\u001d\u0011\u0001$T3fiN+W.\u001b7biRL7-\u001a$v]\u000e$\u0018n\u001c8t\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0007\u0005\u0002\u0010c%\u0011!\u0007\u0005\u0002\u0005+:LG/A\u0002b]\u0012,\"!\u000e\u001d\u0015\u0007Yz\u0015\u000b\u0006\u00028\u0019B\u0011\u0011\u0004\u000f\u0003\nM\t\u0001\u000b\u0011!AC\u0002\u001dBC\u0001\u000f\u001e>\u000fB\u0011qbO\u0005\u0003yA\u00111b\u001d9fG&\fG.\u001b>fIF*1EP B\u0001:\u0011qbP\u0005\u0003\u0001B\t1!\u00138uc\u0011!#IR\t\u000f\u0005\r3U\"\u0001#\u000b\u0005\u0015[\u0011A\u0002\u001fs_>$h(C\u0001\u0012c\u0015\u0019\u0003*S&K\u001d\ty\u0011*\u0003\u0002K!\u0005!Aj\u001c8hc\u0011!#IR\t\t\u000b5\u0013\u00019\u0001(\u0002\u0005\u00154\bcA\r\u001bo!)\u0001K\u0001a\u0001o\u0005\t\u0001\u0010C\u0003S\u0005\u0001\u0007q'A\u0001z\u0003\ty'/\u0006\u0002V1R\u0019a\u000bY1\u0015\u0005]s\u0006CA\rY\t%13\u0001)A\u0001\u0002\u000b\u0007q\u0005\u000b\u0003Yuic\u0016'B\u0012?\u007fm\u0003\u0015\u0007\u0002\u0013C\rF\tTa\t%J;*\u000bD\u0001\n\"G#!)Qj\u0001a\u0002?B\u0019\u0011DG,\t\u000bA\u001b\u0001\u0019A,\t\u000bI\u001b\u0001\u0019A,\u0002\u000f]LG\u000f[8viV\u0011Am\u001a\u000b\u0004K>\u0004HC\u00014n!\tIr\rB\u0005'\t\u0001\u0006\t\u0011!b\u0001O!\"qMO5lc\u0015\u0019ch\u00106Ac\u0011!#IR\t2\u000b\rB\u0015\n\u001c&2\t\u0011\u0012e)\u0005\u0005\u0006\u001b\u0012\u0001\u001dA\u001c\t\u00043i1\u0007\"\u0002)\u0005\u0001\u00041\u0007\"\u0002*\u0005\u0001\u00041\u0017a\u0001=peV\u00111O\u001e\u000b\u0004iz|HCA;}!\tIb\u000fB\u0005'\u000b\u0001\u0006\t\u0011!b\u0001O!\"aO\u000f={c\u0015\u0019chP=Ac\u0011!#IR\t2\u000b\rB\u0015j\u001f&2\t\u0011\u0012e)\u0005\u0005\u0006\u001b\u0016\u0001\u001d! \t\u00043i)\b\"\u0002)\u0006\u0001\u0004)\b\"\u0002*\u0006\u0001\u0004)\b"
)
public interface GenBoolFunctions extends BoundedJoinSemilatticeFunctions, MeetSemilatticeFunctions {
   // $FF: synthetic method
   static Object and$(final GenBoolFunctions $this, final Object x, final Object y, final GenBool ev) {
      return $this.and(x, y, ev);
   }

   default Object and(final Object x, final Object y, final GenBool ev) {
      return ev.and(x, y);
   }

   // $FF: synthetic method
   static Object or$(final GenBoolFunctions $this, final Object x, final Object y, final GenBool ev) {
      return $this.or(x, y, ev);
   }

   default Object or(final Object x, final Object y, final GenBool ev) {
      return ev.or(x, y);
   }

   // $FF: synthetic method
   static Object without$(final GenBoolFunctions $this, final Object x, final Object y, final GenBool ev) {
      return $this.without(x, y, ev);
   }

   default Object without(final Object x, final Object y, final GenBool ev) {
      return ev.without(x, y);
   }

   // $FF: synthetic method
   static Object xor$(final GenBoolFunctions $this, final Object x, final Object y, final GenBool ev) {
      return $this.xor(x, y, ev);
   }

   default Object xor(final Object x, final Object y, final GenBool ev) {
      return ev.xor(x, y);
   }

   // $FF: synthetic method
   static int and$mIc$sp$(final GenBoolFunctions $this, final int x, final int y, final GenBool ev) {
      return $this.and$mIc$sp(x, y, ev);
   }

   default int and$mIc$sp(final int x, final int y, final GenBool ev) {
      return ev.and$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long and$mJc$sp$(final GenBoolFunctions $this, final long x, final long y, final GenBool ev) {
      return $this.and$mJc$sp(x, y, ev);
   }

   default long and$mJc$sp(final long x, final long y, final GenBool ev) {
      return ev.and$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int or$mIc$sp$(final GenBoolFunctions $this, final int x, final int y, final GenBool ev) {
      return $this.or$mIc$sp(x, y, ev);
   }

   default int or$mIc$sp(final int x, final int y, final GenBool ev) {
      return ev.or$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long or$mJc$sp$(final GenBoolFunctions $this, final long x, final long y, final GenBool ev) {
      return $this.or$mJc$sp(x, y, ev);
   }

   default long or$mJc$sp(final long x, final long y, final GenBool ev) {
      return ev.or$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int without$mIc$sp$(final GenBoolFunctions $this, final int x, final int y, final GenBool ev) {
      return $this.without$mIc$sp(x, y, ev);
   }

   default int without$mIc$sp(final int x, final int y, final GenBool ev) {
      return ev.without$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long without$mJc$sp$(final GenBoolFunctions $this, final long x, final long y, final GenBool ev) {
      return $this.without$mJc$sp(x, y, ev);
   }

   default long without$mJc$sp(final long x, final long y, final GenBool ev) {
      return ev.without$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int xor$mIc$sp$(final GenBoolFunctions $this, final int x, final int y, final GenBool ev) {
      return $this.xor$mIc$sp(x, y, ev);
   }

   default int xor$mIc$sp(final int x, final int y, final GenBool ev) {
      return ev.xor$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long xor$mJc$sp$(final GenBoolFunctions $this, final long x, final long y, final GenBool ev) {
      return $this.xor$mJc$sp(x, y, ev);
   }

   default long xor$mJc$sp(final long x, final long y, final GenBool ev) {
      return ev.xor$mcJ$sp(x, y);
   }

   static void $init$(final GenBoolFunctions $this) {
   }
}
