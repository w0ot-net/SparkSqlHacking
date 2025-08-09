package org.apache.derby.impl.sql.compile;

import java.lang.reflect.Method;
import java.util.List;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class StaticMethodCallNode extends MethodCallNode {
   private TableName procedureName;
   private LocalField[] outParamArrays;
   private int[] applicationParameterNumbers;
   private boolean isSystemCode;
   private boolean isInsideBind;
   private LocalField returnsNullOnNullState;
   private String routineDefiner = null;
   AliasDescriptor ad;
   private AggregateNode resolvedAggregate;
   private boolean appearsInGroupBy = false;

   StaticMethodCallNode(String var1, String var2, ContextManager var3) {
      super(var1, var3);
      this.javaClassName = var2;
   }

   StaticMethodCallNode(TableName var1, String var2, ContextManager var3) {
      super(var1.getTableName(), var3);
      this.procedureName = var1;
      this.javaClassName = var2;
   }

   public AggregateNode getResolvedAggregate() {
      return this.resolvedAggregate;
   }

   public void setAppearsInGroupBy() {
      this.appearsInGroupBy = true;
   }

   TableName getFullName() {
      return this.procedureName;
   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this.isInsideBind) {
         return this;
      } else {
         this.isInsideBind = true;

         JavaValueNode var4;
         try {
            var4 = this.bindExpressionMinion(var1, var2, var3);
         } finally {
            this.isInsideBind = false;
         }

         return var4;
      }
   }

   private JavaValueNode bindExpressionMinion(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindParameters(var1, var2, var3);
      if (this.javaClassName == null) {
         CompilerContext var4 = this.getCompilerContext();
         String var5 = this.procedureName.getSchemaName();
         boolean var6 = var5 == null;
         SchemaDescriptor var7 = this.getSchemaDescriptor(var5, var5 != null);
         this.resolveRoutine(var1, var2, var3, var7, var6);
         if (this.ad != null && this.ad.getAliasType() == 'G') {
            this.resolvedAggregate = new AggregateNode(((SQLToJavaValueNode)this.methodParms[0]).getSQLValueNode(), new UserAggregateDefinition(this.ad), this.procedureName, false, this.ad.getJavaClassName(), this.getContextManager());
            this.resolvedAggregate.copyTagsFrom(this);
            if (this.appearsInGroupBy) {
               throw StandardException.newException("42Y26.S.0", new Object[0]);
            }

            return this;
         }

         SchemaDescriptor var8 = var7;
         if (this.ad == null && var6 && !this.forCallStatement) {
            var7 = this.getSchemaDescriptor("SYSFUN", true);
            this.resolveRoutine(var1, var2, var3, var7, var6);
         }

         if (this.ad == null) {
            var7 = var8;
            if (!this.forCallStatement) {
               this.forCallStatement = true;
               this.resolveRoutine(var1, var2, var3, var8, var6);
               this.forCallStatement = false;
               if (this.ad != null) {
                  throw StandardException.newException("42Y03.S.3", new Object[]{this.procedureName});
               }
            } else {
               this.forCallStatement = false;
               this.resolveRoutine(var1, var2, var3, var8, var6);
               this.forCallStatement = true;
               if (this.ad != null) {
                  throw StandardException.newException("42Y03.S.4", new Object[]{this.procedureName});
               }
            }
         }

         if (this.ad == null) {
            throw StandardException.newException("42Y03.S.0", new Object[]{this.procedureName});
         }

         if (var6) {
            this.procedureName.setSchemaName(var7.getSchemaName());
         }

         if (!this.routineInfo.isDeterministic()) {
            this.checkReliability(this.getMethodName(), 4096);
         }

         if (this.permitsSQL(this.routineInfo)) {
            this.checkReliability(this.getMethodName(), 8192);
         }

         var4.createDependency(this.ad);
         this.methodName = this.ad.getAliasInfo().getMethodName();
         this.javaClassName = this.ad.getJavaClassName();
         if (this.javaClassName.startsWith("org.apache.derby.") && !this.javaClassName.startsWith("org.apache.derby.impl.tools.optional.") && !this.javaClassName.startsWith("org.apache.derby.optional.lucene.") && !this.javaClassName.startsWith("org.apache.derby.optional.json.") && !this.javaClassName.startsWith("org.apache.derby.optional.api.") && !this.javaClassName.startsWith("org.apache.derby.optional.dump.") && !this.javaClassName.startsWith("org.apache.derby.vti.") && !var7.isSystemSchema()) {
            throw StandardException.newException("42X51", (Throwable)null, new Object[]{this.javaClassName});
         }
      }

      this.verifyClassExist(this.javaClassName);
      this.resolveMethodCall(this.javaClassName, true);
      if (this.isPrivilegeCollectionRequired()) {
         this.getCompilerContext().addRequiredRoutinePriv(this.ad);
      }

      if (this.routineInfo != null) {
         if (this.methodParms != null) {
            this.optimizeDomainValueConversion();
         }

         TypeDescriptor var10 = this.routineInfo.getReturnType();
         if (var10 != null) {
            this.createTypeDependency(DataTypeDescriptor.getType(var10));
         }

         if (var10 != null && !var10.isRowMultiSet() && !var10.isUserDefinedType()) {
            TypeId var11 = TypeId.getBuiltInTypeId(var10.getJDBCTypeId());
            if (var11.variableLength()) {
               DataTypeDescriptor var12 = new DataTypeDescriptor(var11, var10.getPrecision(), var10.getScale(), var10.isNullable(), var10.getMaximumWidth());
               JavaToSQLValueNode var13 = new JavaToSQLValueNode(this, this.getContextManager());
               CastNode var14 = new CastNode(var13, var12, this.getContextManager());
               ((ValueNode)var14).setCollationInfo(var10.getCollationType(), 1);
               SQLToJavaValueNode var9 = new SQLToJavaValueNode(var14, this.getContextManager());
               ((JavaValueNode)var9).setCollationType(var10.getCollationType());
               return ((JavaValueNode)var9).bindExpression(var1, var2, var3);
            }
         }
      }

      return this;
   }

   private boolean permitsSQL(RoutineAliasInfo var1) {
      short var2 = var1.getSQLAllowed();
      switch (var2) {
         case 0:
         case 1:
         case 2:
            return true;
         default:
            return false;
      }
   }

   private void optimizeDomainValueConversion() throws StandardException {
      if (this.routineInfo.calledOnNullInput()) {
         int var1 = this.methodParms.length;

         for(int var2 = 0; var2 < var1; ++var2) {
            if ((this.methodParms == null || !this.methodParms[var2].mustCastToPrimitive()) && this.methodParms[var2] instanceof SQLToJavaValueNode && ((SQLToJavaValueNode)this.methodParms[var2]).getSQLValueNode() instanceof JavaToSQLValueNode) {
               JavaValueNode var3 = ((JavaToSQLValueNode)((SQLToJavaValueNode)this.methodParms[var2]).getSQLValueNode()).getJavaValueNode();
               if (var3 instanceof StaticMethodCallNode) {
                  StaticMethodCallNode var4 = (StaticMethodCallNode)var3;
                  if (var4.routineInfo != null && var4.routineInfo.calledOnNullInput()) {
                     this.methodParms[var2] = ((JavaToSQLValueNode)((SQLToJavaValueNode)this.methodParms[var2]).getSQLValueNode()).getJavaValueNode();
                  }
               }
            }
         }

      }
   }

   private void resolveRoutine(FromList var1, SubqueryList var2, List var3, SchemaDescriptor var4, boolean var5) throws StandardException {
      if (var4.getUUID() != null) {
         label90: {
            List var6 = this.getDataDictionary().getRoutineList(var4.getUUID().toString(), this.methodName, (char)(this.forCallStatement ? 'P' : 'F'));
            int var7 = var6.size() - 1;

            AliasDescriptor var8;
            RoutineAliasInfo var9;
            int var10;
            boolean var11;
            while(true) {
               if (var7 < 0) {
                  break label90;
               }

               var8 = (AliasDescriptor)var6.get(var7);
               var9 = (RoutineAliasInfo)var8.getAliasInfo();
               var10 = var9.getParameterCount();
               var11 = var9.hasVarargs();
               if (var11) {
                  if (this.methodParms.length >= var10 - 1) {
                     break;
                  }
               } else if (var10 == this.methodParms.length) {
                  break;
               }

               --var7;
            }

            TypeDescriptor[] var12 = var9.getParameterTypes();
            int var13 = var10;
            if (var9.getMaxDynamicResultSets() > 0) {
               var13 = var10 + 1;
            }

            this.signature = new JSQLType[var13];

            for(int var14 = 0; var14 < var10; ++var14) {
               TypeDescriptor var15 = var12[var14];
               TypeId var16 = TypeId.getTypeId(var15);
               TypeId var17 = var16;
               int var18 = var9.getParameterModes()[this.getRoutineArgIdx(var9, var14)];
               if (var18 != 1) {
                  String var19;
                  switch (var16.getJDBCTypeId()) {
                     case -5:
                     case 4:
                     case 5:
                     case 7:
                     case 8:
                     case 16:
                        var19 = this.getTypeCompiler(var16).getCorrespondingPrimitiveTypeName().concat("[]");
                        break;
                     default:
                        var19 = var16.getCorrespondingJavaTypeName().concat("[]");
                  }

                  var16 = TypeId.getUserDefinedTypeId(var19);
               }

               DataTypeDescriptor var23 = new DataTypeDescriptor(var16, var15.getPrecision(), var15.getScale(), var15.isNullable(), var15.getMaximumWidth());
               this.signature[var14] = new JSQLType(var23);
               DataTypeDescriptor var20 = new DataTypeDescriptor(var17, var15.getPrecision(), var15.getScale(), var15.isNullable(), var15.getMaximumWidth());
               if (var11 && var14 == var10 - 1) {
                  for(int var21 = var14; var21 < this.methodParms.length; ++var21) {
                     this.coerceMethodParameter(var1, var2, var3, var9, this.methodParms.length, var20, var17, var18, var21);
                  }
               } else {
                  this.coerceMethodParameter(var1, var2, var3, var9, this.methodParms.length, var20, var17, var18, var14);
               }
            }

            if (var13 != var10) {
               DataTypeDescriptor var22 = new DataTypeDescriptor(TypeId.getUserDefinedTypeId("java.sql.ResultSet[]"), 0, 0, false, -1);
               this.signature[var10] = new JSQLType(var22);
            }

            this.routineInfo = var9;
            this.ad = var8;
            if (var4.isSystemSchema() && this.routineInfo.getReturnType() == null && this.routineInfo.getSQLAllowed() != 3) {
               this.isSystemCode = true;
            }

            this.routineDefiner = var4.getAuthorizationId();
         }
      }

      if (this.ad == null && this.methodParms.length == 1) {
         this.ad = AggregateNode.resolveAggregate(this.getDataDictionary(), var4, this.methodName, var5);
      }

   }

   private void coerceMethodParameter(FromList var1, SubqueryList var2, List var3, RoutineAliasInfo var4, int var5, DataTypeDescriptor var6, TypeId var7, int var8, int var9) throws StandardException {
      Object var10 = null;
      if (this.methodParms[var9] instanceof SQLToJavaValueNode) {
         SQLToJavaValueNode var11 = (SQLToJavaValueNode)this.methodParms[var9];
         var10 = var11.getSQLValueNode();
      }

      boolean var15 = true;
      if (var10 != null && ((ValueNode)var10).requiresTypeFromContext()) {
         if (this.applicationParameterNumbers == null) {
            this.applicationParameterNumbers = new int[var5];
         }

         if (var10 instanceof UnaryOperatorNode) {
            ParameterNode var12 = ((UnaryOperatorNode)var10).getParameterOperand();
            this.applicationParameterNumbers[var9] = var12.getParameterNumber();
         } else {
            this.applicationParameterNumbers[var9] = ((ParameterNode)var10).getParameterNumber();
         }
      } else {
         if (var8 != 1) {
            throw StandardException.newException("42886", new Object[]{RoutineAliasInfo.parameterMode(var8), var4.getParameterNames()[var9]});
         }

         var15 = false;
      }

      boolean var16 = false;
      if (!var15) {
         if (var10 instanceof UntypedNullConstantNode) {
            ((ValueNode)var10).setType(var6);
         } else {
            DataTypeDescriptor var13;
            TypeId var14;
            if (var10 != null) {
               var14 = ((ValueNode)var10).getTypeId();
               var13 = ((ValueNode)var10).getTypeServices();
            } else {
               var13 = DataTypeDescriptor.getSQLDataTypeDescriptor(this.methodParms[var9].getJavaTypeName());
               if (var13 == null) {
                  throw StandardException.newException("X0X57.S", new Object[]{this.methodParms[var9].getJavaTypeName()});
               }

               var14 = var13.getTypeId();
            }

            if (!this.getTypeCompiler(var7).storable(var14, this.getClassFactory())) {
               throw StandardException.newException("42821", new Object[]{var7.getSQLTypeName(), var14.getSQLTypeName()});
            }

            if (!var6.isExactTypeAndLengthMatch(var13)) {
               var16 = true;
            }
         }
      } else if (var7.variableLength() && var8 != 4) {
         var16 = true;
      }

      if (var16) {
         if (var10 == null) {
            var10 = new JavaToSQLValueNode(this.methodParms[var9], this.getContextManager());
         }

         ValueNode var17 = makeCast((ValueNode)var10, var6, this.getContextManager());
         this.methodParms[var9] = new SQLToJavaValueNode(var17, this.getContextManager());
         this.methodParms[var9] = this.methodParms[var9].bindExpression(var1, var2, var3);
      }

      if (var15) {
         ((ValueNode)var10).setType(var6);
      }

   }

   public static ValueNode makeCast(ValueNode var0, DataTypeDescriptor var1, ContextManager var2) throws StandardException {
      CastNode var3 = new CastNode(var0, var1, var2);
      ((CastNode)var3).setAssignmentSemantics();
      return var3;
   }

   private void generatePushNestedSessionContext(ActivationClassBuilder var1, MethodBuilder var2, boolean var3, String var4) throws StandardException {
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
      var1.pushThisAsActivation(var2);
      var2.push(var3);
      var2.push(var4);
      var2.callMethod((short)185, (String)null, "pushNestedSessionContext", "void", 3);
   }

   void generateOneParameter(ExpressionClassBuilder var1, MethodBuilder var2, int var3) throws StandardException {
      SQLToJavaValueNode var5 = null;
      if (this.methodParms[var3] instanceof SQLToJavaValueNode) {
         var5 = (SQLToJavaValueNode)this.methodParms[var3];
      }

      int var4;
      if (this.routineInfo != null) {
         var4 = this.routineInfo.getParameterModes()[this.getRoutineArgIdx(var3)];
      } else {
         var4 = 1;
         if (var5 != null && var5.getSQLValueNode().requiresTypeFromContext()) {
            ParameterNode var6;
            if (var5.getSQLValueNode() instanceof UnaryOperatorNode) {
               var6 = ((UnaryOperatorNode)var5.getSQLValueNode()).getParameterOperand();
            } else {
               var6 = (ParameterNode)var5.getSQLValueNode();
            }

            int var7 = var6.getParameterNumber();
            String var8 = this.methodParameterTypes[this.getRoutineArgIdx(var3)];
            if (var8.endsWith("[]")) {
               MethodBuilder var9 = var1.getConstructor();
               var1.pushThisAsActivation(var9);
               var9.callMethod((short)185, (String)null, "getParameterValueSet", "org.apache.derby.iapi.sql.ParameterValueSet", 0);
               var9.push(var7);
               var9.push((int)0);
               var9.callMethod((short)185, (String)null, "setParameterMode", "void", 2);
               var9.endStatement();
            }
         }
      }

      switch (var4) {
         case 0:
         case 1:
         case 2:
            if (var5 != null) {
               var5.returnsNullOnNullState = this.returnsNullOnNullState;
            }

            super.generateOneParameter(var1, var2, var3);
         case 3:
         case 4:
         default:
            switch (var4) {
               case 2:
               case 4:
                  String var10 = this.methodParameterTypes[this.getRoutineArgIdx(var3)];
                  String var11 = var10.substring(0, var10.length() - 2);
                  if (this.isVararg(var3)) {
                     var10 = this.stripOneArrayLevel(var10);
                     var11 = this.stripOneArrayLevel(var11);
                  }

                  LocalField var12 = var1.newFieldDeclaration(2, var10);
                  if (this.outParamArrays == null) {
                     this.outParamArrays = new LocalField[this.methodParms.length];
                  }

                  this.outParamArrays[var3] = var12;
                  var2.pushNewArray(var11, 1);
                  var2.putField(var12);
                  if (var4 != 4) {
                     var2.swap();
                     var2.setArrayElement(0);
                     var2.getField(var12);
                  }
               case 0:
               case 1:
               case 3:
               default:
            }
      }
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      if (var2) {
         return false;
      } else {
         boolean var3 = true;
         var3 = var3 && super.categorize(var1, var2);
         return var3;
      }
   }

   public String toString() {
      return "";
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.routineInfo != null && !this.routineInfo.calledOnNullInput() && this.routineInfo.getParameterCount() != 0) {
         this.returnsNullOnNullState = var1.newFieldDeclaration(2, "boolean");
      }

      if (this.returnsNullOnNullState != null) {
         var2.push(false);
         var2.setField(this.returnsNullOnNullState);
         var2.pushThis();
      }

      int var3 = this.generateParameters(var1, var2);
      LocalField var4 = null;
      if (this.routineInfo != null) {
         short var5 = this.routineInfo.getSQLAllowed();
         if (var5 != 3) {
            byte var6;
            if (var5 == 1) {
               var6 = 1;
            } else if (var5 == 0) {
               var6 = 0;
            } else {
               var6 = 2;
            }

            this.generateAuthorizeCheck((ActivationClassBuilder)var1, var2, var6);
         }

         int var22 = this.isSystemCode ? 2 : 1;
         boolean var7 = this.routineInfo.getReturnType() != null;
         if (var7) {
            ++var22;
         }

         if (var22 != 0) {
            var1.pushThisAsActivation(var2);
            var2.callMethod((short)185, (String)null, "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
            var2.callMethod((short)185, (String)null, "getStatementContext", "org.apache.derby.iapi.sql.conn.StatementContext", 0);

            for(int var8 = 1; var8 < var22; ++var8) {
               var2.dup();
            }
         }

         if (this.isSystemCode) {
            var2.callMethod((short)185, (String)null, "setSystemCode", "void", 0);
         }

         if (var5 != 3) {
            this.generatePushNestedSessionContext((ActivationClassBuilder)var1, var2, this.routineInfo.hasDefinersRights(), this.routineDefiner);
         }

         if (var7) {
            var4 = var1.newFieldDeclaration(2, "short");
            var2.callMethod((short)185, (String)null, "getSQLAllowed", "short", 0);
            var2.setField(var4);
         }

         var2.push(var5);
         var2.push(false);
         var2.callMethod((short)185, (String)null, "setSQLAllowed", "void", 2);
      }

      if (this.routineInfo != null && !this.hasVarargs()) {
         int var20 = this.methodParameterTypes.length - this.methodParms.length;
         if (var20 != 0) {
            int var23 = this.routineInfo.getMaxDynamicResultSets();
            if (var23 > 0) {
               MethodBuilder var26 = var1.getClassBuilder().newMethodBuilder(1, "int", "getMaxDynamicResults");
               var26.push(var23);
               var26.methodReturn();
               var26.complete();
            }

            MethodBuilder var27 = var1.getClassBuilder().newMethodBuilder(1, "java.sql.ResultSet[][]", "getDynamicResults");
            MethodBuilder var29 = var1.getConstructor();
            LocalField var9 = var1.newFieldDeclaration(2, "java.sql.ResultSet[][]");
            var27.getField(var9);
            var29.pushNewArray("java.sql.ResultSet[]", var20);
            var29.setField(var9);

            for(int var10 = 0; var10 < var20; ++var10) {
               var2.pushNewArray("java.sql.ResultSet", 1);
               var2.dup();
               var2.getField(var9);
               var2.swap();
               var2.setArrayElement(var10);
            }

            var27.methodReturn();
            var27.complete();
            var3 += var20;
         }
      }

      String var21 = this.getJavaTypeName();
      MethodBuilder var24 = null;
      MethodBuilder var28 = var2;
      if (this.returnsNullOnNullState != null) {
         var24 = var1.newGeneratedFun(var21, 2, this.methodParameterTypes);
         Class[] var30 = ((Method)this.method).getExceptionTypes();

         for(int var32 = 0; var32 < var30.length; ++var32) {
            var24.addThrownException(var30[var32].getName());
         }

         var24.getField(this.returnsNullOnNullState);
         var24.conditionalIf();
         var24.pushNull(var21);
         var24.startElseCode();
         if (!this.actualMethodReturnType.equals(var21)) {
            var24.pushNewStart(var21);
         }

         for(int var33 = 0; var33 < var3; ++var33) {
            var24.getParameter(var33);
         }

         var28 = var24;
      }

      var28.callMethod((short)184, this.method.getDeclaringClass().getName(), this.methodName, this.actualMethodReturnType, var3);
      if (this.returnsNullOnNullState != null) {
         if (!this.actualMethodReturnType.equals(var21)) {
            if (this.actualMethodReturnType.equals("short") && var21.equals("java.lang.Integer")) {
               var24.upCast("int");
            }

            var24.pushNewComplete(1);
         }

         var24.completeConditional();
         var24.methodReturn();
         var24.complete();
         var2.callMethod((short)182, var1.getClassBuilder().getFullName(), var24.getName(), var21, var3);
         Object var25 = null;
      }

      if (this.routineInfo != null) {
         if (var4 != null) {
            var1.pushThisAsActivation(var2);
            var2.callMethod((short)185, (String)null, "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
            var2.callMethod((short)185, (String)null, "getStatementContext", "org.apache.derby.iapi.sql.conn.StatementContext", 0);
            var2.getField(var4);
            var2.push(true);
            var2.callMethod((short)185, (String)null, "setSQLAllowed", "void", 2);
         }

         if (this.outParamArrays != null) {
            MethodBuilder var31 = var1.getConstructor();
            var1.pushThisAsActivation(var31);
            var31.callMethod((short)185, (String)null, "getParameterValueSet", "org.apache.derby.iapi.sql.ParameterValueSet", 0);
            var1.pushThisAsActivation(var2);
            var2.callMethod((short)185, (String)null, "getParameterValueSet", "org.apache.derby.iapi.sql.ParameterValueSet", 0);
            int[] var34 = this.routineInfo.getParameterModes();

            for(int var35 = 0; var35 < this.outParamArrays.length; ++var35) {
               int var11 = var34[this.getRoutineArgIdx(var35)];
               if (var11 != 1) {
                  ValueNode var12 = ((SQLToJavaValueNode)this.methodParms[var35]).getSQLValueNode();
                  int var13 = this.applicationParameterNumbers[var35];
                  var31.dup();
                  var31.push(var13);
                  var31.push(var11);
                  var31.callMethod((short)185, (String)null, "setParameterMode", "void", 2);
                  LocalField var14 = this.outParamArrays[var35];
                  var2.dup();
                  var2.push(var13);
                  var2.callMethod((short)185, (String)null, "getParameter", "org.apache.derby.iapi.types.DataValueDescriptor", 1);
                  DataTypeDescriptor var15 = var12.getTypeServices();
                  boolean var16 = var15.getTypeId().isNumericTypeId();
                  boolean var17 = var15.getTypeId().getBaseTypeId().isAnsiUDT();
                  Class var18 = ((Method)this.method).getParameterTypes()[this.getRoutineArgIdx(var35)].getComponentType();
                  if (this.isVararg(var35)) {
                     var18 = var18.getComponentType();
                  }

                  boolean var19 = var18.isPrimitive();
                  if (var16) {
                     if (!var19) {
                        var2.cast("org.apache.derby.iapi.types.NumberDataValue");
                     }
                  } else if (var15.getTypeId().isBooleanTypeId() && !var19) {
                     var2.cast("org.apache.derby.iapi.types.BooleanDataValue");
                  }

                  if (var15.getTypeId().variableLength()) {
                     var2.dup();
                  }

                  var2.getField(var14);
                  var2.getArrayElement(0);
                  if (var16 && !var19) {
                     var2.upCast("java.lang.Number");
                  }

                  if (var17) {
                     var2.upCast("java.lang.Object");
                  }

                  var2.callMethod((short)185, (String)null, "setValue", "void", 1);
                  if (var15.getTypeId().variableLength()) {
                     var2.push(var16 ? var15.getPrecision() : var15.getMaximumWidth());
                     var2.push(var15.getScale());
                     var2.push(var16);
                     var2.callMethod((short)185, "org.apache.derby.iapi.types.VariableSizeDataValue", "setWidth", "void", 3);
                  }
               }
            }

            var31.endStatement();
            var2.endStatement();
         }
      }

   }

   int getPrivType() {
      return 6;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.procedureName != null) {
         this.procedureName = (TableName)this.procedureName.accept(var1);
      }

   }
}
