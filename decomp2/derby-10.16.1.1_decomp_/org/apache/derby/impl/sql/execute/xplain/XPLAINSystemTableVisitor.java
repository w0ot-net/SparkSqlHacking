package org.apache.derby.impl.sql.execute.xplain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINResultSetTimingsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINScanPropsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINSortPropsDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINStatementDescriptor;
import org.apache.derby.impl.sql.catalog.XPLAINStatementTimingsDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.info.JVMInfo;

public class XPLAINSystemTableVisitor implements XPLAINVisitor {
   private boolean no_call_stmts = true;
   private LanguageConnectionContext lcc;
   private DataDictionary dd;
   private Activation activation;
   private boolean considerTimingInformation = false;
   private XPLAINStatementDescriptor stmt;
   private XPLAINStatementTimingsDescriptor stmtTimings = null;
   private UUID stmtUUID;
   private List rsets = new ArrayList();
   private List rsetsTimings = new ArrayList();
   private List sortrsets = new ArrayList();
   private List scanrsets = new ArrayList();
   private int noChildren;
   private Stack UUIDStack = new Stack();

   private void pushUUIDnoChildren(UUID var1) {
      for(int var2 = 0; var2 < this.noChildren; ++var2) {
         this.UUIDStack.push(var1);
      }

   }

   public void setNumberOfChildren(int var1) {
      this.noChildren = var1;
   }

   public void visit(ResultSetStatistics var1) {
      UUID var2 = null;
      if (this.considerTimingInformation) {
         var2 = this.dd.getUUIDFactory().createUUID();
         this.rsetsTimings.add(var1.getResultSetTimingsDescriptor(var2));
      }

      UUID var3 = this.dd.getUUIDFactory().createUUID();
      XPLAINSortPropsDescriptor var4 = (XPLAINSortPropsDescriptor)var1.getSortPropsDescriptor(var3);
      if (var4 != null) {
         this.sortrsets.add(var4);
      } else {
         var3 = null;
      }

      UUID var5 = this.dd.getUUIDFactory().createUUID();
      XPLAINScanPropsDescriptor var6 = (XPLAINScanPropsDescriptor)var1.getScanPropsDescriptor(var5);
      if (var6 != null) {
         this.scanrsets.add(var6);
      } else {
         var5 = null;
      }

      UUID var7 = this.dd.getUUIDFactory().createUUID();
      this.rsets.add((XPLAINResultSetDescriptor)var1.getResultSetDescriptor(var7, this.UUIDStack.empty() ? (UUID)null : this.UUIDStack.pop(), var5, var3, this.stmtUUID, var2));
      this.pushUUIDnoChildren(var7);
   }

   public void reset() {
      this.lcc = this.activation.getLanguageConnectionContext();
      this.dd = this.lcc.getDataDictionary();
   }

   public void doXPLAIN(RunTimeStatistics var1, Activation var2) throws StandardException {
      this.activation = var2;
      this.reset();
      this.considerTimingInformation = this.lcc.getStatisticsTiming();
      UUID var3 = null;
      if (this.considerTimingInformation) {
         var3 = this.dd.getUUIDFactory().createUUID();
         Timestamp var4 = var1.getEndExecutionTimestamp();
         Timestamp var5 = var1.getBeginExecutionTimestamp();
         long var6;
         if (var4 != null && var5 != null) {
            var6 = var4.getTime() - var5.getTime();
         } else {
            var6 = 0L;
         }

         this.stmtTimings = new XPLAINStatementTimingsDescriptor(var3, var1.getParseTimeInMillis(), var1.getBindTimeInMillis(), var1.getOptimizeTimeInMillis(), var1.getGenerateTimeInMillis(), var1.getCompileTimeInMillis(), var6, var1.getBeginCompilationTimestamp(), var1.getEndCompilationTimestamp(), var1.getBeginExecutionTimestamp(), var1.getEndExecutionTimestamp());
      }

      this.stmtUUID = this.dd.getUUIDFactory().createUUID();
      String var16 = XPLAINUtil.getStatementType(var1.getStatementText());
      if (!var16.equalsIgnoreCase("C") || !this.no_call_stmts) {
         String var17 = this.lcc.getTransactionExecute().getTransactionIdString();
         String var18 = Integer.toString(this.lcc.getInstanceNumber());
         String var7 = Integer.toString(JVMInfo.JDK_ID);
         String var8 = System.getProperty("os.name");
         long var9 = System.currentTimeMillis();
         String var11 = this.lcc.getXplainOnlyMode() ? "O" : "F";
         Timestamp var12 = new Timestamp(var9);
         String var13 = Thread.currentThread().toString();
         this.stmt = new XPLAINStatementDescriptor(this.stmtUUID, var1.getStatementName(), var16, var1.getStatementText(), var7, var8, var11, var12, var13, var17, var18, this.lcc.getDbname(), this.lcc.getDrdaID(), var3);

         try {
            this.addStmtDescriptorsToSystemCatalog();
            var1.acceptFromTopResultSet(this);
            this.addArraysToSystemCatalogs();
         } catch (SQLException var15) {
            throw StandardException.plainWrapException(var15);
         }

         this.clean();
      }
   }

   private void clean() {
      this.activation = null;
      this.lcc = null;
      this.dd = null;
      this.stmtUUID = null;
      this.stmt = null;
      this.stmtTimings = null;
      this.rsets.clear();
      this.rsetsTimings.clear();
      this.sortrsets.clear();
      this.scanrsets.clear();
      this.UUIDStack.clear();
   }

   private Connection getDefaultConn() throws SQLException {
      ConnectionContext var1 = (ConnectionContext)this.lcc.getContextManager().getContext("JDBC_ConnectionContext");
      return var1.getNestedConnection(true);
   }

   private void addStmtDescriptorsToSystemCatalog() throws StandardException, SQLException {
      boolean var1 = this.lcc.getRunTimeStatisticsMode();
      this.lcc.setRunTimeStatisticsMode(false);
      Connection var2 = this.getDefaultConn();
      PreparedStatement var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_STATEMENTS"));
      this.stmt.setStatementParameters(var3);
      var3.executeUpdate();
      var3.close();
      if (this.considerTimingInformation) {
         var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_STATEMENT_TIMINGS"));
         this.stmtTimings.setStatementParameters(var3);
         var3.executeUpdate();
         var3.close();
      }

      var2.close();
      this.lcc.setRunTimeStatisticsMode(var1);
   }

   private void addArraysToSystemCatalogs() throws StandardException, SQLException {
      boolean var1 = this.lcc.getRunTimeStatisticsMode();
      this.lcc.setRunTimeStatisticsMode(false);
      Connection var2 = this.getDefaultConn();
      PreparedStatement var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_RESULTSETS"));

      for(XPLAINResultSetDescriptor var5 : this.rsets) {
         var5.setStatementParameters(var3);
         var3.executeUpdate();
      }

      var3.close();
      if (this.considerTimingInformation) {
         var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_RESULTSET_TIMINGS"));

         for(XPLAINResultSetTimingsDescriptor var6 : this.rsetsTimings) {
            var6.setStatementParameters(var3);
            var3.executeUpdate();
         }

         var3.close();
      }

      var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_SCAN_PROPS"));

      for(XPLAINScanPropsDescriptor var13 : this.scanrsets) {
         var13.setStatementParameters(var3);
         var3.executeUpdate();
      }

      var3.close();
      var3 = var2.prepareStatement((String)this.lcc.getXplainStatement("SYSXPLAIN_SORT_PROPS"));

      for(XPLAINSortPropsDescriptor var7 : this.sortrsets) {
         var7.setStatementParameters(var3);
         var3.executeUpdate();
      }

      var3.close();
      var2.close();
      this.lcc.setRunTimeStatisticsMode(var1);
   }
}
