package io.vertx.core.cli.impl;

import io.vertx.core.cli.AmbiguousOptionException;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.MissingOptionException;
import io.vertx.core.cli.MissingValueException;
import io.vertx.core.cli.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultParser {
   protected String token;
   protected Option current;
   protected List expectedOpts;
   private DefaultCommandLine commandLine;
   private boolean skipParsing;
   private CLI cli;

   static String stripLeadingHyphens(String str) {
      if (str == null) {
         return null;
      } else if (str.startsWith("--")) {
         return str.substring(2, str.length());
      } else {
         return str.startsWith("-") ? str.substring(1, str.length()) : str;
      }
   }

   static String stripLeadingAndTrailingQuotes(String str) {
      int length = str.length();
      if (length > 1 && str.startsWith("\"") && str.endsWith("\"") && str.substring(1, length - 1).indexOf(34) == -1) {
         str = str.substring(1, length - 1);
      }

      return str;
   }

   public CommandLine parse(CLI cli, List cla) throws CLIException {
      return this.parse(cli, cla, true);
   }

   public CommandLine parse(CLI cli, List cla, boolean validate) throws CLIException {
      this.commandLine = (DefaultCommandLine)CommandLine.create(cli);
      this.current = null;
      this.skipParsing = false;
      this.cli = cli;
      int current = 0;

      for(Argument argument : cli.getArguments()) {
         if (argument.getIndex() == -1) {
            argument.setIndex(current);
            ++current;
         } else {
            current = argument.getIndex() + 1;
         }
      }

      cli.getArguments().sort((o1, o2) -> o1.getIndex() == o2.getIndex() ? 1 : Integer.valueOf(o1.getIndex()).compareTo(o2.getIndex()));
      cli.getOptions().stream().forEach(Option::ensureValidity);
      cli.getArguments().stream().forEach(Argument::ensureValidity);
      this.expectedOpts = this.getRequiredOptions();
      if (cla != null) {
         cla.forEach(this::visit);
      }

      try {
         this.checkRequiredValues();
         this.checkRequiredOptions();
         this.validate();
         this.commandLine.setValidity(true);
      } catch (CLIException e) {
         if (validate && !this.commandLine.isAskingForHelp()) {
            throw e;
         }

         this.commandLine.setValidity(false);
      }

      return this.commandLine;
   }

   protected void validate() throws CLIException {
      boolean multiValue = false;
      List<Integer> usedIndexes = new ArrayList();

      for(Argument argument : this.cli.getArguments()) {
         if (usedIndexes.contains(argument.getIndex())) {
            throw new CLIException("Only one argument can use the index " + argument.getIndex());
         }

         usedIndexes.add(argument.getIndex());
         if (multiValue) {
            throw new CLIException("Only the last argument can be multi-valued");
         }

         multiValue = argument.isMultiValued();
      }

      Iterator<Argument> iterator = this.cli.getArguments().iterator();
      Argument current = null;
      if (iterator.hasNext()) {
         current = (Argument)iterator.next();
      }

      for(String v : this.commandLine.allArguments()) {
         if (current != null) {
            this.commandLine.setRawValue(current, v);
            if (!current.isMultiValued()) {
               if (iterator.hasNext()) {
                  current = (Argument)iterator.next();
               } else {
                  current = null;
               }
            }
         }
      }

      for(Argument arg : this.cli.getArguments()) {
         if (arg.isRequired() && !this.commandLine.isArgumentAssigned(arg)) {
            throw new MissingValueException(arg);
         }
      }

   }

   private List getRequiredOptions() {
      return (List)this.cli.getOptions().stream().filter(Option::isRequired).collect(Collectors.toList());
   }

   private void checkRequiredOptions() throws MissingOptionException {
      if (!this.expectedOpts.isEmpty()) {
         throw new MissingOptionException(this.expectedOpts);
      }
   }

   private void checkRequiredValues() throws MissingValueException {
      if (this.current != null && this.current.acceptValue() && !this.commandLine.isOptionAssigned(this.current) && !this.current.isFlag()) {
         throw new MissingValueException(this.current);
      }
   }

   private void visit(String token) throws CLIException {
      this.token = token;
      if (this.skipParsing) {
         this.commandLine.addArgumentValue(token);
      } else if (token.equals("--")) {
         this.skipParsing = true;
      } else if (this.current != null && this.current.acceptValue() && this.isValue(token)) {
         this.commandLine.addRawValue(this.current, stripLeadingAndTrailingQuotes(token));
      } else if (token.startsWith("--")) {
         this.handleLongOption(token);
      } else if (token.startsWith("-") && !"-".equals(token)) {
         this.handleShortAndLongOption(token);
      } else {
         this.handleArgument(token);
      }

      if (this.current != null && !this.commandLine.acceptMoreValues(this.current)) {
         this.current = null;
      }

   }

   private boolean isValue(String token) {
      return !this.isOption(token) || this.isNegativeNumber(token);
   }

   private boolean isNegativeNumber(String token) {
      try {
         Double.parseDouble(token);
         return true;
      } catch (NumberFormatException var3) {
         return false;
      }
   }

   private boolean isOption(String token) {
      return this.isLongOption(token) || this.isShortOption(token);
   }

   private boolean isShortOption(String token) {
      return token.startsWith("-") && token.length() >= 2 && this.hasOptionWithShortName(token.substring(1, 2));
   }

   private boolean isLongOption(String token) {
      if (token.startsWith("-") && token.length() != 1) {
         int pos = token.indexOf("=");
         String t = pos == -1 ? token : token.substring(0, pos);
         if (!this.getMatchingOptions(t).isEmpty()) {
            return true;
         } else {
            return this.getLongPrefix(token) != null && !token.startsWith("--");
         }
      } else {
         return false;
      }
   }

   private void handleArgument(String token) {
      this.commandLine.addArgumentValue(token);
   }

   private void handleLongOption(String token) throws CLIException {
      if (token.indexOf(61) == -1) {
         this.handleLongOptionWithoutEqual(token);
      } else {
         this.handleLongOptionWithEqual(token);
      }

   }

   private void handleLongOptionWithoutEqual(String token) throws CLIException {
      List<Option> matchingOpts = this.getMatchingOptions(token);
      if (matchingOpts.isEmpty()) {
         this.handleArgument(token);
      } else {
         if (matchingOpts.size() > 1) {
            throw new AmbiguousOptionException(token, matchingOpts);
         }

         Option option = (Option)matchingOpts.get(0);
         this.handleOption(option);
      }

   }

   private void handleLongOptionWithEqual(String token) throws CLIException {
      int pos = token.indexOf(61);
      String value = token.substring(pos + 1);
      String opt = token.substring(0, pos);
      List<Option> matchingOpts = this.getMatchingOptions(opt);
      if (matchingOpts.isEmpty()) {
         this.handleArgument(token);
      } else {
         if (matchingOpts.size() > 1) {
            throw new AmbiguousOptionException(opt, matchingOpts);
         }

         Option option = (Option)matchingOpts.get(0);
         if (!this.commandLine.acceptMoreValues(option)) {
            throw new InvalidValueException(option, value);
         }

         this.handleOption(option);
         this.commandLine.addRawValue(option, value);
         this.current = null;
      }

   }

   private void handleShortAndLongOption(String token) throws CLIException {
      String t = stripLeadingHyphens(token);
      int pos = t.indexOf(61);
      if (t.length() == 1) {
         if (this.hasOptionWithShortName(t)) {
            this.handleOption(this.getOption(t));
         } else {
            this.handleArgument(token);
         }
      } else if (pos == -1) {
         if (this.hasOptionWithShortName(t)) {
            this.handleOption(this.getOption(t));
         } else if (!this.getMatchingOptions(t).isEmpty()) {
            this.handleLongOptionWithoutEqual(token);
         } else {
            String opt = this.getLongPrefix(t);
            if (opt != null) {
               if (!this.commandLine.acceptMoreValues(this.getOption(opt))) {
                  throw new InvalidValueException(this.getOption(opt), t.substring(opt.length()));
               }

               this.handleOption(this.getOption(opt));
               this.commandLine.addRawValue(this.getOption(opt), t.substring(opt.length()));
               this.current = null;
            } else if (this.isAValidShortOption(t)) {
               String strip = t.substring(0, 1);
               Option option = this.getOption(strip);
               this.handleOption(option);
               this.commandLine.addRawValue(this.current, t.substring(1));
               this.current = null;
            } else {
               this.handleConcatenatedOptions(token);
            }
         }
      } else {
         String opt = t.substring(0, pos);
         String value = t.substring(pos + 1);
         if (opt.length() == 1) {
            Option option = this.getOption(opt);
            if (option != null) {
               if (!this.commandLine.acceptMoreValues(option)) {
                  throw new InvalidValueException(option, value);
               }

               this.handleOption(option);
               this.commandLine.addRawValue(option, value);
               this.current = null;
            } else {
               this.handleArgument(token);
            }
         } else if (this.isAValidShortOption(opt) && !this.hasOptionWithLongName(opt)) {
            this.handleOption(this.getOption(opt.substring(0, 1)));
            this.commandLine.addRawValue(this.current, opt.substring(1) + "=" + value);
            this.current = null;
         } else {
            this.handleLongOptionWithEqual(token);
         }
      }

   }

   private String getLongPrefix(String token) {
      String t = stripLeadingHyphens(token);
      String opt = null;

      for(int i = t.length() - 2; i > 1; --i) {
         String prefix = t.substring(0, i);
         if (this.hasOptionWithLongName(prefix)) {
            opt = prefix;
            break;
         }
      }

      return opt;
   }

   private boolean hasOptionWithLongName(String name) {
      for(Option option : this.cli.getOptions()) {
         if (name.equals(option.getLongName())) {
            return true;
         }
      }

      return false;
   }

   private boolean hasOptionWithShortName(String name) {
      for(Option option : this.cli.getOptions()) {
         if (name.equals(option.getShortName())) {
            return true;
         }
      }

      return false;
   }

   private void handleOption(Option option) throws CLIException {
      this.checkRequiredValues();
      this.updateRequiredOptions(option);
      this.commandLine.setSeenInCommandLine(option);
      if (this.commandLine.acceptMoreValues(option)) {
         this.current = option;
      } else {
         this.current = null;
      }

   }

   private void updateRequiredOptions(Option option) {
      if (option.isRequired()) {
         this.expectedOpts.remove(option);
      }

   }

   public Option getOption(String opt) {
      opt = stripLeadingHyphens(opt);

      for(Option option : this.cli.getOptions()) {
         if (opt.equals(option.getShortName()) || opt.equalsIgnoreCase(option.getLongName())) {
            return option;
         }
      }

      return null;
   }

   private boolean isAValidShortOption(String token) {
      String opt = token.substring(0, 1);
      Option option = this.getOption(opt);
      return option != null && this.commandLine.acceptMoreValues(option);
   }

   public List getMatchingOptions(String opt) {
      Objects.requireNonNull(opt);
      opt = stripLeadingHyphens(opt);
      List<Option> matching = new ArrayList();
      List<Option> options = this.cli.getOptions();

      for(Option option : options) {
         if (opt.equals(option.getLongName())) {
            return Collections.singletonList(option);
         }
      }

      for(Option option : options) {
         if (opt.equalsIgnoreCase(option.getLongName())) {
            matching.add(option);
         }
      }

      for(Option option : options) {
         if (option.getLongName() != null && option.getLongName().startsWith(opt)) {
            matching.add(option);
         }
      }

      return matching;
   }

   protected void handleConcatenatedOptions(String token) throws CLIException {
      for(int i = 1; i < token.length(); ++i) {
         String ch = String.valueOf(token.charAt(i));
         if (!this.hasOptionWithShortName(ch)) {
            this.handleArgument(token);
            break;
         }

         this.handleOption(this.getOption(ch));
         if (this.current != null && token.length() != i + 1) {
            this.commandLine.addRawValue(this.current, token.substring(i + 1));
            break;
         }
      }

   }
}
