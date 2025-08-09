package io.vertx.core.spi.launcher;

import io.vertx.core.cli.CLIException;

public interface Command {
   void setUp(ExecutionContext var1) throws CLIException;

   void run() throws CLIException;

   void tearDown() throws CLIException;
}
