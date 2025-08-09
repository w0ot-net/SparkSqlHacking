package spire.math.interval;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeSemigroup;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\f\u0019\u0001~A\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005W!)\u0001\n\u0001C\u0001\u0013\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bM\u0002\t\t\u0011\"\u0011h\u0011\u001d\u0001\b!!A\u0005\u0002EDq!\u001e\u0001\u0002\u0002\u0013\u0005a\u000fC\u0004z\u0001\u0005\u0005I\u0011\t>\t\u0013\u0005\r\u0001!!A\u0005\u0002\u0005\u0015\u0001\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011D\u0004\n\u0003;A\u0012\u0011!E\u0001\u0003?1\u0001b\u0006\r\u0002\u0002#\u0005\u0011\u0011\u0005\u0005\u0007\u0011F!\t!!\f\t\u0013\u0005M\u0011#!A\u0005F\u0005U\u0001\"CA\u0018#\u0005\u0005I\u0011QA\u0019\u0011%\ti$EA\u0001\n\u0003\u000by\u0004C\u0005\u0002TE\t\t\u0011\"\u0003\u0002V\t!q\n]3o\u0015\tI\"$\u0001\u0005j]R,'O^1m\u0015\tYB$\u0001\u0003nCRD'\"A\u000f\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011\u0001%L\n\u0006\u0001\u0005:c'\u000f\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007!J3&D\u0001\u0019\u0013\tQ\u0003D\u0001\u0006WC2,XMQ8v]\u0012\u0004\"\u0001L\u0017\r\u0001\u0011)a\u0006\u0001b\u0001_\t\t\u0011)\u0005\u00021gA\u0011!%M\u0005\u0003e\r\u0012qAT8uQ&tw\r\u0005\u0002#i%\u0011Qg\t\u0002\u0004\u0003:L\bC\u0001\u00128\u0013\tA4EA\u0004Qe>$Wo\u0019;\u0011\u0005i\u0012eBA\u001eA\u001d\tat(D\u0001>\u0015\tqd$\u0001\u0004=e>|GOP\u0005\u0002I%\u0011\u0011iI\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019EI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002BG\u0005\t\u0011-F\u0001,\u0003\t\t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0015.\u00032\u0001\u000b\u0001,\u0011\u0015)5\u00011\u0001,\u0003!I7o\u00117pg\u0016$W#\u0001(\u0011\u0005\tz\u0015B\u0001)$\u0005\u001d\u0011un\u001c7fC:\fAaY8qsV\u00111K\u0016\u000b\u0003)^\u00032\u0001\u000b\u0001V!\tac\u000bB\u0003/\u000b\t\u0007q\u0006C\u0004F\u000bA\u0005\t\u0019A+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!,Z\u000b\u00027*\u00121\u0006X\u0016\u0002;B\u0011alY\u0007\u0002?*\u0011\u0001-Y\u0001\nk:\u001c\u0007.Z2lK\u0012T!AY\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002e?\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b92!\u0019A\u0018\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0007CA5o\u001b\u0005Q'BA6m\u0003\u0011a\u0017M\\4\u000b\u00035\fAA[1wC&\u0011qN\u001b\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003I\u0004\"AI:\n\u0005Q\u001c#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA\u001ax\u0011\u001dA\u0018\"!AA\u0002I\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A>\u0011\u0007q|8'D\u0001~\u0015\tq8%\u0001\u0006d_2dWm\u0019;j_:L1!!\u0001~\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u00079\u000b9\u0001C\u0004y\u0017\u0005\u0005\t\u0019A\u001a\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Q\u00065\u0001b\u0002=\r\u0003\u0003\u0005\rA]\u0001\tQ\u0006\u001c\bnQ8eKR\t!/\u0001\u0005u_N#(/\u001b8h)\u0005A\u0017AB3rk\u0006d7\u000fF\u0002O\u00037Aq\u0001_\b\u0002\u0002\u0003\u00071'\u0001\u0003Pa\u0016t\u0007C\u0001\u0015\u0012'\u0011\t\u0012%a\t\u0011\t\u0005\u0015\u00121F\u0007\u0003\u0003OQ1!!\u000bm\u0003\tIw.C\u0002D\u0003O!\"!a\b\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005M\u0012\u0011\b\u000b\u0005\u0003k\tY\u0004\u0005\u0003)\u0001\u0005]\u0002c\u0001\u0017\u0002:\u0011)a\u0006\u0006b\u0001_!1Q\t\u0006a\u0001\u0003o\tq!\u001e8baBd\u00170\u0006\u0003\u0002B\u0005-C\u0003BA\"\u0003\u001b\u0002RAIA#\u0003\u0013J1!a\u0012$\u0005\u0019y\u0005\u000f^5p]B\u0019A&a\u0013\u0005\u000b9*\"\u0019A\u0018\t\u0013\u0005=S#!AA\u0002\u0005E\u0013a\u0001=%aA!\u0001\u0006AA%\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0006E\u0002j\u00033J1!a\u0017k\u0005\u0019y%M[3di\u0002"
)
public class Open implements ValueBound, Product, Serializable {
   private final Object a;

   public static Option unapply(final Open x$0) {
      return Open$.MODULE$.unapply(x$0);
   }

   public static Open apply(final Object a) {
      return Open$.MODULE$.apply(a);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ValueBound unary_$minus(final AdditiveGroup ev) {
      return ValueBound.unary_$minus$(this, ev);
   }

   public ValueBound reciprocal(final MultiplicativeGroup ev) {
      return ValueBound.reciprocal$(this, ev);
   }

   public ValueBound $plus$tilde(final ValueBound rhs, final AdditiveSemigroup ev) {
      return ValueBound.$plus$tilde$(this, rhs, ev);
   }

   public ValueBound $minus$tilde(final ValueBound rhs, final AdditiveGroup ev) {
      return ValueBound.$minus$tilde$(this, rhs, ev);
   }

   public ValueBound $times$tilde(final ValueBound rhs, final MultiplicativeSemigroup ev) {
      return ValueBound.$times$tilde$(this, rhs, ev);
   }

   public ValueBound $div$tilde(final ValueBound rhs, final MultiplicativeGroup ev) {
      return ValueBound.$div$tilde$(this, rhs, ev);
   }

   public Bound map(final Function1 f) {
      return Bound.map$(this, f);
   }

   public Bound combine(final Bound rhs, final Function2 f) {
      return Bound.combine$(this, rhs, f);
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

   public Object a() {
      return this.a;
   }

   public boolean isClosed() {
      return false;
   }

   public Open copy(final Object a) {
      return new Open(a);
   }

   public Object copy$default$1() {
      return this.a();
   }

   public String productPrefix() {
      return "Open";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.a();
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
      return x$1 instanceof Open;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "a";
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
         label49: {
            boolean var2;
            if (x$1 instanceof Open) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Open var4 = (Open)x$1;
               if (BoxesRunTime.equals(this.a(), var4.a()) && var4.canEqual(this)) {
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

   public Open(final Object a) {
      this.a = a;
      Bound.$init$(this);
      ValueBound.$init$(this);
      Product.$init$(this);
   }
}
