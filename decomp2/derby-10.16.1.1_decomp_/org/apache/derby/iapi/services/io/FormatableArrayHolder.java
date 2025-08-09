package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.derby.shared.common.util.ArrayUtil;

public class FormatableArrayHolder implements Formatable {
   private Object[] array;

   public FormatableArrayHolder() {
   }

   public FormatableArrayHolder(Object[] var1) {
      this.setArray(var1);
   }

   public void setArray(Object[] var1) {
      this.array = ArrayUtil.copy(var1);
   }

   public Object[] getArray(Class var1) {
      return Arrays.copyOf(this.array, this.array.length, var1);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      ArrayUtil.writeArrayLength(var1, this.array);
      ArrayUtil.writeArrayItems(var1, this.array);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.array = new Object[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.array);
   }

   public int getTypeFormatId() {
      return 270;
   }
}
