package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.TemporaryRowHolder;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLRef;
import org.apache.derby.shared.common.error.StandardException;

class TemporaryRowHolderImpl implements TemporaryRowHolder {
   public static final int DEFAULT_OVERFLOWTHRESHOLD = 5;
   protected static final int STATE_UNINIT = 0;
   protected static final int STATE_INSERT = 1;
   protected static final int STATE_DRAIN = 2;
   protected ExecRow[] rowArray;
   protected int lastArraySlot;
   private int numRowsIn;
   protected int state;
   private long CID;
   private boolean conglomCreated;
   private ConglomerateController cc;
   private Properties properties;
   private ScanController scan;
   private ResultDescription resultDescription;
   Activation activation;
   private boolean isUniqueStream;
   private boolean isVirtualMemHeap;
   private boolean uniqueIndexCreated;
   private boolean positionIndexCreated;
   private long uniqueIndexConglomId;
   private long positionIndexConglomId;
   private ConglomerateController uniqueIndex_cc;
   private ConglomerateController positionIndex_cc;
   private DataValueDescriptor[] uniqueIndexRow;
   private DataValueDescriptor[] positionIndexRow;
   private RowLocation destRowLocation;
   private SQLLongint position_sqllong;

   public TemporaryRowHolderImpl(Activation var1, Properties var2, ResultDescription var3) {
      this(var1, var2, var3, 5, false, false);
   }

   public TemporaryRowHolderImpl(Activation var1, Properties var2, ResultDescription var3, boolean var4) {
      this(var1, var2, var3, 1, var4, false);
   }

   public TemporaryRowHolderImpl(Activation var1, Properties var2, ResultDescription var3, int var4, boolean var5, boolean var6) {
      this.state = 0;
      this.uniqueIndexRow = null;
      this.positionIndexRow = null;
      this.activation = var1;
      this.properties = var2;
      this.resultDescription = var3;
      this.isUniqueStream = var5;
      this.isVirtualMemHeap = var6;
      this.rowArray = new ExecRow[var4];
      this.lastArraySlot = -1;
   }

   private ExecRow cloneRow(ExecRow var1) {
      DataValueDescriptor[] var2 = var1.getRowArray();
      int var3 = var2.length;
      ExecRow var4 = ((ValueRow)var1).cloneMe();

      for(int var5 = 0; var5 < var3; ++var5) {
         if (var2[var5] != null) {
            var4.setColumn(var5 + 1, var2[var5].cloneHolder());
         }
      }

      if (var1 instanceof IndexValueRow) {
         return new IndexValueRow(var4);
      } else {
         return var4;
      }
   }

   public void insert(ExecRow var1) throws StandardException {
      if (!this.isVirtualMemHeap) {
         this.state = 1;
      }

      if (!this.uniqueIndexCreated || !this.isRowAlreadyExist(var1)) {
         ++this.numRowsIn;
         if (this.lastArraySlot + 1 < this.rowArray.length) {
            this.rowArray[++this.lastArraySlot] = this.cloneRow(var1);
            if (!this.isUniqueStream) {
               return;
            }
         }

         if (!this.conglomCreated) {
            TransactionController var2 = this.activation.getTransactionController();
            Object var3 = null;
            this.CID = var2.createConglomerate("heap", var1.getRowArray(), (ColumnOrdering[])null, (int[])var3, this.properties, 3);
            this.conglomCreated = true;
            this.cc = var2.openConglomerate(this.CID, false, 4, 7, 5);
            if (this.isUniqueStream) {
               this.destRowLocation = this.cc.newRowLocationTemplate();
            }
         }

         int var4 = 0;
         if (this.isUniqueStream) {
            this.cc.insertAndFetchLocation(var1.getRowArray(), this.destRowLocation);
            this.insertToPositionIndex(this.numRowsIn - 1, this.destRowLocation);
            if (!this.uniqueIndexCreated) {
               this.isRowAlreadyExist(var1);
            }
         } else {
            var4 = this.cc.insert(var1.getRowArray());
            if (this.isVirtualMemHeap) {
               this.state = 1;
            }
         }

      }
   }

   private boolean isRowAlreadyExist(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(var1.nColumns());
      if (this.CID != 0L && var2 instanceof SQLRef) {
         RowLocation var3 = (RowLocation)var2.getObject();
         if (!this.uniqueIndexCreated) {
            TransactionController var4 = this.activation.getTransactionController();
            byte var5 = 2;
            this.uniqueIndexRow = new DataValueDescriptor[var5];
            this.uniqueIndexRow[0] = var3;
            this.uniqueIndexRow[1] = var3;
            Properties var6 = this.makeIndexProperties(this.uniqueIndexRow, this.CID);
            this.uniqueIndexConglomId = var4.createConglomerate("BTREE", this.uniqueIndexRow, (ColumnOrdering[])null, (int[])null, var6, 3);
            this.uniqueIndex_cc = var4.openConglomerate(this.uniqueIndexConglomId, false, 4, 7, 5);
            this.uniqueIndexCreated = true;
         }

         this.uniqueIndexRow[0] = var3;
         this.uniqueIndexRow[1] = var3;
         int var7;
         if ((var7 = this.uniqueIndex_cc.insert(this.uniqueIndexRow)) != 0 && var7 == 1) {
            return true;
         }
      }

      return false;
   }

   private void insertToPositionIndex(int var1, RowLocation var2) throws StandardException {
      if (!this.positionIndexCreated) {
         TransactionController var3 = this.activation.getTransactionController();
         byte var4 = 2;
         this.position_sqllong = new SQLLongint();
         this.positionIndexRow = new DataValueDescriptor[var4];
         this.positionIndexRow[0] = this.position_sqllong;
         this.positionIndexRow[1] = var2;
         Properties var5 = this.makeIndexProperties(this.positionIndexRow, this.CID);
         this.positionIndexConglomId = var3.createConglomerate("BTREE", this.positionIndexRow, (ColumnOrdering[])null, (int[])null, var5, 3);
         this.positionIndex_cc = var3.openConglomerate(this.positionIndexConglomId, false, 4, 7, 5);
         this.positionIndexCreated = true;
      }

      this.position_sqllong.setValue(var1);
      this.positionIndexRow[0] = this.position_sqllong;
      this.positionIndexRow[1] = var2;
      this.positionIndex_cc.insert(this.positionIndexRow);
   }

   public CursorResultSet getResultSet() {
      this.state = 2;
      TransactionController var1 = this.activation.getTransactionController();
      return this.isUniqueStream ? new TemporaryRowHolderResultSet(var1, this.rowArray, this.resultDescription, this.isVirtualMemHeap, true, this.positionIndexConglomId, this) : new TemporaryRowHolderResultSet(var1, this.rowArray, this.resultDescription, this.isVirtualMemHeap, this);
   }

   public void truncate() throws StandardException {
      this.close();

      for(int var1 = 0; var1 < this.rowArray.length; ++var1) {
         this.rowArray[var1] = null;
      }

      this.numRowsIn = 0;
   }

   public long getTemporaryConglomId() {
      return this.CID;
   }

   public long getPositionIndexConglomId() {
      return this.positionIndexConglomId;
   }

   private Properties makeIndexProperties(DataValueDescriptor[] var1, long var2) throws StandardException {
      int var4 = var1.length;
      Properties var5 = new Properties();
      var5.put("allowDuplicates", "false");
      var5.put("nKeyFields", String.valueOf(var4));
      var5.put("nUniqueColumns", String.valueOf(var4 - 1));
      var5.put("rowLocationColumn", String.valueOf(var4 - 1));
      var5.put("baseConglomerateId", String.valueOf(var2));
      return var5;
   }

   public void setRowHolderTypeToUniqueStream() {
      this.isUniqueStream = true;
   }

   public void close() throws StandardException {
      if (this.scan != null) {
         this.scan.close();
         this.scan = null;
      }

      if (this.cc != null) {
         this.cc.close();
         this.cc = null;
      }

      if (this.uniqueIndex_cc != null) {
         this.uniqueIndex_cc.close();
         this.uniqueIndex_cc = null;
      }

      if (this.positionIndex_cc != null) {
         this.positionIndex_cc.close();
         this.positionIndex_cc = null;
      }

      TransactionController var1 = this.activation.getTransactionController();
      if (this.uniqueIndexCreated) {
         var1.dropConglomerate(this.uniqueIndexConglomId);
         this.uniqueIndexCreated = false;
      }

      if (this.positionIndexCreated) {
         var1.dropConglomerate(this.positionIndexConglomId);
         this.positionIndexCreated = false;
      }

      if (this.conglomCreated) {
         var1.dropConglomerate(this.CID);
         this.conglomCreated = false;
         this.CID = 0L;
      }

      this.state = 0;
      this.lastArraySlot = -1;
   }
}
