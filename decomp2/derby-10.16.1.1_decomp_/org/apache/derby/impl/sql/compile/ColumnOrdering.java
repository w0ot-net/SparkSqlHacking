package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;

class ColumnOrdering {
   int myDirection;
   private final ArrayList columns = new ArrayList();
   private final ArrayList tables = new ArrayList();

   ColumnOrdering(int var1) {
      this.myDirection = var1;
   }

   boolean ordered(int var1, int var2, int var3) {
      return var1 != 3 && var1 != this.myDirection ? false : this.contains(var2, var3);
   }

   boolean contains(int var1, int var2) {
      for(int var3 = 0; var3 < this.columns.size(); ++var3) {
         Integer var4 = (Integer)this.columns.get(var3);
         Integer var5 = (Integer)this.tables.get(var3);
         if (var5 == var1 && var4 == var2) {
            return true;
         }
      }

      return false;
   }

   int direction() {
      return this.myDirection;
   }

   void addColumn(int var1, int var2) {
      this.tables.add(var1);
      this.columns.add(var2);
   }

   void removeColumns(int var1) {
      for(int var2 = this.tables.size() - 1; var2 >= 0; --var2) {
         Integer var3 = (Integer)this.tables.get(var2);
         if (var3 == var1) {
            this.tables.remove(var2);
            this.columns.remove(var2);
         }
      }

   }

   boolean empty() {
      return this.tables.isEmpty();
   }

   ColumnOrdering cloneMe() {
      ColumnOrdering var1 = new ColumnOrdering(this.myDirection);

      for(int var2 = 0; var2 < this.columns.size(); ++var2) {
         var1.columns.add((Integer)this.columns.get(var2));
         var1.tables.add((Integer)this.tables.get(var2));
      }

      return var1;
   }

   boolean hasTable(int var1) {
      return this.tables.contains(var1);
   }

   boolean hasAnyOtherTable(int var1) {
      for(int var2 = 0; var2 < this.tables.size(); ++var2) {
         Integer var3 = (Integer)this.tables.get(var2);
         if (var3 != var1) {
            return true;
         }
      }

      return false;
   }

   public String toString() {
      String var1 = "";
      return var1;
   }
}
