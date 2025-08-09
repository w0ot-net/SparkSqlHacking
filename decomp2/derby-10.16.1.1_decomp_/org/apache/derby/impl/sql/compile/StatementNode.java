package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public abstract class StatementNode extends QueryTreeNode {
   static final TableDescriptor[] EMPTY_TD_LIST = new TableDescriptor[0];
   static final int NEED_DDL_ACTIVATION = 5;
   static final int NEED_CURSOR_ACTIVATION = 4;
   static final int NEED_PARAM_ACTIVATION = 2;
   static final int NEED_ROW_ACTIVATION = 1;
   static final int NEED_NOTHING_ACTIVATION = 0;

   StatementNode(ContextManager var1) {
      super(var1);
   }

   public boolean isAtomic() throws StandardException {
      return true;
   }

   public boolean needsSavepoint() {
      return true;
   }

   public String getSPSName() {
      return null;
   }

   public String executeStatementName() {
      return null;
   }

   public String executeSchemaName() {
      return null;
   }

   public ResultDescription makeResultDescription() {
      return null;
   }

   public Object getCursorInfo() throws StandardException {
      return null;
   }

   public String toString() {
      return "";
   }

   abstract String statementToString();

   public void bindStatement() throws StandardException {
   }

   public void optimizeStatement() throws StandardException {
   }

   abstract int activationKind();

   protected TableDescriptor lockTableForCompilation(TableDescriptor var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      if (var2.getCacheMode() == 1) {
         TransactionController var4 = this.getLanguageConnectionContext().getTransactionCompile();
         ConglomerateController var3 = var4.openConglomerate(var1.getHeapConglomerateId(), false, 68, 6, 5);
         var3.close();
         String var5 = var1.getName();
         var1 = this.getTableDescriptor(var1.getName(), this.getSchemaDescriptor(var1.getSchemaName()));
         if (var1 == null) {
            throw StandardException.newException("42X05", new Object[]{var5});
         }
      }

      return var1;
   }

   public GeneratedClass generate(ByteArray var1) throws StandardException {
      int var2 = this.activationKind();
      String var3;
      switch (var2) {
         case 0:
         case 1:
         case 2:
            var3 = "org.apache.derby.impl.sql.execute.BaseActivation";
            break;
         case 3:
         default:
            throw StandardException.newException("42Z53", new Object[]{String.valueOf(var2)});
         case 4:
            var3 = "org.apache.derby.impl.sql.execute.CursorActivation";
            break;
         case 5:
            return this.getClassFactory().loadGeneratedClass("org.apache.derby.impl.sql.execute.ConstantActionActivation", (ByteArray)null);
      }

      ActivationClassBuilder var4 = new ActivationClassBuilder(var3, this.getCompilerContext());
      MethodBuilder var5 = var4.getClassBuilder().newMethodBuilder(4, "org.apache.derby.iapi.sql.ResultSet", "createResultSet");
      var5.addThrownException("org.apache.derby.shared.common.error.StandardException");
      this.generate(var4, var5);
      var5.methodReturn();
      var5.complete();
      var4.finishExecuteMethod();
      var4.finishConstructor();

      try {
         GeneratedClass var6 = var4.getGeneratedClass(var1);
         return var6;
      } catch (StandardException var8) {
         String var7 = var8.getMessageId();
         if (!"XBCM4.S".equals(var7) && !"XBCM1.S".equals(var7)) {
            throw var8;
         } else {
            throw StandardException.newException("42ZA0", var8, new Object[0]);
         }
      }
   }

   public TableDescriptor[] updateIndexStatisticsFor() throws StandardException {
      return EMPTY_TD_LIST;
   }
}
