package org.apache.parquet.crypto.keytools;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.crypto.KeyAccessDeniedException;

public interface KmsClient {
   String KMS_INSTANCE_ID_DEFAULT = "DEFAULT";
   String KMS_INSTANCE_URL_DEFAULT = "DEFAULT";
   String KEY_ACCESS_TOKEN_DEFAULT = "DEFAULT";

   void initialize(Configuration var1, String var2, String var3, String var4) throws KeyAccessDeniedException;

   String wrapKey(byte[] var1, String var2) throws KeyAccessDeniedException;

   byte[] unwrapKey(String var1, String var2) throws KeyAccessDeniedException;
}
