package org.apache.hadoop.hive.serde2.dynamic_type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;

public class DynamicSerDeTypeMap extends DynamicSerDeTypeBase {
   private final byte FD_KEYTYPE = 0;
   private final byte FD_VALUETYPE = 1;
   TMap serializeMap = null;

   public boolean isPrimitive() {
      return false;
   }

   public boolean isMap() {
      return true;
   }

   public Class getRealType() {
      try {
         Class c = this.getKeyType().getRealType();
         Class c2 = this.getValueType().getRealType();
         Object o = c.newInstance();
         Object o2 = c2.newInstance();
         Map<?, ?> l = Collections.singletonMap(o, o2);
         return l.getClass();
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   public DynamicSerDeTypeMap(int i) {
      super(i);
   }

   public DynamicSerDeTypeMap(thrift_grammar p, int i) {
      super(p, i);
   }

   public DynamicSerDeTypeBase getKeyType() {
      return ((DynamicSerDeFieldType)this.jjtGetChild(0)).getMyType();
   }

   public DynamicSerDeTypeBase getValueType() {
      return ((DynamicSerDeFieldType)this.jjtGetChild(1)).getMyType();
   }

   public String toString() {
      return "map<" + this.getKeyType().toString() + "," + this.getValueType().toString() + ">";
   }

   public Map deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      HashMap<Object, Object> deserializeReuse;
      if (reuse != null) {
         deserializeReuse = (HashMap)reuse;
         deserializeReuse.clear();
      } else {
         deserializeReuse = new HashMap();
      }

      TMap themap = iprot.readMapBegin();
      if (themap == null) {
         return null;
      } else {
         int mapSize = themap.size;

         for(int i = 0; i < mapSize; ++i) {
            Object key = this.getKeyType().deserialize((Object)null, iprot);
            Object value = this.getValueType().deserialize((Object)null, iprot);
            deserializeReuse.put(key, value);
         }

         iprot.readMapEnd();
         return deserializeReuse;
      }
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      DynamicSerDeTypeBase keyType = this.getKeyType();
      DynamicSerDeTypeBase valueType = this.getValueType();
      WriteNullsProtocol nullProtocol = oprot instanceof WriteNullsProtocol ? (WriteNullsProtocol)oprot : null;

      assert oi.getCategory() == ObjectInspector.Category.MAP;

      MapObjectInspector moi = (MapObjectInspector)oi;
      ObjectInspector koi = moi.getMapKeyObjectInspector();
      ObjectInspector voi = moi.getMapValueObjectInspector();
      Map<?, ?> map = moi.getMap(o);
      this.serializeMap = new TMap(keyType.getType(), valueType.getType(), map.size());
      oprot.writeMapBegin(this.serializeMap);

      for(Object element : map.entrySet()) {
         Map.Entry it = (Map.Entry)element;
         Object key = it.getKey();
         Object value = it.getValue();
         keyType.serialize(key, koi, oprot);
         if (value == null) {
            assert nullProtocol != null;

            nullProtocol.writeNull();
         } else {
            valueType.serialize(value, voi, oprot);
         }
      }

      oprot.writeMapEnd();
   }

   public byte getType() {
      return 13;
   }
}
