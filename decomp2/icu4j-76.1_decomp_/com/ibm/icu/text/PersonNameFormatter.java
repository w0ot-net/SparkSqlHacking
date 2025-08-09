package com.ibm.icu.text;

import com.ibm.icu.impl.personname.PersonNameFormatterImpl;
import java.util.Locale;

public class PersonNameFormatter {
   private final PersonNameFormatterImpl impl;

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      Builder builder = builder();
      builder.setLocale(this.impl.getLocale());
      builder.setLength(this.impl.getLength());
      builder.setUsage(this.impl.getUsage());
      builder.setFormality(this.impl.getFormality());
      builder.setDisplayOrder(this.impl.getDisplayOrder());
      builder.setSurnameAllCaps(this.impl.getSurnameAllCaps());
      return builder;
   }

   public String formatToString(PersonName name) {
      return this.impl.formatToString(name);
   }

   private PersonNameFormatter(Locale locale, Length length, Usage usage, Formality formality, DisplayOrder displayOrder, boolean surnameAllCaps) {
      this.impl = new PersonNameFormatterImpl(locale, length, usage, formality, displayOrder, surnameAllCaps);
   }

   /** @deprecated */
   @Deprecated
   public PersonNameFormatter(Locale locale, String[] gnFirstPatterns, String[] snFirstPatterns, String[] gnFirstLocales, String[] snFirstLocales) {
      this.impl = new PersonNameFormatterImpl(locale, gnFirstPatterns, snFirstPatterns, gnFirstLocales, snFirstLocales);
   }

   /** @deprecated */
   @Deprecated
   public String toString() {
      return this.impl.toString();
   }

   public static enum Length {
      LONG,
      MEDIUM,
      SHORT,
      DEFAULT;
   }

   public static enum Usage {
      ADDRESSING,
      REFERRING,
      MONOGRAM;
   }

   public static enum Formality {
      FORMAL,
      INFORMAL,
      DEFAULT;
   }

   public static enum DisplayOrder {
      DEFAULT,
      SORTING,
      FORCE_GIVEN_FIRST,
      FORCE_SURNAME_FIRST;
   }

   public static class Builder {
      private Locale locale;
      private Length length;
      private Usage usage;
      private Formality formality;
      private DisplayOrder displayOrder;
      private boolean surnameAllCaps;

      public Builder setLocale(Locale locale) {
         if (locale != null) {
            this.locale = locale;
         }

         return this;
      }

      public Builder setLength(Length length) {
         this.length = length;
         return this;
      }

      public Builder setUsage(Usage usage) {
         this.usage = usage;
         return this;
      }

      public Builder setFormality(Formality formality) {
         this.formality = formality;
         return this;
      }

      public Builder setDisplayOrder(DisplayOrder order) {
         this.displayOrder = order;
         return this;
      }

      public Builder setSurnameAllCaps(boolean allCaps) {
         this.surnameAllCaps = allCaps;
         return this;
      }

      public PersonNameFormatter build() {
         return new PersonNameFormatter(this.locale, this.length, this.usage, this.formality, this.displayOrder, this.surnameAllCaps);
      }

      private Builder() {
         this.locale = Locale.getDefault();
         this.length = PersonNameFormatter.Length.DEFAULT;
         this.usage = PersonNameFormatter.Usage.REFERRING;
         this.formality = PersonNameFormatter.Formality.DEFAULT;
         this.displayOrder = PersonNameFormatter.DisplayOrder.DEFAULT;
         this.surnameAllCaps = false;
      }
   }
}
