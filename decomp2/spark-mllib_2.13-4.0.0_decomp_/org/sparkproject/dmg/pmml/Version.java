package org.sparkproject.dmg.pmml;

public enum Version {
   PMML_3_0("http://www.dmg.org/PMML-3_0") {
      public Version previous() {
         return null;
      }
   },
   PMML_3_1("http://www.dmg.org/PMML-3_1"),
   PMML_3_2("http://www.dmg.org/PMML-3_2"),
   PMML_4_0("http://www.dmg.org/PMML-4_0"),
   PMML_4_1("http://www.dmg.org/PMML-4_1"),
   PMML_4_2("http://www.dmg.org/PMML-4_2"),
   PMML_4_3("http://www.dmg.org/PMML-4_3"),
   PMML_4_4("http://www.dmg.org/PMML-4_4") {
      public Version next() {
         return null;
      }
   },
   XPMML("http://xpmml.org/XPMML") {
      public boolean isStandard() {
         return false;
      }

      public String getVersion() {
         throw new UnsupportedOperationException();
      }

      public Version previous() {
         return null;
      }

      public Version next() {
         return null;
      }
   };

   private String namespaceURI = null;
   private static final String REGEX_PMML_XMLNS = "http://www\\.dmg\\.org/PMML\\-\\d_\\d";

   private Version(String namespaceURI) {
      this.setNamespaceURI(namespaceURI);
   }

   public boolean isStandard() {
      return true;
   }

   public String getNamespaceURI() {
      return this.namespaceURI;
   }

   private void setNamespaceURI(String namespaceURI) {
      this.namespaceURI = namespaceURI;
   }

   public String getVersion() {
      String namespaceURI = this.getNamespaceURI();
      String version = namespaceURI.substring("http://www.dmg.org/PMML-".length());
      return version.replace('_', '.');
   }

   public Version previous() {
      Version[] versions = values();
      return versions[this.ordinal() - 1];
   }

   public Version next() {
      Version[] versions = values();
      return versions[this.ordinal() + 1];
   }

   public static Version getMinimum() {
      Version[] versions = values();
      return versions[0];
   }

   public static Version getMaximum() {
      Version[] versions = values();
      return versions[versions.length - 1 - 1];
   }

   public static Version forNamespaceURI(String namespaceURI) {
      Version[] versions = values();

      for(Version version : versions) {
         if (version.getNamespaceURI().equals(namespaceURI)) {
            return version;
         }
      }

      boolean valid = namespaceURI != null && namespaceURI.matches("http://www\\.dmg\\.org/PMML\\-\\d_\\d");
      if (!valid) {
         throw new IllegalArgumentException("PMML namespace URI " + namespaceURI + " does not match 'http://www\\.dmg\\.org/PMML\\-\\d_\\d' regex pattern");
      } else {
         throw new IllegalArgumentException("PMML namespace URI " + namespaceURI + " is not supported");
      }
   }
}
