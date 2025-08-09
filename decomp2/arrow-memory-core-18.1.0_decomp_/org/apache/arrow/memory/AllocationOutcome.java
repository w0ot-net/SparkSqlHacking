package org.apache.arrow.memory;

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;

public class AllocationOutcome {
   private final Status status;
   private final @Nullable AllocationOutcomeDetails details;
   static final AllocationOutcome SUCCESS_INSTANCE;

   AllocationOutcome(Status status, @Nullable AllocationOutcomeDetails details) {
      this.status = status;
      this.details = details;
   }

   AllocationOutcome(Status status) {
      this(status, (AllocationOutcomeDetails)null);
   }

   public Status getStatus() {
      return this.status;
   }

   public Optional getDetails() {
      return Optional.ofNullable(this.details);
   }

   public boolean isOk() {
      return this.status.isOk();
   }

   static {
      SUCCESS_INSTANCE = new AllocationOutcome(AllocationOutcome.Status.SUCCESS);
   }

   public static enum Status {
      SUCCESS(true),
      FORCED_SUCCESS(true),
      FAILED_LOCAL(false),
      FAILED_PARENT(false);

      private final boolean ok;

      private Status(boolean ok) {
         this.ok = ok;
      }

      public boolean isOk() {
         return this.ok;
      }

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{SUCCESS, FORCED_SUCCESS, FAILED_LOCAL, FAILED_PARENT};
      }
   }
}
