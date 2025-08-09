package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class VersionCommandFactory extends DefaultCommandFactory {
   public VersionCommandFactory() {
      super(VersionCommand.class, VersionCommand::new);
   }
}
