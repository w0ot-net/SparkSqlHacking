package org.json4s.jackson;

import org.json4s.AsJsonInput;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.Option;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public JValue parseJson(final Object in, final boolean useBigDecimalForDouble, final AsJsonInput evidence$1) {
      return .MODULE$.parse(in, useBigDecimalForDouble, .MODULE$.parse$default$3(), evidence$1);
   }

   public boolean parseJson$default$2() {
      return false;
   }

   public Option parseJsonOpt(final Object in, final boolean useBigDecimalForDouble, final AsJsonInput evidence$2) {
      return .MODULE$.parseOpt(in, useBigDecimalForDouble, .MODULE$.parseOpt$default$3(), evidence$2);
   }

   public boolean parseJsonOpt$default$2() {
      return false;
   }

   public JValue renderJValue(final JValue value, final Formats formats) {
      return .MODULE$.render(value, formats.alwaysEscapeUnicode(), formats.emptyValueStrategy());
   }

   public Formats renderJValue$default$2(final JValue value) {
      return org.json4s.DefaultFormats..MODULE$;
   }

   public String compactJson(final JValue d) {
      return .MODULE$.compact(d);
   }

   public String prettyJson(final JValue d) {
      return .MODULE$.pretty(d);
   }

   private package$() {
   }
}
