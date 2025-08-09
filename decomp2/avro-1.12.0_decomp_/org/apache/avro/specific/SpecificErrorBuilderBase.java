package org.apache.avro.specific;

import java.lang.reflect.Constructor;
import org.apache.avro.Schema;
import org.apache.avro.data.ErrorBuilder;
import org.apache.avro.data.RecordBuilderBase;

public abstract class SpecificErrorBuilderBase extends RecordBuilderBase implements ErrorBuilder {
   private Constructor errorConstructor;
   private Object value;
   private boolean hasValue;
   private Throwable cause;
   private boolean hasCause;

   protected SpecificErrorBuilderBase(Schema schema) {
      super((Schema)schema, SpecificData.get());
   }

   protected SpecificErrorBuilderBase(Schema schema, SpecificData model) {
      super((Schema)schema, model);
   }

   protected SpecificErrorBuilderBase(SpecificErrorBuilderBase other) {
      super((RecordBuilderBase)other, SpecificData.get());
      this.errorConstructor = other.errorConstructor;
      this.value = other.value;
      this.hasValue = other.hasValue;
      this.cause = other.cause;
      this.hasCause = other.hasCause;
   }

   protected SpecificErrorBuilderBase(SpecificExceptionBase other) {
      super((Schema)other.getSchema(), SpecificData.get());
      Object otherValue = other.getValue();
      if (otherValue != null) {
         this.setValue(otherValue);
      }

      Throwable otherCause = other.getCause();
      if (otherCause != null) {
         this.setCause(otherCause);
      }

   }

   public Object getValue() {
      return this.value;
   }

   public SpecificErrorBuilderBase setValue(Object value) {
      this.value = value;
      this.hasValue = true;
      return this;
   }

   public boolean hasValue() {
      return this.hasValue;
   }

   public SpecificErrorBuilderBase clearValue() {
      this.value = null;
      this.hasValue = false;
      return this;
   }

   public Throwable getCause() {
      return this.cause;
   }

   public SpecificErrorBuilderBase setCause(Throwable cause) {
      this.cause = cause;
      this.hasCause = true;
      return this;
   }

   public boolean hasCause() {
      return this.hasCause;
   }

   public SpecificErrorBuilderBase clearCause() {
      this.cause = null;
      this.hasCause = false;
      return this;
   }
}
