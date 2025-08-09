package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class StopCommandFactory extends DefaultCommandFactory {
   public StopCommandFactory() {
      super(StopCommand.class, StopCommand::new);
   }
}
