package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeByte extends DynamicSerDeTypeBase {
   public DynamicSerDeTypeByte(int i) {
      super(i);
   }

   public DynamicSerDeTypeByte(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "byte";
   }

   public Byte deserialize(TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      byte val = iprot.readByte();
      return val == 0 && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      return this.deserialize(iprot);
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      ByteObjectInspector poi = (ByteObjectInspector)oi;
      oprot.writeByte(poi.get(o));
   }

   public byte getType() {
      return 3;
   }
}
