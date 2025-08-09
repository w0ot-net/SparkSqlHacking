package org.apache.derby.iapi.sql.dictionary;

import java.util.ArrayList;
import org.apache.derby.catalog.UUID;

public class ColumnDescriptorList extends ArrayList {
   public void add(UUID var1, ColumnDescriptor var2) {
      this.add(var2);
   }

   public ColumnDescriptor getColumnDescriptor(UUID var1, String var2) {
      ColumnDescriptor var3 = null;

      for(ColumnDescriptor var5 : this) {
         if (var2.equals(var5.getColumnName()) && var1.equals(var5.getReferencingUUID())) {
            var3 = var5;
            break;
         }
      }

      return var3;
   }

   public ColumnDescriptor getColumnDescriptor(UUID var1, int var2) {
      ColumnDescriptor var3 = null;

      for(ColumnDescriptor var5 : this) {
         if (var2 == var5.getPosition() && var1.equals(var5.getReferencingUUID())) {
            var3 = var5;
            break;
         }
      }

      return var3;
   }

   public ColumnDescriptor elementAt(int var1) {
      return (ColumnDescriptor)this.get(var1);
   }

   public String[] getColumnNames() {
      String[] var1 = new String[this.size()];
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ColumnDescriptor var4 = this.elementAt(var3);
         var1[var3] = var4.getColumnName();
      }

      return var1;
   }
}
