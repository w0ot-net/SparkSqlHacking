package com.univocity.parsers.common;

import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.fields.FieldSelector;
import com.univocity.parsers.common.fields.FieldSet;
import com.univocity.parsers.common.input.CharAppender;
import com.univocity.parsers.common.input.CharInputReader;
import com.univocity.parsers.common.input.DefaultCharAppender;
import com.univocity.parsers.common.input.DefaultCharInputReader;
import com.univocity.parsers.common.input.ExpandingCharAppender;
import com.univocity.parsers.common.input.InputAnalysisProcess;
import com.univocity.parsers.common.input.concurrent.ConcurrentCharInputReader;
import com.univocity.parsers.common.processor.NoopRowProcessor;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.common.processor.core.AbstractBeanProcessor;
import com.univocity.parsers.common.processor.core.AbstractMultiBeanProcessor;
import com.univocity.parsers.common.processor.core.ColumnOrderDependent;
import com.univocity.parsers.common.processor.core.NoopProcessor;
import com.univocity.parsers.common.processor.core.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CommonParserSettings extends CommonSettings {
   protected Boolean headerExtractionEnabled = null;
   private Processor processor;
   private boolean columnReorderingEnabled = true;
   private int inputBufferSize = 1048576;
   private boolean readInputOnSeparateThread = Runtime.getRuntime().availableProcessors() > 1;
   private long numberOfRecordsToRead = -1L;
   private boolean lineSeparatorDetectionEnabled = false;
   private long numberOfRowsToSkip = 0L;
   private boolean commentCollectionEnabled = false;
   private boolean autoClosingEnabled = true;
   private boolean commentProcessingEnabled = true;
   private List inputAnalysisProcesses = new ArrayList();

   public boolean getReadInputOnSeparateThread() {
      return this.readInputOnSeparateThread;
   }

   public void setReadInputOnSeparateThread(boolean readInputOnSeparateThread) {
      this.readInputOnSeparateThread = readInputOnSeparateThread;
   }

   public boolean isHeaderExtractionEnabled() {
      return this.headerExtractionEnabled == null ? false : this.headerExtractionEnabled;
   }

   public void setHeaderExtractionEnabled(boolean headerExtractionEnabled) {
      this.headerExtractionEnabled = headerExtractionEnabled;
   }

   /** @deprecated */
   @Deprecated
   public RowProcessor getRowProcessor() {
      return this.processor == null ? NoopRowProcessor.instance : (RowProcessor)this.processor;
   }

   /** @deprecated */
   @Deprecated
   public void setRowProcessor(RowProcessor processor) {
      this.processor = processor;
   }

   public Processor getProcessor() {
      return this.processor == null ? NoopProcessor.instance : this.processor;
   }

   public void setProcessor(Processor processor) {
      this.processor = processor;
   }

   protected CharInputReader newCharInputReader(int whitespaceRangeStart) {
      if (this.readInputOnSeparateThread) {
         return this.lineSeparatorDetectionEnabled ? new ConcurrentCharInputReader(this.getFormat().getNormalizedNewline(), this.getInputBufferSize(), 10, whitespaceRangeStart, this.autoClosingEnabled) : new ConcurrentCharInputReader(this.getFormat().getLineSeparator(), this.getFormat().getNormalizedNewline(), this.getInputBufferSize(), 10, whitespaceRangeStart, this.autoClosingEnabled);
      } else {
         return this.lineSeparatorDetectionEnabled ? new DefaultCharInputReader(this.getFormat().getNormalizedNewline(), this.getInputBufferSize(), whitespaceRangeStart, this.autoClosingEnabled) : new DefaultCharInputReader(this.getFormat().getLineSeparator(), this.getFormat().getNormalizedNewline(), this.getInputBufferSize(), whitespaceRangeStart, this.autoClosingEnabled);
      }
   }

   public long getNumberOfRecordsToRead() {
      return this.numberOfRecordsToRead;
   }

   public void setNumberOfRecordsToRead(long numberOfRecordsToRead) {
      this.numberOfRecordsToRead = numberOfRecordsToRead;
   }

   public boolean isColumnReorderingEnabled() {
      return !this.preventReordering() && this.columnReorderingEnabled;
   }

   FieldSet getFieldSet() {
      return this.preventReordering() ? null : super.getFieldSet();
   }

   FieldSelector getFieldSelector() {
      return this.preventReordering() ? null : super.getFieldSelector();
   }

   public void setColumnReorderingEnabled(boolean columnReorderingEnabled) {
      if (columnReorderingEnabled && this.preventReordering()) {
         throw new IllegalArgumentException("Cannot reorder columns when using a row processor that manipulates nested rows.");
      } else {
         this.columnReorderingEnabled = columnReorderingEnabled;
      }
   }

   public int getInputBufferSize() {
      return this.inputBufferSize;
   }

   public void setInputBufferSize(int inputBufferSize) {
      this.inputBufferSize = inputBufferSize;
   }

   protected CharAppender newCharAppender() {
      int chars = this.getMaxCharsPerColumn();
      return (CharAppender)(chars != -1 ? new DefaultCharAppender(chars, this.getNullValue(), this.getWhitespaceRangeStart()) : new ExpandingCharAppender(this.getNullValue(), this.getWhitespaceRangeStart()));
   }

   public final boolean isLineSeparatorDetectionEnabled() {
      return this.lineSeparatorDetectionEnabled;
   }

   public final void setLineSeparatorDetectionEnabled(boolean lineSeparatorDetectionEnabled) {
      this.lineSeparatorDetectionEnabled = lineSeparatorDetectionEnabled;
   }

   public final long getNumberOfRowsToSkip() {
      return this.numberOfRowsToSkip;
   }

   public final void setNumberOfRowsToSkip(long numberOfRowsToSkip) {
      if (numberOfRowsToSkip < 0L) {
         throw new IllegalArgumentException("Number of rows to skip from the input must be 0 or greater");
      } else {
         this.numberOfRowsToSkip = numberOfRowsToSkip;
      }
   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
      out.put("Header extraction enabled", this.headerExtractionEnabled);
      out.put("Processor", this.processor == null ? "none" : this.processor.getClass().getName());
      out.put("Column reordering enabled", this.columnReorderingEnabled);
      out.put("Input buffer size", this.inputBufferSize);
      out.put("Input reading on separate thread", this.readInputOnSeparateThread);
      out.put("Number of records to read", this.numberOfRecordsToRead == -1L ? "all" : this.numberOfRecordsToRead);
      out.put("Line separator detection enabled", this.lineSeparatorDetectionEnabled);
      out.put("Auto-closing enabled", this.autoClosingEnabled);
   }

   private boolean preventReordering() {
      return this.processor instanceof ColumnOrderDependent ? ((ColumnOrderDependent)this.processor).preventColumnReordering() : false;
   }

   public boolean isCommentCollectionEnabled() {
      return this.commentCollectionEnabled;
   }

   public void setCommentCollectionEnabled(boolean commentCollectionEnabled) {
      this.commentCollectionEnabled = commentCollectionEnabled;
   }

   final void runAutomaticConfiguration() {
      Class<?> beanClass = null;
      if (this.processor instanceof AbstractBeanProcessor) {
         beanClass = ((AbstractBeanProcessor)this.processor).getBeanClass();
      } else if (this.processor instanceof AbstractMultiBeanProcessor) {
         Class[] classes = ((AbstractMultiBeanProcessor)this.processor).getBeanClasses();
         if (classes.length > 0) {
            beanClass = classes[0];
         }
      }

      if (beanClass != null) {
         this.configureFromAnnotations(beanClass);
      }

   }

   protected synchronized void configureFromAnnotations(Class beanClass) {
      if (this.deriveHeadersFrom(beanClass)) {
         Headers headerAnnotation = AnnotationHelper.findHeadersAnnotation(beanClass);
         String[] headersFromBean = ArgumentUtils.EMPTY_STRING_ARRAY;
         boolean allFieldsIndexBased = AnnotationHelper.allFieldsIndexBasedForParsing(beanClass);
         boolean extractHeaders = !allFieldsIndexBased;
         if (headerAnnotation != null) {
            if (headerAnnotation.sequence().length > 0) {
               headersFromBean = headerAnnotation.sequence();
            }

            extractHeaders = headerAnnotation.extract();
         }

         if (this.headerExtractionEnabled == null) {
            this.setHeaderExtractionEnabled(extractHeaders);
         }

         if (this.getHeaders() == null && headersFromBean.length > 0 && !this.headerExtractionEnabled) {
            this.setHeadersDerivedFromClass(beanClass, headersFromBean);
         }

         if (this.getFieldSet() == null) {
            if (allFieldsIndexBased) {
               this.selectIndexes(AnnotationHelper.getSelectedIndexes(beanClass, MethodFilter.ONLY_SETTERS));
            } else if (headersFromBean.length > 0 && AnnotationHelper.allFieldsNameBasedForParsing(beanClass)) {
               this.selectFields(headersFromBean);
            }
         }

      }
   }

   protected CommonParserSettings clone(boolean clearInputSpecificSettings) {
      return (CommonParserSettings)super.clone(clearInputSpecificSettings);
   }

   protected CommonParserSettings clone() {
      return (CommonParserSettings)super.clone();
   }

   protected void clearInputSpecificSettings() {
      super.clearInputSpecificSettings();
      this.processor = null;
      this.numberOfRecordsToRead = -1L;
      this.numberOfRowsToSkip = 0L;
   }

   public boolean isAutoClosingEnabled() {
      return this.autoClosingEnabled;
   }

   public void setAutoClosingEnabled(boolean autoClosingEnabled) {
      this.autoClosingEnabled = autoClosingEnabled;
   }

   public boolean isCommentProcessingEnabled() {
      return this.commentProcessingEnabled;
   }

   public void setCommentProcessingEnabled(boolean commentProcessingEnabled) {
      this.commentProcessingEnabled = commentProcessingEnabled;
   }

   public void addInputAnalysisProcess(InputAnalysisProcess inputAnalysisProcess) {
      if (inputAnalysisProcess != null) {
         if (this.inputAnalysisProcesses == null) {
            this.inputAnalysisProcesses = new ArrayList();
         }

         this.inputAnalysisProcesses.add(inputAnalysisProcess);
      }
   }

   public List getInputAnalysisProcesses() {
      return this.inputAnalysisProcesses;
   }
}
