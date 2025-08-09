package scala.xml.parsing;

public interface ElementContentModel$Occurrence$Signed {
   // $FF: synthetic method
   static String toString$(final ElementContentModel$Occurrence$Signed $this) {
      return $this.toString();
   }

   default String toString() {
      return this.sign();
   }

   String sign();

   static void $init$(final ElementContentModel$Occurrence$Signed $this) {
   }
}
