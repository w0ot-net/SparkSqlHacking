package scala.xml.parsing;

import java.io.File;
import scala.io.Source;
import scala.io.Source.;

public final class ConstructingParser$ {
   public static final ConstructingParser$ MODULE$ = new ConstructingParser$();

   public ConstructingParser fromFile(final File inp, final boolean preserveWS) {
      return (ConstructingParser)(new ConstructingParser(.MODULE$.fromFile(inp, scala.io.Codec..MODULE$.fallbackSystemCodec()), preserveWS)).initialize();
   }

   public ConstructingParser fromSource(final Source inp, final boolean preserveWS) {
      return (ConstructingParser)(new ConstructingParser(inp, preserveWS)).initialize();
   }

   private ConstructingParser$() {
   }
}
