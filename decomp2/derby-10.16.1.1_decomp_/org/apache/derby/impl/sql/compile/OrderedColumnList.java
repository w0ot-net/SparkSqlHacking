package org.apache.derby.impl.sql.compile;

import java.util.HashSet;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.impl.sql.execute.IndexColumnOrder;

public abstract class OrderedColumnList extends QueryTreeNodeVector {
   public OrderedColumnList(Class var1, ContextManager var2) {
      super(var1, var2);
   }

   IndexColumnOrder[] getColumnOrdering() {
      int var2 = this.size();
      IndexColumnOrder[] var1 = new IndexColumnOrder[var2];
      HashSet var4 = new HashSet();
      int var3 = 0;

      for(int var5 = 0; var5 < var2; ++var5) {
         OrderedColumn var6 = (OrderedColumn)this.elementAt(var5);
         int var7 = var6.getColumnPosition() - 1;
         if (var4.add(var7)) {
            var1[var5] = new IndexColumnOrder(var7, var6.isAscending(), var6.isNullsOrderedLow());
            ++var3;
         }
      }

      if (var3 < var2) {
         IndexColumnOrder[] var8 = new IndexColumnOrder[var3];
         System.arraycopy(var1, 0, var8, 0, var3);
         var1 = var8;
      }

      return var1;
   }
}
