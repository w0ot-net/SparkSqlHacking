package org.apache.hadoop.hive.metastore.tools;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.ObjectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveMetaTool {
   private static final Logger LOG = LoggerFactory.getLogger(HiveMetaTool.class.getName());
   private final Options cmdLineOptions = new Options();
   private ObjectStore objStore;
   private boolean isObjStoreInitialized = false;

   private void init() {
      System.out.println("Initializing HiveMetaTool..");
      Option help = new Option("help", "print this message");
      Option listFSRoot = new Option("listFSRoot", "print the current FS root locations");
      OptionBuilder.withArgName("query-string");
      OptionBuilder.hasArgs();
      OptionBuilder.withDescription("execute the given JDOQL query");
      Option executeJDOQL = OptionBuilder.create("executeJDOQL");
      OptionBuilder.withArgName("new-loc> <old-loc");
      OptionBuilder.hasArgs(2);
      OptionBuilder.withDescription("Update FS root location in the metastore to new location.Both new-loc and old-loc should be valid URIs with valid host names and schemes.When run with the dryRun option changes are displayed but are not persisted. When run with the serdepropKey/tablePropKey option updateLocation looks for the serde-prop-key/table-prop-key that is specified and updates its value if found.");
      Option updateFSRootLoc = OptionBuilder.create("updateLocation");
      Option dryRun = new Option("dryRun", "Perform a dry run of updateLocation changes.When run with the dryRun option updateLocation changes are displayed but not persisted. dryRun is valid only with the updateLocation option.");
      OptionBuilder.withArgName("serde-prop-key");
      OptionBuilder.hasArgs();
      OptionBuilder.withValueSeparator();
      OptionBuilder.withDescription("Specify the key for serde property to be updated. serdePropKey option is valid only with updateLocation option.");
      Option serdePropKey = OptionBuilder.create("serdePropKey");
      OptionBuilder.withArgName("table-prop-key");
      OptionBuilder.hasArg();
      OptionBuilder.withValueSeparator();
      OptionBuilder.withDescription("Specify the key for table property to be updated. tablePropKey option is valid only with updateLocation option.");
      Option tablePropKey = OptionBuilder.create("tablePropKey");
      this.cmdLineOptions.addOption(help);
      this.cmdLineOptions.addOption(listFSRoot);
      this.cmdLineOptions.addOption(executeJDOQL);
      this.cmdLineOptions.addOption(updateFSRootLoc);
      this.cmdLineOptions.addOption(dryRun);
      this.cmdLineOptions.addOption(serdePropKey);
      this.cmdLineOptions.addOption(tablePropKey);
   }

   private void initObjectStore(HiveConf hiveConf) {
      if (!this.isObjStoreInitialized) {
         this.objStore = new ObjectStore();
         this.objStore.setConf(hiveConf);
         this.isObjStoreInitialized = true;
      }

   }

   private void shutdownObjectStore() {
      if (this.isObjStoreInitialized) {
         this.objStore.shutdown();
         this.isObjStoreInitialized = false;
      }

   }

   private void listFSRoot() {
      HiveConf hiveConf = new HiveConf(HiveMetaTool.class);
      this.initObjectStore(hiveConf);
      Set<String> hdfsRoots = this.objStore.listFSRoots();
      if (hdfsRoots != null) {
         System.out.println("Listing FS Roots..");

         for(String s : hdfsRoots) {
            System.out.println(s);
         }
      } else {
         System.err.println("Encountered error during listFSRoot - commit of JDO transaction failed");
      }

   }

   private void executeJDOQLSelect(String query) {
      HiveConf hiveConf = new HiveConf(HiveMetaTool.class);
      this.initObjectStore(hiveConf);
      System.out.println("Executing query: " + query);
      ObjectStore.QueryWrapper queryWrapper = new ObjectStore.QueryWrapper();

      try {
         Collection<?> result = this.objStore.executeJDOQLSelect(query, queryWrapper);
         if (result != null) {
            for(Object o : result) {
               System.out.println(o.toString());
            }
         } else {
            System.err.println("Encountered error during executeJDOQLSelect -commit of JDO transaction failed.");
         }
      } finally {
         queryWrapper.close();
      }

   }

   private long executeJDOQLUpdate(String query) {
      HiveConf hiveConf = new HiveConf(HiveMetaTool.class);
      this.initObjectStore(hiveConf);
      System.out.println("Executing query: " + query);
      long numUpdated = this.objStore.executeJDOQLUpdate(query);
      if (numUpdated >= 0L) {
         System.out.println("Number of records updated: " + numUpdated);
      } else {
         System.err.println("Encountered error during executeJDOQL -commit of JDO transaction failed.");
      }

      return numUpdated;
   }

   private int printUpdateLocations(Map updateLocations) {
      int count = 0;

      for(String key : updateLocations.keySet()) {
         String value = (String)updateLocations.get(key);
         System.out.println("old location: " + key + " new location: " + value);
         ++count;
      }

      return count;
   }

   private void printTblURIUpdateSummary(ObjectStore.UpdateMStorageDescriptorTblURIRetVal retVal, boolean isDryRun) {
      String tblName = new String("SDS");
      String fieldName = new String("LOCATION");
      if (retVal == null) {
         System.err.println("Encountered error while executing updateMStorageDescriptorTblURI - commit of JDO transaction failed. Failed to update FSRoot locations in " + fieldName + "field in " + tblName + " table.");
      } else {
         Map<String, String> updateLocations = retVal.getUpdateLocations();
         if (isDryRun) {
            System.out.println("Dry Run of updateLocation on table " + tblName + "..");
         } else {
            System.out.println("Successfully updated the following locations..");
         }

         int count = this.printUpdateLocations(updateLocations);
         if (isDryRun) {
            System.out.println("Found " + count + " records in " + tblName + " table to update");
         } else {
            System.out.println("Updated " + count + " records in " + tblName + " table");
         }

         List<String> badRecords = retVal.getBadRecords();
         if (badRecords.size() > 0) {
            System.err.println("Warning: Found records with bad " + fieldName + " in " + tblName + " table.. ");

            for(String badRecord : badRecords) {
               System.err.println("bad location URI: " + badRecord);
            }
         }

         int numNullRecords = retVal.getNumNullRecords();
         if (numNullRecords != 0) {
            LOG.debug("Number of NULL location URI: " + numNullRecords + ". This can happen for View or Index.");
         }
      }

   }

   private void printDatabaseURIUpdateSummary(ObjectStore.UpdateMDatabaseURIRetVal retVal, boolean isDryRun) {
      String tblName = new String("DBS");
      String fieldName = new String("LOCATION_URI");
      if (retVal == null) {
         System.err.println("Encountered error while executing updateMDatabaseURI - commit of JDO transaction failed. Failed to update FSRoot locations in " + fieldName + "field in " + tblName + " table.");
      } else {
         Map<String, String> updateLocations = retVal.getUpdateLocations();
         if (isDryRun) {
            System.out.println("Dry Run of updateLocation on table " + tblName + "..");
         } else {
            System.out.println("Successfully updated the following locations..");
         }

         int count = this.printUpdateLocations(updateLocations);
         if (isDryRun) {
            System.out.println("Found " + count + " records in " + tblName + " table to update");
         } else {
            System.out.println("Updated " + count + " records in " + tblName + " table");
         }

         List<String> badRecords = retVal.getBadRecords();
         if (badRecords.size() > 0) {
            System.err.println("Warning: Found records with bad " + fieldName + " in " + tblName + " table.. ");

            for(String badRecord : badRecords) {
               System.err.println("bad location URI: " + badRecord);
            }
         }
      }

   }

   private void printPropURIUpdateSummary(ObjectStore.UpdatePropURIRetVal retVal, String tablePropKey, boolean isDryRun, String tblName, String methodName) {
      if (retVal == null) {
         System.err.println("Encountered error while executing " + methodName + " - commit of JDO transaction failed. Failed to update FSRoot locations in value field corresponding to" + tablePropKey + " in " + tblName + " table.");
      } else {
         Map<String, String> updateLocations = retVal.getUpdateLocations();
         if (isDryRun) {
            System.out.println("Dry Run of updateLocation on table " + tblName + "..");
         } else {
            System.out.println("Successfully updated the following locations..");
         }

         int count = this.printUpdateLocations(updateLocations);
         if (isDryRun) {
            System.out.println("Found " + count + " records in " + tblName + " table to update");
         } else {
            System.out.println("Updated " + count + " records in " + tblName + " table");
         }

         List<String> badRecords = retVal.getBadRecords();
         if (badRecords.size() > 0) {
            System.err.println("Warning: Found records with bad " + tablePropKey + " key in " + tblName + " table.. ");

            for(String badRecord : badRecords) {
               System.err.println("bad location URI: " + badRecord);
            }
         }
      }

   }

   private void printSerdePropURIUpdateSummary(ObjectStore.UpdateSerdeURIRetVal retVal, String serdePropKey, boolean isDryRun) {
      String tblName = new String("SERDE_PARAMS");
      if (retVal == null) {
         System.err.println("Encountered error while executing updateSerdeURI - commit of JDO transaction failed. Failed to update FSRoot locations in value field corresponding to " + serdePropKey + " in " + tblName + " table.");
      } else {
         Map<String, String> updateLocations = retVal.getUpdateLocations();
         if (isDryRun) {
            System.out.println("Dry Run of updateLocation on table " + tblName + "..");
         } else {
            System.out.println("Successfully updated the following locations..");
         }

         int count = this.printUpdateLocations(updateLocations);
         if (isDryRun) {
            System.out.println("Found " + count + " records in " + tblName + " table to update");
         } else {
            System.out.println("Updated " + count + " records in " + tblName + " table");
         }

         List<String> badRecords = retVal.getBadRecords();
         if (badRecords.size() > 0) {
            System.err.println("Warning: Found records with bad " + serdePropKey + " key in " + tblName + " table.. ");

            for(String badRecord : badRecords) {
               System.err.println("bad location URI: " + badRecord);
            }
         }
      }

   }

   public void updateFSRootLocation(URI oldURI, URI newURI, String serdePropKey, String tablePropKey, boolean isDryRun) {
      HiveConf hiveConf = new HiveConf(HiveMetaTool.class);
      this.initObjectStore(hiveConf);
      System.out.println("Looking for LOCATION_URI field in DBS table to update..");
      ObjectStore.UpdateMDatabaseURIRetVal updateMDBURIRetVal = this.objStore.updateMDatabaseURI(oldURI, newURI, isDryRun);
      this.printDatabaseURIUpdateSummary(updateMDBURIRetVal, isDryRun);
      System.out.println("Looking for LOCATION field in SDS table to update..");
      ObjectStore.UpdateMStorageDescriptorTblURIRetVal updateTblURIRetVal = this.objStore.updateMStorageDescriptorTblURI(oldURI, newURI, isDryRun);
      this.printTblURIUpdateSummary(updateTblURIRetVal, isDryRun);
      if (tablePropKey != null) {
         System.out.println("Looking for value of " + tablePropKey + " key in TABLE_PARAMS table to update..");
         ObjectStore.UpdatePropURIRetVal updateTblPropURIRetVal = this.objStore.updateTblPropURI(oldURI, newURI, tablePropKey, isDryRun);
         this.printPropURIUpdateSummary(updateTblPropURIRetVal, tablePropKey, isDryRun, "TABLE_PARAMS", "updateTblPropURI");
         System.out.println("Looking for value of " + tablePropKey + " key in SD_PARAMS table to update..");
         ObjectStore.UpdatePropURIRetVal updatePropURIRetVal = this.objStore.updateMStorageDescriptorTblPropURI(oldURI, newURI, tablePropKey, isDryRun);
         this.printPropURIUpdateSummary(updatePropURIRetVal, tablePropKey, isDryRun, "SD_PARAMS", "updateMStorageDescriptorTblPropURI");
      }

      if (serdePropKey != null) {
         System.out.println("Looking for value of " + serdePropKey + " key in SERDE_PARAMS table to update..");
         ObjectStore.UpdateSerdeURIRetVal updateSerdeURIretVal = this.objStore.updateSerdeURI(oldURI, newURI, serdePropKey, isDryRun);
         this.printSerdePropURIUpdateSummary(updateSerdeURIretVal, serdePropKey, isDryRun);
      }

   }

   private static void printAndExit(HiveMetaTool metaTool) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("metatool", metaTool.cmdLineOptions);
      System.exit(1);
   }

   public static void main(String[] args) {
      HiveMetaTool metaTool = new HiveMetaTool();
      metaTool.init();
      CommandLineParser parser = new GnuParser();
      CommandLine line = null;

      try {
         try {
            line = parser.parse(metaTool.cmdLineOptions, args);
         } catch (ParseException e) {
            System.err.println("HiveMetaTool:Parsing failed.  Reason: " + e.getLocalizedMessage());
            printAndExit(metaTool);
         }

         if (line.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("metatool", metaTool.cmdLineOptions);
         } else if (line.hasOption("listFSRoot")) {
            if (line.hasOption("dryRun")) {
               System.err.println("HiveMetaTool: dryRun is not valid with listFSRoot");
               printAndExit(metaTool);
            } else if (line.hasOption("serdePropKey")) {
               System.err.println("HiveMetaTool: serdePropKey is not valid with listFSRoot");
               printAndExit(metaTool);
            } else if (line.hasOption("tablePropKey")) {
               System.err.println("HiveMetaTool: tablePropKey is not valid with listFSRoot");
               printAndExit(metaTool);
            }

            metaTool.listFSRoot();
         } else if (line.hasOption("executeJDOQL")) {
            String query = line.getOptionValue("executeJDOQL");
            if (line.hasOption("dryRun")) {
               System.err.println("HiveMetaTool: dryRun is not valid with executeJDOQL");
               printAndExit(metaTool);
            } else if (line.hasOption("serdePropKey")) {
               System.err.println("HiveMetaTool: serdePropKey is not valid with executeJDOQL");
               printAndExit(metaTool);
            } else if (line.hasOption("tablePropKey")) {
               System.err.println("HiveMetaTool: tablePropKey is not valid with executeJDOQL");
               printAndExit(metaTool);
            }

            if (query.toLowerCase().trim().startsWith("select")) {
               metaTool.executeJDOQLSelect(query);
            } else if (query.toLowerCase().trim().startsWith("update")) {
               metaTool.executeJDOQLUpdate(query);
            } else {
               System.err.println("HiveMetaTool:Unsupported statement type");
               printAndExit(metaTool);
            }
         } else if (line.hasOption("updateLocation")) {
            String[] loc = line.getOptionValues("updateLocation");
            boolean isDryRun = false;
            String serdepropKey = null;
            String tablePropKey = null;
            if (loc.length != 2 && loc.length != 3) {
               System.err.println("HiveMetaTool:updateLocation takes in 2 required and 1 optional arguments but was passed " + loc.length + " arguments");
               printAndExit(metaTool);
            }

            Path newPath = new Path(loc[0]);
            Path oldPath = new Path(loc[1]);
            URI oldURI = oldPath.toUri();
            URI newURI = newPath.toUri();
            if (line.hasOption("dryRun")) {
               isDryRun = true;
            }

            if (line.hasOption("serdePropKey")) {
               serdepropKey = line.getOptionValue("serdePropKey");
            }

            if (line.hasOption("tablePropKey")) {
               tablePropKey = line.getOptionValue("tablePropKey");
            }

            if (oldURI.getHost() != null && newURI.getHost() != null) {
               if (oldURI.getScheme() != null && newURI.getScheme() != null) {
                  metaTool.updateFSRootLocation(oldURI, newURI, serdepropKey, tablePropKey, isDryRun);
               } else {
                  System.err.println("HiveMetaTool:A valid scheme is required in both old-loc and new-loc");
               }
            } else {
               System.err.println("HiveMetaTool:A valid host is required in both old-loc and new-loc");
            }
         } else {
            if (line.hasOption("dryRun")) {
               System.err.println("HiveMetaTool: dryRun is not a valid standalone option");
            } else if (line.hasOption("serdePropKey")) {
               System.err.println("HiveMetaTool: serdePropKey is not a valid standalone option");
            } else if (line.hasOption("tablePropKey")) {
               System.err.println("HiveMetaTool: tablePropKey is not a valid standalone option");
               printAndExit(metaTool);
            } else {
               System.err.print("HiveMetaTool:Parsing failed.  Reason: Invalid arguments: ");

               for(String s : line.getArgs()) {
                  System.err.print(s + " ");
               }

               System.err.println();
            }

            printAndExit(metaTool);
         }
      } finally {
         metaTool.shutdownObjectStore();
      }

   }
}
