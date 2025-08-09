package org.apache.hive.beeline;

import jline.console.completer.Completer;

interface CommandHandler {
   String getName();

   String[] getNames();

   String getHelpText();

   String matches(String var1);

   boolean execute(String var1);

   Completer[] getParameterCompleters();

   Throwable getLastException();
}
