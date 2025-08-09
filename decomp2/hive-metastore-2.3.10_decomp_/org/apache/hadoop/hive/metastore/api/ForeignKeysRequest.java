package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class ForeignKeysRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ForeignKeysRequest");
   private static final TField PARENT_DB_NAME_FIELD_DESC = new TField("parent_db_name", (byte)11, (short)1);
   private static final TField PARENT_TBL_NAME_FIELD_DESC = new TField("parent_tbl_name", (byte)11, (short)2);
   private static final TField FOREIGN_DB_NAME_FIELD_DESC = new TField("foreign_db_name", (byte)11, (short)3);
   private static final TField FOREIGN_TBL_NAME_FIELD_DESC = new TField("foreign_tbl_name", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ForeignKeysRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ForeignKeysRequestTupleSchemeFactory();
   @Nullable
   private String parent_db_name;
   @Nullable
   private String parent_tbl_name;
   @Nullable
   private String foreign_db_name;
   @Nullable
   private String foreign_tbl_name;
   public static final Map metaDataMap;

   public ForeignKeysRequest() {
   }

   public ForeignKeysRequest(String parent_db_name, String parent_tbl_name, String foreign_db_name, String foreign_tbl_name) {
      this();
      this.parent_db_name = parent_db_name;
      this.parent_tbl_name = parent_tbl_name;
      this.foreign_db_name = foreign_db_name;
      this.foreign_tbl_name = foreign_tbl_name;
   }

   public ForeignKeysRequest(ForeignKeysRequest other) {
      if (other.isSetParent_db_name()) {
         this.parent_db_name = other.parent_db_name;
      }

      if (other.isSetParent_tbl_name()) {
         this.parent_tbl_name = other.parent_tbl_name;
      }

      if (other.isSetForeign_db_name()) {
         this.foreign_db_name = other.foreign_db_name;
      }

      if (other.isSetForeign_tbl_name()) {
         this.foreign_tbl_name = other.foreign_tbl_name;
      }

   }

   public ForeignKeysRequest deepCopy() {
      return new ForeignKeysRequest(this);
   }

   public void clear() {
      this.parent_db_name = null;
      this.parent_tbl_name = null;
      this.foreign_db_name = null;
      this.foreign_tbl_name = null;
   }

   @Nullable
   public String getParent_db_name() {
      return this.parent_db_name;
   }

   public void setParent_db_name(@Nullable String parent_db_name) {
      this.parent_db_name = parent_db_name;
   }

   public void unsetParent_db_name() {
      this.parent_db_name = null;
   }

   public boolean isSetParent_db_name() {
      return this.parent_db_name != null;
   }

   public void setParent_db_nameIsSet(boolean value) {
      if (!value) {
         this.parent_db_name = null;
      }

   }

   @Nullable
   public String getParent_tbl_name() {
      return this.parent_tbl_name;
   }

   public void setParent_tbl_name(@Nullable String parent_tbl_name) {
      this.parent_tbl_name = parent_tbl_name;
   }

   public void unsetParent_tbl_name() {
      this.parent_tbl_name = null;
   }

   public boolean isSetParent_tbl_name() {
      return this.parent_tbl_name != null;
   }

   public void setParent_tbl_nameIsSet(boolean value) {
      if (!value) {
         this.parent_tbl_name = null;
      }

   }

   @Nullable
   public String getForeign_db_name() {
      return this.foreign_db_name;
   }

   public void setForeign_db_name(@Nullable String foreign_db_name) {
      this.foreign_db_name = foreign_db_name;
   }

   public void unsetForeign_db_name() {
      this.foreign_db_name = null;
   }

   public boolean isSetForeign_db_name() {
      return this.foreign_db_name != null;
   }

   public void setForeign_db_nameIsSet(boolean value) {
      if (!value) {
         this.foreign_db_name = null;
      }

   }

   @Nullable
   public String getForeign_tbl_name() {
      return this.foreign_tbl_name;
   }

   public void setForeign_tbl_name(@Nullable String foreign_tbl_name) {
      this.foreign_tbl_name = foreign_tbl_name;
   }

   public void unsetForeign_tbl_name() {
      this.foreign_tbl_name = null;
   }

   public boolean isSetForeign_tbl_name() {
      return this.foreign_tbl_name != null;
   }

   public void setForeign_tbl_nameIsSet(boolean value) {
      if (!value) {
         this.foreign_tbl_name = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PARENT_DB_NAME:
            if (value == null) {
               this.unsetParent_db_name();
            } else {
               this.setParent_db_name((String)value);
            }
            break;
         case PARENT_TBL_NAME:
            if (value == null) {
               this.unsetParent_tbl_name();
            } else {
               this.setParent_tbl_name((String)value);
            }
            break;
         case FOREIGN_DB_NAME:
            if (value == null) {
               this.unsetForeign_db_name();
            } else {
               this.setForeign_db_name((String)value);
            }
            break;
         case FOREIGN_TBL_NAME:
            if (value == null) {
               this.unsetForeign_tbl_name();
            } else {
               this.setForeign_tbl_name((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PARENT_DB_NAME:
            return this.getParent_db_name();
         case PARENT_TBL_NAME:
            return this.getParent_tbl_name();
         case FOREIGN_DB_NAME:
            return this.getForeign_db_name();
         case FOREIGN_TBL_NAME:
            return this.getForeign_tbl_name();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PARENT_DB_NAME:
               return this.isSetParent_db_name();
            case PARENT_TBL_NAME:
               return this.isSetParent_tbl_name();
            case FOREIGN_DB_NAME:
               return this.isSetForeign_db_name();
            case FOREIGN_TBL_NAME:
               return this.isSetForeign_tbl_name();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ForeignKeysRequest ? this.equals((ForeignKeysRequest)that) : false;
   }

   public boolean equals(ForeignKeysRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_parent_db_name = this.isSetParent_db_name();
         boolean that_present_parent_db_name = that.isSetParent_db_name();
         if (this_present_parent_db_name || that_present_parent_db_name) {
            if (!this_present_parent_db_name || !that_present_parent_db_name) {
               return false;
            }

            if (!this.parent_db_name.equals(that.parent_db_name)) {
               return false;
            }
         }

         boolean this_present_parent_tbl_name = this.isSetParent_tbl_name();
         boolean that_present_parent_tbl_name = that.isSetParent_tbl_name();
         if (this_present_parent_tbl_name || that_present_parent_tbl_name) {
            if (!this_present_parent_tbl_name || !that_present_parent_tbl_name) {
               return false;
            }

            if (!this.parent_tbl_name.equals(that.parent_tbl_name)) {
               return false;
            }
         }

         boolean this_present_foreign_db_name = this.isSetForeign_db_name();
         boolean that_present_foreign_db_name = that.isSetForeign_db_name();
         if (this_present_foreign_db_name || that_present_foreign_db_name) {
            if (!this_present_foreign_db_name || !that_present_foreign_db_name) {
               return false;
            }

            if (!this.foreign_db_name.equals(that.foreign_db_name)) {
               return false;
            }
         }

         boolean this_present_foreign_tbl_name = this.isSetForeign_tbl_name();
         boolean that_present_foreign_tbl_name = that.isSetForeign_tbl_name();
         if (this_present_foreign_tbl_name || that_present_foreign_tbl_name) {
            if (!this_present_foreign_tbl_name || !that_present_foreign_tbl_name) {
               return false;
            }

            if (!this.foreign_tbl_name.equals(that.foreign_tbl_name)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetParent_db_name() ? 131071 : 524287);
      if (this.isSetParent_db_name()) {
         hashCode = hashCode * 8191 + this.parent_db_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParent_tbl_name() ? 131071 : 524287);
      if (this.isSetParent_tbl_name()) {
         hashCode = hashCode * 8191 + this.parent_tbl_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetForeign_db_name() ? 131071 : 524287);
      if (this.isSetForeign_db_name()) {
         hashCode = hashCode * 8191 + this.foreign_db_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetForeign_tbl_name() ? 131071 : 524287);
      if (this.isSetForeign_tbl_name()) {
         hashCode = hashCode * 8191 + this.foreign_tbl_name.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ForeignKeysRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetParent_db_name(), other.isSetParent_db_name());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetParent_db_name()) {
               lastComparison = TBaseHelper.compareTo(this.parent_db_name, other.parent_db_name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetParent_tbl_name(), other.isSetParent_tbl_name());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetParent_tbl_name()) {
                  lastComparison = TBaseHelper.compareTo(this.parent_tbl_name, other.parent_tbl_name);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetForeign_db_name(), other.isSetForeign_db_name());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetForeign_db_name()) {
                     lastComparison = TBaseHelper.compareTo(this.foreign_db_name, other.foreign_db_name);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetForeign_tbl_name(), other.isSetForeign_tbl_name());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetForeign_tbl_name()) {
                        lastComparison = TBaseHelper.compareTo(this.foreign_tbl_name, other.foreign_tbl_name);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     return 0;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ForeignKeysRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ForeignKeysRequest(");
      boolean first = true;
      sb.append("parent_db_name:");
      if (this.parent_db_name == null) {
         sb.append("null");
      } else {
         sb.append(this.parent_db_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("parent_tbl_name:");
      if (this.parent_tbl_name == null) {
         sb.append("null");
      } else {
         sb.append(this.parent_tbl_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("foreign_db_name:");
      if (this.foreign_db_name == null) {
         sb.append("null");
      } else {
         sb.append(this.foreign_db_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("foreign_tbl_name:");
      if (this.foreign_tbl_name == null) {
         sb.append("null");
      } else {
         sb.append(this.foreign_tbl_name);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
      tmpMap.put(ForeignKeysRequest._Fields.PARENT_DB_NAME, new FieldMetaData("parent_db_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(ForeignKeysRequest._Fields.PARENT_TBL_NAME, new FieldMetaData("parent_tbl_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(ForeignKeysRequest._Fields.FOREIGN_DB_NAME, new FieldMetaData("foreign_db_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(ForeignKeysRequest._Fields.FOREIGN_TBL_NAME, new FieldMetaData("foreign_tbl_name", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ForeignKeysRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PARENT_DB_NAME((short)1, "parent_db_name"),
      PARENT_TBL_NAME((short)2, "parent_tbl_name"),
      FOREIGN_DB_NAME((short)3, "foreign_db_name"),
      FOREIGN_TBL_NAME((short)4, "foreign_tbl_name");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PARENT_DB_NAME;
            case 2:
               return PARENT_TBL_NAME;
            case 3:
               return FOREIGN_DB_NAME;
            case 4:
               return FOREIGN_TBL_NAME;
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

   private static class ForeignKeysRequestStandardSchemeFactory implements SchemeFactory {
      private ForeignKeysRequestStandardSchemeFactory() {
      }

      public ForeignKeysRequestStandardScheme getScheme() {
         return new ForeignKeysRequestStandardScheme();
      }
   }

   private static class ForeignKeysRequestStandardScheme extends StandardScheme {
      private ForeignKeysRequestStandardScheme() {
      }

      public void read(TProtocol iprot, ForeignKeysRequest struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.parent_db_name = iprot.readString();
                     struct.setParent_db_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.parent_tbl_name = iprot.readString();
                     struct.setParent_tbl_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.foreign_db_name = iprot.readString();
                     struct.setForeign_db_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.foreign_tbl_name = iprot.readString();
                     struct.setForeign_tbl_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ForeignKeysRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ForeignKeysRequest.STRUCT_DESC);
         if (struct.parent_db_name != null) {
            oprot.writeFieldBegin(ForeignKeysRequest.PARENT_DB_NAME_FIELD_DESC);
            oprot.writeString(struct.parent_db_name);
            oprot.writeFieldEnd();
         }

         if (struct.parent_tbl_name != null) {
            oprot.writeFieldBegin(ForeignKeysRequest.PARENT_TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.parent_tbl_name);
            oprot.writeFieldEnd();
         }

         if (struct.foreign_db_name != null) {
            oprot.writeFieldBegin(ForeignKeysRequest.FOREIGN_DB_NAME_FIELD_DESC);
            oprot.writeString(struct.foreign_db_name);
            oprot.writeFieldEnd();
         }

         if (struct.foreign_tbl_name != null) {
            oprot.writeFieldBegin(ForeignKeysRequest.FOREIGN_TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.foreign_tbl_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ForeignKeysRequestTupleSchemeFactory implements SchemeFactory {
      private ForeignKeysRequestTupleSchemeFactory() {
      }

      public ForeignKeysRequestTupleScheme getScheme() {
         return new ForeignKeysRequestTupleScheme();
      }
   }

   private static class ForeignKeysRequestTupleScheme extends TupleScheme {
      private ForeignKeysRequestTupleScheme() {
      }

      public void write(TProtocol prot, ForeignKeysRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetParent_db_name()) {
            optionals.set(0);
         }

         if (struct.isSetParent_tbl_name()) {
            optionals.set(1);
         }

         if (struct.isSetForeign_db_name()) {
            optionals.set(2);
         }

         if (struct.isSetForeign_tbl_name()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetParent_db_name()) {
            oprot.writeString(struct.parent_db_name);
         }

         if (struct.isSetParent_tbl_name()) {
            oprot.writeString(struct.parent_tbl_name);
         }

         if (struct.isSetForeign_db_name()) {
            oprot.writeString(struct.foreign_db_name);
         }

         if (struct.isSetForeign_tbl_name()) {
            oprot.writeString(struct.foreign_tbl_name);
         }

      }

      public void read(TProtocol prot, ForeignKeysRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.parent_db_name = iprot.readString();
            struct.setParent_db_nameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.parent_tbl_name = iprot.readString();
            struct.setParent_tbl_nameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.foreign_db_name = iprot.readString();
            struct.setForeign_db_nameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.foreign_tbl_name = iprot.readString();
            struct.setForeign_tbl_nameIsSet(true);
         }

      }
   }
}
