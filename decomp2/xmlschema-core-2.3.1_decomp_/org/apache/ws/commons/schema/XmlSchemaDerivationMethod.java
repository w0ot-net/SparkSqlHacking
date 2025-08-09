package org.apache.ws.commons.schema;

public class XmlSchemaDerivationMethod {
   public static final XmlSchemaDerivationMethod NONE = new XmlSchemaDerivationMethod();
   private boolean all;
   private boolean empty;
   private boolean extension;
   private boolean list;
   private boolean restriction;
   private boolean substitution;
   private boolean union;

   public static XmlSchemaDerivationMethod schemaValueOf(String name) {
      String[] tokens = name.split("\\s");
      XmlSchemaDerivationMethod method = new XmlSchemaDerivationMethod();

      for(String t : tokens) {
         if (!"#all".equalsIgnoreCase(t) && !"all".equalsIgnoreCase(t)) {
            if (method.isAll()) {
               throw new XmlSchemaException("Derivation method cannot be #all and something else.");
            }

            if ("extension".equals(t)) {
               method.setExtension(true);
            } else if ("list".equals(t)) {
               method.setList(true);
            } else if ("restriction".equals(t)) {
               method.setRestriction(true);
            } else if ("substitution".equals(t)) {
               method.setSubstitution(true);
            } else if ("union".equals(t)) {
               method.setUnion(true);
            }
         } else {
            if (method.notAll()) {
               throw new XmlSchemaException("Derivation method cannot be #all and something else.");
            }

            method.setAll(true);
         }
      }

      return method;
   }

   public String toString() {
      if (this.isAll()) {
         return "#all";
      } else {
         StringBuilder sb = new StringBuilder();
         if (this.isExtension()) {
            sb.append("extension ");
         }

         if (this.isList()) {
            sb.append("list ");
         }

         if (this.isRestriction()) {
            sb.append("restriction ");
         }

         if (this.isSubstitution()) {
            sb.append("substitution ");
         }

         if (this.isUnion()) {
            sb.append("union ");
         }

         return sb.toString().trim();
      }
   }

   public boolean notAll() {
      return this.empty || this.extension || this.list || this.restriction || this.substitution || this.union;
   }

   public boolean isAll() {
      return this.all;
   }

   public void setAll(boolean all) {
      this.all = all;
      if (all) {
         this.empty = false;
         this.extension = false;
         this.list = false;
         this.restriction = false;
         this.substitution = false;
         this.union = false;
      }

   }

   public boolean isEmpty() {
      return this.empty;
   }

   public void setEmpty(boolean empty) {
      this.empty = empty;
   }

   public boolean isExtension() {
      return this.extension;
   }

   public void setExtension(boolean extension) {
      this.extension = extension;
   }

   public boolean isList() {
      return this.list;
   }

   public void setList(boolean list) {
      this.list = list;
   }

   public boolean isNone() {
      return !this.all && !this.empty && !this.extension && !this.list && !this.restriction && !this.substitution && !this.union;
   }

   public void setNone(boolean none) {
      this.all = false;
      this.empty = false;
      this.extension = false;
      this.list = false;
      this.restriction = false;
      this.substitution = false;
      this.union = false;
   }

   public boolean isRestriction() {
      return this.restriction;
   }

   public void setRestriction(boolean restriction) {
      this.restriction = restriction;
   }

   public boolean isSubstitution() {
      return this.substitution;
   }

   public void setSubstitution(boolean substitution) {
      this.substitution = substitution;
   }

   public boolean isUnion() {
      return this.union;
   }

   public void setUnion(boolean union) {
      this.union = union;
   }
}
