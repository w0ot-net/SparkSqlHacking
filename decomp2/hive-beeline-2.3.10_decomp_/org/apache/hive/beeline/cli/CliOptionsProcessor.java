package org.apache.hive.beeline.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CliOptionsProcessor {
   private final Options options = new Options();
   private CommandLine commandLine;

   public CliOptionsProcessor() {
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

   public boolean process(String[] argv) {
      try {
         this.commandLine = (new GnuParser()).parse(this.options, argv);
         if (this.commandLine.hasOption("help")) {
            this.printCliUsage();
            return false;
         } else {
            return true;
         }
      } catch (ParseException e) {
         System.err.println(e.getMessage());
         this.printCliUsage();
         return false;
      }
   }

   public void printCliUsage() {
      (new HelpFormatter()).printHelp("hive", this.options);
   }

   public CommandLine getCommandLine() {
      return this.commandLine;
   }

   public void setCommandLine(CommandLine commandLine) {
      this.commandLine = commandLine;
   }
}
