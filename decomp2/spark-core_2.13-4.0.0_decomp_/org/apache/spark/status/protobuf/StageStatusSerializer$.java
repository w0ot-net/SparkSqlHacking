package org.apache.spark.status.protobuf;

import org.apache.spark.status.api.v1.StageStatus;
import scala.MatchError;

public final class StageStatusSerializer$ {
   public static final StageStatusSerializer$ MODULE$ = new StageStatusSerializer$();

   public StoreTypes.StageStatus serialize(final StageStatus input) {
      if (StageStatus.ACTIVE.equals(input)) {
         return StoreTypes.StageStatus.STAGE_STATUS_ACTIVE;
      } else if (StageStatus.COMPLETE.equals(input)) {
         return StoreTypes.StageStatus.STAGE_STATUS_COMPLETE;
      } else if (StageStatus.FAILED.equals(input)) {
         return StoreTypes.StageStatus.STAGE_STATUS_FAILED;
      } else if (StageStatus.PENDING.equals(input)) {
         return StoreTypes.StageStatus.STAGE_STATUS_PENDING;
      } else if (StageStatus.SKIPPED.equals(input)) {
         return StoreTypes.StageStatus.STAGE_STATUS_SKIPPED;
      } else {
         throw new MatchError(input);
      }
   }

   public StageStatus deserialize(final StoreTypes.StageStatus binary) {
      if (StoreTypes.StageStatus.STAGE_STATUS_ACTIVE.equals(binary)) {
         return StageStatus.ACTIVE;
      } else if (StoreTypes.StageStatus.STAGE_STATUS_COMPLETE.equals(binary)) {
         return StageStatus.COMPLETE;
      } else if (StoreTypes.StageStatus.STAGE_STATUS_FAILED.equals(binary)) {
         return StageStatus.FAILED;
      } else if (StoreTypes.StageStatus.STAGE_STATUS_PENDING.equals(binary)) {
         return StageStatus.PENDING;
      } else {
         return StoreTypes.StageStatus.STAGE_STATUS_SKIPPED.equals(binary) ? StageStatus.SKIPPED : null;
      }
   }

   private StageStatusSerializer$() {
   }
}
