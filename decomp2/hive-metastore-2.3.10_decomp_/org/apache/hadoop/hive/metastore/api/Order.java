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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class Order implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Order");
   private static final TField COL_FIELD_DESC = new TField("col", (byte)11, (short)1);
   private static final TField ORDER_FIELD_DESC = new TField("order", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OrderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OrderTupleSchemeFactory();
   @Nullable
   private String col;
   private int order;
   private static final int __ORDER_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Order() {
      this.__isset_bitfield = 0;
   }

   public Order(String col, int order) {
      this();
      this.col = col;
      this.order = order;
      this.setOrderIsSet(true);
   }

   public Order(Order other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetCol()) {
         this.col = other.col;
      }

      this.order = other.order;
   }

   public Order deepCopy() {
      return new Order(this);
   }

   public void clear() {
      this.col = null;
      this.setOrderIsSet(false);
      this.order = 0;
   }

   @Nullable
   public String getCol() {
      return this.col;
   }

   public void setCol(@Nullable String col) {
      this.col = col;
   }

   public void unsetCol() {
      this.col = null;
   }

   public boolean isSetCol() {
      return this.col != null;
   }

   public void setColIsSet(boolean value) {
      if (!value) {
         this.col = null;
      }

   }

   public int getOrder() {
      return this.order;
   }

   public void setOrder(int order) {
      this.order = order;
      this.setOrderIsSet(true);
   }

   public void unsetOrder() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetOrder() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setOrderIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COL:
            if (value == null) {
               this.unsetCol();
            } else {
               this.setCol((String)value);
            }
            break;
         case ORDER:
            if (value == null) {
               this.unsetOrder();
            } else {
               this.setOrder((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COL:
            return this.getCol();
         case ORDER:
            return this.getOrder();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COL:
               return this.isSetCol();
            case ORDER:
               return this.isSetOrder();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Order ? this.equals((Order)that) : false;
   }

   public boolean equals(Order that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_col = this.isSetCol();
         boolean that_present_col = that.isSetCol();
         if (this_present_col || that_present_col) {
            if (!this_present_col || !that_present_col) {
               return false;
            }

            if (!this.col.equals(that.col)) {
               return false;
            }
         }

         boolean this_present_order = true;
         boolean that_present_order = true;
         if (this_present_order || that_present_order) {
            if (!this_present_order || !that_present_order) {
               return false;
            }

            if (this.order != that.order) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetCol() ? 131071 : 524287);
      if (this.isSetCol()) {
         hashCode = hashCode * 8191 + this.col.hashCode();
      }

      hashCode = hashCode * 8191 + this.order;
      return hashCode;
   }

   public int compareTo(Order other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetCol(), other.isSetCol());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetCol()) {
               lastComparison = TBaseHelper.compareTo(this.col, other.col);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetOrder(), other.isSetOrder());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOrder()) {
                  lastComparison = TBaseHelper.compareTo(this.order, other.order);
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
      return Order._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Order(");
      boolean first = true;
      sb.append("col:");
      if (this.col == null) {
         sb.append("null");
      } else {
         sb.append(this.col);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("order:");
      sb.append(this.order);
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Order._Fields.COL, new FieldMetaData("col", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Order._Fields.ORDER, new FieldMetaData("order", (byte)3, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Order.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COL((short)1, "col"),
      ORDER((short)2, "order");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COL;
            case 2:
               return ORDER;
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

   private static class OrderStandardSchemeFactory implements SchemeFactory {
      private OrderStandardSchemeFactory() {
      }

      public OrderStandardScheme getScheme() {
         return new OrderStandardScheme();
      }
   }

   private static class OrderStandardScheme extends StandardScheme {
      private OrderStandardScheme() {
      }

      public void read(TProtocol iprot, Order struct) throws TException {
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
                     struct.col = iprot.readString();
                     struct.setColIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.order = iprot.readI32();
                     struct.setOrderIsSet(true);
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

      public void write(TProtocol oprot, Order struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Order.STRUCT_DESC);
         if (struct.col != null) {
            oprot.writeFieldBegin(Order.COL_FIELD_DESC);
            oprot.writeString(struct.col);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Order.ORDER_FIELD_DESC);
         oprot.writeI32(struct.order);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class OrderTupleSchemeFactory implements SchemeFactory {
      private OrderTupleSchemeFactory() {
      }

      public OrderTupleScheme getScheme() {
         return new OrderTupleScheme();
      }
   }

   private static class OrderTupleScheme extends TupleScheme {
      private OrderTupleScheme() {
      }

      public void write(TProtocol prot, Order struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetCol()) {
            optionals.set(0);
         }

         if (struct.isSetOrder()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetCol()) {
            oprot.writeString(struct.col);
         }

         if (struct.isSetOrder()) {
            oprot.writeI32(struct.order);
         }

      }

      public void read(TProtocol prot, Order struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.col = iprot.readString();
            struct.setColIsSet(true);
         }

         if (incoming.get(1)) {
            struct.order = iprot.readI32();
            struct.setOrderIsSet(true);
         }

      }
   }
}
