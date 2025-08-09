package org.apache.spark.streaming.util;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4Q\u0001D\u0007\u0001\u001f]A\u0001B\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tk\u0001\u0011\t\u0011)A\u0005m!)Q\b\u0001C\u0001}!91\t\u0001b\u0001\n\u0013!\u0005BB&\u0001A\u0003%Q\tC\u0004M\u0001\u0001\u0007I\u0011B'\t\u000fI\u0003\u0001\u0019!C\u0005'\"1\u0011\f\u0001Q!\n9CQA\u0017\u0001\u0005\u0002mCQa\u001a\u0001\u0005B!DQ!\u001b\u0001\u0005\n!\u0014!ER5mK\n\u000b7/\u001a3Xe&$X-\u00115fC\u0012dun\u001a*b]\u0012|WNU3bI\u0016\u0014(B\u0001\b\u0010\u0003\u0011)H/\u001b7\u000b\u0005A\t\u0012!C:ue\u0016\fW.\u001b8h\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7c\u0001\u0001\u0019AA\u0011\u0011DH\u0007\u00025)\u00111\u0004H\u0001\u0005Y\u0006twMC\u0001\u001e\u0003\u0011Q\u0017M^1\n\u0005}Q\"AB(cU\u0016\u001cG\u000f\u0005\u0002\"I5\t!E\u0003\u0002$9\u0005\u0011\u0011n\\\u0005\u0003K\t\u0012\u0011b\u00117pg\u0016\f'\r\\3\u0002\tA\fG\u000f[\u0002\u0001!\tI#G\u0004\u0002+aA\u00111FL\u0007\u0002Y)\u0011QfJ\u0001\u0007yI|w\u000e\u001e \u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0002\rA\u0013X\rZ3g\u0013\t\u0019DG\u0001\u0004TiJLgn\u001a\u0006\u0003c9\nAaY8oMB\u0011qgO\u0007\u0002q)\u0011Q'\u000f\u0006\u0003uM\ta\u0001[1e_>\u0004\u0018B\u0001\u001f9\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"2aP!C!\t\u0001\u0005!D\u0001\u000e\u0011\u001513\u00011\u0001)\u0011\u0015)4\u00011\u00017\u0003!Ign\u001d;sK\u0006lW#A#\u0011\u0005\u0019KU\"A$\u000b\u0005!K\u0014A\u00014t\u0013\tQuIA\tG'\u0012\u000bG/Y%oaV$8\u000b\u001e:fC6\f\u0011\"\u001b8tiJ,\u0017-\u001c\u0011\u0002\r\rdwn]3e+\u0005q\u0005CA(Q\u001b\u0005q\u0013BA)/\u0005\u001d\u0011un\u001c7fC:\f!b\u00197pg\u0016$w\fJ3r)\t!v\u000b\u0005\u0002P+&\u0011aK\f\u0002\u0005+:LG\u000fC\u0004Y\u000f\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0013'A\u0004dY>\u001cX\r\u001a\u0011\u0002\tI,\u0017\r\u001a\u000b\u00039\n\u0004\"!\u00181\u000e\u0003yS!a\u0018\u000f\u0002\u00079Lw.\u0003\u0002b=\nQ!)\u001f;f\u0005V4g-\u001a:\t\u000b\rL\u0001\u0019\u00013\u0002\u000fM,w-\\3oiB\u0011\u0001)Z\u0005\u0003M6\u0011QDR5mK\n\u000b7/\u001a3Xe&$X-\u00115fC\u0012dunZ*fO6,g\u000e^\u0001\u0006G2|7/\u001a\u000b\u0002)\u0006Q\u0011m]:feR|\u0005/\u001a8"
)
public class FileBasedWriteAheadLogRandomReader implements Closeable {
   private final FSDataInputStream instream;
   private boolean closed;

   private FSDataInputStream instream() {
      return this.instream;
   }

   private boolean closed() {
      return this.closed;
   }

   private void closed_$eq(final boolean x$1) {
      this.closed = x$1;
   }

   public synchronized ByteBuffer read(final FileBasedWriteAheadLogSegment segment) {
      this.assertOpen();
      this.instream().seek(segment.offset());
      int nextLength = this.instream().readInt();
      HdfsUtils$.MODULE$.checkState(nextLength == segment.length(), () -> {
         int var10000 = segment.length();
         return "Expected message length to be " + var10000 + ", but was " + nextLength;
      });
      byte[] buffer = new byte[nextLength];
      this.instream().readFully(buffer);
      return ByteBuffer.wrap(buffer);
   }

   public synchronized void close() {
      this.closed_$eq(true);
      this.instream().close();
   }

   private void assertOpen() {
      HdfsUtils$.MODULE$.checkState(!this.closed(), () -> "Stream is closed. Create a new Reader to read from the file.");
   }

   public FileBasedWriteAheadLogRandomReader(final String path, final Configuration conf) {
      this.instream = HdfsUtils$.MODULE$.getInputStream(path, conf);
      this.closed = this.instream() == null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
