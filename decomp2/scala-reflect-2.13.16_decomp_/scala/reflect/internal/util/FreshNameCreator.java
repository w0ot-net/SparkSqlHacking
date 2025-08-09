package scala.reflect.internal.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import scala.reflect.ScalaSignature;
import scala.reflect.NameTransformer.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001B\u000b\u0017\u0001}A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006a\u0001!\t!\r\u0005\bk\u0001\u0011\r\u0011\"\u00057\u0011\u00191\u0005\u0001)A\u0005o!)q\t\u0001C\u0001\u0011\")1\n\u0001C\u0001\u0019\")\u0001\r\u0001C\u0005C\")!\u000e\u0001C\u0005W\u001a!q\n\u0001\u0002Q\u0011!\t\u0016B!A!\u0002\u0013)\u0003\u0002\u0003*\n\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000bAJA\u0011A*\t\u000bYKA\u0011A,\t\u000bmKA\u0011\u0001/\t\u000b\u001dKA\u0011\u00010\t\u000b5\u0004A\u0011\u00018\b\u000fQ4\u0012\u0011!E\u0001k\u001a9QCFA\u0001\u0012\u00031\b\"\u0002\u0019\u0013\t\u00039\bb\u0002=\u0013#\u0003%\t!\u001f\u0002\u0011\rJ,7\u000f\u001b(b[\u0016\u001c%/Z1u_JT!a\u0006\r\u0002\tU$\u0018\u000e\u001c\u0006\u00033i\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00037q\tqA]3gY\u0016\u001cGOC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0011\u0011\u0005\u0005\u0012S\"\u0001\u000f\n\u0005\rb\"AB!osJ+g-A\u0007de\u0016\fGo\u001c:Qe\u00164\u0017\u000e\u001f\t\u0003M5r!aJ\u0016\u0011\u0005!bR\"A\u0015\u000b\u0005)r\u0012A\u0002\u001fs_>$h(\u0003\u0002-9\u00051\u0001K]3eK\u001aL!AL\u0018\u0003\rM#(/\u001b8h\u0015\taC$\u0001\u0004=S:LGO\u0010\u000b\u0003eQ\u0002\"a\r\u0001\u000e\u0003YAq\u0001\n\u0002\u0011\u0002\u0003\u0007Q%\u0001\u0005d_VtG/\u001a:t+\u00059\u0004\u0003\u0002\u001d?K\u0001k\u0011!\u000f\u0006\u0003um\n!bY8oGV\u0014(/\u001a8u\u0015\t9BHC\u0001>\u0003\u0011Q\u0017M^1\n\u0005}J$!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB\u0011\u0011\tR\u0007\u0002\u0005*\u00111)O\u0001\u0007CR|W.[2\n\u0005\u0015\u0013%AC!u_6L7\rT8oO\u0006I1m\\;oi\u0016\u00148\u000fI\u0001\b]\u0016<h*Y7f)\t)\u0013\nC\u0003K\u000b\u0001\u0007Q%\u0001\u0004qe\u00164\u0017\u000e_\u0001\u000f]\u0016<h*Y7f\r\u0006\u001cGo\u001c:z)\tiu\f\u0005\u0002O\u00135\t\u0001AA\u0006OC6,g)Y2u_JL8CA\u0005!\u0003)\u0019\u0018MZ3Qe\u00164\u0017\u000e_\u0001\bG>,h\u000e^3s)\riE+\u0016\u0005\u0006#2\u0001\r!\n\u0005\u0006%2\u0001\r\u0001Q\u0001\u0006S:$W\r\u001f\u000b\u00021B\u0011\u0011%W\u0005\u00035r\u0011A\u0001T8oO\u0006qa.Z<OC6,\u0017\t^%oI\u0016DHCA\u0013^\u0011\u00151f\u00021\u0001Y)\u0005)\u0003\"\u0002&\u0007\u0001\u0004)\u0013\u0001C1tg\u0016l'\r\\3\u0015\u0007\t<\u0007\u000e\u0005\u0002dM6\tAM\u0003\u0002fy\u0005!A.\u00198h\u0013\tqC\rC\u0003R\u000f\u0001\u0007Q\u0005C\u0003j\u000f\u0001\u0007\u0001,A\u0002jIb\fq\"\u00197m_\u000e\fG/Z\"pk:$XM\u001d\u000b\u0003\u00012DQ!\u0015\u0005A\u0002\u0015\nQ\u0002Z3dS6\fG\u000eT3oORDGCA8s!\t\t\u0003/\u0003\u0002r9\t\u0019\u0011J\u001c;\t\u000bM\u0004\u0002\u0019\u0001-\u0002\u0003%\f\u0001C\u0012:fg\"t\u0015-\\3De\u0016\fGo\u001c:\u0011\u0005M\u00122C\u0001\n!)\u0005)\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'F\u0001{U\t)3pK\u0001}!\ri\u0018QA\u0007\u0002}*\u0019q0!\u0001\u0002\u0013Ut7\r[3dW\u0016$'bAA\u00029\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u001daPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class FreshNameCreator {
   private final String creatorPrefix;
   private final ConcurrentHashMap counters;

   public static String $lessinit$greater$default$1() {
      FreshNameCreator$ var10000 = FreshNameCreator$.MODULE$;
      return "";
   }

   public ConcurrentHashMap counters() {
      return this.counters;
   }

   public String newName(final String prefix) {
      String safePrefix = .MODULE$.encode(prefix);
      long idx = this.allocateCounter(safePrefix).incrementAndGet();
      return this.scala$reflect$internal$util$FreshNameCreator$$assemble(safePrefix, idx);
   }

   public NameFactory newNameFactory(final String prefix) {
      String safePrefix = .MODULE$.encode(prefix);
      AtomicLong counter = this.allocateCounter(safePrefix);
      return new NameFactory(safePrefix, counter);
   }

   public String scala$reflect$internal$util$FreshNameCreator$$assemble(final String safePrefix, final long idx) {
      StringBuilder result = new StringBuilder(this.creatorPrefix.length() + safePrefix.length() + this.decimalLength(idx));
      result.append(this.creatorPrefix);
      result.append(safePrefix);
      result.append(idx);
      return (new StringBuilder(0)).append(this.creatorPrefix).append(safePrefix).append(idx).toString();
   }

   private AtomicLong allocateCounter(final String safePrefix) {
      return (AtomicLong)this.counters().computeIfAbsent(safePrefix, (s) -> new AtomicLong(0L));
   }

   public int decimalLength(final long i) {
      if (i < 0L) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append(i).toString());
      } else {
         int ceiling = 10;
         int numDigits = 1;

         int MaxValueLength;
         for(MaxValueLength = 19; numDigits <= MaxValueLength; ceiling *= 10) {
            if (i < (long)ceiling) {
               return numDigits;
            }

            ++numDigits;
         }

         return MaxValueLength;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$decimalLength$1(final long i$1) {
      return i$1;
   }

   public FreshNameCreator(final String creatorPrefix) {
      this.creatorPrefix = creatorPrefix;
      this.counters = new ConcurrentHashMap();
   }

   public final class NameFactory {
      private final String safePrefix;
      private final AtomicLong counter;
      // $FF: synthetic field
      private final FreshNameCreator $outer;

      public long index() {
         return this.counter.incrementAndGet();
      }

      public String newNameAtIndex(final long index) {
         return this.$outer.scala$reflect$internal$util$FreshNameCreator$$assemble(this.safePrefix, index);
      }

      public String newName() {
         return this.newNameAtIndex(this.index());
      }

      public NameFactory(final String safePrefix, final AtomicLong counter) {
         this.safePrefix = safePrefix;
         this.counter = counter;
         if (FreshNameCreator.this == null) {
            throw null;
         } else {
            this.$outer = FreshNameCreator.this;
            super();
         }
      }
   }
}
