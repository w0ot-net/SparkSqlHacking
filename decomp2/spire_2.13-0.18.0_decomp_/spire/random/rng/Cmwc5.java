package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.reflect.ScalaSignature;
import spire.random.LongBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\u0013&\u00051B\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tq\u0001\u0011\t\u0011)A\u0005e!A\u0011\b\u0001B\u0001B\u0003%!\u0007\u0003\u0005;\u0001\t\u0005\t\u0015!\u00033\u0011!Y\u0004A!A!\u0002\u0013\u0011\u0004\"\u0002\u001f\u0001\t\u0003i\u0004bB#\u0001\u0001\u0004%IA\u0012\u0005\b\u000f\u0002\u0001\r\u0011\"\u0003I\u0011\u0019q\u0005\u0001)Q\u0005e!9q\n\u0001a\u0001\n\u00131\u0005b\u0002)\u0001\u0001\u0004%I!\u0015\u0005\u0007'\u0002\u0001\u000b\u0015\u0002\u001a\t\u000fQ\u0003\u0001\u0019!C\u0005\r\"9Q\u000b\u0001a\u0001\n\u00131\u0006B\u0002-\u0001A\u0003&!\u0007C\u0004Z\u0001\u0001\u0007I\u0011\u0002$\t\u000fi\u0003\u0001\u0019!C\u00057\"1Q\f\u0001Q!\nIBqA\u0018\u0001A\u0002\u0013%a\tC\u0004`\u0001\u0001\u0007I\u0011\u00021\t\r\t\u0004\u0001\u0015)\u00033\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u0015)\u0007\u0001\"\u0001g\u0011\u0015Q\u0007\u0001\"\u0001l\u0011\u0015q\u0007\u0001\"\u0001p\u0011\u0015!\b\u0001\"\u0001v\u0011\u0015A\b\u0001\"\u0001z\u000f\u0015QX\u0005#\u0001|\r\u0015!S\u0005#\u0001}\u0011\u0019aT\u0004\"\u0001\u0002\b!9\u0011\u0011B\u000f\u0005\u0002\u0005-\u0001bBA\u0007;\u0011\u0005\u0011q\u0002\u0005\b\u0003'iB\u0011AA\u000b\u0011\u001d\tY\"\bC\u0001\u0003;A\u0011\"a\t\u001e#\u0003%\t!!\n\u0003\u000b\rkwoY\u001b\u000b\u0005\u0019:\u0013a\u0001:oO*\u0011\u0001&K\u0001\u0007e\u0006tGm\\7\u000b\u0003)\nQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001[A\u0011afL\u0007\u0002O%\u0011\u0001g\n\u0002\u0013\u0019>twMQ1tK\u0012<UM\\3sCR|'/\u0001\u0002`qB\u00111GN\u0007\u0002i)\tQ'A\u0003tG\u0006d\u0017-\u0003\u00028i\t!Aj\u001c8h\u0003\ty\u00160\u0001\u0002`u\u0006\u0011ql^\u0001\u0003?Z\fa\u0001P5oSRtDC\u0002 A\u0003\n\u001bE\t\u0005\u0002@\u00015\tQ\u0005C\u00032\r\u0001\u0007!\u0007C\u00039\r\u0001\u0007!\u0007C\u0003:\r\u0001\u0007!\u0007C\u0003;\r\u0001\u0007!\u0007C\u0003<\r\u0001\u0007!'A\u0001y+\u0005\u0011\u0014!\u0002=`I\u0015\fHCA%M!\t\u0019$*\u0003\u0002Li\t!QK\\5u\u0011\u001di\u0005\"!AA\u0002I\n1\u0001\u001f\u00132\u0003\tA\b%A\u0001z\u0003\u0015Ix\fJ3r)\tI%\u000bC\u0004N\u0017\u0005\u0005\t\u0019\u0001\u001a\u0002\u0005e\u0004\u0013!\u0001>\u0002\u000bi|F%Z9\u0015\u0005%;\u0006bB'\u000f\u0003\u0003\u0005\rAM\u0001\u0003u\u0002\n\u0011a^\u0001\u0006o~#S-\u001d\u000b\u0003\u0013rCq!T\t\u0002\u0002\u0003\u0007!'\u0001\u0002xA\u0005\ta/A\u0003w?\u0012*\u0017\u000f\u0006\u0002JC\"9Q\nFA\u0001\u0002\u0004\u0011\u0014A\u0001<!\u0003!\u0019w\u000e]=J]&$X#\u0001 \u0002\u000f\u001d,GoU3fIV\tq\rE\u00024QJJ!!\u001b\u001b\u0003\u000b\u0005\u0013(/Y=\u0002\u000fM,GoU3fIR\u0011\u0011\n\u001c\u0005\u0006[b\u0001\raZ\u0001\u0006Y>twm]\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002aB\u00191\u0007[9\u0011\u0005M\u0012\u0018BA:5\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005%3\b\"B<\u001b\u0001\u0004\u0001\u0018!\u00022zi\u0016\u001c\u0018\u0001\u00038fqRduN\\4\u0015\u0003I\nQaQ7xGV\u0002\"aP\u000f\u0014\tui\u0018\u0011\u0001\t\u0003gyL!a \u001b\u0003\r\u0005s\u0017PU3g!\u0015q\u00131\u0001 h\u0013\r\t)a\n\u0002\u0013\u000f\u0016tWM]1u_J\u001cu.\u001c9b]&|g\u000eF\u0001|\u0003)\u0011\u0018M\u001c3p[N+W\r\u001a\u000b\u0002O\u0006IaM]8n\u0005f$Xm\u001d\u000b\u0004}\u0005E\u0001\"B<!\u0001\u0004\u0001\u0018\u0001\u00034s_6\u001cV-\u001a3\u0015\u0007y\n9\u0002\u0003\u0004\u0002\u001a\u0005\u0002\raZ\u0001\u0005g\u0016,G-\u0001\u0005ge>lG+[7f)\rq\u0014q\u0004\u0005\t\u0003C\u0011\u0003\u0013!a\u0001e\u0005!A/[7f\u0003I1'o\\7US6,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u001d\"f\u0001\u001a\u0002*-\u0012\u00111\u0006\t\u0005\u0003[\t9$\u0004\u0002\u00020)!\u0011\u0011GA\u001a\u0003%)hn\u00195fG.,GMC\u0002\u00026Q\n!\"\u00198o_R\fG/[8o\u0013\u0011\tI$a\f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public final class Cmwc5 extends LongBasedGenerator {
   private long x;
   private long y;
   private long z;
   private long w;
   private long v;

   public static long fromTime$default$1() {
      return Cmwc5$.MODULE$.fromTime$default$1();
   }

   public static Cmwc5 fromTime(final long time) {
      return Cmwc5$.MODULE$.fromTime(time);
   }

   public static Cmwc5 fromSeed(final long[] seed) {
      return Cmwc5$.MODULE$.fromSeed(seed);
   }

   public static Cmwc5 fromBytes(final byte[] bytes) {
      return Cmwc5$.MODULE$.fromBytes(bytes);
   }

   public static long[] randomSeed() {
      return Cmwc5$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Cmwc5$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Cmwc5$.MODULE$.apply();
   }

   private long x() {
      return this.x;
   }

   private void x_$eq(final long x$1) {
      this.x = x$1;
   }

   private long y() {
      return this.y;
   }

   private void y_$eq(final long x$1) {
      this.y = x$1;
   }

   private long z() {
      return this.z;
   }

   private void z_$eq(final long x$1) {
      this.z = x$1;
   }

   private long w() {
      return this.w;
   }

   private void w_$eq(final long x$1) {
      this.w = x$1;
   }

   private long v() {
      return this.v;
   }

   private void v_$eq(final long x$1) {
      this.v = x$1;
   }

   public Cmwc5 copyInit() {
      return new Cmwc5(this.x(), this.y(), this.z(), this.w(), this.v());
   }

   public long[] getSeed() {
      long[] longs = new long[5];
      longs[0] = this.x();
      longs[1] = this.y();
      longs[2] = this.z();
      longs[3] = this.w();
      longs[4] = this.v();
      return longs;
   }

   public void setSeed(final long[] longs) {
      this.x_$eq(longs[0]);
      this.y_$eq(longs[1]);
      this.z_$eq(longs[2]);
      this.w_$eq(longs[3]);
      this.v_$eq(longs[4]);
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[40];
      ByteBuffer bb = ByteBuffer.wrap(bytes);
      bb.putLong(this.x());
      bb.putLong(this.y());
      bb.putLong(this.z());
      bb.putLong(this.w());
      bb.putLong(this.v());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < 40 ? Arrays.copyOf(bytes, 40) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);
      this.x_$eq(bb.getLong());
      this.y_$eq(bb.getLong());
      this.z_$eq(bb.getLong());
      this.w_$eq(bb.getLong());
      this.v_$eq(bb.getLong());
   }

   public long nextLong() {
      long t = this.x() ^ this.x() >>> 7;
      this.x_$eq(this.y());
      this.y_$eq(this.z());
      this.z_$eq(this.w());
      this.w_$eq(this.v());
      this.v_$eq(this.v() ^ this.v() << 6 ^ t ^ t << 13);
      return (this.y() + this.y() + 1L) * this.v();
   }

   public Cmwc5(final long _x, final long _y, final long _z, final long _w, final long _v) {
      this.x = _x;
      this.y = _y;
      this.z = _z;
      this.w = _w;
      this.v = _v;
   }
}
