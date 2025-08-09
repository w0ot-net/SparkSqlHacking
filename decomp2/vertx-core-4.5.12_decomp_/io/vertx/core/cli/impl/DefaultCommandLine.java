package io.vertx.core.cli.impl;

import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CLIException;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.InvalidValueException;
import io.vertx.core.cli.Option;
import io.vertx.core.cli.TypedArgument;
import io.vertx.core.cli.TypedOption;
import io.vertx.core.cli.converters.Converters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCommandLine implements CommandLine {
   protected final CLI cli;
   protected List allArgs = new ArrayList();
   protected Map optionValues = new HashMap();
   protected List optionsSeenInCommandLine = new ArrayList();
   protected Map argumentValues = new HashMap();
   protected boolean valid;

   public DefaultCommandLine(CLI cli) {
      this.cli = cli;
   }

   public CLI cli() {
      return this.cli;
   }

   public List allArguments() {
      return this.allArgs;
   }

   public CommandLine addArgumentValue(String argument) {
      this.allArgs.add(argument);
      return this;
   }

   public Object getOptionValue(String name) {
      Option option = this.cli.getOption(name);
      if (option == null) {
         return null;
      } else {
         return option instanceof TypedOption ? this.getValue((TypedOption)option) : this.getRawValueForOption(option);
      }
   }

   public boolean isFlagEnabled(String name) {
      Option option = this.cli.getOption(name);
      if (option == null) {
         throw new IllegalArgumentException("Cannot find the option '" + name + "'");
      } else if (option.isFlag()) {
         return this.optionsSeenInCommandLine.contains(option);
      } else {
         throw new IllegalStateException("Cannot retrieve the flag value on a non-flag option (" + name + ")");
      }
   }

   public List getOptionValues(String name) {
      Option option = this.cli.getOption(name);
      if (option == null) {
         return null;
      } else if (option instanceof TypedOption) {
         TypedOption<T> typed = (TypedOption)option;
         return typed.isParsedAsList() ? createFromList(this.getRawValueForOption(option), typed) : (List)this.getRawValuesForOption(option).stream().map((s) -> create(s, typed)).collect(Collectors.toList());
      } else {
         return this.getRawValuesForOption(option);
      }
   }

   public List getArgumentValues(int index) {
      Argument argument = this.cli.getArgument(index);
      if (argument == null) {
         return null;
      } else if (argument instanceof TypedArgument) {
         TypedArgument<T> typed = (TypedArgument)argument;
         return (List)this.getRawValuesForArgument(typed).stream().map((s) -> create(s, typed)).collect(Collectors.toList());
      } else {
         return this.getRawValuesForArgument(argument);
      }
   }

   public Object getArgumentValue(String name) {
      Argument arg = this.cli.getArgument(name);
      return arg == null ? null : this.getArgumentValue(arg.getIndex());
   }

   public Object getArgumentValue(int index) {
      Argument arg = this.cli.getArgument(index);
      if (arg == null) {
         return null;
      } else {
         return arg instanceof TypedArgument ? create(this.getRawValueForArgument(arg), (TypedArgument)arg) : this.getRawValueForArgument(arg);
      }
   }

   public boolean isOptionAssigned(Option option) {
      return !this.getRawValuesForOption(option).isEmpty();
   }

   public List getRawValuesForOption(Option option) {
      List<?> list = (List)this.optionValues.get(option);
      return list != null ? (List)list.stream().map(Object::toString).collect(Collectors.toList()) : Collections.emptyList();
   }

   public List getRawValuesForArgument(Argument argument) {
      List<?> list = (List)this.argumentValues.get(argument);
      return list != null ? (List)list.stream().map(Object::toString).collect(Collectors.toList()) : Collections.emptyList();
   }

   public DefaultCommandLine addRawValue(Option option, String value) {
      if (!this.acceptMoreValues(option) && !option.isFlag()) {
         throw new CLIException("The option " + option.getName() + " does not accept value or has already been set");
      } else if (!option.getChoices().isEmpty() && !option.getChoices().contains(value)) {
         throw new InvalidValueException(option, value);
      } else {
         List<String> list = (List)this.optionValues.get(option);
         if (list == null) {
            list = new ArrayList();
            this.optionValues.put(option, list);
         }

         list.add(value);
         return this;
      }
   }

   public String getRawValueForOption(Option option) {
      return this.isOptionAssigned(option) ? (String)this.getRawValuesForOption(option).get(0) : option.getDefaultValue();
   }

   public boolean acceptMoreValues(Option option) {
      return option.isMultiValued() || option.isSingleValued() && !this.isOptionAssigned(option);
   }

   public String getRawValueForArgument(Argument arg) {
      List values = (List)this.argumentValues.get(arg);
      return values != null && !values.isEmpty() ? values.get(0).toString() : arg.getDefaultValue();
   }

   public DefaultCommandLine setRawValue(Argument arg, String rawValue) {
      List<String> list = (List)this.argumentValues.get(arg);
      if (list == null) {
         list = new ArrayList();
         this.argumentValues.put(arg, list);
      }

      list.add(rawValue);
      return this;
   }

   public boolean isArgumentAssigned(Argument arg) {
      return this.argumentValues.get(arg) != null;
   }

   public DefaultCommandLine setSeenInCommandLine(Option option) {
      this.optionsSeenInCommandLine.add(option);
      return this;
   }

   public boolean isSeenInCommandLine(Option option) {
      return this.optionsSeenInCommandLine.contains(option);
   }

   private Object getValue(TypedOption option) {
      if (this.isOptionAssigned(option)) {
         return create(this.getRawValueForOption(option), option);
      } else if (option.getDefaultValue() != null) {
         return create(this.getRawValueForOption(option), option);
      } else if (!option.isFlag() && !this.isBoolean(option)) {
         return null;
      } else {
         try {
            return this.isSeenInCommandLine(option) ? Boolean.TRUE : Boolean.FALSE;
         } catch (InvalidValueException e) {
            throw new IllegalArgumentException(e);
         }
      }
   }

   private boolean isBoolean(TypedOption option) {
      Class type = option.getType();
      return type == Boolean.TYPE || type == Boolean.class;
   }

   public static Object create(String value, TypedArgument argument) {
      Objects.requireNonNull(argument);
      if (value == null) {
         value = argument.getDefaultValue();
      }

      if (value == null) {
         return null;
      } else {
         try {
            return argument.getConverter() != null ? Converters.create(value, argument.getConverter()) : Converters.create(argument.getType(), value);
         } catch (Exception e) {
            throw new InvalidValueException(argument, value, e);
         }
      }
   }

   public static Object create(String value, TypedOption option) {
      Objects.requireNonNull(option);
      if (value == null) {
         value = option.getDefaultValue();
      }

      if (value == null) {
         return null;
      } else {
         try {
            return option.getConverter() != null ? Converters.create(value, option.getConverter()) : Converters.create(option.getType(), value);
         } catch (Exception e) {
            throw new InvalidValueException(option, value, e);
         }
      }
   }

   public static List createFromList(String raw, TypedOption option) {
      if (raw == null) {
         return Collections.emptyList();
      } else {
         String[] segments = raw.split(option.getListSeparator());
         return (List)Arrays.stream(segments).map((s) -> create(s.trim(), option)).collect(Collectors.toList());
      }
   }

   public boolean isValid() {
      return this.valid;
   }

   void setValidity(boolean validity) {
      this.valid = validity;
   }

   public boolean isAskingForHelp() {
      for(Option option : this.cli.getOptions()) {
         if (option.isHelp() && this.isSeenInCommandLine(option)) {
            return true;
         }
      }

      return false;
   }
}
