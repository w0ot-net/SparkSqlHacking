package org.json4s;

import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2A!\u0002\u0004\u0007\u0017!)\u0001\u0003\u0001C\u0001#!11\u0003\u0001Q!\nQAQ\u0001\f\u0001\u0005\u00025BQa\r\u0001\u0005\u0002Q\u0012\u0011D\u0013#fG&l\u0017\r\\!tiJ{w\u000e\u001e&t_:<&/\u001b;fe*\u0011q\u0001C\u0001\u0007UN|g\u000eN:\u000b\u0003%\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055qQ\"\u0001\u0004\n\u0005=1!!\u0006&EK\u000eLW.\u00197BgRT5o\u001c8Xe&$XM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\u0001\"!\u0004\u0001\u0002\u000b9|G-Z:\u0011\u0007Uab$D\u0001\u0017\u0015\t9\u0002$A\u0005j[6,H/\u00192mK*\u0011\u0011DG\u0001\u000bG>dG.Z2uS>t'\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005u1\"\u0001\u0002'jgR\u0004\"aH\u0015\u000f\u0005\u0001:cBA\u0011'\u001d\t\u0011S%D\u0001$\u0015\t!#\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011q\u0001C\u0005\u0003Q\u0019\tqAS:p]\u0006\u001bF+\u0003\u0002+W\t1!JV1mk\u0016T!\u0001\u000b\u0004\u0002\u000f\u0005$GMT8eKR\u0011a&\r\t\u0004\u001b=r\u0012B\u0001\u0019\u0007\u0005)Q5o\u001c8Xe&$XM\u001d\u0005\u0006e\r\u0001\rAH\u0001\u0005]>$W-\u0001\u0004sKN,H\u000e^\u000b\u0002=\u0001"
)
public final class JDecimalAstRootJsonWriter extends JDecimalAstJsonWriter {
   private List nodes;

   public JsonWriter addNode(final JValue node) {
      this.nodes = this.nodes.$colon$colon(node);
      return this;
   }

   public JValue result() {
      return (JValue)(this.nodes.nonEmpty() ? (JValue)this.nodes.head() : JsonAST$.MODULE$.JNothing());
   }

   public JDecimalAstRootJsonWriter() {
      this.nodes = .MODULE$.List().empty();
   }
}
