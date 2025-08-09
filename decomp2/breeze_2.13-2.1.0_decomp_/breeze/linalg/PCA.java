package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.stats.mean$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t3Aa\u0004\t\u0001+!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u001f\u0011!1\u0003A!b\u0001\n\u0003i\u0002\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0010\t\u000b!\u0002A\u0011A\u0015\t\u00115\u0002\u0001R1A\u0005\u00029B\u0001B\r\u0001\t\u0006\u0004%\ta\r\u0005\u000bo\u0001\u0001\n\u0011cb!\n\u0013A\u0004\u0002\u0003\u001f\u0001\u0011\u000b\u0007I\u0011A\u001a\t\u0011u\u0002\u0001R1A\u0005\u0002uA\u0001B\u0010\u0001\t\u0006\u0004%\ta\r\u0005\t\u007f\u0001A)\u0019!C\u0001g!A\u0001\t\u0001EC\u0002\u0013\u00051\u0007\u0003\u0005B\u0001!\u0015\r\u0011\"\u0001\u001e\u0005\r\u00016)\u0011\u0006\u0003#I\ta\u0001\\5oC2<'\"A\n\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u0005AX#\u0001\u0010\u0011\u0007}\u0001#%D\u0001\u0011\u0013\t\t\u0003CA\u0006EK:\u001cX-T1ue&D\bCA\f$\u0013\t!\u0003D\u0001\u0004E_V\u0014G.Z\u0001\u0003q\u0002\naaY8w[\u0006$\u0018aB2pm6\fG\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007)ZC\u0006\u0005\u0002 \u0001!)A$\u0002a\u0001=!)a%\u0002a\u0001=\u0005!an\u001c2t+\u0005y\u0003CA\f1\u0013\t\t\u0004DA\u0002J]R\faaY3oi\u0016\u0014X#\u0001\u001b\u0011\u0007})$%\u0003\u00027!\tYA)\u001a8tKZ+7\r^8s\u0003\rAH%M\u000b\u0002sA!qC\u000f\u001b\u001f\u0013\tY\u0004D\u0001\u0004UkBdWMM\u0001\fK&<WM\u001c<bYV,7/\u0001\u0005m_\u0006$\u0017N\\4t\u0003\u0011\u0019H-\u001a<\u0002\u000fA\u0014x\u000e\u001d<be\u000691-^7vm\u0006\u0014\u0018AB:d_J,7\u000f"
)
public class PCA {
   private int nobs;
   private DenseVector center;
   private Tuple2 x$1;
   private DenseVector eigenvalues;
   private DenseMatrix loadings;
   private DenseVector sdev;
   private DenseVector propvar;
   private DenseVector cumuvar;
   private DenseMatrix scores;
   private final DenseMatrix x;
   private final DenseMatrix covmat;
   private volatile int bitmap$0;

   public DenseMatrix x() {
      return this.x;
   }

   public DenseMatrix covmat() {
      return this.covmat;
   }

   private int nobs$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.nobs = this.x().rows();
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nobs;
   }

   public int nobs() {
      return (this.bitmap$0 & 1) == 0 ? this.nobs$lzycompute() : this.nobs;
   }

   private DenseVector center$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.center = (DenseVector)((ImmutableNumericOps)mean$.MODULE$.apply(this.x(), Axis._0$.MODULE$, Axis$.MODULE$.collapseUred(HasOps$.MODULE$.handholdCanMapRows_DM(), mean$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues()), HasOps$.MODULE$.canCollapseRows_DM(.MODULE$.Double())))).t(HasOps$.MODULE$.canUntranspose());
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.center;
   }

   public DenseVector center() {
      return (this.bitmap$0 & 2) == 0 ? this.center$lzycompute() : this.center;
   }

   private Tuple2 x$1$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            svd.SVD var3 = (svd.SVD)svd$.MODULE$.apply(this.covmat(), svd.Svd_DM_Impl$.MODULE$);
            if (var3 == null) {
               throw new MatchError(var3);
            }

            DenseVector eigenvalues = (DenseVector)var3.singularValues();
            DenseMatrix loadings = (DenseMatrix)var3.rightVectors();
            Tuple2 var1 = new Tuple2(eigenvalues, loadings);
            this.x$1 = var1;
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.x$1;
   }

   // $FF: synthetic method
   private Tuple2 x$1() {
      return (this.bitmap$0 & 4) == 0 ? this.x$1$lzycompute() : this.x$1;
   }

   private DenseVector eigenvalues$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            this.eigenvalues = (DenseVector)this.x$1()._1();
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.eigenvalues;
   }

   public DenseVector eigenvalues() {
      return (this.bitmap$0 & 8) == 0 ? this.eigenvalues$lzycompute() : this.eigenvalues;
   }

   private DenseMatrix loadings$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.loadings = (DenseMatrix)this.x$1()._2();
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadings;
   }

   public DenseMatrix loadings() {
      return (this.bitmap$0 & 16) == 0 ? this.loadings$lzycompute() : this.loadings;
   }

   private DenseVector sdev$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.sdev = (DenseVector)this.eigenvalues().map$mcD$sp((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.sqrt(x), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()));
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sdev;
   }

   public DenseVector sdev() {
      return (this.bitmap$0 & 32) == 0 ? this.sdev$lzycompute() : this.sdev;
   }

   private DenseVector propvar$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.propvar = (DenseVector)normalize$.MODULE$.apply(this.eigenvalues(), normalize$.MODULE$.normalizeImpl(normalize$.MODULE$.normalizeDoubleImpl(HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.propvar;
   }

   public DenseVector propvar() {
      return (this.bitmap$0 & 64) == 0 ? this.propvar$lzycompute() : this.propvar;
   }

   private DenseVector cumuvar$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            DenseVector var10001 = this.propvar();
            DoubleRef c = DoubleRef.create((double)0.0F);
            this.cumuvar = (DenseVector)var10001.map$mcD$sp((JFunction1.mcDD.sp)(d) -> {
               c.elem += d;
               return c.elem;
            }, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()));
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.cumuvar;
   }

   public DenseVector cumuvar() {
      return (this.bitmap$0 & 128) == 0 ? this.cumuvar$lzycompute() : this.cumuvar;
   }

   private DenseMatrix scores$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.scores = (DenseMatrix)((ImmutableNumericOps)this.loadings().$times(((ImmutableNumericOps)((ImmutableNumericOps)this.x().apply($times$.MODULE$, scala.package..MODULE$.$colon$colon(), Broadcaster$.MODULE$.canBroadcastRows(HasOps$.MODULE$.handholdCanMapCols_DM()))).$minus(this.center(), HasOps$.MODULE$.broadcastOp2_BRows(HasOps$.MODULE$.handholdCanMapCols_DM(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double(), HasOps$.MODULE$.canMapCols_DM(.MODULE$.Double(), Zero$.MODULE$.DoubleZero(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet())))).t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).t(HasOps$.MODULE$.canTranspose_DM());
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.scores;
   }

   public DenseMatrix scores() {
      return (this.bitmap$0 & 256) == 0 ? this.scores$lzycompute() : this.scores;
   }

   public PCA(final DenseMatrix x, final DenseMatrix covmat) {
      this.x = x;
      this.covmat = covmat;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
