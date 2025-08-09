package org.apache.hadoop.hive.metastore.parser;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.antlr.runtime.ANTLRStringStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.serde.serdeConstants;

public class ExpressionTree {
   public static final ExpressionTree EMPTY_TREE = new ExpressionTree();
   private TreeNode root = null;
   private final Stack nodeStack = new Stack();

   public void accept(TreeVisitor treeVisitor) throws MetaException {
      if (this.root != null) {
         this.root.accept(treeVisitor);
      }

   }

   private static void makeFilterForEquals(String keyName, String value, String paramName, Map params, int keyPos, int keyCount, boolean isEq, FilterBuilder fltr) throws MetaException {
      Map<String, String> partKeyToVal = new HashMap();
      partKeyToVal.put(keyName, value);
      String escapedNameFragment = Warehouse.makePartName(partKeyToVal, false);
      if (keyCount == 1) {
         params.put(paramName, escapedNameFragment);
         fltr.append("partitionName ").append(isEq ? "== " : "!= ").append(paramName);
      } else if (keyPos + 1 == keyCount) {
         params.put(paramName, "/" + escapedNameFragment);
         fltr.append(isEq ? "" : "!").append("partitionName.endsWith(").append(paramName).append(")");
      } else if (keyPos == 0) {
         params.put(paramName, escapedNameFragment + "/");
         fltr.append(isEq ? "" : "!").append("partitionName.startsWith(").append(paramName).append(")");
      } else {
         params.put(paramName, "/" + escapedNameFragment + "/");
         fltr.append("partitionName.indexOf(").append(paramName).append(")").append(isEq ? ">= 0" : "< 0");
      }

   }

   public TreeNode getRoot() {
      return this.root;
   }

   @VisibleForTesting
   public void setRootForTest(TreeNode tn) {
      this.root = tn;
   }

   public void addIntermediateNode(LogicalOperator andOr) {
      TreeNode rhs = (TreeNode)this.nodeStack.pop();
      TreeNode lhs = (TreeNode)this.nodeStack.pop();
      TreeNode newNode = new TreeNode(lhs, andOr, rhs);
      this.nodeStack.push(newNode);
      this.root = newNode;
   }

   public void addLeafNode(LeafNode newNode) {
      if (this.root == null) {
         this.root = newNode;
      }

      this.nodeStack.push(newNode);
   }

   public void generateJDOFilterFragment(Configuration conf, Table table, Map params, FilterBuilder filterBuilder) throws MetaException {
      if (this.root != null) {
         filterBuilder.append(" && ( ");
         this.root.generateJDOFilter(conf, table, params, filterBuilder);
         filterBuilder.append(" )");
      }
   }

   public static enum LogicalOperator {
      AND,
      OR;
   }

   public static enum Operator {
      EQUALS("=", "==", "="),
      GREATERTHAN(">"),
      LESSTHAN("<"),
      LESSTHANOREQUALTO("<="),
      GREATERTHANOREQUALTO(">="),
      LIKE("LIKE", "matches", "like"),
      NOTEQUALS2("!=", "!=", "<>"),
      NOTEQUALS("<>", "!=", "<>");

      private final String op;
      private final String jdoOp;
      private final String sqlOp;

      private Operator(String op) {
         this.op = op;
         this.jdoOp = op;
         this.sqlOp = op;
      }

      private Operator(String op, String jdoOp, String sqlOp) {
         this.op = op;
         this.jdoOp = jdoOp;
         this.sqlOp = sqlOp;
      }

      public String getOp() {
         return this.op;
      }

      public String getJdoOp() {
         return this.jdoOp;
      }

      public String getSqlOp() {
         return this.sqlOp;
      }

      public static Operator fromString(String inputOperator) {
         for(Operator op : values()) {
            if (op.getOp().equals(inputOperator)) {
               return op;
            }
         }

         throw new Error("Invalid value " + inputOperator + " for " + Operator.class.getSimpleName());
      }

      public String toString() {
         return this.op;
      }
   }

   public static class TreeVisitor {
      private void visit(TreeNode node) throws MetaException {
         if (!this.shouldStop()) {
            assert node != null && node.getLhs() != null && node.getRhs() != null;

            this.beginTreeNode(node);
            node.lhs.accept(this);
            this.midTreeNode(node);
            node.rhs.accept(this);
            this.endTreeNode(node);
         }
      }

      protected void beginTreeNode(TreeNode node) throws MetaException {
      }

      protected void midTreeNode(TreeNode node) throws MetaException {
      }

      protected void endTreeNode(TreeNode node) throws MetaException {
      }

      protected void visit(LeafNode node) throws MetaException {
      }

      protected boolean shouldStop() {
         return false;
      }
   }

   public static class FilterBuilder {
      private final StringBuilder result = new StringBuilder();
      private String errorMessage = null;
      private boolean expectNoErrors = false;

      public FilterBuilder(boolean expectNoErrors) {
         this.expectNoErrors = expectNoErrors;
      }

      public String getFilter() throws MetaException {
         assert this.errorMessage == null;

         if (this.errorMessage != null) {
            throw new MetaException("Trying to get result after error: " + this.errorMessage);
         } else {
            return this.result.toString();
         }
      }

      public String toString() {
         try {
            return this.getFilter();
         } catch (MetaException ex) {
            throw new RuntimeException(ex);
         }
      }

      public String getErrorMessage() {
         return this.errorMessage;
      }

      public boolean hasError() {
         return this.errorMessage != null;
      }

      public FilterBuilder append(String filterPart) {
         this.result.append(filterPart);
         return this;
      }

      public void setError(String errorMessage) throws MetaException {
         this.errorMessage = errorMessage;
         if (this.expectNoErrors) {
            throw new MetaException(errorMessage);
         }
      }
   }

   public static class TreeNode {
      private TreeNode lhs;
      private LogicalOperator andOr;
      private TreeNode rhs;

      public TreeNode() {
      }

      public TreeNode(TreeNode lhs, LogicalOperator andOr, TreeNode rhs) {
         this.lhs = lhs;
         this.andOr = andOr;
         this.rhs = rhs;
      }

      public TreeNode getLhs() {
         return this.lhs;
      }

      public LogicalOperator getAndOr() {
         return this.andOr;
      }

      public TreeNode getRhs() {
         return this.rhs;
      }

      protected void accept(TreeVisitor visitor) throws MetaException {
         visitor.visit(this);
      }

      public void generateJDOFilter(Configuration conf, Table table, Map params, FilterBuilder filterBuffer) throws MetaException {
         if (!filterBuffer.hasError()) {
            if (this.lhs != null) {
               filterBuffer.append(" (");
               this.lhs.generateJDOFilter(conf, table, params, filterBuffer);
               if (this.rhs != null) {
                  if (this.andOr == ExpressionTree.LogicalOperator.AND) {
                     filterBuffer.append(" && ");
                  } else {
                     filterBuffer.append(" || ");
                  }

                  this.rhs.generateJDOFilter(conf, table, params, filterBuffer);
               }

               filterBuffer.append(") ");
            }

         }
      }
   }

   public static class LeafNode extends TreeNode {
      public String keyName;
      public Operator operator;
      public Object value;
      public boolean isReverseOrder = false;
      private static final String PARAM_PREFIX = "hive_filter_param_";
      private static final Set TABLE_FILTER_OPS;

      protected void accept(TreeVisitor visitor) throws MetaException {
         visitor.visit(this);
      }

      public void generateJDOFilter(Configuration conf, Table table, Map params, FilterBuilder filterBuilder) throws MetaException {
         if (table != null) {
            this.generateJDOFilterOverPartitions(conf, table, params, filterBuilder);
         } else {
            this.generateJDOFilterOverTables(params, filterBuilder);
         }

      }

      private void generateJDOFilterOverTables(Map params, FilterBuilder filterBuilder) throws MetaException {
         if (this.keyName.equals("hive_filter_field_owner__")) {
            this.keyName = "this.owner";
         } else if (this.keyName.equals("hive_filter_field_last_access__")) {
            if (this.operator == ExpressionTree.Operator.LIKE) {
               filterBuilder.setError("Like is not supported for HIVE_FILTER_FIELD_LAST_ACCESS");
               return;
            }

            this.keyName = "this.lastAccessTime";
         } else {
            if (!this.keyName.startsWith("hive_filter_field_params__")) {
               filterBuilder.setError("Invalid key name in filter.  Use constants from org.apache.hadoop.hive.metastore.api");
               return;
            }

            if (!TABLE_FILTER_OPS.contains(this.operator)) {
               filterBuilder.setError("Only " + TABLE_FILTER_OPS + " are supported operators for HIVE_FILTER_FIELD_PARAMS");
               return;
            }

            String paramKeyName = this.keyName.substring("hive_filter_field_params__".length());
            this.keyName = "this.parameters.get(\"" + paramKeyName + "\")";
            this.value = this.value.toString();
         }

         this.generateJDOFilterGeneral(params, filterBuilder);
      }

      private void generateJDOFilterGeneral(Map params, FilterBuilder filterBuilder) throws MetaException {
         String paramName = "hive_filter_param_" + params.size();
         params.put(paramName, this.value);
         if (this.isReverseOrder) {
            if (this.operator == ExpressionTree.Operator.LIKE) {
               filterBuilder.setError("Value should be on the RHS for LIKE operator : Key <" + this.keyName + ">");
            } else {
               filterBuilder.append(paramName + " " + this.operator.getJdoOp() + " " + this.keyName);
            }
         } else if (this.operator == ExpressionTree.Operator.LIKE) {
            filterBuilder.append(" " + this.keyName + "." + this.operator.getJdoOp() + "(" + paramName + ") ");
         } else {
            filterBuilder.append(" " + this.keyName + " " + this.operator.getJdoOp() + " " + paramName);
         }

      }

      private void generateJDOFilterOverPartitions(Configuration conf, Table table, Map params, FilterBuilder filterBuilder) throws MetaException {
         int partitionColumnCount = table.getPartitionKeys().size();
         int partitionColumnIndex = this.getPartColIndexForFilter(table, filterBuilder);
         if (!filterBuilder.hasError()) {
            boolean canPushDownIntegral = HiveConf.getBoolVar(conf, ConfVars.METASTORE_INTEGER_JDO_PUSHDOWN);
            String valueAsString = this.getJdoFilterPushdownParam(table, partitionColumnIndex, filterBuilder, canPushDownIntegral);
            if (!filterBuilder.hasError()) {
               String paramName = "hive_filter_param_" + params.size();
               params.put(paramName, valueAsString);
               boolean isOpEquals = this.operator == ExpressionTree.Operator.EQUALS;
               if (!isOpEquals && this.operator != ExpressionTree.Operator.NOTEQUALS && this.operator != ExpressionTree.Operator.NOTEQUALS2) {
                  String valString = "values.get(" + partitionColumnIndex + ")";
                  if (this.operator == ExpressionTree.Operator.LIKE) {
                     if (this.isReverseOrder) {
                        filterBuilder.setError("Value should be on the RHS for LIKE operator : Key <" + this.keyName + ">");
                     }

                     filterBuilder.append(" " + valString + "." + this.operator.getJdoOp() + "(" + paramName + ") ");
                  } else {
                     filterBuilder.append(this.isReverseOrder ? paramName + " " + this.operator.getJdoOp() + " " + valString : " " + valString + " " + this.operator.getJdoOp() + " " + paramName);
                  }

               } else {
                  ExpressionTree.makeFilterForEquals(this.keyName, valueAsString, paramName, params, partitionColumnIndex, partitionColumnCount, isOpEquals, filterBuilder);
               }
            }
         }
      }

      public boolean canJdoUseStringsWithIntegral() {
         return this.operator == ExpressionTree.Operator.EQUALS || this.operator == ExpressionTree.Operator.NOTEQUALS || this.operator == ExpressionTree.Operator.NOTEQUALS2;
      }

      public int getPartColIndexForFilter(Table table, FilterBuilder filterBuilder) throws MetaException {
         assert table.getPartitionKeys().size() > 0;

         int partitionColumnIndex;
         for(partitionColumnIndex = 0; partitionColumnIndex < table.getPartitionKeys().size() && !((FieldSchema)table.getPartitionKeys().get(partitionColumnIndex)).getName().equalsIgnoreCase(this.keyName); ++partitionColumnIndex) {
         }

         if (partitionColumnIndex == table.getPartitionKeys().size()) {
            filterBuilder.setError("Specified key <" + this.keyName + "> is not a partitioning key for the table");
            return -1;
         } else {
            return partitionColumnIndex;
         }
      }

      private String getJdoFilterPushdownParam(Table table, int partColIndex, FilterBuilder filterBuilder, boolean canPushDownIntegral) throws MetaException {
         boolean isIntegralSupported = canPushDownIntegral && this.canJdoUseStringsWithIntegral();
         String colType = ((FieldSchema)table.getPartitionKeys().get(partColIndex)).getType();
         if (colType.equals("string") || isIntegralSupported && serdeConstants.IntegralTypes.contains(colType)) {
            Object val = this.value;
            if (this.value instanceof Date) {
               val = ((DateFormat)HiveMetaStore.PARTITION_DATE_FORMAT.get()).format((Date)this.value);
            }

            boolean isStringValue = val instanceof String;
            if (isStringValue || isIntegralSupported && val instanceof Long) {
               return isStringValue ? (String)val : Long.toString((Long)val);
            } else {
               filterBuilder.setError("Filtering is supported only on partition keys of type string" + (isIntegralSupported ? ", or integral types" : ""));
               return null;
            }
         } else {
            filterBuilder.setError("Filtering is supported only on partition keys of type string" + (isIntegralSupported ? ", or integral types" : ""));
            return null;
         }
      }

      static {
         TABLE_FILTER_OPS = Sets.newHashSet(new Operator[]{ExpressionTree.Operator.EQUALS, ExpressionTree.Operator.NOTEQUALS, ExpressionTree.Operator.NOTEQUALS2, ExpressionTree.Operator.LIKE});
      }
   }

   public static class ANTLRNoCaseStringStream extends ANTLRStringStream {
      public ANTLRNoCaseStringStream(String input) {
         super(input);
      }

      public int LA(int i) {
         int returnChar = super.LA(i);
         if (returnChar == -1) {
            return returnChar;
         } else {
            return returnChar == 0 ? returnChar : Character.toUpperCase((char)returnChar);
         }
      }
   }
}
