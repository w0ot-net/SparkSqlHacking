package org.apache.orc.impl;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;

public interface KeyProvider {
   List getKeyNames() throws IOException;

   HadoopShims.KeyMetadata getCurrentKeyVersion(String var1) throws IOException;

   LocalKey createLocalKey(HadoopShims.KeyMetadata var1) throws IOException;

   Key decryptLocalKey(HadoopShims.KeyMetadata var1, byte[] var2) throws IOException;

   HadoopShims.KeyProviderKind getKind();

   public interface Factory {
      KeyProvider create(String var1, Configuration var2, Random var3) throws IOException;
   }
}
