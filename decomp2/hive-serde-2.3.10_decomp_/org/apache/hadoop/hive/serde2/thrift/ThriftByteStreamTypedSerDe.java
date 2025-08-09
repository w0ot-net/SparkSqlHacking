package org.apache.hadoop.hive.serde2.thrift;

import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.ByteStreamTypedSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.io.Writable;
import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;

public class ThriftByteStreamTypedSerDe extends ByteStreamTypedSerDe {
   protected TIOStreamTransport outTransport;
   protected TIOStreamTransport inTransport;
   protected TProtocol outProtocol;
   protected TProtocol inProtocol;

   private void init(TProtocolFactory inFactory, TProtocolFactory outFactory) throws Exception {
      this.outTransport = new TIOStreamTransport(this.bos);
      this.inTransport = new TIOStreamTransport(this.bis);
      this.outProtocol = outFactory.getProtocol(this.outTransport);
      this.inProtocol = inFactory.getProtocol(this.inTransport);
   }

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
      throw new SerDeException("ThriftByteStreamTypedSerDe is still semi-abstract");
   }

   public ThriftByteStreamTypedSerDe(java.lang.reflect.Type objectType, TProtocolFactory inFactory, TProtocolFactory outFactory) throws SerDeException {
      super(objectType);

      try {
         this.init(inFactory, outFactory);
      } catch (Exception e) {
         throw new SerDeException(e);
      }
   }

   protected ObjectInspectorFactory.ObjectInspectorOptions getObjectInspectorOptions() {
      return ObjectInspectorFactory.ObjectInspectorOptions.THRIFT;
   }

   public Object deserialize(Writable field) throws SerDeException {
      Object obj = super.deserialize(field);

      try {
         ((TBase)obj).read(this.inProtocol);
         return obj;
      } catch (Exception e) {
         throw new SerDeException(e);
      }
   }
}
