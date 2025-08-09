package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TGetInfoValue;

public class GetInfoValue {
   private String stringValue = null;
   private short shortValue;
   private int intValue;
   private long longValue;

   public GetInfoValue(String stringValue) {
      this.stringValue = stringValue;
   }

   public GetInfoValue(short shortValue) {
      this.shortValue = shortValue;
   }

   public GetInfoValue(int intValue) {
      this.intValue = intValue;
   }

   public GetInfoValue(long longValue) {
      this.longValue = longValue;
   }

   public GetInfoValue(TGetInfoValue tGetInfoValue) {
      switch ((TGetInfoValue._Fields)tGetInfoValue.getSetField()) {
         case STRING_VALUE:
            this.stringValue = tGetInfoValue.getStringValue();
            return;
         default:
            throw new IllegalArgumentException("Unrecognized TGetInfoValue");
      }
   }

   public TGetInfoValue toTGetInfoValue() {
      TGetInfoValue tInfoValue = new TGetInfoValue();
      if (this.stringValue != null) {
         tInfoValue.setStringValue(this.stringValue);
      }

      return tInfoValue;
   }

   public String getStringValue() {
      return this.stringValue;
   }

   public short getShortValue() {
      return this.shortValue;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public long getLongValue() {
      return this.longValue;
   }
}
