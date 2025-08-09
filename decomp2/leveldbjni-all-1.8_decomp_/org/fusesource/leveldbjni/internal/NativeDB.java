package org.fusesource.leveldbjni.internal;

import java.io.File;
import java.io.IOException;
import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;
import org.fusesource.hawtjni.runtime.MethodFlag;

public class NativeDB extends NativeObject {
   public static final Library LIBRARY = new Library("leveldbjni", NativeDB.class);

   public void delete() {
      this.assertAllocated();
      NativeDB.DBJNI.delete(this.self);
      this.self = 0L;
   }

   private NativeDB(long self) {
      super(self);
   }

   static void checkStatus(long s) throws DBException {
      NativeStatus status = new NativeStatus(s);

      try {
         if (!status.isOk()) {
            throw new DBException(status.toString(), status.isNotFound());
         }
      } finally {
         status.delete();
      }

   }

   static void checkArgNotNull(Object value, String name) {
      if (value == null) {
         throw new IllegalArgumentException("The " + name + " argument cannot be null");
      }
   }

   public static NativeDB open(NativeOptions options, File path) throws IOException, DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(path, "path");
      long[] rc = new long[1];

      try {
         checkStatus(NativeDB.DBJNI.Open(options, path.getCanonicalPath(), rc));
      } catch (IOException e) {
         if (rc[0] != 0L) {
            NativeDB.DBJNI.delete(rc[0]);
         }

         throw e;
      }

      return new NativeDB(rc[0]);
   }

   public void suspendCompactions() {
      NativeDB.DBJNI.SuspendCompactions(this.self);
   }

   public void resumeCompactions() {
      NativeDB.DBJNI.ResumeCompactions(this.self);
   }

   public void put(NativeWriteOptions options, byte[] key, byte[] value) throws DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(key, "key");
      checkArgNotNull(value, "value");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      try {
         NativeBuffer valueBuffer = NativeBuffer.create(value);

         try {
            this.put(options, keyBuffer, valueBuffer);
         } finally {
            valueBuffer.delete();
         }
      } finally {
         keyBuffer.delete();
      }

   }

   private void put(NativeWriteOptions options, NativeBuffer keyBuffer, NativeBuffer valueBuffer) throws DBException {
      this.put(options, new NativeSlice(keyBuffer), new NativeSlice(valueBuffer));
   }

   private void put(NativeWriteOptions options, NativeSlice keySlice, NativeSlice valueSlice) throws DBException {
      this.assertAllocated();
      checkStatus(NativeDB.DBJNI.Put(this.self, options, keySlice, valueSlice));
   }

   public void delete(NativeWriteOptions options, byte[] key) throws DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(key, "key");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      try {
         this.delete(options, keyBuffer);
      } finally {
         keyBuffer.delete();
      }

   }

   private void delete(NativeWriteOptions options, NativeBuffer keyBuffer) throws DBException {
      this.delete(options, new NativeSlice(keyBuffer));
   }

   private void delete(NativeWriteOptions options, NativeSlice keySlice) throws DBException {
      this.assertAllocated();
      checkStatus(NativeDB.DBJNI.Delete(this.self, options, keySlice));
   }

   public void write(NativeWriteOptions options, NativeWriteBatch updates) throws DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(updates, "updates");
      checkStatus(NativeDB.DBJNI.Write(this.self, options, updates.pointer()));
   }

   public byte[] get(NativeReadOptions options, byte[] key) throws DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(key, "key");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      byte[] var4;
      try {
         var4 = this.get(options, keyBuffer);
      } finally {
         keyBuffer.delete();
      }

      return var4;
   }

   private byte[] get(NativeReadOptions options, NativeBuffer keyBuffer) throws DBException {
      return this.get(options, new NativeSlice(keyBuffer));
   }

   private byte[] get(NativeReadOptions options, NativeSlice keySlice) throws DBException {
      this.assertAllocated();
      NativeStdString result = new NativeStdString();

      byte[] var7;
      try {
         long s = NativeDB.DBJNI.Get(this.self, options, keySlice, result.pointer());
         NativeStatus status = new NativeStatus(s);

         try {
            if (!status.isOk()) {
               if (status.isNotFound()) {
                  var7 = null;
                  return var7;
               }

               throw new DBException(status.toString(), status.isNotFound());
            }

            var7 = result.toByteArray();
         } finally {
            status.delete();
         }
      } finally {
         result.delete();
      }

      return var7;
   }

   public NativeSnapshot getSnapshot() {
      return new NativeSnapshot(NativeDB.DBJNI.GetSnapshot(this.self));
   }

   public void releaseSnapshot(NativeSnapshot snapshot) {
      checkArgNotNull(snapshot, "snapshot");
      NativeDB.DBJNI.ReleaseSnapshot(this.self, snapshot.pointer());
   }

   public NativeIterator iterator(NativeReadOptions options) {
      checkArgNotNull(options, "options");
      return new NativeIterator(NativeDB.DBJNI.NewIterator(this.self, options));
   }

   public long[] getApproximateSizes(NativeRange... ranges) {
      if (ranges == null) {
         return null;
      } else {
         long[] rc = new long[ranges.length];
         NativeRange.RangeJNI[] structs = new NativeRange.RangeJNI[ranges.length];
         if (rc.length > 0) {
            NativeBuffer range_array = NativeRange.RangeJNI.arrayCreate(ranges.length);
            boolean var9 = false;

            try {
               var9 = true;

               for(int i = 0; i < ranges.length; ++i) {
                  structs[i] = new NativeRange.RangeJNI(ranges[i]);
                  structs[i].arrayWrite(range_array.pointer(), i);
               }

               NativeDB.DBJNI.GetApproximateSizes(this.self, range_array.pointer(), ranges.length, rc);
               var9 = false;
            } finally {
               if (var9) {
                  int i = 0;

                  while(true) {
                     if (i >= ranges.length) {
                        range_array.delete();
                     } else {
                        if (structs[i] != null) {
                           structs[i].delete();
                        }

                        ++i;
                     }
                  }
               }
            }

            for(int i = 0; i < ranges.length; ++i) {
               if (structs[i] != null) {
                  structs[i].delete();
               }
            }

            range_array.delete();
         }

         return rc;
      }
   }

   public String getProperty(String name) {
      checkArgNotNull(name, "name");
      NativeBuffer keyBuffer = NativeBuffer.create(name.getBytes());

      String var4;
      try {
         byte[] property = this.getProperty(keyBuffer);
         if (property != null) {
            var4 = new String(property);
            return var4;
         }

         var4 = null;
      } finally {
         keyBuffer.delete();
      }

      return var4;
   }

   private byte[] getProperty(NativeBuffer nameBuffer) {
      return this.getProperty(new NativeSlice(nameBuffer));
   }

   private byte[] getProperty(NativeSlice nameSlice) {
      this.assertAllocated();
      NativeStdString result = new NativeStdString();

      B var3;
      try {
         if (!NativeDB.DBJNI.GetProperty(this.self, nameSlice, result.pointer())) {
            var3 = null;
            return (byte[])var3;
         }

         var3 = result.toByteArray();
      } finally {
         result.delete();
      }

      return (byte[])var3;
   }

   public void compactRange(byte[] begin, byte[] end) {
      NativeBuffer keyBuffer = NativeBuffer.create(begin);

      try {
         NativeBuffer valueBuffer = NativeBuffer.create(end);

         try {
            this.compactRange(keyBuffer, valueBuffer);
         } finally {
            if (valueBuffer != null) {
               valueBuffer.delete();
            }

         }
      } finally {
         if (keyBuffer != null) {
            keyBuffer.delete();
         }

      }

   }

   private void compactRange(NativeBuffer beginBuffer, NativeBuffer endBuffer) {
      this.compactRange(NativeSlice.create(beginBuffer), NativeSlice.create(endBuffer));
   }

   private void compactRange(NativeSlice beginSlice, NativeSlice endSlice) {
      this.assertAllocated();
      NativeDB.DBJNI.CompactRange(this.self, beginSlice, endSlice);
   }

   public static void destroy(File path, NativeOptions options) throws IOException, DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(path, "path");
      checkStatus(NativeDB.DBJNI.DestroyDB(path.getCanonicalPath(), options));
   }

   public static void repair(File path, NativeOptions options) throws IOException, DBException {
      checkArgNotNull(options, "options");
      checkArgNotNull(path, "path");
      checkStatus(NativeDB.DBJNI.RepairDB(path.getCanonicalPath(), options));
   }

   @JniClass(
      name = "leveldb::DB",
      flags = {ClassFlag.CPP}
   )
   static class DBJNI {
      @JniMethod(
         flags = {MethodFlag.JNI, MethodFlag.POINTER_RETURN},
         cast = "jobject"
      )
      public static final native long NewGlobalRef(Object var0);

      @JniMethod(
         flags = {MethodFlag.JNI},
         cast = "jobject"
      )
      public static final native void DeleteGlobalRef(@JniArg(cast = "jobject",flags = {ArgFlag.POINTER_ARG}) long var0);

      @JniMethod(
         flags = {MethodFlag.JNI, MethodFlag.POINTER_RETURN},
         cast = "jmethodID"
      )
      public static final native long GetMethodID(@JniArg(cast = "jclass",flags = {ArgFlag.POINTER_ARG}) Class var0, String var1, String var2);

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      static final native void delete(long var0);

      @JniMethod(
         copy = "leveldb::Status",
         accessor = "leveldb::DB::Open"
      )
      static final native long Open(@JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeOptions var0, @JniArg(cast = "const char*") String var1, @JniArg(cast = "leveldb::DB**") long[] var2);

      @JniMethod(
         copy = "leveldb::Status",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long Put(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeWriteOptions var2, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var3, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var4);

      @JniMethod(
         copy = "leveldb::Status",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long Delete(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeWriteOptions var2, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var3);

      @JniMethod(
         copy = "leveldb::Status",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long Write(long var0, @JniArg(flags = {ArgFlag.BY_VALUE}) NativeWriteOptions var2, @JniArg(cast = "leveldb::WriteBatch *") long var3);

      @JniMethod(
         copy = "leveldb::Status",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long Get(long var0, @JniArg(flags = {ArgFlag.NO_OUT, ArgFlag.BY_VALUE}) NativeReadOptions var2, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var3, @JniArg(cast = "std::string *") long var4);

      @JniMethod(
         cast = "leveldb::Iterator *",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long NewIterator(long var0, @JniArg(flags = {ArgFlag.NO_OUT, ArgFlag.BY_VALUE}) NativeReadOptions var2);

      @JniMethod(
         cast = "leveldb::Snapshot *",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long GetSnapshot(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void ReleaseSnapshot(long var0, @JniArg(cast = "const leveldb::Snapshot *") long var2);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void GetApproximateSizes(long var0, @JniArg(cast = "const leveldb::Range *") long var2, int var4, @JniArg(cast = "uint64_t*") long[] var5);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native boolean GetProperty(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var2, @JniArg(cast = "std::string *") long var3);

      @JniMethod(
         copy = "leveldb::Status",
         accessor = "leveldb::DestroyDB"
      )
      static final native long DestroyDB(@JniArg(cast = "const char*") String var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeOptions var1);

      @JniMethod(
         copy = "leveldb::Status",
         accessor = "leveldb::RepairDB"
      )
      static final native long RepairDB(@JniArg(cast = "const char*") String var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeOptions var1);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void CompactRange(long var0, @JniArg(flags = {ArgFlag.NO_OUT}) NativeSlice var2, @JniArg(flags = {ArgFlag.NO_OUT}) NativeSlice var3);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void SuspendCompactions(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void ResumeCompactions(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }

   public static class DBException extends IOException {
      private final boolean notFound;

      DBException(String s, boolean notFound) {
         super(s);
         this.notFound = notFound;
      }

      public boolean isNotFound() {
         return this.notFound;
      }
   }
}
