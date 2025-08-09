package spire.math;

import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.interval.Closed;
import spire.math.interval.Open;
import spire.math.interval.ValueBound;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ue\u0001B\u000f\u001f\u0001\u000eB\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005S!A\u0011\n\u0001BK\u0002\u0013\u0005q\t\u0003\u0005K\u0001\tE\t\u0015!\u0003*\u0011!Y\u0005A!f\u0001\n\u0003a\u0005\u0002\u0003)\u0001\u0005#\u0005\u000b\u0011B'\t\rE\u0003A\u0011\u0001\u0011S\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015y\u0006\u0001\"\u0001Y\u0011\u001d\u0001\u0007!!A\u0005\u0002\u0005Dq!\u001b\u0001\u0012\u0002\u0013\u0005!\u000eC\u0004x\u0001E\u0005I\u0011\u0001=\t\u000fi\u0004\u0011\u0013!C\u0001w\"Aq\u0010AA\u0001\n\u0003\n\t\u0001\u0003\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001M\u0011%\t)\u0002AA\u0001\n\u0003\t9\u0002C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0011\u0002 !I\u0011Q\u0006\u0001\u0002\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003wA\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\t\u0013\u0005\r\u0003!!A\u0005B\u0005\u0015s!CA%=\u0005\u0005\t\u0012AA&\r!ib$!A\t\u0002\u00055\u0003BB)\u0018\t\u0003\ty\u0006C\u0005\u0002b]\t\t\u0011\"\u0012\u0002d!I\u0011QM\f\u0002\u0002\u0013\u0005\u0015q\r\u0005\n\u0003o:\u0012\u0011!CA\u0003sB\u0011\"a%\u0018\u0003\u0003%I!!&\u0003\u000f\t{WO\u001c3fI*\u0011q\u0004I\u0001\u0005[\u0006$\bNC\u0001\"\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"\u0001J\u0016\u0014\t\u0001)sG\u000f\t\u0004M\u001dJS\"\u0001\u0010\n\u0005!r\"\u0001C%oi\u0016\u0014h/\u00197\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0001\u0011\r!\f\u0002\u0002\u0003F\u0011a\u0006\u000e\t\u0003_Ij\u0011\u0001\r\u0006\u0002c\u0005)1oY1mC&\u00111\u0007\r\u0002\b\u001d>$\b.\u001b8h!\tyS'\u0003\u00027a\t\u0019\u0011I\\=\u0011\u0005=B\u0014BA\u001d1\u0005\u001d\u0001&o\u001c3vGR\u0004\"aO\"\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA #\u0003\u0019a$o\\8u}%\t\u0011'\u0003\u0002Ca\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011\u0005'A\u0003m_^,'/F\u0001*\u0003\u0019awn^3sA\u0005)Q\u000f\u001d9fe\u00061Q\u000f\u001d9fe\u0002\nQA\u001a7bON,\u0012!\u0014\t\u0003_9K!a\u0014\u0019\u0003\u0007%sG/\u0001\u0004gY\u0006<7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tM#VK\u0016\t\u0004M\u0001I\u0003\"\u0002$\b\u0001\u0004I\u0003\"B%\b\u0001\u0004I\u0003\"B&\b\u0001\u0004i\u0015A\u00037po\u0016\u0014(i\\;oIV\t\u0011\fE\u0002[;&j\u0011a\u0017\u0006\u00039z\t\u0001\"\u001b8uKJ4\u0018\r\\\u0005\u0003=n\u0013!BV1mk\u0016\u0014u.\u001e8e\u0003))\b\u000f]3s\u0005>,h\u000eZ\u0001\u0005G>\u0004\u00180\u0006\u0002cKR!1MZ4i!\r1\u0003\u0001\u001a\t\u0003U\u0015$Q\u0001\f\u0006C\u00025BqA\u0012\u0006\u0011\u0002\u0003\u0007A\rC\u0004J\u0015A\u0005\t\u0019\u00013\t\u000f-S\u0001\u0013!a\u0001\u001b\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCA6w+\u0005a'FA\u0015nW\u0005q\u0007CA8u\u001b\u0005\u0001(BA9s\u0003%)hn\u00195fG.,GM\u0003\u0002ta\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005U\u0004(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)Af\u0003b\u0001[\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCA6z\t\u0015aCB1\u0001.\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"\u0001 @\u0016\u0003uT#!T7\u0005\u000b1j!\u0019A\u0017\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005=QBAA\u0004\u0015\u0011\tI!a\u0003\u0002\t1\fgn\u001a\u0006\u0003\u0003\u001b\tAA[1wC&!\u0011\u0011CA\u0004\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001\u001b\u0002\u001a!A\u00111\u0004\t\u0002\u0002\u0003\u0007Q*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003C\u0001R!a\t\u0002*Qj!!!\n\u000b\u0007\u0005\u001d\u0002'\u0001\u0006d_2dWm\u0019;j_:LA!a\u000b\u0002&\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t$a\u000e\u0011\u0007=\n\u0019$C\u0002\u00026A\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u001cI\t\t\u00111\u00015\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\r\u0011Q\b\u0005\t\u00037\u0019\u0012\u0011!a\u0001\u001b\u0006A\u0001.Y:i\u0007>$W\rF\u0001N\u0003\u0019)\u0017/^1mgR!\u0011\u0011GA$\u0011!\tY\"FA\u0001\u0002\u0004!\u0014a\u0002\"pk:$W\r\u001a\t\u0003M]\u0019RaFA(\u0003+\u00022aLA)\u0013\r\t\u0019\u0006\r\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005]\u0013QL\u0007\u0003\u00033RA!a\u0017\u0002\f\u0005\u0011\u0011n\\\u0005\u0004\t\u0006eCCAA&\u0003!!xn\u0015;sS:<GCAA\u0002\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\tI'a\u001c\u0015\u0011\u0005-\u0014\u0011OA:\u0003k\u0002BA\n\u0001\u0002nA\u0019!&a\u001c\u0005\u000b1R\"\u0019A\u0017\t\r\u0019S\u0002\u0019AA7\u0011\u0019I%\u00041\u0001\u0002n!)1J\u0007a\u0001\u001b\u00069QO\\1qa2LX\u0003BA>\u0003\u0017#B!! \u0002\u000eB)q&a \u0002\u0004&\u0019\u0011\u0011\u0011\u0019\u0003\r=\u0003H/[8o!!y\u0013QQAE\u0003\u0013k\u0015bAADa\t1A+\u001e9mKN\u00022AKAF\t\u0015a3D1\u0001.\u0011%\tyiGA\u0001\u0002\u0004\t\t*A\u0002yIA\u0002BA\n\u0001\u0002\n\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0013\t\u0005\u0003\u000b\tI*\u0003\u0003\u0002\u001c\u0006\u001d!AB(cU\u0016\u001cG\u000f"
)
public class Bounded extends Interval implements Product {
   private final Object lower;
   private final Object upper;
   private final int flags;

   public static Option unapply(final Bounded x$0) {
      return Bounded$.MODULE$.unapply(x$0);
   }

   public static Bounded apply(final Object lower, final Object upper, final int flags) {
      return Bounded$.MODULE$.apply(lower, upper, flags);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object lower() {
      return this.lower;
   }

   public Object upper() {
      return this.upper;
   }

   public int flags() {
      return this.flags;
   }

   public ValueBound lowerBound() {
      return (ValueBound)(this.isOpenLower(this.flags()) ? new Open(this.lower()) : new Closed(this.lower()));
   }

   public ValueBound upperBound() {
      return (ValueBound)(this.isOpenUpper(this.flags()) ? new Open(this.upper()) : new Closed(this.upper()));
   }

   public Bounded copy(final Object lower, final Object upper, final int flags) {
      return new Bounded(lower, upper, flags);
   }

   public Object copy$default$1() {
      return this.lower();
   }

   public Object copy$default$2() {
      return this.upper();
   }

   public int copy$default$3() {
      return this.flags();
   }

   public String productPrefix() {
      return "Bounded";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.lower();
            break;
         case 1:
            var10000 = this.upper();
            break;
         case 2:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
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
      return x$1 instanceof Bounded;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "lower";
            break;
         case 1:
            var10000 = "upper";
            break;
         case 2:
            var10000 = "flags";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.lower()));
      var1 = Statics.mix(var1, Statics.anyHash(this.upper()));
      var1 = Statics.mix(var1, this.flags());
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof Bounded) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Bounded var4 = (Bounded)x$1;
               if (this.flags() == var4.flags() && BoxesRunTime.equals(this.lower(), var4.lower()) && BoxesRunTime.equals(this.upper(), var4.upper()) && var4.canEqual(this)) {
                  break label53;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Bounded(final Object lower, final Object upper, final int flags) {
      this.lower = lower;
      this.upper = upper;
      this.flags = flags;
      Product.$init$(this);
   }
}
