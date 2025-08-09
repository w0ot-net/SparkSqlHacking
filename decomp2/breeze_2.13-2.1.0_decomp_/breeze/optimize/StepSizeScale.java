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
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!)!\t\u0001C\u0001\u0007\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\t\u0011\"\u0011b\u0011\u001dQ\u0007!!A\u0005\u0002-Dqa\u001c\u0001\u0002\u0002\u0013\u0005\u0001\u000fC\u0004w\u0001\u0005\u0005I\u0011I<\t\u000fy\u0004\u0011\u0011!C\u0001\u007f\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\b\u0013\u0005e\u0011$!A\t\u0002\u0005ma\u0001\u0003\r\u001a\u0003\u0003E\t!!\b\t\ry\u0002B\u0011AA\u001b\u0011%\t9\u0004EA\u0001\n\u000b\nI\u0004\u0003\u0005C!\u0005\u0005I\u0011QA\u001e\u0011!\ty\u0004EI\u0001\n\u0003)\u0006\"CA!!\u0005\u0005I\u0011QA\"\u0011!\ty\u0005EI\u0001\n\u0003)\u0006\"CA)!\u0005\u0005I\u0011BA*\u00055\u0019F/\u001a9TSj,7kY1mK*\u0011!dG\u0001\t_B$\u0018.\\5{K*\tA$\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0015\u0001q$J\u0015-!\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u0019\te.\u001f*fMB\u0011aeJ\u0007\u00023%\u0011\u0001&\u0007\u0002\u0013\u001fB$\u0018.\\5{CRLwN\\(qi&|g\u000e\u0005\u0002!U%\u00111&\t\u0002\b!J|G-^2u!\tiSG\u0004\u0002/g9\u0011qFM\u0007\u0002a)\u0011\u0011'H\u0001\u0007yI|w\u000e\u001e \n\u0003\tJ!\u0001N\u0011\u0002\u000fA\f7m[1hK&\u0011ag\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003i\u0005\nQ!\u00197qQ\u0006,\u0012A\u000f\t\u0003AmJ!\u0001P\u0011\u0003\r\u0011{WO\u00197f\u0003\u0019\tG\u000e\u001d5bA\u00051A(\u001b8jiz\"\"\u0001Q!\u0011\u0005\u0019\u0002\u0001b\u0002\u001d\u0004!\u0003\u0005\rAO\u0001\u0006CB\u0004H.\u001f\u000b\u0003\t>\u0003\"!\u0012'\u000f\u0005\u0019SeBA$J\u001d\ty\u0003*C\u0001\u001d\u0013\tQ2$\u0003\u0002L3\u0005\u0019b)\u001b:ti>\u0013H-\u001a:NS:LW.\u001b>fe&\u0011QJ\u0014\u0002\n\u001fB$\b+\u0019:b[NT!aS\r\t\u000bA#\u0001\u0019\u0001#\u0002\rA\f'/Y7t\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0001\u001b\u0006b\u0002\u001d\u0006!\u0003\u0005\rAO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051&F\u0001\u001eXW\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003%)hn\u00195fG.,GM\u0003\u0002^C\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}S&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\fA\u0001\\1oO*\tq-\u0001\u0003kCZ\f\u0017BA5e\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA\u000e\u0005\u0002![&\u0011a.\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003cR\u0004\"\u0001\t:\n\u0005M\f#aA!os\"9Q/CA\u0001\u0002\u0004a\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001y!\rIH0]\u0007\u0002u*\u001110I\u0001\u000bG>dG.Z2uS>t\u0017BA?{\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0005\u0011q\u0001\t\u0004A\u0005\r\u0011bAA\u0003C\t9!i\\8mK\u0006t\u0007bB;\f\u0003\u0003\u0005\r!]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002c\u0003\u001bAq!\u001e\u0007\u0002\u0002\u0003\u0007A.\u0001\u0005iCND7i\u001c3f)\u0005a\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0002\u0005]\u0001bB;\u000f\u0003\u0003\u0005\r!]\u0001\u000e'R,\u0007oU5{KN\u001b\u0017\r\\3\u0011\u0005\u0019\u00022#\u0002\t\u0002 \u0005-\u0002CBA\u0011\u0003OQ\u0004)\u0004\u0002\u0002$)\u0019\u0011QE\u0011\u0002\u000fI,h\u000e^5nK&!\u0011\u0011FA\u0012\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003[\t\u0019$\u0004\u0002\u00020)\u0019\u0011\u0011\u00074\u0002\u0005%|\u0017b\u0001\u001c\u00020Q\u0011\u00111D\u0001\ti>\u001cFO]5oOR\t!\rF\u0002A\u0003{Aq\u0001O\n\u0011\u0002\u0003\u0007!(A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132\u0003\u001d)h.\u00199qYf$B!!\u0012\u0002LA!\u0001%a\u0012;\u0013\r\tI%\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u00055S#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%c\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u000b\t\u0004G\u0006]\u0013bAA-I\n1qJ\u00196fGR\u0004"
)
public class StepSizeScale implements OptimizationOption, Product, Serializable {
   private final double alpha;

   public static double $lessinit$greater$default$1() {
      return StepSizeScale$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final StepSizeScale x$0) {
      return StepSizeScale$.MODULE$.unapply(x$0);
   }

   public static double apply$default$1() {
      return StepSizeScale$.MODULE$.apply$default$1();
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

   public double alpha() {
      return this.alpha;
   }

   public FirstOrderMinimizer.OptParams apply(final FirstOrderMinimizer.OptParams params) {
      double x$1 = this.alpha();
      int x$2 = params.copy$default$1();
      double x$3 = params.copy$default$2();
      int x$4 = params.copy$default$4();
      boolean x$5 = params.copy$default$5();
      double x$6 = params.copy$default$6();
      boolean x$7 = params.copy$default$7();
      int x$8 = params.copy$default$8();
      return params.copy(x$2, x$3, x$1, x$4, x$5, x$6, x$7, x$8);
   }

   public StepSizeScale copy(final double alpha) {
      return new StepSizeScale(alpha);
   }

   public double copy$default$1() {
      return this.alpha();
   }

   public String productPrefix() {
      return "StepSizeScale";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.alpha());
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
      return x$1 instanceof StepSizeScale;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "alpha";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.alpha()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof StepSizeScale) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               StepSizeScale var4 = (StepSizeScale)x$1;
               if (this.alpha() == var4.alpha() && var4.canEqual(this)) {
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

   public StepSizeScale(final double alpha) {
      this.alpha = alpha;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
