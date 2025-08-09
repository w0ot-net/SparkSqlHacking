package org.apache.derby.iapi.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class ExecRowBuilder implements Formatable {
   private boolean indexable;
   private Object[] template;
   private int[] columns;
   private int count;
   private int maxColumnNumber;

   public ExecRowBuilder(int var1, boolean var2) {
      this.template = new Object[var1];
      this.columns = new int[var1];
      this.indexable = var2;
   }

   public ExecRowBuilder() {
   }

   public void setColumn(int var1, Object var2) {
      this.template[this.count] = var2;
      this.columns[this.count] = var1;
      ++this.count;
      this.maxColumnNumber = Math.max(this.maxColumnNumber, var1);
   }

   public ExecRow build(ExecutionFactory var1) throws StandardException {
      Object var2 = this.indexable ? var1.getIndexableRow(this.maxColumnNumber) : var1.getValueRow(this.maxColumnNumber);

      for(int var3 = 0; var3 < this.count; ++var3) {
         Object var4 = this.template[var3];
         DataValueDescriptor var5 = var4 instanceof DataValueDescriptor ? ((DataValueDescriptor)var4).getNewNull() : ((DataTypeDescriptor)var4).getNull();
         ((ExecRow)var2).setColumn(this.columns[var3], var5);
      }

      return (ExecRow)var2;
   }

   public void reset(ExecRow var1) throws StandardException {
      for(int var2 = 0; var2 < this.count; ++var2) {
         int var3 = this.columns[var2];
         var1.setColumn(var3, var1.getColumn(var3).getNewNull());
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeBoolean(this.indexable);
      ArrayUtil.writeArray(var1, this.template);
      var1.writeObject(this.columns);
      var1.writeInt(this.count);
      var1.writeInt(this.maxColumnNumber);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.indexable = var1.readBoolean();
      this.template = ArrayUtil.readObjectArray(var1);
      this.columns = (int[])var1.readObject();
      this.count = var1.readInt();
      this.maxColumnNumber = var1.readInt();
   }

   public int getTypeFormatId() {
      return 279;
   }
}
