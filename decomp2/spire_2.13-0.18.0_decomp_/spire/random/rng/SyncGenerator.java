package spire.random.rng;

import scala.reflect.ScalaSignature;
import spire.random.Generator;

@ScalaSignature(
   bytes = "\u0006\u0005%3A!\u0004\b\u0003+!A!\u0004\u0001B\u0001B\u0003%a\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003#\u0001\u0011\u0005\u0013\u0005C\u0003$\u0001\u0011\u0005A\u0005C\u0003/\u0001\u0011\u0005q\u0006C\u00036\u0001\u0011\u0005a\u0007C\u0003;\u0001\u0011\u00051hB\u0003@\u001d!\u0005\u0001IB\u0003\u000e\u001d!\u0005\u0011\tC\u0003\u001c\u0015\u0011\u0005Q\tC\u0003G\u0015\u0011\u0005qIA\u0007Ts:\u001cw)\u001a8fe\u0006$xN\u001d\u0006\u0003\u001fA\t1A\u001d8h\u0015\t\t\"#\u0001\u0004sC:$w.\u001c\u0006\u0002'\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u0017!\t9\u0002$D\u0001\u0011\u0013\tI\u0002CA\u0005HK:,'/\u0019;pe\u0006\u0019q-\u001a8\u0002\rqJg.\u001b;?)\tir\u0004\u0005\u0002\u001f\u00015\ta\u0002C\u0003\u001b\u0005\u0001\u0007a#\u0001\u0005d_BL\u0018J\\5u+\u0005i\u0012\u0001B:z]\u000e\fAbZ3u'\u0016,GMQ=uKN,\u0012!\n\t\u0004M%ZS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0019b\u0013BA\u0017(\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005A\u001a\u0004C\u0001\u00142\u0013\t\u0011tE\u0001\u0003V]&$\b\"\u0002\u001b\u0007\u0001\u0004)\u0013!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002oA\u0011a\u0005O\u0005\u0003s\u001d\u00121!\u00138u\u0003!qW\r\u001f;M_:<G#\u0001\u001f\u0011\u0005\u0019j\u0014B\u0001 (\u0005\u0011auN\\4\u0002\u001bMKhnY$f]\u0016\u0014\u0018\r^8s!\tq\"b\u0005\u0002\u000b\u0005B\u0011aeQ\u0005\u0003\t\u001e\u0012a!\u00118z%\u00164G#\u0001!\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005uA\u0005\"\u0002\u000e\r\u0001\u00041\u0002"
)
public final class SyncGenerator extends Generator {
   private final Generator gen;

   public static SyncGenerator apply(final Generator gen) {
      return SyncGenerator$.MODULE$.apply(gen);
   }

   public SyncGenerator copyInit() {
      return new SyncGenerator(this.gen.copy());
   }

   public SyncGenerator sync() {
      return this;
   }

   public byte[] getSeedBytes() {
      return this.gen.getSeedBytes();
   }

   public void setSeedBytes(final byte[] bytes) {
      this.gen.setSeedBytes(bytes);
   }

   public synchronized int nextInt() {
      return this.gen.nextInt();
   }

   public synchronized long nextLong() {
      return this.gen.nextLong();
   }

   public SyncGenerator(final Generator gen) {
      this.gen = gen;
   }
}
