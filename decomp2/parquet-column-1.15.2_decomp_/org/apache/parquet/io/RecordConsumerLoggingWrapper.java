package org.apache.parquet.io;

import java.util.Arrays;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.RecordConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordConsumerLoggingWrapper extends RecordConsumer {
   private static final Logger LOG = LoggerFactory.getLogger(RecordConsumerLoggingWrapper.class);
   private final RecordConsumer delegate;
   int indent = 0;

   public RecordConsumerLoggingWrapper(RecordConsumer delegate) {
      this.delegate = delegate;
   }

   public void startField(String field, int index) {
      this.logOpen(field);
      this.delegate.startField(field, index);
   }

   private void logOpen(String field) {
      this.log("<{}>", field);
   }

   private String indent() {
      StringBuilder result = new StringBuilder();

      for(int i = 0; i < this.indent; ++i) {
         result.append("  ");
      }

      return result.toString();
   }

   private void log(Object value, Object... parameters) {
      if (LOG.isDebugEnabled()) {
         LOG.debug(this.indent() + value, parameters);
      }

   }

   public void startGroup() {
      ++this.indent;
      this.log("<!-- start group -->");
      this.delegate.startGroup();
   }

   public void addInteger(int value) {
      this.log(value);
      this.delegate.addInteger(value);
   }

   public void addLong(long value) {
      this.log(value);
      this.delegate.addLong(value);
   }

   public void addBoolean(boolean value) {
      this.log(value);
      this.delegate.addBoolean(value);
   }

   public void addBinary(Binary value) {
      if (LOG.isDebugEnabled()) {
         this.log(Arrays.toString(value.getBytesUnsafe()));
      }

      this.delegate.addBinary(value);
   }

   public void addFloat(float value) {
      this.log(value);
      this.delegate.addFloat(value);
   }

   public void addDouble(double value) {
      this.log(value);
      this.delegate.addDouble(value);
   }

   public void flush() {
      this.log("<!-- flush -->");
      this.delegate.flush();
   }

   public void endGroup() {
      this.log("<!-- end group -->");
      --this.indent;
      this.delegate.endGroup();
   }

   public void endField(String field, int index) {
      this.logClose(field);
      this.delegate.endField(field, index);
   }

   private void logClose(String field) {
      this.log("</{}>", field);
   }

   public void startMessage() {
      this.log("<!-- start message -->");
      this.delegate.startMessage();
   }

   public void endMessage() {
      this.delegate.endMessage();
      this.log("<!-- end message -->");
   }
}
