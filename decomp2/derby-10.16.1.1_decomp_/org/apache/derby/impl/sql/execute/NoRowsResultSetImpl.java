package org.apache.derby.impl.sql.execute;

import java.sql.SQLWarning;
import java.sql.Timestamp;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.w3c.dom.Element;

abstract class NoRowsResultSetImpl implements ResultSet {
   final Activation activation;
   private NoPutResultSet[] subqueryTrackingArray;
   private final boolean statisticsTimingOn;
   private boolean isOpen;
   final LanguageConnectionContext lcc;
   protected long beginTime;
   protected long endTime;
   protected long beginExecutionTime;
   protected long endExecutionTime;
   private int firstColumn = -1;
   private int[] generatedColumnPositions;
   private DataValueDescriptor[] normalizedGeneratedValues;

   NoRowsResultSetImpl(Activation var1) {
      this.activation = var1;
      this.lcc = var1.getLanguageConnectionContext();
      this.statisticsTimingOn = this.lcc.getStatisticsTiming();
      this.beginTime = this.getCurrentTimeMillis();
      this.beginExecutionTime = this.beginTime;
   }

   void setup() throws StandardException {
      this.isOpen = true;
      StatementContext var1 = this.lcc.getStatementContext();
      var1.setTopResultSet(this, this.subqueryTrackingArray);
      if (this.subqueryTrackingArray == null) {
         this.subqueryTrackingArray = var1.getSubqueryTrackingArray();
      }

   }

   public final boolean returnsRows() {
      return false;
   }

   public long modifiedRowCount() {
      return 0L;
   }

   public ResultDescription getResultDescription() {
      return (ResultDescription)null;
   }

   public final Activation getActivation() {
      return this.activation;
   }

   public final ExecRow getAbsoluteRow(int var1) throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"absolute"});
   }

   public final ExecRow getRelativeRow(int var1) throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"relative"});
   }

   public final ExecRow setBeforeFirstRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"beforeFirst"});
   }

   public final ExecRow getFirstRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"first"});
   }

   public final ExecRow getNextRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"next"});
   }

   public final ExecRow getPreviousRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"previous"});
   }

   public final ExecRow getLastRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"last"});
   }

   public final ExecRow setAfterLastRow() throws StandardException {
      throw StandardException.newException("XCL01.S", new Object[]{"afterLast"});
   }

   public final void clearCurrentRow() {
   }

   public final boolean checkRowPosition(int var1) {
      return false;
   }

   public final int getRowNumber() {
      return 0;
   }

   public void close(boolean var1) throws StandardException {
      if (this.isOpen) {
         if (this.lcc.getRunTimeStatisticsMode() && !this.doesCommit() && !this.activation.isClosed() && !this.lcc.getStatementContext().getStatementWasInvalidated()) {
            this.endExecutionTime = this.getCurrentTimeMillis();
            ResultSetStatisticsFactory var2 = this.lcc.getLanguageConnectionFactory().getExecutionFactory().getResultSetStatisticsFactory();
            RunTimeStatistics var3 = var2.getRunTimeStatistics(this.activation, this, this.subqueryTrackingArray);
            this.lcc.setRunTimeStatisticsObject(var3);
            XPLAINVisitor var4 = this.lcc.getLanguageConnectionFactory().getExecutionFactory().getXPLAINFactory().getXPLAINVisitor();
            var4.doXPLAIN(var3, this.activation);
         }

         int var5 = this.subqueryTrackingArray == null ? 0 : this.subqueryTrackingArray.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            if (this.subqueryTrackingArray[var6] != null && !this.subqueryTrackingArray[var6].isClosed()) {
               this.subqueryTrackingArray[var6].close();
            }
         }

         this.isOpen = false;
         if (this.activation.isSingleExecution() && !var1) {
            this.activation.close();
         }

      }
   }

   public boolean isClosed() {
      return !this.isOpen;
   }

   public void finish() throws StandardException {
   }

   public long getExecuteTime() {
      return this.endTime - this.beginTime;
   }

   public Timestamp getBeginExecutionTimestamp() {
      return this.beginExecutionTime == 0L ? null : new Timestamp(this.beginExecutionTime);
   }

   public Timestamp getEndExecutionTimestamp() {
      return this.endExecutionTime == 0L ? null : new Timestamp(this.endExecutionTime);
   }

   public String getQueryPlanText(int var1) {
      return MessageService.getTextMessage("42Z47.U", new Object[]{this.getClass().getName()});
   }

   public long getTimeSpent(int var1) {
      return 0L;
   }

   public final NoPutResultSet[] getSubqueryTrackingArray(int var1) {
      if (this.subqueryTrackingArray == null) {
         this.subqueryTrackingArray = new NoPutResultSet[var1];
      }

      return this.subqueryTrackingArray;
   }

   public ResultSet getAutoGeneratedKeysResultset() {
      return (ResultSet)null;
   }

   public String getCursorName() {
      return null;
   }

   protected final long getCurrentTimeMillis() {
      return this.statisticsTimingOn ? System.currentTimeMillis() : 0L;
   }

   public void evaluateGenerationClauses(GeneratedMethod var1, Activation var2, NoPutResultSet var3, ExecRow var4, boolean var5) throws StandardException {
      if (var1 != null) {
         ExecRow var6 = (ExecRow)var2.getCurrentRow(var3.resultSetNumber());

         try {
            var3.setCurrentRow(var4);
            var2.setCurrentRow(var4, var3.resultSetNumber());
            var1.invoke(var2);
            if (this.firstColumn < 0) {
               this.firstColumn = NormalizeResultSet.computeStartColumn(var5, var2.getResultDescription());
            }

            if (this.generatedColumnPositions == null) {
               this.setupGeneratedColumns(var2, (ValueRow)var4);
            }

            ResultDescription var7 = var2.getResultDescription();
            int var8 = this.generatedColumnPositions.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               int var10 = this.generatedColumnPositions[var9];
               DataValueDescriptor var11 = NormalizeResultSet.normalizeColumn(var7.getColumnDescriptor(var10).getType(), var4, var10, this.normalizedGeneratedValues[var9], var7);
               var4.setColumn(var10, var11);
            }
         } finally {
            if (var6 == null) {
               var3.clearCurrentRow();
            } else {
               var3.setCurrentRow(var6);
            }

         }
      }

   }

   private void setupGeneratedColumns(Activation var1, ValueRow var2) throws StandardException {
      ResultDescription var3 = var1.getResultDescription();
      int var4 = var3.getColumnCount();
      ExecRow var5 = var2.getNewNullRow();
      int var6 = 0;

      for(int var7 = 1; var7 <= var4; ++var7) {
         if (var7 >= this.firstColumn) {
            ResultColumnDescriptor var8 = var3.getColumnDescriptor(var7);
            if (var8.hasGenerationClause()) {
               ++var6;
            }
         }
      }

      this.generatedColumnPositions = new int[var6];
      this.normalizedGeneratedValues = new DataValueDescriptor[var6];
      int var10 = 0;

      for(int var11 = 1; var11 <= var4; ++var11) {
         if (var11 >= this.firstColumn) {
            ResultColumnDescriptor var9 = var3.getColumnDescriptor(var11);
            if (var9.hasGenerationClause()) {
               this.generatedColumnPositions[var10] = var11;
               this.normalizedGeneratedValues[var10] = var5.getColumn(var11);
               ++var10;
            }
         }
      }

   }

   public boolean doesCommit() {
      return false;
   }

   public void addWarning(SQLWarning var1) {
      this.getActivation().addWarning(var1);
   }

   public SQLWarning getWarnings() {
      return null;
   }

   public Element toXML(Element var1, String var2) throws Exception {
      return BasicNoPutResultSetImpl.childrenToXML(BasicNoPutResultSetImpl.toXML(var1, var2, this), this);
   }
}
