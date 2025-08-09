package org.apache.derby.impl.sql;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;
import org.apache.derby.shared.common.util.ArrayUtil;

public class CursorInfo implements Formatable {
   ExecCursorTableReference targetTable;
   List updateColumns;
   int updateMode;

   public CursorInfo() {
   }

   public CursorInfo(int var1, ExecCursorTableReference var2, List var3) {
      this.updateMode = var1;
      this.targetTable = var2;
      this.updateColumns = (List)(var3 == null ? var3 : new ArrayList(var3));
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.updateMode);
      var1.writeObject(this.targetTable);
      ArrayUtil.writeArray(var1, (Object[])null);
      ArrayUtil.writeArray(var1, this.updateColumns == null ? null : this.updateColumns.toArray());
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.updateMode = var1.readInt();
      this.targetTable = (ExecCursorTableReference)var1.readObject();
      ArrayUtil.readObjectArray(var1);
      int var2 = ArrayUtil.readArrayLength(var1);
      if (var2 > 0) {
         this.updateColumns = Arrays.asList(ArrayUtil.readStringArray(var1));
      }

   }

   public int getTypeFormatId() {
      return 297;
   }

   public String toString() {
      return "";
   }
}
