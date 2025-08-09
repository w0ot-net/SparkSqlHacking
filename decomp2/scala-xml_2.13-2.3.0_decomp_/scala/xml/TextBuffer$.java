package scala.xml;

import scala.Predef.;

public final class TextBuffer$ {
   public static final TextBuffer$ MODULE$ = new TextBuffer$();

   public TextBuffer fromString(final String str) {
      return (new TextBuffer()).append(.MODULE$.wrapString(str));
   }

   private TextBuffer$() {
   }
}
