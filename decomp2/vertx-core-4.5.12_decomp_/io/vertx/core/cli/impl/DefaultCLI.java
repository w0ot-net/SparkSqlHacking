package io.vertx.core.cli.impl;

import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
import io.vertx.core.cli.UsageMessageFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultCLI implements CLI {
   protected String name;
   protected int priority;
   protected String description;
   protected String summary;
   protected boolean hidden;
   protected List options = new ArrayList();
   private List arguments = new ArrayList();

   public CommandLine parse(List arguments) {
      return (new DefaultParser()).parse(this, arguments);
   }

   public CommandLine parse(List arguments, boolean validate) {
      return (new DefaultParser()).parse(this, arguments, validate);
   }

   public String getName() {
      return this.name;
   }

   public CLI setName(String name) {
      Objects.requireNonNull(name);
      if (name.isEmpty()) {
         throw new IllegalArgumentException("Invalid command name");
      } else {
         this.name = name;
         return this;
      }
   }

   public String getDescription() {
      return this.description;
   }

   public CLI setDescription(String desc) {
      Objects.requireNonNull(desc);
      this.description = desc;
      return this;
   }

   public String getSummary() {
      return this.summary;
   }

   public CLI setSummary(String summary) {
      Objects.requireNonNull(summary);
      this.summary = summary;
      return this;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public CLI setHidden(boolean hidden) {
      this.hidden = hidden;
      return this;
   }

   public List getOptions() {
      return this.options;
   }

   public CLI addOption(Option option) {
      Objects.requireNonNull(option);
      this.options.add(option);
      return this;
   }

   public CLI addOptions(List options) {
      Objects.requireNonNull(options);
      options.forEach(this::addOption);
      return this;
   }

   public CLI setOptions(List options) {
      Objects.requireNonNull(options);
      this.options = new ArrayList();
      return this.addOptions(options);
   }

   public List getArguments() {
      return this.arguments;
   }

   public CLI addArgument(Argument arg) {
      Objects.requireNonNull(arg);
      this.arguments.add(arg);
      return this;
   }

   public CLI addArguments(List args) {
      Objects.requireNonNull(args);
      args.forEach(this::addArgument);
      return this;
   }

   public CLI setArguments(List args) {
      Objects.requireNonNull(args);
      this.arguments = new ArrayList(args);
      return this;
   }

   public Option getOption(String name) {
      Objects.requireNonNull(name);

      for(Predicate equalityCheck : Arrays.asList((optionx) -> name.equals(optionx.getLongName()), (optionx) -> name.equals(optionx.getShortName()), (optionx) -> name.equals(optionx.getArgName()), (optionx) -> name.equalsIgnoreCase(optionx.getLongName()), (optionx) -> name.equalsIgnoreCase(optionx.getShortName()), (optionx) -> name.equalsIgnoreCase(optionx.getArgName()))) {
         for(Option option : this.options) {
            if (equalityCheck.test(option)) {
               return option;
            }
         }
      }

      return null;
   }

   public Argument getArgument(String name) {
      Objects.requireNonNull(name);

      for(Argument arg : this.arguments) {
         if (name.equalsIgnoreCase(arg.getArgName())) {
            return arg;
         }
      }

      return null;
   }

   public Argument getArgument(int index) {
      if (index < 0) {
         throw new IllegalArgumentException("Given index cannot be negative");
      } else {
         for(Argument arg : this.arguments) {
            if (index == arg.getIndex()) {
               return arg;
            }
         }

         return null;
      }
   }

   public CLI removeOption(String name) {
      this.options = (List)this.options.stream().filter((o) -> !o.getLongName().equals(name) && !o.getShortName().equals(name)).collect(Collectors.toList());
      return this;
   }

   public CLI removeArgument(int index) {
      for(Argument arg : new TreeSet(this.arguments)) {
         if (arg.getIndex() == index) {
            this.arguments.remove(arg);
            return this;
         }
      }

      return this;
   }

   public CLI usage(StringBuilder builder) {
      (new UsageMessageFormatter()).usage(builder, this);
      return this;
   }

   public CLI usage(StringBuilder builder, String prefix) {
      (new UsageMessageFormatter()).usage(builder, prefix, this);
      return this;
   }

   public int getPriority() {
      return this.priority;
   }

   public CLI setPriority(int priority) {
      this.priority = priority;
      return this;
   }
}
