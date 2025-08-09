package org.apache.spark.io;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.NoPool;
import com.github.luben.zstd.RecyclingBufferPool;
import com.github.luben.zstd.ZstdInputStreamNoFinalizer;
import com.github.luben.zstd.ZstdOutputStreamNoFinalizer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Aa\u0004\t\u00013!AA\u0005\u0001B\u0001B\u0003%Q\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0004.\u0001\t\u0007I\u0011\u0002\u0018\t\rI\u0002\u0001\u0015!\u00030\u0011\u001d\u0019\u0004A1A\u0005\n9Ba\u0001\u000e\u0001!\u0002\u0013y\u0003bB\u001b\u0001\u0005\u0004%IA\u000e\u0005\u0007\u0007\u0002\u0001\u000b\u0011B\u001c\t\u000f\u0011\u0003!\u0019!C\u0005]!1Q\t\u0001Q\u0001\n=BQA\u0012\u0001\u0005B\u001dCa!\u0015\u0001\u0005BI\u0011\u0006\"B,\u0001\t\u0003B\u0006\"B/\u0001\t\u0003r&\u0001\u0006.Ti\u0012\u001cu.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7M\u0003\u0002\u0012%\u0005\u0011\u0011n\u001c\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u00011c\u0001\u0001\u001bAA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\u0004\"!\t\u0012\u000e\u0003AI!a\t\t\u0003!\r{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001c\u0017\u0001B2p]\u001a\u0004\"AJ\u0014\u000e\u0003II!\u0001\u000b\n\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u0002,YA\u0011\u0011\u0005\u0001\u0005\u0006I\t\u0001\r!J\u0001\u000bEV4g-\u001a:TSj,W#A\u0018\u0011\u0005m\u0001\u0014BA\u0019\u001d\u0005\rIe\u000e^\u0001\fEV4g-\u001a:TSj,\u0007%A\u0003mKZ,G.\u0001\u0004mKZ,G\u000eI\u0001\u000bEV4g-\u001a:Q_>dW#A\u001c\u0011\u0005a\nU\"A\u001d\u000b\u0005iZ\u0014\u0001\u0002>ti\u0012T!\u0001P\u001f\u0002\u000b1,(-\u001a8\u000b\u0005yz\u0014AB4ji\",(MC\u0001A\u0003\r\u0019w.\\\u0005\u0003\u0005f\u0012!BQ;gM\u0016\u0014\bk\\8m\u0003-\u0011WO\u001a4feB{w\u000e\u001c\u0011\u0002\u000f]|'o[3sg\u0006Aqo\u001c:lKJ\u001c\b%\u0001\fd_6\u0004(/Z:tK\u0012|U\u000f\u001e9viN#(/Z1n)\tAu\n\u0005\u0002J\u001b6\t!J\u0003\u0002\u0012\u0017*\tA*\u0001\u0003kCZ\f\u0017B\u0001(K\u00051yU\u000f\u001e9viN#(/Z1n\u0011\u0015\u00016\u00021\u0001I\u0003\u0005\u0019\u0018\u0001I2p[B\u0014Xm]:fI\u000e{g\u000e^5ok>,8oT;uaV$8\u000b\u001e:fC6$\"a\u0015,\u0011\u0005%#\u0016BA+K\u0005Q\u0011UO\u001a4fe\u0016$w*\u001e;qkR\u001cFO]3b[\")\u0001\u000b\u0004a\u0001\u0011\u0006)2m\\7qe\u0016\u001c8/\u001a3J]B,Ho\u0015;sK\u0006lGCA-]!\tI%,\u0003\u0002\\\u0015\nY\u0011J\u001c9viN#(/Z1n\u0011\u0015\u0001V\u00021\u0001Z\u0003}\u0019w.\u001c9sKN\u001cX\rZ\"p]RLg.^8vg&s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u00033~CQ\u0001\u0015\bA\u0002eC#\u0001A1\u0011\u0005\t,W\"A2\u000b\u0005\u0011\u0014\u0012AC1o]>$\u0018\r^5p]&\u0011am\u0019\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b"
)
public class ZStdCompressionCodec implements CompressionCodec {
   private final int bufferSize;
   private final int level;
   private final BufferPool bufferPool;
   private final int workers;

   private int bufferSize() {
      return this.bufferSize;
   }

   private int level() {
      return this.level;
   }

   private BufferPool bufferPool() {
      return this.bufferPool;
   }

   private int workers() {
      return this.workers;
   }

   public OutputStream compressedOutputStream(final OutputStream s) {
      ZstdOutputStreamNoFinalizer os = (new ZstdOutputStreamNoFinalizer(s, this.bufferPool())).setLevel(this.level()).setWorkers(this.workers());
      return new BufferedOutputStream(os, this.bufferSize());
   }

   public BufferedOutputStream compressedContinuousOutputStream(final OutputStream s) {
      ZstdOutputStreamNoFinalizer os = (new ZstdOutputStreamNoFinalizer(s, this.bufferPool())).setLevel(this.level()).setWorkers(this.workers()).setCloseFrameOnFlush(true);
      return new BufferedOutputStream(os, this.bufferSize());
   }

   public InputStream compressedInputStream(final InputStream s) {
      return new BufferedInputStream(new ZstdInputStreamNoFinalizer(s, this.bufferPool()), this.bufferSize());
   }

   public InputStream compressedContinuousInputStream(final InputStream s) {
      return new BufferedInputStream((new ZstdInputStreamNoFinalizer(s, this.bufferPool())).setContinuous(true), this.bufferSize());
   }

   public ZStdCompressionCodec(final SparkConf conf) {
      CompressionCodec.$init$(this);
      this.bufferSize = (int)BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_ZSTD_BUFFERSIZE()));
      this.level = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_ZSTD_LEVEL()));
      this.bufferPool = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_ZSTD_BUFFERPOOL_ENABLED())) ? RecyclingBufferPool.INSTANCE : NoPool.INSTANCE;
      this.workers = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_ZSTD_WORKERS()));
   }
}
