package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

class NativeStatus extends NativeObject {
   public NativeStatus(long self) {
      super(self);
   }

   public void delete() {
      this.assertAllocated();
      NativeStatus.StatusJNI.delete(this.self);
      this.self = 0L;
   }

   public boolean isOk() {
      this.assertAllocated();
      return NativeStatus.StatusJNI.ok(this.self);
   }

   public boolean isNotFound() {
      this.assertAllocated();
      return NativeStatus.StatusJNI.IsNotFound(this.self);
   }

   public String toString() {
      this.assertAllocated();
      long strptr = NativeStatus.StatusJNI.ToString(this.self);
      if (strptr == 0L) {
         return null;
      } else {
         NativeStdString rc = new NativeStdString(strptr);

         String var4;
         try {
            var4 = rc.toString();
         } finally {
            rc.delete();
         }

         return var4;
      }
   }

   @JniClass(
      name = "leveldb::Status",
      flags = {ClassFlag.CPP}
   )
   static class StatusJNI {
      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      public static final native boolean ok(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      public static final native boolean IsNotFound(long var0);

      @JniMethod(
         copy = "std::string",
         flags = {MethodFlag.CPP_METHOD}
      )
      public static final native long ToString(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }
}
