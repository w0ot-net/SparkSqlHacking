package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class UniqueWithDuplicateNullsIndexSortObserver extends BasicSortObserver {
   private final String indexOrConstraintName;
   private final String tableName;
   private final boolean deferrable;
   private final boolean deferred;
   private final LanguageConnectionContext lcc;
   private final UUID constraintId;
   private BackingStoreHashtable deferredDuplicates;

   public UniqueWithDuplicateNullsIndexSortObserver(LanguageConnectionContext var1, UUID var2, boolean var3, boolean var4, boolean var5, String var6, ExecRow var7, boolean var8, String var9) {
      super(var3, false, var7, var8);
      this.lcc = var1;
      this.constraintId = var2;
      this.deferrable = var4;
      this.deferred = var5;
      this.indexOrConstraintName = var6;
      this.tableName = var9;
   }

   public DataValueDescriptor[] insertDuplicateKey(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (var1[var3].isNull()) {
            return super.insertDuplicateKey(var1, var2);
         }
      }

      Object var4 = null;
      StandardException var5 = StandardException.newException("23505", new Object[]{this.indexOrConstraintName, this.tableName});
      throw var5;
   }

   public boolean deferred() {
      return this.deferred;
   }

   public boolean deferrable() {
      return this.deferrable;
   }

   public void rememberDuplicate(DataValueDescriptor[] var1) throws StandardException {
      this.deferredDuplicates = DeferredConstraintsMemory.rememberDuplicate(this.lcc, this.deferredDuplicates, this.constraintId, var1);
   }
}
