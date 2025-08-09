package com.univocity.parsers.common;

import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.common.processor.RowWriterProcessor;
import java.util.Map;

public abstract class CommonWriterSettings extends CommonSettings {
   private RowWriterProcessor rowWriterProcessor;
   private Boolean headerWritingEnabled = null;
   private String emptyValue = "";
   private boolean expandIncompleteRows = false;
   private boolean columnReorderingEnabled = false;

   public String getEmptyValue() {
      return this.emptyValue;
   }

   public void setEmptyValue(String emptyValue) {
      this.emptyValue = emptyValue;
   }

   public RowWriterProcessor getRowWriterProcessor() {
      return this.rowWriterProcessor;
   }

   public void setRowWriterProcessor(RowWriterProcessor rowWriterProcessor) {
      this.rowWriterProcessor = rowWriterProcessor;
   }

   public final boolean isHeaderWritingEnabled() {
      return this.headerWritingEnabled == null ? false : this.headerWritingEnabled;
   }

   public final void setHeaderWritingEnabled(boolean headerWritingEnabled) {
      this.headerWritingEnabled = headerWritingEnabled;
   }

   public final boolean getExpandIncompleteRows() {
      return this.expandIncompleteRows;
   }

   public final void setExpandIncompleteRows(boolean expandIncompleteRows) {
      this.expandIncompleteRows = expandIncompleteRows;
   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
      out.put("Empty value", this.emptyValue);
      out.put("Header writing enabled", this.headerWritingEnabled);
      out.put("Row processor", this.rowWriterProcessor == null ? "none" : this.rowWriterProcessor.getClass().getName());
   }

   final void runAutomaticConfiguration() {
      if (this.rowWriterProcessor instanceof BeanWriterProcessor) {
         Class<?> beanClass = ((BeanWriterProcessor)this.rowWriterProcessor).getBeanClass();
         this.configureFromAnnotations(beanClass);
      }

   }

   protected void configureFromAnnotations(Class beanClass) {
      if (this.deriveHeadersFrom(beanClass)) {
         Headers headerAnnotation = AnnotationHelper.findHeadersAnnotation(beanClass);
         String[] headersFromBean = AnnotationHelper.deriveHeaderNamesFromFields(beanClass, MethodFilter.ONLY_GETTERS);
         boolean writeHeaders = false;
         if (headerAnnotation != null) {
            if (headerAnnotation.sequence().length > 0) {
               headersFromBean = headerAnnotation.sequence();
            }

            writeHeaders = headerAnnotation.write();
         }

         if (this.headerWritingEnabled == null) {
            this.headerWritingEnabled = writeHeaders;
         }

         if (this.getHeaders() == null && headersFromBean.length > 0) {
            this.setHeadersDerivedFromClass(beanClass, headersFromBean);
         }

      }
   }

   protected CommonWriterSettings clone(boolean clearInputSpecificSettings) {
      return (CommonWriterSettings)super.clone(clearInputSpecificSettings);
   }

   protected CommonWriterSettings clone() {
      return (CommonWriterSettings)super.clone();
   }

   protected void clearInputSpecificSettings() {
      super.clearInputSpecificSettings();
      this.rowWriterProcessor = null;
   }

   public boolean isColumnReorderingEnabled() {
      return this.columnReorderingEnabled;
   }

   public void setColumnReorderingEnabled(boolean columnReorderingEnabled) {
      this.columnReorderingEnabled = columnReorderingEnabled;
   }
}
