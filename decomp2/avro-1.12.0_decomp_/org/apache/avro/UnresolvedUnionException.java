package org.apache.avro;

public class UnresolvedUnionException extends AvroRuntimeException {
   private Object unresolvedDatum;
   private Schema unionSchema;

   public UnresolvedUnionException(Schema unionSchema, Object unresolvedDatum) {
      String var10001 = String.valueOf(unionSchema);
      super("Not in union " + var10001 + ": " + String.valueOf(unresolvedDatum));
      this.unionSchema = unionSchema;
      this.unresolvedDatum = unresolvedDatum;
   }

   public UnresolvedUnionException(Schema unionSchema, Schema.Field field, Object unresolvedDatum) {
      String var10001 = String.valueOf(unionSchema);
      super("Not in union " + var10001 + ": " + String.valueOf(unresolvedDatum) + " (field=" + field.name() + ")");
      this.unionSchema = unionSchema;
      this.unresolvedDatum = unresolvedDatum;
   }

   public Object getUnresolvedDatum() {
      return this.unresolvedDatum;
   }

   public Schema getUnionSchema() {
      return this.unionSchema;
   }
}
