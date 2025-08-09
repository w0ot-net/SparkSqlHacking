package org.datanucleus.metadata;

public enum MetadataFileType {
   JDO_FILE("jdo"),
   JDO_ORM_FILE("orm"),
   JDO_QUERY_FILE("jdoquery"),
   ANNOTATIONS("annotations"),
   JPA_MAPPING_FILE("jpa_mapping");

   String name;

   private MetadataFileType(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }
}
