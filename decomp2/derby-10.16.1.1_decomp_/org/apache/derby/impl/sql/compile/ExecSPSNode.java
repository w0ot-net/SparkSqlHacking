package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

class ExecSPSNode extends StatementNode {
   private TableName name;
   private SPSDescriptor spsd;
   private ExecPreparedStatement ps;

   ExecSPSNode(TableName var1, ContextManager var2) {
      super(var2);
      this.name = var1;
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      String var2 = this.name.getSchemaName();
      SchemaDescriptor var3 = this.getSchemaDescriptor(this.name.getSchemaName());
      if (var2 == null) {
         this.name.setSchemaName(var3.getSchemaName());
      }

      if (var3.getUUID() != null) {
         this.spsd = var1.getSPSDescriptor(this.name.getTableName(), var3);
      }

      if (this.spsd == null) {
         throw StandardException.newException("42X94", new Object[]{"STATEMENT", this.name});
      } else if (this.spsd.getType() == 'T') {
         throw StandardException.newException("42Y41", new Object[]{this.name});
      } else {
         this.getCompilerContext().createDependency(this.spsd);
      }
   }

   public boolean isAtomic() {
      return this.ps.isAtomic();
   }

   public GeneratedClass generate(ByteArray var1) throws StandardException {
      if (!this.spsd.isValid()) {
         this.getLanguageConnectionContext().commitNestedTransaction();
         this.getLanguageConnectionContext().beginNestedTransaction(true);
      }

      this.ps = this.spsd.getPreparedStatement();
      this.getCompilerContext().setSavedObjects(this.ps.getSavedObjects());
      this.getCompilerContext().setCursorInfo(this.ps.getCursorInfo());
      GeneratedClass var2 = this.ps.getActivationClass();
      return var2;
   }

   public ResultDescription makeResultDescription() {
      return this.ps.getResultDescription();
   }

   public Object getCursorInfo() {
      return this.ps.getCursorInfo();
   }

   public DataTypeDescriptor[] getParameterTypes() throws StandardException {
      return this.spsd.getParams();
   }

   public ConstantAction makeConstantAction() {
      return this.ps.getConstantAction();
   }

   public boolean needsSavepoint() {
      return this.ps.needsSavepoint();
   }

   public String executeStatementName() {
      return this.name.getTableName();
   }

   public String executeSchemaName() {
      return this.name.getSchemaName();
   }

   public String getSPSName() {
      return this.spsd.getQualifiedName();
   }

   int activationKind() {
      return 2;
   }

   String statementToString() {
      return "EXECUTE STATEMENT";
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.name != null) {
         this.name = (TableName)this.name.accept(var1);
      }

   }
}
