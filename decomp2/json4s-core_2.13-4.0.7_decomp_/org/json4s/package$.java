package org.json4s;

import org.json4s.AsJsonInput.;
import org.json4s.reflect.TypeInfo$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final TypeInfo$ TypeInfo;

   static {
      TypeInfo = TypeInfo$.MODULE$;
   }

   public TypeInfo$ TypeInfo() {
      return TypeInfo;
   }

   public JsonInput convertToJsonInput(final Object input, final AsJsonInput evidence$1) {
      return .MODULE$.asJsonInput(input, evidence$1);
   }

   public JValue jvalue2extractable(final JValue jv) {
      return jv;
   }

   public JValue jvalue2readerSyntax(final JValue j) {
      return j;
   }

   public JValue jvalue2monadic(final JValue jv) {
      return jv;
   }

   public Object jsonwritable(final Object a, final Writer evidence$2) {
      return a;
   }

   private package$() {
   }
}
