package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerDeState {
   protected final Kryo kryo;
   protected final Input input;
   protected final Output output;
   static final byte[] EMPTY_BUFFER = new byte[0];

   protected SerDeState(Kryo var1, Input var2, Output var3) {
      this.kryo = var1;
      this.input = var2;
      this.output = var3;
   }

   public void clear() {
      this.input.setBuffer(EMPTY_BUFFER);
      this.output.clear();
   }

   public void setInput(byte[] var1) {
      this.input.setBuffer(var1);
   }

   public void setInput(byte[] var1, int var2, int var3) {
      this.input.setBuffer(var1, var2, var3);
   }

   public void setInput(InputStream var1) {
      this.input.setInputStream(var1);
   }

   public int numOfWrittenBytes() {
      return (int)this.output.total();
   }

   public int numOfReadBytes() {
      return (int)this.input.total();
   }

   public Object readObject(Class var1) {
      return this.kryo.readObject(this.input, var1);
   }

   public Object readClassAndObject() {
      return this.kryo.readClassAndObject(this.input);
   }

   public void writeObject(Object var1) {
      this.kryo.writeObject(this.output, var1);
   }

   public void writeClassAndObject(Object var1) {
      this.kryo.writeClassAndObject(this.output, var1);
   }

   public byte[] outputToBytes() {
      return this.output.toBytes();
   }

   public void writeOutputTo(OutputStream var1) throws IOException {
      var1.write(this.output.getBuffer(), 0, this.output.position());
   }

   public boolean hasRegistration(Class var1) {
      return this.kryo.getRegistration(var1) != null;
   }
}
