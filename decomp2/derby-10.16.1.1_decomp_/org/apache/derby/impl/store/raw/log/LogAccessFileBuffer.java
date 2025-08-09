package org.apache.derby.impl.store.raw.log;

final class LogAccessFileBuffer {
   protected byte[] buffer;
   protected int bytes_free;
   protected int position;
   protected int length;
   protected long greatest_instant;
   LogAccessFileBuffer next;
   LogAccessFileBuffer prev;

   public LogAccessFileBuffer(int var1) {
      this.buffer = new byte[var1];
      this.prev = null;
      this.next = null;
      this.init(0);
   }

   public void init(int var1) {
      this.length = this.buffer.length - var1;
      this.bytes_free = this.length;
      this.position = var1;
      this.greatest_instant = -1L;
   }
}
