package org.datanucleus.store.rdbms.scostore;

import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class ElementContainerStore extends BaseContainerStore {
   protected boolean iterateUsingDiscriminator = false;
   protected String sizeStmt;
   protected String clearStmt;
   protected String addStmt;
   protected String removeStmt;
   protected ElementInfo[] elementInfo;
   protected AbstractClassMetaData emd;
   protected Table containerTable;
   protected JavaTypeMapping elementMapping;
   protected String elementType;
   protected boolean elementsAreEmbedded;
   protected boolean elementsAreSerialised;
   protected boolean elementIsPersistentInterface = false;
   protected JavaTypeMapping orderMapping;
   protected JavaTypeMapping relationDiscriminatorMapping;
   protected String relationDiscriminatorValue;

   protected ElementContainerStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   public JavaTypeMapping getElementMapping() {
      return this.elementMapping;
   }

   public JavaTypeMapping getRelationDiscriminatorMapping() {
      return this.relationDiscriminatorMapping;
   }

   public String getRelationDiscriminatorValue() {
      return this.relationDiscriminatorValue;
   }

   public Table getContainerTable() {
      return this.containerTable;
   }

   public AbstractClassMetaData getEmd() {
      return this.emd;
   }

   public boolean isElementsAreSerialised() {
      return this.elementsAreSerialised;
   }

   public boolean isElementsAreEmbedded() {
      return this.elementsAreEmbedded;
   }

   protected ElementInfo[] getElementInformationForClass() {
      ElementInfo[] info = null;
      DatastoreClass rootTbl;
      String[] clsNames;
      if (this.clr.classForName(this.elementType).isInterface()) {
         clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(this.elementType, this.clr);
         rootTbl = null;
      } else {
         clsNames = new String[]{this.elementType};
         rootTbl = this.storeMgr.getDatastoreClass(this.elementType, this.clr);
      }

      if (this.emd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
         List<ElementInfo> infos = new ArrayList();
         if (rootTbl != null) {
            infos.add(new ElementInfo(this.emd, rootTbl));
         }

         Collection<String> elementSubclassNames = this.storeMgr.getSubClassesForClass(this.emd.getFullClassName(), true, this.clr);
         if (elementSubclassNames != null) {
            for(String elementSubclassName : elementSubclassNames) {
               AbstractClassMetaData elemSubCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(elementSubclassName, this.clr);
               DatastoreClass elemSubTbl = this.storeMgr.getDatastoreClass(elementSubclassName, this.clr);
               if (elemSubTbl != null) {
                  infos.add(new ElementInfo(elemSubCmd, elemSubTbl));
               }
            }
         }

         info = new ElementInfo[infos.size()];
         int infoNo = 0;

         for(ElementInfo ci : infos) {
            info[infoNo++] = ci;
         }
      } else if (rootTbl == null) {
         if (this.clr.classForName(this.elementType).isInterface()) {
            info = new ElementInfo[clsNames.length];

            for(int i = 0; i < clsNames.length; ++i) {
               AbstractClassMetaData implCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(clsNames[i], this.clr);
               DatastoreClass table = this.storeMgr.getDatastoreClass(clsNames[i], this.clr);
               info[i] = new ElementInfo(implCmd, table);
            }
         } else {
            AbstractClassMetaData[] subclassCmds = this.storeMgr.getClassesManagingTableForClass(this.emd, this.clr);
            info = new ElementInfo[subclassCmds.length];

            for(int i = 0; i < subclassCmds.length; ++i) {
               DatastoreClass table = this.storeMgr.getDatastoreClass(subclassCmds[i].getFullClassName(), this.clr);
               info[i] = new ElementInfo(subclassCmds[i], table);
            }
         }
      } else {
         info = new ElementInfo[clsNames.length];

         for(int i = 0; i < clsNames.length; ++i) {
            AbstractClassMetaData cmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(clsNames[i], this.clr);
            DatastoreClass table = this.storeMgr.getDatastoreClass(cmd.getFullClassName(), this.clr);
            info[i] = new ElementInfo(cmd, table);
         }
      }

      return info;
   }

   public boolean hasOrderMapping() {
      return this.orderMapping != null;
   }

   protected boolean validateElementType(ClassLoaderResolver clr, Object element) {
      if (element == null) {
         return true;
      } else {
         Class primitiveElementClass = ClassUtils.getPrimitiveTypeForType(element.getClass());
         if (primitiveElementClass != null) {
            String elementTypeWrapper = this.elementType;
            Class elementTypeClass = clr.classForName(this.elementType);
            if (elementTypeClass.isPrimitive()) {
               elementTypeWrapper = ClassUtils.getWrapperTypeForPrimitiveType(elementTypeClass).getName();
            }

            return clr.isAssignableFrom(elementTypeWrapper, element.getClass());
         } else {
            String elementType = null;
            if (this.ownerMemberMetaData.hasCollection()) {
               elementType = this.ownerMemberMetaData.getCollection().getElementType();
            } else if (this.ownerMemberMetaData.hasArray()) {
               elementType = this.ownerMemberMetaData.getArray().getElementType();
            } else {
               elementType = this.elementType;
            }

            Class elementCls = clr.classForName(elementType);
            if (!this.storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(elementType) && elementCls.isInterface()) {
               String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(elementType, clr);
               if (clsNames != null && clsNames.length > 0) {
                  for(int i = 0; i < clsNames.length; ++i) {
                     if (clsNames[i].equals(element.getClass().getName())) {
                        return true;
                     }
                  }

                  return false;
               }
            }

            return clr.isAssignableFrom(elementType, element.getClass());
         }
      }
   }

   protected boolean validateElementForReading(ObjectProvider op, Object element) {
      if (!this.validateElementType(op.getExecutionContext().getClassLoaderResolver(), element)) {
         return false;
      } else {
         if (element != null && !this.elementsAreEmbedded && !this.elementsAreSerialised) {
            ExecutionContext ec = op.getExecutionContext();
            if ((!ec.getApiAdapter().isPersistent(element) || ec != ec.getApiAdapter().getExecutionContext(element)) && !ec.getApiAdapter().isDetached(element)) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean validateElementForWriting(ExecutionContext ec, Object element, FieldValues fieldValues) {
      if (!this.elementIsPersistentInterface && !this.validateElementType(ec.getClassLoaderResolver(), element)) {
         throw new ClassCastException(Localiser.msg("056033", new Object[]{element.getClass().getName(), this.ownerMemberMetaData.getFullFieldName(), this.elementType}));
      } else {
         boolean persisted = false;
         if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
            ObjectProvider elementSM = ec.findObjectProvider(element);
            if (elementSM != null && elementSM.isEmbedded()) {
               throw new NucleusUserException(Localiser.msg("056028", new Object[]{this.ownerMemberMetaData.getFullFieldName(), element}));
            }

            persisted = SCOUtils.validateObjectForWriting(ec, element, fieldValues);
         }

         return persisted;
      }
   }

   public abstract Iterator iterator(ObjectProvider var1);

   public void clear(ObjectProvider ownerOP) {
      Collection dependentElements = null;
      CollectionMetaData collmd = this.ownerMemberMetaData.getCollection();
      boolean dependent = collmd.isDependentElement();
      if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent && !collmd.isEmbeddedElement() && !collmd.isSerializedElement()) {
         dependentElements = new HashSet();
         Iterator iter = this.iterator(ownerOP);

         while(iter.hasNext()) {
            dependentElements.add(iter.next());
         }
      }

      String clearStmt = this.getClearStmt();

      try {
         ExecutionContext ec = ownerOP.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, clearStmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               sqlControl.executeStatementUpdate(ec, mconn, clearStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{clearStmt}), e);
      }

      if (dependentElements != null && dependentElements.size() > 0) {
         for(Object obj : dependentElements) {
            if (!ownerOP.getExecutionContext().getApiAdapter().isDeleted(obj)) {
               ownerOP.getExecutionContext().deleteObjectInternal(obj);
            }
         }
      }

   }

   protected String getClearStmt() {
      if (this.clearStmt == null) {
         synchronized(this) {
            StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString()).append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.getRelationDiscriminatorMapping() != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.clearStmt = stmt.toString();
         }
      }

      return this.clearStmt;
   }

   protected void invalidateAddStmt() {
      this.addStmt = null;
   }

   protected String getAddStmtForJoinTable() {
      if (this.addStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("INSERT INTO ");
            stmt.append(this.containerTable.toString());
            stmt.append(" (");

            for(int i = 0; i < this.getOwnerMapping().getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(this.getOwnerMapping().getDatastoreMapping(i).getColumn().getIdentifier().toString());
            }

            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(",").append(this.elementMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            }

            if (this.orderMapping != null) {
               for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",").append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               }
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",").append(this.relationDiscriminatorMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               }
            }

            stmt.append(") VALUES (");

            for(int i = 0; i < this.getOwnerMapping().getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(((AbstractDatastoreMapping)this.getOwnerMapping().getDatastoreMapping(i)).getInsertionInputParameter());
            }

            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(",").append(((AbstractDatastoreMapping)this.elementMapping.getDatastoreMapping(0)).getInsertionInputParameter());
            }

            if (this.orderMapping != null) {
               for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",").append(((AbstractDatastoreMapping)this.orderMapping.getDatastoreMapping(0)).getInsertionInputParameter());
               }
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",").append(((AbstractDatastoreMapping)this.relationDiscriminatorMapping.getDatastoreMapping(0)).getInsertionInputParameter());
               }
            }

            stmt.append(") ");
            this.addStmt = stmt.toString();
         }
      }

      return this.addStmt;
   }

   public int size(ObjectProvider op) {
      return this.getSize(op);
   }

   public int getSize(ObjectProvider ownerOP) {
      String sizeStmt = this.getSizeStmt();

      try {
         ExecutionContext ec = ownerOP.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         int numRows;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, sizeStmt);

            try {
               int jdbcPosition = 1;
               if (this.elementInfo == null) {
                  BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
               } else if (this.usingJoinTable()) {
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
                  if (this.elementInfo[0].getDiscriminatorMapping() != null) {
                     jdbcPosition = BackingStoreHelper.populateElementDiscriminatorInStatement(ec, ps, jdbcPosition, true, this.elementInfo[0], this.clr);
                  }

                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }
               } else {
                  for(int i = 0; i < this.elementInfo.length; ++i) {
                     jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
                     if (this.elementInfo[i].getDiscriminatorMapping() != null) {
                        jdbcPosition = BackingStoreHelper.populateElementDiscriminatorInStatement(ec, ps, jdbcPosition, true, this.elementInfo[i], this.clr);
                     }

                     if (this.relationDiscriminatorMapping != null) {
                        jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                     }
                  }
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, sizeStmt, ps);

               try {
                  if (!rs.next()) {
                     throw new NucleusDataStoreException(Localiser.msg("056007", new Object[]{sizeStmt}));
                  }

                  numRows = rs.getInt(1);
                  if (this.elementInfo != null && this.elementInfo.length > 1) {
                     while(rs.next()) {
                        numRows += rs.getInt(1);
                     }
                  }

                  JDBCUtils.logWarnings(rs);
               } finally {
                  rs.close();
               }
            } catch (SQLException sqle) {
               NucleusLogger.GENERAL.error("Exception in size", sqle);
               throw sqle;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return numRows;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056007", new Object[]{sizeStmt}), e);
      }
   }

   protected String getSizeStmt() {
      if (this.sizeStmt != null) {
         return this.sizeStmt;
      } else {
         synchronized(this) {
            boolean usingDiscriminatorInSizeStmt = false;
            String containerAlias = "THIS";
            StringBuilder stmt = new StringBuilder();
            if (this.elementInfo == null) {
               stmt.append("SELECT COUNT(*) FROM ").append(this.containerTable.toString()).append(" ").append(containerAlias);
               stmt.append(" WHERE ");
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, containerAlias, true);
               if (this.orderMapping != null) {
                  for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                     stmt.append(" AND ");
                     stmt.append(containerAlias).append(".").append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                     stmt.append(">=0");
                  }
               }

               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, containerAlias, false);
               }

               this.sizeStmt = stmt.toString();
               return this.sizeStmt;
            } else {
               if (this.usingJoinTable()) {
                  String joinedElementAlias = "ELEM";
                  ElementInfo elemInfo = this.elementInfo[0];
                  stmt.append("SELECT COUNT(*) FROM ").append(this.containerTable.toString()).append(" ").append(containerAlias);
                  boolean joinedDiscrim = false;
                  if (elemInfo.getDiscriminatorMapping() != null) {
                     joinedDiscrim = true;
                     JavaTypeMapping elemIdMapping = elemInfo.getDatastoreClass().getIdMapping();
                     stmt.append(this.allowNulls ? " LEFT OUTER JOIN " : " INNER JOIN ");
                     stmt.append(elemInfo.getDatastoreClass().toString()).append(" ").append(joinedElementAlias).append(" ON ");

                     for(int j = 0; j < this.elementMapping.getNumberOfDatastoreMappings(); ++j) {
                        if (j > 0) {
                           stmt.append(" AND ");
                        }

                        stmt.append(containerAlias).append(".").append(this.elementMapping.getDatastoreMapping(j).getColumn().getIdentifier());
                        stmt.append("=");
                        stmt.append(joinedElementAlias).append(".").append(elemIdMapping.getDatastoreMapping(j).getColumn().getIdentifier());
                     }
                  }

                  stmt.append(" WHERE ");
                  BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, containerAlias, true);
                  if (this.orderMapping != null) {
                     for(int j = 0; j < this.orderMapping.getNumberOfDatastoreMappings(); ++j) {
                        stmt.append(" AND ");
                        stmt.append(containerAlias).append(".").append(this.orderMapping.getDatastoreMapping(j).getColumn().getIdentifier().toString());
                        stmt.append(">=0");
                     }
                  }

                  StringBuilder discrStmt = new StringBuilder();
                  if (elemInfo.getDiscriminatorMapping() != null) {
                     usingDiscriminatorInSizeStmt = true;
                     JavaTypeMapping discrimMapping = elemInfo.getDiscriminatorMapping();
                     Collection<String> classNames = this.storeMgr.getSubClassesForClass(elemInfo.getClassName(), true, this.clr);
                     classNames.add(elemInfo.getClassName());

                     for(String className : classNames) {
                        Class cls = this.clr.classForName(className);
                        if (!Modifier.isAbstract(cls.getModifiers())) {
                           for(int j = 0; j < discrimMapping.getNumberOfDatastoreMappings(); ++j) {
                              if (discrStmt.length() > 0) {
                                 discrStmt.append(" OR ");
                              }

                              discrStmt.append(joinedDiscrim ? joinedElementAlias : containerAlias);
                              discrStmt.append(".");
                              discrStmt.append(discrimMapping.getDatastoreMapping(j).getColumn().getIdentifier().toString());
                              discrStmt.append("=");
                              discrStmt.append(((AbstractDatastoreMapping)discrimMapping.getDatastoreMapping(j)).getUpdateInputParameter());
                           }
                        }
                     }
                  }

                  if (discrStmt.length() > 0) {
                     stmt.append(" AND (");
                     stmt.append(discrStmt);
                     if (this.allowNulls) {
                        stmt.append(" OR ");
                        stmt.append(elemInfo.getDiscriminatorMapping().getDatastoreMapping(0).getColumn().getIdentifier().toString());
                        stmt.append(" IS NULL");
                     }

                     stmt.append(")");
                  }

                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, containerAlias, false);
                  }
               } else {
                  for(int i = 0; i < this.elementInfo.length; ++i) {
                     if (i > 0) {
                        stmt.append(" UNION ");
                     }

                     ElementInfo elemInfo = this.elementInfo[i];
                     stmt.append("SELECT COUNT(*),").append("'" + elemInfo.getAbstractClassMetaData().getName() + "'");
                     stmt.append(" FROM ").append(elemInfo.getDatastoreClass().toString()).append(" ").append(containerAlias);
                     stmt.append(" WHERE ");
                     BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, containerAlias, true);
                     if (this.orderMapping != null) {
                        for(int j = 0; j < this.orderMapping.getNumberOfDatastoreMappings(); ++j) {
                           stmt.append(" AND ");
                           stmt.append(containerAlias).append(".").append(this.orderMapping.getDatastoreMapping(j).getColumn().getIdentifier().toString());
                           stmt.append(">=0");
                        }
                     }

                     StringBuilder discrStmt = new StringBuilder();
                     if (elemInfo.getDiscriminatorMapping() != null) {
                        usingDiscriminatorInSizeStmt = true;
                        JavaTypeMapping discrimMapping = elemInfo.getDiscriminatorMapping();
                        Collection<String> classNames = this.storeMgr.getSubClassesForClass(elemInfo.getClassName(), true, this.clr);
                        classNames.add(elemInfo.getClassName());

                        for(String className : classNames) {
                           Class cls = this.clr.classForName(className);
                           if (!Modifier.isAbstract(cls.getModifiers())) {
                              for(int j = 0; j < discrimMapping.getNumberOfDatastoreMappings(); ++j) {
                                 if (discrStmt.length() > 0) {
                                    discrStmt.append(" OR ");
                                 }

                                 discrStmt.append(containerAlias).append(".").append(discrimMapping.getDatastoreMapping(j).getColumn().getIdentifier().toString());
                                 discrStmt.append("=");
                                 discrStmt.append(((AbstractDatastoreMapping)discrimMapping.getDatastoreMapping(j)).getUpdateInputParameter());
                              }
                           }
                        }
                     }

                     if (discrStmt.length() > 0) {
                        stmt.append(" AND (");
                        stmt.append(discrStmt);
                        if (this.allowNulls) {
                           stmt.append(" OR ");
                           stmt.append(elemInfo.getDiscriminatorMapping().getDatastoreMapping(0).getColumn().getIdentifier().toString());
                           stmt.append(" IS NULL");
                        }

                        stmt.append(")");
                     }

                     if (this.relationDiscriminatorMapping != null) {
                        BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, containerAlias, false);
                     }
                  }
               }

               if (!usingDiscriminatorInSizeStmt) {
                  this.sizeStmt = stmt.toString();
               }

               return stmt.toString();
            }
         }
      }
   }

   protected ElementInfo getElementInfoForElement(Object element) {
      if (this.elementInfo == null) {
         return null;
      } else {
         ElementInfo elemInfo = null;

         for(int i = 0; i < this.elementInfo.length; ++i) {
            if (this.elementInfo[i].getClassName().equals(element.getClass().getName())) {
               elemInfo = this.elementInfo[i];
               break;
            }
         }

         if (elemInfo == null) {
            Class elementCls = element.getClass();

            for(int i = 0; i < this.elementInfo.length; ++i) {
               Class elemInfoCls = this.clr.classForName(this.elementInfo[i].getClassName());
               if (elemInfoCls.isAssignableFrom(elementCls)) {
                  elemInfo = this.elementInfo[i];
                  break;
               }
            }
         }

         return elemInfo;
      }
   }

   protected boolean usingJoinTable() {
      return this.elementInfo == null || this.elementInfo[0].getDatastoreClass() != this.containerTable;
   }

   public static class ElementInfo {
      AbstractClassMetaData cmd;
      DatastoreClass table;

      public ElementInfo(AbstractClassMetaData cmd, DatastoreClass table) {
         this.cmd = cmd;
         this.table = table;
      }

      public String getClassName() {
         return this.cmd.getFullClassName();
      }

      public AbstractClassMetaData getAbstractClassMetaData() {
         return this.cmd;
      }

      public DatastoreClass getDatastoreClass() {
         return this.table;
      }

      public DiscriminatorStrategy getDiscriminatorStrategy() {
         return this.cmd.getDiscriminatorStrategyForTable();
      }

      public JavaTypeMapping getDiscriminatorMapping() {
         return this.table.getDiscriminatorMapping(false);
      }

      public String toString() {
         return "ElementInfo : [class=" + this.cmd.getFullClassName() + " table=" + this.table + "]";
      }
   }
}
