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

public class Version implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Version");
   private static final TField VERSION_FIELD_DESC = new TField("version", (byte)11, (short)1);
   private static final TField COMMENTS_FIELD_DESC = new TField("comments", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new VersionStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new VersionTupleSchemeFactory();
   @Nullable
   private String version;
   @Nullable
   private String comments;
   public static final Map metaDataMap;

   public Version() {
   }

   public Version(String version, String comments) {
      this();
      this.version = version;
      this.comments = comments;
   }

   public Version(Version other) {
      if (other.isSetVersion()) {
         this.version = other.version;
      }

      if (other.isSetComments()) {
         this.comments = other.comments;
      }

   }

   public Version deepCopy() {
      return new Version(this);
   }

   public void clear() {
      this.version = null;
      this.comments = null;
   }

   @Nullable
   public String getVersion() {
      return this.version;
   }

   public void setVersion(@Nullable String version) {
      this.version = version;
   }

   public void unsetVersion() {
      this.version = null;
   }

   public boolean isSetVersion() {
      return this.version != null;
   }

   public void setVersionIsSet(boolean value) {
      if (!value) {
         this.version = null;
      }

   }

   @Nullable
   public String getComments() {
      return this.comments;
   }

   public void setComments(@Nullable String comments) {
      this.comments = comments;
   }

   public void unsetComments() {
      this.comments = null;
   }

   public boolean isSetComments() {
      return this.comments != null;
   }

   public void setCommentsIsSet(boolean value) {
      if (!value) {
         this.comments = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case VERSION:
            if (value == null) {
               this.unsetVersion();
            } else {
               this.setVersion((String)value);
            }
            break;
         case COMMENTS:
            if (value == null) {
               this.unsetComments();
            } else {
               this.setComments((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VERSION:
            return this.getVersion();
         case COMMENTS:
            return this.getComments();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case VERSION:
               return this.isSetVersion();
            case COMMENTS:
               return this.isSetComments();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Version ? this.equals((Version)that) : false;
   }

   public boolean equals(Version that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_version = this.isSetVersion();
         boolean that_present_version = that.isSetVersion();
         if (this_present_version || that_present_version) {
            if (!this_present_version || !that_present_version) {
               return false;
            }

            if (!this.version.equals(that.version)) {
               return false;
            }
         }

         boolean this_present_comments = this.isSetComments();
         boolean that_present_comments = that.isSetComments();
         if (this_present_comments || that_present_comments) {
            if (!this_present_comments || !that_present_comments) {
               return false;
            }

            if (!this.comments.equals(that.comments)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetVersion() ? 131071 : 524287);
      if (this.isSetVersion()) {
         hashCode = hashCode * 8191 + this.version.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetComments() ? 131071 : 524287);
      if (this.isSetComments()) {
         hashCode = hashCode * 8191 + this.comments.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Version other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetVersion(), other.isSetVersion());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetVersion()) {
               lastComparison = TBaseHelper.compareTo(this.version, other.version);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetComments(), other.isSetComments());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetComments()) {
                  lastComparison = TBaseHelper.compareTo(this.comments, other.comments);
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
      return Version._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Version(");
      boolean first = true;
      sb.append("version:");
      if (this.version == null) {
         sb.append("null");
      } else {
         sb.append(this.version);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("comments:");
      if (this.comments == null) {
         sb.append("null");
      } else {
         sb.append(this.comments);
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
      tmpMap.put(Version._Fields.VERSION, new FieldMetaData("version", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Version._Fields.COMMENTS, new FieldMetaData("comments", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Version.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VERSION((short)1, "version"),
      COMMENTS((short)2, "comments");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VERSION;
            case 2:
               return COMMENTS;
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

   private static class VersionStandardSchemeFactory implements SchemeFactory {
      private VersionStandardSchemeFactory() {
      }

      public VersionStandardScheme getScheme() {
         return new VersionStandardScheme();
      }
   }

   private static class VersionStandardScheme extends StandardScheme {
      private VersionStandardScheme() {
      }

      public void read(TProtocol iprot, Version struct) throws TException {
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
                     struct.version = iprot.readString();
                     struct.setVersionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.comments = iprot.readString();
                     struct.setCommentsIsSet(true);
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

      public void write(TProtocol oprot, Version struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Version.STRUCT_DESC);
         if (struct.version != null) {
            oprot.writeFieldBegin(Version.VERSION_FIELD_DESC);
            oprot.writeString(struct.version);
            oprot.writeFieldEnd();
         }

         if (struct.comments != null) {
            oprot.writeFieldBegin(Version.COMMENTS_FIELD_DESC);
            oprot.writeString(struct.comments);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class VersionTupleSchemeFactory implements SchemeFactory {
      private VersionTupleSchemeFactory() {
      }

      public VersionTupleScheme getScheme() {
         return new VersionTupleScheme();
      }
   }

   private static class VersionTupleScheme extends TupleScheme {
      private VersionTupleScheme() {
      }

      public void write(TProtocol prot, Version struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetVersion()) {
            optionals.set(0);
         }

         if (struct.isSetComments()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetVersion()) {
            oprot.writeString(struct.version);
         }

         if (struct.isSetComments()) {
            oprot.writeString(struct.comments);
         }

      }

      public void read(TProtocol prot, Version struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.version = iprot.readString();
            struct.setVersionIsSet(true);
         }

         if (incoming.get(1)) {
            struct.comments = iprot.readString();
            struct.setCommentsIsSet(true);
         }

      }
   }
}
