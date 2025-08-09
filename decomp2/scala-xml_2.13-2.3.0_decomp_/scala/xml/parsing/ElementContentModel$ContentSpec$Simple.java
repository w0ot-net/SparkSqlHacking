package scala.xml.parsing;

public interface ElementContentModel$ContentSpec$Simple extends ElementContentModel.ContentSpec {
   // $FF: synthetic method
   static String toString$(final ElementContentModel$ContentSpec$Simple $this) {
      return $this.toString();
   }

   default String toString() {
      return this.value();
   }

   String value();

   static void $init$(final ElementContentModel$ContentSpec$Simple $this) {
   }
}
