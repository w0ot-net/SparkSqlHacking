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

public class PrivilegeBag implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PrivilegeBag");
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PrivilegeBagStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PrivilegeBagTupleSchemeFactory();
   @Nullable
   private List privileges;
   public static final Map metaDataMap;

   public PrivilegeBag() {
   }

   public PrivilegeBag(List privileges) {
      this();
      this.privileges = privileges;
   }

   public PrivilegeBag(PrivilegeBag other) {
      if (other.isSetPrivileges()) {
         List<HiveObjectPrivilege> __this__privileges = new ArrayList(other.privileges.size());

         for(HiveObjectPrivilege other_element : other.privileges) {
            __this__privileges.add(new HiveObjectPrivilege(other_element));
         }

         this.privileges = __this__privileges;
      }

   }

   public PrivilegeBag deepCopy() {
      return new PrivilegeBag(this);
   }

   public void clear() {
      this.privileges = null;
   }

   public int getPrivilegesSize() {
      return this.privileges == null ? 0 : this.privileges.size();
   }

   @Nullable
   public Iterator getPrivilegesIterator() {
      return this.privileges == null ? null : this.privileges.iterator();
   }

   public void addToPrivileges(HiveObjectPrivilege elem) {
      if (this.privileges == null) {
         this.privileges = new ArrayList();
      }

      this.privileges.add(elem);
   }

   @Nullable
   public List getPrivileges() {
      return this.privileges;
   }

   public void setPrivileges(@Nullable List privileges) {
      this.privileges = privileges;
   }

   public void unsetPrivileges() {
      this.privileges = null;
   }

   public boolean isSetPrivileges() {
      return this.privileges != null;
   }

   public void setPrivilegesIsSet(boolean value) {
      if (!value) {
         this.privileges = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRIVILEGES:
            return this.getPrivileges();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRIVILEGES:
               return this.isSetPrivileges();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PrivilegeBag ? this.equals((PrivilegeBag)that) : false;
   }

   public boolean equals(PrivilegeBag that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_privileges = this.isSetPrivileges();
         boolean that_present_privileges = that.isSetPrivileges();
         if (this_present_privileges || that_present_privileges) {
            if (!this_present_privileges || !that_present_privileges) {
               return false;
            }

            if (!this.privileges.equals(that.privileges)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PrivilegeBag other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrivileges(), other.isSetPrivileges());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrivileges()) {
               lastComparison = TBaseHelper.compareTo(this.privileges, other.privileges);
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
      return PrivilegeBag._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PrivilegeBag(");
      boolean first = true;
      sb.append("privileges:");
      if (this.privileges == null) {
         sb.append("null");
      } else {
         sb.append(this.privileges);
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
      tmpMap.put(PrivilegeBag._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, HiveObjectPrivilege.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PrivilegeBag.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRIVILEGES((short)1, "privileges");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRIVILEGES;
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

   private static class PrivilegeBagStandardSchemeFactory implements SchemeFactory {
      private PrivilegeBagStandardSchemeFactory() {
      }

      public PrivilegeBagStandardScheme getScheme() {
         return new PrivilegeBagStandardScheme();
      }
   }

   private static class PrivilegeBagStandardScheme extends StandardScheme {
      private PrivilegeBagStandardScheme() {
      }

      public void read(TProtocol iprot, PrivilegeBag struct) throws TException {
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

                  TList _list16 = iprot.readListBegin();
                  struct.privileges = new ArrayList(_list16.size);

                  for(int _i18 = 0; _i18 < _list16.size; ++_i18) {
                     HiveObjectPrivilege _elem17 = new HiveObjectPrivilege();
                     _elem17.read(iprot);
                     struct.privileges.add(_elem17);
                  }

                  iprot.readListEnd();
                  struct.setPrivilegesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PrivilegeBag struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PrivilegeBag.STRUCT_DESC);
         if (struct.privileges != null) {
            oprot.writeFieldBegin(PrivilegeBag.PRIVILEGES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.privileges.size()));

            for(HiveObjectPrivilege _iter19 : struct.privileges) {
               _iter19.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PrivilegeBagTupleSchemeFactory implements SchemeFactory {
      private PrivilegeBagTupleSchemeFactory() {
      }

      public PrivilegeBagTupleScheme getScheme() {
         return new PrivilegeBagTupleScheme();
      }
   }

   private static class PrivilegeBagTupleScheme extends TupleScheme {
      private PrivilegeBagTupleScheme() {
      }

      public void write(TProtocol prot, PrivilegeBag struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPrivileges()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetPrivileges()) {
            oprot.writeI32(struct.privileges.size());

            for(HiveObjectPrivilege _iter20 : struct.privileges) {
               _iter20.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, PrivilegeBag struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list21 = iprot.readListBegin((byte)12);
            struct.privileges = new ArrayList(_list21.size);

            for(int _i23 = 0; _i23 < _list21.size; ++_i23) {
               HiveObjectPrivilege _elem22 = new HiveObjectPrivilege();
               _elem22.read(iprot);
               struct.privileges.add(_elem22);
            }

            struct.setPrivilegesIsSet(true);
         }

      }
   }
}
