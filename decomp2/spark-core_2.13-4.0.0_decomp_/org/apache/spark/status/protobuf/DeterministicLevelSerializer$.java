package org.apache.spark.status.protobuf;

import org.apache.spark.rdd.DeterministicLevel$;
import scala.Enumeration;
import scala.MatchError;

public final class DeterministicLevelSerializer$ {
   public static final DeterministicLevelSerializer$ MODULE$ = new DeterministicLevelSerializer$();

   public StoreTypes.DeterministicLevel serialize(final Enumeration.Value input) {
      Enumeration.Value var10000 = DeterministicLevel$.MODULE$.DETERMINATE();
      if (var10000 == null) {
         if (input == null) {
            return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_DETERMINATE;
         }
      } else if (var10000.equals(input)) {
         return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_DETERMINATE;
      }

      var10000 = DeterministicLevel$.MODULE$.UNORDERED();
      if (var10000 == null) {
         if (input == null) {
            return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_UNORDERED;
         }
      } else if (var10000.equals(input)) {
         return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_UNORDERED;
      }

      var10000 = DeterministicLevel$.MODULE$.INDETERMINATE();
      if (var10000 == null) {
         if (input == null) {
            return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_INDETERMINATE;
         }
      } else if (var10000.equals(input)) {
         return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_INDETERMINATE;
      }

      throw new MatchError(input);
   }

   public Enumeration.Value deserialize(final StoreTypes.DeterministicLevel binary) {
      if (StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_DETERMINATE.equals(binary)) {
         return DeterministicLevel$.MODULE$.DETERMINATE();
      } else if (StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_UNORDERED.equals(binary)) {
         return DeterministicLevel$.MODULE$.UNORDERED();
      } else {
         return StoreTypes.DeterministicLevel.DETERMINISTIC_LEVEL_INDETERMINATE.equals(binary) ? DeterministicLevel$.MODULE$.INDETERMINATE() : null;
      }
   }

   private DeterministicLevelSerializer$() {
   }
}
