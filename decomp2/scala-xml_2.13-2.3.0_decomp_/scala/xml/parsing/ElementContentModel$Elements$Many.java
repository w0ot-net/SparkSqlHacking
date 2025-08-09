package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.List;

public abstract class ElementContentModel$Elements$Many implements ElementContentModel.Elements {
   private final List children;

   public final String toString() {
      return this.children.map((x$2) -> x$2.toString()).mkString("(", Character.toString(this.companion().separator()), ")");
   }

   public abstract ElementContentModel$Elements$ManyCompanion companion();

   public ElementContentModel$Elements$Many(final List children) {
      this.children = children;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
