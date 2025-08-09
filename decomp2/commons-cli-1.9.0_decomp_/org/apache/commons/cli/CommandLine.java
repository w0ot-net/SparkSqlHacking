package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CommandLine implements Serializable {
   private static final long serialVersionUID = 1L;
   private final List args;
   private final List options;
   private final transient Consumer deprecatedHandler;

   public static Builder builder() {
      return new Builder();
   }

   protected CommandLine() {
      this(new LinkedList(), new ArrayList(), CommandLine.Builder.DEPRECATED_HANDLER);
   }

   private CommandLine(List args, List options, Consumer deprecatedHandler) {
      this.args = (List)Objects.requireNonNull(args, "args");
      this.options = (List)Objects.requireNonNull(options, "options");
      this.deprecatedHandler = deprecatedHandler;
   }

   protected void addArg(String arg) {
      if (arg != null) {
         this.args.add(arg);
      }

   }

   protected void addOption(Option opt) {
      if (opt != null) {
         this.options.add(opt);
      }

   }

   private Object get(Supplier supplier) {
      return supplier == null ? null : supplier.get();
   }

   public List getArgList() {
      return this.args;
   }

   public String[] getArgs() {
      return (String[])this.args.toArray(Util.EMPTY_STRING_ARRAY);
   }

   /** @deprecated */
   @Deprecated
   public Object getOptionObject(char opt) {
      return this.getOptionObject(String.valueOf(opt));
   }

   /** @deprecated */
   @Deprecated
   public Object getOptionObject(String opt) {
      try {
         return this.getParsedOptionValue(opt);
      } catch (ParseException pe) {
         System.err.println("Exception found converting " + opt + " to desired type: " + pe.getMessage());
         return null;
      }
   }

   public Properties getOptionProperties(Option option) {
      Properties props = new Properties();

      for(Option processedOption : this.options) {
         if (processedOption.equals(option)) {
            this.processPropertiesFromValues(props, processedOption.getValuesList());
         }
      }

      return props;
   }

   public Properties getOptionProperties(String opt) {
      Properties props = new Properties();

      for(Option option : this.options) {
         if (opt.equals(option.getOpt()) || opt.equals(option.getLongOpt())) {
            this.processPropertiesFromValues(props, option.getValuesList());
         }
      }

      return props;
   }

   public Option[] getOptions() {
      return (Option[])this.options.toArray(Option.EMPTY_ARRAY);
   }

   public String getOptionValue(char opt) {
      return this.getOptionValue(String.valueOf(opt));
   }

   public String getOptionValue(char opt, String defaultValue) {
      return this.getOptionValue((String)String.valueOf(opt), (Supplier)(() -> defaultValue));
   }

   public String getOptionValue(char opt, Supplier defaultValue) {
      return this.getOptionValue(String.valueOf(opt), defaultValue);
   }

   public String getOptionValue(Option option) {
      String[] values = this.getOptionValues(option);
      return values == null ? null : values[0];
   }

   public String getOptionValue(Option option, String defaultValue) {
      return this.getOptionValue((Option)option, (Supplier)(() -> defaultValue));
   }

   public String getOptionValue(Option option, Supplier defaultValue) {
      String answer = this.getOptionValue(option);
      return answer != null ? answer : (String)this.get(defaultValue);
   }

   public String getOptionValue(OptionGroup optionGroup) {
      String[] values = this.getOptionValues(optionGroup);
      return values == null ? null : values[0];
   }

   public String getOptionValue(OptionGroup optionGroup, String defaultValue) {
      return this.getOptionValue((OptionGroup)optionGroup, (Supplier)(() -> defaultValue));
   }

   public String getOptionValue(OptionGroup optionGroup, Supplier defaultValue) {
      String answer = this.getOptionValue(optionGroup);
      return answer != null ? answer : (String)this.get(defaultValue);
   }

   public String getOptionValue(String opt) {
      return this.getOptionValue(this.resolveOption(opt));
   }

   public String getOptionValue(String opt, String defaultValue) {
      return this.getOptionValue((Option)this.resolveOption(opt), (Supplier)(() -> defaultValue));
   }

   public String getOptionValue(String opt, Supplier defaultValue) {
      return this.getOptionValue(this.resolveOption(opt), defaultValue);
   }

   public String[] getOptionValues(char opt) {
      return this.getOptionValues(String.valueOf(opt));
   }

   public String[] getOptionValues(Option option) {
      if (option == null) {
         return null;
      } else {
         List<String> values = new ArrayList();

         for(Option processedOption : this.options) {
            if (processedOption.equals(option)) {
               if (option.isDeprecated()) {
                  this.handleDeprecated(option);
               }

               values.addAll(processedOption.getValuesList());
            }
         }

         return values.isEmpty() ? null : (String[])values.toArray(Util.EMPTY_STRING_ARRAY);
      }
   }

   public String[] getOptionValues(OptionGroup optionGroup) {
      return optionGroup != null && optionGroup.isSelected() ? this.getOptionValues(optionGroup.getSelected()) : null;
   }

   public String[] getOptionValues(String opt) {
      return this.getOptionValues(this.resolveOption(opt));
   }

   public Object getParsedOptionValue(char opt) throws ParseException {
      return this.getParsedOptionValue(String.valueOf(opt));
   }

   public Object getParsedOptionValue(char opt, Supplier defaultValue) throws ParseException {
      return this.getParsedOptionValue(String.valueOf(opt), defaultValue);
   }

   public Object getParsedOptionValue(char opt, Object defaultValue) throws ParseException {
      return this.getParsedOptionValue(String.valueOf(opt), defaultValue);
   }

   public Object getParsedOptionValue(Option option) throws ParseException {
      return this.getParsedOptionValue((Option)option, (Supplier)(() -> null));
   }

   public Object getParsedOptionValue(Option option, Supplier defaultValue) throws ParseException {
      if (option == null) {
         return this.get(defaultValue);
      } else {
         String res = this.getOptionValue(option);

         try {
            return res == null ? this.get(defaultValue) : option.getConverter().apply(res);
         } catch (Throwable e) {
            throw ParseException.wrap(e);
         }
      }
   }

   public Object getParsedOptionValue(Option option, Object defaultValue) throws ParseException {
      return this.getParsedOptionValue((Option)option, (Supplier)(() -> defaultValue));
   }

   public Object getParsedOptionValue(OptionGroup optionGroup) throws ParseException {
      return this.getParsedOptionValue((OptionGroup)optionGroup, (Supplier)(() -> null));
   }

   public Object getParsedOptionValue(OptionGroup optionGroup, Supplier defaultValue) throws ParseException {
      return optionGroup != null && optionGroup.isSelected() ? this.getParsedOptionValue(optionGroup.getSelected(), defaultValue) : this.get(defaultValue);
   }

   public Object getParsedOptionValue(OptionGroup optionGroup, Object defaultValue) throws ParseException {
      return this.getParsedOptionValue((OptionGroup)optionGroup, (Supplier)(() -> defaultValue));
   }

   public Object getParsedOptionValue(String opt) throws ParseException {
      return this.getParsedOptionValue(this.resolveOption(opt));
   }

   public Object getParsedOptionValue(String opt, Supplier defaultValue) throws ParseException {
      return this.getParsedOptionValue(this.resolveOption(opt), defaultValue);
   }

   public Object getParsedOptionValue(String opt, Object defaultValue) throws ParseException {
      return this.getParsedOptionValue(this.resolveOption(opt), defaultValue);
   }

   private void handleDeprecated(Option option) {
      if (this.deprecatedHandler != null) {
         this.deprecatedHandler.accept(option);
      }

   }

   public boolean hasOption(char opt) {
      return this.hasOption(String.valueOf(opt));
   }

   public boolean hasOption(Option opt) {
      boolean result = this.options.contains(opt);
      if (result && opt.isDeprecated()) {
         this.handleDeprecated(opt);
      }

      return result;
   }

   public boolean hasOption(OptionGroup optionGroup) {
      return optionGroup != null && optionGroup.isSelected() ? this.hasOption(optionGroup.getSelected()) : false;
   }

   public boolean hasOption(String opt) {
      return this.hasOption(this.resolveOption(opt));
   }

   public Iterator iterator() {
      return this.options.iterator();
   }

   private void processPropertiesFromValues(Properties props, List values) {
      for(int i = 0; i < values.size(); i += 2) {
         if (i + 1 < values.size()) {
            props.put(values.get(i), values.get(i + 1));
         } else {
            props.put(values.get(i), "true");
         }
      }

   }

   private Option resolveOption(String opt) {
      String actual = Util.stripLeadingHyphens(opt);
      if (actual != null) {
         for(Option option : this.options) {
            if (actual.equals(option.getOpt()) || actual.equals(option.getLongOpt())) {
               return option;
            }
         }
      }

      return null;
   }

   public static final class Builder {
      static final Consumer DEPRECATED_HANDLER = (o) -> System.out.println(o.toDeprecatedString());
      private final List args = new LinkedList();
      private final List options = new ArrayList();
      private Consumer deprecatedHandler;

      public Builder() {
         this.deprecatedHandler = DEPRECATED_HANDLER;
      }

      public Builder addArg(String arg) {
         if (arg != null) {
            this.args.add(arg);
         }

         return this;
      }

      public Builder addOption(Option opt) {
         if (opt != null) {
            this.options.add(opt);
         }

         return this;
      }

      public CommandLine build() {
         return new CommandLine(this.args, this.options, this.deprecatedHandler);
      }

      public Builder setDeprecatedHandler(Consumer deprecatedHandler) {
         this.deprecatedHandler = deprecatedHandler;
         return this;
      }
   }
}
