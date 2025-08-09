package org.apache.avro.data;

public interface ErrorBuilder extends RecordBuilder {
   Object getValue();

   ErrorBuilder setValue(Object value);

   boolean hasValue();

   ErrorBuilder clearValue();

   Throwable getCause();

   ErrorBuilder setCause(Throwable cause);

   boolean hasCause();

   ErrorBuilder clearCause();
}
