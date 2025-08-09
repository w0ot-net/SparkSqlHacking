package org.apache.derby.impl.sql.execute;

import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableIntHolder;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.KeyHasher;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class HashScanResultSet extends ScanResultSet implements CursorResultSet {
   private boolean hashtableBuilt;
   private ExecIndexRow startPosition;
   private ExecIndexRow stopPosition;
   protected ExecRow compactRow;
   protected boolean firstNext = true;
   private int numFetchedOnNext;
   private int entryVectorSize;
   private List entryVector;
   private long conglomId;
   protected StaticCompiledOpenConglomInfo scoci;
   private GeneratedMethod startKeyGetter;
   private int startSearchOperator;
   private GeneratedMethod stopKeyGetter;
   private int stopSearchOperator;
   public Qualifier[][] scanQualifiers;
   public Qualifier[][] nextQualifiers;
   private int initialCapacity;
   private float loadFactor;
   private int maxCapacity;
   public String userSuppliedOptimizerOverrides;
   public boolean forUpdate;
   private boolean runTimeStatisticsOn;
   public int[] keyColumns;
   private boolean sameStartStopPosition;
   private boolean skipNullKeyColumns;
   private boolean keepAfterCommit;
   protected BackingStoreHashtable hashtable;
   protected boolean eliminateDuplicates;
   public Properties scanProperties;
   public String startPositionString;
   public String stopPositionString;
   public int hashtableSize;
   public boolean isConstraint;
   public static final int DEFAULT_INITIAL_CAPACITY = -1;
   public static final float DEFAULT_LOADFACTOR = -1.0F;
   public static final int DEFAULT_MAX_CAPACITY = -1;

   HashScanResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, Qualifier[][] var13, int var14, float var15, int var16, int var17, String var18, String var19, String var20, boolean var21, boolean var22, int var23, int var24, boolean var25, int var26, boolean var27, double var28, double var30) throws StandardException {
      super(var4, var6, var5, var24, var25, var26, var23, var28, var30);
      this.scoci = var3;
      this.conglomId = var1;
      this.startKeyGetter = var7;
      this.startSearchOperator = var8;
      this.stopKeyGetter = var9;
      this.stopSearchOperator = var10;
      this.sameStartStopPosition = var11;
      this.scanQualifiers = var12;
      this.nextQualifiers = var13;
      this.initialCapacity = var14;
      this.loadFactor = var15;
      this.maxCapacity = var16;
      this.tableName = var18;
      this.userSuppliedOptimizerOverrides = var19;
      this.indexName = var20;
      this.isConstraint = var21;
      this.forUpdate = var22;
      this.skipNullKeyColumns = var27;
      this.keepAfterCommit = var4.getResultSetHoldability();
      FormatableArrayHolder var32 = (FormatableArrayHolder)var4.getPreparedStatement().getSavedObject(var17);
      FormatableIntHolder[] var33 = (FormatableIntHolder[])var32.getArray(FormatableIntHolder[].class);
      this.keyColumns = new int[var33.length];

      for(int var34 = 0; var34 < var33.length; ++var34) {
         this.keyColumns[var34] = var33[var34].getInt();
      }

      this.runTimeStatisticsOn = this.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.setRowLocationsState();
      this.compactRow = this.getCompactRow(this.candidate, this.accessedCols, false);
      this.recordConstructorTime();
   }

   boolean canGetInstantaneousLocks() {
      return true;
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      TransactionController var1 = this.activation.getTransactionController();
      this.initIsolationLevel();
      if (this.startKeyGetter != null) {
         this.startPosition = (ExecIndexRow)this.startKeyGetter.invoke(this.activation);
         if (this.sameStartStopPosition) {
            this.stopPosition = this.startPosition;
         }
      }

      if (this.stopKeyGetter != null) {
         this.stopPosition = (ExecIndexRow)this.stopKeyGetter.invoke(this.activation);
      }

      if (!this.skipScan(this.startPosition, this.stopPosition) && !this.hashtableBuilt) {
         DataValueDescriptor[] var2 = this.startPosition == null ? null : this.startPosition.getRowArray();
         DataValueDescriptor[] var3 = this.stopPosition == null ? null : this.stopPosition.getRowArray();
         this.hashtable = var1.createBackingStoreHashtableFromScan(this.conglomId, this.forUpdate ? 4 : 0, this.lockMode, this.isolationLevel, this.accessedCols, var2, this.startSearchOperator, this.scanQualifiers, var3, this.stopSearchOperator, -1L, this.keyColumns, this.eliminateDuplicates, -1L, (long)this.maxCapacity, this.initialCapacity, this.loadFactor, this.runTimeStatisticsOn, this.skipNullKeyColumns, this.keepAfterCommit, this.fetchRowLocations);
         if (this.runTimeStatisticsOn) {
            this.hashtableSize = this.hashtable.size();
            if (this.scanProperties == null) {
               this.scanProperties = new Properties();
            }

            try {
               if (this.hashtable != null) {
                  this.hashtable.getAllRuntimeStats(this.scanProperties);
               }
            } catch (StandardException var5) {
            }
         }

         this.hashtableBuilt = true;
         this.activation.informOfRowCount(this, (long)this.hashtableSize);
      }

      this.isOpen = true;
      this.resetProbeVariables();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.resetProbeVariables();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private void resetProbeVariables() throws StandardException {
      this.firstNext = true;
      this.numFetchedOnNext = 0;
      this.entryVector = null;
      this.entryVectorSize = 0;
      if (this.nextQualifiers != null) {
         this.clearOrderableCache(this.nextQualifiers);
      }

   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         DataValueDescriptor[] var2 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen && this.hashtableBuilt) {
            do {
               if (!this.firstNext) {
                  if (this.numFetchedOnNext < this.entryVectorSize) {
                     var2 = this.unpackHashValue(this.entryVector.get(this.numFetchedOnNext));
                  }
               } else {
                  this.firstNext = false;
                  Object var3;
                  if (this.keyColumns.length == 1) {
                     var3 = this.hashtable.get(this.nextQualifiers[0][0].getOrderable());
                  } else {
                     KeyHasher var4 = new KeyHasher(this.keyColumns.length);

                     for(int var5 = 0; var5 < this.keyColumns.length; ++var5) {
                        DataValueDescriptor var6 = this.nextQualifiers[0][var5].getOrderable();
                        if (var6 == null) {
                           var4 = null;
                           break;
                        }

                        var4.setObject(var5, this.nextQualifiers[0][var5].getOrderable());
                     }

                     var3 = var4 == null ? null : this.hashtable.get(var4);
                  }

                  if (var3 instanceof List) {
                     this.entryVector = (List)var3;
                     this.entryVectorSize = this.entryVector.size();
                     var2 = this.unpackHashValue(this.entryVector.get(0));
                  } else {
                     this.entryVector = null;
                     this.entryVectorSize = 0;
                     var2 = this.unpackHashValue(var3);
                  }
               }

               if (var2 != null) {
                  if (org.apache.derby.iapi.store.access.RowUtil.qualifyRow(var2, this.nextQualifiers)) {
                     this.setCompatRow(this.compactRow, var2);
                     ++this.rowsSeen;
                     var1 = this.compactRow;
                  } else {
                     var1 = null;
                  }

                  ++this.numFetchedOnNext;
               } else {
                  var1 = null;
               }
            } while(var1 == null && this.numFetchedOnNext < this.entryVectorSize);
         }

         this.setCurrentRow(var1);
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var1;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         if (this.hashtableBuilt) {
            this.scanProperties = this.getScanProperties();
            if (this.runTimeStatisticsOn) {
               this.startPositionString = this.printStartPosition();
               this.stopPositionString = this.printStopPosition();
            }

            this.hashtable.close();
            this.hashtable = null;
            this.hashtableBuilt = false;
         }

         this.startPosition = null;
         this.stopPosition = null;
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 : var2;
   }

   public boolean requiresRelocking() {
      return this.isolationLevel == 2 || this.isolationLevel == 3 || this.isolationLevel == 1;
   }

   public RowLocation getRowLocation() throws StandardException {
      if (!this.isOpen) {
         return null;
      } else {
         return !this.hashtableBuilt ? null : (RowLocation)this.currentRow.getColumn(this.currentRow.nColumns());
      }
   }

   public ExecRow getCurrentRow() throws StandardException {
      return null;
   }

   public String printStartPosition() {
      return this.printPosition(this.startSearchOperator, this.startKeyGetter, this.startPosition);
   }

   public String printStopPosition() {
      return this.sameStartStopPosition ? this.printPosition(this.stopSearchOperator, this.startKeyGetter, this.startPosition) : this.printPosition(this.stopSearchOperator, this.stopKeyGetter, this.stopPosition);
   }

   private String printPosition(int var1, GeneratedMethod var2, ExecIndexRow var3) {
      String var4 = "";
      String var5 = "";
      if (var2 == null) {
         return "\t" + MessageService.getTextMessage("42Z37.U", new Object[0]) + "\n";
      } else {
         Object var6 = null;

         try {
            var13 = (ExecIndexRow)var2.invoke(this.activation);
         } catch (StandardException var10) {
            if (var3 == null) {
               return "\t" + MessageService.getTextMessage("42Z38.U", new Object[0]);
            }

            return "\t" + MessageService.getTextMessage("42Z39.U", new Object[0]) + "\n";
         }

         if (var13 == null) {
            return "\t" + MessageService.getTextMessage("42Z37.U", new Object[0]) + "\n";
         } else {
            Object var7 = null;
            String var14;
            switch (var1) {
               case -1 -> var14 = ">";
               case 1 -> var14 = ">=";
               default -> var14 = "unknown value (" + var1 + ")";
            }

            var5 = var5 + "\t" + MessageService.getTextMessage("42Z40.U", new Object[]{var14, String.valueOf(var13.nColumns())}) + "\n";
            var5 = var5 + "\t" + MessageService.getTextMessage("42Z41.U", new Object[0]) + "\n";
            boolean var8 = false;

            for(int var9 = 0; var9 < var13.nColumns(); ++var9) {
               if (var13.areNullsOrdered(var9)) {
                  var5 = var5 + var9 + " ";
                  var8 = true;
               }

               if (var8 && var9 == var13.nColumns() - 1) {
                  var5 = var5 + "\n";
               }
            }

            return var5;
         }
      }
   }

   public Properties getScanProperties() {
      return this.scanProperties;
   }

   public boolean isForUpdate() {
      return this.forUpdate;
   }
}
