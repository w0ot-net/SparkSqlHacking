package com.univocity.parsers.common.record;

import com.univocity.parsers.common.Context;

public abstract class AbstractRecordFactory {
   protected final RecordMetaData metaData;

   public AbstractRecordFactory(Context context) {
      this.metaData = this.createMetaData(context);
   }

   public abstract Record newRecord(String[] var1);

   public abstract RecordMetaData createMetaData(Context var1);

   public final RecordMetaData getRecordMetaData() {
      return this.metaData;
   }
}
