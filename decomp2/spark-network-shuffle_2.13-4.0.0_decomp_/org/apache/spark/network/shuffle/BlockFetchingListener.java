package org.apache.spark.network.shuffle;

import org.apache.spark.network.buffer.ManagedBuffer;

public interface BlockFetchingListener extends BlockTransferListener {
   void onBlockFetchSuccess(String var1, ManagedBuffer var2);

   void onBlockFetchFailure(String var1, Throwable var2);

   default void onBlockTransferSuccess(String blockId, ManagedBuffer data) {
      this.onBlockFetchSuccess(blockId, data);
   }

   default void onBlockTransferFailure(String blockId, Throwable exception) {
      this.onBlockFetchFailure(blockId, exception);
   }

   default String getTransferType() {
      return "fetch";
   }
}
