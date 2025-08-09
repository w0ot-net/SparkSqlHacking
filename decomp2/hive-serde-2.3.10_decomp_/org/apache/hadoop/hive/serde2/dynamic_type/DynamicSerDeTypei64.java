package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypei64 extends DynamicSerDeTypeBase {
   public Class getRealType() {
      return Long.valueOf(0L).getClass();
   }

   public DynamicSerDeTypei64(int i) {
      super(i);
   }

   public DynamicSerDeTypei64(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "i64";
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      long val = iprot.readI64();
      return val == 0L && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      LongObjectInspector poi = (LongObjectInspector)oi;
      oprot.writeI64(poi.get(o));
   }

   public byte getType() {
      return 10;
   }
}
