package com.twitter.chill;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class KryoPool extends ResourcePool {
   protected KryoPool(int var1) {
      super(var1);
   }

   public void release(SerDeState var1) {
      var1.clear();
      super.release(var1);
   }

   public static KryoPool withBuffer(int var0, final KryoInstantiator var1, final int var2, final int var3) {
      return new KryoPool(var0) {
         protected SerDeState newInstance() {
            return new SerDeState(var1.newKryo(), new Input(), new Output(var2, var3));
         }
      };
   }

   public static KryoPool withByteArrayOutputStream(int var0, final KryoInstantiator var1) {
      return new KryoPool(var0) {
         protected SerDeState newInstance() {
            return new SerDeState(var1.newKryo(), new Input(), new Output(new ByteArrayOutputStream())) {
               public void clear() {
                  super.clear();
                  ByteArrayOutputStream var1x = (ByteArrayOutputStream)this.output.getOutputStream();
                  var1x.reset();
               }

               public byte[] outputToBytes() {
                  this.output.flush();
                  ByteArrayOutputStream var1x = (ByteArrayOutputStream)this.output.getOutputStream();
                  return var1x.toByteArray();
               }

               public void writeOutputTo(OutputStream var1x) throws IOException {
                  this.output.flush();
                  ByteArrayOutputStream var2 = (ByteArrayOutputStream)this.output.getOutputStream();
                  var2.writeTo(var1x);
               }
            };
         }
      };
   }

   public Object deepCopy(Object var1) {
      return this.fromBytes(this.toBytesWithoutClass(var1), var1.getClass());
   }

   public Object fromBytes(byte[] var1) {
      SerDeState var2 = (SerDeState)this.borrow();

      Object var3;
      try {
         var2.setInput(var1);
         var3 = var2.readClassAndObject();
      } finally {
         this.release(var2);
      }

      return var3;
   }

   public Object fromBytes(byte[] var1, Class var2) {
      SerDeState var3 = (SerDeState)this.borrow();

      Object var4;
      try {
         var3.setInput(var1);
         var4 = var3.readObject(var2);
      } finally {
         this.release(var3);
      }

      return var4;
   }

   public byte[] toBytesWithClass(Object var1) {
      SerDeState var2 = (SerDeState)this.borrow();

      byte[] var3;
      try {
         var2.writeClassAndObject(var1);
         var3 = var2.outputToBytes();
      } finally {
         this.release(var2);
      }

      return var3;
   }

   public byte[] toBytesWithoutClass(Object var1) {
      SerDeState var2 = (SerDeState)this.borrow();

      byte[] var3;
      try {
         var2.writeObject(var1);
         var3 = var2.outputToBytes();
      } finally {
         this.release(var2);
      }

      return var3;
   }

   public boolean hasRegistration(Class var1) {
      SerDeState var2 = (SerDeState)this.borrow();

      boolean var3;
      try {
         var3 = var2.hasRegistration(var1);
      } finally {
         this.release(var2);
      }

      return var3;
   }
}
