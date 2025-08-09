package org.jline.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jline.reader.impl.completer.SystemCompleter;
import org.jline.terminal.Terminal;

public interface CommandRegistry {
   static SystemCompleter aggregateCompleters(CommandRegistry... commandRegistries) {
      SystemCompleter out = new SystemCompleter();

      for(CommandRegistry r : commandRegistries) {
         out.add(r.compileCompleters());
      }

      return out;
   }

   static SystemCompleter compileCompleters(CommandRegistry... commandRegistries) {
      SystemCompleter out = aggregateCompleters(commandRegistries);
      out.compile();
      return out;
   }

   default String name() {
      return this.getClass().getSimpleName();
   }

   Set commandNames();

   Map commandAliases();

   List commandInfo(String var1);

   boolean hasCommand(String var1);

   SystemCompleter compileCompleters();

   CmdDesc commandDescription(List var1);

   default Object invoke(CommandSession session, String command, Object... args) throws Exception {
      throw new IllegalStateException("CommandRegistry method invoke(session, command, ... args) is not implemented!");
   }

   public static class CommandSession {
      private final Terminal terminal;
      private final InputStream in;
      private final PrintStream out;
      private final PrintStream err;

      public CommandSession() {
         this.in = System.in;
         this.out = System.out;
         this.err = System.err;
         this.terminal = null;
      }

      public CommandSession(Terminal terminal) {
         this(terminal, terminal.input(), new PrintStream(terminal.output()), new PrintStream(terminal.output()));
      }

      public CommandSession(Terminal terminal, InputStream in, PrintStream out, PrintStream err) {
         this.terminal = terminal;
         this.in = in;
         this.out = out;
         this.err = err;
      }

      public Terminal terminal() {
         return this.terminal;
      }

      public InputStream in() {
         return this.in;
      }

      public PrintStream out() {
         return this.out;
      }

      public PrintStream err() {
         return this.err;
      }
   }
}
