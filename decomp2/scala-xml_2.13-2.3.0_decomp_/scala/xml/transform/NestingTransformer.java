package scala.xml.transform;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.Node;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192A\u0001B\u0003\u0001\u0019!A\u0011\u0003\u0001B\u0001B\u0003%!\u0003C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u0007\u0001\u0011\u0005\u0013D\u0001\nOKN$\u0018N\\4Ue\u0006t7OZ8s[\u0016\u0014(B\u0001\u0004\b\u0003%!(/\u00198tM>\u0014XN\u0003\u0002\t\u0013\u0005\u0019\u00010\u001c7\u000b\u0003)\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011abD\u0007\u0002\u000b%\u0011\u0001#\u0002\u0002\u0011\u0005\u0006\u001c\u0018n\u0019+sC:\u001chm\u001c:nKJ\fAA];mKB\u0011abE\u0005\u0003)\u0015\u00111BU3xe&$XMU;mK\u00061A(\u001b8jiz\"\"a\u0006\r\u0011\u00059\u0001\u0001\"B\t\u0003\u0001\u0004\u0011BC\u0001\u000e%!\rYb\u0004I\u0007\u00029)\u0011Q$C\u0001\u000bG>dG.Z2uS>t\u0017BA\u0010\u001d\u0005\r\u0019V-\u001d\t\u0003C\tj\u0011aB\u0005\u0003G\u001d\u0011AAT8eK\")Qe\u0001a\u0001A\u0005\ta\u000e"
)
public class NestingTransformer extends BasicTransformer {
   private final RewriteRule rule;

   public Seq transform(final Node n) {
      return this.rule.transform(super.transform(n));
   }

   public NestingTransformer(final RewriteRule rule) {
      this.rule = rule;
   }
}
