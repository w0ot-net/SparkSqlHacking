package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.thrift.SkippableTProtocol;
import org.apache.hadoop.hive.serde2.thrift.WriteNullsProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;

public class DynamicSerDeFieldList extends DynamicSerDeSimpleNode implements Serializable {
   private Map types_by_id = null;
   private Map types_by_column_name = null;
   private DynamicSerDeTypeBase[] ordered_types = null;
   private Map ordered_column_id_by_name = null;
   protected boolean isRealThrift = false;
   protected boolean[] fieldsPresent;
   TField field = new TField();

   public DynamicSerDeFieldList(int i) {
      super(i);
   }

   public DynamicSerDeFieldList(thrift_grammar p, int i) {
      super(p, i);
   }

   private DynamicSerDeField getField(int i) {
      return (DynamicSerDeField)this.jjtGetChild(i);
   }

   public final DynamicSerDeField[] getChildren() {
      int size = this.jjtGetNumChildren();
      DynamicSerDeField[] result = new DynamicSerDeField[size];

      for(int i = 0; i < size; ++i) {
         result[i] = (DynamicSerDeField)this.jjtGetChild(i);
      }

      return result;
   }

   private int getNumFields() {
      return this.jjtGetNumChildren();
   }

   public void initialize() {
      if (this.types_by_id == null) {
         this.types_by_id = new HashMap();
         this.types_by_column_name = new HashMap();
         this.ordered_types = new DynamicSerDeTypeBase[this.jjtGetNumChildren()];
         this.ordered_column_id_by_name = new HashMap();

         for(int i = 0; i < this.jjtGetNumChildren(); ++i) {
            DynamicSerDeField mt = this.getField(i);
            DynamicSerDeTypeBase type = mt.getFieldType().getMyType();
            type.initialize();
            type.fieldid = mt.fieldid;
            type.name = mt.name;
            this.types_by_id.put(mt.fieldid, type);
            this.types_by_column_name.put(mt.name, type);
            this.ordered_types[i] = type;
            this.ordered_column_id_by_name.put(mt.name, i);
         }
      }

   }

   private DynamicSerDeTypeBase getFieldByFieldId(int i) {
      return (DynamicSerDeTypeBase)this.types_by_id.get(i);
   }

   protected DynamicSerDeTypeBase getFieldByName(String fieldname) {
      return (DynamicSerDeTypeBase)this.types_by_column_name.get(fieldname);
   }

   public Object deserialize(Object reuse, TProtocol iprot) throws SerDeException, TException, IllegalAccessException {
      ArrayList<Object> struct = null;
      if (reuse == null) {
         struct = new ArrayList(this.getNumFields());

         for(DynamicSerDeTypeBase var10000 : this.ordered_types) {
            struct.add((Object)null);
         }
      } else {
         struct = (ArrayList)reuse;

         assert struct.size() == this.ordered_types.length;
      }

      boolean fastSkips = iprot instanceof SkippableTProtocol;
      boolean stopSeen = false;
      if (this.fieldsPresent == null) {
         this.fieldsPresent = new boolean[this.ordered_types.length];
      }

      Arrays.fill(this.fieldsPresent, false);

      for(int i = 0; i < this.getNumFields(); ++i) {
         DynamicSerDeTypeBase mt = null;
         TField field = null;
         if (!this.isRealThrift && this.getField(i).isSkippable()) {
            mt = this.ordered_types[i];
            if (fastSkips) {
               ((SkippableTProtocol)iprot).skip(mt.getType());
            } else {
               TProtocolUtil.skip(iprot, mt.getType());
            }

            struct.set(i, (Object)null);
         } else {
            field = iprot.readFieldBegin();
            if (field.type >= 0) {
               if (field.type == 0) {
                  stopSeen = true;
                  break;
               }

               mt = this.getFieldByFieldId(field.id);
               if (mt == null) {
                  System.err.println("ERROR for fieldid: " + field.id + " system has no knowledge of this field which is of type : " + field.type);
                  TProtocolUtil.skip(iprot, field.type);
                  continue;
               }
            }

            int orderedId = -1;
            if (field.type < 0) {
               mt = this.ordered_types[i];
               orderedId = i;
            } else {
               orderedId = (Integer)this.ordered_column_id_by_name.get(mt.name);
            }

            struct.set(orderedId, mt.deserialize(struct.get(orderedId), iprot));
            iprot.readFieldEnd();
            this.fieldsPresent[orderedId] = true;
         }
      }

      for(int i = 0; i < this.ordered_types.length; ++i) {
         if (!this.fieldsPresent[i]) {
            struct.set(i, (Object)null);
         }
      }

      if (!stopSeen) {
         iprot.readFieldBegin();
      }

      return struct;
   }

   public void serialize(Object o, ObjectInspector oi, TProtocol oprot) throws TException, SerDeException, NoSuchFieldException, IllegalAccessException {
      assert oi instanceof StructObjectInspector;

      StructObjectInspector soi = (StructObjectInspector)oi;
      boolean writeNulls = oprot instanceof WriteNullsProtocol;
      List<? extends StructField> fields = soi.getAllStructFieldRefs();
      if (fields.size() != this.ordered_types.length) {
         throw new SerDeException("Trying to serialize " + fields.size() + " fields into a struct with " + this.ordered_types.length + " object=" + o + " objectinspector=" + oi.getTypeName());
      } else {
         for(int i = 0; i < fields.size(); ++i) {
            Object f = soi.getStructFieldData(o, (StructField)fields.get(i));
            DynamicSerDeTypeBase mt = this.ordered_types[i];
            if (f != null || writeNulls) {
               this.field = new TField(mt.name, mt.getType(), (short)mt.fieldid);
               oprot.writeFieldBegin(this.field);
               if (f == null) {
                  ((WriteNullsProtocol)oprot).writeNull();
               } else {
                  mt.serialize(f, ((StructField)fields.get(i)).getFieldObjectInspector(), oprot);
               }

               oprot.writeFieldEnd();
            }
         }

         oprot.writeFieldStop();
      }
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      String prefix = "";

      for(DynamicSerDeField t : this.getChildren()) {
         result.append(prefix + t.fieldid + ":" + t.getFieldType().getMyType().toString() + " " + t.name);
         prefix = ",";
      }

      return result.toString();
   }
}
