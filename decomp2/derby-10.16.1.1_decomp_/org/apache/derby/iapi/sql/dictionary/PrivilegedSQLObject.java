package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.sql.depend.Provider;

public abstract class PrivilegedSQLObject extends UniqueSQLObjectDescriptor implements Provider {
   public PrivilegedSQLObject() {
   }

   public PrivilegedSQLObject(DataDictionary var1) {
      super(var1);
   }

   public abstract String getObjectTypeName();
}
