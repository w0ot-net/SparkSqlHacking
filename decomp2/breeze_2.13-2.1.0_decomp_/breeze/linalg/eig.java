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
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ux!B\u0013'\u0011\u0003Yc!B\u0017'\u0011\u0003q\u0003\"B\u001e\u0002\t\u0003ad\u0001B\u001f\u0002\u0001zB\u0001bT\u0002\u0003\u0016\u0004%\t\u0001\u0015\u0005\t9\u000e\u0011\t\u0012)A\u0005#\"AQl\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005_\u0007\tE\t\u0015!\u0003R\u0011!y6A!f\u0001\n\u0003\u0001\u0007\u0002\u00033\u0004\u0005#\u0005\u000b\u0011B1\t\u000bm\u001aA\u0011A3\t\u000f-\u001c\u0011\u0011!C\u0001Y\"9aoAI\u0001\n\u00039\b\"CA\u0006\u0007E\u0005I\u0011AA\u0007\u0011%\t\u0019bAI\u0001\n\u0003\t)\u0002C\u0005\u0002 \r\t\t\u0011\"\u0011\u0002\"!I\u00111G\u0002\u0002\u0002\u0013\u0005\u0011Q\u0007\u0005\n\u0003{\u0019\u0011\u0011!C\u0001\u0003\u007fA\u0011\"!\u0012\u0004\u0003\u0003%\t%a\u0012\t\u0013\u0005U3!!A\u0005\u0002\u0005]\u0003\"CA1\u0007\u0005\u0005I\u0011IA2\u0011%\t9gAA\u0001\n\u0003\nI\u0007C\u0005\u0002l\r\t\t\u0011\"\u0011\u0002n!I\u0011qN\u0002\u0002\u0002\u0013\u0005\u0013\u0011O\u0004\n\u0003k\n\u0011\u0011!E\u0001\u0003o2\u0001\"P\u0001\u0002\u0002#\u0005\u0011\u0011\u0010\u0005\u0007we!\t!!\"\t\u0013\u0005-\u0014$!A\u0005F\u00055\u0004\"CAD3\u0005\u0005I\u0011QAE\u0011%\ti*GA\u0001\n\u0003\u000by\nC\u0005\u0002>f\t\t\u0011\"\u0003\u0002@\u00161\u0011qY\u0001\u0001\u0003\u0013<q!!8\u0002\u0011\u0007\tyNB\u0004\u0002b\u0006A\t!a9\t\rm\nC\u0011AAw\u0011\u001d\t9)\tC\u0001\u0003_D\u0011\"!0\"\u0003\u0003%I!a0\u0002\u0007\u0015LwM\u0003\u0002(Q\u00051A.\u001b8bY\u001eT\u0011!K\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A&A\u0007\u0002M\t\u0019Q-[4\u0014\u0007\u0005yS\u0007\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003mej\u0011a\u000e\u0006\u0003q!\nqaZ3oKJL7-\u0003\u0002;o\t)QKR;oG\u00061A(\u001b8jiz\"\u0012a\u000b\u0002\u0004\u000b&<WcA TEN!1a\f!D!\t\u0001\u0014)\u0003\u0002Cc\t9\u0001K]8ek\u000e$\bC\u0001#M\u001d\t)%J\u0004\u0002G\u00136\tqI\u0003\u0002IU\u00051AH]8pizJ\u0011AM\u0005\u0003\u0017F\nq\u0001]1dW\u0006<W-\u0003\u0002N\u001d\na1+\u001a:jC2L'0\u00192mK*\u00111*M\u0001\fK&<WM\u001c<bYV,7/F\u0001R!\t\u00116\u000b\u0004\u0001\u0005\u000bQ\u001b!\u0019A+\u0003\u0003Y\u000b\"AV-\u0011\u0005A:\u0016B\u0001-2\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\r.\n\u0005m\u000b$aA!os\u0006aQ-[4f]Z\fG.^3tA\u0005\u0011R-[4f]Z\fG.^3t\u0007>l\u0007\u000f\\3y\u0003M)\u0017nZ3om\u0006dW/Z:D_6\u0004H.\u001a=!\u00031)\u0017nZ3om\u0016\u001cGo\u001c:t+\u0005\t\u0007C\u0001*c\t\u0015\u00197A1\u0001V\u0005\u0005i\u0015!D3jO\u0016tg/Z2u_J\u001c\b\u0005\u0006\u0003gQ&T\u0007\u0003B4\u0004#\u0006l\u0011!\u0001\u0005\u0006\u001f*\u0001\r!\u0015\u0005\u0006;*\u0001\r!\u0015\u0005\u0006?*\u0001\r!Y\u0001\u0005G>\u0004\u00180F\u0002naJ$BA\\:ukB!qmA8r!\t\u0011\u0006\u000fB\u0003U\u0017\t\u0007Q\u000b\u0005\u0002Se\u0012)1m\u0003b\u0001+\"9qj\u0003I\u0001\u0002\u0004y\u0007bB/\f!\u0003\u0005\ra\u001c\u0005\b?.\u0001\n\u00111\u0001r\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*R\u0001_A\u0004\u0003\u0013)\u0012!\u001f\u0016\u0003#j\\\u0013a\u001f\t\u0004y\u0006\rQ\"A?\u000b\u0005y|\u0018!C;oG\",7m[3e\u0015\r\t\t!M\u0001\u000bC:tw\u000e^1uS>t\u0017bAA\u0003{\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bQc!\u0019A+\u0005\u000b\rd!\u0019A+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU)\u00010a\u0004\u0002\u0012\u0011)A+\u0004b\u0001+\u0012)1-\u0004b\u0001+\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCBA\f\u00037\ti\"\u0006\u0002\u0002\u001a)\u0012\u0011M\u001f\u0003\u0006):\u0011\r!\u0016\u0003\u0006G:\u0011\r!V\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\r\u0002\u0003BA\u0013\u0003_i!!a\n\u000b\t\u0005%\u00121F\u0001\u0005Y\u0006twM\u0003\u0002\u0002.\u0005!!.\u0019<b\u0013\u0011\t\t$a\n\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t9\u0004E\u00021\u0003sI1!a\u000f2\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rI\u0016\u0011\t\u0005\n\u0003\u0007\n\u0012\u0011!a\u0001\u0003o\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA%!\u0015\tY%!\u0015Z\u001b\t\tiEC\u0002\u0002PE\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019&!\u0014\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00033\ny\u0006E\u00021\u00037J1!!\u00182\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\u0011\u0014\u0003\u0003\u0005\r!W\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002$\u0005\u0015\u0004\"CA\")\u0005\u0005\t\u0019AA\u001c\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001c\u0003!!xn\u0015;sS:<GCAA\u0012\u0003\u0019)\u0017/^1mgR!\u0011\u0011LA:\u0011!\t\u0019eFA\u0001\u0002\u0004I\u0016aA#jOB\u0011q-G\n\u00053=\nY\b\u0005\u0003\u0002~\u0005\rUBAA@\u0015\u0011\t\t)a\u000b\u0002\u0005%|\u0017bA'\u0002\u0000Q\u0011\u0011qO\u0001\u0006CB\u0004H._\u000b\u0007\u0003\u0017\u000b\t*!&\u0015\u0011\u00055\u0015qSAM\u00037\u0003baZ\u0002\u0002\u0010\u0006M\u0005c\u0001*\u0002\u0012\u0012)A\u000b\bb\u0001+B\u0019!+!&\u0005\u000b\rd\"\u0019A+\t\r=c\u0002\u0019AAH\u0011\u0019iF\u00041\u0001\u0002\u0010\"1q\f\ba\u0001\u0003'\u000bq!\u001e8baBd\u00170\u0006\u0004\u0002\"\u0006E\u0016Q\u0017\u000b\u0005\u0003G\u000b9\fE\u00031\u0003K\u000bI+C\u0002\u0002(F\u0012aa\u00149uS>t\u0007#\u0003\u0019\u0002,\u0006=\u0016qVAZ\u0013\r\ti+\r\u0002\u0007)V\u0004H.Z\u001a\u0011\u0007I\u000b\t\fB\u0003U;\t\u0007Q\u000bE\u0002S\u0003k#QaY\u000fC\u0002UC\u0011\"!/\u001e\u0003\u0003\u0005\r!a/\u0002\u0007a$\u0003\u0007\u0005\u0004h\u0007\u0005=\u00161W\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0003\u0004B!!\n\u0002D&!\u0011QYA\u0014\u0005\u0019y%M[3di\nAA)\u001a8tK\u0016Kw\r\u0005\u0004h\u0007\u0005-\u0017q\u001b\t\u0006Y\u00055\u0017\u0011[\u0005\u0004\u0003\u001f4#a\u0003#f]N,g+Z2u_J\u00042\u0001MAj\u0013\r\t).\r\u0002\u0007\t>,(\r\\3\u0011\u000b1\nI.!5\n\u0007\u0005mgEA\u0006EK:\u001cX-T1ue&D\u0018aC#jO~#UjX%na2\u0004\"aZ\u0011\u0003\u0017\u0015Kwm\u0018#N?&k\u0007\u000f\\\n\u0005C=\n)\u000fE\u0004h\u0003O\f9.a;\n\u0007\u0005%\u0018H\u0001\u0003J[Bd\u0007CA4 )\t\ty\u000e\u0006\u0003\u0002l\u0006E\bbBAzG\u0001\u0007\u0011q[\u0001\u0002[\u0002"
)
public final class eig {
   public static Object withSink(final Object s) {
      return eig$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return eig$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return eig$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return eig$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return eig$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return eig$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return eig$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return eig$.MODULE$.apply(v, impl);
   }

   public static class Eig implements Product, Serializable {
      private final Object eigenvalues;
      private final Object eigenvaluesComplex;
      private final Object eigenvectors;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object eigenvalues() {
         return this.eigenvalues;
      }

      public Object eigenvaluesComplex() {
         return this.eigenvaluesComplex;
      }

      public Object eigenvectors() {
         return this.eigenvectors;
      }

      public Eig copy(final Object eigenvalues, final Object eigenvaluesComplex, final Object eigenvectors) {
         return new Eig(eigenvalues, eigenvaluesComplex, eigenvectors);
      }

      public Object copy$default$1() {
         return this.eigenvalues();
      }

      public Object copy$default$2() {
         return this.eigenvaluesComplex();
      }

      public Object copy$default$3() {
         return this.eigenvectors();
      }

      public String productPrefix() {
         return "Eig";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.eigenvalues();
               break;
            case 1:
               var10000 = this.eigenvaluesComplex();
               break;
            case 2:
               var10000 = this.eigenvectors();
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
         return x$1 instanceof Eig;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "eigenvalues";
               break;
            case 1:
               var10000 = "eigenvaluesComplex";
               break;
            case 2:
               var10000 = "eigenvectors";
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
            label53: {
               boolean var2;
               if (x$1 instanceof Eig) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Eig var4 = (Eig)x$1;
                  if (BoxesRunTime.equals(this.eigenvalues(), var4.eigenvalues()) && BoxesRunTime.equals(this.eigenvaluesComplex(), var4.eigenvaluesComplex()) && BoxesRunTime.equals(this.eigenvectors(), var4.eigenvectors()) && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Eig(final Object eigenvalues, final Object eigenvaluesComplex, final Object eigenvectors) {
         this.eigenvalues = eigenvalues;
         this.eigenvaluesComplex = eigenvaluesComplex;
         this.eigenvectors = eigenvectors;
         Product.$init$(this);
      }
   }

   public static class Eig$ implements Serializable {
      public static final Eig$ MODULE$ = new Eig$();

      public final String toString() {
         return "Eig";
      }

      public Eig apply(final Object eigenvalues, final Object eigenvaluesComplex, final Object eigenvectors) {
         return new Eig(eigenvalues, eigenvaluesComplex, eigenvectors);
      }

      public Option unapply(final Eig x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.eigenvalues(), x$0.eigenvaluesComplex(), x$0.eigenvectors())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Eig$.class);
      }
   }

   public static class Eig_DM_Impl$ implements UFunc.UImpl {
      public static final Eig_DM_Impl$ MODULE$ = new Eig_DM_Impl$();

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

      public Eig apply(final DenseMatrix m) {
         package$.MODULE$.requireNonEmptyMatrix(m);
         package$.MODULE$.requireSquareMatrix(m);
         boolean cond$macro$1 = !m.valuesIterator().exists((JFunction1.mcZD.sp)(x$1) -> Double.isNaN(x$1));
         if (!cond$macro$1) {
            throw new IllegalArgumentException("requirement failed: m.valuesIterator.exists(((x$1: Double) => scala.Predef.double2Double(x$1).isNaN())).unary_!");
         } else {
            int n = m.rows();
            DenseVector Wr = DenseVector$.MODULE$.zeros$mDc$sp(n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            DenseVector Wi = DenseVector$.MODULE$.zeros$mDc$sp(n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            DenseMatrix Vr = DenseMatrix$.MODULE$.zeros$mDc$sp(n, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            DenseMatrix Vl = DenseMatrix$.MODULE$.zeros$mDc$sp(n, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] worksize = (double[])scala.Array..MODULE$.ofDim(1, scala.reflect.ClassTag..MODULE$.Double());
            intW info = new intW(0);
            LAPACK.getInstance().dgeev("N", "V", n, (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), scala.math.package..MODULE$.max(1, n), (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), scala.math.package..MODULE$.max(1, n), (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), scala.math.package..MODULE$.max(1, n), worksize, -1, info);
            int lwork = info.val != 0 ? scala.math.package..MODULE$.max(1, 4 * n) : scala.math.package..MODULE$.max(1, (int)worksize[0]);
            double[] work = (double[])scala.Array..MODULE$.ofDim(lwork, scala.reflect.ClassTag..MODULE$.Double());
            DenseMatrix A = DenseMatrix$.MODULE$.zeros$mDc$sp(n, n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            A.$colon$eq(m, HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
            LAPACK.getInstance().dgeev("N", "V", n, A.data$mcD$sp(), scala.math.package..MODULE$.max(1, n), Wr.data$mcD$sp(), Wi.data$mcD$sp(), Vl.data$mcD$sp(), scala.math.package..MODULE$.max(1, n), Vr.data$mcD$sp(), scala.math.package..MODULE$.max(1, n), work, work.length, info);
            if (info.val > 0) {
               throw new NotConvergedException(NotConvergedException.Iterations$.MODULE$, NotConvergedException$.MODULE$.$lessinit$greater$default$2());
            } else if (info.val < 0) {
               throw new IllegalArgumentException();
            } else {
               return new Eig(Wr, Wi, Vr);
            }
         }
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Eig_DM_Impl$.class);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
