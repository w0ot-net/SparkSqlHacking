package org.apache.derby.impl.sql.compile;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.transaction.TransactionControl;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.DeferModification;
import org.apache.derby.vti.RestrictedVTI;
import org.apache.derby.vti.Restriction;
import org.apache.derby.vti.VTICosting;
import org.apache.derby.vti.VTIEnvironment;

class FromVTI extends FromTable implements VTIEnvironment {
   JBitSet correlationMap;
   JBitSet dependencyMap;
   MethodCallNode methodCall;
   TableName exposedName;
   SubqueryList subqueryList;
   boolean implementsVTICosting;
   boolean optimized;
   boolean materializable;
   boolean isTarget;
   boolean isDerbyStyleTableFunction;
   boolean isRestrictedTableFunction;
   ResultSet rs;
   private final FormatableHashtable compileTimeConstants = new FormatableHashtable();
   protected int numVTICols;
   private PredicateList restrictionList;
   double estimatedCost = (double)100000.0F;
   double estimatedRowCount = (double)10000.0F;
   boolean supportsMultipleInstantiations = true;
   boolean vtiCosted;
   protected boolean version2;
   private boolean implementsPushable;
   private PreparedStatement ps;
   private JavaValueNode[] methodParms;
   private boolean controlsDeferral;
   private int resultSetType = 1003;
   private String[] projectedColumnNames;
   private Restriction vtiRestriction;
   private ArrayList outerFromLists = new ArrayList();
   private HashMap argSources = new HashMap();

   FromVTI(MethodCallNode var1, String var2, ResultColumnList var3, Properties var4, ContextManager var5) throws StandardException {
      super(var2, var4, var5);
      this.constructorMinion(var1, var3, this.makeTableName((String)null, var2));
   }

   FromVTI(MethodCallNode var1, String var2, ResultColumnList var3, Properties var4, TableName var5, ContextManager var6) {
      super(var2, var4, var6);
      this.constructorMinion(var1, var3, var5);
   }

   private void constructorMinion(MethodCallNode var1, ResultColumnList var2, TableName var3) {
      this.methodCall = var1;
      this.setResultColumns(var2);
      this.subqueryList = new SubqueryList(this.getContextManager());
      this.exposedName = var3;
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      this.setCostEstimate(this.getCostEstimate(var4));
      if (this.implementsVTICosting && !this.vtiCosted) {
         try {
            VTICosting var6 = this.getVTICosting();
            this.estimatedCost = var6.getEstimatedCostPerInstantiation(this);
            this.estimatedRowCount = var6.getEstimatedRowCount(this);
            this.supportsMultipleInstantiations = var6.supportsMultipleInstantiations(this);
            if (this.ps != null) {
               this.ps.close();
               this.ps = null;
            }

            if (this.rs != null) {
               this.rs.close();
               this.rs = null;
            }
         } catch (SQLException var7) {
            throw StandardException.unexpectedUserException(var7);
         }

         this.vtiCosted = true;
      }

      this.getCostEstimate().setCost(this.estimatedCost, this.estimatedRowCount, this.estimatedRowCount);
      if (this.getCurrentAccessPath().getJoinStrategy().multiplyBaseCostByOuterRows()) {
         this.getCostEstimate().multiply(var3.rowCount(), this.getCostEstimate());
      }

      if (!this.optimized) {
         this.subqueryList.optimize(var4.getDataDictionary(), this.getCostEstimate().rowCount());
         this.subqueryList.modifyAccessPaths();
      }

      this.optimized = true;
      return this.getCostEstimate();
   }

   public boolean legalJoinOrder(JBitSet var1) {
      var1.or(this.correlationMap);
      return var1.contains(this.dependencyMap);
   }

   public boolean isMaterializable() {
      return this.materializable;
   }

   public boolean supportsMultipleInstantiations() {
      return this.supportsMultipleInstantiations;
   }

   public boolean isDerbyStyleTableFunction() {
      return this.isDerbyStyleTableFunction;
   }

   void adjustForSortElimination() {
   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      if (this.rs != null) {
         try {
            this.rs.close();
            this.rs = null;
         } catch (Throwable var3) {
            throw StandardException.unexpectedUserException(var3);
         }
      }

      return super.modifyAccessPath(var1);
   }

   public void addOuterFromList(FromList var1) {
      this.outerFromLists.add(var1);
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      if (!this.implementsPushable) {
         return false;
      } else if (!var1.getReferencedMap().hasSingleBitSet()) {
         return false;
      } else {
         if (this.restrictionList == null) {
            this.restrictionList = new PredicateList(this.getContextManager());
         }

         this.restrictionList.addPredicate((Predicate)var1);
         return true;
      }
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   boolean isConstructor() {
      return this.methodCall instanceof NewInvocationNode;
   }

   final MethodCallNode getMethodCall() {
      return this.methodCall;
   }

   String getExposedName() {
      return this.correlationName;
   }

   public TableName getExposedTableName() {
      return this.exposedName;
   }

   void setTarget() {
      this.isTarget = true;
      this.version2 = true;
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      if (this.tableNumber == -1) {
         this.tableNumber = this.getCompilerContext().getNextTableNumber();
      }

      return this;
   }

   String getVTIName() {
      return this.methodCall.getJavaClassName();
   }

   ResultSetNode bindVTITables(FromList var1) throws StandardException {
      ResultColumnList var2 = this.getResultColumns();
      LanguageConnectionContext var3 = this.getLanguageConnectionContext();
      ArrayList var4 = new ArrayList();
      this.methodCall.bindExpression(var1, this.subqueryList, var4);
      this.methodParms = this.methodCall.getMethodParms();
      RoutineAliasInfo var5 = this.methodCall.getRoutineInfo();
      if (var5 != null && var5.getReturnType().isRowMultiSet() && var5.getParameterStyle() == 1) {
         this.isDerbyStyleTableFunction = true;
      }

      if (this.isDerbyStyleTableFunction) {
         Method var6 = (Method)this.methodCall.getResolvedMethod();
         this.isRestrictedTableFunction = RestrictedVTI.class.isAssignableFrom(var6.getReturnType());
      }

      if (this.isConstructor()) {
         NewInvocationNode var10 = (NewInvocationNode)this.methodCall;
         if (!var10.assignableTo("java.sql.PreparedStatement")) {
            if (this.version2) {
               throw StandardException.newException("42X08", new Object[]{this.getVTIName(), "java.sql.PreparedStatement"});
            }

            if (!var10.assignableTo("java.sql.ResultSet")) {
               throw StandardException.newException("42X08", new Object[]{this.getVTIName(), "java.sql.ResultSet"});
            }
         } else {
            this.version2 = true;
         }

         if (this.version2) {
            this.implementsPushable = var10.assignableTo("org.apache.derby.vti.IQualifyable");
         }

         this.implementsVTICosting = var10.assignableTo("org.apache.derby.vti.VTICosting");
      }

      if (this.isDerbyStyleTableFunction) {
         this.implementsVTICosting = this.implementsDerbyStyleVTICosting(this.methodCall.getJavaClassName());
      }

      UUID var11;
      if (this.isConstructor() && (var11 = this.getSpecialTriggerVTITableName(var3, this.methodCall.getJavaClassName())) != null) {
         TableDescriptor var12 = this.getDataDictionary().getTableDescriptor(var11);
         this.setResultColumns(this.genResultColList(var12));
         this.vtiCosted = true;
         this.estimatedCost = (double)50.0F;
         this.estimatedRowCount = (double)5.0F;
         this.supportsMultipleInstantiations = true;
      } else {
         this.setResultColumns(new ResultColumnList(this.getContextManager()));
         if (this.isDerbyStyleTableFunction) {
            this.createResultColumnsForTableFunction(var5.getReturnType());
         } else {
            ResultSetMetaData var7 = this.getResultSetMetaData();
            if (var7 == null) {
               throw StandardException.newException("42X43", new Object[]{this.getVTIName()});
            }

            try {
               this.numVTICols = var7.getColumnCount();
            } catch (SQLException var9) {
               this.numVTICols = 0;
            }

            this.getResultColumns().createListFromResultSetMetaData(var7, this.exposedName, this.getVTIName());
         }
      }

      this.numVTICols = this.getResultColumns().size();
      if (var2 != null) {
         this.getResultColumns().propagateDCLInfo(var2, this.correlationName);
      }

      return this;
   }

   ResultSetMetaData getResultSetMetaData() throws StandardException {
      Object var1 = null;

      try {
         ResultSetMetaData var7;
         if (this.version2) {
            this.ps = (PreparedStatement)this.getNewInstance();
            if (this.ps.getResultSetConcurrency() != 1008) {
               throw StandardException.newException("42Z90", new Object[]{this.getVTIName()});
            }

            var7 = this.ps.getMetaData();
            this.controlsDeferral = this.ps instanceof DeferModification;

            try {
               this.resultSetType = this.ps.getResultSetType();
            } catch (SQLException var3) {
            } catch (AbstractMethodError var4) {
            } catch (NoSuchMethodError var5) {
            }

            if (!this.implementsVTICosting) {
               this.ps.close();
               this.ps = null;
            }
         } else {
            this.rs = (ResultSet)this.getNewInstance();
            var7 = this.rs.getMetaData();
            if (!this.implementsVTICosting) {
               this.rs.close();
               this.rs = null;
            }
         }

         return var7;
      } catch (Throwable var6) {
         throw StandardException.unexpectedUserException(var6);
      }
   }

   private Object getNewInstance() throws StandardException {
      NewInvocationNode var1 = (NewInvocationNode)this.methodCall;
      Class[] var2 = var1.getMethodParameterClasses();
      Object[] var3;
      if (var2 != null) {
         var3 = new Object[var2.length];

         for(int var4 = 0; var4 < var2.length; ++var4) {
            Class var5 = var2[var4];
            var3[var4] = this.methodParms[var4].getConstantValueAsObject();
            if (var3[var4] != null && var5.isPrimitive()) {
               if (var5.equals(Short.TYPE)) {
                  var3[var4] = ((Integer)var3[var4]).shortValue();
               } else if (var5.equals(Byte.TYPE)) {
                  var3[var4] = ((Integer)var3[var4]).byteValue();
               }
            }

            if (var3[var4] == null && var5.isPrimitive()) {
               if (var5.equals(Integer.TYPE)) {
                  var3[var4] = 0;
               } else if (var5.equals(Short.TYPE)) {
                  var3[var4] = Short.valueOf((short)0);
               } else if (var5.equals(Byte.TYPE)) {
                  var3[var4] = 0;
               } else if (var5.equals(Long.TYPE)) {
                  var3[var4] = 0L;
               } else if (var5.equals(Float.TYPE)) {
                  var3[var4] = 0.0F;
               } else if (var5.equals(Double.TYPE)) {
                  var3[var4] = (double)0.0F;
               } else if (var5.equals(Boolean.TYPE)) {
                  var3[var4] = Boolean.FALSE;
               } else if (var5.equals(Character.TYPE)) {
                  var3[var4] = '\u0000';
               }
            }
         }
      } else {
         var2 = new Class[0];
         var3 = new Object[0];
      }

      try {
         ClassInspector var8 = this.getClassFactory().getClassInspector();
         String var9 = this.methodCall.getJavaClassName();
         Constructor var6 = var8.getClass(var9).getConstructor(var2);
         return var6.newInstance(var3);
      } catch (Throwable var7) {
         throw StandardException.unexpectedUserException(var7);
      }
   }

   public DeferModification getDeferralControl() throws StandardException {
      if (!this.controlsDeferral) {
         return null;
      } else {
         try {
            return (DeferModification)this.getNewInstance();
         } catch (Throwable var2) {
            throw StandardException.unexpectedUserException(var2);
         }
      }
   }

   public int getResultSetType() {
      return this.resultSetType;
   }

   void bindExpressions(FromList var1) throws StandardException {
      this.materializable = this.methodCall.areParametersQueryInvariant();
      ArrayList var2 = null;

      for(ColumnReference var4 : this.getNodesFromParameters(ColumnReference.class)) {
         boolean var5 = !var4.getCorrelated();
         if (var4.getCorrelated()) {
            for(int var6 = 0; var6 < this.outerFromLists.size(); ++var6) {
               FromTable var7 = this.columnInFromList((FromList)this.outerFromLists.get(var6), var4);
               if (var7 != null) {
                  var5 = true;
                  break;
               }
            }
         } else {
            FromTable var8 = this.columnInFromList(var1, var4);
            if (var8 != null && !this.isDerbyStyleTableFunction && !(var8 instanceof FromVTI)) {
               break;
            }
         }

         if (var5) {
            throw StandardException.newException("42ZB7", new Object[]{var4.getSQLColumnName()});
         }

         if (var4.getTableNumber() == -1) {
            if (var2 == null) {
               var2 = new ArrayList();
            }

            var4.bindExpression(var1, this.subqueryList, var2);
         }
      }

   }

   private FromTable columnInFromList(FromList var1, ColumnReference var2) throws StandardException {
      int var3 = var2.getTableNumber();

      for(int var4 = 0; var4 < var1.size(); ++var4) {
         FromTable var5 = (FromTable)var1.elementAt(var4);
         if (var3 == var5.getTableNumber()) {
            this.argSources.put(var5.getTableNumber(), var5);
            return var5;
         }
      }

      return null;
   }

   List getNodesFromParameters(Class var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(var1);
      this.methodCall.accept(var2);
      return var2.getList();
   }

   ResultColumnList getAllResultColumns(TableName var1) throws StandardException {
      TableName var2;
      if (var1 != null) {
         var2 = this.makeTableName(var1.getSchemaName(), this.correlationName);
      } else {
         var2 = this.makeTableName((String)null, this.correlationName);
      }

      if (var1 != null && !var1.equals(var2)) {
         return null;
      } else {
         ContextManager var3 = this.getContextManager();
         ResultColumnList var4 = new ResultColumnList(var3);

         for(ResultColumn var6 : this.getResultColumns()) {
            if (!var6.isGenerated()) {
               ResultColumn var7 = new ResultColumn(var6.getName(), new ColumnReference(var6.getName(), this.exposedName, var3), var3);
               var4.addResultColumn(var7);
            }
         }

         return var4;
      }
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      if (this.getResultColumns() == null) {
         return null;
      } else {
         ResultColumn var2 = null;
         TableName var3 = var1.getQualifiedTableName();
         if (var3 == null || var3.equals(this.exposedName)) {
            var2 = this.getResultColumns().getResultColumn(var1.getColumnName());
            if (var2 != null) {
               var1.setTableNumber(this.tableNumber);
               var1.setColumnNumber(var2.getColumnPosition());
            }
         }

         return var2;
      }
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.methodCall.preprocess(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()), new SubqueryList(this.getContextManager()), new PredicateList(this.getContextManager()));
      this.setReferencedTableMap(new JBitSet(var1));
      this.getReferencedTableMap().set(this.tableNumber);
      this.dependencyMap = new JBitSet(var1);
      this.methodCall.categorize(this.dependencyMap, false);
      this.dependencyMap.clear(this.tableNumber);
      this.correlationMap = new JBitSet(var1);
      this.methodCall.getCorrelationTables(this.correlationMap);
      return this.genProjectRestrict(var1);
   }

   protected ResultSetNode genProjectRestrict(int var1) throws StandardException {
      ResultColumnList var2 = this.getResultColumns();
      this.setResultColumns(this.getResultColumns().copyListAndObjects());
      var2.genVirtualColumnNodes(this, this.getResultColumns(), false);
      var2.doProjection();
      return new ProjectRestrictNode(this, var2, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, this.tableProperties, this.getContextManager());
   }

   boolean performMaterialization(JBitSet var1) throws StandardException {
      return var1.getFirstSetBit() != -1 && !var1.hasSingleBitSet() && !this.getTrulyTheBestAccessPath().getJoinStrategy().doesMaterialization() && this.isMaterializable() && !this.supportsMultipleInstantiations;
   }

   void computeProjectionAndRestriction(PredicateList var1) throws StandardException {
      if (this.isRestrictedTableFunction) {
         this.computeRestriction(var1, this.computeProjection());
      }
   }

   private HashMap computeProjection() throws StandardException {
      HashMap var1 = new HashMap();
      ResultColumnList var2 = this.getResultColumns();
      int var3 = var2.size();
      this.projectedColumnNames = new String[var3];

      for(int var4 = 0; var4 < var3; ++var4) {
         ResultColumn var5 = var2.getResultColumn(var4 + 1);
         String var6 = var5.getName();
         if (var5.isReferenced()) {
            String var7 = var5.getBaseColumnNode().getColumnName();
            this.projectedColumnNames[var4] = var7;
            var1.put(var6, var7);
         }
      }

      return var1;
   }

   private void computeRestriction(PredicateList var1, HashMap var2) throws StandardException {
      if (var1 != null) {
         for(Predicate var4 : var1) {
            if (this.canBePushedDown(var4)) {
               Restriction var5 = this.makeRestriction(var4.getAndNode(), var2);
               if (var5 == null) {
                  this.vtiRestriction = null;
                  return;
               }

               if (this.vtiRestriction == null) {
                  this.vtiRestriction = var5;
               } else {
                  this.vtiRestriction = new Restriction.AND(this.vtiRestriction, var5);
               }
            }
         }

      }
   }

   private boolean canBePushedDown(Predicate var1) throws StandardException {
      JBitSet var2 = var1.getReferencedSet();
      return var1.isQualifier() && var2 != null && var2.hasSingleBitSet() && var2.get(this.getTableNumber());
   }

   private Restriction makeRestriction(ValueNode var1, HashMap var2) throws StandardException {
      if (var1 instanceof AndNode var6) {
         if (var6.getRightOperand().isBooleanTrue()) {
            return this.makeRestriction(var6.getLeftOperand(), var2);
         } else {
            Restriction var7 = this.makeRestriction(var6.getLeftOperand(), var2);
            Restriction var8 = this.makeRestriction(var6.getRightOperand(), var2);
            return var7 != null && var8 != null ? new Restriction.AND(var7, var8) : null;
         }
      } else if (var1 instanceof OrNode var3) {
         if (var3.getRightOperand().isBooleanFalse()) {
            return this.makeRestriction(var3.getLeftOperand(), var2);
         } else {
            Restriction var4 = this.makeRestriction(var3.getLeftOperand(), var2);
            Restriction var5 = this.makeRestriction(var3.getRightOperand(), var2);
            return var4 != null && var5 != null ? new Restriction.OR(var4, var5) : null;
         }
      } else if (var1 instanceof BinaryRelationalOperatorNode) {
         return this.makeLeafRestriction((BinaryRelationalOperatorNode)var1, var2);
      } else {
         return var1 instanceof IsNullNode ? this.makeIsNullRestriction((IsNullNode)var1, var2) : this.iAmConfused(var1);
      }
   }

   private Restriction makeLeafRestriction(BinaryRelationalOperatorNode var1, HashMap var2) throws StandardException {
      int var3 = var1.getOperator();
      ColumnReference var4;
      ValueNode var5;
      if (var1.getLeftOperand() instanceof ColumnReference) {
         var4 = (ColumnReference)var1.getLeftOperand();
         var5 = var1.getRightOperand();
      } else {
         if (!(var1.getRightOperand() instanceof ColumnReference)) {
            return this.iAmConfused(var1);
         }

         var4 = (ColumnReference)var1.getRightOperand();
         var5 = var1.getLeftOperand();
         var3 = this.flipOperator(var3);
      }

      int var6 = this.mapOperator(var3);
      if (var6 < 0) {
         return this.iAmConfused(var1);
      } else {
         String var7 = (String)var2.get(var4.getColumnName());
         Object var8 = this.squeezeConstantValue(var5);
         return (Restriction)(var7 != null && var8 != null ? new Restriction.ColumnQualifier(var7, var6, var8) : this.iAmConfused(var1));
      }
   }

   private Restriction makeIsNullRestriction(IsNullNode var1, HashMap var2) throws StandardException {
      ColumnReference var3 = (ColumnReference)var1.getOperand();
      int var4 = this.mapOperator(var1.getOperator());
      if (var4 < 0) {
         return this.iAmConfused(var1);
      } else if (var4 != 5 && var4 != 6) {
         return this.iAmConfused(var1);
      } else {
         String var5 = (String)var2.get(var3.getColumnName());
         return (Restriction)(var5 == null ? this.iAmConfused(var1) : new Restriction.ColumnQualifier(var5, var4, (Object)null));
      }
   }

   private Restriction iAmConfused(ValueNode var1) throws StandardException {
      return null;
   }

   private int flipOperator(int var1) throws StandardException {
      switch (var1) {
         case 1:
            return 1;
         case 2:
            return 2;
         case 3:
            return 5;
         case 4:
            return 6;
         case 5:
            return 3;
         case 6:
            return 4;
         case 7:
         case 8:
         default:
            return -1;
      }
   }

   private int mapOperator(int var1) throws StandardException {
      switch (var1) {
         case 1 -> {
            return 1;
         }
         case 2 -> {
            return 7;
         }
         case 3 -> {
            return 3;
         }
         case 4 -> {
            return 4;
         }
         case 5 -> {
            return 0;
         }
         case 6 -> {
            return 2;
         }
         case 7 -> {
            return 5;
         }
         case 8 -> {
            return 6;
         }
         default -> {
            return -1;
         }
      }
   }

   private Object squeezeConstantValue(ValueNode var1) throws StandardException {
      if (var1 instanceof ParameterNode) {
         return new int[]{((ParameterNode)var1).getParameterNumber()};
      } else {
         return var1 instanceof ConstantNode ? ((ConstantNode)var1).getValue().getObject() : this.iAmConfused(var1);
      }
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.isRestrictedTableFunction && this.projectedColumnNames == null) {
         this.computeProjection();
      }

      RemapCRsVisitor var3 = new RemapCRsVisitor(true);
      this.methodCall.accept(var3);
      this.remapBaseTableColumns();
      this.assignResultSetNumber();
      var1.pushGetResultSetFactoryExpression(var2);
      int var4 = this.getScanArguments(var1, var2);
      var2.callMethod((short)185, (String)null, "getVTIResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", var4);
   }

   private void remapBaseTableColumns() throws StandardException {
      for(ColumnReference var2 : this.getNodesFromParameters(ColumnReference.class)) {
         FromTable var3 = (FromTable)this.argSources.get(var2.getTableNumber());
         if (var3 != null) {
            ResultColumnList var4 = var3.getResultColumns();
            if (var4 != null) {
               ResultColumn var5 = var4.getResultColumn(var2.getColumnName());
               if (var5 != null) {
                  var2.setSource(var5);
               }
            }
         }
      }

   }

   private int getScanArguments(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.getResultColumns().size();
      FormatableBitSet var4 = new FormatableBitSet(var3);
      int var5 = -1;
      int var6 = 0;
      this.setCostEstimate(this.getFinalCostEstimate());

      for(int var7 = 0; var7 < var3; ++var7) {
         ResultColumn var8 = (ResultColumn)this.getResultColumns().elementAt(var7);
         if (var8.isReferenced()) {
            var4.set(var7);
            ++var6;
         }
      }

      if (var6 != this.numVTICols) {
         var5 = var1.addItem(var4);
      }

      int var11 = var1.addItem(this.compileTimeConstants);
      var1.pushThisAsActivation(var2);
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate()));
      boolean var12 = this.version2 && this.getNodesFromParameters(ParameterNode.class).isEmpty() && this.getNodesFromParameters(ColumnReference.class).isEmpty();
      var2.push(this.getResultSetNumber());
      this.generateConstructor(var1, var2, var12);
      var2.push(this.methodCall.getJavaClassName());
      if (this.restrictionList != null) {
         this.restrictionList.generateQualifiers(var1, var2, this, true);
      } else {
         var2.pushNull("org.apache.derby.iapi.store.access.Qualifier[][]");
      }

      var2.push(var5);
      var2.push(this.version2);
      var2.push(var12);
      var2.push(var11);
      var2.push(this.isTarget);
      var2.push(this.getCompilerContext().getScanIsolationLevel());
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(this.isDerbyStyleTableFunction);
      int var9 = -1;
      if (this.isDerbyStyleTableFunction) {
         var9 = var1.addItem(this.methodCall.getRoutineInfo().getReturnType());
      }

      var2.push(var9);
      var2.push(this.storeObjectInPS(var1, this.projectedColumnNames));
      var2.push(this.storeObjectInPS(var1, this.vtiRestriction));
      TableName var10 = this.methodCall.getFullName();
      var2.push(var10 == null ? "" : var10.getSchemaName());
      var2.push(var10 == null ? "" : var10.getTableName());
      return 20;
   }

   private int storeObjectInPS(ActivationClassBuilder var1, Object var2) throws StandardException {
      return var2 == null ? -1 : var1.addItem(var2);
   }

   private void generateConstructor(ActivationClassBuilder var1, MethodBuilder var2, boolean var3) throws StandardException {
      String var4 = this.version2 ? "java.sql.PreparedStatement" : "java.sql.ResultSet";
      MethodBuilder var5 = var1.newGeneratedFun(var4, 1);
      var5.addThrownException("java.lang.Exception");
      LocalField var6 = var3 ? var1.newFieldDeclaration(2, "java.sql.PreparedStatement") : null;
      if (var3) {
         var5.getField(var6);
         var5.conditionalIfNull();
      }

      this.methodCall.generateExpression(var1, var5);
      var5.upCast(var4);
      if (var3) {
         var5.putField(var6);
         var5.startElseCode();
         var5.getField(var6);
         var5.completeConditional();
      }

      var5.methodReturn();
      var5.complete();
      var1.pushMethodReference(var2, var5);
      if (var3) {
         MethodBuilder var7 = var1.getCloseActivationMethod();
         var7.getField(var6);
         var7.conditionalIfNull();
         var7.push((int)0);
         var7.startElseCode();
         var7.getField(var6);
         var7.callMethod((short)185, "java.sql.Statement", "close", "void", 0);
         var7.push((int)0);
         var7.completeConditional();
         var7.endStatement();
      }

   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return !var2 && var1.equals(this.methodCall.getJavaClassName());
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.methodCall != null) {
         this.methodCall = (MethodCallNode)this.methodCall.accept(var1);
      }

      if (this.exposedName != null) {
         this.exposedName = (TableName)this.exposedName.accept(var1);
      }

   }

   private UUID getSpecialTriggerVTITableName(LanguageConnectionContext var1, String var2) throws StandardException {
      if (!var2.equals("org.apache.derby.catalog.TriggerNewTransitionRows") && !var2.equals("org.apache.derby.catalog.TriggerOldTransitionRows")) {
         return (UUID)null;
      } else if (var1.getTriggerTable() != null) {
         return var1.getTriggerTable().getUUID();
      } else if (var1.getTriggerExecutionContext() != null) {
         return var1.getTriggerExecutionContext().getTargetTableId();
      } else {
         throw StandardException.newException("42Y45", new Object[]{var2});
      }
   }

   private ResultColumnList genResultColList(TableDescriptor var1) throws StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());
      ColumnDescriptorList var3 = var1.getColumnDescriptorList();
      int var4 = var3.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ColumnDescriptor var6 = var3.elementAt(var5);
         BaseColumnNode var7 = new BaseColumnNode(var6.getColumnName(), this.exposedName, var6.getType(), this.getContextManager());
         ResultColumn var8 = new ResultColumn(var6, var7, this.getContextManager());
         var2.addResultColumn(var8);
      }

      return var2;
   }

   boolean needsSpecialRCLBinding() {
      return true;
   }

   boolean isUpdatableCursor() throws StandardException {
      return true;
   }

   public final boolean isCompileTime() {
      return true;
   }

   public String getOriginalSQL() {
      return this.getCompilerContext().getParser().getSQLtext();
   }

   public final int getStatementIsolationLevel() {
      return TransactionControl.jdbcIsolationLevel(this.getCompilerContext().getScanIsolationLevel());
   }

   public void setSharedState(String var1, Serializable var2) {
      if (var1 != null) {
         this.compileTimeConstants.put(var1, var2);
      }
   }

   public Object getSharedState(String var1) {
      return var1 == null ? null : this.compileTimeConstants.get(var1);
   }

   private void createResultColumnsForTableFunction(TypeDescriptor var1) throws StandardException {
      String[] var2 = var1.getRowColumnNames();
      TypeDescriptor[] var3 = var1.getRowTypes();

      for(int var4 = 0; var4 < var2.length; ++var4) {
         String var5 = var2[var4];
         DataTypeDescriptor var6 = DataTypeDescriptor.getType(var3[var4]);
         ResultColumn var7 = this.getResultColumns().addColumn(this.exposedName, var5, var6);
         ColumnDescriptor var8 = new ColumnDescriptor(var5, var4 + 1, var6, (DataValueDescriptor)null, (DefaultInfo)null, (UUID)null, (UUID)null, 0L, 0L, 0L, false);
         var7.setColumnDescriptor((TableDescriptor)null, var8);
      }

   }

   private boolean implementsDerbyStyleVTICosting(String var1) throws StandardException {
      Object var2 = null;
      Class var3 = this.lookupClass(var1);
      Class var4 = this.lookupClass(VTICosting.class.getName());

      try {
         if (!var4.isAssignableFrom(var3)) {
            return false;
         }
      } catch (Throwable var7) {
         throw StandardException.unexpectedUserException(var7);
      }

      try {
         var8 = var3.getConstructor();
      } catch (Throwable var6) {
         throw StandardException.newException("42ZB5", var6, new Object[]{var1});
      }

      if (Modifier.isPublic(var8.getModifiers())) {
         return true;
      } else {
         throw StandardException.newException("42ZB5", new Object[]{var1});
      }
   }

   private VTICosting getVTICosting() throws StandardException {
      if (!this.isDerbyStyleTableFunction) {
         return this.version2 ? (VTICosting)this.ps : (VTICosting)this.rs;
      } else {
         String var1 = this.methodCall.getJavaClassName();
         Class var2 = this.lookupClass(var1);

         try {
            Constructor var3 = var2.getConstructor();
            VTICosting var4 = (VTICosting)var3.newInstance((Object[])null);
            return var4;
         } catch (Throwable var5) {
            throw StandardException.unexpectedUserException(var5);
         }
      }
   }

   private Class lookupClass(String var1) throws StandardException {
      try {
         return this.getClassFactory().getClassInspector().getClass(var1);
      } catch (ClassNotFoundException var3) {
         throw StandardException.unexpectedUserException(var3);
      }
   }
}
