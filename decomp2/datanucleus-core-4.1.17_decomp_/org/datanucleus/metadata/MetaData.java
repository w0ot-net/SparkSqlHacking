package org.datanucleus.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;

public class MetaData implements Serializable {
   private static final long serialVersionUID = -5477406260914096062L;
   public static final int METADATA_CREATED_STATE = 0;
   public static final int METADATA_POPULATED_STATE = 1;
   public static final int METADATA_INITIALISED_STATE = 2;
   public static final int METADATA_USED_STATE = 3;
   protected int metaDataState = 0;
   protected MetaData parent;
   public static final String VENDOR_NAME = "datanucleus";
   public static final String EXTENSION_CLASS_READ_ONLY = "read-only";
   public static final String EXTENSION_CLASS_MULTITENANCY_DISABLE = "multitenancy-disable";
   public static final String EXTENSION_CLASS_MULTITENANCY_COLUMN_NAME = "multitenancy-column-name";
   public static final String EXTENSION_CLASS_MULTITENANCY_COLUMN_LENGTH = "multitenancy-column-length";
   public static final String EXTENSION_CLASS_MULTITENANCY_JDBC_TYPE = "multitenancy-jdbc-type";
   public static final String EXTENSION_CLASS_VERSION_FIELD_NAME = "field-name";
   public static final String EXTENSION_MEMBER_TYPE_CONVERTER_NAME = "type-converter-name";
   public static final String EXTENSION_MEMBER_TYPE_CONVERTER_DISABLED = "type-converter-disabled";
   public static final String EXTENSION_MEMBER_COMPARATOR_NAME = "comparator-name";
   public static final String EXTENSION_MEMBER_IMPLEMENTATION_CLASSES = "implementation-classes";
   public static final String EXTENSION_MEMBER_ENUM_VALUE_GETTER = "enum-value-getter";
   public static final String EXTENSION_MEMBER_ENUM_GETTER_BY_VALUE = "enum-getter-by-value";
   public static final String EXTENSION_MEMBER_CALENDAR_ONE_COLUMN = "calendar-one-column";
   public static final String EXTENSION_MEMBER_INSERTABLE = "insertable";
   public static final String EXTENSION_MEMBER_UPDATEABLE = "updateable";
   public static final String EXTENSION_MEMBER_CASCADE_PERSIST = "cascade-persist";
   public static final String EXTENSION_MEMBER_CASCADE_UPDATE = "cascade-update";
   public static final String EXTENSION_MEMBER_CASCADE_REFRESH = "cascade-refresh";
   public static final String EXTENSION_MEMBER_CACHEABLE = "cacheable";
   public static final String EXTENSION_MEMBER_FETCH_FK_ONLY = "fetch-fk-only";
   public static final String EXTENSION_MEMBER_CONTAINER_ALLOW_NULLS = "allow-nulls";
   public static final String EXTENSION_MEMBER_LIST_ORDERING = "list-ordering";
   public static final String EXTENSION_MEMBER_STRATEGY_WHEN_NOTNULL = "strategy-when-notnull";
   protected Collection extensions = null;

   public MetaData() {
   }

   public MetaData(MetaData parent) {
      this.parent = parent;
   }

   public MetaData(MetaData parent, MetaData copy) {
      this.parent = parent;
      if (copy != null && copy.extensions != null) {
         for(ExtensionMetaData extmd : copy.extensions) {
            this.addExtension(extmd.getVendorName(), extmd.getKey(), extmd.getValue());
         }
      }

   }

   public void initialise(ClassLoaderResolver clr, MetaDataManager mmgr) {
      this.setInitialised();
   }

   void setInitialised() {
      this.metaDataState = 2;
   }

   void setPopulated() {
      this.metaDataState = 1;
   }

   void setUsed() {
      this.metaDataState = 3;
   }

   public void setParent(MetaData md) {
      if (!this.isPopulated() && !this.isInitialised()) {
         this.parent = md;
      } else {
         throw new NucleusException("Cannot set parent of " + this + " since it is already populated/initialised");
      }
   }

   public MetaData addExtension(String vendor, String key, String value) {
      if (vendor != null && (!vendor.equalsIgnoreCase("datanucleus") || key != null && value != null)) {
         if (vendor.equalsIgnoreCase("datanucleus") && this.hasExtension(key)) {
            this.removeExtension(key);
         }

         if (this.extensions == null) {
            this.extensions = new HashSet(2);
         }

         this.extensions.add(new ExtensionMetaData(vendor, key, value));
         return this;
      } else {
         throw new InvalidMetaDataException("044160", new Object[]{vendor, key, value});
      }
   }

   public MetaData addExtension(String key, String value) {
      return this.addExtension("datanucleus", key, value);
   }

   public ExtensionMetaData newExtensionMetaData(String vendor, String key, String value) {
      if (vendor != null && (!vendor.equalsIgnoreCase("datanucleus") || key != null && value != null)) {
         ExtensionMetaData extmd = new ExtensionMetaData(vendor, key, value);
         if (this.extensions == null) {
            this.extensions = new HashSet(2);
         }

         this.extensions.add(extmd);
         return extmd;
      } else {
         throw new InvalidMetaDataException("044160", new Object[]{vendor, key, value});
      }
   }

   public MetaData removeExtension(String key) {
      if (this.extensions == null) {
         return this;
      } else {
         Iterator iter = this.extensions.iterator();

         while(iter.hasNext()) {
            ExtensionMetaData ex = (ExtensionMetaData)iter.next();
            if (ex.getKey().equals(key) && ex.getVendorName().equalsIgnoreCase("datanucleus")) {
               iter.remove();
               break;
            }
         }

         return this;
      }
   }

   public MetaData getParent() {
      return this.parent;
   }

   public boolean isPopulated() {
      return this.metaDataState >= 1;
   }

   public boolean isInitialised() {
      return this.metaDataState >= 2;
   }

   public boolean isUsed() {
      return this.metaDataState == 3;
   }

   public int getNoOfExtensions() {
      return this.extensions != null ? this.extensions.size() : 0;
   }

   public ExtensionMetaData[] getExtensions() {
      return this.extensions != null && this.extensions.size() != 0 ? (ExtensionMetaData[])this.extensions.toArray(new ExtensionMetaData[this.extensions.size()]) : null;
   }

   public boolean hasExtension(String key) {
      if (this.extensions != null && key != null) {
         for(ExtensionMetaData ex : this.extensions) {
            if (ex.getKey().equals(key) && ex.getVendorName().equalsIgnoreCase("datanucleus")) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public String getValueForExtension(String key) {
      if (this.extensions != null && key != null) {
         for(ExtensionMetaData ex : this.extensions) {
            if (ex.getKey().equals(key) && ex.getVendorName().equalsIgnoreCase("datanucleus")) {
               return ex.getValue();
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public String[] getValuesForExtension(String key) {
      if (this.extensions != null && key != null) {
         for(ExtensionMetaData ex : this.extensions) {
            if (ex.getKey().equals(key) && ex.getVendorName().equalsIgnoreCase("datanucleus")) {
               return MetaDataUtils.getInstance().getValuesForCommaSeparatedAttribute(ex.getValue());
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public String toString() {
      return this.toString("", "");
   }

   public String toString(String prefix, String indent) {
      if (this.extensions != null && this.extensions.size() != 0) {
         StringBuilder sb = new StringBuilder();

         for(ExtensionMetaData ex : this.extensions) {
            sb.append(prefix).append(ex.toString()).append("\n");
         }

         return sb.toString();
      } else {
         return "";
      }
   }
}
