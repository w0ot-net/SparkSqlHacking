package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class SkewedInfo implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SkewedInfo");
   private static final TField SKEWED_COL_NAMES_FIELD_DESC = new TField("skewedColNames", (byte)15, (short)1);
   private static final TField SKEWED_COL_VALUES_FIELD_DESC = new TField("skewedColValues", (byte)15, (short)2);
   private static final TField SKEWED_COL_VALUE_LOCATION_MAPS_FIELD_DESC = new TField("skewedColValueLocationMaps", (byte)13, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SkewedInfoStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SkewedInfoTupleSchemeFactory();
   @Nullable
   private List skewedColNames;
   @Nullable
   private List skewedColValues;
   @Nullable
   private Map skewedColValueLocationMaps;
   public static final Map metaDataMap;

   public SkewedInfo() {
   }

   public SkewedInfo(List skewedColNames, List skewedColValues, Map skewedColValueLocationMaps) {
      this();
      this.skewedColNames = skewedColNames;
      this.skewedColValues = skewedColValues;
      this.skewedColValueLocationMaps = skewedColValueLocationMaps;
   }

   public SkewedInfo(SkewedInfo other) {
      if (other.isSetSkewedColNames()) {
         List<String> __this__skewedColNames = new ArrayList(other.skewedColNames);
         this.skewedColNames = __this__skewedColNames;
      }

      if (other.isSetSkewedColValues()) {
         List<List<String>> __this__skewedColValues = new ArrayList(other.skewedColValues.size());

         for(List other_element : other.skewedColValues) {
            List<String> __this__skewedColValues_copy = new ArrayList(other_element);
            __this__skewedColValues.add(__this__skewedColValues_copy);
         }

         this.skewedColValues = __this__skewedColValues;
      }

      if (other.isSetSkewedColValueLocationMaps()) {
         Map<List<String>, String> __this__skewedColValueLocationMaps = new HashMap(other.skewedColValueLocationMaps.size());

         for(Map.Entry other_element : other.skewedColValueLocationMaps.entrySet()) {
            List<String> other_element_key = (List)other_element.getKey();
            String other_element_value = (String)other_element.getValue();
            List<String> __this__skewedColValueLocationMaps_copy_key = new ArrayList(other_element_key);
            __this__skewedColValueLocationMaps.put(__this__skewedColValueLocationMaps_copy_key, other_element_value);
         }

         this.skewedColValueLocationMaps = __this__skewedColValueLocationMaps;
      }

   }

   public SkewedInfo deepCopy() {
      return new SkewedInfo(this);
   }

   public void clear() {
      this.skewedColNames = null;
      this.skewedColValues = null;
      this.skewedColValueLocationMaps = null;
   }

   public int getSkewedColNamesSize() {
      return this.skewedColNames == null ? 0 : this.skewedColNames.size();
   }

   @Nullable
   public Iterator getSkewedColNamesIterator() {
      return this.skewedColNames == null ? null : this.skewedColNames.iterator();
   }

   public void addToSkewedColNames(String elem) {
      if (this.skewedColNames == null) {
         this.skewedColNames = new ArrayList();
      }

      this.skewedColNames.add(elem);
   }

   @Nullable
   public List getSkewedColNames() {
      return this.skewedColNames;
   }

   public void setSkewedColNames(@Nullable List skewedColNames) {
      this.skewedColNames = skewedColNames;
   }

   public void unsetSkewedColNames() {
      this.skewedColNames = null;
   }

   public boolean isSetSkewedColNames() {
      return this.skewedColNames != null;
   }

   public void setSkewedColNamesIsSet(boolean value) {
      if (!value) {
         this.skewedColNames = null;
      }

   }

   public int getSkewedColValuesSize() {
      return this.skewedColValues == null ? 0 : this.skewedColValues.size();
   }

   @Nullable
   public Iterator getSkewedColValuesIterator() {
      return this.skewedColValues == null ? null : this.skewedColValues.iterator();
   }

   public void addToSkewedColValues(List elem) {
      if (this.skewedColValues == null) {
         this.skewedColValues = new ArrayList();
      }

      this.skewedColValues.add(elem);
   }

   @Nullable
   public List getSkewedColValues() {
      return this.skewedColValues;
   }

   public void setSkewedColValues(@Nullable List skewedColValues) {
      this.skewedColValues = skewedColValues;
   }

   public void unsetSkewedColValues() {
      this.skewedColValues = null;
   }

   public boolean isSetSkewedColValues() {
      return this.skewedColValues != null;
   }

   public void setSkewedColValuesIsSet(boolean value) {
      if (!value) {
         this.skewedColValues = null;
      }

   }

   public int getSkewedColValueLocationMapsSize() {
      return this.skewedColValueLocationMaps == null ? 0 : this.skewedColValueLocationMaps.size();
   }

   public void putToSkewedColValueLocationMaps(List key, String val) {
      if (this.skewedColValueLocationMaps == null) {
         this.skewedColValueLocationMaps = new HashMap();
      }

      this.skewedColValueLocationMaps.put(key, val);
   }

   @Nullable
   public Map getSkewedColValueLocationMaps() {
      return this.skewedColValueLocationMaps;
   }

   public void setSkewedColValueLocationMaps(@Nullable Map skewedColValueLocationMaps) {
      this.skewedColValueLocationMaps = skewedColValueLocationMaps;
   }

   public void unsetSkewedColValueLocationMaps() {
      this.skewedColValueLocationMaps = null;
   }

   public boolean isSetSkewedColValueLocationMaps() {
      return this.skewedColValueLocationMaps != null;
   }

   public void setSkewedColValueLocationMapsIsSet(boolean value) {
      if (!value) {
         this.skewedColValueLocationMaps = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SKEWED_COL_NAMES:
            if (value == null) {
               this.unsetSkewedColNames();
            } else {
               this.setSkewedColNames((List)value);
            }
            break;
         case SKEWED_COL_VALUES:
            if (value == null) {
               this.unsetSkewedColValues();
            } else {
               this.setSkewedColValues((List)value);
            }
            break;
         case SKEWED_COL_VALUE_LOCATION_MAPS:
            if (value == null) {
               this.unsetSkewedColValueLocationMaps();
            } else {
               this.setSkewedColValueLocationMaps((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SKEWED_COL_NAMES:
            return this.getSkewedColNames();
         case SKEWED_COL_VALUES:
            return this.getSkewedColValues();
         case SKEWED_COL_VALUE_LOCATION_MAPS:
            return this.getSkewedColValueLocationMaps();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SKEWED_COL_NAMES:
               return this.isSetSkewedColNames();
            case SKEWED_COL_VALUES:
               return this.isSetSkewedColValues();
            case SKEWED_COL_VALUE_LOCATION_MAPS:
               return this.isSetSkewedColValueLocationMaps();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SkewedInfo ? this.equals((SkewedInfo)that) : false;
   }

   public boolean equals(SkewedInfo that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_skewedColNames = this.isSetSkewedColNames();
         boolean that_present_skewedColNames = that.isSetSkewedColNames();
         if (this_present_skewedColNames || that_present_skewedColNames) {
            if (!this_present_skewedColNames || !that_present_skewedColNames) {
               return false;
            }

            if (!this.skewedColNames.equals(that.skewedColNames)) {
               return false;
            }
         }

         boolean this_present_skewedColValues = this.isSetSkewedColValues();
         boolean that_present_skewedColValues = that.isSetSkewedColValues();
         if (this_present_skewedColValues || that_present_skewedColValues) {
            if (!this_present_skewedColValues || !that_present_skewedColValues) {
               return false;
            }

            if (!this.skewedColValues.equals(that.skewedColValues)) {
               return false;
            }
         }

         boolean this_present_skewedColValueLocationMaps = this.isSetSkewedColValueLocationMaps();
         boolean that_present_skewedColValueLocationMaps = that.isSetSkewedColValueLocationMaps();
         if (this_present_skewedColValueLocationMaps || that_present_skewedColValueLocationMaps) {
            if (!this_present_skewedColValueLocationMaps || !that_present_skewedColValueLocationMaps) {
               return false;
            }

            if (!this.skewedColValueLocationMaps.equals(that.skewedColValueLocationMaps)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSkewedColNames() ? 131071 : 524287);
      if (this.isSetSkewedColNames()) {
         hashCode = hashCode * 8191 + this.skewedColNames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSkewedColValues() ? 131071 : 524287);
      if (this.isSetSkewedColValues()) {
         hashCode = hashCode * 8191 + this.skewedColValues.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSkewedColValueLocationMaps() ? 131071 : 524287);
      if (this.isSetSkewedColValueLocationMaps()) {
         hashCode = hashCode * 8191 + this.skewedColValueLocationMaps.hashCode();
      }

      return hashCode;
   }

   public int compareTo(SkewedInfo other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSkewedColNames(), other.isSetSkewedColNames());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSkewedColNames()) {
               lastComparison = TBaseHelper.compareTo(this.skewedColNames, other.skewedColNames);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetSkewedColValues(), other.isSetSkewedColValues());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSkewedColValues()) {
                  lastComparison = TBaseHelper.compareTo(this.skewedColValues, other.skewedColValues);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetSkewedColValueLocationMaps(), other.isSetSkewedColValueLocationMaps());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetSkewedColValueLocationMaps()) {
                     lastComparison = TBaseHelper.compareTo(this.skewedColValueLocationMaps, other.skewedColValueLocationMaps);
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
      return SkewedInfo._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SkewedInfo(");
      boolean first = true;
      sb.append("skewedColNames:");
      if (this.skewedColNames == null) {
         sb.append("null");
      } else {
         sb.append(this.skewedColNames);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("skewedColValues:");
      if (this.skewedColValues == null) {
         sb.append("null");
      } else {
         sb.append(this.skewedColValues);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("skewedColValueLocationMaps:");
      if (this.skewedColValueLocationMaps == null) {
         sb.append("null");
      } else {
         sb.append(this.skewedColValueLocationMaps);
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
      tmpMap.put(SkewedInfo._Fields.SKEWED_COL_NAMES, new FieldMetaData("skewedColNames", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(SkewedInfo._Fields.SKEWED_COL_VALUES, new FieldMetaData("skewedColValues", (byte)3, new ListMetaData((byte)15, new ListMetaData((byte)15, new FieldValueMetaData((byte)11)))));
      tmpMap.put(SkewedInfo._Fields.SKEWED_COL_VALUE_LOCATION_MAPS, new FieldMetaData("skewedColValueLocationMaps", (byte)3, new MapMetaData((byte)13, new ListMetaData((byte)15, new FieldValueMetaData((byte)11)), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SkewedInfo.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SKEWED_COL_NAMES((short)1, "skewedColNames"),
      SKEWED_COL_VALUES((short)2, "skewedColValues"),
      SKEWED_COL_VALUE_LOCATION_MAPS((short)3, "skewedColValueLocationMaps");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SKEWED_COL_NAMES;
            case 2:
               return SKEWED_COL_VALUES;
            case 3:
               return SKEWED_COL_VALUE_LOCATION_MAPS;
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

   private static class SkewedInfoStandardSchemeFactory implements SchemeFactory {
      private SkewedInfoStandardSchemeFactory() {
      }

      public SkewedInfoStandardScheme getScheme() {
         return new SkewedInfoStandardScheme();
      }
   }

   private static class SkewedInfoStandardScheme extends StandardScheme {
      private SkewedInfoStandardScheme() {
      }

      public void read(TProtocol iprot, SkewedInfo struct) throws TException {
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

                  TList _list114 = iprot.readListBegin();
                  struct.skewedColNames = new ArrayList(_list114.size);

                  for(int _i116 = 0; _i116 < _list114.size; ++_i116) {
                     String _elem115 = iprot.readString();
                     struct.skewedColNames.add(_elem115);
                  }

                  iprot.readListEnd();
                  struct.setSkewedColNamesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list117 = iprot.readListBegin();
                  struct.skewedColValues = new ArrayList(_list117.size);

                  for(int _i119 = 0; _i119 < _list117.size; ++_i119) {
                     TList _list120 = iprot.readListBegin();
                     List<String> _elem118 = new ArrayList(_list120.size);

                     for(int _i122 = 0; _i122 < _list120.size; ++_i122) {
                        String _elem121 = iprot.readString();
                        _elem118.add(_elem121);
                     }

                     iprot.readListEnd();
                     struct.skewedColValues.add(_elem118);
                  }

                  iprot.readListEnd();
                  struct.setSkewedColValuesIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map123 = iprot.readMapBegin();
                  struct.skewedColValueLocationMaps = new HashMap(2 * _map123.size);

                  for(int _i126 = 0; _i126 < _map123.size; ++_i126) {
                     TList _list127 = iprot.readListBegin();
                     List<String> _key124 = new ArrayList(_list127.size);

                     for(int _i129 = 0; _i129 < _list127.size; ++_i129) {
                        String _elem128 = iprot.readString();
                        _key124.add(_elem128);
                     }

                     iprot.readListEnd();
                     String _val125 = iprot.readString();
                     struct.skewedColValueLocationMaps.put(_key124, _val125);
                  }

                  iprot.readMapEnd();
                  struct.setSkewedColValueLocationMapsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, SkewedInfo struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SkewedInfo.STRUCT_DESC);
         if (struct.skewedColNames != null) {
            oprot.writeFieldBegin(SkewedInfo.SKEWED_COL_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.skewedColNames.size()));

            for(String _iter130 : struct.skewedColNames) {
               oprot.writeString(_iter130);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.skewedColValues != null) {
            oprot.writeFieldBegin(SkewedInfo.SKEWED_COL_VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)15, struct.skewedColValues.size()));

            for(List _iter131 : struct.skewedColValues) {
               oprot.writeListBegin(new TList((byte)11, _iter131.size()));

               for(String _iter132 : _iter131) {
                  oprot.writeString(_iter132);
               }

               oprot.writeListEnd();
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.skewedColValueLocationMaps != null) {
            oprot.writeFieldBegin(SkewedInfo.SKEWED_COL_VALUE_LOCATION_MAPS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)15, (byte)11, struct.skewedColValueLocationMaps.size()));

            for(Map.Entry _iter133 : struct.skewedColValueLocationMaps.entrySet()) {
               oprot.writeListBegin(new TList((byte)11, ((List)_iter133.getKey()).size()));

               for(String _iter134 : (List)_iter133.getKey()) {
                  oprot.writeString(_iter134);
               }

               oprot.writeListEnd();
               oprot.writeString((String)_iter133.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SkewedInfoTupleSchemeFactory implements SchemeFactory {
      private SkewedInfoTupleSchemeFactory() {
      }

      public SkewedInfoTupleScheme getScheme() {
         return new SkewedInfoTupleScheme();
      }
   }

   private static class SkewedInfoTupleScheme extends TupleScheme {
      private SkewedInfoTupleScheme() {
      }

      public void write(TProtocol prot, SkewedInfo struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetSkewedColNames()) {
            optionals.set(0);
         }

         if (struct.isSetSkewedColValues()) {
            optionals.set(1);
         }

         if (struct.isSetSkewedColValueLocationMaps()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetSkewedColNames()) {
            oprot.writeI32(struct.skewedColNames.size());

            for(String _iter135 : struct.skewedColNames) {
               oprot.writeString(_iter135);
            }
         }

         if (struct.isSetSkewedColValues()) {
            oprot.writeI32(struct.skewedColValues.size());

            for(List _iter136 : struct.skewedColValues) {
               oprot.writeI32(_iter136.size());

               for(String _iter137 : _iter136) {
                  oprot.writeString(_iter137);
               }
            }
         }

         if (struct.isSetSkewedColValueLocationMaps()) {
            oprot.writeI32(struct.skewedColValueLocationMaps.size());

            for(Map.Entry _iter138 : struct.skewedColValueLocationMaps.entrySet()) {
               oprot.writeI32(((List)_iter138.getKey()).size());

               for(String _iter139 : (List)_iter138.getKey()) {
                  oprot.writeString(_iter139);
               }

               oprot.writeString((String)_iter138.getValue());
            }
         }

      }

      public void read(TProtocol prot, SkewedInfo struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            TList _list140 = iprot.readListBegin((byte)11);
            struct.skewedColNames = new ArrayList(_list140.size);

            for(int _i142 = 0; _i142 < _list140.size; ++_i142) {
               String _elem141 = iprot.readString();
               struct.skewedColNames.add(_elem141);
            }

            struct.setSkewedColNamesIsSet(true);
         }

         if (incoming.get(1)) {
            TList _list143 = iprot.readListBegin((byte)15);
            struct.skewedColValues = new ArrayList(_list143.size);

            for(int _i145 = 0; _i145 < _list143.size; ++_i145) {
               TList _list146 = iprot.readListBegin((byte)11);
               List<String> _elem144 = new ArrayList(_list146.size);

               for(int _i148 = 0; _i148 < _list146.size; ++_i148) {
                  String _elem147 = iprot.readString();
                  _elem144.add(_elem147);
               }

               struct.skewedColValues.add(_elem144);
            }

            struct.setSkewedColValuesIsSet(true);
         }

         if (incoming.get(2)) {
            TMap _map149 = iprot.readMapBegin((byte)15, (byte)11);
            struct.skewedColValueLocationMaps = new HashMap(2 * _map149.size);

            for(int _i152 = 0; _i152 < _map149.size; ++_i152) {
               TList _list153 = iprot.readListBegin((byte)11);
               List<String> _key150 = new ArrayList(_list153.size);

               for(int _i155 = 0; _i155 < _list153.size; ++_i155) {
                  String _elem154 = iprot.readString();
                  _key150.add(_elem154);
               }

               String _val151 = iprot.readString();
               struct.skewedColValueLocationMaps.put(_key150, _val151);
            }

            struct.setSkewedColValueLocationMapsIsSet(true);
         }

      }
   }
}
