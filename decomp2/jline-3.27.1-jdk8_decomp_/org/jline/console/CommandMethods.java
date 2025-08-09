package org.jline.console;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jline.reader.Completer;

public class CommandMethods {
   Function execute;
   Function compileCompleter;

   public CommandMethods(Function execute, Function compileCompleter) {
      this.execute = execute;
      this.compileCompleter = compileCompleter;
   }

   public CommandMethods(Consumer execute, Function compileCompleter) {
      this.execute = (i) -> {
         execute.accept(i);
         return null;
      };
      this.compileCompleter = compileCompleter;
   }

   public Function execute() {
      return this.execute;
   }

   public Function compileCompleter() {
      return this.compileCompleter;
   }
}
