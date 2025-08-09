package org.apache.spark.sql.catalyst.encoders;

import org.apache.spark.util.SparkSerDeUtils.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3A!\u0003\u0006\u0001/!)A\u0007\u0001C\u0001k!)q\u0007\u0001C!q!)1\b\u0001C!y\u001d)qH\u0003E\u0001\u0001\u001a)\u0011B\u0003E\u0001\u0003\")A'\u0002C\u0001\u001d\")q*\u0002C!!\"9\u0011+BA\u0001\n\u0013\u0011&A\u0006&bm\u0006\u001cVM]5bY&T\u0018\r^5p]\u000e{G-Z2\u000b\u0005-a\u0011\u0001C3oG>$WM]:\u000b\u00055q\u0011\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005=\u0001\u0012aA:rY*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005a)3c\u0001\u0001\u001a?A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u0004B\u0001I\u0011$]5\t!\"\u0003\u0002#\u0015\t)1i\u001c3fGB\u0011A%\n\u0007\u0001\t\u00151\u0003A1\u0001(\u0005\u0005I\u0015C\u0001\u0015,!\tQ\u0012&\u0003\u0002+7\t9aj\u001c;iS:<\u0007C\u0001\u000e-\u0013\ti3DA\u0002B]f\u00042AG\u00182\u0013\t\u00014DA\u0003BeJ\f\u0017\u0010\u0005\u0002\u001be%\u00111g\u0007\u0002\u0005\u0005f$X-\u0001\u0004=S:LGO\u0010\u000b\u0002mA\u0019\u0001\u0005A\u0012\u0002\r\u0015t7m\u001c3f)\tq\u0013\bC\u0003;\u0005\u0001\u00071%\u0001\u0002j]\u00061A-Z2pI\u0016$\"aI\u001f\t\u000by\u001a\u0001\u0019\u0001\u0018\u0002\u0007=,H/\u0001\fKCZ\f7+\u001a:jC2L'0\u0019;j_:\u001cu\u000eZ3d!\t\u0001Sa\u0005\u0003\u00063\t3\u0005c\u0001\u000eD\u000b&\u0011Ai\u0007\u0002\n\rVt7\r^5p]B\u0002B\u0001I\u0011,]A\u0011q\tT\u0007\u0002\u0011*\u0011\u0011JS\u0001\u0003S>T\u0011aS\u0001\u0005U\u00064\u0018-\u0003\u0002N\u0011\na1+\u001a:jC2L'0\u00192mKR\t\u0001)A\u0003baBd\u0017\u0010F\u0001F\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0019\u0006C\u0001+X\u001b\u0005)&B\u0001,K\u0003\u0011a\u0017M\\4\n\u0005a+&AB(cU\u0016\u001cG\u000f"
)
public class JavaSerializationCodec implements Codec {
   public static Codec apply() {
      return JavaSerializationCodec$.MODULE$.apply();
   }

   public byte[] encode(final Object in) {
      return .MODULE$.serialize(in);
   }

   public Object decode(final byte[] out) {
      return .MODULE$.deserialize(out);
   }
}
