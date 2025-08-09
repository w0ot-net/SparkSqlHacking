package scala.xml.transform;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.Node;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112Q\u0001B\u0003\u0002\u00021AQ!\u0005\u0001\u0005\u0002IAQA\u0002\u0001\u0005BQAQA\u0002\u0001\u0005B\u0005\u00121BU3xe&$XMU;mK*\u0011aaB\u0001\niJ\fgn\u001d4pe6T!\u0001C\u0005\u0002\u0007alGNC\u0001\u000b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059yQ\"A\u0003\n\u0005A)!\u0001\u0005\"bg&\u001cGK]1og\u001a|'/\\3s\u0003\u0019a\u0014N\\5u}Q\t1\u0003\u0005\u0002\u000f\u0001Q\u0011Qc\b\t\u0004-eYR\"A\f\u000b\u0005aI\u0011AC2pY2,7\r^5p]&\u0011!d\u0006\u0002\u0004'\u0016\f\bC\u0001\u000f\u001e\u001b\u00059\u0011B\u0001\u0010\b\u0005\u0011qu\u000eZ3\t\u000b\u0001\u0012\u0001\u0019A\u000b\u0002\u00059\u001cHCA\u000b#\u0011\u0015\u00193\u00011\u0001\u001c\u0003\u0005q\u0007"
)
public abstract class RewriteRule extends BasicTransformer {
   public Seq transform(final Seq ns) {
      return super.transform(ns);
   }

   public Seq transform(final Node n) {
      return n;
   }
}
