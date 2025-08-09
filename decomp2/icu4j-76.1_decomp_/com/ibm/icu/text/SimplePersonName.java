package com.ibm.icu.text;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SimplePersonName implements PersonName {
   private final Locale nameLocale;
   private final PersonName.PreferredOrder preferredOrder;
   private final Map fieldValues;

   public static Builder builder() {
      return new Builder();
   }

   private SimplePersonName(Locale nameLocale, PersonName.PreferredOrder preferredOrder, Map fieldValues) {
      this.nameLocale = nameLocale;
      this.preferredOrder = preferredOrder;
      this.fieldValues = new HashMap(fieldValues);
   }

   public Locale getNameLocale() {
      return this.nameLocale;
   }

   public PersonName.PreferredOrder getPreferredOrder() {
      return this.preferredOrder;
   }

   public String getFieldValue(PersonName.NameField nameField, Set modifiers) {
      String fieldName = nameField.toString();
      String result = (String)this.fieldValues.get(makeModifiedFieldName(nameField, modifiers));
      if (result != null) {
         modifiers.clear();
         return result;
      } else {
         result = (String)this.fieldValues.get(fieldName);
         if (result == null) {
            return null;
         } else if (modifiers.size() == 1) {
            return result;
         } else {
            String winningKey = fieldName;
            int winningScore = 0;

            for(String key : this.fieldValues.keySet()) {
               if (key.startsWith(fieldName)) {
                  Set<PersonName.FieldModifier> keyModifiers = makeModifiersFromName(key);
                  if (modifiers.containsAll(keyModifiers) && (keyModifiers.size() > winningScore || keyModifiers.size() == winningScore && key.compareTo(winningKey) < 0)) {
                     winningKey = key;
                     winningScore = keyModifiers.size();
                  }
               }
            }

            result = (String)this.fieldValues.get(winningKey);
            modifiers.removeAll(makeModifiersFromName(winningKey));
            return result;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(String key : this.fieldValues.keySet()) {
         if (sb.length() > 0) {
            sb.append(",");
         }

         sb.append(key + "=" + (String)this.fieldValues.get(key));
      }

      sb.append(",locale=" + this.nameLocale);
      return sb.toString();
   }

   private static String makeModifiedFieldName(PersonName.NameField fieldName, Collection modifiers) {
      StringBuilder result = new StringBuilder();
      result.append(fieldName);
      TreeSet<String> sortedModifierNames = new TreeSet();

      for(PersonName.FieldModifier modifier : modifiers) {
         sortedModifierNames.add(modifier.toString());
      }

      for(String modifierName : sortedModifierNames) {
         result.append("-");
         result.append(modifierName);
      }

      return result.toString();
   }

   private static Set makeModifiersFromName(String modifiedName) {
      StringTokenizer tok = new StringTokenizer(modifiedName, "-");
      Set<PersonName.FieldModifier> result = new HashSet();
      String fieldName = tok.nextToken();

      while(tok.hasMoreTokens()) {
         result.add(PersonName.FieldModifier.forString(tok.nextToken()));
      }

      return result;
   }

   public static class Builder {
      private Locale locale;
      private PersonName.PreferredOrder preferredOrder;
      private Map fieldValues;

      public Builder setLocale(Locale locale) {
         this.locale = locale;
         return this;
      }

      public Builder setPreferredOrder(PersonName.PreferredOrder preferredOrder) {
         this.preferredOrder = preferredOrder;
         return this;
      }

      public Builder addField(PersonName.NameField field, Collection modifiers, String value) {
         Set<String> modifierNames = new TreeSet();
         if (modifiers != null) {
            for(PersonName.FieldModifier modifier : modifiers) {
               modifierNames.add(modifier.toString());
            }
         }

         StringBuilder fieldName = new StringBuilder();
         fieldName.append(field.toString());

         for(String modifierName : modifierNames) {
            fieldName.append("-");
            fieldName.append(modifierName);
         }

         this.fieldValues.put(fieldName.toString(), value);
         return this;
      }

      public SimplePersonName build() {
         if (this.fieldValues.get("surname") == null) {
            String surnamePrefix = (String)this.fieldValues.get("surname-prefix");
            String surnameCore = (String)this.fieldValues.get("surname-core");
            if (surnamePrefix != null && surnameCore != null) {
               this.fieldValues.put("surname", surnamePrefix + " " + surnameCore);
            } else if (surnamePrefix != null) {
               this.fieldValues.put("surname", surnamePrefix);
            } else if (surnameCore != null) {
               this.fieldValues.put("surname", surnameCore);
            }
         }

         return new SimplePersonName(this.locale, this.preferredOrder, this.fieldValues);
      }

      private Builder() {
         this.locale = null;
         this.preferredOrder = PersonName.PreferredOrder.DEFAULT;
         this.fieldValues = new HashMap();
      }
   }
}
