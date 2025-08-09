package org.apache.hadoop.hive.serde2.dynamic_type;

public class DynamicSerDeFieldRequiredness extends SimpleNode {
   protected RequirednessTypes requiredness;

   public RequirednessTypes getRequiredness() {
      return this.requiredness;
   }

   public DynamicSerDeFieldRequiredness(int id) {
      super(id);
   }

   public DynamicSerDeFieldRequiredness(thrift_grammar p, int id) {
      super(p, id);
   }

   public static enum RequirednessTypes {
      Required,
      Skippable,
      Optional;
   }
}
