package org.apache.derby.iapi.services.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FormatIdOutputStream extends DataOutputStream implements ObjectOutput, ErrorInfo {
   public FormatIdOutputStream(OutputStream var1) {
      super(var1);
   }

   public void writeObject(Object var1) throws IOException {
      if (var1 == null) {
         FormatIdUtil.writeFormatIdInteger(this, 0);
      } else {
         if (var1 instanceof String) {
            String var2 = (String)var1;
            if (var2.length() <= 20000) {
               FormatIdUtil.writeFormatIdInteger(this, 1);
               this.writeUTF((String)var1);
               return;
            }
         }

         Object var6 = null;
         if (var1 instanceof Storable) {
            Storable var3 = (Storable)var1;
            int var4 = var3.getTypeFormatId();
            if (var4 != 2) {
               FormatIdUtil.writeFormatIdInteger(this, var4);
               boolean var5 = var3.isNull();
               this.writeBoolean(var5);
               if (!var5) {
                  var3.writeExternal(this);
               }

               return;
            }
         } else if (var1 instanceof Formatable) {
            Formatable var7 = (Formatable)var1;
            int var9 = var7.getTypeFormatId();
            if (var9 != 2) {
               FormatIdUtil.writeFormatIdInteger(this, var9);
               var7.writeExternal(this);
               return;
            }
         }

         FormatIdUtil.writeFormatIdInteger(this, 2);
         ObjectOutputStream var8 = new ObjectOutputStream(this);
         var8.writeObject(var1);
         var8.flush();
      }
   }

   public void setOutput(OutputStream var1) {
      this.out = var1;
      this.written = 0;
   }

   public String getErrorInfo() {
      return null;
   }

   public Exception getNestedException() {
      return null;
   }
}
