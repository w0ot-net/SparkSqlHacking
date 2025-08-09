package com.ibm.icu.message2;

import java.util.Locale;
import java.util.Map;
import java.util.Locale.Category;

/** @deprecated */
@Deprecated
public class MessageFormatter {
   private final Locale locale;
   private final String pattern;
   private final ErrorHandlingBehavior errorHandlingBehavior;
   private final MFFunctionRegistry functionRegistry;
   private final MFDataModel.Message dataModel;
   private final MFDataModelFormatter modelFormatter;

   private MessageFormatter(Builder builder) {
      this.locale = builder.locale;
      this.functionRegistry = builder.functionRegistry;
      this.errorHandlingBehavior = builder.errorHandlingBehavior;
      if ((builder.pattern != null || builder.dataModel != null) && (builder.pattern == null || builder.dataModel == null)) {
         if (builder.dataModel != null) {
            this.dataModel = builder.dataModel;
            this.pattern = MFSerializer.dataModelToString(this.dataModel);
         } else {
            this.pattern = builder.pattern;

            try {
               this.dataModel = MFParser.parse(this.pattern);
            } catch (MFParseException pe) {
               throw new IllegalArgumentException("Parse error:\nMessage: <<" + this.pattern + ">>\nError: " + pe.getMessage() + "\n");
            }
         }

         this.modelFormatter = new MFDataModelFormatter(this.dataModel, this.locale, this.errorHandlingBehavior, this.functionRegistry);
      } else {
         throw new IllegalArgumentException("You need to set either a pattern, or a dataModel, but not both.");
      }
   }

   /** @deprecated */
   @Deprecated
   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public Locale getLocale() {
      return this.locale;
   }

   /** @deprecated */
   @Deprecated
   public ErrorHandlingBehavior getErrorHandlingBehavior() {
      return this.errorHandlingBehavior;
   }

   /** @deprecated */
   @Deprecated
   public String getPattern() {
      return this.pattern;
   }

   /** @deprecated */
   @Deprecated
   public MFDataModel.Message getDataModel() {
      return this.dataModel;
   }

   /** @deprecated */
   @Deprecated
   public String formatToString(Map arguments) {
      return this.modelFormatter.format(arguments);
   }

   /** @deprecated */
   @Deprecated
   public FormattedMessage format(Map arguments) {
      throw new RuntimeException("Not yet implemented.");
   }

   /** @deprecated */
   @Deprecated
   public static enum ErrorHandlingBehavior {
      /** @deprecated */
      @Deprecated
      BEST_EFFORT,
      /** @deprecated */
      @Deprecated
      STRICT;
   }

   /** @deprecated */
   @Deprecated
   public static class Builder {
      private Locale locale;
      private String pattern;
      private ErrorHandlingBehavior errorHandlingBehavior;
      private MFFunctionRegistry functionRegistry;
      private MFDataModel.Message dataModel;

      private Builder() {
         this.locale = Locale.getDefault(Category.FORMAT);
         this.pattern = null;
         this.errorHandlingBehavior = MessageFormatter.ErrorHandlingBehavior.BEST_EFFORT;
         this.functionRegistry = MFFunctionRegistry.builder().build();
         this.dataModel = null;
      }

      /** @deprecated */
      @Deprecated
      public Builder setLocale(Locale locale) {
         this.locale = locale;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setPattern(String pattern) {
         this.pattern = pattern;
         this.dataModel = null;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setErrorHandlingBehavior(ErrorHandlingBehavior errorHandlingBehavior) {
         this.errorHandlingBehavior = errorHandlingBehavior;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setFunctionRegistry(MFFunctionRegistry functionRegistry) {
         this.functionRegistry = functionRegistry;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setDataModel(MFDataModel.Message dataModel) {
         this.dataModel = dataModel;
         this.pattern = null;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public MessageFormatter build() {
         return new MessageFormatter(this);
      }
   }
}
