package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;

public interface CommandFactory {
   Command create(CommandLine var1);

   CLI define();
}
