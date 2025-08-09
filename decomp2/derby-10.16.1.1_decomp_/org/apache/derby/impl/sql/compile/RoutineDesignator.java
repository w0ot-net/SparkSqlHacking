package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.impl.sql.execute.PrivilegeInfo;
import org.apache.derby.impl.sql.execute.RoutinePrivilegeInfo;

class RoutineDesignator {
   TableName name;
   boolean isFunction;
   List paramTypeList;
   AliasDescriptor aliasDescriptor;

   RoutineDesignator(TableName var1, boolean var2, List var3) {
      this.name = var1;
      this.isFunction = var2;
      this.paramTypeList = var3;
   }

   void setAliasDescriptor(AliasDescriptor var1) {
      this.aliasDescriptor = var1;
   }

   PrivilegeInfo makePrivilegeInfo() {
      return new RoutinePrivilegeInfo(this.aliasDescriptor);
   }
}
