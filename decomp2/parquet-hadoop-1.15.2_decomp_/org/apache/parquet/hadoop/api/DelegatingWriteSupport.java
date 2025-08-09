package org.apache.parquet.hadoop.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.io.api.RecordConsumer;

public class DelegatingWriteSupport extends WriteSupport {
   private final WriteSupport delegate;

   public DelegatingWriteSupport(WriteSupport delegate) {
      this.delegate = delegate;
   }

   public WriteSupport.WriteContext init(Configuration configuration) {
      return this.delegate.init(configuration);
   }

   public WriteSupport.WriteContext init(ParquetConfiguration configuration) {
      return this.delegate.init(configuration);
   }

   public void prepareForWrite(RecordConsumer recordConsumer) {
      this.delegate.prepareForWrite(recordConsumer);
   }

   public void write(Object record) {
      this.delegate.write(record);
   }

   public String getName() {
      return this.delegate.getName();
   }

   public WriteSupport.FinalizedWriteContext finalizeWrite() {
      return this.delegate.finalizeWrite();
   }

   public String toString() {
      return this.getClass().getName() + "(" + this.delegate.toString() + ")";
   }
}
