package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

abstract class DMLModGeneratedColumnsStatementNode extends DMLModStatementNode {
   protected RowLocation[] autoincRowLocation;
   protected String identitySequenceUUIDString;

   DMLModGeneratedColumnsStatementNode(ResultSetNode var1, MatchingClauseNode var2, int var3, ContextManager var4) {
      super(var1, var2, var3, var4);
   }

   DMLModGeneratedColumnsStatementNode(ResultSetNode var1, MatchingClauseNode var2, ContextManager var3) {
      super(var1, var2, var3);
   }

   protected String getUUIDofSequenceGenerator() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      if (this.targetTableDescriptor.tableHasAutoincrement() && var1.checkVersion(230, (String)null)) {
         SequenceDescriptor var2 = var1.getSequenceDescriptor(var1.getSystemSchemaDescriptor(), TableDescriptor.makeSequenceName(this.targetTableDescriptor.getUUID()));
         return var2.getUUID().toString();
      } else {
         return null;
      }
   }
}
