package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypei32 extends DynamicSerDeTypeBase {
   public DynamicSerDeTypei32(int i) {
      super(i);
   }

   public DynamicSerDeTypei32(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "i32";
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      int val = iprot.readI32();
      return val == 0 && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      IntObjectInspector poi = (IntObjectInspector)oi;
      oprot.writeI32(poi.get(o));
   }

   public Class getRealType() {
      return Integer.class;
   }

   public Integer getRealTypeInstance() {
      return 0;
   }

   public byte getType() {
      return 8;
   }
}
