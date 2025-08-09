package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class RunCommandFactory extends DefaultCommandFactory {
   public RunCommandFactory() {
      super(RunCommand.class, RunCommand::new);
   }
}
