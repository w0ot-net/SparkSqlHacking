package org.rocksdb;

import java.nio.ByteBuffer;

public abstract class AbstractRocksIterator extends RocksObject implements RocksIteratorInterface {
   final RocksObject parent_;

   protected AbstractRocksIterator(RocksObject var1, long var2) {
      super(var2);

      assert var1 != null;

      this.parent_ = var1;
   }

   public boolean isValid() {
      assert this.isOwningHandle();

      return this.isValid0(this.nativeHandle_);
   }

   public void seekToFirst() {
      assert this.isOwningHandle();

      this.seekToFirst0(this.nativeHandle_);
   }

   public void seekToLast() {
      assert this.isOwningHandle();

      this.seekToLast0(this.nativeHandle_);
   }

   public void seek(byte[] var1) {
      assert this.isOwningHandle();

      this.seek0(this.nativeHandle_, var1, var1.length);
   }

   public void seekForPrev(byte[] var1) {
      assert this.isOwningHandle();

      this.seekForPrev0(this.nativeHandle_, var1, var1.length);
   }

   public void seek(ByteBuffer var1) {
      assert this.isOwningHandle();

      if (var1.isDirect()) {
         this.seekDirect0(this.nativeHandle_, var1, var1.position(), var1.remaining());
      } else {
         this.seekByteArray0(this.nativeHandle_, var1.array(), var1.arrayOffset() + var1.position(), var1.remaining());
      }

      var1.position(var1.limit());
   }

   public void seekForPrev(ByteBuffer var1) {
      assert this.isOwningHandle();

      if (var1.isDirect()) {
         this.seekForPrevDirect0(this.nativeHandle_, var1, var1.position(), var1.remaining());
      } else {
         this.seekForPrevByteArray0(this.nativeHandle_, var1.array(), var1.arrayOffset() + var1.position(), var1.remaining());
      }

      var1.position(var1.limit());
   }

   public void next() {
      assert this.isOwningHandle();

      this.next0(this.nativeHandle_);
   }

   public void prev() {
      assert this.isOwningHandle();

      this.prev0(this.nativeHandle_);
   }

   public void refresh() throws RocksDBException {
      assert this.isOwningHandle();

      this.refresh0(this.nativeHandle_);
   }

   public void refresh(Snapshot var1) throws RocksDBException {
      assert this.isOwningHandle();

      this.refresh1(this.nativeHandle_, var1.getNativeHandle());
   }

   public void status() throws RocksDBException {
      assert this.isOwningHandle();

      this.status0(this.nativeHandle_);
   }

   protected void disposeInternal() {
      if (this.parent_.isOwningHandle()) {
         this.disposeInternal(this.nativeHandle_);
      }

   }

   abstract boolean isValid0(long var1);

   abstract void seekToFirst0(long var1);

   abstract void seekToLast0(long var1);

   abstract void next0(long var1);

   abstract void prev0(long var1);

   abstract void refresh0(long var1) throws RocksDBException;

   abstract void refresh1(long var1, long var3) throws RocksDBException;

   abstract void seek0(long var1, byte[] var3, int var4);

   abstract void seekForPrev0(long var1, byte[] var3, int var4);

   abstract void seekDirect0(long var1, ByteBuffer var3, int var4, int var5);

   abstract void seekForPrevDirect0(long var1, ByteBuffer var3, int var4, int var5);

   abstract void seekByteArray0(long var1, byte[] var3, int var4, int var5);

   abstract void seekForPrevByteArray0(long var1, byte[] var3, int var4, int var5);

   abstract void status0(long var1) throws RocksDBException;
}
