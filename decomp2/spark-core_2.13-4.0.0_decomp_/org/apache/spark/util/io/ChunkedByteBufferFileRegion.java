package org.apache.spark.util.io;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.network.util.AbstractFileRegion;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005E4Q!\u0006\f\u0001-\u0001B\u0001\u0002\u000b\u0001\u0003\u0006\u0004%IA\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005W!A\u0001\u0007\u0001BC\u0002\u0013%\u0011\u0007\u0003\u00059\u0001\t\u0005\t\u0015!\u00033\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u001dq\u0004\u00011A\u0005\n}Bqa\u0011\u0001A\u0002\u0013%A\t\u0003\u0004K\u0001\u0001\u0006K\u0001\u0011\u0005\b\u0017\u0002\u0011\r\u0011\"\u0003M\u0011\u0019A\u0006\u0001)A\u0005\u001b\"9\u0011\f\u0001b\u0001\n\u0013y\u0004B\u0002.\u0001A\u0003%\u0001\tC\u0003\\\u0001\u0011EA\fC\u0003^\u0001\u0011\u0005c\fC\u0003`\u0001\u0011\u0005c\fC\u0003a\u0001\u0011\u0005c\fC\u0004b\u0001\u0001\u0007I\u0011B\u0019\t\u000f\t\u0004\u0001\u0019!C\u0005G\"1Q\r\u0001Q!\nIBQA\u001a\u0001\u0005\u0002\u001d\u00141d\u00115v].,GMQ=uK\n+hMZ3s\r&dWMU3hS>t'BA\f\u0019\u0003\tIwN\u0003\u0002\u001a5\u0005!Q\u000f^5m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7C\u0001\u0001\"!\t\u0011c%D\u0001$\u0015\tIBE\u0003\u0002&5\u00059a.\u001a;x_J\\\u0017BA\u0014$\u0005I\t%m\u001d;sC\u000e$h)\u001b7f%\u0016<\u0017n\u001c8\u0002#\rDWO\\6fI\nKH/\u001a\"vM\u001a,'o\u0001\u0001\u0016\u0003-\u0002\"\u0001L\u0017\u000e\u0003YI!A\f\f\u0003#\rCWO\\6fI\nKH/\u001a\"vM\u001a,'/\u0001\ndQVt7.\u001a3CsR,')\u001e4gKJ\u0004\u0013aC5p\u0007\",hn[*ju\u0016,\u0012A\r\t\u0003gYj\u0011\u0001\u000e\u0006\u0002k\u0005)1oY1mC&\u0011q\u0007\u000e\u0002\u0004\u0013:$\u0018\u0001D5p\u0007\",hn[*ju\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0002<yu\u0002\"\u0001\f\u0001\t\u000b!*\u0001\u0019A\u0016\t\u000bA*\u0001\u0019\u0001\u001a\u0002\u0019}#(/\u00198tM\u0016\u0014(/\u001a3\u0016\u0003\u0001\u0003\"aM!\n\u0005\t#$\u0001\u0002'p]\u001e\f\u0001c\u0018;sC:\u001ch-\u001a:sK\u0012|F%Z9\u0015\u0005\u0015C\u0005CA\u001aG\u0013\t9EG\u0001\u0003V]&$\bbB%\b\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0014!D0ue\u0006t7OZ3se\u0016$\u0007%\u0001\u0004dQVt7n]\u000b\u0002\u001bB\u00191G\u0014)\n\u0005=#$!B!se\u0006L\bCA)W\u001b\u0005\u0011&BA*U\u0003\rq\u0017n\u001c\u0006\u0002+\u0006!!.\u0019<b\u0013\t9&K\u0001\u0006CsR,')\u001e4gKJ\fqa\u00195v].\u001c\b%\u0001\u0003tSj,\u0017!B:ju\u0016\u0004\u0013A\u00033fC2dwnY1uKR\tQ)A\u0003d_VtG\u000fF\u0001A\u0003!\u0001xn]5uS>t\u0017a\u0003;sC:\u001ch-\u001a:sK\u0012\fqbY;se\u0016tGo\u00115v].LE\r_\u0001\u0014GV\u0014(/\u001a8u\u0007\",hn[%eq~#S-\u001d\u000b\u0003\u000b\u0012Dq!\u0013\n\u0002\u0002\u0003\u0007!'\u0001\tdkJ\u0014XM\u001c;DQVt7.\u00133yA\u0005QAO]1og\u001a,'\u000fV8\u0015\u0007\u0001C\u0007\u000fC\u0003j)\u0001\u0007!.\u0001\u0004uCJ<W\r\u001e\t\u0003W:l\u0011\u0001\u001c\u0006\u0003[J\u000b\u0001b\u00195b]:,Gn]\u0005\u0003_2\u00141c\u0016:ji\u0006\u0014G.\u001a\"zi\u0016\u001c\u0005.\u00198oK2DQa\u0018\u000bA\u0002\u0001\u0003"
)
public class ChunkedByteBufferFileRegion extends AbstractFileRegion {
   private final ChunkedByteBuffer chunkedByteBuffer;
   private final int ioChunkSize;
   private long _transferred;
   private final ByteBuffer[] chunks;
   private final long size;
   private int currentChunkIdx;

   private ChunkedByteBuffer chunkedByteBuffer() {
      return this.chunkedByteBuffer;
   }

   private int ioChunkSize() {
      return this.ioChunkSize;
   }

   private long _transferred() {
      return this._transferred;
   }

   private void _transferred_$eq(final long x$1) {
      this._transferred = x$1;
   }

   private ByteBuffer[] chunks() {
      return this.chunks;
   }

   private long size() {
      return this.size;
   }

   public void deallocate() {
   }

   public long count() {
      return this.size();
   }

   public long position() {
      return 0L;
   }

   public long transferred() {
      return this._transferred();
   }

   private int currentChunkIdx() {
      return this.currentChunkIdx;
   }

   private void currentChunkIdx_$eq(final int x$1) {
      this.currentChunkIdx = x$1;
   }

   public long transferTo(final WritableByteChannel target, final long position) {
      .MODULE$.assert(position == this._transferred());
      if (position == this.size()) {
         return 0L;
      } else {
         boolean keepGoing = true;
         long written = 0L;
         ByteBuffer currentChunk = this.chunks()[this.currentChunkIdx()];

         while(keepGoing) {
            while(currentChunk.hasRemaining() && keepGoing) {
               int ioSize = Math.min(currentChunk.remaining(), this.ioChunkSize());
               int originalLimit = currentChunk.limit();
               currentChunk.limit(currentChunk.position() + ioSize);
               int thisWriteSize = target.write(currentChunk);
               currentChunk.limit(originalLimit);
               written += (long)thisWriteSize;
               if (thisWriteSize < ioSize) {
                  keepGoing = false;
               }
            }

            if (keepGoing) {
               this.currentChunkIdx_$eq(this.currentChunkIdx() + 1);
               if (this.currentChunkIdx() == this.chunks().length) {
                  keepGoing = false;
               } else {
                  currentChunk = this.chunks()[this.currentChunkIdx()];
               }
            }
         }

         this._transferred_$eq(this._transferred() + written);
         return written;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$size$1(final long x$1, final ByteBuffer x$2) {
      return x$1 + (long)x$2.remaining();
   }

   public ChunkedByteBufferFileRegion(final ChunkedByteBuffer chunkedByteBuffer, final int ioChunkSize) {
      this.chunkedByteBuffer = chunkedByteBuffer;
      this.ioChunkSize = ioChunkSize;
      this._transferred = 0L;
      this.chunks = chunkedByteBuffer.getChunks();
      this.size = BoxesRunTime.unboxToLong(scala.collection.ArrayOps..MODULE$.foldLeft$extension(.MODULE$.refArrayOps((Object[])this.chunks()), BoxesRunTime.boxToLong(0L), (x$1, x$2) -> BoxesRunTime.boxToLong($anonfun$size$1(BoxesRunTime.unboxToLong(x$1), x$2))));
      this.currentChunkIdx = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
