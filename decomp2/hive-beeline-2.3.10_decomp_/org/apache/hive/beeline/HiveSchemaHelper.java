package org.apache.hive.beeline;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.IllegalFormatException;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.HiveMetaException;

public class HiveSchemaHelper {
   public static final String DB_DERBY = "derby";
   public static final String DB_MSSQL = "mssql";
   public static final String DB_MYSQL = "mysql";
   public static final String DB_POSTGRACE = "postgres";
   public static final String DB_ORACLE = "oracle";

   public static Connection getConnectionToMetastore(String userName, String password, boolean printInfo, HiveConf hiveConf) throws HiveMetaException {
      try {
         String connectionURL = getValidConfVar(ConfVars.METASTORECONNECTURLKEY, hiveConf);
         String driver = getValidConfVar(ConfVars.METASTORE_CONNECTION_DRIVER, hiveConf);
         if (printInfo) {
            System.out.println("Metastore connection URL:\t " + connectionURL);
            System.out.println("Metastore Connection Driver :\t " + driver);
            System.out.println("Metastore connection User:\t " + userName);
         }

         if (userName != null && !userName.isEmpty()) {
            Class.forName(driver);
            return DriverManager.getConnection(connectionURL, userName, password);
         } else {
            throw new HiveMetaException("UserName empty ");
         }
      } catch (IOException e) {
         throw new HiveMetaException("Failed to get schema version.", e);
      } catch (SQLException e) {
         throw new HiveMetaException("Failed to get schema version.", e);
      } catch (ClassNotFoundException e) {
         throw new HiveMetaException("Failed to load driver", e);
      }
   }

   public static String getValidConfVar(HiveConf.ConfVars confVar, HiveConf hiveConf) throws IOException {
      String confVarStr = hiveConf.get(confVar.varname);
      if (confVarStr != null && !confVarStr.isEmpty()) {
         return confVarStr.trim();
      } else {
         throw new IOException("Empty " + confVar.varname);
      }
   }

   public static NestedScriptParser getDbCommandParser(String dbName) {
      return getDbCommandParser(dbName, (String)null, (String)null, (String)null, (HiveConf)null);
   }

   public static NestedScriptParser getDbCommandParser(String dbName, String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
      if (dbName.equalsIgnoreCase("derby")) {
         return new DerbyCommandParser(dbOpts, msUsername, msPassword, hiveConf);
      } else if (dbName.equalsIgnoreCase("mssql")) {
         return new MSSQLCommandParser(dbOpts, msUsername, msPassword, hiveConf);
      } else if (dbName.equalsIgnoreCase("mysql")) {
         return new MySqlCommandParser(dbOpts, msUsername, msPassword, hiveConf);
      } else if (dbName.equalsIgnoreCase("postgres")) {
         return new PostgresCommandParser(dbOpts, msUsername, msPassword, hiveConf);
      } else if (dbName.equalsIgnoreCase("oracle")) {
         return new OracleCommandParser(dbOpts, msUsername, msPassword, hiveConf);
      } else {
         throw new IllegalArgumentException("Unknown dbType " + dbName);
      }
   }

   private abstract static class AbstractCommandParser implements NestedScriptParser {
      private List dbOpts;
      private String msUsername;
      private String msPassword;
      private HiveConf hiveConf;

      public AbstractCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         this.setDbOpts(dbOpts);
         this.msUsername = msUsername;
         this.msPassword = msPassword;
         this.hiveConf = hiveConf;
      }

      public boolean isPartialCommand(String dbCommand) throws IllegalArgumentException {
         if (dbCommand != null && !dbCommand.isEmpty()) {
            dbCommand = dbCommand.trim();
            return !dbCommand.endsWith(this.getDelimiter()) && !this.isNonExecCommand(dbCommand);
         } else {
            throw new IllegalArgumentException("invalid command line " + dbCommand);
         }
      }

      public boolean isNonExecCommand(String dbCommand) {
         return dbCommand.startsWith("--") || dbCommand.startsWith("#");
      }

      public String getDelimiter() {
         return ";";
      }

      public String cleanseCommand(String dbCommand) {
         if (dbCommand.endsWith(this.getDelimiter())) {
            dbCommand = dbCommand.substring(0, dbCommand.length() - this.getDelimiter().length());
         }

         return dbCommand;
      }

      public boolean needsQuotedIdentifier() {
         return false;
      }

      public String buildCommand(String scriptDir, String scriptFile) throws IllegalFormatException, IOException {
         BufferedReader bfReader = new BufferedReader(new FileReader(scriptDir + File.separatorChar + scriptFile));
         StringBuilder sb = new StringBuilder();
         String currentCommand = null;

         String currLine;
         while((currLine = bfReader.readLine()) != null) {
            currLine = currLine.trim();
            if (!currLine.isEmpty()) {
               if (currentCommand == null) {
                  currentCommand = currLine;
               } else {
                  currentCommand = currentCommand + " " + currLine;
               }

               if (!this.isPartialCommand(currLine)) {
                  if (!this.isNonExecCommand(currentCommand)) {
                     currentCommand = this.cleanseCommand(currentCommand);
                     if (this.isNestedScript(currentCommand)) {
                        String currScript = this.getScriptName(currentCommand);
                        sb.append(this.buildCommand(scriptDir, currScript));
                     } else {
                        sb.append(currentCommand);
                        sb.append(System.getProperty("line.separator"));
                     }
                  }

                  currentCommand = null;
               }
            }
         }

         bfReader.close();
         return sb.toString();
      }

      private void setDbOpts(String dbOpts) {
         if (dbOpts != null) {
            this.dbOpts = Lists.newArrayList(dbOpts.split(","));
         } else {
            this.dbOpts = Lists.newArrayList();
         }

      }

      protected List getDbOpts() {
         return this.dbOpts;
      }

      protected String getMsUsername() {
         return this.msUsername;
      }

      protected String getMsPassword() {
         return this.msPassword;
      }

      protected HiveConf getHiveConf() {
         return this.hiveConf;
      }
   }

   public static class DerbyCommandParser extends AbstractCommandParser {
      private static final String DERBY_NESTING_TOKEN = "RUN";

      public DerbyCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         super(dbOpts, msUsername, msPassword, hiveConf);
      }

      public String getScriptName(String dbCommand) throws IllegalArgumentException {
         if (!this.isNestedScript(dbCommand)) {
            throw new IllegalArgumentException("Not a script format " + dbCommand);
         } else {
            String[] tokens = dbCommand.split(" ");
            if (tokens.length != 2) {
               throw new IllegalArgumentException("Couldn't parse line " + dbCommand);
            } else {
               return tokens[1].replace(";", "").replaceAll("'", "");
            }
         }
      }

      public boolean isNestedScript(String dbCommand) {
         return dbCommand.startsWith("RUN");
      }
   }

   public static class MySqlCommandParser extends AbstractCommandParser {
      private static final String MYSQL_NESTING_TOKEN = "SOURCE";
      private static final String DELIMITER_TOKEN = "DELIMITER";
      private String delimiter = ";";

      public MySqlCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         super(dbOpts, msUsername, msPassword, hiveConf);
      }

      public boolean isPartialCommand(String dbCommand) throws IllegalArgumentException {
         boolean isPartial = super.isPartialCommand(dbCommand);
         if (dbCommand.startsWith("DELIMITER")) {
            String[] tokens = dbCommand.split(" ");
            if (tokens.length != 2) {
               throw new IllegalArgumentException("Couldn't parse line " + dbCommand);
            }

            this.delimiter = tokens[1];
         }

         return isPartial;
      }

      public String getScriptName(String dbCommand) throws IllegalArgumentException {
         String[] tokens = dbCommand.split(" ");
         if (tokens.length != 2) {
            throw new IllegalArgumentException("Couldn't parse line " + dbCommand);
         } else {
            return tokens[1].replace(";", "");
         }
      }

      public boolean isNestedScript(String dbCommand) {
         return dbCommand.startsWith("SOURCE");
      }

      public String getDelimiter() {
         return this.delimiter;
      }

      public boolean isNonExecCommand(String dbCommand) {
         return super.isNonExecCommand(dbCommand) || dbCommand.startsWith("/*") && dbCommand.endsWith("*/") || dbCommand.startsWith("DELIMITER");
      }

      public String cleanseCommand(String dbCommand) {
         return super.cleanseCommand(dbCommand).replaceAll("/\\*.*?\\*/[^;]", "");
      }
   }

   public static class PostgresCommandParser extends AbstractCommandParser {
      private static final String POSTGRES_NESTING_TOKEN = "\\i";
      @VisibleForTesting
      public static final String POSTGRES_STANDARD_STRINGS_OPT = "SET standard_conforming_strings";
      @VisibleForTesting
      public static final String POSTGRES_SKIP_STANDARD_STRINGS_DBOPT = "postgres.filter.81";

      public PostgresCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         super(dbOpts, msUsername, msPassword, hiveConf);
      }

      public String getScriptName(String dbCommand) throws IllegalArgumentException {
         String[] tokens = dbCommand.split(" ");
         if (tokens.length != 2) {
            throw new IllegalArgumentException("Couldn't parse line " + dbCommand);
         } else {
            return tokens[1].replace(";", "");
         }
      }

      public boolean isNestedScript(String dbCommand) {
         return dbCommand.startsWith("\\i");
      }

      public boolean needsQuotedIdentifier() {
         return true;
      }

      public boolean isNonExecCommand(String dbCommand) {
         return this.getDbOpts().contains("postgres.filter.81") && dbCommand.startsWith("SET standard_conforming_strings") ? true : super.isNonExecCommand(dbCommand);
      }
   }

   public static class OracleCommandParser extends AbstractCommandParser {
      private static final String ORACLE_NESTING_TOKEN = "@";

      public OracleCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         super(dbOpts, msUsername, msPassword, hiveConf);
      }

      public String getScriptName(String dbCommand) throws IllegalArgumentException {
         if (!this.isNestedScript(dbCommand)) {
            throw new IllegalArgumentException("Not a nested script format " + dbCommand);
         } else {
            return dbCommand.replace(";", "").replace("@", "");
         }
      }

      public boolean isNestedScript(String dbCommand) {
         return dbCommand.startsWith("@");
      }
   }

   public static class MSSQLCommandParser extends AbstractCommandParser {
      private static final String MSSQL_NESTING_TOKEN = ":r";

      public MSSQLCommandParser(String dbOpts, String msUsername, String msPassword, HiveConf hiveConf) {
         super(dbOpts, msUsername, msPassword, hiveConf);
      }

      public String getScriptName(String dbCommand) throws IllegalArgumentException {
         String[] tokens = dbCommand.split(" ");
         if (tokens.length != 2) {
            throw new IllegalArgumentException("Couldn't parse line " + dbCommand);
         } else {
            return tokens[1];
         }
      }

      public boolean isNestedScript(String dbCommand) {
         return dbCommand.startsWith(":r");
      }
   }

   public interface NestedScriptParser {
      String DEFAUTL_DELIMITER = ";";

      boolean isPartialCommand(String var1) throws IllegalArgumentException;

      String getScriptName(String var1) throws IllegalArgumentException;

      boolean isNestedScript(String var1);

      boolean isNonExecCommand(String var1);

      String getDelimiter();

      String cleanseCommand(String var1);

      boolean needsQuotedIdentifier();

      String buildCommand(String var1, String var2) throws IllegalFormatException, IOException;

      public static enum CommandType {
         PARTIAL_STATEMENT,
         TERMINATED_STATEMENT,
         COMMENT;
      }
   }
}
