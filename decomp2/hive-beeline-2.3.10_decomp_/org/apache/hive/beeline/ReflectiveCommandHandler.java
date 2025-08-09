package org.apache.hive.beeline;

import jline.console.completer.Completer;

public class ReflectiveCommandHandler extends AbstractCommandHandler {
   private final BeeLine beeLine;

   public ReflectiveCommandHandler(BeeLine beeLine, String[] cmds, Completer[] completer) {
      super(beeLine, cmds, beeLine.loc("help-" + cmds[0]), completer);
      this.beeLine = beeLine;
   }

   public boolean execute(String line) {
      this.lastException = null;
      ClientHook hook = ClientCommandHookFactory.get().getHook(this.beeLine, line);

      try {
         Object ob = this.beeLine.getCommands().getClass().getMethod(this.getName(), String.class).invoke(this.beeLine.getCommands(), line);
         boolean result = ob != null && ob instanceof Boolean && (Boolean)ob;
         if (hook != null && result) {
            hook.postHook(this.beeLine);
         }

         return result;
      } catch (Throwable e) {
         this.lastException = e;
         return this.beeLine.error(e);
      }
   }
}
