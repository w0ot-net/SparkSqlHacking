package org.apache.parquet.hadoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.apache.hadoop.conf.Configuration;

class ColumnConfigParser {
   private final List helpers = new ArrayList();

   public ColumnConfigParser withColumnConfig(String rootKey, Function function, BiConsumer consumer) {
      this.helpers.add(new ConfigHelper(rootKey + '#', function, consumer));
      return this;
   }

   public void parseConfig(Configuration conf) {
      for(Map.Entry entry : conf) {
         for(ConfigHelper helper : this.helpers) {
            helper.processKey((String)entry.getKey());
         }
      }

   }

   private static class ConfigHelper {
      private final String prefix;
      private final Function function;
      private final BiConsumer consumer;

      public ConfigHelper(String prefix, Function function, BiConsumer consumer) {
         this.prefix = prefix;
         this.function = function;
         this.consumer = consumer;
      }

      public void processKey(String key) {
         if (key.startsWith(this.prefix)) {
            String columnPath = key.substring(this.prefix.length());
            T value = (T)this.function.apply(key);
            this.consumer.accept(columnPath, value);
         }

      }
   }
}
