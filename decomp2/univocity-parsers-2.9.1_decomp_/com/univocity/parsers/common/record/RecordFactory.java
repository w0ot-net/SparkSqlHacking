package com.univocity.parsers.common.record;

import com.univocity.parsers.common.Context;

public class RecordFactory extends AbstractRecordFactory {
   public RecordFactory(Context context) {
      super(context);
   }

   public Record newRecord(String[] data) {
      return new RecordImpl(data, (RecordMetaDataImpl)this.metaData);
   }

   public RecordMetaDataImpl createMetaData(Context context) {
      return new RecordMetaDataImpl(context);
   }
}
