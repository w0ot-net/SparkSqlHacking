package org.apache.spark.network.client;

import org.apache.spark.network.buffer.ManagedBuffer;

public interface MergedBlockMetaResponseCallback extends BaseResponseCallback {
   void onSuccess(int var1, ManagedBuffer var2);
}
