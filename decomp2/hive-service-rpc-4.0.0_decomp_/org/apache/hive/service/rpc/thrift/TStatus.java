package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
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
public class TStatus implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TStatus");
   private static final TField STATUS_CODE_FIELD_DESC = new TField("statusCode", (byte)8, (short)1);
   private static final TField INFO_MESSAGES_FIELD_DESC = new TField("infoMessages", (byte)15, (short)2);
   private static final TField SQL_STATE_FIELD_DESC = new TField("sqlState", (byte)11, (short)3);
   private static final TField ERROR_CODE_FIELD_DESC = new TField("errorCode", (byte)8, (short)4);
   private static final TField ERROR_MESSAGE_FIELD_DESC = new TField("errorMessage", (byte)11, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TStatusStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TStatusTupleSchemeFactory();
   @Nullable
   private TStatusCode statusCode;
   @Nullable
   private List infoMessages;
   @Nullable
   private String sqlState;
   private int errorCode;
   @Nullable
   private String errorMessage;
   private static final int __ERRORCODE_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TStatus() {
      this.__isset_bitfield = 0;
   }

   public TStatus(TStatusCode statusCode) {
      this();
      this.statusCode = statusCode;
   }

   public TStatus(TStatus other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetStatusCode()) {
         this.statusCode = other.statusCode;
      }

      if (other.isSetInfoMessages()) {
         List<String> __this__infoMessages = new ArrayList(other.infoMessages);
         this.infoMessages = __this__infoMessages;
      }

      if (other.isSetSqlState()) {
         this.sqlState = other.sqlState;
      }

      this.errorCode = other.errorCode;
      if (other.isSetErrorMessage()) {
         this.errorMessage = other.errorMessage;
      }

   }

   public TStatus deepCopy() {
      return new TStatus(this);
   }

   public void clear() {
      this.statusCode = null;
      this.infoMessages = null;
      this.sqlState = null;
      this.setErrorCodeIsSet(false);
      this.errorCode = 0;
      this.errorMessage = null;
   }

   @Nullable
   public TStatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(@Nullable TStatusCode statusCode) {
      this.statusCode = statusCode;
   }

   public void unsetStatusCode() {
      this.statusCode = null;
   }

   public boolean isSetStatusCode() {
      return this.statusCode != null;
   }

   public void setStatusCodeIsSet(boolean value) {
      if (!value) {
         this.statusCode = null;
      }

   }

   public int getInfoMessagesSize() {
      return this.infoMessages == null ? 0 : this.infoMessages.size();
   }

   @Nullable
   public Iterator getInfoMessagesIterator() {
      return this.infoMessages == null ? null : this.infoMessages.iterator();
   }

   public void addToInfoMessages(String elem) {
      if (this.infoMessages == null) {
         this.infoMessages = new ArrayList();
      }

      this.infoMessages.add(elem);
   }

   @Nullable
   public List getInfoMessages() {
      return this.infoMessages;
   }

   public void setInfoMessages(@Nullable List infoMessages) {
      this.infoMessages = infoMessages;
   }

   public void unsetInfoMessages() {
      this.infoMessages = null;
   }

   public boolean isSetInfoMessages() {
      return this.infoMessages != null;
   }

   public void setInfoMessagesIsSet(boolean value) {
      if (!value) {
         this.infoMessages = null;
      }

   }

   @Nullable
   public String getSqlState() {
      return this.sqlState;
   }

   public void setSqlState(@Nullable String sqlState) {
      this.sqlState = sqlState;
   }

   public void unsetSqlState() {
      this.sqlState = null;
   }

   public boolean isSetSqlState() {
      return this.sqlState != null;
   }

   public void setSqlStateIsSet(boolean value) {
      if (!value) {
         this.sqlState = null;
      }

   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
      this.setErrorCodeIsSet(true);
   }

   public void unsetErrorCode() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetErrorCode() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setErrorCodeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getErrorMessage() {
      return this.errorMessage;
   }

   public void setErrorMessage(@Nullable String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public void unsetErrorMessage() {
      this.errorMessage = null;
   }

   public boolean isSetErrorMessage() {
      return this.errorMessage != null;
   }

   public void setErrorMessageIsSet(boolean value) {
      if (!value) {
         this.errorMessage = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATUS_CODE:
            if (value == null) {
               this.unsetStatusCode();
            } else {
               this.setStatusCode((TStatusCode)value);
            }
            break;
         case INFO_MESSAGES:
            if (value == null) {
               this.unsetInfoMessages();
            } else {
               this.setInfoMessages((List)value);
            }
            break;
         case SQL_STATE:
            if (value == null) {
               this.unsetSqlState();
            } else {
               this.setSqlState((String)value);
            }
            break;
         case ERROR_CODE:
            if (value == null) {
               this.unsetErrorCode();
            } else {
               this.setErrorCode((Integer)value);
            }
            break;
         case ERROR_MESSAGE:
            if (value == null) {
               this.unsetErrorMessage();
            } else {
               this.setErrorMessage((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATUS_CODE:
            return this.getStatusCode();
         case INFO_MESSAGES:
            return this.getInfoMessages();
         case SQL_STATE:
            return this.getSqlState();
         case ERROR_CODE:
            return this.getErrorCode();
         case ERROR_MESSAGE:
            return this.getErrorMessage();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case STATUS_CODE:
               return this.isSetStatusCode();
            case INFO_MESSAGES:
               return this.isSetInfoMessages();
            case SQL_STATE:
               return this.isSetSqlState();
            case ERROR_CODE:
               return this.isSetErrorCode();
            case ERROR_MESSAGE:
               return this.isSetErrorMessage();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TStatus ? this.equals((TStatus)that) : false;
   }

   public boolean equals(TStatus that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_statusCode = this.isSetStatusCode();
         boolean that_present_statusCode = that.isSetStatusCode();
         if (this_present_statusCode || that_present_statusCode) {
            if (!this_present_statusCode || !that_present_statusCode) {
               return false;
            }

            if (!this.statusCode.equals(that.statusCode)) {
               return false;
            }
         }

         boolean this_present_infoMessages = this.isSetInfoMessages();
         boolean that_present_infoMessages = that.isSetInfoMessages();
         if (this_present_infoMessages || that_present_infoMessages) {
            if (!this_present_infoMessages || !that_present_infoMessages) {
               return false;
            }

            if (!this.infoMessages.equals(that.infoMessages)) {
               return false;
            }
         }

         boolean this_present_sqlState = this.isSetSqlState();
         boolean that_present_sqlState = that.isSetSqlState();
         if (this_present_sqlState || that_present_sqlState) {
            if (!this_present_sqlState || !that_present_sqlState) {
               return false;
            }

            if (!this.sqlState.equals(that.sqlState)) {
               return false;
            }
         }

         boolean this_present_errorCode = this.isSetErrorCode();
         boolean that_present_errorCode = that.isSetErrorCode();
         if (this_present_errorCode || that_present_errorCode) {
            if (!this_present_errorCode || !that_present_errorCode) {
               return false;
            }

            if (this.errorCode != that.errorCode) {
               return false;
            }
         }

         boolean this_present_errorMessage = this.isSetErrorMessage();
         boolean that_present_errorMessage = that.isSetErrorMessage();
         if (this_present_errorMessage || that_present_errorMessage) {
            if (!this_present_errorMessage || !that_present_errorMessage) {
               return false;
            }

            if (!this.errorMessage.equals(that.errorMessage)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatusCode() ? 131071 : 524287);
      if (this.isSetStatusCode()) {
         hashCode = hashCode * 8191 + this.statusCode.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetInfoMessages() ? 131071 : 524287);
      if (this.isSetInfoMessages()) {
         hashCode = hashCode * 8191 + this.infoMessages.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSqlState() ? 131071 : 524287);
      if (this.isSetSqlState()) {
         hashCode = hashCode * 8191 + this.sqlState.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetErrorCode() ? 131071 : 524287);
      if (this.isSetErrorCode()) {
         hashCode = hashCode * 8191 + this.errorCode;
      }

      hashCode = hashCode * 8191 + (this.isSetErrorMessage() ? 131071 : 524287);
      if (this.isSetErrorMessage()) {
         hashCode = hashCode * 8191 + this.errorMessage.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TStatus other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetStatusCode(), other.isSetStatusCode());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetStatusCode()) {
               lastComparison = TBaseHelper.compareTo(this.statusCode, other.statusCode);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetInfoMessages(), other.isSetInfoMessages());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetInfoMessages()) {
                  lastComparison = TBaseHelper.compareTo(this.infoMessages, other.infoMessages);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetSqlState(), other.isSetSqlState());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetSqlState()) {
                     lastComparison = TBaseHelper.compareTo(this.sqlState, other.sqlState);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetErrorCode(), other.isSetErrorCode());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetErrorCode()) {
                        lastComparison = TBaseHelper.compareTo(this.errorCode, other.errorCode);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetErrorMessage(), other.isSetErrorMessage());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetErrorMessage()) {
                           lastComparison = TBaseHelper.compareTo(this.errorMessage, other.errorMessage);
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
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TStatus._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TStatus(");
      boolean first = true;
      sb.append("statusCode:");
      if (this.statusCode == null) {
         sb.append("null");
      } else {
         sb.append(this.statusCode);
      }

      first = false;
      if (this.isSetInfoMessages()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("infoMessages:");
         if (this.infoMessages == null) {
            sb.append("null");
         } else {
            sb.append(this.infoMessages);
         }

         first = false;
      }

      if (this.isSetSqlState()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("sqlState:");
         if (this.sqlState == null) {
            sb.append("null");
         } else {
            sb.append(this.sqlState);
         }

         first = false;
      }

      if (this.isSetErrorCode()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("errorCode:");
         sb.append(this.errorCode);
         first = false;
      }

      if (this.isSetErrorMessage()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("errorMessage:");
         if (this.errorMessage == null) {
            sb.append("null");
         } else {
            sb.append(this.errorMessage);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStatusCode()) {
         throw new TProtocolException("Required field 'statusCode' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TStatus._Fields.INFO_MESSAGES, TStatus._Fields.SQL_STATE, TStatus._Fields.ERROR_CODE, TStatus._Fields.ERROR_MESSAGE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TStatus._Fields.STATUS_CODE, new FieldMetaData("statusCode", (byte)1, new EnumMetaData((byte)16, TStatusCode.class)));
      tmpMap.put(TStatus._Fields.INFO_MESSAGES, new FieldMetaData("infoMessages", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(TStatus._Fields.SQL_STATE, new FieldMetaData("sqlState", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TStatus._Fields.ERROR_CODE, new FieldMetaData("errorCode", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TStatus._Fields.ERROR_MESSAGE, new FieldMetaData("errorMessage", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TStatus.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATUS_CODE((short)1, "statusCode"),
      INFO_MESSAGES((short)2, "infoMessages"),
      SQL_STATE((short)3, "sqlState"),
      ERROR_CODE((short)4, "errorCode"),
      ERROR_MESSAGE((short)5, "errorMessage");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATUS_CODE;
            case 2:
               return INFO_MESSAGES;
            case 3:
               return SQL_STATE;
            case 4:
               return ERROR_CODE;
            case 5:
               return ERROR_MESSAGE;
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

   private static class TStatusStandardSchemeFactory implements SchemeFactory {
      private TStatusStandardSchemeFactory() {
      }

      public TStatusStandardScheme getScheme() {
         return new TStatusStandardScheme();
      }
   }

   private static class TStatusStandardScheme extends StandardScheme {
      private TStatusStandardScheme() {
      }

      public void read(TProtocol iprot, TStatus struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.statusCode = TStatusCode.findByValue(iprot.readI32());
                     struct.setStatusCodeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list134 = iprot.readListBegin();
                  struct.infoMessages = new ArrayList(_list134.size);

                  for(int _i136 = 0; _i136 < _list134.size; ++_i136) {
                     String _elem135 = iprot.readString();
                     struct.infoMessages.add(_elem135);
                  }

                  iprot.readListEnd();
                  struct.setInfoMessagesIsSet(true);
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.sqlState = iprot.readString();
                     struct.setSqlStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.errorCode = iprot.readI32();
                     struct.setErrorCodeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.errorMessage = iprot.readString();
                     struct.setErrorMessageIsSet(true);
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

      public void write(TProtocol oprot, TStatus struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TStatus.STRUCT_DESC);
         if (struct.statusCode != null) {
            oprot.writeFieldBegin(TStatus.STATUS_CODE_FIELD_DESC);
            oprot.writeI32(struct.statusCode.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.infoMessages != null && struct.isSetInfoMessages()) {
            oprot.writeFieldBegin(TStatus.INFO_MESSAGES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.infoMessages.size()));

            for(String _iter137 : struct.infoMessages) {
               oprot.writeString(_iter137);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.sqlState != null && struct.isSetSqlState()) {
            oprot.writeFieldBegin(TStatus.SQL_STATE_FIELD_DESC);
            oprot.writeString(struct.sqlState);
            oprot.writeFieldEnd();
         }

         if (struct.isSetErrorCode()) {
            oprot.writeFieldBegin(TStatus.ERROR_CODE_FIELD_DESC);
            oprot.writeI32(struct.errorCode);
            oprot.writeFieldEnd();
         }

         if (struct.errorMessage != null && struct.isSetErrorMessage()) {
            oprot.writeFieldBegin(TStatus.ERROR_MESSAGE_FIELD_DESC);
            oprot.writeString(struct.errorMessage);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TStatusTupleSchemeFactory implements SchemeFactory {
      private TStatusTupleSchemeFactory() {
      }

      public TStatusTupleScheme getScheme() {
         return new TStatusTupleScheme();
      }
   }

   private static class TStatusTupleScheme extends TupleScheme {
      private TStatusTupleScheme() {
      }

      public void write(TProtocol prot, TStatus struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.statusCode.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetInfoMessages()) {
            optionals.set(0);
         }

         if (struct.isSetSqlState()) {
            optionals.set(1);
         }

         if (struct.isSetErrorCode()) {
            optionals.set(2);
         }

         if (struct.isSetErrorMessage()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetInfoMessages()) {
            oprot.writeI32(struct.infoMessages.size());

            for(String _iter138 : struct.infoMessages) {
               oprot.writeString(_iter138);
            }
         }

         if (struct.isSetSqlState()) {
            oprot.writeString(struct.sqlState);
         }

         if (struct.isSetErrorCode()) {
            oprot.writeI32(struct.errorCode);
         }

         if (struct.isSetErrorMessage()) {
            oprot.writeString(struct.errorMessage);
         }

      }

      public void read(TProtocol prot, TStatus struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.statusCode = TStatusCode.findByValue(iprot.readI32());
         struct.setStatusCodeIsSet(true);
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            TList _list139 = iprot.readListBegin((byte)11);
            struct.infoMessages = new ArrayList(_list139.size);

            for(int _i141 = 0; _i141 < _list139.size; ++_i141) {
               String _elem140 = iprot.readString();
               struct.infoMessages.add(_elem140);
            }

            struct.setInfoMessagesIsSet(true);
         }

         if (incoming.get(1)) {
            struct.sqlState = iprot.readString();
            struct.setSqlStateIsSet(true);
         }

         if (incoming.get(2)) {
            struct.errorCode = iprot.readI32();
            struct.setErrorCodeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.errorMessage = iprot.readString();
            struct.setErrorMessageIsSet(true);
         }

      }
   }
}
