package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import org.apache.spark.network.buffer.ManagedBuffer;

public interface Message extends Encodable {
   Type type();

   ManagedBuffer body();

   boolean isBodyInFrame();

   public static enum Type implements Encodable {
      ChunkFetchRequest(0),
      ChunkFetchSuccess(1),
      ChunkFetchFailure(2),
      RpcRequest(3),
      RpcResponse(4),
      RpcFailure(5),
      StreamRequest(6),
      StreamResponse(7),
      StreamFailure(8),
      OneWayMessage(9),
      UploadStream(10),
      MergedBlockMetaRequest(11),
      MergedBlockMetaSuccess(12),
      User(-1);

      private final byte id;

      private Type(int id) {
         assert id < 128 : "Cannot have more than 128 message types";

         this.id = (byte)id;
      }

      public byte id() {
         return this.id;
      }

      public int encodedLength() {
         return 1;
      }

      public void encode(ByteBuf buf) {
         buf.writeByte(this.id);
      }

      public static Type decode(ByteBuf buf) {
         byte id = buf.readByte();
         Type var10000;
         switch (id) {
            case -1 -> throw new IllegalArgumentException("User type messages cannot be decoded.");
            case 0 -> var10000 = ChunkFetchRequest;
            case 1 -> var10000 = ChunkFetchSuccess;
            case 2 -> var10000 = ChunkFetchFailure;
            case 3 -> var10000 = RpcRequest;
            case 4 -> var10000 = RpcResponse;
            case 5 -> var10000 = RpcFailure;
            case 6 -> var10000 = StreamRequest;
            case 7 -> var10000 = StreamResponse;
            case 8 -> var10000 = StreamFailure;
            case 9 -> var10000 = OneWayMessage;
            case 10 -> var10000 = UploadStream;
            case 11 -> var10000 = MergedBlockMetaRequest;
            case 12 -> var10000 = MergedBlockMetaSuccess;
            default -> throw new IllegalArgumentException("Unknown message type: " + id);
         }

         return var10000;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{ChunkFetchRequest, ChunkFetchSuccess, ChunkFetchFailure, RpcRequest, RpcResponse, RpcFailure, StreamRequest, StreamResponse, StreamFailure, OneWayMessage, UploadStream, MergedBlockMetaRequest, MergedBlockMetaSuccess, User};
      }
   }
}
