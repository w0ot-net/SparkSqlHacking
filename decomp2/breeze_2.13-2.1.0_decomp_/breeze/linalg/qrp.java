package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.storage.Zero$;
import dev.ludovic.netlib.lapack.LAPACK;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.netlib.util.intW;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUq!\u0002\u0015*\u0011\u0003qc!\u0002\u0019*\u0011\u0003\t\u0004\"\u0002 \u0002\t\u0003yd\u0001\u0002!\u0002\u0001\u0006C\u0001BU\u0002\u0003\u0016\u0004%\ta\u0015\u0005\t?\u000e\u0011\t\u0012)A\u0005)\"A\u0001m\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005b\u0007\tE\t\u0015!\u0003U\u0011!\u00117A!f\u0001\n\u0003\u0019\u0007\u0002C4\u0004\u0005#\u0005\u000b\u0011\u00023\t\u0011!\u001c!Q3A\u0005\u0002%D\u0001\u0002]\u0002\u0003\u0012\u0003\u0006IA\u001b\u0005\u0006}\r!\t!\u001d\u0005\bq\u000e\t\t\u0011\"\u0001z\u0011%\tIaAI\u0001\n\u0003\tY\u0001C\u0005\u0002(\r\t\n\u0011\"\u0001\u0002*!I\u0011qF\u0002\u0012\u0002\u0013\u0005\u0011\u0011\u0007\u0005\n\u0003w\u0019\u0011\u0013!C\u0001\u0003{A\u0011\"a\u0012\u0004\u0003\u0003%\t%!\u0013\t\u0013\u0005m3!!A\u0005\u0002\u0005u\u0003\"CA0\u0007\u0005\u0005I\u0011AA1\u0011%\t9gAA\u0001\n\u0003\nI\u0007C\u0005\u0002x\r\t\t\u0011\"\u0001\u0002z!I\u00111Q\u0002\u0002\u0002\u0013\u0005\u0013Q\u0011\u0005\n\u0003\u0013\u001b\u0011\u0011!C!\u0003\u0017C\u0011\"!$\u0004\u0003\u0003%\t%a$\t\u0013\u0005E5!!A\u0005B\u0005Mu!CAL\u0003\u0005\u0005\t\u0012AAM\r!\u0001\u0015!!A\t\u0002\u0005m\u0005B\u0002 \u001d\t\u0003\t9\u000bC\u0005\u0002\u000er\t\t\u0011\"\u0012\u0002\u0010\"I\u0011\u0011\u0016\u000f\u0002\u0002\u0013\u0005\u00151\u0016\u0005\n\u0003\u0003d\u0012\u0011!CA\u0003\u0007D\u0011\"!9\u001d\u0003\u0003%I!a9\u0006\r\u0005-\u0018\u0001AAw\u000f\u001d\ti0\u0001E\u0002\u0003\u007f4qA!\u0001\u0002\u0011\u0003\u0011\u0019\u0001\u0003\u0004?I\u0011\u0005!Q\u0002\u0005\b\u0003S#C\u0011\u0001B\b\u0011%\t\t\u000fJA\u0001\n\u0013\t\u0019/A\u0002reBT!AK\u0016\u0002\r1Lg.\u00197h\u0015\u0005a\u0013A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005=\nQ\"A\u0015\u0003\u0007E\u0014\boE\u0002\u0002ea\u0002\"a\r\u001c\u000e\u0003QR\u0011!N\u0001\u0006g\u000e\fG.Y\u0005\u0003oQ\u0012a!\u00118z%\u00164\u0007CA\u001d=\u001b\u0005Q$BA\u001e,\u0003\u001d9WM\\3sS\u000eL!!\u0010\u001e\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005q#aA)S!V\u0019!IV3\u0014\t\r\u00114I\u0012\t\u0003g\u0011K!!\u0012\u001b\u0003\u000fA\u0013x\u000eZ;diB\u0011qi\u0014\b\u0003\u00116s!!\u0013'\u000e\u0003)S!aS\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0014B\u0001(5\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001U)\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00059#\u0014!A9\u0016\u0003Q\u0003\"!\u0016,\r\u0001\u0011)qk\u0001b\u00011\n\tQ*\u0005\u0002Z9B\u00111GW\u0005\u00037R\u0012qAT8uQ&tw\r\u0005\u00024;&\u0011a\f\u000e\u0002\u0004\u0003:L\u0018AA9!\u0003\u0005\u0011\u0018A\u0001:!\u0003-\u0001\u0018N^8u\u001b\u0006$(/\u001b=\u0016\u0003\u0011\u0004\"!V3\u0005\u000b\u0019\u001c!\u0019\u0001-\u0003\u0017AKgo\u001c;NCR\u0014\u0018\u000e_\u0001\ra&4x\u000e^'biJL\u0007\u0010I\u0001\ra&4x\u000e^%oI&\u001cWm]\u000b\u0002UB\u00191g[7\n\u00051$$!B!se\u0006L\bCA\u001ao\u0013\tyGGA\u0002J]R\fQ\u0002]5w_RLe\u000eZ5dKN\u0004C#\u0002:ukZ<\b\u0003B:\u0004)\u0012l\u0011!\u0001\u0005\u0006%2\u0001\r\u0001\u0016\u0005\u0006A2\u0001\r\u0001\u0016\u0005\u0006E2\u0001\r\u0001\u001a\u0005\u0006Q2\u0001\rA[\u0001\u0005G>\u0004\u00180F\u0002{{~$\u0012b_A\u0001\u0003\u0007\t)!a\u0002\u0011\tM\u001cAP \t\u0003+v$QaV\u0007C\u0002a\u0003\"!V@\u0005\u000b\u0019l!\u0019\u0001-\t\u000fIk\u0001\u0013!a\u0001y\"9\u0001-\u0004I\u0001\u0002\u0004a\bb\u00022\u000e!\u0003\u0005\rA \u0005\bQ6\u0001\n\u00111\u0001k\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*b!!\u0004\u0002$\u0005\u0015RCAA\bU\r!\u0016\u0011C\u0016\u0003\u0003'\u0001B!!\u0006\u0002 5\u0011\u0011q\u0003\u0006\u0005\u00033\tY\"A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0004\u001b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\"\u0005]!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)qK\u0004b\u00011\u0012)aM\u0004b\u00011\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCBA\u0007\u0003W\ti\u0003B\u0003X\u001f\t\u0007\u0001\fB\u0003g\u001f\t\u0007\u0001,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\r\u0005M\u0012qGA\u001d+\t\t)DK\u0002e\u0003#!Qa\u0016\tC\u0002a#QA\u001a\tC\u0002a\u000babY8qs\u0012\"WMZ1vYR$C'\u0006\u0004\u0002@\u0005\r\u0013QI\u000b\u0003\u0003\u0003R3A[A\t\t\u00159\u0016C1\u0001Y\t\u00151\u0017C1\u0001Y\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\n\t\u0005\u0003\u001b\n9&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0013\u0001\u00026bm\u0006LA!!\u0017\u0002P\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\\\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ra\u00161\r\u0005\t\u0003K\"\u0012\u0011!a\u0001[\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u001b\u0011\u000b\u00055\u00141\u000f/\u000e\u0005\u0005=$bAA9i\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005U\u0014q\u000e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002|\u0005\u0005\u0005cA\u001a\u0002~%\u0019\u0011q\u0010\u001b\u0003\u000f\t{w\u000e\\3b]\"A\u0011Q\r\f\u0002\u0002\u0003\u0007A,\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA&\u0003\u000fC\u0001\"!\u001a\u0018\u0003\u0003\u0005\r!\\\u0001\tQ\u0006\u001c\bnQ8eKR\tQ.\u0001\u0005u_N#(/\u001b8h)\t\tY%\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003w\n)\n\u0003\u0005\u0002fi\t\t\u00111\u0001]\u0003\r\t&\u000b\u0015\t\u0003gr\u0019B\u0001\b\u001a\u0002\u001eB!\u0011qTAS\u001b\t\t\tK\u0003\u0003\u0002$\u0006M\u0013AA5p\u0013\r\u0001\u0016\u0011\u0015\u000b\u0003\u00033\u000bQ!\u00199qYf,b!!,\u00024\u0006]FCCAX\u0003s\u000bY,!0\u0002@B11oAAY\u0003k\u00032!VAZ\t\u00159vD1\u0001Y!\r)\u0016q\u0017\u0003\u0006M~\u0011\r\u0001\u0017\u0005\u0007%~\u0001\r!!-\t\r\u0001|\u0002\u0019AAY\u0011\u0019\u0011w\u00041\u0001\u00026\")\u0001n\ba\u0001U\u00069QO\\1qa2LXCBAc\u0003+\fI\u000e\u0006\u0003\u0002H\u0006m\u0007#B\u001a\u0002J\u00065\u0017bAAfi\t1q\n\u001d;j_:\u0004\"bMAh\u0003'\f\u0019.a6k\u0013\r\t\t\u000e\u000e\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u0007U\u000b)\u000eB\u0003XA\t\u0007\u0001\fE\u0002V\u00033$QA\u001a\u0011C\u0002aC\u0011\"!8!\u0003\u0003\u0005\r!a8\u0002\u0007a$\u0003\u0007\u0005\u0004t\u0007\u0005M\u0017q[\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003K\u0004B!!\u0014\u0002h&!\u0011\u0011^A(\u0005\u0019y%M[3di\nAA)\u001a8tKF\u0013\u0006\u000b\u0005\u0004t\u0007\u0005=\u00181 \t\u0006_\u0005E\u0018Q_\u0005\u0004\u0003gL#a\u0003#f]N,W*\u0019;sSb\u00042aMA|\u0013\r\tI\u0010\u000e\u0002\u0007\t>,(\r\\3\u0011\t=\n\t0\\\u0001\u000fS6\u0004Hn\u0018#N?\u0012{WO\u00197f!\t\u0019HE\u0001\bj[Bdw\fR'`\t>,(\r\\3\u0014\t\u0011\u0012$Q\u0001\t\bg\n\u001d\u0011q\u001eB\u0006\u0013\r\u0011I\u0001\u0010\u0002\u0005\u00136\u0004H\u000e\u0005\u0002tEQ\u0011\u0011q \u000b\u0005\u0005\u0017\u0011\t\u0002C\u0004\u0003\u0014\u0019\u0002\r!a<\u0002\u0003\u0005\u0003"
)
public final class qrp {
   public static Object withSink(final Object s) {
      return qrp$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return qrp$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return qrp$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return qrp$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return qrp$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return qrp$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return qrp$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return qrp$.MODULE$.apply(v, impl);
   }

   public static class QRP implements Product, Serializable {
      private final Object q;
      private final Object r;
      private final Object pivotMatrix;
      private final int[] pivotIndices;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object q() {
         return this.q;
      }

      public Object r() {
         return this.r;
      }

      public Object pivotMatrix() {
         return this.pivotMatrix;
      }

      public int[] pivotIndices() {
         return this.pivotIndices;
      }

      public QRP copy(final Object q, final Object r, final Object pivotMatrix, final int[] pivotIndices) {
         return new QRP(q, r, pivotMatrix, pivotIndices);
      }

      public Object copy$default$1() {
         return this.q();
      }

      public Object copy$default$2() {
         return this.r();
      }

      public Object copy$default$3() {
         return this.pivotMatrix();
      }

      public int[] copy$default$4() {
         return this.pivotIndices();
      }

      public String productPrefix() {
         return "QRP";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.q();
               break;
            case 1:
               var10000 = this.r();
               break;
            case 2:
               var10000 = this.pivotMatrix();
               break;
            case 3:
               var10000 = this.pivotIndices();
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
         return x$1 instanceof QRP;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "q";
               break;
            case 1:
               var10000 = "r";
               break;
            case 2:
               var10000 = "pivotMatrix";
               break;
            case 3:
               var10000 = "pivotIndices";
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
         boolean var10000;
         if (this != x$1) {
            label55: {
               boolean var2;
               if (x$1 instanceof QRP) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  QRP var4 = (QRP)x$1;
                  if (BoxesRunTime.equals(this.q(), var4.q()) && BoxesRunTime.equals(this.r(), var4.r()) && BoxesRunTime.equals(this.pivotMatrix(), var4.pivotMatrix()) && this.pivotIndices() == var4.pivotIndices() && var4.canEqual(this)) {
                     break label55;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public QRP(final Object q, final Object r, final Object pivotMatrix, final int[] pivotIndices) {
         this.q = q;
         this.r = r;
         this.pivotMatrix = pivotMatrix;
         this.pivotIndices = pivotIndices;
         Product.$init$(this);
      }
   }

   public static class QRP$ implements Serializable {
      public static final QRP$ MODULE$ = new QRP$();

      public final String toString() {
         return "QRP";
      }

      public QRP apply(final Object q, final Object r, final Object pivotMatrix, final int[] pivotIndices) {
         return new QRP(q, r, pivotMatrix, pivotIndices);
      }

      public Option unapply(final QRP x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.q(), x$0.r(), x$0.pivotMatrix(), x$0.pivotIndices())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QRP$.class);
      }
   }

   public static class impl_DM_Double$ implements UFunc.UImpl {
      public static final impl_DM_Double$ MODULE$ = new impl_DM_Double$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public QRP apply(final DenseMatrix A) {
         int m = A.rows();
         int n = A.cols();
         double[] scratch = new double[1];
         double[] work = new double[1];
         intW info = new intW(0);
         LAPACK.getInstance().dgeqrf(m, n, scratch, m, scratch, work, -1, info);
         int lwork1 = info.val != 0 ? n : (int)work[0];
         LAPACK.getInstance().dorgqr(m, m, scala.math.package..MODULE$.min(m, n), scratch, m, scratch, work, -1, info);
         int lwork2 = info.val != 0 ? n : (int)work[0];
         double[] workspace = new double[scala.math.package..MODULE$.max(lwork1, lwork2)];
         int maxd = scala.math.package..MODULE$.max(m, n);
         DenseMatrix AFact = DenseMatrix$.MODULE$.zeros$mDc$sp(m, maxd, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         int[] pvt = new int[n];
         double[] tau = new double[scala.math.package..MODULE$.min(m, n)];
         int index$macro$7 = 0;

         for(int limit$macro$9 = m; index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = n; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ((r, c) -> AFact.update$mcD$sp(r, c, A.apply$mcD$sp(r, c))).apply$mcVII$sp(index$macro$7, index$macro$2);
            }
         }

         LAPACK.getInstance().dgeqp3(m, n, AFact.data$mcD$sp(), m, pvt, tau, workspace, workspace.length, info);
         if (info.val > 0) {
            throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
         } else if (info.val < 0) {
            throw new IllegalArgumentException();
         } else {
            DenseMatrix R = DenseMatrix$.MODULE$.zeros$mDc$sp(m, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            int index$macro$17 = 0;

            for(int limit$macro$19 = min$.MODULE$.apply$mIIIc$sp(n, maxd, min$.MODULE$.minImpl2_Int()); index$macro$17 < limit$macro$19; ++index$macro$17) {
               int index$macro$12 = 0;

               for(int end$macro$13 = min$.MODULE$.apply$mIIIc$sp(m, index$macro$17, min$.MODULE$.minImpl2_Int()); index$macro$12 <= end$macro$13; ++index$macro$12) {
                  R.update$mcD$sp(index$macro$12, index$macro$17, AFact.apply$mcD$sp(index$macro$12, index$macro$17));
               }
            }

            DenseMatrix Q = DenseMatrix$.MODULE$.zeros$mDc$sp(m, m, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            LAPACK.getInstance().dorgqr(m, m, scala.math.package..MODULE$.min(m, n), AFact.data$mcD$sp(), m, tau, workspace, workspace.length, info);
            int index$macro$27 = 0;

            for(int limit$macro$29 = m; index$macro$27 < limit$macro$29; ++index$macro$27) {
               int index$macro$22 = 0;

               for(int limit$macro$24 = min$.MODULE$.apply$mIIIc$sp(m, maxd, min$.MODULE$.minImpl2_Int()); index$macro$22 < limit$macro$24; ++index$macro$22) {
                  ((r, c) -> Q.update$mcD$sp(r, c, AFact.apply$mcD$sp(r, c))).apply$mcVII$sp(index$macro$27, index$macro$22);
               }
            }

            if (info.val > 0) {
               throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
            } else if (info.val < 0) {
               throw new IllegalArgumentException();
            } else {
               NumericOps.Arrays$.MODULE$.ArrayIsNumericOps(pvt).$minus$eq(BoxesRunTime.boxToInteger(1), NumericOps.Arrays$.MODULE$.binaryUpdateOpFromDVOp(HasOps$.MODULE$.impl_Op_InPlace_DV_S_Int_OpSub()));
               DenseMatrix P = DenseMatrix$.MODULE$.zeros$mIc$sp(n, n, scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
               int index$macro$32 = 0;

               for(int limit$macro$34 = n; index$macro$32 < limit$macro$34; ++index$macro$32) {
                  P.update$mcI$sp(pvt[index$macro$32], index$macro$32, 1);
               }

               return new QRP(Q, R, P, pvt);
            }
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(impl_DM_Double$.class);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
