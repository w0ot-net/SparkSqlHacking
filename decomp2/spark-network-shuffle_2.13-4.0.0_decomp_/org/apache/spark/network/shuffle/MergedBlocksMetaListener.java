package org.apache.spark.network.shuffle;

import java.util.EventListener;

public interface MergedBlocksMetaListener extends EventListener {
   void onSuccess(int var1, int var2, int var3, MergedBlockMeta var4);

   void onFailure(int var1, int var2, int var3, Throwable var4);
}
