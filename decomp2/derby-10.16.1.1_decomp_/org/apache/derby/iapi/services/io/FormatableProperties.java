package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Enumeration;
import java.util.Properties;

public class FormatableProperties extends Properties implements Formatable {
   public FormatableProperties() {
      this((Properties)null);
   }

   public FormatableProperties(Properties var1) {
      super(var1);
   }

   public void clearDefaults() {
      this.defaults = null;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.size());
      Enumeration var2 = this.keys();

      while(var2.hasMoreElements()) {
         String var3 = (String)var2.nextElement();
         var1.writeUTF(var3);
         var1.writeUTF(this.getProperty(var3));
      }

   }

   public void readExternal(ObjectInput var1) throws IOException {
      for(int var2 = var1.readInt(); var2 > 0; --var2) {
         this.put(var1.readUTF(), var1.readUTF());
      }

   }

   public int getTypeFormatId() {
      return 271;
   }
}
