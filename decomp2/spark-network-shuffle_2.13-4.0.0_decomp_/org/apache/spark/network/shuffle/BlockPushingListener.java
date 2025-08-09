package org.apache.spark.network.shuffle;

import org.apache.spark.network.buffer.ManagedBuffer;

public interface BlockPushingListener extends BlockTransferListener {
   void onBlockPushSuccess(String var1, ManagedBuffer var2);

   void onBlockPushFailure(String var1, Throwable var2);

   default void onBlockTransferSuccess(String blockId, ManagedBuffer data) {
      this.onBlockPushSuccess(blockId, data);
   }

   default void onBlockTransferFailure(String blockId, Throwable exception) {
      this.onBlockPushFailure(blockId, exception);
   }

   default String getTransferType() {
      return "push";
   }
}
