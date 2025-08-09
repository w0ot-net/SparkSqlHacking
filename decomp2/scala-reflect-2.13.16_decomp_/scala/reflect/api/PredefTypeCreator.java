package scala.reflect.api;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153A\u0001B\u0003\u0005\u0019!A!\u0003\u0001B\u0001B\u0003%1\u0003C\u0003+\u0001\u0011\u00051\u0006C\u0003/\u0001\u0011\u0005qFA\tQe\u0016$WM\u001a+za\u0016\u001c%/Z1u_JT!AB\u0004\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\t\u0013\u00059!/\u001a4mK\u000e$(\"\u0001\u0006\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011Q\"I\n\u0003\u00019\u0001\"a\u0004\t\u000e\u0003\u0015I!!E\u0003\u0003\u0017QK\b/Z\"sK\u0006$xN]\u0001\u0007G>\u0004\u00180\u00138\u0011\tQ)rCG\u0007\u0002\u0013%\u0011a#\u0003\u0002\n\rVt7\r^5p]F\u0002\"a\u0004\r\n\u0005e)!\u0001C+oSZ,'o]3\u0011\u0007]Yr$\u0003\u0002\u001d;\t9A+\u001f9f)\u0006<\u0017B\u0001\u0010\u0006\u0005!!\u0016\u0010]3UC\u001e\u001c\bC\u0001\u0011\"\u0019\u0001!QA\t\u0001C\u0002\r\u0012\u0011\u0001V\t\u0003I\u001d\u0002\"\u0001F\u0013\n\u0005\u0019J!a\u0002(pi\"Lgn\u001a\t\u0003)!J!!K\u0005\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0003Y5\u00022a\u0004\u0001 \u0011\u0015\u0011\"\u00011\u0001\u0014\u0003\u0015\t\u0007\u000f\u001d7z+\t\u00014\u0007\u0006\u00022\u0001B\u0011!\u0007\u0010\t\u0003AM\"Q\u0001N\u0002C\u0002U\u0012\u0011!V\t\u0003IY\u00122aN\f:\r\u0011A\u0004\u0001\u0001\u001c\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005QQ\u0014BA\u001e\n\u0005%\u0019\u0016N\\4mKR|g.\u0003\u0002>}\t!A+\u001f9f\u0013\tyTAA\u0003UsB,7\u000fC\u0003B\u0007\u0001\u0007!)A\u0001n!\ry1IM\u0005\u0003\t\u0016\u0011a!T5se>\u0014\b"
)
public class PredefTypeCreator extends TypeCreator {
   private final Function1 copyIn;

   public Types.TypeApi apply(final Mirror m) {
      return ((TypeTags.TypeTag)this.copyIn.apply(m.universe())).tpe();
   }

   public PredefTypeCreator(final Function1 copyIn) {
      this.copyIn = copyIn;
   }
}
