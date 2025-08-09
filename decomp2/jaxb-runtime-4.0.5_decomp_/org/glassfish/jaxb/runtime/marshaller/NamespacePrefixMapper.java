package org.glassfish.jaxb.runtime.marshaller;

public abstract class NamespacePrefixMapper {
   private static final String[] EMPTY_STRING = new String[0];

   protected NamespacePrefixMapper() {
   }

   public abstract String getPreferredPrefix(String var1, String var2, boolean var3);

   public String[] getPreDeclaredNamespaceUris() {
      return EMPTY_STRING;
   }

   public String[] getPreDeclaredNamespaceUris2() {
      return EMPTY_STRING;
   }

   public String[] getContextualNamespaceDecls() {
      return EMPTY_STRING;
   }
}
