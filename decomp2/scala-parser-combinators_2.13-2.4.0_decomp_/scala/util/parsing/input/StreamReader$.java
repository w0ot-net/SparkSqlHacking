package scala.util.parsing.input;

public final class StreamReader$ {
   public static final StreamReader$ MODULE$ = new StreamReader$();

   public final char EofCh() {
      return '\u001a';
   }

   public StreamReader apply(final java.io.Reader in) {
      return new StreamReader(PagedSeq$.MODULE$.fromReader(in), 0, 1);
   }

   private StreamReader$() {
   }
}
