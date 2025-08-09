package org.json4s;

import scala.Predef.ArrowAssoc.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2AAB\u0004\u0007\u0019!A\u0011\u0003\u0001B\u0001B\u0003%!\u0003\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003!\u0011\u0015\u0019\u0003\u0001\"\u0001%\u0011\u0015A\u0003\u0001\"\u0001*\u0011\u0015)\u0004\u0001\"\u00017\u0005aQE)Z2j[\u0006d'JR5fY\u0012T5o\u001c8Xe&$XM\u001d\u0006\u0003\u0011%\taA[:p]R\u001a(\"\u0001\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001i\u0001C\u0001\b\u0010\u001b\u00059\u0011B\u0001\t\b\u0005UQE)Z2j[\u0006d\u0017i\u001d;Kg>twK]5uKJ\fAA\\1nKB\u00111\u0003\b\b\u0003)i\u0001\"!\u0006\r\u000e\u0003YQ!aF\u0006\u0002\rq\u0012xn\u001c;?\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0003\u0019\u0001&/\u001a3fM&\u0011QD\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005mA\u0012A\u00029be\u0016tG\u000f\u0005\u0002\u000fC%\u0011!e\u0002\u0002\u001a\u0015\u0012+7-[7bY*{%M[3di*\u001bxN\\,sSR,'/\u0001\u0004=S:LGO\u0010\u000b\u0004K\u0019:\u0003C\u0001\b\u0001\u0011\u0015\t2\u00011\u0001\u0013\u0011\u0015y2\u00011\u0001!\u0003\u0019\u0011Xm];miV\t!\u0006\u0005\u0002,e9\u0011A\u0006\r\b\u0003[=r!!\u0006\u0018\n\u0003)I!\u0001C\u0005\n\u0005E:\u0011a\u0002&t_:\f5\u000bV\u0005\u0003gQ\u0012aA\u0013,bYV,'BA\u0019\b\u0003\u001d\tG\r\u001a(pI\u0016$\"a\u000e\u001e\u0011\u00079A$&\u0003\u0002:\u000f\tQ!j]8o/JLG/\u001a:\t\u000bm*\u0001\u0019\u0001\u0016\u0002\t9|G-\u001a"
)
public final class JDecimalJFieldJsonWriter extends JDecimalAstJsonWriter {
   private final String name;
   private final JDecimalJObjectJsonWriter parent;

   public JValue result() {
      return JsonAST$.MODULE$.JNothing();
   }

   public JsonWriter addNode(final JValue node) {
      return this.parent.addNode(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.name), node));
   }

   public JDecimalJFieldJsonWriter(final String name, final JDecimalJObjectJsonWriter parent) {
      this.name = name;
      this.parent = parent;
   }
}
