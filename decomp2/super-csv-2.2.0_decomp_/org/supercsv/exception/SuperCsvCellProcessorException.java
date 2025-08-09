package org.supercsv.exception;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

public class SuperCsvCellProcessorException extends SuperCsvException {
   private static final long serialVersionUID = 1L;
   private final CellProcessor processor;

   public SuperCsvCellProcessorException(String msg, CsvContext context, CellProcessor processor) {
      super(msg, context);
      this.processor = processor;
   }

   public SuperCsvCellProcessorException(String msg, CsvContext context, CellProcessor processor, Throwable t) {
      super(msg, context, t);
      this.processor = processor;
   }

   public SuperCsvCellProcessorException(Class expectedType, Object actualValue, CsvContext context, CellProcessor processor) {
      super(getUnexpectedTypeMessage(expectedType, actualValue), context);
      this.processor = processor;
   }

   private static String getUnexpectedTypeMessage(Class expectedType, Object actualValue) {
      if (expectedType == null) {
         throw new NullPointerException("expectedType should not be null");
      } else {
         String expectedClassName = expectedType.getName();
         String actualClassName = actualValue != null ? actualValue.getClass().getName() : "null";
         return String.format("the input value should be of type %s but is %s", expectedClassName, actualClassName);
      }
   }

   public CellProcessor getProcessor() {
      return this.processor;
   }

   public String toString() {
      return String.format("%s: %s%nprocessor=%s%ncontext=%s", this.getClass().getName(), this.getMessage(), this.processor, this.getCsvContext());
   }
}
