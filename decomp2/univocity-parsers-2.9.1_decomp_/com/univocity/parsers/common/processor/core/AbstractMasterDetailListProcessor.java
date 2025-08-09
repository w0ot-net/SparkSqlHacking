package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.processor.MasterDetailRecord;
import com.univocity.parsers.common.processor.RowPlacement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMasterDetailListProcessor extends AbstractMasterDetailProcessor {
   private final List records = new ArrayList();
   private String[] headers;

   public AbstractMasterDetailListProcessor(RowPlacement rowPlacement, AbstractObjectListProcessor detailProcessor) {
      super(rowPlacement, detailProcessor);
   }

   public AbstractMasterDetailListProcessor(AbstractObjectListProcessor detailProcessor) {
      super(detailProcessor);
   }

   protected void masterDetailRecordProcessed(MasterDetailRecord record, Context context) {
      this.records.add(record);
   }

   public void processEnded(Context context) {
      this.headers = context.headers();
      super.processEnded(context);
   }

   public List getRecords() {
      return this.records;
   }

   public String[] getHeaders() {
      return this.headers;
   }
}
