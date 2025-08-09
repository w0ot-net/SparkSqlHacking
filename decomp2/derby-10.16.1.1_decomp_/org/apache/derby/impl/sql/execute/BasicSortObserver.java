package org.apache.derby.impl.sql.execute;

import java.util.Vector;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class BasicSortObserver implements SortObserver {
   protected boolean doClone;
   protected boolean distinct;
   private boolean reuseWrappers;
   private ExecRow execRow;
   private Vector vector;

   public BasicSortObserver(boolean var1, boolean var2, ExecRow var3, boolean var4) {
      this.doClone = var1;
      this.distinct = var2;
      this.execRow = var3;
      this.reuseWrappers = var4;
      this.vector = new Vector();
   }

   public DataValueDescriptor[] insertNonDuplicateKey(DataValueDescriptor[] var1) throws StandardException {
      return this.doClone ? this.getClone(var1) : var1;
   }

   public DataValueDescriptor[] insertDuplicateKey(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      return this.distinct ? (DataValueDescriptor[])null : (this.doClone ? this.getClone(var1) : var1);
   }

   public void addToFreeList(DataValueDescriptor[] var1, int var2) {
      if (this.reuseWrappers && this.vector.size() < var2) {
         this.vector.addElement(var1);
      }

   }

   public DataValueDescriptor[] getArrayClone() throws StandardException {
      int var1 = this.vector.size();
      if (var1 > 0) {
         DataValueDescriptor[] var2 = (DataValueDescriptor[])this.vector.elementAt(var1 - 1);
         this.vector.removeElementAt(var1 - 1);
         return var2;
      } else {
         return this.execRow.getRowArrayClone();
      }
   }

   private DataValueDescriptor[] getClone(DataValueDescriptor[] var1) {
      DataValueDescriptor[] var2 = new DataValueDescriptor[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = var1[var3].cloneValue(true);
      }

      return var2;
   }

   public boolean deferred() {
      return false;
   }

   public boolean deferrable() {
      return false;
   }

   public void rememberDuplicate(DataValueDescriptor[] var1) throws StandardException {
   }
}
