package org.datanucleus.store.rdbms.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ColumnMetaDataContainer;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.ForeignKeyAction;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.OrderMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.ClassDefinitionException;
import org.datanucleus.store.rdbms.exceptions.DuplicateColumnException;
import org.datanucleus.store.rdbms.exceptions.NoSuchPersistentFieldException;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.CorrespondentColumnsMapper;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.DiscriminatorMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.IndexMapping;
import org.datanucleus.store.rdbms.mapping.java.IntegerMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.LongMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.mapping.java.VersionLongMapping;
import org.datanucleus.store.rdbms.mapping.java.VersionTimestampMapping;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MacroString;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ClassTable extends AbstractClassTable implements DatastoreClass {
   private final ClassMetaData cmd;
   private final Collection managedClassMetaData = new HashSet();
   private final Map callbacksAppliedForManagedClass = new HashMap();
   private ClassTable supertable;
   private Map secondaryTables;
   private Map externalFkMappings;
   private Map externalFkDiscriminatorMappings;
   private Map externalOrderMappings;
   private MacroString tableDef;
   private String createStatementDDL;
   Map candidateKeysByMapField = new HashMap();
   Set unmappedColumns = null;
   protected transient String managingClassCurrent = null;
   protected boolean runCallbacksAfterManageClass = false;

   public ClassTable(DatastoreIdentifier tableName, RDBMSStoreManager storeMgr, ClassMetaData cmd) {
      super(tableName, storeMgr);
      this.cmd = cmd;
      if (cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.NEW_TABLE && cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.COMPLETE_TABLE) {
         throw (new NucleusUserException(Localiser.msg("057003", new Object[]{cmd.getFullClassName(), cmd.getInheritanceMetaData().getStrategy().toString()}))).setFatal();
      } else {
         this.highestMemberNumber = cmd.getNoOfManagedMembers() + cmd.getNoOfInheritedManagedMembers();
         String tableImpStr = cmd.getValueForExtension("ddl-imports");
         String tableDefStr = null;
         if (this.dba.getVendorID() != null) {
            tableDefStr = cmd.getValueForExtension("ddl-definition-" + this.dba.getVendorID());
         }

         if (tableDefStr == null) {
            tableDefStr = cmd.getValueForExtension("ddl-definition");
         }

         if (tableDefStr != null) {
            this.tableDef = new MacroString(cmd.getFullClassName(), tableImpStr, tableDefStr);
         }

      }
   }

   public void preInitialize(ClassLoaderResolver clr) {
      this.assertIsPKUninitialized();
      if (this.cmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.COMPLETE_TABLE) {
         this.supertable = this.getSupertable(this.cmd, clr);
         if (this.supertable != null && !this.supertable.isInitialized() && !this.supertable.isPKInitialized()) {
            this.supertable.preInitialize(clr);
         }
      }

      if (!this.isPKInitialized()) {
         this.initializePK(clr);
      }

   }

   public void initialize(ClassLoaderResolver clr) {
      if (!this.isInitialized()) {
         if (this.supertable != null) {
            this.supertable.initialize(clr);
         }

         this.initializeForClass(this.cmd, clr);
         MappingManager mapMgr = this.storeMgr.getMappingManager();
         this.versionMetaData = this.cmd.getVersionMetaDataForTable();
         if (this.versionMetaData != null && this.versionMetaData.getFieldName() == null) {
            if (this.versionMetaData.getVersionStrategy() == VersionStrategy.NONE) {
               this.versionMapping = new VersionLongMapping(this, mapMgr.getMapping(Long.class));
            } else if (this.versionMetaData.getVersionStrategy() == VersionStrategy.VERSION_NUMBER) {
               this.versionMapping = new VersionLongMapping(this, mapMgr.getMapping(Long.class));
            } else if (this.versionMetaData.getVersionStrategy() == VersionStrategy.DATE_TIME) {
               if (!this.dba.supportsOption("DateTimeStoresMillisecs")) {
                  throw new NucleusException("Class " + this.cmd.getFullClassName() + " is defined to use date-time versioning, yet this datastore doesnt support storing milliseconds in DATETIME/TIMESTAMP columns. Use version-number");
               }

               this.versionMapping = new VersionTimestampMapping(this, mapMgr.getMapping(Timestamp.class));
            }

            if (this.versionMapping != null) {
               this.logMapping("VERSION", this.versionMapping);
            }
         }

         DiscriminatorMetaData dismd = this.cmd.getDiscriminatorMetaDataForTable();
         if (dismd != null) {
            this.discriminatorMetaData = dismd;
            if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.discriminatorPerSubclassTable")) {
               this.discriminatorMapping = DiscriminatorMapping.createDiscriminatorMapping(this, dismd);
            } else {
               ClassTable tableWithDiscrim = this.getTableWithDiscriminator();
               if (tableWithDiscrim == this) {
                  this.discriminatorMapping = DiscriminatorMapping.createDiscriminatorMapping(this, dismd);
               }
            }

            if (this.discriminatorMapping != null) {
               this.logMapping("DISCRIMINATOR", this.discriminatorMapping);
            }
         }

         if (this.storeMgr.getStringProperty("datanucleus.TenantID") != null && !"true".equalsIgnoreCase(this.cmd.getValueForExtension("multitenancy-disable"))) {
            ColumnMetaData colmd = new ColumnMetaData();
            if (this.cmd.hasExtension("multitenancy-column-name")) {
               colmd.setName(this.cmd.getValueForExtension("multitenancy-column-name"));
            }

            if (this.cmd.hasExtension("multitenancy-jdbc-type")) {
               colmd.setJdbcType(this.cmd.getValueForExtension("multitenancy-jdbc-type"));
            }

            if (this.cmd.hasExtension("multitenancy-column-length")) {
               colmd.setLength(this.cmd.getValueForExtension("multitenancy-column-length"));
            }

            this.addMultitenancyMapping(colmd);
         }

         if (this.secondaryTables != null) {
            for(String secondaryTableName : this.secondaryTables.keySet()) {
               SecondaryTable second = (SecondaryTable)this.secondaryTables.get(secondaryTableName);
               if (!second.isInitialized()) {
                  second.initialize(clr);
               }
            }
         }

         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
         }

         this.storeMgr.registerTableInitialized(this);
         this.state = 2;
      }
   }

   public void postInitialize(final ClassLoaderResolver clr) {
      this.assertIsInitialized();
      this.runCallBacks(clr);
      if (this.tableDef != null) {
         this.createStatementDDL = this.tableDef.substituteMacros(new MacroString.MacroHandler() {
            public void onIdentifierMacro(MacroString.IdentifierMacro im) {
               ClassTable.this.storeMgr.resolveIdentifierMacro(im, clr);
            }

            public void onParameterMacro(MacroString.ParameterMacro pm) {
               throw new NucleusUserException(Localiser.msg("057033", new Object[]{ClassTable.this.cmd.getFullClassName(), pm}));
            }
         }, clr);
      }

   }

   public void manageClass(AbstractClassMetaData theCmd, ClassLoaderResolver clr) {
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057024", new Object[]{this.toString(), theCmd.getFullClassName(), theCmd.getInheritanceMetaData().getStrategy().toString()}));
      }

      this.managingClassCurrent = theCmd.getFullClassName();
      this.managedClassMetaData.add(theCmd);
      this.manageMembers(theCmd, clr, theCmd.getManagedMembers());
      this.manageMembers(theCmd, clr, theCmd.getOverriddenMembers());
      this.manageUnmappedColumns(theCmd, clr);
      this.managingClassCurrent = null;
      if (this.runCallbacksAfterManageClass) {
         this.runCallBacks(clr);
         this.runCallbacksAfterManageClass = false;
      }

   }

   public String[] getManagedClasses() {
      String[] classNames = new String[this.managedClassMetaData.size()];
      Iterator<AbstractClassMetaData> iter = this.managedClassMetaData.iterator();

      for(int i = 0; iter.hasNext(); classNames[i++] = ((AbstractClassMetaData)iter.next()).getFullClassName()) {
      }

      return classNames;
   }

   private void manageMembers(AbstractClassMetaData theCmd, ClassLoaderResolver clr, AbstractMemberMetaData[] mmds) {
      for(int fieldNumber = 0; fieldNumber < mmds.length; ++fieldNumber) {
         AbstractMemberMetaData mmd = mmds[fieldNumber];
         if (!mmd.isPrimaryKey()) {
            if (this.managesMember(mmd.getFullFieldName())) {
               if (!mmd.getClassName(true).equals(theCmd.getFullClassName())) {
                  JavaTypeMapping fieldMapping = this.getMappingForMemberName(mmd.getFullFieldName());
                  ColumnMetaData[] colmds = mmd.getColumnMetaData();
                  if (colmds != null && colmds.length > 0) {
                     int colnum = 0;
                     IdentifierFactory idFactory = this.getStoreManager().getIdentifierFactory();

                     for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
                        Column col = fieldMapping.getDatastoreMapping(i).getColumn();
                        col.setIdentifier(idFactory.newColumnIdentifier(colmds[colnum].getName()));
                        col.setColumnMetaData(colmds[colnum]);
                        ++colnum;
                        if (colnum == colmds.length) {
                           break;
                        }
                     }

                     this.logMapping(mmd.getFullFieldName(), fieldMapping);
                  }
               }
            } else {
               if (mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
                  boolean isPrimary = true;
                  if (mmd.getTable() != null && mmd.getJoinMetaData() == null) {
                     isPrimary = false;
                  }

                  if (isPrimary) {
                     JavaTypeMapping fieldMapping = this.storeMgr.getMappingManager().getMapping(this, mmd, clr, FieldRole.ROLE_FIELD);
                     if (theCmd != this.cmd && theCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUPERCLASS_TABLE && fieldMapping.getNumberOfDatastoreMappings() > 0) {
                        int numCols = fieldMapping.getNumberOfDatastoreMappings();

                        for(int colNum = 0; colNum < numCols; ++colNum) {
                           Column col = fieldMapping.getDatastoreMapping(colNum).getColumn();
                           if (col.getDefaultValue() == null && !col.isNullable()) {
                              NucleusLogger.DATASTORE_SCHEMA.debug("Member " + mmd.getFullFieldName() + " uses superclass-table yet the field is not marked as nullable  nor does it have a default value, so setting the column as nullable");
                              col.setNullable(true);
                           }
                        }
                     }

                     this.addMemberMapping(fieldMapping);
                  } else {
                     if (this.secondaryTables == null) {
                        this.secondaryTables = new HashMap();
                     }

                     SecondaryTable secTable = (SecondaryTable)this.secondaryTables.get(mmd.getTable());
                     if (secTable == null) {
                        JoinMetaData[] joinmds = theCmd.getJoinMetaData();
                        JoinMetaData joinmd = null;
                        if (joinmds != null) {
                           for(int j = 0; j < joinmds.length; ++j) {
                              if (joinmds[j].getTable().equalsIgnoreCase(mmd.getTable()) && (joinmds[j].getCatalog() == null || joinmds[j].getCatalog() != null && joinmds[j].getCatalog().equalsIgnoreCase(mmd.getCatalog())) && (joinmds[j].getSchema() == null || joinmds[j].getSchema() != null && joinmds[j].getSchema().equalsIgnoreCase(mmd.getSchema()))) {
                                 joinmd = joinmds[j];
                                 break;
                              }
                           }
                        }

                        DatastoreIdentifier secTableIdentifier = this.storeMgr.getIdentifierFactory().newTableIdentifier(mmd.getTable());
                        String catalogName = mmd.getCatalog();
                        if (catalogName == null) {
                           catalogName = this.getCatalogName();
                        }

                        String schemaName = mmd.getSchema();
                        if (schemaName == null) {
                           schemaName = this.getSchemaName();
                        }

                        secTableIdentifier.setCatalogName(catalogName);
                        secTableIdentifier.setSchemaName(schemaName);
                        secTable = new SecondaryTable(secTableIdentifier, this.storeMgr, this, joinmd, clr);
                        secTable.preInitialize(clr);
                        secTable.initialize(clr);
                        secTable.postInitialize(clr);
                        this.secondaryTables.put(mmd.getTable(), secTable);
                     }

                     secTable.addMemberMapping(this.storeMgr.getMappingManager().getMapping(secTable, mmd, clr, FieldRole.ROLE_FIELD));
                  }
               } else if (mmd.getPersistenceModifier() != FieldPersistenceModifier.TRANSACTIONAL) {
                  throw (new NucleusException(Localiser.msg("057006", new Object[]{mmd.getName()}))).setFatal();
               }

               boolean needsFKToContainerOwner = false;
               RelationType relationType = mmd.getRelationType(clr);
               if (relationType == RelationType.ONE_TO_MANY_BI) {
                  AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
                  if (mmd.getJoinMetaData() == null && relatedMmds[0].getJoinMetaData() == null) {
                     needsFKToContainerOwner = true;
                  }
               } else if (relationType == RelationType.ONE_TO_MANY_UNI && mmd.getJoinMetaData() == null) {
                  needsFKToContainerOwner = true;
               }

               if (needsFKToContainerOwner) {
                  if (mmd.getCollection() != null && !SCOUtils.collectionHasSerialisedElements(mmd) || mmd.getArray() != null && !SCOUtils.arrayIsStoredInSingleColumn(mmd, this.storeMgr.getMetaDataManager())) {
                     AbstractClassMetaData elementCmd = null;
                     if (mmd.hasCollection()) {
                        elementCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(mmd.getCollection().getElementType(), clr);
                     } else {
                        elementCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(mmd.getType().getComponentType(), clr);
                     }

                     if (elementCmd == null) {
                        String[] implClassNames = this.storeMgr.getMetaDataManager().getClassesImplementingInterface(mmd.getCollection().getElementType(), clr);
                        if (implClassNames != null && implClassNames.length > 0) {
                           AbstractClassMetaData[] elementCmds = new AbstractClassMetaData[implClassNames.length];

                           for(int i = 0; i < implClassNames.length; ++i) {
                              elementCmds[i] = this.storeMgr.getMetaDataManager().getMetaDataForClass(implClassNames[i], clr);
                           }

                           for(int i = 0; i < elementCmds.length; ++i) {
                              this.storeMgr.addSchemaCallback(elementCmds[i].getFullClassName(), mmd);
                              DatastoreClass dc = this.storeMgr.getDatastoreClass(elementCmds[i].getFullClassName(), clr);
                              if (dc == null) {
                                 throw new NucleusException("Unable to add foreign-key to " + elementCmds[i].getFullClassName() + " to " + this + " since element has no table!");
                              }

                              if (dc instanceof ClassTable) {
                                 ClassTable ct = (ClassTable)dc;
                                 if (ct.isInitialized()) {
                                    ct.runCallBacks(clr);
                                 }
                              } else {
                                 NucleusLogger.DATASTORE_SCHEMA.info("Table " + this.toString() + " has to manage member " + mmd.getFullFieldName() + " yet the related element uses a VIEW so not remotely adding key FK owner column; assumed to be part of the VIEW definition");
                              }
                           }
                        } else if (mmd.hasCollection()) {
                           NucleusLogger.METADATA.warn(Localiser.msg("057016", new Object[]{theCmd.getFullClassName(), mmd.getCollection().getElementType()}));
                        } else {
                           NucleusLogger.METADATA.warn(Localiser.msg("057014", new Object[]{theCmd.getFullClassName(), mmd.getType().getComponentType().getName()}));
                        }
                     } else {
                        AbstractClassMetaData[] elementCmds = null;
                        Object var33;
                        if (elementCmd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
                           Collection<String> elementSubclassNames = this.storeMgr.getSubClassesForClass(elementCmd.getFullClassName(), true, clr);
                           var33 = new ClassMetaData[elementSubclassNames != null ? 1 + elementSubclassNames.size() : 1];
                           int elemNo = 0;
                           ((Object[])var33)[elemNo++] = elementCmd;
                           if (elementSubclassNames != null) {
                              for(String elementSubclassName : elementSubclassNames) {
                                 AbstractClassMetaData elemSubCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(elementSubclassName, clr);
                                 ((Object[])var33)[elemNo++] = elemSubCmd;
                              }
                           }
                        } else if (elementCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                           var33 = this.storeMgr.getClassesManagingTableForClass(elementCmd, clr);
                        } else {
                           var33 = new ClassMetaData[]{elementCmd};
                        }

                        ElementMetaData elemmd = mmd.getElementMetaData();
                        if (elemmd != null && !StringUtils.isWhitespace(elemmd.getTable())) {
                           DatastoreIdentifier requiredTableId = this.storeMgr.getIdentifierFactory().newTableIdentifier(elemmd.getTable());
                           DatastoreClass requiredTable = this.storeMgr.getDatastoreClass(requiredTableId);
                           if (requiredTable != null) {
                              NucleusLogger.GENERAL.warn("Member=" + mmd.getFullFieldName() + " has 1-N FK with required table=" + requiredTable + " : we don't currently support specification of the element table, and always take the default table for the element type");
                           } else {
                              NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " specified element FK in table=" + elemmd.getTable() + " but table not known. Ignoring.");
                           }
                        }

                        for(int i = 0; i < ((Object[])var33).length; ++i) {
                           this.storeMgr.addSchemaCallback(((AbstractClassMetaData)((Object[])var33)[i]).getFullClassName(), mmd);
                           DatastoreClass dc = this.storeMgr.getDatastoreClass(((AbstractClassMetaData)((Object[])var33)[i]).getFullClassName(), clr);
                           if (dc != null) {
                              if (dc instanceof ClassTable) {
                                 ClassTable ct = (ClassTable)dc;
                                 if (ct.isInitialized()) {
                                    ct.runCallBacks(clr);
                                 }
                              } else {
                                 NucleusLogger.DATASTORE_SCHEMA.info("Table " + this.toString() + " has to manage member " + mmd.getFullFieldName() + " yet the related element uses a VIEW so not remotely adding element FK owner column; assumed to be part of the VIEW definition");
                              }
                           }
                        }
                     }
                  } else if (mmd.getMap() != null && !SCOUtils.mapHasSerialisedKeysAndValues(mmd)) {
                     if (mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getMappedBy() != null) {
                        AbstractClassMetaData valueCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(mmd.getMap().getValueType(), clr);
                        if (valueCmd == null) {
                           NucleusLogger.METADATA.warn(Localiser.msg("057018", new Object[]{theCmd.getFullClassName(), mmd.getMap().getValueType()}));
                        } else {
                           AbstractClassMetaData[] valueCmds = null;
                           Object var31;
                           if (valueCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                              var31 = this.storeMgr.getClassesManagingTableForClass(valueCmd, clr);
                           } else {
                              var31 = new ClassMetaData[]{valueCmd};
                           }

                           for(int i = 0; i < ((Object[])var31).length; ++i) {
                              this.storeMgr.addSchemaCallback(((AbstractClassMetaData)((Object[])var31)[i]).getFullClassName(), mmd);
                              DatastoreClass dc = this.storeMgr.getDatastoreClass(((AbstractClassMetaData)((Object[])var31)[i]).getFullClassName(), clr);
                              if (dc instanceof ClassTable) {
                                 ClassTable ct = (ClassTable)dc;
                                 if (ct.isInitialized()) {
                                    ct.runCallBacks(clr);
                                 }
                              } else {
                                 NucleusLogger.DATASTORE_SCHEMA.info("Table " + this.toString() + " has to manage member " + mmd.getFullFieldName() + " yet the related value uses a VIEW so not remotely adding value FK owner column; assumed to be part of the VIEW definition");
                              }
                           }
                        }
                     } else if (mmd.getValueMetaData() != null && mmd.getValueMetaData().getMappedBy() != null) {
                        AbstractClassMetaData keyCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(mmd.getMap().getKeyType(), clr);
                        if (keyCmd == null) {
                           NucleusLogger.METADATA.warn(Localiser.msg("057019", new Object[]{theCmd.getFullClassName(), mmd.getMap().getKeyType()}));
                        } else {
                           AbstractClassMetaData[] keyCmds = null;
                           Object keyCmds;
                           if (keyCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
                              keyCmds = this.storeMgr.getClassesManagingTableForClass(keyCmd, clr);
                           } else {
                              keyCmds = new ClassMetaData[]{keyCmd};
                           }

                           for(int i = 0; i < ((Object[])keyCmds).length; ++i) {
                              this.storeMgr.addSchemaCallback(((AbstractClassMetaData)((Object[])keyCmds)[i]).getFullClassName(), mmd);
                              DatastoreClass dc = this.storeMgr.getDatastoreClass(((AbstractClassMetaData)((Object[])keyCmds)[i]).getFullClassName(), clr);
                              if (dc instanceof ClassTable) {
                                 ClassTable ct = (ClassTable)dc;
                                 if (ct.isInitialized()) {
                                    ct.runCallBacks(clr);
                                 }
                              } else {
                                 NucleusLogger.DATASTORE_SCHEMA.info("Table " + this.toString() + " has to manage member " + mmd.getFullFieldName() + " yet the related key uses a VIEW so not remotely adding key FK owner column; assumed to be part of the VIEW definition");
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

   private void manageUnmappedColumns(AbstractClassMetaData theCmd, ClassLoaderResolver clr) {
      List cols = theCmd.getUnmappedColumns();
      if (cols != null && cols.size() > 0) {
         for(ColumnMetaData colmd : cols) {
            if (colmd.getJdbcType() == JdbcType.VARCHAR && colmd.getLength() == null) {
               colmd.setLength(this.storeMgr.getIntProperty("datanucleus.rdbms.stringDefaultLength"));
            }

            IdentifierFactory idFactory = this.getStoreManager().getIdentifierFactory();
            DatastoreIdentifier colIdentifier = idFactory.newIdentifier(IdentifierType.COLUMN, colmd.getName());
            Column col = this.addColumn((String)null, colIdentifier, (JavaTypeMapping)null, colmd);
            SQLTypeInfo sqlTypeInfo = this.storeMgr.getSQLTypeInfoForJDBCType(this.dba.getJDBCTypeForName(colmd.getJdbcTypeName()));
            col.setTypeInfo(sqlTypeInfo);
            if (this.unmappedColumns == null) {
               this.unmappedColumns = new HashSet();
            }

            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057011", new Object[]{col.toString(), colmd.getJdbcType()}));
            }

            this.unmappedColumns.add(col);
         }
      }

   }

   public boolean managesClass(String className) {
      if (className == null) {
         return false;
      } else {
         for(AbstractClassMetaData managedCmd : this.managedClassMetaData) {
            if (managedCmd.getFullClassName().equals(className)) {
               return true;
            }
         }

         return false;
      }
   }

   protected void initializePK(ClassLoaderResolver clr) {
      this.assertIsPKUninitialized();
      AbstractMemberMetaData[] membersToAdd = new AbstractMemberMetaData[this.cmd.getNoOfPrimaryKeyMembers()];
      int pkFieldNum = 0;
      int fieldCount = this.cmd.getNoOfManagedMembers();
      boolean hasPrimaryKeyInThisClass = false;
      if (this.cmd.getNoOfPrimaryKeyMembers() > 0) {
         this.pkMappings = new JavaTypeMapping[this.cmd.getNoOfPrimaryKeyMembers()];
         if (this.cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
            AbstractClassMetaData baseCmd = this.cmd.getBaseAbstractClassMetaData();
            fieldCount = baseCmd.getNoOfManagedMembers();

            for(int relFieldNum = 0; relFieldNum < fieldCount; ++relFieldNum) {
               AbstractMemberMetaData mmd = baseCmd.getMetaDataForManagedMemberAtRelativePosition(relFieldNum);
               if (mmd.isPrimaryKey()) {
                  if (mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
                     membersToAdd[pkFieldNum++] = mmd;
                     hasPrimaryKeyInThisClass = true;
                  } else if (mmd.getPersistenceModifier() != FieldPersistenceModifier.TRANSACTIONAL) {
                     throw (new NucleusException(Localiser.msg("057006", new Object[]{mmd.getName()}))).setFatal();
                  }

                  if (mmd.getValueStrategy() == IdentityStrategy.IDENTITY && !this.dba.supportsOption("IdentityColumns")) {
                     throw (new NucleusException(Localiser.msg("057020", new Object[]{this.cmd.getFullClassName(), mmd.getName()}))).setFatal();
                  }
               }
            }
         } else {
            for(int relFieldNum = 0; relFieldNum < fieldCount; ++relFieldNum) {
               AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(relFieldNum);
               if (fmd.isPrimaryKey()) {
                  if (fmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
                     membersToAdd[pkFieldNum++] = fmd;
                     hasPrimaryKeyInThisClass = true;
                  } else if (fmd.getPersistenceModifier() != FieldPersistenceModifier.TRANSACTIONAL) {
                     throw (new NucleusException(Localiser.msg("057006", new Object[]{fmd.getName()}))).setFatal();
                  }

                  if (fmd.getValueStrategy() == IdentityStrategy.IDENTITY && !this.dba.supportsOption("IdentityColumns")) {
                     throw (new NucleusException(Localiser.msg("057020", new Object[]{this.cmd.getFullClassName(), fmd.getName()}))).setFatal();
                  }
               }
            }
         }
      }

      if (!hasPrimaryKeyInThisClass) {
         if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
            DatastoreClass superTable = this.storeMgr.getDatastoreClass(this.cmd.getPersistableSuperclass(), clr);
            if (this.isPKInitialized()) {
               return;
            }

            if (superTable == null && this.cmd.getPersistableSuperclass() != null) {
               AbstractClassMetaData supercmd = this.cmd.getSuperAbstractClassMetaData();

               while(supercmd.getPersistableSuperclass() != null) {
                  superTable = this.storeMgr.getDatastoreClass(supercmd.getPersistableSuperclass(), clr);
                  if (this.isPKInitialized()) {
                     return;
                  }

                  if (superTable != null) {
                     break;
                  }

                  supercmd = supercmd.getSuperAbstractClassMetaData();
                  if (supercmd == null) {
                     break;
                  }
               }
            }

            if (superTable != null) {
               ColumnMetaDataContainer colContainer = null;
               if (this.cmd.getInheritanceMetaData() != null) {
                  colContainer = this.cmd.getInheritanceMetaData().getJoinMetaData();
               }

               if (colContainer == null) {
                  colContainer = this.cmd.getPrimaryKeyMetaData();
               }

               this.addApplicationIdUsingClassTableId(colContainer, superTable, clr, this.cmd);
            } else {
               AbstractClassMetaData pkCmd = this.getClassWithPrimaryKeyForClass(this.cmd.getSuperAbstractClassMetaData(), clr);
               if (pkCmd != null) {
                  this.pkMappings = new JavaTypeMapping[pkCmd.getNoOfPrimaryKeyMembers()];
                  pkFieldNum = 0;
                  fieldCount = pkCmd.getNoOfInheritedManagedMembers() + pkCmd.getNoOfManagedMembers();

                  for(int absFieldNum = 0; absFieldNum < fieldCount; ++absFieldNum) {
                     AbstractMemberMetaData fmd = pkCmd.getMetaDataForManagedMemberAtAbsolutePosition(absFieldNum);
                     if (fmd.isPrimaryKey()) {
                        AbstractMemberMetaData overriddenFmd = this.cmd.getOverriddenMember(fmd.getName());
                        if (overriddenFmd != null) {
                           fmd = overriddenFmd;
                        } else {
                           AbstractClassMetaData thisCmd = this.cmd;

                           while(thisCmd.getSuperAbstractClassMetaData() != null && thisCmd.getSuperAbstractClassMetaData() != pkCmd) {
                              thisCmd = thisCmd.getSuperAbstractClassMetaData();
                              overriddenFmd = thisCmd.getOverriddenMember(fmd.getName());
                              if (overriddenFmd != null) {
                                 fmd = overriddenFmd;
                                 break;
                              }
                           }
                        }

                        if (fmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
                           membersToAdd[pkFieldNum++] = fmd;
                        } else if (fmd.getPersistenceModifier() != FieldPersistenceModifier.TRANSACTIONAL) {
                           throw (new NucleusException(Localiser.msg("057006", new Object[]{fmd.getName()}))).setFatal();
                        }
                     }
                  }
               }
            }
         } else if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
            ColumnMetaData colmd = null;
            if (this.cmd.getIdentityMetaData() != null && this.cmd.getIdentityMetaData().getColumnMetaData() != null) {
               colmd = this.cmd.getIdentityMetaData().getColumnMetaData();
            }

            if (colmd == null && this.cmd.getPrimaryKeyMetaData() != null && this.cmd.getPrimaryKeyMetaData().getColumnMetaData() != null && this.cmd.getPrimaryKeyMetaData().getColumnMetaData().length > 0) {
               colmd = this.cmd.getPrimaryKeyMetaData().getColumnMetaData()[0];
            }

            this.addDatastoreId(colmd, (DatastoreClass)null, this.cmd);
         } else if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
         }
      }

      for(int i = 0; i < membersToAdd.length; ++i) {
         if (membersToAdd[i] != null) {
            try {
               DatastoreClass datastoreClass = this.getStoreManager().getDatastoreClass(membersToAdd[i].getType().getName(), clr);
               if (datastoreClass.getIdMapping() == null) {
                  throw (new NucleusException("Unsupported relationship with field " + membersToAdd[i].getFullFieldName())).setFatal();
               }
            } catch (NoTableManagedException var12) {
            }

            JavaTypeMapping fieldMapping = this.storeMgr.getMappingManager().getMapping(this, membersToAdd[i], clr, FieldRole.ROLE_FIELD);
            this.addMemberMapping(fieldMapping);
            this.pkMappings[i] = fieldMapping;
         }
      }

      this.initializeIDMapping();
      this.state = 1;
   }

   private AbstractClassMetaData getClassWithPrimaryKeyForClass(AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      if (cmd == null) {
         return null;
      } else if (cmd.getSuperAbstractClassMetaData() == null) {
         return cmd;
      } else {
         return cmd.getNoOfPrimaryKeyMembers() > 0 && cmd.getSuperAbstractClassMetaData().getNoOfPrimaryKeyMembers() == 0 ? cmd : this.getClassWithPrimaryKeyForClass(cmd.getSuperAbstractClassMetaData(), clr);
      }
   }

   private void initializeForClass(AbstractClassMetaData theCmd, ClassLoaderResolver clr) {
      String columnOrdering = this.storeMgr.getStringProperty("datanucleus.rdbms.tableColumnOrder");
      if (columnOrdering.equalsIgnoreCase("superclass-first")) {
         AbstractClassMetaData parentCmd = theCmd.getSuperAbstractClassMetaData();
         if (parentCmd != null) {
            if (this.cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               this.initializeForClass(parentCmd, clr);
            } else if (parentCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
               this.initializeForClass(parentCmd, clr);
            }
         }

         this.manageClass(theCmd, clr);
      } else {
         this.manageClass(theCmd, clr);
         AbstractClassMetaData parentCmd = theCmd.getSuperAbstractClassMetaData();
         if (parentCmd != null) {
            if (this.cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               this.initializeForClass(parentCmd, clr);
            } else if (parentCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
               this.initializeForClass(parentCmd, clr);
            }
         }
      }

   }

   private void runCallBacks(ClassLoaderResolver clr) {
      for(AbstractClassMetaData managedCmd : this.managedClassMetaData) {
         if (this.managingClassCurrent != null && this.managingClassCurrent.equals(managedCmd.getFullClassName())) {
            this.runCallbacksAfterManageClass = true;
            break;
         }

         Collection processedCallbacks = (Collection)this.callbacksAppliedForManagedClass.get(managedCmd.getFullClassName());
         Collection c = (Collection)this.storeMgr.getSchemaCallbacks().get(managedCmd.getFullClassName());
         if (c != null) {
            if (processedCallbacks == null) {
               processedCallbacks = new HashSet();
               this.callbacksAppliedForManagedClass.put(managedCmd.getFullClassName(), processedCallbacks);
            }

            for(AbstractMemberMetaData callbackMmd : c) {
               if (!processedCallbacks.contains(callbackMmd)) {
                  processedCallbacks.add(callbackMmd);
                  if (callbackMmd.getJoinMetaData() == null) {
                     AbstractMemberMetaData ownerFmd = callbackMmd;
                     if (callbackMmd.getMappedBy() != null) {
                        AbstractMemberMetaData fmd = managedCmd.getMetaDataForMember(callbackMmd.getMappedBy());
                        if (fmd == null) {
                           throw new NucleusUserException(Localiser.msg("057036", new Object[]{callbackMmd.getMappedBy(), managedCmd.getFullClassName(), callbackMmd.getFullFieldName()}));
                        }

                        if (callbackMmd.getMap() != null && this.storeMgr.getBooleanProperty("datanucleus.rdbms.uniqueConstraints.mapInverse")) {
                           this.initializeFKMapUniqueConstraints(callbackMmd);
                        }

                        boolean duplicate = false;
                        JavaTypeMapping fkDiscrimMapping = null;
                        JavaTypeMapping orderMapping = null;
                        if (callbackMmd.hasExtension("relation-discriminator-column")) {
                           String colName = callbackMmd.getValueForExtension("relation-discriminator-column");
                           if (colName == null) {
                              colName = "RELATION_DISCRIM";
                           }

                           for(Map.Entry entry : this.getExternalFkDiscriminatorMappings().entrySet()) {
                              JavaTypeMapping discrimMapping = (JavaTypeMapping)entry.getValue();
                              String discrimColName = discrimMapping.getDatastoreMapping(0).getColumn().getColumnMetaData().getName();
                              if (discrimColName.equalsIgnoreCase(colName)) {
                                 duplicate = true;
                                 fkDiscrimMapping = discrimMapping;
                                 orderMapping = (JavaTypeMapping)this.getExternalOrderMappings().get(entry.getKey());
                                 break;
                              }
                           }

                           if (!duplicate) {
                              ColumnMetaData colmd = new ColumnMetaData();
                              colmd.setName(colName);
                              colmd.setAllowsNull(Boolean.TRUE);
                              fkDiscrimMapping = this.storeMgr.getMappingManager().getMapping(String.class);
                              fkDiscrimMapping.setTable(this);
                              ColumnCreator.createIndexColumn(fkDiscrimMapping, this.storeMgr, clr, this, colmd, false);
                           }

                           if (fkDiscrimMapping != null) {
                              this.getExternalFkDiscriminatorMappings().put(callbackMmd, fkDiscrimMapping);
                           }
                        }

                        this.addOrderMapping(callbackMmd, orderMapping, clr);
                     } else {
                        String ownerClassName = callbackMmd.getAbstractClassMetaData().getFullClassName();
                        JavaTypeMapping fkMapping = new PersistableMapping();
                        fkMapping.setTable(this);
                        fkMapping.initialize(this.storeMgr, ownerClassName);
                        JavaTypeMapping fkDiscrimMapping = null;
                        JavaTypeMapping orderMapping = null;
                        boolean duplicate = false;

                        try {
                           DatastoreClass ownerTbl = this.storeMgr.getDatastoreClass(ownerClassName, clr);
                           if (ownerTbl == null) {
                              AbstractClassMetaData[] ownerParentCmds = this.storeMgr.getClassesManagingTableForClass(ownerFmd.getAbstractClassMetaData(), clr);
                              if (ownerParentCmds.length > 1) {
                                 throw new NucleusUserException("Relation (" + ownerFmd.getFullFieldName() + ") with multiple related tables (using subclass-table). Not supported");
                              }

                              ownerClassName = ownerParentCmds[0].getFullClassName();
                              ownerTbl = this.storeMgr.getDatastoreClass(ownerClassName, clr);
                              if (ownerTbl == null) {
                                 throw new NucleusException("Failed to get owner table at other end of relation for field=" + ownerFmd.getFullFieldName());
                              }
                           }

                           JavaTypeMapping ownerIdMapping = ownerTbl.getIdMapping();
                           ColumnMetaDataContainer colmdContainer = null;
                           if (!ownerFmd.hasCollection() && !ownerFmd.hasArray()) {
                              if (ownerFmd.hasMap() && ownerFmd.getKeyMetaData() != null && ownerFmd.getKeyMetaData().getMappedBy() != null) {
                                 colmdContainer = ownerFmd.getValueMetaData();
                              } else if (ownerFmd.hasMap() && ownerFmd.getValueMetaData() != null && ownerFmd.getValueMetaData().getMappedBy() != null) {
                                 colmdContainer = ownerFmd.getKeyMetaData();
                              }
                           } else {
                              colmdContainer = ownerFmd.getElementMetaData();
                           }

                           CorrespondentColumnsMapper correspondentColumnsMapping = new CorrespondentColumnsMapper(colmdContainer, ownerIdMapping, true);
                           int countIdFields = ownerIdMapping.getNumberOfDatastoreMappings();

                           for(int i = 0; i < countIdFields; ++i) {
                              DatastoreMapping refDatastoreMapping = ownerIdMapping.getDatastoreMapping(i);
                              JavaTypeMapping mapping = this.storeMgr.getMappingManager().getMapping(refDatastoreMapping.getJavaTypeMapping().getJavaType());
                              ColumnMetaData colmd = correspondentColumnsMapping.getColumnMetaDataByIdentifier(refDatastoreMapping.getColumn().getIdentifier());
                              if (colmd == null) {
                                 throw (new NucleusUserException(Localiser.msg("057035", new Object[]{refDatastoreMapping.getColumn().getIdentifier(), this.toString()}))).setFatal();
                              }

                              DatastoreIdentifier identifier = null;
                              IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
                              if (colmd.getName() != null && colmd.getName().length() >= 1) {
                                 identifier = idFactory.newColumnIdentifier(colmd.getName());
                              } else {
                                 identifier = idFactory.newForeignKeyFieldIdentifier(ownerFmd, (AbstractMemberMetaData)null, refDatastoreMapping.getColumn().getIdentifier(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(mapping.getJavaType()), FieldRole.ROLE_OWNER);
                              }

                              Column refColumn = this.addColumn(mapping.getJavaType().getName(), identifier, mapping, colmd);
                              refDatastoreMapping.getColumn().copyConfigurationTo(refColumn);
                              if (colmd.getAllowsNull() == null || colmd.getAllowsNull() != null && colmd.isAllowsNull()) {
                                 refColumn.setNullable(true);
                              }

                              fkMapping.addDatastoreMapping(this.getStoreManager().getMappingManager().createDatastoreMapping(mapping, refColumn, refDatastoreMapping.getJavaTypeMapping().getJavaType().getName()));
                              ((PersistableMapping)fkMapping).addJavaTypeMapping(mapping);
                           }
                        } catch (DuplicateColumnException var26) {
                           DuplicateColumnException dce = var26;
                           if (!callbackMmd.hasExtension("relation-discriminator-column")) {
                              throw var26;
                           }

                           Iterator fkIter = this.getExternalFkMappings().entrySet().iterator();
                           fkMapping = null;

                           while(fkIter.hasNext()) {
                              Map.Entry entry = (Map.Entry)fkIter.next();
                              JavaTypeMapping existingFkMapping = (JavaTypeMapping)entry.getValue();

                              for(int j = 0; j < existingFkMapping.getNumberOfDatastoreMappings(); ++j) {
                                 if (existingFkMapping.getDatastoreMapping(j).getColumn().getIdentifier().toString().equals(dce.getConflictingColumn().getIdentifier().toString())) {
                                    fkMapping = existingFkMapping;
                                    fkDiscrimMapping = (JavaTypeMapping)this.externalFkDiscriminatorMappings.get(entry.getKey());
                                    orderMapping = (JavaTypeMapping)this.getExternalOrderMappings().get(entry.getKey());
                                    break;
                                 }
                              }
                           }

                           if (fkMapping == null) {
                              throw dce;
                           }

                           duplicate = true;
                        }

                        if (!duplicate && ownerFmd.hasExtension("relation-discriminator-column")) {
                           String colName = ownerFmd.getValueForExtension("relation-discriminator-column");
                           if (colName == null) {
                              colName = "RELATION_DISCRIM";
                           }

                           ColumnMetaData colmd = new ColumnMetaData();
                           colmd.setName(colName);
                           colmd.setAllowsNull(Boolean.TRUE);
                           fkDiscrimMapping = this.storeMgr.getMappingManager().getMapping(String.class);
                           fkDiscrimMapping.setTable(this);
                           ColumnCreator.createIndexColumn(fkDiscrimMapping, this.storeMgr, clr, this, colmd, false);
                        }

                        this.getExternalFkMappings().put(ownerFmd, fkMapping);
                        if (fkDiscrimMapping != null) {
                           this.getExternalFkDiscriminatorMappings().put(ownerFmd, fkDiscrimMapping);
                        }

                        this.addOrderMapping(ownerFmd, orderMapping, clr);
                     }
                  }
               }
            }
         }
      }

   }

   private JavaTypeMapping addOrderMapping(AbstractMemberMetaData fmd, JavaTypeMapping orderMapping, ClassLoaderResolver clr) {
      boolean needsOrderMapping = false;
      OrderMetaData omd = fmd.getOrderMetaData();
      if (fmd.hasArray()) {
         needsOrderMapping = true;
      } else if (List.class.isAssignableFrom(fmd.getType())) {
         needsOrderMapping = true;
         if (omd != null && !omd.isIndexedList()) {
            needsOrderMapping = false;
         }
      } else if (Collection.class.isAssignableFrom(fmd.getType()) && omd != null && omd.isIndexedList()) {
         needsOrderMapping = true;
         if (omd.getMappedBy() != null) {
            orderMapping = this.getMemberMapping(omd.getMappedBy());
         }
      }

      if (needsOrderMapping) {
         this.state = 0;
         if (orderMapping == null) {
            orderMapping = this.addOrderColumn(fmd, clr);
         }

         this.getExternalOrderMappings().put(fmd, orderMapping);
         this.state = 2;
      }

      return orderMapping;
   }

   public String getType() {
      return this.cmd.getFullClassName();
   }

   public IdentityType getIdentityType() {
      return this.cmd.getIdentityType();
   }

   public final VersionMetaData getVersionMetaData() {
      return this.versionMetaData;
   }

   public final DiscriminatorMetaData getDiscriminatorMetaData() {
      return this.discriminatorMetaData;
   }

   public final ClassTable getTableWithDiscriminator() {
      if (this.supertable != null) {
         ClassTable tbl = this.supertable.getTableWithDiscriminator();
         if (tbl != null) {
            return tbl;
         }
      }

      if (this.discriminatorMetaData != null) {
         return this;
      } else {
         return this.cmd.getInheritanceMetaData() != null && this.cmd.getInheritanceMetaData().getDiscriminatorMetaData() != null ? this : null;
      }
   }

   public boolean isObjectIdDatastoreAttributed() {
      boolean attributed = this.storeMgr.isStrategyDatastoreAttributed(this.cmd, -1);
      if (attributed) {
         return true;
      } else {
         for(int i = 0; i < this.columns.size(); ++i) {
            Column col = (Column)this.columns.get(i);
            if (col.isPrimaryKey() && col.isIdentity()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isBaseDatastoreClass() {
      return this.supertable == null;
   }

   public DatastoreClass getBaseDatastoreClass() {
      return (DatastoreClass)(this.supertable != null ? this.supertable.getBaseDatastoreClass() : this);
   }

   public DatastoreClass getSuperDatastoreClass() {
      this.assertIsInitialized();
      return this.supertable;
   }

   public boolean isSuperDatastoreClass(DatastoreClass table) {
      if (this == table) {
         return true;
      } else if (this.supertable != null) {
         return table == this.supertable ? true : this.supertable.isSuperDatastoreClass(table);
      } else {
         return false;
      }
   }

   public Collection getSecondaryDatastoreClasses() {
      return this.secondaryTables != null ? this.secondaryTables.values() : null;
   }

   public JavaTypeMapping getVersionMapping(boolean allowSuperclasses) {
      if (this.versionMapping != null) {
         return this.versionMapping;
      } else {
         return allowSuperclasses && this.supertable != null ? this.supertable.getVersionMapping(allowSuperclasses) : null;
      }
   }

   public JavaTypeMapping getDiscriminatorMapping(boolean allowSuperclasses) {
      if (this.discriminatorMapping != null) {
         return this.discriminatorMapping;
      } else {
         return allowSuperclasses && this.supertable != null ? this.supertable.getDiscriminatorMapping(allowSuperclasses) : null;
      }
   }

   public ClassTable getTableManagingMapping(JavaTypeMapping mapping) {
      if (this.managesMapping(mapping)) {
         return this;
      } else {
         return this.supertable != null ? this.supertable.getTableManagingMapping(mapping) : null;
      }
   }

   private ClassTable getSupertable(AbstractClassMetaData theCmd, ClassLoaderResolver clr) {
      if (this.cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
         return null;
      } else {
         AbstractClassMetaData superCmd = theCmd.getSuperAbstractClassMetaData();
         if (superCmd != null) {
            if (superCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.NEW_TABLE) {
               return (ClassTable)this.storeMgr.getDatastoreClass(superCmd.getFullClassName(), clr);
            } else {
               return superCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE ? this.getSupertable(superCmd, clr) : this.getSupertable(superCmd, clr);
            }
         } else {
            return null;
         }
      }
   }

   public DatastoreClass getBaseDatastoreClassWithMember(AbstractMemberMetaData mmd) {
      if (mmd == null) {
         return null;
      } else if (mmd.isPrimaryKey() && this.getSuperDatastoreClass() != null) {
         return this.getSuperDatastoreClass().getBaseDatastoreClassWithMember(mmd);
      } else if (this.memberMappingsMap.get(mmd) != null) {
         return this;
      } else if (this.externalFkMappings != null && this.externalFkMappings.get(mmd) != null) {
         return this;
      } else if (this.externalFkDiscriminatorMappings != null && this.externalFkDiscriminatorMappings.get(mmd) != null) {
         return this;
      } else if (this.externalOrderMappings != null && this.externalOrderMappings.get(mmd) != null) {
         return this;
      } else {
         return (DatastoreClass)(this.getSuperDatastoreClass() == null ? this : this.getSuperDatastoreClass().getBaseDatastoreClassWithMember(mmd));
      }
   }

   public ClassMetaData getClassMetaData() {
      return this.cmd;
   }

   protected Set getExpectedIndices(ClassLoaderResolver clr) {
      boolean autoMode = false;
      if (this.storeMgr.getStringProperty("datanucleus.rdbms.constraintCreateMode").equals("DataNucleus")) {
         autoMode = true;
      }

      Set<Index> indices = new HashSet();

      for(AbstractMemberMetaData fmd : this.memberMappingsMap.keySet()) {
         JavaTypeMapping fieldMapping = (JavaTypeMapping)this.memberMappingsMap.get(fmd);
         if (fieldMapping instanceof EmbeddedPCMapping) {
            EmbeddedPCMapping embMapping = (EmbeddedPCMapping)fieldMapping;

            for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
               IndexMetaData imd = embFieldMapping.getMemberMetaData().getIndexMetaData();
               if (imd != null) {
                  Index index = TableUtils.getIndexForField(this, imd, embFieldMapping);
                  if (index != null) {
                     indices.add(index);
                  }
               }
            }
         } else if (!(fieldMapping instanceof SerialisedMapping)) {
            IndexMetaData imd = fmd.getIndexMetaData();
            if (imd != null) {
               Index index = TableUtils.getIndexForField(this, imd, fieldMapping);
               if (index != null) {
                  indices.add(index);
               }
            } else if (autoMode && fmd.getIndexed() == null && !fmd.isPrimaryKey()) {
               RelationType relationType = fmd.getRelationType(clr);
               if (relationType == RelationType.ONE_TO_ONE_UNI) {
                  if (fieldMapping instanceof ReferenceMapping) {
                     ReferenceMapping refMapping = (ReferenceMapping)fieldMapping;
                     if (refMapping.getMappingStrategy() == 0 && refMapping.getJavaTypeMapping() != null) {
                        int colNum = 0;
                        JavaTypeMapping[] implMappings = refMapping.getJavaTypeMapping();

                        for(int i = 0; i < implMappings.length; ++i) {
                           int numColsInImpl = implMappings[i].getNumberOfDatastoreMappings();
                           Index index = new Index(this, false, (String)null);

                           for(int j = 0; j < numColsInImpl; ++j) {
                              index.setColumn(j, fieldMapping.getDatastoreMapping(colNum++).getColumn());
                           }

                           indices.add(index);
                        }
                     }
                  } else {
                     Index index = new Index(this, false, (String)null);

                     for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
                        index.setColumn(i, fieldMapping.getDatastoreMapping(i).getColumn());
                     }

                     indices.add(index);
                  }
               } else if (relationType == RelationType.ONE_TO_ONE_BI && fmd.getMappedBy() == null) {
                  Index index = new Index(this, false, (String)null);

                  for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
                     index.setColumn(i, fieldMapping.getDatastoreMapping(i).getColumn());
                  }

                  indices.add(index);
               } else if (relationType == RelationType.MANY_TO_ONE_BI) {
                  AbstractMemberMetaData relMmd = fmd.getRelatedMemberMetaData(clr)[0];
                  if (relMmd.getJoinMetaData() == null && fmd.getJoinMetaData() == null) {
                     Index index = new Index(this, false, (String)null);

                     for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
                        index.setColumn(i, fieldMapping.getDatastoreMapping(i).getColumn());
                     }

                     indices.add(index);
                  }
               }
            }
         }
      }

      if (this.versionMapping != null) {
         IndexMetaData idxmd = this.getVersionMetaData().getIndexMetaData();
         if (idxmd != null) {
            Index index = new Index(this, idxmd.isUnique(), idxmd.getValueForExtension("extended-setting"));
            if (idxmd.getName() != null) {
               index.setName(idxmd.getName());
            }

            int countVersionFields = this.versionMapping.getNumberOfDatastoreMappings();

            for(int i = 0; i < countVersionFields; ++i) {
               index.addColumn(this.versionMapping.getDatastoreMapping(i).getColumn());
            }

            indices.add(index);
         }
      }

      if (this.discriminatorMapping != null) {
         DiscriminatorMetaData dismd = this.getDiscriminatorMetaData();
         IndexMetaData idxmd = dismd.getIndexMetaData();
         if (idxmd != null) {
            Index index = new Index(this, idxmd.isUnique(), idxmd.getValueForExtension("extended-setting"));
            if (idxmd.getName() != null) {
               index.setName(idxmd.getName());
            }

            int countDiscrimFields = this.discriminatorMapping.getNumberOfDatastoreMappings();

            for(int i = 0; i < countDiscrimFields; ++i) {
               index.addColumn(this.discriminatorMapping.getDatastoreMapping(i).getColumn());
            }

            indices.add(index);
         }
      }

      for(Map.Entry entry : this.getExternalOrderMappings().entrySet()) {
         AbstractMemberMetaData fmd = (AbstractMemberMetaData)entry.getKey();
         JavaTypeMapping mapping = (JavaTypeMapping)entry.getValue();
         OrderMetaData omd = fmd.getOrderMetaData();
         if (omd != null && omd.getIndexMetaData() != null) {
            Index index = this.getIndexForIndexMetaDataAndMapping(omd.getIndexMetaData(), mapping);
            if (index != null) {
               indices.add(index);
            }
         }
      }

      for(AbstractClassMetaData thisCmd : this.managedClassMetaData) {
         IndexMetaData[] classIndices = thisCmd.getIndexMetaData();
         if (classIndices != null) {
            for(int i = 0; i < classIndices.length; ++i) {
               Index index = this.getIndexForIndexMetaData(classIndices[i]);
               if (index != null) {
                  indices.add(index);
               }
            }
         }
      }

      if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         PrimaryKey pk = this.getPrimaryKey();
         Iterator<Index> indicesIter = indices.iterator();

         while(indicesIter.hasNext()) {
            Index idx = (Index)indicesIter.next();
            if (idx.getColumnList().equals(pk.getColumnList())) {
               NucleusLogger.DATASTORE_SCHEMA.debug("Index " + idx + " is for the same columns as the PrimaryKey so being removed from expected set of indices. PK is always indexed");
               indicesIter.remove();
            }
         }
      }

      return indices;
   }

   private Index getIndexForIndexMetaDataAndMapping(IndexMetaData imd, JavaTypeMapping mapping) {
      boolean unique = imd.isUnique();
      Index index = new Index(this, unique, imd.getValueForExtension("extended-setting"));
      if (imd.getName() != null) {
         index.setName(imd.getName());
      }

      int numCols = mapping.getNumberOfDatastoreMappings();

      for(int i = 0; i < numCols; ++i) {
         index.addColumn(mapping.getDatastoreMapping(i).getColumn());
      }

      return index;
   }

   private Index getIndexForIndexMetaData(IndexMetaData imd) {
      boolean unique = imd.isUnique();
      Index index = new Index(this, unique, imd.getValueForExtension("extended-setting"));
      if (imd.getName() != null) {
         index.setName(imd.getName());
      }

      if (imd.getNumberOfColumns() > 0) {
         String[] columnNames = imd.getColumnNames();

         for(String columnName : columnNames) {
            DatastoreIdentifier colName = this.storeMgr.getIdentifierFactory().newColumnIdentifier(columnName);
            Column col = (Column)this.columnsByIdentifier.get(colName);
            if (col == null) {
               NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058001", new Object[]{this.toString(), index.getName(), columnName}));
               break;
            }

            index.addColumn(col);
         }
      } else {
         if (imd.getNumberOfMembers() <= 0) {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058002", new Object[]{this.toString(), index.getName()}));
            return null;
         }

         String[] memberNames = imd.getMemberNames();

         for(int i = 0; i < memberNames.length; ++i) {
            AbstractMemberMetaData realMmd = this.getMetaDataForMember(memberNames[i]);
            if (realMmd == null) {
               throw new NucleusUserException("Table " + this + " has index specified on member " + memberNames[i] + " but that member does not exist in the class that this table represents");
            }

            JavaTypeMapping fieldMapping = (JavaTypeMapping)this.memberMappingsMap.get(realMmd);
            int countFields = fieldMapping.getNumberOfDatastoreMappings();

            for(int j = 0; j < countFields; ++j) {
               index.addColumn(fieldMapping.getDatastoreMapping(j).getColumn());
            }
         }
      }

      return index;
   }

   public List getExpectedForeignKeys(ClassLoaderResolver clr) {
      this.assertIsInitialized();
      boolean autoMode = false;
      if (this.storeMgr.getStringProperty("datanucleus.rdbms.constraintCreateMode").equals("DataNucleus")) {
         autoMode = true;
      }

      ArrayList<ForeignKey> foreignKeys = new ArrayList();

      for(AbstractMemberMetaData mmd : this.memberMappingsMap.keySet()) {
         JavaTypeMapping memberMapping = (JavaTypeMapping)this.memberMappingsMap.get(mmd);
         if (mmd.getEmbeddedMetaData() != null && memberMapping instanceof EmbeddedPCMapping) {
            EmbeddedPCMapping embMapping = (EmbeddedPCMapping)memberMapping;
            this.addExpectedForeignKeysForEmbeddedPCField(foreignKeys, autoMode, clr, embMapping);
         } else if (ClassUtils.isReferenceType(mmd.getType()) && memberMapping instanceof ReferenceMapping) {
            Collection fks = TableUtils.getForeignKeysForReferenceField(memberMapping, mmd, autoMode, this.storeMgr, clr);
            foreignKeys.addAll(fks);
         } else if (this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(mmd.getType(), clr) != null && memberMapping.getNumberOfDatastoreMappings() > 0 && memberMapping instanceof PersistableMapping) {
            ForeignKey fk = TableUtils.getForeignKeyForPCField(memberMapping, mmd, autoMode, this.storeMgr, clr);
            if (fk != null) {
               boolean exists = false;

               for(ForeignKey theFK : foreignKeys) {
                  if (theFK.isEqual(fk)) {
                     exists = true;
                     break;
                  }
               }

               if (!exists) {
                  foreignKeys.add(fk);
               }
            }
         }
      }

      ForeignKeyMetaData idFkmd = this.cmd.getInheritanceMetaData().getJoinMetaData() != null ? this.cmd.getInheritanceMetaData().getJoinMetaData().getForeignKeyMetaData() : null;
      if (this.supertable != null && (autoMode || idFkmd != null && idFkmd.getDeleteAction() != ForeignKeyAction.NONE)) {
         ForeignKey fk = new ForeignKey(this.getIdMapping(), this.dba, this.supertable, false);
         if (idFkmd != null && idFkmd.getName() != null) {
            fk.setName(idFkmd.getName());
         }

         foreignKeys.add(0, fk);
      }

      for(AbstractClassMetaData thisCmd : this.managedClassMetaData) {
         ForeignKeyMetaData[] fkmds = thisCmd.getForeignKeyMetaData();
         if (fkmds != null) {
            for(int i = 0; i < fkmds.length; ++i) {
               ForeignKey fk = this.getForeignKeyForForeignKeyMetaData(fkmds[i]);
               if (fk != null) {
                  foreignKeys.add(fk);
               }
            }
         }
      }

      Map externalFks = this.getExternalFkMappings();
      if (!externalFks.isEmpty()) {
         for(Map.Entry entry : externalFks.entrySet()) {
            AbstractMemberMetaData fmd = (AbstractMemberMetaData)entry.getKey();
            DatastoreClass referencedTable = this.storeMgr.getDatastoreClass(fmd.getAbstractClassMetaData().getFullClassName(), clr);
            if (referencedTable != null) {
               ForeignKeyMetaData fkmd = fmd.getForeignKeyMetaData();
               if (fkmd == null && fmd.getElementMetaData() != null) {
                  fkmd = fmd.getElementMetaData().getForeignKeyMetaData();
               }

               if (fkmd != null && fkmd.getDeleteAction() != ForeignKeyAction.NONE || autoMode) {
                  JavaTypeMapping fkMapping = (JavaTypeMapping)entry.getValue();
                  ForeignKey fk = new ForeignKey(fkMapping, this.dba, referencedTable, true);
                  fk.setForMetaData(fkmd);
                  if (!foreignKeys.contains(fk)) {
                     foreignKeys.add(fk);
                  }
               }
            }
         }
      }

      return foreignKeys;
   }

   private void addExpectedForeignKeysForEmbeddedPCField(List foreignKeys, boolean autoMode, ClassLoaderResolver clr, EmbeddedPCMapping embeddedMapping) {
      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping embFieldMapping = embeddedMapping.getJavaTypeMapping(i);
         if (embFieldMapping instanceof EmbeddedPCMapping) {
            this.addExpectedForeignKeysForEmbeddedPCField(foreignKeys, autoMode, clr, (EmbeddedPCMapping)embFieldMapping);
         } else {
            AbstractMemberMetaData embFmd = embFieldMapping.getMemberMetaData();
            if (ClassUtils.isReferenceType(embFmd.getType()) && embFieldMapping instanceof ReferenceMapping) {
               Collection fks = TableUtils.getForeignKeysForReferenceField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
               foreignKeys.addAll(fks);
            } else if (this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(embFmd.getType(), clr) != null && embFieldMapping.getNumberOfDatastoreMappings() > 0 && embFieldMapping instanceof PersistableMapping) {
               ForeignKey fk = TableUtils.getForeignKeyForPCField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
               if (fk != null) {
                  foreignKeys.add(fk);
               }
            }
         }
      }

   }

   private ForeignKey getForeignKeyForForeignKeyMetaData(ForeignKeyMetaData fkmd) {
      if (fkmd == null) {
         return null;
      } else {
         ForeignKey fk = new ForeignKey(fkmd.isDeferred());
         fk.setForMetaData(fkmd);
         if (fkmd.getFkDefinitionApplies()) {
            return fk;
         } else {
            AbstractClassMetaData acmd = this.cmd;
            if (fkmd.getTable() == null) {
               NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058105", new Object[]{acmd.getFullClassName()}));
               return null;
            } else {
               DatastoreIdentifier tableId = this.storeMgr.getIdentifierFactory().newTableIdentifier(fkmd.getTable());
               ClassTable refTable = (ClassTable)this.storeMgr.getDatastoreClass(tableId);
               if (refTable == null) {
                  NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058106", new Object[]{acmd.getFullClassName(), fkmd.getTable()}));
                  return null;
               } else {
                  PrimaryKey pk = refTable.getPrimaryKey();
                  List targetCols = pk.getColumns();
                  List sourceCols = new ArrayList();
                  ColumnMetaData[] colmds = fkmd.getColumnMetaData();
                  String[] memberNames = fkmd.getMemberNames();
                  if (colmds != null && colmds.length > 0) {
                     for(int i = 0; i < colmds.length; ++i) {
                        DatastoreIdentifier colId = this.storeMgr.getIdentifierFactory().newColumnIdentifier(colmds[i].getName());
                        Column sourceCol = (Column)this.columnsByIdentifier.get(colId);
                        if (sourceCol == null) {
                           NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058107", new Object[]{acmd.getFullClassName(), fkmd.getTable(), colmds[i].getName(), this.toString()}));
                           return null;
                        }

                        sourceCols.add(sourceCol);
                     }
                  } else if (memberNames != null && memberNames.length > 0) {
                     for(int i = 0; i < memberNames.length; ++i) {
                        AbstractMemberMetaData realMmd = this.getMetaDataForMember(memberNames[i]);
                        if (realMmd == null) {
                           throw new NucleusUserException("Table " + this + " has foreign-key specified on member " + memberNames[i] + " but that member does not exist in the class that this table represents");
                        }

                        JavaTypeMapping fieldMapping = (JavaTypeMapping)this.memberMappingsMap.get(realMmd);
                        int countCols = fieldMapping.getNumberOfDatastoreMappings();

                        for(int j = 0; j < countCols; ++j) {
                           sourceCols.add(fieldMapping.getDatastoreMapping(j).getColumn());
                        }
                     }
                  }

                  if (sourceCols.size() != targetCols.size()) {
                     NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058108", new Object[]{acmd.getFullClassName(), fkmd.getTable(), "" + sourceCols.size(), "" + targetCols.size()}));
                  }

                  if (sourceCols.size() > 0) {
                     for(int i = 0; i < sourceCols.size(); ++i) {
                        Column source = (Column)sourceCols.get(i);
                        String targetColName = colmds != null && colmds[i] != null ? colmds[i].getTarget() : null;
                        Column target = (Column)targetCols.get(i);
                        if (targetColName != null) {
                           for(int j = 0; j < targetCols.size(); ++j) {
                              Column targetCol = (Column)targetCols.get(j);
                              if (targetCol.getIdentifier().getName().equalsIgnoreCase(targetColName)) {
                                 target = targetCol;
                                 break;
                              }
                           }
                        }

                        fk.addColumn(source, target);
                     }
                  }

                  return fk;
               }
            }
         }
      }
   }

   protected List getExpectedCandidateKeys() {
      this.assertIsInitialized();
      List<CandidateKey> candidateKeys = super.getExpectedCandidateKeys();

      for(CandidateKey ck : this.candidateKeysByMapField.values()) {
         candidateKeys.add(ck);
      }

      for(AbstractMemberMetaData fmd : this.memberMappingsMap.keySet()) {
         JavaTypeMapping fieldMapping = (JavaTypeMapping)this.memberMappingsMap.get(fmd);
         if (fieldMapping instanceof EmbeddedPCMapping) {
            EmbeddedPCMapping embMapping = (EmbeddedPCMapping)fieldMapping;

            for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
               JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
               UniqueMetaData umd = embFieldMapping.getMemberMetaData().getUniqueMetaData();
               if (umd != null) {
                  CandidateKey ck = TableUtils.getCandidateKeyForField(this, umd, embFieldMapping);
                  if (ck != null) {
                     candidateKeys.add(ck);
                  }
               }
            }
         } else {
            UniqueMetaData umd = fmd.getUniqueMetaData();
            if (umd != null) {
               CandidateKey ck = TableUtils.getCandidateKeyForField(this, umd, fieldMapping);
               if (ck != null) {
                  candidateKeys.add(ck);
               }
            }
         }
      }

      for(AbstractClassMetaData thisCmd : this.managedClassMetaData) {
         UniqueMetaData[] classCKs = thisCmd.getUniqueMetaData();
         if (classCKs != null) {
            for(int i = 0; i < classCKs.length; ++i) {
               CandidateKey ck = this.getCandidateKeyForUniqueMetaData(classCKs[i]);
               if (ck != null) {
                  candidateKeys.add(ck);
               }
            }
         }
      }

      if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         PrimaryKey pk = this.getPrimaryKey();
         Iterator<CandidateKey> candidatesIter = candidateKeys.iterator();

         while(candidatesIter.hasNext()) {
            CandidateKey key = (CandidateKey)candidatesIter.next();
            if (key.getColumnList().equals(pk.getColumnList())) {
               NucleusLogger.DATASTORE_SCHEMA.debug("Candidate key " + key + " is for the same columns as the PrimaryKey so being removed from expected set of candidates. PK is always unique");
               candidatesIter.remove();
            }
         }
      }

      return candidateKeys;
   }

   private CandidateKey getCandidateKeyForUniqueMetaData(UniqueMetaData umd) {
      CandidateKey ck = new CandidateKey(this);
      if (umd.getName() != null) {
         ck.setName(umd.getName());
      }

      if (umd.getNumberOfColumns() > 0) {
         String[] columnNames = umd.getColumnNames();

         for(String columnName : columnNames) {
            DatastoreIdentifier colName = this.storeMgr.getIdentifierFactory().newColumnIdentifier(columnName);
            Column col = (Column)this.columnsByIdentifier.get(colName);
            if (col == null) {
               NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058202", new Object[]{this.toString(), ck.getName(), columnName}));
               break;
            }

            ck.addColumn(col);
         }
      } else {
         if (umd.getNumberOfMembers() <= 0) {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058203", new Object[]{this.toString(), ck.getName()}));
            return null;
         }

         String[] memberNames = umd.getMemberNames();

         for(String memberName : memberNames) {
            AbstractMemberMetaData realMmd = this.getMetaDataForMember(memberName);
            if (realMmd == null) {
               throw new NucleusUserException("Table " + this + " has unique key specified on member " + memberName + " but that member does not exist in the class that this table represents");
            }

            JavaTypeMapping memberMapping = (JavaTypeMapping)this.memberMappingsMap.get(realMmd);
            int countFields = memberMapping.getNumberOfDatastoreMappings();

            for(int j = 0; j < countFields; ++j) {
               ck.addColumn(memberMapping.getDatastoreMapping(j).getColumn());
            }
         }
      }

      return ck;
   }

   public PrimaryKey getPrimaryKey() {
      PrimaryKey pk = super.getPrimaryKey();
      PrimaryKeyMetaData pkmd = this.cmd.getPrimaryKeyMetaData();
      if (pkmd != null && pkmd.getName() != null) {
         pk.setName(pkmd.getName());
      }

      return pk;
   }

   protected List getSQLCreateStatements(Properties props) {
      Properties tableProps = null;
      List stmts;
      if (this.createStatementDDL != null) {
         stmts = new ArrayList();
         StringTokenizer tokens = new StringTokenizer(this.createStatementDDL, ";");

         while(tokens.hasMoreTokens()) {
            stmts.add(tokens.nextToken());
         }
      } else {
         if (this.cmd.getExtensions() != null) {
            tableProps = new Properties();
            ExtensionMetaData[] emds = this.cmd.getExtensions();

            for(int i = 0; i < emds.length; ++i) {
               if (emds[i].getVendorName().equalsIgnoreCase("datanucleus")) {
                  tableProps.put(emds[i].getKey(), emds[i].getValue());
               }
            }
         }

         stmts = super.getSQLCreateStatements(tableProps);
      }

      if (this.secondaryTables != null) {
         Set secondaryTableNames = this.secondaryTables.keySet();
         Iterator iter = secondaryTableNames.iterator();

         while(iter.hasNext()) {
            SecondaryTable secTable = (SecondaryTable)this.secondaryTables.get(iter.next());
            stmts.addAll(secTable.getSQLCreateStatements(tableProps));
         }
      }

      return stmts;
   }

   protected List getSQLDropStatements() {
      this.assertIsInitialized();
      ArrayList stmts = new ArrayList();
      if (this.secondaryTables != null) {
         Set secondaryTableNames = this.secondaryTables.keySet();
         Iterator iter = secondaryTableNames.iterator();

         while(iter.hasNext()) {
            SecondaryTable secTable = (SecondaryTable)this.secondaryTables.get(iter.next());
            stmts.addAll(secTable.getSQLDropStatements());
         }
      }

      stmts.add(this.dba.getDropTableStatement(this));
      return stmts;
   }

   private void initializeFKMapUniqueConstraints(AbstractMemberMetaData ownerMmd) {
      AbstractMemberMetaData mfmd = null;
      String map_field_name = ownerMmd.getMappedBy();
      if (map_field_name != null) {
         mfmd = this.cmd.getMetaDataForMember(map_field_name);
         if (mfmd == null) {
            for(AbstractClassMetaData managedCmd : this.managedClassMetaData) {
               mfmd = managedCmd.getMetaDataForMember(map_field_name);
               if (mfmd != null) {
                  break;
               }
            }
         }

         if (mfmd == null) {
            throw new NucleusUserException(Localiser.msg("057036", new Object[]{map_field_name, this.cmd.getFullClassName(), ownerMmd.getFullFieldName()}));
         }

         if (ownerMmd.getJoinMetaData() == null) {
            if (ownerMmd.getKeyMetaData() != null && ownerMmd.getKeyMetaData().getMappedBy() != null) {
               AbstractMemberMetaData kmd = null;
               String key_field_name = ownerMmd.getKeyMetaData().getMappedBy();
               if (key_field_name != null) {
                  kmd = this.cmd.getMetaDataForMember(key_field_name);
               }

               if (kmd == null) {
                  for(AbstractClassMetaData managedCmd : this.managedClassMetaData) {
                     kmd = managedCmd.getMetaDataForMember(key_field_name);
                     if (kmd != null) {
                        break;
                     }
                  }
               }

               if (kmd == null) {
                  throw new ClassDefinitionException(Localiser.msg("057007", new Object[]{mfmd.getFullFieldName(), key_field_name}));
               }

               JavaTypeMapping ownerMapping = this.getMemberMapping(map_field_name);
               JavaTypeMapping keyMapping = this.getMemberMapping(kmd.getName());
               if ((this.dba.supportsOption("NullsInCandidateKeys") || !ownerMapping.isNullable() && !keyMapping.isNullable()) && keyMapping.getTable() == this && ownerMapping.getTable() == this) {
                  CandidateKey ck = new CandidateKey(this);
                  HashSet addedColumns = new HashSet();
                  int countOwnerFields = ownerMapping.getNumberOfDatastoreMappings();

                  for(int i = 0; i < countOwnerFields; ++i) {
                     Column col = ownerMapping.getDatastoreMapping(i).getColumn();
                     addedColumns.add(col);
                     ck.addColumn(col);
                  }

                  int countKeyFields = keyMapping.getNumberOfDatastoreMappings();

                  for(int i = 0; i < countKeyFields; ++i) {
                     Column col = keyMapping.getDatastoreMapping(i).getColumn();
                     if (!addedColumns.contains(col)) {
                        addedColumns.add(col);
                        ck.addColumn(col);
                     } else {
                        NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("057041", new Object[]{ownerMmd.getName()}));
                     }
                  }

                  if (this.candidateKeysByMapField.put(mfmd, ck) != null) {
                     NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("057012", new Object[]{mfmd.getFullFieldName(), ownerMmd.getFullFieldName()}));
                  }
               }
            } else {
               if (ownerMmd.getValueMetaData() == null || ownerMmd.getValueMetaData().getMappedBy() == null) {
                  throw new ClassDefinitionException(Localiser.msg("057009", new Object[]{ownerMmd.getFullFieldName()}));
               }

               AbstractMemberMetaData vmd = null;
               String value_field_name = ownerMmd.getValueMetaData().getMappedBy();
               if (value_field_name != null) {
                  vmd = this.cmd.getMetaDataForMember(value_field_name);
               }

               if (vmd == null) {
                  throw new ClassDefinitionException(Localiser.msg("057008", new Object[]{mfmd}));
               }

               JavaTypeMapping ownerMapping = this.getMemberMapping(map_field_name);
               JavaTypeMapping valueMapping = this.getMemberMapping(vmd.getName());
               if ((this.dba.supportsOption("NullsInCandidateKeys") || !ownerMapping.isNullable() && !valueMapping.isNullable()) && valueMapping.getTable() == this && ownerMapping.getTable() == this) {
                  CandidateKey ck = new CandidateKey(this);
                  HashSet addedColumns = new HashSet();
                  int countOwnerFields = ownerMapping.getNumberOfDatastoreMappings();

                  for(int i = 0; i < countOwnerFields; ++i) {
                     Column col = ownerMapping.getDatastoreMapping(i).getColumn();
                     addedColumns.add(col);
                     ck.addColumn(col);
                  }

                  int countValueFields = valueMapping.getNumberOfDatastoreMappings();

                  for(int i = 0; i < countValueFields; ++i) {
                     Column col = valueMapping.getDatastoreMapping(i).getColumn();
                     if (!addedColumns.contains(col)) {
                        addedColumns.add(col);
                        ck.addColumn(col);
                     } else {
                        NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("057042", new Object[]{ownerMmd.getName()}));
                     }
                  }

                  if (this.candidateKeysByMapField.put(mfmd, ck) != null) {
                     NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("057012", new Object[]{mfmd.getFullFieldName(), ownerMmd.getFullFieldName()}));
                  }
               }
            }
         }
      }

   }

   private void initializeIDMapping() {
      if (this.idMapping == null) {
         PersistableMapping mapping = new PersistableMapping();
         mapping.setTable(this);
         mapping.initialize(this.getStoreManager(), this.cmd.getFullClassName());
         if (this.getIdentityType() == IdentityType.DATASTORE) {
            mapping.addJavaTypeMapping(this.datastoreIDMapping);
         } else if (this.getIdentityType() == IdentityType.APPLICATION) {
            for(int i = 0; i < this.pkMappings.length; ++i) {
               mapping.addJavaTypeMapping(this.pkMappings[i]);
            }
         }

         this.idMapping = mapping;
      }
   }

   public JavaTypeMapping getIdMapping() {
      return this.idMapping;
   }

   private Map getExternalOrderMappings() {
      if (this.externalOrderMappings == null) {
         this.externalOrderMappings = new HashMap();
      }

      return this.externalOrderMappings;
   }

   public boolean hasExternalFkMappings() {
      return this.externalFkMappings != null && this.externalFkMappings.size() > 0;
   }

   private Map getExternalFkMappings() {
      if (this.externalFkMappings == null) {
         this.externalFkMappings = new HashMap();
      }

      return this.externalFkMappings;
   }

   public JavaTypeMapping getExternalMapping(AbstractMemberMetaData mmd, int mappingType) {
      if (mappingType == 5) {
         return (JavaTypeMapping)this.getExternalFkMappings().get(mmd);
      } else if (mappingType == 6) {
         return (JavaTypeMapping)this.getExternalFkDiscriminatorMappings().get(mmd);
      } else {
         return mappingType == 4 ? (JavaTypeMapping)this.getExternalOrderMappings().get(mmd) : null;
      }
   }

   public AbstractMemberMetaData getMetaDataForExternalMapping(JavaTypeMapping mapping, int mappingType) {
      if (mappingType == 5) {
         for(Map.Entry entry : this.getExternalFkMappings().entrySet()) {
            if (entry.getValue() == mapping) {
               return (AbstractMemberMetaData)entry.getKey();
            }
         }
      } else if (mappingType == 6) {
         for(Map.Entry entry : this.getExternalFkDiscriminatorMappings().entrySet()) {
            if (entry.getValue() == mapping) {
               return (AbstractMemberMetaData)entry.getKey();
            }
         }
      } else if (mappingType == 4) {
         for(Map.Entry entry : this.getExternalOrderMappings().entrySet()) {
            if (entry.getValue() == mapping) {
               return (AbstractMemberMetaData)entry.getKey();
            }
         }
      }

      return null;
   }

   private Map getExternalFkDiscriminatorMappings() {
      if (this.externalFkDiscriminatorMappings == null) {
         this.externalFkDiscriminatorMappings = new HashMap();
      }

      return this.externalFkDiscriminatorMappings;
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      if (mmd == null) {
         return null;
      } else if (mmd instanceof PropertyMetaData && mmd.getAbstractClassMetaData() instanceof InterfaceMetaData) {
         return this.getMemberMapping(mmd.getName());
      } else {
         if (mmd.isPrimaryKey()) {
            this.assertIsPKInitialized();
         } else {
            this.assertIsInitialized();
         }

         JavaTypeMapping m = (JavaTypeMapping)this.memberMappingsMap.get(mmd);
         if (m != null) {
            return m;
         } else {
            if (mmd.isPrimaryKey() && this.pkMappings != null) {
               for(int i = 0; i < this.pkMappings.length; ++i) {
                  if (this.pkMappings[i].getMemberMetaData().equals(mmd)) {
                     return this.pkMappings[i];
                  }
               }
            }

            for(Map.Entry entry : this.memberMappingsMap.entrySet()) {
               if (((AbstractMemberMetaData)entry.getKey()).getFullFieldName().equals(mmd.getFullFieldName())) {
                  return (JavaTypeMapping)entry.getValue();
               }
            }

            int ifc = this.cmd.getNoOfInheritedManagedMembers();
            if (mmd.getAbsoluteFieldNumber() < ifc && this.supertable != null) {
               m = this.supertable.getMemberMapping(mmd);
               if (m != null) {
                  return m;
               }
            }

            if (this.secondaryTables != null) {
               for(SecondaryTable secTable : this.secondaryTables.values()) {
                  m = secTable.getMemberMapping(mmd);
                  if (m != null) {
                     return m;
                  }
               }
            }

            return null;
         }
      }
   }

   public JavaTypeMapping getMemberMappingInDatastoreClass(AbstractMemberMetaData mmd) {
      if (mmd == null) {
         return null;
      } else if (mmd instanceof PropertyMetaData && mmd.getAbstractClassMetaData() instanceof InterfaceMetaData) {
         return this.getMemberMapping(mmd.getName());
      } else {
         if (mmd.isPrimaryKey()) {
            this.assertIsPKInitialized();
         } else {
            this.assertIsInitialized();
         }

         JavaTypeMapping m = (JavaTypeMapping)this.memberMappingsMap.get(mmd);
         if (m != null) {
            return m;
         } else {
            if (this.pkMappings != null) {
               for(int i = 0; i < this.pkMappings.length; ++i) {
                  JavaTypeMapping pkMapping = this.pkMappings[i];
                  if (pkMapping.getMemberMetaData() == mmd) {
                     return pkMapping;
                  }
               }
            }

            return null;
         }
      }
   }

   public JavaTypeMapping getMemberMapping(String memberName) {
      this.assertIsInitialized();
      AbstractMemberMetaData mmd = this.getMetaDataForMember(memberName);
      JavaTypeMapping m = this.getMemberMapping(mmd);
      if (m == null) {
         throw new NoSuchPersistentFieldException(this.cmd.getFullClassName(), memberName);
      } else {
         return m;
      }
   }

   AbstractMemberMetaData getMetaDataForMember(String memberName) {
      AbstractMemberMetaData mmd = this.cmd.getMetaDataForMember(memberName);
      if (mmd == null) {
         for(AbstractClassMetaData theCmd : this.managedClassMetaData) {
            AbstractMemberMetaData foundMmd = theCmd.getMetaDataForMember(memberName);
            if (foundMmd != null) {
               if (mmd != null && (!mmd.toString().equalsIgnoreCase(foundMmd.toString()) || mmd.getType() != foundMmd.getType())) {
                  String errMsg = "Table " + this.getIdentifier() + " manages at least 2 subclasses that both define a field \"" + memberName + "\", and the fields' metadata is different or they have different type! That means you can get e.g. wrong fetch results.";
                  NucleusLogger.DATASTORE_SCHEMA.error(errMsg);
                  throw (new NucleusException(errMsg)).setFatal();
               }

               mmd = foundMmd;
            }
         }
      }

      return mmd;
   }

   void assertPCClass(ObjectProvider op) {
      Class c = op.getObject().getClass();
      if (!op.getExecutionContext().getClassLoaderResolver().isAssignableFrom(this.cmd.getFullClassName(), c)) {
         throw (new NucleusException(Localiser.msg("057013", new Object[]{this.cmd.getFullClassName(), c}))).setFatal();
      }
   }

   private JavaTypeMapping addOrderColumn(AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      Class indexType = Integer.class;
      JavaTypeMapping indexMapping = new IndexMapping();
      indexMapping.initialize(this.storeMgr, indexType.getName());
      indexMapping.setMemberMetaData(mmd);
      indexMapping.setTable(this);
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      DatastoreIdentifier indexColumnName = null;
      ColumnMetaData colmd = null;
      OrderMetaData omd = mmd.getOrderMetaData();
      if (omd != null) {
         colmd = omd.getColumnMetaData() != null && omd.getColumnMetaData().length > 0 ? omd.getColumnMetaData()[0] : null;
         if (omd.getMappedBy() != null) {
            this.state = 2;
            JavaTypeMapping orderMapping = this.getMemberMapping(omd.getMappedBy());
            if (!(orderMapping instanceof IntegerMapping) && !(orderMapping instanceof LongMapping)) {
               throw new NucleusUserException(Localiser.msg("057022", new Object[]{mmd.getFullFieldName(), omd.getMappedBy()}));
            }

            return orderMapping;
         }

         String colName = null;
         if (omd.getColumnMetaData() != null && omd.getColumnMetaData().length > 0 && omd.getColumnMetaData()[0].getName() != null) {
            colName = omd.getColumnMetaData()[0].getName();
            indexColumnName = idFactory.newColumnIdentifier(colName);
         }
      }

      if (indexColumnName == null) {
         indexColumnName = idFactory.newForeignKeyFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(indexType), FieldRole.ROLE_INDEX);
      }

      Column column = this.addColumn(indexType.getName(), indexColumnName, indexMapping, colmd);
      if (colmd == null || colmd.getAllowsNull() == null || colmd.getAllowsNull() != null && colmd.isAllowsNull()) {
         column.setNullable(true);
      }

      this.storeMgr.getMappingManager().createDatastoreMapping(indexMapping, column, indexType.getName());
      return indexMapping;
   }

   public void providePrimaryKeyMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      if (this.pkMappings != null) {
         int[] primaryKeyFieldNumbers = this.cmd.getPKMemberPositions();

         for(int i = 0; i < this.pkMappings.length; ++i) {
            AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(primaryKeyFieldNumbers[i]);
            consumer.consumeMapping(this.pkMappings[i], fmd);
         }
      } else {
         int[] primaryKeyFieldNumbers = this.cmd.getPKMemberPositions();
         int countPkFields = this.cmd.getNoOfPrimaryKeyMembers();

         for(int i = 0; i < countPkFields; ++i) {
            AbstractMemberMetaData pkfmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(primaryKeyFieldNumbers[i]);
            consumer.consumeMapping(this.getMemberMapping(pkfmd), pkfmd);
         }
      }

   }

   public final void provideExternalMappings(MappingConsumer consumer, int mappingType) {
      if (mappingType == 5 && this.externalFkMappings != null) {
         consumer.preConsumeMapping(this.highestMemberNumber + 1);

         for(AbstractMemberMetaData fmd : this.externalFkMappings.keySet()) {
            JavaTypeMapping fieldMapping = (JavaTypeMapping)this.externalFkMappings.get(fmd);
            if (fieldMapping != null) {
               consumer.consumeMapping(fieldMapping, 5);
            }
         }
      } else if (mappingType == 6 && this.externalFkDiscriminatorMappings != null) {
         consumer.preConsumeMapping(this.highestMemberNumber + 1);

         for(AbstractMemberMetaData fmd : this.externalFkDiscriminatorMappings.keySet()) {
            JavaTypeMapping fieldMapping = (JavaTypeMapping)this.externalFkDiscriminatorMappings.get(fmd);
            if (fieldMapping != null) {
               consumer.consumeMapping(fieldMapping, 6);
            }
         }
      } else if (mappingType == 4 && this.externalOrderMappings != null) {
         consumer.preConsumeMapping(this.highestMemberNumber + 1);

         for(AbstractMemberMetaData fmd : this.externalOrderMappings.keySet()) {
            JavaTypeMapping fieldMapping = (JavaTypeMapping)this.externalOrderMappings.get(fmd);
            if (fieldMapping != null) {
               consumer.consumeMapping(fieldMapping, 4);
            }
         }
      }

   }

   public void provideMappingsForMembers(MappingConsumer consumer, AbstractMemberMetaData[] fieldMetaData, boolean includeSecondaryTables) {
      super.provideMappingsForMembers(consumer, fieldMetaData, true);
      if (includeSecondaryTables && this.secondaryTables != null) {
         for(SecondaryTable secTable : this.secondaryTables.values()) {
            secTable.provideMappingsForMembers(consumer, fieldMetaData, false);
         }
      }

   }

   public void provideUnmappedColumns(MappingConsumer consumer) {
      if (this.unmappedColumns != null) {
         Iterator<Column> iter = this.unmappedColumns.iterator();

         while(iter.hasNext()) {
            consumer.consumeUnmappedColumn((Column)iter.next());
         }
      }

   }

   public boolean validateConstraints(Connection conn, boolean autoCreate, Collection autoCreateErrors, ClassLoaderResolver clr) throws SQLException {
      boolean modified = false;
      if (super.validateConstraints(conn, autoCreate, autoCreateErrors, clr)) {
         modified = true;
      }

      if (this.secondaryTables != null) {
         for(SecondaryTable secTable : this.secondaryTables.values()) {
            if (secTable.validateConstraints(conn, autoCreate, autoCreateErrors, clr)) {
               modified = true;
            }
         }
      }

      return modified;
   }
}
