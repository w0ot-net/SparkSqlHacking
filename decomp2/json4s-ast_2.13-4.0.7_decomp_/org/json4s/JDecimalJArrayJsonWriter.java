package org.json4s;

import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.ListBuffer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0002\u0005\u0007\u001b!A!\u0003\u0001B\u0001B\u0003%1\u0003C\u0003%\u0001\u0011\u0005Q\u0005\u0003\u0004)\u0001\u0001\u0006I!\u000b\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006s\u0001!\tA\u000f\u0002\u0019\u0015\u0012+7-[7bY*\u000b%O]1z\u0015N|gn\u0016:ji\u0016\u0014(BA\u0005\u000b\u0003\u0019Q7o\u001c85g*\t1\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001dA\u0011q\u0002E\u0007\u0002\u0011%\u0011\u0011\u0003\u0003\u0002\u0016\u0015\u0012+7-[7bY\u0006\u001bHOS:p]^\u0013\u0018\u000e^3s\u0003\u0019\u0001\u0018M]3oiB\u0019q\u0002\u0006\f\n\u0005UA!A\u0003&t_:<&/\u001b;feB\u0011q#\t\b\u00031}q!!\u0007\u0010\u000f\u0005iiR\"A\u000e\u000b\u0005qa\u0011A\u0002\u001fs_>$h(C\u0001\f\u0013\tI!\"\u0003\u0002!\u0011\u00059!j]8o\u0003N#\u0016B\u0001\u0012$\u0005\u0019Qe+\u00197vK*\u0011\u0001\u0005C\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019:\u0003CA\b\u0001\u0011\u0015\u0011\"\u00011\u0001\u0014\u0003\u0015qw\u000eZ3t!\rQ\u0013GF\u0007\u0002W)\u0011A&L\u0001\b[V$\u0018M\u00197f\u0015\tqs&\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e-\u0012!\u0002T5ti\n+hMZ3s\u0003\u001d\tG\r\u001a(pI\u0016$\"aE\u001b\t\u000bY\"\u0001\u0019\u0001\f\u0002\t9|G-Z\u0001\tK:$\u0017I\u001d:bsR\t1#\u0001\u0004sKN,H\u000e^\u000b\u0002-\u0001"
)
public final class JDecimalJArrayJsonWriter extends JDecimalAstJsonWriter {
   private final JsonWriter parent;
   private final ListBuffer nodes;

   public JsonWriter addNode(final JValue node) {
      this.nodes.$plus$eq(node);
      return this;
   }

   public JsonWriter endArray() {
      JsonWriter var2 = this.parent;
      JsonWriter var1;
      if (var2 instanceof JDecimalAstJsonWriter) {
         JDecimalAstJsonWriter var3 = (JDecimalAstJsonWriter)var2;
         var1 = var3.addNode(this.result());
      } else {
         var1 = this.parent;
      }

      return var1;
   }

   public JValue result() {
      return JsonAST$.MODULE$.JArray().apply(this.nodes.toList());
   }

   public JDecimalJArrayJsonWriter(final JsonWriter parent) {
      this.parent = parent;
      this.nodes = (ListBuffer).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
