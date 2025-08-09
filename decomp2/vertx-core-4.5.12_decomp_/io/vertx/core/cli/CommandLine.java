package io.vertx.core.cli;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.cli.impl.DefaultCommandLine;
import java.util.List;

@VertxGen
public interface CommandLine {
   static CommandLine create(CLI cli) {
      return new DefaultCommandLine(cli);
   }

   CLI cli();

   List allArguments();

   @Nullable Object getOptionValue(String var1);

   @Nullable Object getArgumentValue(String var1);

   @Nullable Object getArgumentValue(int var1);

   @GenIgnore
   List getOptionValues(String var1);

   @GenIgnore
   List getArgumentValues(int var1);

   boolean isFlagEnabled(String var1);

   boolean isOptionAssigned(Option var1);

   /** @deprecated */
   @Deprecated
   default List getRawValues(Option option) {
      return this.getRawValuesForOption(option);
   }

   List getRawValuesForOption(Option var1);

   List getRawValuesForArgument(Argument var1);

   @Nullable String getRawValueForOption(Option var1);

   boolean acceptMoreValues(Option var1);

   @Nullable String getRawValueForArgument(Argument var1);

   boolean isArgumentAssigned(Argument var1);

   boolean isSeenInCommandLine(Option var1);

   boolean isValid();

   boolean isAskingForHelp();
}
