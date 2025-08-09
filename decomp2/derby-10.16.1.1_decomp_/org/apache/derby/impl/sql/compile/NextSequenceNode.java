package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class NextSequenceNode extends ValueNode {
   private TableName sequenceName;
   private SequenceDescriptor sequenceDescriptor;

   NextSequenceNode(TableName var1, ContextManager var2) {
      super(var2);
      this.sequenceName = var1;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3, boolean var4) throws StandardException {
      if (this.sequenceDescriptor != null) {
         return this;
      } else {
         CompilerContext var5 = this.getCompilerContext();
         if ((var5.getReliability() & 16384) != 0) {
            throw StandardException.newException("42XAH", new Object[0]);
         } else {
            this.sequenceName.bind();
            SchemaDescriptor var6 = this.getSchemaDescriptor(this.sequenceName.getSchemaName());
            this.sequenceDescriptor = this.getDataDictionary().getSequenceDescriptor(var6, this.sequenceName.getTableName());
            if (this.sequenceDescriptor == null) {
               throw StandardException.newException("42X94", new Object[]{"SEQUENCE", this.sequenceName.getFullTableName()});
            } else if (var6.isSystemSchema()) {
               throw StandardException.newException("42XAR", new Object[0]);
            } else {
               this.setType(this.sequenceDescriptor.getDataType());
               if (var5.isReferenced(this.sequenceDescriptor)) {
                  throw StandardException.newException("42XAI", new Object[]{this.sequenceName.getFullTableName()});
               } else {
                  var5.addReferencedSequence(this.sequenceDescriptor);
                  this.getCompilerContext().createDependency(this.sequenceDescriptor);
                  if (this.isPrivilegeCollectionRequired()) {
                     this.getCompilerContext().addRequiredUsagePriv(this.sequenceDescriptor);
                  }

                  return this;
               }
            }
         }
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      String var3 = this.sequenceDescriptor.getUUID().toString();
      int var4 = this.sequenceDescriptor.getDataType().getNull().getTypeFormatId();
      var2.pushThis();
      var2.push(var3);
      var2.push(var4);
      var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getCurrentValueAndAdvance", "org.apache.derby.iapi.types.NumberDataValue", 2);
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      switch (this.getTypeServices().getJDBCTypeId()) {
         case 4:
            var2.push((int)1);
         default:
      }
   }

   public String toString() {
      return "";
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      return false;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.sequenceName != null) {
         this.sequenceName = (TableName)this.sequenceName.accept(var1);
      }

   }
}
