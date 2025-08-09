package org.apache.hadoop.hive.serde2.dynamic_type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSet;

public class DynamicSerDeTypeSet extends DynamicSerDeTypeBase {
   private static final int FD_TYPE = 0;
   TSet tset = null;

   public DynamicSerDeTypeSet(int i) {
      super(i);
   }

   public DynamicSerDeTypeSet(thrift_grammar p, int i) {
      super(p, i);
   }

   public Class getRealType() {
      try {
         Class c = this.getElementType().getRealType();
         Object o = c.newInstance();
         Set<?> l = Collections.singleton(o);
         return l.getClass();
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   public DynamicSerDeTypeBase getElementType() {
      return ((DynamicSerDeFieldType)this.jjtGetChild(0)).getMyType();
   }

   public String toString() {
      return "set<" + this.getElementType().toString() + ">";
   }

   public byte getType() {
      return 14;
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      TSet theset = iprot.readSetBegin();
      if (theset == null) {
         return null;
      } else {
         Set<Object> result;
         if (reuse != null) {
            result = (Set)reuse;
            result.clear();
         } else {
            result = new HashSet();
         }

         for(int i = 0; i < theset.size; ++i) {
            Object elem = this.getElementType().deserialize((Object)null, iprot);
            result.add(elem);
         }

         iprot.readSetEnd();
         return result;
      }
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      ListObjectInspector loi = (ListObjectInspector)oi;
      Set<Object> set = (Set)o;
      DynamicSerDeTypeBase mt = this.getElementType();
      this.tset = new TSet(mt.getType(), set.size());
      oprot.writeSetBegin(this.tset);

      for(Object element : set) {
         mt.serialize(element, loi.getListElementObjectInspector(), oprot);
      }

      oprot.writeSetEnd();
   }
}
