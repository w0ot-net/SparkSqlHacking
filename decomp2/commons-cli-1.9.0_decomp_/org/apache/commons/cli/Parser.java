package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

/** @deprecated */
@Deprecated
public abstract class Parser implements CommandLineParser {
   protected CommandLine cmd;
   private Options options;
   private List requiredOptions;

   protected void checkRequiredOptions() throws MissingOptionException {
      if (!this.getRequiredOptions().isEmpty()) {
         throw new MissingOptionException(this.getRequiredOptions());
      }
   }

   protected abstract String[] flatten(Options var1, String[] var2, boolean var3) throws ParseException;

   protected Options getOptions() {
      return this.options;
   }

   protected List getRequiredOptions() {
      return this.requiredOptions;
   }

   public CommandLine parse(Options options, String[] arguments) throws ParseException {
      return this.parse(options, arguments, (Properties)null, false);
   }

   public CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException {
      return this.parse(options, arguments, (Properties)null, stopAtNonOption);
   }

   public CommandLine parse(Options options, String[] arguments, Properties properties) throws ParseException {
      return this.parse(options, arguments, properties, false);
   }

   public CommandLine parse(Options options, String[] arguments, Properties properties, boolean stopAtNonOption) throws ParseException {
      for(Option opt : options.helpOptions()) {
         opt.clearValues();
      }

      for(OptionGroup group : options.getOptionGroups()) {
         group.setSelected((Option)null);
      }

      this.setOptions(options);
      this.cmd = CommandLine.builder().build();
      boolean eatTheRest = false;
      List<String> tokenList = Arrays.asList(this.flatten(this.getOptions(), arguments == null ? new String[0] : arguments, stopAtNonOption));
      ListIterator<String> iterator = tokenList.listIterator();

      while(iterator.hasNext()) {
         String token = (String)iterator.next();
         if (token != null) {
            if ("--".equals(token)) {
               eatTheRest = true;
            } else if ("-".equals(token)) {
               if (stopAtNonOption) {
                  eatTheRest = true;
               } else {
                  this.cmd.addArg(token);
               }
            } else if (token.startsWith("-")) {
               if (stopAtNonOption && !this.getOptions().hasOption(token)) {
                  eatTheRest = true;
                  this.cmd.addArg(token);
               } else {
                  this.processOption(token, iterator);
               }
            } else {
               this.cmd.addArg(token);
               if (stopAtNonOption) {
                  eatTheRest = true;
               }
            }

            if (eatTheRest) {
               while(iterator.hasNext()) {
                  String str = (String)iterator.next();
                  if (!"--".equals(str)) {
                     this.cmd.addArg(str);
                  }
               }
            }
         }
      }

      this.processProperties(properties);
      this.checkRequiredOptions();
      return this.cmd;
   }

   public void processArgs(Option opt, ListIterator iter) throws ParseException {
      while(true) {
         if (iter.hasNext()) {
            String str = (String)iter.next();
            if (this.getOptions().hasOption(str) && str.startsWith("-")) {
               iter.previous();
            } else {
               try {
                  opt.processValue(Util.stripLeadingAndTrailingQuotes(str));
                  continue;
               } catch (RuntimeException var5) {
                  iter.previous();
               }
            }
         }

         if (opt.getValues() == null && !opt.hasOptionalArg()) {
            throw new MissingArgumentException(opt);
         }

         return;
      }
   }

   protected void processOption(String arg, ListIterator iter) throws ParseException {
      boolean hasOption = this.getOptions().hasOption(arg);
      if (!hasOption) {
         throw new UnrecognizedOptionException("Unrecognized option: " + arg, arg);
      } else {
         Option opt = (Option)this.getOptions().getOption(arg).clone();
         this.updateRequiredOptions(opt);
         if (opt.hasArg()) {
            this.processArgs(opt, iter);
         }

         this.cmd.addOption(opt);
      }
   }

   protected void processProperties(Properties properties) throws ParseException {
      if (properties != null) {
         Enumeration<?> e = properties.propertyNames();

         while(e.hasMoreElements()) {
            String option = e.nextElement().toString();
            Option opt = this.options.getOption(option);
            if (opt == null) {
               throw new UnrecognizedOptionException("Default option wasn't defined", option);
            }

            OptionGroup group = this.options.getOptionGroup(opt);
            boolean selected = group != null && group.isSelected();
            if (!this.cmd.hasOption(option) && !selected) {
               String value = properties.getProperty(option);
               if (opt.hasArg()) {
                  if (Util.isEmpty((Object[])opt.getValues())) {
                     try {
                        opt.processValue(value);
                     } catch (RuntimeException var9) {
                     }
                  }
               } else if (!"yes".equalsIgnoreCase(value) && !"true".equalsIgnoreCase(value) && !"1".equalsIgnoreCase(value)) {
                  continue;
               }

               this.cmd.addOption(opt);
               this.updateRequiredOptions(opt);
            }
         }

      }
   }

   protected void setOptions(Options options) {
      this.options = options;
      this.requiredOptions = new ArrayList(options.getRequiredOptions());
   }

   private void updateRequiredOptions(Option opt) throws ParseException {
      if (opt.isRequired()) {
         this.getRequiredOptions().remove(opt.getKey());
      }

      if (this.getOptions().getOptionGroup(opt) != null) {
         OptionGroup group = this.getOptions().getOptionGroup(opt);
         if (group.isRequired()) {
            this.getRequiredOptions().remove(group);
         }

         group.setSelected(opt);
      }

   }
}
