package org.apache.derby.impl.tools.ij;

class QualifiedIdentifier {
   private String sessionName;
   private String localName;

   QualifiedIdentifier(String var1, String var2) {
      this.sessionName = var1;
      this.localName = var2;
   }

   public String getLocalName() {
      return this.localName;
   }

   public String getSessionName() {
      return this.sessionName;
   }

   public String toString() {
      return this.localName + "@" + this.sessionName;
   }
}
