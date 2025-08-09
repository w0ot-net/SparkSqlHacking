package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;

class MaxMinAggregateDefinition implements AggregateDefinition {
   private boolean isMax;

   public MaxMinAggregateDefinition() {
   }

   public final DataTypeDescriptor getAggregator(DataTypeDescriptor var1, StringBuffer var2) {
      LanguageConnectionContext var3 = (LanguageConnectionContext)QueryTreeNode.getContext("LanguageConnectionContext");
      DataTypeDescriptor var4 = var1.getNullabilityType(true);
      TypeId var5 = var4.getTypeId();
      if (var5.orderable(var3.getLanguageConnectionFactory().getClassFactory())) {
         var2.append("org.apache.derby.impl.sql.execute.MaxMinAggregator");
         return var4;
      } else {
         return null;
      }
   }

   final void setMaxOrMin(boolean var1) {
      this.isMax = var1;
   }

   final boolean isMax() {
      return this.isMax;
   }
}
