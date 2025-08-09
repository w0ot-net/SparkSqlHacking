package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.ListIterator;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.shared.common.error.StandardException;

class RowOrderingImpl implements RowOrdering {
   private final ArrayList ordering = new ArrayList();
   ColumnOrdering columnsAlwaysOrdered = new ColumnOrdering(3);
   private final ArrayList alwaysOrderedOptimizables = new ArrayList();
   ColumnOrdering currentColumnOrdering;
   private final ArrayList unorderedOptimizables = new ArrayList();

   public boolean isColumnAlwaysOrdered(int var1, int var2) {
      return this.columnsAlwaysOrdered.contains(var1, var2);
   }

   public boolean orderedOnColumn(int var1, int var2, int var3, int var4) throws StandardException {
      if (this.alwaysOrdered(var3)) {
         return true;
      } else if (this.columnsAlwaysOrdered.contains(var3, var4)) {
         return true;
      } else if (var2 >= this.ordering.size()) {
         return false;
      } else {
         ColumnOrdering var5 = (ColumnOrdering)this.ordering.get(var2);
         return var5.ordered(var1, var3, var4);
      }
   }

   public boolean orderedOnColumn(int var1, int var2, int var3) throws StandardException {
      if (this.alwaysOrdered(var2)) {
         return true;
      } else if (this.columnsAlwaysOrdered.contains(var2, var3)) {
         return true;
      } else {
         boolean var4 = false;

         for(int var5 = 0; var5 < this.ordering.size(); ++var5) {
            ColumnOrdering var6 = (ColumnOrdering)this.ordering.get(var5);
            boolean var7 = var6.ordered(var1, var2, var3);
            if (var7) {
               var4 = true;
               break;
            }
         }

         return var4;
      }
   }

   public void addOrderedColumn(int var1, int var2, int var3) {
      if (this.unorderedOptimizables.isEmpty()) {
         ColumnOrdering var4;
         if (this.ordering.isEmpty()) {
            var4 = new ColumnOrdering(var1);
            this.ordering.add(var4);
         } else {
            var4 = (ColumnOrdering)this.ordering.get(this.ordering.size() - 1);
         }

         var4.addColumn(var2, var3);
      }
   }

   public void nextOrderPosition(int var1) {
      if (this.unorderedOptimizables.isEmpty()) {
         this.currentColumnOrdering = new ColumnOrdering(var1);
         this.ordering.add(this.currentColumnOrdering);
      }
   }

   public void optimizableAlwaysOrdered(Optimizable var1) {
      if (!this.unorderedOptimizablesOtherThan(var1)) {
         boolean var2 = var1.hasTableNumber();
         int var3 = var2 ? var1.getTableNumber() : 0;
         if ((this.ordering.isEmpty() || var2 && ((ColumnOrdering)this.ordering.get(0)).hasTable(var3)) && var2 && !this.columnsAlwaysOrdered.hasAnyOtherTable(var3)) {
            if (var1.hasTableNumber()) {
               this.removeOptimizable(var1.getTableNumber());
            }

            this.alwaysOrderedOptimizables.add(var1);
         }

      }
   }

   public void columnAlwaysOrdered(Optimizable var1, int var2) {
      this.columnsAlwaysOrdered.addColumn(var1.getTableNumber(), var2);
   }

   public boolean alwaysOrdered(int var1) {
      for(Optimizable var3 : this.alwaysOrderedOptimizables) {
         if (var3.hasTableNumber() && var3.getTableNumber() == var1) {
            return true;
         }
      }

      return false;
   }

   public void removeOptimizable(int var1) {
      for(int var2 = this.ordering.size() - 1; var2 >= 0; --var2) {
         ColumnOrdering var3 = (ColumnOrdering)this.ordering.get(var2);
         var3.removeColumns(var1);
         if (var3.empty()) {
            this.ordering.remove(var2);
         }
      }

      this.columnsAlwaysOrdered.removeColumns(var1);
      this.removeOptimizable(var1, this.unorderedOptimizables);
      this.removeOptimizable(var1, this.alwaysOrderedOptimizables);
   }

   private void removeOptimizable(int var1, ArrayList var2) {
      ListIterator var3 = var2.listIterator();

      while(var3.hasNext()) {
         Optimizable var4 = (Optimizable)var3.next();
         if (var4.hasTableNumber() && var4.getTableNumber() == var1) {
            var3.remove();
         }
      }

   }

   public void addUnorderedOptimizable(Optimizable var1) {
      this.unorderedOptimizables.add(var1);
   }

   public void copy(RowOrdering var1) {
      RowOrderingImpl var2 = (RowOrderingImpl)var1;
      var2.ordering.clear();
      var2.currentColumnOrdering = null;
      var2.unorderedOptimizables.clear();

      for(int var3 = 0; var3 < this.unorderedOptimizables.size(); ++var3) {
         var2.unorderedOptimizables.add((Optimizable)this.unorderedOptimizables.get(var3));
      }

      var2.alwaysOrderedOptimizables.clear();

      for(int var5 = 0; var5 < this.alwaysOrderedOptimizables.size(); ++var5) {
         var2.alwaysOrderedOptimizables.add((Optimizable)this.alwaysOrderedOptimizables.get(var5));
      }

      for(int var6 = 0; var6 < this.ordering.size(); ++var6) {
         ColumnOrdering var4 = (ColumnOrdering)this.ordering.get(var6);
         var2.ordering.add(var4.cloneMe());
         if (var4 == this.currentColumnOrdering) {
            var2.rememberCurrentColumnOrdering(var6);
         }
      }

      var2.columnsAlwaysOrdered = null;
      if (this.columnsAlwaysOrdered != null) {
         var2.columnsAlwaysOrdered = this.columnsAlwaysOrdered.cloneMe();
      }

   }

   private void rememberCurrentColumnOrdering(int var1) {
      this.currentColumnOrdering = (ColumnOrdering)this.ordering.get(var1);
   }

   public String toString() {
      Object var1 = null;
      return (String)var1;
   }

   private boolean unorderedOptimizablesOtherThan(Optimizable var1) {
      for(int var2 = 0; var2 < this.unorderedOptimizables.size(); ++var2) {
         Optimizable var3 = (Optimizable)this.unorderedOptimizables.get(var2);
         if (var3 != var1) {
            return true;
         }
      }

      return false;
   }
}
