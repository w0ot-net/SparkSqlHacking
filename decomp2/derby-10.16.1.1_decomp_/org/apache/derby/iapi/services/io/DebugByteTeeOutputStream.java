package org.apache.derby.iapi.services.io;

import java.io.ByteArrayInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class DebugByteTeeOutputStream extends FilterOutputStream {
   private AccessibleByteArrayOutputStream tee = new AccessibleByteArrayOutputStream(256);

   DebugByteTeeOutputStream(OutputStream var1) {
      super(var1);
   }

   public void write(int var1) throws IOException {
      this.out.write(var1);
      this.tee.write(var1);
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.out.write(var1, var2, var3);
      this.tee.write(var1, var2, var3);
   }

   void checkObject(Formatable var1) {
      ByteArrayInputStream var2 = new ByteArrayInputStream(this.tee.getInternalByteArray(), 0, this.tee.size());
      FormatIdInputStream var3 = new FormatIdInputStream(var2);
      Object var4 = null;

      try {
         Formatable var8 = (Formatable)var3.readObject();
         if (var8.equals(var1)) {
            return;
         }

         if (var8.hashCode() == System.identityHashCode(var8) && var1.hashCode() == System.identityHashCode(var1)) {
            return;
         }
      } catch (Throwable var7) {
         String var10000 = var7.toString();
         String var6 = "FormatableError:read error    : " + var10000 + "\nFormatableError:class written : " + var1.getClass();
         var6 = var6 + (var4 == null ? "FormatableError:read back as null" : "FormatableError:class read    : " + var4.getClass());
         var6 = var6 + "FormatableError:write id      : " + FormatIdUtil.formatIdToString(var1.getTypeFormatId());
         if (var4 != null) {
            var6 = var6 + "FormatableError:read id       : " + FormatIdUtil.formatIdToString(((Formatable)var4).getTypeFormatId());
         }

         System.out.println(var6);
         var7.printStackTrace(System.out);
      }

   }
}
