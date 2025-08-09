package com.ibm.icu.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalePriorityList implements Iterable {
   private static final Double D1 = (double)1.0F;
   private static final Pattern languageSplitter = Pattern.compile("\\s*,\\s*");
   private static final Pattern weightSplitter = Pattern.compile("\\s*(\\S*)\\s*;\\s*q\\s*=\\s*(\\S*)");
   private final Map languagesAndWeights;
   private static Comparator myDescendingDouble = new Comparator() {
      public int compare(Double o1, Double o2) {
         int result = o1.compareTo(o2);
         return result > 0 ? -1 : (result < 0 ? 1 : 0);
      }
   };

   public static Builder add(ULocale... locales) {
      return (new Builder()).add(locales);
   }

   public static Builder add(ULocale locale, double weight) {
      return (new Builder()).add(locale, weight);
   }

   public static Builder add(LocalePriorityList list) {
      return new Builder(list);
   }

   public static Builder add(String acceptLanguageString) {
      return (new Builder()).add(acceptLanguageString);
   }

   public Double getWeight(ULocale locale) {
      return (Double)this.languagesAndWeights.get(locale);
   }

   public Set getULocales() {
      return this.languagesAndWeights.keySet();
   }

   public String toString() {
      StringBuilder result = new StringBuilder();

      for(Map.Entry entry : this.languagesAndWeights.entrySet()) {
         ULocale language = (ULocale)entry.getKey();
         double weight = (Double)entry.getValue();
         if (result.length() != 0) {
            result.append(", ");
         }

         result.append(language);
         if (weight != (double)1.0F) {
            result.append(";q=").append(weight);
         }
      }

      return result.toString();
   }

   public Iterator iterator() {
      return this.languagesAndWeights.keySet().iterator();
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (this == o) {
         return true;
      } else {
         try {
            LocalePriorityList that = (LocalePriorityList)o;
            return this.languagesAndWeights.equals(that.languagesAndWeights);
         } catch (RuntimeException var3) {
            return false;
         }
      }
   }

   public int hashCode() {
      return this.languagesAndWeights.hashCode();
   }

   private LocalePriorityList(Map languageToWeight) {
      this.languagesAndWeights = languageToWeight;
   }

   public static class Builder {
      private Map languageToWeight;
      private LocalePriorityList built;
      private boolean hasWeights;

      private Builder() {
         this.hasWeights = false;
         this.languageToWeight = new LinkedHashMap();
      }

      private Builder(LocalePriorityList list) {
         this.hasWeights = false;
         this.built = list;
         Iterator var2 = list.languagesAndWeights.values().iterator();

         while(true) {
            if (var2.hasNext()) {
               Double value = (Double)var2.next();
               double weight = value;

               assert (double)0.0F < weight && weight <= (double)1.0F;

               if (weight == (double)1.0F) {
                  continue;
               }

               this.hasWeights = true;
            }

            return;
         }
      }

      public LocalePriorityList build() {
         return this.build(false);
      }

      public LocalePriorityList build(boolean preserveWeights) {
         if (this.built != null) {
            return this.built;
         } else {
            Map<ULocale, Double> temp;
            if (this.hasWeights) {
               TreeMap<Double, List<ULocale>> weightToLanguages = new TreeMap(LocalePriorityList.myDescendingDouble);

               for(Map.Entry entry : this.languageToWeight.entrySet()) {
                  ULocale lang = (ULocale)entry.getKey();
                  Double weight = (Double)entry.getValue();
                  List<ULocale> s = (List)weightToLanguages.get(weight);
                  if (s == null) {
                     weightToLanguages.put(weight, s = new LinkedList());
                  }

                  s.add(lang);
               }

               if (weightToLanguages.size() <= 1) {
                  temp = this.languageToWeight;
                  if (weightToLanguages.isEmpty() || (Double)weightToLanguages.firstKey() == (double)1.0F) {
                     this.hasWeights = false;
                  }
               } else {
                  temp = new LinkedHashMap();

                  for(Map.Entry langEntry : weightToLanguages.entrySet()) {
                     Double weight = preserveWeights ? (Double)langEntry.getKey() : LocalePriorityList.D1;

                     for(ULocale lang : (List)langEntry.getValue()) {
                        temp.put(lang, weight);
                     }
                  }
               }
            } else {
               temp = this.languageToWeight;
            }

            this.languageToWeight = null;
            return this.built = new LocalePriorityList(Collections.unmodifiableMap(temp));
         }
      }

      public Builder add(LocalePriorityList list) {
         for(Map.Entry entry : list.languagesAndWeights.entrySet()) {
            this.add((ULocale)entry.getKey(), (Double)entry.getValue());
         }

         return this;
      }

      public Builder add(ULocale locale) {
         return this.add(locale, (double)1.0F);
      }

      public Builder add(ULocale... locales) {
         for(ULocale languageCode : locales) {
            this.add(languageCode, (double)1.0F);
         }

         return this;
      }

      public Builder add(ULocale locale, double weight) {
         if (this.languageToWeight == null) {
            this.languageToWeight = new LinkedHashMap(this.built.languagesAndWeights);
            this.built = null;
         }

         if (this.languageToWeight.containsKey(locale)) {
            this.languageToWeight.remove(locale);
         }

         if (weight <= (double)0.0F) {
            return this;
         } else {
            Double value;
            if (weight >= (double)1.0F) {
               value = LocalePriorityList.D1;
            } else {
               value = weight;
               this.hasWeights = true;
            }

            this.languageToWeight.put(locale, value);
            return this;
         }
      }

      public Builder add(String acceptLanguageList) {
         String[] items = LocalePriorityList.languageSplitter.split(acceptLanguageList.trim());
         Matcher itemMatcher = LocalePriorityList.weightSplitter.matcher("");

         for(String item : items) {
            if (itemMatcher.reset(item).matches()) {
               ULocale language = new ULocale(itemMatcher.group(1));
               double weight = Double.parseDouble(itemMatcher.group(2));
               if (!((double)0.0F <= weight) || !(weight <= (double)1.0F)) {
                  throw new IllegalArgumentException("Illegal weight, must be 0..1: " + weight);
               }

               this.add(language, weight);
            } else if (item.length() != 0) {
               this.add(new ULocale(item));
            }
         }

         return this;
      }
   }
}
