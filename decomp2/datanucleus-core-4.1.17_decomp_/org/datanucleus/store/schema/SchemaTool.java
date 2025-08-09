package org.datanucleus.store.schema;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.datanucleus.AbstractNucleusContext;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.PersistenceNucleusContextImpl;
import org.datanucleus.StoreNucleusContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.CommandLine;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.PersistenceUtils;
import org.datanucleus.util.StringUtils;

public class SchemaTool {
   public static final NucleusLogger LOGGER = NucleusLogger.getLoggerInstance("DataNucleus.SchemaTool");
   public static final String OPTION_CREATE_SCHEMA = "createSchema";
   public static final String OPTION_DELETE_SCHEMA = "deleteSchema";
   public static final String OPTION_CREATE_TABLES_FOR_CLASSES = "create";
   public static final String OPTION_DELETE_TABLES_FOR_CLASSES = "delete";
   public static final String OPTION_DELETE_CREATE_TABLES_FOR_CLASSES = "deletecreate";
   public static final String OPTION_VALIDATE_TABLES_FOR_CLASSES = "validate";
   public static final String OPTION_DBINFO = "dbinfo";
   public static final String OPTION_SCHEMAINFO = "schemainfo";
   public static final String OPTION_DDL_FILE = "ddlFile";
   public static final String OPTION_COMPLETE_DDL = "completeDdl";
   public static final String OPTION_INCLUDE_AUTO_START = "includeAutoStart";
   public static final String OPTION_API = "api";
   private String apiName = "JDO";
   private String schemaName = null;
   private String ddlFilename = null;
   private boolean completeDdl = false;
   private boolean includeAutoStart = false;
   private boolean verbose = false;

   public static void main(String[] args) throws Exception {
      SchemaTool tool = new SchemaTool();
      CommandLine cmd = new CommandLine();
      cmd.addOption("createSchema", "createSchema", "createSchema", Localiser.msg("014024"));
      cmd.addOption("deleteSchema", "deleteSchema", "deleteSchema", Localiser.msg("014025"));
      cmd.addOption("create", "create", (String)null, Localiser.msg("014026"));
      cmd.addOption("delete", "delete", (String)null, Localiser.msg("014027"));
      cmd.addOption("deletecreate", "deletecreate", (String)null, Localiser.msg("014044"));
      cmd.addOption("validate", "validate", (String)null, Localiser.msg("014028"));
      cmd.addOption("dbinfo", "dbinfo", (String)null, Localiser.msg("014029"));
      cmd.addOption("schemainfo", "schemainfo", (String)null, Localiser.msg("014030"));
      cmd.addOption("help", "help", (String)null, Localiser.msg("014033"));
      cmd.addOption("ddlFile", "ddlFile", "ddlFile", Localiser.msg("014031"));
      cmd.addOption("completeDdl", "completeDdl", (String)null, Localiser.msg("014032"));
      cmd.addOption("includeAutoStart", "includeAutoStart", (String)null, "Include Auto-Start Mechanisms");
      cmd.addOption("api", "api", "api", "API Adapter (JDO, JPA, etc)");
      cmd.addOption("v", "verbose", (String)null, "verbose output");
      cmd.addOption("pu", "persistenceUnit", "<persistence-unit>", "name of the persistence unit to handle the schema for");
      cmd.addOption("props", "properties", "props", "path to a properties file");
      cmd.parse(args);
      String[] filenames = cmd.getDefaultArgs();
      if (cmd.hasOption("api")) {
         tool.setApi(cmd.getOptionArg("api"));
      }

      String msg = null;
      Mode mode = SchemaTool.Mode.CREATE;
      if (cmd.hasOption("create")) {
         mode = SchemaTool.Mode.CREATE;
         msg = Localiser.msg("014000");
      } else if (cmd.hasOption("delete")) {
         mode = SchemaTool.Mode.DELETE;
         msg = Localiser.msg("014001");
      } else if (cmd.hasOption("deletecreate")) {
         mode = SchemaTool.Mode.DELETE_CREATE;
         msg = Localiser.msg("014045");
      } else if (cmd.hasOption("validate")) {
         mode = SchemaTool.Mode.VALIDATE;
         msg = Localiser.msg("014002");
      } else if (cmd.hasOption("createSchema")) {
         mode = SchemaTool.Mode.CREATE_SCHEMA;
         tool.setSchemaName(cmd.getOptionArg("createSchema"));
         msg = Localiser.msg("014034", tool.getSchemaName());
      } else if (cmd.hasOption("deleteSchema")) {
         mode = SchemaTool.Mode.DELETE_SCHEMA;
         tool.setSchemaName(cmd.getOptionArg("deleteSchema"));
         msg = Localiser.msg("014035", tool.getSchemaName());
      } else if (cmd.hasOption("dbinfo")) {
         mode = SchemaTool.Mode.DATABASE_INFO;
         msg = Localiser.msg("014003");
      } else if (cmd.hasOption("schemainfo")) {
         mode = SchemaTool.Mode.SCHEMA_INFO;
         msg = Localiser.msg("014004");
      } else if (cmd.hasOption("help")) {
         System.out.println(Localiser.msg("014023", cmd.toString()));
         System.exit(0);
      }

      LOGGER.info(msg);
      System.out.println(msg);
      String propsFileName = null;
      String persistenceUnitName = null;
      if (cmd.hasOption("ddlFile")) {
         tool.setDdlFile(cmd.getOptionArg("ddlFile"));
      }

      if (cmd.hasOption("completeDdl")) {
         tool.setCompleteDdl(true);
      }

      if (cmd.hasOption("includeAutoStart")) {
         tool.setIncludeAutoStart(true);
      }

      if (cmd.hasOption("v")) {
         tool.setVerbose(true);
      }

      if (cmd.hasOption("pu")) {
         persistenceUnitName = cmd.getOptionArg("pu");
      }

      if (cmd.hasOption("props")) {
         propsFileName = cmd.getOptionArg("props");
      }

      msg = Localiser.msg("014005");
      LOGGER.info(msg);
      if (tool.isVerbose()) {
         System.out.println(msg);
      }

      StringTokenizer tokeniser = new StringTokenizer(System.getProperty("java.class.path"), File.pathSeparator);

      while(tokeniser.hasMoreTokens()) {
         msg = Localiser.msg("014006", tokeniser.nextToken());
         LOGGER.info(msg);
         if (tool.isVerbose()) {
            System.out.println(msg);
         }
      }

      if (tool.isVerbose()) {
         System.out.println();
      }

      String ddlFilename = tool.getDdlFile();
      if (ddlFilename != null) {
         msg = Localiser.msg(tool.getCompleteDdl() ? "014018" : "014019", ddlFilename);
         LOGGER.info(msg);
         if (tool.isVerbose()) {
            System.out.println(msg);
            System.out.println();
         }
      }

      StoreNucleusContext nucleusCtx = null;

      try {
         if (propsFileName != null) {
            Properties props = PersistenceUtils.setPropertiesUsingFile(propsFileName);
            nucleusCtx = getNucleusContextForMode(mode, tool.getApi(), props, persistenceUnitName, ddlFilename, tool.isVerbose());
         } else {
            nucleusCtx = getNucleusContextForMode(mode, tool.getApi(), (Map)null, persistenceUnitName, ddlFilename, tool.isVerbose());
         }
      } catch (Exception e) {
         LOGGER.error("Error creating NucleusContext", e);
         System.out.println(Localiser.msg("014008", e.getMessage()));
         System.exit(1);
         return;
      }

      Set<String> classNames = null;
      if (mode != SchemaTool.Mode.SCHEMA_INFO && mode != SchemaTool.Mode.DATABASE_INFO) {
         try {
            MetaDataManager metaDataMgr = nucleusCtx.getMetaDataManager();
            ClassLoaderResolver clr = nucleusCtx.getClassLoaderResolver((ClassLoader)null);
            if (filenames == null && persistenceUnitName == null) {
               msg = Localiser.msg("014007");
               LOGGER.error(msg);
               System.out.println(msg);
               throw new NucleusUserException(msg);
            }

            FileMetaData[] filemds = null;
            if (persistenceUnitName != null) {
               msg = Localiser.msg("014015", persistenceUnitName);
               LOGGER.info(msg);
               if (tool.isVerbose()) {
                  System.out.println(msg);
                  System.out.println();
               }

               filemds = metaDataMgr.getFileMetaData();
            } else {
               msg = Localiser.msg("014009");
               LOGGER.info(msg);
               if (tool.isVerbose()) {
                  System.out.println(msg);
               }

               for(int i = 0; i < filenames.length; ++i) {
                  String entry = Localiser.msg("014010", filenames[i]);
                  LOGGER.info(entry);
                  if (tool.isVerbose()) {
                     System.out.println(entry);
                  }
               }

               if (tool.isVerbose()) {
                  System.out.println();
               }

               LOGGER.debug(Localiser.msg("014011", "" + filenames.length));
               filemds = MetaDataUtils.getFileMetaDataForInputFiles(metaDataMgr, clr, filenames);
               LOGGER.debug(Localiser.msg("014012", "" + filenames.length));
            }

            classNames = new TreeSet();
            if (filemds == null) {
               msg = Localiser.msg("014021");
               LOGGER.error(msg);
               System.out.println(msg);
               System.exit(2);
               return;
            }

            for(int i = 0; i < filemds.length; ++i) {
               for(int j = 0; j < filemds[i].getNoOfPackages(); ++j) {
                  for(int k = 0; k < filemds[i].getPackage(j).getNoOfClasses(); ++k) {
                     String className = filemds[i].getPackage(j).getClass(k).getFullClassName();
                     if (!classNames.contains(className)) {
                        classNames.add(className);
                     }
                  }
               }
            }
         } catch (Exception var27) {
            System.exit(2);
            return;
         }
      }

      StoreManager storeMgr = nucleusCtx.getStoreManager();
      if (!(storeMgr instanceof SchemaAwareStoreManager)) {
         LOGGER.error("StoreManager of type " + storeMgr.getClass().getName() + " is not schema-aware so cannot be used with SchemaTool");
         System.exit(2);
      } else {
         SchemaAwareStoreManager schemaStoreMgr = (SchemaAwareStoreManager)storeMgr;

         try {
            if (mode == SchemaTool.Mode.CREATE_SCHEMA) {
               tool.createSchema(schemaStoreMgr, tool.getSchemaName());
            } else if (mode == SchemaTool.Mode.DELETE_SCHEMA) {
               tool.deleteSchema(schemaStoreMgr, tool.getSchemaName());
            } else if (mode == SchemaTool.Mode.CREATE) {
               tool.createSchemaForClasses(schemaStoreMgr, classNames);
            } else if (mode == SchemaTool.Mode.DELETE) {
               tool.deleteSchemaForClasses(schemaStoreMgr, classNames);
            } else if (mode == SchemaTool.Mode.DELETE_CREATE) {
               tool.deleteSchemaForClasses(schemaStoreMgr, classNames);
               tool.createSchemaForClasses(schemaStoreMgr, classNames);
            } else if (mode == SchemaTool.Mode.VALIDATE) {
               tool.validateSchemaForClasses(schemaStoreMgr, classNames);
            } else if (mode == SchemaTool.Mode.DATABASE_INFO) {
               storeMgr.printInformation("DATASTORE", System.out);
            } else if (mode == SchemaTool.Mode.SCHEMA_INFO) {
               storeMgr.printInformation("SCHEMA", System.out);
            }

            msg = Localiser.msg("014043");
            LOGGER.info(msg);
            System.out.println(msg);
            return;
         } catch (Exception e) {
            msg = Localiser.msg("014037", e.getMessage());
            System.out.println(msg);
            LOGGER.error(msg, e);
            System.exit(2);
         } finally {
            storeMgr.close();
         }

      }
   }

   public Properties getPropertiesForSchemaTool() {
      Properties props = new Properties();
      if (this.getDdlFile() != null) {
         props.setProperty("ddlFilename", this.getDdlFile());
      }

      if (this.getCompleteDdl()) {
         props.setProperty("completeDdl", "true");
      }

      if (this.getIncludeAutoStart()) {
         props.setProperty("autoStartTable", "true");
      }

      return props;
   }

   public void createSchema(SchemaAwareStoreManager storeMgr, String schemaName) {
      storeMgr.createSchema(schemaName, this.getPropertiesForSchemaTool());
   }

   public void deleteSchema(SchemaAwareStoreManager storeMgr, String schemaName) {
      storeMgr.deleteSchema(schemaName, this.getPropertiesForSchemaTool());
   }

   public void createSchemaForClasses(SchemaAwareStoreManager storeMgr, Set classNames) {
      storeMgr.createSchemaForClasses(classNames, this.getPropertiesForSchemaTool());
   }

   public void deleteSchemaForClasses(SchemaAwareStoreManager storeMgr, Set classNames) {
      storeMgr.deleteSchemaForClasses(classNames, this.getPropertiesForSchemaTool());
   }

   public void validateSchemaForClasses(SchemaAwareStoreManager storeMgr, Set classNames) {
      storeMgr.validateSchemaForClasses(classNames, this.getPropertiesForSchemaTool());
   }

   public static StoreNucleusContext getNucleusContextForMode(Mode mode, String api, Map userProps, String persistenceUnitName, String ddlFile, boolean verbose) {
      Map startupProps = null;
      if (userProps != null) {
         for(String startupPropName : AbstractNucleusContext.STARTUP_PROPERTIES) {
            if (userProps.containsKey(startupPropName)) {
               if (startupProps == null) {
                  startupProps = new HashMap();
               }

               startupProps.put(startupPropName, userProps.get(startupPropName));
            }
         }
      }

      PersistenceNucleusContext nucleusCtx = new PersistenceNucleusContextImpl(api, startupProps);
      Configuration propConfig = nucleusCtx.getConfiguration();
      Map props = new HashMap();
      PersistenceUnitMetaData pumd = null;
      if (persistenceUnitName != null) {
         props.put("datanucleus.PersistenceUnitName".toLowerCase(), persistenceUnitName);
         pumd = nucleusCtx.getMetaDataManager().getMetaDataForPersistenceUnit(persistenceUnitName);
         if (pumd == null) {
            throw new NucleusUserException("SchemaTool has been specified to use persistence-unit with name " + persistenceUnitName + " but none was found with that name");
         }

         if (pumd.getProperties() != null) {
            props.putAll(pumd.getProperties());
         }

         if (api.equalsIgnoreCase("JPA")) {
            pumd.clearJarFiles();
         }
      }

      if (userProps != null) {
         for(Object key : userProps.keySet()) {
            String propName = (String)key;
            props.put(propName.toLowerCase(Locale.ENGLISH), userProps.get(propName));
         }
      }

      String[] propNames = new String[]{"datanucleus.ConnectionURL", "datanucleus.ConnectionDriverName", "datanucleus.ConnectionUserName", "datanucleus.ConnectionPassword", "datanucleus.mapping", "javax.jdo.option.ConnectionURL", "javax.jdo.option.ConnectionDriverName", "javax.jdo.option.ConnectionUserName", "javax.jdo.option.ConnectionPassword", "javax.jdo.option.Mapping", "javax.persistence.jdbc.url", "javax.persistence.jdbc.driver", "javax.persistence.jdbc.user", "javax.persistence.jdbc.password"};

      for(int i = 0; i < propNames.length; ++i) {
         if (System.getProperty(propNames[i]) != null) {
            props.put(propNames[i].toLowerCase(Locale.ENGLISH), System.getProperty(propNames[i]));
         }
      }

      props.put("datanucleus.autoStartMechanism".toLowerCase(), "None");
      if (mode == SchemaTool.Mode.CREATE) {
         if (ddlFile != null) {
            props.put("datanucleus.schema.validateTables".toLowerCase(), "false");
            props.put("datanucleus.schema.validateColumns".toLowerCase(), "false");
            props.put("datanucleus.schema.validateConstraints".toLowerCase(), "false");
         }

         props.remove("datanucleus.schema.autoCreateAll".toLowerCase());
         if (!props.containsKey("datanucleus.schema.autoCreateTables".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateTables".toLowerCase(), "true");
         }

         if (!props.containsKey("datanucleus.schema.autoCreateColumns".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateColumns".toLowerCase(), "true");
         }

         if (!props.containsKey("datanucleus.schema.autoCreateConstraints".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateConstraints".toLowerCase(), "true");
         }

         props.put("datanucleus.readOnlyDatastore".toLowerCase(), "false");
         props.put("datanucleus.rdbms.checkexisttablesorviews", "true");
      } else if (mode == SchemaTool.Mode.DELETE) {
         props.put("datanucleus.readOnlyDatastore".toLowerCase(), "false");
      } else if (mode == SchemaTool.Mode.DELETE_CREATE) {
         if (ddlFile != null) {
            props.put("datanucleus.schema.validateTables".toLowerCase(), "false");
            props.put("datanucleus.schema.validateColumns".toLowerCase(), "false");
            props.put("datanucleus.schema.validateConstraints".toLowerCase(), "false");
         }

         props.remove("datanucleus.schema.autoCreateAll".toLowerCase());
         if (!props.containsKey("datanucleus.schema.autoCreateTables".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateTables".toLowerCase(), "true");
         }

         if (!props.containsKey("datanucleus.schema.autoCreateColumns".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateColumns".toLowerCase(), "true");
         }

         if (!props.containsKey("datanucleus.schema.autoCreateConstraints".toLowerCase())) {
            props.put("datanucleus.schema.autoCreateConstraints".toLowerCase(), "true");
         }

         props.put("datanucleus.readOnlyDatastore".toLowerCase(), "false");
         props.put("datanucleus.rdbms.checkexisttablesorviews", "true");
      } else if (mode == SchemaTool.Mode.VALIDATE) {
         props.put("datanucleus.schema.autoCreateAll".toLowerCase(), "false");
         props.put("datanucleus.schema.autoCreateTables".toLowerCase(), "false");
         props.put("datanucleus.schema.autoCreateConstraints".toLowerCase(), "false");
         props.put("datanucleus.schema.autoCreateColumns".toLowerCase(), "false");
         props.put("datanucleus.schema.validateTables".toLowerCase(), "true");
         props.put("datanucleus.schema.validateColumns".toLowerCase(), "true");
         props.put("datanucleus.schema.validateConstraints".toLowerCase(), "true");
      }

      propConfig.setPersistenceProperties(props);
      if (pumd != null) {
         nucleusCtx.getMetaDataManager().loadPersistenceUnit(pumd, (ClassLoader)null);
      }

      nucleusCtx.initialise();
      if (verbose) {
         String msg = Localiser.msg("014020");
         LOGGER.info(msg);
         System.out.println(msg);
         Map<String, Object> pmfProps = propConfig.getPersistenceProperties();
         Set<String> keys = pmfProps.keySet();
         List<String> keyNames = new ArrayList(keys);
         Collections.sort(keyNames);

         for(String key : keyNames) {
            Object value = pmfProps.get(key);
            boolean display = true;
            if (!key.startsWith("datanucleus")) {
               display = false;
            } else if (key.equals("datanucleus.ConnectionPassword".toLowerCase())) {
               display = false;
            } else if (value == null) {
               display = false;
            } else if (value instanceof String && StringUtils.isWhitespace((String)value)) {
               display = false;
            }

            if (display) {
               msg = Localiser.msg("014022", key, value);
               LOGGER.info(msg);
               System.out.println(msg);
            }
         }

         System.out.println();
      }

      return nucleusCtx;
   }

   public String getApi() {
      return this.apiName;
   }

   public SchemaTool setApi(String api) {
      this.apiName = api;
      return this;
   }

   public boolean isVerbose() {
      return this.verbose;
   }

   public SchemaTool setVerbose(boolean verbose) {
      this.verbose = verbose;
      return this;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public SchemaTool setSchemaName(String schemaName) {
      this.schemaName = schemaName;
      return this;
   }

   public String getDdlFile() {
      return this.ddlFilename;
   }

   public SchemaTool setDdlFile(String file) {
      this.ddlFilename = file;
      return this;
   }

   public SchemaTool setCompleteDdl(boolean completeDdl) {
      this.completeDdl = completeDdl;
      return this;
   }

   public boolean getCompleteDdl() {
      return this.completeDdl;
   }

   public SchemaTool setIncludeAutoStart(boolean include) {
      this.includeAutoStart = include;
      return this;
   }

   public boolean getIncludeAutoStart() {
      return this.includeAutoStart;
   }

   public static enum Mode {
      CREATE_SCHEMA,
      DELETE_SCHEMA,
      CREATE,
      DELETE,
      DELETE_CREATE,
      VALIDATE,
      DATABASE_INFO,
      SCHEMA_INFO;
   }
}
