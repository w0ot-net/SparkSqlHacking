package org.apache.spark.network.shuffle;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.sparkproject.guava.base.Preconditions;

public final class AppsWithRecoveryDisabled {
   private static final AppsWithRecoveryDisabled INSTANCE = new AppsWithRecoveryDisabled();
   private final Set appsWithRecoveryDisabled = Collections.newSetFromMap(new ConcurrentHashMap());

   private AppsWithRecoveryDisabled() {
   }

   public static void disableRecoveryOfApp(String appId) {
      Preconditions.checkNotNull(appId);
      INSTANCE.appsWithRecoveryDisabled.add(appId);
   }

   public static boolean isRecoveryEnabledForApp(String appId) {
      Preconditions.checkNotNull(appId);
      return !INSTANCE.appsWithRecoveryDisabled.contains(appId);
   }

   public static void removeApp(String appId) {
      Preconditions.checkNotNull(appId);
      INSTANCE.appsWithRecoveryDisabled.remove(appId);
   }
}
