package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class GenericQualifier implements Qualifier {
   private int columnId;
   private int operator;
   private GeneratedMethod orderableGetter;
   private Activation activation;
   private boolean orderedNulls;
   private boolean unknownRV;
   private boolean negateCompareResult;
   protected int variantType;
   private DataValueDescriptor orderableCache = null;

   public GenericQualifier(int var1, int var2, GeneratedMethod var3, Activation var4, boolean var5, boolean var6, boolean var7, int var8) {
      this.columnId = var1;
      this.operator = var2;
      this.orderableGetter = var3;
      this.activation = var4;
      this.orderedNulls = var5;
      this.unknownRV = var6;
      this.negateCompareResult = var7;
      this.variantType = var8;
   }

   public int getColumnId() {
      return this.columnId;
   }

   public DataValueDescriptor getOrderable() throws StandardException {
      if (this.variantType != 0) {
         if (this.orderableCache == null) {
            this.orderableCache = (DataValueDescriptor)this.orderableGetter.invoke(this.activation);
         }

         return this.orderableCache;
      } else {
         return (DataValueDescriptor)this.orderableGetter.invoke(this.activation);
      }
   }

   public int getOperator() {
      return this.operator;
   }

   public boolean negateCompareResult() {
      return this.negateCompareResult;
   }

   public boolean getOrderedNulls() {
      return this.orderedNulls;
   }

   public boolean getUnknownRV() {
      return this.unknownRV;
   }

   public void clearOrderableCache() {
      if (this.variantType == 1 || this.variantType == 0) {
         this.orderableCache = null;
      }

   }

   public void reinitialize() {
      if (this.variantType != 3) {
         this.orderableCache = null;
      }

   }

   public String toString() {
      return "";
   }
}
