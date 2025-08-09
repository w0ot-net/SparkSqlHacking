package org.datanucleus.api.jdo.metadata;

import java.lang.reflect.Method;
import java.util.Map;
import javax.jdo.annotations.Cacheable;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Columns;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Extensions;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.FetchGroups;
import javax.jdo.annotations.FetchPlan;
import javax.jdo.annotations.FetchPlans;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.ForeignKeys;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.Indices;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Joins;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PersistenceModifier;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Sequence;
import javax.jdo.annotations.SequenceStrategy;
import javax.jdo.annotations.Serialized;
import javax.jdo.annotations.Transactional;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.Value;
import javax.jdo.annotations.Version;
import javax.jdo.annotations.VersionStrategy;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.util.StringUtils;

public class JDOAnnotationUtils {
   public static final String PERSISTENCE_CAPABLE = PersistenceCapable.class.getName();
   public static final String PERSISTENCE_AWARE = PersistenceAware.class.getName();
   public static final String EMBEDDED_ONLY = EmbeddedOnly.class.getName();
   public static final String VERSION = Version.class.getName();
   public static final String DATASTORE_IDENTITY = DatastoreIdentity.class.getName();
   public static final String PRIMARY_KEY = PrimaryKey.class.getName();
   public static final String JOINS = Joins.class.getName();
   public static final String JOIN = Join.class.getName();
   public static final String INHERITANCE = Inheritance.class.getName();
   public static final String DISCRIMINATOR = Discriminator.class.getName();
   public static final String QUERIES = Queries.class.getName();
   public static final String QUERY = Query.class.getName();
   public static final String FETCHPLAN = FetchPlan.class.getName();
   public static final String FETCHPLANS = FetchPlans.class.getName();
   public static final String FETCHGROUPS = FetchGroups.class.getName();
   public static final String FETCHGROUP = FetchGroup.class.getName();
   public static final String SEQUENCE = Sequence.class.getName();
   public static final String INDICES = Indices.class.getName();
   public static final String INDEX = Index.class.getName();
   public static final String UNIQUES = Uniques.class.getName();
   public static final String UNIQUE = Unique.class.getName();
   public static final String FOREIGNKEYS = ForeignKeys.class.getName();
   public static final String FOREIGNKEY = ForeignKey.class.getName();
   public static final String COLUMNS = Columns.class.getName();
   public static final String COLUMN = Column.class.getName();
   public static final String EXTENSIONS = Extensions.class.getName();
   public static final String EXTENSION = Extension.class.getName();
   public static final String PERSISTENT = Persistent.class.getName();
   public static final String TRANSACTIONAL = Transactional.class.getName();
   public static final String NOTPERSISTENT = NotPersistent.class.getName();
   public static final String SERIALIZED = Serialized.class.getName();
   public static final String ELEMENT = Element.class.getName();
   public static final String KEY = Key.class.getName();
   public static final String VALUE = Value.class.getName();
   public static final String ORDER = Order.class.getName();
   public static final String EMBEDDED = Embedded.class.getName();
   public static final String CACHEABLE = Cacheable.class.getName();
   public static final String CONVERT = "javax.jdo.annotations.Convert";

   public static String getQueryLanguageName(String value) {
      if (value == null) {
         return QueryLanguage.JDOQL.toString();
      } else if (value.equalsIgnoreCase("javax.jdo.query.JDOQL")) {
         return QueryLanguage.JDOQL.toString();
      } else if (value.equalsIgnoreCase("javax.jdo.query.SQL")) {
         return QueryLanguage.SQL.toString();
      } else {
         return value.equalsIgnoreCase("javax.jdo.query.JPQL") ? QueryLanguage.JPQL.toString() : value;
      }
   }

   public static String getNullValueString(NullValue value) {
      if (value == NullValue.DEFAULT) {
         return org.datanucleus.metadata.NullValue.DEFAULT.toString();
      } else if (value == NullValue.EXCEPTION) {
         return org.datanucleus.metadata.NullValue.EXCEPTION.toString();
      } else {
         return value == NullValue.NONE ? org.datanucleus.metadata.NullValue.NONE.toString() : null;
      }
   }

   public static String getForeignKeyActionString(ForeignKeyAction action) {
      if (action == ForeignKeyAction.CASCADE) {
         return ForeignKeyAction.CASCADE.toString();
      } else if (action == ForeignKeyAction.DEFAULT) {
         return ForeignKeyAction.DEFAULT.toString();
      } else if (action == ForeignKeyAction.NONE) {
         return ForeignKeyAction.NONE.toString();
      } else if (action == ForeignKeyAction.NULL) {
         return ForeignKeyAction.NULL.toString();
      } else {
         return action == ForeignKeyAction.RESTRICT ? ForeignKeyAction.RESTRICT.toString() : null;
      }
   }

   public static FieldPersistenceModifier getFieldPersistenceModifier(PersistenceModifier modifier) {
      if (modifier == PersistenceModifier.PERSISTENT) {
         return FieldPersistenceModifier.PERSISTENT;
      } else if (modifier == PersistenceModifier.TRANSACTIONAL) {
         return FieldPersistenceModifier.TRANSACTIONAL;
      } else {
         return modifier == PersistenceModifier.NONE ? FieldPersistenceModifier.NONE : null;
      }
   }

   public static String getIdentityTypeString(IdentityType idType) {
      if (idType == IdentityType.APPLICATION) {
         return org.datanucleus.metadata.IdentityType.APPLICATION.toString();
      } else if (idType == IdentityType.DATASTORE) {
         return org.datanucleus.metadata.IdentityType.DATASTORE.toString();
      } else {
         return idType == IdentityType.NONDURABLE ? org.datanucleus.metadata.IdentityType.NONDURABLE.toString() : null;
      }
   }

   public static String getSequenceStrategyString(SequenceStrategy strategy) {
      if (strategy == SequenceStrategy.NONTRANSACTIONAL) {
         return org.datanucleus.metadata.SequenceStrategy.NONTRANSACTIONAL.toString();
      } else if (strategy == SequenceStrategy.CONTIGUOUS) {
         return org.datanucleus.metadata.SequenceStrategy.CONTIGUOUS.toString();
      } else {
         return strategy == SequenceStrategy.NONCONTIGUOUS ? org.datanucleus.metadata.SequenceStrategy.NONCONTIGUOUS.toString() : null;
      }
   }

   public static String getIdentityStrategyString(IdGeneratorStrategy strategy) {
      if (strategy == IdGeneratorStrategy.NATIVE) {
         return IdentityStrategy.NATIVE.toString();
      } else if (strategy == IdGeneratorStrategy.IDENTITY) {
         return IdentityStrategy.IDENTITY.toString();
      } else if (strategy == IdGeneratorStrategy.SEQUENCE) {
         return IdentityStrategy.SEQUENCE.toString();
      } else if (strategy == IdGeneratorStrategy.UUIDSTRING) {
         return IdentityStrategy.UUIDSTRING.toString();
      } else if (strategy == IdGeneratorStrategy.UUIDHEX) {
         return IdentityStrategy.UUIDHEX.toString();
      } else {
         return strategy == IdGeneratorStrategy.INCREMENT ? IdentityStrategy.INCREMENT.toString() : null;
      }
   }

   public static String getVersionStrategyString(VersionStrategy strategy) {
      if (strategy == VersionStrategy.NONE) {
         return org.datanucleus.metadata.VersionStrategy.NONE.toString();
      } else if (strategy == VersionStrategy.DATE_TIME) {
         return org.datanucleus.metadata.VersionStrategy.DATE_TIME.toString();
      } else if (strategy == VersionStrategy.VERSION_NUMBER) {
         return org.datanucleus.metadata.VersionStrategy.VERSION_NUMBER.toString();
      } else {
         return strategy == VersionStrategy.STATE_IMAGE ? org.datanucleus.metadata.VersionStrategy.STATE_IMAGE.toString() : null;
      }
   }

   public static String getInheritanceStrategyString(InheritanceStrategy strategy) {
      if (strategy == InheritanceStrategy.NEW_TABLE) {
         return org.datanucleus.metadata.InheritanceStrategy.NEW_TABLE.toString();
      } else if (strategy == InheritanceStrategy.SUBCLASS_TABLE) {
         return org.datanucleus.metadata.InheritanceStrategy.SUBCLASS_TABLE.toString();
      } else if (strategy == InheritanceStrategy.SUPERCLASS_TABLE) {
         return org.datanucleus.metadata.InheritanceStrategy.SUPERCLASS_TABLE.toString();
      } else {
         try {
            if (strategy == InheritanceStrategy.COMPLETE_TABLE) {
               return org.datanucleus.metadata.InheritanceStrategy.COMPLETE_TABLE.toString();
            }
         } catch (Exception var2) {
         } catch (Error var3) {
         }

         return null;
      }
   }

   public static String getDiscriminatorStrategyString(DiscriminatorStrategy strategy) {
      if (strategy == DiscriminatorStrategy.NONE) {
         return org.datanucleus.metadata.DiscriminatorStrategy.NONE.toString();
      } else if (strategy == DiscriminatorStrategy.VALUE_MAP) {
         return org.datanucleus.metadata.DiscriminatorStrategy.VALUE_MAP.toString();
      } else {
         return strategy == DiscriminatorStrategy.CLASS_NAME ? org.datanucleus.metadata.DiscriminatorStrategy.CLASS_NAME.toString() : null;
      }
   }

   public static ColumnMetaData getColumnMetaDataForAnnotations(Map annotationValues) {
      ColumnMetaData colmd = new ColumnMetaData();
      colmd.setName((String)annotationValues.get("name"));
      colmd.setTarget((String)annotationValues.get("target"));
      colmd.setTargetMember((String)annotationValues.get("targetField"));
      colmd.setJdbcType((String)annotationValues.get("jdbcType"));
      colmd.setSqlType((String)annotationValues.get("sqlType"));
      colmd.setLength((Integer)annotationValues.get("length"));
      colmd.setScale((Integer)annotationValues.get("scale"));
      colmd.setAllowsNull((String)annotationValues.get("allowsNull"));
      colmd.setDefaultValue((String)annotationValues.get("defaultValue"));
      colmd.setInsertValue((String)annotationValues.get("insertValue"));
      if (annotationValues.containsKey("position")) {
         colmd.setPosition((Integer)annotationValues.get("position"));
      }

      addExtensionsToMetaData(colmd, (Extension[])annotationValues.get("extensions"));
      return colmd;
   }

   public static ColumnMetaData getColumnMetaDataForColumnAnnotation(Column col) {
      String length = null;
      String scale = null;
      if (col.length() > 0) {
         length = "" + col.length();
      }

      if (col.scale() >= 0) {
         scale = "" + col.scale();
      }

      ColumnMetaData colmd = new ColumnMetaData();
      colmd.setName(col.name());
      colmd.setTarget(col.target());
      colmd.setTargetMember(col.targetMember());
      colmd.setJdbcType(col.jdbcType());
      colmd.setSqlType(col.sqlType());
      colmd.setLength(length);
      colmd.setScale(scale);
      colmd.setAllowsNull(col.allowsNull());
      colmd.setDefaultValue(col.defaultValue());
      colmd.setInsertValue(col.insertValue());

      try {
         Method posMethod = col.getClass().getDeclaredMethod("position", (Class)null);
         Integer posValue = (Integer)posMethod.invoke(col, (Object[])null);
         colmd.setPosition(posValue);
      } catch (Exception var6) {
      }

      addExtensionsToMetaData(colmd, col.extensions());
      return colmd;
   }

   public static IndexMetaData getIndexMetaData(String name, String table, String unique, String[] fields, Column[] columns) {
      IndexMetaData idxmd = new IndexMetaData();
      idxmd.setName(name);
      idxmd.setTable(table);
      if (!StringUtils.isWhitespace(unique)) {
         idxmd.setUnique(Boolean.valueOf(unique));
      }

      if (fields != null && fields.length > 0) {
         for(int j = 0; j < fields.length; ++j) {
            idxmd.addMember(fields[j]);
         }
      }

      if (idxmd.getNumberOfMembers() == 0 && columns != null && columns.length > 0) {
         for(int j = 0; j < columns.length; ++j) {
            idxmd.addColumn(columns[j].name());
         }
      }

      return idxmd;
   }

   public static UniqueMetaData getUniqueMetaData(String name, String table, String deferred, String[] fields, Column[] columns) {
      UniqueMetaData unimd = new UniqueMetaData();
      unimd.setName(name);
      unimd.setTable(table);
      if (!StringUtils.isWhitespace(deferred)) {
         unimd.setDeferred(Boolean.valueOf(deferred));
      }

      if (fields != null && fields.length > 0) {
         for(int j = 0; j < fields.length; ++j) {
            unimd.addMember(fields[j]);
         }
      }

      if (unimd.getNumberOfMembers() == 0 && columns != null && columns.length > 0) {
         for(int j = 0; j < columns.length; ++j) {
            unimd.addColumn(columns[j].name());
         }
      }

      return unimd;
   }

   public static ForeignKeyMetaData getFKMetaData(String name, String table, String unique, String deferred, String deleteAction, String updateAction, String[] fields, Column[] columns) {
      ForeignKeyMetaData fkmd = new ForeignKeyMetaData();
      fkmd.setName(name);
      fkmd.setTable(table);
      fkmd.setUnique(unique);
      fkmd.setDeferred(deferred);
      fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.getForeignKeyAction(deleteAction));
      fkmd.setUpdateAction(org.datanucleus.metadata.ForeignKeyAction.getForeignKeyAction(updateAction));
      if (fields != null && fields.length > 0) {
         for(int j = 0; j < fields.length; ++j) {
            fkmd.addMember(fields[j]);
         }
      }

      if (fkmd.getNumberOfMembers() == 0 && columns != null && columns.length > 0) {
         for(int j = 0; j < columns.length; ++j) {
            ColumnMetaData colmd = getColumnMetaDataForColumnAnnotation(columns[j]);
            fkmd.addColumn(colmd);
         }
      }

      return fkmd;
   }

   public static void addExtensionsToMetaData(MetaData metadata, Extension[] extensions) {
      if (extensions != null && extensions.length != 0) {
         for(int i = 0; i < extensions.length; ++i) {
            metadata.addExtension(extensions[i].vendorName(), extensions[i].key(), extensions[i].value());
         }

      }
   }
}
