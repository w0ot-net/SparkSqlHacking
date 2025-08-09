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
import org.apache.thrift.meta_data.StructMetaData;
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

public class PrincipalPrivilegeSet implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PrincipalPrivilegeSet");
   private static final TField USER_PRIVILEGES_FIELD_DESC = new TField("userPrivileges", (byte)13, (short)1);
   private static final TField GROUP_PRIVILEGES_FIELD_DESC = new TField("groupPrivileges", (byte)13, (short)2);
   private static final TField ROLE_PRIVILEGES_FIELD_DESC = new TField("rolePrivileges", (byte)13, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PrincipalPrivilegeSetStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PrincipalPrivilegeSetTupleSchemeFactory();
   @Nullable
   private Map userPrivileges;
   @Nullable
   private Map groupPrivileges;
   @Nullable
   private Map rolePrivileges;
   public static final Map metaDataMap;

   public PrincipalPrivilegeSet() {
   }

   public PrincipalPrivilegeSet(Map userPrivileges, Map groupPrivileges, Map rolePrivileges) {
      this();
      this.userPrivileges = userPrivileges;
      this.groupPrivileges = groupPrivileges;
      this.rolePrivileges = rolePrivileges;
   }

   public PrincipalPrivilegeSet(PrincipalPrivilegeSet other) {
      if (other.isSetUserPrivileges()) {
         Map<String, List<PrivilegeGrantInfo>> __this__userPrivileges = new HashMap(other.userPrivileges.size());

         for(Map.Entry other_element : other.userPrivileges.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            List<PrivilegeGrantInfo> other_element_value = (List)other_element.getValue();
            List<PrivilegeGrantInfo> __this__userPrivileges_copy_value = new ArrayList(other_element_value.size());

            for(PrivilegeGrantInfo other_element_value_element : other_element_value) {
               __this__userPrivileges_copy_value.add(new PrivilegeGrantInfo(other_element_value_element));
            }

            __this__userPrivileges.put(other_element_key, __this__userPrivileges_copy_value);
         }

         this.userPrivileges = __this__userPrivileges;
      }

      if (other.isSetGroupPrivileges()) {
         Map<String, List<PrivilegeGrantInfo>> __this__groupPrivileges = new HashMap(other.groupPrivileges.size());

         for(Map.Entry other_element : other.groupPrivileges.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            List<PrivilegeGrantInfo> other_element_value = (List)other_element.getValue();
            List<PrivilegeGrantInfo> __this__groupPrivileges_copy_value = new ArrayList(other_element_value.size());

            for(PrivilegeGrantInfo other_element_value_element : other_element_value) {
               __this__groupPrivileges_copy_value.add(new PrivilegeGrantInfo(other_element_value_element));
            }

            __this__groupPrivileges.put(other_element_key, __this__groupPrivileges_copy_value);
         }

         this.groupPrivileges = __this__groupPrivileges;
      }

      if (other.isSetRolePrivileges()) {
         Map<String, List<PrivilegeGrantInfo>> __this__rolePrivileges = new HashMap(other.rolePrivileges.size());

         for(Map.Entry other_element : other.rolePrivileges.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            List<PrivilegeGrantInfo> other_element_value = (List)other_element.getValue();
            List<PrivilegeGrantInfo> __this__rolePrivileges_copy_value = new ArrayList(other_element_value.size());

            for(PrivilegeGrantInfo other_element_value_element : other_element_value) {
               __this__rolePrivileges_copy_value.add(new PrivilegeGrantInfo(other_element_value_element));
            }

            __this__rolePrivileges.put(other_element_key, __this__rolePrivileges_copy_value);
         }

         this.rolePrivileges = __this__rolePrivileges;
      }

   }

   public PrincipalPrivilegeSet deepCopy() {
      return new PrincipalPrivilegeSet(this);
   }

   public void clear() {
      this.userPrivileges = null;
      this.groupPrivileges = null;
      this.rolePrivileges = null;
   }

   public int getUserPrivilegesSize() {
      return this.userPrivileges == null ? 0 : this.userPrivileges.size();
   }

   public void putToUserPrivileges(String key, List val) {
      if (this.userPrivileges == null) {
         this.userPrivileges = new HashMap();
      }

      this.userPrivileges.put(key, val);
   }

   @Nullable
   public Map getUserPrivileges() {
      return this.userPrivileges;
   }

   public void setUserPrivileges(@Nullable Map userPrivileges) {
      this.userPrivileges = userPrivileges;
   }

   public void unsetUserPrivileges() {
      this.userPrivileges = null;
   }

   public boolean isSetUserPrivileges() {
      return this.userPrivileges != null;
   }

   public void setUserPrivilegesIsSet(boolean value) {
      if (!value) {
         this.userPrivileges = null;
      }

   }

   public int getGroupPrivilegesSize() {
      return this.groupPrivileges == null ? 0 : this.groupPrivileges.size();
   }

   public void putToGroupPrivileges(String key, List val) {
      if (this.groupPrivileges == null) {
         this.groupPrivileges = new HashMap();
      }

      this.groupPrivileges.put(key, val);
   }

   @Nullable
   public Map getGroupPrivileges() {
      return this.groupPrivileges;
   }

   public void setGroupPrivileges(@Nullable Map groupPrivileges) {
      this.groupPrivileges = groupPrivileges;
   }

   public void unsetGroupPrivileges() {
      this.groupPrivileges = null;
   }

   public boolean isSetGroupPrivileges() {
      return this.groupPrivileges != null;
   }

   public void setGroupPrivilegesIsSet(boolean value) {
      if (!value) {
         this.groupPrivileges = null;
      }

   }

   public int getRolePrivilegesSize() {
      return this.rolePrivileges == null ? 0 : this.rolePrivileges.size();
   }

   public void putToRolePrivileges(String key, List val) {
      if (this.rolePrivileges == null) {
         this.rolePrivileges = new HashMap();
      }

      this.rolePrivileges.put(key, val);
   }

   @Nullable
   public Map getRolePrivileges() {
      return this.rolePrivileges;
   }

   public void setRolePrivileges(@Nullable Map rolePrivileges) {
      this.rolePrivileges = rolePrivileges;
   }

   public void unsetRolePrivileges() {
      this.rolePrivileges = null;
   }

   public boolean isSetRolePrivileges() {
      return this.rolePrivileges != null;
   }

   public void setRolePrivilegesIsSet(boolean value) {
      if (!value) {
         this.rolePrivileges = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case USER_PRIVILEGES:
            if (value == null) {
               this.unsetUserPrivileges();
            } else {
               this.setUserPrivileges((Map)value);
            }
            break;
         case GROUP_PRIVILEGES:
            if (value == null) {
               this.unsetGroupPrivileges();
            } else {
               this.setGroupPrivileges((Map)value);
            }
            break;
         case ROLE_PRIVILEGES:
            if (value == null) {
               this.unsetRolePrivileges();
            } else {
               this.setRolePrivileges((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case USER_PRIVILEGES:
            return this.getUserPrivileges();
         case GROUP_PRIVILEGES:
            return this.getGroupPrivileges();
         case ROLE_PRIVILEGES:
            return this.getRolePrivileges();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case USER_PRIVILEGES:
               return this.isSetUserPrivileges();
            case GROUP_PRIVILEGES:
               return this.isSetGroupPrivileges();
            case ROLE_PRIVILEGES:
               return this.isSetRolePrivileges();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PrincipalPrivilegeSet ? this.equals((PrincipalPrivilegeSet)that) : false;
   }

   public boolean equals(PrincipalPrivilegeSet that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_userPrivileges = this.isSetUserPrivileges();
         boolean that_present_userPrivileges = that.isSetUserPrivileges();
         if (this_present_userPrivileges || that_present_userPrivileges) {
            if (!this_present_userPrivileges || !that_present_userPrivileges) {
               return false;
            }

            if (!this.userPrivileges.equals(that.userPrivileges)) {
               return false;
            }
         }

         boolean this_present_groupPrivileges = this.isSetGroupPrivileges();
         boolean that_present_groupPrivileges = that.isSetGroupPrivileges();
         if (this_present_groupPrivileges || that_present_groupPrivileges) {
            if (!this_present_groupPrivileges || !that_present_groupPrivileges) {
               return false;
            }

            if (!this.groupPrivileges.equals(that.groupPrivileges)) {
               return false;
            }
         }

         boolean this_present_rolePrivileges = this.isSetRolePrivileges();
         boolean that_present_rolePrivileges = that.isSetRolePrivileges();
         if (this_present_rolePrivileges || that_present_rolePrivileges) {
            if (!this_present_rolePrivileges || !that_present_rolePrivileges) {
               return false;
            }

            if (!this.rolePrivileges.equals(that.rolePrivileges)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetUserPrivileges() ? 131071 : 524287);
      if (this.isSetUserPrivileges()) {
         hashCode = hashCode * 8191 + this.userPrivileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetGroupPrivileges() ? 131071 : 524287);
      if (this.isSetGroupPrivileges()) {
         hashCode = hashCode * 8191 + this.groupPrivileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRolePrivileges() ? 131071 : 524287);
      if (this.isSetRolePrivileges()) {
         hashCode = hashCode * 8191 + this.rolePrivileges.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PrincipalPrivilegeSet other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetUserPrivileges(), other.isSetUserPrivileges());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetUserPrivileges()) {
               lastComparison = TBaseHelper.compareTo(this.userPrivileges, other.userPrivileges);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetGroupPrivileges(), other.isSetGroupPrivileges());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetGroupPrivileges()) {
                  lastComparison = TBaseHelper.compareTo(this.groupPrivileges, other.groupPrivileges);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetRolePrivileges(), other.isSetRolePrivileges());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetRolePrivileges()) {
                     lastComparison = TBaseHelper.compareTo(this.rolePrivileges, other.rolePrivileges);
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
      return PrincipalPrivilegeSet._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PrincipalPrivilegeSet(");
      boolean first = true;
      sb.append("userPrivileges:");
      if (this.userPrivileges == null) {
         sb.append("null");
      } else {
         sb.append(this.userPrivileges);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("groupPrivileges:");
      if (this.groupPrivileges == null) {
         sb.append("null");
      } else {
         sb.append(this.groupPrivileges);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("rolePrivileges:");
      if (this.rolePrivileges == null) {
         sb.append("null");
      } else {
         sb.append(this.rolePrivileges);
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
      tmpMap.put(PrincipalPrivilegeSet._Fields.USER_PRIVILEGES, new FieldMetaData("userPrivileges", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new ListMetaData((byte)15, new StructMetaData((byte)12, PrivilegeGrantInfo.class)))));
      tmpMap.put(PrincipalPrivilegeSet._Fields.GROUP_PRIVILEGES, new FieldMetaData("groupPrivileges", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new ListMetaData((byte)15, new StructMetaData((byte)12, PrivilegeGrantInfo.class)))));
      tmpMap.put(PrincipalPrivilegeSet._Fields.ROLE_PRIVILEGES, new FieldMetaData("rolePrivileges", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new ListMetaData((byte)15, new StructMetaData((byte)12, PrivilegeGrantInfo.class)))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PrincipalPrivilegeSet.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      USER_PRIVILEGES((short)1, "userPrivileges"),
      GROUP_PRIVILEGES((short)2, "groupPrivileges"),
      ROLE_PRIVILEGES((short)3, "rolePrivileges");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return USER_PRIVILEGES;
            case 2:
               return GROUP_PRIVILEGES;
            case 3:
               return ROLE_PRIVILEGES;
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

   private static class PrincipalPrivilegeSetStandardSchemeFactory implements SchemeFactory {
      private PrincipalPrivilegeSetStandardSchemeFactory() {
      }

      public PrincipalPrivilegeSetStandardScheme getScheme() {
         return new PrincipalPrivilegeSetStandardScheme();
      }
   }

   private static class PrincipalPrivilegeSetStandardScheme extends StandardScheme {
      private PrincipalPrivilegeSetStandardScheme() {
      }

      public void read(TProtocol iprot, PrincipalPrivilegeSet struct) throws TException {
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
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map24 = iprot.readMapBegin();
                  struct.userPrivileges = new HashMap(2 * _map24.size);

                  for(int _i27 = 0; _i27 < _map24.size; ++_i27) {
                     String _key25 = iprot.readString();
                     TList _list28 = iprot.readListBegin();
                     List<PrivilegeGrantInfo> _val26 = new ArrayList(_list28.size);

                     for(int _i30 = 0; _i30 < _list28.size; ++_i30) {
                        PrivilegeGrantInfo _elem29 = new PrivilegeGrantInfo();
                        _elem29.read(iprot);
                        _val26.add(_elem29);
                     }

                     iprot.readListEnd();
                     struct.userPrivileges.put(_key25, _val26);
                  }

                  iprot.readMapEnd();
                  struct.setUserPrivilegesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map31 = iprot.readMapBegin();
                  struct.groupPrivileges = new HashMap(2 * _map31.size);

                  for(int _i34 = 0; _i34 < _map31.size; ++_i34) {
                     String _key32 = iprot.readString();
                     TList _list35 = iprot.readListBegin();
                     List<PrivilegeGrantInfo> _val33 = new ArrayList(_list35.size);

                     for(int _i37 = 0; _i37 < _list35.size; ++_i37) {
                        PrivilegeGrantInfo _elem36 = new PrivilegeGrantInfo();
                        _elem36.read(iprot);
                        _val33.add(_elem36);
                     }

                     iprot.readListEnd();
                     struct.groupPrivileges.put(_key32, _val33);
                  }

                  iprot.readMapEnd();
                  struct.setGroupPrivilegesIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map38 = iprot.readMapBegin();
                  struct.rolePrivileges = new HashMap(2 * _map38.size);

                  for(int _i41 = 0; _i41 < _map38.size; ++_i41) {
                     String _key39 = iprot.readString();
                     TList _list42 = iprot.readListBegin();
                     List<PrivilegeGrantInfo> _val40 = new ArrayList(_list42.size);

                     for(int _i44 = 0; _i44 < _list42.size; ++_i44) {
                        PrivilegeGrantInfo _elem43 = new PrivilegeGrantInfo();
                        _elem43.read(iprot);
                        _val40.add(_elem43);
                     }

                     iprot.readListEnd();
                     struct.rolePrivileges.put(_key39, _val40);
                  }

                  iprot.readMapEnd();
                  struct.setRolePrivilegesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PrincipalPrivilegeSet struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PrincipalPrivilegeSet.STRUCT_DESC);
         if (struct.userPrivileges != null) {
            oprot.writeFieldBegin(PrincipalPrivilegeSet.USER_PRIVILEGES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)15, struct.userPrivileges.size()));

            for(Map.Entry _iter45 : struct.userPrivileges.entrySet()) {
               oprot.writeString((String)_iter45.getKey());
               oprot.writeListBegin(new TList((byte)12, ((List)_iter45.getValue()).size()));

               for(PrivilegeGrantInfo _iter46 : (List)_iter45.getValue()) {
                  _iter46.write(oprot);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.groupPrivileges != null) {
            oprot.writeFieldBegin(PrincipalPrivilegeSet.GROUP_PRIVILEGES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)15, struct.groupPrivileges.size()));

            for(Map.Entry _iter47 : struct.groupPrivileges.entrySet()) {
               oprot.writeString((String)_iter47.getKey());
               oprot.writeListBegin(new TList((byte)12, ((List)_iter47.getValue()).size()));

               for(PrivilegeGrantInfo _iter48 : (List)_iter47.getValue()) {
                  _iter48.write(oprot);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.rolePrivileges != null) {
            oprot.writeFieldBegin(PrincipalPrivilegeSet.ROLE_PRIVILEGES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)15, struct.rolePrivileges.size()));

            for(Map.Entry _iter49 : struct.rolePrivileges.entrySet()) {
               oprot.writeString((String)_iter49.getKey());
               oprot.writeListBegin(new TList((byte)12, ((List)_iter49.getValue()).size()));

               for(PrivilegeGrantInfo _iter50 : (List)_iter49.getValue()) {
                  _iter50.write(oprot);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PrincipalPrivilegeSetTupleSchemeFactory implements SchemeFactory {
      private PrincipalPrivilegeSetTupleSchemeFactory() {
      }

      public PrincipalPrivilegeSetTupleScheme getScheme() {
         return new PrincipalPrivilegeSetTupleScheme();
      }
   }

   private static class PrincipalPrivilegeSetTupleScheme extends TupleScheme {
      private PrincipalPrivilegeSetTupleScheme() {
      }

      public void write(TProtocol prot, PrincipalPrivilegeSet struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetUserPrivileges()) {
            optionals.set(0);
         }

         if (struct.isSetGroupPrivileges()) {
            optionals.set(1);
         }

         if (struct.isSetRolePrivileges()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetUserPrivileges()) {
            oprot.writeI32(struct.userPrivileges.size());

            for(Map.Entry _iter51 : struct.userPrivileges.entrySet()) {
               oprot.writeString((String)_iter51.getKey());
               oprot.writeI32(((List)_iter51.getValue()).size());

               for(PrivilegeGrantInfo _iter52 : (List)_iter51.getValue()) {
                  _iter52.write(oprot);
               }
            }
         }

         if (struct.isSetGroupPrivileges()) {
            oprot.writeI32(struct.groupPrivileges.size());

            for(Map.Entry _iter53 : struct.groupPrivileges.entrySet()) {
               oprot.writeString((String)_iter53.getKey());
               oprot.writeI32(((List)_iter53.getValue()).size());

               for(PrivilegeGrantInfo _iter54 : (List)_iter53.getValue()) {
                  _iter54.write(oprot);
               }
            }
         }

         if (struct.isSetRolePrivileges()) {
            oprot.writeI32(struct.rolePrivileges.size());

            for(Map.Entry _iter55 : struct.rolePrivileges.entrySet()) {
               oprot.writeString((String)_iter55.getKey());
               oprot.writeI32(((List)_iter55.getValue()).size());

               for(PrivilegeGrantInfo _iter56 : (List)_iter55.getValue()) {
                  _iter56.write(oprot);
               }
            }
         }

      }

      public void read(TProtocol prot, PrincipalPrivilegeSet struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            TMap _map57 = iprot.readMapBegin((byte)11, (byte)15);
            struct.userPrivileges = new HashMap(2 * _map57.size);

            for(int _i60 = 0; _i60 < _map57.size; ++_i60) {
               String _key58 = iprot.readString();
               TList _list61 = iprot.readListBegin((byte)12);
               List<PrivilegeGrantInfo> _val59 = new ArrayList(_list61.size);

               for(int _i63 = 0; _i63 < _list61.size; ++_i63) {
                  PrivilegeGrantInfo _elem62 = new PrivilegeGrantInfo();
                  _elem62.read(iprot);
                  _val59.add(_elem62);
               }

               struct.userPrivileges.put(_key58, _val59);
            }

            struct.setUserPrivilegesIsSet(true);
         }

         if (incoming.get(1)) {
            TMap _map64 = iprot.readMapBegin((byte)11, (byte)15);
            struct.groupPrivileges = new HashMap(2 * _map64.size);

            for(int _i67 = 0; _i67 < _map64.size; ++_i67) {
               String _key65 = iprot.readString();
               TList _list68 = iprot.readListBegin((byte)12);
               List<PrivilegeGrantInfo> _val66 = new ArrayList(_list68.size);

               for(int _i70 = 0; _i70 < _list68.size; ++_i70) {
                  PrivilegeGrantInfo _elem69 = new PrivilegeGrantInfo();
                  _elem69.read(iprot);
                  _val66.add(_elem69);
               }

               struct.groupPrivileges.put(_key65, _val66);
            }

            struct.setGroupPrivilegesIsSet(true);
         }

         if (incoming.get(2)) {
            TMap _map71 = iprot.readMapBegin((byte)11, (byte)15);
            struct.rolePrivileges = new HashMap(2 * _map71.size);

            for(int _i74 = 0; _i74 < _map71.size; ++_i74) {
               String _key72 = iprot.readString();
               TList _list75 = iprot.readListBegin((byte)12);
               List<PrivilegeGrantInfo> _val73 = new ArrayList(_list75.size);

               for(int _i77 = 0; _i77 < _list75.size; ++_i77) {
                  PrivilegeGrantInfo _elem76 = new PrivilegeGrantInfo();
                  _elem76.read(iprot);
                  _val73.add(_elem76);
               }

               struct.rolePrivileges.put(_key72, _val73);
            }

            struct.setRolePrivilegesIsSet(true);
         }

      }
   }
}
