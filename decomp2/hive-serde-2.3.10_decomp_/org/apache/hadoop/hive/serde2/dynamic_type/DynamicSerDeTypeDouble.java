package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeDouble extends DynamicSerDeTypeBase {
   public DynamicSerDeTypeDouble(int i) {
      super(i);
   }

   public DynamicSerDeTypeDouble(thrift_grammar p, int i) {
      super(p, i);
   }

   public String toString() {
      return "double";
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      double val = iprot.readDouble();
      return val == (double)0.0F && iprot instanceof WriteNullsProtocol && ((WriteNullsProtocol)iprot).lastPrimitiveWasNull() ? null : val;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      DoubleObjectInspector poi = (DoubleObjectInspector)oi;
      oprot.writeDouble(poi.get(o));
   }

   public byte getType() {
      return 4;
   }

   public Class getRealType() {
      return Double.class;
   }

   public Double getRealTypeInstance() {
      return (double)0.0F;
   }
}
