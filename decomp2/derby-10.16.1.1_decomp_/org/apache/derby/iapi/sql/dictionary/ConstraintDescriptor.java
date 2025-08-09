package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public abstract class ConstraintDescriptor extends UniqueTupleDescriptor implements Provider, Dependent {
   public static final int ENABLED = 1;
   public static final int DISABLED = 2;
   public static final int ALL = 3;
   public static final int SYSCONSTRAINTS_STATE_FIELD = 6;
   TableDescriptor table;
   final String constraintName;
   private boolean deferrable;
   private boolean initiallyDeferred;
   private boolean enforced;
   private final int[] referencedColumns;
   final UUID constraintId;
   private final SchemaDescriptor schemaDesc;
   private ColumnDescriptorList colDL;

   ConstraintDescriptor(DataDictionary var1, TableDescriptor var2, String var3, boolean var4, boolean var5, int[] var6, UUID var7, SchemaDescriptor var8, boolean var9) {
      super(var1);
      this.table = var2;
      this.constraintName = var3;
      this.deferrable = var4;
      this.initiallyDeferred = var5;
      this.referencedColumns = var6;
      this.constraintId = var7;
      this.schemaDesc = var8;
      this.enforced = var9;
   }

   public UUID getTableId() {
      return this.table.getUUID();
   }

   public UUID getUUID() {
      return this.constraintId;
   }

   public String getConstraintName() {
      return this.constraintName;
   }

   public abstract int getConstraintType();

   public abstract UUID getConglomerateId();

   public String getConstraintText() {
      return null;
   }

   public boolean deferrable() {
      return this.deferrable;
   }

   public void setDeferrable(boolean var1) {
      this.deferrable = var1;
   }

   public boolean initiallyDeferred() {
      return this.initiallyDeferred;
   }

   public void setInitiallyDeferred(boolean var1) {
      this.initiallyDeferred = var1;
   }

   public int[] getReferencedColumns() {
      return ArrayUtil.copy(this.referencedColumns);
   }

   public abstract boolean hasBackingIndex();

   public SchemaDescriptor getSchemaDescriptor() {
      return this.schemaDesc;
   }

   public int[] getKeyColumns() {
      return this.getReferencedColumns();
   }

   public boolean enforced() {
      return this.enforced;
   }

   public void setEnforced(boolean var1) {
      this.enforced = var1;
   }

   public boolean isReferenced() {
      return false;
   }

   public int getReferenceCount() {
      return 0;
   }

   public abstract boolean needsToFire(int var1, int[] var2);

   public TableDescriptor getTableDescriptor() {
      return this.table;
   }

   public ColumnDescriptorList getColumnDescriptors() throws StandardException {
      if (this.colDL == null) {
         this.colDL = new ColumnDescriptorList();
         int[] var1 = this.getReferencedColumns();

         for(int var2 = 0; var2 < var1.length; ++var2) {
            this.colDL.add(this.table.getColumnDescriptor(var1[var2]));
         }
      }

      return this.colDL;
   }

   public boolean areColumnsComparable(ColumnDescriptorList var1) throws StandardException {
      ColumnDescriptorList var4 = this.getColumnDescriptors();
      if (var1.size() != var4.size()) {
         return false;
      } else {
         int var5 = var4.size();
         int var6 = var1.size();

         int var7;
         for(var7 = 0; var7 < var5 && var7 < var6; ++var7) {
            ColumnDescriptor var2 = var4.elementAt(var7);
            ColumnDescriptor var3 = var1.elementAt(var7);
            if (!var2.getType().isExactTypeAndLengthMatch(var3.getType())) {
               break;
            }
         }

         return var7 == var5 && var7 == var6;
      }
   }

   public boolean columnIntersects(int[] var1) {
      return doColumnsIntersect(this.getReferencedColumns(), var1);
   }

   static boolean doColumnsIntersect(int[] var0, int[] var1) {
      if (var0 != null && var1 != null) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            for(int var3 = 0; var3 < var0.length; ++var3) {
               if (var1[var2] == var0[var3]) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public String toString() {
      return "";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(208);
   }

   public String getObjectName() {
      return this.constraintName;
   }

   public UUID getObjectID() {
      return this.constraintId;
   }

   public String getClassType() {
      return "Constraint";
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      DependencyManager var4 = this.getDataDictionary().getDependencyManager();
      switch (var2) {
         case 20:
         case 21:
         case 23:
         case 29:
         case 30:
         case 34:
         case 44:
         case 47:
         case 48:
            return;
         case 22:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 31:
         case 32:
         case 33:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 45:
         case 46:
         default:
            throw StandardException.newException("X0Y25.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), "CONSTRAINT", this.constraintName});
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      if (var1 != 44 && var1 != 47) {
         if (var1 != 21 && var1 != 20 && var1 != 29 && var1 != 30 && var1 != 23 && var1 != 48 && var1 != 34) {
         }

      } else {
         ConglomerateDescriptor var3 = this.drop(var2, true);
         this.getDataDictionary().getDependencyManager().invalidateFor(this.table, 12, var2);
         var2.getLastActivation().addWarning(StandardException.newWarning("01500", new Object[]{this.getConstraintName(), this.getTableDescriptor().getName()}));
         if (var3 != null) {
         }

      }
   }

   public ConglomerateDescriptor drop(LanguageConnectionContext var1, boolean var2) throws StandardException {
      DataDictionary var3 = this.getDataDictionary();
      TransactionController var4 = var1.getTransactionExecute();
      if (var2) {
         DependencyManager var5 = var3.getDependencyManager();
         var5.clearDependencies(var1, this);
      }

      var3.dropConstraintDescriptor(this, var4);
      ConglomerateDescriptor var11 = null;
      if (this.hasBackingIndex()) {
         ConglomerateDescriptor[] var6 = var3.getConglomerateDescriptors(this.getConglomerateId());

         for(ConglomerateDescriptor var10 : var6) {
            if (var10.isConstraint()) {
               var11 = var10.drop(var1, this.table);
               break;
            }
         }
      }

      this.table.removeConstraintDescriptor(this);
      return var11;
   }

   public String getDescriptorName() {
      return this.constraintName;
   }

   public String getDescriptorType() {
      return "Constraint";
   }
}
