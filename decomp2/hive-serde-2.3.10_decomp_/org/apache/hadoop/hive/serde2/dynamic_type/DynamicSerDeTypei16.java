package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypei16 extends DynamicSerDeTypeBase {
   public Class getRealType() {
      return Integer.valueOf(2).getClass();
   }

   public DynamicSerDeTypei16(int i) {
      super(i);
   }

   public DynamicSerDeTypei16(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "i16";
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      int val = iprot.readI16();
      return val == 0 && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      ShortObjectInspector poi = (ShortObjectInspector)oi;
      oprot.writeI16(poi.get(o));
   }

   public byte getType() {
      return 6;
   }
}
