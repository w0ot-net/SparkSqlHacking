package org.apache.parquet.crypto;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.BadConfigurationException;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface EncryptionPropertiesFactory {
   Logger LOG = LoggerFactory.getLogger(EncryptionPropertiesFactory.class);
   String CRYPTO_FACTORY_CLASS_PROPERTY_NAME = "parquet.crypto.factory.class";

   static EncryptionPropertiesFactory loadFactory(Configuration conf) {
      return loadFactory((ParquetConfiguration)(new HadoopParquetConfiguration(conf)));
   }

   static EncryptionPropertiesFactory loadFactory(ParquetConfiguration conf) {
      Class<?> encryptionPropertiesFactoryClass = ConfigurationUtil.getClassFromConfig(conf, "parquet.crypto.factory.class", EncryptionPropertiesFactory.class);
      if (null == encryptionPropertiesFactoryClass) {
         LOG.debug("EncryptionPropertiesFactory is not configured - name not found in hadoop config");
         return null;
      } else {
         try {
            return (EncryptionPropertiesFactory)encryptionPropertiesFactoryClass.newInstance();
         } catch (IllegalAccessException | InstantiationException e) {
            throw new BadConfigurationException("could not instantiate encryptionPropertiesFactoryClass class: " + encryptionPropertiesFactoryClass, e);
         }
      }
   }

   FileEncryptionProperties getFileEncryptionProperties(Configuration var1, Path var2, WriteSupport.WriteContext var3) throws ParquetCryptoRuntimeException;
}
