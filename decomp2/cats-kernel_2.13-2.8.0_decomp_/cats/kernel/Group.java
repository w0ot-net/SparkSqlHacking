package cats.kernel;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q4qAC\u0006\u0011\u0002\u0007\u0005\u0001\u0003C\u0003B\u0001\u0011\u0005!\tC\u0003G\u0001\u0019\u0005q\tC\u0003K\u0001\u0011\u00051\nC\u0003P\u0001\u0011\u0005\u0003kB\u0003X\u0017!\u0005\u0001LB\u0003\u000b\u0017!\u0005\u0011\fC\u0003g\r\u0011\u0005q\rC\u0003i\r\u0011\u0015\u0011\u000eC\u0004u\r\u0005\u0005I\u0011B;\u0003\u000b\u001d\u0013x.\u001e9\u000b\u00051i\u0011AB6fe:,GNC\u0001\u000f\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u0011\u0011CH\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"aA!osB\u0019\u0011D\u0007\u000f\u000e\u0003-I!aG\u0006\u0003\r5{gn\\5e!\tib\u0004\u0004\u0001\u0005\u0013}\u0001\u0001\u0015!A\u0001\u0006\u0004\u0001#!A!\u0012\u0005\u0005\u0012\u0002CA\n#\u0013\t\u0019CCA\u0004O_RD\u0017N\\4)\ry)\u0003FM\u001c=!\t\u0019b%\u0003\u0002()\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019\u0013F\u000b\u0017,\u001d\t\u0019\"&\u0003\u0002,)\u0005\u0019\u0011J\u001c;2\t\u0011j\u0013'\u0006\b\u0003]Ej\u0011a\f\u0006\u0003a=\ta\u0001\u0010:p_Rt\u0014\"A\u000b2\u000b\r\u001aDGN\u001b\u000f\u0005M!\u0014BA\u001b\u0015\u0003\u0011auN\\42\t\u0011j\u0013'F\u0019\u0006GaJ4H\u000f\b\u0003'eJ!A\u000f\u000b\u0002\u000b\u0019cw.\u0019;2\t\u0011j\u0013'F\u0019\u0006Gur\u0004i\u0010\b\u0003'yJ!a\u0010\u000b\u0002\r\u0011{WO\u00197fc\u0011!S&M\u000b\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0005CA\nE\u0013\t)EC\u0001\u0003V]&$\u0018aB5om\u0016\u00148/\u001a\u000b\u00039!CQ!\u0013\u0002A\u0002q\t\u0011!Y\u0001\u0007e\u0016lwN^3\u0015\u0007qaU\nC\u0003J\u0007\u0001\u0007A\u0004C\u0003O\u0007\u0001\u0007A$A\u0001c\u0003!\u0019w.\u001c2j]\u0016tEc\u0001\u000fR%\")\u0011\n\u0002a\u00019!)1\u000b\u0002a\u0001)\u0006\ta\u000e\u0005\u0002\u0014+&\u0011a\u000b\u0006\u0002\u0004\u0013:$\u0018!B$s_V\u0004\bCA\r\u0007'\r1!L\u0018\t\u00043mk\u0016B\u0001/\f\u000599%o\\;q\rVt7\r^5p]N\u0004\"!\u0007\u0001\u0011\u0005}#W\"\u00011\u000b\u0005\u0005\u0014\u0017AA5p\u0015\u0005\u0019\u0017\u0001\u00026bm\u0006L!!\u001a1\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005A\u0016!B1qa2LXC\u00016n)\tYg\u000eE\u0002\u001a\u00011\u0004\"!H7\u0005\u000b}A!\u0019\u0001\u0011\t\u000b=D\u00019A6\u0002\u0005\u00154\bF\u0001\u0005r!\t\u0019\"/\u0003\u0002t)\t1\u0011N\u001c7j]\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\u001e\t\u0003ojl\u0011\u0001\u001f\u0006\u0003s\n\fA\u0001\\1oO&\u00111\u0010\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Group extends Monoid {
   static Group apply(final Group ev) {
      return Group$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return Group$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return Group$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return Group$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return Group$.MODULE$.maybeCombine(ox, y, ev);
   }

   Object inverse(final Object a);

   // $FF: synthetic method
   static Object remove$(final Group $this, final Object a, final Object b) {
      return $this.remove(a, b);
   }

   default Object remove(final Object a, final Object b) {
      return this.combine(a, this.inverse(b));
   }

   // $FF: synthetic method
   static Object combineN$(final Group $this, final Object a, final int n) {
      return $this.combineN(a, n);
   }

   default Object combineN(final Object a, final int n) {
      return n > 0 ? this.repeatedCombineN(a, n) : (n == 0 ? this.empty() : (n == Integer.MIN_VALUE ? this.combineN(this.inverse(this.combine(a, a)), 1073741824) : this.repeatedCombineN(this.inverse(a), -n)));
   }

   // $FF: synthetic method
   static double inverse$mcD$sp$(final Group $this, final double a) {
      return $this.inverse$mcD$sp(a);
   }

   default double inverse$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.inverse(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float inverse$mcF$sp$(final Group $this, final float a) {
      return $this.inverse$mcF$sp(a);
   }

   default float inverse$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.inverse(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int inverse$mcI$sp$(final Group $this, final int a) {
      return $this.inverse$mcI$sp(a);
   }

   default int inverse$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.inverse(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long inverse$mcJ$sp$(final Group $this, final long a) {
      return $this.inverse$mcJ$sp(a);
   }

   default long inverse$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.inverse(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static double remove$mcD$sp$(final Group $this, final double a, final double b) {
      return $this.remove$mcD$sp(a, b);
   }

   default double remove$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.remove(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float remove$mcF$sp$(final Group $this, final float a, final float b) {
      return $this.remove$mcF$sp(a, b);
   }

   default float remove$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.remove(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int remove$mcI$sp$(final Group $this, final int a, final int b) {
      return $this.remove$mcI$sp(a, b);
   }

   default int remove$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.remove(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long remove$mcJ$sp$(final Group $this, final long a, final long b) {
      return $this.remove$mcJ$sp(a, b);
   }

   default long remove$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.remove(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static double combineN$mcD$sp$(final Group $this, final double a, final int n) {
      return $this.combineN$mcD$sp(a, n);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.combineN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float combineN$mcF$sp$(final Group $this, final float a, final int n) {
      return $this.combineN$mcF$sp(a, n);
   }

   default float combineN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.combineN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int combineN$mcI$sp$(final Group $this, final int a, final int n) {
      return $this.combineN$mcI$sp(a, n);
   }

   default int combineN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.combineN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long combineN$mcJ$sp$(final Group $this, final long a, final int n) {
      return $this.combineN$mcJ$sp(a, n);
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.combineN(BoxesRunTime.boxToLong(a), n));
   }

   static void $init$(final Group $this) {
   }
}
