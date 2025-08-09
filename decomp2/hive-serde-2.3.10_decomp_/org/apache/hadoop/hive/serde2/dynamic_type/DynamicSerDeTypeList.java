package org.apache.hadoop.hive.serde2.dynamic_type;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeList extends DynamicSerDeTypeBase {
   private static final int FD_TYPE = 0;

   public boolean isPrimitive() {
      return false;
   }

   public boolean isList() {
      return true;
   }

   public Class getRealType() {
      return ArrayList.class;
   }

   public DynamicSerDeTypeList(int i) {
      super(i);
   }

   public DynamicSerDeTypeList(thrift_grammar p, int i) {
      super(p, i);
   }

   public DynamicSerDeTypeBase getElementType() {
      return ((DynamicSerDeFieldType)this.jjtGetChild(0)).getMyType();
   }

   public String toString() {
      return "array<" + this.getElementType().toString() + ">";
   }

   public ArrayList deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      TList thelist = iprot.readListBegin();
      if (thelist == null) {
         return null;
      } else {
         ArrayList<Object> deserializeReuse;
         if (reuse != null) {
            deserializeReuse = (ArrayList)reuse;

            while(deserializeReuse.size() > thelist.size) {
               deserializeReuse.remove(deserializeReuse.size() - 1);
            }
         } else {
            deserializeReuse = new ArrayList();
         }

         deserializeReuse.ensureCapacity(thelist.size);

         for(int i = 0; i < thelist.size; ++i) {
            if (i + 1 > deserializeReuse.size()) {
               deserializeReuse.add(this.getElementType().deserialize((Object)null, iprot));
            } else {
               deserializeReuse.set(i, this.getElementType().deserialize(deserializeReuse.get(i), iprot));
            }
         }

         iprot.readListEnd();
         return deserializeReuse;
      }
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      ListObjectInspector loi = (ListObjectInspector)oi;
      ObjectInspector elementObjectInspector = loi.getListElementObjectInspector();
      DynamicSerDeTypeBase mt = this.getElementType();
      WriteNullsProtocol nullProtocol = oprot instanceof WriteNullsProtocol ? (WriteNullsProtocol)oprot : null;
      if (o instanceof List) {
         List<?> list = (List)o;
         oprot.writeListBegin(new TList(mt.getType(), list.size()));

         for(Object element : list) {
            if (element == null) {
               assert nullProtocol != null;

               nullProtocol.writeNull();
            } else {
               mt.serialize(element, elementObjectInspector, oprot);
            }
         }
      } else {
         Object[] list = o;
         oprot.writeListBegin(new TList(mt.getType(), list.length));

         for(Object element : list) {
            if (element == null && nullProtocol != null) {
               assert nullProtocol != null;

               nullProtocol.writeNull();
            } else {
               mt.serialize(element, elementObjectInspector, oprot);
            }
         }
      }

      oprot.writeListEnd();
   }

   public byte getType() {
      return 15;
   }
}
