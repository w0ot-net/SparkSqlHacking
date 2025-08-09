package org.apache.derby.impl.sql.execute;

import java.io.InputStream;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public abstract class DMLWriteResultSet extends NoRowsResultSetImpl {
   protected WriteCursorConstantAction constantAction;
   protected int[] baseRowReadMap;
   protected int[] streamStorableHeapColIds;
   protected DynamicCompiledOpenConglomInfo heapDCOCI;
   protected DynamicCompiledOpenConglomInfo[] indexDCOCIs;
   private boolean needToObjectifyStream;
   public long rowCount;
   protected ResultDescription resultDescription;
   protected DataValueDescriptor[] cachedDestinations;

   DMLWriteResultSet(Activation var1) throws StandardException {
      this(var1, var1.getConstantAction());
   }

   DMLWriteResultSet(Activation var1, ConstantAction var2) throws StandardException {
      super(var1);
      this.constantAction = (WriteCursorConstantAction)var2;
      this.baseRowReadMap = this.constantAction.getBaseRowReadMap();
      this.streamStorableHeapColIds = this.constantAction.getStreamStorableHeapColIds();
      TransactionController var3 = var1.getTransactionController();
      if (!(var2 instanceof UpdatableVTIConstantAction)) {
         this.heapDCOCI = var3.getDynamicCompiledConglomInfo(this.constantAction.conglomId);
         if (this.constantAction.indexCIDS.length != 0) {
            this.indexDCOCIs = new DynamicCompiledOpenConglomInfo[this.constantAction.indexCIDS.length];

            for(int var4 = 0; var4 < this.constantAction.indexCIDS.length; ++var4) {
               this.indexDCOCIs[var4] = var3.getDynamicCompiledConglomInfo(this.constantAction.indexCIDS[var4]);
            }
         }
      }

      this.needToObjectifyStream = this.constantAction.getTriggerInfo() != null;
   }

   public final long modifiedRowCount() {
      return this.rowCount + RowUtil.getRowCountBase();
   }

   public ResultDescription getResultDescription() {
      return this.resultDescription;
   }

   protected ExecRow getNextRowCore(NoPutResultSet var1) throws StandardException {
      ExecRow var2 = var1.getNextRowCore();
      if (this.needToObjectifyStream) {
         this.objectifyStreams(var2);
      }

      return var2;
   }

   private void objectifyStreams(ExecRow var1) throws StandardException {
      if (var1 != null && this.streamStorableHeapColIds != null) {
         for(int var2 = 0; var2 < this.streamStorableHeapColIds.length; ++var2) {
            int var3 = this.streamStorableHeapColIds[var2];
            int var4 = this.baseRowReadMap == null ? var3 : this.baseRowReadMap[var3];
            DataValueDescriptor var5 = var1.getColumn(var4 + 1);
            if (var5 != null) {
               InputStream var6 = ((StreamStorable)var5).returnStream();
               ((StreamStorable)var5).loadStream();
               if (var6 != null) {
                  for(int var7 = 1; var7 <= var1.nColumns(); ++var7) {
                     DataValueDescriptor var8 = var1.getColumn(var7);
                     if (var8 instanceof StreamStorable && ((StreamStorable)var8).returnStream() == var6) {
                        var1.setColumn(var7, var5.cloneValue(false));
                     }
                  }
               }
            }
         }
      }

   }

   protected ExecRow makeDeferredSparseRow(ExecRow var1, FormatableBitSet var2, LanguageConnectionContext var3) throws StandardException {
      ExecRow var4;
      if (var2 == null) {
         var4 = var1;
      } else {
         var4 = RowUtil.getEmptyValueRow(var2.getLength() - 1, var3);
         int var5 = 1;

         for(int var6 = 1; var6 <= var4.nColumns(); ++var6) {
            if (var2.isSet(var6)) {
               var4.setColumn(var6, var1.getColumn(var5++));
            }
         }
      }

      return var4;
   }

   int decodeLockMode(int var1) {
      if (var1 >>> 16 == 0) {
         return var1;
      } else {
         int var2 = this.lcc.getCurrentIsolationLevel();
         return var2 == 4 ? var1 >>> 16 : var1 & 255;
      }
   }

   String getIndexNameFromCID(long var1) {
      return this.constantAction.getIndexNameFromCID(var1);
   }

   protected ExecRow normalizeRow(NoPutResultSet var1, ExecRow var2) throws StandardException {
      int var3 = this.resultDescription.getColumnCount();
      if (this.cachedDestinations == null) {
         this.cachedDestinations = new DataValueDescriptor[var3];

         for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = var4 + 1;
            ResultColumnDescriptor var6 = this.resultDescription.getColumnDescriptor(var5);
            this.cachedDestinations[var4] = var6.getType().getNull();
         }
      }

      for(int var7 = 0; var7 < var3; ++var7) {
         int var8 = var7 + 1;
         DataTypeDescriptor var9 = this.resultDescription.getColumnDescriptor(var8).getType();
         if (var2.getColumn(var8) == null) {
            var2.setColumn(var8, var9.getNull());
         }

         var2.setColumn(var8, NormalizeResultSet.normalizeColumn(var9, var2, var8, this.cachedDestinations[var7], this.resultDescription));
      }

      this.activation.setCurrentRow(var2, var1.resultSetNumber());
      return var2;
   }

   public void rememberConstraint(UUID var1) throws StandardException {
   }
}
