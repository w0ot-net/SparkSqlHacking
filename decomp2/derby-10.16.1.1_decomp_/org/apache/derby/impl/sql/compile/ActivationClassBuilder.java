package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.shared.common.error.StandardException;

class ActivationClassBuilder extends ExpressionClassBuilder {
   private LocalField targetResultSetField;
   private LocalField cursorResultSetField;
   private MethodBuilder closeActivationMethod;

   ActivationClassBuilder(String var1, CompilerContext var2) throws StandardException {
      super(var1, (String)null, var2);
   }

   public String getPackageName() {
      return "org.apache.derby.exe.";
   }

   String getBaseClassName() {
      return "org.apache.derby.impl.sql.execute.BaseActivation";
   }

   public int getRowCount() throws StandardException {
      return this.myCompCtx.getNumResultSets();
   }

   public void setNumSubqueries() {
      int var1 = this.myCompCtx.getNumSubquerys();
      if (var1 != 0) {
         this.constructor.pushThis();
         this.constructor.push(var1);
         this.constructor.putField("org.apache.derby.impl.sql.execute.BaseActivation", "numSubqueries", "int");
         this.constructor.endStatement();
      }
   }

   MethodBuilder startResetMethod() {
      MethodBuilder var1 = this.cb.newMethodBuilder(1, "void", "reset");
      var1.addThrownException("org.apache.derby.shared.common.error.StandardException");
      var1.pushThis();
      var1.callMethod((short)183, "org.apache.derby.impl.sql.execute.BaseActivation", "reset", "void", 0);
      return var1;
   }

   void finishExecuteMethod() {
      if (this.executeMethod != null) {
         this.executeMethod.methodReturn();
         this.executeMethod.complete();
      }

      if (this.closeActivationMethod != null) {
         this.closeActivationMethod.methodReturn();
         this.closeActivationMethod.complete();
      }

   }

   void addCursorPositionCode() {
      MethodBuilder var1 = this.cb.newMethodBuilder(1, "org.apache.derby.iapi.sql.execute.CursorResultSet", "getTargetResultSet");
      var1.getField(this.targetResultSetField);
      var1.methodReturn();
      var1.complete();
      var1 = this.cb.newMethodBuilder(1, "org.apache.derby.iapi.sql.execute.CursorResultSet", "getCursorResultSet");
      var1.getField(this.cursorResultSetField);
      var1.methodReturn();
      var1.complete();
   }

   void rememberCursorTarget(MethodBuilder var1) {
      this.targetResultSetField = this.cb.addField("org.apache.derby.iapi.sql.execute.CursorResultSet", "targetResultSet", 2);
      var1.cast("org.apache.derby.iapi.sql.execute.CursorResultSet");
      var1.putField(this.targetResultSetField);
      var1.cast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
   }

   void rememberCursor(MethodBuilder var1) {
      this.cursorResultSetField = this.cb.addField("org.apache.derby.iapi.sql.execute.CursorResultSet", "cursorResultSet", 2);
      var1.cast("org.apache.derby.iapi.sql.execute.CursorResultSet");
      var1.putField(this.cursorResultSetField);
      var1.cast("org.apache.derby.iapi.sql.ResultSet");
   }

   protected LocalField getCurrentSetup() {
      if (this.cdtField != null) {
         return this.cdtField;
      } else {
         LocalField var1 = super.getCurrentSetup();
         MethodBuilder var2 = this.getExecuteMethod();
         var2.getField(var1);
         var2.callMethod((short)182, (String)null, "forget", "void", 0);
         return var1;
      }
   }

   MethodBuilder getCloseActivationMethod() {
      if (this.closeActivationMethod == null) {
         this.closeActivationMethod = this.cb.newMethodBuilder(1, "void", "closeActivationAction");
         this.closeActivationMethod.addThrownException("java.lang.Exception");
      }

      return this.closeActivationMethod;
   }
}
