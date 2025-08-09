package org.apache.derby.iapi.sql.dictionary;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.IndexDescriptor;
import org.apache.derby.catalog.types.IndexDescriptorImpl;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionContext;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class IndexRowGenerator implements IndexDescriptor, Formatable {
   private IndexDescriptor id;
   private ExecutionFactory ef;

   public IndexRowGenerator(String var1, boolean var2, boolean var3, boolean var4, boolean var5, int[] var6, boolean[] var7, int var8) {
      this.id = new IndexDescriptorImpl(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public IndexRowGenerator(IndexDescriptor var1) {
      this.id = var1;
   }

   public ExecIndexRow getIndexRowTemplate() {
      return this.getExecutionFactory().getIndexableRow(this.id.baseColumnPositions().length + 1);
   }

   public ExecIndexRow getNullIndexRow(ColumnDescriptorList var1, RowLocation var2) throws StandardException {
      int[] var3 = this.id.baseColumnPositions();
      ExecIndexRow var4 = this.getIndexRowTemplate();

      for(int var5 = 0; var5 < var3.length; ++var5) {
         DataTypeDescriptor var6 = var1.elementAt(var3[var5] - 1).getType();
         var4.setColumn(var5 + 1, var6.getNull());
      }

      var4.setColumn(var3.length + 1, var2);
      return var4;
   }

   public void getIndexRow(ExecRow var1, RowLocation var2, ExecIndexRow var3, FormatableBitSet var4) throws StandardException {
      int[] var5 = this.id.baseColumnPositions();
      int var6 = var5.length;
      if (var4 == null) {
         for(int var7 = 0; var7 < var6; ++var7) {
            var3.setColumn(var7 + 1, var1.getColumn(var5[var7]));
         }
      } else {
         for(int var11 = 0; var11 < var6; ++var11) {
            int var8 = var5[var11];
            int var9 = 0;

            for(int var10 = 1; var10 <= var8; ++var10) {
               if (var4.get(var10)) {
                  ++var9;
               }
            }

            var3.setColumn(var11 + 1, var1.getColumn(var9));
         }
      }

      var3.setColumn(var6 + 1, var2);
   }

   public int[] getColumnCollationIds(ColumnDescriptorList var1) throws StandardException {
      int[] var2 = this.id.baseColumnPositions();
      int[] var3 = new int[var2.length + 1];

      for(int var4 = 0; var4 < var2.length; ++var4) {
         var3[var4] = var1.elementAt(var2[var4] - 1).getType().getCollationType();
      }

      var3[var3.length - 1] = 0;
      return var3;
   }

   public IndexDescriptor getIndexDescriptor() {
      return this.id;
   }

   public IndexRowGenerator() {
   }

   public boolean isUniqueWithDuplicateNulls() {
      return this.id.isUniqueWithDuplicateNulls();
   }

   public boolean hasDeferrableChecking() {
      return this.id.hasDeferrableChecking();
   }

   public boolean isUniqueDeferrable() {
      return this.id.isUniqueDeferrable();
   }

   public boolean isUnique() {
      return this.id.isUnique();
   }

   public int[] baseColumnPositions() {
      return this.id.baseColumnPositions();
   }

   public int getKeyColumnPosition(int var1) {
      return this.id.getKeyColumnPosition(var1);
   }

   public int numberOfOrderedColumns() {
      return this.id.numberOfOrderedColumns();
   }

   public String indexType() {
      return this.id.indexType();
   }

   public String toString() {
      return this.id.toString();
   }

   public boolean isAscending(Integer var1) {
      return this.id.isAscending(var1);
   }

   public boolean isDescending(Integer var1) {
      return this.id.isDescending(var1);
   }

   public boolean[] isAscending() {
      return this.id.isAscending();
   }

   public void setBaseColumnPositions(int[] var1) {
      this.id.setBaseColumnPositions(var1);
   }

   public void setIsAscending(boolean[] var1) {
      this.id.setIsAscending(var1);
   }

   public void setNumberOfOrderedColumns(int var1) {
      this.id.setNumberOfOrderedColumns(var1);
   }

   public boolean equals(Object var1) {
      return this.id.equals(var1);
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   private ExecutionFactory getExecutionFactory() {
      if (this.ef == null) {
         ExecutionContext var1 = (ExecutionContext)getContext("ExecutionContext");
         this.ef = var1.getExecutionFactory();
      }

      return this.ef;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.id = (IndexDescriptor)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.id);
   }

   public int getTypeFormatId() {
      return 268;
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
