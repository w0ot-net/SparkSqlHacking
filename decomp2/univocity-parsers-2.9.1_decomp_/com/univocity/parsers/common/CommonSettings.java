package com.univocity.parsers.common;

import com.univocity.parsers.common.fields.ExcludeFieldEnumSelector;
import com.univocity.parsers.common.fields.ExcludeFieldIndexSelector;
import com.univocity.parsers.common.fields.ExcludeFieldNameSelector;
import com.univocity.parsers.common.fields.FieldEnumSelector;
import com.univocity.parsers.common.fields.FieldIndexSelector;
import com.univocity.parsers.common.fields.FieldNameSelector;
import com.univocity.parsers.common.fields.FieldSelector;
import com.univocity.parsers.common.fields.FieldSet;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public abstract class CommonSettings implements Cloneable {
   private Format format;
   private String nullValue = null;
   private int maxCharsPerColumn = 4096;
   private int maxColumns = 512;
   private boolean skipEmptyLines = true;
   private boolean ignoreTrailingWhitespaces = true;
   private boolean ignoreLeadingWhitespaces = true;
   private FieldSelector fieldSelector = null;
   private boolean autoConfigurationEnabled = true;
   private ProcessorErrorHandler errorHandler;
   private int errorContentLength = -1;
   private boolean skipBitsAsWhitespace = true;
   private String[] headers;
   Class headerSourceClass;

   public CommonSettings() {
      this.setFormat(this.createDefaultFormat());
   }

   public String getNullValue() {
      return this.nullValue;
   }

   public void setNullValue(String emptyValue) {
      this.nullValue = emptyValue;
   }

   public int getMaxCharsPerColumn() {
      return this.maxCharsPerColumn;
   }

   public void setMaxCharsPerColumn(int maxCharsPerColumn) {
      this.maxCharsPerColumn = maxCharsPerColumn;
   }

   public boolean getSkipEmptyLines() {
      return this.skipEmptyLines;
   }

   public void setSkipEmptyLines(boolean skipEmptyLines) {
      this.skipEmptyLines = skipEmptyLines;
   }

   public boolean getIgnoreTrailingWhitespaces() {
      return this.ignoreTrailingWhitespaces;
   }

   public void setIgnoreTrailingWhitespaces(boolean ignoreTrailingWhitespaces) {
      this.ignoreTrailingWhitespaces = ignoreTrailingWhitespaces;
   }

   public boolean getIgnoreLeadingWhitespaces() {
      return this.ignoreLeadingWhitespaces;
   }

   public void setIgnoreLeadingWhitespaces(boolean ignoreLeadingWhitespaces) {
      this.ignoreLeadingWhitespaces = ignoreLeadingWhitespaces;
   }

   public void setHeaders(String... headers) {
      if (headers != null && headers.length != 0) {
         this.headers = headers;
      } else {
         this.headers = null;
      }

   }

   void setHeadersDerivedFromClass(Class headerSourceClass, String... headers) {
      this.headerSourceClass = headerSourceClass;
      this.setHeaders(headers);
   }

   boolean deriveHeadersFrom(Class beanClass) {
      if (this.headerSourceClass != null) {
         if (this.headerSourceClass == beanClass) {
            return false;
         }

         this.setHeaders((String[])null);
      }

      return true;
   }

   public String[] getHeaders() {
      return this.headers;
   }

   public int getMaxColumns() {
      return this.maxColumns;
   }

   public void setMaxColumns(int maxColumns) {
      this.maxColumns = maxColumns;
   }

   public Format getFormat() {
      return this.format;
   }

   public void setFormat(Format format) {
      if (format == null) {
         throw new IllegalArgumentException("Format cannot be null");
      } else {
         this.format = format;
      }
   }

   public FieldSet selectFields(String... fieldNames) {
      return this.setFieldSet(new FieldNameSelector(), fieldNames);
   }

   public FieldSet excludeFields(String... fieldNames) {
      return this.setFieldSet(new ExcludeFieldNameSelector(), fieldNames);
   }

   public FieldSet selectIndexes(Integer... fieldIndexes) {
      return this.setFieldSet(new FieldIndexSelector(), fieldIndexes);
   }

   public FieldSet excludeIndexes(Integer... fieldIndexes) {
      return this.setFieldSet(new ExcludeFieldIndexSelector(), fieldIndexes);
   }

   public FieldSet selectFields(Enum... columns) {
      return this.setFieldSet(new FieldEnumSelector(), columns);
   }

   public FieldSet excludeFields(Enum... columns) {
      return this.setFieldSet(new ExcludeFieldEnumSelector(), columns);
   }

   private FieldSet setFieldSet(FieldSet fieldSet, Object... values) {
      this.fieldSelector = (FieldSelector)fieldSet;
      fieldSet.add(values);
      return fieldSet;
   }

   FieldSet getFieldSet() {
      return (FieldSet)this.fieldSelector;
   }

   FieldSelector getFieldSelector() {
      return this.fieldSelector;
   }

   public final boolean isAutoConfigurationEnabled() {
      return this.autoConfigurationEnabled;
   }

   public final void setAutoConfigurationEnabled(boolean autoConfigurationEnabled) {
      this.autoConfigurationEnabled = autoConfigurationEnabled;
   }

   /** @deprecated */
   @Deprecated
   public RowProcessorErrorHandler getRowProcessorErrorHandler() {
      return this.errorHandler == null ? NoopRowProcessorErrorHandler.instance : (RowProcessorErrorHandler)this.errorHandler;
   }

   /** @deprecated */
   @Deprecated
   public void setRowProcessorErrorHandler(RowProcessorErrorHandler rowProcessorErrorHandler) {
      this.errorHandler = rowProcessorErrorHandler;
   }

   public ProcessorErrorHandler getProcessorErrorHandler() {
      return this.errorHandler == null ? NoopProcessorErrorHandler.instance : this.errorHandler;
   }

   public void setProcessorErrorHandler(ProcessorErrorHandler processorErrorHandler) {
      this.errorHandler = processorErrorHandler;
   }

   public boolean isProcessorErrorHandlerDefined() {
      return this.errorHandler != null;
   }

   protected abstract Format createDefaultFormat();

   final void autoConfigure() {
      if (this.autoConfigurationEnabled) {
         this.runAutomaticConfiguration();
      }
   }

   public final void trimValues(boolean trim) {
      this.setIgnoreLeadingWhitespaces(trim);
      this.setIgnoreTrailingWhitespaces(trim);
   }

   public int getErrorContentLength() {
      return this.errorContentLength;
   }

   public void setErrorContentLength(int errorContentLength) {
      this.errorContentLength = errorContentLength;
   }

   void runAutomaticConfiguration() {
   }

   public final boolean getSkipBitsAsWhitespace() {
      return this.skipBitsAsWhitespace;
   }

   public final void setSkipBitsAsWhitespace(boolean skipBitsAsWhitespace) {
      this.skipBitsAsWhitespace = skipBitsAsWhitespace;
   }

   protected final int getWhitespaceRangeStart() {
      return this.skipBitsAsWhitespace ? -1 : 1;
   }

   public final String toString() {
      StringBuilder out = new StringBuilder();
      out.append(this.getClass().getSimpleName()).append(':');
      TreeMap<String, Object> config = new TreeMap();
      this.addConfiguration(config);

      for(Map.Entry e : config.entrySet()) {
         out.append("\n\t");
         out.append((String)e.getKey()).append('=').append(e.getValue());
      }

      out.append("Format configuration:\n\t").append(this.getFormat().toString());
      return out.toString();
   }

   protected void addConfiguration(Map out) {
      out.put("Null value", this.nullValue);
      out.put("Maximum number of characters per column", this.maxCharsPerColumn);
      out.put("Maximum number of columns", this.maxColumns);
      out.put("Skip empty lines", this.skipEmptyLines);
      out.put("Ignore trailing whitespaces", this.ignoreTrailingWhitespaces);
      out.put("Ignore leading whitespaces", this.ignoreLeadingWhitespaces);
      out.put("Selected fields", this.fieldSelector == null ? "none" : this.fieldSelector.describe());
      out.put("Headers", Arrays.toString(this.headers));
      out.put("Auto configuration enabled", this.autoConfigurationEnabled);
      out.put("RowProcessor error handler", this.errorHandler);
      out.put("Length of content displayed on error", this.errorContentLength);
      out.put("Restricting data in exceptions", this.errorContentLength == 0);
      out.put("Skip bits as whitespace", this.skipBitsAsWhitespace);
   }

   protected CommonSettings clone(boolean clearInputSpecificSettings) {
      try {
         CommonSettings out = (CommonSettings)super.clone();
         if (out.format != null) {
            out.format = out.format.clone();
         }

         if (clearInputSpecificSettings) {
            out.clearInputSpecificSettings();
         }

         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   protected CommonSettings clone() {
      return this.clone(false);
   }

   protected void clearInputSpecificSettings() {
      this.fieldSelector = null;
      this.headers = null;
   }
}
