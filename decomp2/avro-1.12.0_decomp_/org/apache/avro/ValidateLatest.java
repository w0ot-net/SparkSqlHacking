package org.apache.avro;

import java.util.Iterator;

public final class ValidateLatest implements SchemaValidator {
   private final SchemaValidationStrategy strategy;

   public ValidateLatest(SchemaValidationStrategy strategy) {
      this.strategy = strategy;
   }

   public void validate(Schema toValidate, Iterable schemasInOrder) throws SchemaValidationException {
      Iterator<Schema> schemas = schemasInOrder.iterator();
      if (schemas.hasNext()) {
         Schema existing = (Schema)schemas.next();
         this.strategy.validate(toValidate, existing);
      }

   }
}
