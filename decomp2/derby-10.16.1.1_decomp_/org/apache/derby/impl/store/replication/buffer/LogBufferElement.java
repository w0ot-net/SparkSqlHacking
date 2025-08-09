package org.apache.derby.impl.store.replication.buffer;

class LogBufferElement {
   private final byte[] bufferdata;
   private int position;
   private long greatestInstant;
   private boolean recycleMe;

   protected LogBufferElement(int var1) {
      this.bufferdata = new byte[var1];
      this.init();
   }

   protected void init() {
      this.position = 0;
      this.greatestInstant = 0L;
      this.recycleMe = true;
   }

   protected void appendLog(long var1, byte[] var3, int var4, int var5) {
      this.greatestInstant = var1;
      this.position = this.appendBytes(var3, var4, this.position, var5);
   }

   protected byte[] getData() {
      return this.bufferdata;
   }

   protected long getLastInstant() {
      return this.greatestInstant;
   }

   protected int freeSize() {
      return this.bufferdata.length - this.position;
   }

   protected int size() {
      return this.position;
   }

   protected boolean isRecyclable() {
      return this.recycleMe;
   }

   protected void setRecyclable(boolean var1) {
      this.recycleMe = var1;
   }

   private int appendBytes(byte[] var1, int var2, int var3, int var4) {
      System.arraycopy(var1, var2, this.bufferdata, var3, var4);
      return var3 + var4;
   }
}
