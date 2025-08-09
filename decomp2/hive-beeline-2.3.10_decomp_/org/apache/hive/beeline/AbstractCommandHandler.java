package org.apache.hive.beeline;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;

public abstract class AbstractCommandHandler implements CommandHandler {
   private final BeeLine beeLine;
   private final String name;
   private final String[] names;
   private final String helpText;
   private Completer[] parameterCompleters = new Completer[0];
   protected transient Throwable lastException;

   public AbstractCommandHandler(BeeLine beeLine, String[] names, String helpText, Completer[] completors) {
      this.beeLine = beeLine;
      this.name = names[0];
      this.names = names;
      this.helpText = helpText;
      if (completors != null && completors.length != 0) {
         List<Completer> c = new LinkedList(Arrays.asList(completors));
         c.add(new NullCompleter());
         this.parameterCompleters = (Completer[])c.toArray(new Completer[0]);
      } else {
         this.parameterCompleters = new Completer[]{new NullCompleter()};
      }

   }

   public String getHelpText() {
      return this.helpText;
   }

   public String getName() {
      return this.name;
   }

   public String[] getNames() {
      return this.names;
   }

   public String matches(String line) {
      if (line != null && line.length() != 0) {
         String[] parts = this.beeLine.split(line);
         if (parts != null && parts.length != 0) {
            for(String name2 : this.names) {
               if (name2.startsWith(parts[0])) {
                  return name2;
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void setParameterCompleters(Completer[] parameterCompleters) {
      this.parameterCompleters = parameterCompleters;
   }

   public Completer[] getParameterCompleters() {
      return this.parameterCompleters;
   }

   public Throwable getLastException() {
      return this.lastException;
   }
}
