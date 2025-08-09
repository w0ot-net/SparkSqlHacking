package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

class CallStatementNode extends DMLStatementNode {
   private JavaToSQLValueNode methodCall;

   CallStatementNode(JavaToSQLValueNode var1, ContextManager var2) {
      super((ResultSetNode)null, var2);
      this.methodCall = var1;
      this.methodCall.getJavaValueNode().markForCallStatement();
   }

   String statementToString() {
      return "CALL";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      SubqueryList var2 = new SubqueryList(this.getContextManager());
      this.getCompilerContext().pushCurrentPrivType(this.getPrivType());
      this.methodCall = (JavaToSQLValueNode)this.methodCall.bindExpression(new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()), var2, (List)null);
      if (var2.size() != 0) {
         throw StandardException.newException("42X74", new Object[0]);
      } else {
         this.checkReliability();
         this.getCompilerContext().popCurrentPrivType();
      }
   }

   public void optimizeStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      this.methodCall = (JavaToSQLValueNode)this.methodCall.preprocess(this.getCompilerContext().getNumTables(), new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()), (SubqueryList)null, (PredicateList)null);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateParameterValueSet(var1);
      JavaValueNode var3 = this.methodCall.getJavaValueNode();
      var3.markReturnValueDiscarded();
      MethodBuilder var4 = var1.newGeneratedFun("void", 1);
      var4.addThrownException("java.lang.Exception");
      var3.generate(var1, var4);
      var4.endStatement();
      var4.methodReturn();
      var4.complete();
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushMethodReference(var2, var4);
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getCallStatementResultSet", "org.apache.derby.iapi.sql.ResultSet", 2);
   }

   public ResultDescription makeResultDescription() {
      return null;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.methodCall != null) {
         this.methodCall = (JavaToSQLValueNode)this.methodCall.accept(var1);
      }

   }

   int getPrivType() {
      return 6;
   }

   private void checkReliability() throws StandardException {
      if (this.getSQLAllowedInProcedure() == 0 && this.getCompilerContext().getReliability() == 2048) {
         throw StandardException.newException("42Z9D.S.1", new Object[0]);
      }
   }

   private short getSQLAllowedInProcedure() {
      RoutineAliasInfo var1 = ((MethodCallNode)this.methodCall.getJavaValueNode()).routineInfo;
      return var1.getSQLAllowed();
   }
}
