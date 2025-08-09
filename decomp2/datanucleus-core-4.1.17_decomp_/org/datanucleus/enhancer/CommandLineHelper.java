package org.datanucleus.enhancer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import org.datanucleus.util.CommandLine;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

class CommandLineHelper {
   public static final NucleusLogger LOGGER = NucleusLogger.getLoggerInstance("DataNucleus.Enhancer");
   private final CommandLine cl = createCommandLine();
   private String[] files;

   public CommandLineHelper(String[] args) {
      this.cl.parse(args);
   }

   private static CommandLine createCommandLine() {
      CommandLine cl = new CommandLine();
      cl.addOption("flf", "fileListFile", "<file-with-list-of-files>", "relative or absolute path to a file containing a list of classes and other files (usually *.jdo) to enhance (one per line) - file is DELETED when read");
      cl.addOption("pu", "persistenceUnit", "<name-of-persistence-unit>", "name of the persistence unit to enhance");
      cl.addOption("dir", "directory", "<name-of-directory>", "name of the directory containing things to enhance");
      cl.addOption("d", "dest", "<directory>", "output directory");
      cl.addOption("checkonly", "checkonly", (String)null, "only check if the class is enhanced");
      cl.addOption("q", "quiet", (String)null, "no output");
      cl.addOption("v", "verbose", (String)null, "verbose output");
      cl.addOption("api", "api", "<api-name>", "API Name (JDO, JPA, etc)");
      cl.addOption("ignoreMetaDataForMissingClasses", "ignoreMetaDataForMissingClasses", (String)null, "Ignore metadata for classes that are missing?");
      cl.addOption("alwaysDetachable", "alwaysDetachable", (String)null, "Always detachable?");
      cl.addOption("generatePK", "generatePK", "<generate-pk>", "Generate PK class where needed?");
      cl.addOption("generateConstructor", "generateConstructor", "<generate-constructor>", "Generate default constructor where needed?");
      cl.addOption("detachListener", "detachListener", "<detach-listener>", "Use Detach Listener?");
      return cl;
   }

   public boolean isQuiet() {
      return this.cl.hasOption("q");
   }

   public boolean isVerbose() {
      return this.cl.hasOption("v");
   }

   public boolean isValidating() {
      return this.cl.hasOption("checkonly");
   }

   public String getPersistenceUnitName() {
      return this.cl.hasOption("pu") ? this.cl.getOptionArg("pu") : null;
   }

   public String getDirectory() {
      return this.cl.hasOption("dir") ? this.cl.getOptionArg("dir") : null;
   }

   public String[] getFiles() {
      if (this.files == null) {
         String fileListFile = this.getFileListFile();
         if (fileListFile != null && !fileListFile.isEmpty()) {
            List<String> fileList = this.readAndDeleteFileListFile();
            this.files = (String[])fileList.toArray(new String[fileList.size()]);
         } else {
            this.files = this.cl.getDefaultArgs();
         }
      }

      return this.files;
   }

   protected String getFileListFile() {
      return this.cl.hasOption("flf") ? this.cl.getOptionArg("flf") : null;
   }

   public DataNucleusEnhancer createDataNucleusEnhancer() {
      String apiName = this.cl.hasOption("api") ? this.cl.getOptionArg("api") : "JDO";
      Properties props = new Properties();
      props.setProperty("datanucleus.plugin.allowUserBundles", "true");
      if (this.cl.hasOption("alwaysDetachable")) {
         props.setProperty("datanucleus.metadata.alwaysDetachable", "true");
      }

      if (this.cl.hasOption("ignoreMetaDataForMissingClasses")) {
         props.setProperty("datanucleus.metadata.ignoreMetaDataForMissingClasses", "true");
      }

      DataNucleusEnhancer enhancer = new DataNucleusEnhancer(apiName, props);
      this.configureQuietAndVerbose(enhancer);
      this.logEnhancerVersion(enhancer, apiName);
      this.configureDestination(enhancer);
      this.configureGenerateConstructor(enhancer);
      this.configureGeneratePK(enhancer);
      this.configureDetachListener(enhancer);
      this.logClasspath(enhancer);
      return enhancer;
   }

   private void configureQuietAndVerbose(DataNucleusEnhancer enhancer) {
      if (!this.isQuiet()) {
         if (this.isVerbose()) {
            enhancer.setVerbose(true);
         }

         enhancer.setSystemOut(true);
      }

   }

   private void configureDestination(DataNucleusEnhancer enhancer) {
      if (this.cl.hasOption("d")) {
         String destination = this.cl.getOptionArg("d");
         File tmp = new File(destination);
         if (tmp.exists()) {
            if (!tmp.isDirectory()) {
               System.err.println(destination + " is not directory. please set directory.");
               System.exit(1);
            }
         } else {
            tmp.mkdirs();
         }

         enhancer.setOutputDirectory(destination);
      }

   }

   private void configureGenerateConstructor(DataNucleusEnhancer enhancer) {
      if (this.cl.hasOption("generateConstructor")) {
         String val = this.cl.getOptionArg("generateConstructor");
         if (val.equalsIgnoreCase("false")) {
            enhancer.setGenerateConstructor(false);
         }
      }

   }

   private void configureGeneratePK(DataNucleusEnhancer enhancer) {
      if (this.cl.hasOption("generatePK")) {
         String val = this.cl.getOptionArg("generatePK");
         if (val.equalsIgnoreCase("false")) {
            enhancer.setGeneratePK(false);
         }
      }

   }

   private void configureDetachListener(DataNucleusEnhancer enhancer) {
      if (this.cl.hasOption("detachListener")) {
         String val = this.cl.getOptionArg("detachListener");
         if (val.equalsIgnoreCase("true")) {
            enhancer.setDetachListener(true);
         }
      }

   }

   private void logEnhancerVersion(DataNucleusEnhancer enhancer, String apiName) {
      String msg = Localiser.msg("005000", enhancer.getEnhancerVersion(), apiName);
      LOGGER.info(msg);
      if (!this.isQuiet()) {
         System.out.println(msg);
      }

   }

   private void logClasspath(DataNucleusEnhancer enhancer) {
      LOGGER.debug(Localiser.msg("005001"));
      if (enhancer.isVerbose()) {
         System.out.println(Localiser.msg("005001"));
      }

      StringTokenizer tokeniser = new StringTokenizer(System.getProperty("java.class.path"), File.pathSeparator);

      while(tokeniser.hasMoreTokens()) {
         String entry = Localiser.msg("005002", tokeniser.nextToken());
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(entry);
         }

         if (enhancer.isVerbose()) {
            System.out.println(entry);
         }
      }

      if (enhancer.isVerbose()) {
         System.out.flush();
      }

   }

   private List readAndDeleteFileListFile() {
      String fileListFile = this.getFileListFile();
      if (this.isVerbose()) {
         System.out.println("Reading fileListFile: " + fileListFile);
      }

      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Reading fileListFile: " + fileListFile);
      }

      File flf = new File(fileListFile);
      if (!flf.isFile()) {
         System.err.println(fileListFile + " is not an existing file. please set fileListFile (argument '-flf') to a valid file path.");
         System.exit(2);
      }

      List<String> result = new ArrayList();

      try {
         InputStream in = new FileInputStream(flf);

         try {
            BufferedReader r = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line;
            while(null != (line = r.readLine())) {
               line = line.trim();
               if (!line.isEmpty() && !line.startsWith("#")) {
                  result.add(line);
               }
            }

            r.close();
         } finally {
            in.close();
         }

         flf.delete();
      } catch (IOException e) {
         System.err.println(fileListFile + " could not be read: " + e.getMessage());
         System.exit(3);
      }

      return result;
   }
}
