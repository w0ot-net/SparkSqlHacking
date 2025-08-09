package org.apache.parquet.example.data.simple;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.Preconditions;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;

public class NanoTime extends Primitive {
   private final int julianDay;
   private final long timeOfDayNanos;

   public static NanoTime fromBinary(Binary bytes) {
      Preconditions.checkArgument(bytes.length() == 12, "Must be 12 bytes");
      ByteBuffer buf = bytes.toByteBuffer();
      buf.order(ByteOrder.LITTLE_ENDIAN);
      long timeOfDayNanos = buf.getLong();
      int julianDay = buf.getInt();
      return new NanoTime(julianDay, timeOfDayNanos);
   }

   public static NanoTime fromInt96(Int96Value int96) {
      ByteBuffer buf = int96.getInt96().toByteBuffer();
      return new NanoTime(buf.getInt(), buf.getLong());
   }

   public NanoTime(int julianDay, long timeOfDayNanos) {
      this.julianDay = julianDay;
      this.timeOfDayNanos = timeOfDayNanos;
   }

   public int getJulianDay() {
      return this.julianDay;
   }

   public long getTimeOfDayNanos() {
      return this.timeOfDayNanos;
   }

   public Binary toBinary() {
      ByteBuffer buf = ByteBuffer.allocate(12);
      buf.order(ByteOrder.LITTLE_ENDIAN);
      buf.putLong(this.timeOfDayNanos);
      buf.putInt(this.julianDay);
      buf.flip();
      return Binary.fromConstantByteBuffer(buf);
   }

   public Int96Value toInt96() {
      return new Int96Value(this.toBinary());
   }

   public void writeValue(RecordConsumer recordConsumer) {
      recordConsumer.addBinary(this.toBinary());
   }

   public String toString() {
      return "NanoTime{julianDay=" + this.julianDay + ", timeOfDayNanos=" + this.timeOfDayNanos + "}";
   }
}
