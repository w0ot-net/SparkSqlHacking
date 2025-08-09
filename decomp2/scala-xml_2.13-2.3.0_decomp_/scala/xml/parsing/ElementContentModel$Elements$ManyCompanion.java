package scala.xml.parsing;

import scala.collection.immutable.List;

public abstract class ElementContentModel$Elements$ManyCompanion {
   private final char separator;

   public char separator() {
      return this.separator;
   }

   public final List split(final String string) {
      return ElementContentModel$.MODULE$.scala$xml$parsing$ElementContentModel$$split(string, this.separator());
   }

   public ElementContentModel$Elements$ManyCompanion(final char separator) {
      this.separator = separator;
   }
}
