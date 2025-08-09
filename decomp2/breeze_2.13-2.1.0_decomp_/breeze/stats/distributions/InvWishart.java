package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.LU$primitive$LU_DM_Impl_Double$;
import breeze.linalg.det$;
import breeze.linalg.diag$;
import breeze.linalg.inv$;
import breeze.linalg.sum$;
import breeze.linalg.trace$;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$log$logIntImpl$;
import breeze.numerics.package$multidigammalog$multidigammalogImplDoubleInt$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef\u0001B\u0014)\u0001>B\u0001\"\u0016\u0001\u0003\u0016\u0004%\tA\u0016\u0005\t/\u0002\u0011\t\u0012)A\u0005\u0001\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005[\u0001\tE\t\u0015!\u0003;\u0011!Y\u0006A!A!\u0002\u0017a\u0006\"B0\u0001\t\u0003\u0001\u0007b\u00024\u0001\u0005\u0004%Ia\u001a\u0005\u0007W\u0002\u0001\u000b\u0011\u00025\t\u000f1\u0004!\u0019!C\u00053\"1Q\u000e\u0001Q\u0001\niBqA\u001c\u0001C\u0002\u0013%q\u000e\u0003\u0004t\u0001\u0001\u0006I\u0001\u001d\u0005\u0006i\u0002!\t!\u0017\u0005\u0006k\u0002!\t!\u0017\u0005\u0006m\u0002!\tA\u0016\u0005\u0006o\u0002!\t!\u0017\u0005\u0006q\u0002!\t!\u001f\u0005\u0006y\u0002!\tA\u0016\u0005\u0006{\u0002!\tA \u0005\t\u007f\u0002\t\t\u0011\"\u0001\u0002\u0002!I\u00111\u0002\u0001\u0012\u0002\u0013\u0005\u0011Q\u0002\u0005\n\u0003G\u0001\u0011\u0013!C\u0001\u0003KA\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0011\u0005u\u0002!!A\u0005\u0002\u001dD\u0011\"a\u0010\u0001\u0003\u0003%\t!!\u0011\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA/\u0001\u0005\u0005I\u0011AA0\u0011%\tI\u0007AA\u0001\n\u0003\nY\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u00111\u000f\u0001\u0002\u0002\u0013\u0005\u0013Q\u000f\u0005\n\u0003o\u0002\u0011\u0011!C!\u0003s:\u0011\"! )\u0003\u0003E\t!a \u0007\u0011\u001dB\u0013\u0011!E\u0001\u0003\u0003CaaX\u0011\u0005\u0002\u00055\u0005\"CA:C\u0005\u0005IQIA;\u0011%\ty)IA\u0001\n\u0003\u000b\t\nC\u0005\u0002\u001c\u0006\n\t\u0011\"!\u0002\u001e\"I\u0011qV\u0011\u0002\u0002\u0013%\u0011\u0011\u0017\u0002\u000b\u0013:4x+[:iCJ$(BA\u0015+\u00035!\u0017n\u001d;sS\n,H/[8og*\u00111\u0006L\u0001\u0006gR\fGo\u001d\u0006\u0002[\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0004\u0001aY\u001ae)\u0013\t\u0003cQj\u0011A\r\u0006\u0002g\u0005)1oY1mC&\u0011QG\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]B$(D\u0001)\u0013\tI\u0004FA\bD_:$\u0018N\\;pkN$\u0015n\u001d;s!\rYd\bQ\u0007\u0002y)\u0011Q\bL\u0001\u0007Y&t\u0017\r\\4\n\u0005}b$a\u0003#f]N,W*\u0019;sSb\u0004\"!M!\n\u0005\t\u0013$A\u0002#pk\ndW\r\u0005\u00038\tjR\u0014BA#)\u0005\u001diu.\\3oiN\u0004\"!M$\n\u0005!\u0013$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0015Js!a\u0013)\u000f\u00051{U\"A'\u000b\u00059s\u0013A\u0002\u001fs_>$h(C\u00014\u0013\t\t&'A\u0004qC\u000e\\\u0017mZ3\n\u0005M#&\u0001D*fe&\fG.\u001b>bE2,'BA)3\u0003\t!g-F\u0001A\u0003\r!g\rI\u0001\u0006g\u000e\fG.Z\u000b\u0002u\u000511oY1mK\u0002\nAA]1oIB\u0011q'X\u0005\u0003=\"\u0012\u0011BU1oI\n\u000b7/[:\u0002\rqJg.\u001b;?)\r\tG-\u001a\u000b\u0003E\u000e\u0004\"a\u000e\u0001\t\u000bm3\u00019\u0001/\t\u000bU3\u0001\u0019\u0001!\t\u000ba3\u0001\u0019\u0001\u001e\u0002\u0003A,\u0012\u0001\u001b\t\u0003c%L!A\u001b\u001a\u0003\u0007%sG/\u0001\u0002qA\u0005a\u0011N\u001c<feN,7kY1mK\u0006i\u0011N\u001c<feN,7kY1mK\u0002\n\u0011a^\u000b\u0002aB\u0011q']\u0005\u0003e\"\u0012qaV5tQ\u0006\u0014H/\u0001\u0002xA\u0005!Q.Z1o\u0003!1\u0018M]5b]\u000e,\u0017aB3oiJ|\u0007/_\u0001\u0005[>$W-\u0001\nv]:|'/\\1mSj,G\rT8h!\u00124GC\u0001!{\u0011\u0015Y\u0018\u00031\u0001;\u0003\u0005A\u0018!\u00047pO:{'/\\1mSj,'/\u0001\u0003ee\u0006<H#\u0001\u001e\u0002\t\r|\u0007/\u001f\u000b\u0007\u0003\u0007\t9!!\u0003\u0015\u0007\t\f)\u0001C\u0003\\)\u0001\u000fA\fC\u0004V)A\u0005\t\u0019\u0001!\t\u000fa#\u0002\u0013!a\u0001u\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\bU\r\u0001\u0015\u0011C\u0016\u0003\u0003'\u0001B!!\u0006\u0002 5\u0011\u0011q\u0003\u0006\u0005\u00033\tY\"A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0004\u001a\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\"\u0005]!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u0014U\rQ\u0014\u0011C\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0002\u0003BA\u0018\u0003si!!!\r\u000b\t\u0005M\u0012QG\u0001\u0005Y\u0006twM\u0003\u0002\u00028\u0005!!.\u0019<b\u0013\u0011\tY$!\r\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\u0002JA\u0019\u0011'!\u0012\n\u0007\u0005\u001d#GA\u0002B]fD\u0001\"a\u0013\u001a\u0003\u0003\u0005\r\u0001[\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0003CBA*\u00033\n\u0019%\u0004\u0002\u0002V)\u0019\u0011q\u000b\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\\\u0005U#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0019\u0002hA\u0019\u0011'a\u0019\n\u0007\u0005\u0015$GA\u0004C_>dW-\u00198\t\u0013\u0005-3$!AA\u0002\u0005\r\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\f\u0002n!A\u00111\n\u000f\u0002\u0002\u0003\u0007\u0001.\u0001\u0005iCND7i\u001c3f)\u0005A\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u00055\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002b\u0005m\u0004\"CA&?\u0005\u0005\t\u0019AA\"\u0003)IeN^,jg\"\f'\u000f\u001e\t\u0003o\u0005\u001aB!\t\u0019\u0002\u0004B!\u0011QQAF\u001b\t\t9I\u0003\u0003\u0002\n\u0006U\u0012AA5p\u0013\r\u0019\u0016q\u0011\u000b\u0003\u0003\u007f\nQ!\u00199qYf$b!a%\u0002\u0018\u0006eEc\u00012\u0002\u0016\")1\f\na\u00029\")Q\u000b\na\u0001\u0001\")\u0001\f\na\u0001u\u00059QO\\1qa2LH\u0003BAP\u0003W\u0003R!MAQ\u0003KK1!a)3\u0005\u0019y\u0005\u000f^5p]B)\u0011'a*Au%\u0019\u0011\u0011\u0016\u001a\u0003\rQ+\b\u000f\\33\u0011!\ti+JA\u0001\u0002\u0004\u0011\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0017\t\u0005\u0003_\t),\u0003\u0003\u00028\u0006E\"AB(cU\u0016\u001cG\u000f"
)
public class InvWishart implements ContinuousDistr, Moments, Product {
   private final double df;
   private final DenseMatrix scale;
   private final int p;
   private final DenseMatrix inverseScale;
   private final Wishart w;
   private double normalizer;
   private volatile boolean bitmap$0;

   public static Option unapply(final InvWishart x$0) {
      return InvWishart$.MODULE$.unapply(x$0);
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

   private int p() {
      return this.p;
   }

   private DenseMatrix inverseScale() {
      return this.inverseScale;
   }

   private Wishart w() {
      return this.w;
   }

   public DenseMatrix mean() {
      .MODULE$.require(this.df() > (double)(this.p() + 1));
      return (DenseMatrix)this.scale().$div$colon$div(BoxesRunTime.boxToDouble(this.df() - (double)this.p() - (double)1), HasOps$.MODULE$.op_DM_S_Double_OpDiv());
   }

   public DenseMatrix variance() {
      .MODULE$.require(this.df() > (double)(this.p() + 3));
      DenseMatrix a = (DenseMatrix)((ImmutableNumericOps)this.scale().$times$colon$times(this.scale(), HasOps$.MODULE$.op_DM_DM_Double_OpMulScalar())).$times$colon$times(BoxesRunTime.boxToDouble(this.df() - (double)this.p() + (double)1), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
      DenseMatrix d = ((DenseVector)diag$.MODULE$.apply(this.scale(), diag$.MODULE$.diagDMDVImpl())).toDenseMatrix$mcD$sp();
      DenseMatrix b = (DenseMatrix)((ImmutableNumericOps)((ImmutableNumericOps)d.t(HasOps$.MODULE$.canTranspose_DM())).$times(d, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$times$colon$times(BoxesRunTime.boxToDouble(this.df() - (double)this.p() - (double)1), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
      return (DenseMatrix)((ImmutableNumericOps)a.$plus(b, HasOps$.MODULE$.op_DM_DM_Double_OpAdd())).$div$colon$div(BoxesRunTime.boxToDouble((this.df() - (double)this.p()) * (this.df() - (double)this.p() - (double)1) * (this.df() - (double)this.p() - (double)1) * (this.df() - (double)this.p() - (double)3)), HasOps$.MODULE$.op_DM_S_Double_OpDiv());
   }

   public double entropy() {
      return Double.NaN;
   }

   public DenseMatrix mode() {
      return (DenseMatrix)this.scale().$div$colon$div(BoxesRunTime.boxToDouble(this.df() - (double)this.p() - (double)1), HasOps$.MODULE$.op_DM_S_Double_OpDiv());
   }

   public double unnormalizedLogPdf(final DenseMatrix x) {
      return -package.log$.MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(det$.MODULE$.apply(x, det$.MODULE$.canDetUsingLU(LU$primitive$LU_DM_Impl_Double$.MODULE$))), package$log$logDoubleImpl$.MODULE$) * (double)0.5F * (this.df() + (double)this.p() + (double)1) - (double)0.5F * BoxesRunTime.unboxToDouble(trace$.MODULE$.apply(this.scale().$times(inv$.MODULE$.apply(x, inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$)), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), trace$.MODULE$.impl_trace_using_diag_and_sum(diag$.MODULE$.diagDMDVImpl(), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()))));
   }

   public double logNormalizer() {
      return -package.log$.MODULE$.apply$mIDc$sp(2, package$log$logIntImpl$.MODULE$) * (double)0.5F * this.df() * (double)this.p() - package.multidigammalog$.MODULE$.apply$mDIDc$sp((double)0.5F * this.df(), this.p(), package$multidigammalog$multidigammalogImplDoubleInt$.MODULE$) - package.log$.MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(det$.MODULE$.apply(this.scale(), det$.MODULE$.canDetUsingLU(LU$primitive$LU_DM_Impl_Double$.MODULE$))), package$log$logDoubleImpl$.MODULE$) * (double)0.5F * this.df();
   }

   public DenseMatrix draw() {
      return (DenseMatrix)inv$.MODULE$.apply(this.w().draw(), inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$));
   }

   public InvWishart copy(final double df, final DenseMatrix scale, final RandBasis rand) {
      return new InvWishart(df, scale, rand);
   }

   public double copy$default$1() {
      return this.df();
   }

   public DenseMatrix copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "InvWishart";
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
      return x$1 instanceof InvWishart;
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
            if (x$1 instanceof InvWishart) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  InvWishart var4 = (InvWishart)x$1;
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

   public InvWishart(final double df, final DenseMatrix scale, final RandBasis rand) {
      this.df = df;
      this.scale = scale;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.p = scale.rows();
      .MODULE$.require(scale.rows() == scale.cols(), () -> "The scale matrix must be square");
      this.inverseScale = (DenseMatrix)inv$.MODULE$.apply(scale, inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$));
      this.w = new Wishart(df, this.inverseScale(), rand);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
