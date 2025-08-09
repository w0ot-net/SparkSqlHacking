package org.json4s;

import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import org.json4s.AsJsonInput.;
import scala.Option;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c!B\b\u0011\u0003\u0003)\u0002\u0002\u0003\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\t\u000b\u0005\u0002A\u0011\u0001\u0012\t\u000f\u0015\u0002!\u0019)C\nM!1q\u0005\u0001Q\u0001\nuAQ\u0001\u000b\u0001\u0007\u0002%BQ\u0001\u000b\u0001\u0007\u0002\u0019CQ!\u0018\u0001\u0007\u0002yCQ!\u0018\u0001\u0007\u0002\rDQa\u001b\u0001\u0005\u00021DQ! \u0001\u0005\u0002yDq!!\u0006\u0001\r\u0003\t9\u0002C\u0004\u00024\u00011\t!!\u000e\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u0011\u0011\u000b\u0001\u0007\u0002\u0005M#\u0001\u0003&t_:,F/\u001b7\u000b\u0005E\u0011\u0012A\u00026t_:$4OC\u0001\u0014\u0003\ry'oZ\u0002\u0001'\t\u0001a\u0003\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VMZ\u0001\u0005M6$8\u000f\u0005\u0002\u001f?5\t\u0001#\u0003\u0002!!\t9ai\u001c:nCR\u001c\u0018A\u0002\u001fj]&$h\b\u0006\u0002$IA\u0011a\u0004\u0001\u0005\u00069\t\u0001\r!H\u0001\bM>\u0014X.\u0019;t+\u0005i\u0012\u0001\u00034pe6\fGo\u001d\u0011\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005)rDCA\u0016E)\tas\u0007\u0005\u0002.i9\u0011aF\r\t\u0003_ai\u0011\u0001\r\u0006\u0003cQ\ta\u0001\u0010:p_Rt\u0014BA\u001a\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011QG\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005MB\u0002b\u0002\u001d\u0006\u0003\u0003\u0005\u001d!O\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA\u0017;y%\u00111H\u000e\u0002\t\u001b\u0006t\u0017NZ3tiB\u0011QH\u0010\u0007\u0001\t\u0015yTA1\u0001A\u0005\u0005\t\u0015CA!\u0017!\t9\")\u0003\u0002D1\t9aj\u001c;iS:<\u0007\"B#\u0006\u0001\u0004a\u0014!A1\u0016\u0007\u001dK&\nF\u0002I5n#\"!S+\u0011\u0005uRE!B&\u0007\u0005\u0004a%!A,\u0012\u0005\u0005k\u0005C\u0001(T\u001b\u0005y%B\u0001)R\u0003\tIwNC\u0001S\u0003\u0011Q\u0017M^1\n\u0005Q{%AB,sSR,'\u000fC\u0004W\r\u0005\u0005\t9A,\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002.ua\u0003\"!P-\u0005\u000b}2!\u0019\u0001!\t\u000b\u00153\u0001\u0019\u0001-\t\u000bq3\u0001\u0019A%\u0002\u0007=,H/A\u0006xe&$X\r\u0015:fiRLXCA0c)\ta\u0003\rC\u0003F\u000f\u0001\u0007\u0011\r\u0005\u0002>E\u0012)qh\u0002b\u0001\u0001V\u0019A-\u001b4\u0015\u0007\u0015<'\u000e\u0005\u0002>M\u0012)1\n\u0003b\u0001\u0019\")Q\t\u0003a\u0001QB\u0011Q(\u001b\u0003\u0006\u007f!\u0011\r\u0001\u0011\u0005\u00069\"\u0001\r!Z\u0001\u0005e\u0016\fG-\u0006\u0002naR\u0011a\u000e\u001f\u000b\u0003_V\u0004\"!\u00109\u0005\u000b}J!\u0019A9\u0012\u0005\u0005\u0013\bCA\ft\u0013\t!\bDA\u0002B]fDqA^\u0005\u0002\u0002\u0003\u000fq/\u0001\u0006fm&$WM\\2fIM\u00022!\f\u001ep\u0011\u0015I\u0018\u00021\u0001{\u0003\u0011Q7o\u001c8\u0011\u0005yY\u0018B\u0001?\u0011\u0005%Q5o\u001c8J]B,H/A\u0004sK\u0006$w\n\u001d;\u0016\u0007}\fY\u0001\u0006\u0003\u0002\u0002\u0005MA\u0003BA\u0002\u0003\u001b\u0001RaFA\u0003\u0003\u0013I1!a\u0002\u0019\u0005\u0019y\u0005\u000f^5p]B\u0019Q(a\u0003\u0005\u000b}R!\u0019A9\t\u0013\u0005=!\"!AA\u0004\u0005E\u0011AC3wS\u0012,gnY3%iA!QFOA\u0005\u0011\u0015I(\u00021\u0001{\u0003\u0015\u0001\u0018M]:f+\u0011\tI\"a\f\u0015\t\u0005m\u0011\u0011\u0007\u000b\u0005\u0003;\t\u0019\u0003E\u0002\u001f\u0003?I1!!\t\u0011\u0005\u0019Qe+\u00197vK\"I\u0011QE\u0006\u0002\u0002\u0003\u000f\u0011qE\u0001\u000bKZLG-\u001a8dK\u0012*\u0004#\u0002\u0010\u0002*\u00055\u0012bAA\u0016!\tY\u0011i\u001d&t_:Le\u000e];u!\ri\u0014q\u0006\u0003\u0006\u007f-\u0011\r!\u001d\u0005\u0007s.\u0001\r!!\f\u0002\u0011A\f'o]3PaR,B!a\u000e\u0002FQ!\u0011\u0011HA$)\u0011\tY$!\u0010\u0011\u000b]\t)!!\b\t\u0013\u0005}B\"!AA\u0004\u0005\u0005\u0013AC3wS\u0012,gnY3%mA)a$!\u000b\u0002DA\u0019Q(!\u0012\u0005\u000b}b!\u0019A9\t\red\u0001\u0019AA\"\u0003%!WmY8na>\u001cX\r\u0006\u0003\u0002\u001e\u00055\u0003BBA(\u001b\u0001\u0007!/A\u0002b]f\f1b^5uQ\u001a{'/\\1ugR\u00191%!\u0016\t\u000bqq\u0001\u0019A\u000f"
)
public abstract class JsonUtil {
   private final Formats formats;

   public Formats formats() {
      return this.formats;
   }

   public abstract String write(final Object a, final Manifest evidence$1);

   public abstract Writer write(final Object a, final Writer out, final Manifest evidence$2);

   public abstract String writePretty(final Object a);

   public abstract Writer writePretty(final Object a, final Writer out);

   public Object read(final JsonInput json, final Manifest evidence$3) {
      return ExtractableJsonAstNode$.MODULE$.extract$extension(package$.MODULE$.jvalue2extractable(this.parse(json, .MODULE$.identity())), this.formats(), evidence$3);
   }

   public Option readOpt(final JsonInput json, final Manifest evidence$4) {
      return this.parseOpt(json, .MODULE$.identity()).flatMap((x$1) -> ExtractableJsonAstNode$.MODULE$.extractOpt$extension(package$.MODULE$.jvalue2extractable(x$1), this.formats(), evidence$4));
   }

   public abstract JValue parse(final Object json, final AsJsonInput evidence$5);

   public abstract Option parseOpt(final Object json, final AsJsonInput evidence$6);

   public JValue decompose(final Object any) {
      return Extraction$.MODULE$.decompose(any, this.formats());
   }

   public abstract JsonUtil withFormats(final Formats fmts);

   public JsonUtil(final Formats fmts) {
      this.formats = fmts;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
