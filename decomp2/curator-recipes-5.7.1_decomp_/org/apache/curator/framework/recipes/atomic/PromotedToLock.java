package org.apache.curator.framework.recipes.atomic;

import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.PathUtils;

public class PromotedToLock {
   private final String path;
   private final long maxLockTime;
   private final TimeUnit maxLockTimeUnit;
   private final RetryPolicy retryPolicy;

   public static Builder builder() {
      return new Builder();
   }

   String getPath() {
      return this.path;
   }

   long getMaxLockTime() {
      return this.maxLockTime;
   }

   TimeUnit getMaxLockTimeUnit() {
      return this.maxLockTimeUnit;
   }

   RetryPolicy getRetryPolicy() {
      return this.retryPolicy;
   }

   private PromotedToLock(String path, long maxLockTime, TimeUnit maxLockTimeUnit, RetryPolicy retryPolicy) {
      this.path = path;
      this.maxLockTime = maxLockTime;
      this.maxLockTimeUnit = maxLockTimeUnit;
      this.retryPolicy = retryPolicy;
   }

   public static class Builder {
      private PromotedToLock instance;

      public PromotedToLock build() {
         Preconditions.checkNotNull(this.instance.path, "path cannot be null");
         Preconditions.checkNotNull(this.instance.retryPolicy, "retryPolicy cannot be null");
         return new PromotedToLock(this.instance.path, this.instance.maxLockTime, this.instance.maxLockTimeUnit, this.instance.retryPolicy);
      }

      public Builder lockPath(String path) {
         this.instance = new PromotedToLock(PathUtils.validatePath(path), this.instance.maxLockTime, this.instance.maxLockTimeUnit, this.instance.retryPolicy);
         return this;
      }

      public Builder retryPolicy(RetryPolicy retryPolicy) {
         this.instance = new PromotedToLock(this.instance.path, this.instance.maxLockTime, this.instance.maxLockTimeUnit, retryPolicy);
         return this;
      }

      public Builder timeout(long maxLockTime, TimeUnit maxLockTimeUnit) {
         this.instance = new PromotedToLock(this.instance.path, maxLockTime, maxLockTimeUnit, this.instance.retryPolicy);
         return this;
      }

      private Builder() {
         this.instance = new PromotedToLock((String)null, -1L, (TimeUnit)null, new RetryNTimes(0, 0));
      }
   }
}
