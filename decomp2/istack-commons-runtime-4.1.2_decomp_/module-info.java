module com.sun.istack.runtime {
   requires transitive java.logging;
   requires transitive java.xml;
   requires static transitive jakarta.activation;

   exports com.sun.istack;
   exports com.sun.istack.localization;
   exports com.sun.istack.logging;
}
