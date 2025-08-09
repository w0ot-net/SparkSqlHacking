package org.apache.hadoop.hive.serde2.dynamic_type;

import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypedef extends DynamicSerDeTypeBase {
   private static final int FD_DEFINITION_TYPE = 0;

   public DynamicSerDeTypedef(int i) {
      super(i);
   }

   public DynamicSerDeTypedef(thrift_grammar p, int i) {
      super(p, i);
   }

   private DynamicSerDeSimpleNode getDefinitionType() {
      return (DynamicSerDeSimpleNode)this.jjtGetChild(0);
   }

   public DynamicSerDeTypeBase getMyType() {
      DynamicSerDeSimpleNode child = this.getDefinitionType();
      DynamicSerDeTypeBase ret = (DynamicSerDeTypeBase)child.jjtGetChild(0);
      return ret;
   }

   public String toString() {
      String result = "typedef " + this.name + "(";
      result = result + this.getDefinitionType().toString();
      result = result + ")";
      return result;
   }

   public byte getType() {
      throw new RuntimeException("not implemented");
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      throw new RuntimeException("not implemented");
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      throw new RuntimeException("not implemented");
   }
}
