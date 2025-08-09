package org.json4s;

public final class JsonWriter$ {
   public static final JsonWriter$ MODULE$ = new JsonWriter$();

   public JsonWriter ast() {
      return new JDoubleAstRootJsonWriter();
   }

   public JsonWriter bigDecimalAst() {
      return new JDecimalAstRootJsonWriter();
   }

   public JsonWriter streaming(final java.io.Writer writer, final boolean alwaysEscapeUnicode) {
      boolean x$2 = false;
      int x$4 = RootStreamingJsonWriter$.MODULE$.$lessinit$greater$default$3();
      return new RootStreamingJsonWriter(writer, false, x$4, alwaysEscapeUnicode);
   }

   public JsonWriter streamingPretty(final java.io.Writer writer, final boolean alwaysEscapeUnicode) {
      boolean x$2 = true;
      int x$4 = RootStreamingJsonWriter$.MODULE$.$lessinit$greater$default$3();
      return new RootStreamingJsonWriter(writer, true, x$4, alwaysEscapeUnicode);
   }

   private JsonWriter$() {
   }
}
