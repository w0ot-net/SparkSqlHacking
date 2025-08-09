package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import org.datanucleus.util.TypeConversionHelper;

public class UUIDStringGenerator extends AbstractUUIDGenerator {
   public UUIDStringGenerator(String name, Properties props) {
      super(name, props);
   }

   protected String getIdentifier() {
      byte[] ipAddrBytes = TypeConversionHelper.getBytesFromInt(IP_ADDRESS);
      byte[] jvmBytes = TypeConversionHelper.getBytesFromInt(JVM_UNIQUE);
      short timeHigh = (short)((int)(System.currentTimeMillis() >>> 32));
      byte[] timeHighBytes = TypeConversionHelper.getBytesFromShort(timeHigh);
      int timeLow = (int)System.currentTimeMillis();
      byte[] timeLowBytes = TypeConversionHelper.getBytesFromInt(timeLow);
      short count = this.getCount();
      byte[] countBytes = TypeConversionHelper.getBytesFromShort(count);
      byte[] bytes = new byte[16];
      int pos = 0;

      for(int i = 0; i < 4; ++i) {
         bytes[pos++] = ipAddrBytes[i];
      }

      for(int i = 0; i < 4; ++i) {
         bytes[pos++] = jvmBytes[i];
      }

      for(int i = 0; i < 2; ++i) {
         bytes[pos++] = timeHighBytes[i];
      }

      for(int i = 0; i < 4; ++i) {
         bytes[pos++] = timeLowBytes[i];
      }

      for(int i = 0; i < 2; ++i) {
         bytes[pos++] = countBytes[i];
      }

      try {
         return new String(bytes, "ISO-8859-1");
      } catch (Exception var12) {
         return new String(bytes);
      }
   }
}
