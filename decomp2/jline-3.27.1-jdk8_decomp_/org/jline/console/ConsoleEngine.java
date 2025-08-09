package org.jline.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.jline.reader.LineReader;
import org.jline.reader.Widget;

public interface ConsoleEngine extends CommandRegistry {
   String VAR_NANORC = "NANORC";

   static String plainCommand(String command) {
      return command.startsWith(":") ? command.substring(1) : command;
   }

   void setLineReader(LineReader var1);

   void setSystemRegistry(SystemRegistry var1);

   Object[] expandParameters(String[] var1) throws Exception;

   String expandCommandLine(String var1);

   String expandToList(List var1);

   Map scripts();

   void setScriptExtension(String var1);

   boolean hasAlias(String var1);

   String getAlias(String var1);

   Map getPipes();

   List getNamedPipes();

   List scriptCompleters();

   void persist(Path var1, Object var2);

   Object slurp(Path var1) throws IOException;

   Object consoleOption(String var1, Object var2);

   void setConsoleOption(String var1, Object var2);

   Object execute(String var1, String var2, String[] var3) throws Exception;

   default Object execute(File script) throws Exception {
      return this.execute(script, "", new String[0]);
   }

   Object execute(File var1, String var2, String[] var3) throws Exception;

   ExecutionResult postProcess(String var1, Object var2, String var3);

   ExecutionResult postProcess(Object var1);

   void trace(Object var1);

   void println(Object var1);

   void putVariable(String var1, Object var2);

   Object getVariable(String var1);

   boolean hasVariable(String var1);

   void purge();

   boolean executeWidget(Object var1);

   boolean isExecuting();

   public static class ExecutionResult {
      final int status;
      final Object result;

      public ExecutionResult(int status, Object result) {
         this.status = status;
         this.result = result;
      }

      public int status() {
         return this.status;
      }

      public Object result() {
         return this.result;
      }
   }

   public static class WidgetCreator implements Widget {
      private final ConsoleEngine consoleEngine;
      private final Object function;
      private final String name;

      public WidgetCreator(ConsoleEngine consoleEngine, String function) {
         this.consoleEngine = consoleEngine;
         this.name = function;
         this.function = consoleEngine.getVariable(function);
      }

      public boolean apply() {
         return this.consoleEngine.executeWidget(this.function);
      }

      public String toString() {
         return this.name;
      }
   }
}
