package org.apache.spark.network.shuffle;

import java.util.EventListener;
import org.apache.spark.network.buffer.ManagedBuffer;

public interface BlockTransferListener extends EventListener {
   void onBlockTransferSuccess(String var1, ManagedBuffer var2);

   void onBlockTransferFailure(String var1, Throwable var2);

   String getTransferType();
}
