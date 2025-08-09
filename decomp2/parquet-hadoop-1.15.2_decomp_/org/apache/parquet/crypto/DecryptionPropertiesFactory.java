package org.apache.parquet.crypto;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.BadConfigurationException;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DecryptionPropertiesFactory {
   Logger LOG = LoggerFactory.getLogger(DecryptionPropertiesFactory.class);
   String CRYPTO_FACTORY_CLASS_PROPERTY_NAME = "parquet.crypto.factory.class";

   static DecryptionPropertiesFactory loadFactory(Configuration conf) {
      Class<?> decryptionPropertiesFactoryClass = ConfigurationUtil.getClassFromConfig(conf, "parquet.crypto.factory.class", DecryptionPropertiesFactory.class);
      if (null == decryptionPropertiesFactoryClass) {
         LOG.debug("DecryptionPropertiesFactory is not configured - name not found in hadoop config");
         return null;
      } else {
         try {
            return (DecryptionPropertiesFactory)decryptionPropertiesFactoryClass.newInstance();
         } catch (IllegalAccessException | InstantiationException e) {
            throw new BadConfigurationException("could not instantiate decryptionPropertiesFactoryClass class: " + decryptionPropertiesFactoryClass, e);
         }
      }
   }

   FileDecryptionProperties getFileDecryptionProperties(Configuration var1, Path var2) throws ParquetCryptoRuntimeException;
}
