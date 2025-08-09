package org.apache.hadoop.hive.conf.valcoersion;

public abstract class VariableCoercion {
   private final String name;

   public VariableCoercion(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public abstract String getCoerced(String var1);
}
