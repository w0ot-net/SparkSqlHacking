package org.apache.derby.impl.io.vfmem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.derby.io.StorageRandomAccessFile;

public class VirtualRandomAccessFile implements StorageRandomAccessFile {
   private final DataStoreEntry entry;
   private final boolean _readOnly;
   private long fp;
   private final BlockedByteArrayInputStream bIn;
   private final DataInputStream dIs;
   private final BlockedByteArrayOutputStream bOut;
   private final DataOutputStream dOs;

   public VirtualRandomAccessFile(DataStoreEntry var1, boolean var2) throws FileNotFoundException {
      this.entry = var1;
      this._readOnly = var2;
      this.bIn = var1.getInputStream();
      this.bIn.setPosition(0L);
      this.dIs = new DataInputStream(this.bIn);
      if (var2) {
         this.bOut = null;
         this.dOs = null;
      } else {
         this.bOut = var1.getOutputStream(true);
         this.bOut.setPosition(0L);
         this.dOs = new DataOutputStream(this.bOut);
      }

   }

   public VirtualRandomAccessFile clone() {
      try {
         return new VirtualRandomAccessFile(this.entry, this._readOnly);
      } catch (IOException var2) {
         throw new RuntimeException(var2.getMessage(), var2);
      }
   }

   public void close() throws IOException {
      this.dIs.close();
      if (this.dOs != null) {
         this.dOs.close();
      }

      this.fp = Long.MIN_VALUE;
   }

   public long getFilePointer() {
      return this.fp;
   }

   public long length() {
      return this.entry.length();
   }

   public void seek(long var1) throws IOException {
      if (var1 < 0L) {
         throw new IOException("Negative position: " + var1);
      } else {
         this.fp = var1;
         this.bIn.setPosition(var1);
         if (this.bOut != null) {
            this.bOut.setPosition(var1);
         }

      }
   }

   public void setLength(long var1) {
      if (this.bOut == null) {
         throw new NullPointerException();
      } else {
         this.entry.setLength(var1);
         if (var1 < this.fp) {
            this.fp = var1;
         }

      }
   }

   public void sync() {
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      int var4 = this.bIn.read(var1, var2, var3);
      this.fp = this.bIn.getPosition();
      return var4;
   }

   public void readFully(byte[] var1) throws IOException {
      this.readFully(var1, 0, var1.length);
   }

   public void readFully(byte[] var1, int var2, int var3) throws IOException {
      this.dIs.readFully(var1, var2, var3);
      this.fp = this.bIn.getPosition();
   }

   public int skipBytes(int var1) {
      if (var1 <= 0) {
         return 0;
      } else {
         long var2 = Math.min((long)var1, this.entry.length() - this.fp);
         this.fp += var2;
         return (int)var2;
      }
   }

   public boolean readBoolean() throws IOException {
      boolean var1 = this.dIs.readBoolean();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public byte readByte() throws IOException {
      byte var1 = this.dIs.readByte();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public int readUnsignedByte() throws IOException {
      int var1 = this.dIs.readUnsignedByte();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public short readShort() throws IOException {
      short var1 = this.dIs.readShort();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public int readUnsignedShort() throws IOException {
      int var1 = this.dIs.readUnsignedShort();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public char readChar() throws IOException {
      char var1 = this.dIs.readChar();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public int readInt() throws IOException {
      int var1 = this.dIs.readInt();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public long readLong() throws IOException {
      long var1 = this.dIs.readLong();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public float readFloat() throws IOException {
      float var1 = this.dIs.readFloat();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public double readDouble() throws IOException {
      double var1 = this.dIs.readDouble();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public String readLine() throws IOException {
      throw new UnsupportedOperationException("readLine");
   }

   public String readUTF() throws IOException {
      String var1 = this.dIs.readUTF();
      this.fp = this.bIn.getPosition();
      return var1;
   }

   public void write(int var1) throws IOException {
      this.dOs.write(var1);
      this.fp = this.bOut.getPosition();
   }

   public void write(byte[] var1) throws IOException {
      this.write(var1, 0, var1.length);
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.dOs.write(var1, var2, var3);
      this.fp = this.bOut.getPosition();
   }

   public void writeBoolean(boolean var1) throws IOException {
      this.dOs.writeBoolean(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeByte(int var1) throws IOException {
      this.dOs.writeByte(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeShort(int var1) throws IOException {
      this.dOs.writeShort(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeChar(int var1) throws IOException {
      this.dOs.writeChar(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeInt(int var1) throws IOException {
      this.dOs.writeInt(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeLong(long var1) throws IOException {
      this.dOs.writeLong(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeFloat(float var1) throws IOException {
      this.dOs.writeFloat(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeDouble(double var1) throws IOException {
      this.dOs.writeDouble(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeBytes(String var1) throws IOException {
      this.dOs.writeBytes(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeChars(String var1) throws IOException {
      this.dOs.writeChars(var1);
      this.fp = this.bOut.getPosition();
   }

   public void writeUTF(String var1) throws IOException {
      this.dOs.writeUTF(var1);
      this.fp = this.bOut.getPosition();
   }
}
