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
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\f\u0018\u0001rA\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\tw\u0001\u0011\t\u0012)A\u0005q!)A\b\u0001C\u0001{!)\u0001\t\u0001C\u0001\u0003\"9q\nAA\u0001\n\u0003\u0001\u0006b\u0002*\u0001#\u0003%\ta\u0015\u0005\b=\u0002\t\t\u0011\"\u0011`\u0011\u001dA\u0007!!A\u0005\u0002]Bq!\u001b\u0001\u0002\u0002\u0013\u0005!\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nIaB\u0005\u0002\u000e]\t\t\u0011#\u0001\u0002\u0010\u0019AacFA\u0001\u0012\u0003\t\t\u0002\u0003\u0004=!\u0011\u0005\u0011\u0011\u0006\u0005\n\u0003W\u0001\u0012\u0011!C#\u0003[A\u0001\u0002\u0011\t\u0002\u0002\u0013\u0005\u0015q\u0006\u0005\n\u0003g\u0001\u0012\u0011!CA\u0003kA\u0011\"!\u0011\u0011\u0003\u0003%I!a\u0011\u0003\u001b5\u000b\u00070\u0013;fe\u0006$\u0018n\u001c8t\u0015\tA\u0012$\u0001\u0005paRLW.\u001b>f\u0015\u0005Q\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u000b\u0001i2e\n\u0016\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\t!S%D\u0001\u0018\u0013\t1sC\u0001\nPaRLW.\u001b>bi&|gn\u00149uS>t\u0007C\u0001\u0010)\u0013\tIsDA\u0004Qe>$Wo\u0019;\u0011\u0005-\u001adB\u0001\u00172\u001d\ti\u0003'D\u0001/\u0015\ty3$\u0001\u0004=e>|GOP\u0005\u0002A%\u0011!gH\u0001\ba\u0006\u001c7.Y4f\u0013\t!TG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00023?\u0005\u0019a.^7\u0016\u0003a\u0002\"AH\u001d\n\u0005iz\"aA%oi\u0006!a.^7!\u0003\u0019a\u0014N\\5u}Q\u0011ah\u0010\t\u0003I\u0001AQAN\u0002A\u0002a\nQ!\u00199qYf$\"AQ'\u0011\u0005\rSeB\u0001#I\u001d\t)uI\u0004\u0002.\r&\t!$\u0003\u0002\u00193%\u0011\u0011jF\u0001\u0014\r&\u00148\u000f^(sI\u0016\u0014X*\u001b8j[&TXM]\u0005\u0003\u00172\u0013\u0011b\u00149u!\u0006\u0014\u0018-\\:\u000b\u0005%;\u0002\"\u0002(\u0005\u0001\u0004\u0011\u0015A\u00029be\u0006l7/\u0001\u0003d_BLHC\u0001 R\u0011\u001d1T\u0001%AA\u0002a\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001UU\tATkK\u0001W!\t9F,D\u0001Y\u0015\tI&,A\u0005v]\u000eDWmY6fI*\u00111lH\u0001\u000bC:tw\u000e^1uS>t\u0017BA/Y\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0001\u0004\"!\u00194\u000e\u0003\tT!a\u00193\u0002\t1\fgn\u001a\u0006\u0002K\u0006!!.\u0019<b\u0013\t9'M\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tYg\u000e\u0005\u0002\u001fY&\u0011Qn\b\u0002\u0004\u0003:L\bbB8\n\u0003\u0003\u0005\r\u0001O\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003I\u00042a\u001d<l\u001b\u0005!(BA; \u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003oR\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011!0 \t\u0003=mL!\u0001`\u0010\u0003\u000f\t{w\u000e\\3b]\"9qnCA\u0001\u0002\u0004Y\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001YA\u0001\u0011\u001dyG\"!AA\u0002a\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002q\u00051Q-];bYN$2A_A\u0006\u0011\u001dyg\"!AA\u0002-\fQ\"T1y\u0013R,'/\u0019;j_:\u001c\bC\u0001\u0013\u0011'\u0015\u0001\u00121CA\u0010!\u0019\t)\"a\u00079}5\u0011\u0011q\u0003\u0006\u0004\u00033y\u0012a\u0002:v]RLW.Z\u0005\u0005\u0003;\t9BA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\t\u0002(5\u0011\u00111\u0005\u0006\u0004\u0003K!\u0017AA5p\u0013\r!\u00141\u0005\u000b\u0003\u0003\u001f\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002AR\u0019a(!\r\t\u000bY\u001a\u0002\u0019\u0001\u001d\u0002\u000fUt\u0017\r\u001d9msR!\u0011qGA\u001f!\u0011q\u0012\u0011\b\u001d\n\u0007\u0005mrD\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u007f!\u0012\u0011!a\u0001}\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0003cA1\u0002H%\u0019\u0011\u0011\n2\u0003\r=\u0013'.Z2u\u0001"
)
public class MaxIterations implements OptimizationOption, Product, Serializable {
   private final int num;

   public static Option unapply(final MaxIterations x$0) {
      return MaxIterations$.MODULE$.unapply(x$0);
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

   public int num() {
      return this.num;
   }

   public FirstOrderMinimizer.OptParams apply(final FirstOrderMinimizer.OptParams params) {
      int x$1 = this.num();
      int x$2 = params.copy$default$1();
      double x$3 = params.copy$default$2();
      double x$4 = params.copy$default$3();
      boolean x$5 = params.copy$default$5();
      double x$6 = params.copy$default$6();
      boolean x$7 = params.copy$default$7();
      int x$8 = params.copy$default$8();
      return params.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8);
   }

   public MaxIterations copy(final int num) {
      return new MaxIterations(num);
   }

   public int copy$default$1() {
      return this.num();
   }

   public String productPrefix() {
      return "MaxIterations";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.num());
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
      return x$1 instanceof MaxIterations;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "num";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.num());
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof MaxIterations) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               MaxIterations var4 = (MaxIterations)x$1;
               if (this.num() == var4.num() && var4.canEqual(this)) {
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

   public MaxIterations(final int num) {
      this.num = num;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
