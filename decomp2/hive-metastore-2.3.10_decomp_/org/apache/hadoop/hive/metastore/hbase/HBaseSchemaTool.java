package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;

public class HBaseSchemaTool {
   public static void main(String[] args) {
      Options options = new Options();
      OptionBuilder.withLongOpt("help");
      OptionBuilder.withDescription("You're looking at it");
      options.addOption(OptionBuilder.create('h'));
      OptionBuilder.withLongOpt("install");
      OptionBuilder.withDescription("Install the schema onto an HBase cluster.");
      options.addOption(OptionBuilder.create('i'));
      OptionBuilder.withLongOpt("key");
      OptionBuilder.withDescription("Key to scan with.  This should be an exact key (not a regular expression");
      OptionBuilder.hasArg();
      options.addOption(OptionBuilder.create('k'));
      OptionBuilder.withLongOpt("list-tables");
      OptionBuilder.withDescription("List tables in HBase metastore");
      options.addOption(OptionBuilder.create('l'));
      OptionBuilder.withLongOpt("regex-key");
      OptionBuilder.withDescription("Regular expression to scan keys with.");
      OptionBuilder.hasArg();
      options.addOption(OptionBuilder.create('r'));
      OptionBuilder.withLongOpt("table");
      OptionBuilder.withDescription("HBase metastore table to scan");
      OptionBuilder.hasArg();
      options.addOption(OptionBuilder.create('t'));
      CommandLine cli = null;

      try {
         cli = (new GnuParser()).parse(options, args);
      } catch (ParseException e) {
         System.err.println("Parse Exception: " + e.getMessage());
         usage(options);
         return;
      }

      if (cli.hasOption('h')) {
         usage(options);
      } else {
         Configuration conf = new Configuration();
         if (cli.hasOption('i')) {
            (new HBaseSchemaTool()).install(conf, System.err);
         } else {
            String key = null;
            if (cli.hasOption('k')) {
               key = cli.getOptionValue('k');
            }

            String regex = null;
            if (cli.hasOption('r')) {
               regex = cli.getOptionValue('r');
            }

            if (key != null && regex != null) {
               usage(options);
            } else {
               if (key == null && regex == null) {
                  regex = ".*";
               }

               (new HBaseSchemaTool()).go(cli.hasOption('l'), cli.getOptionValue('t'), key, regex, conf, System.out, System.err);
            }
         }
      }
   }

   private static void usage(Options options) {
      HelpFormatter formatter = new HelpFormatter();
      String header = "This tool dumps contents of your hbase metastore.  You need to specify\nthe table to dump.  You can optionally specify a regular expression on the key for\nthe table.  Keep in mind that the key is often a compound.  For partitions regular\nexpressions are not used because non-string values are\nstored in binary.  Instead for partition you can specify as much of the exact prefix as you want.  So you can give dbname.tablename or dbname.tablename.pval1...";
      String footer = "If neither key or regex is provided a regex of .* will be assumed.  You\ncannot set both key and regex.";
      formatter.printHelp("hbaseschematool", header, options, footer);
   }

   @VisibleForTesting
   void go(boolean listTables, String table, String key, String regex, Configuration conf, PrintStream out, PrintStream err) {
      List<String> lines = new ArrayList();
      if (listTables) {
         lines = Arrays.asList(HBaseReadWrite.tableNames);
      } else {
         if (key != null) {
            key = key.replace('.', '\u0001');
         }

         try {
            HBaseReadWrite.setConf(conf);
            HBaseReadWrite hrw = HBaseReadWrite.getInstance();
            if (table.equalsIgnoreCase("HBMS_DBS")) {
               if (key != null) {
                  lines.add(hrw.printDatabase(key));
               } else {
                  lines.addAll(hrw.printDatabases(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_FUNCS")) {
               if (key != null) {
                  lines.add(hrw.printFunction(key));
               } else {
                  lines.addAll(hrw.printFunctions(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_GLOBAL_PRIVS")) {
               lines.add(hrw.printGlobalPrivs());
            } else if (table.equalsIgnoreCase("HBMS_PARTITIONS")) {
               if (key != null) {
                  lines.add(hrw.printPartition(key));
               } else {
                  lines.addAll(hrw.printPartitions(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_USER_TO_ROLE")) {
               if (key != null) {
                  lines.add(hrw.printRolesForUser(key));
               } else {
                  lines.addAll(hrw.printRolesForUsers(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_ROLES")) {
               if (key != null) {
                  lines.add(hrw.printRole(key));
               } else {
                  lines.addAll(hrw.printRoles(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_TBLS")) {
               if (key != null) {
                  lines.add(hrw.printTable(key));
               } else {
                  lines.addAll(hrw.printTables(regex));
               }
            } else if (table.equalsIgnoreCase("HBMS_SDS")) {
               if (key != null) {
                  lines.add(hrw.printStorageDescriptor(Base64.decodeBase64(key)));
               } else {
                  lines.addAll(hrw.printStorageDescriptors());
               }
            } else if (table.equalsIgnoreCase("HBMS_SECURITY")) {
               lines.addAll(hrw.printSecurity());
            } else {
               if (!table.equalsIgnoreCase("HBMS_SEQUENCES")) {
                  err.println("Unknown table: " + table);
                  return;
               }

               lines.addAll(hrw.printSequences());
            }
         } catch (Exception e) {
            err.println("Caught exception " + e.getClass() + " with message: " + e.getMessage());
            return;
         }
      }

      for(String line : lines) {
         out.println(line);
      }

   }

   @VisibleForTesting
   void install(Configuration conf, PrintStream err) {
      try {
         HBaseReadWrite.setConf(conf);
         HBaseReadWrite.createTablesIfNotExist();
      } catch (Exception e) {
         err.println("Caught exception " + e.getClass() + " with message: " + e.getMessage());
      }
   }
}
