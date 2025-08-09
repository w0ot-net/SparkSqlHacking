package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.network.util.AbstractFileRegion;
import org.apache.spark.unsafe.Platform;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005e3AAD\b\u00051!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003.\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u001dI\u0004\u00011A\u0005\niBqa\u000f\u0001A\u0002\u0013%A\b\u0003\u0004C\u0001\u0001\u0006K!\f\u0005\b\u0007\u0002\u0011\r\u0011\"\u0003E\u0011\u0019I\u0005\u0001)A\u0005\u000b\")!\n\u0001C!\u0017\")A\n\u0001C!\u0017\")Q\n\u0001C!\u0017\")a\n\u0001C!\u001f\")q\u000b\u0001C!1\nI\"+Z1eC\ndWm\u00115b]:,GNR5mKJ+w-[8o\u0015\t\u0001\u0012#A\u0004ti>\u0014\u0018mZ3\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00013A\u0011!dH\u0007\u00027)\u0011A$H\u0001\u0005kRLGN\u0003\u0002\u001f#\u00059a.\u001a;x_J\\\u0017B\u0001\u0011\u001c\u0005I\t%m\u001d;sC\u000e$h)\u001b7f%\u0016<\u0017n\u001c8\u0002\rM|WO]2f!\t\u0019#&D\u0001%\u0015\t)c%\u0001\u0005dQ\u0006tg.\u001a7t\u0015\t9\u0003&A\u0002oS>T\u0011!K\u0001\u0005U\u00064\u0018-\u0003\u0002,I\t\u0019\"+Z1eC\ndWMQ=uK\u000eC\u0017M\u001c8fY\u0006I!\r\\8dWNK'0\u001a\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0005\u0019>tw-\u0001\u0004=S:LGO\u0010\u000b\u0004k]B\u0004C\u0001\u001c\u0001\u001b\u0005y\u0001\"B\u0011\u0004\u0001\u0004\u0011\u0003\"\u0002\u0017\u0004\u0001\u0004i\u0013\u0001D0ue\u0006t7OZ3se\u0016$W#A\u0017\u0002!}#(/\u00198tM\u0016\u0014(/\u001a3`I\u0015\fHCA\u001fA!\tqc(\u0003\u0002@_\t!QK\\5u\u0011\u001d\tU!!AA\u00025\n1\u0001\u001f\u00132\u00035yFO]1og\u001a,'O]3eA\u00051!-\u001e4gKJ,\u0012!\u0012\t\u0003\r\u001ek\u0011AJ\u0005\u0003\u0011\u001a\u0012!BQ=uK\n+hMZ3s\u0003\u001d\u0011WO\u001a4fe\u0002\nQaY8v]R$\u0012!L\u0001\ta>\u001c\u0018\u000e^5p]\u0006YAO]1og\u001a,'O]3e\u0003)!(/\u00198tM\u0016\u0014Hk\u001c\u000b\u0004[A+\u0006\"B)\r\u0001\u0004\u0011\u0016A\u0002;be\u001e,G\u000f\u0005\u0002$'&\u0011A\u000b\n\u0002\u0014/JLG/\u00192mK\nKH/Z\"iC:tW\r\u001c\u0005\u0006-2\u0001\r!L\u0001\u0004a>\u001c\u0018A\u00033fC2dwnY1uKR\tQ\b"
)
public class ReadableChannelFileRegion extends AbstractFileRegion {
   private final ReadableByteChannel source;
   private final long blockSize;
   private long _transferred;
   private final ByteBuffer buffer;

   private long _transferred() {
      return this._transferred;
   }

   private void _transferred_$eq(final long x$1) {
      this._transferred = x$1;
   }

   private ByteBuffer buffer() {
      return this.buffer;
   }

   public long count() {
      return this.blockSize;
   }

   public long position() {
      return 0L;
   }

   public long transferred() {
      return this._transferred();
   }

   public long transferTo(final WritableByteChannel target, final long pos) {
      .MODULE$.assert(pos == this.transferred(), () -> "Invalid position.");
      long written = 0L;
      long lastWrite = -1L;

      while(lastWrite != 0L) {
         if (!this.buffer().hasRemaining()) {
            this.buffer().clear();
            this.source.read(this.buffer());
            this.buffer().flip();
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (this.buffer().hasRemaining()) {
            lastWrite = (long)target.write(this.buffer());
            written += lastWrite;
         } else {
            lastWrite = 0L;
         }
      }

      this._transferred_$eq(this._transferred() + written);
      return written;
   }

   public void deallocate() {
      this.source.close();
   }

   public ReadableChannelFileRegion(final ReadableByteChannel source, final long blockSize) {
      this.source = source;
      this.blockSize = blockSize;
      this._transferred = 0L;
      this.buffer = Platform.allocateDirectBuffer(65536);
      this.buffer().flip();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
