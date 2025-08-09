package org.datanucleus.store.rdbms.mapping;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ColumnMetaDataContainer;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.NullValue;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMappingFactory;
import org.datanucleus.store.rdbms.mapping.java.ArrayMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.InterfaceMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ObjectMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedLocalFileMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMultiMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.store.types.converters.MultiColumnConverter;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MultiMap;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class RDBMSMappingManager implements MappingManager {
   protected final RDBMSStoreManager storeMgr;
   protected final ClassLoaderResolver clr;
   protected MultiMap datastoreMappingsByJavaType;
   protected MultiMap datastoreMappingsByJDBCType;
   protected MultiMap datastoreMappingsBySQLType;

   public RDBMSMappingManager(RDBMSStoreManager storeMgr) {
      this.storeMgr = storeMgr;
      this.clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
   }

   public String getDefaultSqlTypeForJavaType(String javaType, String jdbcType) {
      if (javaType != null && jdbcType != null) {
         Collection coll = (Collection)this.datastoreMappingsByJavaType.get(javaType);
         if (coll != null && coll.size() > 0) {
            String sqlType = null;

            for(RDBMSTypeMapping typeMapping : coll) {
               if (typeMapping.jdbcType.equalsIgnoreCase(jdbcType)) {
                  sqlType = typeMapping.sqlType;
                  if (typeMapping.isDefault) {
                     return sqlType;
                  }
               }
            }

            return sqlType;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void loadDatastoreMapping(PluginManager mgr, ClassLoaderResolver clr, String vendorId) {
      if (this.datastoreMappingsByJavaType == null) {
         this.datastoreMappingsByJDBCType = new MultiMap();
         this.datastoreMappingsBySQLType = new MultiMap();
         this.datastoreMappingsByJavaType = new MultiMap();
         ConfigurationElement[] elems = mgr.getConfigurationElementsForExtension("org.datanucleus.store.rdbms.datastore_mapping", (String)null, (String)null);
         if (elems != null) {
            for(int i = 0; i < elems.length; ++i) {
               String javaName = elems[i].getAttribute("java-type").trim();
               String rdbmsMappingClassName = elems[i].getAttribute("rdbms-mapping-class");
               String jdbcType = elems[i].getAttribute("jdbc-type");
               String sqlType = elems[i].getAttribute("sql-type");
               String defaultJava = elems[i].getAttribute("default");
               boolean defaultForJavaType = false;
               if (defaultJava != null && defaultJava.equalsIgnoreCase("true")) {
                  defaultForJavaType = Boolean.TRUE;
               }

               Class mappingType = null;
               if (!StringUtils.isWhitespace(rdbmsMappingClassName)) {
                  try {
                     mappingType = mgr.loadClass(elems[i].getExtension().getPlugin().getSymbolicName(), rdbmsMappingClassName);
                  } catch (NucleusException var17) {
                     NucleusLogger.DATASTORE.error(Localiser.msg("041013", new Object[]{rdbmsMappingClassName}));
                  }

                  Set includes = new HashSet();
                  Set excludes = new HashSet();
                  ConfigurationElement[] childElm = elems[i].getChildren();

                  for(int j = 0; j < childElm.length; ++j) {
                     if (childElm[j].getName().equals("includes")) {
                        includes.add(childElm[j].getAttribute("vendor-id"));
                     } else if (childElm[j].getName().equals("excludes")) {
                        excludes.add(childElm[j].getAttribute("vendor-id"));
                     }
                  }

                  if (!excludes.contains(vendorId) && (includes.isEmpty() || includes.contains(vendorId))) {
                     this.registerDatastoreMapping(javaName, mappingType, jdbcType, sqlType, defaultForJavaType);
                  }
               }
            }
         }

      }
   }

   public JavaTypeMapping getMappingWithDatastoreMapping(Class javaType, boolean serialised, boolean embedded, ClassLoaderResolver clr) {
      try {
         DatastoreClass datastoreClass = this.storeMgr.getDatastoreClass(javaType.getName(), clr);
         return datastoreClass.getIdMapping();
      } catch (NoTableManagedException var11) {
         MappingConverterDetails mcd = this.getMappingClass(javaType, serialised, embedded, (ColumnMetaData[])null, (String)null);
         Class mc = mcd.mappingClass;
         mc = this.getOverrideMappingClass(mc, (AbstractMemberMetaData)null, (FieldRole)null);

         try {
            JavaTypeMapping m = (JavaTypeMapping)mc.newInstance();
            m.initialize(this.storeMgr, javaType.getName());
            if (m.hasSimpleDatastoreRepresentation()) {
               this.createDatastoreMapping(m, (Column)null, m.getJavaTypeForDatastoreMapping(0));
            }

            return m;
         } catch (NucleusUserException nue) {
            throw nue;
         } catch (Exception e) {
            throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
         }
      }
   }

   public JavaTypeMapping getMapping(Class javaType) {
      return this.getMapping(javaType, false, false, (String)null);
   }

   public JavaTypeMapping getMapping(Class javaType, boolean serialised, boolean embedded, String fieldName) {
      MappingConverterDetails mcd = this.getMappingClass(javaType, serialised, embedded, (ColumnMetaData[])null, fieldName);
      Class mc = mcd.mappingClass;
      mc = this.getOverrideMappingClass(mc, (AbstractMemberMetaData)null, (FieldRole)null);

      try {
         JavaTypeMapping m = (JavaTypeMapping)mc.newInstance();
         m.initialize(this.storeMgr, javaType.getName());
         return m;
      } catch (Exception e) {
         throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
      }
   }

   public JavaTypeMapping getMapping(Table table, AbstractMemberMetaData mmd, ClassLoaderResolver clr, FieldRole fieldRole) {
      if (fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT && fieldRole != FieldRole.ROLE_ARRAY_ELEMENT) {
         if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            return this.getKeyMapping(table, mmd, clr);
         } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
            return this.getValueMapping(table, mmd, clr);
         } else {
            TypeManager typeMgr = table.getStoreManager().getNucleusContext().getTypeManager();
            TypeConverter conv = null;
            if (!mmd.isTypeConversionDisabled()) {
               if (mmd.getTypeConverterName() != null) {
                  conv = typeMgr.getTypeConverterForName(mmd.getTypeConverterName());
                  if (conv == null) {
                     throw new NucleusUserException(Localiser.msg("044062", new Object[]{mmd.getFullFieldName(), mmd.getTypeConverterName()}));
                  }
               } else {
                  TypeConverter autoApplyConv = typeMgr.getAutoApplyTypeConverterForType(mmd.getType());
                  if (autoApplyConv != null) {
                     conv = autoApplyConv;
                  }
               }

               if (conv != null) {
                  JavaTypeMapping m = null;
                  if (conv instanceof MultiColumnConverter) {
                     Class mc = TypeConverterMultiMapping.class;

                     try {
                        m = (JavaTypeMapping)mc.newInstance();
                        m.setRoleForMember(FieldRole.ROLE_FIELD);
                        ((TypeConverterMultiMapping)m).initialize(mmd, table, clr, conv);
                        return m;
                     } catch (Exception e) {
                        throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
                     }
                  }

                  Class mc = TypeConverterMapping.class;

                  try {
                     m = (JavaTypeMapping)mc.newInstance();
                     m.setRoleForMember(FieldRole.ROLE_FIELD);
                     ((TypeConverterMapping)m).initialize(mmd, table, clr, conv);
                     return m;
                  } catch (Exception e) {
                     throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
                  }
               }
            }

            AbstractMemberMetaData overrideMmd = null;
            MappingConverterDetails mcd = null;
            Class mc = null;
            String userMappingClassName = mmd.getValueForExtension("mapping-class");
            if (userMappingClassName != null) {
               try {
                  mc = clr.classForName(userMappingClassName);
               } catch (NucleusException var18) {
                  throw (new NucleusUserException(Localiser.msg("041014", new Object[]{mmd.getFullFieldName(), userMappingClassName}))).setFatal();
               }
            } else {
               AbstractClassMetaData typeCmd = null;
               Object typeCmd;
               if (mmd.getType().isInterface()) {
                  typeCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForInterface(mmd.getType(), clr);
               } else {
                  typeCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(mmd.getType(), clr);
               }

               if (mmd.hasExtension("serializeToFileLocation") && Serializable.class.isAssignableFrom(mmd.getType())) {
                  mc = SerialisedLocalFileMapping.class;
               } else if (mmd.isSerialized()) {
                  mcd = this.getMappingClass(mmd.getType(), true, false, (ColumnMetaData[])null, mmd.getFullFieldName());
               } else if (mmd.getEmbeddedMetaData() != null) {
                  mcd = this.getMappingClass(mmd.getType(), false, true, (ColumnMetaData[])null, mmd.getFullFieldName());
               } else if (typeCmd != null && ((AbstractClassMetaData)typeCmd).isEmbeddedOnly()) {
                  mcd = this.getMappingClass(mmd.getType(), false, true, (ColumnMetaData[])null, mmd.getFullFieldName());
               } else if (mmd.isEmbedded()) {
                  mcd = this.getMappingClass(mmd.getType(), true, false, mmd.getColumnMetaData(), mmd.getFullFieldName());
               } else {
                  Class memberType = mmd.getType();
                  mcd = this.getMappingClass(memberType, false, false, mmd.getColumnMetaData(), mmd.getFullFieldName());
                  if (mmd.getParent() instanceof EmbeddedMetaData && mmd.getRelationType(clr) != RelationType.NONE) {
                     AbstractClassMetaData cmdForFmd = table.getStoreManager().getMetaDataManager().getMetaDataForClass(mmd.getClassName(), clr);
                     overrideMmd = cmdForFmd.getMetaDataForMember(mmd.getName());
                  }
               }
            }

            if (mcd != null) {
               mc = mcd.mappingClass;
               mc = this.getOverrideMappingClass(mc, mmd, fieldRole);
            }

            if (mc == null || mcd != null && mcd.typeConverter != null) {
               if (mcd != null && mcd.typeConverter != null) {
                  try {
                     JavaTypeMapping m = (JavaTypeMapping)mcd.mappingClass.newInstance();
                     m.setRoleForMember(FieldRole.ROLE_FIELD);
                     if (m instanceof TypeConverterMapping) {
                        ((TypeConverterMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                     } else if (m instanceof TypeConverterMultiMapping) {
                        ((TypeConverterMultiMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                     }

                     if (overrideMmd != null) {
                        m.setMemberMetaData(overrideMmd);
                     }

                     return m;
                  } catch (Exception e) {
                     throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
                  }
               } else {
                  throw new NucleusException("Unable to create mapping for member at " + mmd.getFullFieldName() + " - no available mapping");
               }
            } else {
               try {
                  JavaTypeMapping m = (JavaTypeMapping)mc.newInstance();
                  m.setRoleForMember(FieldRole.ROLE_FIELD);
                  m.initialize(mmd, table, clr);
                  if (overrideMmd != null) {
                     m.setMemberMetaData(overrideMmd);
                  }

                  return m;
               } catch (Exception e) {
                  throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
               }
            }
         }
      } else {
         return this.getElementMapping(table, mmd, fieldRole, clr);
      }
   }

   protected MappingConverterDetails getMappingClass(Class javaType, boolean serialised, boolean embedded, ColumnMetaData[] colmds, String fieldName) {
      ApiAdapter api = this.storeMgr.getApiAdapter();
      if (api.isPersistable(javaType)) {
         if (serialised) {
            return new MappingConverterDetails(SerialisedPCMapping.class);
         } else {
            return embedded ? new MappingConverterDetails(EmbeddedPCMapping.class) : new MappingConverterDetails(PersistableMapping.class);
         }
      } else if (javaType.isInterface() && !this.storeMgr.getMappedTypeManager().isSupportedMappedType(javaType.getName())) {
         if (serialised) {
            return new MappingConverterDetails(SerialisedReferenceMapping.class);
         } else {
            return embedded ? new MappingConverterDetails(EmbeddedPCMapping.class) : new MappingConverterDetails(InterfaceMapping.class);
         }
      } else if (javaType == Object.class) {
         if (serialised) {
            return new MappingConverterDetails(SerialisedReferenceMapping.class);
         } else if (embedded) {
            throw (new NucleusUserException(Localiser.msg("041042", new Object[]{fieldName}))).setFatal();
         } else {
            return new MappingConverterDetails(ObjectMapping.class);
         }
      } else {
         if (javaType.isArray()) {
            if (api.isPersistable(javaType.getComponentType())) {
               return new MappingConverterDetails(ArrayMapping.class);
            }

            if (javaType.getComponentType().isInterface() && !this.storeMgr.getMappedTypeManager().isSupportedMappedType(javaType.getComponentType().getName())) {
               return new MappingConverterDetails(ArrayMapping.class);
            }

            if (javaType.getComponentType() == Object.class) {
               return new MappingConverterDetails(ArrayMapping.class);
            }
         }

         MappingConverterDetails mcd = this.getDefaultJavaTypeMapping(javaType, colmds);
         if (mcd == null || mcd.mappingClass == null) {
            for(Class superClass = javaType.getSuperclass(); superClass != null && !superClass.getName().equals(ClassNameConstants.Object) && (mcd == null || mcd.mappingClass == null); superClass = superClass.getSuperclass()) {
               mcd = this.getDefaultJavaTypeMapping(superClass, colmds);
            }
         }

         if (mcd == null) {
            if (this.storeMgr.getMappedTypeManager().isSupportedMappedType(javaType.getName())) {
               throw new NucleusUserException(Localiser.msg("041001", new Object[]{fieldName, javaType.getName()}));
            }

            for(Class superClass = javaType; superClass != null && !superClass.getName().equals(ClassNameConstants.Object) && (mcd == null || mcd.mappingClass == null); superClass = superClass.getSuperclass()) {
               Class[] interfaces = superClass.getInterfaces();

               for(int i = 0; i < interfaces.length && (mcd == null || mcd.mappingClass == null); ++i) {
                  mcd = this.getDefaultJavaTypeMapping(interfaces[i], colmds);
               }
            }

            if (mcd == null) {
               return new MappingConverterDetails(SerialisedMapping.class);
            }
         }

         return mcd;
      }
   }

   protected JavaTypeMapping getElementMapping(Table table, AbstractMemberMetaData mmd, FieldRole fieldRole, ClassLoaderResolver clr) {
      if (!mmd.hasCollection() && !mmd.hasArray()) {
         throw (new NucleusException("Attempt to get element mapping for field " + mmd.getFullFieldName() + " that has no collection/array!")).setFatal();
      } else {
         if (mmd.getJoinMetaData() == null) {
            AbstractMemberMetaData[] refMmds = mmd.getRelatedMemberMetaData(clr);
            if (refMmds == null || refMmds.length == 0) {
               throw (new NucleusException("Attempt to get element mapping for field " + mmd.getFullFieldName() + " that has no join table defined for the collection/array")).setFatal();
            }

            if (refMmds[0].getJoinMetaData() == null) {
               throw (new NucleusException("Attempt to get element mapping for field " + mmd.getFullFieldName() + " that has no join table defined for the collection/array")).setFatal();
            }
         }

         MappingConverterDetails mcd = null;
         Class mc = null;
         String userMappingClassName = null;
         String userTypeConverterName = null;
         if (mmd.getElementMetaData() != null) {
            userTypeConverterName = mmd.getElementMetaData().getValueForExtension("type-converter-name");
            userMappingClassName = mmd.getElementMetaData().getValueForExtension("mapping-class");
         }

         if (userTypeConverterName != null) {
            TypeConverter conv = this.storeMgr.getNucleusContext().getTypeManager().getTypeConverterForName(userTypeConverterName);
            if (conv == null) {
               throw new NucleusUserException("Field " + mmd.getFullFieldName() + " ELEMENT has been specified to use type converter " + userTypeConverterName + " but not found!");
            }

            mcd = new MappingConverterDetails(TypeConverterMapping.class, conv);
         } else if (userMappingClassName != null) {
            try {
               mc = clr.classForName(userMappingClassName);
            } catch (NucleusException var17) {
               throw (new NucleusUserException(Localiser.msg("041014", new Object[]{userMappingClassName}))).setFatal();
            }
         } else {
            boolean serialised = mmd.hasCollection() && mmd.getCollection().isSerializedElement() || mmd.hasArray() && mmd.getArray().isSerializedElement();
            boolean embeddedPC = mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null;
            boolean elementPC = mmd.hasCollection() && mmd.getCollection().elementIsPersistent() || mmd.hasArray() && mmd.getArray().elementIsPersistent();
            boolean embedded = true;
            if (mmd.hasCollection()) {
               embedded = mmd.getCollection().isEmbeddedElement();
            } else if (mmd.hasArray()) {
               embedded = mmd.getArray().isEmbeddedElement();
            }

            Class elementCls = null;
            if (mmd.hasCollection()) {
               elementCls = clr.classForName(mmd.getCollection().getElementType());
            } else if (mmd.hasArray()) {
               elementCls = mmd.getType().getComponentType();
            }

            boolean elementReference = ClassUtils.isReferenceType(elementCls);
            if (serialised) {
               if (elementPC) {
                  mc = SerialisedElementPCMapping.class;
               } else if (elementReference) {
                  mc = SerialisedReferenceMapping.class;
               } else {
                  mc = SerialisedMapping.class;
               }
            } else if (embedded) {
               if (embeddedPC) {
                  mc = EmbeddedElementPCMapping.class;
               } else if (elementPC) {
                  mc = PersistableMapping.class;
               } else {
                  mcd = this.getMappingClass(elementCls, serialised, embedded, mmd.getElementMetaData() != null ? mmd.getElementMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
               }
            } else {
               mcd = this.getMappingClass(elementCls, serialised, embedded, mmd.getElementMetaData() != null ? mmd.getElementMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
            }
         }

         if (mcd != null && mcd.typeConverter == null) {
            mc = mcd.mappingClass;
         }

         if (mc == null || mcd != null && mcd.typeConverter != null) {
            if (mcd != null && mcd.typeConverter != null) {
               try {
                  JavaTypeMapping m = (JavaTypeMapping)mcd.mappingClass.newInstance();
                  m.setRoleForMember(fieldRole);
                  if (m instanceof TypeConverterMapping) {
                     ((TypeConverterMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  } else if (m instanceof TypeConverterMultiMapping) {
                     ((TypeConverterMultiMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  }

                  return m;
               } catch (Exception e) {
                  throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
               }
            } else {
               throw new NucleusException("Unable to create mapping for element of collection/array at " + mmd.getFullFieldName() + " - no available mapping");
            }
         } else {
            JavaTypeMapping m = null;

            try {
               m = (JavaTypeMapping)mc.newInstance();
               m.setRoleForMember(fieldRole);
               m.initialize(mmd, table, clr);
               return m;
            } catch (Exception e) {
               throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
            }
         }
      }
   }

   protected JavaTypeMapping getKeyMapping(Table table, AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      if (mmd.getMap() == null) {
         throw (new NucleusException("Attempt to get key mapping for field " + mmd.getFullFieldName() + " that has no map!")).setFatal();
      } else {
         MappingConverterDetails mcd = null;
         Class mc = null;
         String userTypeConverterName = null;
         String userMappingClassName = null;
         if (mmd.getKeyMetaData() != null) {
            userTypeConverterName = mmd.getKeyMetaData().getValueForExtension("type-converter-name");
            userMappingClassName = mmd.getKeyMetaData().getValueForExtension("mapping-class");
         }

         if (userTypeConverterName != null) {
            TypeConverter conv = this.storeMgr.getNucleusContext().getTypeManager().getTypeConverterForName(userTypeConverterName);
            if (conv == null) {
               throw new NucleusUserException("Field " + mmd.getFullFieldName() + " KEY has been specified to use type converter " + userTypeConverterName + " but not found!");
            }

            mcd = new MappingConverterDetails(TypeConverterMapping.class, conv);
         } else if (userMappingClassName != null) {
            try {
               mc = clr.classForName(userMappingClassName);
            } catch (NucleusException var16) {
               throw (new NucleusUserException(Localiser.msg("041014", new Object[]{userMappingClassName}))).setFatal();
            }
         } else {
            boolean serialised = mmd.hasMap() && mmd.getMap().isSerializedKey();
            boolean embedded = mmd.hasMap() && mmd.getMap().isEmbeddedKey();
            boolean embeddedPC = mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getEmbeddedMetaData() != null;
            boolean keyPC = mmd.hasMap() && mmd.getMap().keyIsPersistent();
            Class keyCls = clr.classForName(mmd.getMap().getKeyType());
            boolean keyReference = ClassUtils.isReferenceType(keyCls);
            if (serialised) {
               if (keyPC) {
                  mc = SerialisedKeyPCMapping.class;
               } else if (keyReference) {
                  mc = SerialisedReferenceMapping.class;
               } else {
                  mc = SerialisedMapping.class;
               }
            } else if (embedded) {
               if (embeddedPC) {
                  mc = EmbeddedKeyPCMapping.class;
               } else if (keyPC) {
                  mc = PersistableMapping.class;
               } else {
                  mcd = this.getMappingClass(keyCls, serialised, embedded, mmd.getKeyMetaData() != null ? mmd.getKeyMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
               }
            } else {
               mcd = this.getMappingClass(keyCls, serialised, embedded, mmd.getKeyMetaData() != null ? mmd.getKeyMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
            }
         }

         if (mcd != null && mcd.typeConverter == null) {
            mc = mcd.mappingClass;
         }

         if (mc == null || mcd != null && mcd.typeConverter != null) {
            if (mcd != null && mcd.typeConverter != null) {
               try {
                  JavaTypeMapping m = (JavaTypeMapping)mcd.mappingClass.newInstance();
                  m.setRoleForMember(FieldRole.ROLE_MAP_KEY);
                  if (m instanceof TypeConverterMapping) {
                     ((TypeConverterMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  } else if (m instanceof TypeConverterMultiMapping) {
                     ((TypeConverterMultiMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  }

                  return m;
               } catch (Exception e) {
                  throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
               }
            } else {
               throw new NucleusException("Unable to create mapping for key of map at " + mmd.getFullFieldName() + " - no available mapping");
            }
         } else {
            JavaTypeMapping m = null;

            try {
               m = (JavaTypeMapping)mc.newInstance();
               m.setRoleForMember(FieldRole.ROLE_MAP_KEY);
               m.initialize(mmd, table, clr);
               return m;
            } catch (Exception e) {
               throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
            }
         }
      }
   }

   protected JavaTypeMapping getValueMapping(Table table, AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      if (mmd.getMap() == null) {
         throw (new NucleusException("Attempt to get value mapping for field " + mmd.getFullFieldName() + " that has no map!")).setFatal();
      } else {
         MappingConverterDetails mcd = null;
         Class mc = null;
         String userTypeConverterName = null;
         String userMappingClassName = null;
         if (mmd.getValueMetaData() != null) {
            userTypeConverterName = mmd.getValueMetaData().getValueForExtension("type-converter-name");
            userMappingClassName = mmd.getValueMetaData().getValueForExtension("mapping-class");
         }

         if (userTypeConverterName != null) {
            TypeConverter conv = this.storeMgr.getNucleusContext().getTypeManager().getTypeConverterForName(userTypeConverterName);
            if (conv == null) {
               throw new NucleusUserException("Field " + mmd.getFullFieldName() + " VALUE has been specified to use type converter " + userTypeConverterName + " but not found!");
            }

            mcd = new MappingConverterDetails(TypeConverterMapping.class, conv);
         } else if (userMappingClassName != null) {
            try {
               mc = clr.classForName(userMappingClassName);
            } catch (NucleusException var16) {
               throw (new NucleusUserException(Localiser.msg("041014", new Object[]{userMappingClassName}))).setFatal();
            }
         } else {
            boolean serialised = mmd.hasMap() && mmd.getMap().isSerializedValue();
            boolean embedded = mmd.hasMap() && mmd.getMap().isEmbeddedValue();
            boolean embeddedPC = mmd.getValueMetaData() != null && mmd.getValueMetaData().getEmbeddedMetaData() != null;
            boolean valuePC = mmd.hasMap() && mmd.getMap().valueIsPersistent();
            Class valueCls = clr.classForName(mmd.getMap().getValueType());
            boolean valueReference = ClassUtils.isReferenceType(valueCls);
            if (serialised) {
               if (valuePC) {
                  mc = SerialisedValuePCMapping.class;
               } else if (valueReference) {
                  mc = SerialisedReferenceMapping.class;
               } else {
                  mc = SerialisedMapping.class;
               }
            } else if (embedded) {
               if (embeddedPC) {
                  mc = EmbeddedValuePCMapping.class;
               } else if (valuePC) {
                  mc = PersistableMapping.class;
               } else {
                  mcd = this.getMappingClass(valueCls, serialised, embedded, mmd.getValueMetaData() != null ? mmd.getValueMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
               }
            } else {
               mcd = this.getMappingClass(valueCls, serialised, embedded, mmd.getValueMetaData() != null ? mmd.getValueMetaData().getColumnMetaData() : null, mmd.getFullFieldName());
            }
         }

         if (mcd != null && mcd.typeConverter == null) {
            mc = mcd.mappingClass;
         }

         if (mc == null || mcd != null && mcd.typeConverter != null) {
            if (mcd != null && mcd.typeConverter != null) {
               try {
                  JavaTypeMapping m = (JavaTypeMapping)mcd.mappingClass.newInstance();
                  m.setRoleForMember(FieldRole.ROLE_MAP_VALUE);
                  if (m instanceof TypeConverterMapping) {
                     ((TypeConverterMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  } else if (m instanceof TypeConverterMultiMapping) {
                     ((TypeConverterMultiMapping)m).initialize(mmd, table, clr, mcd.typeConverter);
                  }

                  return m;
               } catch (Exception e) {
                  throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
               }
            } else {
               throw new NucleusException("Unable to create mapping for value of map at " + mmd.getFullFieldName() + " - no available mapping");
            }
         } else {
            JavaTypeMapping m = null;

            try {
               m = (JavaTypeMapping)mc.newInstance();
               m.setRoleForMember(FieldRole.ROLE_MAP_VALUE);
               m.initialize(mmd, table, clr);
               return m;
            } catch (Exception e) {
               throw (new NucleusException(Localiser.msg("041009", new Object[]{mc.getName(), e}), e)).setFatal();
            }
         }
      }
   }

   protected MappingConverterDetails getDefaultJavaTypeMapping(Class javaType, ColumnMetaData[] colmds) {
      Class cls = this.storeMgr.getMappedTypeManager().getMappingType(javaType.getName());
      if (cls != null) {
         return new MappingConverterDetails(cls);
      } else {
         TypeManager typeMgr = this.storeMgr.getNucleusContext().getTypeManager();
         if (colmds != null && colmds.length > 0) {
            if (colmds.length > 1) {
               Collection<TypeConverter> converters = typeMgr.getTypeConvertersForType(javaType);
               if (converters != null && !converters.isEmpty()) {
                  for(TypeConverter conv : converters) {
                     if (conv instanceof MultiColumnConverter && ((MultiColumnConverter)conv).getDatastoreColumnTypes().length == colmds.length) {
                        return new MappingConverterDetails(TypeConverterMultiMapping.class, conv);
                     }
                  }
               }
            } else {
               JdbcType jdbcType = colmds[0].getJdbcType();
               if (jdbcType != null) {
                  TypeConverter conv = null;
                  if (MetaDataUtils.isJdbcTypeString(jdbcType)) {
                     conv = typeMgr.getTypeConverterForType(javaType, String.class);
                  } else if (MetaDataUtils.isJdbcTypeNumeric(jdbcType)) {
                     conv = typeMgr.getTypeConverterForType(javaType, Long.class);
                  } else if (jdbcType == JdbcType.TIMESTAMP) {
                     conv = typeMgr.getTypeConverterForType(javaType, Timestamp.class);
                  } else if (jdbcType == JdbcType.TIME) {
                     conv = typeMgr.getTypeConverterForType(javaType, Time.class);
                  } else if (jdbcType == JdbcType.DATE) {
                     conv = typeMgr.getTypeConverterForType(javaType, Date.class);
                  }

                  if (conv != null) {
                     return new MappingConverterDetails(TypeConverterMapping.class, conv);
                  }
               }
            }
         }

         TypeConverter conv = typeMgr.getDefaultTypeConverterForType(javaType);
         if (conv != null) {
            return conv instanceof MultiColumnConverter ? new MappingConverterDetails(TypeConverterMultiMapping.class, conv) : new MappingConverterDetails(TypeConverterMapping.class, conv);
         } else {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("041000", new Object[]{javaType.getName()}));
            return null;
         }
      }
   }

   public void registerDatastoreMapping(String javaTypeName, Class datastoreMappingType, String jdbcType, String sqlType, boolean dflt) {
      boolean mappingRequired = true;
      Collection coll = (Collection)this.datastoreMappingsByJavaType.get(javaTypeName);
      if (coll != null && coll.size() > 0) {
         for(RDBMSTypeMapping typeMapping : coll) {
            if (typeMapping.jdbcType.equals(jdbcType) && typeMapping.sqlType.equals(sqlType)) {
               mappingRequired = false;
               if (typeMapping.isDefault() != dflt) {
                  typeMapping.setDefault(dflt);
               }
            } else if (dflt) {
               typeMapping.setDefault(false);
            }
         }
      }

      if (mappingRequired) {
         RDBMSTypeMapping mapping = new RDBMSTypeMapping(datastoreMappingType, dflt, javaTypeName, jdbcType, sqlType);
         this.datastoreMappingsByJDBCType.put(jdbcType, mapping);
         this.datastoreMappingsBySQLType.put(sqlType, mapping);
         this.datastoreMappingsByJavaType.put(javaTypeName, mapping);
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("054009", new Object[]{javaTypeName, jdbcType, sqlType, datastoreMappingType.getName(), "" + dflt}));
         }
      }

   }

   public void deregisterDatastoreMappingsForJDBCType(String jdbcTypeName) {
      Collection coll = (Collection)this.datastoreMappingsByJDBCType.get(jdbcTypeName);
      if (coll != null && coll.size() != 0) {
         for(RDBMSTypeMapping mapping : new HashSet(coll)) {
            this.datastoreMappingsByJavaType.removeKeyValue(mapping.javaType, mapping);
            this.datastoreMappingsBySQLType.removeKeyValue(mapping.sqlType, mapping);
            this.datastoreMappingsByJDBCType.removeKeyValue(mapping.jdbcType, mapping);
            if (NucleusLogger.DATASTORE.isDebugEnabled()) {
               NucleusLogger.DATASTORE.debug(Localiser.msg("054010", new Object[]{mapping.javaType, mapping.jdbcType, mapping.sqlType}));
            }
         }

      }
   }

   protected Class getOverrideMappingClass(Class mappingClass, AbstractMemberMetaData mmd, FieldRole fieldRole) {
      return mappingClass;
   }

   protected Class getDatastoreMappingClass(String fieldName, String javaType, String jdbcType, String sqlType, ClassLoaderResolver clr) {
      if (javaType == null) {
         return null;
      } else {
         javaType = ClassUtils.getWrapperTypeNameForPrimitiveTypeName(javaType);
         RDBMSTypeMapping datastoreMapping = null;
         if (sqlType != null) {
            if (this.datastoreMappingsBySQLType.get(sqlType.toUpperCase()) == null) {
               if (jdbcType == null) {
                  if (fieldName != null) {
                     throw (new NucleusException(Localiser.msg("054001", new Object[]{javaType, sqlType, fieldName}))).setFatal();
                  }

                  throw (new NucleusException(Localiser.msg("054000", new Object[]{javaType, sqlType}))).setFatal();
               }

               if (fieldName != null) {
                  NucleusLogger.DATASTORE_SCHEMA.info(Localiser.msg("054012", new Object[]{javaType, sqlType, fieldName, jdbcType}));
               } else {
                  NucleusLogger.DATASTORE_SCHEMA.info(Localiser.msg("054011", new Object[]{javaType, sqlType, jdbcType}));
               }
            } else {
               for(RDBMSTypeMapping sqlTypeMapping : (Collection)this.datastoreMappingsBySQLType.get(sqlType.toUpperCase())) {
                  if (sqlTypeMapping.javaType.equals(javaType)) {
                     datastoreMapping = sqlTypeMapping;
                     break;
                  }
               }
            }
         }

         if (datastoreMapping == null && jdbcType != null) {
            if (this.datastoreMappingsByJDBCType.get(jdbcType.toUpperCase()) == null) {
               if (fieldName != null) {
                  throw (new NucleusException(Localiser.msg("054003", new Object[]{javaType, jdbcType, fieldName}))).setFatal();
               }

               throw (new NucleusException(Localiser.msg("054002", new Object[]{javaType, jdbcType}))).setFatal();
            }

            for(RDBMSTypeMapping jdbcTypeMapping : (Collection)this.datastoreMappingsByJDBCType.get(jdbcType.toUpperCase())) {
               if (jdbcTypeMapping.javaType.equals(javaType)) {
                  datastoreMapping = jdbcTypeMapping;
                  break;
               }
            }

            if (datastoreMapping == null) {
               if (fieldName != null) {
                  throw (new NucleusException(Localiser.msg("054003", new Object[]{javaType, jdbcType, fieldName}))).setFatal();
               }

               throw (new NucleusException(Localiser.msg("054002", new Object[]{javaType, jdbcType}))).setFatal();
            }
         }

         if (datastoreMapping == null) {
            String type = ClassUtils.getWrapperTypeNameForPrimitiveTypeName(javaType);
            Collection mappings = (Collection)this.datastoreMappingsByJavaType.get(type);
            if (mappings == null) {
               Class javaTypeClass = clr.classForName(type);

               for(Class superClass = javaTypeClass.getSuperclass(); superClass != null && !superClass.getName().equals(ClassNameConstants.Object) && mappings == null; superClass = superClass.getSuperclass()) {
                  mappings = (Collection)this.datastoreMappingsByJavaType.get(superClass.getName());
               }
            }

            if (mappings != null) {
               if (mappings.size() == 1) {
                  datastoreMapping = (RDBMSTypeMapping)mappings.iterator().next();
               } else {
                  for(RDBMSTypeMapping rdbmsMapping : mappings) {
                     if (rdbmsMapping.isDefault()) {
                        datastoreMapping = rdbmsMapping;
                        break;
                     }
                  }

                  if (datastoreMapping == null && mappings.size() > 0) {
                     datastoreMapping = (RDBMSTypeMapping)mappings.iterator().next();
                  }
               }
            }
         }

         if (datastoreMapping == null) {
            if (fieldName != null) {
               throw (new NucleusException(Localiser.msg("054005", new Object[]{javaType, jdbcType, sqlType, fieldName}))).setFatal();
            } else {
               throw (new NucleusException(Localiser.msg("054004", new Object[]{javaType, jdbcType, sqlType}))).setFatal();
            }
         } else {
            return datastoreMapping.getMappingType();
         }
      }
   }

   public DatastoreMapping createDatastoreMapping(JavaTypeMapping mapping, AbstractMemberMetaData mmd, int index, Column column) {
      Class datastoreMappingClass = null;
      if (mmd.getColumnMetaData().length > 0 && mmd.getColumnMetaData()[index].hasExtension("datastore-mapping-class")) {
         datastoreMappingClass = this.clr.classForName(mmd.getColumnMetaData()[index].getValueForExtension("datastore-mapping-class"));
      }

      if (datastoreMappingClass == null) {
         String javaType = mapping.getJavaTypeForDatastoreMapping(index);
         String jdbcType = null;
         String sqlType = null;
         if (mapping.getRoleForMember() != FieldRole.ROLE_ARRAY_ELEMENT && mapping.getRoleForMember() != FieldRole.ROLE_COLLECTION_ELEMENT) {
            if (mapping.getRoleForMember() == FieldRole.ROLE_MAP_KEY) {
               ColumnMetaData[] colmds = mmd.getKeyMetaData() != null ? mmd.getKeyMetaData().getColumnMetaData() : null;
               if (colmds != null && colmds.length > 0) {
                  jdbcType = colmds[index].getJdbcTypeName();
                  sqlType = colmds[index].getSqlType();
               }

               if (mmd.getMap().isSerializedKey()) {
                  javaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
               }
            } else if (mapping.getRoleForMember() == FieldRole.ROLE_MAP_VALUE) {
               ColumnMetaData[] colmds = mmd.getValueMetaData() != null ? mmd.getValueMetaData().getColumnMetaData() : null;
               if (colmds != null && colmds.length > 0) {
                  jdbcType = colmds[index].getJdbcTypeName();
                  sqlType = colmds[index].getSqlType();
               }

               if (mmd.getMap().isSerializedValue()) {
                  javaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
               }
            } else {
               if (mmd.getColumnMetaData().length > 0) {
                  jdbcType = mmd.getColumnMetaData()[index].getJdbcTypeName();
                  sqlType = mmd.getColumnMetaData()[index].getSqlType();
               }

               IdentityStrategy strategy = mmd.getValueStrategy();
               if (strategy != null) {
                  String strategyName = strategy.toString();
                  if (strategy == IdentityStrategy.NATIVE) {
                     strategyName = this.storeMgr.getStrategyForNative(mmd.getAbstractClassMetaData(), mmd.getAbsoluteFieldNumber());
                  }

                  if (strategyName != null && IdentityStrategy.IDENTITY.toString().equals(strategyName)) {
                     Class requestedType = this.clr.classForName(javaType);
                     Class requiredType = this.storeMgr.getDatastoreAdapter().getAutoIncrementJavaTypeForType(requestedType);
                     if (requiredType != mmd.getType()) {
                        NucleusLogger.DATASTORE_SCHEMA.debug("Member " + mmd.getFullFieldName() + " uses IDENTITY strategy and rather than using memberType of " + mmd.getTypeName() + " for the column type, using " + requiredType + " since the datastore requires that");
                     }

                     javaType = requiredType.getName();
                  }
               }

               if (mmd.isSerialized()) {
                  javaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
               }
            }
         } else {
            ColumnMetaData[] colmds = mmd.getElementMetaData() != null ? mmd.getElementMetaData().getColumnMetaData() : null;
            if (colmds != null && colmds.length > 0) {
               jdbcType = colmds[index].getJdbcTypeName();
               sqlType = colmds[index].getSqlType();
            }

            if (mmd.getCollection() != null && mmd.getCollection().isSerializedElement()) {
               javaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
            }

            if (mmd.getArray() != null && mmd.getArray().isSerializedElement()) {
               javaType = ClassNameConstants.JAVA_IO_SERIALIZABLE;
            }
         }

         datastoreMappingClass = this.getDatastoreMappingClass(mmd.getFullFieldName(), javaType, jdbcType, sqlType, this.clr);
      }

      DatastoreMapping datastoreMapping = DatastoreMappingFactory.createMapping(datastoreMappingClass, mapping, this.storeMgr, column);
      if (column != null) {
         column.setDatastoreMapping(datastoreMapping);
      }

      return datastoreMapping;
   }

   public DatastoreMapping createDatastoreMapping(JavaTypeMapping mapping, Column column, String javaType) {
      String jdbcType = null;
      String sqlType = null;
      if (column != null && column.getColumnMetaData() != null) {
         jdbcType = column.getColumnMetaData().getJdbcTypeName();
         sqlType = column.getColumnMetaData().getSqlType();
      }

      Class datastoreMappingClass = this.getDatastoreMappingClass((String)null, javaType, jdbcType, sqlType, this.clr);
      DatastoreMapping datastoreMapping = DatastoreMappingFactory.createMapping(datastoreMappingClass, mapping, this.storeMgr, column);
      if (column != null) {
         column.setDatastoreMapping(datastoreMapping);
      }

      return datastoreMapping;
   }

   public Column createColumn(JavaTypeMapping mapping, String javaType, int datastoreFieldIndex) {
      AbstractMemberMetaData mmd = mapping.getMemberMetaData();
      FieldRole roleForField = mapping.getRoleForMember();
      Table tbl = mapping.getTable();
      ColumnMetaData colmd = null;
      ColumnMetaDataContainer columnContainer = mmd;
      if (roleForField != FieldRole.ROLE_COLLECTION_ELEMENT && roleForField != FieldRole.ROLE_ARRAY_ELEMENT) {
         if (roleForField == FieldRole.ROLE_MAP_KEY) {
            columnContainer = mmd.getKeyMetaData();
         } else if (roleForField == FieldRole.ROLE_MAP_VALUE) {
            columnContainer = mmd.getValueMetaData();
         }
      } else {
         columnContainer = mmd.getElementMetaData();
      }

      ColumnMetaData[] colmds;
      if (columnContainer != null && columnContainer.getColumnMetaData().length > datastoreFieldIndex) {
         colmd = columnContainer.getColumnMetaData()[datastoreFieldIndex];
         colmds = columnContainer.getColumnMetaData();
      } else {
         colmd = new ColumnMetaData();
         if (mmd.getColumnMetaData() != null && mmd.getColumnMetaData().length > datastoreFieldIndex) {
            colmd.setName(mmd.getColumnMetaData()[datastoreFieldIndex].getName());
         }

         if (columnContainer != null) {
            columnContainer.addColumn(colmd);
            colmds = columnContainer.getColumnMetaData();
         } else {
            colmds = new ColumnMetaData[1];
            colmds[0] = colmd;
         }
      }

      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      DatastoreIdentifier identifier = null;
      if (colmd.getName() == null) {
         if (roleForField == FieldRole.ROLE_COLLECTION_ELEMENT) {
            identifier = idFactory.newJoinTableFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, true, FieldRole.ROLE_COLLECTION_ELEMENT);
         } else if (roleForField == FieldRole.ROLE_ARRAY_ELEMENT) {
            identifier = idFactory.newJoinTableFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, true, FieldRole.ROLE_ARRAY_ELEMENT);
         } else if (roleForField == FieldRole.ROLE_MAP_KEY) {
            identifier = idFactory.newJoinTableFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, true, FieldRole.ROLE_MAP_KEY);
         } else if (roleForField == FieldRole.ROLE_MAP_VALUE) {
            identifier = idFactory.newJoinTableFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, true, FieldRole.ROLE_MAP_VALUE);
         } else {
            identifier = idFactory.newIdentifier(IdentifierType.COLUMN, mmd.getName());

            for(int i = 0; tbl.hasColumn(identifier); ++i) {
               identifier = idFactory.newIdentifier(IdentifierType.COLUMN, mmd.getName() + "_" + i);
            }
         }

         colmd.setName(identifier.getName());
      } else {
         identifier = idFactory.newColumnIdentifier(colmds[datastoreFieldIndex].getName(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(mmd.getType()), (FieldRole)null, true);
      }

      Column col = tbl.addColumn(javaType, identifier, mapping, colmd);
      if (mmd.isPrimaryKey()) {
         col.setPrimaryKey();
      }

      if (mmd.getParent() instanceof AbstractClassMetaData && this.storeMgr.isStrategyDatastoreAttributed(mmd.getAbstractClassMetaData(), mmd.getAbsoluteFieldNumber()) && tbl instanceof DatastoreClass && (mmd.isPrimaryKey() && ((DatastoreClass)tbl).isBaseDatastoreClass() || !mmd.isPrimaryKey())) {
         col.setIdentity(true);
      }

      if (mmd.getValueForExtension("select-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("select-function"), 0);
      }

      if (mmd.getValueForExtension("insert-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("insert-function"), 1);
      }

      if (mmd.getValueForExtension("update-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("update-function"), 2);
      }

      this.setColumnNullability(mmd, colmd, col);
      if (mmd.getNullValue() == NullValue.DEFAULT) {
         col.setDefaultable(colmd.getDefaultValue());
      }

      return col;
   }

   public Column createColumn(JavaTypeMapping mapping, String javaType, ColumnMetaData colmd) {
      AbstractMemberMetaData mmd = mapping.getMemberMetaData();
      Table tbl = mapping.getTable();
      if (colmd == null) {
         colmd = new ColumnMetaData();
         if (mmd.getColumnMetaData() != null && mmd.getColumnMetaData().length == 1) {
            colmd.setName(mmd.getColumnMetaData()[0].getName());
         }

         mmd.addColumn(colmd);
      }

      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      Column col;
      if (colmd.getName() == null) {
         DatastoreIdentifier identifier = idFactory.newIdentifier(IdentifierType.COLUMN, mmd.getName());

         for(int i = 0; tbl.hasColumn(identifier); ++i) {
            identifier = idFactory.newIdentifier(IdentifierType.COLUMN, mmd.getName() + "_" + i);
         }

         colmd.setName(identifier.getName());
         col = tbl.addColumn(javaType, identifier, mapping, colmd);
      } else {
         col = tbl.addColumn(javaType, idFactory.newColumnIdentifier(colmd.getName(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(mmd.getType()), (FieldRole)null, true), mapping, colmd);
      }

      this.setColumnNullability(mmd, colmd, col);
      if (mmd.getNullValue() == NullValue.DEFAULT) {
         col.setDefaultable(colmd.getDefaultValue());
      }

      return col;
   }

   public Column createColumn(AbstractMemberMetaData mmd, Table table, JavaTypeMapping mapping, ColumnMetaData colmd, Column reference, ClassLoaderResolver clr) {
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      DatastoreIdentifier identifier = null;
      if (colmd.getName() == null) {
         AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
         identifier = idFactory.newForeignKeyFieldIdentifier(relatedMmds != null ? relatedMmds[0] : null, mmd, reference.getIdentifier(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(mmd.getType()), FieldRole.ROLE_OWNER);
         colmd.setName(identifier.getName());
      } else {
         identifier = idFactory.newColumnIdentifier(colmd.getName(), false, (FieldRole)null, true);
      }

      Column col = table.addColumn(mmd.getType().getName(), identifier, mapping, colmd);
      reference.copyConfigurationTo(col);
      if (mmd.isPrimaryKey()) {
         col.setPrimaryKey();
      }

      if (mmd.getParent() instanceof AbstractClassMetaData && this.storeMgr.isStrategyDatastoreAttributed(mmd.getAbstractClassMetaData(), mmd.getAbsoluteFieldNumber()) && (mmd.isPrimaryKey() && ((DatastoreClass)table).isBaseDatastoreClass() || !mmd.isPrimaryKey())) {
         col.setIdentity(true);
      }

      if (mmd.getValueForExtension("select-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("select-function"), 0);
      }

      if (mmd.getValueForExtension("insert-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("insert-function"), 1);
      }

      if (mmd.getValueForExtension("update-function") != null) {
         col.setWrapperFunction(mmd.getValueForExtension("update-function"), 2);
      }

      this.setColumnNullability(mmd, colmd, col);
      if (mmd.getNullValue() == NullValue.DEFAULT) {
         col.setDefaultable(colmd.getDefaultValue());
      }

      return col;
   }

   private void setColumnNullability(AbstractMemberMetaData mmd, ColumnMetaData colmd, Column col) {
      if (colmd != null && colmd.getAllowsNull() == null) {
         if (mmd.isPrimaryKey()) {
            colmd.setAllowsNull(false);
         } else if (!mmd.getType().isPrimitive() && mmd.getNullValue() != NullValue.EXCEPTION) {
            colmd.setAllowsNull(true);
         } else {
            colmd.setAllowsNull(false);
         }

         if (colmd.isAllowsNull()) {
            col.setNullable(true);
         }
      } else if (colmd != null && colmd.getAllowsNull() != null) {
         if (colmd.isAllowsNull()) {
            col.setNullable(true);
         }
      } else if (!mmd.isPrimaryKey() && !mmd.getType().isPrimitive() && mmd.getNullValue() != NullValue.EXCEPTION) {
         col.setNullable(true);
      }

   }

   public class MappingConverterDetails {
      Class mappingClass;
      TypeConverter typeConverter;

      public MappingConverterDetails(Class mappingCls) {
         this.mappingClass = mappingCls;
      }

      public MappingConverterDetails(Class mappingCls, TypeConverter typeConv) {
         this.mappingClass = mappingCls;
         this.typeConverter = typeConv;
      }
   }

   protected class RDBMSTypeMapping {
      private String javaType;
      private String jdbcType;
      private String sqlType;
      private Class javaMappingType;
      private boolean isDefault;

      public RDBMSTypeMapping(Class mappingType, boolean isDefault, String javaType, String jdbcType, String sqlType) {
         this.javaMappingType = mappingType;
         this.isDefault = isDefault;
         this.javaType = javaType;
         this.jdbcType = jdbcType;
         this.sqlType = sqlType;
      }

      public boolean isDefault() {
         return this.isDefault;
      }

      public void setDefault(boolean isDefault) {
         this.isDefault = isDefault;
      }

      public Class getMappingType() {
         return this.javaMappingType;
      }

      public void setMappingType(Class type) {
         this.javaMappingType = type;
      }
   }
}
