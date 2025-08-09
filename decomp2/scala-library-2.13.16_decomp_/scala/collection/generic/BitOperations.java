package scala.collection.generic;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntegralProxy;
import scala.runtime.RichInt$;
import scala.runtime.RichLong;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-tAB\u0012%\u0011\u00031#F\u0002\u0004-I!\u0005a%\f\u0005\u0006e\u0005!\t\u0001\u000e\u0004\bk\u0005\u0001\n1!\u00017\u0011\u001594\u0001\"\u00019\u000b\u0011)4\u0001\u0001\u001f\t\u000by\u001aA\u0011A \t\u000b!\u001bA\u0011A%\t\u000b1\u001bA\u0011A'\t\u000bQ\u001bA\u0011A+\t\u000be\u001bA\u0011\u0001.\t\u000b}\u001bA\u0011\u00011\t\u000b\t\u001cA\u0011A2\t\u000b1\u001cA\u0011A7\t\u000fq\u001c\u0011\u0013!C\u0001{\"9\u0011\u0011C\u0002\u0005\u0002\u0005MqaBA\f\u0003!\u0005\u0011\u0011\u0004\u0004\u0007k\u0005A\t!!\b\t\rI\nB\u0011AA\u0011\r%\t\u0019#\u0001I\u0001\u0004\u0003\t)\u0003C\u00038'\u0011\u0005\u0001(\u0002\u0004\u0002$M\u0001\u0011q\u0005\u0005\u0007}M!\t!a\u000b\t\r!\u001bB\u0011AA\u001b\u0011\u0019a5\u0003\"\u0001\u0002<!1Ak\u0005C\u0001\u0003\u0007Ba!W\n\u0005\u0002\u0005%\u0003BB0\u0014\t\u0003\ty\u0005\u0003\u0004c'\u0011\u0005\u00111\u000b\u0005\u0007YN!\t!a\u0016\t\u000fq\u001c\u0012\u0013!C\u0001{\"9\u0011\u0011C\n\u0005\u0002\u0005usaBA1\u0003!\u0005\u00111\r\u0004\b\u0003G\t\u0001\u0012AA3\u0011\u0019\u0011\u0014\u0005\"\u0001\u0002j\u0005i!)\u001b;Pa\u0016\u0014\u0018\r^5p]NT!!\n\u0014\u0002\u000f\u001d,g.\u001a:jG*\u0011q\u0005K\u0001\u000bG>dG.Z2uS>t'\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\u0011\u0005-\nQ\"\u0001\u0013\u0003\u001b\tKGo\u00149fe\u0006$\u0018n\u001c8t'\t\ta\u0006\u0005\u00020a5\t\u0001&\u0003\u00022Q\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002U\t\u0019\u0011J\u001c;\u0014\u0005\rq\u0013A\u0002\u0013j]&$H\u0005F\u0001:!\ty#(\u0003\u0002<Q\t!QK\\5u!\tyS(\u0003\u00026Q\u0005!!0\u001a:p)\r\u00015i\u0012\t\u0003_\u0005K!A\u0011\u0015\u0003\u000f\t{w\u000e\\3b]\")AI\u0002a\u0001\u000b\u0006\t\u0011\u000e\u0005\u0002G\u000b5\t1\u0001C\u0003I\r\u0001\u0007Q)\u0001\u0003nCN\\Gc\u0001\u001fK\u0017\")Ai\u0002a\u0001\u000b\")\u0001j\u0002a\u0001\u000b\u0006A\u0001.Y:NCR\u001c\u0007\u000e\u0006\u0003A\u001dB\u0013\u0006\"B(\t\u0001\u0004)\u0015aA6fs\")\u0011\u000b\u0003a\u0001\u000b\u00061\u0001O]3gSbDQa\u0015\u0005A\u0002\u0015\u000b\u0011!\\\u0001\u0010k:\u001c\u0018n\u001a8fI\u000e{W\u000e]1sKR\u0019\u0001IV,\t\u000b\u0011K\u0001\u0019A#\t\u000baK\u0001\u0019A#\u0002\u0003)\fqa\u001d5peR,'\u000fF\u0002A7vCQ\u0001\u0018\u0006A\u0002\u0015\u000b!!\\\u0019\t\u000byS\u0001\u0019A#\u0002\u00055\u0014\u0014AC2p[BdW-\\3oiR\u0011A(\u0019\u0005\u0006\t.\u0001\r!R\u0001\u0005E&$8\u000f\u0006\u0002eUB\u0019Q\r\u001b!\u000e\u0003\u0019T!a\u001a\u0014\u0002\u0013%lW.\u001e;bE2,\u0017BA5g\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\u0006W2\u0001\r!R\u0001\u0004]Vl\u0017!\u00032jiN#(/\u001b8h)\rq\u0017P\u001f\t\u0003_Zt!\u0001\u001d;\u0011\u0005EDS\"\u0001:\u000b\u0005M\u001c\u0014A\u0002\u001fs_>$h(\u0003\u0002vQ\u00051\u0001K]3eK\u001aL!a\u001e=\u0003\rM#(/\u001b8h\u0015\t)\b\u0006C\u0003l\u001b\u0001\u0007Q\tC\u0004|\u001bA\u0005\t\u0019\u00018\u0002\u0007M,\u0007/A\ncSR\u001cFO]5oO\u0012\"WMZ1vYR$#'F\u0001\u007fU\tqwp\u000b\u0002\u0002\u0002A!\u00111AA\u0007\u001b\t\t)A\u0003\u0003\u0002\b\u0005%\u0011!C;oG\",7m[3e\u0015\r\tY\u0001K\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\b\u0003\u000b\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035A\u0017n\u001a5fgR|e.\u001a\"jiR\u0019A(!\u0006\t\u000ba{\u0001\u0019A#\u0002\u0007%sG\u000fE\u0002\u0002\u001cEi\u0011!A\n\u0005#9\ny\u0002E\u0002\u0002\u001c\r!\"!!\u0007\u0003\t1{gnZ\n\u0003'9\u00022aLA\u0015\u0013\r\t\u0019\u0003\u000b\u000b\u0006\u0001\u00065\u00121\u0007\u0005\u0007\tZ\u0001\r!a\f\u0011\u0007\u0005ER#D\u0001\u0014\u0011\u0019Ae\u00031\u0001\u00020Q1\u0011qEA\u001c\u0003sAa\u0001R\fA\u0002\u0005=\u0002B\u0002%\u0018\u0001\u0004\ty\u0003F\u0004A\u0003{\ty$!\u0011\t\r=C\u0002\u0019AA\u0018\u0011\u0019\t\u0006\u00041\u0001\u00020!11\u000b\u0007a\u0001\u0003_!R\u0001QA#\u0003\u000fBa\u0001R\rA\u0002\u0005=\u0002B\u0002-\u001a\u0001\u0004\ty\u0003F\u0003A\u0003\u0017\ni\u0005\u0003\u0004]5\u0001\u0007\u0011q\u0006\u0005\u0007=j\u0001\r!a\f\u0015\t\u0005\u001d\u0012\u0011\u000b\u0005\u0007\tn\u0001\r!a\f\u0015\u0007\u0011\f)\u0006\u0003\u0004l9\u0001\u0007\u0011q\u0006\u000b\u0006]\u0006e\u00131\f\u0005\u0007Wv\u0001\r!a\f\t\u000fml\u0002\u0013!a\u0001]R!\u0011qEA0\u0011\u0019Av\u00041\u0001\u00020\u0005!Aj\u001c8h!\r\tY\"I\n\u0005C9\n9\u0007E\u0002\u0002\u001cM!\"!a\u0019"
)
public final class BitOperations {
   public interface Int {
      default boolean zero(final int i, final int mask) {
         return (i & mask) == 0;
      }

      default int mask(final int i, final int mask) {
         return i & (this.complement(mask - 1) ^ mask);
      }

      default boolean hasMatch(final int key, final int prefix, final int m) {
         return this.mask(key, m) == prefix;
      }

      default boolean unsignedCompare(final int i, final int j) {
         return i < j ^ i < 0 ^ j < 0;
      }

      default boolean shorter(final int m1, final int m2) {
         return this.unsignedCompare(m2, m1);
      }

      default int complement(final int i) {
         return ~i;
      }

      default IndexedSeq bits(final int num) {
         RichInt$ var10000 = RichInt$.MODULE$;
         byte var2 = 31;
         int to$extension_end = 0;
         Range$ var10 = Range$.MODULE$;
         Range var11 = (new Range.Inclusive(var2, to$extension_end, 1)).by(-1);
         if (var11 == null) {
            throw null;
         } else {
            Range map_this = var11;
            map_this.scala$collection$immutable$Range$$validateMaxLength();
            Builder strictOptimizedMap_b = IndexedSeq$.MODULE$.newBuilder();

            Object var9;
            for(Iterator strictOptimizedMap_it = map_this.iterator(); strictOptimizedMap_it.hasNext(); var9 = null) {
               int var8 = BoxesRunTime.unboxToInt(strictOptimizedMap_it.next());
               Boolean strictOptimizedMap_$plus$eq_elem = BoxesRunTime.boxToBoolean($anonfun$bits$1(num, var8));
               if (strictOptimizedMap_b == null) {
                  throw null;
               }

               strictOptimizedMap_b.addOne(strictOptimizedMap_$plus$eq_elem);
            }

            return (IndexedSeq)strictOptimizedMap_b.result();
         }
      }

      default String bitString(final int num, final String sep) {
         IterableOnceOps var10000 = (IterableOnceOps)this.bits(num).map((b) -> $anonfun$bitString$1(BoxesRunTime.unboxToBoolean(b)));
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.mkString("", sep, "");
         }
      }

      default String bitString$default$2() {
         return "";
      }

      default int highestOneBit(final int j) {
         return Integer.highestOneBit(j);
      }

      // $FF: synthetic method
      static boolean $anonfun$bits$1(final int num$1, final int i) {
         return (num$1 >>> i & 1) != 0;
      }

      // $FF: synthetic method
      static String $anonfun$bitString$1(final boolean b) {
         return b ? "1" : "0";
      }

      static void $init$(final Int $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Int$ implements Int {
      public static final Int$ MODULE$ = new Int$();

      static {
         Int$ var10000 = MODULE$;
      }

      public boolean zero(final int i, final int mask) {
         return BitOperations.Int.super.zero(i, mask);
      }

      public int mask(final int i, final int mask) {
         return BitOperations.Int.super.mask(i, mask);
      }

      public boolean hasMatch(final int key, final int prefix, final int m) {
         return BitOperations.Int.super.hasMatch(key, prefix, m);
      }

      public boolean unsignedCompare(final int i, final int j) {
         return BitOperations.Int.super.unsignedCompare(i, j);
      }

      public boolean shorter(final int m1, final int m2) {
         return BitOperations.Int.super.shorter(m1, m2);
      }

      public int complement(final int i) {
         return BitOperations.Int.super.complement(i);
      }

      public IndexedSeq bits(final int num) {
         return BitOperations.Int.super.bits(num);
      }

      public String bitString(final int num, final String sep) {
         return BitOperations.Int.super.bitString(num, sep);
      }

      public String bitString$default$2() {
         return BitOperations.Int.super.bitString$default$2();
      }

      public int highestOneBit(final int j) {
         return BitOperations.Int.super.highestOneBit(j);
      }
   }

   public interface Long {
      default boolean zero(final long i, final long mask) {
         return (i & mask) == 0L;
      }

      default long mask(final long i, final long mask) {
         return i & (this.complement(mask - 1L) ^ mask);
      }

      default boolean hasMatch(final long key, final long prefix, final long m) {
         return this.mask(key, m) == prefix;
      }

      default boolean unsignedCompare(final long i, final long j) {
         return i < j ^ i < 0L ^ j < 0L;
      }

      default boolean shorter(final long m1, final long m2) {
         return this.unsignedCompare(m2, m1);
      }

      default long complement(final long i) {
         return ~i;
      }

      default IndexedSeq bits(final long num) {
         return (IndexedSeq)IntegralProxy.to$(new RichLong(63L), 0L).by(-1L).map((i) -> (num >>> (int)i & 1L) != 0L);
      }

      default String bitString(final long num, final String sep) {
         IterableOnceOps var10000 = (IterableOnceOps)this.bits(num).map((b) -> $anonfun$bitString$2(BoxesRunTime.unboxToBoolean(b)));
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.mkString("", sep, "");
         }
      }

      default String bitString$default$2() {
         return "";
      }

      default long highestOneBit(final long j) {
         return java.lang.Long.highestOneBit(j);
      }

      // $FF: synthetic method
      static String $anonfun$bitString$2(final boolean b) {
         return b ? "1" : "0";
      }

      static void $init$(final Long $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Long$ implements Long {
      public static final Long$ MODULE$ = new Long$();

      static {
         Long$ var10000 = MODULE$;
      }

      public boolean zero(final long i, final long mask) {
         return BitOperations.Long.super.zero(i, mask);
      }

      public long mask(final long i, final long mask) {
         return BitOperations.Long.super.mask(i, mask);
      }

      public boolean hasMatch(final long key, final long prefix, final long m) {
         return BitOperations.Long.super.hasMatch(key, prefix, m);
      }

      public boolean unsignedCompare(final long i, final long j) {
         return BitOperations.Long.super.unsignedCompare(i, j);
      }

      public boolean shorter(final long m1, final long m2) {
         return BitOperations.Long.super.shorter(m1, m2);
      }

      public long complement(final long i) {
         return BitOperations.Long.super.complement(i);
      }

      public IndexedSeq bits(final long num) {
         return BitOperations.Long.super.bits(num);
      }

      public String bitString(final long num, final String sep) {
         return BitOperations.Long.super.bitString(num, sep);
      }

      public String bitString$default$2() {
         return BitOperations.Long.super.bitString$default$2();
      }

      public long highestOneBit(final long j) {
         return BitOperations.Long.super.highestOneBit(j);
      }
   }
}
