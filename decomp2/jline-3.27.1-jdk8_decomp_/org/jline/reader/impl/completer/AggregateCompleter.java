package org.jline.reader.impl.completer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

public class AggregateCompleter implements Completer {
   private final Collection completers;

   public AggregateCompleter(Completer... completers) {
      this((Collection)Arrays.asList(completers));
   }

   public AggregateCompleter(Collection completers) {
      assert completers != null;

      this.completers = completers;
   }

   public Collection getCompleters() {
      return this.completers;
   }

   public void complete(LineReader reader, ParsedLine line, List candidates) {
      Objects.requireNonNull(line);
      Objects.requireNonNull(candidates);
      this.completers.forEach((c) -> c.complete(reader, line, candidates));
   }

   public String toString() {
      return this.getClass().getSimpleName() + "{completers=" + this.completers + '}';
   }
}
