package breeze.optimize;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\f\u0018\u0001rA\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\tw\u0001\u0011\t\u0012)A\u0005q!)A\b\u0001C\u0001{!)\u0001\t\u0001C\u0001\u0003\"9q\nAA\u0001\n\u0003\u0001\u0006b\u0002*\u0001#\u0003%\ta\u0015\u0005\b=\u0002\t\t\u0011\"\u0011`\u0011\u001dA\u0007!!A\u0005\u0002]Bq!\u001b\u0001\u0002\u0002\u0013\u0005!\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nIaB\u0005\u0002\u000e]\t\t\u0011#\u0001\u0002\u0010\u0019AacFA\u0001\u0012\u0003\t\t\u0002\u0003\u0004=!\u0011\u0005\u0011\u0011\u0006\u0005\n\u0003W\u0001\u0012\u0011!C#\u0003[A\u0001\u0002\u0011\t\u0002\u0002\u0013\u0005\u0015q\u0006\u0005\n\u0003g\u0001\u0012\u0011!CA\u0003kA\u0011\"!\u0011\u0011\u0003\u0003%I!a\u0011\u0003\u0013\t\u000bGo\u00195TSj,'B\u0001\r\u001a\u0003!y\u0007\u000f^5nSj,'\"\u0001\u000e\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001A\u000f$O)\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u0013&\u001b\u00059\u0012B\u0001\u0014\u0018\u0005Iy\u0005\u000f^5nSj\fG/[8o\u001fB$\u0018n\u001c8\u0011\u0005yA\u0013BA\u0015 \u0005\u001d\u0001&o\u001c3vGR\u0004\"aK\u001a\u000f\u00051\ndBA\u00171\u001b\u0005q#BA\u0018\u001c\u0003\u0019a$o\\8u}%\t\u0001%\u0003\u00023?\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001b6\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011t$\u0001\u0003tSj,W#\u0001\u001d\u0011\u0005yI\u0014B\u0001\u001e \u0005\rIe\u000e^\u0001\u0006g&TX\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005yz\u0004C\u0001\u0013\u0001\u0011\u001514\u00011\u00019\u0003\u0015\t\u0007\u000f\u001d7z)\t\u0011U\n\u0005\u0002D\u0015:\u0011A\t\u0013\b\u0003\u000b\u001es!!\f$\n\u0003iI!\u0001G\r\n\u0005%;\u0012a\u0005$jeN$xJ\u001d3fe6Kg.[7ju\u0016\u0014\u0018BA&M\u0005%y\u0005\u000f\u001e)be\u0006l7O\u0003\u0002J/!)a\n\u0002a\u0001\u0005\u00061\u0001/\u0019:b[N\fAaY8qsR\u0011a(\u0015\u0005\bm\u0015\u0001\n\u00111\u00019\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0016\u0016\u0003qU[\u0013A\u0016\t\u0003/rk\u0011\u0001\u0017\u0006\u00033j\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005m{\u0012AC1o]>$\u0018\r^5p]&\u0011Q\f\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001a!\t\tg-D\u0001c\u0015\t\u0019G-\u0001\u0003mC:<'\"A3\u0002\t)\fg/Y\u0005\u0003O\n\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003W:\u0004\"A\b7\n\u00055|\"aA!os\"9q.CA\u0001\u0002\u0004A\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001s!\r\u0019ho[\u0007\u0002i*\u0011QoH\u0001\u000bG>dG.Z2uS>t\u0017BA<u\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005il\bC\u0001\u0010|\u0013\taxDA\u0004C_>dW-\u00198\t\u000f=\\\u0011\u0011!a\u0001W\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0001\u0017\u0011\u0001\u0005\b_2\t\t\u00111\u00019\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\u001d\u0002\r\u0015\fX/\u00197t)\rQ\u00181\u0002\u0005\b_:\t\t\u00111\u0001l\u0003%\u0011\u0015\r^2i'&TX\r\u0005\u0002%!M)\u0001#a\u0005\u0002 A1\u0011QCA\u000eqyj!!a\u0006\u000b\u0007\u0005eq$A\u0004sk:$\u0018.\\3\n\t\u0005u\u0011q\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0011\u0003Oi!!a\t\u000b\u0007\u0005\u0015B-\u0001\u0002j_&\u0019A'a\t\u0015\u0005\u0005=\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0001$2APA\u0019\u0011\u001514\u00031\u00019\u0003\u001d)h.\u00199qYf$B!a\u000e\u0002>A!a$!\u000f9\u0013\r\tYd\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005}B#!AA\u0002y\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0005E\u0002b\u0003\u000fJ1!!\u0013c\u0005\u0019y%M[3di\u0002"
)
public class BatchSize implements OptimizationOption, Product, Serializable {
   private final int size;

   public static Option unapply(final BatchSize x$0) {
      return BatchSize$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public int size() {
      return this.size;
   }

   public FirstOrderMinimizer.OptParams apply(final FirstOrderMinimizer.OptParams params) {
      return params.copy(this.size(), params.copy$default$2(), params.copy$default$3(), params.copy$default$4(), params.copy$default$5(), params.copy$default$6(), params.copy$default$7(), params.copy$default$8());
   }

   public BatchSize copy(final int size) {
      return new BatchSize(size);
   }

   public int copy$default$1() {
      return this.size();
   }

   public String productPrefix() {
      return "BatchSize";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.size());
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
      return x$1 instanceof BatchSize;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "size";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.size());
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof BatchSize) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               BatchSize var4 = (BatchSize)x$1;
               if (this.size() == var4.size() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public BatchSize(final int size) {
      this.size = size;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
