package org.apache.hadoop.hive.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.hadoop.hive.common.cli.CommonCliOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionsProcessor {
   protected static final Logger l4j = LoggerFactory.getLogger(OptionsProcessor.class.getName());
   private final Options options = new Options();
   private CommandLine commandLine;
   Map hiveVariables = new HashMap();

   public OptionsProcessor() {
      Options var10000 = this.options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("databasename");
      OptionBuilder.withLongOpt("database");
      OptionBuilder.withDescription("Specify the database to use");
      var10000.addOption(OptionBuilder.create());
      var10000 = this.options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("quoted-query-string");
      OptionBuilder.withDescription("SQL from command line");
      var10000.addOption(OptionBuilder.create('e'));
      var10000 = this.options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("filename");
      OptionBuilder.withDescription("SQL from files");
      var10000.addOption(OptionBuilder.create('f'));
      var10000 = this.options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("filename");
      OptionBuilder.withDescription("Initialization SQL file");
      var10000.addOption(OptionBuilder.create('i'));
      var10000 = this.options;
      OptionBuilder.withValueSeparator();
      OptionBuilder.hasArgs(2);
      OptionBuilder.withArgName("property=value");
      OptionBuilder.withLongOpt("hiveconf");
      OptionBuilder.withDescription("Use value for given property");
      var10000.addOption(OptionBuilder.create());
      var10000 = this.options;
      OptionBuilder.withValueSeparator();
      OptionBuilder.hasArgs(2);
      OptionBuilder.withArgName("key=value");
      OptionBuilder.withLongOpt("define");
      OptionBuilder.withDescription("Variable substitution to apply to Hive commands. e.g. -d A=B or --define A=B");
      var10000.addOption(OptionBuilder.create('d'));
      var10000 = this.options;
      OptionBuilder.withValueSeparator();
      OptionBuilder.hasArgs(2);
      OptionBuilder.withArgName("key=value");
      OptionBuilder.withLongOpt("hivevar");
      OptionBuilder.withDescription("Variable substitution to apply to Hive commands. e.g. --hivevar A=B");
      var10000.addOption(OptionBuilder.create());
      this.options.addOption(new Option("S", "silent", false, "Silent mode in interactive shell"));
      this.options.addOption(new Option("v", "verbose", false, "Verbose mode (echo executed SQL to the console)"));
      this.options.addOption(new Option("H", "help", false, "Print help information"));
   }

   public boolean process_stage1(String[] argv) {
      try {
         this.commandLine = (new GnuParser()).parse(this.options, argv);
         Properties confProps = this.commandLine.getOptionProperties("hiveconf");

         for(String propKey : confProps.stringPropertyNames()) {
            if (propKey.equalsIgnoreCase("hive.root.logger")) {
               CommonCliOptions.splitAndSetLogger(propKey, confProps);
            } else {
               System.setProperty(propKey, confProps.getProperty(propKey));
            }
         }

         Properties hiveVars = this.commandLine.getOptionProperties("define");

         for(String propKey : hiveVars.stringPropertyNames()) {
            this.hiveVariables.put(propKey, hiveVars.getProperty(propKey));
         }

         Properties hiveVars2 = this.commandLine.getOptionProperties("hivevar");

         for(String propKey : hiveVars2.stringPropertyNames()) {
            this.hiveVariables.put(propKey, hiveVars2.getProperty(propKey));
         }

         return true;
      } catch (ParseException e) {
         System.err.println(e.getMessage());
         this.printUsage();
         return false;
      }
   }

   public boolean process_stage2(CliSessionState ss) {
      ss.getConf();
      if (this.commandLine.hasOption('H')) {
         this.printUsage();
         return false;
      } else {
         ss.setIsSilent(this.commandLine.hasOption('S'));
         ss.database = this.commandLine.getOptionValue("database");
         ss.execString = this.commandLine.getOptionValue('e');
         ss.fileName = this.commandLine.getOptionValue('f');
         ss.setIsVerbose(this.commandLine.hasOption('v'));
         String[] initFiles = this.commandLine.getOptionValues('i');
         if (null != initFiles) {
            ss.initFiles = Arrays.asList(initFiles);
         }

         if (ss.execString != null && ss.fileName != null) {
            System.err.println("The '-e' and '-f' options cannot be specified simultaneously");
            this.printUsage();
            return false;
         } else {
            if (this.commandLine.hasOption("hiveconf")) {
               Properties confProps = this.commandLine.getOptionProperties("hiveconf");

               for(String propKey : confProps.stringPropertyNames()) {
                  ss.cmdProperties.setProperty(propKey, confProps.getProperty(propKey));
               }
            }

            return true;
         }
      }
   }

   private void printUsage() {
      (new HelpFormatter()).printHelp("hive", this.options);
   }

   public Map getHiveVariables() {
      return this.hiveVariables;
   }
}
