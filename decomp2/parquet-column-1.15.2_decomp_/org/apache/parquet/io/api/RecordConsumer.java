package org.apache.parquet.io.api;

public abstract class RecordConsumer {
   public abstract void startMessage();

   public abstract void endMessage();

   public abstract void startField(String var1, int var2);

   public abstract void endField(String var1, int var2);

   public abstract void startGroup();

   public abstract void endGroup();

   public abstract void addInteger(int var1);

   public abstract void addLong(long var1);

   public abstract void addBoolean(boolean var1);

   public abstract void addBinary(Binary var1);

   public abstract void addFloat(float var1);

   public abstract void addDouble(double var1);

   public void flush() {
   }
}
