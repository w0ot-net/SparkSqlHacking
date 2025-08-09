package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.Failure$;
import org.json4s.scalap.Result;
import org.json4s.scalap.Success;
import scala.Function2;
import scala.io.Codec.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-s!B\f\u0019\u0011\u0003\tc!B\u0012\u0019\u0011\u0003!\u0003\"B\u0016\u0002\t\u0003a\u0003\"B\u0017\u0002\t\u0003q\u0003bBA\u001b\u0003\u0011\u0005\u0011q\u0007\u0004\u0005Ga\u0001\u0001\u0007\u0003\u00052\u000b\t\u0015\r\u0011\"\u00013\u0011!ITA!A!\u0002\u0013\u0019\u0004\u0002\u0003\u001e\u0006\u0005\u000b\u0007I\u0011A\u001e\t\u0011}*!\u0011!Q\u0001\nqB\u0001\u0002Q\u0003\u0003\u0006\u0004%\ta\u000f\u0005\t\u0003\u0016\u0011\t\u0011)A\u0005y!)1&\u0002C\u0001\u0005\")a)\u0002C\u0001\u000f\")q,\u0002C\u0001A\")a-\u0002C\u0001O\")\u0011.\u0002C\u0001U\")A.\u0002C\u0001[\"1q0\u0002C!\u0003\u0003Aa!!\u0006\u0006\t\u0003Y\u0004bBA\f\u000b\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003C)A\u0011AA\u0012\u0011\u001d\tY#\u0002C\u0001\u0003[\t\u0001BQ=uK\u000e{G-\u001a\u0006\u00033i\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u00037q\taa]2bY\u0006\u0004(BA\u000f\u001f\u0003\u0019Q7o\u001c85g*\tq$A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002#\u00035\t\u0001D\u0001\u0005CsR,7i\u001c3f'\t\tQ\u0005\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0005\nQ!\u00199qYf$2aLA\u001a!\t\u0011Sa\u0005\u0002\u0006K\u0005)!-\u001f;fgV\t1\u0007E\u0002'iYJ!!N\u0014\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0019:\u0014B\u0001\u001d(\u0005\u0011\u0011\u0015\u0010^3\u0002\r\tLH/Z:!\u0003\r\u0001xn]\u000b\u0002yA\u0011a%P\u0005\u0003}\u001d\u00121!\u00138u\u0003\u0011\u0001xn\u001d\u0011\u0002\r1,gn\u001a;i\u0003\u001daWM\\4uQ\u0002\"BaL\"E\u000b\")\u0011\u0007\u0004a\u0001g!)!\b\u0004a\u0001y!)\u0001\t\u0004a\u0001y\u0005Aa.\u001a=u\u0005f$X-F\u0001I%\u0011IU\nU,\u0007\t)\u0003\u0001\u0001\u0013\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\u0006\u0003\u0019\u0002\na\u0001\u0010:p_Rt\u0004C\u0001\u0014O\u0013\tyuEA\u0004Qe>$Wo\u0019;\u0011\u000bE\u0013vF\u000e+\u000e\u0003iI!a\u0015\u000e\u0003\rI+7/\u001e7u!\t1S+\u0003\u0002WO\t9aj\u001c;iS:<\u0007C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\tIwNC\u0001]\u0003\u0011Q\u0017M^1\n\u0005yK&\u0001D*fe&\fG.\u001b>bE2,\u0017\u0001\u00028fqR$\"!\u00193\u0013\t\tl5m\u0016\u0004\u0005\u0015\u0002\u0001\u0011\rE\u0003R%>zC\u000bC\u0003f\u001d\u0001\u0007A(A\u0001o\u0003\u0011!\u0018m[3\u0015\u0005=B\u0007\"B3\u0010\u0001\u0004a\u0014\u0001\u00023s_B$\"aL6\t\u000b\u0015\u0004\u0002\u0019\u0001\u001f\u0002\t\u0019|G\u000eZ\u000b\u0003]J$\"a\\?\u0015\u0005AD\bCA9s\u0019\u0001!Qa]\tC\u0002Q\u0014\u0011\u0001W\t\u0003)V\u0004\"A\n<\n\u0005]<#aA!os\")\u00110\u0005a\u0001u\u0006\ta\rE\u0003'wB4\u0004/\u0003\u0002}O\tIa)\u001e8di&|gN\r\u0005\u0006}F\u0001\r\u0001]\u0001\u0002q\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u0004A!\u0011QAA\b\u001d\u0011\t9!a\u0003\u0011\u0007\u0005%q%D\u0001L\u0013\r\tiaJ\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00111\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055q%A\u0003u_&sG/\u0001\u0004u_2{gnZ\u000b\u0003\u00037\u00012AJA\u000f\u0013\r\tyb\n\u0002\u0005\u0019>tw-\u0001\fge>lW\u000b\u0016$9'R\u0014\u0018N\\4B]\u0012\u0014\u0015\u0010^3t+\t\t)\u0003E\u0002#\u0003OI1!!\u000b\u0019\u0005=\u0019FO]5oO\nKH/Z:QC&\u0014\u0018\u0001\u00022zi\u0016$2\u0001PA\u0018\u0011\u0019\t\tD\u0006a\u0001y\u0005\t\u0011\u000eC\u00032\u0007\u0001\u00071'\u0001\u0005g_J\u001cE.Y:t)\ry\u0013\u0011\b\u0005\b\u0003w!\u0001\u0019AA\u001f\u0003\u0015\u0019G.\u0019>{a\u0011\ty$a\u0012\u0011\r\u0005\u0015\u0011\u0011IA#\u0013\u0011\t\u0019%a\u0005\u0003\u000b\rc\u0017m]:\u0011\u0007E\f9\u0005B\u0006\u0002J\u0005e\u0012\u0011!A\u0001\u0006\u0003!(aA0%c\u0001"
)
public class ByteCode {
   private final byte[] bytes;
   private final int pos;
   private final int length;

   public static ByteCode forClass(final Class clazz) {
      return ByteCode$.MODULE$.forClass(clazz);
   }

   public static ByteCode apply(final byte[] bytes) {
      return ByteCode$.MODULE$.apply(bytes);
   }

   public byte[] bytes() {
      return this.bytes;
   }

   public int pos() {
      return this.pos;
   }

   public int length() {
      return this.length;
   }

   public Result nextByte() {
      return (Result)(this.length() == 0 ? Failure$.MODULE$ : new Success(this.drop(1), BoxesRunTime.boxToByte(this.bytes()[this.pos()])));
   }

   public Result next(final int n) {
      return (Result)(this.length() >= n ? new Success(this.drop(n), this.take(n)) : Failure$.MODULE$);
   }

   public ByteCode take(final int n) {
      return new ByteCode(this.bytes(), this.pos(), n);
   }

   public ByteCode drop(final int n) {
      return new ByteCode(this.bytes(), this.pos() + n, this.length() - n);
   }

   public Object fold(final Object x, final Function2 f) {
      Object result = x;

      for(int i = this.pos(); i < this.pos() + this.length(); ++i) {
         result = f.apply(result, BoxesRunTime.boxToByte(this.bytes()[i]));
      }

      return result;
   }

   public String toString() {
      return (new StringBuilder(5)).append(this.length()).append("bytes").toString();
   }

   public int toInt() {
      return BoxesRunTime.unboxToInt(this.fold(BoxesRunTime.boxToInteger(0), (x, b) -> BoxesRunTime.boxToInteger($anonfun$toInt$1(BoxesRunTime.unboxToInt(x), BoxesRunTime.unboxToByte(b)))));
   }

   public long toLong() {
      return BoxesRunTime.unboxToLong(this.fold(BoxesRunTime.boxToLong(0L), (x, b) -> BoxesRunTime.boxToLong($anonfun$toLong$1(BoxesRunTime.unboxToLong(x), BoxesRunTime.unboxToByte(b)))));
   }

   public StringBytesPair fromUTF8StringAndBytes() {
      byte[] chunk = new byte[this.length()];
      System.arraycopy(this.bytes(), this.pos(), chunk, 0, this.length());
      String str = new String(.MODULE$.fromUTF8(this.bytes(), this.pos(), this.length()));
      return new StringBytesPair(str, chunk);
   }

   public int byte(final int i) {
      return this.bytes()[this.pos()] & 255;
   }

   // $FF: synthetic method
   public static final int $anonfun$toInt$1(final int x, final byte b) {
      return (x << 8) + (b & 255);
   }

   // $FF: synthetic method
   public static final long $anonfun$toLong$1(final long x, final byte b) {
      return (x << 8) + (long)(b & 255);
   }

   public ByteCode(final byte[] bytes, final int pos, final int length) {
      this.bytes = bytes;
      this.pos = pos;
      this.length = length;
      scala.Predef..MODULE$.assert(pos >= 0 && length >= 0 && pos + length <= bytes.length);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
