package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
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

@Public
@Stable
public class THandleIdentifier implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("THandleIdentifier");
   private static final TField GUID_FIELD_DESC = new TField("guid", (byte)11, (short)1);
   private static final TField SECRET_FIELD_DESC = new TField("secret", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new THandleIdentifierStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new THandleIdentifierTupleSchemeFactory();
   @Nullable
   private ByteBuffer guid;
   @Nullable
   private ByteBuffer secret;
   public static final Map metaDataMap;

   public THandleIdentifier() {
   }

   public THandleIdentifier(ByteBuffer guid, ByteBuffer secret) {
      this();
      this.guid = TBaseHelper.copyBinary(guid);
      this.secret = TBaseHelper.copyBinary(secret);
   }

   public THandleIdentifier(THandleIdentifier other) {
      if (other.isSetGuid()) {
         this.guid = TBaseHelper.copyBinary(other.guid);
      }

      if (other.isSetSecret()) {
         this.secret = TBaseHelper.copyBinary(other.secret);
      }

   }

   public THandleIdentifier deepCopy() {
      return new THandleIdentifier(this);
   }

   public void clear() {
      this.guid = null;
      this.secret = null;
   }

   public byte[] getGuid() {
      this.setGuid(TBaseHelper.rightSize(this.guid));
      return this.guid == null ? null : this.guid.array();
   }

   public ByteBuffer bufferForGuid() {
      return TBaseHelper.copyBinary(this.guid);
   }

   public void setGuid(byte[] guid) {
      this.guid = guid == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)guid).clone());
   }

   public void setGuid(@Nullable ByteBuffer guid) {
      this.guid = TBaseHelper.copyBinary(guid);
   }

   public void unsetGuid() {
      this.guid = null;
   }

   public boolean isSetGuid() {
      return this.guid != null;
   }

   public void setGuidIsSet(boolean value) {
      if (!value) {
         this.guid = null;
      }

   }

   public byte[] getSecret() {
      this.setSecret(TBaseHelper.rightSize(this.secret));
      return this.secret == null ? null : this.secret.array();
   }

   public ByteBuffer bufferForSecret() {
      return TBaseHelper.copyBinary(this.secret);
   }

   public void setSecret(byte[] secret) {
      this.secret = secret == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)secret).clone());
   }

   public void setSecret(@Nullable ByteBuffer secret) {
      this.secret = TBaseHelper.copyBinary(secret);
   }

   public void unsetSecret() {
      this.secret = null;
   }

   public boolean isSetSecret() {
      return this.secret != null;
   }

   public void setSecretIsSet(boolean value) {
      if (!value) {
         this.secret = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case GUID:
            if (value == null) {
               this.unsetGuid();
            } else if (value instanceof byte[]) {
               this.setGuid((byte[])value);
            } else {
               this.setGuid((ByteBuffer)value);
            }
            break;
         case SECRET:
            if (value == null) {
               this.unsetSecret();
            } else if (value instanceof byte[]) {
               this.setSecret((byte[])value);
            } else {
               this.setSecret((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case GUID:
            return this.getGuid();
         case SECRET:
            return this.getSecret();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case GUID:
               return this.isSetGuid();
            case SECRET:
               return this.isSetSecret();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof THandleIdentifier ? this.equals((THandleIdentifier)that) : false;
   }

   public boolean equals(THandleIdentifier that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_guid = this.isSetGuid();
         boolean that_present_guid = that.isSetGuid();
         if (this_present_guid || that_present_guid) {
            if (!this_present_guid || !that_present_guid) {
               return false;
            }

            if (!this.guid.equals(that.guid)) {
               return false;
            }
         }

         boolean this_present_secret = this.isSetSecret();
         boolean that_present_secret = that.isSetSecret();
         if (this_present_secret || that_present_secret) {
            if (!this_present_secret || !that_present_secret) {
               return false;
            }

            if (!this.secret.equals(that.secret)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetGuid() ? 131071 : 524287);
      if (this.isSetGuid()) {
         hashCode = hashCode * 8191 + this.guid.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSecret() ? 131071 : 524287);
      if (this.isSetSecret()) {
         hashCode = hashCode * 8191 + this.secret.hashCode();
      }

      return hashCode;
   }

   public int compareTo(THandleIdentifier other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetGuid(), other.isSetGuid());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetGuid()) {
               lastComparison = TBaseHelper.compareTo(this.guid, other.guid);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetSecret(), other.isSetSecret());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSecret()) {
                  lastComparison = TBaseHelper.compareTo(this.secret, other.secret);
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
      return THandleIdentifier._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("THandleIdentifier(");
      boolean first = true;
      sb.append("guid:");
      if (this.guid == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.guid, sb);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("secret:");
      if (this.secret == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.secret, sb);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetGuid()) {
         throw new TProtocolException("Required field 'guid' is unset! Struct:" + this.toString());
      } else if (!this.isSetSecret()) {
         throw new TProtocolException("Required field 'secret' is unset! Struct:" + this.toString());
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
      tmpMap.put(THandleIdentifier._Fields.GUID, new FieldMetaData("guid", (byte)1, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(THandleIdentifier._Fields.SECRET, new FieldMetaData("secret", (byte)1, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(THandleIdentifier.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      GUID((short)1, "guid"),
      SECRET((short)2, "secret");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return GUID;
            case 2:
               return SECRET;
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

   private static class THandleIdentifierStandardSchemeFactory implements SchemeFactory {
      private THandleIdentifierStandardSchemeFactory() {
      }

      public THandleIdentifierStandardScheme getScheme() {
         return new THandleIdentifierStandardScheme();
      }
   }

   private static class THandleIdentifierStandardScheme extends StandardScheme {
      private THandleIdentifierStandardScheme() {
      }

      public void read(TProtocol iprot, THandleIdentifier struct) throws TException {
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
                     struct.guid = iprot.readBinary();
                     struct.setGuidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.secret = iprot.readBinary();
                     struct.setSecretIsSet(true);
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

      public void write(TProtocol oprot, THandleIdentifier struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(THandleIdentifier.STRUCT_DESC);
         if (struct.guid != null) {
            oprot.writeFieldBegin(THandleIdentifier.GUID_FIELD_DESC);
            oprot.writeBinary(struct.guid);
            oprot.writeFieldEnd();
         }

         if (struct.secret != null) {
            oprot.writeFieldBegin(THandleIdentifier.SECRET_FIELD_DESC);
            oprot.writeBinary(struct.secret);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class THandleIdentifierTupleSchemeFactory implements SchemeFactory {
      private THandleIdentifierTupleSchemeFactory() {
      }

      public THandleIdentifierTupleScheme getScheme() {
         return new THandleIdentifierTupleScheme();
      }
   }

   private static class THandleIdentifierTupleScheme extends TupleScheme {
      private THandleIdentifierTupleScheme() {
      }

      public void write(TProtocol prot, THandleIdentifier struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBinary(struct.guid);
         oprot.writeBinary(struct.secret);
      }

      public void read(TProtocol prot, THandleIdentifier struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.guid = iprot.readBinary();
         struct.setGuidIsSet(true);
         struct.secret = iprot.readBinary();
         struct.setSecretIsSet(true);
      }
   }
}
