package io.vertx.core.impl.launcher;

import io.vertx.core.ServiceHelper;
import io.vertx.core.spi.launcher.CommandFactory;
import io.vertx.core.spi.launcher.CommandFactoryLookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceCommandFactoryLoader implements CommandFactoryLookup {
   private Collection commands;

   public ServiceCommandFactoryLoader() {
      this.commands = ServiceHelper.loadFactories(CommandFactory.class, this.getClass().getClassLoader());
   }

   public ServiceCommandFactoryLoader(ClassLoader loader) {
      this.commands = ServiceHelper.loadFactories(CommandFactory.class, loader);
   }

   public Collection lookup() {
      List<CommandFactory<?>> list = new ArrayList();
      this.commands.stream().forEach(list::add);
      return list;
   }
}
