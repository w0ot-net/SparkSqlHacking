package org.json4s;

import scala.Function1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e\u0001B\n\u0015\u0005eAA\"\t\u0001\u0005\u0002\u0003\u0015)Q1A\u0005\n\tB\u0011\u0002\u0010\u0001\u0003\u0006\u0003\u0005\u000b\u0011B\u0012\t\u000bu\u0002A\u0011\u0001 \t\u000b\r\u0003A\u0011\u0001#\t\u000b\r\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011A3\t\u000b\u0011\u0004A\u0011A8\t\u000fM\u0004\u0011\u0011!C!i\"9\u0001\u0010AA\u0001\n\u0003Jx\u0001C@\u0015\u0003\u0003E\t!!\u0001\u0007\u0011M!\u0012\u0011!E\u0001\u0003\u0007Aa!P\u0006\u0005\u0002\u0005-\u0001bBA\u0007\u0017\u0011\u0015\u0011q\u0002\u0005\b\u0003\u001bYAQAA\u0019\u0011\u001d\t9e\u0003C\u0003\u0003\u0013Bq!a\u0012\f\t\u000b\tI\u0007C\u0005\u0002\u0000-\t\t\u0011\"\u0002\u0002\u0002\"I\u0011QR\u0006\u0002\u0002\u0013\u0015\u0011q\u0012\u0002\n\u0015N|g.Q:t_\u000eT!!\u0006\f\u0002\r)\u001cxN\u001c\u001bt\u0015\u00059\u0012aA8sO\u000e\u0001QC\u0001\u000e4'\t\u00011\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f4\u0016\r\\\u0001\u001b_J<GE[:p]R\u001aHES:p]\u0006\u001b8o\\2%I1,g\r^\u000b\u0002GA!A\u0004\n\u00142\u0013\t)SD\u0001\u0004UkBdWM\r\t\u0003O9r!\u0001\u000b\u0017\u0011\u0005%jR\"\u0001\u0016\u000b\u0005-B\u0012A\u0002\u001fs_>$h(\u0003\u0002.;\u00051\u0001K]3eK\u001aL!a\f\u0019\u0003\rM#(/\u001b8h\u0015\tiS\u0004\u0005\u00023g1\u0001A!\u0002\u001b\u0001\u0005\u0004)$!A!\u0012\u0005YJ\u0004C\u0001\u000f8\u0013\tATDA\u0004O_RD\u0017N\\4\u0011\u0005qQ\u0014BA\u001e\u001e\u0005\r\te._\u0001\u001c_J<GE[:p]R\u001aHES:p]\u0006\u001b8o\\2%I1,g\r\u001e\u0011\u0002\rqJg.\u001b;?)\ty\u0014\tE\u0002A\u0001Ej\u0011\u0001\u0006\u0005\u0006\u0005\u000e\u0001\raI\u0001\u0005Y\u00164G/\u0001\u0004%i&dG-Z\u000b\u0003\u000bj#\"A\u0012/\u0015\u0007\u001dse\u000b\u0005\u0002I\u0017:\u0011\u0001)S\u0005\u0003\u0015R\tqAS:p]\u0006\u001bF+\u0003\u0002M\u001b\n9!j\u00142kK\u000e$(B\u0001&\u0015\u0011\u0015yE\u0001q\u0001Q\u0003\r)g/\r\t\u00059E\u000b4+\u0003\u0002S;\tIa)\u001e8di&|g.\r\t\u0003\u0011RK!!V'\u0003\r)3\u0016\r\\;f\u0011\u00159F\u0001q\u0001Y\u0003\r)gO\r\t\u00059EK6\u000b\u0005\u000235\u0012)1\f\u0002b\u0001k\t\t!\tC\u0003^\t\u0001\u0007a,A\u0003sS\u001eDG\u000f\u0005\u0003\u001dI\u0019JFC\u00011d)\t9\u0015\rC\u0003c\u000b\u0001\u000f\u0001+\u0001\u0002fm\")Q,\u0002a\u0001\u000f\u0006aA\u0005^5mI\u0016$C/\u001b7eKV\u0011a\r\u001c\u000b\u0003O6$2a\u00125j\u0011\u0015ye\u0001q\u0001Q\u0011\u00159f\u0001q\u0001k!\u0011a\u0012k[*\u0011\u0005IbG!B.\u0007\u0005\u0004)\u0004\"B/\u0007\u0001\u0004q\u0007\u0003\u0002\u000f%M-$\"\u0001\u001d:\u0015\u0005\u001d\u000b\b\"\u00022\b\u0001\b\u0001\u0006\"B/\b\u0001\u00049\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003U\u0004\"\u0001\b<\n\u0005]l\"aA%oi\u00061Q-];bYN$\"A_?\u0011\u0005qY\u0018B\u0001?\u001e\u0005\u001d\u0011un\u001c7fC:DqA`\u0005\u0002\u0002\u0003\u0007\u0011(A\u0002yIE\n\u0011BS:p]\u0006\u001b8o\\2\u0011\u0005\u0001[1cA\u0006\u0002\u0006A\u0019A$a\u0002\n\u0007\u0005%QD\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003\u0003\t\u0001\u0003\n;jY\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0016\r\u0005E\u0011QEA\u000f)\u0011\t\u0019\"a\u000b\u0015\t\u0005U\u0011q\u0005\u000b\u0006\u000f\u0006]\u0011q\u0004\u0005\u0007\u001f6\u0001\u001d!!\u0007\u0011\u000bq\t\u00161D*\u0011\u0007I\ni\u0002B\u00035\u001b\t\u0007Q\u0007\u0003\u0004X\u001b\u0001\u000f\u0011\u0011\u0005\t\u00069E\u000b\u0019c\u0015\t\u0004e\u0005\u0015B!B.\u000e\u0005\u0004)\u0004BB/\u000e\u0001\u0004\tI\u0003E\u0003\u001dI\u0019\n\u0019\u0003C\u0004\u0002.5\u0001\r!a\f\u0002\u000b\u0011\"\b.[:\u0011\t\u0001\u0003\u00111D\u000b\u0005\u0003g\ty\u0004\u0006\u0003\u00026\u0005\rC\u0003BA\u001c\u0003\u0003\"2aRA\u001d\u0011\u0019\u0011g\u0002q\u0001\u0002<A)A$UA\u001f'B\u0019!'a\u0010\u0005\u000bQr!\u0019A\u001b\t\u000bus\u0001\u0019A$\t\u000f\u00055b\u00021\u0001\u0002FA!\u0001\tAA\u001f\u0003Y!C/\u001b7eK\u0012\"\u0018\u000e\u001c3fI\u0015DH/\u001a8tS>tWCBA&\u0003?\n9\u0006\u0006\u0003\u0002N\u0005\u0015D\u0003BA(\u0003C\"RaRA)\u00033BaaT\bA\u0004\u0005M\u0003#\u0002\u000fR\u0003+\u001a\u0006c\u0001\u001a\u0002X\u0011)Ag\u0004b\u0001k!1qk\u0004a\u0002\u00037\u0002R\u0001H)\u0002^M\u00032AMA0\t\u0015YvB1\u00016\u0011\u0019iv\u00021\u0001\u0002dA)A\u0004\n\u0014\u0002^!9\u0011QF\bA\u0002\u0005\u001d\u0004\u0003\u0002!\u0001\u0003+*B!a\u001b\u0002xQ!\u0011QNA>)\u0011\ty'!\u001f\u0015\u0007\u001d\u000b\t\b\u0003\u0004c!\u0001\u000f\u00111\u000f\t\u00069E\u000b)h\u0015\t\u0004e\u0005]D!\u0002\u001b\u0011\u0005\u0004)\u0004\"B/\u0011\u0001\u00049\u0005bBA\u0017!\u0001\u0007\u0011Q\u0010\t\u0005\u0001\u0002\t)(\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tW\u0003BAB\u0003\u0017#2\u0001^AC\u0011\u001d\ti#\u0005a\u0001\u0003\u000f\u0003B\u0001\u0011\u0001\u0002\nB\u0019!'a#\u0005\u000bQ\n\"\u0019A\u001b\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tW\u0003BAI\u0003;#B!a%\u0002\u0018R\u0019!0!&\t\u000fy\u0014\u0012\u0011!a\u0001s!9\u0011Q\u0006\nA\u0002\u0005e\u0005\u0003\u0002!\u0001\u00037\u00032AMAO\t\u0015!$C1\u00016\u0001"
)
public final class JsonAssoc {
   private final Tuple2 org$json4s$JsonAssoc$$left;

   public static boolean equals$extension(final Tuple2 $this, final Object x$1) {
      return JsonAssoc$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Tuple2 $this) {
      return JsonAssoc$.MODULE$.hashCode$extension($this);
   }

   public static JObject $tilde$tilde$extension(final Tuple2 $this, final JObject right, final Function1 ev) {
      return JsonAssoc$.MODULE$.$tilde$tilde$extension($this, right, ev);
   }

   public static JObject $tilde$tilde$extension(final Tuple2 $this, final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      return JsonAssoc$.MODULE$.$tilde$tilde$extension($this, right, ev1, ev2);
   }

   public static JObject $tilde$extension(final Tuple2 $this, final JObject right, final Function1 ev) {
      return JsonAssoc$.MODULE$.$tilde$extension($this, right, ev);
   }

   public static JObject $tilde$extension(final Tuple2 $this, final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      return JsonAssoc$.MODULE$.$tilde$extension($this, right, ev1, ev2);
   }

   public Tuple2 org$json4s$JsonAssoc$$left() {
      return this.org$json4s$JsonAssoc$$left;
   }

   public JObject $tilde(final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      return JsonAssoc$.MODULE$.$tilde$extension(this.org$json4s$JsonAssoc$$left(), right, ev1, ev2);
   }

   public JObject $tilde(final JObject right, final Function1 ev) {
      return JsonAssoc$.MODULE$.$tilde$extension(this.org$json4s$JsonAssoc$$left(), right, ev);
   }

   public JObject $tilde$tilde(final Tuple2 right, final Function1 ev1, final Function1 ev2) {
      return JsonAssoc$.MODULE$.$tilde$tilde$extension(this.org$json4s$JsonAssoc$$left(), right, ev1, ev2);
   }

   public JObject $tilde$tilde(final JObject right, final Function1 ev) {
      return JsonAssoc$.MODULE$.$tilde$tilde$extension(this.org$json4s$JsonAssoc$$left(), right, ev);
   }

   public int hashCode() {
      return JsonAssoc$.MODULE$.hashCode$extension(this.org$json4s$JsonAssoc$$left());
   }

   public boolean equals(final Object x$1) {
      return JsonAssoc$.MODULE$.equals$extension(this.org$json4s$JsonAssoc$$left(), x$1);
   }

   public JsonAssoc(final Tuple2 left) {
      this.org$json4s$JsonAssoc$$left = left;
   }
}
