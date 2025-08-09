package breeze.optimize.proximal;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.optimize.DiffFunction;
import breeze.optimize.StochasticDiffFunction;
import breeze.storage.Zero$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]u!B\u000f\u001f\u0011\u0003)c!B\u0014\u001f\u0011\u0003A\u0003\"B\u0018\u0002\t\u0003\u0001d\u0001B\u0019\u0002\u0001JB\u0001bT\u0002\u0003\u0016\u0004%\t\u0001\u0015\u0005\t)\u000e\u0011\t\u0012)A\u0005#\"AQk\u0001BK\u0002\u0013\u0005a\u000b\u0003\u0005X\u0007\tE\t\u0015!\u00038\u0011\u0015y3\u0001\"\u0001Y\u0011\u0015i6\u0001\"\u0001_\u0011\u001d!7!!A\u0005\u0002\u0015Dq\u0001[\u0002\u0012\u0002\u0013\u0005\u0011\u000eC\u0004u\u0007E\u0005I\u0011A;\t\u000f]\u001c\u0011\u0011!C!q\"I\u00111A\u0002\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001b\u0019\u0011\u0011!C\u0001\u0003\u001fA\u0011\"a\u0007\u0004\u0003\u0003%\t%!\b\t\u0013\u0005-2!!A\u0005\u0002\u00055\u0002\"CA\u001c\u0007\u0005\u0005I\u0011IA\u001d\u0011%\tidAA\u0001\n\u0003\ny\u0004C\u0005\u0002B\r\t\t\u0011\"\u0011\u0002D\u001dI\u0011qI\u0001\u0002\u0002#\u0005\u0011\u0011\n\u0004\tc\u0005\t\t\u0011#\u0001\u0002L!1qF\u0006C\u0001\u0003GB\u0011\"!\u001a\u0017\u0003\u0003%)%a\u001a\t\u0013\u0005%d#!A\u0005\u0002\u0006-\u0004\"CA9-\u0005\u0005I\u0011QA:\u0011%\t\tIFA\u0001\n\u0013\t\u0019\tC\u0004\u0002j\u0005!\t!a#\u0002\u001f1Kg.Z1s\u000f\u0016tWM]1u_JT!a\b\u0011\u0002\u0011A\u0014x\u000e_5nC2T!!\t\u0012\u0002\u0011=\u0004H/[7ju\u0016T\u0011aI\u0001\u0007EJ,WM_3\u0004\u0001A\u0011a%A\u0007\u0002=\tyA*\u001b8fCJ<UM\\3sCR|'o\u0005\u0002\u0002SA\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u0013\u0003\t\r{7\u000f^\n\u0006\u0007%\u001a\u0004i\u0011\t\u0004iU:T\"\u0001\u0011\n\u0005Y\u0002#\u0001\u0004#jM\u001a4UO\\2uS>t\u0007c\u0001\u001d<{5\t\u0011H\u0003\u0002;E\u00051A.\u001b8bY\u001eL!\u0001P\u001d\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003UyJ!aP\u0016\u0003\r\u0011{WO\u00197f!\tQ\u0013)\u0003\u0002CW\t9\u0001K]8ek\u000e$\bC\u0001#M\u001d\t)%J\u0004\u0002G\u00136\tqI\u0003\u0002II\u00051AH]8pizJ\u0011\u0001L\u0005\u0003\u0017.\nq\u0001]1dW\u0006<W-\u0003\u0002N\u001d\na1+\u001a:jC2L'0\u00192mK*\u00111jK\u0001\u0005I\u0006$\u0018-F\u0001R!\rA$+P\u0005\u0003'f\u00121\u0002R3og\u0016l\u0015\r\u001e:jq\u0006)A-\u0019;bA\u00051A.\u00192fYN,\u0012aN\u0001\bY\u0006\u0014W\r\\:!)\rI6\f\u0018\t\u00035\u000ei\u0011!\u0001\u0005\u0006\u001f\"\u0001\r!\u0015\u0005\u0006+\"\u0001\raN\u0001\nG\u0006d7-\u001e7bi\u0016$\"a\u00182\u0011\t)\u0002WhN\u0005\u0003C.\u0012a\u0001V;qY\u0016\u0014\u0004\"B2\n\u0001\u00049\u0014!\u0001=\u0002\t\r|\u0007/\u001f\u000b\u00043\u001a<\u0007bB(\u000b!\u0003\u0005\r!\u0015\u0005\b+*\u0001\n\u00111\u00018\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001b\u0016\u0003#.\\\u0013\u0001\u001c\t\u0003[Jl\u0011A\u001c\u0006\u0003_B\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005E\\\u0013AC1o]>$\u0018\r^5p]&\u00111O\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002m*\u0012qg[\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003e\u0004\"A_@\u000e\u0003mT!\u0001`?\u0002\t1\fgn\u001a\u0006\u0002}\u0006!!.\u0019<b\u0013\r\t\ta\u001f\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0001c\u0001\u0016\u0002\n%\u0019\u00111B\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0011q\u0003\t\u0004U\u0005M\u0011bAA\u000bW\t\u0019\u0011I\\=\t\u0013\u0005eq\"!AA\u0002\u0005\u001d\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002 A1\u0011\u0011EA\u0014\u0003#i!!a\t\u000b\u0007\u0005\u00152&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000b\u0002$\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty#!\u000e\u0011\u0007)\n\t$C\u0002\u00024-\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u001aE\t\t\u00111\u0001\u0002\u0012\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rI\u00181\b\u0005\n\u00033\u0011\u0012\u0011!a\u0001\u0003\u000f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000f\ta!Z9vC2\u001cH\u0003BA\u0018\u0003\u000bB\u0011\"!\u0007\u0015\u0003\u0003\u0005\r!!\u0005\u0002\t\r{7\u000f\u001e\t\u00035Z\u0019RAFA'\u00033\u0002r!a\u0014\u0002VE;\u0014,\u0004\u0002\u0002R)\u0019\u00111K\u0016\u0002\u000fI,h\u000e^5nK&!\u0011qKA)\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u00037\n\t'\u0004\u0002\u0002^)\u0019\u0011qL?\u0002\u0005%|\u0017bA'\u0002^Q\u0011\u0011\u0011J\u0001\ti>\u001cFO]5oOR\t\u00110A\u0003baBd\u0017\u0010F\u0003Z\u0003[\ny\u0007C\u0003P3\u0001\u0007\u0011\u000bC\u0003V3\u0001\u0007q'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005U\u0014Q\u0010\t\u0006U\u0005]\u00141P\u0005\u0004\u0003sZ#AB(qi&|g\u000e\u0005\u0003+AF;\u0004\u0002CA@5\u0005\u0005\t\u0019A-\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0006B\u0019!0a\"\n\u0007\u0005%5P\u0001\u0004PE*,7\r\u001e\u000b\u0005\u0003\u001b\u000b\u0019\n\u0005\u0004+\u0003\u001f\u001b\u0014kN\u0005\u0004\u0003#[#A\u0002+va2,7\u0007C\u0004\u0002\u0016r\u0001\r!a\u0002\u0002\t9$\u0017.\u001c"
)
public final class LinearGenerator {
   public static Tuple3 apply(final int ndim) {
      return LinearGenerator$.MODULE$.apply(ndim);
   }

   public static class Cost implements DiffFunction, Product, Serializable {
      private final DenseMatrix data;
      private final DenseVector labels;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

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

      public DenseMatrix data() {
         return this.data;
      }

      public DenseVector labels() {
         return this.labels;
      }

      public Tuple2 calculate(final DenseVector x) {
         DenseVector cumGradient = DenseVector$.MODULE$.zeros$mDc$sp(x.length(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         double cumLoss = (double)0.0F;

         for(int i = 0; i < this.data().rows(); ++i) {
            DenseVector brzData = (DenseVector)((ImmutableNumericOps)this.data().apply(BoxesRunTime.boxToInteger(i), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow())).t(HasOps$.MODULE$.canUntranspose());
            double diff = BoxesRunTime.unboxToDouble(x.dot(brzData, HasOps$.MODULE$.canDotD())) - this.labels().apply$mcD$sp(i);
            cumGradient.$plus$eq(brzData.$times(BoxesRunTime.boxToDouble((double)2.0F * diff), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix()), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            cumLoss += diff * diff;
         }

         return new Tuple2(BoxesRunTime.boxToDouble(cumLoss), cumGradient);
      }

      public Cost copy(final DenseMatrix data, final DenseVector labels) {
         return new Cost(data, labels);
      }

      public DenseMatrix copy$default$1() {
         return this.data();
      }

      public DenseVector copy$default$2() {
         return this.labels();
      }

      public String productPrefix() {
         return "Cost";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.data();
               break;
            case 1:
               var10000 = this.labels();
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
         return x$1 instanceof Cost;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "data";
               break;
            case 1:
               var10000 = "labels";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof Cost) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        Cost var4 = (Cost)x$1;
                        DenseMatrix var10000 = this.data();
                        DenseMatrix var5 = var4.data();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        DenseVector var7 = this.labels();
                        DenseVector var6 = var4.labels();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var7.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public Cost(final DenseMatrix data, final DenseVector labels) {
         this.data = data;
         this.labels = labels;
         Function1.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         StochasticDiffFunction.$init$(this);
         DiffFunction.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Cost$ extends AbstractFunction2 implements Serializable {
      public static final Cost$ MODULE$ = new Cost$();

      public final String toString() {
         return "Cost";
      }

      public Cost apply(final DenseMatrix data, final DenseVector labels) {
         return new Cost(data, labels);
      }

      public Option unapply(final Cost x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.data(), x$0.labels())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cost$.class);
      }
   }
}
