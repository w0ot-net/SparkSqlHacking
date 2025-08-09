package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.LU$primitive$LU_DM_Impl_Double$;
import breeze.linalg.NumericOps;
import breeze.linalg.cholesky;
import breeze.linalg.cholesky$;
import breeze.linalg.det$;
import breeze.linalg.diag$;
import breeze.linalg.inv$;
import breeze.linalg.mpow;
import breeze.linalg.mpow$;
import breeze.linalg.sum$;
import breeze.linalg.trace$;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$multidigamma$multidigammaImplDoubleInt$;
import breeze.numerics.package$multidigammalog$multidigammalogImplDoubleInt$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef\u0001B\u0014)\u0001>B\u0001\"\u0016\u0001\u0003\u0016\u0004%\tA\u0016\u0005\t/\u0002\u0011\t\u0012)A\u0005\u0001\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005[\u0001\tE\t\u0015!\u0003;\u0011!Y\u0006A!A!\u0002\u0017a\u0006\"B0\u0001\t\u0003\u0001\u0007b\u00024\u0001\u0005\u0004%Ia\u001a\u0005\u0007W\u0002\u0001\u000b\u0011\u00025\t\u000f1\u0004!\u0019!C\u00053\"1Q\u000e\u0001Q\u0001\niBqA\u001c\u0001C\u0002\u0013%\u0011\f\u0003\u0004p\u0001\u0001\u0006IA\u000f\u0005\u0006a\u0002!\t!\u001d\u0005\u0006i\u0002!\tA\u0016\u0005\u0006k\u0002!\t!\u0017\u0005\u0006m\u0002!\t!\u0017\u0005\u0006o\u0002!\tA\u0016\u0005\u0006q\u0002!\t!\u0017\u0005\u0006s\u0002!\tA\u001f\u0005\bw\u0002\t\t\u0011\"\u0001}\u0011%\t\u0019\u0001AI\u0001\n\u0003\t)\u0001C\u0005\u0002\u001c\u0001\t\n\u0011\"\u0001\u0002\u001e!I\u0011\u0011\u0005\u0001\u0002\u0002\u0013\u0005\u00131\u0005\u0005\t\u0003k\u0001\u0011\u0011!C\u0001O\"I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u000b\u0002\u0011\u0011!C!\u0003\u000fB\u0011\"!\u0016\u0001\u0003\u0003%\t!a\u0016\t\u0013\u0005\u0005\u0004!!A\u0005B\u0005\r\u0004\"CA4\u0001\u0005\u0005I\u0011IA5\u0011%\tY\u0007AA\u0001\n\u0003\ni\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r\u001dI\u0011Q\u000f\u0015\u0002\u0002#\u0005\u0011q\u000f\u0004\tO!\n\t\u0011#\u0001\u0002z!1q,\tC\u0001\u0003\u000bC\u0011\"a\u001b\"\u0003\u0003%)%!\u001c\t\u0013\u0005\u001d\u0015%!A\u0005\u0002\u0006%\u0005\"CAJC\u0005\u0005I\u0011QAK\u0011%\t9+IA\u0001\n\u0013\tIKA\u0004XSND\u0017M\u001d;\u000b\u0005%R\u0013!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002,Y\u0005)1\u000f^1ug*\tQ&\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0019\u0001\u0001GN\"G\u0013B\u0011\u0011\u0007N\u0007\u0002e)\t1'A\u0003tG\u0006d\u0017-\u0003\u00026e\t1\u0011I\\=SK\u001a\u00042a\u000e\u001d;\u001b\u0005A\u0013BA\u001d)\u0005=\u0019uN\u001c;j]V|Wo\u001d#jgR\u0014\bcA\u001e?\u00016\tAH\u0003\u0002>Y\u00051A.\u001b8bY\u001eL!a\u0010\u001f\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003c\u0005K!A\u0011\u001a\u0003\r\u0011{WO\u00197f!\u00119DI\u000f\u001e\n\u0005\u0015C#aB'p[\u0016tGo\u001d\t\u0003c\u001dK!\u0001\u0013\u001a\u0003\u000fA\u0013x\u000eZ;diB\u0011!J\u0015\b\u0003\u0017Bs!\u0001T(\u000e\u00035S!A\u0014\u0018\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0014BA)3\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0015+\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005E\u0013\u0014A\u00013g+\u0005\u0001\u0015a\u00013gA\u0005)1oY1mKV\t!(\u0001\u0004tG\u0006dW\rI\u0001\ne\u0006tGMQ1tSN\u0004\"aN/\n\u0005yC#!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q\u0019\u0011\rZ3\u0015\u0005\t\u001c\u0007CA\u001c\u0001\u0011\u0015Yf\u0001q\u0001]\u0011\u0015)f\u00011\u0001A\u0011\u0015Af\u00011\u0001;\u0003\u0011!\u0017.\\:\u0016\u0003!\u0004\"!M5\n\u0005)\u0014$aA%oi\u0006)A-[7tA\u0005A\u0011N\u001c<TG\u0006dW-A\u0005j]Z\u001c6-\u00197fA\u0005!1\r[8m\u0003\u0015\u0019\u0007n\u001c7!\u0003I)hN\\8s[\u0006d\u0017N_3e\u0019><\u0007\u000b\u001a4\u0015\u0005\u0001\u0013\b\"B:\u000e\u0001\u0004Q\u0014!\u0001=\u0002\u001b1|wMT8s[\u0006d\u0017N_3s\u0003\u0011iW-\u00198\u0002\u0011Y\f'/[1oG\u0016\fq!\u001a8ue>\u0004\u00180\u0001\u0003n_\u0012,\u0017\u0001\u00023sC^$\u0012AO\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003~\u007f\u0006\u0005AC\u00012\u007f\u0011\u0015YF\u0003q\u0001]\u0011\u001d)F\u0003%AA\u0002\u0001Cq\u0001\u0017\u000b\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u001d!f\u0001!\u0002\n-\u0012\u00111\u0002\t\u0005\u0003\u001b\t9\"\u0004\u0002\u0002\u0010)!\u0011\u0011CA\n\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0016I\n!\"\u00198o_R\fG/[8o\u0013\u0011\tI\"a\u0004\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005}!f\u0001\u001e\u0002\n\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\n\u0011\t\u0005\u001d\u0012\u0011G\u0007\u0003\u0003SQA!a\u000b\u0002.\u0005!A.\u00198h\u0015\t\ty#\u0001\u0003kCZ\f\u0017\u0002BA\u001a\u0003S\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003w\t\t\u0005E\u00022\u0003{I1!a\u00103\u0005\r\te.\u001f\u0005\t\u0003\u0007J\u0012\u0011!a\u0001Q\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0013\u0011\r\u0005-\u0013\u0011KA\u001e\u001b\t\tiEC\u0002\u0002PI\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019&!\u0014\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00033\ny\u0006E\u00022\u00037J1!!\u00183\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u0011\u001c\u0003\u0003\u0005\r!a\u000f\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003K\t)\u0007\u0003\u0005\u0002Dq\t\t\u00111\u0001i\u0003!A\u0017m\u001d5D_\u0012,G#\u00015\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\n\u0002\r\u0015\fX/\u00197t)\u0011\tI&a\u001d\t\u0013\u0005\rs$!AA\u0002\u0005m\u0012aB,jg\"\f'\u000f\u001e\t\u0003o\u0005\u001aB!\t\u0019\u0002|A!\u0011QPAB\u001b\t\tyH\u0003\u0003\u0002\u0002\u00065\u0012AA5p\u0013\r\u0019\u0016q\u0010\u000b\u0003\u0003o\nQ!\u00199qYf$b!a#\u0002\u0010\u0006EEc\u00012\u0002\u000e\")1\f\na\u00029\")Q\u000b\na\u0001\u0001\")\u0001\f\na\u0001u\u00059QO\\1qa2LH\u0003BAL\u0003G\u0003R!MAM\u0003;K1!a'3\u0005\u0019y\u0005\u000f^5p]B)\u0011'a(Au%\u0019\u0011\u0011\u0015\u001a\u0003\rQ+\b\u000f\\33\u0011!\t)+JA\u0001\u0002\u0004\u0011\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0016\t\u0005\u0003O\ti+\u0003\u0003\u00020\u0006%\"AB(cU\u0016\u001cG\u000f"
)
public class Wishart implements ContinuousDistr, Moments, Product {
   private final double df;
   private final DenseMatrix scale;
   private final RandBasis randBasis;
   private final int dims;
   private final DenseMatrix invScale;
   private final DenseMatrix chol;
   private double normalizer;
   private volatile boolean bitmap$0;

   public static Option unapply(final Wishart x$0) {
      return Wishart$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double pdf(final Object x) {
      return ContinuousDistr.pdf$(this, x);
   }

   public double logPdf(final Object x) {
      return ContinuousDistr.logPdf$(this, x);
   }

   public double unnormalizedPdf(final Object x) {
      return ContinuousDistr.unnormalizedPdf$(this, x);
   }

   public double apply(final Object x) {
      return ContinuousDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return ContinuousDistr.logApply$(this, x);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return !this.bitmap$0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public double df() {
      return this.df;
   }

   public DenseMatrix scale() {
      return this.scale;
   }

   private int dims() {
      return this.dims;
   }

   private DenseMatrix invScale() {
      return this.invScale;
   }

   private DenseMatrix chol() {
      return this.chol;
   }

   public double unnormalizedLogPdf(final DenseMatrix x) {
      return .MODULE$.log(BoxesRunTime.unboxToDouble(det$.MODULE$.apply(x, det$.MODULE$.canDetUsingLU(LU$primitive$LU_DM_Impl_Double$.MODULE$)))) * (double)0.5F * (this.df() - (double)this.dims() - (double)1) - (double)0.5F * BoxesRunTime.unboxToDouble(trace$.MODULE$.apply(this.invScale().$times(x, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), trace$.MODULE$.impl_trace_using_diag_and_sum(diag$.MODULE$.diagDMDVImpl(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))));
   }

   public double logNormalizer() {
      return .MODULE$.log((double)2.0F) * (double)this.dims() * (double)0.5F * this.df() + .MODULE$.log(BoxesRunTime.unboxToDouble(det$.MODULE$.apply(this.scale(), det$.MODULE$.canDetUsingLU(LU$primitive$LU_DM_Impl_Double$.MODULE$)))) * (double)0.5F * this.df() + package.multidigammalog$.MODULE$.apply$mDIDc$sp((double)0.5F * this.df(), this.dims(), package$multidigammalog$multidigammalogImplDoubleInt$.MODULE$);
   }

   public DenseMatrix mean() {
      return (DenseMatrix)this.scale().$times$colon$times(BoxesRunTime.boxToDouble(this.df()), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
   }

   public DenseMatrix variance() {
      DenseMatrix t = ((DenseVector)diag$.MODULE$.apply(this.scale(), diag$.MODULE$.diagDMDVImpl())).toDenseMatrix$mcD$sp();
      return (DenseMatrix)((ImmutableNumericOps)((NumericOps)mpow$.MODULE$.apply(this.scale(), BoxesRunTime.boxToInteger(2), mpow.implDM_Double_Int$.MODULE$)).$plus(t.$times(t.t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), HasOps$.MODULE$.op_DM_DM_Double_OpAdd())).$times$colon$times(BoxesRunTime.boxToDouble(this.df()), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
   }

   public double entropy() {
      double elnx = package.multidigamma$.MODULE$.apply$mDIDc$sp(this.df() / (double)2, this.dims(), package$multidigamma$multidigammaImplDoubleInt$.MODULE$) + (double)this.dims() * .MODULE$.log((double)2.0F) + .MODULE$.log(BoxesRunTime.unboxToDouble(det$.MODULE$.apply(this.scale(), det$.MODULE$.canDetUsingLU(LU$primitive$LU_DM_Impl_Double$.MODULE$))));
      return -this.logNormalizer() - (this.df() - (double)this.dims() - (double)1) / (double)2 * elnx + this.df() * (double)this.dims() / (double)2;
   }

   public DenseMatrix mode() {
      scala.Predef..MODULE$.require(this.df() >= (double)(this.dims() + 1));
      return (DenseMatrix)this.scale().$times$colon$times(BoxesRunTime.boxToDouble(this.df() - (double)this.dims() - (double)1), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
   }

   public DenseMatrix draw() {
      DenseMatrix a = DenseMatrix$.MODULE$.zeros$mDc$sp(this.dims(), this.dims(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      int index$macro$7 = 0;

      for(int limit$macro$9 = this.dims(); index$macro$7 < limit$macro$9; ++index$macro$7) {
         a.update$mcD$sp(index$macro$7, index$macro$7, .MODULE$.sqrt((new ChiSquared(this.df() - (double)index$macro$7, this.randBasis)).draw$mcD$sp()));
         int index$macro$2 = 0;

         for(int limit$macro$4 = index$macro$7; index$macro$2 < limit$macro$4; ++index$macro$2) {
            a.update$mcD$sp(index$macro$7, index$macro$2, this.randBasis.gaussian().draw$mcD$sp());
         }
      }

      DenseMatrix sample = (DenseMatrix)this.chol().$times(a, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      sample = (DenseMatrix)sample.$times(sample.t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      return sample;
   }

   public Wishart copy(final double df, final DenseMatrix scale, final RandBasis randBasis) {
      return new Wishart(df, scale, randBasis);
   }

   public double copy$default$1() {
      return this.df();
   }

   public DenseMatrix copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "Wishart";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.df());
            break;
         case 1:
            var10000 = this.scale();
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
      return x$1 instanceof Wishart;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "df";
            break;
         case 1:
            var10000 = "scale";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.df()));
      var1 = Statics.mix(var1, Statics.anyHash(this.scale()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof Wishart) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  Wishart var4 = (Wishart)x$1;
                  if (this.df() == var4.df()) {
                     label36: {
                        DenseMatrix var10000 = this.scale();
                        DenseMatrix var5 = var4.scale();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Wishart(final double df, final DenseMatrix scale, final RandBasis randBasis) {
      this.df = df;
      this.scale = scale;
      this.randBasis = randBasis;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.dims = scale.rows();
      scala.Predef..MODULE$.require(this.dims() == scale.cols(), () -> "Scale must be a square matrix");
      scala.Predef..MODULE$.require(df > (double)(this.dims() - 1), () -> "df must be greater than one less than the dimensionality");
      this.invScale = (DenseMatrix)inv$.MODULE$.apply(scale, inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$));
      this.chol = (DenseMatrix)cholesky$.MODULE$.apply(scale, cholesky.ImplCholesky_DM$.MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
