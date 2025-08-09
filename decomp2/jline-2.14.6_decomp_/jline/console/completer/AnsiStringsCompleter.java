package jline.console.completer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import jline.internal.Ansi;
import jline.internal.Preconditions;

public class AnsiStringsCompleter implements Completer {
   private final SortedMap strings;

   public AnsiStringsCompleter() {
      this.strings = new TreeMap();
   }

   public AnsiStringsCompleter(Collection strings) {
      this.strings = new TreeMap();
      Preconditions.checkNotNull(strings);

      for(String str : strings) {
         this.strings.put(Ansi.stripAnsi(str), str);
      }

   }

   public AnsiStringsCompleter(String... strings) {
      this((Collection)Arrays.asList(strings));
   }

   public Collection getStrings() {
      return this.strings.values();
   }

   public int complete(String buffer, int cursor, List candidates) {
      Preconditions.checkNotNull(candidates);
      if (buffer == null) {
         candidates.addAll(this.strings.values());
      } else {
         buffer = Ansi.stripAnsi(buffer);

         for(Map.Entry match : this.strings.tailMap(buffer).entrySet()) {
            if (!((String)match.getKey()).startsWith(buffer)) {
               break;
            }

            candidates.add(match.getValue());
         }
      }

      return candidates.isEmpty() ? -1 : 0;
   }
}
