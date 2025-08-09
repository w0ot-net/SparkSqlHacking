package org.fusesource.leveldbjni.internal;

import java.io.File;
import java.io.IOException;
import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public class Util {
   public static void link(File source, File target) throws IOException {
      if (Util.UtilJNI.ON_WINDOWS == 1) {
         if (Util.UtilJNI.CreateHardLinkW(target.getCanonicalPath(), source.getCanonicalPath(), 0L) == 0) {
            throw new IOException("link failed");
         }
      } else if (Util.UtilJNI.link(source.getCanonicalPath(), target.getCanonicalPath()) != 0) {
         throw new IOException("link failed: " + strerror());
      }

   }

   static int errno() {
      return Util.UtilJNI.errno();
   }

   static String strerror() {
      return string(Util.UtilJNI.strerror(errno()));
   }

   static String string(long ptr) {
      return ptr == 0L ? null : new String((new NativeSlice(ptr, (long)Util.UtilJNI.strlen(ptr))).toByteArray());
   }

   @JniClass(
      name = "leveldb::Env",
      flags = {ClassFlag.CPP}
   )
   static class EnvJNI {
      @JniMethod(
         cast = "leveldb::Env *",
         accessor = "leveldb::Env::Default"
      )
      public static final native long Default();

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      public static final native void Schedule(long var0, @JniArg(cast = "void (*)(void*)") long var2, @JniArg(cast = "void *") long var4);

      static {
         NativeDB.LIBRARY.load();
      }
   }

   @JniClass(
      flags = {ClassFlag.CPP}
   )
   static class UtilJNI {
      @JniField(
         flags = {FieldFlag.CONSTANT},
         accessor = "1",
         conditional = "defined(_WIN32) || defined(_WIN64)"
      )
      static int ON_WINDOWS;

      @JniMethod(
         flags = {MethodFlag.CONSTANT_INITIALIZER}
      )
      private static final native void init();

      @JniMethod(
         conditional = "!defined(_WIN32) && !defined(_WIN64)"
      )
      static final native int link(@JniArg(cast = "const char*") String var0, @JniArg(cast = "const char*") String var1);

      @JniMethod(
         conditional = "defined(_WIN32) || defined(_WIN64)"
      )
      static final native int CreateHardLinkW(@JniArg(cast = "LPCWSTR",flags = {ArgFlag.POINTER_ARG, ArgFlag.UNICODE}) String var0, @JniArg(cast = "LPCWSTR",flags = {ArgFlag.POINTER_ARG, ArgFlag.UNICODE}) String var1, @JniArg(cast = "LPSECURITY_ATTRIBUTES",flags = {ArgFlag.POINTER_ARG}) long var2);

      @JniMethod(
         flags = {MethodFlag.CONSTANT_GETTER}
      )
      public static final native int errno();

      @JniMethod(
         cast = "char *"
      )
      public static final native long strerror(int var0);

      public static final native int strlen(@JniArg(cast = "const char *") long var0);

      static {
         NativeDB.LIBRARY.load();
         init();
      }
   }
}
