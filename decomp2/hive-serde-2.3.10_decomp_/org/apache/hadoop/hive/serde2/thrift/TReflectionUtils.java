package org.apache.hadoop.hive.serde2.thrift;

import org.apache.thrift.protocol.TProtocolFactory;

public final class TReflectionUtils {
   public static final String thriftReaderFname = "read";
   public static final String thriftWriterFname = "write";
   public static final Class[] thriftRWParams;

   public static TProtocolFactory getProtocolFactoryByName(String protocolName) throws Exception {
      Class<?> protoClass = Class.forName(protocolName + "$Factory");
      return (TProtocolFactory)protoClass.newInstance();
   }

   private TReflectionUtils() {
   }

   static {
      try {
         thriftRWParams = new Class[]{Class.forName("org.apache.thrift.protocol.TProtocol")};
      } catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
}
