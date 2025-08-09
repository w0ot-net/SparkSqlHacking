package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

class NativeStdString extends NativeObject {
   public NativeStdString(long self) {
      super(self);
   }

   public NativeStdString() {
      super(NativeStdString.StdStringJNI.create());
   }

   public void delete() {
      this.assertAllocated();
      NativeStdString.StdStringJNI.delete(this.self);
      this.self = 0L;
   }

   public String toString() {
      return new String(this.toByteArray());
   }

   public long length() {
      this.assertAllocated();
      return NativeStdString.StdStringJNI.length(this.self);
   }

   public byte[] toByteArray() {
      long l = this.length();
      if (l > 2147483647L) {
         throw new ArrayIndexOutOfBoundsException("Native string is larger than the maximum Java array");
      } else {
         byte[] rc = new byte[(int)l];
         NativeBuffer.NativeBufferJNI.buffer_copy(NativeStdString.StdStringJNI.c_str_ptr(this.self), 0L, rc, 0L, (long)rc.length);
         return rc;
      }
   }

   @JniClass(
      name = "std::string",
      flags = {ClassFlag.CPP}
   )
   private static class StdStringJNI {
      @JniMethod(
         flags = {MethodFlag.CPP_NEW}
      )
      public static final native long create();

      @JniMethod(
         flags = {MethodFlag.CPP_NEW}
      )
      public static final native long create(String var0);

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      static final native void delete(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD},
         accessor = "c_str",
         cast = "const char*"
      )
      public static final native long c_str_ptr(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD},
         cast = "size_t"
      )
      public static final native long length(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }
}
