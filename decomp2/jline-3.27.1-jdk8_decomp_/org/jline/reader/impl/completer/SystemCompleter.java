package org.jline.reader.impl.completer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.utils.AttributedString;

public class SystemCompleter implements Completer {
   private Map completers = new HashMap();
   private Map aliasCommand = new HashMap();
   private StringsCompleter commands;
   private boolean compiled = false;

   public void complete(LineReader reader, ParsedLine commandLine, List candidates) {
      if (!this.compiled) {
         throw new IllegalStateException();
      } else {
         assert commandLine != null;

         assert candidates != null;

         if (commandLine.words().size() > 0) {
            if (commandLine.words().size() == 1) {
               String buffer = (String)commandLine.words().get(0);
               int eq = buffer.indexOf(61);
               if (eq < 0) {
                  this.commands.complete(reader, commandLine, candidates);
               } else if (reader.getParser().validVariableName(buffer.substring(0, eq))) {
                  String curBuf = buffer.substring(0, eq + 1);

                  for(String c : this.completers.keySet()) {
                     candidates.add(new Candidate(AttributedString.stripAnsi(curBuf + c), c, (String)null, (String)null, (String)null, (String)null, true));
                  }
               }
            } else {
               String cmd = reader.getParser().getCommand((String)commandLine.words().get(0));
               if (this.command(cmd) != null) {
                  ((Completer)((List)this.completers.get(this.command(cmd))).get(0)).complete(reader, commandLine, candidates);
               }
            }
         }

      }
   }

   public boolean isCompiled() {
      return this.compiled;
   }

   private String command(String cmd) {
      String out = null;
      if (cmd != null) {
         if (this.completers.containsKey(cmd)) {
            out = cmd;
         } else {
            out = (String)this.aliasCommand.get(cmd);
         }
      }

      return out;
   }

   public void add(String command, List completers) {
      for(Completer c : completers) {
         this.add(command, c);
      }

   }

   public void add(List commands, Completer completer) {
      for(String c : commands) {
         this.add(c, completer);
      }

   }

   public void add(String command, Completer completer) {
      Objects.requireNonNull(command);
      if (this.compiled) {
         throw new IllegalStateException();
      } else {
         if (!this.completers.containsKey(command)) {
            this.completers.put(command, new ArrayList());
         }

         if (completer instanceof ArgumentCompleter) {
            ((ArgumentCompleter)completer).setStrictCommand(false);
         }

         ((List)this.completers.get(command)).add(completer);
      }
   }

   public void add(SystemCompleter other) {
      if (other.isCompiled()) {
         throw new IllegalStateException();
      } else {
         for(Map.Entry entry : other.getCompleters().entrySet()) {
            for(Completer c : (List)entry.getValue()) {
               this.add((String)entry.getKey(), c);
            }
         }

         this.addAliases(other.getAliases());
      }
   }

   public void addAliases(Map aliasCommand) {
      if (this.compiled) {
         throw new IllegalStateException();
      } else {
         this.aliasCommand.putAll(aliasCommand);
      }
   }

   private Map getAliases() {
      return this.aliasCommand;
   }

   public void compile() {
      if (!this.compiled) {
         Map<String, List<Completer>> compiledCompleters = new HashMap();

         for(Map.Entry entry : this.completers.entrySet()) {
            if (((List)entry.getValue()).size() == 1) {
               compiledCompleters.put((String)entry.getKey(), (List)entry.getValue());
            } else {
               compiledCompleters.put((String)entry.getKey(), new ArrayList());
               ((List)compiledCompleters.get(entry.getKey())).add(new AggregateCompleter((Collection)entry.getValue()));
            }
         }

         this.completers = compiledCompleters;
         Set<String> cmds = new HashSet(this.completers.keySet());
         cmds.addAll(this.aliasCommand.keySet());
         this.commands = new StringsCompleter(cmds);
         this.compiled = true;
      }
   }

   public Map getCompleters() {
      return this.completers;
   }
}
