package spire.random.rng;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015<Q\u0001E\t\t\u0002a1QAG\t\t\u0002mAQAI\u0001\u0005\u0002\rBq\u0001J\u0001A\u0002\u0013%Q\u0005C\u0004*\u0003\u0001\u0007I\u0011\u0002\u0016\t\rA\n\u0001\u0015)\u0003'\u0011\u0015)\u0014\u0001\"\u00017\u0011\u001da\u0014!%A\u0005\u0002uBQ\u0001S\u0001\u0005\u0002%CqaS\u0001\u0012\u0002\u0013\u0005Q\bC\u0003M\u0003\u0011\u0005Q\nC\u0004V\u0003E\u0005I\u0011\u0001,\t\u000ba\u000bA\u0011A-\t\u000fu\u000b\u0011\u0013!C\u0001{!)a,\u0001C\u0001?\")a,\u0001C\u0001E\u0006)Q\u000b^5mg*\u0011!cE\u0001\u0004e:<'B\u0001\u000b\u0016\u0003\u0019\u0011\u0018M\u001c3p[*\ta#A\u0003ta&\u0014Xm\u0001\u0001\u0011\u0005e\tQ\"A\t\u0003\u000bU#\u0018\u000e\\:\u0014\u0005\u0005a\u0002CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00021\u0005q1/Z3e+:L\u0017/^5gS\u0016\u0014X#\u0001\u0014\u0011\u0005u9\u0013B\u0001\u0015\u001f\u0005\u0011auN\\4\u0002%M,W\rZ+oSF,\u0018NZ5fe~#S-\u001d\u000b\u0003W9\u0002\"!\b\u0017\n\u00055r\"\u0001B+oSRDqa\f\u0003\u0002\u0002\u0003\u0007a%A\u0002yIE\nqb]3fIVs\u0017.];jM&,'\u000f\t\u0015\u0003\u000bI\u0002\"!H\u001a\n\u0005Qr\"\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u0017%tGO\u0012:p[RKW.\u001a\u000b\u0003oi\u0002\"!\b\u001d\n\u0005er\"aA%oi\"91H\u0002I\u0001\u0002\u00041\u0013\u0001\u0002;j[\u0016\fQ#\u001b8u\rJ|W\u000eV5nK\u0012\"WMZ1vYR$\u0013'F\u0001?U\t1shK\u0001A!\t\te)D\u0001C\u0015\t\u0019E)A\u0005v]\u000eDWmY6fI*\u0011QIH\u0001\u000bC:tw\u000e^1uS>t\u0017BA$C\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\rY>twM\u0012:p[RKW.\u001a\u000b\u0003M)Cqa\u000f\u0005\u0011\u0002\u0003\u0007a%\u0001\fm_:<gI]8n)&lW\r\n3fM\u0006,H\u000e\u001e\u00132\u0003-\u0019X-\u001a3Ge>l\u0017J\u001c;\u0015\u00079\u000b6\u000bE\u0002\u001e\u001f^J!\u0001\u0015\u0010\u0003\u000b\u0005\u0013(/Y=\t\u000bIS\u0001\u0019A\u001c\u0002\r1,gn\u001a;i\u0011\u001d!&\u0002%AA\u0002]\nAa]3fI\u0006)2/Z3e\rJ|W.\u00138uI\u0011,g-Y;mi\u0012\u0012T#A,+\u0005]z\u0014\u0001D:fK\u00124%o\\7M_:<Gc\u0001.\\9B\u0019Qd\u0014\u0014\t\u000bIc\u0001\u0019A\u001c\t\u000fQc\u0001\u0013!a\u0001M\u000512/Z3e\rJ|W\u000eT8oO\u0012\"WMZ1vYR$#'A\u0007tK\u0016$gI]8n\u0003J\u0014\u0018-\u001f\u000b\u0004\u001d\u0002\f\u0007\"\u0002*\u000f\u0001\u00049\u0004\"\u0002+\u000f\u0001\u0004qEc\u0001.dI\")!k\u0004a\u0001o!)Ak\u0004a\u00015\u0002"
)
public final class Utils {
   public static long[] seedFromArray(final int length, final long[] seed) {
      return Utils$.MODULE$.seedFromArray(length, seed);
   }

   public static int[] seedFromArray(final int length, final int[] seed) {
      return Utils$.MODULE$.seedFromArray(length, seed);
   }

   public static long seedFromLong$default$2() {
      return Utils$.MODULE$.seedFromLong$default$2();
   }

   public static long[] seedFromLong(final int length, final long seed) {
      return Utils$.MODULE$.seedFromLong(length, seed);
   }

   public static int seedFromInt$default$2() {
      return Utils$.MODULE$.seedFromInt$default$2();
   }

   public static int[] seedFromInt(final int length, final int seed) {
      return Utils$.MODULE$.seedFromInt(length, seed);
   }

   public static long longFromTime$default$1() {
      return Utils$.MODULE$.longFromTime$default$1();
   }

   public static long longFromTime(final long time) {
      return Utils$.MODULE$.longFromTime(time);
   }

   public static long intFromTime$default$1() {
      return Utils$.MODULE$.intFromTime$default$1();
   }

   public static int intFromTime(final long time) {
      return Utils$.MODULE$.intFromTime(time);
   }
}
