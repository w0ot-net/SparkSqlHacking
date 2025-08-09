package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Enumeration;
import java.util.Hashtable;

public class FormatableHashtable extends Hashtable implements Formatable {
   public Object put(Object var1, Object var2) {
      return var2 == null ? this.remove(var1) : super.put(var1, var2);
   }

   public void putInt(Object var1, int var2) {
      super.put(var1, new FormatableIntHolder(var2));
   }

   public int getInt(Object var1) {
      return ((FormatableIntHolder)this.get(var1)).getInt();
   }

   public void putLong(Object var1, long var2) {
      super.put(var1, new FormatableLongHolder(var2));
   }

   public long getLong(Object var1) {
      return ((FormatableLongHolder)this.get(var1)).getLong();
   }

   public void putBoolean(Object var1, boolean var2) {
      this.putInt(var1, var2 ? 1 : 0);
   }

   public boolean getBoolean(Object var1) {
      return this.getInt(var1) != 0;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.size());
      Enumeration var2 = this.keys();

      while(var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         var1.writeObject(var3);
         var1.writeObject(this.get(var3));
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      for(int var2 = var1.readInt(); var2 > 0; --var2) {
         super.put(var1.readObject(), var1.readObject());
      }

   }

   public int getTypeFormatId() {
      return 313;
   }
}
