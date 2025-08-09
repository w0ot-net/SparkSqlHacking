package org.apache.spark.storage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.io.IOUtils;
import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001B\u000f\u001f\t\u001dB\u0011\u0002\r\u0001\u0003\u0006\u0004%\tAH\u0019\t\u0011I\u0002!\u0011!Q\u0001\n!B\u0001b\r\u0001\u0003\u0006\u0004%I\u0001\u000e\u0005\ts\u0001\u0011\t\u0011)A\u0005k!A!\b\u0001BC\u0002\u0013%1\b\u0003\u0005@\u0001\t\u0005\t\u0015!\u0003=\u0011!\u0001\u0005A!b\u0001\n\u0013\t\u0005\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011%\u0003!Q1A\u0005\n)C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0013\u0005\t\u001f\u0002\u0011)\u0019!C\u0005!\"AA\u000b\u0001B\u0001B\u0003%\u0011\u000b\u0003\u0005V\u0001\t\u0015\r\u0011\"\u0003Q\u0011!1\u0006A!A!\u0002\u0013\t\u0006\u0002C,\u0001\u0005\u000b\u0007I\u0011\u0002-\t\u0011\u0011\u0004!\u0011!Q\u0001\neCQ!\u001a\u0001\u0005\u0002\u0019Da\u0001\u001d\u0001!B\u0013\t\u0006\"B9\u0001\t\u0003\u0012\b\"B:\u0001\t\u0003\"\b\"\u0002=\u0001\t\u0003\u0012\b\"B=\u0001\t\u0003R\b\"B?\u0001\t\u0003r\bbBA\u0005\u0001\u0011\u0005\u00131\u0002\u0005\u0007c\u0002!\t%!\u0004\t\rE\u0004A\u0011IA\u0010\u0011\u0019\tY\u0003\u0001C!i\"9\u0011Q\u0006\u0001\u0005\n\u0005=\"A\u0007\"vM\u001a,'OU3mK\u0006\u001c\u0018N\\4J]B,Ho\u0015;sK\u0006l'BA\u0010!\u0003\u001d\u0019Ho\u001c:bO\u0016T!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\u0006\u0005\u0002*]5\t!F\u0003\u0002,Y\u0005\u0011\u0011n\u001c\u0006\u0002[\u0005!!.\u0019<b\u0013\ty#FA\u0006J]B,Ho\u0015;sK\u0006l\u0017\u0001\u00033fY\u0016<\u0017\r^3\u0016\u0003!\n\u0011\u0002Z3mK\u001e\fG/\u001a\u0011\u0002\u0011%$XM]1u_J,\u0012!\u000e\t\u0003m]j\u0011AH\u0005\u0003qy\u00111d\u00155vM\u001adWM\u00117pG.4U\r^2iKJLE/\u001a:bi>\u0014\u0018!C5uKJ\fGo\u001c:!\u0003\u001d\u0011Gn\\2l\u0013\u0012,\u0012\u0001\u0010\t\u0003muJ!A\u0010\u0010\u0003\u000f\tcwnY6JI\u0006A!\r\\8dW&#\u0007%\u0001\u0005nCBLe\u000eZ3y+\u0005\u0011\u0005CA\"G\u001b\u0005!%\"A#\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d#%aA%oi\u0006IQ.\u00199J]\u0012,\u0007\u0010I\u0001\bC\u0012$'/Z:t+\u0005Y\u0005C\u0001\u001cM\u0013\tieD\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u0002\u0011\u0005$GM]3tg\u0002\n\u0001\u0003Z3uK\u000e$8i\u001c:skB$\u0018n\u001c8\u0016\u0003E\u0003\"a\u0011*\n\u0005M#%a\u0002\"p_2,\u0017M\\\u0001\u0012I\u0016$Xm\u0019;D_J\u0014X\u000f\u001d;j_:\u0004\u0013\u0001E5t\u001d\u0016$xo\u001c:l%\u0016\fHi\u001c8f\u0003EI7OT3uo>\u00148NU3r\t>tW\rI\u0001\rG\",7m[3e\u0013:|\u0005\u000f^\u000b\u00023B\u00191I\u0017/\n\u0005m#%AB(qi&|g\u000e\u0005\u0002^E6\taL\u0003\u0002`A\u0006\u0019!0\u001b9\u000b\u0005\u0005d\u0013\u0001B;uS2L!a\u00190\u0003%\rCWmY6fI&s\u0007/\u001e;TiJ,\u0017-\\\u0001\u000eG\",7m[3e\u0013:|\u0005\u000f\u001e\u0011\u0002\rqJg.\u001b;?)%9\u0007.\u001b6lY6tw\u000e\u0005\u00027\u0001!)\u0001'\u0005a\u0001Q!)1'\u0005a\u0001k!)!(\u0005a\u0001y!)\u0001)\u0005a\u0001\u0005\")\u0011*\u0005a\u0001\u0017\")q*\u0005a\u0001#\")Q+\u0005a\u0001#\")q+\u0005a\u00013\u000611\r\\8tK\u0012\fAA]3bIR\t!)A\u0003dY>\u001cX\rF\u0001v!\t\u0019e/\u0003\u0002x\t\n!QK\\5u\u0003%\tg/Y5mC\ndW-\u0001\u0003nCJ\\GCA;|\u0011\u0015ah\u00031\u0001C\u0003%\u0011X-\u00193mS6LG/\u0001\u0003tW&\u0004HcA@\u0002\u0006A\u00191)!\u0001\n\u0007\u0005\rAI\u0001\u0003M_:<\u0007BBA\u0004/\u0001\u0007q0A\u0001o\u00035i\u0017M]6TkB\u0004xN\u001d;fIR\t\u0011\u000bF\u0002C\u0003\u001fAq!!\u0005\u001a\u0001\u0004\t\u0019\"A\u0001c!\u0015\u0019\u0015QCA\r\u0013\r\t9\u0002\u0012\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u0007\u0006m\u0011bAA\u000f\t\n!!)\u001f;f)\u001d\u0011\u0015\u0011EA\u0012\u0003OAq!!\u0005\u001b\u0001\u0004\t\u0019\u0002\u0003\u0004\u0002&i\u0001\rAQ\u0001\u0004_\u001a4\u0007BBA\u00155\u0001\u0007!)A\u0002mK:\fQA]3tKR\f\u0011\u0004\u001e:z\u001fJ4U\r^2i\r\u0006LG.\u001a3Fq\u000e,\u0007\u000f^5p]V!\u0011\u0011GA\u001c)\u0011\t\u0019$!\u0013\u0011\t\u0005U\u0012q\u0007\u0007\u0001\t\u001d\tI\u0004\bb\u0001\u0003w\u0011\u0011\u0001V\t\u0005\u0003{\t\u0019\u0005E\u0002D\u0003\u007fI1!!\u0011E\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aQA#\u0013\r\t9\u0005\u0012\u0002\u0004\u0003:L\b\u0002CA&9\u0011\u0005\r!!\u0014\u0002\u000b\tdwnY6\u0011\u000b\r\u000by%a\r\n\u0007\u0005ECI\u0001\u0005=Eft\u0017-\\3?\u0001"
)
public class BufferReleasingInputStream extends InputStream {
   private final InputStream delegate;
   private final ShuffleBlockFetcherIterator iterator;
   private final BlockId blockId;
   private final int mapIndex;
   private final BlockManagerId address;
   private final boolean detectCorruption;
   private final boolean isNetworkReqDone;
   private final Option checkedInOpt;
   private boolean closed;

   public InputStream delegate() {
      return this.delegate;
   }

   private ShuffleBlockFetcherIterator iterator() {
      return this.iterator;
   }

   private BlockId blockId() {
      return this.blockId;
   }

   private int mapIndex() {
      return this.mapIndex;
   }

   private BlockManagerId address() {
      return this.address;
   }

   private boolean detectCorruption() {
      return this.detectCorruption;
   }

   private boolean isNetworkReqDone() {
      return this.isNetworkReqDone;
   }

   private Option checkedInOpt() {
      return this.checkedInOpt;
   }

   public int read() {
      return BoxesRunTime.unboxToInt(this.tryOrFetchFailedException((JFunction0.mcI.sp)() -> this.delegate().read()));
   }

   public void close() {
      if (!this.closed) {
         try {
            this.delegate().close();
            this.iterator().releaseCurrentResultBuffer();
         } finally {
            if (this.isNetworkReqDone()) {
               ShuffleBlockFetcherIterator$.MODULE$.resetNettyOOMFlagIfPossible(this.iterator().maxReqSizeShuffleToMem());
            }

            this.closed = true;
         }

      }
   }

   public int available() {
      return BoxesRunTime.unboxToInt(this.tryOrFetchFailedException((JFunction0.mcI.sp)() -> this.delegate().available()));
   }

   public void mark(final int readlimit) {
      this.delegate().mark(readlimit);
   }

   public long skip(final long n) {
      return BoxesRunTime.unboxToLong(this.tryOrFetchFailedException((JFunction0.mcJ.sp)() -> this.delegate().skip(n)));
   }

   public boolean markSupported() {
      return this.delegate().markSupported();
   }

   public int read(final byte[] b) {
      return BoxesRunTime.unboxToInt(this.tryOrFetchFailedException((JFunction0.mcI.sp)() -> this.delegate().read(b)));
   }

   public int read(final byte[] b, final int off, final int len) {
      return BoxesRunTime.unboxToInt(this.tryOrFetchFailedException((JFunction0.mcI.sp)() -> this.delegate().read(b, off, len)));
   }

   public void reset() {
      this.tryOrFetchFailedException((JFunction0.mcV.sp)() -> this.delegate().reset());
   }

   private Object tryOrFetchFailedException(final Function0 block) {
      try {
         return block.apply();
      } catch (Throwable var7) {
         if (var7 instanceof IOException var5) {
            if (this.detectCorruption()) {
               Option diagnosisResponse = this.checkedInOpt().map((checkedIn) -> this.iterator().diagnoseCorruption(checkedIn, this.address(), this.blockId()));
               IOUtils.closeQuietly(this);
               throw this.iterator().throwFetchFailedException(this.blockId(), this.mapIndex(), this.address(), var5, diagnosisResponse);
            }
         }

         throw var7;
      }
   }

   public BufferReleasingInputStream(final InputStream delegate, final ShuffleBlockFetcherIterator iterator, final BlockId blockId, final int mapIndex, final BlockManagerId address, final boolean detectCorruption, final boolean isNetworkReqDone, final Option checkedInOpt) {
      this.delegate = delegate;
      this.iterator = iterator;
      this.blockId = blockId;
      this.mapIndex = mapIndex;
      this.address = address;
      this.detectCorruption = detectCorruption;
      this.isNetworkReqDone = isNetworkReqDone;
      this.checkedInOpt = checkedInOpt;
      this.closed = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
