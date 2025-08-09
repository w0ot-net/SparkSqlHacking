package io.vertx.core.impl.launcher.commands;

import io.vertx.core.spi.launcher.DefaultCommandFactory;

public class BareCommandFactory extends DefaultCommandFactory {
   public BareCommandFactory() {
      super(BareCommand.class, BareCommand::new);
   }
}
