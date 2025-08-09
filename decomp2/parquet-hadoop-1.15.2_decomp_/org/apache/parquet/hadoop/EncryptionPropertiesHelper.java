package org.apache.parquet.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.EncryptionPropertiesFactory;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.io.OutputFile;

final class EncryptionPropertiesHelper {
   static FileEncryptionProperties createEncryptionProperties(ParquetConfiguration fileParquetConfig, OutputFile file, WriteSupport.WriteContext fileWriteContext) {
      EncryptionPropertiesFactory cryptoFactory = EncryptionPropertiesFactory.loadFactory(fileParquetConfig);
      if (null == cryptoFactory) {
         return null;
      } else {
         Configuration hadoopConf = ConfigurationUtil.createHadoopConfiguration(fileParquetConfig);
         return cryptoFactory.getFileEncryptionProperties(hadoopConf, file == null ? null : new Path(file.getPath()), fileWriteContext);
      }
   }

   static FileEncryptionProperties createEncryptionProperties(Configuration fileHadoopConfig, Path tempFilePath, WriteSupport.WriteContext fileWriteContext) {
      EncryptionPropertiesFactory cryptoFactory = EncryptionPropertiesFactory.loadFactory(fileHadoopConfig);
      return null == cryptoFactory ? null : cryptoFactory.getFileEncryptionProperties(fileHadoopConfig, tempFilePath, fileWriteContext);
   }
}
