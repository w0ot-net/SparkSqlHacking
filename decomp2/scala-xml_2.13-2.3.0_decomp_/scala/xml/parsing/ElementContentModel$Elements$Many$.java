package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.List;

public class ElementContentModel$Elements$Many$ {
   public static final ElementContentModel$Elements$Many$ MODULE$ = new ElementContentModel$Elements$Many$();

   public ElementContentModel$Elements$Many parse(final String string) {
      List choice = ElementContentModel$Elements$Choice$.MODULE$.split(string);
      return (ElementContentModel$Elements$Many)(choice.length() > 1 ? new ElementContentModel$Elements$Choice(choice.map((stringx) -> ElementContentModel.Cp$.MODULE$.parse(stringx))) : new ElementContentModel$Elements$Sequence(ElementContentModel$Elements$Sequence$.MODULE$.split(string).map((stringx) -> ElementContentModel.Cp$.MODULE$.parse(stringx))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
