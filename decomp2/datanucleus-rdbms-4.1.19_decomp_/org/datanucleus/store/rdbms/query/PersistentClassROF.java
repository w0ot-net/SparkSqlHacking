package org.datanucleus.store.rdbms.query;

import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.fieldmanager.ResultSetGetter;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.SoftValueMap;
import org.datanucleus.util.StringUtils;

public final class PersistentClassROF implements ResultObjectFactory {
   protected final RDBMSStoreManager storeMgr;
   protected final AbstractClassMetaData acmd;
   private Class persistentClass;
   protected StatementClassMapping stmtMapping = null;
   protected final FetchPlan fetchPlan;
   private final boolean ignoreCache;
   private Map resolvedClasses = new SoftValueMap();

   public PersistentClassROF(RDBMSStoreManager storeMgr, AbstractClassMetaData acmd, StatementClassMapping mappingDefinition, boolean ignoreCache, FetchPlan fetchPlan, Class persistentClass) {
      if (mappingDefinition == null) {
         throw new NucleusException("Attempt to create PersistentIDROF with null mappingDefinition");
      } else {
         this.storeMgr = storeMgr;
         this.stmtMapping = mappingDefinition;
         this.acmd = acmd;
         this.ignoreCache = ignoreCache;
         this.fetchPlan = fetchPlan;
         this.persistentClass = persistentClass;
      }
   }

   public Object getObject(ExecutionContext ec, ResultSet rs) {
      String className = null;
      boolean requiresInheritanceCheck = true;
      StatementMappingIndex discrimMapIdx = this.stmtMapping.getMappingForMemberPosition(-3);
      if (discrimMapIdx != null) {
         try {
            String discrimValue = rs.getString(discrimMapIdx.getColumnPositions()[0]);
            if (discrimValue == null) {
               NucleusLogger.DATASTORE_RETRIEVE.debug("Value of discriminator is null so assuming object is null");
               return null;
            }

            JavaTypeMapping discrimMapping = discrimMapIdx.getMapping();
            DiscriminatorMetaData dismd = discrimMapping != null ? discrimMapping.getTable().getDiscriminatorMetaData() : null;
            className = ec.getMetaDataManager().getClassNameFromDiscriminatorValue(discrimValue, dismd);
            requiresInheritanceCheck = false;
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE_RETRIEVE.debug("Exception obtaining value of discriminator : " + sqle.getMessage());
         }
      } else if (this.stmtMapping.getNucleusTypeColumnName() != null) {
         try {
            className = rs.getString(this.stmtMapping.getNucleusTypeColumnName());
            if (className == null) {
               NucleusLogger.DATASTORE_RETRIEVE.debug("Value of determiner column is null so assuming object is null");
               return null;
            }

            className = className.trim();
            requiresInheritanceCheck = false;
         } catch (SQLException var25) {
         }
      }

      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      Class pcClassForObject = this.persistentClass;
      if (className != null) {
         Class cls = (Class)this.resolvedClasses.get(className);
         if (cls != null) {
            pcClassForObject = cls;
         } else {
            if (this.persistentClass.getName().equals(className)) {
               pcClassForObject = this.persistentClass;
            } else {
               pcClassForObject = clr.classForName(className, this.persistentClass.getClassLoader());
            }

            this.resolvedClasses.put(className, pcClassForObject);
         }
      }

      if (requiresInheritanceCheck) {
         String[] subclasses = ec.getMetaDataManager().getSubclassesForClass(pcClassForObject.getName(), false);
         if (subclasses == null || subclasses.length == 0) {
            requiresInheritanceCheck = false;
         }
      }

      String warnMsg = null;
      if (Modifier.isAbstract(pcClassForObject.getModifiers())) {
         String[] subclasses = ec.getMetaDataManager().getSubclassesForClass(pcClassForObject.getName(), false);
         if (subclasses != null) {
            Class concreteSubclass = null;
            int numConcreteSubclasses = 0;

            for(int i = 0; i < subclasses.length; ++i) {
               Class subcls = clr.classForName(subclasses[i]);
               if (!Modifier.isAbstract(subcls.getModifiers())) {
                  ++numConcreteSubclasses;
                  concreteSubclass = subcls;
               }
            }

            if (numConcreteSubclasses == 1) {
               NucleusLogger.DATASTORE_RETRIEVE.warn(Localiser.msg("052300", new Object[]{pcClassForObject.getName(), concreteSubclass.getName()}));
               pcClassForObject = concreteSubclass;
            } else {
               if (numConcreteSubclasses == 0) {
                  throw new NucleusUserException(Localiser.msg("052301", new Object[]{pcClassForObject.getName()}));
               }

               warnMsg = "Found type=" + pcClassForObject + " but abstract and more than 1 concrete subclass (" + StringUtils.objectArrayToString(subclasses) + "). Really you need a discriminator to help identifying the type. Choosing " + concreteSubclass;
               pcClassForObject = concreteSubclass;
               requiresInheritanceCheck = true;
            }
         }
      }

      AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(pcClassForObject, clr);
      if (cmd == null) {
         return null;
      } else {
         int[] fieldNumbers = this.stmtMapping.getMemberNumbers();
         StatementClassMapping mappingDefinition;
         int[] mappedFieldNumbers;
         if (this.acmd instanceof InterfaceMetaData) {
            mappingDefinition = new StatementClassMapping();
            mappingDefinition.setNucleusTypeColumnName(this.stmtMapping.getNucleusTypeColumnName());
            mappedFieldNumbers = new int[fieldNumbers.length];

            for(int i = 0; i < fieldNumbers.length; ++i) {
               AbstractMemberMetaData mmd = this.acmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumbers[i]);
               mappedFieldNumbers[i] = cmd.getAbsolutePositionOfMember(mmd.getName());
               mappingDefinition.addMappingForMember(mappedFieldNumbers[i], this.stmtMapping.getMappingForMemberPosition(fieldNumbers[i]));
            }
         } else {
            mappingDefinition = this.stmtMapping;
            mappedFieldNumbers = fieldNumbers;
         }

         VersionMetaData vermd = cmd.getVersionMetaDataForClass();
         Object surrogateVersion = null;
         StatementMappingIndex versionMapping = null;
         if (vermd != null) {
            if (vermd.getFieldName() == null) {
               versionMapping = this.stmtMapping.getMappingForMemberPosition(-2);
            } else {
               AbstractMemberMetaData vermmd = cmd.getMetaDataForMember(vermd.getFieldName());
               versionMapping = this.stmtMapping.getMappingForMemberPosition(vermmd.getAbsoluteFieldNumber());
            }
         }

         if (versionMapping != null) {
            JavaTypeMapping mapping = versionMapping.getMapping();
            surrogateVersion = mapping.getObject(ec, rs, versionMapping.getColumnPositions());
         }

         T obj = (T)null;
         boolean needToSetVersion = false;
         if (this.persistentClass.isInterface() && !cmd.isImplementationOfPersistentDefinition()) {
            cmd = ec.getMetaDataManager().getMetaDataForInterface(this.persistentClass, clr);
            if (cmd == null) {
               cmd = ec.getMetaDataManager().getMetaDataForClass(pcClassForObject, clr);
            }
         }

         if (cmd.getIdentityType() == IdentityType.APPLICATION) {
            int[] pkNumbers = cmd.getPKMemberPositions();
            boolean nullObject = true;

            for(int i = 0; i < pkNumbers.length; ++i) {
               StatementMappingIndex pkIdx = mappingDefinition.getMappingForMemberPosition(pkNumbers[i]);
               if (pkIdx == null) {
                  throw new NucleusException("You have just executed an SQL statement yet the information for the primary key column(s) is not available! Please generate a testcase and report this issue");
               }

               int[] colPositions = pkIdx.getColumnPositions();

               for(int j = 0; j < colPositions.length; ++j) {
                  try {
                     Object pkObj = rs.getObject(colPositions[j]);
                     if (pkObj != null) {
                        nullObject = false;
                        break;
                     }
                  } catch (SQLException sqle) {
                     NucleusLogger.DATASTORE_RETRIEVE.warn("Exception thrown while retrieving results ", sqle);
                  }

                  if (!nullObject) {
                     break;
                  }
               }
            }

            if (!nullObject) {
               if (warnMsg != null) {
                  NucleusLogger.DATASTORE_RETRIEVE.warn(warnMsg);
               }

               obj = (T)this.getObjectForApplicationId(ec, rs, mappingDefinition, mappedFieldNumbers, pcClassForObject, cmd, requiresInheritanceCheck, surrogateVersion);
            }
         } else if (cmd.getIdentityType() == IdentityType.DATASTORE) {
            StatementMappingIndex datastoreIdMapping = this.stmtMapping.getMappingForMemberPosition(-1);
            JavaTypeMapping mapping = datastoreIdMapping.getMapping();
            Object id = mapping.getObject(ec, rs, datastoreIdMapping.getColumnPositions());
            if (id != null) {
               if (!pcClassForObject.getName().equals(IdentityUtils.getTargetClassNameForIdentitySimple(id))) {
                  id = ec.getNucleusContext().getIdentityManager().getDatastoreId(pcClassForObject.getName(), IdentityUtils.getTargetKeyForDatastoreIdentity(id));
               }

               if (warnMsg != null) {
                  NucleusLogger.DATASTORE_RETRIEVE.warn(warnMsg);
               }

               if (mappedFieldNumbers == null) {
                  obj = (T)ec.findObject(id, false, requiresInheritanceCheck, (String)null);
                  needToSetVersion = true;
               } else {
                  obj = (T)this.getObjectForDatastoreId(ec, rs, mappingDefinition, mappedFieldNumbers, id, requiresInheritanceCheck ? null : pcClassForObject, cmd, surrogateVersion);
               }
            }
         } else if (cmd.getIdentityType() == IdentityType.NONDURABLE) {
            String classNameForId = className;
            if (className == null) {
               classNameForId = cmd.getFullClassName();
            }

            Object id = ec.newObjectId(classNameForId, (Object)null);
            if (mappedFieldNumbers == null) {
               obj = (T)ec.findObject(id, false, requiresInheritanceCheck, (String)null);
               needToSetVersion = true;
            } else {
               obj = (T)this.getObjectForDatastoreId(ec, rs, mappingDefinition, mappedFieldNumbers, id, pcClassForObject, cmd, surrogateVersion);
            }
         }

         if (obj != null && needToSetVersion) {
            if (surrogateVersion != null) {
               ObjectProvider objOP = ec.findObjectProvider(obj);
               objOP.setVersion(surrogateVersion);
            } else if (vermd != null && vermd.getFieldName() != null) {
               int versionFieldNumber = this.acmd.getMetaDataForMember(vermd.getFieldName()).getAbsoluteFieldNumber();
               if (this.stmtMapping.getMappingForMemberPosition(versionFieldNumber) != null) {
                  ObjectProvider objOP = ec.findObjectProvider(obj);
                  Object verFieldValue = objOP.provideField(versionFieldNumber);
                  if (verFieldValue != null) {
                     objOP.setVersion(verFieldValue);
                  }
               }
            }
         }

         return obj;
      }
   }

   private Object getObjectForDatastoreId(ExecutionContext ec, final ResultSet resultSet, final StatementClassMapping mappingDefinition, final int[] fieldNumbers, Object oid, Class pcClass, final AbstractClassMetaData cmd, final Object surrogateVersion) {
      return oid == null ? null : ec.findObject(oid, new FieldValues() {
         public void fetchFields(ObjectProvider op) {
            FieldManager fm = PersistentClassROF.this.storeMgr.getFieldManagerForResultProcessing(op, resultSet, mappingDefinition);
            op.replaceFields(fieldNumbers, fm, false);
            if (surrogateVersion != null) {
               op.setVersion(surrogateVersion);
            } else if (cmd.getVersionMetaData() != null && cmd.getVersionMetaData().getFieldName() != null) {
               VersionMetaData vermd = cmd.getVersionMetaData();
               int versionFieldNumber = PersistentClassROF.this.acmd.getMetaDataForMember(vermd.getFieldName()).getAbsoluteFieldNumber();
               if (PersistentClassROF.this.stmtMapping.getMappingForMemberPosition(versionFieldNumber) != null) {
                  Object verFieldValue = op.provideField(versionFieldNumber);
                  if (verFieldValue != null) {
                     op.setVersion(verFieldValue);
                  }
               }
            }

         }

         public void fetchNonLoadedFields(ObjectProvider sm) {
            FieldManager fm = PersistentClassROF.this.storeMgr.getFieldManagerForResultProcessing(sm, resultSet, mappingDefinition);
            sm.replaceNonLoadedFields(fieldNumbers, fm);
         }

         public FetchPlan getFetchPlanForLoading() {
            return PersistentClassROF.this.fetchPlan;
         }
      }, pcClass, this.ignoreCache, false);
   }

   private Object getObjectForApplicationId(ExecutionContext ec, final ResultSet resultSet, final StatementClassMapping mappingDefinition, final int[] fieldNumbers, Class pcClass, final AbstractClassMetaData cmd, boolean requiresInheritanceCheck, final Object surrogateVersion) {
      Object id = getIdentityForResultSetRow(this.storeMgr, resultSet, mappingDefinition, ec, cmd, pcClass, requiresInheritanceCheck);
      if (IdentityUtils.isSingleFieldIdentity(id)) {
         pcClass = ec.getClassLoaderResolver().classForName(IdentityUtils.getTargetClassNameForIdentitySimple(id));
      }

      return ec.findObject(id, new FieldValues() {
         public void fetchFields(ObjectProvider op) {
            FieldManager fm = PersistentClassROF.this.storeMgr.getFieldManagerForResultProcessing(op, resultSet, mappingDefinition);
            op.replaceFields(fieldNumbers, fm, false);
            if (surrogateVersion != null) {
               op.setVersion(surrogateVersion);
            } else if (cmd.getVersionMetaData() != null && cmd.getVersionMetaData().getFieldName() != null) {
               VersionMetaData vermd = cmd.getVersionMetaData();
               int versionFieldNumber = PersistentClassROF.this.acmd.getMetaDataForMember(vermd.getFieldName()).getAbsoluteFieldNumber();
               if (PersistentClassROF.this.stmtMapping.getMappingForMemberPosition(versionFieldNumber) != null) {
                  Object verFieldValue = op.provideField(versionFieldNumber);
                  if (verFieldValue != null) {
                     op.setVersion(verFieldValue);
                  }
               }
            }

         }

         public void fetchNonLoadedFields(ObjectProvider op) {
            FieldManager fm = PersistentClassROF.this.storeMgr.getFieldManagerForResultProcessing(op, resultSet, mappingDefinition);
            op.replaceNonLoadedFields(fieldNumbers, fm);
         }

         public FetchPlan getFetchPlanForLoading() {
            return PersistentClassROF.this.fetchPlan;
         }
      }, pcClass, this.ignoreCache, false);
   }

   public static Object getIdentityForResultSetRow(RDBMSStoreManager storeMgr, ResultSet resultSet, StatementClassMapping mappingDefinition, ExecutionContext ec, AbstractClassMetaData cmd, Class pcClass, boolean inheritanceCheck) {
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         return getDatastoreIdentityForResultSetRow(ec, cmd, pcClass, inheritanceCheck, resultSet, mappingDefinition);
      } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         FieldManager resultsFM = new ResultSetGetter(storeMgr, ec, resultSet, mappingDefinition, cmd);
         return IdentityUtils.getApplicationIdentityForResultSetRow(ec, cmd, pcClass, inheritanceCheck, resultsFM);
      } else {
         return null;
      }
   }

   public static Object getDatastoreIdentityForResultSetRow(ExecutionContext ec, AbstractClassMetaData cmd, Class pcClass, boolean inheritanceCheck, ResultSet resultSet, StatementClassMapping mappingDefinition) {
      if (cmd.getIdentityType() != IdentityType.DATASTORE) {
         return null;
      } else {
         if (pcClass == null) {
            pcClass = ec.getClassLoaderResolver().classForName(cmd.getFullClassName());
         }

         StatementMappingIndex datastoreIdMapping = mappingDefinition.getMappingForMemberPosition(-1);
         JavaTypeMapping mapping = datastoreIdMapping.getMapping();
         Object id = mapping.getObject(ec, resultSet, datastoreIdMapping.getColumnPositions());
         if (id != null && !pcClass.getName().equals(IdentityUtils.getTargetClassNameForIdentitySimple(id))) {
            id = ec.getNucleusContext().getIdentityManager().getDatastoreId(pcClass.getName(), IdentityUtils.getTargetKeyForDatastoreIdentity(id));
         }

         if (inheritanceCheck) {
            if (ec.hasIdentityInCache(id)) {
               return id;
            } else {
               String[] subclasses = ec.getMetaDataManager().getSubclassesForClass(pcClass.getName(), true);
               if (subclasses != null) {
                  for(int i = 0; i < subclasses.length; ++i) {
                     id = ec.getNucleusContext().getIdentityManager().getDatastoreId(subclasses[i], IdentityUtils.getTargetKeyForDatastoreIdentity(id));
                     if (ec.hasIdentityInCache(id)) {
                        return id;
                     }
                  }
               }

               String className = ec.getStoreManager().getClassNameForObjectID(id, ec.getClassLoaderResolver(), ec);
               return ec.getNucleusContext().getIdentityManager().getDatastoreId(className, IdentityUtils.getTargetKeyForDatastoreIdentity(id));
            }
         } else {
            return id;
         }
      }
   }
}
