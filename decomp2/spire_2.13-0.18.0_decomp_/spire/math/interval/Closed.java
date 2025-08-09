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
   bytes = "\u0006\u0005\u0005uc\u0001B\f\u0019\u0001~A\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005W!)\u0001\n\u0001C\u0001\u0013\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bM\u0002\t\t\u0011\"\u0011h\u0011\u001d\u0001\b!!A\u0005\u0002EDq!\u001e\u0001\u0002\u0002\u0013\u0005a\u000fC\u0004z\u0001\u0005\u0005I\u0011\t>\t\u0013\u0005\r\u0001!!A\u0005\u0002\u0005\u0015\u0001\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011D\u0004\n\u0003;A\u0012\u0011!E\u0001\u0003?1\u0001b\u0006\r\u0002\u0002#\u0005\u0011\u0011\u0005\u0005\u0007\u0011F!\t!!\f\t\u0013\u0005M\u0011#!A\u0005F\u0005U\u0001\"CA\u0018#\u0005\u0005I\u0011QA\u0019\u0011%\ti$EA\u0001\n\u0003\u000by\u0004C\u0005\u0002TE\t\t\u0011\"\u0003\u0002V\t11\t\\8tK\u0012T!!\u0007\u000e\u0002\u0011%tG/\u001a:wC2T!a\u0007\u000f\u0002\t5\fG\u000f\u001b\u0006\u0002;\u0005)1\u000f]5sK\u000e\u0001QC\u0001\u0011.'\u0015\u0001\u0011e\n\u001c:!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0019\u0001&K\u0016\u000e\u0003aI!A\u000b\r\u0003\u0015Y\u000bG.^3C_VtG\r\u0005\u0002-[1\u0001A!\u0002\u0018\u0001\u0005\u0004y#!A!\u0012\u0005A\u001a\u0004C\u0001\u00122\u0013\t\u00114EA\u0004O_RD\u0017N\\4\u0011\u0005\t\"\u0014BA\u001b$\u0005\r\te.\u001f\t\u0003E]J!\u0001O\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011!H\u0011\b\u0003w\u0001s!\u0001P \u000e\u0003uR!A\u0010\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0013BA!$\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0011#\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0005\u001b\u0013!A1\u0016\u0003-\n!!\u0019\u0011\u0002\rqJg.\u001b;?)\tQ5\nE\u0002)\u0001-BQ!R\u0002A\u0002-\n\u0001\"[:DY>\u001cX\rZ\u000b\u0002\u001dB\u0011!eT\u0005\u0003!\u000e\u0012qAQ8pY\u0016\fg.\u0001\u0003d_BLXCA*W)\t!v\u000bE\u0002)\u0001U\u0003\"\u0001\f,\u0005\u000b9*!\u0019A\u0018\t\u000f\u0015+\u0001\u0013!a\u0001+\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001.f+\u0005Y&FA\u0016]W\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003%)hn\u00195fG.,GM\u0003\u0002cG\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011|&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)aF\u0002b\u0001_\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001b\t\u0003S:l\u0011A\u001b\u0006\u0003W2\fA\u0001\\1oO*\tQ.\u0001\u0003kCZ\f\u0017BA8k\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\u000f\u0005\u0002#g&\u0011Ao\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003g]Dq\u0001_\u0005\u0002\u0002\u0003\u0007!/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002wB\u0019Ap`\u001a\u000e\u0003uT!A`\u0012\u0002\u0015\r|G\u000e\\3di&|g.C\u0002\u0002\u0002u\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a*a\u0002\t\u000fa\\\u0011\u0011!a\u0001g\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rA\u0017Q\u0002\u0005\bq2\t\t\u00111\u0001s\u0003!A\u0017m\u001d5D_\u0012,G#\u0001:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001[\u0001\u0007KF,\u0018\r\\:\u0015\u00079\u000bY\u0002C\u0004y\u001f\u0005\u0005\t\u0019A\u001a\u0002\r\rcwn]3e!\tA\u0013c\u0005\u0003\u0012C\u0005\r\u0002\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%B.\u0001\u0002j_&\u00191)a\n\u0015\u0005\u0005}\u0011!B1qa2LX\u0003BA\u001a\u0003s!B!!\u000e\u0002<A!\u0001\u0006AA\u001c!\ra\u0013\u0011\b\u0003\u0006]Q\u0011\ra\f\u0005\u0007\u000bR\u0001\r!a\u000e\u0002\u000fUt\u0017\r\u001d9msV!\u0011\u0011IA&)\u0011\t\u0019%!\u0014\u0011\u000b\t\n)%!\u0013\n\u0007\u0005\u001d3E\u0001\u0004PaRLwN\u001c\t\u0004Y\u0005-C!\u0002\u0018\u0016\u0005\u0004y\u0003\"CA(+\u0005\u0005\t\u0019AA)\u0003\rAH\u0005\r\t\u0005Q\u0001\tI%\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002XA\u0019\u0011.!\u0017\n\u0007\u0005m#N\u0001\u0004PE*,7\r\u001e"
)
public class Closed implements ValueBound, Product, Serializable {
   private final Object a;

   public static Option unapply(final Closed x$0) {
      return Closed$.MODULE$.unapply(x$0);
   }

   public static Closed apply(final Object a) {
      return Closed$.MODULE$.apply(a);
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
      return true;
   }

   public Closed copy(final Object a) {
      return new Closed(a);
   }

   public Object copy$default$1() {
      return this.a();
   }

   public String productPrefix() {
      return "Closed";
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
      return x$1 instanceof Closed;
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
            if (x$1 instanceof Closed) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Closed var4 = (Closed)x$1;
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

   public Closed(final Object a) {
      this.a = a;
      Bound.$init$(this);
      ValueBound.$init$(this);
      Product.$init$(this);
   }
}
