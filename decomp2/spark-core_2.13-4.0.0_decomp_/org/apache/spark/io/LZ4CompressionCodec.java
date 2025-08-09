package org.apache.spark.io;

import java.io.InputStream;
import java.io.OutputStream;
import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.xxhash.XXHashFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005u3A!\u0003\u0006\u0001'!Aa\u0004\u0001B\u0001B\u0003%q\u0004C\u0003$\u0001\u0011\u0005A\u0005\u0003\u0005(\u0001!\u0015\r\u0015\"\u0003)\u0011!9\u0004\u0001#b!\n\u0013A\u0004B\u0002!\u0001A\u0003%\u0011\t\u0003\u0004E\u0001\u0001\u0006I!\u0011\u0005\u0006\u000b\u0002!\tE\u0012\u0005\u0006!\u0002!\t%\u0015\u0002\u0014\u0019j#4i\\7qe\u0016\u001c8/[8o\u0007>$Wm\u0019\u0006\u0003\u00171\t!![8\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007CA\u000e\u001d\u001b\u0005Q\u0011BA\u000f\u000b\u0005A\u0019u.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7-\u0001\u0003d_:4\u0007C\u0001\u0011\"\u001b\u0005a\u0011B\u0001\u0012\r\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0003K\u0019\u0002\"a\u0007\u0001\t\u000by\u0011\u0001\u0019A\u0010\u0002\u00151THGR1di>\u0014\u00180F\u0001*!\tQ\u0013'D\u0001,\u0015\taS&A\u0002muRR!AL\u0018\u0002\u000f)\u0004x.\u001e8uu*\t\u0001'A\u0002oKRL!AM\u0016\u0003\u00151SFGR1di>\u0014\u0018\u0010\u000b\u0002\u0004iA\u0011Q#N\u0005\u0003mY\u0011\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u001baD\b*Y:i\r\u0006\u001cGo\u001c:z+\u0005I\u0004C\u0001\u001e>\u001b\u0005Y$B\u0001\u001f.\u0003\u0019A\b\u0010[1tQ&\u0011ah\u000f\u0002\u000e1bC\u0015m\u001d5GC\u000e$xN]=)\u0005\u0011!\u0014a\u00033fM\u0006,H\u000e^*fK\u0012\u0004\"!\u0006\"\n\u0005\r3\"aA%oi\u0006I!\r\\8dWNK'0Z\u0001\u0017G>l\u0007O]3tg\u0016$w*\u001e;qkR\u001cFO]3b[R\u0011qI\u0014\t\u0003\u00112k\u0011!\u0013\u0006\u0003\u0017)S\u0011aS\u0001\u0005U\u00064\u0018-\u0003\u0002N\u0013\naq*\u001e;qkR\u001cFO]3b[\")qj\u0002a\u0001\u000f\u0006\t1/A\u000bd_6\u0004(/Z:tK\u0012Le\u000e];u'R\u0014X-Y7\u0015\u0005I+\u0006C\u0001%T\u0013\t!\u0016JA\u0006J]B,Ho\u0015;sK\u0006l\u0007\"B(\t\u0001\u0004\u0011\u0006F\u0001\u0001X!\tA6,D\u0001Z\u0015\tQF\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001X-\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public class LZ4CompressionCodec implements CompressionCodec {
   private transient LZ4Factory lz4Factory;
   private transient XXHashFactory xxHashFactory;
   private final int defaultSeed;
   private final int blockSize;
   private transient volatile byte bitmap$trans$0;

   public OutputStream compressedContinuousOutputStream(final OutputStream s) {
      return CompressionCodec.compressedContinuousOutputStream$(this, s);
   }

   public InputStream compressedContinuousInputStream(final InputStream s) {
      return CompressionCodec.compressedContinuousInputStream$(this, s);
   }

   private LZ4Factory lz4Factory$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.lz4Factory = LZ4Factory.fastestInstance();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.lz4Factory;
   }

   private LZ4Factory lz4Factory() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.lz4Factory$lzycompute() : this.lz4Factory;
   }

   private XXHashFactory xxHashFactory$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.xxHashFactory = XXHashFactory.fastestInstance();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.xxHashFactory;
   }

   private XXHashFactory xxHashFactory() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.xxHashFactory$lzycompute() : this.xxHashFactory;
   }

   public OutputStream compressedOutputStream(final OutputStream s) {
      boolean syncFlush = false;
      return new LZ4BlockOutputStream(s, this.blockSize, this.lz4Factory().fastCompressor(), this.xxHashFactory().newStreamingHash32(this.defaultSeed).asChecksum(), syncFlush);
   }

   public InputStream compressedInputStream(final InputStream s) {
      boolean disableConcatenationOfByteStream = false;
      return new LZ4BlockInputStream(s, this.lz4Factory().fastDecompressor(), this.xxHashFactory().newStreamingHash32(this.defaultSeed).asChecksum(), disableConcatenationOfByteStream);
   }

   public LZ4CompressionCodec(final SparkConf conf) {
      CompressionCodec.$init$(this);
      this.defaultSeed = -1756908916;
      this.blockSize = (int)BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_LZ4_BLOCKSIZE()));
   }
}
