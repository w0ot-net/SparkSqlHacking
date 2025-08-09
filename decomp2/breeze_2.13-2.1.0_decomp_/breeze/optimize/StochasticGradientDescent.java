package breeze.optimize;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.math.NormedModule;
import java.io.Serializable;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug!\u0002\u0013&\u0003\u0003Q\u0003\u0002C$\u0001\u0005\u000b\u0007I\u0011\u0001%\t\u00111\u0003!\u0011!Q\u0001\n%C\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005\u001f\"A1\u000b\u0001B\u0001B\u0003%\u0011\n\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003P\u0011!)\u0006A!b\u0001\n'1\u0006\u0002C/\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u000by\u0003A\u0011A0\t\u000b\u001d\u0004A\u0011\u00035\t\u000bM\u0004A\u0011\u0003;\t\u000ba\u0004A\u0011A=\b\u000by,\u0003\u0012A@\u0007\r\u0011*\u0003\u0012AA\u0001\u0011\u0019qf\u0002\"\u0001\u0002\u001a!9\u00111\u0004\b\u0005\u0002\u0005u\u0001\"CA\u001b\u001dE\u0005I\u0011AA\u001c\u0011%\t\tFDI\u0001\n\u0003\t\u0019F\u0002\u0004\u0002\\9\u0001\u0011Q\f\u0005\u000b\u0003c\u0019\"\u0011!Q\u0001\n%\u000b\u0001\"C'\u0014\u0005\u0003\u0005\u000b\u0011B(\u0004\u0011-\tYc\u0005B\u0001B\u0003-\u0011qM\u0004\t\ry\u001bB\u0011AA5\u000b\u0019\t9h\u0005\u0001\u0002z!9\u0011qP\n\u0005\u0002\u0005\u0005\u0005bBAF'\u0011\u0005\u0011QR\u0004\n\u0003Ks\u0011\u0011!E\u0001\u0003O3\u0011\"a\u0017\u000f\u0003\u0003E\t!!+\t\rycB\u0011AAV\u0011%\ti\u000bHI\u0001\n\u0003\ty\u000bC\u0005\u00024r\t\n\u0011\"\u0001\u00026\"I\u0011\u0011\u0018\u000f\u0002\u0002\u0013%\u00111\u0018\u0005\n\u0003\u0013t\u0011\u0013!C\u0001\u0003\u0017D\u0011\"a4\u000f#\u0003%\t!!5\t\u0013\u0005ef\"!A\u0005\n\u0005m&!G*u_\u000eD\u0017m\u001d;jG\u001e\u0013\u0018\rZ5f]R$Um]2f]RT!AJ\u0014\u0002\u0011=\u0004H/[7ju\u0016T\u0011\u0001K\u0001\u0007EJ,WM_3\u0004\u0001U\u00111FM\n\u0004\u00011\n\u0005\u0003B\u0017/ayj\u0011!J\u0005\u0003_\u0015\u00121CR5sgR|%\u000fZ3s\u001b&t\u0017.\\5{KJ\u0004\"!\r\u001a\r\u0001\u0011)1\u0007\u0001b\u0001i\t\tA+\u0005\u00026wA\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t9aj\u001c;iS:<\u0007C\u0001\u001c=\u0013\titGA\u0002B]f\u00042!L 1\u0013\t\u0001UE\u0001\fTi>\u001c\u0007.Y:uS\u000e$\u0015N\u001a4Gk:\u001cG/[8o!\t\u0011U)D\u0001D\u0015\t!u%\u0001\u0003vi&d\u0017B\u0001$D\u0005M\u0019VM]5bY&T\u0018M\u00197f\u0019><w-\u001b8h\u0003=!WMZ1vYR\u001cF/\u001a9TSj,W#A%\u0011\u0005YR\u0015BA&8\u0005\u0019!u.\u001e2mK\u0006\u0001B-\u001a4bk2$8\u000b^3q'&TX\rI\u0001\b[\u0006D\u0018\n^3s+\u0005y\u0005C\u0001\u001cQ\u0013\t\tvGA\u0002J]R\f\u0001\"\\1y\u0013R,'\u000fI\u0001\ni>dWM]1oG\u0016\f!B\u001a<bY6+Wn\u001c:z\u0003\u001918\u000f]1dKV\tq\u000b\u0005\u0003Y7BJU\"A-\u000b\u0005i;\u0013\u0001B7bi\"L!\u0001X-\u0003\u00199{'/\\3e\u001b>$W\u000f\\3\u0002\u000fY\u001c\b/Y2fA\u00051A(\u001b8jiz\"R\u0001Y2eK\u001a$\"!\u00192\u0011\u00075\u0002\u0001\u0007C\u0003V\u0013\u0001\u000fq\u000bC\u0003H\u0013\u0001\u0007\u0011\nC\u0003N\u0013\u0001\u0007q\nC\u0004T\u0013A\u0005\t\u0019A%\t\u000fQK\u0001\u0013!a\u0001\u001f\u0006AA/Y6f'R,\u0007\u000f\u0006\u00031S>\f\b\"\u00026\u000b\u0001\u0004Y\u0017!B:uCR,\u0007C\u00017n\u001b\u0005\u0001\u0011B\u00018/\u0005\u0015\u0019F/\u0019;f\u0011\u0015\u0001(\u00021\u00011\u0003\r!\u0017N\u001d\u0005\u0006e*\u0001\r!S\u0001\tgR,\u0007oU5{K\u000612\r[8pg\u0016$Um]2f]R$\u0015N]3di&|g\u000eF\u00021kZDQA[\u0006A\u0002-DQa^\u0006A\u0002y\n!A\u001a8\u0002#\u0011,G/\u001a:nS:,7\u000b^3q'&TX\r\u0006\u0003Junl\b\"\u00026\r\u0001\u0004Y\u0007\"\u0002?\r\u0001\u0004q\u0014!\u00014\t\u000bAd\u0001\u0019\u0001\u0019\u00023M#xn\u00195bgRL7m\u0012:bI&,g\u000e\u001e#fg\u000e,g\u000e\u001e\t\u0003[9\u0019RADA\u0002\u0003\u0013\u00012ANA\u0003\u0013\r\t9a\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005-\u0011QC\u0007\u0003\u0003\u001bQA!a\u0004\u0002\u0012\u0005\u0011\u0011n\u001c\u0006\u0003\u0003'\tAA[1wC&!\u0011qCA\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005y\u0018!B1qa2LX\u0003BA\u0010\u0003O!b!!\t\u00020\u0005MB\u0003BA\u0012\u0003S\u0001B!\f\u0001\u0002&A\u0019\u0011'a\n\u0005\u000bM\u0002\"\u0019\u0001\u001b\t\u000f\u0005-\u0002\u0003q\u0001\u0002.\u0005\u0011ao\u001d\t\u00061n\u000b)#\u0013\u0005\t\u0003c\u0001\u0002\u0013!a\u0001\u0013\u0006y\u0011N\\5uS\u0006d7\u000b^3q'&TX\rC\u0004N!A\u0005\t\u0019A(\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE*B!!\u000f\u0002PU\u0011\u00111\b\u0016\u0004\u0013\u0006u2FAA !\u0011\t\t%a\u0013\u000e\u0005\u0005\r#\u0002BA#\u0003\u000f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%s'\u0001\u0006b]:|G/\u0019;j_:LA!!\u0014\u0002D\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bM\n\"\u0019\u0001\u001b\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uII*B!!\u0016\u0002ZU\u0011\u0011q\u000b\u0016\u0004\u001f\u0006uB!B\u001a\u0013\u0005\u0004!$!C*j[BdWmU$E+\u0011\ty&!\u001a\u0014\u0007M\t\t\u0007\u0005\u0003.\u0001\u0005\r\u0004cA\u0019\u0002f\u0011)1g\u0005b\u0001iA)\u0001lWA2\u0013R1\u00111NA:\u0003k\"B!!\u001c\u0002rA)\u0011qN\n\u0002d5\ta\u0002C\u0004\u0002,]\u0001\u001d!a\u001a\t\u0011\u0005Er\u0003%AA\u0002%Cq!T\f\u0011\u0002\u0003\u0007qJA\u0004ISN$xN]=\u0011\u0007Y\nY(C\u0002\u0002~]\u0012A!\u00168ji\u0006q\u0011N\\5uS\u0006d\u0007*[:u_JLHCBA=\u0003\u0007\u000b9\t\u0003\u0004}3\u0001\u0007\u0011Q\u0011\t\u0005[}\n\u0019\u0007C\u0004\u0002\nf\u0001\r!a\u0019\u0002\t%t\u0017\u000e^\u0001\u000ekB$\u0017\r^3ISN$xN]=\u0015\u0019\u0005e\u0014qRAJ\u0003/\u000bY*!(\t\u000f\u0005E%\u00041\u0001\u0002d\u0005!a.Z<Y\u0011\u001d\t)J\u0007a\u0001\u0003G\nqA\\3x\u000fJ\fG\r\u0003\u0004\u0002\u001aj\u0001\r!S\u0001\t]\u0016<h+\u00197vK\"1AP\u0007a\u0001\u0003\u000bCq!a(\u001b\u0001\u0004\t\t+\u0001\u0005pY\u0012\u001cF/\u0019;f!\r\t\u0019+\\\u0007\u0002'\u0005I1+[7qY\u0016\u001cv\t\u0012\t\u0004\u0003_b2#\u0002\u000f\u0002\u0004\u0005%ACAAT\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU!\u0011\u0011HAY\t\u0015\u0019dD1\u00015\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU!\u0011QKA\\\t\u0015\u0019tD1\u00015\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\f\u0005\u0003\u0002@\u0006\u0015WBAAa\u0015\u0011\t\u0019-!\u0005\u0002\t1\fgnZ\u0005\u0005\u0003\u000f\f\tM\u0001\u0004PE*,7\r^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005e\u0012Q\u001a\u0003\u0006g\u0005\u0012\r\u0001N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\t\u0005U\u00131\u001b\u0003\u0006g\t\u0012\r\u0001\u000e"
)
public abstract class StochasticGradientDescent extends FirstOrderMinimizer {
   private final double defaultStepSize;
   private final int maxIter;
   private final NormedModule vspace;

   public static int $lessinit$greater$default$4() {
      return StochasticGradientDescent$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return StochasticGradientDescent$.MODULE$.$lessinit$greater$default$3();
   }

   public static int apply$default$2() {
      return StochasticGradientDescent$.MODULE$.apply$default$2();
   }

   public static double apply$default$1() {
      return StochasticGradientDescent$.MODULE$.apply$default$1();
   }

   public static StochasticGradientDescent apply(final double initialStepSize, final int maxIter, final NormedModule vs) {
      return StochasticGradientDescent$.MODULE$.apply(initialStepSize, maxIter, vs);
   }

   public double defaultStepSize() {
      return this.defaultStepSize;
   }

   public int maxIter() {
      return this.maxIter;
   }

   public NormedModule vspace() {
      return this.vspace;
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      return ((NumericOps)this.vspace().hasOps().apply(state.x())).$plus(((ImmutableNumericOps)this.vspace().hasOps().apply(dir)).$times(BoxesRunTime.boxToDouble(stepSize), this.vspace().mulVS_M()), this.vspace().addVV());
   }

   public Object chooseDescentDirection(final FirstOrderMinimizer.State state, final StochasticDiffFunction fn) {
      return ((ImmutableNumericOps)this.vspace().hasOps().apply(state.grad())).$times(BoxesRunTime.boxToDouble((double)-1.0F), this.vspace().mulVS_M());
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final StochasticDiffFunction f, final Object dir) {
      return this.defaultStepSize() / .MODULE$.pow((double)(state.iter() + 1), 0.6666666666666666);
   }

   public StochasticGradientDescent(final double defaultStepSize, final int maxIter, final double tolerance, final int fvalMemory, final NormedModule vspace) {
      super(maxIter, tolerance, fvalMemory, true, vspace);
      this.defaultStepSize = defaultStepSize;
      this.maxIter = maxIter;
      this.vspace = vspace;
   }

   public static class SimpleSGD$ implements Serializable {
      public static final SimpleSGD$ MODULE$ = new SimpleSGD$();

      public double $lessinit$greater$default$1() {
         return (double)4.0F;
      }

      public int $lessinit$greater$default$2() {
         return 100;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SimpleSGD$.class);
      }
   }

   public static class SimpleSGD extends StochasticGradientDescent {
      public void initialHistory(final StochasticDiffFunction f, final Object init) {
      }

      public void updateHistory(final Object newX, final Object newGrad, final double newValue, final StochasticDiffFunction f, final FirstOrderMinimizer.State oldState) {
      }

      public SimpleSGD(final double initialStepSize, final int maxIter, final NormedModule vs) {
         super(initialStepSize, maxIter, StochasticGradientDescent$.MODULE$.$lessinit$greater$default$3(), StochasticGradientDescent$.MODULE$.$lessinit$greater$default$4(), vs);
      }
   }
}
