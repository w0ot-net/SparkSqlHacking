package io.fabric8.kubernetes.client.extended.leaderelection.resourcelock;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.Generated;

public class LeaderElectionRecord {
   private final String holderIdentity;
   private final Duration leaseDuration;
   @JsonFormat(
      timezone = "UTC",
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
   )
   private final ZonedDateTime acquireTime;
   @JsonFormat(
      timezone = "UTC",
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
   )
   private final ZonedDateTime renewTime;
   private final int leaderTransitions;

   @JsonCreator
   public LeaderElectionRecord(@JsonProperty("holderIdentity") String holderIdentity, @JsonProperty("leaseDuration") Duration leaseDuration, @JsonProperty("acquireTime") ZonedDateTime acquireTime, @JsonProperty("renewTime") ZonedDateTime renewTime, @JsonProperty("leaderTransitions") int leaderTransitions) {
      this.holderIdentity = holderIdentity;
      this.leaseDuration = (Duration)Objects.requireNonNull(leaseDuration, "leaseDuration is required");
      this.acquireTime = (ZonedDateTime)Objects.requireNonNull(acquireTime, "acquireTime is required");
      this.renewTime = (ZonedDateTime)Objects.requireNonNull(renewTime, "renewTime is required");
      this.leaderTransitions = leaderTransitions;
   }

   public String getHolderIdentity() {
      return this.holderIdentity;
   }

   public Duration getLeaseDuration() {
      return this.leaseDuration;
   }

   public ZonedDateTime getAcquireTime() {
      return this.acquireTime;
   }

   public ZonedDateTime getRenewTime() {
      return this.renewTime;
   }

   public int getLeaderTransitions() {
      return this.leaderTransitions;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         LeaderElectionRecord that = (LeaderElectionRecord)o;
         return this.leaderTransitions == that.leaderTransitions && Objects.equals(this.holderIdentity, that.holderIdentity) && Objects.equals(this.leaseDuration, that.leaseDuration) && Objects.equals(this.acquireTime, that.acquireTime) && Objects.equals(this.renewTime, that.renewTime);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.holderIdentity, this.leaseDuration, this.acquireTime, this.renewTime, this.leaderTransitions});
   }

   @Generated
   public static LeaderElectionRecordBuilder builder() {
      return new LeaderElectionRecordBuilder();
   }

   @Generated
   public LeaderElectionRecordBuilder toBuilder() {
      return (new LeaderElectionRecordBuilder()).holderIdentity(this.holderIdentity).leaseDuration(this.leaseDuration).acquireTime(this.acquireTime).renewTime(this.renewTime).leaderTransitions(this.leaderTransitions);
   }

   @Generated
   public static class LeaderElectionRecordBuilder {
      @Generated
      private String holderIdentity;
      @Generated
      private Duration leaseDuration;
      @Generated
      private ZonedDateTime acquireTime;
      @Generated
      private ZonedDateTime renewTime;
      @Generated
      private int leaderTransitions;

      @Generated
      LeaderElectionRecordBuilder() {
      }

      @Generated
      public LeaderElectionRecordBuilder holderIdentity(String holderIdentity) {
         this.holderIdentity = holderIdentity;
         return this;
      }

      @Generated
      public LeaderElectionRecordBuilder leaseDuration(Duration leaseDuration) {
         this.leaseDuration = leaseDuration;
         return this;
      }

      @JsonFormat(
         timezone = "UTC",
         pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
      )
      @Generated
      public LeaderElectionRecordBuilder acquireTime(ZonedDateTime acquireTime) {
         this.acquireTime = acquireTime;
         return this;
      }

      @JsonFormat(
         timezone = "UTC",
         pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
      )
      @Generated
      public LeaderElectionRecordBuilder renewTime(ZonedDateTime renewTime) {
         this.renewTime = renewTime;
         return this;
      }

      @Generated
      public LeaderElectionRecordBuilder leaderTransitions(int leaderTransitions) {
         this.leaderTransitions = leaderTransitions;
         return this;
      }

      @Generated
      public LeaderElectionRecord build() {
         return new LeaderElectionRecord(this.holderIdentity, this.leaseDuration, this.acquireTime, this.renewTime, this.leaderTransitions);
      }

      @Generated
      public String toString() {
         return "LeaderElectionRecord.LeaderElectionRecordBuilder(holderIdentity=" + this.holderIdentity + ", leaseDuration=" + this.leaseDuration + ", acquireTime=" + this.acquireTime + ", renewTime=" + this.renewTime + ", leaderTransitions=" + this.leaderTransitions + ")";
      }
   }
}
