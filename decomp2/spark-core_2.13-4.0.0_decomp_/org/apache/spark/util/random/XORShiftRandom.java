package org.apache.spark.util.random;

import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3QAD\b\u0001'eA\u0001\"\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\u0006S\u0001!\tA\u000b\u0005\u0006S\u0001!\tA\f\u0005\b_\u0001\u0001\r\u0011\"\u00031\u0011\u001d\t\u0004\u00011A\u0005\nIBa\u0001\u000f\u0001!B\u0013\u0019\u0003\"B\u001d\u0001\t#R\u0004\"\u0002!\u0001\t\u0003\nuA\u0002#\u0010\u0011\u0003\u0019RI\u0002\u0004\u000f\u001f!\u00051C\u0012\u0005\u0006S)!\t\u0001\u0015\u0005\u0007#*!\ta\u0004*\t\u000fQS\u0011\u0011!C\u0005+\nq\u0001l\u0014*TQ&4GOU1oI>l'B\u0001\t\u0012\u0003\u0019\u0011\u0018M\u001c3p[*\u0011!cE\u0001\u0005kRLGN\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h'\t\u0001!\u0004\u0005\u0002\u001c?5\tAD\u0003\u0002\u0013;)\ta$\u0001\u0003kCZ\f\u0017B\u0001\u0011\u001d\u0005\u0019\u0011\u0016M\u001c3p[\u0006!\u0011N\\5u\u0007\u0001\u0001\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012A\u0001T8oO\u00061A(\u001b8jiz\"\"aK\u0017\u0011\u00051\u0002Q\"A\b\t\u000b\u0005\u0012\u0001\u0019A\u0012\u0015\u0003-\nAa]3fIV\t1%\u0001\u0005tK\u0016$w\fJ3r)\t\u0019d\u0007\u0005\u0002%i%\u0011Q'\n\u0002\u0005+:LG\u000fC\u00048\u000b\u0005\u0005\t\u0019A\u0012\u0002\u0007a$\u0013'A\u0003tK\u0016$\u0007%\u0001\u0003oKb$HCA\u001e?!\t!C(\u0003\u0002>K\t\u0019\u0011J\u001c;\t\u000b}:\u0001\u0019A\u001e\u0002\t\tLGo]\u0001\bg\u0016$8+Z3e)\t\u0019$\tC\u0003D\u0011\u0001\u00071%A\u0001t\u00039AvJU*iS\u001a$(+\u00198e_6\u0004\"\u0001\f\u0006\u0014\u0007)9%\n\u0005\u0002%\u0011&\u0011\u0011*\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-sU\"\u0001'\u000b\u00055k\u0012AA5p\u0013\tyEJ\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001F\u0003!A\u0017m\u001d5TK\u0016$GCA\u0012T\u0011\u0015yC\u00021\u0001$\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u00051\u0006CA,[\u001b\u0005A&BA-\u001e\u0003\u0011a\u0017M\\4\n\u0005mC&AB(cU\u0016\u001cG\u000f"
)
public class XORShiftRandom extends Random {
   private long seed;

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   public int next(final int bits) {
      long nextSeed = this.seed() ^ this.seed() << 21;
      nextSeed ^= nextSeed >>> 35;
      nextSeed ^= nextSeed << 4;
      this.seed_$eq(nextSeed);
      return (int)(nextSeed & (1L << bits) - 1L);
   }

   public void setSeed(final long s) {
      this.seed_$eq(XORShiftRandom$.MODULE$.hashSeed(s));
   }

   public XORShiftRandom(final long init) {
      super(init);
      this.seed = XORShiftRandom$.MODULE$.hashSeed(init);
   }

   public XORShiftRandom() {
      this(System.nanoTime());
   }
}
