package org.json4s;

import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.ListBuffer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0002\u0005\u0007\u001b!A!\u0003\u0001B\u0001B\u0003%1\u0003C\u0003%\u0001\u0011\u0005Q\u0005\u0003\u0004)\u0001\u0001\u0006I!\u000b\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006s\u0001!\tA\u000f\u0002\u0018\u0015\u0012{WO\u00197f\u0015\u0006\u0013(/Y=Kg>twK]5uKJT!!\u0003\u0006\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005Y\u0011aA8sO\u000e\u00011C\u0001\u0001\u000f!\ty\u0001#D\u0001\t\u0013\t\t\u0002B\u0001\u000bK\t>,(\r\\3BgRT5o\u001c8Xe&$XM]\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007=!b#\u0003\u0002\u0016\u0011\tQ!j]8o/JLG/\u001a:\u0011\u0005]\tcB\u0001\r \u001d\tIbD\u0004\u0002\u001b;5\t1D\u0003\u0002\u001d\u0019\u00051AH]8pizJ\u0011aC\u0005\u0003\u0013)I!\u0001\t\u0005\u0002\u000f)\u001bxN\\!T)&\u0011!e\t\u0002\u0007\u0015Z\u000bG.^3\u000b\u0005\u0001B\u0011A\u0002\u001fj]&$h\b\u0006\u0002'OA\u0011q\u0002\u0001\u0005\u0006%\t\u0001\raE\u0001\u0006]>$Wm\u001d\t\u0004UE2R\"A\u0016\u000b\u00051j\u0013aB7vi\u0006\u0014G.\u001a\u0006\u0003]=\n!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a,\u0005)a\u0015n\u001d;Ck\u001a4WM]\u0001\bC\u0012$gj\u001c3f)\t\u0019R\u0007C\u00037\t\u0001\u0007a#\u0001\u0003o_\u0012,\u0017\u0001C3oI\u0006\u0013(/Y=\u0015\u0003M\taA]3tk2$X#\u0001\f"
)
public final class JDoubleJArrayJsonWriter extends JDoubleAstJsonWriter {
   private final JsonWriter parent;
   private final ListBuffer nodes;

   public JsonWriter addNode(final JValue node) {
      this.nodes.$plus$eq(node);
      return this;
   }

   public JsonWriter endArray() {
      JsonWriter var2 = this.parent;
      JsonWriter var1;
      if (var2 instanceof JDoubleAstJsonWriter) {
         JDoubleAstJsonWriter var3 = (JDoubleAstJsonWriter)var2;
         var1 = var3.addNode(this.result());
      } else {
         var1 = this.parent;
      }

      return var1;
   }

   public JValue result() {
      return JsonAST$.MODULE$.JArray().apply(this.nodes.toList());
   }

   public JDoubleJArrayJsonWriter(final JsonWriter parent) {
      this.parent = parent;
      this.nodes = (ListBuffer).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
