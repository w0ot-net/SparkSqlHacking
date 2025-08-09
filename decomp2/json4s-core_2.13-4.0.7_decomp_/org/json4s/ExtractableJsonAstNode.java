package org.json4s;

import scala.Function0;
import scala.Option;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001B\t\u0013\u0005]AAB\b\u0001\u0005\u0002\u0003\u0015)Q1A\u0005\n}A\u0011\u0002\n\u0001\u0003\u0006\u0003\u0005\u000b\u0011\u0002\u0011\t\u000b\u0015\u0002A\u0011\u0001\u0014\t\u000b)\u0002A\u0011A\u0016\t\u000b\u0015\u0003A\u0011\u0001$\t\u000bA\u0003A\u0011A)\t\u000fy\u0003\u0011\u0011!C!?\"91\rAA\u0001\n\u0003\"wa\u00026\u0013\u0003\u0003E\ta\u001b\u0004\b#I\t\t\u0011#\u0001m\u0011\u0015)#\u0002\"\u0001q\u0011\u0015\t(\u0002\"\u0002s\u0011\u0015a(\u0002\"\u0002~\u0011\u001d\tyA\u0003C\u0003\u0003#A\u0011\"!\u000b\u000b\u0003\u0003%)!a\u000b\t\u0013\u0005=\"\"!A\u0005\u0006\u0005E\"AF#yiJ\f7\r^1cY\u0016T5o\u001c8BgRtu\u000eZ3\u000b\u0005M!\u0012A\u00026t_:$4OC\u0001\u0016\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f4\u0016\r\\\u0001&_J<GE[:p]R\u001aH%\u0012=ue\u0006\u001cG/\u00192mK*\u001bxN\\!ti:{G-\u001a\u0013%UZ,\u0012\u0001\t\t\u0003C\tj\u0011AE\u0005\u0003GI\u0011aA\u0013,bYV,\u0017AJ8sO\u0012R7o\u001c85g\u0012*\u0005\u0010\u001e:bGR\f'\r\\3Kg>t\u0017i\u001d;O_\u0012,G\u0005\n6wA\u00051A(\u001b8jiz\"\"a\n\u0015\u0011\u0005\u0005\u0002\u0001\"B\u0015\u0004\u0001\u0004\u0001\u0013A\u00016w\u0003\u001d)\u0007\u0010\u001e:bGR,\"\u0001L\u0018\u0015\u00075BT\b\u0005\u0002/_1\u0001A!\u0002\u0019\u0005\u0005\u0004\t$!A!\u0012\u0005I*\u0004CA\r4\u0013\t!$DA\u0004O_RD\u0017N\\4\u0011\u0005e1\u0014BA\u001c\u001b\u0005\r\te.\u001f\u0005\u0006s\u0011\u0001\u001dAO\u0001\bM>\u0014X.\u0019;t!\t\t3(\u0003\u0002=%\t9ai\u001c:nCR\u001c\b\"\u0002 \u0005\u0001\by\u0014AA7g!\r\u00015)L\u0007\u0002\u0003*\u0011!IG\u0001\be\u00164G.Z2u\u0013\t!\u0015I\u0001\u0005NC:Lg-Z:u\u0003))\u0007\u0010\u001e:bGR|\u0005\u000f^\u000b\u0003\u000f2#2\u0001S'O!\rI\u0012jS\u0005\u0003\u0015j\u0011aa\u00149uS>t\u0007C\u0001\u0018M\t\u0015\u0001TA1\u00012\u0011\u0015IT\u0001q\u0001;\u0011\u0015qT\u0001q\u0001P!\r\u00015iS\u0001\u000eKb$(/Y2u\u001fJ,En]3\u0016\u0005I+FCA*Z)\r!fk\u0016\t\u0003]U#Q\u0001\r\u0004C\u0002EBQ!\u000f\u0004A\u0004iBQA\u0010\u0004A\u0004a\u00032\u0001Q\"U\u0011\u0019Qf\u0001\"a\u00017\u00069A-\u001a4bk2$\bcA\r])&\u0011QL\u0007\u0002\ty\tLh.Y7f}\u0005A\u0001.Y:i\u0007>$W\rF\u0001a!\tI\u0012-\u0003\u0002c5\t\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\t)\u0007\u000e\u0005\u0002\u001aM&\u0011qM\u0007\u0002\b\u0005>|G.Z1o\u0011\u001dI\u0007\"!AA\u0002U\n1\u0001\u001f\u00132\u0003Y)\u0005\u0010\u001e:bGR\f'\r\\3Kg>t\u0017i\u001d;O_\u0012,\u0007CA\u0011\u000b'\tQQ\u000e\u0005\u0002\u001a]&\u0011qN\u0007\u0002\u0007\u0003:L(+\u001a4\u0015\u0003-\f\u0011#\u001a=ue\u0006\u001cG\u000fJ3yi\u0016t7/[8o+\t\u0019h\u000f\u0006\u0002uuR\u0019Qo\u001e=\u0011\u000592H!\u0002\u0019\r\u0005\u0004\t\u0004\"B\u001d\r\u0001\bQ\u0004\"\u0002 \r\u0001\bI\bc\u0001!Dk\")1\u0010\u0004a\u0001O\u0005)A\u0005\u001e5jg\u0006!R\r\u001f;sC\u000e$x\n\u001d;%Kb$XM\\:j_:,2A`A\u0003)\ry\u0018Q\u0002\u000b\u0007\u0003\u0003\t9!!\u0003\u0011\teI\u00151\u0001\t\u0004]\u0005\u0015A!\u0002\u0019\u000e\u0005\u0004\t\u0004\"B\u001d\u000e\u0001\bQ\u0004B\u0002 \u000e\u0001\b\tY\u0001\u0005\u0003A\u0007\u0006\r\u0001\"B>\u000e\u0001\u00049\u0013aF3yiJ\f7\r^(s\u000b2\u001cX\rJ3yi\u0016t7/[8o+\u0011\t\u0019\"a\u0007\u0015\t\u0005U\u0011q\u0005\u000b\u0005\u0003/\t\u0019\u0003\u0006\u0004\u0002\u001a\u0005u\u0011q\u0004\t\u0004]\u0005mA!\u0002\u0019\u000f\u0005\u0004\t\u0004\"B\u001d\u000f\u0001\bQ\u0004B\u0002 \u000f\u0001\b\t\t\u0003\u0005\u0003A\u0007\u0006e\u0001b\u0002.\u000f\t\u0003\u0007\u0011Q\u0005\t\u00053q\u000bI\u0002C\u0003|\u001d\u0001\u0007q%\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tGcA0\u0002.!)1p\u0004a\u0001O\u0005\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003g\t9\u0004F\u0002f\u0003kAq!\u001b\t\u0002\u0002\u0003\u0007Q\u0007C\u0003|!\u0001\u0007q\u0005"
)
public final class ExtractableJsonAstNode {
   private final JValue org$json4s$ExtractableJsonAstNode$$jv;

   public static boolean equals$extension(final JValue $this, final Object x$1) {
      return ExtractableJsonAstNode$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final JValue $this) {
      return ExtractableJsonAstNode$.MODULE$.hashCode$extension($this);
   }

   public static Object extractOrElse$extension(final JValue $this, final Function0 default, final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extractOrElse$extension($this, default, formats, mf);
   }

   public static Option extractOpt$extension(final JValue $this, final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extractOpt$extension($this, formats, mf);
   }

   public static Object extract$extension(final JValue $this, final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extract$extension($this, formats, mf);
   }

   public JValue org$json4s$ExtractableJsonAstNode$$jv() {
      return this.org$json4s$ExtractableJsonAstNode$$jv;
   }

   public Object extract(final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extract$extension(this.org$json4s$ExtractableJsonAstNode$$jv(), formats, mf);
   }

   public Option extractOpt(final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extractOpt$extension(this.org$json4s$ExtractableJsonAstNode$$jv(), formats, mf);
   }

   public Object extractOrElse(final Function0 default, final Formats formats, final Manifest mf) {
      return ExtractableJsonAstNode$.MODULE$.extractOrElse$extension(this.org$json4s$ExtractableJsonAstNode$$jv(), default, formats, mf);
   }

   public int hashCode() {
      return ExtractableJsonAstNode$.MODULE$.hashCode$extension(this.org$json4s$ExtractableJsonAstNode$$jv());
   }

   public boolean equals(final Object x$1) {
      return ExtractableJsonAstNode$.MODULE$.equals$extension(this.org$json4s$ExtractableJsonAstNode$$jv(), x$1);
   }

   public ExtractableJsonAstNode(final JValue jv) {
      this.org$json4s$ExtractableJsonAstNode$$jv = jv;
   }
}
