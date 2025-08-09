package org.apache.hive.beeline;

import com.google.common.collect.ImmutableMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.HiveMetaException;
import org.apache.hadoop.hive.metastore.MetaStoreSchemaInfo;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveSchemaTool {
   private String userName;
   private String passWord;
   private boolean dryRun;
   private boolean verbose;
   private String dbOpts;
   private URI[] validationServers;
   private final HiveConf hiveConf;
   private final String dbType;
   private final MetaStoreSchemaInfo metaStoreSchemaInfo;
   private static final Logger LOG = LoggerFactory.getLogger(HiveSchemaTool.class.getName());

   public HiveSchemaTool(String dbType) throws HiveMetaException {
      this(System.getenv("HIVE_HOME"), new HiveConf(HiveSchemaTool.class), dbType);
   }

   public HiveSchemaTool(String hiveHome, HiveConf hiveConf, String dbType) throws HiveMetaException {
      this.userName = null;
      this.passWord = null;
      this.dryRun = false;
      this.verbose = false;
      this.dbOpts = null;
      this.validationServers = null;
      if (hiveHome != null && !hiveHome.isEmpty()) {
         this.hiveConf = hiveConf;
         this.dbType = dbType;
         this.metaStoreSchemaInfo = new MetaStoreSchemaInfo(hiveHome, dbType);
         this.userName = hiveConf.get(ConfVars.METASTORE_CONNECTION_USER_NAME.varname);

         try {
            this.passWord = ShimLoader.getHadoopShims().getPassword(hiveConf, ConfVars.METASTOREPWD.varname);
         } catch (IOException err) {
            throw new HiveMetaException("Error getting metastore password", err);
         }
      } else {
         throw new HiveMetaException("No Hive home directory provided");
      }
   }

   public HiveConf getHiveConf() {
      return this.hiveConf;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public void setPassWord(String passWord) {
      this.passWord = passWord;
   }

   public void setDryRun(boolean dryRun) {
      this.dryRun = dryRun;
   }

   public void setVerbose(boolean verbose) {
      this.verbose = verbose;
   }

   public void setDbOpts(String dbOpts) {
      this.dbOpts = dbOpts;
   }

   public void setValidationServers(String servers) {
      if (StringUtils.isNotEmpty(servers)) {
         String[] strServers = servers.split(",");
         this.validationServers = new URI[strServers.length];

         for(int i = 0; i < this.validationServers.length; ++i) {
            this.validationServers[i] = (new Path(strServers[i])).toUri();
         }
      }

   }

   private static void printAndExit(Options cmdLineOptions) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("schemaTool", cmdLineOptions);
      System.exit(1);
   }

   Connection getConnectionToMetastore(boolean printInfo) throws HiveMetaException {
      return HiveSchemaHelper.getConnectionToMetastore(this.userName, this.passWord, printInfo, this.hiveConf);
   }

   private HiveSchemaHelper.NestedScriptParser getDbCommandParser(String dbType) {
      return HiveSchemaHelper.getDbCommandParser(dbType, this.dbOpts, this.userName, this.passWord, this.hiveConf);
   }

   public void showInfo() throws HiveMetaException {
      Connection metastoreConn = this.getConnectionToMetastore(true);
      String hiveVersion = MetaStoreSchemaInfo.getHiveSchemaVersion();
      String dbVersion = this.getMetaStoreSchemaVersion(metastoreConn);
      System.out.println("Hive distribution version:\t " + hiveVersion);
      System.out.println("Metastore schema version:\t " + dbVersion);
      this.assertCompatibleVersion(hiveVersion, dbVersion);
   }

   private String getMetaStoreSchemaVersion(Connection metastoreConn) throws HiveMetaException {
      return this.getMetaStoreSchemaVersion(metastoreConn, false);
   }

   private String getMetaStoreSchemaVersion(Connection metastoreConn, boolean checkDuplicatedVersion) throws HiveMetaException {
      String versionQuery;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         versionQuery = "select t.\"SCHEMA_VERSION\" from \"VERSION\" t";
      } else {
         versionQuery = "select t.SCHEMA_VERSION from VERSION t";
      }

      try {
         Statement stmt = metastoreConn.createStatement();
         Throwable var5 = null;

         String var9;
         try {
            ResultSet res = stmt.executeQuery(versionQuery);
            Throwable var7 = null;

            try {
               if (!res.next()) {
                  throw new HiveMetaException("Could not find version info in metastore VERSION table");
               }

               String currentSchemaVersion = res.getString(1);
               if (checkDuplicatedVersion && res.next()) {
                  throw new HiveMetaException("Multiple versions were found in metastore.");
               }

               var9 = currentSchemaVersion;
            } catch (Throwable var34) {
               var7 = var34;
               throw var34;
            } finally {
               if (res != null) {
                  if (var7 != null) {
                     try {
                        res.close();
                     } catch (Throwable var33) {
                        var7.addSuppressed(var33);
                     }
                  } else {
                     res.close();
                  }
               }

            }
         } catch (Throwable var36) {
            var5 = var36;
            throw var36;
         } finally {
            if (stmt != null) {
               if (var5 != null) {
                  try {
                     stmt.close();
                  } catch (Throwable var32) {
                     var5.addSuppressed(var32);
                  }
               } else {
                  stmt.close();
               }
            }

         }

         return var9;
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get schema version, Cause:" + e.getMessage());
      }
   }

   boolean validateLocations(Connection conn, URI[] defaultServers) throws HiveMetaException {
      System.out.println("Validating database/table/partition locations");
      boolean rtn = this.checkMetaStoreDBLocation(conn, defaultServers);
      rtn = this.checkMetaStoreTableLocation(conn, defaultServers) && rtn;
      rtn = this.checkMetaStorePartitionLocation(conn, defaultServers) && rtn;
      rtn = this.checkMetaStoreSkewedColumnsLocation(conn, defaultServers) && rtn;
      System.out.println((rtn ? "Succeeded" : "Failed") + " in database/table/partition location validation");
      return rtn;
   }

   private String getNameOrID(ResultSet res, int nameInx, int idInx) throws SQLException {
      String itemName = res.getString(nameInx);
      return itemName != null && !itemName.isEmpty() ? "Name: " + itemName : "ID: " + res.getString(idInx);
   }

   private boolean checkMetaStoreDBLocation(Connection conn, URI[] defaultServers) throws HiveMetaException {
      boolean isValid = true;
      int numOfInvalid = 0;
      String dbLoc;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         dbLoc = "select dbt.\"DB_ID\", dbt.\"NAME\", dbt.\"DB_LOCATION_URI\" from \"DBS\" dbt";
      } else {
         dbLoc = "select dbt.DB_ID, dbt.NAME, dbt.DB_LOCATION_URI from DBS dbt";
      }

      try {
         Statement stmt = conn.createStatement();
         Throwable var7 = null;

         try {
            ResultSet res = stmt.executeQuery(dbLoc);
            Throwable var9 = null;

            try {
               while(res.next()) {
                  String locValue = res.getString(3);
                  String dbName = this.getNameOrID(res, 2, 1);
                  if (!this.checkLocation("Database " + dbName, locValue, defaultServers)) {
                     ++numOfInvalid;
                  }
               }
            } catch (Throwable var35) {
               var9 = var35;
               throw var35;
            } finally {
               if (res != null) {
                  if (var9 != null) {
                     try {
                        res.close();
                     } catch (Throwable var34) {
                        var9.addSuppressed(var34);
                     }
                  } else {
                     res.close();
                  }
               }

            }
         } catch (Throwable var37) {
            var7 = var37;
            throw var37;
         } finally {
            if (stmt != null) {
               if (var7 != null) {
                  try {
                     stmt.close();
                  } catch (Throwable var33) {
                     var7.addSuppressed(var33);
                  }
               } else {
                  stmt.close();
               }
            }

         }
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get DB Location Info.", e);
      }

      if (numOfInvalid > 0) {
         isValid = false;
         System.err.println("Total number of invalid DB locations is: " + numOfInvalid);
      }

      return isValid;
   }

   private boolean checkMetaStoreTableLocation(Connection conn, URI[] defaultServers) throws HiveMetaException {
      boolean isValid = true;
      int numOfInvalid = 0;
      String tabIDRange;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         tabIDRange = "select max(\"TBL_ID\"), min(\"TBL_ID\") from \"TBLS\" ";
      } else {
         tabIDRange = "select max(TBL_ID), min(TBL_ID) from TBLS";
      }

      String tabLoc;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         tabLoc = "select tbl.\"TBL_ID\", tbl.\"TBL_NAME\", sd.\"LOCATION\", dbt.\"DB_ID\", dbt.\"NAME\" from \"TBLS\" tbl inner join \"SDS\" sd on tbl.\"SD_ID\" = sd.\"SD_ID\" and tbl.\"TBL_TYPE\" != '" + TableType.VIRTUAL_VIEW + "' and tbl.\"TBL_ID\" >= ? and tbl.\"TBL_ID\"<= ? inner join \"DBS\" dbt on tbl.\"DB_ID\" = dbt.\"DB_ID\" ";
      } else {
         tabLoc = "select tbl.TBL_ID, tbl.TBL_NAME, sd.LOCATION, dbt.DB_ID, dbt.NAME from TBLS tbl join SDS sd on tbl.SD_ID = sd.SD_ID and tbl.TBL_TYPE !='" + TableType.VIRTUAL_VIEW + "' and tbl.TBL_ID >= ? and tbl.TBL_ID <= ?  inner join DBS dbt on tbl.DB_ID = dbt.DB_ID";
      }

      long maxID = 0L;
      long minID = 0L;
      long rtnSize = 2000L;

      try {
         Statement stmt = conn.createStatement();
         ResultSet res = stmt.executeQuery(tabIDRange);
         if (res.next()) {
            maxID = res.getLong(1);
            minID = res.getLong(2);
         }

         res.close();
         stmt.close();

         PreparedStatement pStmt;
         for(pStmt = conn.prepareStatement(tabLoc); minID <= maxID; minID += rtnSize + 1L) {
            pStmt.setLong(1, minID);
            pStmt.setLong(2, minID + rtnSize);
            res = pStmt.executeQuery();

            while(res.next()) {
               String locValue = res.getString(3);
               String entity = "Database " + this.getNameOrID(res, 5, 4) + ", Table " + this.getNameOrID(res, 2, 1);
               if (!this.checkLocation(entity, locValue, defaultServers)) {
                  ++numOfInvalid;
               }
            }

            res.close();
         }

         pStmt.close();
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get Table Location Info.", e);
      }

      if (numOfInvalid > 0) {
         isValid = false;
         System.err.println("Total number of invalid TABLE locations is: " + numOfInvalid);
      }

      return isValid;
   }

   private boolean checkMetaStorePartitionLocation(Connection conn, URI[] defaultServers) throws HiveMetaException {
      boolean isValid = true;
      int numOfInvalid = 0;
      String partIDRange;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         partIDRange = "select max(\"PART_ID\"), min(\"PART_ID\") from \"PARTITIONS\" ";
      } else {
         partIDRange = "select max(PART_ID), min(PART_ID) from PARTITIONS";
      }

      String partLoc;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         partLoc = "select pt.\"PART_ID\", pt.\"PART_NAME\", sd.\"LOCATION\", tbl.\"TBL_ID\", tbl.\"TBL_NAME\",dbt.\"DB_ID\", dbt.\"NAME\" from \"PARTITIONS\" pt inner join \"SDS\" sd on pt.\"SD_ID\" = sd.\"SD_ID\" and pt.\"PART_ID\" >= ? and pt.\"PART_ID\"<= ?  inner join \"TBLS\" tbl on pt.\"TBL_ID\" = tbl.\"TBL_ID\" inner join \"DBS\" dbt on tbl.\"DB_ID\" = dbt.\"DB_ID\" ";
      } else {
         partLoc = "select pt.PART_ID, pt.PART_NAME, sd.LOCATION, tbl.TBL_ID, tbl.TBL_NAME, dbt.DB_ID, dbt.NAME from PARTITIONS pt inner join SDS sd on pt.SD_ID = sd.SD_ID and pt.PART_ID >= ? and pt.PART_ID <= ?  inner join TBLS tbl on tbl.TBL_ID = pt.TBL_ID inner join DBS dbt on tbl.DB_ID = dbt.DB_ID ";
      }

      long maxID = 0L;
      long minID = 0L;
      long rtnSize = 2000L;

      try {
         Statement stmt = conn.createStatement();
         ResultSet res = stmt.executeQuery(partIDRange);
         if (res.next()) {
            maxID = res.getLong(1);
            minID = res.getLong(2);
         }

         res.close();
         stmt.close();

         PreparedStatement pStmt;
         for(pStmt = conn.prepareStatement(partLoc); minID <= maxID; minID += rtnSize + 1L) {
            pStmt.setLong(1, minID);
            pStmt.setLong(2, minID + rtnSize);
            res = pStmt.executeQuery();

            while(res.next()) {
               String locValue = res.getString(3);
               String entity = "Database " + this.getNameOrID(res, 7, 6) + ", Table " + this.getNameOrID(res, 5, 4) + ", Partition " + this.getNameOrID(res, 2, 1);
               if (!this.checkLocation(entity, locValue, defaultServers)) {
                  ++numOfInvalid;
               }
            }

            res.close();
         }

         pStmt.close();
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get Partiton Location Info.", e);
      }

      if (numOfInvalid > 0) {
         isValid = false;
         System.err.println("Total number of invalid PARTITION locations is: " + numOfInvalid);
      }

      return isValid;
   }

   private boolean checkMetaStoreSkewedColumnsLocation(Connection conn, URI[] defaultServers) throws HiveMetaException {
      boolean isValid = true;
      int numOfInvalid = 0;
      String skewedColIDRange;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         skewedColIDRange = "select max(\"STRING_LIST_ID_KID\"), min(\"STRING_LIST_ID_KID\") from \"SKEWED_COL_VALUE_LOC_MAP\" ";
      } else {
         skewedColIDRange = "select max(STRING_LIST_ID_KID), min(STRING_LIST_ID_KID) from SKEWED_COL_VALUE_LOC_MAP";
      }

      String skewedColLoc;
      if (this.getDbCommandParser(this.dbType).needsQuotedIdentifier()) {
         skewedColLoc = "select t.\"TBL_NAME\", t.\"TBL_ID\", sk.\"STRING_LIST_ID_KID\", sk.\"LOCATION\" from \"TBLS\" t, \"SDS\" s, \"SKEWED_COL_VALUE_LOC_MAP\" sk where sk.\"SD_ID\" = s.\"SD_ID\" and s.\"SD_ID\" = t.\"SD_ID\" and sk.\"STRING_LIST_ID_KID\" >= ? and sk.\"STRING_LIST_ID_KID\" <= ? ";
      } else {
         skewedColLoc = "select t.TBL_NAME, t.TBL_ID, sk.STRING_LIST_ID_KID, sk.LOCATION from TBLS t, SDS s, SKEWED_COL_VALUE_LOC_MAP sk where sk.SD_ID = s.SD_ID and s.SD_ID = t.SD_ID and sk.STRING_LIST_ID_KID >= ? and sk.STRING_LIST_ID_KID <= ? ";
      }

      long maxID = 0L;
      long minID = 0L;
      long rtnSize = 2000L;

      try {
         Statement stmt = conn.createStatement();
         ResultSet res = stmt.executeQuery(skewedColIDRange);
         if (res.next()) {
            maxID = res.getLong(1);
            minID = res.getLong(2);
         }

         res.close();
         stmt.close();

         PreparedStatement pStmt;
         for(pStmt = conn.prepareStatement(skewedColLoc); minID <= maxID; minID += rtnSize + 1L) {
            pStmt.setLong(1, minID);
            pStmt.setLong(2, minID + rtnSize);
            res = pStmt.executeQuery();

            while(res.next()) {
               String locValue = res.getString(4);
               String entity = "Table " + this.getNameOrID(res, 1, 2) + ", String list " + res.getString(3);
               if (!this.checkLocation(entity, locValue, defaultServers)) {
                  ++numOfInvalid;
               }
            }

            res.close();
         }

         pStmt.close();
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get skewed columns location info.", e);
      }

      if (numOfInvalid > 0) {
         isValid = false;
         System.err.println("Total number of invalid SKEWED_COL_VALUE_LOC_MAP locations is: " + numOfInvalid);
      }

      return isValid;
   }

   private boolean checkLocation(String entity, String entityLocation, URI[] defaultServers) {
      boolean isValid = true;
      if (entityLocation == null) {
         System.err.println(entity + ", error: empty location");
         isValid = false;
      } else {
         try {
            URI currentUri = (new Path(entityLocation)).toUri();
            String scheme = currentUri.getScheme();
            if (StringUtils.isEmpty(scheme)) {
               System.err.println(entity + ", location: " + entityLocation + ", error: missing location scheme");
               isValid = false;
            } else if (ArrayUtils.isNotEmpty(defaultServers) && currentUri.getAuthority() != null) {
               String authority = currentUri.getAuthority();
               boolean matchServer = false;

               for(URI server : defaultServers) {
                  if (StringUtils.equalsIgnoreCase(server.getScheme(), scheme) && StringUtils.equalsIgnoreCase(server.getAuthority(), authority)) {
                     matchServer = true;
                     break;
                  }
               }

               if (!matchServer) {
                  System.err.println(entity + ", location: " + entityLocation + ", error: mismatched server");
                  isValid = false;
               }
            }
         } catch (Exception pe) {
            System.err.println(entity + ", error: invalid location " + pe.getMessage());
            isValid = false;
         }
      }

      return isValid;
   }

   private void testConnectionToMetastore() throws HiveMetaException {
      Connection conn = this.getConnectionToMetastore(true);

      try {
         conn.close();
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to close metastore connection", e);
      }
   }

   public void verifySchemaVersion() throws HiveMetaException {
      if (!this.dryRun) {
         String newSchemaVersion = this.getMetaStoreSchemaVersion(this.getConnectionToMetastore(false));
         this.assertCompatibleVersion(MetaStoreSchemaInfo.getHiveSchemaVersion(), newSchemaVersion);
      }
   }

   private void assertCompatibleVersion(String hiveSchemaVersion, String dbSchemaVersion) throws HiveMetaException {
      if (!MetaStoreSchemaInfo.isVersionCompatible(hiveSchemaVersion, dbSchemaVersion)) {
         throw new HiveMetaException("Metastore schema version is not compatible. Hive Version: " + hiveSchemaVersion + ", Database Schema Version: " + dbSchemaVersion);
      }
   }

   public void doUpgrade() throws HiveMetaException {
      String fromVersion = this.getMetaStoreSchemaVersion(this.getConnectionToMetastore(false));
      if (fromVersion != null && !fromVersion.isEmpty()) {
         this.doUpgrade(fromVersion);
      } else {
         throw new HiveMetaException("Schema version not stored in the metastore. Metastore schema is too old or corrupt. Try specifying the version manually");
      }
   }

   public void doUpgrade(String fromSchemaVer) throws HiveMetaException {
      if (MetaStoreSchemaInfo.getHiveSchemaVersion().equals(fromSchemaVer)) {
         System.out.println("No schema upgrade required from version " + fromSchemaVer);
      } else {
         List<String> upgradeScripts = this.metaStoreSchemaInfo.getUpgradeScripts(fromSchemaVer);
         this.testConnectionToMetastore();
         System.out.println("Starting upgrade metastore schema from version " + fromSchemaVer + " to " + MetaStoreSchemaInfo.getHiveSchemaVersion());
         String scriptDir = this.metaStoreSchemaInfo.getMetaStoreScriptDir();

         try {
            for(String scriptFile : upgradeScripts) {
               System.out.println("Upgrade script " + scriptFile);
               if (!this.dryRun) {
                  this.runPreUpgrade(scriptDir, scriptFile);
                  this.runBeeLine(scriptDir, scriptFile);
                  System.out.println("Completed " + scriptFile);
               }
            }
         } catch (IOException eIO) {
            throw new HiveMetaException("Upgrade FAILED! Metastore state would be inconsistent !!", eIO);
         }

         this.verifySchemaVersion();
      }
   }

   public void doInit() throws HiveMetaException {
      this.doInit(MetaStoreSchemaInfo.getHiveSchemaVersion());
      this.verifySchemaVersion();
   }

   public void doInit(String toVersion) throws HiveMetaException {
      this.testConnectionToMetastore();
      System.out.println("Starting metastore schema initialization to " + toVersion);
      String initScriptDir = this.metaStoreSchemaInfo.getMetaStoreScriptDir();
      String initScriptFile = this.metaStoreSchemaInfo.generateInitFileName(toVersion);

      try {
         System.out.println("Initialization script " + initScriptFile);
         if (!this.dryRun) {
            this.runBeeLine(initScriptDir, initScriptFile);
            System.out.println("Initialization script completed");
         }

      } catch (IOException e) {
         throw new HiveMetaException("Schema initialization FAILED! Metastore state would be inconsistent !!", e);
      }
   }

   public void doValidate() throws HiveMetaException {
      System.out.println("Starting metastore validation\n");
      Connection conn = this.getConnectionToMetastore(false);
      boolean success = true;

      try {
         if (this.validateSchemaVersions(conn)) {
            System.out.println("[SUCCESS]\n");
         } else {
            success = false;
            System.out.println("[FAIL]\n");
         }

         if (this.validateSequences(conn)) {
            System.out.println("[SUCCESS]\n");
         } else {
            success = false;
            System.out.println("[FAIL]\n");
         }

         if (this.validateSchemaTables(conn)) {
            System.out.println("[SUCCESS]\n");
         } else {
            success = false;
            System.out.println("[FAIL]\n");
         }

         if (this.validateLocations(conn, this.validationServers)) {
            System.out.println("[SUCCESS]\n");
         } else {
            success = false;
            System.out.println("[FAIL]\n");
         }

         if (this.validateColumnNullValues(conn)) {
            System.out.println("[SUCCESS]\n");
         } else {
            success = false;
            System.out.println("[FAIL]\n");
         }
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
               throw new HiveMetaException("Failed to close metastore connection", e);
            }
         }

      }

      System.out.print("Done with metastore validation: ");
      if (!success) {
         System.out.println("[FAIL]");
         System.exit(1);
      } else {
         System.out.println("[SUCCESS]");
      }

   }

   boolean validateSequences(Connection conn) throws HiveMetaException {
      Map<String, Pair<String, String>> seqNameToTable = (new ImmutableMap.Builder()).put("MDatabase", Pair.of("DBS", "DB_ID")).put("MRole", Pair.of("ROLES", "ROLE_ID")).put("MGlobalPrivilege", Pair.of("GLOBAL_PRIVS", "USER_GRANT_ID")).put("MTable", Pair.of("TBLS", "TBL_ID")).put("MStorageDescriptor", Pair.of("SDS", "SD_ID")).put("MSerDeInfo", Pair.of("SERDES", "SERDE_ID")).put("MColumnDescriptor", Pair.of("CDS", "CD_ID")).put("MTablePrivilege", Pair.of("TBL_PRIVS", "TBL_GRANT_ID")).put("MTableColumnStatistics", Pair.of("TAB_COL_STATS", "CS_ID")).put("MPartition", Pair.of("PARTITIONS", "PART_ID")).put("MPartitionColumnStatistics", Pair.of("PART_COL_STATS", "CS_ID")).put("MFunction", Pair.of("FUNCS", "FUNC_ID")).put("MIndex", Pair.of("IDXS", "INDEX_ID")).put("MStringList", Pair.of("SKEWED_STRING_LIST", "STRING_LIST_ID")).build();
      System.out.println("Validating sequence number for SEQUENCE_TABLE");
      boolean isValid = true;

      try {
         Statement stmt = conn.createStatement();

         for(String seqName : seqNameToTable.keySet()) {
            String tableName = (String)((Pair)seqNameToTable.get(seqName)).getLeft();
            String tableKey = (String)((Pair)seqNameToTable.get(seqName)).getRight();
            String seqQuery = this.getDbCommandParser(this.dbType).needsQuotedIdentifier() ? "select t.\"NEXT_VAL\" from \"SEQUENCE_TABLE\" t WHERE t.\"SEQUENCE_NAME\"='org.apache.hadoop.hive.metastore.model." + seqName + "'" : "select t.NEXT_VAL from SEQUENCE_TABLE t WHERE t.SEQUENCE_NAME='org.apache.hadoop.hive.metastore.model." + seqName + "'";
            String maxIdQuery = this.getDbCommandParser(this.dbType).needsQuotedIdentifier() ? "select max(\"" + tableKey + "\") from \"" + tableName + "\"" : "select max(" + tableKey + ") from " + tableName;
            ResultSet res = stmt.executeQuery(maxIdQuery);
            if (res.next()) {
               long maxId = res.getLong(1);
               if (maxId > 0L) {
                  ResultSet resSeq = stmt.executeQuery(seqQuery);
                  if (!resSeq.next()) {
                     isValid = false;
                     System.err.println("Missing SEQUENCE_NAME " + seqName + " from SEQUENCE_TABLE");
                  } else if (resSeq.getLong(1) < maxId) {
                     isValid = false;
                     System.err.println("NEXT_VAL for " + seqName + " in SEQUENCE_TABLE < max(" + tableKey + ") in " + tableName);
                  }
               }
            }
         }

         System.out.println((isValid ? "Succeeded" : "Failed") + " in sequence number validation for SEQUENCE_TABLE");
         return isValid;
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to validate sequence number for SEQUENCE_TABLE", e);
      }
   }

   boolean validateSchemaVersions(Connection conn) throws HiveMetaException {
      System.out.println("Validating schema version");

      try {
         String newSchemaVersion = this.getMetaStoreSchemaVersion(conn, true);
         this.assertCompatibleVersion(MetaStoreSchemaInfo.getHiveSchemaVersion(), newSchemaVersion);
      } catch (HiveMetaException var3) {
         if (!var3.getMessage().contains("Metastore schema version is not compatible") && !var3.getMessage().contains("Multiple versions were found in metastore") && !var3.getMessage().contains("Could not find version info in metastore VERSION table")) {
            throw var3;
         }

         System.out.println("Failed in schema version validation: " + var3.getMessage());
         return false;
      }

      System.out.println("Succeeded in schema version validation.");
      return true;
   }

   boolean validateSchemaTables(Connection conn) throws HiveMetaException {
      String version = null;
      ResultSet rs = null;
      DatabaseMetaData metadata = null;
      List<String> dbTables = new ArrayList();
      List<String> schemaTables = new ArrayList();
      List<String> subScripts = new ArrayList();
      Connection hmsConn = this.getConnectionToMetastore(false);
      System.out.println("Validating metastore schema tables");

      try {
         version = this.getMetaStoreSchemaVersion(hmsConn);
      } catch (HiveMetaException he) {
         System.err.println("Failed to determine schema version from Hive Metastore DB," + he.getMessage());
         LOG.debug("Failed to determine schema version from Hive Metastore DB," + he.getMessage());
         return false;
      }

      hmsConn = this.getConnectionToMetastore(false);
      LOG.debug("Validating tables in the schema for version " + version);

      try {
         metadata = conn.getMetaData();
         String[] types = new String[]{"TABLE"};
         rs = metadata.getTables((String)null, (String)null, "%", types);
         String table = null;

         while(rs.next()) {
            table = rs.getString("TABLE_NAME");
            dbTables.add(table.toLowerCase());
            LOG.debug("Found table " + table + " in HMS dbstore");
         }
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to retrieve schema tables from Hive Metastore DB," + e.getMessage());
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               throw new HiveMetaException("Failed to close resultset", e);
            }
         }

      }

      String baseDir = (new File(this.metaStoreSchemaInfo.getMetaStoreScriptDir())).getParent();
      String schemaFile = baseDir + "/" + this.dbType + "/hive-schema-" + version + "." + this.dbType + ".sql";

      try {
         LOG.debug("Parsing schema script " + schemaFile);
         subScripts.addAll(this.findCreateTable(schemaFile, schemaTables));

         while(subScripts.size() > 0) {
            schemaFile = baseDir + "/" + this.dbType + "/" + (String)subScripts.remove(0);
            LOG.debug("Parsing subscript " + schemaFile);
            subScripts.addAll(this.findCreateTable(schemaFile, schemaTables));
         }
      } catch (Exception e) {
         System.err.println("Exception in parsing schema file. Cause:" + e.getMessage());
         System.out.println("Schema table validation failed!!!");
         return false;
      }

      LOG.debug("Schema tables:[ " + Arrays.toString(schemaTables.toArray()) + " ]");
      LOG.debug("DB tables:[ " + Arrays.toString(dbTables.toArray()) + " ]");
      int schemaSize = schemaTables.size();
      schemaTables.removeAll(dbTables);
      if (schemaTables.size() > 0) {
         System.out.println("Table(s) [ " + Arrays.toString(schemaTables.toArray()) + " ] are missing from the metastore database schema.");
         System.out.println("Schema table validation failed!!!");
         return false;
      } else {
         System.out.println("Succeeded in schema table validation.");
         return true;
      }
   }

   private List findCreateTable(String path, List tableList) throws Exception {
      HiveSchemaHelper.NestedScriptParser sp = HiveSchemaHelper.getDbCommandParser(this.dbType);
      Matcher matcher = null;
      Pattern regexp = null;
      List<String> subs = new ArrayList();
      int groupNo = 0;
      switch (this.dbType) {
         case "oracle":
            regexp = Pattern.compile("(CREATE TABLE(IF NOT EXISTS)*) (\\S+).*");
            groupNo = 3;
            break;
         case "mysql":
            regexp = Pattern.compile("(CREATE TABLE) (\\S+).*");
            groupNo = 2;
            break;
         case "mssql":
            regexp = Pattern.compile("(CREATE TABLE) (\\S+).*");
            groupNo = 2;
            break;
         case "derby":
            regexp = Pattern.compile("(CREATE TABLE(IF NOT EXISTS)*) (\\S+).*");
            groupNo = 3;
            break;
         case "postgres":
            regexp = Pattern.compile("(CREATE TABLE(IF NOT EXISTS)*) (\\S+).*");
            groupNo = 3;
            break;
         default:
            regexp = Pattern.compile("(CREATE TABLE(IF NOT EXISTS)*) (\\S+).*");
            groupNo = 3;
      }

      if (!(new File(path)).exists()) {
         throw new Exception(path + " does not exist. Potentially incorrect version in the metastore VERSION table");
      } else {
         try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Throwable var27 = null;

            try {
               String line = null;

               while((line = reader.readLine()) != null) {
                  if (sp.isNestedScript(line)) {
                     String subScript = null;
                     subScript = sp.getScriptName(line);
                     LOG.debug("Schema subscript " + subScript + " found");
                     subs.add(subScript);
                  } else {
                     line = line.replaceAll("\\(", " ");
                     line = line.replaceAll("IF NOT EXISTS ", "");
                     line = line.replaceAll("`", "");
                     line = line.replaceAll("'", "");
                     line = line.replaceAll("\"", "");
                     matcher = regexp.matcher(line);
                     if (matcher.find()) {
                        String table = matcher.group(groupNo);
                        if (this.dbType.equals("derby")) {
                           table = table.replaceAll("APP.", "");
                        }

                        tableList.add(table.toLowerCase());
                        LOG.debug("Found table " + table + " in the schema");
                     }
                  }
               }
            } catch (Throwable var20) {
               var27 = var20;
               throw var20;
            } finally {
               if (reader != null) {
                  if (var27 != null) {
                     try {
                        reader.close();
                     } catch (Throwable var19) {
                        var27.addSuppressed(var19);
                     }
                  } else {
                     reader.close();
                  }
               }

            }

            return subs;
         } catch (IOException ex) {
            throw new Exception(ex.getMessage());
         }
      }
   }

   boolean validateColumnNullValues(Connection conn) throws HiveMetaException {
      System.out.println("Validating columns for incorrect NULL values");
      boolean isValid = true;

      try {
         Statement stmt = conn.createStatement();
         String tblQuery = this.getDbCommandParser(this.dbType).needsQuotedIdentifier() ? "select t.* from \"TBLS\" t WHERE t.\"SD_ID\" IS NULL and (t.\"TBL_TYPE\"='" + TableType.EXTERNAL_TABLE + "' or t.\"TBL_TYPE\"='" + TableType.MANAGED_TABLE + "')" : "select t.* from TBLS t WHERE t.SD_ID IS NULL and (t.TBL_TYPE='" + TableType.EXTERNAL_TABLE + "' or t.TBL_TYPE='" + TableType.MANAGED_TABLE + "')";
         ResultSet res = stmt.executeQuery(tblQuery);

         while(res.next()) {
            long tableId = res.getLong("TBL_ID");
            String tableName = res.getString("TBL_NAME");
            String tableType = res.getString("TBL_TYPE");
            isValid = false;
            System.err.println("SD_ID in TBLS should not be NULL for Table Name=" + tableName + ", Table ID=" + tableId + ", Table Type=" + tableType);
         }

         System.out.println((isValid ? "Succeeded" : "Failed") + " in column validation for incorrect NULL values");
         return isValid;
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to validate columns for incorrect NULL values", e);
      }
   }

   private void runPreUpgrade(String scriptDir, String scriptFile) {
      int i = 0;

      while(true) {
         String preUpgradeScript = MetaStoreSchemaInfo.getPreUpgradeScriptName(i, scriptFile);
         File preUpgradeScriptFile = new File(scriptDir, preUpgradeScript);
         if (!preUpgradeScriptFile.isFile()) {
            return;
         }

         try {
            this.runBeeLine(scriptDir, preUpgradeScript);
            System.out.println("Completed " + preUpgradeScript);
         } catch (Exception e) {
            System.err.println("Warning in pre-upgrade script " + preUpgradeScript + ": " + e.getMessage());
            if (this.verbose) {
               e.printStackTrace();
            }
         }

         ++i;
      }
   }

   private void runBeeLine(String scriptDir, String scriptFile) throws IOException, HiveMetaException {
      HiveSchemaHelper.NestedScriptParser dbCommandParser = this.getDbCommandParser(this.dbType);
      String sqlCommands = dbCommandParser.buildCommand(scriptDir, scriptFile);
      File tmpFile = File.createTempFile("schematool", ".sql");
      tmpFile.deleteOnExit();
      FileWriter fstream = new FileWriter(tmpFile.getPath());
      BufferedWriter out = new BufferedWriter(fstream);
      out.write("!autocommit on" + System.getProperty("line.separator"));
      out.write(sqlCommands);
      out.write("!closeall" + System.getProperty("line.separator"));
      out.close();
      this.runBeeLine(tmpFile.getPath());
   }

   public void runBeeLine(String sqlScriptFile) throws IOException {
      CommandBuilder builder = new CommandBuilder(this.hiveConf, this.userName, this.passWord, sqlScriptFile);
      BeeLine beeLine = new BeeLine();
      Throwable var4 = null;

      try {
         if (!this.verbose) {
            beeLine.setOutputStream(new PrintStream(new NullOutputStream()));
            beeLine.getOpts().setSilent(true);
         }

         beeLine.getOpts().setAllowMultiLineCommand(false);
         beeLine.getOpts().setIsolation("TRANSACTION_READ_COMMITTED");
         beeLine.getOpts().setEntireLineAsCommand(true);
         LOG.debug("Going to run command <" + builder.buildToLog() + ">");
         int status = beeLine.begin(builder.buildToRun(), (InputStream)null);
         if (status != 0) {
            throw new IOException("Schema script failed, errorcode " + status);
         }
      } catch (Throwable var13) {
         var4 = var13;
         throw var13;
      } finally {
         if (beeLine != null) {
            if (var4 != null) {
               try {
                  beeLine.close();
               } catch (Throwable var12) {
                  var4.addSuppressed(var12);
               }
            } else {
               beeLine.close();
            }
         }

      }

   }

   private static void initOptions(Options cmdLineOptions) {
      Option help = new Option("help", "print this message");
      Option upgradeOpt = new Option("upgradeSchema", "Schema upgrade");
      OptionBuilder.withArgName("upgradeFrom");
      OptionBuilder.hasArg();
      OptionBuilder.withDescription("Schema upgrade from a version");
      Option upgradeFromOpt = OptionBuilder.create("upgradeSchemaFrom");
      Option initOpt = new Option("initSchema", "Schema initialization");
      OptionBuilder.withArgName("initTo");
      OptionBuilder.hasArg();
      OptionBuilder.withDescription("Schema initialization to a version");
      Option initToOpt = OptionBuilder.create("initSchemaTo");
      Option infoOpt = new Option("info", "Show config and schema details");
      Option validateOpt = new Option("validate", "Validate the database");
      OptionGroup optGroup = new OptionGroup();
      optGroup.addOption(upgradeOpt).addOption(initOpt).addOption(help).addOption(upgradeFromOpt).addOption(initToOpt).addOption(infoOpt).addOption(validateOpt);
      optGroup.setRequired(true);
      OptionBuilder.withArgName("user");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("Override config file user name");
      Option userNameOpt = OptionBuilder.create("userName");
      OptionBuilder.withArgName("password");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("Override config file password");
      Option passwdOpt = OptionBuilder.create("passWord");
      OptionBuilder.withArgName("databaseType");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("Metastore database type");
      Option dbTypeOpt = OptionBuilder.create("dbType");
      OptionBuilder.withArgName("databaseOpts");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("Backend DB specific options");
      Option dbOpts = OptionBuilder.create("dbOpts");
      Option dryRunOpt = new Option("dryRun", "list SQL scripts (no execute)");
      Option verboseOpt = new Option("verbose", "only print SQL statements");
      OptionBuilder.withArgName("serverList");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("a comma-separated list of servers used in location validation");
      Option serversOpt = OptionBuilder.create("servers");
      cmdLineOptions.addOption(help);
      cmdLineOptions.addOption(dryRunOpt);
      cmdLineOptions.addOption(userNameOpt);
      cmdLineOptions.addOption(passwdOpt);
      cmdLineOptions.addOption(dbTypeOpt);
      cmdLineOptions.addOption(verboseOpt);
      cmdLineOptions.addOption(dbOpts);
      cmdLineOptions.addOption(serversOpt);
      cmdLineOptions.addOptionGroup(optGroup);
   }

   public static void main(String[] args) {
      CommandLineParser parser = new GnuParser();
      CommandLine line = null;
      String dbType = null;
      String schemaVer = null;
      Options cmdLineOptions = new Options();
      initOptions(cmdLineOptions);

      try {
         line = parser.parse(cmdLineOptions, args);
      } catch (ParseException e) {
         System.err.println("HiveSchemaTool:Parsing failed.  Reason: " + e.getLocalizedMessage());
         printAndExit(cmdLineOptions);
      }

      if (line.hasOption("help")) {
         HelpFormatter formatter = new HelpFormatter();
         formatter.printHelp("schemaTool", cmdLineOptions);
      } else {
         if (line.hasOption("dbType")) {
            dbType = line.getOptionValue("dbType");
            if (!dbType.equalsIgnoreCase("derby") && !dbType.equalsIgnoreCase("mssql") && !dbType.equalsIgnoreCase("mysql") && !dbType.equalsIgnoreCase("postgres") && !dbType.equalsIgnoreCase("oracle")) {
               System.err.println("Unsupported dbType " + dbType);
               printAndExit(cmdLineOptions);
            }
         } else {
            System.err.println("no dbType supplied");
            printAndExit(cmdLineOptions);
         }

         System.setProperty(ConfVars.METASTORE_SCHEMA_VERIFICATION.varname, "true");

         try {
            HiveSchemaTool schemaTool = new HiveSchemaTool(dbType);
            if (line.hasOption("userName")) {
               schemaTool.setUserName(line.getOptionValue("userName"));
            }

            if (line.hasOption("passWord")) {
               schemaTool.setPassWord(line.getOptionValue("passWord"));
            }

            if (line.hasOption("dryRun")) {
               schemaTool.setDryRun(true);
            }

            if (line.hasOption("verbose")) {
               schemaTool.setVerbose(true);
            }

            if (line.hasOption("dbOpts")) {
               schemaTool.setDbOpts(line.getOptionValue("dbOpts"));
            }

            if (line.hasOption("validate") && line.hasOption("servers")) {
               schemaTool.setValidationServers(line.getOptionValue("servers"));
            }

            if (line.hasOption("info")) {
               schemaTool.showInfo();
            } else if (line.hasOption("upgradeSchema")) {
               schemaTool.doUpgrade();
            } else if (line.hasOption("upgradeSchemaFrom")) {
               schemaVer = line.getOptionValue("upgradeSchemaFrom");
               schemaTool.doUpgrade(schemaVer);
            } else if (line.hasOption("initSchema")) {
               schemaTool.doInit();
            } else if (line.hasOption("initSchemaTo")) {
               schemaVer = line.getOptionValue("initSchemaTo");
               schemaTool.doInit(schemaVer);
            } else if (line.hasOption("validate")) {
               schemaTool.doValidate();
            } else {
               System.err.println("no valid option supplied");
               printAndExit(cmdLineOptions);
            }
         } catch (HiveMetaException var9) {
            System.err.println(var9);
            if (var9.getCause() != null) {
               Throwable t = var9.getCause();
               System.err.println("Underlying cause: " + t.getClass().getName() + " : " + t.getMessage());
               if (var9.getCause() instanceof SQLException) {
                  System.err.println("SQL Error code: " + ((SQLException)t).getErrorCode());
               }
            }

            if (line.hasOption("verbose")) {
               var9.printStackTrace();
            } else {
               System.err.println("Use --verbose for detailed stacktrace.");
            }

            System.err.println("*** schemaTool failed ***");
            System.exit(1);
         }

         System.out.println("schemaTool completed");
      }
   }

   static class CommandBuilder {
      private final HiveConf hiveConf;
      private final String userName;
      private final String password;
      private final String sqlScriptFile;

      CommandBuilder(HiveConf hiveConf, String userName, String password, String sqlScriptFile) {
         this.hiveConf = hiveConf;
         this.userName = userName;
         this.password = password;
         this.sqlScriptFile = sqlScriptFile;
      }

      String[] buildToRun() throws IOException {
         return this.argsWith(this.password);
      }

      String buildToLog() throws IOException {
         this.logScript();
         return StringUtils.join(this.argsWith("[passwd stripped]"), " ");
      }

      private String[] argsWith(String password) throws IOException {
         return new String[]{"-u", HiveSchemaHelper.getValidConfVar(ConfVars.METASTORECONNECTURLKEY, this.hiveConf), "-d", HiveSchemaHelper.getValidConfVar(ConfVars.METASTORE_CONNECTION_DRIVER, this.hiveConf), "-n", this.userName, "-p", password, "-f", this.sqlScriptFile};
      }

      private void logScript() throws IOException {
         if (HiveSchemaTool.LOG.isDebugEnabled()) {
            HiveSchemaTool.LOG.debug("Going to invoke file that contains:");
            BufferedReader reader = new BufferedReader(new FileReader(this.sqlScriptFile));
            Throwable var2 = null;

            try {
               String line;
               try {
                  while((line = reader.readLine()) != null) {
                     HiveSchemaTool.LOG.debug("script: " + line);
                  }
               } catch (Throwable var11) {
                  var2 = var11;
                  throw var11;
               }
            } finally {
               if (reader != null) {
                  if (var2 != null) {
                     try {
                        reader.close();
                     } catch (Throwable var10) {
                        var2.addSuppressed(var10);
                     }
                  } else {
                     reader.close();
                  }
               }

            }
         }

      }
   }
}
