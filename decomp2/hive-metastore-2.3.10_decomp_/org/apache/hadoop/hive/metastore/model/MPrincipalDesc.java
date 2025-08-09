package org.apache.hadoop.hive.metastore.model;

public class MPrincipalDesc {
   private String name;
   private String type;

   public MPrincipalDesc() {
   }

   public MPrincipalDesc(String name, String type) {
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public int hashCode() {
      return this.type.hashCode() + this.name.hashCode();
   }

   public boolean equals(Object object) {
      MPrincipalDesc another = (MPrincipalDesc)object;
      return this.type.equals(another.type) && this.name.equals(another.name);
   }
}
