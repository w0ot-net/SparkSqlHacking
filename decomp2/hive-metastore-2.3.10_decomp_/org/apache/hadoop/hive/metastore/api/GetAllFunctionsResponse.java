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
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class GetAllFunctionsResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetAllFunctionsResponse");
   private static final TField FUNCTIONS_FIELD_DESC = new TField("functions", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetAllFunctionsResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetAllFunctionsResponseTupleSchemeFactory();
   @Nullable
   private List functions;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GetAllFunctionsResponse() {
   }

   public GetAllFunctionsResponse(GetAllFunctionsResponse other) {
      if (other.isSetFunctions()) {
         List<Function> __this__functions = new ArrayList(other.functions.size());

         for(Function other_element : other.functions) {
            __this__functions.add(new Function(other_element));
         }

         this.functions = __this__functions;
      }

   }

   public GetAllFunctionsResponse deepCopy() {
      return new GetAllFunctionsResponse(this);
   }

   public void clear() {
      this.functions = null;
   }

   public int getFunctionsSize() {
      return this.functions == null ? 0 : this.functions.size();
   }

   @Nullable
   public Iterator getFunctionsIterator() {
      return this.functions == null ? null : this.functions.iterator();
   }

   public void addToFunctions(Function elem) {
      if (this.functions == null) {
         this.functions = new ArrayList();
      }

      this.functions.add(elem);
   }

   @Nullable
   public List getFunctions() {
      return this.functions;
   }

   public void setFunctions(@Nullable List functions) {
      this.functions = functions;
   }

   public void unsetFunctions() {
      this.functions = null;
   }

   public boolean isSetFunctions() {
      return this.functions != null;
   }

   public void setFunctionsIsSet(boolean value) {
      if (!value) {
         this.functions = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FUNCTIONS:
            if (value == null) {
               this.unsetFunctions();
            } else {
               this.setFunctions((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FUNCTIONS:
            return this.getFunctions();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FUNCTIONS:
               return this.isSetFunctions();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetAllFunctionsResponse ? this.equals((GetAllFunctionsResponse)that) : false;
   }

   public boolean equals(GetAllFunctionsResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_functions = this.isSetFunctions();
         boolean that_present_functions = that.isSetFunctions();
         if (this_present_functions || that_present_functions) {
            if (!this_present_functions || !that_present_functions) {
               return false;
            }

            if (!this.functions.equals(that.functions)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetFunctions() ? 131071 : 524287);
      if (this.isSetFunctions()) {
         hashCode = hashCode * 8191 + this.functions.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetAllFunctionsResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFunctions(), other.isSetFunctions());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFunctions()) {
               lastComparison = TBaseHelper.compareTo(this.functions, other.functions);
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
      return GetAllFunctionsResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetAllFunctionsResponse(");
      boolean first = true;
      if (this.isSetFunctions()) {
         sb.append("functions:");
         if (this.functions == null) {
            sb.append("null");
         } else {
            sb.append(this.functions);
         }

         first = false;
      }

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
      optionals = new _Fields[]{GetAllFunctionsResponse._Fields.FUNCTIONS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetAllFunctionsResponse._Fields.FUNCTIONS, new FieldMetaData("functions", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, Function.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetAllFunctionsResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FUNCTIONS((short)1, "functions");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FUNCTIONS;
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

   private static class GetAllFunctionsResponseStandardSchemeFactory implements SchemeFactory {
      private GetAllFunctionsResponseStandardSchemeFactory() {
      }

      public GetAllFunctionsResponseStandardScheme getScheme() {
         return new GetAllFunctionsResponseStandardScheme();
      }
   }

   private static class GetAllFunctionsResponseStandardScheme extends StandardScheme {
      private GetAllFunctionsResponseStandardScheme() {
      }

      public void read(TProtocol iprot, GetAllFunctionsResponse struct) throws TException {
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

                  TList _list674 = iprot.readListBegin();
                  struct.functions = new ArrayList(_list674.size);

                  for(int _i676 = 0; _i676 < _list674.size; ++_i676) {
                     Function _elem675 = new Function();
                     _elem675.read(iprot);
                     struct.functions.add(_elem675);
                  }

                  iprot.readListEnd();
                  struct.setFunctionsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetAllFunctionsResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetAllFunctionsResponse.STRUCT_DESC);
         if (struct.functions != null && struct.isSetFunctions()) {
            oprot.writeFieldBegin(GetAllFunctionsResponse.FUNCTIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.functions.size()));

            for(Function _iter677 : struct.functions) {
               _iter677.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetAllFunctionsResponseTupleSchemeFactory implements SchemeFactory {
      private GetAllFunctionsResponseTupleSchemeFactory() {
      }

      public GetAllFunctionsResponseTupleScheme getScheme() {
         return new GetAllFunctionsResponseTupleScheme();
      }
   }

   private static class GetAllFunctionsResponseTupleScheme extends TupleScheme {
      private GetAllFunctionsResponseTupleScheme() {
      }

      public void write(TProtocol prot, GetAllFunctionsResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetFunctions()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetFunctions()) {
            oprot.writeI32(struct.functions.size());

            for(Function _iter678 : struct.functions) {
               _iter678.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, GetAllFunctionsResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list679 = iprot.readListBegin((byte)12);
            struct.functions = new ArrayList(_list679.size);

            for(int _i681 = 0; _i681 < _list679.size; ++_i681) {
               Function _elem680 = new Function();
               _elem680.read(iprot);
               struct.functions.add(_elem680);
            }

            struct.setFunctionsIsSet(true);
         }

      }
   }
}
