package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.shared.common.error.StandardException;

public abstract class UniqueSQLObjectDescriptor extends UniqueTupleDescriptor {
   public UniqueSQLObjectDescriptor() {
   }

   public UniqueSQLObjectDescriptor(DataDictionary var1) {
      super(var1);
   }

   public abstract String getName();

   public abstract SchemaDescriptor getSchemaDescriptor() throws StandardException;
}
