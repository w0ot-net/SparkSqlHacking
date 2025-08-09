package org.datanucleus.store.rdbms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.flush.FlushOrdered;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.identity.SCOID;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.IdentityMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.metadata.TableGeneratorMetaData;
import org.datanucleus.state.ActivityState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.ReferentialStateManagerImpl;
import org.datanucleus.store.AbstractStoreManager;
import org.datanucleus.store.BackedSCOStoreManager;
import org.datanucleus.store.NucleusConnection;
import org.datanucleus.store.NucleusConnectionImpl;
import org.datanucleus.store.NucleusSequence;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.autostart.AutoStartMechanism;
import org.datanucleus.store.connection.ConnectionFactory;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapterFactory;
import org.datanucleus.store.rdbms.autostart.SchemaAutoStarter;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.exceptions.UnsupportedDataTypeException;
import org.datanucleus.store.rdbms.fieldmanager.ParameterSetter;
import org.datanucleus.store.rdbms.fieldmanager.ResultSetGetter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.mapping.MappedTypeManager;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.ArrayMapping;
import org.datanucleus.store.rdbms.mapping.java.CollectionMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.MapMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.schema.JDBCTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaHandler;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTableInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTypesInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.scostore.FKArrayStore;
import org.datanucleus.store.rdbms.scostore.FKListStore;
import org.datanucleus.store.rdbms.scostore.FKMapStore;
import org.datanucleus.store.rdbms.scostore.FKSetStore;
import org.datanucleus.store.rdbms.scostore.JoinArrayStore;
import org.datanucleus.store.rdbms.scostore.JoinListStore;
import org.datanucleus.store.rdbms.scostore.JoinMapStore;
import org.datanucleus.store.rdbms.scostore.JoinPersistableRelationStore;
import org.datanucleus.store.rdbms.scostore.JoinSetStore;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.ArrayTable;
import org.datanucleus.store.rdbms.table.ClassTable;
import org.datanucleus.store.rdbms.table.ClassView;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.rdbms.table.PersistableJoinTable;
import org.datanucleus.store.rdbms.table.ProbeTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.store.rdbms.table.ViewImpl;
import org.datanucleus.store.rdbms.valuegenerator.SequenceTable;
import org.datanucleus.store.schema.SchemaAwareStoreManager;
import org.datanucleus.store.schema.SchemaScriptAwareStoreManager;
import org.datanucleus.store.scostore.ArrayStore;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.PersistableRelationStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.IncompatibleFieldTypeException;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator;
import org.datanucleus.store.valuegenerator.ValueGenerationConnectionProvider;
import org.datanucleus.store.valuegenerator.ValueGenerator;
import org.datanucleus.store.valuegenerator.AbstractDatastoreGenerator.ConnectionPreference;
import org.datanucleus.transaction.TransactionUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MacroString;
import org.datanucleus.util.MultiMap;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class RDBMSStoreManager extends AbstractStoreManager implements BackedSCOStoreManager, SchemaAwareStoreManager, SchemaScriptAwareStoreManager {
   public static final String METADATA_NONDURABLE_REQUIRES_TABLE = "requires-table";
   protected DatastoreAdapter dba;
   protected IdentifierFactory identifierFactory;
   protected String catalogName = null;
   protected String schemaName = null;
   protected MappedTypeManager mappedTypeMgr = null;
   protected MappingManager mappingManager;
   protected Map insertedDatastoreClassByObjectProvider = new ConcurrentHashMap();
   protected ReadWriteLock schemaLock = new ReentrantReadWriteLock();
   private SQLController sqlController = null;
   protected SQLExpressionFactory expressionFactory;
   private transient Calendar dateTimezoneCalendar = null;
   private ClassAdder classAdder = null;
   private Writer ddlWriter = null;
   private boolean completeDDL = false;
   private Set writtenDdlStatements = null;
   private MultiMap schemaCallbacks = new MultiMap();
   private Map backingStoreByMemberName = new ConcurrentHashMap();
   boolean performingDeleteSchemaForClasses = false;

   public RDBMSStoreManager(ClassLoaderResolver clr, PersistenceNucleusContext ctx, Map props) {
      super("rdbms", clr, ctx, props);
      this.mappedTypeMgr = new MappedTypeManager(this.nucleusContext);
      this.persistenceHandler = new RDBMSPersistenceHandler(this);
      this.flushProcess = new FlushOrdered();
      this.schemaHandler = new RDBMSSchemaHandler(this);
      this.expressionFactory = new SQLExpressionFactory(this);

      try {
         ManagedConnection mc = this.getConnection(-1);
         Connection conn = (Connection)mc.getConnection();
         if (conn == null) {
            throw new NucleusDataStoreException(Localiser.msg("050007"));
         } else {
            try {
               this.dba = DatastoreAdapterFactory.getInstance().getDatastoreAdapter(clr, conn, this.getStringProperty("datanucleus.rdbms.datastoreAdapterClassName"), ctx.getPluginManager());
               this.dba.initialiseTypes(this.schemaHandler, mc);
               this.dba.removeUnsupportedMappings(this.schemaHandler, mc);
               if (this.hasPropertyNotNull("datanucleus.mapping.Catalog")) {
                  if (!this.dba.supportsOption("CatalogInTableDefinition")) {
                     NucleusLogger.DATASTORE.warn(Localiser.msg("050002", new Object[]{this.getStringProperty("datanucleus.mapping.Catalog")}));
                  } else {
                     this.catalogName = this.getStringProperty("datanucleus.mapping.Catalog");
                  }
               }

               if (this.hasPropertyNotNull("datanucleus.mapping.Schema")) {
                  if (!this.dba.supportsOption("SchemaInTableDefinition")) {
                     NucleusLogger.DATASTORE.warn(Localiser.msg("050003", new Object[]{this.getStringProperty("datanucleus.mapping.Schema")}));
                  } else {
                     this.schemaName = this.getStringProperty("datanucleus.mapping.Schema");
                  }
               }

               this.initialiseIdentifierFactory(ctx);
               if (this.schemaName != null) {
                  String validSchemaName = this.identifierFactory.getIdentifierInAdapterCase(this.schemaName);
                  if (!validSchemaName.equals(this.schemaName)) {
                     NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("020192", new Object[]{"schema", this.schemaName, validSchemaName}));
                     this.schemaName = validSchemaName;
                  }
               }

               if (this.catalogName != null) {
                  String validCatalogName = this.identifierFactory.getIdentifierInAdapterCase(this.catalogName);
                  if (!validCatalogName.equals(this.catalogName)) {
                     NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("020192", new Object[]{"catalog", this.catalogName, validCatalogName}));
                     this.catalogName = validCatalogName;
                  }
               }

               this.sqlController = new SQLController(this.dba.supportsOption("StatementBatching"), this.getIntProperty("datanucleus.rdbms.statementBatchLimit"), this.getIntProperty("datanucleus.datastoreReadTimeout"), this.getStringProperty("datanucleus.rdbms.statementLogging"));
               Map<String, Object> dbaProps = new HashMap();
               Map<String, Object> persistenceProps = ctx.getConfiguration().getPersistenceProperties();

               for(Map.Entry entry : persistenceProps.entrySet()) {
                  String prop = (String)entry.getKey();
                  if (prop.startsWith("datanucleus.rdbms.adapter.")) {
                     dbaProps.put(prop, entry.getValue());
                  }
               }

               if (dbaProps.size() > 0) {
                  this.dba.setProperties(dbaProps);
               }

               this.initialiseSchema(conn, clr);
               this.logConfiguration();
            } catch (Exception e) {
               NucleusLogger.GENERAL.info("Error in initialisation of RDBMSStoreManager", e);
               throw e;
            } finally {
               mc.release();
            }

         }
      } catch (NucleusException ne) {
         NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("050004"), ne);
         throw ne.setFatal();
      } catch (Exception e1) {
         String msg = Localiser.msg("050004") + ' ' + Localiser.msg("050006") + ' ' + Localiser.msg("048000", new Object[]{e1});
         NucleusLogger.DATASTORE_SCHEMA.error(msg, e1);
         throw (new NucleusUserException(msg, e1)).setFatal();
      }
   }

   public String getQueryCacheKey() {
      return this.getStoreManagerKey() + "-" + this.getDatastoreAdapter().getVendorID();
   }

   protected void initialiseIdentifierFactory(NucleusContext nucleusContext) {
      if (this.dba == null) {
         throw new NucleusException("DatastoreAdapter not yet created so cannot create IdentifierFactory!");
      } else {
         String idFactoryName = this.getStringProperty("datanucleus.identifierFactory");
         String idFactoryClassName = nucleusContext.getPluginManager().getAttributeValueForExtension("org.datanucleus.store.rdbms.identifierfactory", "name", idFactoryName, "class-name");
         if (idFactoryClassName == null) {
            throw (new NucleusUserException(Localiser.msg("039003", new Object[]{idFactoryName}))).setFatal();
         } else {
            try {
               Map props = new HashMap();
               if (this.catalogName != null) {
                  props.put("DefaultCatalog", this.catalogName);
               }

               if (this.schemaName != null) {
                  props.put("DefaultSchema", this.schemaName);
               }

               String val = this.getStringProperty("datanucleus.identifier.case");
               props.put("RequiredCase", val != null ? val : this.getDefaultIdentifierCase());
               val = this.getStringProperty("datanucleus.identifier.wordSeparator");
               if (val != null) {
                  props.put("WordSeparator", val);
               }

               val = this.getStringProperty("datanucleus.identifier.tablePrefix");
               if (val != null) {
                  props.put("TablePrefix", val);
               }

               val = this.getStringProperty("datanucleus.identifier.tableSuffix");
               if (val != null) {
                  props.put("TableSuffix", val);
               }

               props.put("NamingFactory", this.getNamingFactory());
               Class[] argTypes = new Class[]{DatastoreAdapter.class, ClassConstants.CLASS_LOADER_RESOLVER, Map.class};
               Object[] args = new Object[]{this.dba, nucleusContext.getClassLoaderResolver((ClassLoader)null), props};
               this.identifierFactory = (IdentifierFactory)nucleusContext.getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.identifierfactory", "name", idFactoryName, "class-name", argTypes, args);
            } catch (ClassNotFoundException cnfe) {
               throw (new NucleusUserException(Localiser.msg("039004", new Object[]{idFactoryName, idFactoryClassName}), cnfe)).setFatal();
            } catch (Exception e) {
               NucleusLogger.PERSISTENCE.error("Exception creating IdentifierFactory", e);
               throw (new NucleusException(Localiser.msg("039005", new Object[]{idFactoryClassName}), e)).setFatal();
            }
         }
      }
   }

   public boolean supportsValueStrategy(String strategy) {
      if (!strategy.equalsIgnoreCase("IDENTITY") && !super.supportsValueStrategy(strategy)) {
         return false;
      } else if (strategy.equalsIgnoreCase("IDENTITY") && !this.dba.supportsOption("IdentityColumns")) {
         return false;
      } else {
         return !strategy.equalsIgnoreCase("SEQUENCE") || this.dba.supportsOption("Sequences");
      }
   }

   public MappedTypeManager getMappedTypeManager() {
      return this.mappedTypeMgr;
   }

   public IdentifierFactory getIdentifierFactory() {
      return this.identifierFactory;
   }

   public DatastoreAdapter getDatastoreAdapter() {
      return this.dba;
   }

   public MappingManager getMappingManager() {
      if (this.mappingManager == null) {
         this.mappingManager = this.dba.getMappingManager(this);
      }

      return this.mappingManager;
   }

   public String getDefaultObjectProviderClassName() {
      return ReferentialStateManagerImpl.class.getName();
   }

   public StoreData[] getStoreDataForDatastoreContainerObject(DatastoreIdentifier tableIdentifier) {
      this.schemaLock.readLock().lock();

      StoreData[] var2;
      try {
         var2 = this.storeDataMgr.getStoreDataForProperties("tableId", tableIdentifier, "table-owner", "true");
      } finally {
         this.schemaLock.readLock().unlock();
      }

      return var2;
   }

   public Table getTable(AbstractMemberMetaData mmd) {
      this.schemaLock.readLock().lock();

      Table var3;
      try {
         StoreData sd = this.storeDataMgr.get(mmd);
         if (sd == null || !(sd instanceof RDBMSStoreData)) {
            var3 = null;
            return var3;
         }

         var3 = (Table)sd.getTable();
      } finally {
         this.schemaLock.readLock().unlock();
      }

      return var3;
   }

   public DatastoreClass getDatastoreClass(String className, ClassLoaderResolver clr) {
      DatastoreClass ct = null;
      if (className == null) {
         NucleusLogger.PERSISTENCE.error(Localiser.msg("032015"));
         return null;
      } else {
         this.schemaLock.readLock().lock();

         try {
            StoreData sd = this.storeDataMgr.get(className);
            if (sd != null && sd instanceof RDBMSStoreData) {
               ct = (DatastoreClass)sd.getTable();
               if (ct != null) {
                  DatastoreClass var5 = ct;
                  return var5;
               }
            }
         } finally {
            this.schemaLock.readLock().unlock();
         }

         boolean toBeAdded = false;
         if (clr != null) {
            Class cls = clr.classForName(className);
            ApiAdapter api = this.getApiAdapter();
            if (cls != null && !cls.isInterface() && api.isPersistable(cls)) {
               toBeAdded = true;
            }
         } else {
            toBeAdded = true;
         }

         boolean classKnown = false;
         if (toBeAdded) {
            this.manageClasses(clr, className);
            this.schemaLock.readLock().lock();

            try {
               StoreData sd = this.storeDataMgr.get(className);
               if (sd != null && sd instanceof RDBMSStoreData) {
                  classKnown = true;
                  ct = (DatastoreClass)sd.getTable();
               }
            } finally {
               this.schemaLock.readLock().unlock();
            }
         }

         if (!classKnown && ct == null) {
            throw new NoTableManagedException(className);
         } else {
            return ct;
         }
      }
   }

   public DatastoreClass getDatastoreClass(DatastoreIdentifier name) {
      this.schemaLock.readLock().lock();

      try {
         for(StoreData sd : this.storeDataMgr.getManagedStoreData()) {
            if (sd instanceof RDBMSStoreData) {
               RDBMSStoreData tsd = (RDBMSStoreData)sd;
               if (tsd.hasTable() && tsd.getDatastoreIdentifier().equals(name)) {
                  DatastoreClass var5 = (DatastoreClass)tsd.getTable();
                  return var5;
               }
            }
         }

         Object var9 = null;
         return (DatastoreClass)var9;
      } finally {
         this.schemaLock.readLock().unlock();
      }
   }

   public AbstractClassMetaData[] getClassesManagingTableForClass(AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      if (cmd == null) {
         return null;
      } else if (cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.COMPLETE_TABLE && cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.NEW_TABLE) {
         if (cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.SUBCLASS_TABLE) {
            return cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE ? this.getClassesManagingTableForClass(cmd.getSuperAbstractClassMetaData(), clr) : null;
         } else {
            String[] subclasses = this.getMetaDataManager().getSubclassesForClass(cmd.getFullClassName(), true);
            if (subclasses != null) {
               for(int i = 0; i < subclasses.length; ++i) {
                  if (!this.storeDataMgr.managesClass(subclasses[i])) {
                     this.manageClasses(clr, subclasses[i]);
                  }
               }
            }

            HashSet managingClasses = new HashSet();

            for(StoreData data : this.storeDataMgr.getManagedStoreData()) {
               if (data.isFCO() && ((AbstractClassMetaData)data.getMetaData()).getSuperAbstractClassMetaData() != null && ((AbstractClassMetaData)data.getMetaData()).getSuperAbstractClassMetaData().getFullClassName().equals(cmd.getFullClassName())) {
                  AbstractClassMetaData[] superCmds = this.getClassesManagingTableForClass((AbstractClassMetaData)data.getMetaData(), clr);
                  if (superCmds != null) {
                     for(int i = 0; i < superCmds.length; ++i) {
                        managingClasses.add(superCmds[i]);
                     }
                  }
               }
            }

            Iterator managingClassesIter = managingClasses.iterator();
            AbstractClassMetaData[] managingCmds = new AbstractClassMetaData[managingClasses.size()];

            for(int i = 0; managingClassesIter.hasNext(); managingCmds[i++] = (AbstractClassMetaData)managingClassesIter.next()) {
            }

            return managingCmds;
         }
      } else {
         return new AbstractClassMetaData[]{cmd};
      }
   }

   public boolean isObjectInserted(ObjectProvider op, int fieldNumber) {
      if (op == null) {
         return false;
      } else if (!op.isInserting()) {
         return true;
      } else {
         DatastoreClass latestTable = (DatastoreClass)this.insertedDatastoreClassByObjectProvider.get(op);
         if (latestTable == null) {
            return false;
         } else {
            AbstractMemberMetaData mmd = op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
            if (mmd == null) {
               return false;
            } else {
               String className = mmd.getClassName();
               if (mmd.isPrimaryKey()) {
                  className = op.getObject().getClass().getName();
               }

               for(DatastoreClass datastoreCls = latestTable; datastoreCls != null; datastoreCls = datastoreCls.getSuperDatastoreClass()) {
                  if (datastoreCls.managesClass(className)) {
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }

   public boolean isObjectInserted(ObjectProvider op, String className) {
      if (op == null) {
         return false;
      } else if (!op.isInserting()) {
         return false;
      } else {
         DatastoreClass latestTable = (DatastoreClass)this.insertedDatastoreClassByObjectProvider.get(op);
         if (latestTable != null) {
            for(DatastoreClass datastoreCls = latestTable; datastoreCls != null; datastoreCls = datastoreCls.getSuperDatastoreClass()) {
               if (datastoreCls.managesClass(className)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public void setObjectIsInsertedToLevel(ObjectProvider op, DatastoreClass table) {
      this.insertedDatastoreClassByObjectProvider.put(op, table);
      if (table.managesClass(op.getClassMetaData().getFullClassName())) {
         op.changeActivityState(ActivityState.INSERTING_CALLBACKS);
         this.insertedDatastoreClassByObjectProvider.remove(op);
      }

   }

   public Store getBackingStoreForField(ClassLoaderResolver clr, AbstractMemberMetaData mmd, Class type) {
      if (mmd != null && !mmd.isSerialized()) {
         Store store = (Store)this.backingStoreByMemberName.get(mmd.getFullFieldName());
         if (store != null) {
            return store;
         } else {
            synchronized(this.backingStoreByMemberName) {
               store = (Store)this.backingStoreByMemberName.get(mmd.getFullFieldName());
               if (store != null) {
                  return store;
               } else {
                  if (mmd.getMap() != null) {
                     this.assertCompatibleFieldType(mmd, clr, type, MapMapping.class);
                     store = this.getBackingStoreForMap(mmd, clr);
                  } else if (mmd.getArray() != null) {
                     this.assertCompatibleFieldType(mmd, clr, type, ArrayMapping.class);
                     store = this.getBackingStoreForArray(mmd, clr);
                  } else if (mmd.getCollection() != null) {
                     this.assertCompatibleFieldType(mmd, clr, type, CollectionMapping.class);
                     store = this.getBackingStoreForCollection(mmd, clr, type);
                  } else {
                     this.assertCompatibleFieldType(mmd, clr, type, PersistableMapping.class);
                     store = this.getBackingStoreForPersistableRelation(mmd, clr);
                  }

                  this.backingStoreByMemberName.put(mmd.getFullFieldName(), store);
                  return store;
               }
            }
         }
      } else {
         return null;
      }
   }

   private void assertCompatibleFieldType(AbstractMemberMetaData mmd, ClassLoaderResolver clr, Class preferredType, Class expectedMappingType) {
      DatastoreClass ownerTable = this.getDatastoreClass(mmd.getClassName(), clr);
      if (ownerTable == null) {
         AbstractClassMetaData fieldTypeCmd = this.getMetaDataManager().getMetaDataForClass(mmd.getClassName(), clr);
         AbstractClassMetaData[] tableOwnerCmds = this.getClassesManagingTableForClass(fieldTypeCmd, clr);
         if (tableOwnerCmds != null && tableOwnerCmds.length == 1) {
            ownerTable = this.getDatastoreClass(tableOwnerCmds[0].getFullClassName(), clr);
         }
      }

      if (ownerTable != null) {
         JavaTypeMapping m = ownerTable.getMemberMapping(mmd);
         if (!expectedMappingType.isAssignableFrom(m.getClass())) {
            String requiredType = preferredType != null ? preferredType.getName() : mmd.getTypeName();
            NucleusLogger.PERSISTENCE.warn("Member " + mmd.getFullFieldName() + " in table=" + ownerTable + " has mapping=" + m + " but expected mapping type=" + expectedMappingType);
            throw new IncompatibleFieldTypeException(mmd.getFullFieldName(), requiredType, m.getType());
         }
      }

   }

   protected CollectionStore getBackingStoreForCollection(AbstractMemberMetaData mmd, ClassLoaderResolver clr, Class type) {
      Table datastoreTable = this.getTable(mmd);
      if (type == null) {
         if (datastoreTable == null) {
            if (Set.class.isAssignableFrom(mmd.getType())) {
               return new FKSetStore(mmd, this, clr);
            } else if (!List.class.isAssignableFrom(mmd.getType()) && !Queue.class.isAssignableFrom(mmd.getType())) {
               return (CollectionStore)(mmd.getOrderMetaData() != null ? new FKListStore(mmd, this, clr) : new FKSetStore(mmd, this, clr));
            } else {
               return new FKListStore(mmd, this, clr);
            }
         } else if (Set.class.isAssignableFrom(mmd.getType())) {
            return new JoinSetStore(mmd, (CollectionTable)datastoreTable, clr);
         } else if (!List.class.isAssignableFrom(mmd.getType()) && !Queue.class.isAssignableFrom(mmd.getType())) {
            return (CollectionStore)(mmd.getOrderMetaData() != null ? new JoinListStore(mmd, (CollectionTable)datastoreTable, clr) : new JoinSetStore(mmd, (CollectionTable)datastoreTable, clr));
         } else {
            return new JoinListStore(mmd, (CollectionTable)datastoreTable, clr);
         }
      } else if (datastoreTable == null) {
         return (CollectionStore)(SCOUtils.isListBased(type) ? new FKListStore(mmd, this, clr) : new FKSetStore(mmd, this, clr));
      } else {
         return (CollectionStore)(SCOUtils.isListBased(type) ? new JoinListStore(mmd, (CollectionTable)datastoreTable, clr) : new JoinSetStore(mmd, (CollectionTable)datastoreTable, clr));
      }
   }

   protected MapStore getBackingStoreForMap(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      Table datastoreTable = this.getTable(mmd);
      return (MapStore)(datastoreTable == null ? new FKMapStore(mmd, this, clr) : new JoinMapStore((MapTable)datastoreTable, clr));
   }

   protected ArrayStore getBackingStoreForArray(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      Table datastoreTable = this.getTable(mmd);
      return (ArrayStore)(datastoreTable != null ? new JoinArrayStore(mmd, (ArrayTable)datastoreTable, clr) : new FKArrayStore(mmd, this, clr));
   }

   protected PersistableRelationStore getBackingStoreForPersistableRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      return new JoinPersistableRelationStore(mmd, (PersistableJoinTable)this.getTable(mmd), clr);
   }

   public String getDefaultIdentifierCase() {
      return "UPPERCASE";
   }

   public MultiMap getSchemaCallbacks() {
      return this.schemaCallbacks;
   }

   public void addSchemaCallback(String className, AbstractMemberMetaData mmd) {
      Collection coll = (Collection)this.schemaCallbacks.get(className);
      if (coll != null && coll.contains(mmd)) {
         NucleusLogger.DATASTORE_SCHEMA.debug("RDBMSStoreManager.addSchemaCallback called for " + mmd.getFullFieldName() + " on class=" + className + " but already registered");
      } else {
         this.schemaCallbacks.put(className, mmd);
      }

   }

   public boolean isJdbcStore() {
      return true;
   }

   public String getNativeQueryLanguage() {
      return QueryLanguage.SQL.toString();
   }

   protected void logConfiguration() {
      super.logConfiguration();
      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         NucleusLogger.DATASTORE.debug("Datastore Adapter : " + this.dba.getClass().getName());
         NucleusLogger.DATASTORE.debug("Datastore : name=\"" + this.dba.getDatastoreProductName() + "\" version=\"" + this.dba.getDatastoreProductVersion() + "\"");
         NucleusLogger.DATASTORE.debug("Datastore Driver : name=\"" + this.dba.getDatastoreDriverName() + "\" version=\"" + this.dba.getDatastoreDriverVersion() + "\"");
         String primaryDS = null;
         if (this.getConnectionFactory() != null) {
            primaryDS = "DataSource[input DataSource]";
         } else if (this.getConnectionFactoryName() != null) {
            primaryDS = "JNDI[" + this.getConnectionFactoryName() + "]";
         } else {
            primaryDS = "URL[" + this.getConnectionURL() + "]";
         }

         NucleusLogger.DATASTORE.debug("Primary Connection Factory : " + primaryDS);
         String secondaryDS = null;
         if (this.getConnectionFactory2() != null) {
            secondaryDS = "DataSource[input DataSource]";
         } else if (this.getConnectionFactory2Name() != null) {
            secondaryDS = "JNDI[" + this.getConnectionFactory2Name() + "]";
         } else if (this.getConnectionURL() != null) {
            secondaryDS = "URL[" + this.getConnectionURL() + "]";
         } else {
            secondaryDS = primaryDS;
         }

         NucleusLogger.DATASTORE.debug("Secondary Connection Factory : " + secondaryDS);
         if (this.identifierFactory != null) {
            NucleusLogger.DATASTORE.debug("Datastore Identifiers : factory=\"" + this.getStringProperty("datanucleus.identifierFactory") + "\" case=" + this.identifierFactory.getNamingCase().toString() + (this.catalogName != null ? " catalog=" + this.catalogName : "") + (this.schemaName != null ? " schema=" + this.schemaName : ""));
            NucleusLogger.DATASTORE.debug("Supported Identifier Cases : " + (this.dba.supportsOption("LowerCaseIdentifiers") ? "lowercase " : "") + (this.dba.supportsOption("LowerCaseQuotedIdentifiers") ? "\"lowercase\" " : "") + (this.dba.supportsOption("MixedCaseIdentifiers") ? "MixedCase " : "") + (this.dba.supportsOption("MixedCaseQuotedIdentifiers") ? "\"MixedCase\" " : "") + (this.dba.supportsOption("UpperCaseIdentifiers") ? "UPPERCASE " : "") + (this.dba.supportsOption("UpperCaseQuotedIdentifiers") ? "\"UPPERCASE\" " : "") + (this.dba.supportsOption("MixedCaseSensitiveIdentifiers") ? "MixedCase-Sensitive " : "") + (this.dba.supportsOption("MixedCaseQuotedSensitiveIdentifiers") ? "\"MixedCase-Sensitive\" " : ""));
            NucleusLogger.DATASTORE.debug("Supported Identifier Lengths (max) : Table=" + this.dba.getDatastoreIdentifierMaxLength(IdentifierType.TABLE) + " Column=" + this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN) + " Constraint=" + this.dba.getDatastoreIdentifierMaxLength(IdentifierType.CANDIDATE_KEY) + " Index=" + this.dba.getDatastoreIdentifierMaxLength(IdentifierType.INDEX) + " Delimiter=" + this.dba.getIdentifierQuoteString());
            NucleusLogger.DATASTORE.debug("Support for Identifiers in DDL : catalog=" + this.dba.supportsOption("CatalogInTableDefinition") + " schema=" + this.dba.supportsOption("SchemaInTableDefinition"));
         }

         NucleusLogger.DATASTORE.debug("Datastore : " + (this.getBooleanProperty("datanucleus.rdbms.checkExistTablesOrViews") ? "checkTableViewExistence" : "") + ", rdbmsConstraintCreateMode=" + this.getStringProperty("datanucleus.rdbms.constraintCreateMode") + ", initialiseColumnInfo=" + this.getStringProperty("datanucleus.rdbms.initializeColumnInfo"));
         int batchLimit = this.getIntProperty("datanucleus.rdbms.statementBatchLimit");
         boolean supportBatching = this.dba.supportsOption("StatementBatching");
         if (supportBatching) {
            NucleusLogger.DATASTORE.debug("Support Statement Batching : yes (max-batch-size=" + (batchLimit == -1 ? "UNLIMITED" : "" + batchLimit) + ")");
         } else {
            NucleusLogger.DATASTORE.debug("Support Statement Batching : no");
         }

         NucleusLogger.DATASTORE.debug("Queries : Results direction=" + this.getStringProperty("datanucleus.rdbms.query.fetchDirection") + ", type=" + this.getStringProperty("datanucleus.rdbms.query.resultSetType") + ", concurrency=" + this.getStringProperty("datanucleus.rdbms.query.resultSetConcurrency"));
         NucleusLogger.DATASTORE.debug("Java-Types : string-default-length=" + this.getIntProperty("datanucleus.rdbms.stringDefaultLength"));
         RDBMSTypesInfo typesInfo = (RDBMSTypesInfo)this.schemaHandler.getSchemaData((Object)null, "types", (Object[])null);
         if (typesInfo != null && typesInfo.getNumberOfChildren() > 0) {
            StringBuilder typeStr = new StringBuilder();
            Iterator jdbcTypesIter = typesInfo.getChildren().keySet().iterator();

            while(jdbcTypesIter.hasNext()) {
               String jdbcTypeStr = (String)jdbcTypesIter.next();
               int jdbcTypeNumber = 0;

               try {
                  jdbcTypeNumber = Short.valueOf(jdbcTypeStr);
               } catch (NumberFormatException var11) {
               }

               String typeName = this.dba.getNameForJDBCType(jdbcTypeNumber);
               if (typeName == null) {
                  typeName = "[id=" + jdbcTypeNumber + "]";
               }

               typeStr.append(typeName);
               if (jdbcTypesIter.hasNext()) {
                  typeStr.append(", ");
               }
            }

            NucleusLogger.DATASTORE.debug("JDBC-Types : " + typeStr);
         }

         NucleusLogger.DATASTORE.debug("===========================================================");
      }

   }

   public synchronized void close() {
      this.dba = null;
      super.close();
      this.classAdder = null;
   }

   public NucleusSequence getNucleusSequence(ExecutionContext ec, SequenceMetaData seqmd) {
      return new NucleusSequenceImpl(ec, this, seqmd);
   }

   public NucleusConnection getNucleusConnection(ExecutionContext ec) {
      final boolean enlisted;
      if (!ec.getTransaction().isActive()) {
         enlisted = false;
      } else {
         enlisted = true;
      }

      ConnectionFactory cf = null;
      if (enlisted) {
         cf = this.connectionMgr.lookupConnectionFactory(this.primaryConnectionFactoryName);
      } else {
         cf = this.connectionMgr.lookupConnectionFactory(this.secondaryConnectionFactoryName);
      }

      final ManagedConnection mc = cf.getConnection(enlisted ? ec : null, ec.getTransaction(), (Map)null);
      mc.lock();
      Runnable closeRunnable = new Runnable() {
         public void run() {
            mc.unlock();
            if (!enlisted) {
               try {
                  ((Connection)mc.getConnection()).close();
               } catch (SQLException sqle) {
                  throw new NucleusDataStoreException(sqle.getMessage());
               }
            }

         }
      };
      return new NucleusConnectionImpl(mc.getConnection(), closeRunnable);
   }

   public SQLController getSQLController() {
      return this.sqlController;
   }

   public SQLExpressionFactory getSQLExpressionFactory() {
      return this.expressionFactory;
   }

   private void initialiseSchema(Connection conn, ClassLoaderResolver clr) throws Exception {
      if (this.schemaName == null && this.catalogName == null && (this.dba.supportsOption("CatalogInTableDefinition") || this.dba.supportsOption("SchemaInTableDefinition"))) {
         try {
            try {
               this.catalogName = this.dba.getCatalogName(conn);
               this.schemaName = this.dba.getSchemaName(conn);
            } catch (UnsupportedOperationException var11) {
               if (!this.getBooleanProperty("datanucleus.readOnlyDatastore") && this.getSchemaHandler().isAutoCreateTables()) {
                  ProbeTable pt = new ProbeTable(this);
                  pt.initialize(clr);
                  pt.create(conn);

                  try {
                     String[] schema_details = pt.findSchemaDetails(conn);
                     if (schema_details != null) {
                        this.catalogName = schema_details[0];
                        this.schemaName = schema_details[1];
                     }
                  } finally {
                     pt.drop(conn);
                  }
               }
            }
         } catch (SQLException e) {
            String msg = Localiser.msg("050005", new Object[]{e.getMessage()}) + ' ' + Localiser.msg("050006");
            NucleusLogger.DATASTORE_SCHEMA.warn(msg);
         }
      }

      if (this.getBooleanProperty("datanucleus.readOnlyDatastore")) {
         String autoStartMechanismName = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.autoStartMechanism");
         if ("SchemaTable".equals(autoStartMechanismName)) {
            this.nucleusContext.getConfiguration().setProperty("datanucleus.autoStartMechanism", "None");
         }
      } else {
         this.dba.initialiseDatastore(conn);
      }

   }

   private void clearSchemaData() {
      this.deregisterAllStoreData();
      this.schemaHandler.clear();
      ManagedConnection mc = this.getConnection(-1);

      try {
         this.dba.initialiseTypes(this.schemaHandler, mc);
      } finally {
         mc.release();
      }

      ((RDBMSPersistenceHandler)this.persistenceHandler).removeAllRequests();
   }

   public String getCatalogName() {
      return this.catalogName;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public Date getDatastoreDate() {
      Date serverDate = null;
      String dateStmt = this.dba.getDatastoreDateStatement();
      ManagedConnection mconn = null;

      Timestamp time;
      try {
         mconn = this.getConnection(0);
         PreparedStatement ps = null;
         ResultSet rs = null;

         try {
            ps = this.getSQLController().getStatementForQuery(mconn, dateStmt);
            rs = this.getSQLController().executeStatementQuery((ExecutionContext)null, mconn, dateStmt, ps);
            if (rs.next()) {
               time = rs.getTimestamp(1, this.getCalendarForDateTimezone());
               serverDate = new Date(time.getTime());
               return serverDate;
            }

            time = null;
         } catch (SQLException var18) {
            time = var18;
            String msg = Localiser.msg("050052", new Object[]{var18.getMessage()});
            NucleusLogger.DATASTORE.warn(msg, var18);
            throw (new NucleusUserException(msg, var18)).setFatal();
         } finally {
            if (rs != null) {
               rs.close();
            }

            if (ps != null) {
               this.getSQLController().closeStatement(mconn, ps);
            }

         }
      } catch (SQLException sqle) {
         String msg = Localiser.msg("050052", new Object[]{sqle.getMessage()});
         NucleusLogger.DATASTORE.warn(msg, sqle);
         throw (new NucleusException(msg, sqle)).setFatal();
      } finally {
         if (mconn != null) {
            mconn.release();
         }

      }

      return time;
   }

   public void manageClasses(ClassLoaderResolver clr, String... classNames) {
      if (classNames != null && classNames.length != 0) {
         boolean allManaged = true;

         for(int i = 0; i < classNames.length; ++i) {
            if (!this.managesClass(classNames[i])) {
               allManaged = false;
               break;
            }
         }

         if (!allManaged) {
            classNames = this.getNucleusContext().getTypeManager().filterOutSupportedSecondClassNames(classNames);
            if (classNames.length != 0) {
               try {
                  this.schemaLock.writeLock().lock();
                  if (this.classAdder == null) {
                     (new ClassAdder(classNames, (Writer)null)).execute(clr);
                     return;
                  }

                  this.classAdder.addClassTables(classNames, clr);
               } finally {
                  this.schemaLock.writeLock().unlock();
               }

            }
         }
      }
   }

   public void unmanageAllClasses(ClassLoaderResolver clr) {
      DeleteTablesSchemaTransaction deleteTablesTxn = new DeleteTablesSchemaTransaction(this, 2, this.storeDataMgr);
      boolean success = true;

      try {
         deleteTablesTxn.execute(clr);
      } catch (NucleusException ne) {
         success = false;
         throw ne;
      } finally {
         if (success) {
            this.clearSchemaData();
         }

      }

   }

   public Writer getDdlWriter() {
      return this.ddlWriter;
   }

   public boolean getCompleteDDL() {
      return this.completeDDL;
   }

   public boolean hasWrittenDdlStatement(String stmt) {
      return this.writtenDdlStatements != null && this.writtenDdlStatements.contains(stmt);
   }

   public void addWrittenDdlStatement(String stmt) {
      if (this.writtenDdlStatements != null) {
         this.writtenDdlStatements.add(stmt);
      }

   }

   public void validateTable(TableImpl table, ClassLoaderResolver clr) {
      ValidateTableSchemaTransaction validateTblTxn = new ValidateTableSchemaTransaction(this, 2, table);
      validateTblTxn.execute(clr);
   }

   public String getClassNameForObjectID(Object id, ClassLoaderResolver clr, ExecutionContext ec) {
      if (id instanceof SCOID) {
         return ((SCOID)id).getSCOClass();
      } else {
         List<AbstractClassMetaData> rootCmds = new ArrayList();
         if (IdentityUtils.isDatastoreIdentity(id)) {
            String className = IdentityUtils.getTargetClassNameForIdentitySimple(id);
            AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
            rootCmds.add(cmd);
            if (cmd.getIdentityType() != IdentityType.DATASTORE) {
               throw new NucleusUserException(Localiser.msg("050022", new Object[]{id, cmd.getFullClassName()}));
            }
         } else if (IdentityUtils.isSingleFieldIdentity(id)) {
            String className = IdentityUtils.getTargetClassNameForIdentitySimple(id);
            AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
            rootCmds.add(cmd);
            if (cmd.getIdentityType() != IdentityType.APPLICATION || !cmd.getObjectidClass().equals(id.getClass().getName())) {
               throw new NucleusUserException(Localiser.msg("050022", new Object[]{id, cmd.getFullClassName()}));
            }
         } else {
            Collection<AbstractClassMetaData> pkCmds = this.getMetaDataManager().getClassMetaDataWithApplicationId(id.getClass().getName());
            if (pkCmds != null && pkCmds.size() > 0) {
               for(AbstractClassMetaData pkCmd : pkCmds) {
                  AbstractClassMetaData cmdToSwap = null;
                  boolean toAdd = true;

                  for(AbstractClassMetaData rootCmd : rootCmds) {
                     if (rootCmd.isDescendantOf(pkCmd)) {
                        cmdToSwap = rootCmd;
                        toAdd = false;
                        break;
                     }

                     if (pkCmd.isDescendantOf(rootCmd)) {
                        toAdd = false;
                     }
                  }

                  if (cmdToSwap != null) {
                     rootCmds.remove(cmdToSwap);
                     rootCmds.add(pkCmd);
                  } else if (toAdd) {
                     rootCmds.add(pkCmd);
                  }
               }
            }

            if (rootCmds.size() == 0) {
               return null;
            }
         }

         AbstractClassMetaData rootCmd = (AbstractClassMetaData)rootCmds.get(0);
         if (ec == null) {
            if (rootCmds.size() > 1) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug("Id \"" + id + "\" has been determined to be the id of class " + rootCmd.getFullClassName() + " : this is the first of " + rootCmds.size() + " possible, but unable to determine further");
               }

               return rootCmd.getFullClassName();
            } else {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug("Id \"" + id + "\" has been determined to be the id of class " + rootCmd.getFullClassName() + " : unable to determine if actually of a subclass");
               }

               return rootCmd.getFullClassName();
            }
         } else if (rootCmds.size() == 1) {
            Collection<String> subclasses = this.getSubClassesForClass(rootCmd.getFullClassName(), true, clr);
            if (rootCmd.isImplementationOfPersistentDefinition() || subclasses != null && !subclasses.isEmpty()) {
               int numConcrete = 0;
               String concreteClassName = null;
               Class rootCls = clr.classForName(rootCmd.getFullClassName());
               if (!Modifier.isAbstract(rootCls.getModifiers())) {
                  concreteClassName = rootCmd.getFullClassName();
                  ++numConcrete;
               }

               for(String subclassName : subclasses) {
                  Class subcls = clr.classForName(subclassName);
                  if (!Modifier.isAbstract(subcls.getModifiers())) {
                     if (concreteClassName == null) {
                        concreteClassName = subclassName;
                     }

                     ++numConcrete;
                  }
               }

               if (numConcrete == 1) {
                  return concreteClassName;
               } else if (rootCmd.hasDiscriminatorStrategy()) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug("Performing query using discriminator on " + rootCmd.getFullClassName() + " and its subclasses to find the class of " + id);
                  }

                  return RDBMSStoreHelper.getClassNameForIdUsingDiscriminator(this, ec, id, rootCmd);
               } else {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug("Performing query using UNION on " + rootCmd.getFullClassName() + " and its subclasses to find the class of " + id);
                  }

                  return RDBMSStoreHelper.getClassNameForIdUsingUnion(this, ec, id, rootCmds);
               }
            } else {
               return rootCmd.getFullClassName();
            }
         } else {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               StringBuilder str = new StringBuilder();
               Iterator<AbstractClassMetaData> rootCmdIter = rootCmds.iterator();

               while(rootCmdIter.hasNext()) {
                  AbstractClassMetaData cmd = (AbstractClassMetaData)rootCmdIter.next();
                  str.append(cmd.getFullClassName());
                  if (rootCmdIter.hasNext()) {
                     str.append(",");
                  }
               }

               NucleusLogger.PERSISTENCE.debug("Performing query using UNION on " + str.toString() + " and their subclasses to find the class of " + id);
            }

            return RDBMSStoreHelper.getClassNameForIdUsingUnion(this, ec, id, rootCmds);
         }
      }
   }

   public FieldManager getFieldManagerForResultProcessing(ObjectProvider op, ResultSet rs, StatementClassMapping resultMappings) {
      return new ResultSetGetter(this, op, rs, resultMappings);
   }

   public FieldManager getFieldManagerForResultProcessing(ExecutionContext ec, ResultSet rs, StatementClassMapping resultMappings, AbstractClassMetaData cmd) {
      return new ResultSetGetter(this, ec, rs, resultMappings, cmd);
   }

   public FieldManager getFieldManagerForStatementGeneration(ObjectProvider sm, PreparedStatement ps, StatementClassMapping stmtMappings) {
      return new ParameterSetter(sm, ps, stmtMappings);
   }

   public Object getResultValueAtPosition(ResultSet rs, JavaTypeMapping mapping, int position) {
      try {
         return rs.getObject(position);
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException(sqle.getMessage(), sqle);
      }
   }

   protected Object getStrategyValueForGenerator(ValueGenerator generator, final ExecutionContext ec) {
      Object oid = null;
      synchronized(generator) {
         if (generator instanceof AbstractDatastoreGenerator) {
            AbstractDatastoreGenerator.ConnectionPreference connPref = ((AbstractDatastoreGenerator)generator).getConnectionPreference();
            final boolean newConnection;
            if (connPref == ConnectionPreference.NONE) {
               if (!this.getStringProperty("datanucleus.valuegeneration.transactionAttribute").equalsIgnoreCase("UsePM") && !this.getStringProperty("datanucleus.valuegeneration.transactionAttribute").equalsIgnoreCase("EXISTING")) {
                  newConnection = true;
               } else {
                  newConnection = false;
               }
            } else {
               newConnection = connPref == ConnectionPreference.NEW;
            }

            ValueGenerationConnectionProvider connProvider = new ValueGenerationConnectionProvider() {
               ManagedConnection mconn;

               public ManagedConnection retrieveConnection() {
                  if (newConnection) {
                     this.mconn = RDBMSStoreManager.this.getConnection(TransactionUtils.getTransactionIsolationLevelForName(RDBMSStoreManager.this.getStringProperty("datanucleus.valuegeneration.transactionIsolation")));
                  } else {
                     this.mconn = RDBMSStoreManager.this.getConnection(ec);
                  }

                  return this.mconn;
               }

               public void releaseConnection() {
                  try {
                     this.mconn.release();
                     this.mconn = null;
                  } catch (NucleusException e) {
                     String msg = Localiser.msg("050025", new Object[]{e});
                     NucleusLogger.VALUEGENERATION.error(msg);
                     throw new NucleusDataStoreException(msg, e);
                  }
               }
            };
            ((AbstractDatastoreGenerator)generator).setConnectionProvider(connProvider);
         }

         oid = generator.next();
         return oid;
      }
   }

   protected Properties getPropertiesForGenerator(AbstractClassMetaData cmd, int absoluteFieldNumber, ExecutionContext ec, SequenceMetaData seqmd, TableGeneratorMetaData tablegenmd) {
      AbstractMemberMetaData mmd = null;
      IdentityStrategy strategy = null;
      String sequence = null;
      ExtensionMetaData[] extensions = null;
      if (absoluteFieldNumber >= 0) {
         mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absoluteFieldNumber);
         strategy = mmd.getValueStrategy();
         sequence = mmd.getSequence();
         extensions = mmd.getExtensions();
      } else {
         IdentityMetaData idmd = cmd.getBaseIdentityMetaData();
         strategy = idmd.getValueStrategy();
         sequence = idmd.getSequence();
         extensions = idmd.getExtensions();
      }

      DatastoreClass tbl = this.getDatastoreClass(cmd.getBaseAbstractClassMetaData().getFullClassName(), ec.getClassLoaderResolver());
      if (tbl == null) {
         tbl = this.getTableForStrategy(cmd, absoluteFieldNumber, ec.getClassLoaderResolver());
      }

      JavaTypeMapping m = null;
      if (mmd != null) {
         m = tbl.getMemberMapping(mmd);
         if (m == null) {
            tbl = this.getTableForStrategy(cmd, absoluteFieldNumber, ec.getClassLoaderResolver());
            m = tbl.getMemberMapping(mmd);
         }
      } else {
         m = tbl.getIdMapping();
      }

      StringBuilder columnsName = new StringBuilder();

      for(int i = 0; i < m.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            columnsName.append(",");
         }

         columnsName.append(m.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      Properties properties = new Properties();
      properties.setProperty("class-name", cmd.getFullClassName());
      properties.put("root-class-name", cmd.getBaseAbstractClassMetaData().getFullClassName());
      if (mmd != null) {
         properties.setProperty("field-name", mmd.getFullFieldName());
      }

      if (cmd.getCatalog() != null) {
         properties.setProperty("catalog-name", cmd.getCatalog());
      } else if (!StringUtils.isWhitespace(this.catalogName)) {
         properties.setProperty("catalog-name", this.catalogName);
      }

      if (cmd.getSchema() != null) {
         properties.setProperty("schema-name", cmd.getSchema());
      } else if (!StringUtils.isWhitespace(this.schemaName)) {
         properties.setProperty("schema-name", this.schemaName);
      }

      properties.setProperty("table-name", tbl.getIdentifier().toString());
      properties.setProperty("column-name", columnsName.toString());
      if (sequence != null) {
         properties.setProperty("sequence-name", sequence);
      }

      if (extensions != null) {
         for(int i = 0; i < extensions.length; ++i) {
            properties.put(extensions[i].getKey(), extensions[i].getValue());
         }
      }

      if (strategy.equals(IdentityStrategy.NATIVE)) {
         String realStrategyName = this.getStrategyForNative(cmd, absoluteFieldNumber);
         strategy = IdentityStrategy.getIdentityStrategy(realStrategyName);
      }

      if (strategy == IdentityStrategy.INCREMENT && tablegenmd != null) {
         properties.put("key-initial-value", "" + tablegenmd.getInitialValue());
         properties.put("key-cache-size", "" + tablegenmd.getAllocationSize());
         if (tablegenmd.getTableName() != null) {
            properties.put("sequence-table-name", tablegenmd.getTableName());
         }

         if (tablegenmd.getCatalogName() != null) {
            properties.put("sequence-catalog-name", tablegenmd.getCatalogName());
         }

         if (tablegenmd.getSchemaName() != null) {
            properties.put("sequence-schema-name", tablegenmd.getSchemaName());
         }

         if (tablegenmd.getPKColumnName() != null) {
            properties.put("sequence-name-column-name", tablegenmd.getPKColumnName());
         }

         if (tablegenmd.getPKColumnName() != null) {
            properties.put("sequence-nextval-column-name", tablegenmd.getValueColumnName());
         }

         if (tablegenmd.getPKColumnValue() != null) {
            properties.put("sequence-name", tablegenmd.getPKColumnValue());
         }

         properties.remove("table-name");
         properties.remove("column-name");
      } else if (strategy == IdentityStrategy.INCREMENT && tablegenmd == null) {
         if (!properties.containsKey("key-cache-size")) {
            int allocSize = this.getIntProperty("datanucleus.valuegeneration.increment.allocationSize");
            properties.put("key-cache-size", "" + allocSize);
         }
      } else if (strategy == IdentityStrategy.SEQUENCE && seqmd != null) {
         if (StringUtils.isWhitespace(sequence) && seqmd.getName() != null) {
            properties.put("sequence-name", seqmd.getName());
         }

         if (seqmd.getDatastoreSequence() != null) {
            if (seqmd.getInitialValue() >= 0) {
               properties.put("key-initial-value", "" + seqmd.getInitialValue());
            }

            if (seqmd.getAllocationSize() > 0) {
               properties.put("key-cache-size", "" + seqmd.getAllocationSize());
            } else {
               int allocSize = this.getIntProperty("datanucleus.valuegeneration.sequence.allocationSize");
               properties.put("key-cache-size", "" + allocSize);
            }

            properties.put("sequence-name", "" + seqmd.getDatastoreSequence());
            ExtensionMetaData[] seqExtensions = seqmd.getExtensions();
            if (seqExtensions != null) {
               for(int i = 0; i < seqExtensions.length; ++i) {
                  properties.put(seqExtensions[i].getKey(), seqExtensions[i].getValue());
               }
            }
         }
      }

      return properties;
   }

   private DatastoreClass getTableForStrategy(AbstractClassMetaData cmd, int fieldNumber, ClassLoaderResolver clr) {
      DatastoreClass t = this.getDatastoreClass(cmd.getFullClassName(), clr);
      if (t == null && cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
         throw new NucleusUserException(Localiser.msg("032013", new Object[]{cmd.getFullClassName()}));
      } else {
         if (fieldNumber >= 0) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
            t = t.getBaseDatastoreClassWithMember(mmd);
         } else if (t != null) {
            boolean has_superclass = true;

            while(has_superclass) {
               DatastoreClass supert = t.getSuperDatastoreClass();
               if (supert != null) {
                  t = supert;
               } else {
                  has_superclass = false;
               }
            }
         }

         return t;
      }
   }

   public String getStrategyForNative(AbstractClassMetaData cmd, int absFieldNumber) {
      if (this.getBooleanProperty("datanucleus.rdbms.useLegacyNativeValueStrategy")) {
         String sequence = null;
         if (absFieldNumber >= 0) {
            sequence = cmd.getMetaDataForManagedMemberAtAbsolutePosition(absFieldNumber).getSequence();
         } else {
            sequence = cmd.getIdentityMetaData().getSequence();
         }

         return this.dba.supportsOption("Sequences") && sequence != null ? "sequence" : "table-sequence";
      } else {
         return super.getStrategyForNative(cmd, absFieldNumber);
      }
   }

   public SQLTypeInfo getSQLTypeInfoForJDBCType(int jdbcType) throws UnsupportedDataTypeException {
      return this.getSQLTypeInfoForJDBCType(jdbcType, "DEFAULT");
   }

   public SQLTypeInfo getSQLTypeInfoForJDBCType(int jdbcType, String sqlType) throws UnsupportedDataTypeException {
      RDBMSTypesInfo typesInfo = (RDBMSTypesInfo)this.schemaHandler.getSchemaData((Object)null, "types", (Object[])null);
      JDBCTypeInfo jdbcTypeInfo = (JDBCTypeInfo)typesInfo.getChild("" + jdbcType);
      if (jdbcTypeInfo.getNumberOfChildren() == 0) {
         throw new UnsupportedDataTypeException(Localiser.msg("051005", new Object[]{this.dba.getNameForJDBCType(jdbcType)}));
      } else {
         SQLTypeInfo sqlTypeInfo = (SQLTypeInfo)jdbcTypeInfo.getChild(sqlType != null ? sqlType : "DEFAULT");
         if (sqlTypeInfo == null && sqlType != null) {
            sqlTypeInfo = (SQLTypeInfo)jdbcTypeInfo.getChild(sqlType.toUpperCase());
            if (sqlTypeInfo == null) {
               sqlTypeInfo = (SQLTypeInfo)jdbcTypeInfo.getChild(sqlType.toLowerCase());
               if (sqlTypeInfo == null) {
                  NucleusLogger.DATASTORE_SCHEMA.debug("Attempt to find JDBC driver 'typeInfo' for jdbc-type=" + this.dba.getNameForJDBCType(jdbcType) + " but sql-type=" + sqlType + " is not found. Using default sql-type for this jdbc-type.");
                  sqlTypeInfo = (SQLTypeInfo)jdbcTypeInfo.getChild("DEFAULT");
               }
            }
         }

         return sqlTypeInfo;
      }
   }

   public RDBMSColumnInfo getColumnInfoForColumnName(Table table, Connection conn, DatastoreIdentifier column) throws SQLException {
      return (RDBMSColumnInfo)this.schemaHandler.getSchemaData(conn, "column", new Object[]{table, column.getName()});
   }

   public List getColumnInfoForTable(Table table, Connection conn) throws SQLException {
      RDBMSTableInfo tableInfo = (RDBMSTableInfo)this.schemaHandler.getSchemaData(conn, "columns", new Object[]{table});
      if (tableInfo == null) {
         return Collections.EMPTY_LIST;
      } else {
         List cols = new ArrayList(tableInfo.getNumberOfChildren());
         cols.addAll(tableInfo.getChildren());
         return cols;
      }
   }

   public void invalidateColumnInfoForTable(Table table) {
      RDBMSSchemaInfo schemaInfo = (RDBMSSchemaInfo)this.schemaHandler.getSchemaData((Object)null, "tables", (Object[])null);
      if (schemaInfo != null && schemaInfo.getNumberOfChildren() > 0) {
         schemaInfo.getChildren().remove(table.getIdentifier().getFullyQualifiedName(true));
      }

   }

   public Collection getManagedTables(String catalog, String schema) {
      if (this.storeDataMgr == null) {
         return Collections.EMPTY_SET;
      } else {
         Collection tables = new HashSet();

         for(RDBMSStoreData sd : this.storeDataMgr.getManagedStoreData()) {
            if (sd.getTable() != null) {
               DatastoreIdentifier identifier = ((Table)sd.getTable()).getIdentifier();
               boolean catalogMatches = true;
               boolean schemaMatches = true;
               if (catalog != null && identifier.getCatalogName() != null && !catalog.equals(identifier.getCatalogName())) {
                  catalogMatches = false;
               }

               if (schema != null && identifier.getSchemaName() != null && !schema.equals(identifier.getSchemaName())) {
                  schemaMatches = false;
               }

               if (catalogMatches && schemaMatches) {
                  tables.add(sd.getTable());
               }
            }
         }

         return tables;
      }
   }

   public void resolveIdentifierMacro(MacroString.IdentifierMacro im, ClassLoaderResolver clr) {
      DatastoreClass ct = this.getDatastoreClass(im.className, clr);
      if (im.fieldName == null) {
         im.value = ct.getIdentifier().toString();
      } else {
         JavaTypeMapping m;
         if (im.fieldName.equals("this")) {
            if (!(ct instanceof ClassTable)) {
               throw new NucleusUserException(Localiser.msg("050034", new Object[]{im.className}));
            }

            if (im.subfieldName != null) {
               throw new NucleusUserException(Localiser.msg("050035", new Object[]{im.className, im.fieldName, im.subfieldName}));
            }

            m = ct.getIdMapping();
         } else {
            AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(im.className, clr);
            AbstractMemberMetaData mmd = cmd.getMetaDataForMember(im.fieldName);
            m = ct.getMemberMapping(mmd);
            Table t = this.getTable(mmd);
            if (im.subfieldName == null) {
               if (t != null) {
                  im.value = t.getIdentifier().toString();
                  return;
               }
            } else if (t instanceof CollectionTable) {
               CollectionTable collTable = (CollectionTable)t;
               if (im.subfieldName.equals("owner")) {
                  m = collTable.getOwnerMapping();
               } else if (im.subfieldName.equals("element")) {
                  m = collTable.getElementMapping();
               } else {
                  if (!im.subfieldName.equals("index")) {
                     throw new NucleusUserException(Localiser.msg("050036", new Object[]{im.subfieldName, im}));
                  }

                  m = collTable.getOrderMapping();
               }
            } else {
               if (!(t instanceof MapTable)) {
                  throw new NucleusUserException(Localiser.msg("050035", new Object[]{im.className, im.fieldName, im.subfieldName}));
               }

               MapTable mt = (MapTable)t;
               if (im.subfieldName.equals("owner")) {
                  m = mt.getOwnerMapping();
               } else if (im.subfieldName.equals("key")) {
                  m = mt.getKeyMapping();
               } else {
                  if (!im.subfieldName.equals("value")) {
                     throw new NucleusUserException(Localiser.msg("050037", new Object[]{im.subfieldName, im}));
                  }

                  m = mt.getValueMapping();
               }
            }
         }

         im.value = m.getDatastoreMapping(0).getColumn().getIdentifier().toString();
      }
   }

   public void printInformation(String category, PrintStream ps) throws Exception {
      DatastoreAdapter dba = this.getDatastoreAdapter();
      super.printInformation(category, ps);
      if (category.equalsIgnoreCase("DATASTORE")) {
         ps.println(dba.toString());
         ps.println();
         ps.println("Database TypeInfo");
         RDBMSTypesInfo typesInfo = (RDBMSTypesInfo)this.schemaHandler.getSchemaData((Object)null, "types", (Object[])null);
         if (typesInfo != null) {
            for(String jdbcTypeStr : typesInfo.getChildren().keySet()) {
               short jdbcTypeNumber = 0;

               try {
                  jdbcTypeNumber = Short.valueOf(jdbcTypeStr);
               } catch (NumberFormatException var15) {
               }

               JDBCTypeInfo jdbcType = (JDBCTypeInfo)typesInfo.getChild(jdbcTypeStr);
               Collection sqlTypeNames = jdbcType.getChildren().keySet();
               String typeStr = "JDBC Type=" + dba.getNameForJDBCType(jdbcTypeNumber) + " sqlTypes=" + StringUtils.collectionToString(sqlTypeNames);
               ps.println(typeStr);
               SQLTypeInfo sqlType = (SQLTypeInfo)jdbcType.getChild("DEFAULT");
               ps.println(sqlType);
            }
         }

         ps.println("");
         ps.println("Database Keywords");
         Iterator reservedWordsIter = dba.iteratorReservedWords();

         while(reservedWordsIter.hasNext()) {
            Object words = reservedWordsIter.next();
            ps.println(words);
         }

         ps.println("");
      } else if (category.equalsIgnoreCase("SCHEMA")) {
         ps.println(dba.toString());
         ps.println();
         ps.println("TABLES");
         ManagedConnection mc = this.getConnection(-1);

         try {
            Connection conn = (Connection)mc.getConnection();
            RDBMSSchemaInfo schemaInfo = (RDBMSSchemaInfo)this.schemaHandler.getSchemaData(conn, "tables", new Object[]{this.catalogName, this.schemaName});
            if (schemaInfo != null) {
               for(RDBMSTableInfo tableInfo : schemaInfo.getChildren().values()) {
                  ps.println(tableInfo);

                  for(RDBMSColumnInfo colInfo : tableInfo.getChildren()) {
                     ps.println(colInfo);
                  }
               }
            }
         } finally {
            if (mc != null) {
               mc.release();
            }

         }

         ps.println("");
      }

   }

   public Table newJoinTable(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      if (mmd.getJoinMetaData() == null) {
         AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
         if (relatedMmds == null || relatedMmds[0].getJoinMetaData() == null) {
            Class element_class;
            if (mmd.hasCollection()) {
               element_class = clr.classForName(mmd.getCollection().getElementType());
            } else if (mmd.hasMap()) {
               MapMetaData mapmd = (MapMetaData)mmd.getContainer();
               if (mmd.getValueMetaData() != null && mmd.getValueMetaData().getMappedBy() != null) {
                  element_class = clr.classForName(mapmd.getKeyType());
               } else {
                  if (mmd.getKeyMetaData() == null || mmd.getKeyMetaData().getMappedBy() == null) {
                     throw new NucleusUserException(Localiser.msg("050050", new Object[]{mmd.getFullFieldName()}));
                  }

                  element_class = clr.classForName(mapmd.getValueType());
               }
            } else {
               if (!mmd.hasArray()) {
                  return null;
               }

               element_class = clr.classForName(mmd.getTypeName()).getComponentType();
            }

            if (this.getMetaDataManager().getMetaDataForClass(element_class, clr) != null) {
               return null;
            }

            if (ClassUtils.isReferenceType(element_class)) {
               return null;
            }

            throw new NucleusUserException(Localiser.msg("050049", new Object[]{mmd.getFullFieldName(), mmd.toString()}));
         }
      }

      Table joinTable = this.getTable(mmd);
      if (joinTable != null) {
         return joinTable;
      } else if (this.classAdder == null) {
         throw new IllegalStateException(Localiser.msg("050016"));
      } else if (mmd.getType().isArray()) {
         return this.classAdder.addJoinTableForContainer(mmd, clr, 3);
      } else if (Map.class.isAssignableFrom(mmd.getType())) {
         return this.classAdder.addJoinTableForContainer(mmd, clr, 2);
      } else {
         return Collection.class.isAssignableFrom(mmd.getType()) ? this.classAdder.addJoinTableForContainer(mmd, clr, 1) : this.classAdder.addJoinTableForContainer(mmd, clr, 4);
      }
   }

   public void registerTableInitialized(Table table) {
      if (this.classAdder != null) {
         this.classAdder.tablesRecentlyInitialized.add(table);
      }

   }

   public Collection getSupportedOptions() {
      Set set = new HashSet();
      set.add("ApplicationId");
      set.add("ApplicationCompositeId");
      set.add("DatastoreId");
      set.add("NonDurableId");
      set.add("ORM");
      set.add("ORM.SecondaryTable");
      set.add("ORM.EmbeddedPC");
      set.add("ORM.EmbeddedCollection");
      set.add("ORM.EmbeddedMap");
      set.add("ORM.EmbeddedArray");
      set.add("ORM.ForeignKeys");
      if (this.dba.supportsOption("TxIsolationReadCommitted")) {
         set.add("TransactionIsolationLevel.read-committed");
      }

      if (this.dba.supportsOption("TxIsolationReadUncommitted")) {
         set.add("TransactionIsolationLevel.read-uncommitted");
      }

      if (this.dba.supportsOption("TxIsolationReadRepeatableRead")) {
         set.add("TransactionIsolationLevel.repeatable-read");
      }

      if (this.dba.supportsOption("TxIsolationSerializable")) {
         set.add("TransactionIsolationLevel.serializable");
      }

      set.add("Query.Cancel");
      set.add("Datastore.Timeout");
      if (this.dba.supportsOption("BitwiseAndOperator")) {
         set.add("Query.JDOQL.BitwiseOperations");
      }

      return set;
   }

   public boolean insertValuesOnInsert(DatastoreMapping datastoreMapping) {
      return ((AbstractDatastoreMapping)datastoreMapping).insertValuesOnInsert();
   }

   public boolean allowsBatching() {
      return this.dba.supportsOption("StatementBatching") && this.getIntProperty("datanucleus.rdbms.statementBatchLimit") != 0;
   }

   public boolean usesBackedSCOWrappers() {
      return true;
   }

   public boolean useBackedSCOWrapperForMember(AbstractMemberMetaData mmd, ExecutionContext ec) {
      return !mmd.hasCollection() && !mmd.hasMap() || !mmd.hasExtension("type-converter-name");
   }

   public Calendar getCalendarForDateTimezone() {
      if (this.dateTimezoneCalendar == null) {
         String serverTimeZoneID = this.getStringProperty("datanucleus.ServerTimeZoneID");
         TimeZone tz;
         if (serverTimeZoneID != null) {
            tz = TimeZone.getTimeZone(serverTimeZoneID);
         } else {
            tz = TimeZone.getDefault();
         }

         this.dateTimezoneCalendar = new GregorianCalendar(tz);
      }

      return (Calendar)this.dateTimezoneCalendar.clone();
   }

   public void createSchema(String schemaName, Properties props) {
      this.schemaHandler.createSchema(schemaName, props, (Object)null);
   }

   public void deleteSchema(String schemaName, Properties props) {
      this.schemaHandler.deleteSchema(schemaName, props, (Object)null);
   }

   public void createSchemaForClasses(Set inputClassNames, Properties props) {
      Set<String> classNames = cleanInputClassNames(this.nucleusContext, inputClassNames);
      String ddlFilename = props != null ? props.getProperty("ddlFilename") : null;
      String completeDdlProp = props != null ? props.getProperty("completeDdl") : null;
      boolean completeDdl = completeDdlProp != null && completeDdlProp.equalsIgnoreCase("true");
      String autoStartProp = props != null ? props.getProperty("autoStartTable") : null;
      boolean autoStart = autoStartProp != null && autoStartProp.equalsIgnoreCase("true");
      if (classNames.size() > 0) {
         ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
         FileWriter ddlFileWriter = null;

         try {
            if (ddlFilename != null) {
               File ddlFile = StringUtils.getFileForFilename(ddlFilename);
               if (ddlFile.exists()) {
                  ddlFile.delete();
               }

               if (ddlFile.getParentFile() != null && !ddlFile.getParentFile().exists()) {
                  ddlFile.getParentFile().mkdirs();
               }

               ddlFile.createNewFile();
               ddlFileWriter = new FileWriter(ddlFile);
               SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
               ddlFileWriter.write("-- ----------------------------------------------------------------\n");
               ddlFileWriter.write("-- DataNucleus SchemaTool (ran at " + fmt.format(new Date()) + ")\n");
               ddlFileWriter.write("-- ----------------------------------------------------------------\n");
               if (completeDdl) {
                  ddlFileWriter.write("-- Complete schema required for the following classes:-\n");
               } else {
                  ddlFileWriter.write("-- Schema diff for " + this.getConnectionURL() + " and the following classes:-\n");
               }

               Iterator classNameIter = classNames.iterator();

               while(classNameIter.hasNext()) {
                  ddlFileWriter.write("--     " + classNameIter.next() + "\n");
               }

               ddlFileWriter.write("--\n");
            }

            try {
               if (ddlFileWriter != null) {
                  this.ddlWriter = ddlFileWriter;
                  this.completeDDL = completeDdl;
                  this.writtenDdlStatements = new HashSet();
               }

               String[] classNamesArr = this.getNucleusContext().getTypeManager().filterOutSupportedSecondClassNames((String[])classNames.toArray(new String[classNames.size()]));
               if (classNamesArr.length > 0) {
                  (new ClassAdder(classNamesArr, ddlFileWriter)).execute(clr);
               }

               if (autoStart) {
                  if (ddlFileWriter != null) {
                     try {
                        ddlFileWriter.write("\n");
                        ddlFileWriter.write("-- ----------------------------------------------------------------\n");
                        ddlFileWriter.write("-- Table for SchemaTable auto-starter\n");
                     } catch (IOException var18) {
                     }
                  }

                  new SchemaAutoStarter(this, clr);
               }

               if (ddlFileWriter != null) {
                  this.ddlWriter = null;
                  this.completeDDL = false;
                  this.writtenDdlStatements.clear();
                  this.writtenDdlStatements = null;
               }

               if (ddlFileWriter != null) {
                  ddlFileWriter.write("\n");
                  ddlFileWriter.write("-- ----------------------------------------------------------------\n");
                  ddlFileWriter.write("-- Sequences and SequenceTables\n");
               }

               this.createSchemaSequences(classNames, clr, ddlFileWriter);
            } finally {
               if (ddlFileWriter != null) {
                  ddlFileWriter.close();
               }

            }
         } catch (IOException ioe) {
            NucleusLogger.DATASTORE_SCHEMA.error("Exception thrown writing DDL file", ioe);
         }

      } else {
         String msg = Localiser.msg("014039");
         NucleusLogger.DATASTORE_SCHEMA.error(msg);
         System.out.println(msg);
         throw new NucleusException(msg);
      }
   }

   protected void createSchemaSequences(Set classNames, ClassLoaderResolver clr, FileWriter ddlWriter) {
      if (classNames != null && classNames.size() > 0) {
         Set<String> seqTablesGenerated = new HashSet();
         Set<String> sequencesGenerated = new HashSet();

         for(String className : classNames) {
            AbstractClassMetaData cmd = this.getMetaDataManager().getMetaDataForClass(className, clr);
            if (cmd.getIdentityMetaData() != null && cmd.getIdentityMetaData().getValueStrategy() != null) {
               if (cmd.getIdentityMetaData().getValueStrategy() == IdentityStrategy.INCREMENT) {
                  this.addSequenceTableForMetaData(cmd.getIdentityMetaData(), clr, seqTablesGenerated);
               } else if (cmd.getIdentityMetaData().getValueStrategy() == IdentityStrategy.SEQUENCE) {
                  String seqName = cmd.getIdentityMetaData().getSequence();
                  if (StringUtils.isWhitespace(seqName)) {
                     seqName = cmd.getIdentityMetaData().getValueGeneratorName();
                  }

                  if (!StringUtils.isWhitespace(seqName)) {
                     this.addSequenceForMetaData(cmd.getIdentityMetaData(), seqName, clr, sequencesGenerated, ddlWriter);
                  }
               }
            }

            AbstractMemberMetaData[] mmds = cmd.getManagedMembers();

            for(int j = 0; j < mmds.length; ++j) {
               IdentityStrategy str = mmds[j].getValueStrategy();
               if (str == IdentityStrategy.INCREMENT) {
                  this.addSequenceTableForMetaData(mmds[j], clr, seqTablesGenerated);
               } else if (str == IdentityStrategy.SEQUENCE) {
                  String seqName = mmds[j].getSequence();
                  if (StringUtils.isWhitespace(seqName)) {
                     seqName = mmds[j].getValueGeneratorName();
                  }

                  if (!StringUtils.isWhitespace(seqName)) {
                     this.addSequenceForMetaData(mmds[j], seqName, clr, sequencesGenerated, ddlWriter);
                  }
               }
            }
         }
      }

   }

   protected void addSequenceTableForMetaData(MetaData md, ClassLoaderResolver clr, Set seqTablesGenerated) {
      String catName = null;
      String schName = null;
      String tableName = "SEQUENCE_TABLE";
      String seqColName = "SEQUENCE_NAME";
      String nextValColName = "NEXT_VAL";
      if (md.hasExtension("sequence-catalog-name")) {
         catName = md.getValueForExtension("sequence-catalog-name");
      }

      if (md.hasExtension("sequence-schema-name")) {
         schName = md.getValueForExtension("sequence-schema-name");
      }

      if (md.hasExtension("sequence-table-name")) {
         tableName = md.getValueForExtension("sequence-table-name");
      }

      if (md.hasExtension("sequence-name-column-name")) {
         seqColName = md.getValueForExtension("sequence-name-column-name");
      }

      if (md.hasExtension("sequence-nextval-column-name")) {
         nextValColName = md.getValueForExtension("sequence-nextval-column-name");
      }

      if (!seqTablesGenerated.contains(tableName)) {
         ManagedConnection mconn = this.getConnection(0);
         Connection conn = (Connection)mconn.getConnection();

         try {
            DatastoreIdentifier tableIdentifier = this.identifierFactory.newTableIdentifier(tableName);
            if (catName != null) {
               tableIdentifier.setCatalogName(catName);
            }

            if (schName != null) {
               tableIdentifier.setSchemaName(schName);
            }

            SequenceTable seqTable = new SequenceTable(tableIdentifier, this, seqColName, nextValColName);
            seqTable.initialize(clr);
            seqTable.exists(conn, true);
         } catch (Exception var16) {
         } finally {
            mconn.release();
         }

         seqTablesGenerated.add(tableName);
      }

   }

   protected void addSequenceForMetaData(MetaData md, String seq, ClassLoaderResolver clr, Set sequencesGenerated, FileWriter ddlWriter) {
      String seqName = seq;
      Integer min = null;
      Integer max = null;
      Integer start = null;
      Integer increment = null;
      Integer cacheSize = null;
      SequenceMetaData seqmd = this.getMetaDataManager().getMetaDataForSequence(clr, seq);
      if (seqmd != null) {
         seqName = seqmd.getDatastoreSequence();
         if (seqmd.getAllocationSize() > 0) {
            increment = seqmd.getAllocationSize();
         }

         if (seqmd.getInitialValue() >= 0) {
            start = seqmd.getInitialValue();
         }

         md = seqmd;
      }

      if (md.hasExtension("key-min-value")) {
         min = Integer.valueOf(md.getValueForExtension("key-min-value"));
      }

      if (md.hasExtension("key-max-value")) {
         max = Integer.valueOf(md.getValueForExtension("key-max-value"));
      }

      if (md.hasExtension("key-cache-size")) {
         increment = Integer.valueOf(md.getValueForExtension("key-cache-size"));
      }

      if (md.hasExtension("key-initial-value")) {
         start = Integer.valueOf(md.getValueForExtension("key-initial-value"));
      }

      if (md.hasExtension("key-database-cache-size")) {
         cacheSize = Integer.valueOf(md.getValueForExtension("key-database-cache-size"));
      }

      if (!sequencesGenerated.contains(seqName)) {
         String stmt = this.getDatastoreAdapter().getSequenceCreateStmt(seqName, min, max, start, increment, cacheSize);
         if (ddlWriter != null) {
            try {
               ddlWriter.write(stmt + ";\n");
            } catch (IOException var28) {
            }
         } else {
            PreparedStatement ps = null;
            ManagedConnection mconn = this.getConnection(0);

            try {
               ps = this.sqlController.getStatementForUpdate(mconn, stmt, false);
               this.sqlController.executeStatementUpdate((ExecutionContext)null, mconn, stmt, ps, true);
            } catch (SQLException var26) {
            } finally {
               try {
                  if (ps != null) {
                     this.sqlController.closeStatement(mconn, ps);
                  }
               } catch (SQLException var25) {
               }

               mconn.release();
            }
         }

         sequencesGenerated.add(seqName);
      }

   }

   public void deleteSchemaForClasses(Set inputClassNames, Properties props) {
      Set<String> classNames = cleanInputClassNames(this.nucleusContext, inputClassNames);
      if (classNames.size() > 0) {
         String ddlFilename = props != null ? props.getProperty("ddlFilename") : null;
         String completeDdlProp = props != null ? props.getProperty("completeDdl") : null;
         boolean completeDdl = completeDdlProp != null && completeDdlProp.equalsIgnoreCase("true");
         String autoStartProp = props != null ? props.getProperty("autoStartTable") : null;
         boolean autoStart = autoStartProp != null && autoStartProp.equalsIgnoreCase("true");
         ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
         FileWriter ddlFileWriter = null;

         try {
            if (ddlFilename != null) {
               File ddlFile = StringUtils.getFileForFilename(ddlFilename);
               if (ddlFile.exists()) {
                  ddlFile.delete();
               }

               if (ddlFile.getParentFile() != null && !ddlFile.getParentFile().exists()) {
                  ddlFile.getParentFile().mkdirs();
               }

               ddlFile.createNewFile();
               ddlFileWriter = new FileWriter(ddlFile);
               SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
               ddlFileWriter.write("------------------------------------------------------------------\n");
               ddlFileWriter.write("-- DataNucleus SchemaTool (ran at " + fmt.format(new Date()) + ")\n");
               ddlFileWriter.write("------------------------------------------------------------------\n");
               ddlFileWriter.write("-- Delete schema required for the following classes:-\n");
               Iterator classNameIter = classNames.iterator();

               while(classNameIter.hasNext()) {
                  ddlFileWriter.write("--     " + classNameIter.next() + "\n");
               }

               ddlFileWriter.write("--\n");
            }

            try {
               this.performingDeleteSchemaForClasses = true;
               if (ddlFileWriter != null) {
                  this.ddlWriter = ddlFileWriter;
                  this.completeDDL = completeDdl;
                  this.writtenDdlStatements = new HashSet();
               }

               String[] classNameArray = (String[])classNames.toArray(new String[classNames.size()]);
               this.manageClasses(clr, classNameArray);
               DeleteTablesSchemaTransaction deleteTablesTxn = new DeleteTablesSchemaTransaction(this, 2, this.storeDataMgr);
               deleteTablesTxn.setWriter(this.ddlWriter);
               boolean success = true;

               try {
                  deleteTablesTxn.execute(clr);
               } catch (NucleusException ne) {
                  success = false;
                  throw ne;
               } finally {
                  if (success) {
                     this.clearSchemaData();
                  }

               }

               if (autoStart) {
               }
            } finally {
               this.performingDeleteSchemaForClasses = false;
               if (ddlFileWriter != null) {
                  this.ddlWriter = null;
                  this.completeDDL = false;
                  this.writtenDdlStatements.clear();
                  this.writtenDdlStatements = null;
                  ddlFileWriter.close();
               }

            }
         } catch (IOException var28) {
         }

      } else {
         String msg = Localiser.msg("014039");
         NucleusLogger.DATASTORE_SCHEMA.error(msg);
         System.out.println(msg);
         throw new NucleusException(msg);
      }
   }

   public void validateSchemaForClasses(Set inputClassNames, Properties props) {
      Set<String> classNames = cleanInputClassNames(this.nucleusContext, inputClassNames);
      if (classNames != null && classNames.size() > 0) {
         ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
         String[] classNameArray = (String[])classNames.toArray(new String[classNames.size()]);
         this.manageClasses(clr, classNameArray);
      } else {
         String msg = Localiser.msg("014039");
         NucleusLogger.DATASTORE_SCHEMA.error(msg);
         System.out.println(msg);
         throw new NucleusException(msg);
      }
   }

   public void executeScript(String script) {
      script = StringUtils.replaceAll(script, "\n", " ");
      script = StringUtils.replaceAll(script, "\t", " ");
      ManagedConnection mc = this.getConnection(-1);

      try {
         Connection conn = (Connection)mc.getConnection();
         Statement stmt = conn.createStatement();

         try {
            StringTokenizer tokeniser = new StringTokenizer(script, ";");

            while(tokeniser.hasMoreTokens()) {
               String token = tokeniser.nextToken().trim();
               if (!StringUtils.isWhitespace(token)) {
                  NucleusLogger.DATASTORE_NATIVE.debug("Executing script statement : " + token);
                  stmt.execute(token + ";");
               }
            }
         } finally {
            stmt.close();
         }
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_NATIVE.error("Exception executing user script", e);
         throw new NucleusUserException("Exception executing user script. See nested exception for details", e);
      } finally {
         mc.release();
      }

   }

   protected static Set cleanInputClassNames(NucleusContext ctx, Set inputClassNames) {
      Set<String> classNames = new TreeSet();
      if (inputClassNames != null && inputClassNames.size() != 0) {
         classNames.addAll(inputClassNames);
      } else {
         Collection classesWithMetadata = ctx.getMetaDataManager().getClassesWithMetaData();
         classNames.addAll(classesWithMetadata);
      }

      return classNames;
   }

   static {
      Localiser.registerBundle("org.datanucleus.store.rdbms.Localisation", RDBMSStoreManager.class.getClassLoader());
   }

   private class ClassAdder extends AbstractSchemaTransaction {
      public static final int JOIN_TABLE_COLLECTION = 1;
      public static final int JOIN_TABLE_MAP = 2;
      public static final int JOIN_TABLE_ARRAY = 3;
      public static final int JOIN_TABLE_PERSISTABLE = 4;
      private Writer ddlWriter;
      private final boolean checkExistTablesOrViews;
      private Set schemaDataAdded;
      private final String[] classNames;
      private List tablesRecentlyInitialized;
      private int addClassTablesRecursionCounter;

      private ClassAdder(String[] classNames, Writer writer) {
         super(RDBMSStoreManager.this, RDBMSStoreManager.this.dba.getTransactionIsolationForSchemaCreation());
         this.ddlWriter = null;
         this.schemaDataAdded = new HashSet();
         this.tablesRecentlyInitialized = new ArrayList();
         this.addClassTablesRecursionCounter = 0;
         this.ddlWriter = writer;
         this.classNames = classNames;
         this.checkExistTablesOrViews = RDBMSStoreManager.this.getBooleanProperty("datanucleus.rdbms.checkExistTablesOrViews");
      }

      public String toString() {
         return Localiser.msg("050038", new Object[]{RDBMSStoreManager.this.catalogName, RDBMSStoreManager.this.schemaName});
      }

      protected void run(ClassLoaderResolver clr) throws SQLException {
         if (this.classNames != null && this.classNames.length != 0) {
            try {
               RDBMSStoreManager.this.schemaLock.writeLock().lock();
               RDBMSStoreManager.this.classAdder = this;

               try {
                  RDBMSStoreManager.this.storeDataMgr.begin();
                  boolean completed = false;
                  List tablesCreated = null;
                  List tableConstraintsCreated = null;
                  List viewsCreated = null;

                  try {
                     List autoCreateErrors = new ArrayList();
                     this.addClassTables(this.classNames, clr);
                     List<Table>[] toValidate = this.initializeClassTables(this.classNames, clr);
                     if (!RDBMSStoreManager.this.performingDeleteSchemaForClasses) {
                        if (toValidate[0] != null && toValidate[0].size() > 0) {
                           List[] result = this.performTablesValidation(toValidate[0], clr);
                           tablesCreated = result[0];
                           tableConstraintsCreated = result[1];
                           autoCreateErrors = result[2];
                        }

                        if (toValidate[1] != null && toValidate[1].size() > 0) {
                           List[] result = this.performViewsValidation(toValidate[1]);
                           viewsCreated = result[0];
                           autoCreateErrors.addAll(result[1]);
                        }

                        if (autoCreateErrors.size() > 0) {
                           for(Throwable exc : autoCreateErrors) {
                              if (this.rdbmsMgr.getSchemaHandler().isAutoCreateWarnOnError()) {
                                 NucleusLogger.DATASTORE.warn(Localiser.msg("050044", new Object[]{exc}));
                              } else {
                                 NucleusLogger.DATASTORE.error(Localiser.msg("050044", new Object[]{exc}));
                              }
                           }

                           if (!this.rdbmsMgr.getSchemaHandler().isAutoCreateWarnOnError()) {
                              throw new NucleusDataStoreException(Localiser.msg("050043"), (Throwable[])autoCreateErrors.toArray(new Throwable[autoCreateErrors.size()]));
                           }
                        }
                     }

                     completed = true;
                  } catch (SQLException sqle) {
                     String msg = Localiser.msg("050044", new Object[]{sqle});
                     NucleusLogger.DATASTORE_SCHEMA.error(msg);
                     throw new NucleusDataStoreException(msg, sqle);
                  } catch (Exception e) {
                     if (NucleusException.class.isAssignableFrom(e.getClass())) {
                        throw (NucleusException)e;
                     }

                     NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("050044", new Object[]{e}));
                     throw (new NucleusException(e.toString(), e)).setFatal();
                  } finally {
                     if (!completed) {
                        RDBMSStoreManager.this.storeDataMgr.rollback();
                        this.rollbackSchemaCreation(viewsCreated, tableConstraintsCreated, tablesCreated);
                     } else {
                        RDBMSStoreManager.this.storeDataMgr.commit();
                     }

                     this.schemaDataAdded.clear();
                  }
               } finally {
                  RDBMSStoreManager.this.classAdder = null;
               }
            } finally {
               RDBMSStoreManager.this.schemaLock.writeLock().unlock();
            }

         }
      }

      public void addClassTables(String[] classNames, ClassLoaderResolver clr) {
         ++this.addClassTablesRecursionCounter;

         try {
            Iterator iter = RDBMSStoreManager.this.getMetaDataManager().getReferencedClasses(classNames, clr).iterator();
            AutoStartMechanism starter = this.rdbmsMgr.getNucleusContext().getAutoStartMechanism();

            try {
               if (starter != null && !starter.isOpen()) {
                  starter.open();
               }

               while(iter.hasNext()) {
                  this.addClassTable((ClassMetaData)iter.next(), clr);
               }

               for(RDBMSStoreData data : new HashSet(this.schemaDataAdded)) {
                  if (data.getTable() == null && data.isFCO()) {
                     AbstractClassMetaData cmd = (AbstractClassMetaData)data.getMetaData();
                     InheritanceMetaData imd = cmd.getInheritanceMetaData();
                     if (imd.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
                        AbstractClassMetaData[] managingCmds = RDBMSStoreManager.this.getClassesManagingTableForClass(cmd, clr);
                        DatastoreClass superTable = null;
                        if (managingCmds != null && managingCmds.length == 1) {
                           RDBMSStoreData superData = (RDBMSStoreData)RDBMSStoreManager.this.storeDataMgr.get(managingCmds[0].getFullClassName());
                           if (superData == null) {
                              this.addClassTables(new String[]{managingCmds[0].getFullClassName()}, clr);
                              superData = (RDBMSStoreData)RDBMSStoreManager.this.storeDataMgr.get(managingCmds[0].getFullClassName());
                           }

                           if (superData == null) {
                              String msg = Localiser.msg("050013", new Object[]{cmd.getFullClassName()});
                              NucleusLogger.PERSISTENCE.error(msg);
                              throw new NucleusUserException(msg);
                           }

                           superTable = (DatastoreClass)superData.getTable();
                           data.setDatastoreContainerObject(superTable);
                        }
                     }
                  }
               }
            } finally {
               if (starter != null && starter.isOpen() && this.addClassTablesRecursionCounter <= 1) {
                  starter.close();
               }

            }
         } finally {
            --this.addClassTablesRecursionCounter;
         }

      }

      private void addClassTable(ClassMetaData cmd, ClassLoaderResolver clr) {
         if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
            if (cmd.getIdentityType() != IdentityType.NONDURABLE || !cmd.hasExtension("requires-table") || cmd.getValueForExtension("requires-table") == null || !cmd.getValueForExtension("requires-table").equalsIgnoreCase("false")) {
               if (!RDBMSStoreManager.this.storeDataMgr.managesClass(cmd.getFullClassName())) {
                  if (cmd.getIdentityType() == IdentityType.APPLICATION && !cmd.usesSingleFieldIdentityClass()) {
                     String baseClassWithMetaData = cmd.getBaseAbstractClassMetaData().getFullClassName();
                     Collection<AbstractClassMetaData> pkCmds = RDBMSStoreManager.this.getMetaDataManager().getClassMetaDataWithApplicationId(cmd.getObjectidClass());
                     if (pkCmds != null && pkCmds.size() > 0) {
                        boolean in_same_tree = false;
                        String sample_class_in_other_tree = null;

                        for(AbstractClassMetaData pkCmd : pkCmds) {
                           String otherClassBaseClass = pkCmd.getBaseAbstractClassMetaData().getFullClassName();
                           if (otherClassBaseClass.equals(baseClassWithMetaData)) {
                              in_same_tree = true;
                              break;
                           }

                           sample_class_in_other_tree = pkCmd.getFullClassName();
                        }

                        if (!in_same_tree) {
                           String error_msg = Localiser.msg("050021", new Object[]{cmd.getFullClassName(), cmd.getObjectidClass(), sample_class_in_other_tree});
                           NucleusLogger.DATASTORE.error(error_msg);
                           throw new NucleusUserException(error_msg);
                        }
                     }
                  }

                  if (cmd.isEmbeddedOnly()) {
                     NucleusLogger.DATASTORE.debug(Localiser.msg("032012", new Object[]{cmd.getFullClassName()}));
                  } else {
                     InheritanceMetaData imd = cmd.getInheritanceMetaData();
                     RDBMSStoreData sdNew = null;
                     if (imd.getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                        sdNew = new RDBMSStoreData(cmd, (Table)null, false);
                        RDBMSStoreManager.this.registerStoreData(sdNew);
                     } else if (imd.getStrategy() == InheritanceStrategy.COMPLETE_TABLE && cmd.isAbstract()) {
                        sdNew = new RDBMSStoreData(cmd, (Table)null, false);
                        RDBMSStoreManager.this.registerStoreData(sdNew);
                     } else if (imd.getStrategy() != InheritanceStrategy.NEW_TABLE && imd.getStrategy() != InheritanceStrategy.COMPLETE_TABLE) {
                        if (imd.getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE) {
                           AbstractClassMetaData[] managingCmds = RDBMSStoreManager.this.getClassesManagingTableForClass(cmd, clr);
                           Table superTable = null;
                           if (managingCmds == null || managingCmds.length != 1) {
                              String msg = Localiser.msg("050013", new Object[]{cmd.getFullClassName()});
                              NucleusLogger.PERSISTENCE.error(msg);
                              throw new NucleusUserException(msg);
                           }

                           RDBMSStoreData superData = (RDBMSStoreData)RDBMSStoreManager.this.storeDataMgr.get(managingCmds[0].getFullClassName());
                           if (superData != null) {
                              superTable = (Table)superData.getTable();
                           }

                           sdNew = new RDBMSStoreData(cmd, superTable, false);
                           this.rdbmsMgr.registerStoreData(sdNew);
                        }
                     } else {
                        DatastoreIdentifier tableName = null;
                        RDBMSStoreData tmpData = (RDBMSStoreData)RDBMSStoreManager.this.storeDataMgr.get(cmd.getFullClassName());
                        if (tmpData != null && tmpData.getDatastoreIdentifier() != null) {
                           tableName = tmpData.getDatastoreIdentifier();
                        } else {
                           tableName = this.rdbmsMgr.getIdentifierFactory().newTableIdentifier((AbstractClassMetaData)cmd);
                        }

                        StoreData[] existingStoreData = RDBMSStoreManager.this.getStoreDataForDatastoreContainerObject(tableName);
                        if (existingStoreData != null) {
                           String existingClass = null;

                           for(int j = 0; j < existingStoreData.length; ++j) {
                              if (!existingStoreData[j].getName().equals(cmd.getFullClassName())) {
                                 existingClass = existingStoreData[j].getName();
                                 break;
                              }
                           }

                           if (existingClass != null) {
                              NucleusLogger.DATASTORE.warn(Localiser.msg("050015", new Object[]{cmd.getFullClassName(), tableName.getName(), existingClass}));
                           }
                        }

                        DatastoreClass t = null;
                        boolean hasViewDef = false;
                        if (RDBMSStoreManager.this.dba.getVendorID() != null) {
                           hasViewDef = cmd.hasExtension("view-definition-" + RDBMSStoreManager.this.dba.getVendorID());
                        }

                        if (!hasViewDef) {
                           hasViewDef = cmd.hasExtension("view-definition");
                        }

                        Object var22;
                        if (hasViewDef) {
                           var22 = new ClassView(tableName, RDBMSStoreManager.this, cmd);
                        } else {
                           var22 = new ClassTable(tableName, RDBMSStoreManager.this, cmd);
                        }

                        sdNew = new RDBMSStoreData(cmd, (Table)var22, true);
                        this.rdbmsMgr.registerStoreData(sdNew);
                        ((Table)var22).preInitialize(clr);
                     }

                     this.schemaDataAdded.add(sdNew);
                  }
               }

            }
         }
      }

      private List[] initializeClassTables(String[] classNames, ClassLoaderResolver clr) {
         List<Table> tablesToValidate = new ArrayList();
         List<Table> viewsToValidate = new ArrayList();
         this.tablesRecentlyInitialized.clear();
         int numTablesInitializedInit = 0;
         int numStoreDataInit = 0;
         RDBMSStoreData[] rdbmsStoreData = (RDBMSStoreData[])RDBMSStoreManager.this.storeDataMgr.getManagedStoreData().toArray(new RDBMSStoreData[RDBMSStoreManager.this.storeDataMgr.size()]);

         do {
            numStoreDataInit = rdbmsStoreData.length;
            numTablesInitializedInit = this.tablesRecentlyInitialized.size();

            for(int i = 0; i < rdbmsStoreData.length; ++i) {
               RDBMSStoreData currentStoreData = rdbmsStoreData[i];
               if (currentStoreData.hasTable()) {
                  Table t = (Table)currentStoreData.getTable();
                  if (t instanceof DatastoreClass) {
                     ((RDBMSPersistenceHandler)this.rdbmsMgr.getPersistenceHandler()).removeRequestsForTable((DatastoreClass)t);
                  }

                  if (!t.isInitialized()) {
                     t.initialize(clr);
                  }

                  if (!currentStoreData.isTableOwner() && !((ClassTable)t).managesClass(currentStoreData.getName())) {
                     ((ClassTable)t).manageClass((ClassMetaData)currentStoreData.getMetaData(), clr);
                     if (!tablesToValidate.contains(t)) {
                        tablesToValidate.add(t);
                     }
                  }
               }
            }

            rdbmsStoreData = (RDBMSStoreData[])RDBMSStoreManager.this.storeDataMgr.getManagedStoreData().toArray(new RDBMSStoreData[RDBMSStoreManager.this.storeDataMgr.size()]);
         } while(this.tablesRecentlyInitialized.size() > numTablesInitializedInit || rdbmsStoreData.length > numStoreDataInit);

         for(int j = 0; j < this.tablesRecentlyInitialized.size(); ++j) {
            ((Table)this.tablesRecentlyInitialized.get(j)).postInitialize(clr);
         }

         for(Table t : this.tablesRecentlyInitialized) {
            if (t instanceof ViewImpl) {
               viewsToValidate.add(t);
            } else if (!tablesToValidate.contains(t)) {
               tablesToValidate.add(t);
            }
         }

         return new List[]{tablesToValidate, viewsToValidate};
      }

      private List[] performTablesValidation(List tablesToValidate, ClassLoaderResolver clr) throws SQLException {
         List autoCreateErrors = new ArrayList();
         List<Table> tableConstraintsCreated = new ArrayList();
         List<Table> tablesCreated = new ArrayList();
         if (this.ddlWriter != null) {
            List<Table> tmpTablesToValidate = new ArrayList();

            for(Table tbl : tablesToValidate) {
               if (!tmpTablesToValidate.contains(tbl)) {
                  tmpTablesToValidate.add(tbl);
               }
            }

            tablesToValidate = tmpTablesToValidate;
         }

         for(TableImpl t : tablesToValidate) {
            boolean columnsValidated = false;
            boolean columnsInitialised = false;
            if (this.checkExistTablesOrViews) {
               if (this.ddlWriter != null) {
                  try {
                     if (t instanceof ClassTable) {
                        this.ddlWriter.write("-- Table " + t.toString() + " for classes " + StringUtils.objectArrayToString(((ClassTable)t).getManagedClasses()) + "\n");
                     } else if (t instanceof JoinTable) {
                        this.ddlWriter.write("-- Table " + t.toString() + " for join relationship\n");
                     }
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file for table " + t, ioe);
                  }
               }

               if (!tablesCreated.contains(t) && t.exists(this.getCurrentConnection(), this.rdbmsMgr.getSchemaHandler().isAutoCreateTables())) {
                  tablesCreated.add(t);
                  columnsValidated = true;
               } else if (t.isInitializedModified() || this.rdbmsMgr.getSchemaHandler().isAutoCreateColumns()) {
                  t.validateColumns(this.getCurrentConnection(), false, this.rdbmsMgr.getSchemaHandler().isAutoCreateColumns(), autoCreateErrors);
                  columnsValidated = true;
               }
            }

            if (this.rdbmsMgr.getSchemaHandler().isValidateTables() && !columnsValidated) {
               t.validate(this.getCurrentConnection(), this.rdbmsMgr.getSchemaHandler().isValidateColumns(), false, autoCreateErrors);
               columnsInitialised = this.rdbmsMgr.getSchemaHandler().isValidateColumns();
            }

            if (!columnsInitialised) {
               String initInfo = RDBMSStoreManager.this.getStringProperty("datanucleus.rdbms.initializeColumnInfo");
               if (initInfo.equalsIgnoreCase("PK")) {
                  t.initializeColumnInfoForPrimaryKeyColumns(this.getCurrentConnection());
               } else if (initInfo.equalsIgnoreCase("ALL")) {
                  t.initializeColumnInfoFromDatastore(this.getCurrentConnection());
               }
            }

            RDBMSStoreManager.this.invalidateColumnInfoForTable(t);
         }

         for(TableImpl t : tablesToValidate) {
            if (this.rdbmsMgr.getSchemaHandler().isValidateConstraints() || this.rdbmsMgr.getSchemaHandler().isAutoCreateConstraints()) {
               if (this.ddlWriter != null) {
                  try {
                     if (t instanceof ClassTable) {
                        this.ddlWriter.write("-- Constraints for table " + t.toString() + " for class(es) " + StringUtils.objectArrayToString(((ClassTable)t).getManagedClasses()) + "\n");
                     } else {
                        this.ddlWriter.write("-- Constraints for table " + t.toString() + "\n");
                     }
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file for table " + t, ioe);
                  }
               }

               if (tablesCreated.contains(t) && !this.hasDuplicateTablesFromList(tablesToValidate)) {
                  if (t.createConstraints(this.getCurrentConnection(), autoCreateErrors, clr)) {
                     tableConstraintsCreated.add(t);
                  }
               } else if (t.validateConstraints(this.getCurrentConnection(), this.rdbmsMgr.getSchemaHandler().isAutoCreateConstraints(), autoCreateErrors, clr)) {
                  tableConstraintsCreated.add(t);
               }

               if (this.ddlWriter != null) {
                  try {
                     this.ddlWriter.write("\n");
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file for table " + t, ioe);
                  }
               }
            }
         }

         return new List[]{tablesCreated, tableConstraintsCreated, autoCreateErrors};
      }

      private boolean hasDuplicateTablesFromList(List newTables) {
         Map map = new HashMap();

         for(int i = 0; i < newTables.size(); ++i) {
            Table t1 = (Table)newTables.get(i);
            if (map.containsKey(t1.getIdentifier().getName())) {
               return true;
            }

            map.put(t1.getIdentifier().getName(), t1);
         }

         return false;
      }

      private List[] performViewsValidation(List viewsToValidate) throws SQLException {
         List<Table> viewsCreated = new ArrayList();
         List autoCreateErrors = new ArrayList();

         for(ViewImpl v : viewsToValidate) {
            if (this.checkExistTablesOrViews && v.exists(this.getCurrentConnection(), this.rdbmsMgr.getSchemaHandler().isAutoCreateTables())) {
               viewsCreated.add(v);
            }

            if (this.rdbmsMgr.getSchemaHandler().isValidateTables()) {
               v.validate(this.getCurrentConnection(), true, false, autoCreateErrors);
            }

            RDBMSStoreManager.this.invalidateColumnInfoForTable(v);
         }

         return new List[]{viewsCreated, autoCreateErrors};
      }

      private void rollbackSchemaCreation(List viewsCreated, List tableConstraintsCreated, List tablesCreated) {
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050040"));
         }

         try {
            if (viewsCreated != null) {
               ListIterator li = viewsCreated.listIterator(viewsCreated.size());

               while(li.hasPrevious()) {
                  ((ViewImpl)li.previous()).drop(this.getCurrentConnection());
               }
            }

            if (tableConstraintsCreated != null) {
               ListIterator li = tableConstraintsCreated.listIterator(tableConstraintsCreated.size());

               while(li.hasPrevious()) {
                  ((TableImpl)li.previous()).dropConstraints(this.getCurrentConnection());
               }
            }

            if (tablesCreated != null) {
               ListIterator li = tablesCreated.listIterator(tablesCreated.size());

               while(li.hasPrevious()) {
                  ((TableImpl)li.previous()).drop(this.getCurrentConnection());
               }
            }
         } catch (Exception e) {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("050041", new Object[]{e}));
         }

         AutoStartMechanism starter = this.rdbmsMgr.getNucleusContext().getAutoStartMechanism();
         if (starter != null) {
            try {
               if (!starter.isOpen()) {
                  starter.open();
               }

               for(RDBMSStoreData sd : this.schemaDataAdded) {
                  starter.deleteClass(sd.getName());
               }
            } finally {
               if (starter.isOpen()) {
                  starter.close();
               }

            }
         }

      }

      private Table addJoinTableForContainer(AbstractMemberMetaData mmd, ClassLoaderResolver clr, int type) {
         DatastoreIdentifier tableName = null;
         RDBMSStoreData sd = (RDBMSStoreData)RDBMSStoreManager.this.storeDataMgr.get(mmd);
         if (sd != null && sd.getDatastoreIdentifier() != null) {
            tableName = sd.getDatastoreIdentifier();
         } else {
            tableName = RDBMSStoreManager.this.identifierFactory.newTableIdentifier(mmd);
         }

         Table join = null;
         if (type == 1) {
            join = new CollectionTable(tableName, mmd, RDBMSStoreManager.this);
         } else if (type == 2) {
            join = new MapTable(tableName, mmd, RDBMSStoreManager.this);
         } else if (type == 3) {
            join = new ArrayTable(tableName, mmd, RDBMSStoreManager.this);
         } else if (type == 4) {
            join = new PersistableJoinTable(tableName, mmd, RDBMSStoreManager.this);
         }

         AutoStartMechanism starter = this.rdbmsMgr.getNucleusContext().getAutoStartMechanism();

         try {
            if (starter != null && !starter.isOpen()) {
               starter.open();
            }

            RDBMSStoreData data = new RDBMSStoreData(mmd, join);
            this.schemaDataAdded.add(data);
            this.rdbmsMgr.registerStoreData(data);
         } finally {
            if (starter != null && starter.isOpen()) {
               starter.close();
            }

         }

         return join;
      }
   }
}
