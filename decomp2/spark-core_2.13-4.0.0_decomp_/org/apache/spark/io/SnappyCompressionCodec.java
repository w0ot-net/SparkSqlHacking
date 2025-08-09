package org.apache.spark.io;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00013AAB\u0004\u0001!!A1\u0004\u0001B\u0001B\u0003%A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005\u0003\u0004%\u0001\u0001\u0006I!\n\u0005\u0006Q\u0001!\t%\u000b\u0005\u0006g\u0001!\t\u0005\u000e\u0002\u0017':\f\u0007\u000f]=D_6\u0004(/Z:tS>t7i\u001c3fG*\u0011\u0001\"C\u0001\u0003S>T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aIR\"A\u0004\n\u0005i9!\u0001E\"p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u0003\u0011\u0019wN\u001c4\u0011\u0005uqR\"A\u0005\n\u0005}I!!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011!e\t\t\u00031\u0001AQa\u0007\u0002A\u0002q\t\u0011B\u00197pG.\u001c\u0016N_3\u0011\u0005I1\u0013BA\u0014\u0014\u0005\rIe\u000e^\u0001\u0017G>l\u0007O]3tg\u0016$w*\u001e;qkR\u001cFO]3b[R\u0011!&\r\t\u0003W=j\u0011\u0001\f\u0006\u0003\u00115R\u0011AL\u0001\u0005U\u00064\u0018-\u0003\u00021Y\taq*\u001e;qkR\u001cFO]3b[\")!\u0007\u0002a\u0001U\u0005\t1/A\u000bd_6\u0004(/Z:tK\u0012Le\u000e];u'R\u0014X-Y7\u0015\u0005UB\u0004CA\u00167\u0013\t9DFA\u0006J]B,Ho\u0015;sK\u0006l\u0007\"\u0002\u001a\u0006\u0001\u0004)\u0004F\u0001\u0001;!\tYd(D\u0001=\u0015\ti\u0014\"\u0001\u0006b]:|G/\u0019;j_:L!a\u0010\u001f\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public class SnappyCompressionCodec implements CompressionCodec {
   private final int blockSize;

   public OutputStream compressedContinuousOutputStream(final OutputStream s) {
      return CompressionCodec.compressedContinuousOutputStream$(this, s);
   }

   public InputStream compressedContinuousInputStream(final InputStream s) {
      return CompressionCodec.compressedContinuousInputStream$(this, s);
   }

   public OutputStream compressedOutputStream(final OutputStream s) {
      return new SnappyOutputStream(s, this.blockSize);
   }

   public InputStream compressedInputStream(final InputStream s) {
      return new SnappyInputStream(s);
   }

   public SnappyCompressionCodec(final SparkConf conf) {
      CompressionCodec.$init$(this);

      try {
         Snappy.getNativeLibraryVersion();
      } catch (Error var3) {
         throw new IllegalArgumentException(var3);
      }

      this.blockSize = (int)BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_SNAPPY_BLOCKSIZE()));
   }
}
