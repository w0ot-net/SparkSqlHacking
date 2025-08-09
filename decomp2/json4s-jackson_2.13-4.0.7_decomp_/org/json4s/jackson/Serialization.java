package org.json4s.jackson;

import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.json4s.Formats;
import org.json4s.JsonInput;
import org.json4s.TypeHints;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAE\u0001\u0005\u0002M\tQbU3sS\u0006d\u0017N_1uS>t'BA\u0003\u0007\u0003\u001dQ\u0017mY6t_:T!a\u0002\u0005\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005I\u0011aA8sO\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u0005!!!D*fe&\fG.\u001b>bi&|gn\u0005\u0002\u0002\u001fA\u0011A\u0002E\u0005\u0003#\u0011\u0011ACS1dWN|gnU3sS\u0006d\u0017N_1uS>t\u0017A\u0002\u001fj]&$h\bF\u0001\f\u0001"
)
public final class Serialization {
   public static Object read(final Reader in, final Formats formats, final Manifest mf) {
      return Serialization$.MODULE$.read(in, formats, mf);
   }

   public static Object read(final JsonInput json, final Formats formats, final Manifest mf) {
      return Serialization$.MODULE$.read(json, formats, mf);
   }

   public static Writer writePretty(final Object a, final Writer out, final Formats formats) {
      return Serialization$.MODULE$.writePretty(a, out, formats);
   }

   public static String writePretty(final Object a, final Formats formats) {
      return Serialization$.MODULE$.writePretty(a, formats);
   }

   public static void write(final Object a, final OutputStream out, final Formats formats) {
      Serialization$.MODULE$.write(a, out, formats);
   }

   public static Writer write(final Object a, final Writer out, final Formats formats) {
      return Serialization$.MODULE$.write(a, out, formats);
   }

   public static String write(final Object a, final Formats formats) {
      return Serialization$.MODULE$.write(a, formats);
   }

   public static Formats formats(final TypeHints hints) {
      return Serialization$.MODULE$.formats(hints);
   }

   public static Object read(final String json, final Formats formats, final Manifest mf) {
      return Serialization$.MODULE$.read(json, formats, mf);
   }
}
