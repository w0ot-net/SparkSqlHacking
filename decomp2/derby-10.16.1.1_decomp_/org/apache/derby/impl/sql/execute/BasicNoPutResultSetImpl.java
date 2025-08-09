package org.apache.derby.impl.sql.execute;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.w3c.dom.Element;

abstract class BasicNoPutResultSetImpl implements NoPutResultSet {
   protected boolean isOpen;
   protected boolean finished;
   protected ExecRow currentRow;
   protected boolean isTopResultSet;
   private SQLWarning warnings;
   public int numOpens;
   public int rowsSeen;
   public int rowsFiltered;
   protected long startExecutionTime;
   protected long endExecutionTime;
   public long beginTime;
   public long constructorTime;
   public long openTime;
   public long nextTime;
   public long closeTime;
   public double optimizerEstimatedRowCount;
   public double optimizerEstimatedCost;
   private StatementContext statementContext;
   public NoPutResultSet[] subqueryTrackingArray;
   ExecRow compactRow;
   protected final Activation activation;
   private final boolean statisticsTimingOn;
   ResultDescription resultDescription;
   private transient TransactionController tc;
   private int[] baseColumnMap;

   BasicNoPutResultSetImpl(ResultDescription var1, Activation var2, double var3, double var5) {
      this.activation = var2;
      if (this.statisticsTimingOn = this.getLanguageConnectionContext().getStatisticsTiming()) {
         this.beginTime = this.startExecutionTime = this.getCurrentTimeMillis();
      }

      this.resultDescription = var1;
      this.optimizerEstimatedRowCount = var3;
      this.optimizerEstimatedCost = var5;
   }

   protected final void recordConstructorTime() {
      if (this.statisticsTimingOn) {
         this.constructorTime = this.getElapsedMillis(this.beginTime);
      }

   }

   public final Activation getActivation() {
      return this.activation;
   }

   protected final boolean isXplainOnlyMode() {
      LanguageConnectionContext var1 = this.getLanguageConnectionContext();
      return var1.getRunTimeStatisticsMode() && var1.getXplainOnlyMode();
   }

   public void reopenCore() throws StandardException {
      this.close();
      this.openCore();
   }

   public abstract ExecRow getNextRowCore() throws StandardException;

   public int getPointOfAttachment() {
      return -1;
   }

   public void markAsTopResultSet() {
      this.isTopResultSet = true;
   }

   public int getScanIsolationLevel() {
      return 0;
   }

   public double getEstimatedRowCount() {
      return this.optimizerEstimatedRowCount;
   }

   public boolean requiresRelocking() {
      return false;
   }

   public final void open() throws StandardException {
      this.finished = false;
      this.attachStatementContext();

      try {
         this.openCore();
      } catch (StandardException var2) {
         this.activation.checkStatementValidity();
         throw var2;
      }

      this.activation.checkStatementValidity();
   }

   public ExecRow getAbsoluteRow(int var1) throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"absolute"});
      } else {
         this.attachStatementContext();
         return null;
      }
   }

   public ExecRow getRelativeRow(int var1) throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"relative"});
      } else {
         this.attachStatementContext();
         return null;
      }
   }

   public ExecRow setBeforeFirstRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"first"});
      } else {
         return null;
      }
   }

   public boolean checkRowPosition(int var1) throws StandardException {
      return false;
   }

   public int getRowNumber() {
      return 0;
   }

   public ExecRow getFirstRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"first"});
      } else {
         this.attachStatementContext();
         return null;
      }
   }

   public final ExecRow getNextRow() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"next"});
      } else {
         this.attachStatementContext();
         return this.getNextRowCore();
      }
   }

   public ExecRow getPreviousRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"previous"});
      } else {
         this.attachStatementContext();
         return null;
      }
   }

   public ExecRow getLastRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"last"});
      } else {
         this.attachStatementContext();
         return null;
      }
   }

   public ExecRow setAfterLastRow() throws StandardException {
      if (!this.isOpen) {
         throw StandardException.newException("XCL16.S", new Object[]{"last"});
      } else {
         return null;
      }
   }

   public boolean returnsRows() {
      return true;
   }

   public final long modifiedRowCount() {
      return 0L;
   }

   public void cleanUp() throws StandardException {
      if (this.isOpen) {
         this.close();
      }

   }

   public boolean isClosed() {
      return !this.isOpen;
   }

   public void finish() throws StandardException {
      this.finishAndRTS();
   }

   protected final void finishAndRTS() throws StandardException {
      if (!this.finished) {
         if (!this.isClosed()) {
            this.close();
         }

         this.finished = true;
         if (this.isTopResultSet && this.activation.isSingleExecution()) {
            this.activation.close();
         }
      }

   }

   public ResultDescription getResultDescription() {
      return this.resultDescription;
   }

   public long getExecuteTime() {
      return this.getTimeSpent(1);
   }

   public Timestamp getBeginExecutionTimestamp() {
      return this.startExecutionTime == 0L ? null : new Timestamp(this.startExecutionTime);
   }

   public Timestamp getEndExecutionTimestamp() {
      return this.endExecutionTime == 0L ? null : new Timestamp(this.endExecutionTime);
   }

   public final NoPutResultSet[] getSubqueryTrackingArray(int var1) {
      if (this.subqueryTrackingArray == null) {
         this.subqueryTrackingArray = new NoPutResultSet[var1];
      }

      return this.subqueryTrackingArray;
   }

   protected final long getCurrentTimeMillis() {
      return this.statisticsTimingOn ? System.currentTimeMillis() : 0L;
   }

   public ResultSet getAutoGeneratedKeysResultset() {
      return (ResultSet)null;
   }

   protected final long getElapsedMillis(long var1) {
      return this.statisticsTimingOn ? System.currentTimeMillis() - var1 : 0L;
   }

   protected final String dumpTimeStats(String var1, String var2) {
      return var1 + MessageService.getTextMessage("42Z30.U", new Object[0]) + " " + this.getTimeSpent(0) + "\n" + var1 + MessageService.getTextMessage("42Z31.U", new Object[0]) + " " + this.getTimeSpent(1) + "\n" + var1 + MessageService.getTextMessage("42Z32.U", new Object[0]) + "\n" + var2 + MessageService.getTextMessage("42Z33.U", new Object[0]) + " " + this.constructorTime + "\n" + var2 + MessageService.getTextMessage("42Z34.U", new Object[0]) + " " + this.openTime + "\n" + var2 + MessageService.getTextMessage("42Z35.U", new Object[0]) + " " + this.nextTime + "\n" + var2 + MessageService.getTextMessage("42Z36.U", new Object[0]) + " " + this.closeTime;
   }

   protected void attachStatementContext() throws StandardException {
      if (this.isTopResultSet) {
         if (this.statementContext == null || !this.statementContext.onStack()) {
            this.statementContext = this.getLanguageConnectionContext().getStatementContext();
         }

         this.statementContext.setTopResultSet(this, this.subqueryTrackingArray);
         if (this.subqueryTrackingArray == null) {
            this.subqueryTrackingArray = this.statementContext.getSubqueryTrackingArray();
         }

         this.statementContext.setActivation(this.activation);
      }

   }

   protected final LanguageConnectionContext getLanguageConnectionContext() {
      return this.getActivation().getLanguageConnectionContext();
   }

   public int resultSetNumber() {
      return 0;
   }

   final ExecutionFactory getExecutionFactory() {
      return this.activation.getExecutionFactory();
   }

   final TransactionController getTransactionController() {
      if (this.tc == null) {
         this.tc = this.getLanguageConnectionContext().getTransactionExecute();
      }

      return this.tc;
   }

   protected ExecRow getCompactRow(ExecRow var1, FormatableBitSet var2, boolean var3) throws StandardException {
      int var4 = var1.nColumns();
      if (var2 == null) {
         this.compactRow = var1;
         this.baseColumnMap = new int[var4];

         for(int var5 = 0; var5 < this.baseColumnMap.length; this.baseColumnMap[var5] = var5++) {
         }
      } else {
         int var9 = var2.getNumBitsSet();
         this.baseColumnMap = new int[var9];
         if (this.compactRow == null) {
            ExecutionFactory var6 = this.getLanguageConnectionContext().getLanguageConnectionFactory().getExecutionFactory();
            if (var3) {
               this.compactRow = var6.getIndexableRow(var9);
            } else {
               this.compactRow = var6.getValueRow(var9);
            }
         }

         int var10 = 0;

         for(int var7 = var2.anySetBit(); var7 != -1 && var7 < var4; var7 = var2.anySetBit(var7)) {
            DataValueDescriptor var8 = var1.getColumn(var7 + 1);
            if (var8 != null) {
               this.compactRow.setColumn(var10 + 1, var8);
            }

            this.baseColumnMap[var10] = var7;
            ++var10;
         }
      }

      return this.compactRow;
   }

   protected ExecRow setCompactRow(ExecRow var1, ExecRow var2) {
      ExecRow var3;
      if (this.baseColumnMap == null) {
         var3 = var1;
      } else {
         var3 = var2;
         this.setCompatRow(var2, var1.getRowArray());
      }

      return var3;
   }

   protected final void setCompatRow(ExecRow var1, DataValueDescriptor[] var2) {
      DataValueDescriptor[] var3 = var1.getRowArray();
      int[] var4 = this.baseColumnMap;

      for(int var5 = 0; var5 < var4.length; ++var5) {
         var3[var5] = var2[var4[var5]];
      }

   }

   public boolean isForUpdate() {
      return false;
   }

   public void checkCancellationFlag() throws StandardException {
      LanguageConnectionContext var1 = this.getLanguageConnectionContext();
      StatementContext var2 = var1.getStatementContext();
      if (var2 != null) {
         InterruptStatus.throwIf(var1);
         if (var2.isCancelled()) {
            throw StandardException.newException("XCL52.S", new Object[0]);
         }
      }
   }

   public final void addWarning(SQLWarning var1) {
      if (this.isTopResultSet) {
         if (this.warnings == null) {
            this.warnings = var1;
         } else {
            this.warnings.setNextWarning(var1);
         }

      } else {
         if (this.activation != null) {
            ResultSet var2 = this.activation.getResultSet();
            if (var2 != null) {
               var2.addWarning(var1);
            }
         }

      }
   }

   public final SQLWarning getWarnings() {
      SQLWarning var1 = this.warnings;
      this.warnings = null;
      return var1;
   }

   public Element toXML(Element var1, String var2) throws Exception {
      return childrenToXML(toXML(var1, var2, this), this);
   }

   public static Element toXML(Element var0, String var1, ResultSet var2) throws Exception {
      Element var3 = var0.getOwnerDocument().createElement(var1);
      var3.setAttribute("type", stripPackage(var2.getClass().getName()));
      var0.appendChild(var3);
      return var3;
   }

   private static String stripPackage(String var0) {
      return var0.substring(var0.lastIndexOf(".") + 1);
   }

   public static Element childrenToXML(Element var0, ResultSet var1) throws Exception {
      ArrayList var2 = new ArrayList();
      findResultSetFields(var2, var1.getClass());
      Field[] var3 = new Field[var2.size()];
      var2.toArray(var3);
      Arrays.sort(var3, new FieldComparator());

      for(Field var7 : var3) {
         Object var8 = var7.get(var1);
         if (var8 != null) {
            if (var7.getType().isArray()) {
               Element var15 = var0.getOwnerDocument().createElement("array");
               var15.setAttribute("arrayName", var7.getName());
               String var10 = stripPackage(var7.getType().getComponentType().getName()) + "[]";
               var15.setAttribute("type", var10);
               var0.appendChild(var15);
               int var11 = Array.getLength(var8);

               for(int var12 = 0; var12 < var11; ++var12) {
                  ResultSet var13 = (ResultSet)Array.get(var8, var12);
                  if (var13 != null) {
                     Element var14 = var13.toXML(var15, "cell");
                     var14.setAttribute("cellNumber", Integer.toString(var12));
                  }
               }
            } else {
               ResultSet var9 = (ResultSet)var8;
               var9.toXML(var0, var7.getName());
            }
         }
      }

      return var0;
   }

   private static void findResultSetFields(ArrayList var0, Class var1) throws Exception {
      if (var1 != null) {
         Field[] var2 = var1.getDeclaredFields();

         for(Field var6 : var2) {
            if (ResultSet.class.isAssignableFrom(var6.getType())) {
               var0.add(var6);
            } else if (var6.getType().isArray() && ResultSet.class.isAssignableFrom(var6.getType().getComponentType())) {
               var0.add(var6);
            }
         }

         findResultSetFields(var0, var1.getSuperclass());
      }
   }

   public static final class FieldComparator implements Comparator {
      public int compare(Field var1, Field var2) {
         return var1.getName().compareTo(var2.getName());
      }
   }
}
