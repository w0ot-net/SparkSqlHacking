package com.univocity.parsers.fixed;

import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.CommonParserSettings;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.input.CharAppender;
import com.univocity.parsers.common.input.DefaultCharAppender;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FixedWidthParserSettings extends CommonParserSettings {
   protected boolean skipTrailingCharsUntilNewline = false;
   protected boolean recordEndsOnNewline = false;
   private boolean useDefaultPaddingForHeaders = true;
   private boolean keepPadding = false;
   private FixedWidthFields fieldLengths;
   private Map lookaheadFormats = new HashMap();
   private Map lookbehindFormats = new HashMap();

   public FixedWidthParserSettings(FixedWidthFields fieldLengths) {
      if (fieldLengths == null) {
         throw new IllegalArgumentException("Field lengths cannot be null");
      } else {
         this.fieldLengths = fieldLengths;
         NormalizedString[] names = fieldLengths.getFieldNames();
         if (names != null) {
            this.setHeaders(NormalizedString.toArray(names));
         }

      }
   }

   public FixedWidthParserSettings() {
      this.fieldLengths = null;
   }

   int[] getFieldLengths() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldLengths();
   }

   int[] getAllLengths() {
      return this.fieldLengths == null ? null : this.fieldLengths.getAllLengths();
   }

   char[] getFieldPaddings() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldPaddings((FixedWidthFormat)this.getFormat());
   }

   boolean[] getFieldsToIgnore() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldsToIgnore();
   }

   FieldAlignment[] getFieldAlignments() {
      return this.fieldLengths == null ? null : this.fieldLengths.getFieldAlignments();
   }

   public boolean getSkipTrailingCharsUntilNewline() {
      return this.skipTrailingCharsUntilNewline;
   }

   public void setSkipTrailingCharsUntilNewline(boolean skipTrailingCharsUntilNewline) {
      this.skipTrailingCharsUntilNewline = skipTrailingCharsUntilNewline;
   }

   public boolean getRecordEndsOnNewline() {
      return this.recordEndsOnNewline;
   }

   public void setRecordEndsOnNewline(boolean recordEndsOnNewline) {
      this.recordEndsOnNewline = recordEndsOnNewline;
   }

   protected FixedWidthFormat createDefaultFormat() {
      return new FixedWidthFormat();
   }

   protected CharAppender newCharAppender() {
      return new DefaultCharAppender(this.getMaxCharsPerColumn(), this.getNullValue(), this.getWhitespaceRangeStart());
   }

   public int getMaxCharsPerColumn() {
      int max = super.getMaxCharsPerColumn();
      int minimum = 0;

      for(int length : this.calculateMaxFieldLengths()) {
         minimum += length + 2;
      }

      return max > minimum ? max : minimum;
   }

   public int getMaxColumns() {
      int max = super.getMaxColumns();
      int minimum = this.calculateMaxFieldLengths().length;
      return max > minimum ? max : minimum;
   }

   private int[] calculateMaxFieldLengths() {
      return Lookup.calculateMaxFieldLengths(this.fieldLengths, this.lookaheadFormats, this.lookbehindFormats);
   }

   Lookup[] getLookaheadFormats() {
      return Lookup.getLookupFormats(this.lookaheadFormats, (FixedWidthFormat)this.getFormat());
   }

   Lookup[] getLookbehindFormats() {
      return Lookup.getLookupFormats(this.lookbehindFormats, (FixedWidthFormat)this.getFormat());
   }

   public void addFormatForLookahead(String lookahead, FixedWidthFields lengths) {
      Lookup.registerLookahead(lookahead, lengths, this.lookaheadFormats);
   }

   public void addFormatForLookbehind(String lookbehind, FixedWidthFields lengths) {
      Lookup.registerLookbehind(lookbehind, lengths, this.lookbehindFormats);
   }

   public boolean getUseDefaultPaddingForHeaders() {
      return this.useDefaultPaddingForHeaders;
   }

   public void setUseDefaultPaddingForHeaders(boolean useDefaultPaddingForHeaders) {
      this.useDefaultPaddingForHeaders = useDefaultPaddingForHeaders;
   }

   protected void configureFromAnnotations(Class beanClass) {
      if (this.fieldLengths == null) {
         try {
            this.fieldLengths = FixedWidthFields.forParsing(beanClass);
            Headers headerAnnotation = AnnotationHelper.findHeadersAnnotation(beanClass);
            if (this.headerExtractionEnabled == null && headerAnnotation != null) {
               this.setHeaderExtractionEnabled(headerAnnotation.extract());
            }
         } catch (IllegalArgumentException e) {
            throw e;
         } catch (Exception var4) {
         }
      }

      if (this.headerExtractionEnabled == null) {
         this.setHeaderExtractionEnabled(false);
      }

      super.configureFromAnnotations(beanClass);
      if (!this.isHeaderExtractionEnabled()) {
         FixedWidthFields.setHeadersIfPossible(this.fieldLengths, this);
      }

   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
      out.put("Skip trailing characters until new line", this.skipTrailingCharsUntilNewline);
      out.put("Record ends on new line", this.recordEndsOnNewline);
      out.put("Field lengths", this.fieldLengths == null ? "<null>" : this.fieldLengths.toString());
      out.put("Lookahead formats", this.lookaheadFormats);
      out.put("Lookbehind formats", this.lookbehindFormats);
   }

   public final FixedWidthParserSettings clone() {
      return (FixedWidthParserSettings)super.clone();
   }

   /** @deprecated */
   @Deprecated
   protected final FixedWidthParserSettings clone(boolean clearInputSpecificSettings) {
      return this.clone(clearInputSpecificSettings, this.fieldLengths == null ? null : this.fieldLengths.clone());
   }

   public final FixedWidthParserSettings clone(FixedWidthFields fields) {
      return this.clone(true, fields);
   }

   private FixedWidthParserSettings clone(boolean clearInputSpecificSettings, FixedWidthFields fields) {
      FixedWidthParserSettings out = (FixedWidthParserSettings)super.clone(clearInputSpecificSettings);
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

   public final boolean getKeepPadding() {
      return this.keepPadding;
   }

   public final void setKeepPadding(boolean keepPadding) {
      this.keepPadding = keepPadding;
   }

   Boolean[] getKeepPaddingFlags() {
      if (this.fieldLengths == null) {
         return null;
      } else {
         Boolean[] keepFlags = this.fieldLengths.getKeepPaddingFlags();
         Boolean[] out = new Boolean[keepFlags.length];
         Arrays.fill(out, this.getKeepPadding());

         for(int i = 0; i < keepFlags.length; ++i) {
            Boolean flag = keepFlags[i];
            if (flag != null) {
               out[i] = flag;
            }
         }

         return out;
      }
   }
}
