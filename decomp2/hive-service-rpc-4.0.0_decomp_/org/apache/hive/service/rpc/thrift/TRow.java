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
public class TRow implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TRow");
   private static final TField COL_VALS_FIELD_DESC = new TField("colVals", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TRowStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TRowTupleSchemeFactory();
   @Nullable
   private List colVals;
   public static final Map metaDataMap;

   public TRow() {
   }

   public TRow(List colVals) {
      this();
      this.colVals = colVals;
   }

   public TRow(TRow other) {
      if (other.isSetColVals()) {
         List<TColumnValue> __this__colVals = new ArrayList(other.colVals.size());

         for(TColumnValue other_element : other.colVals) {
            __this__colVals.add(new TColumnValue(other_element));
         }

         this.colVals = __this__colVals;
      }

   }

   public TRow deepCopy() {
      return new TRow(this);
   }

   public void clear() {
      this.colVals = null;
   }

   public int getColValsSize() {
      return this.colVals == null ? 0 : this.colVals.size();
   }

   @Nullable
   public Iterator getColValsIterator() {
      return this.colVals == null ? null : this.colVals.iterator();
   }

   public void addToColVals(TColumnValue elem) {
      if (this.colVals == null) {
         this.colVals = new ArrayList();
      }

      this.colVals.add(elem);
   }

   @Nullable
   public List getColVals() {
      return this.colVals;
   }

   public void setColVals(@Nullable List colVals) {
      this.colVals = colVals;
   }

   public void unsetColVals() {
      this.colVals = null;
   }

   public boolean isSetColVals() {
      return this.colVals != null;
   }

   public void setColValsIsSet(boolean value) {
      if (!value) {
         this.colVals = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COL_VALS:
            if (value == null) {
               this.unsetColVals();
            } else {
               this.setColVals((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COL_VALS:
            return this.getColVals();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COL_VALS:
               return this.isSetColVals();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TRow ? this.equals((TRow)that) : false;
   }

   public boolean equals(TRow that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_colVals = this.isSetColVals();
         boolean that_present_colVals = that.isSetColVals();
         if (this_present_colVals || that_present_colVals) {
            if (!this_present_colVals || !that_present_colVals) {
               return false;
            }

            if (!this.colVals.equals(that.colVals)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetColVals() ? 131071 : 524287);
      if (this.isSetColVals()) {
         hashCode = hashCode * 8191 + this.colVals.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TRow other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColVals(), other.isSetColVals());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColVals()) {
               lastComparison = TBaseHelper.compareTo(this.colVals, other.colVals);
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
      return TRow._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TRow(");
      boolean first = true;
      sb.append("colVals:");
      if (this.colVals == null) {
         sb.append("null");
      } else {
         sb.append(this.colVals);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColVals()) {
         throw new TProtocolException("Required field 'colVals' is unset! Struct:" + this.toString());
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
      tmpMap.put(TRow._Fields.COL_VALS, new FieldMetaData("colVals", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, TColumnValue.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TRow.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COL_VALS((short)1, "colVals");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COL_VALS;
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

   private static class TRowStandardSchemeFactory implements SchemeFactory {
      private TRowStandardSchemeFactory() {
      }

      public TRowStandardScheme getScheme() {
         return new TRowStandardScheme();
      }
   }

   private static class TRowStandardScheme extends StandardScheme {
      private TRowStandardScheme() {
      }

      public void read(TProtocol iprot, TRow struct) throws TException {
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

                  TList _list46 = iprot.readListBegin();
                  struct.colVals = new ArrayList(_list46.size);

                  for(int _i48 = 0; _i48 < _list46.size; ++_i48) {
                     TColumnValue _elem47 = new TColumnValue();
                     _elem47.read(iprot);
                     struct.colVals.add(_elem47);
                  }

                  iprot.readListEnd();
                  struct.setColValsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TRow struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TRow.STRUCT_DESC);
         if (struct.colVals != null) {
            oprot.writeFieldBegin(TRow.COL_VALS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.colVals.size()));

            for(TColumnValue _iter49 : struct.colVals) {
               _iter49.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TRowTupleSchemeFactory implements SchemeFactory {
      private TRowTupleSchemeFactory() {
      }

      public TRowTupleScheme getScheme() {
         return new TRowTupleScheme();
      }
   }

   private static class TRowTupleScheme extends TupleScheme {
      private TRowTupleScheme() {
      }

      public void write(TProtocol prot, TRow struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.colVals.size());

         for(TColumnValue _iter50 : struct.colVals) {
            _iter50.write(oprot);
         }

      }

      public void read(TProtocol prot, TRow struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list51 = iprot.readListBegin((byte)12);
         struct.colVals = new ArrayList(_list51.size);

         for(int _i53 = 0; _i53 < _list51.size; ++_i53) {
            TColumnValue _elem52 = new TColumnValue();
            _elem52.read(iprot);
            struct.colVals.add(_elem52);
         }

         struct.setColValsIsSet(true);
      }
   }
}
