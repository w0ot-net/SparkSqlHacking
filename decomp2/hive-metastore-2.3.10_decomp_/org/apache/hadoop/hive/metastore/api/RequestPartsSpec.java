package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TEnum;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

public class RequestPartsSpec extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("RequestPartsSpec");
   private static final TField NAMES_FIELD_DESC = new TField("names", (byte)15, (short)1);
   private static final TField EXPRS_FIELD_DESC = new TField("exprs", (byte)15, (short)2);
   public static final Map metaDataMap;

   public RequestPartsSpec() {
   }

   public RequestPartsSpec(_Fields setField, Object value) {
      super(setField, value);
   }

   public RequestPartsSpec(RequestPartsSpec other) {
      super(other);
   }

   public RequestPartsSpec deepCopy() {
      return new RequestPartsSpec(this);
   }

   public static RequestPartsSpec names(List value) {
      RequestPartsSpec x = new RequestPartsSpec();
      x.setNames(value);
      return x;
   }

   public static RequestPartsSpec exprs(List value) {
      RequestPartsSpec x = new RequestPartsSpec();
      x.setExprs(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case NAMES:
            if (!(value instanceof List)) {
               throw new ClassCastException("Was expecting value of type java.util.List<java.lang.String> for field 'names', but got " + value.getClass().getSimpleName());
            }
            break;
         case EXPRS:
            if (!(value instanceof List)) {
               throw new ClassCastException("Was expecting value of type java.util.List<DropPartitionsExpr> for field 'exprs', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = RequestPartsSpec._Fields.findByThriftId(field.id);
      if (setField == null) {
         TProtocolUtil.skip(iprot, field.type);
         return null;
      } else {
         switch (setField) {
            case NAMES:
               if (field.type != NAMES_FIELD_DESC.type) {
                  TProtocolUtil.skip(iprot, field.type);
                  return null;
               }

               TList _list436 = iprot.readListBegin();
               List<String> names = new ArrayList(_list436.size);

               for(int _i438 = 0; _i438 < _list436.size; ++_i438) {
                  String _elem437 = iprot.readString();
                  names.add(_elem437);
               }

               iprot.readListEnd();
               return names;
            case EXPRS:
               if (field.type != EXPRS_FIELD_DESC.type) {
                  TProtocolUtil.skip(iprot, field.type);
                  return null;
               }

               TList _list439 = iprot.readListBegin();
               List<DropPartitionsExpr> exprs = new ArrayList(_list439.size);

               for(int _i441 = 0; _i441 < _list439.size; ++_i441) {
                  DropPartitionsExpr _elem440 = new DropPartitionsExpr();
                  _elem440.read(iprot);
                  exprs.add(_elem440);
               }

               iprot.readListEnd();
               return exprs;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      }
   }

   protected void standardSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case NAMES:
            List<String> names = (List)this.value_;
            oprot.writeListBegin(new TList((byte)11, names.size()));

            for(String _iter442 : names) {
               oprot.writeString(_iter442);
            }

            oprot.writeListEnd();
            return;
         case EXPRS:
            List<DropPartitionsExpr> exprs = (List)this.value_;
            oprot.writeListBegin(new TList((byte)12, exprs.size()));

            for(DropPartitionsExpr _iter443 : exprs) {
               _iter443.write(oprot);
            }

            oprot.writeListEnd();
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = RequestPartsSpec._Fields.findByThriftId(fieldID);
      if (setField == null) {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      } else {
         switch (setField) {
            case NAMES:
               TList _list444 = iprot.readListBegin();
               List<String> names = new ArrayList(_list444.size);

               for(int _i446 = 0; _i446 < _list444.size; ++_i446) {
                  String _elem445 = iprot.readString();
                  names.add(_elem445);
               }

               iprot.readListEnd();
               return names;
            case EXPRS:
               TList _list447 = iprot.readListBegin();
               List<DropPartitionsExpr> exprs = new ArrayList(_list447.size);

               for(int _i449 = 0; _i449 < _list447.size; ++_i449) {
                  DropPartitionsExpr _elem448 = new DropPartitionsExpr();
                  _elem448.read(iprot);
                  exprs.add(_elem448);
               }

               iprot.readListEnd();
               return exprs;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case NAMES:
            List<String> names = (List)this.value_;
            oprot.writeListBegin(new TList((byte)11, names.size()));

            for(String _iter450 : names) {
               oprot.writeString(_iter450);
            }

            oprot.writeListEnd();
            return;
         case EXPRS:
            List<DropPartitionsExpr> exprs = (List)this.value_;
            oprot.writeListBegin(new TList((byte)12, exprs.size()));

            for(DropPartitionsExpr _iter451 : exprs) {
               _iter451.write(oprot);
            }

            oprot.writeListEnd();
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case NAMES:
            return NAMES_FIELD_DESC;
         case EXPRS:
            return EXPRS_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return RequestPartsSpec._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return RequestPartsSpec._Fields.findByThriftId(fieldId);
   }

   public List getNames() {
      if (this.getSetField() == RequestPartsSpec._Fields.NAMES) {
         return (List)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'names' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setNames(List value) {
      this.setField_ = RequestPartsSpec._Fields.NAMES;
      this.value_ = Objects.requireNonNull(value, "_Fields.NAMES");
   }

   public List getExprs() {
      if (this.getSetField() == RequestPartsSpec._Fields.EXPRS) {
         return (List)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'exprs' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setExprs(List value) {
      this.setField_ = RequestPartsSpec._Fields.EXPRS;
      this.value_ = Objects.requireNonNull(value, "_Fields.EXPRS");
   }

   public boolean isSetNames() {
      return this.setField_ == RequestPartsSpec._Fields.NAMES;
   }

   public boolean isSetExprs() {
      return this.setField_ == RequestPartsSpec._Fields.EXPRS;
   }

   public boolean equals(Object other) {
      return other instanceof RequestPartsSpec ? this.equals((RequestPartsSpec)other) : false;
   }

   public boolean equals(RequestPartsSpec other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(RequestPartsSpec other) {
      int lastComparison = TBaseHelper.compareTo((Comparable)this.getSetField(), (Comparable)other.getSetField());
      return lastComparison == 0 ? TBaseHelper.compareTo(this.getFieldValue(), other.getFieldValue()) : lastComparison;
   }

   public int hashCode() {
      List<Object> list = new ArrayList();
      list.add(this.getClass().getName());
      TFieldIdEnum setField = this.getSetField();
      if (setField != null) {
         list.add(setField.getThriftFieldId());
         Object value = this.getFieldValue();
         if (value instanceof TEnum) {
            list.add(((TEnum)this.getFieldValue()).getValue());
         } else {
            list.add(value);
         }
      }

      return list.hashCode();
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

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(RequestPartsSpec._Fields.NAMES, new FieldMetaData("names", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(RequestPartsSpec._Fields.EXPRS, new FieldMetaData("exprs", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, DropPartitionsExpr.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(RequestPartsSpec.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAMES((short)1, "names"),
      EXPRS((short)2, "exprs");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAMES;
            case 2:
               return EXPRS;
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
}
