package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteTextProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeString extends DynamicSerDeTypeBase {
   public DynamicSerDeTypeString(int i) {
      super(i);
   }

   public DynamicSerDeTypeString(thrift_grammar p, int i) {
      super(p, i);
   }

   public Class getRealType() {
      return String.class;
   }

   public String toString() {
      return "string";
   }

   public String deserialize(TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      return iprot.readString();
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      return iprot.readString();
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      StringObjectInspector poi = (StringObjectInspector)oi;
      if (oprot instanceof WriteTextProtocol) {
         ((WriteTextProtocol)oprot).writeText(poi.getPrimitiveWritableObject(o));
      } else {
         oprot.writeString(poi.getPrimitiveJavaObject(o));
      }

   }

   public byte getType() {
      return 11;
   }
}
