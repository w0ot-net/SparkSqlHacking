package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.cli.impl.ReflectionUtils;
import java.util.function.Supplier;

public class DefaultCommandFactory implements CommandFactory {
   private final Class clazz;
   private final Supplier supplier;

   /** @deprecated */
   @Deprecated
   public DefaultCommandFactory(Class clazz) {
      this(clazz, () -> (Command)ReflectionUtils.newInstance(clazz));
   }

   public DefaultCommandFactory(Class clazz, Supplier supplier) {
      this.clazz = clazz;
      this.supplier = supplier;
   }

   public Command create(CommandLine cl) {
      return (Command)this.supplier.get();
   }

   public CLI define() {
      return CLIConfigurator.define(this.clazz);
   }
}
