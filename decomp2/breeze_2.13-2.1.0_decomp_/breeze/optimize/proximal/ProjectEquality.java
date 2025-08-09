package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.pinv$;
import breeze.linalg.operators.HasOps$;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e\u0001B\u000f\u001f\u0001\u0016B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0003\"A1\n\u0001BK\u0002\u0013\u0005A\n\u0003\u0005Q\u0001\tE\t\u0015!\u0003N\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001d1\u0006A1A\u0005\u0002\u0001Caa\u0016\u0001!\u0002\u0013\t\u0005\"\u0002-\u0001\t\u0003I\u0006bB1\u0001#\u0003%\tA\u0019\u0005\b[\u0002\t\t\u0011\"\u0001o\u0011\u001d\t\b!%A\u0005\u0002IDq\u0001\u001e\u0001\u0012\u0002\u0013\u0005Q\u000fC\u0004x\u0001\u0005\u0005I\u0011\t=\t\u0013\u0005\r\u0001!!A\u0005\u0002\u0005\u0015\u0001\"CA\u0007\u0001\u0005\u0005I\u0011AA\b\u0011%\tY\u0002AA\u0001\n\u0003\ni\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0001\u0002.!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\n\u0003{\u0001\u0011\u0011!C!\u0003\u007fA\u0011\"!\u0011\u0001\u0003\u0003%\t%a\u0011\t\u0013\u0005\u0015\u0003!!A\u0005B\u0005\u001ds!CA&=\u0005\u0005\t\u0012AA'\r!ib$!A\t\u0002\u0005=\u0003BB)\u0018\t\u0003\t9\u0007C\u0005\u0002B]\t\t\u0011\"\u0012\u0002D!I\u0011\u0011N\f\u0002\u0002\u0013\u0005\u00151\u000e\u0005\n\u0003c:\u0012\u0011!CA\u0003gB\u0011\"!\"\u0018\u0003\u0003%I!a\"\u0003\u001fA\u0013xN[3di\u0016\u000bX/\u00197jifT!a\b\u0011\u0002\u0011A\u0014x\u000e_5nC2T!!\t\u0012\u0002\u0011=\u0004H/[7ju\u0016T\u0011aI\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001A\n\u00171gA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!\f\u0018\u000e\u0003yI!a\f\u0010\u0003\u0011A\u0013x\u000e_5nC2\u0004\"aJ\u0019\n\u0005IB#a\u0002)s_\u0012,8\r\u001e\t\u0003iqr!!\u000e\u001e\u000f\u0005YJT\"A\u001c\u000b\u0005a\"\u0013A\u0002\u001fs_>$h(C\u0001*\u0013\tY\u0004&A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e)\u0003\r\tU-]\u000b\u0002\u0003B\u0019!)R$\u000e\u0003\rS!\u0001\u0012\u0012\u0002\r1Lg.\u00197h\u0013\t15IA\u0006EK:\u001cX-T1ue&D\bCA\u0014I\u0013\tI\u0005F\u0001\u0004E_V\u0014G.Z\u0001\u0005\u0003\u0016\f\b%A\u0002cKF,\u0012!\u0014\t\u0004\u0005:;\u0015BA(D\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002\t\t,\u0017\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007M#V\u000b\u0005\u0002.\u0001!)q(\u0002a\u0001\u0003\")1*\u0002a\u0001\u001b\u00061\u0011N\u001c<BKF\fq!\u001b8w\u0003\u0016\f\b%\u0001\u0003qe>DHc\u0001.^?B\u0011qeW\u0005\u00039\"\u0012A!\u00168ji\")a\f\u0003a\u0001\u001b\u0006\t\u0001\u0010C\u0004a\u0011A\u0005\t\u0019A$\u0002\u0007IDw.\u0001\bqe>DH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\rT#a\u00123,\u0003\u0015\u0004\"AZ6\u000e\u0003\u001dT!\u0001[5\u0002\u0013Ut7\r[3dW\u0016$'B\u00016)\u0003)\tgN\\8uCRLwN\\\u0005\u0003Y\u001e\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0011\u0019w\u000e]=\u0015\u0007M{\u0007\u000fC\u0004@\u0015A\u0005\t\u0019A!\t\u000f-S\u0001\u0013!a\u0001\u001b\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A:+\u0005\u0005#\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002m*\u0012Q\nZ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003e\u0004\"A_@\u000e\u0003mT!\u0001`?\u0002\t1\fgn\u001a\u0006\u0002}\u0006!!.\u0019<b\u0013\r\t\ta\u001f\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0001cA\u0014\u0002\n%\u0019\u00111\u0002\u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0011q\u0003\t\u0004O\u0005M\u0011bAA\u000bQ\t\u0019\u0011I\\=\t\u0013\u0005eq\"!AA\u0002\u0005\u001d\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002 A1\u0011\u0011EA\u0014\u0003#i!!a\t\u000b\u0007\u0005\u0015\u0002&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000b\u0002$\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty#!\u000e\u0011\u0007\u001d\n\t$C\u0002\u00024!\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u001aE\t\t\u00111\u0001\u0002\u0012\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rI\u00181\b\u0005\n\u00033\u0011\u0012\u0011!a\u0001\u0003\u000f\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000f\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002s\u00061Q-];bYN$B!a\f\u0002J!I\u0011\u0011D\u000b\u0002\u0002\u0003\u0007\u0011\u0011C\u0001\u0010!J|'.Z2u\u000bF,\u0018\r\\5usB\u0011QfF\n\u0006/\u0005E\u0013Q\f\t\b\u0003'\nI&Q'T\u001b\t\t)FC\u0002\u0002X!\nqA];oi&lW-\u0003\u0003\u0002\\\u0005U#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011qLA3\u001b\t\t\tGC\u0002\u0002du\f!![8\n\u0007u\n\t\u0007\u0006\u0002\u0002N\u0005)\u0011\r\u001d9msR)1+!\u001c\u0002p!)qH\u0007a\u0001\u0003\")1J\u0007a\u0001\u001b\u00069QO\\1qa2LH\u0003BA;\u0003\u0003\u0003RaJA<\u0003wJ1!!\u001f)\u0005\u0019y\u0005\u000f^5p]B)q%! B\u001b&\u0019\u0011q\u0010\u0015\u0003\rQ+\b\u000f\\33\u0011!\t\u0019iGA\u0001\u0002\u0004\u0019\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0012\t\u0004u\u0006-\u0015bAAGw\n1qJ\u00196fGR\u0004"
)
public class ProjectEquality implements Proximal, Product, Serializable {
   private final DenseMatrix Aeq;
   private final DenseVector beq;
   private final DenseMatrix invAeq;

   public static Option unapply(final ProjectEquality x$0) {
      return ProjectEquality$.MODULE$.unapply(x$0);
   }

   public static ProjectEquality apply(final DenseMatrix Aeq, final DenseVector beq) {
      return ProjectEquality$.MODULE$.apply(Aeq, beq);
   }

   public static Function1 tupled() {
      return ProjectEquality$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ProjectEquality$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public DenseMatrix Aeq() {
      return this.Aeq;
   }

   public DenseVector beq() {
      return this.beq;
   }

   public DenseMatrix invAeq() {
      return this.invAeq;
   }

   public void prox(final DenseVector x, final double rho) {
      DenseVector Av = (DenseVector)this.Aeq().$times(x, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
      Av.$minus$eq(this.beq(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
      x.$plus$eq(this.invAeq().$times(Av, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
   }

   public double prox$default$2() {
      return (double)0.0F;
   }

   public ProjectEquality copy(final DenseMatrix Aeq, final DenseVector beq) {
      return new ProjectEquality(Aeq, beq);
   }

   public DenseMatrix copy$default$1() {
      return this.Aeq();
   }

   public DenseVector copy$default$2() {
      return this.beq();
   }

   public String productPrefix() {
      return "ProjectEquality";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.Aeq();
            break;
         case 1:
            var10000 = this.beq();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProjectEquality;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "Aeq";
            break;
         case 1:
            var10000 = "beq";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof ProjectEquality) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ProjectEquality var4 = (ProjectEquality)x$1;
                     DenseMatrix var10000 = this.Aeq();
                     DenseMatrix var5 = var4.Aeq();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     DenseVector var7 = this.beq();
                     DenseVector var6 = var4.beq();
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

   public ProjectEquality(final DenseMatrix Aeq, final DenseVector beq) {
      this.Aeq = Aeq;
      this.beq = beq;
      Proximal.$init$(this);
      Product.$init$(this);
      this.invAeq = (DenseMatrix)pinv$.MODULE$.apply(Aeq, pinv$.MODULE$.pinvFromSVD_Double());
   }
}
