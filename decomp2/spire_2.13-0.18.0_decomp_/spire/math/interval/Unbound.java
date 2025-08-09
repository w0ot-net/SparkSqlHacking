package spire.math.interval;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeSemigroup;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001B\n\u0015\u0001nAQ!\u0011\u0001\u0005\u0002\tCq\u0001\u0012\u0001\u0002\u0002\u0013\u0005Q\tC\u0004K\u0001\u0005\u0005I\u0011I&\t\u000fQ\u0003\u0011\u0011!C\u0001+\"9\u0011\fAA\u0001\n\u0003Q\u0006bB/\u0001\u0003\u0003%\tE\u0018\u0005\bK\u0002\t\t\u0011\"\u0001g\u0011\u001dY\u0007!!A\u0005B1DqA\u001c\u0001\u0002\u0002\u0013\u0005s\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fI\u0004\u0011\u0011!C!g\u001e9Q\u000fFA\u0001\u0012\u00031haB\n\u0015\u0003\u0003E\ta\u001e\u0005\u0006\u00036!\t! \u0005\ba6\t\t\u0011\"\u0012r\u0011\u001dqX\"!A\u0005\u0002~D\u0011\"!\u0003\u000e\u0003\u0003%\t)a\u0003\t\u0013\u0005eQ\"!A\u0005\n\u0005m!aB+oE>,h\u000e\u001a\u0006\u0003+Y\t\u0001\"\u001b8uKJ4\u0018\r\u001c\u0006\u0003/a\tA!\\1uQ*\t\u0011$A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005qI3#\u0002\u0001\u001eGI*\u0004C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\rE\u0002%K\u001dj\u0011\u0001F\u0005\u0003MQ\u0011QAQ8v]\u0012\u0004\"\u0001K\u0015\r\u0001\u0011)!\u0006\u0001b\u0001W\t\t\u0011)\u0005\u0002-_A\u0011a$L\u0005\u0003]}\u0011qAT8uQ&tw\r\u0005\u0002\u001fa%\u0011\u0011g\b\u0002\u0004\u0003:L\bC\u0001\u00104\u0013\t!tDA\u0004Qe>$Wo\u0019;\u0011\u0005YrdBA\u001c=\u001d\tA4(D\u0001:\u0015\tQ$$\u0001\u0004=e>|GOP\u0005\u0002A%\u0011QhH\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002>?\u00051A(\u001b8jiz\"\u0012a\u0011\t\u0004I\u00019\u0013\u0001B2paf,\"AR%\u0015\u0003\u001d\u00032\u0001\n\u0001I!\tA\u0013\nB\u0003+\u0005\t\u00071&A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0019B\u0011QJU\u0007\u0002\u001d*\u0011q\nU\u0001\u0005Y\u0006twMC\u0001R\u0003\u0011Q\u0017M^1\n\u0005Ms%AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001W!\tqr+\u0003\u0002Y?\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qf\u0017\u0005\b9\u0016\t\t\u00111\u0001W\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\fE\u0002aG>j\u0011!\u0019\u0006\u0003E~\t!bY8mY\u0016\u001cG/[8o\u0013\t!\u0017M\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA4k!\tq\u0002.\u0003\u0002j?\t9!i\\8mK\u0006t\u0007b\u0002/\b\u0003\u0003\u0005\raL\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002M[\"9A\fCA\u0001\u0002\u00041\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Y\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0019\u00061Q-];bYN$\"a\u001a;\t\u000fq[\u0011\u0011!a\u0001_\u00059QK\u001c2pk:$\u0007C\u0001\u0013\u000e'\riQ\u0004\u001f\t\u0003srl\u0011A\u001f\u0006\u0003wB\u000b!![8\n\u0005}RH#\u0001<\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005\u0005\u0011q\u0001\u000b\u0003\u0003\u0007\u0001B\u0001\n\u0001\u0002\u0006A\u0019\u0001&a\u0002\u0005\u000b)\u0002\"\u0019A\u0016\u0002\u000fUt\u0017\r\u001d9msV!\u0011QBA\f)\r9\u0017q\u0002\u0005\n\u0003#\t\u0012\u0011!a\u0001\u0003'\t1\u0001\u001f\u00131!\u0011!\u0003!!\u0006\u0011\u0007!\n9\u0002B\u0003+#\t\u00071&\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001eA\u0019Q*a\b\n\u0007\u0005\u0005bJ\u0001\u0004PE*,7\r\u001e"
)
public class Unbound implements Bound, Product, Serializable {
   public static boolean unapply(final Unbound x$0) {
      return Unbound$.MODULE$.unapply(x$0);
   }

   public static Unbound apply() {
      return Unbound$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Bound map(final Function1 f) {
      return Bound.map$(this, f);
   }

   public Bound combine(final Bound rhs, final Function2 f) {
      return Bound.combine$(this, rhs, f);
   }

   public Bound unary_$minus(final AdditiveGroup ev) {
      return Bound.unary_$minus$(this, ev);
   }

   public Bound reciprocal(final MultiplicativeGroup ev) {
      return Bound.reciprocal$(this, ev);
   }

   public Bound $plus(final Object a, final AdditiveSemigroup ev) {
      return Bound.$plus$(this, (Object)a, ev);
   }

   public Bound $minus(final Object a, final AdditiveGroup ev) {
      return Bound.$minus$(this, (Object)a, ev);
   }

   public Bound $times(final Object a, final MultiplicativeSemigroup ev) {
      return Bound.$times$(this, (Object)a, ev);
   }

   public Bound $div(final Object a, final MultiplicativeGroup ev) {
      return Bound.$div$(this, (Object)a, ev);
   }

   public Bound $plus(final Bound rhs, final AdditiveSemigroup ev) {
      return Bound.$plus$(this, (Bound)rhs, ev);
   }

   public Bound $minus(final Bound rhs, final AdditiveGroup ev) {
      return Bound.$minus$(this, (Bound)rhs, ev);
   }

   public Bound $times(final Bound rhs, final MultiplicativeSemigroup ev) {
      return Bound.$times$(this, (Bound)rhs, ev);
   }

   public Bound $div(final Bound rhs, final MultiplicativeGroup ev) {
      return Bound.$div$(this, (Bound)rhs, ev);
   }

   public Unbound copy() {
      return new Unbound();
   }

   public String productPrefix() {
      return "Unbound";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Unbound;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof Unbound) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((Unbound)x$1).canEqual(this);
   }

   public Unbound() {
      Bound.$init$(this);
      Product.$init$(this);
   }
}
