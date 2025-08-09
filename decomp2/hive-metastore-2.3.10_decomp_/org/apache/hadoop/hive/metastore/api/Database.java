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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class Database implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Database");
   private static final TField NAME_FIELD_DESC = new TField("name", (byte)11, (short)1);
   private static final TField DESCRIPTION_FIELD_DESC = new TField("description", (byte)11, (short)2);
   private static final TField LOCATION_URI_FIELD_DESC = new TField("locationUri", (byte)11, (short)3);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)4);
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)12, (short)5);
   private static final TField OWNER_NAME_FIELD_DESC = new TField("ownerName", (byte)11, (short)6);
   private static final TField OWNER_TYPE_FIELD_DESC = new TField("ownerType", (byte)8, (short)7);
   private static final TField CATALOG_NAME_FIELD_DESC = new TField("catalogName", (byte)11, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DatabaseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DatabaseTupleSchemeFactory();
   @Nullable
   private String name;
   @Nullable
   private String description;
   @Nullable
   private String locationUri;
   @Nullable
   private Map parameters;
   @Nullable
   private PrincipalPrivilegeSet privileges;
   @Nullable
   private String ownerName;
   @Nullable
   private PrincipalType ownerType;
   @Nullable
   private String catalogName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Database() {
   }

   public Database(String name, String description, String locationUri, Map parameters) {
      this();
      this.name = name;
      this.description = description;
      this.locationUri = locationUri;
      this.parameters = parameters;
   }

   public Database(Database other) {
      if (other.isSetName()) {
         this.name = other.name;
      }

      if (other.isSetDescription()) {
         this.description = other.description;
      }

      if (other.isSetLocationUri()) {
         this.locationUri = other.locationUri;
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      if (other.isSetPrivileges()) {
         this.privileges = new PrincipalPrivilegeSet(other.privileges);
      }

      if (other.isSetOwnerName()) {
         this.ownerName = other.ownerName;
      }

      if (other.isSetOwnerType()) {
         this.ownerType = other.ownerType;
      }

      if (other.isSetCatalogName()) {
         this.catalogName = other.catalogName;
      }

   }

   public Database deepCopy() {
      return new Database(this);
   }

   public void clear() {
      this.name = null;
      this.description = null;
      this.locationUri = null;
      this.parameters = null;
      this.privileges = null;
      this.ownerName = null;
      this.ownerType = null;
      this.catalogName = null;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(@Nullable String name) {
      this.name = name;
   }

   public void unsetName() {
      this.name = null;
   }

   public boolean isSetName() {
      return this.name != null;
   }

   public void setNameIsSet(boolean value) {
      if (!value) {
         this.name = null;
      }

   }

   @Nullable
   public String getDescription() {
      return this.description;
   }

   public void setDescription(@Nullable String description) {
      this.description = description;
   }

   public void unsetDescription() {
      this.description = null;
   }

   public boolean isSetDescription() {
      return this.description != null;
   }

   public void setDescriptionIsSet(boolean value) {
      if (!value) {
         this.description = null;
      }

   }

   @Nullable
   public String getLocationUri() {
      return this.locationUri;
   }

   public void setLocationUri(@Nullable String locationUri) {
      this.locationUri = locationUri;
   }

   public void unsetLocationUri() {
      this.locationUri = null;
   }

   public boolean isSetLocationUri() {
      return this.locationUri != null;
   }

   public void setLocationUriIsSet(boolean value) {
      if (!value) {
         this.locationUri = null;
      }

   }

   public int getParametersSize() {
      return this.parameters == null ? 0 : this.parameters.size();
   }

   public void putToParameters(String key, String val) {
      if (this.parameters == null) {
         this.parameters = new HashMap();
      }

      this.parameters.put(key, val);
   }

   @Nullable
   public Map getParameters() {
      return this.parameters;
   }

   public void setParameters(@Nullable Map parameters) {
      this.parameters = parameters;
   }

   public void unsetParameters() {
      this.parameters = null;
   }

   public boolean isSetParameters() {
      return this.parameters != null;
   }

   public void setParametersIsSet(boolean value) {
      if (!value) {
         this.parameters = null;
      }

   }

   @Nullable
   public PrincipalPrivilegeSet getPrivileges() {
      return this.privileges;
   }

   public void setPrivileges(@Nullable PrincipalPrivilegeSet privileges) {
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

   @Nullable
   public String getOwnerName() {
      return this.ownerName;
   }

   public void setOwnerName(@Nullable String ownerName) {
      this.ownerName = ownerName;
   }

   public void unsetOwnerName() {
      this.ownerName = null;
   }

   public boolean isSetOwnerName() {
      return this.ownerName != null;
   }

   public void setOwnerNameIsSet(boolean value) {
      if (!value) {
         this.ownerName = null;
      }

   }

   @Nullable
   public PrincipalType getOwnerType() {
      return this.ownerType;
   }

   public void setOwnerType(@Nullable PrincipalType ownerType) {
      this.ownerType = ownerType;
   }

   public void unsetOwnerType() {
      this.ownerType = null;
   }

   public boolean isSetOwnerType() {
      return this.ownerType != null;
   }

   public void setOwnerTypeIsSet(boolean value) {
      if (!value) {
         this.ownerType = null;
      }

   }

   @Nullable
   public String getCatalogName() {
      return this.catalogName;
   }

   public void setCatalogName(@Nullable String catalogName) {
      this.catalogName = catalogName;
   }

   public void unsetCatalogName() {
      this.catalogName = null;
   }

   public boolean isSetCatalogName() {
      return this.catalogName != null;
   }

   public void setCatalogNameIsSet(boolean value) {
      if (!value) {
         this.catalogName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NAME:
            if (value == null) {
               this.unsetName();
            } else {
               this.setName((String)value);
            }
            break;
         case DESCRIPTION:
            if (value == null) {
               this.unsetDescription();
            } else {
               this.setDescription((String)value);
            }
            break;
         case LOCATION_URI:
            if (value == null) {
               this.unsetLocationUri();
            } else {
               this.setLocationUri((String)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
            break;
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((PrincipalPrivilegeSet)value);
            }
            break;
         case OWNER_NAME:
            if (value == null) {
               this.unsetOwnerName();
            } else {
               this.setOwnerName((String)value);
            }
            break;
         case OWNER_TYPE:
            if (value == null) {
               this.unsetOwnerType();
            } else {
               this.setOwnerType((PrincipalType)value);
            }
            break;
         case CATALOG_NAME:
            if (value == null) {
               this.unsetCatalogName();
            } else {
               this.setCatalogName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NAME:
            return this.getName();
         case DESCRIPTION:
            return this.getDescription();
         case LOCATION_URI:
            return this.getLocationUri();
         case PARAMETERS:
            return this.getParameters();
         case PRIVILEGES:
            return this.getPrivileges();
         case OWNER_NAME:
            return this.getOwnerName();
         case OWNER_TYPE:
            return this.getOwnerType();
         case CATALOG_NAME:
            return this.getCatalogName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NAME:
               return this.isSetName();
            case DESCRIPTION:
               return this.isSetDescription();
            case LOCATION_URI:
               return this.isSetLocationUri();
            case PARAMETERS:
               return this.isSetParameters();
            case PRIVILEGES:
               return this.isSetPrivileges();
            case OWNER_NAME:
               return this.isSetOwnerName();
            case OWNER_TYPE:
               return this.isSetOwnerType();
            case CATALOG_NAME:
               return this.isSetCatalogName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Database ? this.equals((Database)that) : false;
   }

   public boolean equals(Database that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_name = this.isSetName();
         boolean that_present_name = that.isSetName();
         if (this_present_name || that_present_name) {
            if (!this_present_name || !that_present_name) {
               return false;
            }

            if (!this.name.equals(that.name)) {
               return false;
            }
         }

         boolean this_present_description = this.isSetDescription();
         boolean that_present_description = that.isSetDescription();
         if (this_present_description || that_present_description) {
            if (!this_present_description || !that_present_description) {
               return false;
            }

            if (!this.description.equals(that.description)) {
               return false;
            }
         }

         boolean this_present_locationUri = this.isSetLocationUri();
         boolean that_present_locationUri = that.isSetLocationUri();
         if (this_present_locationUri || that_present_locationUri) {
            if (!this_present_locationUri || !that_present_locationUri) {
               return false;
            }

            if (!this.locationUri.equals(that.locationUri)) {
               return false;
            }
         }

         boolean this_present_parameters = this.isSetParameters();
         boolean that_present_parameters = that.isSetParameters();
         if (this_present_parameters || that_present_parameters) {
            if (!this_present_parameters || !that_present_parameters) {
               return false;
            }

            if (!this.parameters.equals(that.parameters)) {
               return false;
            }
         }

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

         boolean this_present_ownerName = this.isSetOwnerName();
         boolean that_present_ownerName = that.isSetOwnerName();
         if (this_present_ownerName || that_present_ownerName) {
            if (!this_present_ownerName || !that_present_ownerName) {
               return false;
            }

            if (!this.ownerName.equals(that.ownerName)) {
               return false;
            }
         }

         boolean this_present_ownerType = this.isSetOwnerType();
         boolean that_present_ownerType = that.isSetOwnerType();
         if (this_present_ownerType || that_present_ownerType) {
            if (!this_present_ownerType || !that_present_ownerType) {
               return false;
            }

            if (!this.ownerType.equals(that.ownerType)) {
               return false;
            }
         }

         boolean this_present_catalogName = this.isSetCatalogName();
         boolean that_present_catalogName = that.isSetCatalogName();
         if (this_present_catalogName || that_present_catalogName) {
            if (!this_present_catalogName || !that_present_catalogName) {
               return false;
            }

            if (!this.catalogName.equals(that.catalogName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetName() ? 131071 : 524287);
      if (this.isSetName()) {
         hashCode = hashCode * 8191 + this.name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDescription() ? 131071 : 524287);
      if (this.isSetDescription()) {
         hashCode = hashCode * 8191 + this.description.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLocationUri() ? 131071 : 524287);
      if (this.isSetLocationUri()) {
         hashCode = hashCode * 8191 + this.locationUri.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOwnerName() ? 131071 : 524287);
      if (this.isSetOwnerName()) {
         hashCode = hashCode * 8191 + this.ownerName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOwnerType() ? 131071 : 524287);
      if (this.isSetOwnerType()) {
         hashCode = hashCode * 8191 + this.ownerType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetCatalogName() ? 131071 : 524287);
      if (this.isSetCatalogName()) {
         hashCode = hashCode * 8191 + this.catalogName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Database other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetName(), other.isSetName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetName()) {
               lastComparison = TBaseHelper.compareTo(this.name, other.name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetDescription(), other.isSetDescription());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDescription()) {
                  lastComparison = TBaseHelper.compareTo(this.description, other.description);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetLocationUri(), other.isSetLocationUri());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetLocationUri()) {
                     lastComparison = TBaseHelper.compareTo(this.locationUri, other.locationUri);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetParameters(), other.isSetParameters());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetParameters()) {
                        lastComparison = TBaseHelper.compareTo(this.parameters, other.parameters);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

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

                        lastComparison = Boolean.compare(this.isSetOwnerName(), other.isSetOwnerName());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetOwnerName()) {
                              lastComparison = TBaseHelper.compareTo(this.ownerName, other.ownerName);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetOwnerType(), other.isSetOwnerType());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetOwnerType()) {
                                 lastComparison = TBaseHelper.compareTo(this.ownerType, other.ownerType);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetCatalogName(), other.isSetCatalogName());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetCatalogName()) {
                                    lastComparison = TBaseHelper.compareTo(this.catalogName, other.catalogName);
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
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Database._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Database(");
      boolean first = true;
      sb.append("name:");
      if (this.name == null) {
         sb.append("null");
      } else {
         sb.append(this.name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("description:");
      if (this.description == null) {
         sb.append("null");
      } else {
         sb.append(this.description);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("locationUri:");
      if (this.locationUri == null) {
         sb.append("null");
      } else {
         sb.append(this.locationUri);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("parameters:");
      if (this.parameters == null) {
         sb.append("null");
      } else {
         sb.append(this.parameters);
      }

      first = false;
      if (this.isSetPrivileges()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("privileges:");
         if (this.privileges == null) {
            sb.append("null");
         } else {
            sb.append(this.privileges);
         }

         first = false;
      }

      if (this.isSetOwnerName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ownerName:");
         if (this.ownerName == null) {
            sb.append("null");
         } else {
            sb.append(this.ownerName);
         }

         first = false;
      }

      if (this.isSetOwnerType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ownerType:");
         if (this.ownerType == null) {
            sb.append("null");
         } else {
            sb.append(this.ownerType);
         }

         first = false;
      }

      if (this.isSetCatalogName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("catalogName:");
         if (this.catalogName == null) {
            sb.append("null");
         } else {
            sb.append(this.catalogName);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.privileges != null) {
         this.privileges.validate();
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
      optionals = new _Fields[]{Database._Fields.PRIVILEGES, Database._Fields.OWNER_NAME, Database._Fields.OWNER_TYPE, Database._Fields.CATALOG_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Database._Fields.NAME, new FieldMetaData("name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Database._Fields.DESCRIPTION, new FieldMetaData("description", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Database._Fields.LOCATION_URI, new FieldMetaData("locationUri", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Database._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(Database._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)2, new StructMetaData((byte)12, PrincipalPrivilegeSet.class)));
      tmpMap.put(Database._Fields.OWNER_NAME, new FieldMetaData("ownerName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(Database._Fields.OWNER_TYPE, new FieldMetaData("ownerType", (byte)2, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(Database._Fields.CATALOG_NAME, new FieldMetaData("catalogName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Database.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAME((short)1, "name"),
      DESCRIPTION((short)2, "description"),
      LOCATION_URI((short)3, "locationUri"),
      PARAMETERS((short)4, "parameters"),
      PRIVILEGES((short)5, "privileges"),
      OWNER_NAME((short)6, "ownerName"),
      OWNER_TYPE((short)7, "ownerType"),
      CATALOG_NAME((short)8, "catalogName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAME;
            case 2:
               return DESCRIPTION;
            case 3:
               return LOCATION_URI;
            case 4:
               return PARAMETERS;
            case 5:
               return PRIVILEGES;
            case 6:
               return OWNER_NAME;
            case 7:
               return OWNER_TYPE;
            case 8:
               return CATALOG_NAME;
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

   private static class DatabaseStandardSchemeFactory implements SchemeFactory {
      private DatabaseStandardSchemeFactory() {
      }

      public DatabaseStandardScheme getScheme() {
         return new DatabaseStandardScheme();
      }
   }

   private static class DatabaseStandardScheme extends StandardScheme {
      private DatabaseStandardScheme() {
      }

      public void read(TProtocol iprot, Database struct) throws TException {
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
                     struct.name = iprot.readString();
                     struct.setNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.description = iprot.readString();
                     struct.setDescriptionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.locationUri = iprot.readString();
                     struct.setLocationUriIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map94 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map94.size);

                  for(int _i97 = 0; _i97 < _map94.size; ++_i97) {
                     String _key95 = iprot.readString();
                     String _val96 = iprot.readString();
                     struct.parameters.put(_key95, _val96);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 5:
                  if (schemeField.type == 12) {
                     struct.privileges = new PrincipalPrivilegeSet();
                     struct.privileges.read(iprot);
                     struct.setPrivilegesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.ownerName = iprot.readString();
                     struct.setOwnerNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.ownerType = PrincipalType.findByValue(iprot.readI32());
                     struct.setOwnerTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 11) {
                     struct.catalogName = iprot.readString();
                     struct.setCatalogNameIsSet(true);
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

      public void write(TProtocol oprot, Database struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Database.STRUCT_DESC);
         if (struct.name != null) {
            oprot.writeFieldBegin(Database.NAME_FIELD_DESC);
            oprot.writeString(struct.name);
            oprot.writeFieldEnd();
         }

         if (struct.description != null) {
            oprot.writeFieldBegin(Database.DESCRIPTION_FIELD_DESC);
            oprot.writeString(struct.description);
            oprot.writeFieldEnd();
         }

         if (struct.locationUri != null) {
            oprot.writeFieldBegin(Database.LOCATION_URI_FIELD_DESC);
            oprot.writeString(struct.locationUri);
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(Database.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter98 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter98.getKey());
               oprot.writeString((String)_iter98.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.privileges != null && struct.isSetPrivileges()) {
            oprot.writeFieldBegin(Database.PRIVILEGES_FIELD_DESC);
            struct.privileges.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.ownerName != null && struct.isSetOwnerName()) {
            oprot.writeFieldBegin(Database.OWNER_NAME_FIELD_DESC);
            oprot.writeString(struct.ownerName);
            oprot.writeFieldEnd();
         }

         if (struct.ownerType != null && struct.isSetOwnerType()) {
            oprot.writeFieldBegin(Database.OWNER_TYPE_FIELD_DESC);
            oprot.writeI32(struct.ownerType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.catalogName != null && struct.isSetCatalogName()) {
            oprot.writeFieldBegin(Database.CATALOG_NAME_FIELD_DESC);
            oprot.writeString(struct.catalogName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DatabaseTupleSchemeFactory implements SchemeFactory {
      private DatabaseTupleSchemeFactory() {
      }

      public DatabaseTupleScheme getScheme() {
         return new DatabaseTupleScheme();
      }
   }

   private static class DatabaseTupleScheme extends TupleScheme {
      private DatabaseTupleScheme() {
      }

      public void write(TProtocol prot, Database struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetName()) {
            optionals.set(0);
         }

         if (struct.isSetDescription()) {
            optionals.set(1);
         }

         if (struct.isSetLocationUri()) {
            optionals.set(2);
         }

         if (struct.isSetParameters()) {
            optionals.set(3);
         }

         if (struct.isSetPrivileges()) {
            optionals.set(4);
         }

         if (struct.isSetOwnerName()) {
            optionals.set(5);
         }

         if (struct.isSetOwnerType()) {
            optionals.set(6);
         }

         if (struct.isSetCatalogName()) {
            optionals.set(7);
         }

         oprot.writeBitSet(optionals, 8);
         if (struct.isSetName()) {
            oprot.writeString(struct.name);
         }

         if (struct.isSetDescription()) {
            oprot.writeString(struct.description);
         }

         if (struct.isSetLocationUri()) {
            oprot.writeString(struct.locationUri);
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter99 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter99.getKey());
               oprot.writeString((String)_iter99.getValue());
            }
         }

         if (struct.isSetPrivileges()) {
            struct.privileges.write(oprot);
         }

         if (struct.isSetOwnerName()) {
            oprot.writeString(struct.ownerName);
         }

         if (struct.isSetOwnerType()) {
            oprot.writeI32(struct.ownerType.getValue());
         }

         if (struct.isSetCatalogName()) {
            oprot.writeString(struct.catalogName);
         }

      }

      public void read(TProtocol prot, Database struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(8);
         if (incoming.get(0)) {
            struct.name = iprot.readString();
            struct.setNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.description = iprot.readString();
            struct.setDescriptionIsSet(true);
         }

         if (incoming.get(2)) {
            struct.locationUri = iprot.readString();
            struct.setLocationUriIsSet(true);
         }

         if (incoming.get(3)) {
            TMap _map100 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map100.size);

            for(int _i103 = 0; _i103 < _map100.size; ++_i103) {
               String _key101 = iprot.readString();
               String _val102 = iprot.readString();
               struct.parameters.put(_key101, _val102);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(4)) {
            struct.privileges = new PrincipalPrivilegeSet();
            struct.privileges.read(iprot);
            struct.setPrivilegesIsSet(true);
         }

         if (incoming.get(5)) {
            struct.ownerName = iprot.readString();
            struct.setOwnerNameIsSet(true);
         }

         if (incoming.get(6)) {
            struct.ownerType = PrincipalType.findByValue(iprot.readI32());
            struct.setOwnerTypeIsSet(true);
         }

         if (incoming.get(7)) {
            struct.catalogName = iprot.readString();
            struct.setCatalogNameIsSet(true);
         }

      }
   }
}
