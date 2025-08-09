package com.univocity.parsers.fixed;

import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.CommonWriterSettings;
import com.univocity.parsers.common.NormalizedString;
import java.util.HashMap;
import java.util.Map;

public class FixedWidthWriterSettings extends CommonWriterSettings {
   private FixedWidthFields fieldLengths;
   private Map lookaheadFormats = new HashMap();
   private Map lookbehindFormats = new HashMap();
   private boolean useDefaultPaddingForHeaders = true;
   private FieldAlignment defaultAlignmentForHeaders = null;
   private boolean writeLineSeparatorAfterRecord = true;

   public FixedWidthWriterSettings(FixedWidthFields fieldLengths) {
      this.setFieldLengths(fieldLengths);
      NormalizedString[] names = fieldLengths.getFieldNames();
      if (names != null) {
         this.setHeaders(NormalizedString.toArray(names));
      }

   }

   public FixedWidthWriterSettings() {
      this.fieldLengths = null;
   }

   final void setFieldLengths(FixedWidthFields fieldLengths) {
      if (fieldLengths == null) {
         throw new IllegalArgumentException("Field lengths cannot be null");
      } else {
         this.fieldLengths = fieldLengths;
      }
   }

   int[] getFieldLengths() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldLengths();
   }

   int[] getAllLengths() {
      return this.fieldLengths == null ? null : this.fieldLengths.getAllLengths();
   }

   FieldAlignment[] getFieldAlignments() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldAlignments();
   }

   char[] getFieldPaddings() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldPaddings((FixedWidthFormat)this.getFormat());
   }

   boolean[] getFieldsToIgnore() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldsToIgnore();
   }

   protected FixedWidthFormat createDefaultFormat() {
      return new FixedWidthFormat();
   }

   public int getMaxColumns() {
      int max = super.getMaxColumns();
      int minimum = Lookup.calculateMaxFieldLengths(this.fieldLengths, this.lookaheadFormats, this.lookbehindFormats).length;
      return max > minimum ? max : minimum;
   }

   public void addFormatForLookahead(String lookahead, FixedWidthFields lengths) {
      Lookup.registerLookahead(lookahead, lengths, this.lookaheadFormats);
   }

   public void addFormatForLookbehind(String lookbehind, FixedWidthFields lengths) {
      Lookup.registerLookbehind(lookbehind, lengths, this.lookbehindFormats);
   }

   Lookup[] getLookaheadFormats() {
      return Lookup.getLookupFormats(this.lookaheadFormats, (FixedWidthFormat)this.getFormat());
   }

   Lookup[] getLookbehindFormats() {
      return Lookup.getLookupFormats(this.lookbehindFormats, (FixedWidthFormat)this.getFormat());
   }

   public boolean getUseDefaultPaddingForHeaders() {
      return this.useDefaultPaddingForHeaders;
   }

   public void setUseDefaultPaddingForHeaders(boolean useDefaultPaddingForHeaders) {
      this.useDefaultPaddingForHeaders = useDefaultPaddingForHeaders;
   }

   public FieldAlignment getDefaultAlignmentForHeaders() {
      return this.defaultAlignmentForHeaders;
   }

   public void setDefaultAlignmentForHeaders(FieldAlignment defaultAlignmentForHeaders) {
      this.defaultAlignmentForHeaders = defaultAlignmentForHeaders;
   }

   public boolean getWriteLineSeparatorAfterRecord() {
      return this.writeLineSeparatorAfterRecord;
   }

   public void setWriteLineSeparatorAfterRecord(boolean writeLineSeparatorAfterRecord) {
      this.writeLineSeparatorAfterRecord = writeLineSeparatorAfterRecord;
   }

   protected void configureFromAnnotations(Class beanClass) {
      if (this.fieldLengths == null) {
         try {
            this.fieldLengths = FixedWidthFields.forWriting(beanClass);
            Headers headerAnnotation = AnnotationHelper.findHeadersAnnotation(beanClass);
            this.setHeaderWritingEnabled(headerAnnotation != null && headerAnnotation.write());
         } catch (Exception var3) {
         }

         super.configureFromAnnotations(beanClass);
         FixedWidthFields.setHeadersIfPossible(this.fieldLengths, this);
      }
   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
      out.put("Write line separator after record", this.writeLineSeparatorAfterRecord);
      out.put("Field lengths", this.fieldLengths);
      out.put("Lookahead formats", this.lookaheadFormats);
      out.put("Lookbehind formats", this.lookbehindFormats);
      out.put("Use default padding for headers", this.useDefaultPaddingForHeaders);
      out.put("Default alignment for headers", this.defaultAlignmentForHeaders);
   }

   public final FixedWidthWriterSettings clone() {
      return (FixedWidthWriterSettings)super.clone(false);
   }

   /** @deprecated */
   @Deprecated
   protected final FixedWidthWriterSettings clone(boolean clearInputSpecificSettings) {
      return this.clone(clearInputSpecificSettings, this.fieldLengths == null ? null : this.fieldLengths.clone());
   }

   public final FixedWidthWriterSettings clone(FixedWidthFields fields) {
      return this.clone(true, fields);
   }

   private FixedWidthWriterSettings clone(boolean clearInputSpecificSettings, FixedWidthFields fields) {
      FixedWidthWriterSettings out = (FixedWidthWriterSettings)super.clone(clearInputSpecificSettings);
      out.fieldLengths = fields;
      if (clearInputSpecificSettings) {
         out.lookaheadFormats = new HashMap();
         out.lookbehindFormats = new HashMap();
      } else {
         out.lookaheadFormats = new HashMap(this.lookaheadFormats);
         out.lookbehindFormats = new HashMap(this.lookbehindFormats);
      }

      return out;
   }
}
