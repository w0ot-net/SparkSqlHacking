package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import org.apache.spark.network.protocol.Encodable;

public abstract class BlockTransferMessage implements Encodable {
   protected abstract Type type();

   public ByteBuffer toByteBuffer() {
      ByteBuf buf = Unpooled.buffer(this.encodedLength() + 1);
      buf.writeByte(this.type().id);
      this.encode(buf);

      assert buf.writableBytes() == 0 : "Writable bytes remain: " + buf.writableBytes();

      return buf.nioBuffer();
   }

   public static enum Type {
      OPEN_BLOCKS(0),
      UPLOAD_BLOCK(1),
      REGISTER_EXECUTOR(2),
      STREAM_HANDLE(3),
      REGISTER_DRIVER(4),
      HEARTBEAT(5),
      UPLOAD_BLOCK_STREAM(6),
      REMOVE_BLOCKS(7),
      BLOCKS_REMOVED(8),
      FETCH_SHUFFLE_BLOCKS(9),
      GET_LOCAL_DIRS_FOR_EXECUTORS(10),
      LOCAL_DIRS_FOR_EXECUTORS(11),
      PUSH_BLOCK_STREAM(12),
      FINALIZE_SHUFFLE_MERGE(13),
      MERGE_STATUSES(14),
      FETCH_SHUFFLE_BLOCK_CHUNKS(15),
      DIAGNOSE_CORRUPTION(16),
      CORRUPTION_CAUSE(17),
      PUSH_BLOCK_RETURN_CODE(18),
      REMOVE_SHUFFLE_MERGE(19);

      private final byte id;

      private Type(int id) {
         assert id < 128 : "Cannot have more than 128 message types";

         this.id = (byte)id;
      }

      public byte id() {
         return this.id;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{OPEN_BLOCKS, UPLOAD_BLOCK, REGISTER_EXECUTOR, STREAM_HANDLE, REGISTER_DRIVER, HEARTBEAT, UPLOAD_BLOCK_STREAM, REMOVE_BLOCKS, BLOCKS_REMOVED, FETCH_SHUFFLE_BLOCKS, GET_LOCAL_DIRS_FOR_EXECUTORS, LOCAL_DIRS_FOR_EXECUTORS, PUSH_BLOCK_STREAM, FINALIZE_SHUFFLE_MERGE, MERGE_STATUSES, FETCH_SHUFFLE_BLOCK_CHUNKS, DIAGNOSE_CORRUPTION, CORRUPTION_CAUSE, PUSH_BLOCK_RETURN_CODE, REMOVE_SHUFFLE_MERGE};
      }
   }

   public static class Decoder {
      public static BlockTransferMessage fromByteBuffer(ByteBuffer msg) {
         ByteBuf buf = Unpooled.wrappedBuffer(msg);
         byte type = buf.readByte();
         Object var10000;
         switch (type) {
            case 0:
               var10000 = OpenBlocks.decode(buf);
               break;
            case 1:
               var10000 = UploadBlock.decode(buf);
               break;
            case 2:
               var10000 = RegisterExecutor.decode(buf);
               break;
            case 3:
               var10000 = StreamHandle.decode(buf);
               break;
            case 4:
            case 5:
            default:
               throw new IllegalArgumentException("Unknown message type: " + type);
            case 6:
               var10000 = UploadBlockStream.decode(buf);
               break;
            case 7:
               var10000 = RemoveBlocks.decode(buf);
               break;
            case 8:
               var10000 = BlocksRemoved.decode(buf);
               break;
            case 9:
               var10000 = FetchShuffleBlocks.decode(buf);
               break;
            case 10:
               var10000 = GetLocalDirsForExecutors.decode(buf);
               break;
            case 11:
               var10000 = LocalDirsForExecutors.decode(buf);
               break;
            case 12:
               var10000 = PushBlockStream.decode(buf);
               break;
            case 13:
               var10000 = FinalizeShuffleMerge.decode(buf);
               break;
            case 14:
               var10000 = MergeStatuses.decode(buf);
               break;
            case 15:
               var10000 = FetchShuffleBlockChunks.decode(buf);
               break;
            case 16:
               var10000 = DiagnoseCorruption.decode(buf);
               break;
            case 17:
               var10000 = CorruptionCause.decode(buf);
               break;
            case 18:
               var10000 = BlockPushReturnCode.decode(buf);
               break;
            case 19:
               var10000 = RemoveShuffleMerge.decode(buf);
         }

         return (BlockTransferMessage)var10000;
      }
   }
}
