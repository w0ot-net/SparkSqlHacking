package org.apache.hadoop.hive.serde2.thrift;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.AbstractDeserializer;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.thrift.protocol.TProtocolFactory;

public class ThriftDeserializer extends AbstractDeserializer {
   private ThriftByteStreamTypedSerDe tsd;

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
      try {
         String className = tbl.getProperty("serialization.class");
         Class<?> recordClass = job.getClassByName(className);
         String protoName = tbl.getProperty("serialization.format");
         if (protoName == null) {
            protoName = "TBinaryProtocol";
         }

         protoName = protoName.replace("com.facebook.thrift.protocol", "org.apache.thrift.protocol");
         TProtocolFactory tp = TReflectionUtils.getProtocolFactoryByName(protoName);
         this.tsd = new ThriftByteStreamTypedSerDe(recordClass, tp, tp);
      } catch (Exception e) {
         throw new SerDeException(e);
      }
   }

   public Object deserialize(Writable field) throws SerDeException {
      return this.tsd.deserialize(field);
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.tsd.getObjectInspector();
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
