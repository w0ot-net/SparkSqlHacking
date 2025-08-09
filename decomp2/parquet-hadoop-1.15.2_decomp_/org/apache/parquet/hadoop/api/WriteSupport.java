package org.apache.parquet.hadoop.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;

public abstract class WriteSupport {
   /** @deprecated */
   @Deprecated
   public abstract WriteContext init(Configuration var1);

   public WriteContext init(ParquetConfiguration configuration) {
      return this.init(ConfigurationUtil.createHadoopConfiguration(configuration));
   }

   public abstract void prepareForWrite(RecordConsumer var1);

   public abstract void write(Object var1);

   public String getName() {
      return null;
   }

   public FinalizedWriteContext finalizeWrite() {
      return new FinalizedWriteContext(new HashMap());
   }

   public static final class WriteContext {
      private final MessageType schema;
      private final Map extraMetaData;

      public WriteContext(MessageType schema, Map extraMetaData) {
         this.schema = (MessageType)Objects.requireNonNull(schema, "schema cannot be null");
         this.extraMetaData = Collections.unmodifiableMap((Map)Objects.requireNonNull(extraMetaData, "extraMetaData cannot be null"));
      }

      public MessageType getSchema() {
         return this.schema;
      }

      public Map getExtraMetaData() {
         return this.extraMetaData;
      }
   }

   public static final class FinalizedWriteContext {
      private final Map extraMetaData;

      public FinalizedWriteContext(Map extraMetaData) {
         this.extraMetaData = Collections.unmodifiableMap((Map)Objects.requireNonNull(extraMetaData, "extraMetaData cannot be null"));
      }

      public Map getExtraMetaData() {
         return this.extraMetaData;
      }
   }
}
