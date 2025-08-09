package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

public class DefaultParser implements CommandLineParser {
   protected CommandLine cmd;
   protected Options options;
   protected boolean stopAtNonOption;
   protected String currentToken;
   protected Option currentOption;
   protected boolean skipParsing;
   protected List expectedOpts;
   private final boolean allowPartialMatching;
   private final Boolean stripLeadingAndTrailingQuotes;
   private final Consumer deprecatedHandler;

   public static Builder builder() {
      return new Builder();
   }

   static int indexOfEqual(String token) {
      return token.indexOf(61);
   }

   public DefaultParser() {
      this.allowPartialMatching = true;
      this.stripLeadingAndTrailingQuotes = null;
      this.deprecatedHandler = CommandLine.Builder.DEPRECATED_HANDLER;
   }

   public DefaultParser(boolean allowPartialMatching) {
      this.allowPartialMatching = allowPartialMatching;
      this.stripLeadingAndTrailingQuotes = null;
      this.deprecatedHandler = CommandLine.Builder.DEPRECATED_HANDLER;
   }

   private DefaultParser(boolean allowPartialMatching, Boolean stripLeadingAndTrailingQuotes, Consumer deprecatedHandler) {
      this.allowPartialMatching = allowPartialMatching;
      this.stripLeadingAndTrailingQuotes = stripLeadingAndTrailingQuotes;
      this.deprecatedHandler = deprecatedHandler;
   }

   private void checkRequiredArgs() throws ParseException {
      if (this.currentOption != null && this.currentOption.requiresArg()) {
         if (!this.isJavaProperty(this.currentOption.getKey()) || this.currentOption.getValuesList().size() != 1) {
            throw new MissingArgumentException(this.currentOption);
         }
      }
   }

   protected void checkRequiredOptions() throws MissingOptionException {
      if (!this.expectedOpts.isEmpty()) {
         throw new MissingOptionException(this.expectedOpts);
      }
   }

   private String getLongPrefix(String token) {
      String t = Util.stripLeadingHyphens(token);
      String opt = null;

      for(int i = t.length() - 2; i > 1; --i) {
         String prefix = t.substring(0, i);
         if (this.options.hasLongOption(prefix)) {
            opt = prefix;
            break;
         }
      }

      return opt;
   }

   private List getMatchingLongOptions(String token) {
      if (this.allowPartialMatching) {
         return this.options.getMatchingOptions(token);
      } else {
         List<String> matches = new ArrayList(1);
         if (this.options.hasLongOption(token)) {
            matches.add(this.options.getOption(token).getLongOpt());
         }

         return matches;
      }
   }

   protected void handleConcatenatedOptions(String token) throws ParseException {
      for(int i = 1; i < token.length(); ++i) {
         String ch = String.valueOf(token.charAt(i));
         if (!this.options.hasOption(ch)) {
            this.handleUnknownToken(this.stopAtNonOption && i > 1 ? token.substring(i) : token);
            break;
         }

         this.handleOption(this.options.getOption(ch));
         if (this.currentOption != null && token.length() != i + 1) {
            this.currentOption.processValue(this.stripLeadingAndTrailingQuotesDefaultOff(token.substring(i + 1)));
            break;
         }
      }

   }

   private void handleLongOption(String token) throws ParseException {
      if (indexOfEqual(token) == -1) {
         this.handleLongOptionWithoutEqual(token);
      } else {
         this.handleLongOptionWithEqual(token);
      }

   }

   private void handleLongOptionWithEqual(String token) throws ParseException {
      int pos = indexOfEqual(token);
      String value = token.substring(pos + 1);
      String opt = token.substring(0, pos);
      List<String> matchingOpts = this.getMatchingLongOptions(opt);
      if (matchingOpts.isEmpty()) {
         this.handleUnknownToken(this.currentToken);
      } else {
         if (matchingOpts.size() > 1 && !this.options.hasLongOption(opt)) {
            throw new AmbiguousOptionException(opt, matchingOpts);
         }

         String key = this.options.hasLongOption(opt) ? opt : (String)matchingOpts.get(0);
         Option option = this.options.getOption(key);
         if (option.acceptsArg()) {
            this.handleOption(option);
            this.currentOption.processValue(this.stripLeadingAndTrailingQuotesDefaultOff(value));
            this.currentOption = null;
         } else {
            this.handleUnknownToken(this.currentToken);
         }
      }

   }

   private void handleLongOptionWithoutEqual(String token) throws ParseException {
      List<String> matchingOpts = this.getMatchingLongOptions(token);
      if (matchingOpts.isEmpty()) {
         this.handleUnknownToken(this.currentToken);
      } else {
         if (matchingOpts.size() > 1 && !this.options.hasLongOption(token)) {
            throw new AmbiguousOptionException(token, matchingOpts);
         }

         String key = this.options.hasLongOption(token) ? token : (String)matchingOpts.get(0);
         this.handleOption(this.options.getOption(key));
      }

   }

   private void handleOption(Option option) throws ParseException {
      this.checkRequiredArgs();
      Option copy = (Option)option.clone();
      this.updateRequiredOptions(copy);
      this.cmd.addOption(copy);
      this.currentOption = copy.hasArg() ? copy : null;
   }

   private void handleProperties(Properties properties) throws ParseException {
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
                     opt.processValue(this.stripLeadingAndTrailingQuotesDefaultOff(value));
                  }
               } else if (!"yes".equalsIgnoreCase(value) && !"true".equalsIgnoreCase(value) && !"1".equalsIgnoreCase(value)) {
                  continue;
               }

               this.handleOption(opt);
               this.currentOption = null;
            }
         }

      }
   }

   private void handleShortAndLongOption(String hyphenToken) throws ParseException {
      String token = Util.stripLeadingHyphens(hyphenToken);
      int pos = indexOfEqual(token);
      if (token.length() == 1) {
         if (this.options.hasShortOption(token)) {
            this.handleOption(this.options.getOption(token));
         } else {
            this.handleUnknownToken(hyphenToken);
         }
      } else if (pos == -1) {
         if (this.options.hasShortOption(token)) {
            this.handleOption(this.options.getOption(token));
         } else if (!this.getMatchingLongOptions(token).isEmpty()) {
            this.handleLongOptionWithoutEqual(hyphenToken);
         } else {
            String opt = this.getLongPrefix(token);
            if (opt != null && this.options.getOption(opt).acceptsArg()) {
               this.handleOption(this.options.getOption(opt));
               this.currentOption.processValue(this.stripLeadingAndTrailingQuotesDefaultOff(token.substring(opt.length())));
               this.currentOption = null;
            } else if (this.isJavaProperty(token)) {
               this.handleOption(this.options.getOption(token.substring(0, 1)));
               this.currentOption.processValue(this.stripLeadingAndTrailingQuotesDefaultOff(token.substring(1)));
               this.currentOption = null;
            } else {
               this.handleConcatenatedOptions(hyphenToken);
            }
         }
      } else {
         String opt = token.substring(0, pos);
         String value = token.substring(pos + 1);
         if (opt.length() == 1) {
            Option option = this.options.getOption(opt);
            if (option != null && option.acceptsArg()) {
               this.handleOption(option);
               this.currentOption.processValue(value);
               this.currentOption = null;
            } else {
               this.handleUnknownToken(hyphenToken);
            }
         } else if (this.isJavaProperty(opt)) {
            this.handleOption(this.options.getOption(opt.substring(0, 1)));
            this.currentOption.processValue(opt.substring(1));
            this.currentOption.processValue(value);
            this.currentOption = null;
         } else {
            this.handleLongOptionWithEqual(hyphenToken);
         }
      }

   }

   private void handleToken(String token) throws ParseException {
      if (token != null) {
         this.currentToken = token;
         if (this.skipParsing) {
            this.cmd.addArg(token);
         } else if ("--".equals(token)) {
            this.skipParsing = true;
         } else if (this.currentOption != null && this.currentOption.acceptsArg() && this.isArgument(token)) {
            this.currentOption.processValue(this.stripLeadingAndTrailingQuotesDefaultOn(token));
         } else if (token.startsWith("--")) {
            this.handleLongOption(token);
         } else if (token.startsWith("-") && !"-".equals(token)) {
            this.handleShortAndLongOption(token);
         } else {
            this.handleUnknownToken(token);
         }

         if (this.currentOption != null && !this.currentOption.acceptsArg()) {
            this.currentOption = null;
         }
      }

   }

   private void handleUnknownToken(String token) throws ParseException {
      if (token.startsWith("-") && token.length() > 1 && !this.stopAtNonOption) {
         throw new UnrecognizedOptionException("Unrecognized option: " + token, token);
      } else {
         this.cmd.addArg(token);
         if (this.stopAtNonOption) {
            this.skipParsing = true;
         }

      }
   }

   private boolean isArgument(String token) {
      return !this.isOption(token) || this.isNegativeNumber(token);
   }

   private boolean isJavaProperty(String token) {
      String opt = token.isEmpty() ? null : token.substring(0, 1);
      Option option = this.options.getOption(opt);
      return option != null && (option.getArgs() >= 2 || option.getArgs() == -2);
   }

   private boolean isLongOption(String token) {
      if (token != null && token.startsWith("-") && token.length() != 1) {
         int pos = indexOfEqual(token);
         String t = pos == -1 ? token : token.substring(0, pos);
         if (!this.getMatchingLongOptions(t).isEmpty()) {
            return true;
         } else {
            return this.getLongPrefix(token) != null && !token.startsWith("--");
         }
      } else {
         return false;
      }
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
      if (token != null && token.startsWith("-") && token.length() != 1) {
         int pos = indexOfEqual(token);
         String optName = pos == -1 ? token.substring(1) : token.substring(1, pos);
         if (this.options.hasShortOption(optName)) {
            return true;
         } else {
            return !optName.isEmpty() && this.options.hasShortOption(String.valueOf(optName.charAt(0)));
         }
      } else {
         return false;
      }
   }

   public CommandLine parse(Options options, String[] arguments) throws ParseException {
      return this.parse(options, arguments, (Properties)null);
   }

   public CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException {
      return this.parse(options, arguments, (Properties)null, stopAtNonOption);
   }

   public CommandLine parse(Options options, String[] arguments, Properties properties) throws ParseException {
      return this.parse(options, arguments, properties, false);
   }

   public CommandLine parse(Options options, String[] arguments, Properties properties, boolean stopAtNonOption) throws ParseException {
      this.options = options;
      this.stopAtNonOption = stopAtNonOption;
      this.skipParsing = false;
      this.currentOption = null;
      this.expectedOpts = new ArrayList(options.getRequiredOptions());

      for(OptionGroup group : options.getOptionGroups()) {
         group.setSelected((Option)null);
      }

      this.cmd = CommandLine.builder().setDeprecatedHandler(this.deprecatedHandler).build();
      if (arguments != null) {
         for(String argument : arguments) {
            this.handleToken(argument);
         }
      }

      this.checkRequiredArgs();
      this.handleProperties(properties);
      this.checkRequiredOptions();
      return this.cmd;
   }

   private String stripLeadingAndTrailingQuotesDefaultOff(String token) {
      return this.stripLeadingAndTrailingQuotes != null && this.stripLeadingAndTrailingQuotes ? Util.stripLeadingAndTrailingQuotes(token) : token;
   }

   private String stripLeadingAndTrailingQuotesDefaultOn(String token) {
      return this.stripLeadingAndTrailingQuotes != null && !this.stripLeadingAndTrailingQuotes ? token : Util.stripLeadingAndTrailingQuotes(token);
   }

   private void updateRequiredOptions(Option option) throws AlreadySelectedException {
      if (option.isRequired()) {
         this.expectedOpts.remove(option.getKey());
      }

      if (this.options.getOptionGroup(option) != null) {
         OptionGroup group = this.options.getOptionGroup(option);
         if (group.isRequired()) {
            this.expectedOpts.remove(group);
         }

         group.setSelected(option);
      }

   }

   public static final class Builder {
      private boolean allowPartialMatching;
      private Consumer deprecatedHandler;
      private Boolean stripLeadingAndTrailingQuotes;

      private Builder() {
         this.allowPartialMatching = true;
         this.deprecatedHandler = CommandLine.Builder.DEPRECATED_HANDLER;
      }

      public DefaultParser build() {
         return new DefaultParser(this.allowPartialMatching, this.stripLeadingAndTrailingQuotes, this.deprecatedHandler);
      }

      public Builder setAllowPartialMatching(boolean allowPartialMatching) {
         this.allowPartialMatching = allowPartialMatching;
         return this;
      }

      public Builder setDeprecatedHandler(Consumer deprecatedHandler) {
         this.deprecatedHandler = deprecatedHandler;
         return this;
      }

      public Builder setStripLeadingAndTrailingQuotes(Boolean stripLeadingAndTrailingQuotes) {
         this.stripLeadingAndTrailingQuotes = stripLeadingAndTrailingQuotes;
         return this;
      }
   }
}
