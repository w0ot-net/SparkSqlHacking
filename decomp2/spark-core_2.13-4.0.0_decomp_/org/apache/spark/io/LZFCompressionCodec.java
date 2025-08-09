package org.apache.spark.io;

import com.ning.compress.lzf.LZFInputStream;
import com.ning.compress.lzf.LZFOutputStream;
import com.ning.compress.lzf.parallel.PLZFOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\r3Aa\u0002\u0005\u0001#!AA\u0004\u0001B\u0001B\u0003%Q\u0004C\u0003\"\u0001\u0011\u0005!\u0005C\u0004&\u0001\t\u0007I\u0011\u0002\u0014\t\r)\u0002\u0001\u0015!\u0003(\u0011\u0015Y\u0003\u0001\"\u0011-\u0011\u00151\u0004\u0001\"\u00118\u0005Ma%LR\"p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u0015\tI!\"\u0001\u0002j_*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0011\u0002\u0004\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VM\u001a\t\u00033ii\u0011\u0001C\u0005\u00037!\u0011\u0001cQ8naJ,7o]5p]\u000e{G-Z2\u0002\t\r|gN\u001a\t\u0003=}i\u0011AC\u0005\u0003A)\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\t\u0019C\u0005\u0005\u0002\u001a\u0001!)AD\u0001a\u0001;\u0005\u0019\u0002/\u0019:bY2,GnQ8naJ,7o]5p]V\tq\u0005\u0005\u0002\u0014Q%\u0011\u0011\u0006\u0006\u0002\b\u0005>|G.Z1o\u0003Q\u0001\u0018M]1mY\u0016d7i\\7qe\u0016\u001c8/[8oA\u000512m\\7qe\u0016\u001c8/\u001a3PkR\u0004X\u000f^*ue\u0016\fW\u000e\u0006\u0002.iA\u0011aFM\u0007\u0002_)\u0011\u0011\u0002\r\u0006\u0002c\u0005!!.\u0019<b\u0013\t\u0019tF\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u00036\u000b\u0001\u0007Q&A\u0001t\u0003U\u0019w.\u001c9sKN\u001cX\rZ%oaV$8\u000b\u001e:fC6$\"\u0001O\u001e\u0011\u00059J\u0014B\u0001\u001e0\u0005-Ie\u000e];u'R\u0014X-Y7\t\u000bU2\u0001\u0019\u0001\u001d)\u0005\u0001i\u0004C\u0001 B\u001b\u0005y$B\u0001!\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0005~\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public class LZFCompressionCodec implements CompressionCodec {
   private final boolean parallelCompression;

   public OutputStream compressedContinuousOutputStream(final OutputStream s) {
      return CompressionCodec.compressedContinuousOutputStream$(this, s);
   }

   public InputStream compressedContinuousInputStream(final InputStream s) {
      return CompressionCodec.compressedContinuousInputStream$(this, s);
   }

   private boolean parallelCompression() {
      return this.parallelCompression;
   }

   public OutputStream compressedOutputStream(final OutputStream s) {
      return (OutputStream)(this.parallelCompression() ? new PLZFOutputStream(s) : (new LZFOutputStream(s)).setFinishBlockOnFlush(true));
   }

   public InputStream compressedInputStream(final InputStream s) {
      return new LZFInputStream(s);
   }

   public LZFCompressionCodec(final SparkConf conf) {
      CompressionCodec.$init$(this);
      this.parallelCompression = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_LZF_PARALLEL()));
   }
}
