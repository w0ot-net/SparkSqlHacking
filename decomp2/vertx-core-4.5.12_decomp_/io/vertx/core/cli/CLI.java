package io.vertx.core.cli;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.cli.impl.DefaultCLI;
import java.util.List;

@VertxGen
public interface CLI {
   static CLI create(String name) {
      return (new DefaultCLI()).setName(name);
   }

   @GenIgnore
   static CLI create(Class clazz) {
      return CLIConfigurator.define(clazz);
   }

   CommandLine parse(List var1);

   CommandLine parse(List var1, boolean var2);

   String getName();

   @Fluent
   CLI setName(String var1);

   @Nullable String getDescription();

   @Fluent
   CLI setDescription(String var1);

   @Nullable String getSummary();

   @Fluent
   CLI setSummary(String var1);

   boolean isHidden();

   @Fluent
   CLI setHidden(boolean var1);

   List getOptions();

   @Fluent
   CLI addOption(Option var1);

   @Fluent
   CLI addOptions(List var1);

   @Fluent
   CLI setOptions(List var1);

   List getArguments();

   @Fluent
   CLI addArgument(Argument var1);

   @Fluent
   CLI addArguments(List var1);

   @Fluent
   CLI setArguments(List var1);

   @Nullable Option getOption(String var1);

   @Nullable Argument getArgument(String var1);

   @Nullable Argument getArgument(int var1);

   @Fluent
   CLI removeOption(String var1);

   @Fluent
   CLI removeArgument(int var1);

   @GenIgnore
   CLI usage(StringBuilder var1);

   @GenIgnore
   CLI usage(StringBuilder var1, String var2);

   int getPriority();

   @Fluent
   CLI setPriority(int var1);
}
