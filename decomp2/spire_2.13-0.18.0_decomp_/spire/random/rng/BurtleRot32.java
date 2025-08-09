package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005y3QAF\f\u0002\u0002yA\u0001b\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tU\u0001\u0011\t\u0011)A\u0005I!A1\u0006\u0001B\u0001B\u0003%A\u0005\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003%\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u001d)\u0004\u00011A\u0005\u0012YBqa\u000e\u0001A\u0002\u0013E\u0001\b\u0003\u0004?\u0001\u0001\u0006K\u0001\n\u0005\b\u007f\u0001\u0001\r\u0011\"\u00057\u0011\u001d\u0001\u0005\u00011A\u0005\u0012\u0005Caa\u0011\u0001!B\u0013!\u0003b\u0002#\u0001\u0001\u0004%\tB\u000e\u0005\b\u000b\u0002\u0001\r\u0011\"\u0005G\u0011\u0019A\u0005\u0001)Q\u0005I!9\u0011\n\u0001a\u0001\n#1\u0004b\u0002&\u0001\u0001\u0004%\tb\u0013\u0005\u0007\u001b\u0002\u0001\u000b\u0015\u0002\u0013\t\u000b9\u0003A\u0011I(\t\u000bA\u0003a\u0011C)\t\u000bI\u0003A\u0011I*\t\u000bi\u0003A\u0011A.\u0003\u0017\t+(\u000f\u001e7f%>$8G\r\u0006\u00031e\t1A\u001d8h\u0015\tQ2$\u0001\u0004sC:$w.\u001c\u0006\u00029\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001 !\t\u0001\u0013%D\u0001\u001a\u0013\t\u0011\u0013DA\tJ]R\u0014\u0015m]3e\u000f\u0016tWM]1u_J\f!aX1\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\u0007%sG/\u0001\u0002`E\u0006\u0011qlY\u0001\u0003?\u0012\fa\u0001P5oSRtD#B\u00182eM\"\u0004C\u0001\u0019\u0001\u001b\u00059\u0002\"B\u0012\u0006\u0001\u0004!\u0003\"\u0002\u0016\u0006\u0001\u0004!\u0003\"B\u0016\u0006\u0001\u0004!\u0003\"\u0002\u0017\u0006\u0001\u0004!\u0013!A1\u0016\u0003\u0011\nQ!Y0%KF$\"!\u000f\u001f\u0011\u0005\u0015R\u0014BA\u001e'\u0005\u0011)f.\u001b;\t\u000fu:\u0011\u0011!a\u0001I\u0005\u0019\u0001\u0010J\u0019\u0002\u0005\u0005\u0004\u0013!\u00012\u0002\u000b\t|F%Z9\u0015\u0005e\u0012\u0005bB\u001f\u000b\u0003\u0003\u0005\r\u0001J\u0001\u0003E\u0002\n\u0011aY\u0001\u0006G~#S-\u001d\u000b\u0003s\u001dCq!P\u0007\u0002\u0002\u0003\u0007A%\u0001\u0002dA\u0005\tA-A\u0003e?\u0012*\u0017\u000f\u0006\u0002:\u0019\"9Q\bEA\u0001\u0002\u0004!\u0013A\u00013!\u0003\u001dqW\r\u001f;J]R$\u0012\u0001J\u0001\bC\u00124\u0018M\\2f+\u0005I\u0014\u0001D4fiN+W\r\u001a\"zi\u0016\u001cX#\u0001+\u0011\u0007\u0015*v+\u0003\u0002WM\t)\u0011I\u001d:bsB\u0011Q\u0005W\u0005\u00033\u001a\u0012AAQ=uK\u0006a1/\u001a;TK\u0016$')\u001f;fgR\u0011\u0011\b\u0018\u0005\u0006;V\u0001\r\u0001V\u0001\u0006Ef$Xm\u001d"
)
public abstract class BurtleRot32 extends IntBasedGenerator {
   private int a;
   private int b;
   private int c;
   private int d;

   public int a() {
      return this.a;
   }

   public void a_$eq(final int x$1) {
      this.a = x$1;
   }

   public int b() {
      return this.b;
   }

   public void b_$eq(final int x$1) {
      this.b = x$1;
   }

   public int c() {
      return this.c;
   }

   public void c_$eq(final int x$1) {
      this.c = x$1;
   }

   public int d() {
      return this.d;
   }

   public void d_$eq(final int x$1) {
      this.d = x$1;
   }

   public int nextInt() {
      this.advance();
      return this.d();
   }

   public abstract void advance();

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[16];
      ByteBuffer bb = ByteBuffer.wrap(bytes);
      bb.putInt(this.a());
      bb.putInt(this.b());
      bb.putInt(this.c());
      bb.putInt(this.d());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < 16 ? Arrays.copyOf(bytes, 16) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);
      this.a_$eq(bb.getInt());
      this.b_$eq(bb.getInt());
      this.c_$eq(bb.getInt());
      this.d_$eq(bb.getInt());
   }

   public BurtleRot32(final int _a, final int _b, final int _c, final int _d) {
      this.a = _a;
      this.b = _b;
      this.c = _c;
      this.d = _d;
   }
}
