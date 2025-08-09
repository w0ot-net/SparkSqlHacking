package org.apache.spark.storage;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.network.buffer.ManagedBuffer;
import scala.Option;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a!\u0002\u000b\u0016\u0001Ui\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u00111\u0002!\u0011!Q\u0001\n5B\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\ti\u0001\u0011\t\u0011)A\u0005k!A1\b\u0001B\u0001B\u0003%Q\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0004E\u0001\t\u0007I\u0011B#\t\rI\u0003\u0001\u0015!\u0003G\u0011\u0015\u0019\u0006\u0001\"\u0011U\u0011\u0015A\u0006\u0001\"\u0011Z\u0011\u0015\u0001\u0007\u0001\"\u0011b\u0011\u0015A\u0007\u0001\"\u0011j\u0011\u0015\u0001\b\u0001\"\u0011j\u0011\u0015\t\b\u0001\"\u0011s\u0011\u0015\u0019\b\u0001\"\u0011s\u000f!!X#!A\t\u0002U)h\u0001\u0003\u000b\u0016\u0003\u0003E\t!\u0006<\t\u000bq\nB\u0011\u0001>\t\u000fm\f\u0012\u0013!C\u0001y\nI\"\t\\8dW6\u000bg.Y4fe6\u000bg.Y4fI\n+hMZ3s\u0015\t1r#A\u0004ti>\u0014\u0018mZ3\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\"\u0001\u0001\u0010\u0011\u0005}!S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013A\u00022vM\u001a,'O\u0003\u0002$/\u00059a.\u001a;x_J\\\u0017BA\u0013!\u00055i\u0015M\\1hK\u0012\u0014UO\u001a4fe\u0006\u0001\"\r\\8dW&sgm\\'b]\u0006<WM]\u0002\u0001!\tI#&D\u0001\u0016\u0013\tYSC\u0001\tCY>\u001c7.\u00138g_6\u000bg.Y4fe\u00069!\r\\8dW&#\u0007CA\u0015/\u0013\tySCA\u0004CY>\u001c7.\u00133\u0002\t\u0011\fG/\u0019\t\u0003SIJ!aM\u000b\u0003\u0013\tcwnY6ECR\f\u0017a\u00023jgB|7/\u001a\t\u0003mej\u0011a\u000e\u0006\u0002q\u0005)1oY1mC&\u0011!h\u000e\u0002\b\u0005>|G.Z1o\u0003I)h\u000e\\8dW>sG)Z1mY>\u001c\u0017\r^3\u0002\rqJg.\u001b;?)\u0019qt\bQ!C\u0007B\u0011\u0011\u0006\u0001\u0005\u0006M\u0019\u0001\r\u0001\u000b\u0005\u0006Y\u0019\u0001\r!\f\u0005\u0006a\u0019\u0001\r!\r\u0005\u0006i\u0019\u0001\r!\u000e\u0005\bw\u0019\u0001\n\u00111\u00016\u0003!\u0011XMZ\"pk:$X#\u0001$\u0011\u0005\u001d\u0003V\"\u0001%\u000b\u0005%S\u0015AB1u_6L7M\u0003\u0002L\u0019\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u00055s\u0015\u0001B;uS2T\u0011aT\u0001\u0005U\u00064\u0018-\u0003\u0002R\u0011\ni\u0011\t^8nS\u000eLe\u000e^3hKJ\f\u0011B]3g\u0007>,h\u000e\u001e\u0011\u0002\tML'0\u001a\u000b\u0002+B\u0011aGV\u0005\u0003/^\u0012A\u0001T8oO\u0006ia.[8CsR,')\u001e4gKJ$\u0012A\u0017\t\u00037zk\u0011\u0001\u0018\u0006\u0003;:\u000b1A\\5p\u0013\tyFL\u0001\u0006CsR,')\u001e4gKJ\f\u0011c\u0019:fCR,\u0017J\u001c9viN#(/Z1n)\u0005\u0011\u0007CA2g\u001b\u0005!'BA3O\u0003\tIw.\u0003\u0002hI\nY\u0011J\u001c9viN#(/Z1n\u00039\u0019wN\u001c<feR$vNT3uif$\u0012A\u001b\t\u0003W:l\u0011\u0001\u001c\u0006\u0003[:\u000bA\u0001\\1oO&\u0011q\u000e\u001c\u0002\u0007\u001f\nTWm\u0019;\u0002)\r|gN^3siR{g*\u001a;us\u001a{'oU:m\u0003\u0019\u0011X\r^1j]R\ta$A\u0004sK2,\u0017m]3\u00023\tcwnY6NC:\fw-\u001a:NC:\fw-\u001a3Ck\u001a4WM\u001d\t\u0003SE\u0019\"!E<\u0011\u0005YB\u0018BA=8\u0005\u0019\te.\u001f*fMR\tQ/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0002{*\u0012QG`\u0016\u0002\u007fB!\u0011\u0011AA\u0006\u001b\t\t\u0019A\u0003\u0003\u0002\u0006\u0005\u001d\u0011!C;oG\",7m[3e\u0015\r\tIaN\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0007\u0003\u0007\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public class BlockManagerManagedBuffer extends ManagedBuffer {
   private final BlockInfoManager blockInfoManager;
   private final BlockId blockId;
   private final BlockData data;
   private final boolean dispose;
   private final boolean unlockOnDeallocate;
   private final AtomicInteger refCount;

   public static boolean $lessinit$greater$default$5() {
      return BlockManagerManagedBuffer$.MODULE$.$lessinit$greater$default$5();
   }

   private AtomicInteger refCount() {
      return this.refCount;
   }

   public long size() {
      return this.data.size();
   }

   public ByteBuffer nioByteBuffer() {
      return this.data.toByteBuffer();
   }

   public InputStream createInputStream() {
      return this.data.toInputStream();
   }

   public Object convertToNetty() {
      return this.data.toNetty();
   }

   public Object convertToNettyForSsl() {
      return this.data.toNettyForSsl();
   }

   public ManagedBuffer retain() {
      this.refCount().incrementAndGet();
      Option locked = this.blockInfoManager.lockForReading(this.blockId, false);
      .MODULE$.assert(locked.isDefined());
      return this;
   }

   public ManagedBuffer release() {
      if (this.unlockOnDeallocate) {
         this.blockInfoManager.unlock(this.blockId, this.blockInfoManager.unlock$default$2());
      }

      if (this.refCount().decrementAndGet() == 0 && this.dispose) {
         this.data.dispose();
      }

      return this;
   }

   public BlockManagerManagedBuffer(final BlockInfoManager blockInfoManager, final BlockId blockId, final BlockData data, final boolean dispose, final boolean unlockOnDeallocate) {
      this.blockInfoManager = blockInfoManager;
      this.blockId = blockId;
      this.data = data;
      this.dispose = dispose;
      this.unlockOnDeallocate = unlockOnDeallocate;
      this.refCount = new AtomicInteger(1);
   }
}
