package org.datanucleus.store.valuegenerator;

import java.net.InetAddress;
import java.util.Properties;
import org.datanucleus.util.TypeConversionHelper;

public abstract class AbstractUUIDGenerator extends AbstractUIDGenerator {
   static final int IP_ADDRESS;
   static final int JVM_UNIQUE;
   static short counter;

   public AbstractUUIDGenerator(String name, Properties props) {
      super(name, props);
   }

   protected short getCount() {
      synchronized(AbstractUUIDGenerator.class) {
         if (counter < 0) {
            counter = 0;
         }

         short var10000 = counter;
         counter = (short)(var10000 + 1);
         return var10000;
      }
   }

   static {
      int ipAddr = 0;

      try {
         ipAddr = TypeConversionHelper.getIntFromByteArray(InetAddress.getLocalHost().getAddress());
      } catch (Exception var2) {
         ipAddr = 0;
      }

      IP_ADDRESS = ipAddr;
      JVM_UNIQUE = (int)(System.currentTimeMillis() >>> 8);
      counter = 0;
   }
}
