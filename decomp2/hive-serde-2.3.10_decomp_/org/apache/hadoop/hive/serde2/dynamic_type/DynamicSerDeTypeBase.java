package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.Serializable;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public abstract class DynamicSerDeTypeBase extends DynamicSerDeSimpleNode implements Serializable {
   private static final long serialVersionUID = 1L;

   public DynamicSerDeTypeBase(int i) {
      super(i);
   }

   public DynamicSerDeTypeBase(thrift_grammar p, int i) {
      super(p, i);
   }

   public void initialize() {
   }

   public Class getRealType() throws SerDeException {
      throw new SerDeException("Not implemented in base");
   }

   public Object get(Object obj) {
      throw new RuntimeException("Not implemented in base");
   }

   public abstract Object deserialize(Object var1, TProtocol var2) throws SerDeException, TException, IllegalAccessException;

   public abstract void serialize(Object var1, ObjectInspector var2, TProtocol var3) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException;

   public String toString() {
      return "BAD";
   }

   public byte getType() {
      return -1;
   }

   public boolean isPrimitive() {
      return true;
   }

   public boolean isList() {
      return false;
   }

   public boolean isMap() {
      return false;
   }
}
