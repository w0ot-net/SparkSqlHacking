package breeze.generic;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001\u0002\f\u0018\u0001rA\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0017\")q\n\u0001C\u0001!\"9!\u000bAA\u0001\n\u0003\u0019\u0006b\u0002/\u0001#\u0003%\t!\u0018\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d)\b!!A\u0005\u0002YDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011I@\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\r\u0001\u0005\u0005I\u0011IA\u000e\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011F\u0004\n\u0003[9\u0012\u0011!E\u0001\u0003_1\u0001BF\f\u0002\u0002#\u0005\u0011\u0011\u0007\u0005\u0007\u001fB!\t!!\u0010\t\u0013\u0005\r\u0002#!A\u0005F\u0005\u0015\u0002\"CA !\u0005\u0005I\u0011QA!\u0011%\t\u0019\u0006EA\u0001\n\u0003\u000b)\u0006C\u0005\u0002pA\t\t\u0011\"\u0003\u0002r\tiqK]1qa\u0016$WKR;oGFR!\u0001G\r\u0002\u000f\u001d,g.\u001a:jG*\t!$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\rib\u0006O\n\u0006\u0001y!#(\u0010\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u0003:L(+\u001a4\u0011\t\u00152\u0003fK\u0007\u0002/%\u0011qe\u0006\u0002\u000e-\u0006\u0014\u0018.\u00192mKV3UO\\2\u000f\u0005\u0015J\u0013B\u0001\u0016\u0018\u000319&/\u00199qK\u0012,f)\u001e8d!\u0011)\u0003\u0001L\u001c\u0011\u00055rC\u0002\u0001\u0003\u0006_\u0001\u0011\r\u0001\r\u0002\u0003\u0003F\n\"!\r\u001b\u0011\u0005}\u0011\u0014BA\u001a!\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aH\u001b\n\u0005Y\u0002#aA!osB\u0011Q\u0006\u000f\u0003\u0006s\u0001\u0011\r\u0001\r\u0002\u0002%B\u0011qdO\u0005\u0003y\u0001\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002?\r:\u0011q\b\u0012\b\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005n\ta\u0001\u0010:p_Rt\u0014\"A\u0011\n\u0005\u0015\u0003\u0013a\u00029bG.\fw-Z\u0005\u0003\u000f\"\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0012\u0011\u0002\u0003\u0019,\u0012a\u0013\t\u0005?1cs'\u0003\u0002NA\tIa)\u001e8di&|g.M\u0001\u0003M\u0002\na\u0001P5oSRtDCA\u0016R\u0011\u0015I5\u00011\u0001L\u0003\u0011\u0019w\u000e]=\u0016\u0007Q;\u0016\f\u0006\u0002V5B!Q\u0005\u0001,Y!\tis\u000bB\u00030\t\t\u0007\u0001\u0007\u0005\u0002.3\u0012)\u0011\b\u0002b\u0001a!9\u0011\n\u0002I\u0001\u0002\u0004Y\u0006\u0003B\u0010M-b\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0002_S*,\u0012a\u0018\u0016\u0003\u0017\u0002\\\u0013!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0019\u0004\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001n\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B\u0018\u0006\u0005\u0004\u0001D!B\u001d\u0006\u0005\u0004\u0001\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001n!\tq7/D\u0001p\u0015\t\u0001\u0018/\u0001\u0003mC:<'\"\u0001:\u0002\t)\fg/Y\u0005\u0003i>\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A<\u0011\u0005}A\u0018BA=!\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t!D\u0010C\u0004~\u0011\u0005\u0005\t\u0019A<\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0001E\u0003\u0002\u0004\u0005%A'\u0004\u0002\u0002\u0006)\u0019\u0011q\u0001\u0011\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\f\u0005\u0015!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0005\u0002\u0018A\u0019q$a\u0005\n\u0007\u0005U\u0001EA\u0004C_>dW-\u00198\t\u000fuT\u0011\u0011!a\u0001i\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ri\u0017Q\u0004\u0005\b{.\t\t\u00111\u0001x\u0003!A\u0017m\u001d5D_\u0012,G#A<\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u00111\u0006\u0005\b{:\t\t\u00111\u00015\u000359&/\u00199qK\u0012,f)\u001e8dcA\u0011Q\u0005E\n\u0005!y\t\u0019\u0004\u0005\u0003\u00026\u0005mRBAA\u001c\u0015\r\tI$]\u0001\u0003S>L1aRA\u001c)\t\ty#A\u0003baBd\u00170\u0006\u0004\u0002D\u0005%\u0013Q\n\u000b\u0005\u0003\u000b\ny\u0005\u0005\u0004&\u0001\u0005\u001d\u00131\n\t\u0004[\u0005%C!B\u0018\u0014\u0005\u0004\u0001\u0004cA\u0017\u0002N\u0011)\u0011h\u0005b\u0001a!1\u0011j\u0005a\u0001\u0003#\u0002ba\b'\u0002H\u0005-\u0013aB;oCB\u0004H._\u000b\u0007\u0003/\n\u0019'a\u001a\u0015\t\u0005e\u0013\u0011\u000e\t\u0006?\u0005m\u0013qL\u0005\u0004\u0003;\u0002#AB(qi&|g\u000e\u0005\u0004 \u0019\u0006\u0005\u0014Q\r\t\u0004[\u0005\rD!B\u0018\u0015\u0005\u0004\u0001\u0004cA\u0017\u0002h\u0011)\u0011\b\u0006b\u0001a!I\u00111\u000e\u000b\u0002\u0002\u0003\u0007\u0011QN\u0001\u0004q\u0012\u0002\u0004CB\u0013\u0001\u0003C\n)'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002tA\u0019a.!\u001e\n\u0007\u0005]tN\u0001\u0004PE*,7\r\u001e"
)
public class WrappedUFunc1 implements VariableUFunc, Product, Serializable {
   private final Function1 f;

   public static Option unapply(final WrappedUFunc1 x$0) {
      return WrappedUFunc1$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final Object apply(final Object v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$(this, v, impl);
   }

   public final double apply$mDDc$sp(final double v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mDDc$sp$(this, v, impl);
   }

   public final float apply$mDFc$sp(final double v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mDFc$sp$(this, v, impl);
   }

   public final int apply$mDIc$sp(final double v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mDIc$sp$(this, v, impl);
   }

   public final double apply$mFDc$sp(final float v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mFDc$sp$(this, v, impl);
   }

   public final float apply$mFFc$sp(final float v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mFFc$sp$(this, v, impl);
   }

   public final int apply$mFIc$sp(final float v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mFIc$sp$(this, v, impl);
   }

   public final double apply$mIDc$sp(final int v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mIDc$sp$(this, v, impl);
   }

   public final float apply$mIFc$sp(final int v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mIFc$sp$(this, v, impl);
   }

   public final int apply$mIIc$sp(final int v, final UFunc.UImpl2 impl) {
      return VariableUFunc.apply$mIIc$sp$(this, v, impl);
   }

   public final Object apply(final Object v1, final Object v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$(this, v1, v2, impl);
   }

   public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mDIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mFIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
      return VariableUFunc.apply$mIIIc$sp$(this, v1, v2, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl4 impl) {
      return VariableUFunc.apply$(this, v1, v2, v3, impl);
   }

   public Function1 f() {
      return this.f;
   }

   public WrappedUFunc1 copy(final Function1 f) {
      return new WrappedUFunc1(f);
   }

   public Function1 copy$default$1() {
      return this.f();
   }

   public String productPrefix() {
      return "WrappedUFunc1";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.f();
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
      return x$1 instanceof WrappedUFunc1;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "f";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof WrappedUFunc1) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     WrappedUFunc1 var4 = (WrappedUFunc1)x$1;
                     Function1 var10000 = this.f();
                     Function1 var5 = var4.f();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public WrappedUFunc1(final Function1 f) {
      this.f = f;
      VariableUFunc.$init$(this);
      Product.$init$(this);
   }
}
