package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeBool extends DynamicSerDeTypeBase {
   public DynamicSerDeTypeBool(int i) {
      super(i);
   }

   public DynamicSerDeTypeBool(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "bool";
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      boolean val = iprot.readBool();
      return !val && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      BooleanObjectInspector poi = (BooleanObjectInspector)oi;
      oprot.writeBool(poi.get(o));
   }

   public byte getType() {
      return 2;
   }

   public Class getRealType() {
      return Boolean.class;
   }

   public Boolean getRealTypeInstance() {
      return Boolean.FALSE;
   }
}
