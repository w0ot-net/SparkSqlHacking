package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.impl.sql.execute.rts.RealAnyResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealCurrentOfStatistics;
import org.apache.derby.impl.sql.execute.rts.RealDeleteCascadeResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealDeleteResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealDeleteVTIResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealDistinctScalarAggregateStatistics;
import org.apache.derby.impl.sql.execute.rts.RealDistinctScanStatistics;
import org.apache.derby.impl.sql.execute.rts.RealGroupedAggregateStatistics;
import org.apache.derby.impl.sql.execute.rts.RealHashJoinStatistics;
import org.apache.derby.impl.sql.execute.rts.RealHashLeftOuterJoinStatistics;
import org.apache.derby.impl.sql.execute.rts.RealHashScanStatistics;
import org.apache.derby.impl.sql.execute.rts.RealHashTableStatistics;
import org.apache.derby.impl.sql.execute.rts.RealIndexRowToBaseRowStatistics;
import org.apache.derby.impl.sql.execute.rts.RealInsertResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealInsertVTIResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealLastIndexKeyScanStatistics;
import org.apache.derby.impl.sql.execute.rts.RealMaterializedResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealNestedLoopJoinStatistics;
import org.apache.derby.impl.sql.execute.rts.RealNestedLoopLeftOuterJoinStatistics;
import org.apache.derby.impl.sql.execute.rts.RealNormalizeResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealOnceResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealProjectRestrictStatistics;
import org.apache.derby.impl.sql.execute.rts.RealRowCountStatistics;
import org.apache.derby.impl.sql.execute.rts.RealRowResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealScalarAggregateStatistics;
import org.apache.derby.impl.sql.execute.rts.RealScrollInsensitiveResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealSetOpResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealSortStatistics;
import org.apache.derby.impl.sql.execute.rts.RealTableScanStatistics;
import org.apache.derby.impl.sql.execute.rts.RealUnionResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealUpdateResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RealVTIStatistics;
import org.apache.derby.impl.sql.execute.rts.RealWindowResultSetStatistics;
import org.apache.derby.impl.sql.execute.rts.RunTimeStatisticsImpl;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class RealResultSetStatisticsFactory implements ResultSetStatisticsFactory {
   public RunTimeStatistics getRunTimeStatistics(Activation var1, ResultSet var2, NoPutResultSet[] var3) throws StandardException {
      ExecPreparedStatement var4 = var1.getPreparedStatement();
      if (var4 == null) {
         return null;
      } else {
         ResultSetStatistics var5;
         if (var2 instanceof NoPutResultSet) {
            var5 = this.getResultSetStatistics((NoPutResultSet)var2);
         } else {
            var5 = this.getResultSetStatistics(var2);
         }

         int var6 = var3 == null ? 0 : var3.length;
         ResultSetStatistics[] var7 = new ResultSetStatistics[var6];
         boolean var8 = false;

         for(int var9 = 0; var9 < var6; ++var9) {
            if (var3[var9] != null && var3[var9].getPointOfAttachment() == -1) {
               var7[var9] = this.getResultSetStatistics(var3[var9]);
               var8 = true;
            }
         }

         if (!var8) {
            var7 = null;
         }

         return new RunTimeStatisticsImpl(var4.getSPSName(), var1.getCursorName(), var4.getSource(), var4.getCompileTimeInMillis(), var4.getParseTimeInMillis(), var4.getBindTimeInMillis(), var4.getOptimizeTimeInMillis(), var4.getGenerateTimeInMillis(), var2.getExecuteTime(), var4.getBeginCompileTimestamp(), var4.getEndCompileTimestamp(), var2.getBeginExecutionTimestamp(), var2.getEndExecutionTimestamp(), var7, var5);
      }
   }

   public ResultSetStatistics getResultSetStatistics(ResultSet var1) {
      if (!var1.returnsRows()) {
         return this.getNoRowsResultSetStatistics(var1);
      } else {
         return var1 instanceof NoPutResultSet ? this.getResultSetStatistics((NoPutResultSet)var1) : null;
      }
   }

   public ResultSetStatistics getNoRowsResultSetStatistics(ResultSet var1) {
      Object var2 = null;
      if (var1 instanceof InsertResultSet var3) {
         var2 = new RealInsertResultSetStatistics((int)var3.rowCount, var3.constants.deferred, var3.constants.irgs.length, var3.userSpecifiedBulkInsert, var3.bulkInsertPerformed, var3.constants.lockMode == 7, var3.getExecuteTime(), this.getResultSetStatistics(var3.savedSource));
         var3.savedSource = null;
      } else if (var1 instanceof InsertVTIResultSet var8) {
         var2 = new RealInsertVTIResultSetStatistics((int)var8.rowCount, var8.constants.deferred, var8.getExecuteTime(), this.getResultSetStatistics(var8.savedSource));
         var8.savedSource = null;
      } else if (var1 instanceof UpdateResultSet var9) {
         var2 = new RealUpdateResultSetStatistics((int)var9.rowCount, var9.constants.deferred, var9.constants.irgs.length, var9.constants.lockMode == 7, var9.getExecuteTime(), this.getResultSetStatistics(var9.savedSource));
         var9.savedSource = null;
      } else if (var1 instanceof DeleteCascadeResultSet var10) {
         int var4 = var10.dependentResultSets == null ? 0 : var10.dependentResultSets.length;
         ResultSetStatistics[] var5 = new ResultSetStatistics[var4];
         boolean var6 = false;

         for(int var7 = 0; var7 < var4; ++var7) {
            if (var10.dependentResultSets[var7] != null) {
               var5[var7] = this.getResultSetStatistics(var10.dependentResultSets[var7]);
               var6 = true;
            }
         }

         if (!var6) {
            var5 = null;
         }

         var2 = new RealDeleteCascadeResultSetStatistics((int)var10.rowCount, var10.constants.deferred, var10.constants.irgs.length, var10.constants.lockMode == 7, var10.getExecuteTime(), this.getResultSetStatistics(var10.savedSource), var5);
         var10.savedSource = null;
      } else if (var1 instanceof DeleteResultSet var11) {
         var2 = new RealDeleteResultSetStatistics((int)var11.rowCount, var11.constants.deferred, var11.constants.irgs.length, var11.constants.lockMode == 7, var11.getExecuteTime(), this.getResultSetStatistics(var11.savedSource));
         var11.savedSource = null;
      } else if (var1 instanceof DeleteVTIResultSet var12) {
         var2 = new RealDeleteVTIResultSetStatistics((int)var12.rowCount, var12.getExecuteTime(), this.getResultSetStatistics(var12.savedSource));
         var12.savedSource = null;
      }

      return (ResultSetStatistics)var2;
   }

   public ResultSetStatistics getResultSetStatistics(NoPutResultSet var1) {
      if (var1 instanceof ProjectRestrictResultSet var34) {
         int var39 = var34.subqueryTrackingArray == null ? 0 : var34.subqueryTrackingArray.length;
         ResultSetStatistics[] var45 = new ResultSetStatistics[var39];
         boolean var50 = false;

         for(int var54 = 0; var54 < var39; ++var54) {
            if (var34.subqueryTrackingArray[var54] != null && var34.subqueryTrackingArray[var54].getPointOfAttachment() == var34.resultSetNumber) {
               var45[var54] = this.getResultSetStatistics(var34.subqueryTrackingArray[var54]);
               var50 = true;
            }
         }

         if (!var50) {
            var45 = null;
         }

         return new RealProjectRestrictStatistics(var34.numOpens, var34.rowsSeen, var34.rowsFiltered, var34.constructorTime, var34.openTime, var34.nextTime, var34.closeTime, var34.resultSetNumber, var34.restrictionTime, var34.projectionTime, var45, var34.restriction != null, var34.doesProjection, var34.optimizerEstimatedRowCount, var34.optimizerEstimatedCost, this.getResultSetStatistics(var34.source));
      } else if (var1 instanceof RowCountResultSet var33) {
         return new RealRowCountStatistics(var33.numOpens, var33.rowsSeen, var33.rowsFiltered, var33.constructorTime, var33.openTime, var33.nextTime, var33.closeTime, var33.resultSetNumber, var33.optimizerEstimatedRowCount, var33.optimizerEstimatedCost, this.getResultSetStatistics(var33.source));
      } else if (var1 instanceof SortResultSet var32) {
         return new RealSortStatistics(var32.numOpens, var32.rowsSeen, var32.rowsFiltered, var32.constructorTime, var32.openTime, var32.nextTime, var32.closeTime, var32.resultSetNumber, var32.rowsInput, var32.rowsReturned, var32.distinct, var32.isInSortedOrder, var32.sortProperties, var32.optimizerEstimatedRowCount, var32.optimizerEstimatedCost, this.getResultSetStatistics(var32.source));
      } else if (var1 instanceof DistinctScalarAggregateResultSet var31) {
         return new RealDistinctScalarAggregateStatistics(var31.numOpens, var31.rowsSeen, var31.rowsFiltered, var31.constructorTime, var31.openTime, var31.nextTime, var31.closeTime, var31.resultSetNumber, var31.rowsInput, var31.optimizerEstimatedRowCount, var31.optimizerEstimatedCost, this.getResultSetStatistics(var31.source));
      } else if (var1 instanceof ScalarAggregateResultSet var30) {
         return new RealScalarAggregateStatistics(var30.numOpens, var30.rowsSeen, var30.rowsFiltered, var30.constructorTime, var30.openTime, var30.nextTime, var30.closeTime, var30.resultSetNumber, var30.singleInputRow, var30.rowsInput, var30.optimizerEstimatedRowCount, var30.optimizerEstimatedCost, this.getResultSetStatistics(var30.source));
      } else if (var1 instanceof GroupedAggregateResultSet var29) {
         return new RealGroupedAggregateStatistics(var29.numOpens, var29.rowsSeen, var29.rowsFiltered, var29.constructorTime, var29.openTime, var29.nextTime, var29.closeTime, var29.resultSetNumber, var29.rowsInput, var29.hasDistinctAggregate, var29.isInSortedOrder, var29.sortProperties, var29.optimizerEstimatedRowCount, var29.optimizerEstimatedCost, this.getResultSetStatistics(var29.source));
      } else if (var1 instanceof TableScanResultSet) {
         boolean var28 = false;
         TableScanResultSet var38 = (TableScanResultSet)var1;
         String var44 = null;
         String var49 = null;
         String var53 = null;
         Object var58 = null;
         String var60 = null;
         switch (var38.isolationLevel) {
            case 1:
               var53 = MessageService.getTextMessage("42Z9A", new Object[0]);
               break;
            case 3:
               var28 = true;
            case 2:
               var53 = MessageService.getTextMessage("42Z81.U", new Object[0]);
               break;
            case 4:
               var53 = MessageService.getTextMessage("42Z92", new Object[0]);
               break;
            case 5:
               var53 = MessageService.getTextMessage("42Z80.U", new Object[0]);
         }

         String var59;
         if (var38.forUpdate) {
            var59 = MessageService.getTextMessage("42Z82.U", new Object[0]);
         } else if (var28) {
            var59 = MessageService.getTextMessage("42Z83.U", new Object[0]);
         } else {
            var59 = MessageService.getTextMessage("42Z84.U", new Object[0]);
         }

         switch (var38.lockMode) {
            case 6 -> var60 = var59 + " " + MessageService.getTextMessage("42Z86.U", new Object[0]);
            case 7 -> var60 = var59 + " " + MessageService.getTextMessage("42Z85.U", new Object[0]);
         }

         if (var38.indexName != null) {
            var44 = var38.startPositionString;
            if (var44 == null) {
               var44 = var38.printStartPosition();
            }

            var49 = var38.stopPositionString;
            if (var49 == null) {
               var49 = var38.printStopPosition();
            }
         }

         return new RealTableScanStatistics(var38.numOpens, var38.rowsSeen, var38.rowsFiltered, var38.constructorTime, var38.openTime, var38.nextTime, var38.closeTime, var38.resultSetNumber, var38.tableName, var38.userSuppliedOptimizerOverrides, var38.indexName, var38.isConstraint, TableScanResultSet.printQualifiers(var38.qualifiers), var38.getScanProperties(), var44, var49, var53, var60, var38.rowsPerRead, var38.coarserLock, var38.optimizerEstimatedRowCount, var38.optimizerEstimatedCost);
      } else if (var1 instanceof LastIndexKeyResultSet) {
         LastIndexKeyResultSet var27 = (LastIndexKeyResultSet)var1;
         String var37 = null;
         String var43 = null;
         switch (var27.isolationLevel) {
            case 1:
               var37 = MessageService.getTextMessage("42Z9A", new Object[0]);
               break;
            case 2:
            case 3:
               var37 = MessageService.getTextMessage("42Z81.U", new Object[0]);
               break;
            case 4:
               var37 = MessageService.getTextMessage("42Z92", new Object[0]);
               break;
            case 5:
               var37 = MessageService.getTextMessage("42Z80.U", new Object[0]);
         }

         switch (var27.lockMode) {
            case 6 -> var43 = MessageService.getTextMessage("42Z88.U", new Object[0]);
            case 7 -> var43 = MessageService.getTextMessage("42Z87.U", new Object[0]);
         }

         return new RealLastIndexKeyScanStatistics(var27.numOpens, var27.constructorTime, var27.openTime, var27.nextTime, var27.closeTime, var27.resultSetNumber, var27.tableName, var27.indexName, var37, var43, var27.optimizerEstimatedRowCount, var27.optimizerEstimatedCost);
      } else if (var1 instanceof HashLeftOuterJoinResultSet) {
         HashLeftOuterJoinResultSet var26 = (HashLeftOuterJoinResultSet)var1;
         return new RealHashLeftOuterJoinStatistics(var26.numOpens, var26.rowsSeen, var26.rowsFiltered, var26.constructorTime, var26.openTime, var26.nextTime, var26.closeTime, var26.resultSetNumber, var26.rowsSeenLeft, var26.rowsSeenRight, var26.rowsReturned, var26.restrictionTime, var26.optimizerEstimatedRowCount, var26.optimizerEstimatedCost, var26.userSuppliedOptimizerOverrides, this.getResultSetStatistics(var26.leftResultSet), this.getResultSetStatistics(var26.rightResultSet), var26.emptyRightRowsReturned);
      } else if (var1 instanceof NestedLoopLeftOuterJoinResultSet) {
         NestedLoopLeftOuterJoinResultSet var25 = (NestedLoopLeftOuterJoinResultSet)var1;
         return new RealNestedLoopLeftOuterJoinStatistics(var25.numOpens, var25.rowsSeen, var25.rowsFiltered, var25.constructorTime, var25.openTime, var25.nextTime, var25.closeTime, var25.resultSetNumber, var25.rowsSeenLeft, var25.rowsSeenRight, var25.rowsReturned, var25.restrictionTime, var25.optimizerEstimatedRowCount, var25.optimizerEstimatedCost, var25.userSuppliedOptimizerOverrides, this.getResultSetStatistics(var25.leftResultSet), this.getResultSetStatistics(var25.rightResultSet), var25.emptyRightRowsReturned);
      } else if (var1 instanceof HashJoinResultSet) {
         HashJoinResultSet var24 = (HashJoinResultSet)var1;
         return new RealHashJoinStatistics(var24.numOpens, var24.rowsSeen, var24.rowsFiltered, var24.constructorTime, var24.openTime, var24.nextTime, var24.closeTime, var24.resultSetNumber, var24.rowsSeenLeft, var24.rowsSeenRight, var24.rowsReturned, var24.restrictionTime, var24.oneRowRightSide, var24.optimizerEstimatedRowCount, var24.optimizerEstimatedCost, var24.userSuppliedOptimizerOverrides, this.getResultSetStatistics(var24.leftResultSet), this.getResultSetStatistics(var24.rightResultSet));
      } else if (var1 instanceof NestedLoopJoinResultSet) {
         NestedLoopJoinResultSet var23 = (NestedLoopJoinResultSet)var1;
         return new RealNestedLoopJoinStatistics(var23.numOpens, var23.rowsSeen, var23.rowsFiltered, var23.constructorTime, var23.openTime, var23.nextTime, var23.closeTime, var23.resultSetNumber, var23.rowsSeenLeft, var23.rowsSeenRight, var23.rowsReturned, var23.restrictionTime, var23.oneRowRightSide, var23.optimizerEstimatedRowCount, var23.optimizerEstimatedCost, var23.userSuppliedOptimizerOverrides, this.getResultSetStatistics(var23.leftResultSet), this.getResultSetStatistics(var23.rightResultSet));
      } else if (var1 instanceof IndexRowToBaseRowResultSet) {
         IndexRowToBaseRowResultSet var22 = (IndexRowToBaseRowResultSet)var1;
         return new RealIndexRowToBaseRowStatistics(var22.numOpens, var22.rowsSeen, var22.rowsFiltered, var22.constructorTime, var22.openTime, var22.nextTime, var22.closeTime, var22.resultSetNumber, var22.indexName, var22.accessedHeapCols, var22.optimizerEstimatedRowCount, var22.optimizerEstimatedCost, this.getResultSetStatistics(var22.source));
      } else if (var1 instanceof RowResultSet) {
         RowResultSet var21 = (RowResultSet)var1;
         return new RealRowResultSetStatistics(var21.numOpens, var21.rowsSeen, var21.rowsFiltered, var21.constructorTime, var21.openTime, var21.nextTime, var21.closeTime, var21.resultSetNumber, var21.rowsReturned, var21.optimizerEstimatedRowCount, var21.optimizerEstimatedCost);
      } else if (var1 instanceof WindowResultSet) {
         WindowResultSet var20 = (WindowResultSet)var1;
         return new RealWindowResultSetStatistics(var20.numOpens, var20.rowsSeen, var20.rowsFiltered, var20.constructorTime, var20.openTime, var20.nextTime, var20.closeTime, var20.resultSetNumber, var20.optimizerEstimatedRowCount, var20.optimizerEstimatedCost, this.getResultSetStatistics(var20.source));
      } else if (var1 instanceof SetOpResultSet) {
         SetOpResultSet var19 = (SetOpResultSet)var1;
         return new RealSetOpResultSetStatistics(var19.getOpType(), var19.numOpens, var19.rowsSeen, var19.rowsFiltered, var19.constructorTime, var19.openTime, var19.nextTime, var19.closeTime, var19.getResultSetNumber(), var19.getRowsSeenLeft(), var19.getRowsSeenRight(), var19.getRowsReturned(), var19.optimizerEstimatedRowCount, var19.optimizerEstimatedCost, this.getResultSetStatistics(var19.getLeftSourceInput()), this.getResultSetStatistics(var19.getRightSourceInput()));
      } else if (var1 instanceof UnionResultSet) {
         UnionResultSet var18 = (UnionResultSet)var1;
         return new RealUnionResultSetStatistics(var18.numOpens, var18.rowsSeen, var18.rowsFiltered, var18.constructorTime, var18.openTime, var18.nextTime, var18.closeTime, var18.resultSetNumber, var18.rowsSeenLeft, var18.rowsSeenRight, var18.rowsReturned, var18.optimizerEstimatedRowCount, var18.optimizerEstimatedCost, this.getResultSetStatistics(var18.source1), this.getResultSetStatistics(var18.source2));
      } else if (var1 instanceof AnyResultSet) {
         AnyResultSet var17 = (AnyResultSet)var1;
         return new RealAnyResultSetStatistics(var17.numOpens, var17.rowsSeen, var17.rowsFiltered, var17.constructorTime, var17.openTime, var17.nextTime, var17.closeTime, var17.resultSetNumber, var17.subqueryNumber, var17.pointOfAttachment, var17.optimizerEstimatedRowCount, var17.optimizerEstimatedCost, this.getResultSetStatistics(var17.source));
      } else if (var1 instanceof OnceResultSet) {
         OnceResultSet var16 = (OnceResultSet)var1;
         return new RealOnceResultSetStatistics(var16.numOpens, var16.rowsSeen, var16.rowsFiltered, var16.constructorTime, var16.openTime, var16.nextTime, var16.closeTime, var16.resultSetNumber, var16.subqueryNumber, var16.pointOfAttachment, var16.optimizerEstimatedRowCount, var16.optimizerEstimatedCost, this.getResultSetStatistics(var16.source));
      } else if (var1 instanceof NormalizeResultSet) {
         NormalizeResultSet var15 = (NormalizeResultSet)var1;
         return new RealNormalizeResultSetStatistics(var15.numOpens, var15.rowsSeen, var15.rowsFiltered, var15.constructorTime, var15.openTime, var15.nextTime, var15.closeTime, var15.resultSetNumber, var15.optimizerEstimatedRowCount, var15.optimizerEstimatedCost, this.getResultSetStatistics(var15.source));
      } else if (var1 instanceof MaterializedResultSet) {
         MaterializedResultSet var14 = (MaterializedResultSet)var1;
         return new RealMaterializedResultSetStatistics(var14.numOpens, var14.rowsSeen, var14.rowsFiltered, var14.constructorTime, var14.openTime, var14.nextTime, var14.closeTime, var14.createTCTime, var14.fetchTCTime, var14.resultSetNumber, var14.optimizerEstimatedRowCount, var14.optimizerEstimatedCost, this.getResultSetStatistics(var14.source));
      } else if (var1 instanceof ScrollInsensitiveResultSet) {
         ScrollInsensitiveResultSet var13 = (ScrollInsensitiveResultSet)var1;
         return new RealScrollInsensitiveResultSetStatistics(var13.numOpens, var13.rowsSeen, var13.rowsFiltered, var13.constructorTime, var13.openTime, var13.nextTime, var13.closeTime, var13.numFromHashTable, var13.numToHashTable, var13.resultSetNumber, var13.optimizerEstimatedRowCount, var13.optimizerEstimatedCost, this.getResultSetStatistics(var13.source));
      } else if (var1 instanceof CurrentOfResultSet) {
         CurrentOfResultSet var12 = (CurrentOfResultSet)var1;
         return new RealCurrentOfStatistics(var12.numOpens, var12.rowsSeen, var12.rowsFiltered, var12.constructorTime, var12.openTime, var12.nextTime, var12.closeTime, var12.resultSetNumber);
      } else if (var1 instanceof HashScanResultSet) {
         boolean var11 = false;
         HashScanResultSet var36 = (HashScanResultSet)var1;
         String var42 = null;
         String var48 = null;
         String var52 = null;
         Object var56 = null;
         switch (var36.isolationLevel) {
            case 3:
               var11 = true;
            case 2:
               var52 = MessageService.getTextMessage("42Z81.U", new Object[0]);
               break;
            case 4:
               var52 = MessageService.getTextMessage("42Z92", new Object[0]);
               break;
            case 5:
               var52 = MessageService.getTextMessage("42Z80.U", new Object[0]);
         }

         String var57;
         if (var36.forUpdate) {
            var57 = MessageService.getTextMessage("42Z82.U", new Object[0]);
         } else if (var11) {
            var57 = MessageService.getTextMessage("42Z83.U", new Object[0]);
         } else {
            var57 = MessageService.getTextMessage("42Z84.U", new Object[0]);
         }

         switch (var36.lockMode) {
            case 6 -> var57 = var57 + " " + MessageService.getTextMessage("42Z86.U", new Object[0]);
            case 7 -> var57 = var57 + " " + MessageService.getTextMessage("42Z85.U", new Object[0]);
         }

         if (var36.indexName != null) {
            var42 = var36.startPositionString;
            if (var42 == null) {
               var42 = var36.printStartPosition();
            }

            var48 = var36.stopPositionString;
            if (var48 == null) {
               var48 = var36.printStopPosition();
            }
         }

         return (ResultSetStatistics)(var1 instanceof DistinctScanResultSet ? new RealDistinctScanStatistics(var36.numOpens, var36.rowsSeen, var36.rowsFiltered, var36.constructorTime, var36.openTime, var36.nextTime, var36.closeTime, var36.resultSetNumber, var36.tableName, var36.indexName, var36.isConstraint, var36.hashtableSize, var36.keyColumns, HashScanResultSet.printQualifiers(var36.scanQualifiers), HashScanResultSet.printQualifiers(var36.nextQualifiers), var36.getScanProperties(), var42, var48, var52, var57, var36.optimizerEstimatedRowCount, var36.optimizerEstimatedCost) : new RealHashScanStatistics(var36.numOpens, var36.rowsSeen, var36.rowsFiltered, var36.constructorTime, var36.openTime, var36.nextTime, var36.closeTime, var36.resultSetNumber, var36.tableName, var36.indexName, var36.isConstraint, var36.hashtableSize, var36.keyColumns, HashScanResultSet.printQualifiers(var36.scanQualifiers), HashScanResultSet.printQualifiers(var36.nextQualifiers), var36.getScanProperties(), var42, var48, var52, var57, var36.optimizerEstimatedRowCount, var36.optimizerEstimatedCost));
      } else if (var1 instanceof HashTableResultSet) {
         HashTableResultSet var10 = (HashTableResultSet)var1;
         int var35 = var10.subqueryTrackingArray == null ? 0 : var10.subqueryTrackingArray.length;
         ResultSetStatistics[] var41 = new ResultSetStatistics[var35];
         boolean var47 = false;

         for(int var51 = 0; var51 < var35; ++var51) {
            if (var10.subqueryTrackingArray[var51] != null && var10.subqueryTrackingArray[var51].getPointOfAttachment() == var10.resultSetNumber) {
               var41[var51] = this.getResultSetStatistics(var10.subqueryTrackingArray[var51]);
               var47 = true;
            }
         }

         if (!var47) {
            var41 = null;
         }

         return new RealHashTableStatistics(var10.numOpens, var10.rowsSeen, var10.rowsFiltered, var10.constructorTime, var10.openTime, var10.nextTime, var10.closeTime, var10.resultSetNumber, var10.hashtableSize, var10.keyColumns, HashScanResultSet.printQualifiers(var10.nextQualifiers), var10.scanProperties, var10.optimizerEstimatedRowCount, var10.optimizerEstimatedCost, var41, this.getResultSetStatistics(var10.source));
      } else if (var1 instanceof VTIResultSet) {
         VTIResultSet var9 = (VTIResultSet)var1;
         return new RealVTIStatistics(var9.numOpens, var9.rowsSeen, var9.rowsFiltered, var9.constructorTime, var9.openTime, var9.nextTime, var9.closeTime, var9.resultSetNumber, var9.javaClassName, var9.optimizerEstimatedRowCount, var9.optimizerEstimatedCost);
      } else if (var1 instanceof DependentResultSet) {
         boolean var2 = false;
         DependentResultSet var3 = (DependentResultSet)var1;
         Object var4 = null;
         Object var5 = null;
         String var6 = null;
         Object var7 = null;
         String var8 = null;
         switch (var3.isolationLevel) {
            case 1:
               var6 = MessageService.getTextMessage("42Z9A", new Object[0]);
               break;
            case 3:
               var2 = true;
            case 2:
               var6 = MessageService.getTextMessage("42Z81.U", new Object[0]);
               break;
            case 4:
               var6 = MessageService.getTextMessage("42Z92", new Object[0]);
               break;
            case 5:
               var6 = MessageService.getTextMessage("42Z80.U", new Object[0]);
         }

         String var55;
         if (var3.forUpdate) {
            var55 = MessageService.getTextMessage("42Z82.U", new Object[0]);
         } else if (var2) {
            var55 = MessageService.getTextMessage("42Z83.U", new Object[0]);
         } else {
            var55 = MessageService.getTextMessage("42Z84.U", new Object[0]);
         }

         switch (var3.lockMode) {
            case 6 -> var8 = var55 + " " + MessageService.getTextMessage("42Z86.U", new Object[0]);
            case 7 -> var8 = var55 + " " + MessageService.getTextMessage("42Z85.U", new Object[0]);
         }

         String var40 = var3.startPositionString;
         if (var40 == null) {
            var40 = var3.printStartPosition();
         }

         String var46 = var3.stopPositionString;
         if (var46 == null) {
            var46 = var3.printStopPosition();
         }

         return new RealTableScanStatistics(var3.numOpens, var3.rowsSeen, var3.rowsFiltered, var3.constructorTime, var3.openTime, var3.nextTime, var3.closeTime, var3.resultSetNumber, var3.tableName, (String)null, var3.indexName, var3.isConstraint, var3.printQualifiers(), var3.getScanProperties(), var40, var46, var6, var8, var3.rowsPerRead, var3.coarserLock, var3.optimizerEstimatedRowCount, var3.optimizerEstimatedCost);
      } else {
         return null;
      }
   }
}
