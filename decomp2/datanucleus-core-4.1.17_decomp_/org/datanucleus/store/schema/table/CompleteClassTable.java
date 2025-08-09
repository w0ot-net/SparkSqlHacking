package org.datanucleus.store.schema.table;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.schema.naming.ColumnType;
import org.datanucleus.store.schema.naming.NamingFactory;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.store.types.converters.MultiColumnConverter;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class CompleteClassTable implements Table {
   StoreManager storeMgr;
   AbstractClassMetaData cmd;
   String catalogName;
   String schemaName;
   String identifier;
   List columns = null;
   Column versionColumn;
   Column discriminatorColumn;
   Column datastoreIdColumn;
   Column multitenancyColumn;
   Map mappingByMember = new HashMap();
   Map mappingByEmbeddedMember = new HashMap();
   Map columnByName = new HashMap();
   SchemaVerifier schemaVerifier;

   public CompleteClassTable(StoreManager storeMgr, AbstractClassMetaData cmd, SchemaVerifier verifier) {
      this.storeMgr = storeMgr;
      this.cmd = cmd;
      this.schemaVerifier = verifier;
      if (cmd.getSchema() != null) {
         this.schemaName = cmd.getSchema();
      } else {
         this.schemaName = storeMgr.getStringProperty("datanucleus.mapping.Schema");
      }

      if (cmd.getCatalog() != null) {
         this.catalogName = cmd.getCatalog();
      } else {
         this.catalogName = storeMgr.getStringProperty("datanucleus.mapping.Catalog");
      }

      this.identifier = storeMgr.getNamingFactory().getTableName(cmd);
      this.columns = new ArrayList();
      TypeManager typeMgr = storeMgr.getNucleusContext().getTypeManager();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      int numMembers = cmd.getAllMemberPositions().length;

      for(int i = 0; i < numMembers; ++i) {
         AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(i);
         if (mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
            RelationType relationType = mmd.getRelationType(clr);
            if (relationType != RelationType.NONE && MetaDataUtils.getInstance().isMemberEmbedded(storeMgr.getMetaDataManager(), clr, mmd, relationType, (AbstractMemberMetaData)null)) {
               List<AbstractMemberMetaData> embMmds = new ArrayList();
               embMmds.add(mmd);
               if (RelationType.isRelationSingleValued(relationType)) {
                  boolean nested = false;
                  if (storeMgr.getSupportedOptions().contains("ORM.EmbeddedPC.Nested")) {
                     nested = !storeMgr.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.metadata.embedded.flat");
                     String nestedStr = mmd.getValueForExtension("nested");
                     if (nestedStr != null && nestedStr.equalsIgnoreCase("" + !nested)) {
                        nested = !nested;
                     }
                  }

                  if (nested) {
                     ColumnMetaData[] colmds = mmd.getColumnMetaData();
                     String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                     ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                     if (colmds != null && colmds.length == 1) {
                        col.setColumnMetaData(colmds[0]);
                        if (colmds[0].getPosition() != null) {
                           col.setPosition(colmds[0].getPosition());
                        }

                        if (colmds[0].getJdbcType() != null) {
                           col.setJdbcType(colmds[0].getJdbcType());
                        }
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                     col.setMemberColumnMapping(mapping);
                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeMember(mapping, mmd);
                     }

                     this.mappingByMember.put(mmd, mapping);
                     this.processEmbeddedMember(embMmds, clr, mmd.getEmbeddedMetaData(), true);
                  } else {
                     this.processEmbeddedMember(embMmds, clr, mmd.getEmbeddedMetaData(), false);
                  }
               } else if (RelationType.isRelationMultiValued(relationType)) {
                  if (mmd.hasCollection()) {
                     if (storeMgr.getSupportedOptions().contains("ORM.EmbeddedCollection.Nested")) {
                        ColumnMetaData[] colmds = mmd.getColumnMetaData();
                        String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                        ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                        if (colmds != null && colmds.length == 1) {
                           col.setColumnMetaData(colmds[0]);
                           if (colmds[0].getPosition() != null) {
                              col.setPosition(colmds[0].getPosition());
                           }

                           if (colmds[0].getJdbcType() != null) {
                              col.setJdbcType(colmds[0].getJdbcType());
                           }
                        }

                        MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                        col.setMemberColumnMapping(mapping);
                        if (this.schemaVerifier != null) {
                           this.schemaVerifier.attributeMember(mapping, mmd);
                        }

                        this.mappingByMember.put(mmd, mapping);
                        EmbeddedMetaData embmd = mmd.getElementMetaData() != null ? mmd.getElementMetaData().getEmbeddedMetaData() : null;
                        this.processEmbeddedMember(embMmds, clr, embmd, true);
                     } else {
                        NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded collection. Not yet supported. Ignoring");
                     }
                  } else if (mmd.hasMap()) {
                     if (storeMgr.getSupportedOptions().contains("ORM.EmbeddedMap.Nested")) {
                        ColumnMetaData[] colmds = mmd.getColumnMetaData();
                        String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                        ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                        if (colmds != null && colmds.length == 1) {
                           col.setColumnMetaData(colmds[0]);
                           if (colmds[0].getPosition() != null) {
                              col.setPosition(colmds[0].getPosition());
                           }

                           if (colmds[0].getJdbcType() != null) {
                              col.setJdbcType(colmds[0].getJdbcType());
                           }
                        }

                        MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                        col.setMemberColumnMapping(mapping);
                        if (this.schemaVerifier != null) {
                           this.schemaVerifier.attributeMember(mapping, mmd);
                        }

                        this.mappingByMember.put(mmd, mapping);
                     }

                     NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded collection. Not yet supported. Ignoring");
                  } else if (mmd.hasArray()) {
                     if (storeMgr.getSupportedOptions().contains("ORM.EmbeddedArray.Nested")) {
                        ColumnMetaData[] colmds = mmd.getColumnMetaData();
                        String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                        ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                        if (colmds != null && colmds.length == 1) {
                           col.setColumnMetaData(colmds[0]);
                           if (colmds[0].getPosition() != null) {
                              col.setPosition(colmds[0].getPosition());
                           }

                           if (colmds[0].getJdbcType() != null) {
                              col.setJdbcType(colmds[0].getJdbcType());
                           }
                        }

                        MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                        col.setMemberColumnMapping(mapping);
                        if (this.schemaVerifier != null) {
                           this.schemaVerifier.attributeMember(mapping, mmd);
                        }

                        this.mappingByMember.put(mmd, mapping);
                     }

                     NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded array. Not yet supported. Ignoring");
                  }
               }
            } else {
               ColumnMetaData[] colmds = mmd.getColumnMetaData();
               if ((colmds == null || colmds.length == 0) && mmd.hasCollection() && mmd.getElementMetaData() != null) {
                  colmds = mmd.getElementMetaData().getColumnMetaData();
               }

               if (relationType != RelationType.NONE) {
                  String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                  ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                  if (colmds != null && colmds.length == 1) {
                     col.setColumnMetaData(colmds[0]);
                     if (colmds[0].getPosition() != null) {
                        col.setPosition(colmds[0].getPosition());
                     }

                     if (colmds[0].getJdbcType() != null) {
                        col.setJdbcType(colmds[0].getJdbcType());
                     }
                  }

                  MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                  col.setMemberColumnMapping(mapping);
                  if (this.schemaVerifier != null) {
                     this.schemaVerifier.attributeMember(mapping, mmd);
                  }

                  this.mappingByMember.put(mmd, mapping);
               } else {
                  TypeConverter typeConv = this.getTypeConverterForMember(mmd, colmds, typeMgr);
                  if (typeConv == null) {
                     String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                     ColumnImpl col = this.addColumn(mmd, colName, (TypeConverter)null);
                     if (colmds != null && colmds.length == 1) {
                        col.setColumnMetaData(colmds[0]);
                        if (colmds[0].getPosition() != null) {
                           col.setPosition(colmds[0].getPosition());
                        }

                        if (colmds[0].getJdbcType() != null) {
                           col.setJdbcType(colmds[0].getJdbcType());
                        }
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                     col.setMemberColumnMapping(mapping);
                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeMember(mapping, mmd);
                     }

                     this.mappingByMember.put(mmd, mapping);
                  } else if (!(typeConv instanceof MultiColumnConverter)) {
                     String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, 0);
                     ColumnImpl col = this.addColumn(mmd, colName, typeConv);
                     if (colmds != null && colmds.length == 1) {
                        col.setColumnMetaData(colmds[0]);
                        if (colmds[0].getPosition() != null) {
                           col.setPosition(colmds[0].getPosition());
                        }

                        if (colmds[0].getJdbcType() != null) {
                           col.setJdbcType(colmds[0].getJdbcType());
                        }
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                     col.setMemberColumnMapping(mapping);
                     mapping.setTypeConverter(typeConv);
                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeMember(mapping, mmd);
                     }

                     this.mappingByMember.put(mmd, mapping);
                  } else {
                     Class[] colJavaTypes = ((MultiColumnConverter)typeConv).getDatastoreColumnTypes();
                     Column[] cols = new Column[colJavaTypes.length];

                     for(int j = 0; j < colJavaTypes.length; ++j) {
                        String colName = storeMgr.getNamingFactory().getColumnName(mmd, ColumnType.COLUMN, j);
                        ColumnImpl col = this.addColumn(mmd, colName, typeConv);
                        if (colmds != null && colmds.length == 1) {
                           col.setColumnMetaData(colmds[0]);
                           if (colmds[j].getPosition() != null) {
                              col.setPosition(colmds[j].getPosition());
                           }

                           if (colmds[j].getJdbcType() != null) {
                              col.setJdbcType(colmds[j].getJdbcType());
                           }
                        }

                        cols[j] = col;
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, cols, typeConv);

                     for(int j = 0; j < colJavaTypes.length; ++j) {
                        ((ColumnImpl)cols[j]).setMemberColumnMapping(mapping);
                     }

                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeMember(mapping, mmd);
                     }

                     this.mappingByMember.put(mmd, mapping);
                  }
               }
            }
         }
      }

      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         String colName = storeMgr.getNamingFactory().getColumnName(cmd, ColumnType.DATASTOREID_COLUMN);
         ColumnImpl col = this.addColumn((AbstractMemberMetaData)null, colName, ColumnType.DATASTOREID_COLUMN, (TypeConverter)null);
         if (this.schemaVerifier != null) {
            this.schemaVerifier.attributeMember(new MemberColumnMappingImpl((AbstractMemberMetaData)null, col));
         }

         if (cmd.getIdentityMetaData() != null && cmd.getIdentityMetaData().getColumnMetaData() != null) {
            if (cmd.getIdentityMetaData().getColumnMetaData().getPosition() != null) {
               col.setPosition(cmd.getIdentityMetaData().getColumnMetaData().getPosition());
            }

            if (cmd.getIdentityMetaData().getColumnMetaData().getJdbcType() != null) {
               col.setJdbcType(cmd.getIdentityMetaData().getColumnMetaData().getJdbcType());
            }
         }

         this.datastoreIdColumn = col;
      }

      VersionMetaData vermd = cmd.getVersionMetaDataForClass();
      if (vermd != null && vermd.getFieldName() == null) {
         String colName = storeMgr.getNamingFactory().getColumnName(cmd, ColumnType.VERSION_COLUMN);
         ColumnImpl col = this.addColumn((AbstractMemberMetaData)null, colName, ColumnType.VERSION_COLUMN, (TypeConverter)null);
         if (this.schemaVerifier != null) {
            this.schemaVerifier.attributeMember(new MemberColumnMappingImpl((AbstractMemberMetaData)null, col));
         }

         if (vermd.getColumnMetaData() != null) {
            if (vermd.getColumnMetaData().getPosition() != null) {
               col.setPosition(vermd.getColumnMetaData().getPosition());
            }

            if (vermd.getColumnMetaData().getJdbcType() != null) {
               col.setJdbcType(vermd.getColumnMetaData().getJdbcType());
            }
         }

         this.versionColumn = col;
      }

      if (cmd.hasDiscriminatorStrategy()) {
         String colName = storeMgr.getNamingFactory().getColumnName(cmd, ColumnType.DISCRIMINATOR_COLUMN);
         ColumnImpl col = this.addColumn((AbstractMemberMetaData)null, colName, ColumnType.DISCRIMINATOR_COLUMN, (TypeConverter)null);
         if (this.schemaVerifier != null) {
            this.schemaVerifier.attributeMember(new MemberColumnMappingImpl((AbstractMemberMetaData)null, col));
         }

         DiscriminatorMetaData dismd = cmd.getDiscriminatorMetaDataForTable();
         if (dismd != null && dismd.getColumnMetaData() != null) {
            if (dismd.getColumnMetaData().getPosition() != null) {
               col.setPosition(dismd.getColumnMetaData().getPosition());
            }

            if (dismd.getColumnMetaData().getJdbcType() != null) {
               col.setJdbcType(dismd.getColumnMetaData().getJdbcType());
            }
         }

         this.discriminatorColumn = col;
      }

      if (storeMgr.getStringProperty("datanucleus.TenantID") != null && !"true".equalsIgnoreCase(cmd.getValueForExtension("multitenancy-disable"))) {
         String colName = storeMgr.getNamingFactory().getColumnName(cmd, ColumnType.MULTITENANCY_COLUMN);
         Column col = this.addColumn((AbstractMemberMetaData)null, colName, ColumnType.MULTITENANCY_COLUMN, (TypeConverter)null);
         col.setJdbcType(JdbcType.VARCHAR);
         if (this.schemaVerifier != null) {
            this.schemaVerifier.attributeMember(new MemberColumnMappingImpl((AbstractMemberMetaData)null, col));
         }

         this.multitenancyColumn = col;
      }

      List<Column> unorderedCols = new ArrayList();
      Column[] cols = new Column[this.columns.size()];

      for(Column col : this.columns) {
         if (col.getPosition() >= this.columns.size()) {
            NucleusLogger.DATASTORE_SCHEMA.warn("Column with name " + col.getName() + " is specified with position=" + col.getPosition() + " which is invalid. This table has " + this.columns.size() + " columns");
            unorderedCols.add(col);
         } else if (col.getPosition() >= 0) {
            if (cols[col.getPosition()] != null) {
               NucleusLogger.DATASTORE_SCHEMA.warn("Column with name " + col.getName() + " defined for position=" + col.getPosition() + " yet there is also " + cols[col.getPosition()].getName() + " at that position! Ignoring");
               unorderedCols.add(col);
            } else {
               cols[col.getPosition()] = col;
            }
         } else {
            unorderedCols.add(col);
         }
      }

      if (!unorderedCols.isEmpty()) {
         for(int i = 0; i < cols.length; ++i) {
            if (cols[i] == null) {
               cols[i] = (Column)unorderedCols.get(0);
               cols[i].setPosition(i);
               unorderedCols.remove(0);
            }
         }
      }

      this.columns = new ArrayList();

      for(Column col : cols) {
         MemberColumnMapping mapping = col.getMemberColumnMapping();
         if (mapping != null) {
            if (!mapping.getMemberMetaData().isInsertable() && !mapping.getMemberMetaData().isUpdateable()) {
               NucleusLogger.DATASTORE_SCHEMA.debug("Not adding column " + col.getName() + " for member=" + mapping.getMemberMetaData().getFullFieldName() + " since is not insertable/updateable");
            } else {
               if (this.columnByName.containsKey(col.getName())) {
                  NucleusLogger.DATASTORE_SCHEMA.error("Unable to add column with name=" + col.getName() + " to table=" + this.getName() + " for class=" + cmd.getFullClassName() + " since one with same name already exists.");
                  throw new NucleusUserException("Unable to add column with name=" + col.getName() + " to table=" + this.getName() + " for class=" + cmd.getFullClassName() + " since one with same name already exists.");
               }

               this.columns.add(col);
               this.columnByName.put(col.getName(), col);
            }
         } else {
            this.columns.add(col);
            this.columnByName.put(col.getName(), col);
         }
      }

   }

   protected TypeConverter getTypeConverterForMember(AbstractMemberMetaData mmd, ColumnMetaData[] colmds, TypeManager typeMgr) {
      TypeConverter typeConv = null;
      String typeConvName = mmd.getTypeConverterName();
      if (typeConvName != null) {
         typeConv = typeMgr.getTypeConverterForName(typeConvName);
         if (typeConv == null) {
            throw new NucleusUserException(Localiser.msg("044062", mmd.getFullFieldName(), typeConvName));
         }
      } else {
         typeConv = typeMgr.getAutoApplyTypeConverterForType(mmd.getType());
      }

      if (typeConv == null) {
         if (colmds != null && colmds.length > 1) {
            Collection<TypeConverter> converters = typeMgr.getTypeConvertersForType(mmd.getType());
            if (converters != null && !converters.isEmpty()) {
               for(TypeConverter conv : converters) {
                  if (conv instanceof MultiColumnConverter && ((MultiColumnConverter)conv).getDatastoreColumnTypes().length == colmds.length) {
                     typeConv = conv;
                     break;
                  }
               }
            }

            if (typeConv == null) {
            }
         } else {
            JdbcType jdbcType = colmds != null && colmds.length > 0 ? colmds[0].getJdbcType() : null;
            if (jdbcType != null) {
               if (MetaDataUtils.isJdbcTypeString(jdbcType)) {
                  typeConv = typeMgr.getTypeConverterForType(mmd.getType(), String.class);
               } else if (MetaDataUtils.isJdbcTypeNumeric(jdbcType)) {
                  typeConv = typeMgr.getTypeConverterForType(mmd.getType(), Long.class);
               } else if (jdbcType == JdbcType.TIMESTAMP) {
                  typeConv = typeMgr.getTypeConverterForType(mmd.getType(), Timestamp.class);
               } else if (jdbcType == JdbcType.TIME) {
                  typeConv = typeMgr.getTypeConverterForType(mmd.getType(), Time.class);
               } else if (jdbcType == JdbcType.DATE) {
                  typeConv = typeMgr.getTypeConverterForType(mmd.getType(), Date.class);
               }
            } else {
               typeConv = typeMgr.getDefaultTypeConverterForType(mmd.getType());
            }
         }
      }

      if (this.schemaVerifier != null) {
         typeConv = this.schemaVerifier.verifyTypeConverterForMember(mmd, typeConv);
      }

      return typeConv;
   }

   protected void processEmbeddedMember(List mmds, ClassLoaderResolver clr, EmbeddedMetaData embmd, boolean ownerNested) {
      TypeManager typeMgr = this.storeMgr.getNucleusContext().getTypeManager();
      MetaDataManager mmgr = this.storeMgr.getMetaDataManager();
      NamingFactory namingFactory = this.storeMgr.getNamingFactory();
      AbstractMemberMetaData lastMmd = (AbstractMemberMetaData)mmds.get(mmds.size() - 1);
      AbstractClassMetaData embCmd = null;
      if (lastMmd.hasCollection()) {
         embCmd = mmgr.getMetaDataForClass(lastMmd.getCollection().getElementType(), clr);
      } else if (lastMmd.hasArray()) {
         embCmd = mmgr.getMetaDataForClass(lastMmd.getArray().getElementType(), clr);
      } else {
         embCmd = mmgr.getMetaDataForClass(lastMmd.getType(), clr);
      }

      int[] memberPositions = embCmd.getAllMemberPositions();

      for(int i = 0; i < memberPositions.length; ++i) {
         AbstractMemberMetaData mmd = embCmd.getMetaDataForManagedMemberAtAbsolutePosition(memberPositions[i]);
         if (mmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT && (mmds.size() != 1 || embmd == null || embmd.getOwnerMember() == null || !embmd.getOwnerMember().equals(mmd.getName()))) {
            AbstractMemberMetaData embmdMmd = null;
            if (embmd != null) {
               AbstractMemberMetaData[] embmdMmds = embmd.getMemberMetaData();
               if (embmdMmds != null) {
                  for(AbstractMemberMetaData thisMmd : embmdMmds) {
                     if (thisMmd.getName().equals(mmd.getName())) {
                        embmdMmd = thisMmd;
                        break;
                     }
                  }
               }
            }

            RelationType relationType = mmd.getRelationType(clr);
            if (relationType != RelationType.NONE && MetaDataUtils.getInstance().isMemberEmbedded(mmgr, clr, mmd, relationType, lastMmd)) {
               if (RelationType.isRelationSingleValued(relationType)) {
                  boolean nested = false;
                  if (this.storeMgr.getSupportedOptions().contains("ORM.EmbeddedPC.Nested")) {
                     nested = !this.storeMgr.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.metadata.embedded.flat");
                     String nestedStr = mmd.getValueForExtension("nested");
                     if (nestedStr != null && nestedStr.equalsIgnoreCase("" + !nested)) {
                        nested = !nested;
                     }
                  }

                  List<AbstractMemberMetaData> embMmds = new ArrayList(mmds);
                  embMmds.add(mmd);
                  if (!nested) {
                     this.processEmbeddedMember(embMmds, clr, embmdMmd != null ? embmdMmd.getEmbeddedMetaData() : null, false);
                  } else {
                     ColumnMetaData[] colmds = mmd.getColumnMetaData();
                     String colName = namingFactory.getColumnName(embMmds, 0);
                     ColumnImpl col = this.addEmbeddedColumn(colName, (TypeConverter)null);
                     col.setNested(true);
                     if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getPosition() != null) {
                        col.setPosition(embmdMmd.getColumnMetaData()[0].getPosition());
                     } else if (colmds != null && colmds.length == 1 && colmds[0].getPosition() != null) {
                        col.setPosition(colmds[0].getPosition());
                     }

                     if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getJdbcType() != null) {
                        col.setJdbcType(embmdMmd.getColumnMetaData()[0].getJdbcType());
                     } else if (colmds != null && colmds.length == 1 && colmds[0].getJdbcType() != null) {
                        col.setJdbcType(colmds[0].getJdbcType());
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                     col.setMemberColumnMapping(mapping);
                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeEmbeddedMember(mapping, embMmds);
                     }

                     this.mappingByEmbeddedMember.put(this.getEmbeddedMemberNavigatedPath(embMmds), mapping);
                     this.processEmbeddedMember(embMmds, clr, embmdMmd != null ? embmdMmd.getEmbeddedMetaData() : null, true);
                  }
               } else if (mmd.hasCollection()) {
                  if (this.storeMgr.getSupportedOptions().contains("ORM.EmbeddedCollection.Nested")) {
                  }

                  NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded collection. Not yet supported. Ignoring");
               } else if (mmd.hasMap()) {
                  if (this.storeMgr.getSupportedOptions().contains("ORM.EmbeddedMap.Nested")) {
                  }

                  NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded collection. Not yet supported. Ignoring");
               } else if (mmd.hasArray()) {
                  if (this.storeMgr.getSupportedOptions().contains("ORM.EmbeddedArray.Nested")) {
                  }

                  NucleusLogger.DATASTORE_SCHEMA.warn("Member " + mmd.getFullFieldName() + " is an embedded array. Not yet supported. Ignoring");
               }
            } else {
               List<AbstractMemberMetaData> embMmds = new ArrayList(mmds);
               embMmds.add(mmd);
               ColumnMetaData[] colmds = mmd.getColumnMetaData();
               if (relationType != RelationType.NONE) {
                  String colName = namingFactory.getColumnName(embMmds, 0);
                  ColumnImpl col = this.addEmbeddedColumn(colName, (TypeConverter)null);
                  col.setNested(ownerNested);
                  if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getPosition() != null) {
                     col.setPosition(embmdMmd.getColumnMetaData()[0].getPosition());
                  } else if (colmds != null && colmds.length == 1 && colmds[0].getPosition() != null) {
                     col.setPosition(colmds[0].getPosition());
                  }

                  if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getJdbcType() != null) {
                     col.setJdbcType(embmdMmd.getColumnMetaData()[0].getJdbcType());
                  } else if (colmds != null && colmds.length == 1 && colmds[0].getJdbcType() != null) {
                     col.setJdbcType(colmds[0].getJdbcType());
                  }

                  MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                  col.setMemberColumnMapping(mapping);
                  if (this.schemaVerifier != null) {
                     this.schemaVerifier.attributeEmbeddedMember(mapping, embMmds);
                  }

                  this.mappingByEmbeddedMember.put(this.getEmbeddedMemberNavigatedPath(embMmds), mapping);
               } else {
                  TypeConverter typeConv = this.getTypeConverterForMember(mmd, colmds, typeMgr);
                  if (typeConv != null) {
                     if (typeConv instanceof MultiColumnConverter) {
                        Class[] colJavaTypes = ((MultiColumnConverter)typeConv).getDatastoreColumnTypes();
                        Column[] cols = new Column[colJavaTypes.length];

                        for(int j = 0; j < colJavaTypes.length; ++j) {
                           String colName = namingFactory.getColumnName(embMmds, j);
                           ColumnImpl col = this.addEmbeddedColumn(colName, typeConv);
                           col.setNested(ownerNested);
                           if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == colJavaTypes.length && embmdMmd.getColumnMetaData()[j].getPosition() != null) {
                              col.setPosition(embmdMmd.getColumnMetaData()[j].getPosition());
                           } else if (colmds != null && colmds.length == colJavaTypes.length && colmds[j].getPosition() != null) {
                              col.setPosition(colmds[j].getPosition());
                           }

                           if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == colJavaTypes.length && embmdMmd.getColumnMetaData()[j].getJdbcType() != null) {
                              col.setJdbcType(embmdMmd.getColumnMetaData()[j].getJdbcType());
                           } else if (colmds != null && colmds.length == colJavaTypes.length && colmds[j].getJdbcType() != null) {
                              col.setJdbcType(colmds[j].getJdbcType());
                           }

                           cols[j] = col;
                        }

                        MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, cols, typeConv);

                        for(int j = 0; j < colJavaTypes.length; ++j) {
                           ((ColumnImpl)cols[j]).setMemberColumnMapping(mapping);
                        }

                        if (this.schemaVerifier != null) {
                           this.schemaVerifier.attributeEmbeddedMember(mapping, embMmds);
                        }

                        this.mappingByEmbeddedMember.put(this.getEmbeddedMemberNavigatedPath(embMmds), mapping);
                     } else {
                        String colName = namingFactory.getColumnName(embMmds, 0);
                        ColumnImpl col = this.addEmbeddedColumn(colName, typeConv);
                        col.setNested(ownerNested);
                        if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getPosition() != null) {
                           col.setPosition(embmdMmd.getColumnMetaData()[0].getPosition());
                        } else if (colmds != null && colmds.length == 1 && colmds[0].getPosition() != null) {
                           col.setPosition(colmds[0].getPosition());
                        }

                        if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getJdbcType() != null) {
                           col.setJdbcType(embmdMmd.getColumnMetaData()[0].getJdbcType());
                        } else if (colmds != null && colmds.length == 1 && colmds[0].getJdbcType() != null) {
                           col.setJdbcType(colmds[0].getJdbcType());
                        }

                        MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                        col.setMemberColumnMapping(mapping);
                        mapping.setTypeConverter(typeConv);
                        if (this.schemaVerifier != null) {
                           this.schemaVerifier.attributeEmbeddedMember(mapping, embMmds);
                        }

                        this.mappingByEmbeddedMember.put(this.getEmbeddedMemberNavigatedPath(embMmds), mapping);
                     }
                  } else {
                     String colName = namingFactory.getColumnName(embMmds, 0);
                     ColumnImpl col = this.addEmbeddedColumn(colName, (TypeConverter)null);
                     col.setNested(ownerNested);
                     AbstractMemberMetaData theMmd = (AbstractMemberMetaData)embMmds.get(0);
                     if (theMmd.isPrimaryKey()) {
                        col.setPrimaryKey();
                     }

                     if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getPosition() != null) {
                        col.setPosition(embmdMmd.getColumnMetaData()[0].getPosition());
                     } else if (colmds != null && colmds.length == 1 && colmds[0].getPosition() != null) {
                        col.setPosition(colmds[0].getPosition());
                     }

                     if (embmdMmd != null && embmdMmd.getColumnMetaData() != null && embmdMmd.getColumnMetaData().length == 1 && embmdMmd.getColumnMetaData()[0].getJdbcType() != null) {
                        col.setJdbcType(embmdMmd.getColumnMetaData()[0].getJdbcType());
                     } else if (colmds != null && colmds.length == 1 && colmds[0].getJdbcType() != null) {
                        col.setJdbcType(colmds[0].getJdbcType());
                     }

                     MemberColumnMapping mapping = new MemberColumnMappingImpl(mmd, col);
                     col.setMemberColumnMapping(mapping);
                     if (this.schemaVerifier != null) {
                        this.schemaVerifier.attributeEmbeddedMember(mapping, embMmds);
                     }

                     this.mappingByEmbeddedMember.put(this.getEmbeddedMemberNavigatedPath(embMmds), mapping);
                  }
               }
            }
         }
      }

   }

   protected ColumnImpl addColumn(AbstractMemberMetaData mmd, String colName, TypeConverter typeConv) {
      return this.addColumn(mmd, colName, ColumnType.COLUMN, typeConv);
   }

   protected ColumnImpl addColumn(AbstractMemberMetaData mmd, String colName, ColumnType colType, TypeConverter typeConv) {
      ColumnImpl col = new ColumnImpl(this, colName, colType);
      if (mmd != null) {
         if (mmd.isPrimaryKey()) {
            col.setPrimaryKey();
         }
      } else if (colType == ColumnType.DATASTOREID_COLUMN) {
         col.setPrimaryKey();
      }

      this.columns.add(col);
      return col;
   }

   protected ColumnImpl addEmbeddedColumn(String colName, TypeConverter typeConv) {
      ColumnImpl col = new ColumnImpl(this, colName, ColumnType.COLUMN);
      this.columns.add(col);
      return col;
   }

   private String getEmbeddedMemberNavigatedPath(List mmds) {
      Iterator<AbstractMemberMetaData> mmdIter = mmds.iterator();
      StringBuilder strBldr = new StringBuilder(((AbstractMemberMetaData)mmdIter.next()).getFullFieldName());

      while(mmdIter.hasNext()) {
         strBldr.append('.').append(((AbstractMemberMetaData)mmdIter.next()).getName());
      }

      return strBldr.toString();
   }

   public AbstractClassMetaData getClassMetaData() {
      return this.cmd;
   }

   public StoreManager getStoreManager() {
      return this.storeMgr;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public String getCatalogName() {
      return this.catalogName;
   }

   public String getName() {
      return this.identifier;
   }

   public int getNumberOfColumns() {
      return this.columns.size();
   }

   public List getColumns() {
      return this.columns;
   }

   public Column getColumnForPosition(int pos) {
      if (pos >= 0 && pos < this.columns.size()) {
         return (Column)this.columns.get(pos);
      } else {
         throw new ArrayIndexOutOfBoundsException("There are " + this.columns.size() + " columns, so specify a value between 0 and " + (this.columns.size() - 1));
      }
   }

   public Column getDatastoreIdColumn() {
      return this.datastoreIdColumn;
   }

   public Column getVersionColumn() {
      return this.versionColumn;
   }

   public Column getDiscriminatorColumn() {
      return this.discriminatorColumn;
   }

   public Column getMultitenancyColumn() {
      return this.multitenancyColumn;
   }

   public Column getColumnForName(String name) {
      Column col = (Column)this.columnByName.get(name);
      if (col != null) {
         return col;
      } else {
         if (!name.startsWith("\"")) {
            col = (Column)this.columnByName.get("\"" + name + "\"");
         }

         return col;
      }
   }

   public MemberColumnMapping getMemberColumnMappingForMember(AbstractMemberMetaData mmd) {
      return (MemberColumnMapping)this.mappingByMember.get(mmd);
   }

   public MemberColumnMapping getMemberColumnMappingForEmbeddedMember(List mmds) {
      return (MemberColumnMapping)this.mappingByEmbeddedMember.get(this.getEmbeddedMemberNavigatedPath(mmds));
   }

   public Set getMemberColumnMappings() {
      Set<MemberColumnMapping> mappings = new HashSet(this.mappingByMember.values());
      mappings.addAll(this.mappingByEmbeddedMember.values());
      return mappings;
   }

   public String toString() {
      return "Table: " + this.identifier;
   }

   public String debugString() {
      StringBuilder str = new StringBuilder();
      str.append("Table: ");
      if (this.catalogName != null) {
         str.append(this.catalogName).append('.');
      }

      if (this.schemaName != null) {
         str.append(this.schemaName).append('.');
      }

      str.append(this.identifier).append("\n");
      str.append("{\n");

      for(Column col : this.columns) {
         str.append("  ").append(col.toString()).append("\n");
      }

      str.append("}");
      return str.toString();
   }
}
