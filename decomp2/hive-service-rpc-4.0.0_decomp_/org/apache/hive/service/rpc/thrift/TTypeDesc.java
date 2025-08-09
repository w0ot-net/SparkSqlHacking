package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TTypeDesc implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TTypeDesc");
   private static final TField TYPES_FIELD_DESC = new TField("types", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TTypeDescStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TTypeDescTupleSchemeFactory();
   @Nullable
   private List types;
   public static final Map metaDataMap;

   public TTypeDesc() {
   }

   public TTypeDesc(List types) {
      this();
      this.types = types;
   }

   public TTypeDesc(TTypeDesc other) {
      if (other.isSetTypes()) {
         List<TTypeEntry> __this__types = new ArrayList(other.types.size());

         for(TTypeEntry other_element : other.types) {
            __this__types.add(new TTypeEntry(other_element));
         }

         this.types = __this__types;
      }

   }

   public TTypeDesc deepCopy() {
      return new TTypeDesc(this);
   }

   public void clear() {
      this.types = null;
   }

   public int getTypesSize() {
      return this.types == null ? 0 : this.types.size();
   }

   @Nullable
   public Iterator getTypesIterator() {
      return this.types == null ? null : this.types.iterator();
   }

   public void addToTypes(TTypeEntry elem) {
      if (this.types == null) {
         this.types = new ArrayList();
      }

      this.types.add(elem);
   }

   @Nullable
   public List getTypes() {
      return this.types;
   }

   public void setTypes(@Nullable List types) {
      this.types = types;
   }

   public void unsetTypes() {
      this.types = null;
   }

   public boolean isSetTypes() {
      return this.types != null;
   }

   public void setTypesIsSet(boolean value) {
      if (!value) {
         this.types = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPES:
            if (value == null) {
               this.unsetTypes();
            } else {
               this.setTypes((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPES:
            return this.getTypes();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TYPES:
               return this.isSetTypes();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TTypeDesc ? this.equals((TTypeDesc)that) : false;
   }

   public boolean equals(TTypeDesc that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_types = this.isSetTypes();
         boolean that_present_types = that.isSetTypes();
         if (this_present_types || that_present_types) {
            if (!this_present_types || !that_present_types) {
               return false;
            }

            if (!this.types.equals(that.types)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTypes() ? 131071 : 524287);
      if (this.isSetTypes()) {
         hashCode = hashCode * 8191 + this.types.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TTypeDesc other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTypes(), other.isSetTypes());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTypes()) {
               lastComparison = TBaseHelper.compareTo(this.types, other.types);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TTypeDesc._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TTypeDesc(");
      boolean first = true;
      sb.append("types:");
      if (this.types == null) {
         sb.append("null");
      } else {
         sb.append(this.types);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTypes()) {
         throw new TProtocolException("Required field 'types' is unset! Struct:" + this.toString());
      }
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TTypeDesc._Fields.TYPES, new FieldMetaData("types", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, TTypeEntry.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TTypeDesc.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPES((short)1, "types");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPES;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class TTypeDescStandardSchemeFactory implements SchemeFactory {
      private TTypeDescStandardSchemeFactory() {
      }

      public TTypeDescStandardScheme getScheme() {
         return new TTypeDescStandardScheme();
      }
   }

   private static class TTypeDescStandardScheme extends StandardScheme {
      private TTypeDescStandardScheme() {
      }

      public void read(TProtocol iprot, TTypeDesc struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list30 = iprot.readListBegin();
                  struct.types = new ArrayList(_list30.size);

                  for(int _i32 = 0; _i32 < _list30.size; ++_i32) {
                     TTypeEntry _elem31 = new TTypeEntry();
                     _elem31.read(iprot);
                     struct.types.add(_elem31);
                  }

                  iprot.readListEnd();
                  struct.setTypesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TTypeDesc struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TTypeDesc.STRUCT_DESC);
         if (struct.types != null) {
            oprot.writeFieldBegin(TTypeDesc.TYPES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.types.size()));

            for(TTypeEntry _iter33 : struct.types) {
               _iter33.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TTypeDescTupleSchemeFactory implements SchemeFactory {
      private TTypeDescTupleSchemeFactory() {
      }

      public TTypeDescTupleScheme getScheme() {
         return new TTypeDescTupleScheme();
      }
   }

   private static class TTypeDescTupleScheme extends TupleScheme {
      private TTypeDescTupleScheme() {
      }

      public void write(TProtocol prot, TTypeDesc struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.types.size());

         for(TTypeEntry _iter34 : struct.types) {
            _iter34.write(oprot);
         }

      }

      public void read(TProtocol prot, TTypeDesc struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list35 = iprot.readListBegin((byte)12);
         struct.types = new ArrayList(_list35.size);

         for(int _i37 = 0; _i37 < _list35.size; ++_i37) {
            TTypeEntry _elem36 = new TTypeEntry();
            _elem36.read(iprot);
            struct.types.add(_elem36);
         }

         struct.setTypesIsSet(true);
      }
   }
}
