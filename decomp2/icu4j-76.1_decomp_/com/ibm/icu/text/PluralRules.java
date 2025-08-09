package com.ibm.icu.text;

import com.ibm.icu.impl.PluralRulesLoader;
import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import com.ibm.icu.impl.number.range.StandardPluralRanges;
import com.ibm.icu.number.FormattedNumber;
import com.ibm.icu.number.FormattedNumberRange;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public class PluralRules implements Serializable {
   static final UnicodeSet ALLOWED_ID = (new UnicodeSet("[a-z]")).freeze();
   private static final String CATEGORY_SEPARATOR = ";  ";
   private static final long serialVersionUID = 1L;
   private final RuleList rules;
   private final transient Set keywords;
   private final transient StandardPluralRanges standardPluralRanges;
   public static final String KEYWORD_ZERO = "zero";
   public static final String KEYWORD_ONE = "one";
   public static final String KEYWORD_TWO = "two";
   public static final String KEYWORD_FEW = "few";
   public static final String KEYWORD_MANY = "many";
   public static final String KEYWORD_OTHER = "other";
   public static final double NO_UNIQUE_VALUE = -0.00123456777;
   /** @deprecated */
   @Deprecated
   public static final DecimalQuantity NO_UNIQUE_VALUE_DECIMAL_QUANTITY = new DecimalQuantity_DualStorageBCD(-0.00123456777);
   private static final Constraint NO_CONSTRAINT = new Constraint() {
      private static final long serialVersionUID = 9163464945387899416L;

      public boolean isFulfilled(IFixedDecimal n) {
         return true;
      }

      public boolean isLimited(SampleType sampleType) {
         return false;
      }

      public String toString() {
         return "";
      }
   };
   private static final Rule DEFAULT_RULE;
   public static final PluralRules DEFAULT;
   static final Pattern AT_SEPARATED;
   static final Pattern OR_SEPARATED;
   static final Pattern AND_SEPARATED;
   static final Pattern COMMA_SEPARATED;
   static final Pattern DOTDOT_SEPARATED;
   static final Pattern TILDE_SEPARATED;
   static final Pattern SEMI_SEPARATED;

   public static PluralRules parseDescription(String description) throws ParseException {
      return newInternal(description, (StandardPluralRanges)null);
   }

   public static PluralRules createRules(String description) {
      try {
         return parseDescription(description);
      } catch (Exception var2) {
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static PluralRules newInternal(String description, StandardPluralRanges ranges) throws ParseException {
      description = description.trim();
      return description.length() == 0 ? DEFAULT : new PluralRules(parseRuleChain(description), ranges);
   }

   private static Constraint parseConstraint(String description) throws ParseException {
      Constraint result = null;
      String[] or_together = OR_SEPARATED.split(description, 0);

      for(int i = 0; i < or_together.length; ++i) {
         Constraint andConstraint = null;
         String[] and_together = AND_SEPARATED.split(or_together[i], 0);

         for(int j = 0; j < and_together.length; ++j) {
            Constraint newConstraint = NO_CONSTRAINT;
            String condition = and_together[j].trim();
            String[] tokens = PluralRules.SimpleTokenizer.split(condition);
            int mod = 0;
            boolean inRange = true;
            boolean integersOnly = true;
            double lowBound = (double)Long.MAX_VALUE;
            double highBound = (double)Long.MIN_VALUE;
            long[] vals = null;
            int x = 0;
            String t = tokens[x++];
            boolean hackForCompatibility = false;

            Operand operand;
            try {
               operand = PluralRules.FixedDecimal.getOperand(t);
            } catch (Exception var27) {
               throw unexpected(t, condition);
            }

            if (x < tokens.length) {
               t = tokens[x++];
               if ("mod".equals(t) || "%".equals(t)) {
                  mod = Integer.parseInt(tokens[x++]);
                  t = nextToken(tokens, x++, condition);
               }

               if ("not".equals(t)) {
                  inRange = !inRange;
                  t = nextToken(tokens, x++, condition);
                  if ("=".equals(t)) {
                     throw unexpected(t, condition);
                  }
               } else if ("!".equals(t)) {
                  inRange = !inRange;
                  t = nextToken(tokens, x++, condition);
                  if (!"=".equals(t)) {
                     throw unexpected(t, condition);
                  }
               }

               if (!"is".equals(t) && !"in".equals(t) && !"=".equals(t)) {
                  if (!"within".equals(t)) {
                     throw unexpected(t, condition);
                  }

                  integersOnly = false;
                  t = nextToken(tokens, x++, condition);
               } else {
                  hackForCompatibility = "is".equals(t);
                  if (hackForCompatibility && !inRange) {
                     throw unexpected(t, condition);
                  }

                  t = nextToken(tokens, x++, condition);
               }

               if ("not".equals(t)) {
                  if (!hackForCompatibility && !inRange) {
                     throw unexpected(t, condition);
                  }

                  inRange = !inRange;
                  t = nextToken(tokens, x++, condition);
               }

               List<Long> valueList = new ArrayList();

               while(true) {
                  long low = Long.parseLong(t);
                  long high = low;
                  if (x < tokens.length) {
                     t = nextToken(tokens, x++, condition);
                     if (t.equals(".")) {
                        t = nextToken(tokens, x++, condition);
                        if (!t.equals(".")) {
                           throw unexpected(t, condition);
                        }

                        t = nextToken(tokens, x++, condition);
                        high = Long.parseLong(t);
                        if (x < tokens.length) {
                           t = nextToken(tokens, x++, condition);
                           if (!t.equals(",")) {
                              throw unexpected(t, condition);
                           }
                        }
                     } else if (!t.equals(",")) {
                        throw unexpected(t, condition);
                     }
                  }

                  if (low > high) {
                     throw unexpected(low + "~" + high, condition);
                  }

                  if (mod != 0 && high >= (long)mod) {
                     throw unexpected(high + ">mod=" + mod, condition);
                  }

                  valueList.add(low);
                  valueList.add(high);
                  lowBound = Math.min(lowBound, (double)low);
                  highBound = Math.max(highBound, (double)high);
                  if (x >= tokens.length) {
                     if (t.equals(",")) {
                        throw unexpected(t, condition);
                     }

                     if (valueList.size() == 2) {
                        vals = null;
                     } else {
                        vals = new long[valueList.size()];

                        for(int k = 0; k < vals.length; ++k) {
                           vals[k] = (Long)valueList.get(k);
                        }
                     }

                     if (lowBound != highBound && hackForCompatibility && !inRange) {
                        throw unexpected("is not <range>", condition);
                     }

                     newConstraint = new RangeConstraint(mod, inRange, operand, integersOnly, lowBound, highBound, vals);
                     break;
                  }

                  t = nextToken(tokens, x++, condition);
               }
            }

            if (andConstraint == null) {
               andConstraint = newConstraint;
            } else {
               andConstraint = new AndConstraint(andConstraint, newConstraint);
            }
         }

         if (result == null) {
            result = andConstraint;
         } else {
            result = new OrConstraint(result, andConstraint);
         }
      }

      return result;
   }

   private static ParseException unexpected(String token, String context) {
      return new ParseException("unexpected token '" + token + "' in '" + context + "'", -1);
   }

   private static String nextToken(String[] tokens, int x, String context) throws ParseException {
      if (x < tokens.length) {
         return tokens[x];
      } else {
         throw new ParseException("missing token at end of '" + context + "'", -1);
      }
   }

   private static Rule parseRule(String description) throws ParseException {
      if (description.length() == 0) {
         return DEFAULT_RULE;
      } else {
         description = description.toLowerCase(Locale.ENGLISH);
         int x = description.indexOf(58);
         if (x == -1) {
            throw new ParseException("missing ':' in rule description '" + description + "'", 0);
         } else {
            String keyword = description.substring(0, x).trim();
            if (!isValidKeyword(keyword)) {
               throw new ParseException("keyword '" + keyword + " is not valid", 0);
            } else {
               description = description.substring(x + 1).trim();
               String[] constraintOrSamples = AT_SEPARATED.split(description, 0);
               boolean sampleFailure = false;
               DecimalQuantitySamples integerSamples = null;
               DecimalQuantitySamples decimalSamples = null;
               switch (constraintOrSamples.length) {
                  case 1:
                     break;
                  case 2:
                     integerSamples = PluralRules.DecimalQuantitySamples.parse(constraintOrSamples[1]);
                     if (integerSamples.sampleType == PluralRules.SampleType.DECIMAL) {
                        decimalSamples = integerSamples;
                        integerSamples = null;
                     }
                     break;
                  case 3:
                     integerSamples = PluralRules.DecimalQuantitySamples.parse(constraintOrSamples[1]);
                     decimalSamples = PluralRules.DecimalQuantitySamples.parse(constraintOrSamples[2]);
                     if (integerSamples.sampleType != PluralRules.SampleType.INTEGER || decimalSamples.sampleType != PluralRules.SampleType.DECIMAL) {
                        throw new IllegalArgumentException("Must have @integer then @decimal in " + description);
                     }
                     break;
                  default:
                     throw new IllegalArgumentException("Too many samples in " + description);
               }

               if (sampleFailure) {
                  throw new IllegalArgumentException("Ill-formed samples—'@' characters.");
               } else {
                  boolean isOther = keyword.equals("other");
                  if (isOther != (constraintOrSamples[0].length() == 0)) {
                     throw new IllegalArgumentException("The keyword 'other' must have no constraints, just samples.");
                  } else {
                     Constraint constraint;
                     if (isOther) {
                        constraint = NO_CONSTRAINT;
                     } else {
                        constraint = parseConstraint(constraintOrSamples[0]);
                     }

                     return new Rule(keyword, constraint, integerSamples, decimalSamples);
                  }
               }
            }
         }
      }
   }

   private static RuleList parseRuleChain(String description) throws ParseException {
      RuleList result = new RuleList();
      if (description.endsWith(";")) {
         description = description.substring(0, description.length() - 1);
      }

      String[] rules = SEMI_SEPARATED.split(description, 0);

      for(int i = 0; i < rules.length; ++i) {
         Rule rule = parseRule(rules[i].trim());
         result.hasExplicitBoundingInfo = result.hasExplicitBoundingInfo | (rule.integerSamples != null || rule.decimalSamples != null);
         result.addRule(rule);
      }

      return result.finish();
   }

   private static void addRange(StringBuilder result, double lb, double ub, boolean addSeparator) {
      if (addSeparator) {
         result.append(",");
      }

      if (lb == ub) {
         result.append(format(lb));
      } else {
         result.append(format(lb) + ".." + format(ub));
      }

   }

   private static String format(double lb) {
      long lbi = (long)lb;
      return lb == (double)lbi ? String.valueOf(lbi) : String.valueOf(lb);
   }

   private boolean addConditional(Set toAddTo, Set others, double trial) {
      IFixedDecimal toAdd = new FixedDecimal(trial);
      boolean added;
      if (!toAddTo.contains(toAdd) && !others.contains(toAdd)) {
         others.add(toAdd);
         added = true;
      } else {
         added = false;
      }

      return added;
   }

   public static PluralRules forLocale(ULocale locale) {
      return PluralRules.Factory.getDefaultFactory().forLocale(locale, PluralRules.PluralType.CARDINAL);
   }

   public static PluralRules forLocale(Locale locale) {
      return forLocale(ULocale.forLocale(locale));
   }

   public static PluralRules forLocale(ULocale locale, PluralType type) {
      return PluralRules.Factory.getDefaultFactory().forLocale(locale, type);
   }

   public static PluralRules forLocale(Locale locale, PluralType type) {
      return forLocale(ULocale.forLocale(locale), type);
   }

   private static boolean isValidKeyword(String token) {
      return ALLOWED_ID.containsAll(token);
   }

   private PluralRules(RuleList rules, StandardPluralRanges standardPluralRanges) {
      this.rules = rules;
      this.keywords = Collections.unmodifiableSet(rules.getKeywords());
      this.standardPluralRanges = standardPluralRanges;
   }

   public int hashCode() {
      return this.rules.hashCode();
   }

   public String select(double number) {
      return this.rules.select(new FixedDecimal(number));
   }

   public String select(FormattedNumber number) {
      return this.rules.select(number.getFixedDecimal());
   }

   public String select(FormattedNumberRange range) {
      if (this.standardPluralRanges == null) {
         throw new UnsupportedOperationException("Plural ranges are unavailable on this instance");
      } else {
         StandardPlural form1 = StandardPlural.fromString(this.select(range.getFirstFixedDecimal()));
         StandardPlural form2 = StandardPlural.fromString(this.select(range.getSecondFixedDecimal()));
         StandardPlural result = this.standardPluralRanges.resolve(form1, form2);
         return result.getKeyword();
      }
   }

   /** @deprecated */
   @Deprecated
   public String select(double number, int countVisibleFractionDigits, long fractionaldigits) {
      return this.rules.select(new FixedDecimal(number, countVisibleFractionDigits, fractionaldigits));
   }

   /** @deprecated */
   @Deprecated
   public String select(IFixedDecimal number) {
      return this.rules.select(number);
   }

   /** @deprecated */
   @Deprecated
   public boolean matches(FixedDecimal sample, String keyword) {
      return this.rules.select(sample, keyword);
   }

   public Set getKeywords() {
      return this.keywords;
   }

   public double getUniqueKeywordValue(String keyword) {
      DecimalQuantity uniqValDq = this.getUniqueKeywordDecimalQuantityValue(keyword);
      return uniqValDq.equals(NO_UNIQUE_VALUE_DECIMAL_QUANTITY) ? -0.00123456777 : uniqValDq.toDouble();
   }

   /** @deprecated */
   @Deprecated
   public DecimalQuantity getUniqueKeywordDecimalQuantityValue(String keyword) {
      Collection<DecimalQuantity> values = this.getAllKeywordDecimalQuantityValues(keyword);
      return values != null && values.size() == 1 ? (DecimalQuantity)values.iterator().next() : NO_UNIQUE_VALUE_DECIMAL_QUANTITY;
   }

   public Collection getAllKeywordValues(String keyword) {
      Collection<DecimalQuantity> samples = this.getAllKeywordDecimalQuantityValues(keyword);
      if (samples == null) {
         return null;
      } else {
         Collection<Double> result = new LinkedHashSet();

         for(DecimalQuantity dq : samples) {
            result.add(dq.toDouble());
         }

         return result;
      }
   }

   /** @deprecated */
   @Deprecated
   public Collection getAllKeywordDecimalQuantityValues(String keyword) {
      return this.getAllKeywordValues(keyword, PluralRules.SampleType.INTEGER);
   }

   /** @deprecated */
   @Deprecated
   public Collection getAllKeywordValues(String keyword, SampleType type) {
      return !this.isLimited(keyword, type) ? null : this.getDecimalQuantitySamples(keyword, type);
   }

   public Collection getSamples(String keyword) {
      return this.getSamples(keyword, PluralRules.SampleType.INTEGER);
   }

   /** @deprecated */
   @Deprecated
   public Collection getDecimalQuantitySamples(String keyword) {
      return this.getDecimalQuantitySamples(keyword, PluralRules.SampleType.INTEGER);
   }

   /** @deprecated */
   @Deprecated
   public Collection getSamples(String keyword, SampleType sampleType) {
      Collection<DecimalQuantity> samples = this.getDecimalQuantitySamples(keyword, sampleType);
      if (samples == null) {
         return null;
      } else {
         Collection<Double> result = new LinkedHashSet();

         for(DecimalQuantity dq : samples) {
            result.add(dq.toDouble());
         }

         return result;
      }
   }

   /** @deprecated */
   @Deprecated
   public Collection getDecimalQuantitySamples(String keyword, SampleType sampleType) {
      if (!this.keywords.contains(keyword)) {
         return null;
      } else {
         Set<DecimalQuantity> result = new LinkedHashSet();
         if (this.rules.hasExplicitBoundingInfo) {
            DecimalQuantitySamples samples = this.rules.getDecimalSamples(keyword, sampleType);
            return (Collection)(samples == null ? Collections.unmodifiableSet(result) : Collections.unmodifiableCollection(samples.addDecimalQuantitySamples(result)));
         } else {
            int maxCount = this.isLimited(keyword, sampleType) ? Integer.MAX_VALUE : 20;
            switch (sampleType) {
               case INTEGER:
                  for(int i = 0; i < 200 && this.addSample(keyword, new DecimalQuantity_DualStorageBCD(i), maxCount, result); ++i) {
                  }

                  this.addSample(keyword, new DecimalQuantity_DualStorageBCD(1000000), maxCount, result);
                  break;
               case DECIMAL:
                  for(int i = 0; i < 2000; ++i) {
                     DecimalQuantity_DualStorageBCD nextSample = new DecimalQuantity_DualStorageBCD(i);
                     nextSample.adjustMagnitude(-1);
                     if (!this.addSample(keyword, nextSample, maxCount, result)) {
                        break;
                     }
                  }

                  this.addSample(keyword, DecimalQuantity_DualStorageBCD.fromExponentString("1000000.0"), maxCount, result);
            }

            return result.size() == 0 ? null : Collections.unmodifiableSet(result);
         }
      }
   }

   private boolean addSample(String keyword, DecimalQuantity sample, int maxCount, Set result) {
      String selectedKeyword = this.select((IFixedDecimal)sample);
      if (selectedKeyword.equals(keyword)) {
         result.add(sample);
         --maxCount;
         if (maxCount < 0) {
            return false;
         }
      }

      return true;
   }

   /** @deprecated */
   @Deprecated
   public DecimalQuantitySamples getDecimalSamples(String keyword, SampleType sampleType) {
      return this.rules.getDecimalSamples(keyword, sampleType);
   }

   public static ULocale[] getAvailableULocales() {
      return PluralRules.Factory.getDefaultFactory().getAvailableULocales();
   }

   public static ULocale getFunctionalEquivalent(ULocale locale, boolean[] isAvailable) {
      return PluralRules.Factory.getDefaultFactory().getFunctionalEquivalent(locale, isAvailable);
   }

   public String toString() {
      return this.rules.toString();
   }

   public boolean equals(Object rhs) {
      return rhs instanceof PluralRules && this.equals((PluralRules)rhs);
   }

   public boolean equals(PluralRules rhs) {
      return rhs != null && this.toString().equals(rhs.toString());
   }

   public KeywordStatus getKeywordStatus(String keyword, int offset, Set explicits, Output uniqueValue) {
      return this.getKeywordStatus(keyword, offset, explicits, uniqueValue, PluralRules.SampleType.INTEGER);
   }

   /** @deprecated */
   @Deprecated
   public KeywordStatus getKeywordStatus(String keyword, int offset, Set explicits, Output uniqueValue, SampleType sampleType) {
      if (uniqueValue != null) {
         uniqueValue.value = null;
      }

      if (!this.keywords.contains(keyword)) {
         return PluralRules.KeywordStatus.INVALID;
      } else if (!this.isLimited(keyword, sampleType)) {
         return PluralRules.KeywordStatus.UNBOUNDED;
      } else {
         Collection<DecimalQuantity> values = this.getDecimalQuantitySamples(keyword, sampleType);
         int originalSize = values.size();
         if (explicits == null) {
            explicits = Collections.emptySet();
         }

         if (originalSize > explicits.size()) {
            if (originalSize == 1) {
               if (uniqueValue != null) {
                  uniqueValue.value = values.iterator().next();
               }

               return PluralRules.KeywordStatus.UNIQUE;
            } else {
               return PluralRules.KeywordStatus.BOUNDED;
            }
         } else {
            ArrayList<DecimalQuantity> subtractedSet = new ArrayList(values);

            for(DecimalQuantity explicit : explicits) {
               BigDecimal explicitBd = explicit.toBigDecimal();
               BigDecimal valToRemoveBd = explicitBd.subtract(new BigDecimal(offset));
               DecimalQuantity_DualStorageBCD valToRemove = new DecimalQuantity_DualStorageBCD(valToRemoveBd);
               subtractedSet.remove(valToRemove);
            }

            if (subtractedSet.size() == 0) {
               return PluralRules.KeywordStatus.SUPPRESSED;
            } else {
               if (uniqueValue != null && subtractedSet.size() == 1) {
                  uniqueValue.value = subtractedSet.iterator().next();
               }

               return originalSize == 1 ? PluralRules.KeywordStatus.UNIQUE : PluralRules.KeywordStatus.BOUNDED;
            }
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public String getRules(String keyword) {
      return this.rules.getRules(keyword);
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      throw new NotSerializableException();
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      throw new NotSerializableException();
   }

   private Object writeReplace() throws ObjectStreamException {
      return new PluralRulesSerialProxy(this.toString());
   }

   /** @deprecated */
   @Deprecated
   public int compareTo(PluralRules other) {
      return this.toString().compareTo(other.toString());
   }

   Boolean isLimited(String keyword) {
      return this.rules.isLimited(keyword, PluralRules.SampleType.INTEGER);
   }

   /** @deprecated */
   @Deprecated
   public boolean isLimited(String keyword, SampleType sampleType) {
      return this.rules.isLimited(keyword, sampleType);
   }

   /** @deprecated */
   @Deprecated
   public boolean computeLimited(String keyword, SampleType sampleType) {
      return this.rules.computeLimited(keyword, sampleType);
   }

   static {
      DEFAULT_RULE = new Rule("other", NO_CONSTRAINT, (DecimalQuantitySamples)null, (DecimalQuantitySamples)null);
      DEFAULT = new PluralRules((new RuleList()).addRule(DEFAULT_RULE), StandardPluralRanges.DEFAULT);
      AT_SEPARATED = Pattern.compile("\\s*\\Q\\E@\\s*");
      OR_SEPARATED = Pattern.compile("\\s*or\\s*");
      AND_SEPARATED = Pattern.compile("\\s*and\\s*");
      COMMA_SEPARATED = Pattern.compile("\\s*,\\s*");
      DOTDOT_SEPARATED = Pattern.compile("\\s*\\Q..\\E\\s*");
      TILDE_SEPARATED = Pattern.compile("\\s*~\\s*");
      SEMI_SEPARATED = Pattern.compile("\\s*;\\s*");
   }

   /** @deprecated */
   @Deprecated
   public abstract static class Factory {
      /** @deprecated */
      @Deprecated
      protected Factory() {
      }

      /** @deprecated */
      @Deprecated
      public abstract PluralRules forLocale(ULocale var1, PluralType var2);

      /** @deprecated */
      @Deprecated
      public final PluralRules forLocale(ULocale locale) {
         return this.forLocale(locale, PluralRules.PluralType.CARDINAL);
      }

      /** @deprecated */
      @Deprecated
      public abstract ULocale[] getAvailableULocales();

      /** @deprecated */
      @Deprecated
      public abstract ULocale getFunctionalEquivalent(ULocale var1, boolean[] var2);

      /** @deprecated */
      @Deprecated
      public static PluralRulesLoader getDefaultFactory() {
         return PluralRulesLoader.loader;
      }

      /** @deprecated */
      @Deprecated
      public abstract boolean hasOverride(ULocale var1);
   }

   public static enum PluralType {
      CARDINAL,
      ORDINAL;
   }

   /** @deprecated */
   @Deprecated
   public static enum Operand {
      /** @deprecated */
      @Deprecated
      n,
      /** @deprecated */
      @Deprecated
      i,
      /** @deprecated */
      @Deprecated
      f,
      /** @deprecated */
      @Deprecated
      t,
      /** @deprecated */
      @Deprecated
      v,
      /** @deprecated */
      @Deprecated
      w,
      /** @deprecated */
      @Deprecated
      e,
      /** @deprecated */
      @Deprecated
      c,
      /** @deprecated */
      @Deprecated
      j;
   }

   /** @deprecated */
   @Deprecated
   public static class FixedDecimal extends Number implements Comparable, IFixedDecimal {
      private static final long serialVersionUID = -4756200506571685661L;
      final double source;
      final int visibleDecimalDigitCount;
      final int visibleDecimalDigitCountWithoutTrailingZeros;
      final long decimalDigits;
      final long decimalDigitsWithoutTrailingZeros;
      final long integerValue;
      final boolean hasIntegerValue;
      final boolean isNegative;
      final int exponent;
      private final int baseFactor;
      static final long MAX = 1000000000000000000L;
      private static final long MAX_INTEGER_PART = 1000000000L;

      /** @deprecated */
      @Deprecated
      public double getSource() {
         return this.source;
      }

      /** @deprecated */
      @Deprecated
      public int getVisibleDecimalDigitCount() {
         return this.visibleDecimalDigitCount;
      }

      /** @deprecated */
      @Deprecated
      public int getVisibleDecimalDigitCountWithoutTrailingZeros() {
         return this.visibleDecimalDigitCountWithoutTrailingZeros;
      }

      /** @deprecated */
      @Deprecated
      public long getDecimalDigits() {
         return this.decimalDigits;
      }

      /** @deprecated */
      @Deprecated
      public long getDecimalDigitsWithoutTrailingZeros() {
         return this.decimalDigitsWithoutTrailingZeros;
      }

      /** @deprecated */
      @Deprecated
      public long getIntegerValue() {
         return this.integerValue;
      }

      /** @deprecated */
      @Deprecated
      public boolean isHasIntegerValue() {
         return this.hasIntegerValue;
      }

      /** @deprecated */
      @Deprecated
      public boolean isNegative() {
         return this.isNegative;
      }

      /** @deprecated */
      @Deprecated
      public int getBaseFactor() {
         return this.baseFactor;
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(double n, int v, long f, int e, int c) {
         this.isNegative = n < (double)0.0F;
         this.source = this.isNegative ? -n : n;
         this.visibleDecimalDigitCount = v;
         this.decimalDigits = f;
         this.integerValue = n > 1.0E18 ? 1000000000000000000L : (long)this.source;
         int initExpVal = e;
         if (e == 0) {
            initExpVal = c;
         }

         this.exponent = initExpVal;
         this.hasIntegerValue = this.source == (double)this.integerValue;
         if (f == 0L) {
            this.decimalDigitsWithoutTrailingZeros = 0L;
            this.visibleDecimalDigitCountWithoutTrailingZeros = 0;
         } else {
            long fdwtz = f;

            int trimmedCount;
            for(trimmedCount = v; fdwtz % 10L == 0L; --trimmedCount) {
               fdwtz /= 10L;
            }

            this.decimalDigitsWithoutTrailingZeros = fdwtz;
            this.visibleDecimalDigitCountWithoutTrailingZeros = trimmedCount;
         }

         this.baseFactor = (int)Math.pow((double)10.0F, (double)v);
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(double n, int v, long f, int e) {
         this(n, v, f, e, e);
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(double n, int v, long f) {
         this(n, v, f, 0);
      }

      /** @deprecated */
      @Deprecated
      public static FixedDecimal createWithExponent(double n, int v, int e) {
         return new FixedDecimal(n, v, (long)getFractionalDigits(n, v), e);
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(double n, int v) {
         this(n, v, (long)getFractionalDigits(n, v));
      }

      private static int getFractionalDigits(double n, int v) {
         if (v == 0) {
            return 0;
         } else {
            if (n < (double)0.0F) {
               n = -n;
            }

            int baseFactor = (int)Math.pow((double)10.0F, (double)v);
            long scaled = Math.round(n * (double)baseFactor);
            return (int)(scaled % (long)baseFactor);
         }
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(double n) {
         this(n, decimals(n));
      }

      /** @deprecated */
      @Deprecated
      public FixedDecimal(long n) {
         this((double)n, 0);
      }

      /** @deprecated */
      @Deprecated
      public static int decimals(double n) {
         if (!Double.isInfinite(n) && !Double.isNaN(n)) {
            if (n < (double)0.0F) {
               n = -n;
            }

            if (n == Math.floor(n)) {
               return 0;
            } else if (n < (double)1.0E9F) {
               long temp = (long)(n * (double)1000000.0F) % 1000000L;
               int mask = 10;

               for(int digits = 6; digits > 0; --digits) {
                  if (temp % (long)mask != 0L) {
                     return digits;
                  }

                  mask *= 10;
               }

               return 0;
            } else {
               String buf = String.format(Locale.ENGLISH, "%1.15e", n);
               int ePos = buf.lastIndexOf(101);
               int expNumPos = ePos + 1;
               if (buf.charAt(expNumPos) == '+') {
                  ++expNumPos;
               }

               String exponentStr = buf.substring(expNumPos);
               int exponent = Integer.parseInt(exponentStr);
               int numFractionDigits = ePos - 2 - exponent;
               if (numFractionDigits < 0) {
                  return 0;
               } else {
                  for(int i = ePos - 1; numFractionDigits > 0 && buf.charAt(i) == '0'; --i) {
                     --numFractionDigits;
                  }

                  return numFractionDigits;
               }
            }
         } else {
            return 0;
         }
      }

      /** @deprecated */
      @Deprecated
      private FixedDecimal(FixedDecimal other) {
         this.source = other.source;
         this.visibleDecimalDigitCount = other.visibleDecimalDigitCount;
         this.visibleDecimalDigitCountWithoutTrailingZeros = other.visibleDecimalDigitCountWithoutTrailingZeros;
         this.decimalDigits = other.decimalDigits;
         this.decimalDigitsWithoutTrailingZeros = other.decimalDigitsWithoutTrailingZeros;
         this.integerValue = other.integerValue;
         this.hasIntegerValue = other.hasIntegerValue;
         this.isNegative = other.isNegative;
         this.exponent = other.exponent;
         this.baseFactor = other.baseFactor;
      }

      /** @deprecated */
      @Deprecated
      public double getPluralOperand(Operand operand) {
         switch (operand) {
            case n:
               return this.exponent == 0 ? this.source : this.source * Math.pow((double)10.0F, (double)this.exponent);
            case i:
               return (double)this.intValue();
            case f:
               return (double)this.decimalDigits;
            case t:
               return (double)this.decimalDigitsWithoutTrailingZeros;
            case v:
               return (double)this.visibleDecimalDigitCount;
            case w:
               return (double)this.visibleDecimalDigitCountWithoutTrailingZeros;
            case e:
               return (double)this.exponent;
            case c:
               return (double)this.exponent;
            default:
               return this.doubleValue();
         }
      }

      /** @deprecated */
      @Deprecated
      public static Operand getOperand(String t) {
         return PluralRules.Operand.valueOf(t);
      }

      /** @deprecated */
      @Deprecated
      public int compareTo(FixedDecimal other) {
         if (this.exponent != other.exponent) {
            return this.doubleValue() < other.doubleValue() ? -1 : 1;
         } else if (this.integerValue != other.integerValue) {
            return this.integerValue < other.integerValue ? -1 : 1;
         } else if (this.source != other.source) {
            return this.source < other.source ? -1 : 1;
         } else if (this.visibleDecimalDigitCount != other.visibleDecimalDigitCount) {
            return this.visibleDecimalDigitCount < other.visibleDecimalDigitCount ? -1 : 1;
         } else {
            long diff = this.decimalDigits - other.decimalDigits;
            if (diff != 0L) {
               return diff < 0L ? -1 : 1;
            } else {
               return 0;
            }
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean equals(Object arg0) {
         if (arg0 == null) {
            return false;
         } else if (arg0 == this) {
            return true;
         } else if (!(arg0 instanceof FixedDecimal)) {
            return false;
         } else {
            FixedDecimal other = (FixedDecimal)arg0;
            return this.source == other.source && this.visibleDecimalDigitCount == other.visibleDecimalDigitCount && this.decimalDigits == other.decimalDigits && this.exponent == other.exponent;
         }
      }

      /** @deprecated */
      @Deprecated
      public int hashCode() {
         return (int)(this.decimalDigits + (long)(37 * (this.visibleDecimalDigitCount + (int)((double)37.0F * this.source))));
      }

      /** @deprecated */
      @Deprecated
      public String toString() {
         String baseString = String.format(Locale.ROOT, "%." + this.visibleDecimalDigitCount + "f", this.source);
         return this.exponent != 0 ? baseString + "e" + this.exponent : baseString;
      }

      /** @deprecated */
      @Deprecated
      public boolean hasIntegerValue() {
         return this.hasIntegerValue;
      }

      /** @deprecated */
      @Deprecated
      public int intValue() {
         return (int)this.longValue();
      }

      /** @deprecated */
      @Deprecated
      public long longValue() {
         return this.exponent == 0 ? this.integerValue : (long)(Math.pow((double)10.0F, (double)this.exponent) * (double)this.integerValue);
      }

      /** @deprecated */
      @Deprecated
      public float floatValue() {
         return (float)(this.source * Math.pow((double)10.0F, (double)this.exponent));
      }

      /** @deprecated */
      @Deprecated
      public double doubleValue() {
         return (this.isNegative ? -this.source : this.source) * Math.pow((double)10.0F, (double)this.exponent);
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         throw new NotSerializableException();
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         throw new NotSerializableException();
      }

      /** @deprecated */
      @Deprecated
      public boolean isNaN() {
         return Double.isNaN(this.source);
      }

      /** @deprecated */
      @Deprecated
      public boolean isInfinite() {
         return Double.isInfinite(this.source);
      }
   }

   /** @deprecated */
   @Deprecated
   public static enum SampleType {
      /** @deprecated */
      @Deprecated
      INTEGER,
      /** @deprecated */
      @Deprecated
      DECIMAL;
   }

   /** @deprecated */
   @Deprecated
   public static class DecimalQuantitySamplesRange {
      /** @deprecated */
      @Deprecated
      public final DecimalQuantity start;
      /** @deprecated */
      @Deprecated
      public final DecimalQuantity end;

      /** @deprecated */
      @Deprecated
      public DecimalQuantitySamplesRange(DecimalQuantity start, DecimalQuantity end) {
         if (start.getPluralOperand(PluralRules.Operand.v) != end.getPluralOperand(PluralRules.Operand.v)) {
            throw new IllegalArgumentException("Ranges must have the same number of visible decimals: " + start + "~" + end);
         } else {
            this.start = start;
            this.end = end;
         }
      }

      /** @deprecated */
      @Deprecated
      public String toString() {
         return this.start.toExponentString() + (this.end == this.start ? "" : "~" + this.end.toExponentString());
      }
   }

   /** @deprecated */
   @Deprecated
   public static class DecimalQuantitySamples {
      /** @deprecated */
      @Deprecated
      public final SampleType sampleType;
      /** @deprecated */
      @Deprecated
      public final Set samples;
      /** @deprecated */
      @Deprecated
      public final boolean bounded;

      private DecimalQuantitySamples(SampleType sampleType, Set samples, boolean bounded) {
         this.sampleType = sampleType;
         this.samples = samples;
         this.bounded = bounded;
      }

      static DecimalQuantitySamples parse(String source) {
         boolean bounded2 = true;
         boolean haveBound = false;
         Set<DecimalQuantitySamplesRange> samples2 = new LinkedHashSet();
         SampleType sampleType2;
         if (source.startsWith("integer")) {
            sampleType2 = PluralRules.SampleType.INTEGER;
         } else {
            if (!source.startsWith("decimal")) {
               throw new IllegalArgumentException("Samples must start with 'integer' or 'decimal'");
            }

            sampleType2 = PluralRules.SampleType.DECIMAL;
         }

         source = source.substring(7).trim();

         for(String range : PluralRules.COMMA_SEPARATED.split(source, 0)) {
            if (!range.equals("…") && !range.equals("...")) {
               if (haveBound) {
                  throw new IllegalArgumentException("Can only have … at the end of samples: " + range);
               }

               String[] rangeParts = PluralRules.TILDE_SEPARATED.split(range, 0);
               switch (rangeParts.length) {
                  case 1:
                     DecimalQuantity sample = DecimalQuantity_DualStorageBCD.fromExponentString(rangeParts[0]);
                     checkDecimal(sampleType2, sample);
                     samples2.add(new DecimalQuantitySamplesRange(sample, sample));
                     break;
                  case 2:
                     DecimalQuantity start = DecimalQuantity_DualStorageBCD.fromExponentString(rangeParts[0]);
                     DecimalQuantity end = DecimalQuantity_DualStorageBCD.fromExponentString(rangeParts[1]);
                     checkDecimal(sampleType2, start);
                     checkDecimal(sampleType2, end);
                     samples2.add(new DecimalQuantitySamplesRange(start, end));
                     break;
                  default:
                     throw new IllegalArgumentException("Ill-formed number range: " + range);
               }
            } else {
               bounded2 = false;
               haveBound = true;
            }
         }

         return new DecimalQuantitySamples(sampleType2, Collections.unmodifiableSet(samples2), bounded2);
      }

      private static void checkDecimal(SampleType sampleType2, DecimalQuantity sample) {
         if (sampleType2 == PluralRules.SampleType.INTEGER && sample.getPluralOperand(PluralRules.Operand.v) != (double)0.0F || sampleType2 == PluralRules.SampleType.DECIMAL && sample.getPluralOperand(PluralRules.Operand.v) == (double)0.0F && sample.getPluralOperand(PluralRules.Operand.e) == (double)0.0F) {
            throw new IllegalArgumentException("Ill-formed number range: " + sample);
         }
      }

      /** @deprecated */
      @Deprecated
      public Collection addSamples(Collection result) {
         this.addSamples(result, (Collection)null);
         return result;
      }

      /** @deprecated */
      @Deprecated
      public Collection addDecimalQuantitySamples(Collection result) {
         this.addSamples((Collection)null, result);
         return result;
      }

      /** @deprecated */
      @Deprecated
      public void addSamples(Collection doubleResult, Collection dqResult) {
         if ((doubleResult != null || dqResult != null) && (doubleResult == null || dqResult == null)) {
            boolean isDouble = doubleResult != null;

            for(DecimalQuantitySamplesRange range : this.samples) {
               DecimalQuantity start = range.start;
               DecimalQuantity end = range.end;
               int lowerDispMag = start.getLowerDisplayMagnitude();
               int exponent = start.getExponent();
               int incrementScale = lowerDispMag + exponent;
               BigDecimal incrementBd = BigDecimal.ONE.movePointRight(incrementScale);
               DecimalQuantity dq = start.createCopy();

               while(dq.toDouble() <= end.toDouble()) {
                  if (isDouble) {
                     double dblValue = dq.toDouble();
                     if (dblValue != Math.floor(dblValue) && dq.getPluralOperand(PluralRules.Operand.v) > (double)0.0F) {
                        doubleResult.add(dblValue);
                     }
                  } else {
                     dqResult.add(dq);
                  }

                  BigDecimal dqBd = dq.toBigDecimal();
                  BigDecimal newDqBd = dqBd.add(incrementBd);
                  dq = new DecimalQuantity_DualStorageBCD(newDqBd);
                  dq.setMinFraction(-lowerDispMag);
                  dq.adjustMagnitude(-exponent);
                  dq.adjustExponent(exponent);
               }
            }

         }
      }

      /** @deprecated */
      @Deprecated
      public String toString() {
         StringBuilder b = (new StringBuilder("@")).append(this.sampleType.toString().toLowerCase(Locale.ENGLISH));
         boolean first = true;

         for(DecimalQuantitySamplesRange item : this.samples) {
            if (first) {
               first = false;
            } else {
               b.append(",");
            }

            b.append(' ').append(item);
         }

         if (!this.bounded) {
            b.append(", …");
         }

         return b.toString();
      }

      /** @deprecated */
      @Deprecated
      public Set getSamples() {
         return this.samples;
      }

      /** @deprecated */
      @Deprecated
      public void getStartEndSamples(Set target) {
         for(DecimalQuantitySamplesRange range : this.samples) {
            target.add(range.start);
            target.add(range.end);
         }

      }
   }

   static class SimpleTokenizer {
      static final UnicodeSet BREAK_AND_IGNORE = (new UnicodeSet(new int[]{9, 10, 12, 13, 32, 32})).freeze();
      static final UnicodeSet BREAK_AND_KEEP = (new UnicodeSet(new int[]{33, 33, 37, 37, 44, 44, 46, 46, 61, 61})).freeze();

      static String[] split(String source) {
         int last = -1;
         List<String> result = new ArrayList();

         for(int i = 0; i < source.length(); ++i) {
            char ch = source.charAt(i);
            if (BREAK_AND_IGNORE.contains(ch)) {
               if (last >= 0) {
                  result.add(source.substring(last, i));
                  last = -1;
               }
            } else if (BREAK_AND_KEEP.contains(ch)) {
               if (last >= 0) {
                  result.add(source.substring(last, i));
               }

               result.add(source.substring(i, i + 1));
               last = -1;
            } else if (last < 0) {
               last = i;
            }
         }

         if (last >= 0) {
            result.add(source.substring(last));
         }

         return (String[])result.toArray(new String[result.size()]);
      }
   }

   private static class RangeConstraint implements Constraint, Serializable {
      private static final long serialVersionUID = 1L;
      private final int mod;
      private final boolean inRange;
      private final boolean integersOnly;
      private final double lowerBound;
      private final double upperBound;
      private final long[] range_list;
      private final Operand operand;

      RangeConstraint(int mod, boolean inRange, Operand operand, boolean integersOnly, double lowBound, double highBound, long[] vals) {
         this.mod = mod;
         this.inRange = inRange;
         this.integersOnly = integersOnly;
         this.lowerBound = lowBound;
         this.upperBound = highBound;
         this.range_list = vals;
         this.operand = operand;
      }

      public boolean isFulfilled(IFixedDecimal number) {
         double n = number.getPluralOperand(this.operand);
         if ((!this.integersOnly || n - (double)((long)n) == (double)0.0F) && (this.operand != PluralRules.Operand.j || number.getPluralOperand(PluralRules.Operand.v) == (double)0.0F)) {
            if (this.mod != 0) {
               n %= (double)this.mod;
            }

            boolean test = n >= this.lowerBound && n <= this.upperBound;
            if (test && this.range_list != null) {
               test = false;

               for(int i = 0; !test && i < this.range_list.length; i += 2) {
                  test = n >= (double)this.range_list[i] && n <= (double)this.range_list[i + 1];
               }
            }

            return this.inRange == test;
         } else {
            return !this.inRange;
         }
      }

      public boolean isLimited(SampleType sampleType) {
         boolean valueIsZero = this.lowerBound == this.upperBound && this.lowerBound == (double)0.0F;
         boolean hasDecimals = (this.operand == PluralRules.Operand.v || this.operand == PluralRules.Operand.w || this.operand == PluralRules.Operand.f || this.operand == PluralRules.Operand.t) && this.inRange != valueIsZero;
         switch (sampleType) {
            case INTEGER:
               return hasDecimals || (this.operand == PluralRules.Operand.n || this.operand == PluralRules.Operand.i || this.operand == PluralRules.Operand.j) && this.mod == 0 && this.inRange;
            case DECIMAL:
               return (!hasDecimals || this.operand == PluralRules.Operand.n || this.operand == PluralRules.Operand.j) && (this.integersOnly || this.lowerBound == this.upperBound) && this.mod == 0 && this.inRange;
            default:
               return false;
         }
      }

      public String toString() {
         StringBuilder result = new StringBuilder();
         result.append(this.operand);
         if (this.mod != 0) {
            result.append(" % ").append(this.mod);
         }

         boolean isList = this.lowerBound != this.upperBound;
         result.append(!isList ? (this.inRange ? " = " : " != ") : (this.integersOnly ? (this.inRange ? " = " : " != ") : (this.inRange ? " within " : " not within ")));
         if (this.range_list != null) {
            for(int i = 0; i < this.range_list.length; i += 2) {
               PluralRules.addRange(result, (double)this.range_list[i], (double)this.range_list[i + 1], i != 0);
            }
         } else {
            PluralRules.addRange(result, this.lowerBound, this.upperBound, false);
         }

         return result.toString();
      }
   }

   private abstract static class BinaryConstraint implements Constraint, Serializable {
      private static final long serialVersionUID = 1L;
      protected final Constraint a;
      protected final Constraint b;

      protected BinaryConstraint(Constraint a, Constraint b) {
         this.a = a;
         this.b = b;
      }
   }

   private static class AndConstraint extends BinaryConstraint {
      private static final long serialVersionUID = 7766999779862263523L;

      AndConstraint(Constraint a, Constraint b) {
         super(a, b);
      }

      public boolean isFulfilled(IFixedDecimal n) {
         return this.a.isFulfilled(n) && this.b.isFulfilled(n);
      }

      public boolean isLimited(SampleType sampleType) {
         return this.a.isLimited(sampleType) || this.b.isLimited(sampleType);
      }

      public String toString() {
         return this.a.toString() + " and " + this.b.toString();
      }
   }

   private static class OrConstraint extends BinaryConstraint {
      private static final long serialVersionUID = 1405488568664762222L;

      OrConstraint(Constraint a, Constraint b) {
         super(a, b);
      }

      public boolean isFulfilled(IFixedDecimal n) {
         return this.a.isFulfilled(n) || this.b.isFulfilled(n);
      }

      public boolean isLimited(SampleType sampleType) {
         return this.a.isLimited(sampleType) && this.b.isLimited(sampleType);
      }

      public String toString() {
         return this.a.toString() + " or " + this.b.toString();
      }
   }

   private static class Rule implements Serializable {
      private static final long serialVersionUID = 1L;
      private final String keyword;
      private final Constraint constraint;
      private final DecimalQuantitySamples integerSamples;
      private final DecimalQuantitySamples decimalSamples;

      public Rule(String keyword, Constraint constraint, DecimalQuantitySamples integerSamples, DecimalQuantitySamples decimalSamples) {
         this.keyword = keyword;
         this.constraint = constraint;
         this.integerSamples = integerSamples;
         this.decimalSamples = decimalSamples;
      }

      public Rule and(Constraint c) {
         return new Rule(this.keyword, new AndConstraint(this.constraint, c), this.integerSamples, this.decimalSamples);
      }

      public Rule or(Constraint c) {
         return new Rule(this.keyword, new OrConstraint(this.constraint, c), this.integerSamples, this.decimalSamples);
      }

      public String getKeyword() {
         return this.keyword;
      }

      public boolean appliesTo(IFixedDecimal n) {
         return this.constraint.isFulfilled(n);
      }

      public boolean isLimited(SampleType sampleType) {
         return this.constraint.isLimited(sampleType);
      }

      public String toString() {
         return this.keyword + ": " + this.constraint.toString() + (this.integerSamples == null ? "" : " " + this.integerSamples.toString()) + (this.decimalSamples == null ? "" : " " + this.decimalSamples.toString());
      }

      public int hashCode() {
         return this.keyword.hashCode() ^ this.constraint.hashCode();
      }

      public String getConstraint() {
         return this.constraint.toString();
      }
   }

   private static class RuleList implements Serializable {
      private boolean hasExplicitBoundingInfo;
      private static final long serialVersionUID = 1L;
      private final List rules;

      private RuleList() {
         this.hasExplicitBoundingInfo = false;
         this.rules = new ArrayList();
      }

      public RuleList addRule(Rule nextRule) {
         String keyword = nextRule.getKeyword();

         for(Rule rule : this.rules) {
            if (keyword.equals(rule.getKeyword())) {
               throw new IllegalArgumentException("Duplicate keyword: " + keyword);
            }
         }

         this.rules.add(nextRule);
         return this;
      }

      public RuleList finish() throws ParseException {
         Rule otherRule = null;
         Iterator<Rule> it = this.rules.iterator();

         while(it.hasNext()) {
            Rule rule = (Rule)it.next();
            if ("other".equals(rule.getKeyword())) {
               otherRule = rule;
               it.remove();
            }
         }

         if (otherRule == null) {
            otherRule = PluralRules.parseRule("other:");
         }

         this.rules.add(otherRule);
         return this;
      }

      private Rule selectRule(IFixedDecimal n) {
         for(Rule rule : this.rules) {
            if (rule.appliesTo(n)) {
               return rule;
            }
         }

         return null;
      }

      public String select(IFixedDecimal n) {
         if (!n.isInfinite() && !n.isNaN()) {
            Rule r = this.selectRule(n);
            return r.getKeyword();
         } else {
            return "other";
         }
      }

      public Set getKeywords() {
         Set<String> result = new LinkedHashSet();

         for(Rule rule : this.rules) {
            result.add(rule.getKeyword());
         }

         return result;
      }

      public boolean isLimited(String keyword, SampleType sampleType) {
         if (this.hasExplicitBoundingInfo) {
            DecimalQuantitySamples mySamples = this.getDecimalSamples(keyword, sampleType);
            return mySamples == null ? true : mySamples.bounded;
         } else {
            return this.computeLimited(keyword, sampleType);
         }
      }

      public boolean computeLimited(String keyword, SampleType sampleType) {
         boolean result = false;

         for(Rule rule : this.rules) {
            if (keyword.equals(rule.getKeyword())) {
               if (!rule.isLimited(sampleType)) {
                  return false;
               }

               result = true;
            }
         }

         return result;
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();

         for(Rule rule : this.rules) {
            if (builder.length() != 0) {
               builder.append(";  ");
            }

            builder.append(rule);
         }

         return builder.toString();
      }

      public String getRules(String keyword) {
         for(Rule rule : this.rules) {
            if (rule.getKeyword().equals(keyword)) {
               return rule.getConstraint();
            }
         }

         return null;
      }

      public boolean select(IFixedDecimal sample, String keyword) {
         for(Rule rule : this.rules) {
            if (rule.getKeyword().equals(keyword) && rule.appliesTo(sample)) {
               return true;
            }
         }

         return false;
      }

      public DecimalQuantitySamples getDecimalSamples(String keyword, SampleType sampleType) {
         for(Rule rule : this.rules) {
            if (rule.getKeyword().equals(keyword)) {
               return sampleType == PluralRules.SampleType.INTEGER ? rule.integerSamples : rule.decimalSamples;
            }
         }

         return null;
      }
   }

   public static enum KeywordStatus {
      INVALID,
      SUPPRESSED,
      UNIQUE,
      BOUNDED,
      UNBOUNDED;
   }

   private interface Constraint extends Serializable {
      boolean isFulfilled(IFixedDecimal var1);

      boolean isLimited(SampleType var1);
   }

   /** @deprecated */
   @Deprecated
   public interface IFixedDecimal {
      /** @deprecated */
      @Deprecated
      double getPluralOperand(Operand var1);

      /** @deprecated */
      @Deprecated
      boolean isNaN();

      /** @deprecated */
      @Deprecated
      boolean isInfinite();

      /** @deprecated */
      @Deprecated
      boolean isHasIntegerValue();
   }
}
