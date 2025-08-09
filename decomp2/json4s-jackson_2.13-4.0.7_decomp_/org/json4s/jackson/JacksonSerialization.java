package org.json4s.jackson;

import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.json4s.Formats;
import org.json4s.JsonInput;
import org.json4s.TypeHints;
import org.json4s.Extraction.;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001\u0002\u0006\f\u0001IA\u0001\"\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006E\u0001!\ta\t\u0005\u0006M\u0001!\ta\n\u0005\u0006M\u0001!\t\u0001\u0012\u0005\u0006M\u0001!\t!\u0017\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006O\u0002!\ta\u001c\u0005\u0006s\u0002!\tA\u001f\u0005\u0007s\u0002!\t!a\t\u0003))\u000b7m[:p]N+'/[1mSj\fG/[8o\u0015\taQ\"A\u0004kC\u000e\\7o\u001c8\u000b\u00059y\u0011A\u00026t_:$4OC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0007\n\u0005qi!!D*fe&\fG.\u001b>bi&|g.A\u0006kg>tW*\u001a;i_\u0012\u001c\bCA\u0010!\u001b\u0005Y\u0011BA\u0011\f\u0005-Q5o\u001c8NKRDw\u000eZ:\u0002\rqJg.\u001b;?)\t!S\u0005\u0005\u0002 \u0001!)QD\u0001a\u0001=\u0005)qO]5uKV\u0011\u0001F\u0010\u000b\u0003Si\"\"AK\u001b\u0011\u0005-\u0012dB\u0001\u00171!\tiS#D\u0001/\u0015\ty\u0013#\u0001\u0004=e>|GOP\u0005\u0003cU\ta\u0001\u0015:fI\u00164\u0017BA\u001a5\u0005\u0019\u0019FO]5oO*\u0011\u0011'\u0006\u0005\u0006m\r\u0001\u001daN\u0001\bM>\u0014X.\u0019;t!\tQ\u0002(\u0003\u0002:\u001b\t9ai\u001c:nCR\u001c\b\"B\u001e\u0004\u0001\u0004a\u0014!A1\u0011\u0005urD\u0002\u0001\u0003\u0006\u007f\r\u0011\r\u0001\u0011\u0002\u0002\u0003F\u0011\u0011i\u0005\t\u0003)\tK!aQ\u000b\u0003\u000f9{G\u000f[5oOV\u0019QI\u0016%\u0015\u0007\u0019#v\u000b\u0006\u0002H'B\u0011Q\b\u0013\u0003\u0006\u0013\u0012\u0011\rA\u0013\u0002\u0002/F\u0011\u0011i\u0013\t\u0003\u0019Fk\u0011!\u0014\u0006\u0003\u001d>\u000b!![8\u000b\u0003A\u000bAA[1wC&\u0011!+\u0014\u0002\u0007/JLG/\u001a:\t\u000bY\"\u00019A\u001c\t\u000bm\"\u0001\u0019A+\u0011\u0005u2F!B \u0005\u0005\u0004\u0001\u0005\"\u0002-\u0005\u0001\u00049\u0015aA8viV\u0011!L\u0019\u000b\u00047\u0002\u001cGC\u0001/`!\t!R,\u0003\u0002_+\t!QK\\5u\u0011\u00151T\u0001q\u00018\u0011\u0015YT\u00011\u0001b!\ti$\rB\u0003@\u000b\t\u0007\u0001\tC\u0003Y\u000b\u0001\u0007A\r\u0005\u0002MK&\u0011a-\u0014\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\foJLG/\u001a)sKR$\u00180\u0006\u0002j]R\u0011!\u000e\u001c\u000b\u0003U-DQA\u000e\u0004A\u0004]BQa\u000f\u0004A\u00025\u0004\"!\u00108\u0005\u000b}2!\u0019\u0001!\u0016\u0007A<8\u000fF\u0002rkb$\"A\u001d;\u0011\u0005u\u001aH!B%\b\u0005\u0004Q\u0005\"\u0002\u001c\b\u0001\b9\u0004\"B\u001e\b\u0001\u00041\bCA\u001fx\t\u0015ytA1\u0001A\u0011\u0015Av\u00011\u0001s\u0003\u0011\u0011X-\u00193\u0016\u0005mtHc\u0001?\u0002\u001aQ)Q0a\u0002\u0002\nA\u0011QH \u0003\u0006\u007f!\u0011\ra`\t\u0004\u0003\u0006\u0005\u0001c\u0001\u000b\u0002\u0004%\u0019\u0011QA\u000b\u0003\u0007\u0005s\u0017\u0010C\u00037\u0011\u0001\u000fq\u0007C\u0004\u0002\f!\u0001\u001d!!\u0004\u0002\u000554\u0007#BA\b\u0003+iXBAA\t\u0015\r\t\u0019\"F\u0001\be\u00164G.Z2u\u0013\u0011\t9\"!\u0005\u0003\u00115\u000bg.\u001b4fgRDq!a\u0007\t\u0001\u0004\ti\"\u0001\u0003kg>t\u0007c\u0001\u000e\u0002 %\u0019\u0011\u0011E\u0007\u0003\u0013)\u001bxN\\%oaV$X\u0003BA\u0013\u0003W!B!a\n\u00024Q1\u0011\u0011FA\u0017\u0003_\u00012!PA\u0016\t\u0015y\u0014B1\u0001\u0000\u0011\u00151\u0014\u0002q\u00018\u0011\u001d\tY!\u0003a\u0002\u0003c\u0001b!a\u0004\u0002\u0016\u0005%\u0002bBA\u001b\u0013\u0001\u0007\u0011qG\u0001\u0003S:\u00042\u0001TA\u001d\u0013\r\tY$\u0014\u0002\u0007%\u0016\fG-\u001a:"
)
public class JacksonSerialization implements org.json4s.Serialization {
   private final JsonMethods jsonMethods;

   public Object read(final String json, final Formats formats, final Manifest mf) {
      return org.json4s.Serialization.read$(this, json, formats, mf);
   }

   public Formats formats(final TypeHints hints) {
      return org.json4s.Serialization.formats$(this, hints);
   }

   public String write(final Object a, final Formats formats) {
      return this.jsonMethods.mapper().writeValueAsString(.MODULE$.decompose(a, formats));
   }

   public Writer write(final Object a, final Writer out, final Formats formats) {
      this.jsonMethods.mapper().writeValue(out, .MODULE$.decompose(a, formats));
      return out;
   }

   public void write(final Object a, final OutputStream out, final Formats formats) {
      this.jsonMethods.mapper().writeValue(out, .MODULE$.decompose(a, formats));
   }

   public String writePretty(final Object a, final Formats formats) {
      return this.jsonMethods.mapper().writerWithDefaultPrettyPrinter().writeValueAsString(.MODULE$.decompose(a, formats));
   }

   public Writer writePretty(final Object a, final Writer out, final Formats formats) {
      this.jsonMethods.mapper().writerWithDefaultPrettyPrinter().writeValue(out, .MODULE$.decompose(a, formats));
      return out;
   }

   public Object read(final JsonInput json, final Formats formats, final Manifest mf) {
      return org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(this.jsonMethods.parse(json, formats.wantsBigDecimal(), formats.wantsBigInt(), org.json4s.AsJsonInput..MODULE$.identity())), formats, mf);
   }

   public Object read(final Reader in, final Formats formats, final Manifest mf) {
      return org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(this.jsonMethods.parse(in, formats.wantsBigDecimal(), formats.wantsBigInt(), org.json4s.AsJsonInput..MODULE$.readerAsJsonInput())), formats, mf);
   }

   public JacksonSerialization(final JsonMethods jsonMethods) {
      this.jsonMethods = jsonMethods;
      org.json4s.Serialization.$init$(this);
   }
}
