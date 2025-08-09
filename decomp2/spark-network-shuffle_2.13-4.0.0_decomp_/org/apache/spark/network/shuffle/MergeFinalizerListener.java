package org.apache.spark.network.shuffle;

import java.util.EventListener;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;

public interface MergeFinalizerListener extends EventListener {
   void onShuffleMergeSuccess(MergeStatuses var1);

   void onShuffleMergeFailure(Throwable var1);
}
