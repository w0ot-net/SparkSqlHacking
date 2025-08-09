package com.ibm.icu.text;

import java.util.Locale;
import java.util.Set;

public interface PersonName {
   Locale getNameLocale();

   PreferredOrder getPreferredOrder();

   String getFieldValue(NameField var1, Set var2);

   public static enum NameField {
      TITLE("title"),
      GIVEN("given"),
      GIVEN2("given2"),
      SURNAME("surname"),
      SURNAME2("surname2"),
      GENERATION("generation"),
      CREDENTIALS("credentials");

      private final String name;

      private NameField(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      /** @deprecated */
      @Deprecated
      public static NameField forString(String name) {
         for(NameField field : values()) {
            if (field.name.equals(name)) {
               return field;
            }
         }

         throw new IllegalArgumentException("Invalid field name " + name);
      }
   }

   public static enum FieldModifier {
      INFORMAL("informal"),
      PREFIX("prefix"),
      CORE("core"),
      INITIAL("initial"),
      MONOGRAM("monogram"),
      ALL_CAPS("allCaps"),
      /** @deprecated */
      @Deprecated
      RETAIN("retain"),
      INITIAL_CAP("initialCap"),
      GENITIVE("genitive"),
      VOCATIVE("vocative");

      private final String name;

      private FieldModifier(String name) {
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      public static FieldModifier forString(String name) {
         for(FieldModifier modifier : values()) {
            if (modifier.name.equals(name)) {
               return modifier;
            }
         }

         throw new IllegalArgumentException("Invalid modifier name " + name);
      }
   }

   public static enum PreferredOrder {
      DEFAULT,
      GIVEN_FIRST,
      SURNAME_FIRST;
   }
}
