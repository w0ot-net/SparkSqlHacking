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
import org.apache.hive.common.util.HiveStringUtils;
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

public class FieldSchema implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("FieldSchema");
   private static final TField NAME_FIELD_DESC = new TField("name", (byte)11, (short)1);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)11, (short)2);
   private static final TField COMMENT_FIELD_DESC = new TField("comment", (byte)11, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FieldSchemaStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FieldSchemaTupleSchemeFactory();
   @Nullable
   private String name;
   @Nullable
   private String type;
   @Nullable
   private String comment;
   public static final Map metaDataMap;

   public FieldSchema() {
   }

   public FieldSchema(String name, String type, String comment) {
      this();
      this.name = HiveStringUtils.intern(name);
      this.type = HiveStringUtils.intern(type);
      this.comment = HiveStringUtils.intern(comment);
   }

   public FieldSchema(FieldSchema other) {
      if (other.isSetName()) {
         this.name = HiveStringUtils.intern(other.name);
      }

      if (other.isSetType()) {
         this.type = HiveStringUtils.intern(other.type);
      }

      if (other.isSetComment()) {
         this.comment = HiveStringUtils.intern(other.comment);
      }

   }

   public FieldSchema deepCopy() {
      return new FieldSchema(this);
   }

   public void clear() {
      this.name = null;
      this.type = null;
      this.comment = null;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(@Nullable String name) {
      this.name = HiveStringUtils.intern(name);
   }

   public void unsetName() {
      this.name = null;
   }

   public boolean isSetName() {
      return this.name != null;
   }

   public void setNameIsSet(boolean value) {
      if (!value) {
         this.name = null;
      }

   }

   @Nullable
   public String getType() {
      return this.type;
   }

   public void setType(@Nullable String type) {
      this.type = HiveStringUtils.intern(type);
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   @Nullable
   public String getComment() {
      return this.comment;
   }

   public void setComment(@Nullable String comment) {
      this.comment = HiveStringUtils.intern(comment);
   }

   public void unsetComment() {
      this.comment = null;
   }

   public boolean isSetComment() {
      return this.comment != null;
   }

   public void setCommentIsSet(boolean value) {
      if (!value) {
         this.comment = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NAME:
            if (value == null) {
               this.unsetName();
            } else {
               this.setName((String)value);
            }
            break;
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((String)value);
            }
            break;
         case COMMENT:
            if (value == null) {
               this.unsetComment();
            } else {
               this.setComment((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NAME:
            return this.getName();
         case TYPE:
            return this.getType();
         case COMMENT:
            return this.getComment();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NAME:
               return this.isSetName();
            case TYPE:
               return this.isSetType();
            case COMMENT:
               return this.isSetComment();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof FieldSchema ? this.equals((FieldSchema)that) : false;
   }

   public boolean equals(FieldSchema that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_name = this.isSetName();
         boolean that_present_name = that.isSetName();
         if (this_present_name || that_present_name) {
            if (!this_present_name || !that_present_name) {
               return false;
            }

            if (!this.name.equals(that.name)) {
               return false;
            }
         }

         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_comment = this.isSetComment();
         boolean that_present_comment = that.isSetComment();
         if (this_present_comment || that_present_comment) {
            if (!this_present_comment || !that_present_comment) {
               return false;
            }

            if (!this.comment.equals(that.comment)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetName() ? 131071 : 524287);
      if (this.isSetName()) {
         hashCode = hashCode * 8191 + this.name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetComment() ? 131071 : 524287);
      if (this.isSetComment()) {
         hashCode = hashCode * 8191 + this.comment.hashCode();
      }

      return hashCode;
   }

   public int compareTo(FieldSchema other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetName(), other.isSetName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetName()) {
               lastComparison = TBaseHelper.compareTo(this.name, other.name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetType()) {
                  lastComparison = TBaseHelper.compareTo(this.type, other.type);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetComment(), other.isSetComment());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetComment()) {
                     lastComparison = TBaseHelper.compareTo(this.comment, other.comment);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return FieldSchema._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("FieldSchema(");
      boolean first = true;
      sb.append("name:");
      if (this.name == null) {
         sb.append("null");
      } else {
         sb.append(this.name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("type:");
      if (this.type == null) {
         sb.append("null");
      } else {
         sb.append(this.type);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("comment:");
      if (this.comment == null) {
         sb.append("null");
      } else {
         sb.append(this.comment);
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
      tmpMap.put(FieldSchema._Fields.NAME, new FieldMetaData("name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(FieldSchema._Fields.TYPE, new FieldMetaData("type", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(FieldSchema._Fields.COMMENT, new FieldMetaData("comment", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(FieldSchema.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAME((short)1, "name"),
      TYPE((short)2, "type"),
      COMMENT((short)3, "comment");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAME;
            case 2:
               return TYPE;
            case 3:
               return COMMENT;
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

   private static class FieldSchemaStandardSchemeFactory implements SchemeFactory {
      private FieldSchemaStandardSchemeFactory() {
      }

      public FieldSchemaStandardScheme getScheme() {
         return new FieldSchemaStandardScheme();
      }
   }

   private static class FieldSchemaStandardScheme extends StandardScheme {
      private FieldSchemaStandardScheme() {
      }

      public void read(TProtocol iprot, FieldSchema struct) throws TException {
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
                     struct.name = iprot.readString();
                     struct.setNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.type = iprot.readString();
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.comment = iprot.readString();
                     struct.setCommentIsSet(true);
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

      public void write(TProtocol oprot, FieldSchema struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(FieldSchema.STRUCT_DESC);
         if (struct.name != null) {
            oprot.writeFieldBegin(FieldSchema.NAME_FIELD_DESC);
            oprot.writeString(struct.name);
            oprot.writeFieldEnd();
         }

         if (struct.type != null) {
            oprot.writeFieldBegin(FieldSchema.TYPE_FIELD_DESC);
            oprot.writeString(struct.type);
            oprot.writeFieldEnd();
         }

         if (struct.comment != null) {
            oprot.writeFieldBegin(FieldSchema.COMMENT_FIELD_DESC);
            oprot.writeString(struct.comment);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class FieldSchemaTupleSchemeFactory implements SchemeFactory {
      private FieldSchemaTupleSchemeFactory() {
      }

      public FieldSchemaTupleScheme getScheme() {
         return new FieldSchemaTupleScheme();
      }
   }

   private static class FieldSchemaTupleScheme extends TupleScheme {
      private FieldSchemaTupleScheme() {
      }

      public void write(TProtocol prot, FieldSchema struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetName()) {
            optionals.set(0);
         }

         if (struct.isSetType()) {
            optionals.set(1);
         }

         if (struct.isSetComment()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetName()) {
            oprot.writeString(struct.name);
         }

         if (struct.isSetType()) {
            oprot.writeString(struct.type);
         }

         if (struct.isSetComment()) {
            oprot.writeString(struct.comment);
         }

      }

      public void read(TProtocol prot, FieldSchema struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.name = iprot.readString();
            struct.setNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.type = iprot.readString();
            struct.setTypeIsSet(true);
         }

         if (incoming.get(2)) {
            struct.comment = iprot.readString();
            struct.setCommentIsSet(true);
         }

      }
   }
}
