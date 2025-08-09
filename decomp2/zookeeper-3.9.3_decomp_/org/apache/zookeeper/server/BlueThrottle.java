package org.apache.zookeeper.server;

import java.util.Random;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlueThrottle {
   private static final Logger LOG = LoggerFactory.getLogger(BlueThrottle.class);
   private int maxTokens;
   private int fillTime;
   private int fillCount;
   private int tokens;
   private long lastTime;
   private int freezeTime;
   private long lastFreeze;
   private double dropIncrease;
   private double dropDecrease;
   private double decreasePoint;
   private double drop;
   Random rng;
   public static final String CONNECTION_THROTTLE_TOKENS = "zookeeper.connection_throttle_tokens";
   private static final int DEFAULT_CONNECTION_THROTTLE_TOKENS;
   public static final String CONNECTION_THROTTLE_FILL_TIME = "zookeeper.connection_throttle_fill_time";
   private static final int DEFAULT_CONNECTION_THROTTLE_FILL_TIME;
   public static final String CONNECTION_THROTTLE_FILL_COUNT = "zookeeper.connection_throttle_fill_count";
   private static final int DEFAULT_CONNECTION_THROTTLE_FILL_COUNT;
   public static final String CONNECTION_THROTTLE_FREEZE_TIME = "zookeeper.connection_throttle_freeze_time";
   private static final int DEFAULT_CONNECTION_THROTTLE_FREEZE_TIME;
   public static final String CONNECTION_THROTTLE_DROP_INCREASE = "zookeeper.connection_throttle_drop_increase";
   private static final double DEFAULT_CONNECTION_THROTTLE_DROP_INCREASE;
   public static final String CONNECTION_THROTTLE_DROP_DECREASE = "zookeeper.connection_throttle_drop_decrease";
   private static final double DEFAULT_CONNECTION_THROTTLE_DROP_DECREASE;
   public static final String CONNECTION_THROTTLE_DECREASE_RATIO = "zookeeper.connection_throttle_decrease_ratio";
   private static final double DEFAULT_CONNECTION_THROTTLE_DECREASE_RATIO;
   public static final String WEIGHED_CONNECTION_THROTTLE = "zookeeper.connection_throttle_weight_enabled";
   private static boolean connectionWeightEnabled;
   public static final String GLOBAL_SESSION_WEIGHT = "zookeeper.connection_throttle_global_session_weight";
   private static final int DEFAULT_GLOBAL_SESSION_WEIGHT;
   public static final String LOCAL_SESSION_WEIGHT = "zookeeper.connection_throttle_local_session_weight";
   private static final int DEFAULT_LOCAL_SESSION_WEIGHT;
   public static final String RENEW_SESSION_WEIGHT = "zookeeper.connection_throttle_renew_session_weight";
   private static final int DEFAULT_RENEW_SESSION_WEIGHT;

   protected static void setConnectionWeightEnabled(boolean enabled) {
      connectionWeightEnabled = enabled;
      logWeighedThrottlingSetting();
   }

   private static void logWeighedThrottlingSetting() {
      if (connectionWeightEnabled) {
         LOG.info("Weighed connection throttling is enabled. But it will only be effective if connection throttling is enabled");
         LOG.info("The weights for different session types are: global {} renew {} local {}", new Object[]{DEFAULT_GLOBAL_SESSION_WEIGHT, DEFAULT_RENEW_SESSION_WEIGHT, DEFAULT_LOCAL_SESSION_WEIGHT});
      } else {
         LOG.info("Weighed connection throttling is disabled");
      }

   }

   private static double getDoubleProp(String name, double def) {
      String val = System.getProperty(name);
      return val != null ? Double.parseDouble(val) : def;
   }

   public BlueThrottle() {
      this.maxTokens = DEFAULT_CONNECTION_THROTTLE_TOKENS;
      this.fillTime = DEFAULT_CONNECTION_THROTTLE_FILL_TIME;
      this.fillCount = DEFAULT_CONNECTION_THROTTLE_FILL_COUNT;
      this.tokens = this.maxTokens;
      this.lastTime = Time.currentElapsedTime();
      this.freezeTime = DEFAULT_CONNECTION_THROTTLE_FREEZE_TIME;
      this.lastFreeze = Time.currentElapsedTime();
      this.dropIncrease = DEFAULT_CONNECTION_THROTTLE_DROP_INCREASE;
      this.dropDecrease = DEFAULT_CONNECTION_THROTTLE_DROP_DECREASE;
      this.decreasePoint = DEFAULT_CONNECTION_THROTTLE_DECREASE_RATIO;
      this.drop = (double)0.0F;
      this.rng = new Random();
   }

   public synchronized void setMaxTokens(int max) {
      int deficit = this.maxTokens - this.tokens;
      this.maxTokens = max;
      this.tokens = max - deficit;
   }

   public synchronized void setFillTime(int time) {
      this.fillTime = time;
   }

   public synchronized void setFillCount(int count) {
      this.fillCount = count;
   }

   public synchronized void setFreezeTime(int time) {
      this.freezeTime = time;
   }

   public synchronized void setDropIncrease(double increase) {
      this.dropIncrease = increase;
   }

   public synchronized void setDropDecrease(double decrease) {
      this.dropDecrease = decrease;
   }

   public synchronized void setDecreasePoint(double ratio) {
      this.decreasePoint = ratio;
   }

   public synchronized int getMaxTokens() {
      return this.maxTokens;
   }

   public synchronized int getFillTime() {
      return this.fillTime;
   }

   public synchronized int getFillCount() {
      return this.fillCount;
   }

   public synchronized int getFreezeTime() {
      return this.freezeTime;
   }

   public synchronized double getDropIncrease() {
      return this.dropIncrease;
   }

   public synchronized double getDropDecrease() {
      return this.dropDecrease;
   }

   public synchronized double getDecreasePoint() {
      return this.decreasePoint;
   }

   public synchronized double getDropChance() {
      return this.drop;
   }

   public synchronized int getDeficit() {
      return this.maxTokens - this.tokens;
   }

   public int getRequiredTokensForGlobal() {
      return DEFAULT_GLOBAL_SESSION_WEIGHT;
   }

   public int getRequiredTokensForLocal() {
      return DEFAULT_LOCAL_SESSION_WEIGHT;
   }

   public int getRequiredTokensForRenew() {
      return DEFAULT_RENEW_SESSION_WEIGHT;
   }

   public boolean isConnectionWeightEnabled() {
      return connectionWeightEnabled;
   }

   public synchronized boolean checkLimit(int need) {
      if (this.maxTokens == 0) {
         return true;
      } else {
         long now = Time.currentElapsedTime();
         long diff = now - this.lastTime;
         if (diff > (long)this.fillTime) {
            int refill = (int)(diff * (long)this.fillCount / (long)this.fillTime);
            this.tokens = Math.min(this.tokens + refill, this.maxTokens);
            this.lastTime = now;
         }

         if (this.freezeTime != -1 && !this.checkBlue(now)) {
            return false;
         } else if (this.tokens < need) {
            return false;
         } else {
            this.tokens -= need;
            return true;
         }
      }
   }

   public synchronized boolean checkBlue(long now) {
      int length = this.maxTokens - this.tokens;
      int limit = this.maxTokens;
      long diff = now - this.lastFreeze;
      long threshold = Math.round((double)this.maxTokens * this.decreasePoint);
      if (diff > (long)this.freezeTime) {
         if (length == limit && this.drop < (double)1.0F) {
            this.drop = Math.min(this.drop + this.dropIncrease, (double)1.0F);
         } else if ((long)length <= threshold && this.drop > (double)0.0F) {
            this.drop = Math.max(this.drop - this.dropDecrease, (double)0.0F);
         }

         this.lastFreeze = now;
      }

      return !(this.rng.nextDouble() < this.drop);
   }

   static {
      int tokens = Integer.getInteger("zookeeper.connection_throttle_tokens", 0);
      int fillCount = Integer.getInteger("zookeeper.connection_throttle_fill_count", 1);
      connectionWeightEnabled = Boolean.getBoolean("zookeeper.connection_throttle_weight_enabled");
      int globalWeight = Integer.getInteger("zookeeper.connection_throttle_global_session_weight", 3);
      int localWeight = Integer.getInteger("zookeeper.connection_throttle_local_session_weight", 1);
      int renewWeight = Integer.getInteger("zookeeper.connection_throttle_renew_session_weight", 2);
      if (globalWeight <= 0) {
         LOG.warn("Invalid global session weight {}. It should be larger than 0", globalWeight);
         DEFAULT_GLOBAL_SESSION_WEIGHT = 3;
      } else if (globalWeight < localWeight) {
         LOG.warn("The global session weight {} is less than the local session weight {}. Use the local session weight.", globalWeight, localWeight);
         DEFAULT_GLOBAL_SESSION_WEIGHT = localWeight;
      } else {
         DEFAULT_GLOBAL_SESSION_WEIGHT = globalWeight;
      }

      if (localWeight <= 0) {
         LOG.warn("Invalid local session weight {}. It should be larger than 0", localWeight);
         DEFAULT_LOCAL_SESSION_WEIGHT = 1;
      } else {
         DEFAULT_LOCAL_SESSION_WEIGHT = localWeight;
      }

      if (renewWeight <= 0) {
         LOG.warn("Invalid renew session weight {}. It should be larger than 0", renewWeight);
         DEFAULT_RENEW_SESSION_WEIGHT = 2;
      } else if (renewWeight < localWeight) {
         LOG.warn("The renew session weight {} is less than the local session weight {}. Use the local session weight.", renewWeight, localWeight);
         DEFAULT_RENEW_SESSION_WEIGHT = localWeight;
      } else {
         DEFAULT_RENEW_SESSION_WEIGHT = renewWeight;
      }

      DEFAULT_CONNECTION_THROTTLE_TOKENS = connectionWeightEnabled ? DEFAULT_GLOBAL_SESSION_WEIGHT * tokens : tokens;
      DEFAULT_CONNECTION_THROTTLE_FILL_TIME = Integer.getInteger("zookeeper.connection_throttle_fill_time", 1);
      DEFAULT_CONNECTION_THROTTLE_FILL_COUNT = connectionWeightEnabled ? DEFAULT_GLOBAL_SESSION_WEIGHT * fillCount : fillCount;
      DEFAULT_CONNECTION_THROTTLE_FREEZE_TIME = Integer.getInteger("zookeeper.connection_throttle_freeze_time", -1);
      DEFAULT_CONNECTION_THROTTLE_DROP_INCREASE = getDoubleProp("zookeeper.connection_throttle_drop_increase", 0.02);
      DEFAULT_CONNECTION_THROTTLE_DROP_DECREASE = getDoubleProp("zookeeper.connection_throttle_drop_decrease", 0.002);
      DEFAULT_CONNECTION_THROTTLE_DECREASE_RATIO = getDoubleProp("zookeeper.connection_throttle_decrease_ratio", (double)0.0F);
      logWeighedThrottlingSetting();
   }
}
