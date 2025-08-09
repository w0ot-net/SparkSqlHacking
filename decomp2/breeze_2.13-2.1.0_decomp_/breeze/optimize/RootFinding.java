package breeze.optimize;

import scala.Function1;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Q!\u0004\b\t\u0002M1Q!\u0006\b\t\u0002YAQ!H\u0001\u0005\u0002yA\u0001bH\u0001\t\u0006\u0004%\t\u0001\t\u0005\tI\u0005A)\u0019!C\u0001K!)\u0011&\u0001C\u0001U!9q'AI\u0001\n\u0003A\u0004\"B\"\u0002\t\u0003!\u0005\"\u0002&\u0002\t\u0003Y\u0005b\u0002*\u0002#\u0003%\ta\u0015\u0005\u0006+\u0006!\tA\u0016\u0005\b7\u0006\t\n\u0011\"\u0001T\u0011\u0015a\u0016\u0001\"\u0001^\u0003-\u0011vn\u001c;GS:$\u0017N\\4\u000b\u0005=\u0001\u0012\u0001C8qi&l\u0017N_3\u000b\u0003E\taA\u0019:fKj,7\u0001\u0001\t\u0003)\u0005i\u0011A\u0004\u0002\f%>|GOR5oI&twm\u0005\u0002\u0002/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\n\u0002\u0007\u0015\u00048/F\u0001\"!\tA\"%\u0003\u0002$3\t1Ai\\;cY\u0016\fa\u0002Z3gCVdG/T1y\u0013R,'/F\u0001'!\tAr%\u0003\u0002)3\t\u0019\u0011J\u001c;\u0002\t\u0019Lg\u000e\u001a\u000b\u0005C-\u0002$\u0007C\u0003-\u000b\u0001\u0007Q&\u0001\u0002g]B!\u0001DL\u0011\"\u0013\ty\u0013DA\u0005Gk:\u001cG/[8oc!)\u0011'\u0002a\u0001C\u0005\u0011\u0001\u0010\r\u0005\bg\u0015\u0001\n\u00111\u00015\u0003\tA\u0018\u0007E\u0002\u0019k\u0005J!AN\r\u0003\r=\u0003H/[8o\u000391\u0017N\u001c3%I\u00164\u0017-\u001e7uIM*\u0012!\u000f\u0016\u0003iiZ\u0013a\u000f\t\u0003y\u0005k\u0011!\u0010\u0006\u0003}}\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0001K\u0012AC1o]>$\u0018\r^5p]&\u0011!)\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00032jg\u0016\u001cG/[8o)\u0011\tSI\u0012%\t\u000b1:\u0001\u0019A\u0017\t\u000b\u001d;\u0001\u0019A\u0011\u0002\u0003\u0005DQ!S\u0004A\u0002\u0005\n\u0011AY\u0001\u000e]\u0016<Ho\u001c8SCBD7o\u001c8\u0015\u000b\u0005bUj\u0014)\t\u000b1B\u0001\u0019A\u0017\t\u000b9C\u0001\u0019A\u0017\u0002\u0005\u0019$\u0007\"B\u0019\t\u0001\u0004\t\u0003bB)\t!\u0003\u0005\rAJ\u0001\b[\u0006D\u0018\n^3s\u0003]qWm\u001e;p]J\u000b\u0007\u000f[:p]\u0012\"WMZ1vYR$C'F\u0001UU\t1#(\u0001\u0004tK\u000e\fg\u000e\u001e\u000b\u0006C]C\u0016L\u0017\u0005\u0006Y)\u0001\r!\f\u0005\u0006c)\u0001\r!\t\u0005\u0006g)\u0001\r!\t\u0005\b#*\u0001\n\u00111\u0001'\u0003A\u0019XmY1oi\u0012\"WMZ1vYR$C'A\u0003ce\u0016tG\u000f\u0006\u0003\"=~\u0003\u0007\"\u0002\u0017\r\u0001\u0004i\u0003\"B$\r\u0001\u0004\t\u0003\"B%\r\u0001\u0004\t\u0003"
)
public final class RootFinding {
   public static double brent(final Function1 fn, final double a, final double b) {
      return RootFinding$.MODULE$.brent(fn, a, b);
   }

   public static int secant$default$4() {
      return RootFinding$.MODULE$.secant$default$4();
   }

   public static double secant(final Function1 fn, final double x0, final double x1, final int maxIter) {
      return RootFinding$.MODULE$.secant(fn, x0, x1, maxIter);
   }

   public static int newtonRaphson$default$4() {
      return RootFinding$.MODULE$.newtonRaphson$default$4();
   }

   public static double newtonRaphson(final Function1 fn, final Function1 fd, final double x0, final int maxIter) {
      return RootFinding$.MODULE$.newtonRaphson(fn, fd, x0, maxIter);
   }

   public static double bisection(final Function1 fn, final double a, final double b) {
      return RootFinding$.MODULE$.bisection(fn, a, b);
   }

   public static Option find$default$3() {
      return RootFinding$.MODULE$.find$default$3();
   }

   public static double find(final Function1 fn, final double x0, final Option x1) {
      return RootFinding$.MODULE$.find(fn, x0, x1);
   }

   public static int defaultMaxIter() {
      return RootFinding$.MODULE$.defaultMaxIter();
   }

   public static double eps() {
      return RootFinding$.MODULE$.eps();
   }
}
