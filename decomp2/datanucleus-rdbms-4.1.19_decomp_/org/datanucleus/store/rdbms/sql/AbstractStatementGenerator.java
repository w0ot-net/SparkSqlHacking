package org.datanucleus.store.rdbms.sql;

import java.util.HashSet;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;

public abstract class AbstractStatementGenerator implements StatementGenerator {
   protected final RDBMSStoreManager storeMgr;
   protected final ClassLoaderResolver clr;
   protected SQLStatement parentStmt;
   protected Class candidateType;
   protected final boolean includeSubclasses;
   protected DatastoreClass candidateTable;
   protected DatastoreIdentifier candidateTableAlias;
   protected String candidateTableGroupName;
   Table joinTable;
   DatastoreIdentifier joinTableAlias;
   JavaTypeMapping joinElementMapping;
   Set options;

   public AbstractStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean subclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName) {
      this.parentStmt = null;
      this.candidateTableGroupName = null;
      this.joinTable = null;
      this.joinTableAlias = null;
      this.joinElementMapping = null;
      this.options = new HashSet();
      this.storeMgr = storeMgr;
      this.clr = clr;
      this.candidateType = candidateType;
      this.includeSubclasses = subclasses;
      this.candidateTableGroupName = candidateTableGroupName;
      String candidateClassName = candidateType.getName();
      AbstractClassMetaData acmd = storeMgr.getMetaDataManager().getMetaDataForClass(candidateType, clr);
      if (!storeMgr.getMappedTypeManager().isSupportedMappedType(candidateClassName)) {
         if (acmd == null) {
            throw new NucleusUserException("Attempt to create SQL statement for type without metadata! : " + candidateType.getName());
         }

         this.candidateTable = storeMgr.getDatastoreClass(acmd.getFullClassName(), clr);
         if (this.candidateTable == null) {
            AbstractClassMetaData[] subcmds = storeMgr.getClassesManagingTableForClass(acmd, clr);
            if (subcmds == null || subcmds.length > 1) {
               throw new NucleusException("Attempt to generate SQL statement for instances of " + candidateType.getName() + " but has no table of its own and not single subclass with table so unsupported");
            }

            this.candidateTable = storeMgr.getDatastoreClass(subcmds[0].getFullClassName(), clr);
         }
      }

      this.candidateTableAlias = candidateTableAlias;
   }

   public AbstractStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean subclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName, Table joinTable, DatastoreIdentifier joinTableAlias, JavaTypeMapping joinElementMapping) {
      this(storeMgr, clr, candidateType, subclasses, candidateTableAlias, candidateTableGroupName);
      this.joinTable = joinTable;
      this.joinTableAlias = joinTableAlias;
      this.joinElementMapping = joinElementMapping;
   }

   public StatementGenerator setOption(String name) {
      this.options.add(name);
      return this;
   }

   public StatementGenerator unsetOption(String name) {
      this.options.remove(name);
      return this;
   }

   public boolean hasOption(String name) {
      return this.options.contains(name);
   }
}
