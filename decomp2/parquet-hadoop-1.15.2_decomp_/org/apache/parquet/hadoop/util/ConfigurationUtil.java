package org.apache.parquet.hadoop.util;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.BadConfigurationException;

public class ConfigurationUtil {
   public static Class getClassFromConfig(Configuration configuration, String configName, Class assignableFrom) {
      return getClassFromConfig((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)), configName, assignableFrom);
   }

   public static Class getClassFromConfig(ParquetConfiguration configuration, String configName, Class assignableFrom) {
      String className = configuration.get(configName);
      if (className == null) {
         return null;
      } else {
         try {
            Class<?> foundClass = configuration.getClassByName(className);
            if (!assignableFrom.isAssignableFrom(foundClass)) {
               throw new BadConfigurationException("class " + className + " set in job conf at " + configName + " is not a subclass of " + assignableFrom.getCanonicalName());
            } else {
               return foundClass;
            }
         } catch (ClassNotFoundException e) {
            throw new BadConfigurationException("could not instantiate class " + className + " set in job conf at " + configName, e);
         }
      }
   }

   public static Configuration createHadoopConfiguration(ParquetConfiguration conf) {
      if (conf == null) {
         return new Configuration();
      } else if (conf instanceof HadoopParquetConfiguration) {
         return ((HadoopParquetConfiguration)conf).getConfiguration();
      } else {
         Configuration configuration = new Configuration();

         for(Map.Entry entry : conf) {
            configuration.set((String)entry.getKey(), (String)entry.getValue());
         }

         return configuration;
      }
   }
}
