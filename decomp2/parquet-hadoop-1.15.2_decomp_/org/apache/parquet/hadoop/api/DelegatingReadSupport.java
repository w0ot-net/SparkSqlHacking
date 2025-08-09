package org.apache.parquet.hadoop.api;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;

public class DelegatingReadSupport extends ReadSupport {
   private final ReadSupport delegate;

   public DelegatingReadSupport(ReadSupport delegate) {
      this.delegate = delegate;
   }

   public ReadSupport.ReadContext init(InitContext context) {
      return this.delegate.init(context);
   }

   public RecordMaterializer prepareForRead(Configuration configuration, Map keyValueMetaData, MessageType fileSchema, ReadSupport.ReadContext readContext) {
      return this.delegate.prepareForRead(configuration, keyValueMetaData, fileSchema, readContext);
   }

   public RecordMaterializer prepareForRead(ParquetConfiguration configuration, Map keyValueMetaData, MessageType fileSchema, ReadSupport.ReadContext readContext) {
      return this.delegate.prepareForRead(configuration, keyValueMetaData, fileSchema, readContext);
   }

   public String toString() {
      return this.getClass().getName() + "(" + this.delegate.toString() + ")";
   }
}
