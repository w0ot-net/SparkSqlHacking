package org.apache.spark.network.sasl;

public interface SecretKeyHolder {
   String getSaslUser(String var1);

   String getSecretKey(String var1);
}
