package org.apache.derby.iapi.sql.dictionary;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.daemon.IndexStatisticsDaemon;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface DataDictionary {
   String MODULE = "org.apache.derby.iapi.sql.dictionary.DataDictionary";
   int DD_VERSION_CURRENT = -1;
   int DD_VERSION_CS_5_0 = 80;
   int DD_VERSION_CS_5_1 = 90;
   int DD_VERSION_CS_5_2 = 100;
   int DD_VERSION_CS_8_1 = 110;
   int DD_VERSION_CS_10_0 = 120;
   int DD_VERSION_DERBY_10_1 = 130;
   int DD_VERSION_DERBY_10_2 = 140;
   int DD_VERSION_DERBY_10_3 = 150;
   int DD_VERSION_DERBY_10_4 = 160;
   int DD_VERSION_DERBY_10_5 = 170;
   int DD_VERSION_DERBY_10_6 = 180;
   int DD_VERSION_DERBY_10_7 = 190;
   int DD_VERSION_DERBY_10_8 = 200;
   int DD_VERSION_DERBY_10_9 = 210;
   int DD_VERSION_DERBY_10_10 = 220;
   int DD_VERSION_DERBY_10_11 = 230;
   int DD_VERSION_DERBY_10_12 = 240;
   int DD_VERSION_DERBY_10_13 = 250;
   int DD_VERSION_DERBY_10_14 = 260;
   int DD_VERSION_DERBY_10_15 = 270;
   int DD_VERSION_DERBY_10_16 = 280;
   String DATABASE_ID = "derby.databaseID";
   String CORE_DATA_DICTIONARY_VERSION = "DataDictionaryVersion";
   String CREATE_DATA_DICTIONARY_VERSION = "CreateDataDictionaryVersion";
   String SOFT_DATA_DICTIONARY_VERSION = "derby.softDataDictionaryVersion";
   String PROPERTY_CONGLOMERATE_VERSION = "PropertyConglomerateVersion";
   DataTypeDescriptor TYPE_SYSTEM_IDENTIFIER = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, false, 128);
   TypeDescriptor CATALOG_TYPE_SYSTEM_IDENTIFIER = TYPE_SYSTEM_IDENTIFIER.getCatalogType();
   int SYSCONGLOMERATES_CATALOG_NUM = 0;
   int SYSTABLES_CATALOG_NUM = 1;
   int SYSCOLUMNS_CATALOG_NUM = 2;
   int SYSSCHEMAS_CATALOG_NUM = 3;
   int SYSCONSTRAINTS_CATALOG_NUM = 4;
   int SYSKEYS_CATALOG_NUM = 5;
   int SYSDEPENDS_CATALOG_NUM = 6;
   int SYSALIASES_CATALOG_NUM = 7;
   int SYSVIEWS_CATALOG_NUM = 8;
   int SYSCHECKS_CATALOG_NUM = 9;
   int SYSFOREIGNKEYS_CATALOG_NUM = 10;
   int SYSSTATEMENTS_CATALOG_NUM = 11;
   int SYSFILES_CATALOG_NUM = 12;
   int SYSTRIGGERS_CATALOG_NUM = 13;
   int SYSSTATISTICS_CATALOG_NUM = 14;
   int SYSDUMMY1_CATALOG_NUM = 15;
   int SYSTABLEPERMS_CATALOG_NUM = 16;
   int SYSCOLPERMS_CATALOG_NUM = 17;
   int SYSROUTINEPERMS_CATALOG_NUM = 18;
   int SYSROLES_CATALOG_NUM = 19;
   int SYSSEQUENCES_CATALOG_NUM = 20;
   int SYSPERMS_CATALOG_NUM = 21;
   int SYSUSERS_CATALOG_NUM = 22;
   int NOTNULL_CONSTRAINT = 1;
   int PRIMARYKEY_CONSTRAINT = 2;
   int UNIQUE_CONSTRAINT = 3;
   int CHECK_CONSTRAINT = 4;
   int DROP_CONSTRAINT = 5;
   int FOREIGNKEY_CONSTRAINT = 6;
   int MODIFY_CONSTRAINT = 7;
   int COMPILE_ONLY_MODE = 0;
   int DDL_MODE = 1;

   void clearCaches(boolean var1) throws StandardException;

   void clearCaches() throws StandardException;

   void clearSequenceCaches() throws StandardException;

   int startReading(LanguageConnectionContext var1) throws StandardException;

   void doneReading(int var1, LanguageConnectionContext var2) throws StandardException;

   void startWriting(LanguageConnectionContext var1) throws StandardException;

   void transactionFinished() throws StandardException;

   ExecutionFactory getExecutionFactory();

   DataValueFactory getDataValueFactory();

   DataDescriptorGenerator getDataDescriptorGenerator();

   String getAuthorizationDatabaseOwner();

   boolean usesSqlAuthorization();

   int getCollationTypeOfSystemSchemas();

   int getCollationTypeOfUserSchemas();

   SchemaDescriptor getSchemaDescriptor(String var1, TransactionController var2, boolean var3) throws StandardException;

   SchemaDescriptor getSchemaDescriptor(UUID var1, TransactionController var2) throws StandardException;

   SchemaDescriptor getSchemaDescriptor(UUID var1, int var2, TransactionController var3) throws StandardException;

   boolean existsSchemaOwnedBy(String var1, TransactionController var2) throws StandardException;

   PasswordHasher makePasswordHasher(Dictionary var1) throws StandardException;

   SchemaDescriptor getSystemSchemaDescriptor() throws StandardException;

   SchemaDescriptor getSysIBMSchemaDescriptor() throws StandardException;

   SchemaDescriptor getDeclaredGlobalTemporaryTablesSchemaDescriptor() throws StandardException;

   boolean isSystemSchemaName(String var1) throws StandardException;

   void dropRoleGrant(String var1, String var2, String var3, TransactionController var4) throws StandardException;

   void dropRoleGrantsByGrantee(String var1, TransactionController var2) throws StandardException;

   void dropRoleGrantsByName(String var1, TransactionController var2) throws StandardException;

   RoleClosureIterator createRoleClosureIterator(TransactionController var1, String var2, boolean var3) throws StandardException;

   void dropAllPermsByGrantee(String var1, TransactionController var2) throws StandardException;

   void dropSchemaDescriptor(String var1, TransactionController var2) throws StandardException;

   boolean isSchemaEmpty(SchemaDescriptor var1) throws StandardException;

   TableDescriptor getTableDescriptor(String var1, SchemaDescriptor var2, TransactionController var3) throws StandardException;

   TableDescriptor getTableDescriptor(UUID var1) throws StandardException;

   void dropTableDescriptor(TableDescriptor var1, SchemaDescriptor var2, TransactionController var3) throws StandardException;

   void updateLockGranularity(TableDescriptor var1, SchemaDescriptor var2, char var3, TransactionController var4) throws StandardException;

   ColumnDescriptor getColumnDescriptorByDefaultId(UUID var1) throws StandardException;

   void dropColumnDescriptor(UUID var1, String var2, TransactionController var3) throws StandardException;

   void dropAllColumnDescriptors(UUID var1, TransactionController var2) throws StandardException;

   void dropAllTableAndColPermDescriptors(UUID var1, TransactionController var2) throws StandardException;

   void updateSYSCOLPERMSforAddColumnToUserTable(UUID var1, TransactionController var2) throws StandardException;

   void updateSYSCOLPERMSforDropColumn(UUID var1, TransactionController var2, ColumnDescriptor var3) throws StandardException;

   void dropAllRoutinePermDescriptors(UUID var1, TransactionController var2) throws StandardException;

   ViewDescriptor getViewDescriptor(UUID var1) throws StandardException;

   ViewDescriptor getViewDescriptor(TableDescriptor var1) throws StandardException;

   void dropViewDescriptor(ViewDescriptor var1, TransactionController var2) throws StandardException;

   ConstraintDescriptor getConstraintDescriptor(UUID var1) throws StandardException;

   ConstraintDescriptor getConstraintDescriptor(String var1, UUID var2) throws StandardException;

   ConstraintDescriptorList getConstraintDescriptors(TableDescriptor var1) throws StandardException;

   ConstraintDescriptorList getActiveConstraintDescriptors(ConstraintDescriptorList var1) throws StandardException;

   boolean activeConstraint(ConstraintDescriptor var1) throws StandardException;

   ConstraintDescriptor getConstraintDescriptor(TableDescriptor var1, UUID var2) throws StandardException;

   ConstraintDescriptor getConstraintDescriptorById(TableDescriptor var1, UUID var2) throws StandardException;

   ConstraintDescriptor getConstraintDescriptorByName(TableDescriptor var1, SchemaDescriptor var2, String var3, boolean var4) throws StandardException;

   TableDescriptor getConstraintTableDescriptor(UUID var1) throws StandardException;

   ConstraintDescriptorList getForeignKeys(UUID var1) throws StandardException;

   void addConstraintDescriptor(ConstraintDescriptor var1, TransactionController var2) throws StandardException;

   void dropConstraintDescriptor(ConstraintDescriptor var1, TransactionController var2) throws StandardException;

   void dropAllConstraintDescriptors(TableDescriptor var1, TransactionController var2) throws StandardException;

   void updateConstraintDescriptor(ConstraintDescriptor var1, UUID var2, int[] var3, TransactionController var4) throws StandardException;

   SubKeyConstraintDescriptor getSubKeyConstraint(UUID var1, int var2) throws StandardException;

   SPSDescriptor getSPSDescriptor(UUID var1) throws StandardException;

   SPSDescriptor getSPSDescriptor(String var1, SchemaDescriptor var2) throws StandardException;

   List getAllSPSDescriptors() throws StandardException;

   DataTypeDescriptor[] getSPSParams(SPSDescriptor var1, List var2) throws StandardException;

   void addSPSDescriptor(SPSDescriptor var1, TransactionController var2) throws StandardException;

   void updateSPS(SPSDescriptor var1, TransactionController var2, boolean var3) throws StandardException;

   void dropSPSDescriptor(SPSDescriptor var1, TransactionController var2) throws StandardException;

   void dropSPSDescriptor(UUID var1, TransactionController var2) throws StandardException;

   void invalidateAllSPSPlans(LanguageConnectionContext var1) throws StandardException;

   void invalidateAllSPSPlans() throws StandardException;

   TriggerDescriptor getTriggerDescriptor(UUID var1) throws StandardException;

   TriggerDescriptor getTriggerDescriptor(String var1, SchemaDescriptor var2) throws StandardException;

   int[] examineTriggerNodeAndCols(Visitable var1, String var2, String var3, String var4, int[] var5, int[] var6, int var7, TableDescriptor var8, int var9, boolean var10, List var11) throws StandardException;

   String getTriggerActionString(Visitable var1, String var2, String var3, String var4, int[] var5, int[] var6, int var7, TableDescriptor var8, int var9, boolean var10, List var11, int[] var12) throws StandardException;

   TriggerDescriptorList getTriggerDescriptors(TableDescriptor var1) throws StandardException;

   void updateTriggerDescriptor(TriggerDescriptor var1, UUID var2, int[] var3, TransactionController var4) throws StandardException;

   void dropTriggerDescriptor(TriggerDescriptor var1, TransactionController var2) throws StandardException;

   Hashtable hashAllConglomerateDescriptorsByNumber(TransactionController var1) throws StandardException;

   Hashtable hashAllTableDescriptorsByTableId(TransactionController var1) throws StandardException;

   ConglomerateDescriptor getConglomerateDescriptor(UUID var1) throws StandardException;

   ConglomerateDescriptor[] getConglomerateDescriptors(UUID var1) throws StandardException;

   ConglomerateDescriptor getConglomerateDescriptor(long var1) throws StandardException;

   ConglomerateDescriptor[] getConglomerateDescriptors(long var1) throws StandardException;

   ConglomerateDescriptor getConglomerateDescriptor(String var1, SchemaDescriptor var2, boolean var3) throws StandardException;

   void dropConglomerateDescriptor(ConglomerateDescriptor var1, TransactionController var2) throws StandardException;

   void dropAllConglomerateDescriptors(TableDescriptor var1, TransactionController var2) throws StandardException;

   void updateConglomerateDescriptor(ConglomerateDescriptor[] var1, long var2, TransactionController var4) throws StandardException;

   void updateConglomerateDescriptor(ConglomerateDescriptor var1, long var2, TransactionController var4) throws StandardException;

   List getDependentsDescriptorList(String var1) throws StandardException;

   List getProvidersDescriptorList(String var1) throws StandardException;

   List getAllDependencyDescriptorsList() throws StandardException;

   void dropStoredDependency(DependencyDescriptor var1, TransactionController var2) throws StandardException;

   void dropDependentsStoredDependencies(UUID var1, TransactionController var2) throws StandardException;

   UUIDFactory getUUIDFactory();

   AliasDescriptor getAliasDescriptorForUDT(TransactionController var1, DataTypeDescriptor var2) throws StandardException;

   AliasDescriptor getAliasDescriptor(UUID var1) throws StandardException;

   AliasDescriptor getAliasDescriptor(String var1, String var2, char var3) throws StandardException;

   List getRoutineList(String var1, String var2, char var3) throws StandardException;

   void dropAliasDescriptor(AliasDescriptor var1, TransactionController var2) throws StandardException;

   void updateUser(UserDescriptor var1, TransactionController var2) throws StandardException;

   UserDescriptor getUser(String var1) throws StandardException;

   void dropUser(String var1, TransactionController var2) throws StandardException;

   int getEngineType();

   FileInfoDescriptor getFileInfoDescriptor(UUID var1) throws StandardException;

   FileInfoDescriptor getFileInfoDescriptor(SchemaDescriptor var1, String var2) throws StandardException;

   void dropFileInfoDescriptor(FileInfoDescriptor var1) throws StandardException;

   RowLocation[] computeAutoincRowLocations(TransactionController var1, TableDescriptor var2) throws StandardException;

   RowLocation getRowLocationTemplate(LanguageConnectionContext var1, TableDescriptor var2) throws StandardException;

   NumberDataValue getSetAutoincrementValue(RowLocation var1, TransactionController var2, boolean var3, NumberDataValue var4, boolean var5) throws StandardException;

   void setAutoincrementValue(TransactionController var1, UUID var2, String var3, long var4, boolean var6) throws StandardException;

   void computeSequenceRowLocation(TransactionController var1, String var2, RowLocation[] var3, SequenceDescriptor[] var4) throws StandardException;

   boolean updateCurrentSequenceValue(TransactionController var1, RowLocation var2, boolean var3, Long var4, Long var5) throws StandardException;

   void getCurrentValueAndAdvance(String var1, NumberDataValue var2) throws StandardException;

   Long peekAtIdentity(String var1, String var2) throws StandardException;

   Long peekAtSequence(String var1, String var2) throws StandardException;

   List getStatisticsDescriptors(TableDescriptor var1) throws StandardException;

   void dropStatisticsDescriptors(UUID var1, UUID var2, TransactionController var3) throws StandardException;

   DependencyManager getDependencyManager();

   int getCacheMode();

   String getSystemSQLName();

   void addDescriptor(TupleDescriptor var1, TupleDescriptor var2, int var3, boolean var4, TransactionController var5) throws StandardException;

   void addDescriptorArray(TupleDescriptor[] var1, TupleDescriptor var2, int var3, boolean var4, TransactionController var5) throws StandardException;

   boolean checkVersion(int var1, String var2) throws StandardException;

   boolean isReadOnlyUpgrade();

   boolean addRemovePermissionsDescriptor(boolean var1, PermissionsDescriptor var2, String var3, TransactionController var4) throws StandardException;

   TablePermsDescriptor getTablePermissions(UUID var1, String var2) throws StandardException;

   TablePermsDescriptor getTablePermissions(UUID var1) throws StandardException;

   ColPermsDescriptor getColumnPermissions(UUID var1, int var2, boolean var3, String var4) throws StandardException;

   ColPermsDescriptor getColumnPermissions(UUID var1, String var2, boolean var3, String var4) throws StandardException;

   ColPermsDescriptor getColumnPermissions(UUID var1) throws StandardException;

   RoutinePermsDescriptor getRoutinePermissions(UUID var1, String var2) throws StandardException;

   RoutinePermsDescriptor getRoutinePermissions(UUID var1) throws StandardException;

   String getVTIClass(TableDescriptor var1, boolean var2) throws StandardException;

   String getBuiltinVTIClass(TableDescriptor var1, boolean var2) throws StandardException;

   RoleGrantDescriptor getRoleDefinitionDescriptor(String var1) throws StandardException;

   RoleGrantDescriptor getRoleGrantDescriptor(UUID var1) throws StandardException;

   RoleGrantDescriptor getRoleGrantDescriptor(String var1, String var2, String var3) throws StandardException;

   void dropDependentsStoredDependencies(UUID var1, TransactionController var2, boolean var3) throws StandardException;

   boolean existsGrantToAuthid(String var1, TransactionController var2) throws StandardException;

   void updateMetadataSPSes(TransactionController var1) throws StandardException;

   void dropSequenceDescriptor(SequenceDescriptor var1, TransactionController var2) throws StandardException;

   SequenceDescriptor getSequenceDescriptor(UUID var1) throws StandardException;

   SequenceDescriptor getSequenceDescriptor(SchemaDescriptor var1, String var2) throws StandardException;

   PermDescriptor getGenericPermissions(UUID var1, String var2, String var3, String var4) throws StandardException;

   PermDescriptor getGenericPermissions(UUID var1) throws StandardException;

   void dropAllPermDescriptors(UUID var1, TransactionController var2) throws StandardException;

   boolean doCreateIndexStatsRefresher();

   void createIndexStatsRefresher(Database var1, String var2);

   IndexStatisticsDaemon getIndexStatsRefresher(boolean var1);

   void disableIndexStatsRefresher();

   DependableFinder getDependableFinder(int var1);

   DependableFinder getColumnDependableFinder(int var1, byte[] var2);

   BulkInsertCounter getBulkInsertCounter(String var1, boolean var2) throws StandardException;

   void flushBulkInsertCounter(String var1, BulkInsertCounter var2) throws StandardException;
}
