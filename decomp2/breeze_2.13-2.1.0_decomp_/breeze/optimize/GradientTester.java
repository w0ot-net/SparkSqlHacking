package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import scala.;
import scala.Function1;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ux!B\b\u0011\u0011\u0003)b!B\f\u0011\u0011\u0003A\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"CA\u000f\u0003E\u0005I\u0011AA\u0010\u0011%\tY$AI\u0001\n\u0003\ti\u0004C\u0005\u0002H\u0005\t\n\u0011\"\u0001\u0002J!I\u0011qJ\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u000b\u0005\n\u0003/\n\u0011\u0013!C\u0001\u00033Bq!!\u001e\u0002\t\u0003\t9\bC\u0005\u0002D\u0006\t\n\u0011\"\u0001\u0002F\"I\u00111Z\u0001\u0012\u0002\u0013\u0005\u0011Q\u001a\u0005\n\u00037\f\u0011\u0013!C\u0001\u0003;D\u0011\"a9\u0002#\u0003%\t!!:\t\u0013\u0005-\u0018!!A\u0005\n\u00055\u0018AD$sC\u0012LWM\u001c;UKN$XM\u001d\u0006\u0003#I\t\u0001b\u001c9uS6L'0\u001a\u0006\u0002'\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0017\u00035\t\u0001C\u0001\bHe\u0006$\u0017.\u001a8u)\u0016\u001cH/\u001a:\u0014\u0007\u0005Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\rj\u0011!\t\u0006\u0003EI\tA!\u001e;jY&\u0011A%\t\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003U\tA\u0001^3tiV\u0019\u0011\u0006S\u0017\u0015\u0011)b\u0017o];{yz$ba\u000b\u001cB\u001bV\u000b\u0007C\u0001\u0017.\u0019\u0001!QAL\u0002C\u0002=\u0012\u0011\u0001V\t\u0003aM\u0002\"AG\u0019\n\u0005IZ\"a\u0002(pi\"Lgn\u001a\t\u00035QJ!!N\u000e\u0003\u0007\u0005s\u0017\u0010C\u00038\u0007\u0001\u000f\u0001(A\u0003wS\u0016<(\u0007\u0005\u0003\u001bs-Z\u0014B\u0001\u001e\u001c\u0005A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000fE\u0002=\u007f-j\u0011!\u0010\u0006\u0003}I\ta\u0001\\5oC2<\u0017B\u0001!>\u0005)qU/\\3sS\u000e|\u0005o\u001d\u0005\u0006\u0005\u000e\u0001\u001daQ\u0001\u0005m&,w\u000f\u0005\u0003\u001bs-\"\u0005\u0003\u0002\u001fF\u000f*K!AR\u001f\u0003\rQ+gn]8s!\ta\u0003\nB\u0003J\u0007\t\u0007qFA\u0001L!\tQ2*\u0003\u0002M7\t1Ai\\;cY\u0016DQAT\u0002A\u0004=\u000bAaY8qsB\u0019\u0001kU\u0016\u000e\u0003ES!AU\u001f\u0002\u000fM,\b\u000f]8si&\u0011A+\u0015\u0002\b\u0007\u0006t7i\u001c9z\u0011\u001516\u0001q\u0001X\u0003\u001d\u0019\u0017M\u001c(pe6\u0004B\u0001W.,\u0015:\u0011A(W\u0005\u00035v\nAA\\8s[&\u0011A,\u0018\u0002\u0005\u00136\u0004H.\u0003\u0002_?\n)QKR;oG*\u0011\u0001ME\u0001\bO\u0016tWM]5d\u0011\u0015\u00117\u0001q\u0001d\u0003\u0015y\u0007oU;c!\u0015!'nK\u0016,\u001d\t)\u0007.D\u0001g\u0015\t9W(A\u0005pa\u0016\u0014\u0018\r^8sg&\u0011\u0011NZ\u0001\u0006\u001fB\u001cVOY\u0005\u0003Wv\u0013Q!S7qYJBQ!\\\u0002A\u00029\f\u0011A\u001a\t\u0004-=\\\u0013B\u00019\u0011\u00051!\u0015N\u001a4Gk:\u001cG/[8o\u0011\u0015\u00118\u00011\u0001,\u0003\u0005A\bb\u0002;\u0004!\u0003\u0005\rAS\u0001\re\u0006tGM\u0012:bGRLwN\u001c\u0005\bm\u000e\u0001\n\u00111\u0001x\u0003%\u00198.\u001b9[KJ|7\u000f\u0005\u0002\u001bq&\u0011\u0011p\u0007\u0002\b\u0005>|G.Z1o\u0011\u001dY8\u0001%AA\u0002)\u000bq!\u001a9tS2|g\u000eC\u0004~\u0007A\u0005\t\u0019\u0001&\u0002\u0013Q|G.\u001a:b]\u000e,\u0007\u0002C@\u0004!\u0003\u0005\r!!\u0001\u0002\u0011Q|7\u000b\u001e:j]\u001e\u0004bAGA\u0002\u000f\u0006\u001d\u0011bAA\u00037\tIa)\u001e8di&|g.\r\t\u0005\u0003\u0013\t9B\u0004\u0003\u0002\f\u0005M\u0001cAA\u000775\u0011\u0011q\u0002\u0006\u0004\u0003#!\u0012A\u0002\u001fs_>$h(C\u0002\u0002\u0016m\ta\u0001\u0015:fI\u00164\u0017\u0002BA\r\u00037\u0011aa\u0015;sS:<'bAA\u000b7\u0005qA/Z:uI\u0011,g-Y;mi\u0012\u001aTCBA\u0011\u0003o\tI$\u0006\u0002\u0002$)\u001a!*!\n,\u0005\u0005\u001d\u0002\u0003BA\u0015\u0003gi!!a\u000b\u000b\t\u00055\u0012qF\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\r\u001c\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003k\tYCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q!\u0013\u0003C\u0002=\"QA\f\u0003C\u0002=\na\u0002^3ti\u0012\"WMZ1vYR$C'\u0006\u0004\u0002@\u0005\r\u0013QI\u000b\u0003\u0003\u0003R3a^A\u0013\t\u0015IUA1\u00010\t\u0015qSA1\u00010\u00039!Xm\u001d;%I\u00164\u0017-\u001e7uIU*b!!\t\u0002L\u00055C!B%\u0007\u0005\u0004yC!\u0002\u0018\u0007\u0005\u0004y\u0013A\u0004;fgR$C-\u001a4bk2$HEN\u000b\u0007\u0003C\t\u0019&!\u0016\u0005\u000b%;!\u0019A\u0018\u0005\u000b9:!\u0019A\u0018\u0002\u001dQ,7\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%oU1\u00111LA2\u0003g*\"!!\u0018+\t\u0005}\u0013Q\u0005\t\b5\u0005\r\u0011\u0011MA3!\ra\u00131\r\u0003\u0006\u0013\"\u0011\ra\f\t\u0005\u0003O\n\t(\u0004\u0002\u0002j)!\u00111NA7\u0003\u0011a\u0017M\\4\u000b\u0005\u0005=\u0014\u0001\u00026bm\u0006LA!!\u0007\u0002j\u0011)a\u0006\u0003b\u0001_\u0005YA/Z:u\u0013:$\u0017nY3t+\u0019\tI(a \u0002\u0010R\u0001\u00121PAO\u0003C\u000b\u0019+!/\u0002<\u0006}\u0016\u0011\u0019\u000b\r\u0003{\n\t)a\"\u0002\u0012\u0006U\u0015\u0011\u0014\t\u0004Y\u0005}D!\u0002\u0018\n\u0005\u0004y\u0003BB\u001c\n\u0001\b\t\u0019\t\u0005\u0004\u001bs\u0005u\u0014Q\u0011\t\u0005y}\ni\b\u0003\u0004C\u0013\u0001\u000f\u0011\u0011\u0012\t\u00075e\ni(a#\u0011\u000bq*\u0015Q\u0012&\u0011\u00071\ny\tB\u0003J\u0013\t\u0007q\u0006\u0003\u0004O\u0013\u0001\u000f\u00111\u0013\t\u0005!N\u000bi\b\u0003\u0004W\u0013\u0001\u000f\u0011q\u0013\t\u00061n\u000biH\u0013\u0005\u0007E&\u0001\u001d!a'\u0011\u0011\u0011T\u0017QPA?\u0003{Ba!\\\u0005A\u0002\u0005}\u0005\u0003\u0002\fp\u0003{BaA]\u0005A\u0002\u0005u\u0004bBAS\u0013\u0001\u0007\u0011qU\u0001\bS:$\u0017nY3t!\u0019\tI+a-\u0002\u000e:!\u00111VAX\u001d\u0011\ti!!,\n\u0003qI1!!-\u001c\u0003\u001d\u0001\u0018mY6bO\u0016LA!!.\u00028\nA\u0011\n^3sC\ndWMC\u0002\u00022nAqA^\u0005\u0011\u0002\u0003\u0007q\u000f\u0003\u0005\u0000\u0013A\u0005\t\u0019AA_!\u001dQ\u00121AAG\u0003\u000fAqa_\u0005\u0011\u0002\u0003\u0007!\nC\u0004~\u0013A\u0005\t\u0019\u0001&\u0002+Q,7\u000f^%oI&\u001cWm\u001d\u0013eK\u001a\fW\u000f\u001c;%iU1\u0011qHAd\u0003\u0013$QA\f\u0006C\u0002=\"Q!\u0013\u0006C\u0002=\nQ\u0003^3ti&sG-[2fg\u0012\"WMZ1vYR$S'\u0006\u0004\u0002P\u0006e\u0017q[\u000b\u0003\u0003#TC!a5\u0002&A9!$a\u0001\u0002V\u0006\u0015\u0004c\u0001\u0017\u0002X\u0012)\u0011j\u0003b\u0001_\u0011)af\u0003b\u0001_\u0005)B/Z:u\u0013:$\u0017nY3tI\u0011,g-Y;mi\u00122TCBA\u0011\u0003?\f\t\u000fB\u0003/\u0019\t\u0007q\u0006B\u0003J\u0019\t\u0007q&A\u000buKN$\u0018J\u001c3jG\u0016\u001cH\u0005Z3gCVdG\u000fJ\u001c\u0016\r\u0005\u0005\u0012q]Au\t\u0015qSB1\u00010\t\u0015IUB1\u00010\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u000f\u0005\u0003\u0002h\u0005E\u0018\u0002BAz\u0003S\u0012aa\u00142kK\u000e$\b"
)
public final class GradientTester {
   public static double testIndices$default$7() {
      return GradientTester$.MODULE$.testIndices$default$7();
   }

   public static double testIndices$default$6() {
      return GradientTester$.MODULE$.testIndices$default$6();
   }

   public static Function1 testIndices$default$5() {
      return GradientTester$.MODULE$.testIndices$default$5();
   }

   public static boolean testIndices$default$4() {
      return GradientTester$.MODULE$.testIndices$default$4();
   }

   public static Object testIndices(final DiffFunction f, final Object x, final Iterable indices, final boolean skipZeros, final Function1 toString, final double epsilon, final double tolerance, final .less.colon.less view2, final .less.colon.less view, final CanCopy copy, final UFunc.UImpl canNorm, final UFunc.UImpl2 opSub) {
      return GradientTester$.MODULE$.testIndices(f, x, indices, skipZeros, toString, epsilon, tolerance, view2, view, copy, canNorm, opSub);
   }

   public static Function1 test$default$7() {
      return GradientTester$.MODULE$.test$default$7();
   }

   public static double test$default$6() {
      return GradientTester$.MODULE$.test$default$6();
   }

   public static double test$default$5() {
      return GradientTester$.MODULE$.test$default$5();
   }

   public static boolean test$default$4() {
      return GradientTester$.MODULE$.test$default$4();
   }

   public static double test$default$3() {
      return GradientTester$.MODULE$.test$default$3();
   }

   public static Object test(final DiffFunction f, final Object x, final double randFraction, final boolean skipZeros, final double epsilon, final double tolerance, final Function1 toString, final .less.colon.less view2, final .less.colon.less view, final CanCopy copy, final UFunc.UImpl canNorm, final UFunc.UImpl2 opSub) {
      return GradientTester$.MODULE$.test(f, x, randFraction, skipZeros, epsilon, tolerance, toString, view2, view, copy, canNorm, opSub);
   }
}
