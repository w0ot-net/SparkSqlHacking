package spire.math;

import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3A\u0001C\u0005\u0001\u001d!Aa\u0003\u0001B\u0001B\u0003%q\u0003\u0003\u0005#\u0001\t\u0005\t\u0015a\u0003$\u0011\u00159\u0003\u0001\"\u0001)\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u00159\u0005\u0001\"\u0001I\u0005-Ie\u000e^3he\u0006dw\n]:\u000b\u0005)Y\u0011\u0001B7bi\"T\u0011\u0001D\u0001\u0006gBL'/Z\u0002\u0001+\ty\u0011d\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\f1\u0001\u001c5t!\tA\u0012\u0004\u0004\u0001\u0005\u000bi\u0001!\u0019A\u000e\u0003\u0003\u0005\u000b\"\u0001H\u0010\u0011\u0005Ei\u0012B\u0001\u0010\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0005\u0011\n\u0005\u0005\u0012\"aA!os\u0006\u0011QM\u001e\t\u0004I\u0015:R\"A\u0005\n\u0005\u0019J!\u0001C%oi\u0016<'/\u00197\u0002\rqJg.\u001b;?)\tIC\u0006\u0006\u0002+WA\u0019A\u0005A\f\t\u000b\t\u001a\u00019A\u0012\t\u000bY\u0019\u0001\u0019A\f\u0002\u0015Q|7+\u00194f\u0019>tw-F\u00010!\t!\u0003'\u0003\u00022\u0013\tA1+\u00194f\u0019>tw-\u0001\u0004d_\u0016\u00148-\u001a\u000b\u0003i]\u0002\"!E\u001b\n\u0005Y\u0012\"\u0001\u0002'p]\u001eDQ\u0001O\u0003A\u0002]\t\u0011!Y\u0001\u0006I\t\fgnZ\u000b\u0002wA\u0011A\b\u0012\b\u0003{\ts!AP!\u000e\u0003}R!\u0001Q\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0012BA\"\u0013\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0012$\u0003\r\tKw-\u00138u\u0015\t\u0019%#\u0001\u0004dQ>|7/\u001a\u000b\u0003w%CQAS\u0004A\u0002]\t1A\u001d5t\u0001"
)
public class IntegralOps {
   private final Object lhs;
   private final Integral ev;

   public SafeLong toSafeLong() {
      return SafeLong$.MODULE$.apply(this.ev.toBigInt(this.lhs));
   }

   public long coerce(final Object a) {
      BigInt n = this.ev.toBigInt(a);
      if (.MODULE$.long2bigInt(Long.MIN_VALUE).$less$eq(n) && n.$less$eq(.MODULE$.long2bigInt(Long.MAX_VALUE))) {
         return this.ev.toLong(a);
      } else {
         throw new IllegalArgumentException((new StringBuilder(10)).append(this.lhs).append(" too large").toString());
      }
   }

   public BigInt $bang() {
      return package$.MODULE$.fact(this.coerce(this.lhs));
   }

   public BigInt choose(final Object rhs) {
      return package$.MODULE$.choose(this.coerce(this.lhs), this.coerce(rhs));
   }

   public IntegralOps(final Object lhs, final Integral ev) {
      this.lhs = lhs;
      this.ev = ev;
   }
}
