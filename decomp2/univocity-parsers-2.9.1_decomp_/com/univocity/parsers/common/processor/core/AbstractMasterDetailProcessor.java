package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.processor.MasterDetailRecord;
import com.univocity.parsers.common.processor.RowPlacement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMasterDetailProcessor extends AbstractObjectProcessor {
   private final AbstractObjectListProcessor detailProcessor;
   private MasterDetailRecord record;
   private final boolean isMasterRowAboveDetail;

   public AbstractMasterDetailProcessor(RowPlacement rowPlacement, AbstractObjectListProcessor detailProcessor) {
      ArgumentUtils.noNulls("Row processor for reading detail rows", detailProcessor);
      this.detailProcessor = detailProcessor;
      this.isMasterRowAboveDetail = rowPlacement == RowPlacement.TOP;
   }

   public AbstractMasterDetailProcessor(AbstractObjectListProcessor detailProcessor) {
      this(RowPlacement.TOP, detailProcessor);
   }

   public void processStarted(Context context) {
      this.detailProcessor.processStarted(context);
   }

   public final void rowProcessed(String[] row, Context context) {
      if (this.isMasterRecord(row, context)) {
         super.rowProcessed(row, context);
      } else {
         if (this.isMasterRowAboveDetail && this.record == null) {
            return;
         }

         this.detailProcessor.rowProcessed(row, context);
      }

   }

   public final void rowProcessed(Object[] row, Context context) {
      if (this.record == null) {
         this.record = new MasterDetailRecord();
         this.record.setMasterRow(row);
         if (this.isMasterRowAboveDetail) {
            return;
         }
      }

      this.processRecord(row, context);
   }

   private void processRecord(Object[] row, Context context) {
      List<Object[]> detailRows = this.detailProcessor.getRows();
      this.record.setDetailRows(new ArrayList(detailRows));
      if (!this.isMasterRowAboveDetail) {
         this.record.setMasterRow(row);
      }

      if (this.record.getMasterRow() != null) {
         this.masterDetailRecordProcessed(this.record.clone(), context);
         this.record.clear();
      }

      detailRows.clear();
      if (this.isMasterRowAboveDetail) {
         this.record.setMasterRow(row);
      }

   }

   public void processEnded(Context context) {
      super.processEnded(context);
      this.detailProcessor.processEnded(context);
      if (this.isMasterRowAboveDetail) {
         this.processRecord((Object[])null, context);
      }

   }

   protected abstract boolean isMasterRecord(String[] var1, Context var2);

   protected abstract void masterDetailRecordProcessed(MasterDetailRecord var1, Context var2);
}
