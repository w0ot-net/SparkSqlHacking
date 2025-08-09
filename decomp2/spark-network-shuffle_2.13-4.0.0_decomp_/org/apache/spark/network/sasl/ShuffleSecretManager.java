package org.apache.spark.network.sasl;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.APP_ID.;
import org.apache.spark.network.util.JavaUtils;

public class ShuffleSecretManager implements SecretKeyHolder {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ShuffleSecretManager.class);
   private final ConcurrentHashMap shuffleSecretMap = new ConcurrentHashMap();
   private static final String SPARK_SASL_USER = "sparkSaslUser";

   public void registerApp(String appId, String shuffleSecret) {
      this.shuffleSecretMap.put(appId, shuffleSecret);
      logger.info("Registered shuffle secret for application {}", new MDC[]{MDC.of(.MODULE$, appId)});
   }

   public void registerApp(String appId, ByteBuffer shuffleSecret) {
      this.registerApp(appId, JavaUtils.bytesToString(shuffleSecret));
   }

   public void unregisterApp(String appId) {
      this.shuffleSecretMap.remove(appId);
      logger.info("Unregistered shuffle secret for application {}", new MDC[]{MDC.of(.MODULE$, appId)});
   }

   public String getSaslUser(String appId) {
      return "sparkSaslUser";
   }

   public String getSecretKey(String appId) {
      return (String)this.shuffleSecretMap.get(appId);
   }
}
