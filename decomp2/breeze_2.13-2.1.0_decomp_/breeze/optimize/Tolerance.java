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
   bytes = "\u0006\u0005\u0005md\u0001B\u000f\u001f\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005a\b\u0003\u0005E\u0001\tE\t\u0015!\u0003@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001dI\u0006!!A\u0005\u0002iCq!\u0018\u0001\u0012\u0002\u0013\u0005a\fC\u0004j\u0001E\u0005I\u0011\u00010\t\u000f)\u0004\u0011\u0011!C!W\"9A\u000fAA\u0001\n\u0003)\bbB=\u0001\u0003\u0003%\tA\u001f\u0005\n\u0003\u0003\u0001\u0011\u0011!C!\u0003\u0007A\u0011\"!\u0005\u0001\u0003\u0003%\t!a\u0005\t\u0013\u0005u\u0001!!A\u0005B\u0005}\u0001\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\t9\u0003AA\u0001\n\u0003\nIcB\u0005\u0002.y\t\t\u0011#\u0001\u00020\u0019AQDHA\u0001\u0012\u0003\t\t\u0004\u0003\u0004F'\u0011\u0005\u0011\u0011\n\u0005\n\u0003\u0017\u001a\u0012\u0011!C#\u0003\u001bB\u0001BS\n\u0002\u0002\u0013\u0005\u0015q\n\u0005\t\u0003+\u001a\u0012\u0013!C\u0001=\"A\u0011qK\n\u0012\u0002\u0013\u0005a\fC\u0005\u0002ZM\t\t\u0011\"!\u0002\\!A\u0011QN\n\u0012\u0002\u0013\u0005a\f\u0003\u0005\u0002pM\t\n\u0011\"\u0001_\u0011%\t\thEA\u0001\n\u0013\t\u0019HA\u0005U_2,'/\u00198dK*\u0011q\u0004I\u0001\t_B$\u0018.\\5{K*\t\u0011%\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0015\u0001AE\u000b\u00182!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u0002=%\u0011QF\b\u0002\u0013\u001fB$\u0018.\\5{CRLwN\\(qi&|g\u000e\u0005\u0002&_%\u0011\u0001G\n\u0002\b!J|G-^2u!\t\u0011$H\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011aGI\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u000f\u0014\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003s\u0019\nQB\u001a<bYR{G.\u001a:b]\u000e,W#A \u0011\u0005\u0015\u0002\u0015BA!'\u0005\u0019!u.\u001e2mK\u0006qaM^1m)>dWM]1oG\u0016\u0004\u0013!D4wC2$v\u000e\\3sC:\u001cW-\u0001\bhm\u0006dGk\u001c7fe\u0006t7-\u001a\u0011\u0002\rqJg.\u001b;?)\r9\u0005*\u0013\t\u0003W\u0001Aq!P\u0003\u0011\u0002\u0003\u0007q\bC\u0004D\u000bA\u0005\t\u0019A \u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00051;\u0006CA'U\u001d\tq%K\u0004\u0002P#:\u0011A\u0007U\u0005\u0002C%\u0011q\u0004I\u0005\u0003'z\t1CR5sgR|%\u000fZ3s\u001b&t\u0017.\\5{KJL!!\u0016,\u0003\u0013=\u0003H\u000fU1sC6\u001c(BA*\u001f\u0011\u0015Af\u00011\u0001M\u0003\u0019\u0001\u0018M]1ng\u0006!1m\u001c9z)\r95\f\u0018\u0005\b{\u001d\u0001\n\u00111\u0001@\u0011\u001d\u0019u\u0001%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001`U\ty\u0004mK\u0001b!\t\u0011w-D\u0001d\u0015\t!W-A\u0005v]\u000eDWmY6fI*\u0011aMJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00015d\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\u000e\u0005\u0002ne6\taN\u0003\u0002pa\u0006!A.\u00198h\u0015\u0005\t\u0018\u0001\u00026bm\u0006L!a\u001d8\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u00051\bCA\u0013x\u0013\tAhEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002|}B\u0011Q\u0005`\u0005\u0003{\u001a\u00121!\u00118z\u0011\u001dyH\"!AA\u0002Y\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0003!\u0015\t9!!\u0004|\u001b\t\tIAC\u0002\u0002\f\u0019\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty!!\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003+\tY\u0002E\u0002&\u0003/I1!!\u0007'\u0005\u001d\u0011un\u001c7fC:Dqa \b\u0002\u0002\u0003\u000710\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00017\u0002\"!9qpDA\u0001\u0002\u00041\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Y\fa!Z9vC2\u001cH\u0003BA\u000b\u0003WAqa`\t\u0002\u0002\u0003\u000710A\u0005U_2,'/\u00198dKB\u00111fE\n\u0006'\u0005M\u0012q\b\t\b\u0003k\tYdP H\u001b\t\t9DC\u0002\u0002:\u0019\nqA];oi&lW-\u0003\u0003\u0002>\u0005]\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011IA$\u001b\t\t\u0019EC\u0002\u0002FA\f!![8\n\u0007m\n\u0019\u0005\u0006\u0002\u00020\u0005AAo\\*ue&tw\rF\u0001m)\u00159\u0015\u0011KA*\u0011\u001did\u0003%AA\u0002}Bqa\u0011\f\u0011\u0002\u0003\u0007q(A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003;\nI\u0007E\u0003&\u0003?\n\u0019'C\u0002\u0002b\u0019\u0012aa\u00149uS>t\u0007#B\u0013\u0002f}z\u0014bAA4M\t1A+\u001e9mKJB\u0001\"a\u001b\u001a\u0003\u0003\u0005\raR\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00022!\\A<\u0013\r\tIH\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class Tolerance implements OptimizationOption, Product, Serializable {
   private final double fvalTolerance;
   private final double gvalTolerance;

   public static double $lessinit$greater$default$2() {
      return Tolerance$.MODULE$.$lessinit$greater$default$2();
   }

   public static double $lessinit$greater$default$1() {
      return Tolerance$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final Tolerance x$0) {
      return Tolerance$.MODULE$.unapply(x$0);
   }

   public static double apply$default$2() {
      return Tolerance$.MODULE$.apply$default$2();
   }

   public static double apply$default$1() {
      return Tolerance$.MODULE$.apply$default$1();
   }

   public static Function1 tupled() {
      return Tolerance$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Tolerance$.MODULE$.curried();
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

   public double fvalTolerance() {
      return this.fvalTolerance;
   }

   public double gvalTolerance() {
      return this.gvalTolerance;
   }

   public FirstOrderMinimizer.OptParams apply(final FirstOrderMinimizer.OptParams params) {
      double x$1 = this.fvalTolerance();
      int x$2 = params.copy$default$1();
      double x$3 = params.copy$default$2();
      double x$4 = params.copy$default$3();
      int x$5 = params.copy$default$4();
      boolean x$6 = params.copy$default$5();
      boolean x$7 = params.copy$default$7();
      int x$8 = params.copy$default$8();
      return params.copy(x$2, x$3, x$4, x$5, x$6, x$1, x$7, x$8);
   }

   public Tolerance copy(final double fvalTolerance, final double gvalTolerance) {
      return new Tolerance(fvalTolerance, gvalTolerance);
   }

   public double copy$default$1() {
      return this.fvalTolerance();
   }

   public double copy$default$2() {
      return this.gvalTolerance();
   }

   public String productPrefix() {
      return "Tolerance";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.fvalTolerance());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.gvalTolerance());
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
      return x$1 instanceof Tolerance;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "fvalTolerance";
            break;
         case 1:
            var10000 = "gvalTolerance";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.fvalTolerance()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.gvalTolerance()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Tolerance) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Tolerance var4 = (Tolerance)x$1;
               if (this.fvalTolerance() == var4.fvalTolerance() && this.gvalTolerance() == var4.gvalTolerance() && var4.canEqual(this)) {
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

   public Tolerance(final double fvalTolerance, final double gvalTolerance) {
      this.fvalTolerance = fvalTolerance;
      this.gvalTolerance = gvalTolerance;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
