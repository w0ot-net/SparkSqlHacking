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
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eu!B\u000f\u001f\u0011\u0003)c!B\u0014\u001f\u0011\u0003A\u0003\"B\u0018\u0002\t\u0003\u0001d\u0001B\u0019\u0002\u0001JB\u0001bT\u0002\u0003\u0016\u0004%\t\u0001\u0015\u0005\t)\u000e\u0011\t\u0012)A\u0005#\"AQk\u0001BK\u0002\u0013\u0005a\u000b\u0003\u0005X\u0007\tE\t\u0015!\u00038\u0011\u0015y3\u0001\"\u0001Y\u0011\u0015i6\u0001\"\u0001_\u0011\u001d!7!!A\u0005\u0002\u0015Dq\u0001[\u0002\u0012\u0002\u0013\u0005\u0011\u000eC\u0004u\u0007E\u0005I\u0011A;\t\u000f]\u001c\u0011\u0011!C!q\"I\u00111A\u0002\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001b\u0019\u0011\u0011!C\u0001\u0003\u001fA\u0011\"a\u0007\u0004\u0003\u0003%\t%!\b\t\u0013\u0005-2!!A\u0005\u0002\u00055\u0002\"CA\u001c\u0007\u0005\u0005I\u0011IA\u001d\u0011%\tidAA\u0001\n\u0003\ny\u0004C\u0005\u0002B\r\t\t\u0011\"\u0011\u0002D\u001dI\u0011qI\u0001\u0002\u0002#\u0005\u0011\u0011\n\u0004\tc\u0005\t\t\u0011#\u0001\u0002L!1qF\u0006C\u0001\u0003GB\u0011\"!\u001a\u0017\u0003\u0003%)%a\u001a\t\u0013\u0005%d#!A\u0005\u0002\u0006-\u0004\"CA9-\u0005\u0005I\u0011QA:\u0011%\t\tIFA\u0001\n\u0013\t\u0019\tC\u0004\u0002j\u0005!\t!a#\u0002#1{w-[:uS\u000e<UM\\3sCR|'O\u0003\u0002 A\u0005A\u0001O]8yS6\fGN\u0003\u0002\"E\u0005Aq\u000e\u001d;j[&TXMC\u0001$\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0014\u0002\u001b\u0005q\"!\u0005'pO&\u001cH/[2HK:,'/\u0019;peN\u0011\u0011!\u000b\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005)#\u0001B\"pgR\u001cRaA\u00154\u0001\u000e\u00032\u0001N\u001b8\u001b\u0005\u0001\u0013B\u0001\u001c!\u00051!\u0015N\u001a4Gk:\u001cG/[8o!\rA4(P\u0007\u0002s)\u0011!HI\u0001\u0007Y&t\u0017\r\\4\n\u0005qJ$a\u0003#f]N,g+Z2u_J\u0004\"A\u000b \n\u0005}Z#A\u0002#pk\ndW\r\u0005\u0002+\u0003&\u0011!i\u000b\u0002\b!J|G-^2u!\t!EJ\u0004\u0002F\u0015:\u0011a)S\u0007\u0002\u000f*\u0011\u0001\nJ\u0001\u0007yI|w\u000e\u001e \n\u00031J!aS\u0016\u0002\u000fA\f7m[1hK&\u0011QJ\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0017.\nA\u0001Z1uCV\t\u0011\u000bE\u00029%vJ!aU\u001d\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e_\u0001\u0006I\u0006$\u0018\rI\u0001\u0007Y\u0006\u0014W\r\\:\u0016\u0003]\nq\u0001\\1cK2\u001c\b\u0005F\u0002Z7r\u0003\"AW\u0002\u000e\u0003\u0005AQa\u0014\u0005A\u0002ECQ!\u0016\u0005A\u0002]\n\u0011bY1mGVd\u0017\r^3\u0015\u0005}\u0013\u0007\u0003\u0002\u0016a{]J!!Y\u0016\u0003\rQ+\b\u000f\\33\u0011\u0015\u0019\u0017\u00021\u00018\u0003\u0005A\u0018\u0001B2paf$2!\u00174h\u0011\u001dy%\u0002%AA\u0002ECq!\u0016\u0006\u0011\u0002\u0003\u0007q'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003)T#!U6,\u00031\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\u0013Ut7\r[3dW\u0016$'BA9,\u0003)\tgN\\8uCRLwN\\\u0005\u0003g:\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012A\u001e\u0016\u0003o-\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A=\u0011\u0005i|X\"A>\u000b\u0005ql\u0018\u0001\u00027b]\u001eT\u0011A`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0002m\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0004!\rQ\u0013\u0011B\u0005\u0004\u0003\u0017Y#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\t\u0003/\u00012AKA\n\u0013\r\t)b\u000b\u0002\u0004\u0003:L\b\"CA\r\u001f\u0005\u0005\t\u0019AA\u0004\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0004\t\u0007\u0003C\t9#!\u0005\u000e\u0005\u0005\r\"bAA\u0013W\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u00121\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00020\u0005U\u0002c\u0001\u0016\u00022%\u0019\u00111G\u0016\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011D\t\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002z\u0003wA\u0011\"!\u0007\u0013\u0003\u0003\u0005\r!a\u0002\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0002\u0002\r\u0015\fX/\u00197t)\u0011\ty#!\u0012\t\u0013\u0005eA#!AA\u0002\u0005E\u0011\u0001B\"pgR\u0004\"A\u0017\f\u0014\u000bY\ti%!\u0017\u0011\u000f\u0005=\u0013QK)836\u0011\u0011\u0011\u000b\u0006\u0004\u0003'Z\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003/\n\tFA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0004\u0003?j\u0018AA5p\u0013\ri\u0015Q\f\u000b\u0003\u0003\u0013\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002s\u0006)\u0011\r\u001d9msR)\u0011,!\u001c\u0002p!)q*\u0007a\u0001#\")Q+\u0007a\u0001o\u00059QO\\1qa2LH\u0003BA;\u0003{\u0002RAKA<\u0003wJ1!!\u001f,\u0005\u0019y\u0005\u000f^5p]B!!\u0006Y)8\u0011!\tyHGA\u0001\u0002\u0004I\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0011\t\u0004u\u0006\u001d\u0015bAAEw\n1qJ\u00196fGR$2aMAG\u0011\u001d\ty\t\ba\u0001\u0003\u000f\tAA\u001c3j[\u0002"
)
public final class LogisticGenerator {
   public static DiffFunction apply(final int ndim) {
      return LogisticGenerator$.MODULE$.apply(ndim);
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
            double margin = (double)-1.0F * BoxesRunTime.unboxToDouble(x.dot(brzData, HasOps$.MODULE$.canDotD()));
            double gradientMultiplier = (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(margin)) - this.labels().apply$mcD$sp(i);
            DenseVector gradient = (DenseVector)brzData.$times(BoxesRunTime.boxToDouble(gradientMultiplier), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
            double loss = this.labels().apply$mcD$sp(i) > (double)0 ? scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(margin)) : scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(margin)) - margin;
            cumGradient.$plus$eq(gradient, HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            cumLoss += loss;
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
