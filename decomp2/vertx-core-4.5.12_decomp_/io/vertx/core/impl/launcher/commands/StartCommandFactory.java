package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class StartCommandFactory extends DefaultCommandFactory {
   public StartCommandFactory() {
      super(StartCommand.class, StartCommand::new);
   }
}
