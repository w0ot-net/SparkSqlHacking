package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.optimize.proximal.QuadraticMinimizer$;
import breeze.storage.Zero$;
import breeze.util.Implicits$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t}c\u0001\u0002\u001d:\u0001\u0001C\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\t#\u0002\u0011\t\u0011)A\u0005%\")Q\u000b\u0001C\u0001-\u001a!1\f\u0001!]\u0011!aGA!f\u0001\n\u0003i\u0007\u0002\u00038\u0005\u0005#\u0005\u000b\u0011\u0002*\t\u0011=$!Q3A\u0005\u0002AD\u0011\"!\u0014\u0005\u0005#\u0005\u000b\u0011B9\t\u0013\u0005=CA!f\u0001\n\u0003\u0001\b\"CA)\t\tE\t\u0015!\u0003r\u0011)\t\u0019\u0006\u0002BK\u0002\u0013\u0005\u0011Q\u000b\u0005\n\u0003/\"!\u0011#Q\u0001\n9C!\"!\u0017\u0005\u0005+\u0007I\u0011AA.\u0011)\t\u0019\u0007\u0002B\tB\u0003%\u0011Q\f\u0005\b+\u0012!\t\u0001AA3\u0011%\t)\bBA\u0001\n\u0003\t9\bC\u0005\u0002\u0004\u0012\t\n\u0011\"\u0001\u00026!I\u0011Q\u0011\u0003\u0012\u0002\u0013\u0005\u0011q\u0011\u0005\n\u0003\u0017#\u0011\u0013!C\u0001\u0003\u000fC\u0011\"!$\u0005#\u0003%\t!!\b\t\u0013\u0005=E!%A\u0005\u0002\u0005E\u0005\"CAK\t\u0005\u0005I\u0011IAL\u0011%\ty\nBA\u0001\n\u0003\t)\u0006C\u0005\u0002\"\u0012\t\t\u0011\"\u0001\u0002$\"I\u0011q\u0016\u0003\u0002\u0002\u0013\u0005\u0013\u0011\u0017\u0005\n\u0003\u007f#\u0011\u0011!C\u0001\u0003\u0003D\u0011\"!2\u0005\u0003\u0003%\t%a2\t\u0013\u0005-G!!A\u0005B\u00055\u0007\"CAh\t\u0005\u0005I\u0011IAi\u0011%\t\u0019\u000eBA\u0001\n\u0003\n)nB\u0005\u0002Z\u0002\t\t\u0011#\u0001\u0002\\\u001aA1\fAA\u0001\u0012\u0003\ti\u000e\u0003\u0004VA\u0011\u0005\u00111\u001e\u0005\n\u0003\u001f\u0004\u0013\u0011!C#\u0003#D\u0011\"!<!\u0003\u0003%\t)a<\t\u0013\u0005m\b%!A\u0005\u0002\u0006u\bb\u0002B\b\u0001\u0011\u0005!\u0011\u0003\u0005\b\u00057\u0001A\u0011\u0001B\u000f\u0011\u001d\u0011\u0019\u0003\u0001C\u0001\u0005KAqAa\r\u0001\t\u0003\u0011)\u0004C\u0004\u0003<\u0001!\tA!\u0010\t\u000f\tm\u0002\u0001\"\u0001\u0003H!9!q\n\u0001\u0005\u0002\tE\u0003b\u0002B,\u0001\u0011\u0005!\u0011L\u0004\u0006gfB\t\u0001\u001e\u0004\u0006qeB\t!\u001e\u0005\u0006+:\"\t!`\u0003\u0005}:\u0002q0\u0002\u0004\u0002\f9\u0002\u0011Q\u0002\u0005\b\u0003'qC\u0011AA\u000b\u0011%\tYBLI\u0001\n\u0003\ti\u0002C\u0005\u000249\n\n\u0011\"\u0001\u00026!I\u0011\u0011\b\u0018\u0012\u0002\u0013\u0005\u0011Q\u0004\u0005\n\u0003wq\u0013\u0013!C\u0001\u0003kA\u0011\"!\u0010/\u0003\u0003%I!a\u0010\u0003\u0017A{w/\u001a:NKRDw\u000e\u001a\u0006\u0003um\na\u0001\\5oK\u0006\u0014(B\u0001\u001f>\u0003!y\u0007\u000f^5nSj,'\"\u0001 \u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001A!H!\t\u0011U)D\u0001D\u0015\u0005!\u0015!B:dC2\f\u0017B\u0001$D\u0005\u0019\te.\u001f*fMB\u0011\u0001jS\u0007\u0002\u0013*\u0011!*P\u0001\u0005kRLG.\u0003\u0002M\u0013\n\u00192+\u001a:jC2L'0\u00192mK2{wmZ5oO\u0006AQ.\u0019=Ji\u0016\u00148\u000f\u0005\u0002C\u001f&\u0011\u0001k\u0011\u0002\u0004\u0013:$\u0018!\u0003;pY\u0016\u0014\u0018M\\2f!\t\u00115+\u0003\u0002U\u0007\n1Ai\\;cY\u0016\fa\u0001P5oSRtDcA,Z5B\u0011\u0001\fA\u0007\u0002s!9Qj\u0001I\u0001\u0002\u0004q\u0005bB)\u0004!\u0003\u0005\rA\u0015\u0002\u0006'R\fG/Z\n\u0005\t\u0005k\u0006\r\u0005\u0002C=&\u0011ql\u0011\u0002\b!J|G-^2u!\t\t\u0017N\u0004\u0002cO:\u00111MZ\u0007\u0002I*\u0011QmP\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011K!\u0001[\"\u0002\u000fA\f7m[1hK&\u0011!n\u001b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Q\u000e\u000b!\"Z5hK:4\u0016\r\\;f+\u0005\u0011\u0016aC3jO\u0016tg+\u00197vK\u0002\n1\"Z5hK:4Vm\u0019;peV\t\u0011\u000f\u0005\u0002sa9\u0011\u0001,L\u0001\f!><XM]'fi\"|G\r\u0005\u0002Y]M\u0019a&\u0011<\u0011\u0005]dX\"\u0001=\u000b\u0005eT\u0018AA5p\u0015\u0005Y\u0018\u0001\u00026bm\u0006L!A\u001b=\u0015\u0003Q\u00141A\u0011#W!\u0015\t\t!a\u0002S\u001b\t\t\u0019AC\u0002\u0002\u0006u\na\u0001\\5oC2<\u0017\u0002BA\u0005\u0003\u0007\u00111\u0002R3og\u00164Vm\u0019;pe\n\u0019!\tR'\u0011\u000b\u0005\u0005\u0011q\u0002*\n\t\u0005E\u00111\u0001\u0002\f\t\u0016t7/Z'biJL\u00070A\u0004j]Z,'o]3\u0015\u000b]\u000b9\"!\u0007\t\u000f5\u0013\u0004\u0013!a\u0001\u001d\"9\u0011K\rI\u0001\u0002\u0004\u0011\u0016!E5om\u0016\u00148/\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0004\u0016\u0004\u001d\u0006\u00052FAA\u0012!\u0011\t)#a\f\u000e\u0005\u0005\u001d\"\u0002BA\u0015\u0003W\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u000552)\u0001\u0006b]:|G/\u0019;j_:LA!!\r\u0002(\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002#%tg/\u001a:tK\u0012\"WMZ1vYR$#'\u0006\u0002\u00028)\u001a!+!\t\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\t\t\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)\u0019\u0011q\t>\u0002\t1\fgnZ\u0005\u0005\u0003\u0017\n)E\u0001\u0004PE*,7\r^\u0001\rK&<WM\u001c,fGR|'\u000fI\u0001\u0003Cf\f1!Y=!\u0003\u0011IG/\u001a:\u0016\u00039\u000bQ!\u001b;fe\u0002\n\u0011bY8om\u0016\u0014x-\u001a3\u0016\u0005\u0005u\u0003c\u0001\"\u0002`%\u0019\u0011\u0011M\"\u0003\u000f\t{w\u000e\\3b]\u0006Q1m\u001c8wKJ<W\r\u001a\u0011\u0015\u0019\u0005\u001d\u00141NA7\u0003_\n\t(a\u001d\u0011\u0007\u0005%D!D\u0001\u0001\u0011\u0015aw\u00021\u0001S\u0011\u0015yw\u00021\u0001r\u0011\u0019\tye\u0004a\u0001c\"1\u00111K\bA\u00029Cq!!\u0017\u0010\u0001\u0004\ti&\u0001\u0003d_BLH\u0003DA4\u0003s\nY(! \u0002\u0000\u0005\u0005\u0005b\u00027\u0011!\u0003\u0005\rA\u0015\u0005\b_B\u0001\n\u00111\u0001r\u0011!\ty\u0005\u0005I\u0001\u0002\u0004\t\b\u0002CA*!A\u0005\t\u0019\u0001(\t\u0013\u0005e\u0003\u0003%AA\u0002\u0005u\u0013AD2paf$C-\u001a4bk2$H%M\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tIIK\u0002r\u0003C\tabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u00111\u0013\u0016\u0005\u0003;\n\t#A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u00033\u0003B!a\u0011\u0002\u001c&!\u0011QTA#\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAS\u0003W\u00032AQAT\u0013\r\tIk\u0011\u0002\u0004\u0003:L\b\u0002CAW1\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\f\u0005\u0004\u00026\u0006m\u0016QU\u0007\u0003\u0003oS1!!/D\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003{\u000b9L\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA/\u0003\u0007D\u0011\"!,\u001b\u0003\u0003\u0005\r!!*\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u00033\u000bI\r\u0003\u0005\u0002.n\t\t\u00111\u0001O\u0003!A\u0017m\u001d5D_\u0012,G#\u0001(\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!'\u0002\r\u0015\fX/\u00197t)\u0011\ti&a6\t\u0013\u00055f$!AA\u0002\u0005\u0015\u0016!B*uCR,\u0007cAA5AM!\u0001%a8w!1\t\t/a:ScFt\u0015QLA4\u001b\t\t\u0019OC\u0002\u0002f\u000e\u000bqA];oi&lW-\u0003\u0003\u0002j\u0006\r(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8okQ\u0011\u00111\\\u0001\u0006CB\u0004H.\u001f\u000b\r\u0003O\n\t0a=\u0002v\u0006]\u0018\u0011 \u0005\u0006Y\u000e\u0002\rA\u0015\u0005\u0006_\u000e\u0002\r!\u001d\u0005\u0007\u0003\u001f\u001a\u0003\u0019A9\t\r\u0005M3\u00051\u0001O\u0011\u001d\tIf\ta\u0001\u0003;\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0000\n-\u0001#\u0002\"\u0003\u0002\t\u0015\u0011b\u0001B\u0002\u0007\n1q\n\u001d;j_:\u0004\u0012B\u0011B\u0004%F\fh*!\u0018\n\u0007\t%1I\u0001\u0004UkBdW-\u000e\u0005\n\u0005\u001b!\u0013\u0011!a\u0001\u0003O\n1\u0001\u001f\u00131\u0003%qwN]7bY&TX\rF\u0003\u0000\u0005'\u00119\u0002\u0003\u0004\u0003\u0016\u0015\u0002\r!]\u0001\u0006s:|'/\u001c\u0005\u0007\u00053)\u0003\u0019A9\u0002\u0003e\fA\"\u001b8ji&\fGn\u0015;bi\u0016$B!a\u001a\u0003 !1!\u0011\u0005\u0014A\u00029\u000b\u0011A\\\u0001\u0006e\u0016\u001cX\r\u001e\u000b\t\u0003O\u00129C!\f\u00030!9!\u0011F\u0014A\u0002\t-\u0012!A!\u0011\u0005I\f\u0004B\u0002B\rO\u0001\u0007\u0011\u000fC\u0004\u00032\u001d\u0002\r!a\u001a\u0002\t%t\u0017\u000e^\u0001\n]\u0016DH/R5hK:$RA\u0015B\u001c\u0005sAQa\u001c\u0015A\u0002EDa!a\u0014)\u0001\u0004\t\u0018AC5uKJ\fG/[8ogR1!q\bB\"\u0005\u000b\u0002R!\u0019B!\u0003OJ1!!0l\u0011\u001d\u0011I#\u000ba\u0001\u0005WAaA!\u0007*\u0001\u0004\tH\u0003\u0003B \u0005\u0013\u0012YE!\u0014\t\u000f\t%\"\u00061\u0001\u0003,!1!\u0011\u0004\u0016A\u0002EDqAa\u0007+\u0001\u0004\t9'A\u000bji\u0016\u0014\u0018\r^3B]\u0012\u0014V\r^;s]N#\u0018\r^3\u0015\r\u0005\u001d$1\u000bB+\u0011\u001d\u0011Ic\u000ba\u0001\u0005WAaA!\u0007,\u0001\u0004\t\u0018!B3jO\u0016tG#\u0002*\u0003\\\tu\u0003b\u0002B\u0015Y\u0001\u0007!1\u0006\u0005\u0007\u00053a\u0003\u0019A9"
)
public class PowerMethod implements SerializableLogging {
   private volatile State$ State$module;
   private final int maxIters;
   private final double tolerance;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static double $lessinit$greater$default$2() {
      return PowerMethod$.MODULE$.$lessinit$greater$default$2();
   }

   public static int $lessinit$greater$default$1() {
      return PowerMethod$.MODULE$.$lessinit$greater$default$1();
   }

   public static double inverse$default$2() {
      return PowerMethod$.MODULE$.inverse$default$2();
   }

   public static int inverse$default$1() {
      return PowerMethod$.MODULE$.inverse$default$1();
   }

   public static PowerMethod inverse(final int maxIters, final double tolerance) {
      return PowerMethod$.MODULE$.inverse(maxIters, tolerance);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public DenseVector normalize(final DenseVector ynorm, final DenseVector y) {
      double normInit = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(y, norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
      ynorm.$colon$eq(y, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
      return (DenseVector)ynorm.$times$eq(BoxesRunTime.boxToDouble((double)1.0F / normInit), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
   }

   public State initialState(final int n) {
      DenseVector ynorm = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector ay = DenseVector$.MODULE$.zeros$mDc$sp(n, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      return this.State().apply((double)0.0F, ynorm, ay, 0, false);
   }

   public State reset(final DenseMatrix A, final DenseVector y, final State init) {
      scala.Predef..MODULE$.require(init.eigenVector().length() == y.length(), () -> "PowerMethod:reset mismatch in state dimension");
      this.normalize(init.eigenVector(), y);
      QuadraticMinimizer$.MODULE$.gemv((double)1.0F, A, init.eigenVector(), (double)0.0F, init.ay());
      double lambda = this.nextEigen(init.eigenVector(), init.ay());
      return this.State().apply(lambda, init.eigenVector(), init.ay(), 0, false);
   }

   public double nextEigen(final DenseVector eigenVector, final DenseVector ay) {
      double lambda = BoxesRunTime.unboxToDouble(eigenVector.dot(ay, HasOps$.MODULE$.canDotD()));
      eigenVector.$colon$eq(ay, HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
      double norm1 = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(ay, norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
      eigenVector.$times$eq(BoxesRunTime.boxToDouble((double)1.0F / norm1), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
      if (lambda < (double)0.0F) {
         eigenVector.$times$eq(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return lambda;
   }

   public Iterator iterations(final DenseMatrix A, final DenseVector y) {
      State init = this.initialState(y.length());
      return this.iterations(A, y, init);
   }

   public Iterator iterations(final DenseMatrix A, final DenseVector y, final State initialState) {
      return Implicits$.MODULE$.scEnrichIterator(scala.package..MODULE$.Iterator().iterate(this.reset(A, y, initialState), (state) -> {
         QuadraticMinimizer$.MODULE$.gemv((double)1.0F, A, state.eigenVector(), (double)0.0F, state.ay());
         double lambda = this.nextEigen(state.eigenVector(), state.ay());
         double val_dif = package.abs$.MODULE$.apply$mDDc$sp(lambda - state.eigenValue(), package$abs$absDoubleImpl$.MODULE$);
         return !(val_dif <= this.tolerance) && state.iter() <= this.maxIters ? this.State().apply(lambda, state.eigenVector(), state.ay(), state.iter() + 1, false) : this.State().apply(lambda, state.eigenVector(), state.ay(), state.iter() + 1, true);
      })).takeUpToWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$iterations$2(x$1)));
   }

   public State iterateAndReturnState(final DenseMatrix A, final DenseVector y) {
      return (State)Implicits$.MODULE$.scEnrichIterator(this.iterations(A, y)).last();
   }

   public double eigen(final DenseMatrix A, final DenseVector y) {
      return this.iterateAndReturnState(A, y).eigenValue();
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterations$2(final State x$1) {
      return x$1.converged();
   }

   public PowerMethod(final int maxIters, final double tolerance) {
      this.maxIters = maxIters;
      this.tolerance = tolerance;
      SerializableLogging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class State implements Product, Serializable {
      private final double eigenValue;
      private final DenseVector eigenVector;
      private final DenseVector ay;
      private final int iter;
      private final boolean converged;
      // $FF: synthetic field
      public final PowerMethod $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double eigenValue() {
         return this.eigenValue;
      }

      public DenseVector eigenVector() {
         return this.eigenVector;
      }

      public DenseVector ay() {
         return this.ay;
      }

      public int iter() {
         return this.iter;
      }

      public boolean converged() {
         return this.converged;
      }

      public State copy(final double eigenValue, final DenseVector eigenVector, final DenseVector ay, final int iter, final boolean converged) {
         return this.breeze$optimize$linear$PowerMethod$State$$$outer().new State(eigenValue, eigenVector, ay, iter, converged);
      }

      public double copy$default$1() {
         return this.eigenValue();
      }

      public DenseVector copy$default$2() {
         return this.eigenVector();
      }

      public DenseVector copy$default$3() {
         return this.ay();
      }

      public int copy$default$4() {
         return this.iter();
      }

      public boolean copy$default$5() {
         return this.converged();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.eigenValue());
               break;
            case 1:
               var10000 = this.eigenVector();
               break;
            case 2:
               var10000 = this.ay();
               break;
            case 3:
               var10000 = BoxesRunTime.boxToInteger(this.iter());
               break;
            case 4:
               var10000 = BoxesRunTime.boxToBoolean(this.converged());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "eigenValue";
               break;
            case 1:
               var10000 = "eigenVector";
               break;
            case 2:
               var10000 = "ay";
               break;
            case 3:
               var10000 = "iter";
               break;
            case 4:
               var10000 = "converged";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.eigenValue()));
         var1 = Statics.mix(var1, Statics.anyHash(this.eigenVector()));
         var1 = Statics.mix(var1, Statics.anyHash(this.ay()));
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, this.converged() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 5);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label74: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$linear$PowerMethod$State$$$outer() == this.breeze$optimize$linear$PowerMethod$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label50: {
                     State var4 = (State)x$1;
                     if (this.eigenValue() == var4.eigenValue() && this.iter() == var4.iter() && this.converged() == var4.converged()) {
                        label64: {
                           DenseVector var10000 = this.eigenVector();
                           DenseVector var5 = var4.eigenVector();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label64;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label64;
                           }

                           var10000 = this.ay();
                           DenseVector var6 = var4.ay();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label64;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label64;
                           }

                           if (var4.canEqual(this)) {
                              var9 = true;
                              break label50;
                           }
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label74;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      // $FF: synthetic method
      public PowerMethod breeze$optimize$linear$PowerMethod$State$$$outer() {
         return this.$outer;
      }

      public State(final double eigenValue, final DenseVector eigenVector, final DenseVector ay, final int iter, final boolean converged) {
         this.eigenValue = eigenValue;
         this.eigenVector = eigenVector;
         this.ay = ay;
         this.iter = iter;
         this.converged = converged;
         if (PowerMethod.this == null) {
            throw null;
         } else {
            this.$outer = PowerMethod.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction5 implements Serializable {
      // $FF: synthetic field
      private final PowerMethod $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final double eigenValue, final DenseVector eigenVector, final DenseVector ay, final int iter, final boolean converged) {
         return this.$outer.new State(eigenValue, eigenVector, ay, iter, converged);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToDouble(x$0.eigenValue()), x$0.eigenVector(), x$0.ay(), BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToBoolean(x$0.converged()))));
      }

      public State$() {
         if (PowerMethod.this == null) {
            throw null;
         } else {
            this.$outer = PowerMethod.this;
            super();
         }
      }
   }
}
