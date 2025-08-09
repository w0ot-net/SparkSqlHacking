package org.json4s.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json4s.Formats;
import org.json4s.jackson.JsonMethods.;

public final class Json$ {
   public static final Json$ MODULE$ = new Json$();

   public ObjectMapper $lessinit$greater$default$2() {
      return .MODULE$.mapper();
   }

   public Json apply(final Formats fmts, final ObjectMapper mapper) {
      return new Json(fmts, mapper);
   }

   public ObjectMapper apply$default$2() {
      return .MODULE$.mapper();
   }

   private Json$() {
   }
}
