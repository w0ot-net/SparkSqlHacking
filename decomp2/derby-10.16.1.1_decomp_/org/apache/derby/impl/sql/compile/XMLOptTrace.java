package org.apache.derby.impl.sql.compile;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.UniqueTupleDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class XMLOptTrace implements OptTrace {
   private static final String STMT = "statement";
   private static final String STMT_ID = "stmtID";
   private static final String STMT_TEXT = "stmtText";
   private static final String QBLOCK = "queryBlock";
   private static final String QBLOCK_OPTIMIZER_ID = "qbOptimizerID";
   private static final String QBLOCK_START_TIME = "qbStartTime";
   private static final String QBLOCK_ID = "qbID";
   private static final String QBLOCK_OPTIMIZABLE = "qbOptimizable";
   private static final String QBLOCK_OPT_TABLE_NUMBER = "qboTableNumber";
   private static final String QBLOCK_TIMEOUT = "qbTimeout";
   private static final String QBLOCK_VACUOUS = "qbVacuous";
   private static final String QBLOCK_SORT_COST = "qbSortCost";
   private static final String QBLOCK_TOTAL_COST = "qbTotalCost";
   private static final String QBLOCK_NO_BEST_PLAN = "qbNoBestPlan";
   private static final String QBLOCK_SKIP = "qbSkip";
   private static final String JO = "joinOrder";
   private static final String JO_COMPLETE = "joComplete";
   private static final String JO_SLOT = "joSlot";
   private static final String DECORATION = "decoration";
   private static final String DECORATION_CONGLOM_NAME = "decConglomerateName";
   private static final String DECORATION_KEY = "decKey";
   private static final String DECORATION_TABLE_NAME = "decTableName";
   private static final String DECORATION_JOIN_STRATEGY = "decJoinStrategy";
   private static final String DECORATION_SKIP = "decSkip";
   private static final String DECORATION_CONGLOM_COST = "decConglomerateCost";
   private static final String DECORATION_FIRST_COLUMN_SELECTIVITY = "decExtraFirstColumnPreds";
   private static final String DECORATION_EXTRA_START_STOP_SELECTIVITY = "decExtraFirstStartStopPreds";
   private static final String DECORATION_START_STOP_SELECTIVITY = "decStartStopPred";
   private static final String DECORATION_EXTRA_QUALIFIERS = "decExtraQualifiers";
   private static final String DECORATION_EXTRA_NON_QUALIFIERS = "decExtraNonQualifiers";
   private static final String SKIP_REASON = "skipReason";
   private static final String PC = "planCost";
   private static final String PC_TYPE = "pcType";
   private static final String PC_COMPLETE = "pcComplete";
   private static final String PC_AVOID_SORT = "pcAvoidSort";
   private static final String PC_SUMMARY = "pcSummary";
   private static final String CE_ESTIMATED_COST = "ceEstimatedCost";
   private static final String CE_ROW_COUNT = "ceEstimatedRowCount";
   private static final String CE_SINGLE_SCAN_ROW_COUNT = "ceSingleScanRowCount";
   private static final String SEL_COUNT = "selCount";
   private static final String SEL_SELECTIVITY = "selSelectivity";
   private static final String TABLE_FUNCTION_FLAG = "()";
   static final String PLAN_COST_VTI = "create function planCost\n(\n    xmlResourceName varchar( 32672 ),\n    rowTag varchar( 32672 ),\n    parentTags ArrayList,\n    childTags ArrayList\n)\nreturns table\n(\n    text varchar( 32672 ),\n    stmtID    int,\n    qbID   int,\n    complete  boolean,\n    summary   varchar( 32672 ),\n    type        varchar( 50 ),\n    estimatedCost        double,\n    estimatedRowCount    bigint\n)\nlanguage java parameter style derby_jdbc_result_set no sql\nexternal name 'org.apache.derby.vti.XmlVTI.xmlVTI'\n";
   static final String PLAN_COST_VIEW = "create view planCost as\nselect *\nfrom table\n(\n    planCost\n    (\n        'FILE_URL',\n        'planCost',\n        asList( 'stmtText', 'stmtID', 'qbID' ),\n        asList( 'pcComplete', 'pcSummary', 'pcType', 'ceEstimatedCost', 'ceEstimatedRowCount' )\n     )\n) v\n";
   private Document _doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
   private Element _root = this.createElement((Element)null, "optimizerTrace", (String)null);
   private Element _currentStatement;
   private int _currentStatementID;
   private QueryBlock _currentQueryBlock;
   private int _maxQueryID;
   private Stack _queryBlockStack;
   private ContextManager _cm;
   private LanguageConnectionContext _lcc;

   public XMLOptTrace() throws ParserConfigurationException {
      this._doc.appendChild(this._root);
   }

   public void traceStartStatement(String var1) {
      ++this._currentStatementID;
      this._maxQueryID = 0;
      this._currentQueryBlock = null;
      this._queryBlockStack = new Stack();
      this._currentStatement = this.createElement(this._root, "statement", (String)null);
      this._currentStatement.setAttribute("stmtID", Integer.toString(this._currentStatementID));
      this.createElement(this._currentStatement, "stmtText", var1);
   }

   public void traceStartQueryBlock(long var1, int var3, OptimizableList var4) {
      ++this._maxQueryID;
      if (this._currentQueryBlock != null) {
         this._queryBlockStack.push(this._currentQueryBlock);
      }

      Element var5 = this.createElement(this._currentStatement, "queryBlock", (String)null);
      var5.setAttribute("qbOptimizerID", Integer.toString(var3));
      var5.setAttribute("qbStartTime", this.formatTimestamp(var1));
      var5.setAttribute("qbID", Integer.toString(this._maxQueryID));
      this._currentQueryBlock = new QueryBlock(this._maxQueryID, var4, var5);
      if (var4 != null) {
         for(int var6 = 0; var6 < var4.size(); ++var6) {
            Optimizable var7 = var4.getOptimizable(var6);
            if (this._cm == null) {
               this._cm = ((QueryTreeNode)var7).getContextManager();
               this._lcc = (LanguageConnectionContext)this._cm.getContext("LanguageConnectionContext");
            }

            Element var8 = this.createElement(var5, "qbOptimizable", this.getOptimizableName(var7).getFullSQLName());
            var8.setAttribute("qboTableNumber", Integer.toString(var7.getTableNumber()));
         }
      }

   }

   public void traceEndQueryBlock() {
      if (this._queryBlockStack.size() > 0) {
         this._currentQueryBlock = (QueryBlock)this._queryBlockStack.pop();
      }

   }

   public void traceTimeout(long var1, CostEstimate var3) {
      Element var4 = this.createElement(this._currentQueryBlock.queryBlockElement, "qbTimeout", (String)null);
      this.formatCost(var4, var3);
   }

   public void traceVacuous() {
      this.createElement(this._currentQueryBlock.queryBlockElement, "qbVacuous", (String)null);
   }

   public void traceCompleteJoinOrder() {
      if (this._currentQueryBlock.currentJoinsElement != null) {
         this._currentQueryBlock.currentJoinsElement.setAttribute("joComplete", "true");
      }

   }

   public void traceSortCost(CostEstimate var1, CostEstimate var2) {
      Element var3 = this.createElement(this._currentQueryBlock.queryBlockElement, "qbSortCost", (String)null);
      this.formatCost(var3, var1);
      Element var4 = this.createElement(this._currentQueryBlock.queryBlockElement, "qbTotalCost", (String)null);
      this.formatCost(var4, var2);
   }

   public void traceNoBestPlan() {
      this.createElement(this._currentQueryBlock.queryBlockElement, "qbNoBestPlan", (String)null);
   }

   public void traceModifyingAccessPaths(int var1) {
   }

   public void traceShortCircuiting(boolean var1, Optimizable var2, int var3) {
   }

   public void traceSkippingJoinOrder(int var1, int var2, int[] var3, JBitSet var4) {
      Optimizable var5 = this._currentQueryBlock.optimizableList.getOptimizable(var1);
      Element var10001 = this._currentQueryBlock.queryBlockElement;
      TableName var10003 = this.getOptimizableName(var5);
      Element var6 = this.formatSkip(var10001, "qbSkip", "Useless join order. " + var10003.getFullSQLName() + " depends on tables after it in the join order");
      this.formatJoinOrder(var6, var3);
   }

   public void traceIllegalUserJoinOrder() {
   }

   public void traceUserJoinOrderOptimized() {
   }

   public void traceJoinOrderConsideration(int var1, int[] var2, JBitSet var3) {
      this._currentQueryBlock.currentJoinsElement = this.createElement(this._currentQueryBlock.queryBlockElement, "joinOrder", (String)null);
      this._currentQueryBlock.currentJoinOrder = var2;
      this._currentQueryBlock.currentDecorationStrategy = null;
      this._currentQueryBlock.currentDecoration = null;
      this.formatJoinOrder(this._currentQueryBlock.currentJoinsElement, var2);
   }

   public void traceCostWithoutSortAvoidance(CostEstimate var1) {
      this.formatPlanCost(this._currentQueryBlock.currentJoinsElement, "withoutSortAvoidance", this._currentQueryBlock.currentJoinOrder, 1, var1);
   }

   public void traceCostWithSortAvoidance(CostEstimate var1) {
      this.formatPlanCost(this._currentQueryBlock.currentJoinsElement, "withSortAvoidance", this._currentQueryBlock.currentJoinOrder, 2, var1);
   }

   public void traceCurrentPlanAvoidsSort(CostEstimate var1, CostEstimate var2) {
   }

   public void traceCheapestPlanSoFar(int var1, CostEstimate var2) {
   }

   public void traceSortNeededForOrdering(int var1, RequiredRowOrdering var2) {
   }

   public void traceRememberingBestJoinOrder(int var1, int[] var2, int var3, CostEstimate var4, JBitSet var5) {
      if (this._currentQueryBlock.currentBestPlan != null) {
         this._currentQueryBlock.queryBlockElement.removeChild(this._currentQueryBlock.currentBestPlan);
      }

      this._currentQueryBlock.currentBestPlan = this.formatPlanCost(this._currentQueryBlock.queryBlockElement, "bestPlan", var2, var3, var4);
   }

   public void traceSkippingBecauseTooMuchMemory(int var1) {
      this.formatSkip(this._currentQueryBlock.currentDecoration, "decSkip", "Exceeds limit on memory per table: " + var1);
   }

   public void traceCostOfNScans(int var1, double var2, CostEstimate var4) {
   }

   public void traceSkipUnmaterializableHashJoin() {
      this.formatSkip(this._currentQueryBlock.currentDecoration, "decSkip", "Hash strategy not possible because table is not materializable");
   }

   public void traceSkipHashJoinNoHashKeys() {
      this.formatSkip(this._currentQueryBlock.currentDecoration, "decSkip", "No hash keys");
   }

   public void traceHashKeyColumns(int[] var1) {
   }

   public void traceOptimizingJoinNode() {
   }

   public void traceConsideringJoinStrategy(JoinStrategy var1, int var2) {
      this._currentQueryBlock.currentDecorationStrategy = var1;
   }

   public void traceRememberingBestAccessPath(AccessPath var1, int var2, int var3) {
   }

   public void traceNoMoreConglomerates(int var1) {
   }

   public void traceConsideringConglomerate(ConglomerateDescriptor var1, int var2) {
      Optimizable var3 = this.getOptimizable(var2);
      this._currentQueryBlock.currentDecoration = this.createElement(this._currentQueryBlock.currentJoinsElement, "decoration", (String)null);
      this._currentQueryBlock.currentDecoration.setAttribute("decConglomerateName", var1.getConglomerateName());
      this._currentQueryBlock.currentDecoration.setAttribute("decTableName", this.getOptimizableName(var3).toString());
      this._currentQueryBlock.currentDecoration.setAttribute("decJoinStrategy", this._currentQueryBlock.currentDecorationStrategy.getName());
      String[] var4 = var1.getColumnNames();
      if (var1.isIndex() && var4 != null) {
         int[] var5 = var1.getIndexDescriptor().baseColumnPositions();

         for(int var6 = 0; var6 < var5.length; ++var6) {
            this.createElement(this._currentQueryBlock.currentDecoration, "decKey", var4[var5[var6] - 1]);
         }
      }

   }

   public void traceScanningHeapWithUniqueKey() {
   }

   public void traceAddingUnorderedOptimizable(int var1) {
   }

   public void traceChangingAccessPathForTable(int var1) {
   }

   public void traceNoStartStopPosition() {
   }

   public void traceNonCoveringIndexCost(double var1, int var3) {
   }

   public void traceConstantStartStopPositions() {
   }

   public void traceEstimatingCostOfConglomerate(ConglomerateDescriptor var1, int var2) {
   }

   public void traceLookingForSpecifiedIndex(String var1, int var2) {
   }

   public void traceSingleMatchedRowCost(double var1, int var3) {
   }

   public void traceCostIncludingExtra1stColumnSelectivity(CostEstimate var1, int var2) {
   }

   public void traceNextAccessPath(String var1, int var2) {
   }

   public void traceCostIncludingExtraStartStop(CostEstimate var1, int var2) {
   }

   public void traceCostIncludingExtraQualifierSelectivity(CostEstimate var1, int var2) {
   }

   public void traceCostIncludingExtraNonQualifierSelectivity(CostEstimate var1, int var2) {
   }

   public void traceCostOfNoncoveringIndex(CostEstimate var1, int var2) {
   }

   public void traceRememberingJoinStrategy(JoinStrategy var1, int var2) {
   }

   public void traceRememberingBestAccessPathSubstring(AccessPath var1, int var2) {
   }

   public void traceRememberingBestSortAvoidanceAccessPathSubstring(AccessPath var1, int var2) {
   }

   public void traceRememberingBestUnknownAccessPathSubstring(AccessPath var1, int var2) {
   }

   public void traceCostOfConglomerateScan(int var1, ConglomerateDescriptor var2, CostEstimate var3, int var4, double var5, int var7, double var8, int var10, double var11, int var13, double var14, int var16, double var17) {
      Element var19 = this.createElement(this._currentQueryBlock.currentDecoration, "decConglomerateCost", (String)null);
      var19.setAttribute("name", var2.getConglomerateName());
      this.formatCost(var19, var3);
      this.formatSelectivity(var19, "decExtraFirstColumnPreds", var4, var5);
      this.formatSelectivity(var19, "decExtraFirstStartStopPreds", var7, var8);
      this.formatSelectivity(var19, "decStartStopPred", var10, var11);
      this.formatSelectivity(var19, "decExtraQualifiers", var13, var14);
      this.formatSelectivity(var19, "decExtraNonQualifiers", var16, var17);
   }

   public void traceCostIncludingCompositeSelectivityFromStats(CostEstimate var1, int var2) {
   }

   public void traceCompositeSelectivityFromStatistics(double var1) {
   }

   public void traceCostIncludingStatsForIndex(CostEstimate var1, int var2) {
   }

   public void printToWriter(PrintWriter var1) {
      try {
         TransformerFactory var2 = TransformerFactory.newInstance();
         Transformer var3 = var2.newTransformer();
         DOMSource var4 = new DOMSource(this._doc);
         StreamResult var5 = new StreamResult(var1);
         var3.setOutputProperty("omit-xml-declaration", "no");
         var3.setOutputProperty("method", "xml");
         var3.setOutputProperty("indent", "yes");
         var3.setOutputProperty("encoding", "UTF-8");
         var3.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
         var3.transform(var4, var5);
      } catch (Throwable var6) {
         this.printThrowable(var6);
      }

   }

   private Optimizable getOptimizable(int var1) {
      for(int var2 = 0; var2 < this._currentQueryBlock.optimizableList.size(); ++var2) {
         Optimizable var3 = this._currentQueryBlock.optimizableList.getOptimizable(var2);
         if (var1 == var3.getTableNumber()) {
            return var3;
         }
      }

      return null;
   }

   private TableName getOptimizableName(Optimizable var1) {
      try {
         if (this.isBaseTable(var1)) {
            ProjectRestrictNode var7 = (ProjectRestrictNode)var1;
            TableDescriptor var9 = ((FromBaseTable)var7.getChildResult()).getTableDescriptor();
            return this.makeTableName(var9.getSchemaName(), var9.getName(), this._cm);
         }

         if (OptimizerImpl.isTableFunction(var1)) {
            ProjectRestrictNode var6 = (ProjectRestrictNode)var1;
            AliasDescriptor var8 = ((StaticMethodCallNode)((FromVTI)var6.getChildResult()).getMethodCall()).ad;
            return this.makeTableName(var8.getSchemaName(), var8.getName(), this._cm);
         }

         if (this.isFromTable(var1)) {
            TableName var2 = ((FromTable)((ProjectRestrictNode)var1).getChildResult()).getTableName();
            if (var2 != null) {
               return var2;
            }
         }
      } catch (StandardException var4) {
      }

      String var5 = var1.getClass().getName();
      String var3 = var5.substring(var5.lastIndexOf(".") + 1);
      return this.makeTableName((String)null, var3, this._cm);
   }

   private boolean isBaseTable(Optimizable var1) {
      if (!(var1 instanceof ProjectRestrictNode)) {
         return false;
      } else {
         ResultSetNode var2 = ((ProjectRestrictNode)var1).getChildResult();
         return var2 instanceof FromBaseTable;
      }
   }

   private boolean isFromTable(Optimizable var1) {
      if (!(var1 instanceof ProjectRestrictNode)) {
         return false;
      } else {
         ResultSetNode var2 = ((ProjectRestrictNode)var1).getChildResult();
         return var2 instanceof FromTable;
      }
   }

   private TableName makeTableName(String var1, String var2, ContextManager var3) {
      TableName var4 = new TableName(var1, var2, var3);
      return var4;
   }

   private void printThrowable(Throwable var1) {
      var1.printStackTrace(Monitor.getStream().getPrintWriter());
   }

   private Element createElement(Element var1, String var2, String var3) {
      Element var4 = null;

      try {
         var4 = this._doc.createElement(var2);
         if (var1 != null) {
            var1.appendChild(var4);
         }

         if (var3 != null) {
            var4.setTextContent(var3);
         }
      } catch (Throwable var6) {
         this.printThrowable(var6);
      }

      return var4;
   }

   private String formatTimestamp(long var1) {
      return (new Date(var1)).toString();
   }

   private Element formatSkip(Element var1, String var2, String var3) {
      Element var4 = this.createElement(var1, var2, (String)null);
      var4.setAttribute("skipReason", var3);
      return var4;
   }

   private Element formatPlanCost(Element var1, String var2, int[] var3, int var4, CostEstimate var5) {
      Element var6 = this.createElement(var1, "planCost", (String)null);
      var6.setAttribute("pcType", var2);
      if (this.isComplete(var3)) {
         var6.setAttribute("pcComplete", "true");
      }

      if (var4 == 2) {
         var6.setAttribute("pcAvoidSort", "true");
      }

      this.createElement(var6, "pcSummary", this.formatPlanSummary(var3, var4));
      this.formatCost(var6, var5);
      return var6;
   }

   private boolean isComplete(int[] var1) {
      if (var1 == null) {
         return false;
      } else if (var1.length < this._currentQueryBlock.optimizableList.size()) {
         return false;
      } else {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            if (var1[var2] < 0) {
               return false;
            }
         }

         return true;
      }
   }

   private void formatCost(Element var1, CostEstimate var2) {
      this.createElement(var1, "ceEstimatedCost", Double.toString(var2.getEstimatedCost()));
      this.createElement(var1, "ceEstimatedRowCount", Long.toString(var2.getEstimatedRowCount()));
      this.createElement(var1, "ceSingleScanRowCount", Double.toString(var2.singleScanRowCount()));
   }

   private void formatSelectivity(Element var1, String var2, int var3, double var4) {
      Element var6 = this.createElement(var1, var2, (String)null);
      var6.setAttribute("selCount", Integer.toString(var3));
      var6.setAttribute("selSelectivity", Double.toString(var4));
   }

   private void formatJoinOrder(Element var1, int[] var2) {
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            int var4 = var2[var3];
            if (var4 >= 0) {
               Optimizable var5 = this._currentQueryBlock.optimizableList.getOptimizable(var4);
               this.createElement(var1, "joSlot", this.getOptimizableName(var5).getFullSQLName());
            }
         }
      }

   }

   private String formatPlanSummary(int[] var1, int var2) {
      try {
         Object var3 = null;
         StringBuilder var4 = new StringBuilder();
         boolean var5 = var2 == 2;

         int var6;
         for(var6 = 0; var6 < var1.length && var1[var6] >= 0; ++var6) {
         }

         for(int var7 = 0; var7 < var6; ++var7) {
            int var8 = var1[var7];
            if (var8 >= this._currentQueryBlock.optimizableList.size()) {
               var4.append("{ UNKNOWN LIST INDEX " + var8 + " } ");
            } else {
               Optimizable var9 = this._currentQueryBlock.optimizableList.getOptimizable(var8);
               AccessPath var10 = var5 ? var9.getBestSortAvoidancePath() : var9.getBestAccessPath();
               JoinStrategy var11 = var10.getJoinStrategy();
               Object var12 = OptimizerImpl.isTableFunction(var9) ? ((StaticMethodCallNode)((FromVTI)((ProjectRestrictNode)var9).getChildResult()).getMethodCall()).ad : var10.getConglomerateDescriptor();
               Object var13 = var12 == null ? new OptimizerPlan.DeadEnd(this.getOptimizableName(var9).toString()) : OptimizerPlan.makeRowSource((UniqueTupleDescriptor)var12, this._lcc.getDataDictionary());
               if (var3 != null) {
                  var13 = new OptimizerPlan.Join(var11, (OptimizerPlan)var3, (OptimizerPlan)var13);
               }

               var3 = var13;
            }
         }

         return var3.toString();
      } catch (Exception var14) {
         return var14.getMessage();
      }
   }

   public static final class QueryBlock {
      final int queryBlockID;
      final OptimizableList optimizableList;
      final Element queryBlockElement;
      Element currentJoinsElement;
      int[] currentJoinOrder;
      Element currentBestPlan;
      JoinStrategy currentDecorationStrategy;
      Element currentDecoration;

      public QueryBlock(int var1, OptimizableList var2, Element var3) {
         this.queryBlockID = var1;
         this.optimizableList = var2;
         this.queryBlockElement = var3;
      }
   }
}
