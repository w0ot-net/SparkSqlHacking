package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class DropPartitionsExpr implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DropPartitionsExpr");
   private static final TField EXPR_FIELD_DESC = new TField("expr", (byte)11, (short)1);
   private static final TField PART_ARCHIVE_LEVEL_FIELD_DESC = new TField("partArchiveLevel", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DropPartitionsExprStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DropPartitionsExprTupleSchemeFactory();
   @Nullable
   private ByteBuffer expr;
   private int partArchiveLevel;
   private static final int __PARTARCHIVELEVEL_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DropPartitionsExpr() {
      this.__isset_bitfield = 0;
   }

   public DropPartitionsExpr(ByteBuffer expr) {
      this();
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public DropPartitionsExpr(DropPartitionsExpr other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetExpr()) {
         this.expr = TBaseHelper.copyBinary(other.expr);
      }

      this.partArchiveLevel = other.partArchiveLevel;
   }

   public DropPartitionsExpr deepCopy() {
      return new DropPartitionsExpr(this);
   }

   public void clear() {
      this.expr = null;
      this.setPartArchiveLevelIsSet(false);
      this.partArchiveLevel = 0;
   }

   public byte[] getExpr() {
      this.setExpr(TBaseHelper.rightSize(this.expr));
      return this.expr == null ? null : this.expr.array();
   }

   public ByteBuffer bufferForExpr() {
      return TBaseHelper.copyBinary(this.expr);
   }

   public void setExpr(byte[] expr) {
      this.expr = expr == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)expr).clone());
   }

   public void setExpr(@Nullable ByteBuffer expr) {
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public void unsetExpr() {
      this.expr = null;
   }

   public boolean isSetExpr() {
      return this.expr != null;
   }

   public void setExprIsSet(boolean value) {
      if (!value) {
         this.expr = null;
      }

   }

   public int getPartArchiveLevel() {
      return this.partArchiveLevel;
   }

   public void setPartArchiveLevel(int partArchiveLevel) {
      this.partArchiveLevel = partArchiveLevel;
      this.setPartArchiveLevelIsSet(true);
   }

   public void unsetPartArchiveLevel() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetPartArchiveLevel() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setPartArchiveLevelIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case EXPR:
            if (value == null) {
               this.unsetExpr();
            } else if (value instanceof byte[]) {
               this.setExpr((byte[])value);
            } else {
               this.setExpr((ByteBuffer)value);
            }
            break;
         case PART_ARCHIVE_LEVEL:
            if (value == null) {
               this.unsetPartArchiveLevel();
            } else {
               this.setPartArchiveLevel((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case EXPR:
            return this.getExpr();
         case PART_ARCHIVE_LEVEL:
            return this.getPartArchiveLevel();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case EXPR:
               return this.isSetExpr();
            case PART_ARCHIVE_LEVEL:
               return this.isSetPartArchiveLevel();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DropPartitionsExpr ? this.equals((DropPartitionsExpr)that) : false;
   }

   public boolean equals(DropPartitionsExpr that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_expr = this.isSetExpr();
         boolean that_present_expr = that.isSetExpr();
         if (this_present_expr || that_present_expr) {
            if (!this_present_expr || !that_present_expr) {
               return false;
            }

            if (!this.expr.equals(that.expr)) {
               return false;
            }
         }

         boolean this_present_partArchiveLevel = this.isSetPartArchiveLevel();
         boolean that_present_partArchiveLevel = that.isSetPartArchiveLevel();
         if (this_present_partArchiveLevel || that_present_partArchiveLevel) {
            if (!this_present_partArchiveLevel || !that_present_partArchiveLevel) {
               return false;
            }

            if (this.partArchiveLevel != that.partArchiveLevel) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetExpr() ? 131071 : 524287);
      if (this.isSetExpr()) {
         hashCode = hashCode * 8191 + this.expr.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartArchiveLevel() ? 131071 : 524287);
      if (this.isSetPartArchiveLevel()) {
         hashCode = hashCode * 8191 + this.partArchiveLevel;
      }

      return hashCode;
   }

   public int compareTo(DropPartitionsExpr other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetExpr(), other.isSetExpr());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetExpr()) {
               lastComparison = TBaseHelper.compareTo(this.expr, other.expr);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPartArchiveLevel(), other.isSetPartArchiveLevel());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPartArchiveLevel()) {
                  lastComparison = TBaseHelper.compareTo(this.partArchiveLevel, other.partArchiveLevel);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return DropPartitionsExpr._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DropPartitionsExpr(");
      boolean first = true;
      sb.append("expr:");
      if (this.expr == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.expr, sb);
      }

      first = false;
      if (this.isSetPartArchiveLevel()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partArchiveLevel:");
         sb.append(this.partArchiveLevel);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetExpr()) {
         throw new TProtocolException("Required field 'expr' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{DropPartitionsExpr._Fields.PART_ARCHIVE_LEVEL};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DropPartitionsExpr._Fields.EXPR, new FieldMetaData("expr", (byte)1, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(DropPartitionsExpr._Fields.PART_ARCHIVE_LEVEL, new FieldMetaData("partArchiveLevel", (byte)2, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DropPartitionsExpr.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      EXPR((short)1, "expr"),
      PART_ARCHIVE_LEVEL((short)2, "partArchiveLevel");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return EXPR;
            case 2:
               return PART_ARCHIVE_LEVEL;
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

   private static class DropPartitionsExprStandardSchemeFactory implements SchemeFactory {
      private DropPartitionsExprStandardSchemeFactory() {
      }

      public DropPartitionsExprStandardScheme getScheme() {
         return new DropPartitionsExprStandardScheme();
      }
   }

   private static class DropPartitionsExprStandardScheme extends StandardScheme {
      private DropPartitionsExprStandardScheme() {
      }

      public void read(TProtocol iprot, DropPartitionsExpr struct) throws TException {
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
                     struct.expr = iprot.readBinary();
                     struct.setExprIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.partArchiveLevel = iprot.readI32();
                     struct.setPartArchiveLevelIsSet(true);
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

      public void write(TProtocol oprot, DropPartitionsExpr struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DropPartitionsExpr.STRUCT_DESC);
         if (struct.expr != null) {
            oprot.writeFieldBegin(DropPartitionsExpr.EXPR_FIELD_DESC);
            oprot.writeBinary(struct.expr);
            oprot.writeFieldEnd();
         }

         if (struct.isSetPartArchiveLevel()) {
            oprot.writeFieldBegin(DropPartitionsExpr.PART_ARCHIVE_LEVEL_FIELD_DESC);
            oprot.writeI32(struct.partArchiveLevel);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DropPartitionsExprTupleSchemeFactory implements SchemeFactory {
      private DropPartitionsExprTupleSchemeFactory() {
      }

      public DropPartitionsExprTupleScheme getScheme() {
         return new DropPartitionsExprTupleScheme();
      }
   }

   private static class DropPartitionsExprTupleScheme extends TupleScheme {
      private DropPartitionsExprTupleScheme() {
      }

      public void write(TProtocol prot, DropPartitionsExpr struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBinary(struct.expr);
         BitSet optionals = new BitSet();
         if (struct.isSetPartArchiveLevel()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetPartArchiveLevel()) {
            oprot.writeI32(struct.partArchiveLevel);
         }

      }

      public void read(TProtocol prot, DropPartitionsExpr struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.expr = iprot.readBinary();
         struct.setExprIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.partArchiveLevel = iprot.readI32();
            struct.setPartArchiveLevelIsSet(true);
         }

      }
   }
}
