package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public class NativeIterator extends NativeObject {
   NativeIterator(long self) {
      super(self);
   }

   public void delete() {
      this.assertAllocated();
      NativeIterator.IteratorJNI.delete(this.self);
      this.self = 0L;
   }

   public boolean isValid() {
      this.assertAllocated();
      return NativeIterator.IteratorJNI.Valid(this.self);
   }

   private void checkStatus() throws NativeDB.DBException {
      NativeDB.checkStatus(NativeIterator.IteratorJNI.status(this.self));
   }

   public void seekToFirst() {
      this.assertAllocated();
      NativeIterator.IteratorJNI.SeekToFirst(this.self);
   }

   public void seekToLast() {
      this.assertAllocated();
      NativeIterator.IteratorJNI.SeekToLast(this.self);
   }

   public void seek(byte[] key) throws NativeDB.DBException {
      NativeDB.checkArgNotNull(key, "key");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      try {
         this.seek(keyBuffer);
      } finally {
         keyBuffer.delete();
      }

   }

   private void seek(NativeBuffer keyBuffer) throws NativeDB.DBException {
      this.seek(new NativeSlice(keyBuffer));
   }

   private void seek(NativeSlice keySlice) throws NativeDB.DBException {
      this.assertAllocated();
      NativeIterator.IteratorJNI.Seek(this.self, keySlice);
      this.checkStatus();
   }

   public void next() throws NativeDB.DBException {
      this.assertAllocated();
      NativeIterator.IteratorJNI.Next(this.self);
      this.checkStatus();
   }

   public void prev() throws NativeDB.DBException {
      this.assertAllocated();
      NativeIterator.IteratorJNI.Prev(this.self);
      this.checkStatus();
   }

   public byte[] key() throws NativeDB.DBException {
      this.assertAllocated();
      long slice_ptr = NativeIterator.IteratorJNI.key(this.self);
      this.checkStatus();

      byte[] var4;
      try {
         NativeSlice slice = new NativeSlice();
         slice.read(slice_ptr, 0);
         var4 = slice.toByteArray();
      } finally {
         NativeSlice.SliceJNI.delete(slice_ptr);
      }

      return var4;
   }

   public byte[] value() throws NativeDB.DBException {
      this.assertAllocated();
      long slice_ptr = NativeIterator.IteratorJNI.value(this.self);
      this.checkStatus();

      byte[] var4;
      try {
         NativeSlice slice = new NativeSlice();
         slice.read(slice_ptr, 0);
         var4 = slice.toByteArray();
      } finally {
         NativeSlice.SliceJNI.delete(slice_ptr);
      }

      return var4;
   }

   @JniClass(
      name = "leveldb::Iterator",
      flags = {ClassFlag.CPP}
   )
   private static class IteratorJNI {
      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native boolean Valid(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void SeekToFirst(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void SeekToLast(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Seek(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var2);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Next(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Prev(long var0);

      @JniMethod(
         copy = "leveldb::Slice",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long key(long var0);

      @JniMethod(
         copy = "leveldb::Slice",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long value(long var0);

      @JniMethod(
         copy = "leveldb::Status",
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native long status(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }
}
