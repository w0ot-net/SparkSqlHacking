package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.AggregateAliasInfo;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class AggregateNode extends UnaryOperatorNode {
   private static BuiltinAggDescriptor[] BUILTIN_MODERN_AGGS;
   private boolean distinct;
   private AggregateDefinition uad;
   private TableName userAggregateName;
   private StringBuffer aggregatorClassName;
   private String aggregateDefinitionClassName;
   private Class aggregateDefinitionClass;
   private ClassInspector classInspector;
   private String aggregateName;
   private ResultColumn generatedRC;
   private ColumnReference generatedRef;

   AggregateNode(ValueNode var1, UserAggregateDefinition var2, TableName var3, boolean var4, String var5, ContextManager var6) throws StandardException {
      this(var1, var3, var4, var5, var6);
      this.setUserDefinedAggregate(var2);
   }

   AggregateNode(ValueNode var1, TableName var2, boolean var3, String var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.aggregateName = var4;
      this.userAggregateName = var2;
      this.distinct = var3;
   }

   AggregateNode(ValueNode var1, Class var2, boolean var3, String var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.aggregateName = var4;
      this.aggregateDefinitionClass = var2;
      if (!this.aggregateDefinitionClass.equals(MaxMinAggregateDefinition.class)) {
         this.distinct = var3;
      }

      this.aggregateDefinitionClassName = this.aggregateDefinitionClass.getName();
   }

   private void setUserDefinedAggregate(UserAggregateDefinition var1) {
      this.uad = var1;
      this.aggregateDefinitionClass = this.uad.getClass();
      this.aggregateDefinitionClassName = this.aggregateDefinitionClass.getName();
   }

   ValueNode replaceAggregatesWithColumnReferences(ResultColumnList var1, int var2) throws StandardException {
      if (this.generatedRef == null) {
         CompilerContext var4 = this.getCompilerContext();
         String var3 = "SQLCol" + var4.getNextColumnNumber();
         this.generatedRC = new ResultColumn(var3, this, this.getContextManager());
         this.generatedRC.markGenerated();
         this.generatedRef = new ColumnReference(this.generatedRC.getName(), (TableName)null, this.getContextManager());
         this.generatedRef.setSource(this.generatedRC);
         this.generatedRef.setNestingLevel(0);
         this.generatedRef.setSourceLevel(0);
         if (var2 != -1) {
            this.generatedRef.setTableNumber(var2);
         }

         var1.addResultColumn(this.generatedRC);
         this.generatedRef.markGeneratedToReplaceAggregate();
      } else {
         var1.addResultColumn(this.generatedRC);
      }

      return this.generatedRef;
   }

   AggregateDefinition getAggregateDefinition() {
      return this.uad;
   }

   ResultColumn getGeneratedRC() {
      return this.generatedRC;
   }

   ColumnReference getGeneratedRef() {
      return this.generatedRef;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      DataDictionary var4 = this.getDataDictionary();
      DataTypeDescriptor var5 = null;
      ClassFactory var6 = this.getClassFactory();
      this.classInspector = var6.getClassInspector();
      boolean var7 = true;
      if (this.userAggregateName != null) {
         var7 = this.userAggregateName.getSchemaName() == null;
         this.userAggregateName.bind();
      }

      if (this.userAggregateName != null && this.uad == null) {
         String var8 = this.userAggregateName.getSchemaName();
         AliasDescriptor var9 = resolveAggregate(var4, this.getSchemaDescriptor(var8, true), this.userAggregateName.getTableName(), var7);
         if (var9 == null) {
            throw StandardException.newException("42X94", new Object[]{AliasDescriptor.getAliasType('G'), this.userAggregateName.getTableName()});
         }

         this.setUserDefinedAggregate(new UserAggregateDefinition(var9));
         this.aggregateName = var9.getJavaClassName();
      }

      this.instantiateAggDef();
      if (this.isUserDefinedAggregate()) {
         AliasDescriptor var11 = ((UserAggregateDefinition)this.uad).getAliasDescriptor();
         boolean var13 = "SYS".equals(var11.getSchemaName());
         if (this.distinct && var13) {
            throw StandardException.newException("42XAS", new Object[0]);
         }

         if (!var13) {
            this.getCompilerContext().createDependency(var11);
         }

         if (this.isPrivilegeCollectionRequired() && !var13) {
            this.getCompilerContext().addRequiredUsagePriv(var11);
         }
      }

      var3.add(this);
      CompilerContext var12 = this.getCompilerContext();
      if (this.operand != null) {
         int var14 = this.orReliability(16384);
         this.bindOperand(var1, var2, var3);
         var12.setReliability(var14);
         HasNodeVisitor var10 = new HasNodeVisitor(this.getClass(), ResultSetNode.class);
         this.operand.accept(var10);
         if (var10.hasNode()) {
            throw StandardException.newException("42Y33", new Object[]{this.getSQLName()});
         }

         SelectNode.checkNoWindowFunctions(this.operand, this.aggregateName);
         var5 = this.operand.getTypeServices();
         if (this.uad instanceof CountAggregateDefinition && !var5.isNullable()) {
            this.setOperator(this.aggregateName);
            this.setMethodName(this.aggregateName);
         }

         if (this.distinct && !this.operand.getTypeId().orderable(var6)) {
            throw StandardException.newException("X0X67.S", new Object[]{var5.getTypeId().getSQLTypeName()});
         }

         if (this.operand instanceof UntypedNullConstantNode) {
            throw StandardException.newException("42Y83", new Object[]{this.getSQLName()});
         }
      }

      this.aggregatorClassName = new StringBuffer();
      DataTypeDescriptor var15 = this.uad.getAggregator(var5, this.aggregatorClassName);
      if (var15 == null) {
         throw StandardException.newException("42Y22", new Object[]{this.getSQLName(), this.operand.getTypeId().getSQLTypeName()});
      } else {
         if (this.isUserDefinedAggregate()) {
            ValueNode var16 = ((UserAggregateDefinition)this.uad).castInputValue(this.operand, this.getContextManager());
            if (var16 != null) {
               this.operand = var16.bindExpression(var1, var2, var3);
            }
         }

         this.checkAggregatorClassName(this.aggregatorClassName.toString());
         this.setType(var15);
         return this;
      }
   }

   static AliasDescriptor resolveAggregate(DataDictionary var0, SchemaDescriptor var1, String var2, boolean var3) throws StandardException {
      AliasDescriptor var4 = resolveBuiltinAggregate(var0, var2, var3);
      if (var4 != null) {
         return var4;
      } else if (var1.getUUID() == null) {
         return null;
      } else {
         List var5 = var0.getRoutineList(var1.getUUID().toString(), var2, 'G');
         return var5.size() > 0 ? (AliasDescriptor)var5.get(0) : null;
      }
   }

   private static AliasDescriptor resolveBuiltinAggregate(DataDictionary var0, String var1, boolean var2) throws StandardException {
      if (!var2) {
         return null;
      } else {
         BuiltinAggDescriptor var3 = null;

         for(BuiltinAggDescriptor var7 : BUILTIN_MODERN_AGGS) {
            if (var7.aggName.equals(var1)) {
               var3 = var7;
               break;
            }
         }

         if (var3 == null) {
            return null;
         } else {
            AggregateAliasInfo var8 = new AggregateAliasInfo(var3.argType, var3.returnType);
            return new AliasDescriptor(var0, (UUID)null, var1, var0.getSystemSchemaDescriptor().getUUID(), var3.aggClassName, 'G', 'G', false, var8, (String)null);
         }
      }
   }

   private void checkAggregatorClassName(String var1) throws StandardException {
      this.verifyClassExist(var1);
      if (!this.classInspector.assignableTo(var1, "org.apache.derby.iapi.sql.execute.ExecAggregator")) {
         throw StandardException.newException("42Y32", new Object[]{var1, this.getSQLName(), this.operand.getTypeId().getSQLTypeName()});
      }
   }

   private void instantiateAggDef() throws StandardException {
      if (this.uad == null) {
         Class var1 = this.aggregateDefinitionClass;
         if (var1 == null) {
            String var2 = this.aggregateDefinitionClassName;
            this.verifyClassExist(var2);

            try {
               var1 = this.classInspector.getClass(var2);
            } catch (Throwable var5) {
               throw StandardException.unexpectedUserException(var5);
            }
         }

         Object var6 = null;

         try {
            var6 = var1.getConstructor().newInstance();
         } catch (Throwable var4) {
            throw StandardException.unexpectedUserException(var4);
         }

         if (!(var6 instanceof AggregateDefinition)) {
            throw StandardException.newException("42Y00", new Object[]{this.aggregateDefinitionClassName});
         }

         if (var6 instanceof MaxMinAggregateDefinition) {
            MaxMinAggregateDefinition var3 = (MaxMinAggregateDefinition)var6;
            if (this.aggregateName.equals("MAX")) {
               var3.setMaxOrMin(true);
            } else {
               var3.setMaxOrMin(false);
            }
         }

         if (var6 instanceof SumAvgAggregateDefinition) {
            SumAvgAggregateDefinition var8 = (SumAvgAggregateDefinition)var6;
            if (this.aggregateName.equals("SUM")) {
               var8.setSumOrAvg(true);
            } else {
               var8.setSumOrAvg(false);
            }
         }

         this.uad = (AggregateDefinition)var6;
      }

      this.setOperator(this.aggregateName);
      this.setMethodName(this.aggregateDefinitionClassName);
   }

   boolean isDistinct() {
      return this.distinct;
   }

   String getAggregatorClassName() {
      return this.aggregatorClassName.toString();
   }

   String getAggregateName() {
      return this.aggregateName;
   }

   ResultColumn getNewAggregatorResultColumn(DataDictionary var1) throws StandardException {
      String var2 = this.aggregatorClassName.toString();
      DataTypeDescriptor var3 = DataTypeDescriptor.getSQLDataTypeDescriptor(var2);
      ConstantNode var4 = this.getNullNode(var3);
      var4.bindExpression((FromList)null, (SubqueryList)null, (List)null);
      return new ResultColumn(this.aggregateName, var4, this.getContextManager());
   }

   ResultColumn getNewExpressionResultColumn(DataDictionary var1) throws StandardException {
      ValueNode var2 = this.operand == null ? this.getNewNullResultExpression() : this.operand;
      return new ResultColumn("##aggregate expression", var2, this.getContextManager());
   }

   ValueNode getNewNullResultExpression() throws StandardException {
      return this.getNullNode(this.getTypeServices());
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
   }

   public String toString() {
      return "";
   }

   boolean isConstant() {
      return false;
   }

   boolean constantExpression(PredicateList var1) {
      return false;
   }

   public String getSQLName() {
      return this.isUserDefinedAggregate() ? ((UserAggregateDefinition)this.uad).getAliasDescriptor().getQualifiedName() : this.aggregateName;
   }

   private boolean isUserDefinedAggregate() {
      return this.uad instanceof UserAggregateDefinition;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.userAggregateName != null) {
         this.userAggregateName = (TableName)this.userAggregateName.accept(var1);
      }

   }

   static {
      BUILTIN_MODERN_AGGS = new BuiltinAggDescriptor[]{new BuiltinAggDescriptor("VAR_POP", "org.apache.derby.impl.sql.execute.VarPAggregator", TypeDescriptor.DOUBLE, TypeDescriptor.DOUBLE), new BuiltinAggDescriptor("VAR_SAMP", "org.apache.derby.impl.sql.execute.VarSAggregator", TypeDescriptor.DOUBLE, TypeDescriptor.DOUBLE), new BuiltinAggDescriptor("STDDEV_POP", "org.apache.derby.impl.sql.execute.StdDevPAggregator", TypeDescriptor.DOUBLE, TypeDescriptor.DOUBLE), new BuiltinAggDescriptor("STDDEV_SAMP", "org.apache.derby.impl.sql.execute.StdDevSAggregator", TypeDescriptor.DOUBLE, TypeDescriptor.DOUBLE)};
   }

   static final class BuiltinAggDescriptor {
      public final String aggName;
      public final String aggClassName;
      public final TypeDescriptor argType;
      public final TypeDescriptor returnType;

      public BuiltinAggDescriptor(String var1, String var2, TypeDescriptor var3, TypeDescriptor var4) {
         this.aggName = var1;
         this.aggClassName = var2;
         this.argType = var3;
         this.returnType = var4;
      }
   }
}
