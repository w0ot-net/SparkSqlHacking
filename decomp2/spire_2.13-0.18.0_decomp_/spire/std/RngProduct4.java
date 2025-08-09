package spire.std;

import algebra.ring.Rng;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u0002!\tA\u0015\u0002\f%:<\u0007K]8ek\u000e$HG\u0003\u0002\n\u0015\u0005\u00191\u000f\u001e3\u000b\u0003-\tQa\u001d9je\u0016,R!\u0004\u00165oi\u001aB\u0001\u0001\b\u0015yA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\u0012&\u001d\t1rD\u0004\u0002\u0018;9\u0011\u0001\u0004H\u0007\u00023)\u0011!dG\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t1\"\u0003\u0002\u001f\u0015\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0011\"\u0003\u001d\u0001\u0018mY6bO\u0016T!A\b\u0006\n\u0005\r\"#a\u0001*oO*\u0011\u0001%\t\t\u0007\u001f\u0019B3GN\u001d\n\u0005\u001d\u0002\"A\u0002+va2,G\u0007\u0005\u0002*U1\u0001A!B\u0016\u0001\u0005\u0004a#!A!\u0012\u00055\u0002\u0004CA\b/\u0013\ty\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=\t\u0014B\u0001\u001a\u0011\u0005\r\te.\u001f\t\u0003SQ\"Q!\u000e\u0001C\u00021\u0012\u0011A\u0011\t\u0003S]\"Q\u0001\u000f\u0001C\u00021\u0012\u0011a\u0011\t\u0003Si\"Qa\u000f\u0001C\u00021\u0012\u0011\u0001\u0012\t\u0007{yB3GN\u001d\u000e\u0003!I!a\u0010\u0005\u0003!M+W.\u001b:j]\u001e\u0004&o\u001c3vGR$\u0014A\u0002\u0013j]&$H\u0005F\u0001C!\ty1)\u0003\u0002E!\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002\u000fB\u0019QC\t\u0015\u0002\u0015M$(/^2ukJ,''F\u0001K!\r)\"eM\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A'\u0011\u0007U\u0011c'\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u0015\t\u0004+\tJ\u0014A\u00028fO\u0006$X\r\u0006\u0002&'\")AK\u0002a\u0001K\u0005\u0011\u0001\u0010\r"
)
public interface RngProduct4 extends Rng, SemiringProduct4 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   // $FF: synthetic method
   static Tuple4 negate$(final RngProduct4 $this, final Tuple4 x0) {
      return $this.negate(x0);
   }

   default Tuple4 negate(final Tuple4 x0) {
      return new Tuple4(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()));
   }

   static void $init$(final RngProduct4 $this) {
   }
}
