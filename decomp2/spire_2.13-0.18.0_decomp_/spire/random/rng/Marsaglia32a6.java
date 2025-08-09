package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a\u0001\u0002\u000f\u001e\u0001\u0011B\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005U!A\u0011\u0007\u0001B\u0001B\u0003%!\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u0003+\u0011!\u0019\u0004A!A!\u0002\u0013Q\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u000bU\u0002A\u0011\u0001\u001c\t\r}\u0002\u0001\u0015)\u0003+\u0011\u0019\u0001\u0005\u0001)Q\u0005U!1\u0011\t\u0001Q!\n)BaA\u0011\u0001!B\u0013Q\u0003BB\"\u0001A\u0003&!\u0006\u0003\u0004E\u0001\u0001\u0006KA\u000b\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006'\u0002!\t\u0005\u0016\u0005\u00063\u0002!\tA\u0017\u0005\u0006;\u0002!\tAX\u0004\u0006?vA\t\u0001\u0019\u0004\u00069uA\t!\u0019\u0005\u0006kU!\t\u0001\u001b\u0005\u0006SV!\tA\u001b\u0005\u0006YV!\t!\u001c\u0005\u0006aV!\t!\u001d\u0005\boV\t\n\u0011\"\u0001y\u0011\u001d\t9!\u0006C!\u0003\u0013\u0011Q\"T1sg\u0006<G.[14e\u00054$B\u0001\u0010 \u0003\r\u0011hn\u001a\u0006\u0003A\u0005\naA]1oI>l'\"\u0001\u0012\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001!\n\t\u0003M\u001dj\u0011aH\u0005\u0003Q}\u0011\u0011#\u00138u\u0005\u0006\u001cX\rZ$f]\u0016\u0014\u0018\r^8s\u0003\ty\u0006\u0010\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCFA\u0002J]R\f!aX=\u0002\u0005}S\u0018AA0x\u0003\tyf/\u0001\u0002`I\u00061A(\u001b8jiz\"raN\u001d;wqjd\b\u0005\u00029\u00015\tQ\u0004C\u0003*\u000f\u0001\u0007!\u0006C\u00031\u000f\u0001\u0007!\u0006C\u00032\u000f\u0001\u0007!\u0006C\u00033\u000f\u0001\u0007!\u0006C\u00034\u000f\u0001\u0007!\u0006C\u00035\u000f\u0001\u0007!&A\u0001y\u0003\u0005I\u0018!\u0001>\u0002\u0003]\f\u0011A^\u0001\u0002I\u0006A1m\u001c9z\u0013:LG/F\u00018\u0003\u001d9W\r^*fK\u0012,\u0012!\u0013\t\u0004W)S\u0013BA&-\u0005\u0015\t%O]1z\u0003\u001d\u0019X\r^*fK\u0012$\"AT)\u0011\u0005-z\u0015B\u0001)-\u0005\u0011)f.\u001b;\t\u000bI\u0003\u0002\u0019A%\u0002\tM,W\rZ\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002+B\u00191F\u0013,\u0011\u0005-:\u0016B\u0001--\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u00059[\u0006\"\u0002/\u0013\u0001\u0004)\u0016!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002U\u0005iQ*\u0019:tC\u001ed\u0017.Y\u001a3CZ\u0002\"\u0001O\u000b\u0014\u0007U\u0011W\r\u0005\u0002,G&\u0011A\r\f\u0002\u0007\u0003:L(+\u001a4\u0011\t\u00192w'S\u0005\u0003O~\u0011!cR3oKJ\fGo\u001c:D_6\u0004\u0018M\\5p]R\t\u0001-A\u0005ge>l')\u001f;fgR\u0011qg\u001b\u0005\u00069^\u0001\r!V\u0001\tMJ|WnU3fIR\u0011qG\u001c\u0005\u0006_b\u0001\r!S\u0001\u0005S:$8/\u0001\u0005ge>lG+[7f)\t9$\u000fC\u0004t3A\u0005\t\u0019\u0001;\u0002\tQLW.\u001a\t\u0003WUL!A\u001e\u0017\u0003\t1{gnZ\u0001\u0013MJ|W\u000eV5nK\u0012\"WMZ1vYR$\u0013'F\u0001zU\t!(pK\u0001|!\ra\u00181A\u0007\u0002{*\u0011ap`\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0001-\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u000bi(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006Q!/\u00198e_6\u001cV-\u001a3\u0015\u0003%\u0003"
)
public class Marsaglia32a6 extends IntBasedGenerator {
   private int x;
   private int y;
   private int z;
   private int w;
   private int v;
   private int d;

   public static int[] randomSeed() {
      return Marsaglia32a6$.MODULE$.randomSeed();
   }

   public static long fromTime$default$1() {
      return Marsaglia32a6$.MODULE$.fromTime$default$1();
   }

   public static Marsaglia32a6 fromTime(final long time) {
      return Marsaglia32a6$.MODULE$.fromTime(time);
   }

   public static Marsaglia32a6 fromSeed(final int[] ints) {
      return Marsaglia32a6$.MODULE$.fromSeed(ints);
   }

   public static Marsaglia32a6 fromBytes(final byte[] bytes) {
      return Marsaglia32a6$.MODULE$.fromBytes(bytes);
   }

   public static Object apply(final Object seed) {
      return Marsaglia32a6$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Marsaglia32a6$.MODULE$.apply();
   }

   public Marsaglia32a6 copyInit() {
      return new Marsaglia32a6(this.x, this.y, this.z, this.w, this.v, this.d);
   }

   public int[] getSeed() {
      int[] ints = new int[6];
      ints[0] = this.x;
      ints[1] = this.y;
      ints[2] = this.z;
      ints[3] = this.w;
      ints[4] = this.v;
      ints[5] = this.d;
      return ints;
   }

   public void setSeed(final int[] seed) {
      int[] zs = seed.length < 6 ? Arrays.copyOf(seed, 6) : seed;
      this.x = zs[0];
      this.y = zs[0];
      this.z = zs[0];
      this.w = zs[0];
      this.v = zs[0];
      this.d = zs[0];
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[24];
      ByteBuffer bb = ByteBuffer.wrap(bytes);
      bb.putInt(this.x);
      bb.putInt(this.y);
      bb.putInt(this.z);
      bb.putInt(this.w);
      bb.putInt(this.v);
      bb.putInt(this.d);
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < 24 ? Arrays.copyOf(bytes, 24) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);
      this.x = bb.getInt();
      this.y = bb.getInt();
      this.z = bb.getInt();
      this.w = bb.getInt();
      this.v = bb.getInt();
      this.d = bb.getInt();
   }

   public int nextInt() {
      int t = this.x ^ this.x >>> 2;
      this.x = this.y;
      this.y = this.z;
      this.z = this.w;
      this.w = this.v;
      this.v = this.v ^ this.v << 4 ^ t ^ t << 1;
      this.d += 362437;
      return this.d + this.v;
   }

   public Marsaglia32a6(final int _x, final int _y, final int _z, final int _w, final int _v, final int _d) {
      this.x = _x;
      this.y = _y;
      this.z = _z;
      this.w = _w;
      this.v = _v;
      this.d = _d;
   }
}
