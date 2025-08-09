package cats.kernel.compat;

import cats.kernel.Hash;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u000514Q\u0001C\u0005\u0001\u0017=AQA\u0006\u0001\u0005\u0002aAaa\u0007\u0001\u0005\u0002-a\u0002BB\u0018\u0001\t\u0003i\u0001\u0007\u0003\u00046\u0001\u0011\u0005QB\u000e\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u00067\u0002!\t\u0001\u0018\u0005\u0006Q\u0002!)\"\u001b\u0002\u000b\u0011\u0006\u001c\bnQ8na\u0006$(B\u0001\u0006\f\u0003\u0019\u0019w.\u001c9bi*\u0011A\"D\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u00039\tAaY1ugN\u0011\u0001\u0001\u0005\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\u0007\t\u00035\u0001i\u0011!C\u0001\u0017aJ|G-^2uc!\u000b7\u000f[,ji\"\u0004&/\u001a4jqR\u0019Q\u0004\t\u0012\u0011\u0005Eq\u0012BA\u0010\u0013\u0005\rIe\u000e\u001e\u0005\u0006C\t\u0001\r!H\u0001\u0007?FB\u0015m\u001d5\t\u000b\r\u0012\u0001\u0019\u0001\u0013\u0002\rA\u0014XMZ5y!\t)CF\u0004\u0002'UA\u0011qEE\u0007\u0002Q)\u0011\u0011fF\u0001\u0007yI|w\u000e\u001e \n\u0005-\u0012\u0012A\u0002)sK\u0012,g-\u0003\u0002.]\t11\u000b\u001e:j]\u001eT!a\u000b\n\u0002-A\u0014x\u000eZ;diJB\u0015m\u001d5XSRD\u0007K]3gSb$B!H\u00193i!)\u0011e\u0001a\u0001;!)1g\u0001a\u0001;\u00051qL\r%bg\"DQaI\u0002A\u0002\u0011\nA#\u001e9eCR,WK\\8sI\u0016\u0014X\r\u001a%bg\"\u001cEcA\u000f8s!)\u0001\b\u0002a\u0001;\u0005\t1\rC\u0003;\t\u0001\u0007Q$A\u0001i\u0003!a\u0017n\u001d;ICNDWCA\u001fH)\tq\u0004\u000b\u0006\u0002\u001e\u007f!)\u0001)\u0002a\u0002\u0003\u0006\t\u0011\tE\u0002C\u0007\u0016k\u0011aC\u0005\u0003\t.\u0011A\u0001S1tQB\u0011ai\u0012\u0007\u0001\t\u0015AUA1\u0001J\u0005\u0005\t\u0015C\u0001&N!\t\t2*\u0003\u0002M%\t9aj\u001c;iS:<\u0007CA\tO\u0013\ty%CA\u0002B]fDQ!U\u0003A\u0002I\u000b\u0011\u0001\u001f\t\u0004'b+eB\u0001+W\u001d\t9S+C\u0001\u0014\u0013\t9&#A\u0004qC\u000e\\\u0017mZ3\n\u0005eS&\u0001\u0002'jgRT!a\u0016\n\u0002\u0017=\u0014H-\u001a:fI\"\u000b7\u000f[\u000b\u0003;\n$\"AX2\u0015\u0005uy\u0006\"\u0002!\u0007\u0001\b\u0001\u0007c\u0001\"DCB\u0011aI\u0019\u0003\u0006\u0011\u001a\u0011\r!\u0013\u0005\u0006I\u001a\u0001\r!Z\u0001\u0003qN\u00042a\u00154b\u0013\t9'L\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0005bm\u0006d\u0017M\\2iKR\u0011QD\u001b\u0005\u0006W\u001e\u0001\r!H\u0001\u0005Q\u0006\u001c\b\u000e"
)
public class HashCompat {
   public int product1HashWithPrefix(final int _1Hash, final String prefix) {
      int h = -889275714;
      h = .MODULE$.mix(h, prefix.hashCode());
      h = .MODULE$.mix(h, _1Hash);
      return .MODULE$.finalizeHash(h, 1);
   }

   public int product2HashWithPrefix(final int _1Hash, final int _2Hash, final String prefix) {
      int h = -889275714;
      h = .MODULE$.mix(h, prefix.hashCode());
      h = .MODULE$.mix(h, _1Hash);
      h = .MODULE$.mix(h, _2Hash);
      return .MODULE$.finalizeHash(h, 2);
   }

   public int updateUnorderedHashC(final int c, final int h) {
      return c * (h | 1);
   }

   public int listHash(final List x, final Hash A) {
      int n = 0;
      int h = .MODULE$.seqSeed();
      int rangeState = 0;
      int rangeDiff = 0;
      int prev = 0;
      int initial = 0;

      List tail;
      for(List elems = x; !elems.isEmpty(); elems = tail) {
         Object head = elems.head();
         tail = (List)elems.tail();
         int hash = A.hash(head);
         h = .MODULE$.mix(h, hash);
         switch (rangeState) {
            case 0:
               initial = hash;
               rangeState = 1;
               break;
            case 1:
               rangeDiff = hash - prev;
               rangeState = 2;
               break;
            case 2:
               if (rangeDiff != hash - prev) {
                  rangeState = 3;
               }
         }

         prev = hash;
         ++n;
      }

      return rangeState == 2 ? .MODULE$.rangeHash(initial, rangeDiff, prev, .MODULE$.seqSeed()) : .MODULE$.finalizeHash(h, n);
   }

   public int orderedHash(final IterableOnce xs, final Hash A) {
      Iterator it = xs.iterator();
      int h = .MODULE$.seqSeed();
      if (!it.hasNext()) {
         return .MODULE$.finalizeHash(h, 0);
      } else {
         Object x0 = it.next();
         if (!it.hasNext()) {
            return .MODULE$.finalizeHash(.MODULE$.mix(h, A.hash(x0)), 1);
         } else {
            Object x1 = it.next();
            int initial = A.hash(x0);
            h = .MODULE$.mix(h, initial);
            int h0 = h;
            int prev = A.hash(x1);
            int rangeDiff = prev - initial;

            for(int i = 2; it.hasNext(); ++i) {
               h = .MODULE$.mix(h, prev);
               int hash = A.hash(it.next());
               if (rangeDiff != hash - prev) {
                  h = .MODULE$.mix(h, hash);
                  ++i;

                  while(it.hasNext()) {
                     h = .MODULE$.mix(h, A.hash(it.next()));
                     ++i;
                  }

                  return .MODULE$.finalizeHash(h, i);
               }

               prev = hash;
            }

            return this.avalanche(.MODULE$.mix(.MODULE$.mix(h0, rangeDiff), prev));
         }
      }
   }

   public final int avalanche(final int hash) {
      int h = hash ^ hash >>> 16;
      h *= -2048144789;
      h ^= h >>> 13;
      h *= -1028477387;
      h ^= h >>> 16;
      return h;
   }
}
