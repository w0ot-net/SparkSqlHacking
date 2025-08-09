package com.univocity.parsers.common;

import [Ljava.lang.Object;;
import java.util.HashMap;
import java.util.Map;

public class DataProcessingException extends TextParsingException {
   private static final long serialVersionUID = 1410975527141918215L;
   private String columnName;
   private int columnIndex;
   private Object[] row;
   private Object value;
   private Map values;
   private boolean fatal;
   private boolean handled;
   private String details;

   public DataProcessingException(String message) {
      this(message, -1, (Object[])null, (Throwable)null);
   }

   public DataProcessingException(String message, Throwable cause) {
      this(message, -1, (Object[])null, cause);
   }

   public DataProcessingException(String message, Object[] row) {
      this(message, -1, row, (Throwable)null);
   }

   public DataProcessingException(String message, Object[] row, Throwable cause) {
      this(message, -1, row, cause);
   }

   public DataProcessingException(String message, int columnIndex) {
      this(message, columnIndex, (Object[])null, (Throwable)null);
   }

   public DataProcessingException(String message, int columnIndex, Object[] row, Throwable cause) {
      super((Context)null, message, cause);
      this.values = new HashMap();
      this.fatal = true;
      this.handled = false;
      this.details = null;
      this.setColumnIndex(columnIndex);
      this.row = row;
   }

   protected String getErrorDescription() {
      return "Error processing parsed input";
   }

   protected String getDetails() {
      String details = (this.details == null ? "" : this.details + '\n') + super.getDetails();
      Object[] row = this.getRow();
      if (row != null) {
         row = ((Object;)row).clone();

         for(int i = 0; i < row.length; ++i) {
            row[i] = this.restrictContent(row[i]);
         }
      }

      details = printIfNotEmpty(details, "row", row);
      details = printIfNotEmpty(details, "value", this.restrictContent(this.getValue()));
      details = printIfNotEmpty(details, "columnName", this.getColumnName());
      details = printIfNotEmpty(details, "columnIndex", this.getColumnIndex());
      return details;
   }

   public String getColumnName() {
      if (this.columnName != null) {
         return this.columnName;
      } else {
         String[] headers = this.getHeaders();
         return headers != null && this.getExtractedColumnIndex() != -1 && this.getExtractedColumnIndex() < headers.length ? headers[this.getExtractedColumnIndex()] : null;
      }
   }

   public final int getColumnIndex() {
      return this.columnIndex;
   }

   public final Object[] getRow() {
      return this.restrictContent(this.row);
   }

   public final void setValue(Object value) {
      if (this.errorContentLength == 0) {
         value = null;
      }

      if (value == null) {
         value = "null";
      }

      this.value = value;
   }

   public final void setValue(String label, Object value) {
      if (this.errorContentLength == 0) {
         value = null;
      }

      this.values.put(label, value);
   }

   public final Object getValue() {
      if (this.errorContentLength == 0) {
         return null;
      } else if (this.value != null) {
         return this.value;
      } else {
         return this.row != null && this.columnIndex != -1 && this.columnIndex < this.row.length ? this.row[this.columnIndex] : null;
      }
   }

   public final void setColumnIndex(int columnIndex) {
      this.columnIndex = columnIndex;
   }

   private int getExtractedColumnIndex() {
      return this.extractedIndexes != null && this.columnIndex < this.extractedIndexes.length && this.columnIndex > -1 ? this.extractedIndexes[this.columnIndex] : this.columnIndex;
   }

   public final void setColumnName(String columnName) {
      this.columnName = columnName;
   }

   public final void setRow(Object[] row) {
      if (this.errorContentLength == 0) {
         row = null;
      }

      this.row = row;
   }

   final boolean isFatal() {
      return this.fatal;
   }

   public final void markAsNonFatal() {
      this.fatal = false;
   }

   public final void markAsHandled(ProcessorErrorHandler handler) {
      this.handled = handler != null && !(handler instanceof NoopProcessorErrorHandler) && !(handler instanceof NoopRowProcessorErrorHandler);
   }

   public boolean isHandled() {
      return this.handled;
   }

   public void setDetails(String details) {
      this.details = details != null && !details.trim().isEmpty() ? details : null;
   }

   protected final String updateMessage(String msg) {
      if (this.errorContentLength != 0 && msg != null) {
         StringBuilder out = new StringBuilder(msg.length());
         int previous = 0;
         int start = 0;

         while(true) {
            start = msg.indexOf(123, start);
            if (start == -1) {
               break;
            }

            int end = msg.indexOf(125, start);
            if (end == -1) {
               break;
            }

            String label = msg.substring(start + 1, end);
            Object value = null;
            if ("value".equals(label)) {
               value = this.value;
            } else if (this.values.containsKey(label)) {
               value = this.values.get(label);
            }

            if (value != null) {
               String content = this.restrictContent(value);
               out.append(msg, previous, start);
               out.append(content);
               previous = end;
            }

            start = end;
         }

         out.append(msg, previous == 0 ? 0 : previous + 1, msg.length());
         return out.toString();
      } else {
         return msg;
      }
   }
}
