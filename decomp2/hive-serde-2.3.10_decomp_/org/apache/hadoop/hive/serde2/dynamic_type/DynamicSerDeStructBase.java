package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.Serializable;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TStruct;

public abstract class DynamicSerDeStructBase extends DynamicSerDeTypeBase implements Serializable {
   DynamicSerDeFieldList fieldList;

   public DynamicSerDeStructBase(int i) {
      super(i);
   }

   public DynamicSerDeStructBase(thrift_grammar p, int i) {
      super(p, i);
   }

   public abstract DynamicSerDeFieldList getFieldList();

   public void initialize() {
      this.fieldList = this.getFieldList();
      this.fieldList.initialize();
   }

   public boolean isPrimitive() {
      return false;
   }

   public Class getRealType() {
      return List.class;
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      iprot.readStructBegin();
      Object o = this.fieldList.deserialize(reuse, iprot);
      iprot.readStructEnd();
      return o;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      oprot.writeStructBegin(new TStruct(this.name));
      this.fieldList.serialize(o, oi, oprot);
      oprot.writeStructEnd();
   }
}
