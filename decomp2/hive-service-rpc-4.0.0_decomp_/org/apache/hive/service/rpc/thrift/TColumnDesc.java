package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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
public class TColumnDesc implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TColumnDesc");
   private static final TField COLUMN_NAME_FIELD_DESC = new TField("columnName", (byte)11, (short)1);
   private static final TField TYPE_DESC_FIELD_DESC = new TField("typeDesc", (byte)12, (short)2);
   private static final TField POSITION_FIELD_DESC = new TField("position", (byte)8, (short)3);
   private static final TField COMMENT_FIELD_DESC = new TField("comment", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TColumnDescStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TColumnDescTupleSchemeFactory();
   @Nullable
   private String columnName;
   @Nullable
   private TTypeDesc typeDesc;
   private int position;
   @Nullable
   private String comment;
   private static final int __POSITION_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TColumnDesc() {
      this.__isset_bitfield = 0;
   }

   public TColumnDesc(String columnName, TTypeDesc typeDesc, int position) {
      this();
      this.columnName = columnName;
      this.typeDesc = typeDesc;
      this.position = position;
      this.setPositionIsSet(true);
   }

   public TColumnDesc(TColumnDesc other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetColumnName()) {
         this.columnName = other.columnName;
      }

      if (other.isSetTypeDesc()) {
         this.typeDesc = new TTypeDesc(other.typeDesc);
      }

      this.position = other.position;
      if (other.isSetComment()) {
         this.comment = other.comment;
      }

   }

   public TColumnDesc deepCopy() {
      return new TColumnDesc(this);
   }

   public void clear() {
      this.columnName = null;
      this.typeDesc = null;
      this.setPositionIsSet(false);
      this.position = 0;
      this.comment = null;
   }

   @Nullable
   public String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(@Nullable String columnName) {
      this.columnName = columnName;
   }

   public void unsetColumnName() {
      this.columnName = null;
   }

   public boolean isSetColumnName() {
      return this.columnName != null;
   }

   public void setColumnNameIsSet(boolean value) {
      if (!value) {
         this.columnName = null;
      }

   }

   @Nullable
   public TTypeDesc getTypeDesc() {
      return this.typeDesc;
   }

   public void setTypeDesc(@Nullable TTypeDesc typeDesc) {
      this.typeDesc = typeDesc;
   }

   public void unsetTypeDesc() {
      this.typeDesc = null;
   }

   public boolean isSetTypeDesc() {
      return this.typeDesc != null;
   }

   public void setTypeDescIsSet(boolean value) {
      if (!value) {
         this.typeDesc = null;
      }

   }

   public int getPosition() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
      this.setPositionIsSet(true);
   }

   public void unsetPosition() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetPosition() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setPositionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getComment() {
      return this.comment;
   }

   public void setComment(@Nullable String comment) {
      this.comment = comment;
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
         case COLUMN_NAME:
            if (value == null) {
               this.unsetColumnName();
            } else {
               this.setColumnName((String)value);
            }
            break;
         case TYPE_DESC:
            if (value == null) {
               this.unsetTypeDesc();
            } else {
               this.setTypeDesc((TTypeDesc)value);
            }
            break;
         case POSITION:
            if (value == null) {
               this.unsetPosition();
            } else {
               this.setPosition((Integer)value);
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
         case COLUMN_NAME:
            return this.getColumnName();
         case TYPE_DESC:
            return this.getTypeDesc();
         case POSITION:
            return this.getPosition();
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
            case COLUMN_NAME:
               return this.isSetColumnName();
            case TYPE_DESC:
               return this.isSetTypeDesc();
            case POSITION:
               return this.isSetPosition();
            case COMMENT:
               return this.isSetComment();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TColumnDesc ? this.equals((TColumnDesc)that) : false;
   }

   public boolean equals(TColumnDesc that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_columnName = this.isSetColumnName();
         boolean that_present_columnName = that.isSetColumnName();
         if (this_present_columnName || that_present_columnName) {
            if (!this_present_columnName || !that_present_columnName) {
               return false;
            }

            if (!this.columnName.equals(that.columnName)) {
               return false;
            }
         }

         boolean this_present_typeDesc = this.isSetTypeDesc();
         boolean that_present_typeDesc = that.isSetTypeDesc();
         if (this_present_typeDesc || that_present_typeDesc) {
            if (!this_present_typeDesc || !that_present_typeDesc) {
               return false;
            }

            if (!this.typeDesc.equals(that.typeDesc)) {
               return false;
            }
         }

         boolean this_present_position = true;
         boolean that_present_position = true;
         if (this_present_position || that_present_position) {
            if (!this_present_position || !that_present_position) {
               return false;
            }

            if (this.position != that.position) {
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
      hashCode = hashCode * 8191 + (this.isSetColumnName() ? 131071 : 524287);
      if (this.isSetColumnName()) {
         hashCode = hashCode * 8191 + this.columnName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTypeDesc() ? 131071 : 524287);
      if (this.isSetTypeDesc()) {
         hashCode = hashCode * 8191 + this.typeDesc.hashCode();
      }

      hashCode = hashCode * 8191 + this.position;
      hashCode = hashCode * 8191 + (this.isSetComment() ? 131071 : 524287);
      if (this.isSetComment()) {
         hashCode = hashCode * 8191 + this.comment.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TColumnDesc other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColumnName(), other.isSetColumnName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColumnName()) {
               lastComparison = TBaseHelper.compareTo(this.columnName, other.columnName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTypeDesc(), other.isSetTypeDesc());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTypeDesc()) {
                  lastComparison = TBaseHelper.compareTo(this.typeDesc, other.typeDesc);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPosition(), other.isSetPosition());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPosition()) {
                     lastComparison = TBaseHelper.compareTo(this.position, other.position);
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
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TColumnDesc._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TColumnDesc(");
      boolean first = true;
      sb.append("columnName:");
      if (this.columnName == null) {
         sb.append("null");
      } else {
         sb.append(this.columnName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("typeDesc:");
      if (this.typeDesc == null) {
         sb.append("null");
      } else {
         sb.append(this.typeDesc);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("position:");
      sb.append(this.position);
      first = false;
      if (this.isSetComment()) {
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
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColumnName()) {
         throw new TProtocolException("Required field 'columnName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTypeDesc()) {
         throw new TProtocolException("Required field 'typeDesc' is unset! Struct:" + this.toString());
      } else if (!this.isSetPosition()) {
         throw new TProtocolException("Required field 'position' is unset! Struct:" + this.toString());
      } else {
         if (this.typeDesc != null) {
            this.typeDesc.validate();
         }

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
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{TColumnDesc._Fields.COMMENT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TColumnDesc._Fields.COLUMN_NAME, new FieldMetaData("columnName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TColumnDesc._Fields.TYPE_DESC, new FieldMetaData("typeDesc", (byte)1, new StructMetaData((byte)12, TTypeDesc.class)));
      tmpMap.put(TColumnDesc._Fields.POSITION, new FieldMetaData("position", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(TColumnDesc._Fields.COMMENT, new FieldMetaData("comment", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TColumnDesc.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COLUMN_NAME((short)1, "columnName"),
      TYPE_DESC((short)2, "typeDesc"),
      POSITION((short)3, "position"),
      COMMENT((short)4, "comment");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COLUMN_NAME;
            case 2:
               return TYPE_DESC;
            case 3:
               return POSITION;
            case 4:
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

   private static class TColumnDescStandardSchemeFactory implements SchemeFactory {
      private TColumnDescStandardSchemeFactory() {
      }

      public TColumnDescStandardScheme getScheme() {
         return new TColumnDescStandardScheme();
      }
   }

   private static class TColumnDescStandardScheme extends StandardScheme {
      private TColumnDescStandardScheme() {
      }

      public void read(TProtocol iprot, TColumnDesc struct) throws TException {
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
                     struct.columnName = iprot.readString();
                     struct.setColumnNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.typeDesc = new TTypeDesc();
                     struct.typeDesc.read(iprot);
                     struct.setTypeDescIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.position = iprot.readI32();
                     struct.setPositionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
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

      public void write(TProtocol oprot, TColumnDesc struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TColumnDesc.STRUCT_DESC);
         if (struct.columnName != null) {
            oprot.writeFieldBegin(TColumnDesc.COLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.columnName);
            oprot.writeFieldEnd();
         }

         if (struct.typeDesc != null) {
            oprot.writeFieldBegin(TColumnDesc.TYPE_DESC_FIELD_DESC);
            struct.typeDesc.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(TColumnDesc.POSITION_FIELD_DESC);
         oprot.writeI32(struct.position);
         oprot.writeFieldEnd();
         if (struct.comment != null && struct.isSetComment()) {
            oprot.writeFieldBegin(TColumnDesc.COMMENT_FIELD_DESC);
            oprot.writeString(struct.comment);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TColumnDescTupleSchemeFactory implements SchemeFactory {
      private TColumnDescTupleSchemeFactory() {
      }

      public TColumnDescTupleScheme getScheme() {
         return new TColumnDescTupleScheme();
      }
   }

   private static class TColumnDescTupleScheme extends TupleScheme {
      private TColumnDescTupleScheme() {
      }

      public void write(TProtocol prot, TColumnDesc struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.columnName);
         struct.typeDesc.write(oprot);
         oprot.writeI32(struct.position);
         BitSet optionals = new BitSet();
         if (struct.isSetComment()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetComment()) {
            oprot.writeString(struct.comment);
         }

      }

      public void read(TProtocol prot, TColumnDesc struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.columnName = iprot.readString();
         struct.setColumnNameIsSet(true);
         struct.typeDesc = new TTypeDesc();
         struct.typeDesc.read(iprot);
         struct.setTypeDescIsSet(true);
         struct.position = iprot.readI32();
         struct.setPositionIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.comment = iprot.readString();
            struct.setCommentIsSet(true);
         }

      }
   }
}
