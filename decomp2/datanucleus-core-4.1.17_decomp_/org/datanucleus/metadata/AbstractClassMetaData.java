package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MacroString;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.ViewUtils;

public abstract class AbstractClassMetaData extends MetaData {
   private static final long serialVersionUID = -2433561862769017940L;
   public static final String GENERATED_PK_SUFFIX = "_PK";
   protected final String name;
   protected String entityName;
   protected boolean mappedSuperclass;
   protected boolean instantiable;
   protected Boolean accessViaField;
   protected IdentityType identityType;
   protected ClassPersistenceModifier persistenceModifier;
   protected String persistableSuperclass;
   protected String objectidClass;
   protected boolean requiresExtent;
   protected boolean detachable;
   protected boolean embeddedOnly;
   protected String catalog;
   protected String schema;
   protected String table;
   protected Boolean cacheable;
   protected final String fullName;
   protected VersionMetaData versionMetaData;
   protected IdentityMetaData identityMetaData;
   protected boolean identitySpecified;
   protected InheritanceMetaData inheritanceMetaData;
   protected PrimaryKeyMetaData primaryKeyMetaData;
   protected List listeners;
   protected Boolean excludeSuperClassListeners;
   protected Boolean excludeDefaultListeners;
   protected Map fetchGroupMetaDataByName;
   protected AbstractClassMetaData pcSuperclassMetaData;
   protected boolean metaDataComplete;
   protected boolean serializeRead;
   protected Collection queries;
   protected Collection storedProcQueries;
   protected Collection queryResultMetaData;
   protected JoinMetaData[] joinMetaData;
   protected IndexMetaData[] indexMetaData;
   protected ForeignKeyMetaData[] foreignKeyMetaData;
   protected UniqueMetaData[] uniqueMetaData;
   protected List members;
   protected List unmappedColumns;
   protected Set fetchGroups;
   protected List joins;
   protected List foreignKeys;
   protected List indexes;
   protected List uniqueConstraints;
   protected AbstractMemberMetaData[] managedMembers;
   protected AbstractMemberMetaData[] overriddenMembers;
   protected Map memberPositionsByName;
   protected int[] allMemberPositions;
   protected int[] pkMemberPositions;
   protected int[] nonPkMemberPositions;
   protected boolean[] nonPkMemberFlags;
   protected int[] dfgMemberPositions;
   protected boolean[] dfgMemberFlags;
   protected int[] scoMutableMemberPositions;
   protected boolean[] scoMutableMemberFlags;
   protected int[] scoNonContainerMemberPositions;
   protected int[] relationPositions;
   protected int noOfInheritedManagedMembers;
   protected boolean usesSingleFieldIdentityClass;
   protected int memberCount;
   protected boolean implementationOfPersistentDefinition;
   boolean populating;
   boolean initialising;
   protected Boolean fetchGroupMetaWithPostLoad;
   protected Boolean pkIsDatastoreAttributed;
   protected Boolean hasRelations;
   protected transient boolean persistentInterfaceImplNeedingTableFromSuperclass;
   protected transient boolean persistentInterfaceImplNeedingTableFromSubclass;
   protected int[] secondClassContainerMemberPositions;

   protected AbstractClassMetaData(PackageMetaData parent, String name) {
      super(parent);
      this.mappedSuperclass = false;
      this.instantiable = true;
      this.accessViaField = null;
      this.identityType = IdentityType.DATASTORE;
      this.persistenceModifier = ClassPersistenceModifier.PERSISTENCE_CAPABLE;
      this.requiresExtent = true;
      this.detachable = false;
      this.embeddedOnly = false;
      this.cacheable = null;
      this.identitySpecified = false;
      this.listeners = null;
      this.excludeSuperClassListeners = null;
      this.excludeDefaultListeners = null;
      this.pcSuperclassMetaData = null;
      this.metaDataComplete = false;
      this.serializeRead = false;
      this.queries = null;
      this.storedProcQueries = null;
      this.queryResultMetaData = null;
      this.members = new ArrayList();
      this.unmappedColumns = null;
      this.fetchGroups = new HashSet();
      this.joins = new ArrayList();
      this.foreignKeys = new ArrayList();
      this.indexes = new ArrayList();
      this.uniqueConstraints = new ArrayList();
      this.scoNonContainerMemberPositions = null;
      this.relationPositions = null;
      this.noOfInheritedManagedMembers = 0;
      this.implementationOfPersistentDefinition = false;
      this.populating = false;
      this.initialising = false;
      this.pkIsDatastoreAttributed = null;
      this.hasRelations = null;
      this.persistentInterfaceImplNeedingTableFromSuperclass = false;
      this.persistentInterfaceImplNeedingTableFromSubclass = false;
      this.secondClassContainerMemberPositions = null;
      if (StringUtils.isWhitespace(name)) {
         throw new InvalidMetaDataException("044061", parent.name);
      } else {
         this.name = name;
         this.fullName = ClassUtils.createFullClassName(parent.name, name);
      }
   }

   public AbstractClassMetaData(InterfaceMetaData imd, String implClassName, boolean copyMembers) {
      this((PackageMetaData)imd.parent, implClassName);
      this.setMappedSuperclass(imd.mappedSuperclass);
      this.setRequiresExtent(imd.requiresExtent);
      this.setDetachable(imd.detachable);
      this.setTable(imd.table);
      this.setCatalog(imd.catalog);
      this.setSchema(imd.schema);
      this.setEntityName(imd.entityName);
      this.setObjectIdClass(imd.objectidClass);
      this.setPersistenceModifier(ClassPersistenceModifier.PERSISTENCE_CAPABLE);
      this.setEmbeddedOnly(imd.embeddedOnly);
      this.setIdentityType(imd.identityType);
      this.implementationOfPersistentDefinition = true;
      if (copyMembers) {
         this.copyMembersFromInterface(imd);
      }

      this.setVersionMetaData(imd.versionMetaData);
      this.setIdentityMetaData(imd.identityMetaData);
      this.setPrimaryKeyMetaData(imd.primaryKeyMetaData);
      if (imd.inheritanceMetaData != null) {
         if (imd.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
            this.persistentInterfaceImplNeedingTableFromSuperclass = true;
         } else if (imd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
            this.persistentInterfaceImplNeedingTableFromSubclass = true;
         }

         InheritanceMetaData inhmd = new InheritanceMetaData();
         inhmd.setStrategy(InheritanceStrategy.NEW_TABLE);
         if (imd.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
            for(AbstractClassMetaData acmd = imd.getSuperAbstractClassMetaData(); acmd != null; acmd = acmd.getSuperAbstractClassMetaData()) {
               if (acmd.getInheritanceMetaData() != null && acmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE) {
                  if (acmd.getInheritanceMetaData().getDiscriminatorMetaData() != null) {
                     inhmd.setDiscriminatorMetaData(new DiscriminatorMetaData(acmd.getInheritanceMetaData().getDiscriminatorMetaData()));
                  }

                  inhmd.setJoinMetaData(acmd.getInheritanceMetaData().getJoinMetaData());
                  break;
               }
            }
         } else if (imd.inheritanceMetaData.getStrategy() == InheritanceStrategy.NEW_TABLE) {
            if (imd.getInheritanceMetaData().getDiscriminatorMetaData() != null) {
               inhmd.setDiscriminatorMetaData(new DiscriminatorMetaData(imd.getInheritanceMetaData().getDiscriminatorMetaData()));
            }

            inhmd.setJoinMetaData(imd.getInheritanceMetaData().getJoinMetaData());
         }

         this.setInheritanceMetaData(inhmd);
      }

      if (imd.joinMetaData != null) {
         for(int i = 0; i < imd.joinMetaData.length; ++i) {
            this.addJoin(imd.joinMetaData[i]);
         }
      }

      if (imd.foreignKeyMetaData != null) {
         for(int i = 0; i < imd.foreignKeyMetaData.length; ++i) {
            this.addForeignKey(imd.foreignKeyMetaData[i]);
         }
      }

      if (imd.indexMetaData != null) {
         for(int i = 0; i < imd.indexMetaData.length; ++i) {
            this.addIndex(imd.indexMetaData[i]);
         }
      }

      if (imd.uniqueMetaData != null) {
         for(int i = 0; i < imd.uniqueMetaData.length; ++i) {
            this.addUniqueConstraint(imd.uniqueMetaData[i]);
         }
      }

      if (imd.fetchGroups != null) {
         for(FetchGroupMetaData fgmd : imd.fetchGroups) {
            this.addFetchGroup(fgmd);
         }
      }

      if (imd.queries != null) {
         for(QueryMetaData query : imd.queries) {
            this.addQuery(query);
         }
      }

      if (imd.storedProcQueries != null) {
         for(StoredProcQueryMetaData query : imd.storedProcQueries) {
            this.addStoredProcQuery(query);
         }
      }

      if (imd.listeners != null) {
         if (this.listeners == null) {
            this.listeners = new ArrayList();
         }

         this.listeners.addAll(imd.listeners);
      }

   }

   public AbstractClassMetaData(ClassMetaData cmd, String implClassName) {
      this((PackageMetaData)cmd.parent, implClassName);
      this.setMappedSuperclass(cmd.mappedSuperclass);
      this.setRequiresExtent(cmd.requiresExtent);
      this.setDetachable(cmd.detachable);
      this.setCatalog(cmd.catalog);
      this.setSchema(cmd.schema);
      this.setTable(cmd.table);
      this.setEntityName(cmd.entityName);
      this.setPersistenceModifier(ClassPersistenceModifier.PERSISTENCE_CAPABLE);
      this.setEmbeddedOnly(cmd.embeddedOnly);
      this.setIdentityType(cmd.identityType);
      this.persistableSuperclass = cmd.getFullClassName();
      this.implementationOfPersistentDefinition = true;

      for(int i = 0; i < cmd.getMemberCount(); ++i) {
         FieldMetaData fmd = new FieldMetaData(this, cmd.getMetaDataForManagedMemberAtAbsolutePosition(i));
         fmd.persistenceModifier = FieldPersistenceModifier.NONE;
         fmd.primaryKey = Boolean.FALSE;
         fmd.defaultFetchGroup = Boolean.FALSE;
         this.addMember(fmd);
      }

   }

   public boolean isInstantiable() {
      return this.instantiable;
   }

   protected AbstractClassMetaData getRootInstantiableClass() {
      if (this.pcSuperclassMetaData == null) {
         return this.instantiable ? this : null;
      } else {
         AbstractClassMetaData rootCmd = this.pcSuperclassMetaData.getRootInstantiableClass();
         return rootCmd == null && this.instantiable ? this : rootCmd;
      }
   }

   public boolean isRootInstantiableClass() {
      return this.getRootInstantiableClass() == this;
   }

   public boolean isImplementationOfPersistentDefinition() {
      return this.implementationOfPersistentDefinition;
   }

   protected void copyMembersFromInterface(InterfaceMetaData imd) {
      for(int i = 0; i < imd.getMemberCount(); ++i) {
         FieldMetaData fmd = new FieldMetaData(this, imd.getMetaDataForManagedMemberAtAbsolutePosition(i));
         this.addMember(fmd);
      }

   }

   protected void checkInitialised() {
      if (!this.isInitialised()) {
         throw (new NucleusException(Localiser.msg("044069", this.fullName))).setFatal();
      }
   }

   protected void checkPopulated() {
      if (!this.isPopulated() && !this.isInitialised()) {
         throw (new NucleusException(Localiser.msg("044070", this.fullName))).setFatal();
      }
   }

   protected void checkNotYetPopulated() {
      if (this.isPopulated() || this.isInitialised()) {
         throw new NucleusUserException("Already populated/initialised");
      }
   }

   protected Class loadClass(ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (clr == null) {
         NucleusLogger.METADATA.warn(Localiser.msg("044067", this.fullName));
         clr = mmgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      }

      try {
         Class cls = clr.classForName(this.fullName, primary, false);
         if (cls == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044080", this.fullName));
            throw new InvalidClassMetaDataException("044080", new Object[]{this.fullName});
         } else {
            return cls;
         }
      } catch (ClassNotResolvedException cnre) {
         NucleusLogger.METADATA.error(Localiser.msg("044080", this.fullName));
         NucleusException ne = new InvalidClassMetaDataException("044080", new Object[]{this.fullName});
         ne.setNestedException(cnre);
         throw ne;
      }
   }

   protected void determineIdentity() {
      if (this.identityType == null) {
         if (this.objectidClass != null) {
            this.identityType = IdentityType.APPLICATION;
         } else {
            int noOfPkKeys = 0;

            for(AbstractMemberMetaData mmd : this.members) {
               if (mmd.isPrimaryKey()) {
                  ++noOfPkKeys;
               }
            }

            if (noOfPkKeys > 0) {
               this.identityType = IdentityType.APPLICATION;
            } else {
               this.identityType = IdentityType.DATASTORE;
            }
         }
      }

   }

   protected void determineSuperClassName(ClassLoaderResolver clr, Class cls, MetaDataManager mmgr) {
      String realPcSuperclassName = null;
      Collection<Class<?>> superclasses;
      if (cls.isInterface()) {
         superclasses = ClassUtils.getSuperinterfaces(cls);
      } else {
         superclasses = ClassUtils.getSuperclasses(cls);
      }

      for(Class superclass : superclasses) {
         AbstractClassMetaData superCmd = mmgr.getMetaDataForClassInternal(superclass, clr);
         if (superCmd != null && superCmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
            realPcSuperclassName = superclass.getName();
            break;
         }
      }

      this.persistableSuperclass = realPcSuperclassName;
      if (this.persistableSuperclass != null) {
         Class pcsc = null;

         try {
            pcsc = clr.classForName(this.persistableSuperclass);
         } catch (ClassNotResolvedException var10) {
            throw new InvalidClassMetaDataException("044081", new Object[]{this.fullName, this.persistableSuperclass});
         }

         if (this.persistableSuperclass.equals(this.fullName) || !pcsc.isAssignableFrom(cls)) {
            throw new InvalidClassMetaDataException("044082", new Object[]{this.fullName, this.persistableSuperclass});
         }

         if (mmgr != null) {
            this.pcSuperclassMetaData = mmgr.getMetaDataForClassInternal(pcsc, clr);
            if (this.pcSuperclassMetaData == null) {
               throw new InvalidClassMetaDataException("044083", new Object[]{this.fullName, this.persistableSuperclass});
            }
         } else {
            String superclass_pkg_name = this.persistableSuperclass.substring(0, this.persistableSuperclass.lastIndexOf(46));
            PackageMetaData pmd = this.getPackageMetaData().getFileMetaData().getPackage(superclass_pkg_name);
            if (pmd != null) {
               String superclass_class_name = this.persistableSuperclass.substring(this.persistableSuperclass.lastIndexOf(46) + 1);
               this.pcSuperclassMetaData = pmd.getClass(superclass_class_name);
            }
         }

         if (this.pcSuperclassMetaData == null) {
            throw new InvalidClassMetaDataException("044084", new Object[]{this.fullName, this.persistableSuperclass});
         }

         if (!this.pcSuperclassMetaData.isPopulated() && !this.pcSuperclassMetaData.isInitialised()) {
            this.pcSuperclassMetaData.populate(clr, cls.getClassLoader(), mmgr);
         }
      }

      if (this.persistableSuperclass != null && !this.isDetachable() && this.pcSuperclassMetaData.isDetachable()) {
         this.detachable = true;
      }

   }

   protected void validateUserInputForIdentity() {
      if (this.pcSuperclassMetaData != null) {
         AbstractClassMetaData baseCmd = this.getBaseAbstractClassMetaData();
         IdentityMetaData baseImd = baseCmd.getIdentityMetaData();
         if (baseCmd.identitySpecified && this.identitySpecified && baseImd != null && baseImd.getValueStrategy() != null && this.identityMetaData != null && this.identityMetaData.getValueStrategy() != null && this.identityMetaData.getValueStrategy() != baseImd.getValueStrategy() && this.identityMetaData.getValueStrategy() != null && this.identityMetaData.getValueStrategy() != IdentityStrategy.NATIVE) {
            throw new InvalidClassMetaDataException("044094", new Object[]{this.fullName, this.identityMetaData.getValueStrategy(), baseImd.getValueStrategy()});
         }

         if (baseCmd.identitySpecified && this.identityMetaData != null && baseImd.getValueStrategy() != this.identityMetaData.getValueStrategy()) {
            this.identityMetaData.setValueStrategy(baseImd.getValueStrategy());
         }
      }

   }

   public AbstractClassMetaData getBaseAbstractClassMetaData() {
      return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getBaseAbstractClassMetaData() : this;
   }

   public boolean isDescendantOf(AbstractClassMetaData cmd) {
      if (this.pcSuperclassMetaData == null) {
         return false;
      } else {
         return this.pcSuperclassMetaData == cmd ? true : this.pcSuperclassMetaData.isDescendantOf(cmd);
      }
   }

   protected String getBaseInheritanceStrategy() {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getStrategyForTree() != null) {
         return this.inheritanceMetaData.getStrategyForTree();
      } else {
         return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getBaseInheritanceStrategy() : null;
      }
   }

   protected void inheritIdentity() {
      if (this.objectidClass != null) {
         this.objectidClass = ClassUtils.createFullClassName(((PackageMetaData)this.parent).name, this.objectidClass);
      }

      if (this.persistableSuperclass != null) {
         if (this.objectidClass != null) {
            String superObjectIdClass = this.pcSuperclassMetaData.getObjectidClass();
            if (superObjectIdClass == null || !this.objectidClass.equals(superObjectIdClass)) {
               throw new InvalidClassMetaDataException("044085", new Object[]{this.fullName, this.persistableSuperclass});
            }

            NucleusLogger.METADATA.info(Localiser.msg("044086", this.name, this.persistableSuperclass));
         } else {
            this.objectidClass = this.pcSuperclassMetaData.getObjectidClass();
         }

         if (this.identityType == null) {
            this.identityType = this.pcSuperclassMetaData.getIdentityType();
         }

         if (this.identityType != null && !this.identityType.equals(this.pcSuperclassMetaData.getIdentityType())) {
            throw new InvalidClassMetaDataException("044093", new Object[]{this.fullName});
         }

         if (this.pcSuperclassMetaData.getIdentityType() == IdentityType.APPLICATION && this.pcSuperclassMetaData.getNoOfPopulatedPKMembers() > 0) {
            int noOfPkKeys = 0;

            for(AbstractMemberMetaData mmd : this.members) {
               if (mmd.isPrimaryKey() && mmd.fieldBelongsToClass()) {
                  ++noOfPkKeys;
               }
            }

            if (noOfPkKeys > 0) {
               throw new InvalidClassMetaDataException("044034", new Object[]{this.getFullClassName(), noOfPkKeys, this.pcSuperclassMetaData.getNoOfPopulatedPKMembers()});
            }
         }
      }

   }

   protected AbstractMemberMetaData newDefaultedProperty(String name) {
      return new PropertyMetaData(this, name);
   }

   protected void validateUserInputForInheritanceMetaData(boolean isAbstract) {
      if (this.mappedSuperclass) {
         String baseInhStrategy = this.getBaseInheritanceStrategy();
         if (baseInhStrategy != null && baseInhStrategy.equalsIgnoreCase("SINGLE_TABLE") && this.getSuperclassManagingTable() != null && this.inheritanceMetaData != null) {
            this.inheritanceMetaData.setStrategy(InheritanceStrategy.SUPERCLASS_TABLE);
         }
      }

      if (this.inheritanceMetaData != null) {
         if (this.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
            AbstractClassMetaData superCmd = this.getClassManagingTable();
            if (superCmd == null) {
               throw new InvalidClassMetaDataException("044099", new Object[]{this.fullName});
            }

            DiscriminatorMetaData superDismd = superCmd.getInheritanceMetaData().getDiscriminatorMetaData();
            if (superDismd == null) {
               throw new InvalidClassMetaDataException("044100", new Object[]{this.fullName, superCmd.fullName});
            }

            DiscriminatorMetaData dismd = this.inheritanceMetaData.getDiscriminatorMetaData();
            if (superDismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP && (dismd == null || dismd.getValue() == null) && !this.mappedSuperclass && !isAbstract) {
               throw new InvalidClassMetaDataException("044102", new Object[]{this.fullName, superCmd.fullName, superDismd.getColumnName()});
            }
         }

         if (isAbstract) {
            DiscriminatorMetaData dismd = this.inheritanceMetaData.getDiscriminatorMetaData();
            if (dismd != null && !StringUtils.isWhitespace(dismd.getValue())) {
               NucleusLogger.METADATA.info(Localiser.msg("044105", this.fullName));
            }
         } else {
            DiscriminatorMetaData dismd = this.inheritanceMetaData.getDiscriminatorMetaData();
            if (dismd != null && dismd.getColumnMetaData() != null && this.pcSuperclassMetaData != null) {
               ColumnMetaData superDiscrimColmd = this.pcSuperclassMetaData.getDiscriminatorColumnMetaData();
               if (superDiscrimColmd != null) {
                  NucleusLogger.GENERAL.debug(Localiser.msg("044126", this.fullName));
               }
            }
         }
      }

   }

   protected void determineInheritanceMetaData(MetaDataManager mmgr) {
      if (this.inheritanceMetaData == null) {
         if (this.pcSuperclassMetaData != null) {
            AbstractClassMetaData baseCmd = this.getBaseAbstractClassMetaData();
            if (this.getBaseInheritanceStrategy() != null) {
               String treeStrategy = this.getBaseInheritanceStrategy();
               if (treeStrategy.equals("JOINED")) {
                  this.inheritanceMetaData = new InheritanceMetaData();
                  this.inheritanceMetaData.setStrategy(InheritanceStrategy.NEW_TABLE);
                  return;
               }

               if (treeStrategy.equals("SINGLE_TABLE")) {
                  this.inheritanceMetaData = new InheritanceMetaData();
                  this.inheritanceMetaData.setStrategy(InheritanceStrategy.SUPERCLASS_TABLE);
                  return;
               }

               if (treeStrategy.equals("TABLE_PER_CLASS")) {
                  this.inheritanceMetaData = new InheritanceMetaData();
                  this.inheritanceMetaData.setStrategy(InheritanceStrategy.COMPLETE_TABLE);
                  return;
               }
            }

            if (baseCmd.getInheritanceMetaData() != null && baseCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               this.inheritanceMetaData = new InheritanceMetaData();
               this.inheritanceMetaData.setStrategy(InheritanceStrategy.COMPLETE_TABLE);
            } else if (this.pcSuperclassMetaData.getInheritanceMetaData() != null && this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
               this.inheritanceMetaData = new InheritanceMetaData();
               this.inheritanceMetaData.setStrategy(InheritanceStrategy.NEW_TABLE);
            } else if (mmgr.getNucleusContext().getConfiguration().getStringProperty("datanucleus.metadata.defaultInheritanceStrategy").equalsIgnoreCase("TABLE_PER_CLASS")) {
               this.inheritanceMetaData = new InheritanceMetaData();
               this.inheritanceMetaData.setStrategy(InheritanceStrategy.NEW_TABLE);
            } else {
               this.inheritanceMetaData = new InheritanceMetaData();
               this.inheritanceMetaData.setStrategy(InheritanceStrategy.SUPERCLASS_TABLE);
            }
         } else {
            this.inheritanceMetaData = new InheritanceMetaData();
            this.inheritanceMetaData.setStrategy(InheritanceStrategy.NEW_TABLE);
         }

      } else {
         if (this.inheritanceMetaData.getStrategy() == null) {
            if (this.getBaseInheritanceStrategy() != null) {
               String treeStrategy = this.getBaseInheritanceStrategy();
               if (treeStrategy.equalsIgnoreCase("SINGLE_TABLE")) {
                  if (this.pcSuperclassMetaData != null) {
                     if (this.pcSuperclassMetaData.getInheritanceMetaData() != null && this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                        this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
                     } else {
                        this.inheritanceMetaData.strategy = InheritanceStrategy.SUPERCLASS_TABLE;
                     }
                  } else {
                     this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
                  }
               } else if (treeStrategy.equalsIgnoreCase("TABLE_PER_CLASS")) {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.COMPLETE_TABLE;
               } else if (treeStrategy.equalsIgnoreCase("JOINED")) {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
               }

               return;
            }

            if (this.pcSuperclassMetaData != null) {
               String treeStrategy = this.getBaseInheritanceStrategy();
               InheritanceStrategy baseStrategy = null;
               if (treeStrategy != null && treeStrategy.equalsIgnoreCase("SINGLE_TABLE")) {
                  baseStrategy = InheritanceStrategy.SUPERCLASS_TABLE;
               } else if (treeStrategy != null && treeStrategy.equalsIgnoreCase("TABLE_PER_CLASS")) {
                  baseStrategy = InheritanceStrategy.COMPLETE_TABLE;
               } else if (treeStrategy != null && treeStrategy.equalsIgnoreCase("JOINED")) {
                  baseStrategy = InheritanceStrategy.NEW_TABLE;
               } else {
                  AbstractClassMetaData baseCmd = this.getBaseAbstractClassMetaData();
                  if (baseCmd.getInheritanceMetaData() != null) {
                     baseStrategy = baseCmd.getInheritanceMetaData().getStrategy();
                  }
               }

               if (baseStrategy == InheritanceStrategy.COMPLETE_TABLE) {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.COMPLETE_TABLE;
               } else if (this.pcSuperclassMetaData.getInheritanceMetaData() != null && this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
               } else if (mmgr.getNucleusContext().getConfiguration().getStringProperty("datanucleus.metadata.defaultInheritanceStrategy").equalsIgnoreCase("TABLE_PER_CLASS")) {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
               } else {
                  this.inheritanceMetaData.strategy = InheritanceStrategy.SUPERCLASS_TABLE;
               }
            } else {
               this.inheritanceMetaData.strategy = InheritanceStrategy.NEW_TABLE;
            }
         }

      }
   }

   protected void applyDefaultDiscriminatorValueWhenNotSpecified(MetaDataManager mmgr) {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
         AbstractClassMetaData superCmd = this.getClassManagingTable();
         if (superCmd == null) {
            throw new InvalidClassMetaDataException("044064", new Object[]{this.getFullClassName()});
         }

         if (superCmd.getInheritanceMetaData() != null) {
            DiscriminatorMetaData superDismd = superCmd.getInheritanceMetaData().getDiscriminatorMetaData();
            DiscriminatorMetaData dismd = this.inheritanceMetaData.getDiscriminatorMetaData();
            if (superDismd != null && superDismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP && (dismd == null || dismd.getValue() == null)) {
               if (dismd == null) {
                  dismd = this.inheritanceMetaData.newDiscriminatorMetadata();
               }

               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug("No discriminator value specified for " + this.getFullClassName() + " so using fully-qualified class name");
               }

               dismd.setValue(this.getFullClassName());
            }
         }
      }

      if (this.inheritanceMetaData != null) {
         DiscriminatorMetaData dismd = this.inheritanceMetaData.getDiscriminatorMetaData();
         if (dismd != null && this.getDiscriminatorStrategy() == DiscriminatorStrategy.VALUE_MAP && dismd.getValue() != null) {
            mmgr.registerDiscriminatorValueForClass(this, dismd.getValue());
         }
      }

   }

   protected void validateUnmappedColumns() {
      if (this.unmappedColumns != null && this.unmappedColumns.size() > 0) {
         for(ColumnMetaData colmd : this.unmappedColumns) {
            if (colmd.getName() == null) {
               throw new InvalidClassMetaDataException("044119", new Object[]{this.fullName});
            }

            if (colmd.getJdbcType() == null) {
               throw new InvalidClassMetaDataException("044120", new Object[]{this.fullName, colmd.getName()});
            }
         }
      }

   }

   private AbstractClassMetaData getSuperclassManagingTable() {
      if (this.pcSuperclassMetaData != null) {
         return this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE ? this.pcSuperclassMetaData : this.pcSuperclassMetaData.getSuperclassManagingTable();
      } else {
         return null;
      }
   }

   private AbstractClassMetaData getClassManagingTable() {
      if (this.inheritanceMetaData == null) {
         return this;
      } else if (this.inheritanceMetaData.getStrategy() == InheritanceStrategy.NEW_TABLE) {
         return this;
      } else if (this.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
         return this.pcSuperclassMetaData == null ? null : this.pcSuperclassMetaData.getClassManagingTable();
      } else {
         return null;
      }
   }

   public final AbstractClassMetaData getSuperAbstractClassMetaData() {
      this.checkPopulated();
      return this.pcSuperclassMetaData;
   }

   public boolean pkIsDatastoreAttributed(StoreManager storeMgr) {
      if (this.pkIsDatastoreAttributed == null) {
         this.pkIsDatastoreAttributed = Boolean.FALSE;
         if (this.identityType == IdentityType.APPLICATION) {
            for(int i = 0; i < this.pkMemberPositions.length; ++i) {
               if (storeMgr.isStrategyDatastoreAttributed(this, this.pkMemberPositions[i])) {
                  this.pkIsDatastoreAttributed = true;
               }
            }
         } else if (this.identityType == IdentityType.DATASTORE) {
            this.pkIsDatastoreAttributed = storeMgr.isStrategyDatastoreAttributed(this, -1);
         }
      }

      return this.pkIsDatastoreAttributed;
   }

   protected void determineObjectIdClass(MetaDataManager mmgr) {
      if (this.identityType == IdentityType.APPLICATION && this.objectidClass == null) {
         int no_of_pk_fields = 0;
         AbstractMemberMetaData mmd_pk = null;

         for(AbstractMemberMetaData mmd : this.members) {
            if (mmd.isPrimaryKey()) {
               mmd_pk = mmd;
               ++no_of_pk_fields;
            }
         }

         if (no_of_pk_fields == 0 && this.inheritanceMetaData.getStrategy() == InheritanceStrategy.SUBCLASS_TABLE && this.getSuperclassManagingTable() == null) {
            NucleusLogger.METADATA.debug(Localiser.msg("044163", this.getFullClassName()));
            this.instantiable = false;
         } else {
            boolean needsObjectidClass = false;
            if (this.persistableSuperclass == null) {
               needsObjectidClass = true;
            } else if (this.getSuperclassManagingTable() == null) {
               needsObjectidClass = true;
            }

            if (needsObjectidClass) {
               if (no_of_pk_fields == 0) {
                  NucleusLogger.METADATA.error(Localiser.msg("044065", this.fullName, "" + no_of_pk_fields));
                  throw new InvalidClassMetaDataException("044065", new Object[]{this.fullName, "" + no_of_pk_fields});
               }

               if (no_of_pk_fields > 1) {
                  NucleusLogger.METADATA.warn(Localiser.msg("044065", this.fullName, "" + no_of_pk_fields));
                  if (!mmgr.isEnhancing()) {
                     this.objectidClass = this.fullName + "_PK";
                     NucleusLogger.METADATA.debug(Localiser.msg("044164", this.fullName, "" + this.getNoOfPrimaryKeyMembers(), this.objectidClass));
                  }
               } else {
                  Class pk_type = mmd_pk.getType();
                  if (!Byte.class.isAssignableFrom(pk_type) && !Byte.TYPE.isAssignableFrom(pk_type)) {
                     if (!Character.class.isAssignableFrom(pk_type) && !Character.TYPE.isAssignableFrom(pk_type)) {
                        if (!Integer.class.isAssignableFrom(pk_type) && !Integer.TYPE.isAssignableFrom(pk_type)) {
                           if (!Long.class.isAssignableFrom(pk_type) && !Long.TYPE.isAssignableFrom(pk_type)) {
                              if (!Short.class.isAssignableFrom(pk_type) && !Short.TYPE.isAssignableFrom(pk_type)) {
                                 if (String.class.isAssignableFrom(pk_type)) {
                                    this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_STRING;
                                 } else {
                                    if (!Object.class.isAssignableFrom(pk_type)) {
                                       NucleusLogger.METADATA.error(Localiser.msg("044066", this.fullName, pk_type.getName()));
                                       throw new InvalidClassMetaDataException("044066", new Object[]{this.fullName, pk_type.getName()});
                                    }

                                    this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_OBJECT;
                                 }
                              } else {
                                 this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_SHORT;
                              }
                           } else {
                              this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_LONG;
                           }
                        } else {
                           this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_INT;
                        }
                     } else {
                        this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_CHAR;
                     }
                  } else {
                     this.objectidClass = ClassNameConstants.IDENTITY_SINGLEFIELD_BYTE;
                  }
               }
            }

         }
      }
   }

   protected void validateObjectIdClass(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.getPersistableSuperclass() == null && this.objectidClass != null) {
         ApiAdapter api = mmgr.getApiAdapter();
         Class obj_cls = null;

         try {
            obj_cls = clr.classForName(this.objectidClass);
         } catch (ClassNotResolvedException var9) {
            throw new InvalidClassMetaDataException("044079", new Object[]{this.fullName, this.objectidClass});
         }

         boolean validated = false;
         Set errors = new HashSet();

         try {
            if (api.isValidPrimaryKeyClass(obj_cls, this, clr, this.getNoOfPopulatedPKMembers(), mmgr)) {
               validated = true;
            }
         } catch (NucleusException ex) {
            errors.add(ex);
         }

         if (!validated) {
            throw new NucleusUserException(Localiser.msg("019016", this.getFullClassName(), obj_cls.getName()), (Throwable[])errors.toArray(new Throwable[errors.size()]));
         }
      }

   }

   public abstract void populate(ClassLoaderResolver var1, ClassLoader var2, MetaDataManager var3);

   public abstract void initialise(ClassLoaderResolver var1, MetaDataManager var2);

   protected void initialiseMemberPositionInformation(MetaDataManager mmgr) {
      this.memberCount = this.noOfInheritedManagedMembers + this.managedMembers.length;
      this.dfgMemberFlags = new boolean[this.memberCount];
      this.scoMutableMemberFlags = new boolean[this.memberCount];
      this.nonPkMemberFlags = new boolean[this.memberCount];
      int pk_field_count = 0;
      int dfg_field_count = 0;
      int scm_field_count = 0;

      for(int i = 0; i < this.memberCount; ++i) {
         AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePositionInternal(i);
         if (mmd.isPrimaryKey()) {
            ++pk_field_count;
         } else {
            this.nonPkMemberFlags[i] = true;
         }

         if (mmd.isDefaultFetchGroup()) {
            this.dfgMemberFlags[i] = true;
            ++dfg_field_count;
         }

         if (mmd.calcIsSecondClassMutable(mmgr)) {
            this.scoMutableMemberFlags[i] = true;
            ++scm_field_count;
         }
      }

      if (pk_field_count > 0 && this.identityType != IdentityType.APPLICATION) {
         throw new InvalidClassMetaDataException("044078", new Object[]{this.fullName, pk_field_count, this.identityType});
      } else {
         if (pk_field_count > 0) {
            this.pkMemberPositions = new int[pk_field_count];
            int i = 0;

            for(int pk_num = 0; i < this.memberCount; ++i) {
               AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePositionInternal(i);
               if (mmd.isPrimaryKey()) {
                  this.pkMemberPositions[pk_num++] = i;
               }
            }
         } else if (this.instantiable && pk_field_count == 0 && this.identityType == IdentityType.APPLICATION) {
            throw new InvalidClassMetaDataException("044077", new Object[]{this.fullName, this.objectidClass});
         }

         this.nonPkMemberPositions = new int[this.memberCount - pk_field_count];
         int i = 0;

         for(int npkf = 0; i < this.memberCount; ++i) {
            AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePositionInternal(i);
            if (!mmd.isPrimaryKey()) {
               this.nonPkMemberPositions[npkf++] = i;
            }
         }

         this.dfgMemberPositions = new int[dfg_field_count];
         this.scoMutableMemberPositions = new int[scm_field_count];
         i = 0;
         int dfg_num = 0;

         for(int scm_num = 0; i < this.memberCount; ++i) {
            if (this.dfgMemberFlags[i]) {
               this.dfgMemberPositions[dfg_num++] = i;
            }

            if (this.scoMutableMemberFlags[i]) {
               this.scoMutableMemberPositions[scm_num++] = i;
            }
         }

      }
   }

   void getReferencedClassMetaData(List orderedCMDs, Set referencedCMDs, ClassLoaderResolver clr, MetaDataManager mmgr) {
      Map viewReferences = new HashMap();
      this.getReferencedClassMetaData(orderedCMDs, referencedCMDs, viewReferences, clr, mmgr);
   }

   private void getReferencedClassMetaData(final List orderedCMDs, final Set referencedCMDs, final Map viewReferences, final ClassLoaderResolver clr, final MetaDataManager mmgr) {
      if (!referencedCMDs.contains(this)) {
         referencedCMDs.add(this);

         for(int i = 0; i < this.managedMembers.length; ++i) {
            AbstractMemberMetaData mmd = this.managedMembers[i];
            mmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
         }

         if (this.persistableSuperclass != null) {
            AbstractClassMetaData superCmd = this.getSuperAbstractClassMetaData();
            superCmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
         }

         if (this.objectidClass != null && !this.usesSingleFieldIdentityClass()) {
            AbstractClassMetaData idCmd = mmgr.getMetaDataForClass(this.objectidClass, clr);
            if (idCmd != null) {
               idCmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, mmgr);
            }
         }

         String viewDefStr = this.getValueForExtension("view-definition");
         if (viewDefStr != null) {
            MacroString viewDef = new MacroString(this.fullName, this.getValueForExtension("view-imports"), viewDefStr);
            viewDef.substituteMacros(new MacroString.MacroHandler() {
               public void onIdentifierMacro(MacroString.IdentifierMacro im) {
                  if (!AbstractClassMetaData.this.getFullClassName().equals(im.className)) {
                     AbstractClassMetaData.this.addViewReference(viewReferences, im.className);
                     AbstractClassMetaData view_cmd = mmgr.getMetaDataForClass(im.className, clr);
                     view_cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, viewReferences, clr, mmgr);
                  }

               }

               public void onParameterMacro(MacroString.ParameterMacro pm) {
                  throw new NucleusUserException("Parameter macros not allowed in view definitions: " + pm);
               }
            }, clr);
         }

         orderedCMDs.add(this);
      }

   }

   private void addViewReference(Map viewReferences, String referenced_name) {
      if (this.fullName.equals(referenced_name)) {
         Set referencedSet = (Set)viewReferences.get(referenced_name);
         if (referencedSet == null) {
            referencedSet = new HashSet();
            viewReferences.put(this.fullName, referencedSet);
         }

         referencedSet.add(referenced_name);
         ViewUtils.checkForCircularViewReferences(viewReferences, this.fullName, referenced_name, (List)null);
      }

   }

   public int getNoOfQueries() {
      return this.queries.size();
   }

   public QueryMetaData[] getQueries() {
      return this.queries == null ? null : (QueryMetaData[])((QueryMetaData[])this.queries.toArray(new QueryMetaData[this.queries.size()]));
   }

   public int getNoOfStoredProcQueries() {
      return this.storedProcQueries.size();
   }

   public StoredProcQueryMetaData[] getStoredProcQueries() {
      return this.storedProcQueries == null ? null : (StoredProcQueryMetaData[])((StoredProcQueryMetaData[])this.storedProcQueries.toArray(new StoredProcQueryMetaData[this.storedProcQueries.size()]));
   }

   public QueryResultMetaData[] getQueryResultMetaData() {
      return this.queryResultMetaData == null ? null : (QueryResultMetaData[])this.queryResultMetaData.toArray(new QueryResultMetaData[this.queryResultMetaData.size()]);
   }

   public final VersionMetaData getVersionMetaData() {
      return this.versionMetaData;
   }

   public final VersionMetaData getVersionMetaDataForClass() {
      if (this.versionMetaData != null) {
         return this.versionMetaData;
      } else {
         return this.getSuperAbstractClassMetaData() != null ? this.getSuperAbstractClassMetaData().getVersionMetaDataForClass() : null;
      }
   }

   public final boolean isVersioned() {
      VersionMetaData vermd = this.getVersionMetaDataForClass();
      return vermd != null && vermd.getVersionStrategy() != null && vermd.getVersionStrategy() != VersionStrategy.NONE;
   }

   public final VersionMetaData getVersionMetaDataForTable() {
      if (this.pcSuperclassMetaData != null) {
         if (this.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE && this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE) {
            VersionMetaData vermd = this.pcSuperclassMetaData.getVersionMetaDataForTable();
            if (vermd != null) {
               return vermd;
            }
         }

         if (this.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE && this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
            VersionMetaData vermd = this.pcSuperclassMetaData.getVersionMetaDataForTable();
            if (vermd != null) {
               return vermd;
            }
         }

         if (this.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
            VersionMetaData vermd = this.pcSuperclassMetaData.getVersionMetaDataForTable();
            if (vermd != null) {
               return vermd;
            }
         }
      }

      return this.versionMetaData;
   }

   public final DiscriminatorMetaData getDiscriminatorMetaDataForTable() {
      if (this.pcSuperclassMetaData != null && (this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE || this.pcSuperclassMetaData.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE)) {
         DiscriminatorMetaData superDismd = this.pcSuperclassMetaData.getDiscriminatorMetaDataForTable();
         if (superDismd != null) {
            return superDismd;
         }
      }

      return this.inheritanceMetaData != null ? this.inheritanceMetaData.getDiscriminatorMetaData() : null;
   }

   public final DiscriminatorStrategy getDiscriminatorStrategyForTable() {
      if (this.inheritanceMetaData == null) {
         return null;
      } else if (this.inheritanceMetaData.getStrategy() == InheritanceStrategy.NEW_TABLE && this.inheritanceMetaData.getDiscriminatorMetaData() != null) {
         return this.inheritanceMetaData.getDiscriminatorMetaData().getStrategy();
      } else {
         return this.getSuperAbstractClassMetaData() != null ? this.getSuperAbstractClassMetaData().getDiscriminatorStrategy() : null;
      }
   }

   public final DiscriminatorMetaData getDiscriminatorMetaData() {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getDiscriminatorMetaData() != null) {
         return this.inheritanceMetaData.getDiscriminatorMetaData();
      } else {
         return this.getSuperAbstractClassMetaData() != null ? this.getSuperAbstractClassMetaData().getDiscriminatorMetaData() : null;
      }
   }

   public final DiscriminatorMetaData getDiscriminatorMetaDataRoot() {
      DiscriminatorMetaData dismd = null;
      if (this.pcSuperclassMetaData != null) {
         dismd = this.pcSuperclassMetaData.getDiscriminatorMetaDataRoot();
      }

      if (dismd == null) {
         dismd = this.inheritanceMetaData != null ? this.inheritanceMetaData.getDiscriminatorMetaData() : null;
      }

      return dismd;
   }

   public final boolean hasDiscriminatorStrategy() {
      DiscriminatorStrategy strategy = this.getDiscriminatorStrategy();
      return strategy != null && strategy != DiscriminatorStrategy.NONE;
   }

   public final DiscriminatorStrategy getDiscriminatorStrategy() {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getDiscriminatorMetaData() != null && this.inheritanceMetaData.getDiscriminatorMetaData().getStrategy() != null) {
         return this.inheritanceMetaData.getDiscriminatorMetaData().getStrategy();
      } else {
         return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getDiscriminatorStrategy() : null;
      }
   }

   public String getDiscriminatorColumnName() {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getDiscriminatorMetaData() != null && this.inheritanceMetaData.getDiscriminatorMetaData().getColumnMetaData() != null && this.inheritanceMetaData.getDiscriminatorMetaData().getColumnMetaData().getName() != null) {
         return this.inheritanceMetaData.getDiscriminatorMetaData().getColumnMetaData().getName();
      } else {
         return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getDiscriminatorColumnName() : null;
      }
   }

   public ColumnMetaData getDiscriminatorColumnMetaData() {
      if (this.inheritanceMetaData != null && this.inheritanceMetaData.getDiscriminatorMetaData() != null && this.inheritanceMetaData.getDiscriminatorMetaData().getColumnMetaData() != null) {
         return this.inheritanceMetaData.getDiscriminatorMetaData().getColumnMetaData();
      } else {
         return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getDiscriminatorColumnMetaData() : null;
      }
   }

   public Object getDiscriminatorValue() {
      if (this.hasDiscriminatorStrategy()) {
         DiscriminatorStrategy str = this.getDiscriminatorStrategy();
         if (str == DiscriminatorStrategy.CLASS_NAME) {
            return this.getFullClassName();
         }

         if (str == DiscriminatorStrategy.VALUE_MAP) {
            DiscriminatorMetaData dismd = this.getDiscriminatorMetaDataRoot();
            Object value = this.getInheritanceMetaData().getDiscriminatorMetaData().getValue();
            if (dismd.getColumnMetaData() != null) {
               ColumnMetaData colmd = dismd.getColumnMetaData();
               if (MetaDataUtils.isJdbcTypeNumeric(colmd.getJdbcType())) {
                  value = Long.parseLong((String)value);
               }
            }

            return value;
         }
      }

      return null;
   }

   public final JoinMetaData[] getJoinMetaData() {
      return this.joinMetaData;
   }

   public final Set getFetchGroupMetaData() {
      return this.fetchGroups;
   }

   public Set getFetchGroupMetaData(Collection groupNames) {
      Set<FetchGroupMetaData> results = new HashSet();

      for(String groupname : groupNames) {
         FetchGroupMetaData fgmd = this.getFetchGroupMetaData(groupname);
         if (fgmd != null) {
            results.add(fgmd);
         }
      }

      return results;
   }

   public FetchGroupMetaData getFetchGroupMetaData(String groupname) {
      FetchGroupMetaData fgmd = this.fetchGroupMetaDataByName != null ? (FetchGroupMetaData)this.fetchGroupMetaDataByName.get(groupname) : null;
      return fgmd == null && this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getFetchGroupMetaData(groupname) : fgmd;
   }

   public IdentityType getIdentityType() {
      return this.identityType;
   }

   public synchronized void setIdentityType(IdentityType type) {
      this.checkNotYetPopulated();
      this.identityType = type;
   }

   public final IndexMetaData[] getIndexMetaData() {
      return this.indexMetaData;
   }

   public final ForeignKeyMetaData[] getForeignKeyMetaData() {
      return this.foreignKeyMetaData;
   }

   public final UniqueMetaData[] getUniqueMetaData() {
      return this.uniqueMetaData;
   }

   public final List getUnmappedColumns() {
      return this.unmappedColumns;
   }

   public String getName() {
      return this.name;
   }

   public String getFullClassName() {
      return this.fullName;
   }

   public String getObjectidClass() {
      return this.objectidClass;
   }

   public AbstractClassMetaData setObjectIdClass(String objectidClass) {
      this.objectidClass = StringUtils.isWhitespace(objectidClass) ? this.objectidClass : objectidClass;
      return this;
   }

   public String getEntityName() {
      return this.entityName;
   }

   public AbstractClassMetaData setEntityName(String name) {
      this.entityName = StringUtils.isWhitespace(name) ? this.entityName : name;
      return this;
   }

   public String getCatalog() {
      return this.catalog == null ? ((PackageMetaData)this.parent).getCatalog() : this.catalog;
   }

   public AbstractClassMetaData setCatalog(String catalog) {
      this.catalog = StringUtils.isWhitespace(catalog) ? this.catalog : catalog;
      return this;
   }

   public String getSchema() {
      return this.schema == null ? ((PackageMetaData)this.parent).getSchema() : this.schema;
   }

   public AbstractClassMetaData setSchema(String schema) {
      this.schema = StringUtils.isWhitespace(schema) ? this.schema : schema;
      return this;
   }

   public String getTable() {
      return this.table;
   }

   public AbstractClassMetaData setTable(String table) {
      this.table = StringUtils.isWhitespace(table) ? this.table : table;
      return this;
   }

   public boolean isRequiresExtent() {
      return this.requiresExtent;
   }

   public AbstractClassMetaData setRequiresExtent(boolean flag) {
      this.requiresExtent = flag;
      return this;
   }

   public AbstractClassMetaData setRequiresExtent(String flag) {
      if (!StringUtils.isWhitespace(flag)) {
         this.requiresExtent = Boolean.parseBoolean(flag);
      }

      return this;
   }

   public boolean isDetachable() {
      return this.detachable;
   }

   public AbstractClassMetaData setDetachable(boolean flag) {
      this.detachable = flag;
      return this;
   }

   public AbstractClassMetaData setDetachable(String flag) {
      if (!StringUtils.isWhitespace(flag)) {
         this.detachable = Boolean.parseBoolean(flag);
      }

      return this;
   }

   public Boolean isCacheable() {
      return this.cacheable;
   }

   public AbstractClassMetaData setCacheable(boolean cache) {
      this.cacheable = cache;
      return this;
   }

   public AbstractClassMetaData setCacheable(String cache) {
      if (!StringUtils.isWhitespace(cache)) {
         this.cacheable = Boolean.parseBoolean(cache);
      }

      return this;
   }

   public boolean isEmbeddedOnly() {
      return this.embeddedOnly;
   }

   public AbstractClassMetaData setEmbeddedOnly(boolean flag) {
      this.embeddedOnly = flag;
      return this;
   }

   public AbstractClassMetaData setEmbeddedOnly(String flag) {
      if (!StringUtils.isWhitespace(flag)) {
         this.embeddedOnly = Boolean.parseBoolean(flag);
      }

      return this;
   }

   public final IdentityMetaData getIdentityMetaData() {
      return this.identityMetaData;
   }

   public final IdentityMetaData getBaseIdentityMetaData() {
      return this.pcSuperclassMetaData != null ? this.pcSuperclassMetaData.getBaseIdentityMetaData() : this.identityMetaData;
   }

   public final InheritanceMetaData getInheritanceMetaData() {
      return this.inheritanceMetaData;
   }

   public final PrimaryKeyMetaData getPrimaryKeyMetaData() {
      return this.primaryKeyMetaData;
   }

   public PackageMetaData getPackageMetaData() {
      return this.parent != null ? (PackageMetaData)this.parent : null;
   }

   public String getPackageName() {
      return this.getPackageMetaData().getName();
   }

   public int getNoOfMembers() {
      return this.members.size();
   }

   public AbstractMemberMetaData getMetaDataForMemberAtRelativePosition(int index) {
      return index >= 0 && index < this.members.size() ? (AbstractMemberMetaData)this.members.get(index) : null;
   }

   public ClassPersistenceModifier getPersistenceModifier() {
      return this.persistenceModifier;
   }

   public AbstractClassMetaData setPersistenceModifier(ClassPersistenceModifier modifier) {
      this.persistenceModifier = modifier;
      return this;
   }

   public String getPersistableSuperclass() {
      return this.persistableSuperclass;
   }

   public boolean usesSingleFieldIdentityClass() {
      return this.usesSingleFieldIdentityClass;
   }

   public boolean isMetaDataComplete() {
      return this.metaDataComplete;
   }

   public boolean isMappedSuperclass() {
      return this.mappedSuperclass;
   }

   public boolean isSerializeRead() {
      return this.serializeRead;
   }

   public boolean isSameOrAncestorOf(AbstractClassMetaData cmd) {
      this.checkInitialised();
      if (cmd == null) {
         return false;
      } else if (this.fullName.equals(cmd.fullName)) {
         return true;
      } else {
         for(AbstractClassMetaData parent = cmd.getSuperAbstractClassMetaData(); parent != null; parent = parent.getSuperAbstractClassMetaData()) {
            if (this.fullName.equals(parent.fullName)) {
               return true;
            }
         }

         return false;
      }
   }

   public String[] getPrimaryKeyMemberNames() {
      if (this.identityType != IdentityType.APPLICATION) {
         return null;
      } else {
         List memberNames = new ArrayList();

         for(AbstractMemberMetaData mmd : this.members) {
            if (Boolean.TRUE.equals(mmd.primaryKey)) {
               memberNames.add(mmd.name);
            }
         }

         if (memberNames.size() > 0) {
            return (String[])memberNames.toArray(new String[memberNames.size()]);
         } else {
            memberNames = null;
            return this.pcSuperclassMetaData.getPrimaryKeyMemberNames();
         }
      }
   }

   public boolean hasMember(String memberName) {
      for(AbstractMemberMetaData mmd : this.members) {
         if (mmd.getName().equals(memberName)) {
            return true;
         }
      }

      if (this.pcSuperclassMetaData != null) {
         return this.pcSuperclassMetaData.hasMember(memberName);
      } else {
         return false;
      }
   }

   public AbstractMemberMetaData getMetaDataForMember(String name) {
      if (name == null) {
         return null;
      } else {
         for(AbstractMemberMetaData mmd : this.members) {
            if (mmd.getName().equals(name)) {
               return mmd;
            }
         }

         if (this.pcSuperclassMetaData != null) {
            return this.pcSuperclassMetaData.getMetaDataForMember(name);
         } else {
            return null;
         }
      }
   }

   public int getNoOfManagedMembers() {
      return this.managedMembers == null ? 0 : this.managedMembers.length;
   }

   public AbstractMemberMetaData[] getManagedMembers() {
      this.checkInitialised();
      return this.managedMembers;
   }

   public int getNoOfOverriddenMembers() {
      return this.overriddenMembers == null ? 0 : this.overriddenMembers.length;
   }

   public AbstractMemberMetaData[] getOverriddenMembers() {
      this.checkInitialised();
      return this.overriddenMembers;
   }

   public AbstractMemberMetaData getOverriddenMember(String name) {
      this.checkInitialised();
      if (this.overriddenMembers == null) {
         return null;
      } else {
         for(int i = 0; i < this.overriddenMembers.length; ++i) {
            if (this.overriddenMembers[i].getName().equals(name)) {
               return this.overriddenMembers[i];
            }
         }

         return null;
      }
   }

   protected AbstractMemberMetaData getMemberBeingOverridden(String name) {
      for(AbstractMemberMetaData apmd : this.members) {
         if (apmd.name.equals(name) && apmd.fieldBelongsToClass()) {
            return apmd;
         }
      }

      if (this.pcSuperclassMetaData != null) {
         return this.pcSuperclassMetaData.getMemberBeingOverridden(name);
      } else {
         return null;
      }
   }

   public int getNoOfInheritedManagedMembers() {
      this.checkInitialised();
      return this.noOfInheritedManagedMembers;
   }

   public int getMemberCount() {
      return this.memberCount;
   }

   public AbstractMemberMetaData getMetaDataForManagedMemberAtRelativePosition(int position) {
      this.checkInitialised();
      if (this.managedMembers == null) {
         return null;
      } else {
         return position >= 0 && position < this.managedMembers.length ? this.managedMembers[position] : null;
      }
   }

   public AbstractMemberMetaData getMetaDataForManagedMemberAtAbsolutePosition(int abs_position) {
      this.checkInitialised();
      return this.getMetaDataForManagedMemberAtAbsolutePositionInternal(abs_position);
   }

   protected AbstractMemberMetaData getMetaDataForManagedMemberAtAbsolutePositionInternal(int abs_position) {
      if (abs_position < this.noOfInheritedManagedMembers) {
         if (this.pcSuperclassMetaData == null) {
            return null;
         } else {
            AbstractMemberMetaData mmd = this.pcSuperclassMetaData.getMetaDataForManagedMemberAtAbsolutePositionInternal(abs_position);
            if (mmd != null) {
               for(int i = 0; i < this.overriddenMembers.length; ++i) {
                  if (this.overriddenMembers[i].getName().equals(mmd.getName()) && this.overriddenMembers[i].getClassName().equals(mmd.getClassName())) {
                     return this.overriddenMembers[i];
                  }
               }

               return mmd;
            } else {
               return null;
            }
         }
      } else {
         return abs_position - this.noOfInheritedManagedMembers >= this.managedMembers.length ? null : this.managedMembers[abs_position - this.noOfInheritedManagedMembers];
      }
   }

   public int getAbsoluteMemberPositionForRelativePosition(int relativePosition) {
      return this.noOfInheritedManagedMembers + relativePosition;
   }

   public int getRelativePositionOfMember(String memberName) {
      this.checkInitialised();
      if (memberName == null) {
         return -1;
      } else {
         Integer i = (Integer)this.memberPositionsByName.get(memberName);
         return i == null ? -1 : i;
      }
   }

   public int getAbsolutePositionOfMember(String memberName) {
      this.checkInitialised();
      if (memberName == null) {
         return -1;
      } else {
         int i = this.getRelativePositionOfMember(memberName);
         if (i < 0) {
            if (this.pcSuperclassMetaData != null) {
               i = this.pcSuperclassMetaData.getAbsolutePositionOfMember(memberName);
            }
         } else {
            i += this.noOfInheritedManagedMembers;
         }

         return i;
      }
   }

   public int getAbsolutePositionOfMember(String className, String memberName) {
      this.checkInitialised();
      if (memberName == null) {
         return -1;
      } else {
         int i = -1;
         if (className.equals(this.getFullClassName())) {
            i = this.getRelativePositionOfMember(memberName);
         }

         if (i < 0) {
            if (this.pcSuperclassMetaData != null) {
               i = this.pcSuperclassMetaData.getAbsolutePositionOfMember(className, memberName);
            }
         } else {
            i += this.noOfInheritedManagedMembers;
         }

         return i;
      }
   }

   private int getNoOfPopulatedPKMembers() {
      if (this.pcSuperclassMetaData != null) {
         return this.pcSuperclassMetaData.getNoOfPopulatedPKMembers();
      } else {
         Iterator<AbstractMemberMetaData> fields_iter = this.members.iterator();
         int noOfPks = 0;

         while(fields_iter.hasNext()) {
            AbstractMemberMetaData mmd = (AbstractMemberMetaData)fields_iter.next();
            if (mmd.isPrimaryKey()) {
               ++noOfPks;
            }
         }

         return noOfPks;
      }
   }

   public int getNoOfPrimaryKeyMembers() {
      return this.pkMemberPositions == null ? 0 : this.pkMemberPositions.length;
   }

   public int[] getAllMemberPositions() {
      this.checkInitialised();
      if (this.allMemberPositions == null) {
         this.allMemberPositions = new int[this.memberCount];

         for(int i = 0; i < this.memberCount; this.allMemberPositions[i] = i++) {
         }
      }

      return this.allMemberPositions;
   }

   public int[] getPKMemberPositions() {
      this.checkInitialised();
      return this.pkMemberPositions;
   }

   public int[] getNonPKMemberPositions() {
      this.checkInitialised();
      return this.nonPkMemberPositions;
   }

   public boolean[] getNonPKMemberFlags() {
      this.checkInitialised();
      return this.nonPkMemberFlags;
   }

   public int[] getDFGMemberPositions() {
      this.checkInitialised();
      return this.dfgMemberPositions;
   }

   public boolean[] getDFGMemberFlags() {
      this.checkInitialised();
      return this.dfgMemberFlags;
   }

   public int[] getBasicMemberPositions(ClassLoaderResolver clr, MetaDataManager mmgr) {
      Iterator<AbstractMemberMetaData> iter = this.members.iterator();
      int numBasics = 0;

      while(iter.hasNext()) {
         AbstractMemberMetaData mmd = (AbstractMemberMetaData)iter.next();
         if (mmd.getRelationType(clr) == RelationType.NONE && !mmd.isPersistentInterface(clr, mmgr) && !Collection.class.isAssignableFrom(mmd.getType()) && !Map.class.isAssignableFrom(mmd.getType()) && !mmd.getType().isArray()) {
            ++numBasics;
         }
      }

      int[] inheritedBasicPositions = null;
      if (this.pcSuperclassMetaData != null) {
         inheritedBasicPositions = this.pcSuperclassMetaData.getBasicMemberPositions(clr, mmgr);
      }

      int[] basicPositions = new int[numBasics + (inheritedBasicPositions != null ? inheritedBasicPositions.length : 0)];
      int number = 0;
      if (inheritedBasicPositions != null) {
         for(int i = 0; i < inheritedBasicPositions.length; ++i) {
            basicPositions[number++] = inheritedBasicPositions[i];
         }
      }

      for(AbstractMemberMetaData mmd : this.members) {
         if (mmd.getRelationType(clr) == RelationType.NONE && !mmd.isPersistentInterface(clr, mmgr) && !Collection.class.isAssignableFrom(mmd.getType()) && !Map.class.isAssignableFrom(mmd.getType()) && !mmd.getType().isArray()) {
            basicPositions[number++] = mmd.getAbsoluteFieldNumber();
         }
      }

      return basicPositions;
   }

   public int[] getMultivaluedMemberPositions() {
      Iterator<AbstractMemberMetaData> iter = this.members.iterator();
      int numMultivalues = 0;

      while(iter.hasNext()) {
         AbstractMemberMetaData mmd = (AbstractMemberMetaData)iter.next();
         if (mmd.getType().isArray() || Collection.class.isAssignableFrom(mmd.getType()) || Map.class.isAssignableFrom(mmd.getType())) {
            ++numMultivalues;
         }
      }

      int[] inheritedMultivaluePositions = null;
      if (this.pcSuperclassMetaData != null) {
         inheritedMultivaluePositions = this.pcSuperclassMetaData.getMultivaluedMemberPositions();
      }

      int[] multivaluePositions = new int[numMultivalues + (inheritedMultivaluePositions != null ? inheritedMultivaluePositions.length : 0)];
      int number = 0;
      if (inheritedMultivaluePositions != null) {
         for(int i = 0; i < inheritedMultivaluePositions.length; ++i) {
            multivaluePositions[number++] = inheritedMultivaluePositions[i];
         }
      }

      for(AbstractMemberMetaData mmd : this.members) {
         if (mmd.getType().isArray() || Collection.class.isAssignableFrom(mmd.getType()) || Map.class.isAssignableFrom(mmd.getType())) {
            multivaluePositions[number++] = mmd.getAbsoluteFieldNumber();
         }
      }

      return multivaluePositions;
   }

   public int[] getSCOMutableMemberPositions() {
      this.checkInitialised();
      return this.scoMutableMemberPositions;
   }

   public int[] getSCONonContainerMemberPositions() {
      this.checkInitialised();
      if (this.scoNonContainerMemberPositions == null) {
         int numberNonContainerSCOFields = 0;

         for(int i = 0; i < this.scoMutableMemberPositions.length; ++i) {
            AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.scoMutableMemberPositions[i]);
            if (!Collection.class.isAssignableFrom(mmd.getType()) && !Map.class.isAssignableFrom(mmd.getType())) {
               ++numberNonContainerSCOFields;
            }
         }

         int[] noncontainerMemberPositions = new int[numberNonContainerSCOFields];
         int nonContNum = 0;

         for(int i = 0; i < this.scoMutableMemberPositions.length; ++i) {
            AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.scoMutableMemberPositions[i]);
            if (!Collection.class.isAssignableFrom(mmd.getType()) && !Map.class.isAssignableFrom(mmd.getType())) {
               noncontainerMemberPositions[nonContNum++] = this.scoMutableMemberPositions[i];
            }
         }

         this.scoNonContainerMemberPositions = noncontainerMemberPositions;
      }

      return this.scoNonContainerMemberPositions;
   }

   public int[] getSCOContainerMemberPositions() {
      this.checkInitialised();
      if (this.secondClassContainerMemberPositions == null) {
         int numberContainerSCOFields = 0;

         for(int i = 0; i < this.scoMutableMemberPositions.length; ++i) {
            AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.scoMutableMemberPositions[i]);
            if (Collection.class.isAssignableFrom(mmd.getType()) || Map.class.isAssignableFrom(mmd.getType())) {
               ++numberContainerSCOFields;
            }
         }

         int[] containerMemberPositions = new int[numberContainerSCOFields];
         int contNum = 0;

         for(int i = 0; i < this.scoMutableMemberPositions.length; ++i) {
            AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.scoMutableMemberPositions[i]);
            if (Collection.class.isAssignableFrom(mmd.getType()) || Map.class.isAssignableFrom(mmd.getType())) {
               containerMemberPositions[contNum++] = this.scoMutableMemberPositions[i];
            }
         }

         this.secondClassContainerMemberPositions = containerMemberPositions;
      }

      return this.secondClassContainerMemberPositions;
   }

   public boolean[] getSCOMutableMemberFlags() {
      this.checkInitialised();
      return this.scoMutableMemberFlags;
   }

   public boolean hasRelations(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.hasRelations == null) {
         this.hasRelations = this.getRelationMemberPositions(clr, mmgr).length > 0;
      }

      return this.hasRelations;
   }

   public int[] getNonRelationMemberPositions(ClassLoaderResolver clr, MetaDataManager mmgr) {
      int[] relPositions = this.getRelationMemberPositions(clr, mmgr);
      if (relPositions != null && relPositions.length != 0) {
         int[] allPositions = this.getAllMemberPositions();
         int[] nonrelPositions = new int[allPositions.length - relPositions.length];
         int nonrelPos = 0;
         int nextRelPos = 0;

         for(int i = 0; i < allPositions.length; ++i) {
            if (nextRelPos == relPositions.length) {
               nonrelPositions[nonrelPos++] = i;
            } else if (allPositions[i] == relPositions[nextRelPos]) {
               ++nextRelPos;
            } else {
               nonrelPositions[nonrelPos++] = i;
            }
         }

         return nonrelPositions;
      } else {
         return this.getAllMemberPositions();
      }
   }

   public int[] getRelationMemberPositions(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.relationPositions == null) {
         int[] superclassRelationPositions = null;
         if (this.pcSuperclassMetaData != null) {
            superclassRelationPositions = this.pcSuperclassMetaData.getRelationMemberPositions(clr, mmgr);
         }

         int numRelationsSuperclass = superclassRelationPositions != null ? superclassRelationPositions.length : 0;
         int numRelations = numRelationsSuperclass;

         for(int i = 0; i < this.managedMembers.length; ++i) {
            if (this.managedMembers[i].getRelationType(clr) != RelationType.NONE || this.managedMembers[i].isPersistentInterface(clr, mmgr)) {
               ++numRelations;
            }
         }

         this.relationPositions = new int[numRelations];
         int num = 0;
         if (numRelationsSuperclass > 0) {
            for(int i = 0; i < superclassRelationPositions.length; ++i) {
               this.relationPositions[num++] = superclassRelationPositions[i];
            }
         }

         if (numRelations > numRelationsSuperclass) {
            for(int i = 0; i < this.managedMembers.length; ++i) {
               if (this.managedMembers[i].getRelationType(clr) != RelationType.NONE || this.managedMembers[i].isPersistentInterface(clr, mmgr)) {
                  this.relationPositions[num++] = this.managedMembers[i].getAbsoluteFieldNumber();
               }
            }
         }
      }

      return this.relationPositions;
   }

   public int[] getBidirectionalRelationMemberPositions(ClassLoaderResolver clr, MetaDataManager mmgr) {
      if (this.relationPositions == null) {
         this.getRelationMemberPositions(clr, mmgr);
      }

      int numBidirs = 0;

      for(int i = 0; i < this.relationPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.relationPositions[i]);
         RelationType relationType = mmd.getRelationType(clr);
         if (relationType == RelationType.ONE_TO_ONE_BI || relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_ONE_BI || relationType == RelationType.MANY_TO_MANY_BI) {
            ++numBidirs;
         }
      }

      int[] bidirRelations = new int[numBidirs];
      numBidirs = 0;

      for(int i = 0; i < this.relationPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.getMetaDataForManagedMemberAtAbsolutePosition(this.relationPositions[i]);
         RelationType relationType = mmd.getRelationType(clr);
         if (relationType == RelationType.ONE_TO_ONE_BI || relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_ONE_BI || relationType == RelationType.MANY_TO_MANY_BI) {
            bidirRelations[numBidirs] = mmd.getAbsoluteFieldNumber();
         }
      }

      return bidirRelations;
   }

   public void setAccessViaField(boolean flag) {
      this.accessViaField = flag;
   }

   public Boolean getAccessViaField() {
      return this.accessViaField;
   }

   public void setMappedSuperclass(boolean mapped) {
      this.mappedSuperclass = mapped;
   }

   public void setSerializeRead(boolean serialise) {
      this.serializeRead = serialise;
   }

   public void setMetaDataComplete() {
      this.metaDataComplete = true;
   }

   public void addQuery(QueryMetaData qmd) {
      if (qmd != null) {
         if (this.queries == null) {
            this.queries = new HashSet();
         }

         this.queries.add(qmd);
         qmd.parent = this;
      }
   }

   public QueryMetaData newQueryMetadata(String queryName) {
      if (StringUtils.isWhitespace(queryName)) {
         throw new InvalidClassMetaDataException("044154", new Object[]{this.fullName});
      } else {
         QueryMetaData qmd = new QueryMetaData(queryName);
         this.addQuery(qmd);
         return qmd;
      }
   }

   public void addStoredProcQuery(StoredProcQueryMetaData qmd) {
      if (qmd != null) {
         if (this.storedProcQueries == null) {
            this.storedProcQueries = new HashSet();
         }

         this.storedProcQueries.add(qmd);
         qmd.parent = this;
      }
   }

   public StoredProcQueryMetaData newStoredProcQueryMetadata(String queryName) {
      if (StringUtils.isWhitespace(queryName)) {
         throw new InvalidClassMetaDataException("044154", new Object[]{this.fullName});
      } else {
         StoredProcQueryMetaData qmd = new StoredProcQueryMetaData(queryName);
         this.addStoredProcQuery(qmd);
         return qmd;
      }
   }

   public void addQueryResultMetaData(QueryResultMetaData resultMetaData) {
      if (this.queryResultMetaData == null) {
         this.queryResultMetaData = new HashSet();
      }

      if (!this.queryResultMetaData.contains(resultMetaData)) {
         this.queryResultMetaData.add(resultMetaData);
         resultMetaData.parent = this;
      }

   }

   public void addIndex(IndexMetaData idxmd) {
      if (idxmd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("Already initialised");
         } else {
            this.indexes.add(idxmd);
            idxmd.parent = this;
         }
      }
   }

   public IndexMetaData newIndexMetadata() {
      IndexMetaData idxmd = new IndexMetaData();
      this.addIndex(idxmd);
      return idxmd;
   }

   public void addForeignKey(ForeignKeyMetaData fkmd) {
      if (fkmd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("Already initialised");
         } else {
            this.foreignKeys.add(fkmd);
            fkmd.parent = this;
         }
      }
   }

   public ForeignKeyMetaData newForeignKeyMetadata() {
      ForeignKeyMetaData fkmd = new ForeignKeyMetaData();
      this.addForeignKey(fkmd);
      return fkmd;
   }

   public void addUniqueConstraint(UniqueMetaData unimd) {
      if (unimd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("Already initialised");
         } else {
            this.uniqueConstraints.add(unimd);
            unimd.parent = this;
         }
      }
   }

   public UniqueMetaData newUniqueMetadata() {
      UniqueMetaData unimd = new UniqueMetaData();
      this.addUniqueConstraint(unimd);
      return unimd;
   }

   public final void addUnmappedColumn(ColumnMetaData colmd) {
      if (this.unmappedColumns == null) {
         this.unmappedColumns = new ArrayList();
      }

      this.unmappedColumns.add(colmd);
      colmd.parent = this;
   }

   public ColumnMetaData newUnmappedColumnMetaData() {
      ColumnMetaData colmd = new ColumnMetaData();
      this.addUnmappedColumn(colmd);
      return colmd;
   }

   public FieldMetaData newFieldMetadata(String fieldName) {
      FieldMetaData fmd = new FieldMetaData(this, fieldName);
      this.addMember(fmd);
      return fmd;
   }

   public PropertyMetaData newPropertyMetadata(String propName) {
      PropertyMetaData pmd = new PropertyMetaData(this, propName);
      this.addMember(pmd);
      return pmd;
   }

   public void addMember(AbstractMemberMetaData mmd) {
      if (mmd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("adding field/property " + mmd.getName() + " when already initialised!");
         } else {
            Iterator<AbstractMemberMetaData> iter = this.members.iterator();

            while(iter.hasNext()) {
               AbstractMemberMetaData md = (AbstractMemberMetaData)iter.next();
               if (mmd.getName().equals(md.getName()) && (mmd instanceof PropertyMetaData && md instanceof PropertyMetaData || mmd instanceof FieldMetaData && md instanceof FieldMetaData)) {
                  throw new NucleusUserException(Localiser.msg("044090", this.fullName, mmd.getName()));
               }

               String existingName = md.getName();
               boolean existingIsProperty = md instanceof PropertyMetaData;
               if (existingIsProperty) {
                  existingName = ((PropertyMetaData)md).getFieldName();
                  if (existingName == null) {
                     existingName = md.getName();
                  }
               }

               String newName = mmd.getName();
               boolean newIsProperty = mmd instanceof PropertyMetaData;
               if (newIsProperty) {
                  newName = ((PropertyMetaData)mmd).getFieldName();
                  if (newName == null) {
                     newName = mmd.getName();
                  }
               }

               if (existingName.equals(newName)) {
                  if (existingIsProperty && newIsProperty) {
                     throw new NucleusUserException(Localiser.msg("044090", this.fullName, mmd.getName()));
                  }

                  if (existingIsProperty && !newIsProperty) {
                     NucleusLogger.METADATA.debug("Ignoring metadata for field " + mmd.getFullFieldName() + " since we already have MetaData for the property " + md.getFullFieldName());
                     return;
                  }

                  if (!existingIsProperty && newIsProperty) {
                     NucleusLogger.METADATA.debug("Ignoring existing metadata for field " + md.getFullFieldName() + " since now we have MetaData for the property " + mmd.getFullFieldName());
                     iter.remove();
                  }
               }
            }

            mmd.parent = this;
            this.members.add(mmd);
         }
      }
   }

   public void addFetchGroup(FetchGroupMetaData fgmd) {
      if (fgmd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("Already initialised");
         } else {
            this.fetchGroups.add(fgmd);
            fgmd.parent = this;
         }
      }
   }

   public FetchGroupMetaData newFetchGroupMetaData(String name) {
      FetchGroupMetaData fgmd = new FetchGroupMetaData(name);
      this.addFetchGroup(fgmd);
      return fgmd;
   }

   public void addJoin(JoinMetaData jnmd) {
      if (jnmd != null) {
         if (this.isInitialised()) {
            throw new NucleusUserException("Already initialised");
         } else {
            this.joins.add(jnmd);
            jnmd.parent = this;
         }
      }
   }

   public JoinMetaData newJoinMetaData() {
      JoinMetaData joinmd = new JoinMetaData();
      this.addJoin(joinmd);
      return joinmd;
   }

   public void addListener(EventListenerMetaData listener) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      if (!this.listeners.contains(listener)) {
         this.listeners.add(listener);
         listener.parent = this;
      }

   }

   public EventListenerMetaData getListenerForClass(String className) {
      if (this.listeners == null) {
         return null;
      } else {
         for(int i = 0; i < this.listeners.size(); ++i) {
            EventListenerMetaData elmd = (EventListenerMetaData)this.listeners.get(i);
            if (elmd.getClassName().equals(className)) {
               return elmd;
            }
         }

         return null;
      }
   }

   public List getListeners() {
      return this.listeners;
   }

   public void excludeSuperClassListeners() {
      this.excludeSuperClassListeners = Boolean.TRUE;
   }

   public boolean isExcludeSuperClassListeners() {
      return this.excludeSuperClassListeners != null && Boolean.TRUE.equals(this.excludeSuperClassListeners);
   }

   public void excludeDefaultListeners() {
      this.excludeDefaultListeners = Boolean.TRUE;
   }

   public boolean isExcludeDefaultListeners() {
      return this.excludeDefaultListeners != null && Boolean.TRUE.equals(this.excludeDefaultListeners);
   }

   public final void setVersionMetaData(VersionMetaData versionMetaData) {
      this.versionMetaData = versionMetaData;
      if (this.versionMetaData != null) {
         this.versionMetaData.parent = this;
      }

   }

   public VersionMetaData newVersionMetadata() {
      VersionMetaData vermd = new VersionMetaData();
      this.setVersionMetaData(vermd);
      return vermd;
   }

   public final void setIdentityMetaData(IdentityMetaData identityMetaData) {
      this.identityMetaData = identityMetaData;
      if (this.identityMetaData != null) {
         this.identityMetaData.parent = this;
      }

      this.identitySpecified = true;
   }

   public IdentityMetaData newIdentityMetadata() {
      IdentityMetaData idmd = new IdentityMetaData();
      this.setIdentityMetaData(idmd);
      return idmd;
   }

   public final void setInheritanceMetaData(InheritanceMetaData inheritanceMetaData) {
      this.inheritanceMetaData = inheritanceMetaData;
      if (this.inheritanceMetaData != null) {
         this.inheritanceMetaData.parent = this;
      }

   }

   public InheritanceMetaData newInheritanceMetadata() {
      InheritanceMetaData inhmd = new InheritanceMetaData();
      this.setInheritanceMetaData(inhmd);
      return inhmd;
   }

   public final void setPrimaryKeyMetaData(PrimaryKeyMetaData primaryKeyMetaData) {
      this.primaryKeyMetaData = primaryKeyMetaData;
      if (this.primaryKeyMetaData != null) {
         this.primaryKeyMetaData.parent = this;
      }

   }

   public PrimaryKeyMetaData newPrimaryKeyMetadata() {
      PrimaryKeyMetaData pkmd = new PrimaryKeyMetaData();
      this.setPrimaryKeyMetaData(pkmd);
      return pkmd;
   }

   public final boolean hasFetchGroupWithPostLoad() {
      if (this.fetchGroupMetaWithPostLoad == null) {
         this.fetchGroupMetaWithPostLoad = Boolean.FALSE;
         if (this.fetchGroups != null) {
            for(FetchGroupMetaData fgmd : this.fetchGroups) {
               if (fgmd.getPostLoad()) {
                  this.fetchGroupMetaWithPostLoad = Boolean.TRUE;
                  break;
               }
            }
         }
      }

      if (this.getSuperAbstractClassMetaData() == null) {
         return this.fetchGroupMetaWithPostLoad;
      } else {
         return this.getSuperAbstractClassMetaData().hasFetchGroupWithPostLoad() || this.fetchGroupMetaWithPostLoad;
      }
   }
}
