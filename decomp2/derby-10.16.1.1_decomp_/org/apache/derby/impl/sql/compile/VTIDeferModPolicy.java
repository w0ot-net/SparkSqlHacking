package org.apache.derby.impl.sql.compile;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.DeferModification;

class VTIDeferModPolicy implements Visitor {
   private boolean deferred = false;
   private DeferModification deferralControl;
   private int statementType;
   private int tableNumber;
   private final HashSet columns = new HashSet();

   public static boolean deferIt(int var0, FromVTI var1, String[] var2, QueryTreeNode var3) throws StandardException {
      try {
         int var5 = var1.getResultSetType();
         if ((var0 == 2 || var0 == 3) && var5 == 1003) {
            return false;
         } else {
            Object var4 = var1.getDeferralControl();
            if (var4 == null) {
               String var6 = var1.getMethodCall().getJavaClassName();
               var4 = new DefaultVTIModDeferPolicy(var6, 1005 == var5);
            }

            if (((DeferModification)var4).alwaysDefer(var0)) {
               return true;
            } else if (var3 == null && var0 != 2) {
               return false;
            } else {
               VTIDeferModPolicy var10 = new VTIDeferModPolicy(var1, var2, (DeferModification)var4, var0);
               if (var3 != null) {
                  var3.accept(var10);
               }

               if (var0 == 2) {
                  for(String var8 : var10.columns) {
                     if (((DeferModification)var4).columnRequiresDefer(var0, var8, false)) {
                        return true;
                     }
                  }
               }

               return var10.deferred;
            }
         }
      } catch (SQLException var9) {
         throw StandardException.unexpectedUserException(var9);
      }
   }

   private VTIDeferModPolicy(FromVTI var1, String[] var2, DeferModification var3, int var4) {
      this.deferralControl = var3;
      this.statementType = var4;
      this.tableNumber = var1.getTableNumber();
      if (var4 == 2 && var2 != null) {
         this.columns.addAll(Arrays.asList(var2));
      }

   }

   public Visitable visit(Visitable var1) throws StandardException {
      try {
         if (var1 instanceof ColumnReference var8 && this.statementType != 1) {
            if (var8.getTableNumber() == this.tableNumber) {
               String var9 = var8.getColumnName();
               if (this.statementType == 3) {
                  if (this.columns.add(var9) && this.deferralControl.columnRequiresDefer(this.statementType, var9, true)) {
                     this.deferred = true;
                  }
               } else if (this.statementType == 2 && this.columns.remove(var9) && this.deferralControl.columnRequiresDefer(this.statementType, var9, true)) {
                  this.deferred = true;
               }
            }
         } else if (var1 instanceof SelectNode var2) {
            FromList var3 = var2.getFromList();

            for(int var4 = 0; var4 < var3.size(); ++var4) {
               FromTable var5 = (FromTable)var3.elementAt(var4);
               if (var5 instanceof FromBaseTable) {
                  TableDescriptor var6 = var5.getTableDescriptor();
                  if (this.deferralControl.subselectRequiresDefer(this.statementType, var6.getSchemaName(), var6.getName())) {
                     this.deferred = true;
                  }
               } else if (var5 instanceof FromVTI) {
                  FromVTI var10 = (FromVTI)var5;
                  if (this.deferralControl.subselectRequiresDefer(this.statementType, var10.getMethodCall().getJavaClassName())) {
                     this.deferred = true;
                  }
               }
            }
         }

         return var1;
      } catch (SQLException var7) {
         throw StandardException.unexpectedUserException(var7);
      }
   }

   public boolean stopTraversal() {
      return this.deferred;
   }

   public boolean skipChildren(Visitable var1) {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }
}
