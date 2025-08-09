package org.apache.avro;

public class LogicalType {
   public static final String LOGICAL_TYPE_PROP = "logicalType";
   private static final String[] INCOMPATIBLE_PROPS = new String[]{"avro.java.string", "java-class", "java-key-class", "java-element-class"};
   private final String name;

   public LogicalType(String logicalTypeName) {
      this.name = logicalTypeName.intern();
   }

   public String getName() {
      return this.name;
   }

   public Schema addToSchema(Schema schema) {
      this.validate(schema);
      schema.addProp("logicalType", this.name);
      schema.setLogicalType(this);
      return schema;
   }

   public void validate(Schema schema) {
      for(String incompatible : INCOMPATIBLE_PROPS) {
         if (schema.getProp(incompatible) != null) {
            throw new IllegalArgumentException("logicalType cannot be used with " + incompatible);
         }
      }

   }
}
