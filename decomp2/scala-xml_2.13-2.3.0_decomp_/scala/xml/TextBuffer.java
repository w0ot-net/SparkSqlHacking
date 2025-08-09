package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.collection.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005);QAC\u0006\t\u0002A1QAE\u0006\t\u0002MAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0002m1AAE\u0006\u0001;!)\u0001\u0004\u0002C\u0001=!9q\u0004\u0002b\u0001\n\u0003\u0001\u0003B\u0002\u0015\u0005A\u0003%\u0011\u0005C\u0003*\t\u0011\u0005!\u0006C\u00038\t\u0011\u0005\u0001(\u0001\u0006UKb$()\u001e4gKJT!\u0001D\u0007\u0002\u0007alGNC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!E\u0001\u000e\u0003-\u0011!\u0002V3yi\n+hMZ3s'\t\tA\u0003\u0005\u0002\u0016-5\tQ\"\u0003\u0002\u0018\u001b\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\t\u0002\u0015\u0019\u0014x.\\*ue&tw\r\u0006\u0002\u001d{A\u0011\u0011\u0003B\n\u0003\tQ!\u0012\u0001H\u0001\u0003g\n,\u0012!\t\t\u0003E\u0015r!!F\u0012\n\u0005\u0011j\u0011a\u00029bG.\fw-Z\u0005\u0003M\u001d\u0012Qb\u0015;sS:<')^5mI\u0016\u0014(B\u0001\u0013\u000e\u0003\r\u0019(\rI\u0001\u0007CB\u0004XM\u001c3\u0015\u0005-bS\"\u0001\u0003\t\u000b5B\u0001\u0019\u0001\u0018\u0002\u0005\r\u001c\bcA\u00183i5\t\u0001G\u0003\u00022\u001b\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005M\u0002$aA*fcB\u0011Q#N\u0005\u0003m5\u0011Aa\u00115be\u00061Ao\u001c+fqR,\u0012!\u000f\t\u0004_IR\u0004CA\t<\u0013\ta4B\u0001\u0003UKb$\b\"\u0002 \u0004\u0001\u0004y\u0014aA:ueB\u0011\u0001i\u0012\b\u0003\u0003\u0016\u0003\"AQ\u0007\u000e\u0003\rS!\u0001R\b\u0002\rq\u0012xn\u001c;?\u0013\t1U\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u0013aa\u0015;sS:<'B\u0001$\u000e\u0001"
)
public class TextBuffer {
   private final StringBuilder sb = new StringBuilder();

   public static TextBuffer fromString(final String str) {
      return TextBuffer$.MODULE$.fromString(str);
   }

   public StringBuilder sb() {
      return this.sb;
   }

   public TextBuffer append(final Seq cs) {
      cs.foreach((c) -> $anonfun$append$1(this, BoxesRunTime.unboxToChar(c)));
      return this;
   }

   public Seq toText() {
      String var2 = this.sb().toString().trim();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 0:
            if ("".equals(var2)) {
               return .MODULE$;
            }
         default:
            return new scala.collection.immutable..colon.colon(Text$.MODULE$.apply(var2), .MODULE$);
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$append$1(final TextBuffer $this, final char c) {
      if (!Utility$.MODULE$.isSpace(c)) {
         return $this.sb().append(c);
      } else {
         return !$this.sb().isEmpty() && Utility$.MODULE$.isSpace(BoxesRunTime.unboxToChar($this.sb().last())) ? BoxedUnit.UNIT : $this.sb().append(' ');
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
