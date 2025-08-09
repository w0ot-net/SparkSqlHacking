package org.apache.datasketches.theta;

public enum UpdateReturnState {
   InsertedCountIncremented,
   InsertedCountIncrementedResized,
   InsertedCountIncrementedRebuilt,
   InsertedCountNotIncremented,
   ConcurrentBufferInserted,
   ConcurrentPropagated,
   RejectedDuplicate,
   RejectedNullOrEmpty,
   RejectedOverTheta;
}
