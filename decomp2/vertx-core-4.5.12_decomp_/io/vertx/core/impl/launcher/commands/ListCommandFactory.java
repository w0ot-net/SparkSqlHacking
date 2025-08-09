package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class ListCommandFactory extends DefaultCommandFactory {
   public ListCommandFactory() {
      super(ListCommand.class, ListCommand::new);
   }
}
