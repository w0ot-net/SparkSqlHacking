package org.apache.curator.utils;

public class ZookeeperCompatibility {
   public static final ZookeeperCompatibility LATEST = builder().hasPersistentWatchers(true).build();
   private final boolean hasPersistentWatchers;

   public static Builder builder() {
      return new Builder();
   }

   private ZookeeperCompatibility(Builder builder) {
      this.hasPersistentWatchers = builder.hasPersistentWatchers;
   }

   public boolean hasPersistentWatchers() {
      return this.hasPersistentWatchers && Compatibility.hasPersistentWatchers();
   }

   public static class Builder {
      private boolean hasPersistentWatchers = false;

      public Builder hasPersistentWatchers(boolean value) {
         this.hasPersistentWatchers = value;
         return this;
      }

      public boolean hasPersistentWatchers() {
         return this.hasPersistentWatchers;
      }

      public ZookeeperCompatibility build() {
         return new ZookeeperCompatibility(this);
      }
   }
}
