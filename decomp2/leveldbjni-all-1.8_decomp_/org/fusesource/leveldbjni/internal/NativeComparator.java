package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public abstract class NativeComparator extends NativeObject {
   private NativeBuffer name_buffer;
   private long globalRef;
   public static final NativeComparator BYTEWISE_COMPARATOR;

   public NativeComparator() {
      super(NativeComparator.ComparatorJNI.create());

      try {
         this.name_buffer = NativeBuffer.create(this.name());
         this.globalRef = NativeDB.DBJNI.NewGlobalRef(this);
         if (this.globalRef == 0L) {
            throw new RuntimeException("jni call failed: NewGlobalRef");
         } else {
            ComparatorJNI struct = new ComparatorJNI();
            struct.compare_method = NativeDB.DBJNI.GetMethodID(this.getClass(), "compare", "(JJ)I");
            if (struct.compare_method == 0L) {
               throw new RuntimeException("jni call failed: GetMethodID");
            } else {
               struct.target = this.globalRef;
               struct.name = this.name_buffer.pointer();
               NativeComparator.ComparatorJNI.memmove(this.self, struct, (long)NativeComparator.ComparatorJNI.SIZEOF);
            }
         }
      } catch (RuntimeException e) {
         this.delete();
         throw e;
      }
   }

   NativeComparator(long ptr) {
      super(ptr);
   }

   public void delete() {
      if (this.name_buffer != null) {
         this.name_buffer.delete();
         this.name_buffer = null;
      }

      if (this.globalRef != 0L) {
         NativeDB.DBJNI.DeleteGlobalRef(this.globalRef);
         this.globalRef = 0L;
      }

   }

   private int compare(long ptr1, long ptr2) {
      NativeSlice s1 = new NativeSlice();
      s1.read(ptr1, 0);
      NativeSlice s2 = new NativeSlice();
      s2.read(ptr2, 0);
      return this.compare(s1.toByteArray(), s2.toByteArray());
   }

   public abstract int compare(byte[] var1, byte[] var2);

   public abstract String name();

   static {
      BYTEWISE_COMPARATOR = new NativeComparator(NativeComparator.ComparatorJNI.BYTEWISE_COMPARATOR) {
         public void delete() {
         }

         public int compare(byte[] key1, byte[] key2) {
            throw new UnsupportedOperationException();
         }

         public String name() {
            throw new UnsupportedOperationException();
         }
      };
   }

   @JniClass(
      name = "JNIComparator",
      flags = {ClassFlag.STRUCT, ClassFlag.CPP}
   )
   public static class ComparatorJNI {
      @JniField(
         cast = "jobject",
         flags = {FieldFlag.POINTER_FIELD}
      )
      long target;
      @JniField(
         cast = "jmethodID",
         flags = {FieldFlag.POINTER_FIELD}
      )
      long compare_method;
      @JniField(
         cast = "const char *"
      )
      long name;
      @JniField(
         flags = {FieldFlag.CONSTANT},
         accessor = "sizeof(struct JNIComparator)"
      )
      static int SIZEOF;
      @JniField(
         flags = {FieldFlag.CONSTANT},
         cast = "const Comparator*",
         accessor = "leveldb::BytewiseComparator()"
      )
      private static long BYTEWISE_COMPARATOR;

      @JniMethod(
         flags = {MethodFlag.CPP_NEW}
      )
      public static final native long create();

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      public static final native void memmove(@JniArg(cast = "void *") long var0, @JniArg(cast = "const void *",flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) ComparatorJNI var2, @JniArg(cast = "size_t") long var3);

      public static final native void memmove(@JniArg(cast = "void *",flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) ComparatorJNI var0, @JniArg(cast = "const void *") long var1, @JniArg(cast = "size_t") long var3);

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
