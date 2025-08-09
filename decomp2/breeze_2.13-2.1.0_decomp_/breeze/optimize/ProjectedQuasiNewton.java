package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.min$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Module;
import breeze.math.MutableInnerProductModule;
import breeze.math.Ring$;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t]b\u0001\u0002\u00192\u0001YB\u0011b\u0015\u0001\u0003\u0002\u0003\u0006I\u0001\u00162\t\u0011\r\u0004!Q1A\u0005\u0002\u0011D\u0001\u0002\u001b\u0001\u0003\u0002\u0003\u0006I!\u001a\u0005\tS\u0002\u0011)\u0019!C\u0001U\"Aa\u000e\u0001B\u0001B\u0003%1\u000e\u0003\u0005p\u0001\t\u0015\r\u0011\"\u0001q\u0011!!\bA!A!\u0002\u0013\t\b\u0002C;\u0001\u0005\u000b\u0007I\u0011\u00019\t\u0011Y\u0004!\u0011!Q\u0001\nED\u0001b\u001e\u0001\u0003\u0006\u0004%\tA\u001b\u0005\tq\u0002\u0011\t\u0011)A\u0005W\"A\u0011\u0010\u0001BC\u0002\u0013\u0005!\u0010\u0003\u0005|\u0001\t\u0005\t\u0015!\u0003B\u0011!a\bA!b\u0001\n\u0003i\b\"CA\u0002\u0001\t\u0005\t\u0015!\u0003\u007f\u0011)\t)\u0001\u0001B\u0001B\u0003-\u0011q\u0001\u0005\b\u0003'\u0001A\u0011AA\u000b\u000b\u0015\ti\u0003\u0001\u0001<\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003_)a!!\u0014\u0001\u0001\u0005=\u0003bBA+\u0001\u0011E\u0011q\u000b\u0005\b\u0003K\u0002A\u0011KA4\u0011\u001d\tY\b\u0001C\u0005\u0003{Bq!a\"\u0001\t#\tI\tC\u0004\u0002\u001a\u0002!\t\"a'\t\u000f\u0005\u0015\u0006\u0001\"\u0005\u0002(\"9\u0011\u0011\u0017\u0001\u0005\u0012\u0005MvaBAac!\u0005\u00111\u0019\u0004\u0007aEB\t!!2\t\u000f\u0005MQ\u0004\"\u0001\u0002N\u001a1\u0011qZ\u000f\u0001\u0003#D\u0011\"a5 \u0005\u0003\u0005\u000b\u0011B!\t\u0013\u0005UwD!A!\u0002\u0013Y\u0004\"CAl?\t\u0005\t\u0015!\u0003<\u0011)\tIn\bB\u0001B\u0003%\u0011q\n\u0005\b\u0003'yB\u0011AAn\u0011\u001d\tIo\bC!\u0003WD\u0011\"a<\u001e#\u0003%\t!!=\t\u0013\t\u001dQ$%A\u0005\u0002\t%\u0001\"\u0003B\u0007;E\u0005I\u0011\u0001B\b\u0011%\u0011\u0019\"HI\u0001\n\u0003\u0011y\u0001C\u0005\u0003\u0016u\t\n\u0011\"\u0001\u0003\n!I!qC\u000f\u0012\u0002\u0013\u0005!\u0011\u0002\u0005\n\u00053i\u0012\u0013!C\u0001\u0003cD\u0011Ba\u0007\u001e#\u0003%\tA!\b\t\u0013\t\u0005R$%A\u0005\u0002\t=\u0001\"\u0003B\u0012;\u0005\u0005I\u0011\u0002B\u0013\u0005Q\u0001&o\u001c6fGR,G-U;bg&tUm\u001e;p]*\u0011!gM\u0001\t_B$\u0018.\\5{K*\tA'\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0011\u0001qGS'\u0011\taJ4hR\u0007\u0002c%\u0011!(\r\u0002\u0014\r&\u00148\u000f^(sI\u0016\u0014X*\u001b8j[&TXM\u001d\t\u0004y}\nU\"A\u001f\u000b\u0005y\u001a\u0014A\u00027j]\u0006dw-\u0003\u0002A{\tYA)\u001a8tKZ+7\r^8s!\t\u0011U)D\u0001D\u0015\u0005!\u0015!B:dC2\f\u0017B\u0001$D\u0005\u0019!u.\u001e2mKB\u0019\u0001\bS\u001e\n\u0005%\u000b$\u0001\u0004#jM\u001a4UO\\2uS>t\u0007c\u0001\u001dLw%\u0011A*\r\u0002\u000b!J|'.Z2uS:<\u0007C\u0001(R\u001b\u0005y%B\u0001)4\u0003\u0011)H/\u001b7\n\u0005I{%aE*fe&\fG.\u001b>bE2,Gj\\4hS:<\u0017\u0001E2p]Z,'oZ3oG\u0016\u001c\u0005.Z2l!\r)vl\u000f\b\u0003-vs!a\u0016/\u000f\u0005a[V\"A-\u000b\u0005i+\u0014A\u0002\u001fs_>$h(C\u00015\u0013\t\u00114'\u0003\u0002_c\u0005\u0019b)\u001b:ti>\u0013H-\u001a:NS:LW.\u001b>fe&\u0011\u0001-\u0019\u0002\u0011\u0007>tg/\u001a:hK:\u001cWm\u00115fG.T!AX\u0019\n\u0005MK\u0014AD5o]\u0016\u0014x\n\u001d;j[&TXM]\u000b\u0002KB\u0019\u0001HZ\u001e\n\u0005\u001d\f$!G*qK\u000e$(/\u00197Qe>TWm\u0019;fI\u001e\u0013\u0018\rZ5f]R\fq\"\u001b8oKJ|\u0005\u000f^5nSj,'\u000fI\u0001\u0002[V\t1\u000e\u0005\u0002CY&\u0011Qn\u0011\u0002\u0004\u0013:$\u0018AA7!\u0003!Ig.\u001b;GK\u0006\u001cX#A9\u0011\u0005\t\u0013\u0018BA:D\u0005\u001d\u0011un\u001c7fC:\f\u0011\"\u001b8ji\u001a+\u0017m\u001d\u0011\u0002\u000fQ,7\u000f^(qi\u0006AA/Z:u\u001fB$\b%A\u0005nCb\u001c&o\u00195Ji\u0006QQ.\u0019=Te\u000eD\u0017\n\u001e\u0011\u0002\u000b\u001d\fW.\\1\u0016\u0003\u0005\u000baaZ1n[\u0006\u0004\u0013A\u00039s_*,7\r^5p]V\ta\u0010\u0005\u0003C\u007fnZ\u0014bAA\u0001\u0007\nIa)\u001e8di&|g.M\u0001\faJ|'.Z2uS>t\u0007%A\u0003ta\u0006\u001cW\r\u0005\u0004\u0002\n\u0005=1(Q\u0007\u0003\u0003\u0017Q1!!\u00044\u0003\u0011i\u0017\r\u001e5\n\t\u0005E\u00111\u0002\u0002\u001a\u001bV$\u0018M\u00197f\u0013:tWM\u001d)s_\u0012,8\r^'pIVdW-\u0001\u0004=S:LGO\u0010\u000b\u0013\u0003/\ti\"a\b\u0002\"\u0005\r\u0012QEA\u0014\u0003S\tY\u0003\u0006\u0003\u0002\u001a\u0005m\u0001C\u0001\u001d\u0001\u0011\u001d\t)!\u0005a\u0002\u0003\u000fAQaU\tA\u0002QCQaY\tA\u0002\u0015DQ![\tA\u0002-DQa\\\tA\u0002EDQ!^\tA\u0002EDQa^\tA\u0002-DQ!_\tA\u0002\u0005CQ\u0001`\tA\u0002y\u00141A\u0011#W)Q\t\t$!\u000e\u0002:\u0005m\u0012QHA \u0003\u0007\n)%a\u0012\u0002JQ!\u0011\u0011DA\u001a\u0011\u001d\t)a\u0005a\u0002\u0003\u000fA\u0001\"a\u000e\u0014!\u0003\u0005\r!Q\u0001\ni>dWM]1oG\u0016Dq![\n\u0011\u0002\u0003\u00071\u000eC\u0004p'A\u0005\t\u0019A9\t\u000fU\u001c\u0002\u0013!a\u0001c\"A\u0011\u0011I\n\u0011\u0002\u0003\u00071.A\u0004nCbLE/\u001a:\t\u000f]\u001c\u0002\u0013!a\u0001W\"9\u0011p\u0005I\u0001\u0002\u0004\t\u0005b\u0002?\u0014!\u0003\u0005\rA \u0005\t\u0003\u0017\u001a\u0002\u0013!a\u0001c\u0006\t\"/\u001a7bi&4X\rV8mKJ\fgnY3\u0003\u000f!K7\u000f^8ssB\u0019\u0001(!\u0015\n\u0007\u0005M\u0013G\u0001\bD_6\u0004\u0018m\u0019;IKN\u001c\u0018.\u00198\u0002\u001d%t\u0017\u000e^5bY\"K7\u000f^8ssR1\u0011\u0011LA/\u0003C\u00022!a\u0017\u0015\u001b\u0005\u0001\u0001BBA0+\u0001\u0007q)A\u0001g\u0011\u0019\t\u0019'\u0006a\u0001w\u0005!\u0011N\\5u\u0003\u0019\tGM[;tiRA\u0011\u0011NA8\u0003g\n9\bE\u0003C\u0003W\n5(C\u0002\u0002n\r\u0013a\u0001V;qY\u0016\u0014\u0004BBA9-\u0001\u00071(\u0001\u0003oK^D\u0006BBA;-\u0001\u00071(A\u0004oK^<%/\u00193\t\r\u0005ed\u00031\u0001B\u0003\u0019qWm\u001e,bY\u0006y1m\\7qkR,wI]1eS\u0016tG\u000fF\u0003<\u0003\u007f\n\u0019\t\u0003\u0004\u0002\u0002^\u0001\raO\u0001\u0002q\"1\u0011QQ\fA\u0002m\n\u0011aZ\u0001\u0017G\"|wn]3EKN\u001cWM\u001c;ESJ,7\r^5p]R)1(a#\u0002\u0016\"9\u0011Q\u0012\rA\u0002\u0005=\u0015!B:uCR,\u0007\u0003BA.\u0003#K1!a%:\u0005\u0015\u0019F/\u0019;f\u0011\u0019\t9\n\u0007a\u0001\u000f\u0006\u0011aM\\\u0001\u0012I\u0016$XM]7j]\u0016\u001cF/\u001a9TSj,GcB!\u0002\u001e\u0006}\u0015\u0011\u0015\u0005\b\u0003\u001bK\u0002\u0019AAH\u0011\u0019\ty&\u0007a\u0001\u000f\"1\u00111U\rA\u0002m\n1\u0001Z5s\u0003!!\u0018m[3Ti\u0016\u0004HcB\u001e\u0002*\u0006-\u0016Q\u0016\u0005\b\u0003\u001bS\u0002\u0019AAH\u0011\u0019\t\u0019K\u0007a\u0001w!1\u0011q\u0016\u000eA\u0002\u0005\u000b\u0001b\u001d;faNK'0Z\u0001\u000ekB$\u0017\r^3ISN$xN]=\u0015\u0019\u0005e\u0013QWA\\\u0003s\u000bY,!0\t\r\u0005E4\u00041\u0001<\u0011\u0019\t)h\u0007a\u0001w!1\u0011\u0011P\u000eA\u0002\u0005Ca!a\u0018\u001c\u0001\u00049\u0005bBA`7\u0001\u0007\u0011qR\u0001\t_2$7\u000b^1uK\u0006!\u0002K]8kK\u000e$X\rZ)vCNLg*Z<u_:\u0004\"\u0001O\u000f\u0014\tu\t9-\u0014\t\u0004\u0005\u0006%\u0017bAAf\u0007\n1\u0011I\\=SK\u001a$\"!a1\u0003'E+\u0018\r\u001a:bi&\u001c7+\u001e2qe>\u0014G.Z7\u0014\t}\t9mR\u0001\u0003M.\f!\u0001_6\u0002\u0005\u001d\\\u0017!\u0001\"\u0015\u0015\u0005u\u0017\u0011]Ar\u0003K\f9\u000fE\u0002\u0002`~i\u0011!\b\u0005\u0007\u0003'$\u0003\u0019A!\t\r\u0005UG\u00051\u0001<\u0011\u0019\t9\u000e\na\u0001w!9\u0011\u0011\u001c\u0013A\u0002\u0005=\u0013!C2bY\u000e,H.\u0019;f)\u0011\tI'!<\t\r\u0005\u0005U\u00051\u0001<\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u001f\u0016\u0004\u0003\u0006U8FAA|!\u0011\tIPa\u0001\u000e\u0005\u0005m(\u0002BA\u007f\u0003\u007f\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t\u00051)\u0001\u0006b]:|G/\u0019;j_:LAA!\u0002\u0002|\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011YAK\u0002l\u0003k\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTC\u0001B\tU\r\t\u0018Q_\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%m\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012BTC\u0001B\u0010U\rq\u0018Q_\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001d\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\u001d\u0002\u0003\u0002B\u0015\u0005gi!Aa\u000b\u000b\t\t5\"qF\u0001\u0005Y\u0006twM\u0003\u0002\u00032\u0005!!.\u0019<b\u0013\u0011\u0011)Da\u000b\u0003\r=\u0013'.Z2u\u0001"
)
public class ProjectedQuasiNewton extends FirstOrderMinimizer implements Projecting {
   private final SpectralProjectedGradient innerOptimizer;
   private final int m;
   private final boolean initFeas;
   private final boolean testOpt;
   private final int maxSrchIt;
   private final double gamma;
   private final Function1 projection;
   private final MutableInnerProductModule space;

   public static boolean $lessinit$greater$default$9() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$9();
   }

   public static Function1 $lessinit$greater$default$8() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$8();
   }

   public static double $lessinit$greater$default$7() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$7();
   }

   public static int $lessinit$greater$default$6() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$2();
   }

   public static double $lessinit$greater$default$1() {
      return ProjectedQuasiNewton$.MODULE$.$lessinit$greater$default$1();
   }

   public Object projectedVector(final Object x, final Object g, final Module vspace) {
      return Projecting.projectedVector$(this, x, g, vspace);
   }

   public SpectralProjectedGradient innerOptimizer() {
      return this.innerOptimizer;
   }

   public int m() {
      return this.m;
   }

   public boolean initFeas() {
      return this.initFeas;
   }

   public boolean testOpt() {
      return this.testOpt;
   }

   public int maxSrchIt() {
      return this.maxSrchIt;
   }

   public double gamma() {
      return this.gamma;
   }

   public Function1 projection() {
      return this.projection;
   }

   public CompactHessian initialHistory(final DiffFunction f, final DenseVector init) {
      return new CompactHessian(this.m());
   }

   public Tuple2 adjust(final DenseVector newX, final DenseVector newGrad, final double newVal) {
      return new Tuple2(BoxesRunTime.boxToDouble(newVal), this.projectedVector(newX, newGrad.unary_$minus(HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseVector$.MODULE$.DV_scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar())), this.space));
   }

   private DenseVector computeGradient(final DenseVector x, final DenseVector g) {
      return (DenseVector)this.projectedVector(x, g.unary_$minus(HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseVector$.MODULE$.DV_scalarOf(), Ring$.MODULE$.ringD(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar())), this.space);
   }

   public DenseVector chooseDescentDirection(final FirstOrderMinimizer.State state, final DiffFunction fn) {
      DenseVector var10000;
      if (state.iter() == 0) {
         var10000 = this.computeGradient((DenseVector)state.x(), (DenseVector)state.grad());
      } else {
         QuadraticSubproblem subprob = new QuadraticSubproblem(state.adjustedValue(), (DenseVector)state.x(), (DenseVector)state.grad(), (CompactHessian)state.history());
         FirstOrderMinimizer.State spgResult = this.innerOptimizer().minimizeAndReturnState(new CachedDiffFunction(subprob, DenseVector$.MODULE$.canCopyDenseVector(.MODULE$.Double())), state.x());
         this.logger().info(() -> {
            Object arg$macro$1 = BoxesRunTime.boxToInteger(state.iter());
            Object arg$macro$2 = BoxesRunTime.boxToInteger(spgResult.iter());
            return scala.collection.StringOps..MODULE$.format$extension("ProjectedQuasiNewton: outerIter %s innerIters %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{arg$macro$1, arg$macro$2}));
         });
         var10000 = (DenseVector)((ImmutableNumericOps)spgResult.x()).$minus(state.x(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      }

      return var10000;
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final DiffFunction f, final DenseVector dir) {
      DenseVector x = (DenseVector)state.x();
      DenseVector grad = (DenseVector)state.grad();
      DiffFunction ff = LineSearch$.MODULE$.functionFromSearchDirection(f, x, dir, this.space);
      BacktrackingLineSearch search = new BacktrackingLineSearch(state.value(), this.maxSrchIt(), state.iter() < 1 ? 0.1 : (double)0.5F, BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$4(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$5(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$6(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$7(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$8(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$9(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$10());
      double alpha = (double)state.iter() == (double)0.0F ? min$.MODULE$.apply$mDDDc$sp((double)1.0F, (double)1.0F / BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(dir, norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))), min$.MODULE$.minImpl2_Double()) : (double)1.0F;
      alpha = search.minimize(ff, alpha);
      if (alpha * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(grad, norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))) < 1.0E-10) {
         throw new StepSizeUnderflow();
      } else {
         return alpha;
      }
   }

   public DenseVector takeStep(final FirstOrderMinimizer.State state, final DenseVector dir, final double stepSize) {
      return (DenseVector)this.projection().apply(((NumericOps)state.x()).$plus(dir.$times(BoxesRunTime.boxToDouble(stepSize), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()));
   }

   public CompactHessian updateHistory(final DenseVector newX, final DenseVector newGrad, final double newVal, final DiffFunction f, final FirstOrderMinimizer.State oldState) {
      DenseVector s = (DenseVector)newX.$minus(oldState.x(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      DenseVector y = (DenseVector)newGrad.$minus(oldState.grad(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      return ((CompactHessian)oldState.history()).updated(y, s);
   }

   public ProjectedQuasiNewton(final FirstOrderMinimizer.ConvergenceCheck convergenceCheck, final SpectralProjectedGradient innerOptimizer, final int m, final boolean initFeas, final boolean testOpt, final int maxSrchIt, final double gamma, final Function1 projection, final MutableInnerProductModule space) {
      super(convergenceCheck, space);
      this.innerOptimizer = innerOptimizer;
      this.m = m;
      this.initFeas = initFeas;
      this.testOpt = testOpt;
      this.maxSrchIt = maxSrchIt;
      this.gamma = gamma;
      this.projection = projection;
      this.space = space;
      Projecting.$init$(this);
   }

   public ProjectedQuasiNewton(final double tolerance, final int m, final boolean initFeas, final boolean testOpt, final int maxIter, final int maxSrchIt, final double gamma, final Function1 projection, final boolean relativeTolerance, final MutableInnerProductModule space) {
      FirstOrderMinimizer.ConvergenceCheck var10001 = FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck(maxIter, tolerance, relativeTolerance, FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$4(), space);
      int x$2 = 50;
      int x$3 = 5;
      boolean x$4 = true;
      int x$5 = 10;
      double x$7 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$3();
      double x$8 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$5();
      double x$9 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$6();
      boolean x$10 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$10();
      int x$11 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$11();
      int x$12 = SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$12();
      this(var10001, new SpectralProjectedGradient(projection, tolerance, x$7, 10, x$8, x$9, 5, 50, true, x$10, x$11, x$12, DenseVector$.MODULE$.space_Double()), m, initFeas, testOpt, maxSrchIt, gamma, projection, space);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class QuadraticSubproblem implements DiffFunction {
      private final double fk;
      private final DenseVector xk;
      private final DenseVector gk;
      private final CompactHessian B;

      public DiffFunction repr() {
         return DiffFunction.repr$(this);
      }

      public DiffFunction cached(final CanCopy copy) {
         return DiffFunction.cached$(this, copy);
      }

      public DiffFunction throughLens(final Isomorphism l) {
         return DiffFunction.throughLens$(this, l);
      }

      public Object gradientAt(final Object x) {
         return StochasticDiffFunction.gradientAt$(this, x);
      }

      public double valueAt(final Object x) {
         return StochasticDiffFunction.valueAt$(this, x);
      }

      public final double apply(final Object x) {
         return StochasticDiffFunction.apply$(this, x);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public Tuple2 calculate(final DenseVector x) {
         DenseVector d = (DenseVector)x.$minus(this.xk, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
         DenseVector Bd = this.B.$times(d);
         double f = this.fk + BoxesRunTime.unboxToDouble(d.dot(this.gk, HasOps$.MODULE$.canDotD())) + (double)0.5F * BoxesRunTime.unboxToDouble(d.dot(Bd, HasOps$.MODULE$.canDotD()));
         DenseVector g = (DenseVector)this.gk.$plus(Bd, HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double());
         return new Tuple2(BoxesRunTime.boxToDouble(f), g);
      }

      public QuadraticSubproblem(final double fk, final DenseVector xk, final DenseVector gk, final CompactHessian B) {
         this.fk = fk;
         this.xk = xk;
         this.gk = gk;
         this.B = B;
         Function1.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         StochasticDiffFunction.$init$(this);
         DiffFunction.$init$(this);
      }
   }
}
