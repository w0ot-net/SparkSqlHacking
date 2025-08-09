package org.json4s;

import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2A!\u0002\u0004\u0007\u0017!)\u0001\u0003\u0001C\u0001#!11\u0003\u0001Q!\nQAQ\u0001\f\u0001\u0005\u00025BQa\r\u0001\u0005\u0002Q\u0012\u0001D\u0013#pk\ndW-Q:u%>|GOS:p]^\u0013\u0018\u000e^3s\u0015\t9\u0001\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u0013\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001b9i\u0011AB\u0005\u0003\u001f\u0019\u0011AC\u0013#pk\ndW-Q:u\u0015N|gn\u0016:ji\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0001\u0013!\ti\u0001!A\u0003o_\u0012,7\u000fE\u0002\u00169yi\u0011A\u0006\u0006\u0003/a\t\u0011\"[7nkR\f'\r\\3\u000b\u0005eQ\u0012AC2pY2,7\r^5p]*\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e-\t!A*[:u!\ty\u0012F\u0004\u0002!O9\u0011\u0011E\n\b\u0003E\u0015j\u0011a\t\u0006\u0003I)\ta\u0001\u0010:p_Rt\u0014\"A\u0005\n\u0005\u001dA\u0011B\u0001\u0015\u0007\u0003\u001dQ5o\u001c8B'RK!AK\u0016\u0003\r)3\u0016\r\\;f\u0015\tAc!A\u0004bI\u0012tu\u000eZ3\u0015\u00059\n\u0004cA\u00070=%\u0011\u0001G\u0002\u0002\u000b\u0015N|gn\u0016:ji\u0016\u0014\b\"\u0002\u001a\u0004\u0001\u0004q\u0012\u0001\u00028pI\u0016\faA]3tk2$X#\u0001\u0010"
)
public final class JDoubleAstRootJsonWriter extends JDoubleAstJsonWriter {
   private List nodes;

   public JsonWriter addNode(final JValue node) {
      this.nodes = this.nodes.$colon$colon(node);
      return this;
   }

   public JValue result() {
      return (JValue)(this.nodes.nonEmpty() ? (JValue)this.nodes.head() : JsonAST$.MODULE$.JNothing());
   }

   public JDoubleAstRootJsonWriter() {
      this.nodes = .MODULE$.List().empty();
   }
}
