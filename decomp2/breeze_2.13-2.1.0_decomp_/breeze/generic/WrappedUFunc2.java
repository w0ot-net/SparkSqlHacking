package breeze.generic;

import java.io.Serializable;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055e\u0001\u0002\f\u0018\u0001rA\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001d\")!\u000b\u0001C\u0001'\"9Q\u000bAA\u0001\n\u00031\u0006bB1\u0001#\u0003%\tA\u0019\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001dY\b!!A\u0005\u0002qD\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9\u0011\"!\u000f\u0018\u0003\u0003E\t!a\u000f\u0007\u0011Y9\u0012\u0011!E\u0001\u0003{AaA\u0015\t\u0005\u0002\u0005%\u0003\"CA\u0018!\u0005\u0005IQIA\u0019\u0011%\tY\u0005EA\u0001\n\u0003\u000bi\u0005C\u0005\u0002dA\t\t\u0011\"!\u0002f!I\u00111\u0011\t\u0002\u0002\u0013%\u0011Q\u0011\u0002\u000e/J\f\u0007\u000f]3e+\u001a+hn\u0019\u001a\u000b\u0005aI\u0012aB4f]\u0016\u0014\u0018n\u0019\u0006\u00025\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003\u001e]aZ4#\u0002\u0001\u001fIu\u0002\u0005CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\r\u0005\u0003&M!ZS\"A\f\n\u0005\u001d:\"!\u0004,be&\f'\r\\3V\rVt7M\u0004\u0002&S%\u0011!fF\u0001\r/J\f\u0007\u000f]3e+\u001a+hn\u0019\t\u0006K\u0001asG\u000f\t\u0003[9b\u0001\u0001B\u00030\u0001\t\u0007\u0001G\u0001\u0002BcE\u0011\u0011\u0007\u000e\t\u0003?IJ!a\r\u0011\u0003\u000f9{G\u000f[5oOB\u0011q$N\u0005\u0003m\u0001\u00121!\u00118z!\ti\u0003\bB\u0003:\u0001\t\u0007\u0001G\u0001\u0002BeA\u0011Qf\u000f\u0003\u0006y\u0001\u0011\r\u0001\r\u0002\u0002%B\u0011qDP\u0005\u0003\u007f\u0001\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002B\u0013:\u0011!i\u0012\b\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0003\u000bn\ta\u0001\u0010:p_Rt\u0014\"A\u0011\n\u0005!\u0003\u0013a\u00029bG.\fw-Z\u0005\u0003\u0015.\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0013\u0011\u0002\u0003\u0019,\u0012A\u0014\t\u0006?=csGO\u0005\u0003!\u0002\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\u0005\u0019\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002,)\")Aj\u0001a\u0001\u001d\u0006!1m\u001c9z+\u00119&\f\u00180\u0015\u0005a{\u0006#B\u0013\u00013nk\u0006CA\u0017[\t\u0015yCA1\u00011!\tiC\fB\u0003:\t\t\u0007\u0001\u0007\u0005\u0002.=\u0012)A\b\u0002b\u0001a!9A\n\u0002I\u0001\u0002\u0004\u0001\u0007#B\u0010P3nk\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0005G:|\u0007/F\u0001eU\tqUmK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111\u000eI\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006_\u0015\u0011\r\u0001\r\u0003\u0006s\u0015\u0011\r\u0001\r\u0003\u0006y\u0015\u0011\r\u0001M\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003M\u0004\"\u0001^=\u000e\u0003UT!A^<\u0002\t1\fgn\u001a\u0006\u0002q\u0006!!.\u0019<b\u0013\tQXO\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002{B\u0011qD`\u0005\u0003\u007f\u0002\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001NA\u0003\u0011!\t9\u0001CA\u0001\u0002\u0004i\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u000eA)\u0011qBA\u000bi5\u0011\u0011\u0011\u0003\u0006\u0004\u0003'\u0001\u0013AC2pY2,7\r^5p]&!\u0011qCA\t\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00111\u0005\t\u0004?\u0005}\u0011bAA\u0011A\t9!i\\8mK\u0006t\u0007\u0002CA\u0004\u0015\u0005\u0005\t\u0019\u0001\u001b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004g\u0006%\u0002\u0002CA\u0004\u0017\u0005\u0005\t\u0019A?\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!`\u0001\ti>\u001cFO]5oOR\t1/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003;\t9\u0004\u0003\u0005\u0002\b9\t\t\u00111\u00015\u000359&/\u00199qK\u0012,f)\u001e8deA\u0011Q\u0005E\n\u0005!y\ty\u0004\u0005\u0003\u0002B\u0005\u001dSBAA\"\u0015\r\t)e^\u0001\u0003S>L1ASA\")\t\tY$A\u0003baBd\u00170\u0006\u0005\u0002P\u0005U\u0013\u0011LA/)\u0011\t\t&a\u0018\u0011\u0011\u0015\u0002\u00111KA,\u00037\u00022!LA+\t\u0015y3C1\u00011!\ri\u0013\u0011\f\u0003\u0006sM\u0011\r\u0001\r\t\u0004[\u0005uC!\u0002\u001f\u0014\u0005\u0004\u0001\u0004B\u0002'\u0014\u0001\u0004\t\t\u0007\u0005\u0005 \u001f\u0006M\u0013qKA.\u0003\u001d)h.\u00199qYf,\u0002\"a\u001a\u0002t\u0005]\u00141\u0010\u000b\u0005\u0003S\ni\bE\u0003 \u0003W\ny'C\u0002\u0002n\u0001\u0012aa\u00149uS>t\u0007\u0003C\u0010P\u0003c\n)(!\u001f\u0011\u00075\n\u0019\bB\u00030)\t\u0007\u0001\u0007E\u0002.\u0003o\"Q!\u000f\u000bC\u0002A\u00022!LA>\t\u0015aDC1\u00011\u0011%\ty\bFA\u0001\u0002\u0004\t\t)A\u0002yIA\u0002\u0002\"\n\u0001\u0002r\u0005U\u0014\u0011P\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u000f\u00032\u0001^AE\u0013\r\tY)\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class WrappedUFunc2 implements VariableUFunc, Product, Serializable {
   private final Function2 f;

   public static Option unapply(final WrappedUFunc2 x$0) {
      return WrappedUFunc2$.MODULE$.unapply(x$0);
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

   public Function2 f() {
      return this.f;
   }

   public WrappedUFunc2 copy(final Function2 f) {
      return new WrappedUFunc2(f);
   }

   public Function2 copy$default$1() {
      return this.f();
   }

   public String productPrefix() {
      return "WrappedUFunc2";
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
      return x$1 instanceof WrappedUFunc2;
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
            if (x$1 instanceof WrappedUFunc2) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     WrappedUFunc2 var4 = (WrappedUFunc2)x$1;
                     Function2 var10000 = this.f();
                     Function2 var5 = var4.f();
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

   public WrappedUFunc2(final Function2 f) {
      this.f = f;
      VariableUFunc.$init$(this);
      Product.$init$(this);
   }
}
