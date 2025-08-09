package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import org.datanucleus.util.TypeConversionHelper;

public class UUIDHexGenerator extends AbstractUUIDGenerator {
   public UUIDHexGenerator(String name, Properties props) {
      super(name, props);
   }

   protected String getIdentifier() {
      StringBuilder str = new StringBuilder(32);
      str.append(TypeConversionHelper.getHexFromInt(IP_ADDRESS));
      str.append(TypeConversionHelper.getHexFromInt(JVM_UNIQUE));
      short timeHigh = (short)((int)(System.currentTimeMillis() >>> 32));
      str.append(TypeConversionHelper.getHexFromShort(timeHigh));
      int timeLow = (int)System.currentTimeMillis();
      str.append(TypeConversionHelper.getHexFromInt(timeLow));
      str.append(TypeConversionHelper.getHexFromShort(this.getCount()));
      return str.toString();
   }
}
