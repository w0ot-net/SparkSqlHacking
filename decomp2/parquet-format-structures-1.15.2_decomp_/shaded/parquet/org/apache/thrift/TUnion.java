package shaded.parquet.org.apache.thrift;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;

public abstract class TUnion implements TBase {
   protected Object value_;
   protected TFieldIdEnum setField_;
   private static final Map schemes = new HashMap();

   protected TUnion() {
      this.setField_ = null;
      this.value_ = null;
   }

   protected TUnion(TFieldIdEnum setField, Object value) {
      this.setFieldValue(setField, value);
   }

   protected TUnion(TUnion other) {
      if (!other.getClass().equals(this.getClass())) {
         throw new ClassCastException();
      } else {
         this.setField_ = other.setField_;
         this.value_ = deepCopyObject(other.value_);
      }
   }

   private static Object deepCopyObject(Object o) {
      if (o instanceof TBase) {
         return ((TBase)o).deepCopy();
      } else if (o instanceof ByteBuffer) {
         return TBaseHelper.copyBinary((ByteBuffer)o);
      } else if (o instanceof List) {
         return deepCopyList((List)o);
      } else if (o instanceof Set) {
         return deepCopySet((Set)o);
      } else {
         return o instanceof Map ? deepCopyMap((Map)o) : o;
      }
   }

   private static Map deepCopyMap(Map map) {
      Map copy = new HashMap(map.size());

      for(Map.Entry entry : map.entrySet()) {
         copy.put(deepCopyObject(entry.getKey()), deepCopyObject(entry.getValue()));
      }

      return copy;
   }

   private static Set deepCopySet(Set set) {
      Set copy = new HashSet(set.size());

      for(Object o : set) {
         copy.add(deepCopyObject(o));
      }

      return copy;
   }

   private static List deepCopyList(List list) {
      List copy = new ArrayList(list.size());

      for(Object o : list) {
         copy.add(deepCopyObject(o));
      }

      return copy;
   }

   public TFieldIdEnum getSetField() {
      return this.setField_;
   }

   public Object getFieldValue() {
      return this.value_;
   }

   public Object getFieldValue(TFieldIdEnum fieldId) {
      if (fieldId != this.setField_) {
         throw new IllegalArgumentException("Cannot get the value of field " + fieldId + " because union's set field is " + this.setField_);
      } else {
         return this.getFieldValue();
      }
   }

   public Object getFieldValue(int fieldId) {
      return this.getFieldValue(this.enumForId((short)fieldId));
   }

   public boolean isSet() {
      return this.setField_ != null;
   }

   public boolean isSet(TFieldIdEnum fieldId) {
      return this.setField_ == fieldId;
   }

   public boolean isSet(int fieldId) {
      return this.isSet(this.enumForId((short)fieldId));
   }

   public void read(TProtocol iprot) throws TException {
      ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
   }

   public void setFieldValue(TFieldIdEnum fieldId, Object value) {
      this.checkType(fieldId, value);
      this.setField_ = fieldId;
      this.value_ = value;
   }

   public void setFieldValue(int fieldId, Object value) {
      this.setFieldValue(this.enumForId((short)fieldId), value);
   }

   public void write(TProtocol oprot) throws TException {
      ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
   }

   protected abstract void checkType(TFieldIdEnum var1, Object var2) throws ClassCastException;

   protected abstract Object standardSchemeReadValue(TProtocol var1, TField var2) throws TException;

   protected abstract void standardSchemeWriteValue(TProtocol var1) throws TException;

   protected abstract Object tupleSchemeReadValue(TProtocol var1, short var2) throws TException;

   protected abstract void tupleSchemeWriteValue(TProtocol var1) throws TException;

   protected abstract TStruct getStructDesc();

   protected abstract TField getFieldDesc(TFieldIdEnum var1);

   protected abstract TFieldIdEnum enumForId(short var1);

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("<");
      sb.append(this.getClass().getSimpleName());
      sb.append(" ");
      if (this.getSetField() != null) {
         Object v = this.getFieldValue();
         sb.append(this.getFieldDesc(this.getSetField()).name);
         sb.append(":");
         if (v instanceof ByteBuffer) {
            TBaseHelper.toString((ByteBuffer)v, sb);
         } else {
            sb.append(v.toString());
         }
      }

      sb.append(">");
      return sb.toString();
   }

   public final void clear() {
      this.setField_ = null;
      this.value_ = null;
   }

   static {
      schemes.put(StandardScheme.class, new TUnionStandardSchemeFactory());
      schemes.put(TupleScheme.class, new TUnionTupleSchemeFactory());
   }

   private static class TUnionStandardSchemeFactory implements SchemeFactory {
      private TUnionStandardSchemeFactory() {
      }

      public TUnionStandardScheme getScheme() {
         return new TUnionStandardScheme();
      }
   }

   private static class TUnionStandardScheme extends StandardScheme {
      private TUnionStandardScheme() {
      }

      public void read(TProtocol iprot, TUnion struct) throws TException {
         struct.setField_ = null;
         struct.value_ = null;
         iprot.readStructBegin();
         TField field = iprot.readFieldBegin();
         struct.value_ = struct.standardSchemeReadValue(iprot, field);
         if (struct.value_ != null) {
            struct.setField_ = struct.enumForId(field.id);
         }

         iprot.readFieldEnd();
         iprot.readFieldBegin();
         iprot.readStructEnd();
      }

      public void write(TProtocol oprot, TUnion struct) throws TException {
         if (struct.getSetField() != null && struct.getFieldValue() != null) {
            oprot.writeStructBegin(struct.getStructDesc());
            oprot.writeFieldBegin(struct.getFieldDesc(struct.setField_));
            struct.standardSchemeWriteValue(oprot);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         } else {
            throw new TProtocolException("Cannot write a TUnion with no set value!");
         }
      }
   }

   private static class TUnionTupleSchemeFactory implements SchemeFactory {
      private TUnionTupleSchemeFactory() {
      }

      public TUnionTupleScheme getScheme() {
         return new TUnionTupleScheme();
      }
   }

   private static class TUnionTupleScheme extends TupleScheme {
      private TUnionTupleScheme() {
      }

      public void read(TProtocol iprot, TUnion struct) throws TException {
         struct.setField_ = null;
         struct.value_ = null;
         short fieldID = iprot.readI16();
         struct.value_ = struct.tupleSchemeReadValue(iprot, fieldID);
         if (struct.value_ != null) {
            struct.setField_ = struct.enumForId(fieldID);
         }

      }

      public void write(TProtocol oprot, TUnion struct) throws TException {
         if (struct.getSetField() != null && struct.getFieldValue() != null) {
            oprot.writeI16(struct.setField_.getThriftFieldId());
            struct.tupleSchemeWriteValue(oprot);
         } else {
            throw new TProtocolException("Cannot write a TUnion with no set value!");
         }
      }
   }
}
