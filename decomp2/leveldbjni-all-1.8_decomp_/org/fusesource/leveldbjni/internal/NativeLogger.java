package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public abstract class NativeLogger extends NativeObject {
   private long globalRef;

   public NativeLogger() {
      super(NativeLogger.LoggerJNI.create());

      try {
         this.globalRef = NativeDB.DBJNI.NewGlobalRef(this);
         if (this.globalRef == 0L) {
            throw new RuntimeException("jni call failed: NewGlobalRef");
         } else {
            LoggerJNI struct = new LoggerJNI();
            struct.log_method = NativeDB.DBJNI.GetMethodID(this.getClass(), "log", "(Ljava/lang/String;)V");
            if (struct.log_method == 0L) {
               throw new RuntimeException("jni call failed: GetMethodID");
            } else {
               struct.target = this.globalRef;
               NativeLogger.LoggerJNI.memmove(this.self, struct, (long)NativeLogger.LoggerJNI.SIZEOF);
            }
         }
      } catch (RuntimeException e) {
         this.delete();
         throw e;
      }
   }

   NativeLogger(long ptr) {
      super(ptr);
   }

   public void delete() {
      if (this.globalRef != 0L) {
         NativeDB.DBJNI.DeleteGlobalRef(this.globalRef);
         this.globalRef = 0L;
      }

   }

   public abstract void log(String var1);

   @JniClass(
      name = "JNILogger",
      flags = {ClassFlag.STRUCT, ClassFlag.CPP}
   )
   public static class LoggerJNI {
      @JniField(
         cast = "jobject",
         flags = {FieldFlag.POINTER_FIELD}
      )
      long target;
      @JniField(
         cast = "jmethodID",
         flags = {FieldFlag.POINTER_FIELD}
      )
      long log_method;
      @JniField(
         flags = {FieldFlag.CONSTANT},
         accessor = "sizeof(struct JNILogger)"
      )
      static int SIZEOF;

      @JniMethod(
         flags = {MethodFlag.CPP_NEW}
      )
      public static final native long create();

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      public static final native void memmove(@JniArg(cast = "void *") long var0, @JniArg(cast = "const void *",flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) LoggerJNI var2, @JniArg(cast = "size_t") long var3);

      @JniMethod(
         flags = {MethodFlag.CONSTANT_INITIALIZER}
      )
      private static final native void init();

      static {
         NativeDB.LIBRARY.load();
         init();
      }
   }
}
