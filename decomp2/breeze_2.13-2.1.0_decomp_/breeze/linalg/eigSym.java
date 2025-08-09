package breeze.linalg;

import breeze.generic.UFunc;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tmq!B\u0016-\u0011\u0003\td!B\u001a-\u0011\u0003!\u0004\"B!\u0002\t\u0003\u0011e\u0001B\"\u0002\u0001\u0012C\u0001\"V\u0002\u0003\u0016\u0004%\tA\u0016\u0005\tE\u000e\u0011\t\u0012)A\u0005/\"A1m\u0001BK\u0002\u0013\u0005A\r\u0003\u0005i\u0007\tE\t\u0015!\u0003f\u0011\u0015\t5\u0001\"\u0001j\u0011\u001dq7!!A\u0005\u0002=Dq\u0001_\u0002\u0012\u0002\u0013\u0005\u0011\u0010C\u0005\u0002\u0010\r\t\n\u0011\"\u0001\u0002\u0012!I\u00111D\u0002\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003_\u0019\u0011\u0011!C\u0001\u0003cA\u0011\"!\u000f\u0004\u0003\u0003%\t!a\u000f\t\u0013\u0005\u00053!!A\u0005B\u0005\r\u0003\"CA)\u0007\u0005\u0005I\u0011AA*\u0011%\tifAA\u0001\n\u0003\ny\u0006C\u0005\u0002d\r\t\t\u0011\"\u0011\u0002f!I\u0011qM\u0002\u0002\u0002\u0013\u0005\u0013\u0011\u000e\u0005\n\u0003W\u001a\u0011\u0011!C!\u0003[:\u0011\"!\u001d\u0002\u0003\u0003E\t!a\u001d\u0007\u0011\r\u000b\u0011\u0011!E\u0001\u0003kBa!\u0011\f\u0005\u0002\u0005\u0005\u0005\"CA4-\u0005\u0005IQIA5\u0011%\t\u0019IFA\u0001\n\u0003\u000b)\tC\u0005\u0002\u0018Z\t\t\u0011\"!\u0002\u001a\"I\u0011q\u0017\f\u0002\u0002\u0013%\u0011\u0011X\u0003\u0007\u0003\u0003\f\u0001!a1\b\u000f\u0005]\u0017\u0001c\u0001\u0002Z\u001a9\u00111\\\u0001\t\u0002\u0005u\u0007BB!\u001f\t\u0003\t9\u000fC\u0004\u0002\u0004z!\t!!;\t\u0013\u0005]f$!A\u0005\n\u0005evaBAx\u0003!\u0005\u0011\u0011\u001f\u0004\b\u0003g\f\u0001\u0012AA{\u0011\u0019\t5\u0005\"\u0001\u0002x\u001e9\u0011q[\u0012\t\u0004\u0005ehaBAnG!\u0005\u0011Q \u0005\u0007\u0003\u001a\"\tA!\u0001\t\u000f\u0005\re\u0005\"\u0001\u0003\u0004!I\u0011q\u0017\u0014\u0002\u0002\u0013%\u0011\u0011\u0018\u0005\b\u0005\u000f\tA\u0011\u0002B\u0005\u0003\u0019)\u0017nZ*z[*\u0011QFL\u0001\u0007Y&t\u0017\r\\4\u000b\u0003=\naA\u0019:fKj,7\u0001\u0001\t\u0003e\u0005i\u0011\u0001\f\u0002\u0007K&<7+_7\u0014\u0007\u0005)4\b\u0005\u00027s5\tqGC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQtG\u0001\u0004B]f\u0014VM\u001a\t\u0003y}j\u0011!\u0010\u0006\u0003}9\nqaZ3oKJL7-\u0003\u0002A{\t)QKR;oG\u00061A(\u001b8jiz\"\u0012!\r\u0002\u0007\u000b&<7+_7\u0016\u0007\u0015Kfm\u0005\u0003\u0004k\u0019K\u0005C\u0001\u001cH\u0013\tAuGA\u0004Qe>$Wo\u0019;\u0011\u0005)\u0013fBA&Q\u001d\tau*D\u0001N\u0015\tq\u0005'\u0001\u0004=e>|GOP\u0005\u0002q%\u0011\u0011kN\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019FK\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Ro\u0005YQ-[4f]Z\fG.^3t+\u00059\u0006C\u0001-Z\u0019\u0001!QAW\u0002C\u0002m\u0013\u0011AV\t\u00039~\u0003\"AN/\n\u0005y;$a\u0002(pi\"Lgn\u001a\t\u0003m\u0001L!!Y\u001c\u0003\u0007\u0005s\u00170\u0001\u0007fS\u001e,gN^1mk\u0016\u001c\b%\u0001\u0007fS\u001e,gN^3di>\u00148/F\u0001f!\tAf\rB\u0003h\u0007\t\u00071LA\u0001N\u00035)\u0017nZ3om\u0016\u001cGo\u001c:tAQ\u0019!\u000e\\7\u0011\t-\u001cq+Z\u0007\u0002\u0003!)Q\u000b\u0003a\u0001/\")1\r\u0003a\u0001K\u0006!1m\u001c9z+\r\u00018/\u001e\u000b\u0004cZ<\b\u0003B6\u0004eR\u0004\"\u0001W:\u0005\u000biK!\u0019A.\u0011\u0005a+H!B4\n\u0005\u0004Y\u0006bB+\n!\u0003\u0005\rA\u001d\u0005\bG&\u0001\n\u00111\u0001u\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*RA_A\u0006\u0003\u001b)\u0012a\u001f\u0016\u0003/r\\\u0013! \t\u0004}\u0006\u001dQ\"A@\u000b\t\u0005\u0005\u00111A\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00028\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u0013y(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!L\u0003b\u00017\u0012)qM\u0003b\u00017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCBA\n\u0003/\tI\"\u0006\u0002\u0002\u0016)\u0012Q\r \u0003\u00065.\u0011\ra\u0017\u0003\u0006O.\u0011\raW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005}\u0001\u0003BA\u0011\u0003Wi!!a\t\u000b\t\u0005\u0015\u0012qE\u0001\u0005Y\u0006twM\u0003\u0002\u0002*\u0005!!.\u0019<b\u0013\u0011\ti#a\t\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\u0019\u0004E\u00027\u0003kI1!a\u000e8\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ry\u0016Q\b\u0005\n\u0003\u007fq\u0011\u0011!a\u0001\u0003g\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA#!\u0015\t9%!\u0014`\u001b\t\tIEC\u0002\u0002L]\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty%!\u0013\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003+\nY\u0006E\u00027\u0003/J1!!\u00178\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\u0010\u0011\u0003\u0003\u0005\raX\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002 \u0005\u0005\u0004\"CA #\u0005\u0005\t\u0019AA\u001a\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001a\u0003!!xn\u0015;sS:<GCAA\u0010\u0003\u0019)\u0017/^1mgR!\u0011QKA8\u0011!\ty\u0004FA\u0001\u0002\u0004y\u0016AB#jONKX\u000e\u0005\u0002l-M!a#NA<!\u0011\tI(a \u000e\u0005\u0005m$\u0002BA?\u0003O\t!![8\n\u0007M\u000bY\b\u0006\u0002\u0002t\u0005)\u0011\r\u001d9msV1\u0011qQAG\u0003##b!!#\u0002\u0014\u0006U\u0005CB6\u0004\u0003\u0017\u000by\tE\u0002Y\u0003\u001b#QAW\rC\u0002m\u00032\u0001WAI\t\u00159\u0017D1\u0001\\\u0011\u0019)\u0016\u00041\u0001\u0002\f\"11-\u0007a\u0001\u0003\u001f\u000bq!\u001e8baBd\u00170\u0006\u0004\u0002\u001c\u0006-\u0016q\u0016\u000b\u0005\u0003;\u000b\t\fE\u00037\u0003?\u000b\u0019+C\u0002\u0002\"^\u0012aa\u00149uS>t\u0007c\u0002\u001c\u0002&\u0006%\u0016QV\u0005\u0004\u0003O;$A\u0002+va2,'\u0007E\u0002Y\u0003W#QA\u0017\u000eC\u0002m\u00032\u0001WAX\t\u00159'D1\u0001\\\u0011%\t\u0019LGA\u0001\u0002\u0004\t),A\u0002yIA\u0002ba[\u0002\u0002*\u00065\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAA^!\u0011\t\t#!0\n\t\u0005}\u00161\u0005\u0002\u0007\u001f\nTWm\u0019;\u0003\u0017\u0011+gn]3FS\u001e\u001c\u00160\u001c\t\u0007W\u000e\t)-!5\u0011\u000bI\n9-a3\n\u0007\u0005%GFA\u0006EK:\u001cXMV3di>\u0014\bc\u0001\u001c\u0002N&\u0019\u0011qZ\u001c\u0003\r\u0011{WO\u00197f!\u0015\u0011\u00141[Af\u0013\r\t)\u000e\f\u0002\f\t\u0016t7/Z'biJL\u00070\u0001\bFS\u001e\u001c\u00160\\0E\u001b~KU\u000e\u001d7\u0011\u0005-t\"AD#jONKXn\u0018#N?&k\u0007\u000f\\\n\u0005=U\ny\u000eE\u0004l\u0003C\f\t.!:\n\u0007\u0005\rxH\u0001\u0003J[Bd\u0007CA6\u001d)\t\tI\u000e\u0006\u0003\u0002f\u0006-\bbBAwA\u0001\u0007\u0011\u0011[\u0001\u00021\u0006y!.^:u\u000b&<WM\u001c<bYV,7\u000f\u0005\u0002lG\ty!.^:u\u000b&<WM\u001c<bYV,7oE\u0002$km\"\"!!=\u0011\u0007\u0005mh%D\u0001$'\u00111S'a@\u0011\u0011\u0005m\u0018\u0011]Ai\u0003\u000b$\"!!?\u0015\t\u0005\u0015'Q\u0001\u0005\b\u0003[D\u0003\u0019AAi\u0003!!w.R5h'flGC\u0002B\u0006\u0005\u001f\u00119\u0002E\u00047\u0003K\u000b)M!\u0004\u0011\u000bY\ny*!5\t\u000f\u00055(\u00061\u0001\u0003\u0012A)!Ga\u0005\u0002L&\u0019!Q\u0003\u0017\u0003\r5\u000bGO]5y\u0011\u001d\u0011IB\u000ba\u0001\u0003+\n\u0011C]5hQR,\u0015nZ3om\u0016\u001cGo\u001c:t\u0001"
)
public final class eigSym {
   public static Object withSink(final Object s) {
      return eigSym$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return eigSym$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return eigSym$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return eigSym$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return eigSym$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return eigSym$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return eigSym$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return eigSym$.MODULE$.apply(v, impl);
   }

   public static class EigSym implements Product, Serializable {
      private final Object eigenvalues;
      private final Object eigenvectors;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object eigenvalues() {
         return this.eigenvalues;
      }

      public Object eigenvectors() {
         return this.eigenvectors;
      }

      public EigSym copy(final Object eigenvalues, final Object eigenvectors) {
         return new EigSym(eigenvalues, eigenvectors);
      }

      public Object copy$default$1() {
         return this.eigenvalues();
      }

      public Object copy$default$2() {
         return this.eigenvectors();
      }

      public String productPrefix() {
         return "EigSym";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.eigenvalues();
               break;
            case 1:
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
         return x$1 instanceof EigSym;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "eigenvalues";
               break;
            case 1:
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
            label51: {
               boolean var2;
               if (x$1 instanceof EigSym) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  EigSym var4 = (EigSym)x$1;
                  if (BoxesRunTime.equals(this.eigenvalues(), var4.eigenvalues()) && BoxesRunTime.equals(this.eigenvectors(), var4.eigenvectors()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public EigSym(final Object eigenvalues, final Object eigenvectors) {
         this.eigenvalues = eigenvalues;
         this.eigenvectors = eigenvectors;
         Product.$init$(this);
      }
   }

   public static class EigSym$ implements Serializable {
      public static final EigSym$ MODULE$ = new EigSym$();

      public final String toString() {
         return "EigSym";
      }

      public EigSym apply(final Object eigenvalues, final Object eigenvectors) {
         return new EigSym(eigenvalues, eigenvectors);
      }

      public Option unapply(final EigSym x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.eigenvalues(), x$0.eigenvectors())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EigSym$.class);
      }
   }

   public static class EigSym_DM_Impl$ implements UFunc.UImpl {
      public static final EigSym_DM_Impl$ MODULE$ = new EigSym_DM_Impl$();

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

      public EigSym apply(final DenseMatrix X) {
         Tuple2 var3 = eigSym$.MODULE$.breeze$linalg$eigSym$$doEigSym(X, true);
         if (var3 != null) {
            DenseVector ev = (DenseVector)var3._1();
            Option var5 = (Option)var3._2();
            if (var5 instanceof Some) {
               Some var6 = (Some)var5;
               DenseMatrix rev = (DenseMatrix)var6.value();
               EigSym var2 = new EigSym(ev, rev);
               return var2;
            }
         }

         throw new RuntimeException("Shouldn't be here!");
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EigSym_DM_Impl$.class);
      }
   }

   public static class justEigenvalues$ implements UFunc {
      public static final justEigenvalues$ MODULE$ = new justEigenvalues$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }
   }
}
