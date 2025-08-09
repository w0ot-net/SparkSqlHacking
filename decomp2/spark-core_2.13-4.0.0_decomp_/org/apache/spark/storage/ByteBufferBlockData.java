package org.apache.spark.storage;

import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Q!\u0004\b\u0001!YA\u0001\"\t\u0001\u0003\u0006\u0004%\ta\t\u0005\tY\u0001\u0011\t\u0011)A\u0005I!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u00030\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015A\u0004\u0001\"\u0011:\u0011\u0015\t\u0005\u0001\"\u0011C\u0011\u0015I\u0005\u0001\"\u0011K\u0011\u0015Y\u0005\u0001\"\u0011M\u0011\u0015Y\u0006\u0001\"\u0011]\u0011\u0015i\u0006\u0001\"\u0011_\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0005M\u0011\u0015\u0010^3Ck\u001a4WM\u001d\"m_\u000e\\G)\u0019;b\u0015\ty\u0001#A\u0004ti>\u0014\u0018mZ3\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0001A\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011adH\u0007\u0002\u001d%\u0011\u0001E\u0004\u0002\n\u00052|7m\u001b#bi\u0006\faAY;gM\u0016\u00148\u0001A\u000b\u0002IA\u0011QEK\u0007\u0002M)\u0011q\u0005K\u0001\u0003S>T!!\u000b\t\u0002\tU$\u0018\u000e\\\u0005\u0003W\u0019\u0012\u0011c\u00115v].,GMQ=uK\n+hMZ3s\u0003\u001d\u0011WO\u001a4fe\u0002\nQb\u001d5pk2$G)[:q_N,W#A\u0018\u0011\u0005a\u0001\u0014BA\u0019\u001a\u0005\u001d\u0011un\u001c7fC:\fab\u001d5pk2$G)[:q_N,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004kY:\u0004C\u0001\u0010\u0001\u0011\u0015\tS\u00011\u0001%\u0011\u0015iS\u00011\u00010\u00035!x.\u00138qkR\u001cFO]3b[R\t!\b\u0005\u0002<\u007f5\tAH\u0003\u0002({)\ta(\u0001\u0003kCZ\f\u0017B\u0001!=\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\u000fQ|g*\u001a;usR\t1\t\u0005\u0002E\u000f6\tQI\u0003\u0002G{\u0005!A.\u00198h\u0013\tAUI\u0001\u0004PE*,7\r^\u0001\u000ei>tU\r\u001e;z\r>\u00148k\u001d7\u0015\u0003]\t1\u0003^8DQVt7.\u001a3CsR,')\u001e4gKJ$\"\u0001J'\t\u000b9K\u0001\u0019A(\u0002\u0013\u0005dGn\\2bi>\u0014\b\u0003\u0002\rQ%VK!!U\r\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\rT\u0013\t!\u0016DA\u0002J]R\u0004\"AV-\u000e\u0003]S!\u0001W\u001f\u0002\u00079Lw.\u0003\u0002[/\nQ!)\u001f;f\u0005V4g-\u001a:\u0002\u0019Q|')\u001f;f\u0005V4g-\u001a:\u0015\u0003U\u000bAa]5{KV\tq\f\u0005\u0002\u0019A&\u0011\u0011-\u0007\u0002\u0005\u0019>tw-A\u0004eSN\u0004xn]3\u0015\u0003\u0011\u0004\"\u0001G3\n\u0005\u0019L\"\u0001B+oSR\u0004"
)
public class ByteBufferBlockData implements BlockData {
   private final ChunkedByteBuffer buffer;
   private final boolean shouldDispose;

   public ChunkedByteBuffer buffer() {
      return this.buffer;
   }

   public boolean shouldDispose() {
      return this.shouldDispose;
   }

   public InputStream toInputStream() {
      return this.buffer().toInputStream(this.buffer().toInputStream$default$1());
   }

   public Object toNetty() {
      return this.buffer().toNetty();
   }

   public Object toNettyForSsl() {
      return this.buffer().toNettyForSsl();
   }

   public ChunkedByteBuffer toChunkedByteBuffer(final Function1 allocator) {
      return this.buffer().copy(allocator);
   }

   public ByteBuffer toByteBuffer() {
      return this.buffer().toByteBuffer();
   }

   public long size() {
      return this.buffer().size();
   }

   public void dispose() {
      if (this.shouldDispose()) {
         this.buffer().dispose();
      }
   }

   public ByteBufferBlockData(final ChunkedByteBuffer buffer, final boolean shouldDispose) {
      this.buffer = buffer;
      this.shouldDispose = shouldDispose;
   }
}
