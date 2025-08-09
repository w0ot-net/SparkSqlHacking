package org.datanucleus.metadata;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.xml.MetaDataParser;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class MetaDataUtils {
   private static MetaDataUtils instance;

   public static synchronized MetaDataUtils getInstance() {
      if (instance == null) {
         instance = new MetaDataUtils();
      }

      return instance;
   }

   protected MetaDataUtils() {
   }

   public boolean arrayStorableAsByteArrayInSingleColumn(AbstractMemberMetaData fmd) {
      if (fmd != null && fmd.hasArray()) {
         String arrayComponentType = fmd.getType().getComponentType().getName();
         return arrayComponentType.equals(ClassNameConstants.BOOLEAN) || arrayComponentType.equals(ClassNameConstants.BYTE) || arrayComponentType.equals(ClassNameConstants.CHAR) || arrayComponentType.equals(ClassNameConstants.DOUBLE) || arrayComponentType.equals(ClassNameConstants.FLOAT) || arrayComponentType.equals(ClassNameConstants.INT) || arrayComponentType.equals(ClassNameConstants.LONG) || arrayComponentType.equals(ClassNameConstants.SHORT) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_BOOLEAN) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_BYTE) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_CHARACTER) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_DOUBLE) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_FLOAT) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_INTEGER) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_LONG) || arrayComponentType.equals(ClassNameConstants.JAVA_LANG_SHORT) || arrayComponentType.equals(ClassNameConstants.JAVA_MATH_BIGDECIMAL) || arrayComponentType.equals(ClassNameConstants.JAVA_MATH_BIGINTEGER);
      } else {
         return false;
      }
   }

   public boolean storesPersistable(AbstractMemberMetaData fmd, ExecutionContext ec) {
      if (fmd == null) {
         return false;
      } else {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         MetaDataManager mmgr = ec.getMetaDataManager();
         if (fmd.hasCollection()) {
            if (fmd.getCollection().elementIsPersistent()) {
               return true;
            }

            String elementType = fmd.getCollection().getElementType();
            Class elementCls = clr.classForName(elementType);
            if (mmgr.getMetaDataForImplementationOfReference(elementCls, (Object)null, clr) != null) {
               return true;
            }

            if (elementCls != null && ClassUtils.isReferenceType(elementCls)) {
               try {
                  String[] impls = this.getImplementationNamesForReferenceField(fmd, FieldRole.ROLE_COLLECTION_ELEMENT, clr, mmgr);
                  if (impls != null) {
                     elementCls = clr.classForName(impls[0]);
                     if (ec.getApiAdapter().isPersistable(elementCls)) {
                        return true;
                     }
                  }
               } catch (NucleusUserException var13) {
               }
            }
         } else if (fmd.hasMap()) {
            if (fmd.getMap().keyIsPersistent()) {
               return true;
            }

            String keyType = fmd.getMap().getKeyType();
            Class keyCls = clr.classForName(keyType);
            if (mmgr.getMetaDataForImplementationOfReference(keyCls, (Object)null, clr) != null) {
               return true;
            }

            if (keyCls != null && ClassUtils.isReferenceType(keyCls)) {
               try {
                  String[] impls = this.getImplementationNamesForReferenceField(fmd, FieldRole.ROLE_MAP_KEY, clr, mmgr);
                  if (impls != null) {
                     keyCls = clr.classForName(impls[0]);
                     if (ec.getApiAdapter().isPersistable(keyCls)) {
                        return true;
                     }
                  }
               } catch (NucleusUserException var12) {
               }
            }

            if (fmd.getMap().valueIsPersistent()) {
               return true;
            }

            String valueType = fmd.getMap().getValueType();
            Class valueCls = clr.classForName(valueType);
            if (mmgr.getMetaDataForImplementationOfReference(valueCls, (Object)null, clr) != null) {
               return true;
            }

            if (valueCls != null && ClassUtils.isReferenceType(valueCls)) {
               try {
                  String[] impls = this.getImplementationNamesForReferenceField(fmd, FieldRole.ROLE_MAP_VALUE, clr, mmgr);
                  if (impls != null) {
                     valueCls = clr.classForName(impls[0]);
                     if (ec.getApiAdapter().isPersistable(valueCls)) {
                        return true;
                     }
                  }
               } catch (NucleusUserException var11) {
               }
            }
         } else if (fmd.hasArray()) {
            if (mmgr.getApiAdapter().isPersistable(fmd.getType().getComponentType())) {
               return true;
            }

            String elementType = fmd.getArray().getElementType();
            Class elementCls = clr.classForName(elementType);
            if (mmgr.getApiAdapter().isPersistable(elementCls)) {
               return true;
            }

            if (mmgr.getMetaDataForImplementationOfReference(elementCls, (Object)null, clr) != null) {
               return true;
            }

            if (elementCls != null && ClassUtils.isReferenceType(elementCls)) {
               try {
                  String[] impls = this.getImplementationNamesForReferenceField(fmd, FieldRole.ROLE_ARRAY_ELEMENT, clr, mmgr);
                  if (impls != null) {
                     elementCls = clr.classForName(impls[0]);
                     if (ec.getApiAdapter().isPersistable(elementCls)) {
                        return true;
                     }
                  }
               } catch (NucleusUserException var10) {
               }
            }
         } else {
            if (ClassUtils.isReferenceType(fmd.getType()) && mmgr.getMetaDataForImplementationOfReference(fmd.getType(), (Object)null, clr) != null) {
               return true;
            }

            if (mmgr.getMetaDataForClass(fmd.getType(), clr) != null) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean storesFCO(AbstractMemberMetaData fmd, ExecutionContext ec) {
      if (fmd == null) {
         return false;
      } else {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         MetaDataManager mgr = ec.getMetaDataManager();
         if (!fmd.isSerialized() && !fmd.isEmbedded()) {
            if (fmd.hasCollection() && !fmd.getCollection().isSerializedElement() && !fmd.getCollection().isEmbeddedElement()) {
               if (fmd.getCollection().elementIsPersistent()) {
                  return true;
               }

               String elementType = fmd.getCollection().getElementType();
               Class elementCls = clr.classForName(elementType);
               if (elementCls != null && ClassUtils.isReferenceType(elementCls) && mgr.getMetaDataForImplementationOfReference(elementCls, (Object)null, clr) != null) {
                  return true;
               }
            } else if (fmd.hasMap()) {
               if (fmd.getMap().keyIsPersistent() && !fmd.getMap().isEmbeddedKey() && !fmd.getMap().isSerializedKey()) {
                  return true;
               }

               String keyType = fmd.getMap().getKeyType();
               Class keyCls = clr.classForName(keyType);
               if (keyCls != null && ClassUtils.isReferenceType(keyCls) && mgr.getMetaDataForImplementationOfReference(keyCls, (Object)null, clr) != null) {
                  return true;
               }

               if (fmd.getMap().valueIsPersistent() && !fmd.getMap().isEmbeddedValue() && !fmd.getMap().isSerializedValue()) {
                  return true;
               }

               String valueType = fmd.getMap().getValueType();
               Class valueCls = clr.classForName(valueType);
               if (valueCls != null && ClassUtils.isReferenceType(valueCls) && mgr.getMetaDataForImplementationOfReference(valueCls, (Object)null, clr) != null) {
                  return true;
               }
            } else if (fmd.hasArray() && !fmd.getArray().isSerializedElement() && !fmd.getArray().isEmbeddedElement()) {
               if (mgr.getApiAdapter().isPersistable(fmd.getType().getComponentType())) {
                  return true;
               }
            } else {
               if (ClassUtils.isReferenceType(fmd.getType()) && mgr.getMetaDataForImplementationOfReference(fmd.getType(), (Object)null, clr) != null) {
                  return true;
               }

               if (mgr.getMetaDataForClass(fmd.getType(), clr) != null) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   public String[] getValuesForCommaSeparatedAttribute(String attr) {
      if (attr != null && attr.length() != 0) {
         String[] values = StringUtils.split(attr, ",");
         if (values != null) {
            for(int i = 0; i < values.length; ++i) {
               values[i] = values[i].trim();
            }
         }

         return values;
      } else {
         return null;
      }
   }

   public String[] getImplementationNamesForReferenceField(AbstractMemberMetaData fmd, FieldRole fieldRole, ClassLoaderResolver clr, MetaDataManager mmgr) {
      String[] implTypes = null;
      if (fieldRole == FieldRole.ROLE_FIELD) {
         implTypes = fmd.getFieldTypes();
         if (implTypes != null && implTypes.length == 1) {
            Class implCls = clr.classForName(implTypes[0].trim());
            if (implCls.isInterface()) {
               implTypes = mmgr.getClassesImplementingInterface(implTypes[0], clr);
            }
         }
      } else if (FieldRole.ROLE_COLLECTION_ELEMENT == fieldRole) {
         implTypes = fmd.getCollection().getElementTypes();
      } else if (FieldRole.ROLE_ARRAY_ELEMENT == fieldRole) {
         implTypes = fmd.getArray().getElementTypes();
      } else if (FieldRole.ROLE_MAP_KEY == fieldRole) {
         implTypes = fmd.getMap().getKeyTypes();
      } else if (FieldRole.ROLE_MAP_VALUE == fieldRole) {
         implTypes = fmd.getMap().getValueTypes();
      }

      if (implTypes == null) {
         String type = null;
         if (fmd.hasCollection() && fieldRole == FieldRole.ROLE_COLLECTION_ELEMENT) {
            type = fmd.getCollection().getElementType();
         } else if (fmd.hasMap() && fieldRole == FieldRole.ROLE_MAP_KEY) {
            type = fmd.getMap().getKeyType();
         } else if (fmd.hasMap() && fieldRole == FieldRole.ROLE_MAP_VALUE) {
            type = fmd.getMap().getValueType();
         } else if (fmd.hasArray() && fieldRole == FieldRole.ROLE_ARRAY_ELEMENT) {
            type = fmd.getArray().getElementType();
            if (type == null) {
               type = fmd.getType().getComponentType().getName();
            }
         } else {
            type = fmd.getTypeName();
         }

         if (!type.equals(ClassNameConstants.Object)) {
            implTypes = mmgr.getClassesImplementingInterface(type, clr);
         }

         if (implTypes == null) {
            throw new InvalidMemberMetaDataException("044161", new Object[]{fmd.getClassName(), fmd.getName(), type});
         }
      }

      int noOfDups = 0;

      for(int i = 0; i < implTypes.length; ++i) {
         for(int j = 0; j < i; ++j) {
            if (implTypes[j].equals(implTypes[i])) {
               ++noOfDups;
               break;
            }
         }
      }

      String[] impls = new String[implTypes.length - noOfDups];
      int n = 0;

      for(int i = 0; i < implTypes.length; ++i) {
         boolean dup = false;

         for(int j = 0; j < i; ++j) {
            if (implTypes[j].equals(implTypes[i])) {
               dup = true;
               break;
            }
         }

         if (!dup) {
            impls[n++] = implTypes[i];
         }
      }

      return impls;
   }

   public static boolean getBooleanForString(String str, boolean dflt) {
      return StringUtils.isWhitespace(str) ? dflt : Boolean.parseBoolean(str);
   }

   public static String getValueForExtensionRecursively(MetaData metadata, String key) {
      if (metadata == null) {
         return null;
      } else {
         String value = metadata.getValueForExtension(key);
         if (value == null) {
            value = getValueForExtensionRecursively(metadata.getParent(), key);
         }

         return value;
      }
   }

   public static String[] getValuesForExtensionRecursively(MetaData metadata, String key) {
      if (metadata == null) {
         return null;
      } else {
         String[] values = metadata.getValuesForExtension(key);
         if (values == null) {
            values = getValuesForExtensionRecursively(metadata.getParent(), key);
         }

         return values;
      }
   }

   public static boolean isJdbcTypeNumeric(JdbcType jdbcType) {
      if (jdbcType == null) {
         return false;
      } else {
         return jdbcType == JdbcType.INTEGER || jdbcType == JdbcType.SMALLINT || jdbcType == JdbcType.TINYINT || jdbcType == JdbcType.NUMERIC || jdbcType == JdbcType.BIGINT;
      }
   }

   public static boolean isJdbcTypeFloatingPoint(JdbcType jdbcType) {
      if (jdbcType == null) {
         return false;
      } else {
         return jdbcType == JdbcType.DECIMAL || jdbcType == JdbcType.FLOAT || jdbcType == JdbcType.REAL || jdbcType == JdbcType.DECIMAL;
      }
   }

   public static boolean isJdbcTypeString(JdbcType jdbcType) {
      if (jdbcType == null) {
         return false;
      } else {
         return jdbcType == JdbcType.CHAR || jdbcType == JdbcType.VARCHAR || jdbcType == JdbcType.CLOB || jdbcType == JdbcType.LONGVARCHAR || jdbcType == JdbcType.NCHAR || jdbcType == JdbcType.NVARCHAR || jdbcType == JdbcType.LONGNVARCHAR;
      }
   }

   public static List getMetaDataForCandidates(Class cls, boolean subclasses, ExecutionContext ec) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      List<AbstractClassMetaData> cmds = new ArrayList();
      if (cls.isInterface()) {
         AbstractClassMetaData icmd = ec.getMetaDataManager().getMetaDataForInterface(cls, clr);
         if (icmd == null) {
            throw new NucleusUserException("Attempting to query an interface yet it is not declared 'persistent'. Define the interface in metadata as being persistent to perform this operation, and make sure any implementations use the same identity and identity member(s)");
         }

         String[] impls = ec.getMetaDataManager().getClassesImplementingInterface(cls.getName(), clr);

         for(int i = 0; i < impls.length; ++i) {
            AbstractClassMetaData implCmd = ec.getMetaDataManager().getMetaDataForClass(impls[i], clr);
            cmds.add(implCmd);
            if (subclasses) {
               String[] subclassNames = ec.getMetaDataManager().getSubclassesForClass(implCmd.getFullClassName(), true);
               if (subclassNames != null && subclassNames.length > 0) {
                  for(int j = 0; j < subclassNames.length; ++j) {
                     AbstractClassMetaData subcmd = ec.getMetaDataManager().getMetaDataForClass(subclassNames[j], clr);
                     cmds.add(subcmd);
                  }
               }
            }
         }
      } else {
         AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(cls, clr);
         cmds.add(cmd);
         if (subclasses) {
            String[] subclassNames = ec.getMetaDataManager().getSubclassesForClass(cls.getName(), true);
            if (subclassNames != null && subclassNames.length > 0) {
               for(int j = 0; j < subclassNames.length; ++j) {
                  AbstractClassMetaData subcmd = ec.getMetaDataManager().getMetaDataForClass(subclassNames[j], clr);
                  cmds.add(subcmd);
               }
            }
         }
      }

      return cmds;
   }

   public static FileMetaData[] getFileMetaDataForInputFiles(MetaDataManager metaDataMgr, ClassLoaderResolver clr, String[] inputFiles) {
      FileMetaData[] filemds = null;
      String msg = null;

      try {
         Set<String> metadataFiles = new HashSet();
         Set<String> classNames = new HashSet();

         for(int i = 0; i < inputFiles.length; ++i) {
            if (inputFiles[i].endsWith(".class")) {
               URL classFileURL = null;

               try {
                  classFileURL = new URL("file:" + inputFiles[i]);
               } catch (Exception var11) {
                  msg = Localiser.msg("014013", inputFiles[i]);
                  NucleusLogger.METADATA.error(msg);
                  throw new NucleusUserException(msg);
               }

               String className = null;

               try {
                  className = ClassUtils.getClassNameForFileURL(classFileURL);
                  classNames.add(className);
               } catch (Throwable e) {
                  className = ClassUtils.getClassNameForFileName(inputFiles[i], clr);
                  if (className != null) {
                     classNames.add(className);
                  } else {
                     NucleusLogger.METADATA.info("File \"" + inputFiles[i] + "\" could not be resolved to a class name, so ignoring. Specify it as a class explicitly using persistence.xml to overcome this", e);
                  }
               }
            } else {
               metadataFiles.add(inputFiles[i]);
            }
         }

         FileMetaData[] filemds1 = metaDataMgr.loadMetadataFiles((String[])metadataFiles.toArray(new String[metadataFiles.size()]), (ClassLoader)null);
         FileMetaData[] filemds2 = metaDataMgr.loadClasses((String[])classNames.toArray(new String[classNames.size()]), (ClassLoader)null);
         filemds = new FileMetaData[filemds1.length + filemds2.length];
         int pos = 0;

         for(int i = 0; i < filemds1.length; ++i) {
            filemds[pos++] = filemds1[i];
         }

         for(int i = 0; i < filemds2.length; ++i) {
            filemds[pos++] = filemds2[i];
         }

         return filemds;
      } catch (Exception e) {
         msg = Localiser.msg("014014", e.getMessage());
         NucleusLogger.METADATA.error(msg, e);
         throw new NucleusUserException(msg, e);
      }
   }

   public static PersistenceFileMetaData[] parsePersistenceFiles(PluginManager pluginMgr, String persistenceFilename, boolean validate, ClassLoaderResolver clr) {
      if (persistenceFilename != null) {
         try {
            URL fileURL = new URL(persistenceFilename);
            MetaDataParser parser = new MetaDataParser((MetaDataManager)null, pluginMgr, validate);
            MetaData permd = parser.parseMetaDataURL(fileURL, "persistence");
            return new PersistenceFileMetaData[]{(PersistenceFileMetaData)permd};
         } catch (MalformedURLException mue) {
            NucleusLogger.METADATA.error("Error reading user-specified persistence.xml file " + persistenceFilename, mue);
            return null;
         }
      } else {
         Set metadata = new LinkedHashSet();

         try {
            Enumeration files = clr.getResources("META-INF/persistence.xml", Thread.currentThread().getContextClassLoader());
            if (!files.hasMoreElements()) {
               return null;
            }

            MetaDataParser parser = null;

            while(files.hasMoreElements()) {
               URL fileURL = (URL)files.nextElement();
               if (parser == null) {
                  parser = new MetaDataParser((MetaDataManager)null, pluginMgr, validate);
               }

               MetaData permd = parser.parseMetaDataURL(fileURL, "persistence");
               metadata.add(permd);
            }
         } catch (IOException ioe) {
            NucleusLogger.METADATA.warn(StringUtils.getStringFromStackTrace(ioe));
         }

         return (PersistenceFileMetaData[])metadata.toArray(new PersistenceFileMetaData[metadata.size()]);
      }
   }

   public static boolean persistColumnAsNumeric(ColumnMetaData colmd) {
      boolean useLong = false;
      if (colmd != null && isJdbcTypeNumeric(colmd.getJdbcType())) {
         useLong = true;
      }

      return useLong;
   }

   public static boolean persistColumnAsString(ColumnMetaData colmd) {
      boolean useString = false;
      if (colmd != null && isJdbcTypeString(colmd.getJdbcType())) {
         useString = true;
      }

      return useString;
   }

   public static Class getTypeOfDatastoreIdentity(IdentityMetaData idmd) {
      if (idmd == null) {
         return Long.TYPE;
      } else {
         return idmd.getValueStrategy() != IdentityStrategy.UUIDHEX && idmd.getValueStrategy() != IdentityStrategy.UUIDSTRING ? Long.TYPE : String.class;
      }
   }

   public static boolean isMemberEmbedded(AbstractMemberMetaData mmd, RelationType relationType, ClassLoaderResolver clr, MetaDataManager mmgr) {
      boolean embedded = false;
      if (mmd.isEmbedded()) {
         embedded = true;
      } else if (mmd.getEmbeddedMetaData() != null) {
         embedded = true;
      } else if (RelationType.isRelationMultiValued(relationType)) {
         if (mmd.hasCollection() && mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null) {
            embedded = true;
         } else if (mmd.hasArray() && mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null) {
            embedded = true;
         } else if (mmd.hasMap() && (mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getEmbeddedMetaData() != null || mmd.getValueMetaData() != null && mmd.getValueMetaData().getEmbeddedMetaData() != null)) {
            embedded = true;
         }
      } else if (RelationType.isRelationSingleValued(relationType)) {
         AbstractClassMetaData mmdCmd = mmgr.getMetaDataForClass(mmd.getType(), clr);
         if (mmdCmd != null && mmdCmd.isEmbeddedOnly()) {
            embedded = true;
         }
      } else if (RelationType.isRelationMultiValued(relationType)) {
      }

      return embedded;
   }

   public boolean isMemberEmbedded(MetaDataManager mmgr, ClassLoaderResolver clr, AbstractMemberMetaData mmd, RelationType relationType, AbstractMemberMetaData ownerMmd) {
      boolean embedded = false;
      if (relationType != RelationType.NONE) {
         if (RelationType.isRelationSingleValued(relationType)) {
            AbstractClassMetaData mmdCmd = mmgr.getMetaDataForClass(mmd.getType(), clr);
            if (mmdCmd != null && mmdCmd.isEmbeddedOnly()) {
               return true;
            }
         }

         if (mmd.isEmbedded() || mmd.getEmbeddedMetaData() != null) {
            return true;
         }

         if (RelationType.isRelationMultiValued(relationType)) {
            if (mmd.hasCollection() && mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null) {
               return true;
            }

            if (mmd.hasArray() && mmd.getElementMetaData() != null && mmd.getElementMetaData().getEmbeddedMetaData() != null) {
               return true;
            }

            if (mmd.hasMap() && (mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getEmbeddedMetaData() != null || mmd.getValueMetaData() != null && mmd.getValueMetaData().getEmbeddedMetaData() != null)) {
               return true;
            }
         }

         if (RelationType.isRelationSingleValued(relationType) && ownerMmd != null) {
            if (!ownerMmd.hasCollection()) {
               if (ownerMmd.getEmbeddedMetaData() != null) {
                  AbstractMemberMetaData[] embMmds = ownerMmd.getEmbeddedMetaData().getMemberMetaData();
                  if (embMmds != null) {
                     for(int i = 0; i < embMmds.length; ++i) {
                        if (embMmds[i].getName().equals(mmd.getName())) {
                           return embMmds[i].getEmbeddedMetaData() != null;
                        }
                     }
                  }
               }
            } else {
               EmbeddedMetaData embmd = ownerMmd.getElementMetaData().getEmbeddedMetaData();
               if (embmd != null) {
                  AbstractMemberMetaData[] embMmds = embmd.getMemberMetaData();
                  if (embMmds != null) {
                     for(AbstractMemberMetaData embMmd : embMmds) {
                        if (embMmd.getName().equals(mmd.getName()) && (embMmd.isEmbedded() || embMmd.getEmbeddedMetaData() != null)) {
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return embedded;
   }
}
