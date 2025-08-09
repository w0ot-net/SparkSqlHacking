package org.apache.hive.beeline;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

class BeeLineCommandCompleter extends AggregateCompleter {
   public BeeLineCommandCompleter(Iterable handlers) {
      super(getCompleters(handlers));
   }

   public static List getCompleters(Iterable handlers) {
      List<Completer> completers = new LinkedList();

      for(CommandHandler handler : handlers) {
         String[] commandNames = handler.getNames();
         if (commandNames != null) {
            for(String commandName : commandNames) {
               List<Completer> compl = new LinkedList();
               compl.add(new StringsCompleter(new String[]{"!" + commandName}));
               compl.addAll(Arrays.asList(handler.getParameterCompleters()));
               compl.add(new NullCompleter());
               completers.add(new AggregateCompleter((Completer[])compl.toArray(new Completer[compl.size()])));
            }
         }
      }

      return completers;
   }
}
