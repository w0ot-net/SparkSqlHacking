package com.ibm.icu.text;

import com.ibm.icu.impl.CacheBase;
import com.ibm.icu.impl.CurrencyData;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SoftCache;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;

public class DecimalFormatSymbols implements Cloneable, Serializable {
   public static final int CURRENCY_SPC_CURRENCY_MATCH = 0;
   public static final int CURRENCY_SPC_SURROUNDING_MATCH = 1;
   public static final int CURRENCY_SPC_INSERT = 2;
   private String[] currencySpcBeforeSym;
   private String[] currencySpcAfterSym;
   private static final String[] SYMBOL_KEYS = new String[]{"decimal", "group", "percentSign", "minusSign", "plusSign", "exponential", "perMille", "infinity", "nan", "currencyDecimal", "currencyGroup", "superscriptingExponent", "approximatelySign"};
   private static final String[] DEF_DIGIT_STRINGS_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
   private static final char[] DEF_DIGIT_CHARS_ARRAY = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
   private static final char DEF_DECIMAL_SEPARATOR = '.';
   private static final char DEF_GROUPING_SEPARATOR = ',';
   private static final char DEF_PERCENT = '%';
   private static final char DEF_MINUS_SIGN = '-';
   private static final char DEF_PLUS_SIGN = '+';
   private static final char DEF_PERMILL = '‰';
   private static final String[] SYMBOL_DEFAULTS = new String[]{String.valueOf('.'), String.valueOf(','), String.valueOf('%'), String.valueOf('-'), String.valueOf('+'), "E", String.valueOf('‰'), "∞", "NaN", null, null, "×", "~"};
   private static final String LATIN_NUMBERING_SYSTEM = "latn";
   private static final String NUMBER_ELEMENTS = "NumberElements";
   private static final String SYMBOLS = "symbols";
   private char zeroDigit;
   private char[] digits;
   private String[] digitStrings;
   private transient int codePointZero;
   private char groupingSeparator;
   private String groupingSeparatorString;
   private char decimalSeparator;
   private String decimalSeparatorString;
   private char perMill;
   private String perMillString;
   private char percent;
   private String percentString;
   private char digit;
   private char sigDigit;
   private char patternSeparator;
   private String infinity;
   private String NaN;
   private char minusSign;
   private String minusString;
   private char plusSign;
   private String plusString;
   private String approximatelyString;
   private String currencySymbol;
   private String intlCurrencySymbol;
   private char monetarySeparator;
   private String monetarySeparatorString;
   private char monetaryGroupingSeparator;
   private String monetaryGroupingSeparatorString;
   private char exponential;
   private String exponentSeparator;
   private char padEscape;
   private Locale requestedLocale;
   private ULocale ulocale;
   private String exponentMultiplicationSign;
   private static final long serialVersionUID = 5772796243397350300L;
   private static final int currentSerialVersion = 10;
   private int serialVersionOnStream;
   private static final CacheBase cachedLocaleData = new SoftCache() {
      protected CacheData createInstance(ULocale locale, Void unused) {
         return DecimalFormatSymbols.loadData(locale);
      }
   };
   private String currencyPattern;
   private ULocale validLocale;
   private ULocale actualLocale;
   private transient Currency currency;

   public DecimalFormatSymbols() {
      this(ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public DecimalFormatSymbols(Locale locale) {
      this(ULocale.forLocale(locale));
   }

   public DecimalFormatSymbols(ULocale locale) {
      this.exponentMultiplicationSign = null;
      this.serialVersionOnStream = 10;
      this.currencyPattern = null;
      this.initialize(locale, (NumberingSystem)null);
   }

   private DecimalFormatSymbols(Locale locale, NumberingSystem ns) {
      this(ULocale.forLocale(locale), ns);
   }

   private DecimalFormatSymbols(ULocale locale, NumberingSystem ns) {
      this.exponentMultiplicationSign = null;
      this.serialVersionOnStream = 10;
      this.currencyPattern = null;
      this.initialize(locale, ns);
   }

   public static DecimalFormatSymbols getInstance() {
      return new DecimalFormatSymbols();
   }

   public static DecimalFormatSymbols getInstance(Locale locale) {
      return new DecimalFormatSymbols(locale);
   }

   public static DecimalFormatSymbols getInstance(ULocale locale) {
      return new DecimalFormatSymbols(locale);
   }

   public static DecimalFormatSymbols forNumberingSystem(Locale locale, NumberingSystem ns) {
      return new DecimalFormatSymbols(locale, ns);
   }

   public static DecimalFormatSymbols forNumberingSystem(ULocale locale, NumberingSystem ns) {
      return new DecimalFormatSymbols(locale, ns);
   }

   public static Locale[] getAvailableLocales() {
      return ICUResourceBundle.getAvailableLocales();
   }

   public static ULocale[] getAvailableULocales() {
      return ICUResourceBundle.getAvailableULocales();
   }

   public char getZeroDigit() {
      return this.zeroDigit;
   }

   public char[] getDigits() {
      return (char[])this.digits.clone();
   }

   public void setZeroDigit(char zeroDigit) {
      this.zeroDigit = zeroDigit;
      this.digitStrings = (String[])this.digitStrings.clone();
      this.digits = (char[])this.digits.clone();
      this.digitStrings[0] = String.valueOf(zeroDigit);
      this.digits[0] = zeroDigit;

      for(int i = 1; i < 10; ++i) {
         char d = (char)(zeroDigit + i);
         this.digitStrings[i] = String.valueOf(d);
         this.digits[i] = d;
      }

      this.codePointZero = zeroDigit;
   }

   public String[] getDigitStrings() {
      return (String[])this.digitStrings.clone();
   }

   /** @deprecated */
   @Deprecated
   public String[] getDigitStringsLocal() {
      return this.digitStrings;
   }

   /** @deprecated */
   @Deprecated
   public int getCodePointZero() {
      return this.codePointZero;
   }

   public void setDigitStrings(String[] digitStrings) {
      if (digitStrings == null) {
         throw new NullPointerException("The input digit string array is null");
      } else if (digitStrings.length != 10) {
         throw new IllegalArgumentException("Number of digit strings is not 10");
      } else {
         String[] tmpDigitStrings = new String[10];
         char[] tmpDigits = new char[10];
         int tmpCodePointZero = -1;

         for(int i = 0; i < 10; ++i) {
            String digitStr = digitStrings[i];
            if (digitStr == null) {
               throw new IllegalArgumentException("The input digit string array contains a null element");
            }

            tmpDigitStrings[i] = digitStr;
            int cp;
            int cc;
            if (digitStr.length() == 0) {
               cp = -1;
               cc = 0;
            } else {
               cp = Character.codePointAt(digitStrings[i], 0);
               cc = Character.charCount(cp);
            }

            if (cc == digitStr.length()) {
               if (cc == 1 && tmpDigits != null) {
                  tmpDigits[i] = (char)cp;
               } else {
                  tmpDigits = null;
               }

               if (i == 0) {
                  tmpCodePointZero = cp;
               } else if (cp != tmpCodePointZero + i) {
                  tmpCodePointZero = -1;
               }
            } else {
               tmpCodePointZero = -1;
               tmpDigits = null;
            }
         }

         this.digitStrings = tmpDigitStrings;
         this.codePointZero = tmpCodePointZero;
         if (tmpDigits == null) {
            this.zeroDigit = DEF_DIGIT_CHARS_ARRAY[0];
            this.digits = DEF_DIGIT_CHARS_ARRAY;
         } else {
            this.zeroDigit = tmpDigits[0];
            this.digits = tmpDigits;
         }

      }
   }

   public char getSignificantDigit() {
      return this.sigDigit;
   }

   public void setSignificantDigit(char sigDigit) {
      this.sigDigit = sigDigit;
   }

   public char getGroupingSeparator() {
      return this.groupingSeparator;
   }

   public void setGroupingSeparator(char groupingSeparator) {
      this.groupingSeparator = groupingSeparator;
      this.groupingSeparatorString = String.valueOf(groupingSeparator);
   }

   public String getGroupingSeparatorString() {
      return this.groupingSeparatorString;
   }

   public void setGroupingSeparatorString(String groupingSeparatorString) {
      if (groupingSeparatorString == null) {
         throw new NullPointerException("The input grouping separator is null");
      } else {
         this.groupingSeparatorString = groupingSeparatorString;
         if (groupingSeparatorString.length() == 1) {
            this.groupingSeparator = groupingSeparatorString.charAt(0);
         } else {
            this.groupingSeparator = ',';
         }

      }
   }

   public char getDecimalSeparator() {
      return this.decimalSeparator;
   }

   public void setDecimalSeparator(char decimalSeparator) {
      this.decimalSeparator = decimalSeparator;
      this.decimalSeparatorString = String.valueOf(decimalSeparator);
   }

   public String getDecimalSeparatorString() {
      return this.decimalSeparatorString;
   }

   public void setDecimalSeparatorString(String decimalSeparatorString) {
      if (decimalSeparatorString == null) {
         throw new NullPointerException("The input decimal separator is null");
      } else {
         this.decimalSeparatorString = decimalSeparatorString;
         if (decimalSeparatorString.length() == 1) {
            this.decimalSeparator = decimalSeparatorString.charAt(0);
         } else {
            this.decimalSeparator = '.';
         }

      }
   }

   public char getPerMill() {
      return this.perMill;
   }

   public void setPerMill(char perMill) {
      this.perMill = perMill;
      this.perMillString = String.valueOf(perMill);
   }

   public String getPerMillString() {
      return this.perMillString;
   }

   public void setPerMillString(String perMillString) {
      if (perMillString == null) {
         throw new NullPointerException("The input permille string is null");
      } else {
         this.perMillString = perMillString;
         if (perMillString.length() == 1) {
            this.perMill = perMillString.charAt(0);
         } else {
            this.perMill = 8240;
         }

      }
   }

   public char getPercent() {
      return this.percent;
   }

   public void setPercent(char percent) {
      this.percent = percent;
      this.percentString = String.valueOf(percent);
   }

   public String getPercentString() {
      return this.percentString;
   }

   public void setPercentString(String percentString) {
      if (percentString == null) {
         throw new NullPointerException("The input percent sign is null");
      } else {
         this.percentString = percentString;
         if (percentString.length() == 1) {
            this.percent = percentString.charAt(0);
         } else {
            this.percent = '%';
         }

      }
   }

   public char getDigit() {
      return this.digit;
   }

   public void setDigit(char digit) {
      this.digit = digit;
   }

   public char getPatternSeparator() {
      return this.patternSeparator;
   }

   public void setPatternSeparator(char patternSeparator) {
      this.patternSeparator = patternSeparator;
   }

   public String getInfinity() {
      return this.infinity;
   }

   public void setInfinity(String infinity) {
      this.infinity = infinity;
   }

   public String getNaN() {
      return this.NaN;
   }

   public void setNaN(String NaN) {
      this.NaN = NaN;
   }

   public char getMinusSign() {
      return this.minusSign;
   }

   public void setMinusSign(char minusSign) {
      this.minusSign = minusSign;
      this.minusString = String.valueOf(minusSign);
   }

   public String getMinusSignString() {
      return this.minusString;
   }

   public void setMinusSignString(String minusSignString) {
      if (minusSignString == null) {
         throw new NullPointerException("The input minus sign is null");
      } else {
         this.minusString = minusSignString;
         if (minusSignString.length() == 1) {
            this.minusSign = minusSignString.charAt(0);
         } else {
            this.minusSign = '-';
         }

      }
   }

   public char getPlusSign() {
      return this.plusSign;
   }

   public void setPlusSign(char plus) {
      this.plusSign = plus;
      this.plusString = String.valueOf(plus);
   }

   public String getPlusSignString() {
      return this.plusString;
   }

   public void setPlusSignString(String plusSignString) {
      if (plusSignString == null) {
         throw new NullPointerException("The input plus sign is null");
      } else {
         this.plusString = plusSignString;
         if (plusSignString.length() == 1) {
            this.plusSign = plusSignString.charAt(0);
         } else {
            this.plusSign = '+';
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public String getApproximatelySignString() {
      return this.approximatelyString;
   }

   /** @deprecated */
   @Deprecated
   public void setApproximatelySignString(String approximatelySignString) {
      if (approximatelySignString == null) {
         throw new NullPointerException("The input plus sign is null");
      } else {
         this.approximatelyString = approximatelySignString;
      }
   }

   public String getCurrencySymbol() {
      return this.currencySymbol;
   }

   public void setCurrencySymbol(String currency) {
      this.currencySymbol = currency;
   }

   public String getInternationalCurrencySymbol() {
      return this.intlCurrencySymbol;
   }

   public void setInternationalCurrencySymbol(String currency) {
      this.intlCurrencySymbol = currency;
   }

   public Currency getCurrency() {
      return this.currency;
   }

   public void setCurrency(Currency currency) {
      if (currency == null) {
         throw new NullPointerException();
      } else if (!currency.equals(this.currency)) {
         CurrencyData.CurrencyDisplayInfo displayInfo = CurrencyData.provider.getInstance(this.ulocale, true);
         this.setCurrencyOrNull(currency, displayInfo);
      }
   }

   private void setCurrencyOrNull(Currency currency, CurrencyData.CurrencyDisplayInfo displayInfo) {
      this.currency = currency;
      if (currency == null) {
         this.intlCurrencySymbol = "XXX";
         this.currencySymbol = "¤";
         this.currencyPattern = null;
      } else {
         this.intlCurrencySymbol = currency.getCurrencyCode();
         this.currencySymbol = currency.getSymbol(this.ulocale);
         CurrencyData.CurrencyFormatInfo formatInfo = displayInfo.getFormatInfo(currency.getCurrencyCode());
         if (formatInfo != null) {
            this.setMonetaryDecimalSeparatorString(formatInfo.monetaryDecimalSeparator);
            this.setMonetaryGroupingSeparatorString(formatInfo.monetaryGroupingSeparator);
            this.currencyPattern = formatInfo.currencyPattern;
         }

      }
   }

   public char getMonetaryDecimalSeparator() {
      return this.monetarySeparator;
   }

   public void setMonetaryDecimalSeparator(char sep) {
      this.monetarySeparator = sep;
      this.monetarySeparatorString = String.valueOf(sep);
   }

   public String getMonetaryDecimalSeparatorString() {
      return this.monetarySeparatorString;
   }

   public void setMonetaryDecimalSeparatorString(String sep) {
      if (sep == null) {
         throw new NullPointerException("The input monetary decimal separator is null");
      } else {
         this.monetarySeparatorString = sep;
         if (sep.length() == 1) {
            this.monetarySeparator = sep.charAt(0);
         } else {
            this.monetarySeparator = '.';
         }

      }
   }

   public char getMonetaryGroupingSeparator() {
      return this.monetaryGroupingSeparator;
   }

   public void setMonetaryGroupingSeparator(char sep) {
      this.monetaryGroupingSeparator = sep;
      this.monetaryGroupingSeparatorString = String.valueOf(sep);
   }

   public String getMonetaryGroupingSeparatorString() {
      return this.monetaryGroupingSeparatorString;
   }

   public void setMonetaryGroupingSeparatorString(String sep) {
      if (sep == null) {
         throw new NullPointerException("The input monetary grouping separator is null");
      } else {
         this.monetaryGroupingSeparatorString = sep;
         if (sep.length() == 1) {
            this.monetaryGroupingSeparator = sep.charAt(0);
         } else {
            this.monetaryGroupingSeparator = ',';
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public String getCurrencyPattern() {
      return this.currencyPattern;
   }

   public String getExponentMultiplicationSign() {
      return this.exponentMultiplicationSign;
   }

   public void setExponentMultiplicationSign(String exponentMultiplicationSign) {
      this.exponentMultiplicationSign = exponentMultiplicationSign;
   }

   public String getExponentSeparator() {
      return this.exponentSeparator;
   }

   public void setExponentSeparator(String exp) {
      this.exponentSeparator = exp;
   }

   public char getPadEscape() {
      return this.padEscape;
   }

   public void setPadEscape(char c) {
      this.padEscape = c;
   }

   public String getPatternForCurrencySpacing(int itemType, boolean beforeCurrency) {
      if (itemType >= 0 && itemType <= 2) {
         return beforeCurrency ? this.currencySpcBeforeSym[itemType] : this.currencySpcAfterSym[itemType];
      } else {
         throw new IllegalArgumentException("unknown currency spacing: " + itemType);
      }
   }

   public void setPatternForCurrencySpacing(int itemType, boolean beforeCurrency, String pattern) {
      if (itemType >= 0 && itemType <= 2) {
         if (beforeCurrency) {
            this.currencySpcBeforeSym = (String[])this.currencySpcBeforeSym.clone();
            this.currencySpcBeforeSym[itemType] = pattern;
         } else {
            this.currencySpcAfterSym = (String[])this.currencySpcAfterSym.clone();
            this.currencySpcAfterSym[itemType] = pattern;
         }

      } else {
         throw new IllegalArgumentException("unknown currency spacing: " + itemType);
      }
   }

   public Locale getLocale() {
      return this.requestedLocale;
   }

   public ULocale getULocale() {
      return this.ulocale;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException e) {
         throw new ICUCloneNotSupportedException(e);
      }
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof DecimalFormatSymbols)) {
         return false;
      } else if (this == obj) {
         return true;
      } else {
         DecimalFormatSymbols other = (DecimalFormatSymbols)obj;

         for(int i = 0; i <= 2; ++i) {
            if (!this.currencySpcBeforeSym[i].equals(other.currencySpcBeforeSym[i])) {
               return false;
            }

            if (!this.currencySpcAfterSym[i].equals(other.currencySpcAfterSym[i])) {
               return false;
            }
         }

         if (other.digits == null) {
            for(int i = 0; i < 10; ++i) {
               if (this.digits[i] != other.zeroDigit + i) {
                  return false;
               }
            }
         } else if (!Arrays.equals(this.digits, other.digits)) {
            return false;
         }

         return this.groupingSeparator == other.groupingSeparator && this.decimalSeparator == other.decimalSeparator && this.percent == other.percent && this.perMill == other.perMill && this.digit == other.digit && this.minusSign == other.minusSign && this.minusString.equals(other.minusString) && this.patternSeparator == other.patternSeparator && this.infinity.equals(other.infinity) && this.NaN.equals(other.NaN) && this.currencySymbol.equals(other.currencySymbol) && this.intlCurrencySymbol.equals(other.intlCurrencySymbol) && this.padEscape == other.padEscape && this.plusSign == other.plusSign && this.plusString.equals(other.plusString) && this.approximatelyString.equals(other.approximatelyString) && this.exponentSeparator.equals(other.exponentSeparator) && this.monetarySeparator == other.monetarySeparator && this.monetaryGroupingSeparator == other.monetaryGroupingSeparator && this.exponentMultiplicationSign.equals(other.exponentMultiplicationSign);
      }
   }

   public int hashCode() {
      int result = this.digits[0];
      result = result * 37 + this.groupingSeparator;
      result = result * 37 + this.decimalSeparator;
      return result;
   }

   private void initialize(ULocale locale, NumberingSystem ns) {
      this.requestedLocale = locale.toLocale();
      this.ulocale = locale;
      ULocale keyLocale = ns == null ? locale : locale.setKeywordValue("numbers", ns.getName());
      CacheData data = (CacheData)cachedLocaleData.getInstance(keyLocale, (Object)null);
      this.setLocale(data.validLocale, data.validLocale);
      this.setDigitStrings(data.digits);
      String[] numberElements = data.numberElements;
      this.setDecimalSeparatorString(numberElements[0]);
      this.setGroupingSeparatorString(numberElements[1]);
      this.patternSeparator = ';';
      this.setPercentString(numberElements[2]);
      this.setMinusSignString(numberElements[3]);
      this.setPlusSignString(numberElements[4]);
      this.setExponentSeparator(numberElements[5]);
      this.setPerMillString(numberElements[6]);
      this.setInfinity(numberElements[7]);
      this.setNaN(numberElements[8]);
      this.setMonetaryDecimalSeparatorString(numberElements[9]);
      this.setMonetaryGroupingSeparatorString(numberElements[10]);
      this.setExponentMultiplicationSign(numberElements[11]);
      this.setApproximatelySignString(numberElements[12]);
      this.digit = '#';
      this.padEscape = '*';
      this.sigDigit = '@';
      CurrencyData.CurrencyDisplayInfo displayInfo = CurrencyData.provider.getInstance(this.ulocale, true);
      this.initSpacingInfo(displayInfo.getSpacingInfo());
      this.setCurrencyOrNull(Currency.getInstance(this.ulocale), displayInfo);
   }

   private static CacheData loadData(ULocale locale) {
      NumberingSystem ns = NumberingSystem.getInstance(locale);
      String[] digits = new String[10];
      String nsName;
      if (ns != null && ns.getRadix() == 10 && !ns.isAlgorithmic() && NumberingSystem.isValidDigitString(ns.getDescription())) {
         String digitString = ns.getDescription();
         int i = 0;

         for(int offset = 0; i < 10; ++i) {
            int cp = digitString.codePointAt(offset);
            int nextOffset = offset + Character.charCount(cp);
            digits[i] = digitString.substring(offset, nextOffset);
            offset = nextOffset;
         }

         nsName = ns.getName();
      } else {
         digits = DEF_DIGIT_STRINGS_ARRAY;
         nsName = "latn";
      }

      ICUResourceBundle rb = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", locale);
      ULocale validLocale = rb.getULocale();
      String[] numberElements = new String[SYMBOL_KEYS.length];
      DecFmtDataSink sink = new DecFmtDataSink(numberElements);

      try {
         rb.getAllItemsWithFallback("NumberElements/" + nsName + "/" + "symbols", sink);
      } catch (MissingResourceException var13) {
      }

      boolean hasNull = false;

      for(String entry : numberElements) {
         if (entry == null) {
            hasNull = true;
            break;
         }
      }

      if (hasNull && !nsName.equals("latn")) {
         rb.getAllItemsWithFallback("NumberElements/latn/symbols", sink);
      }

      for(int i = 0; i < SYMBOL_KEYS.length; ++i) {
         if (numberElements[i] == null) {
            numberElements[i] = SYMBOL_DEFAULTS[i];
         }
      }

      if (numberElements[9] == null) {
         numberElements[9] = numberElements[0];
      }

      if (numberElements[10] == null) {
         numberElements[10] = numberElements[1];
      }

      return new CacheData(validLocale, digits, numberElements);
   }

   private void initSpacingInfo(CurrencyData.CurrencySpacingInfo spcInfo) {
      this.currencySpcBeforeSym = spcInfo.getBeforeSymbols();
      this.currencySpcAfterSym = spcInfo.getAfterSymbols();
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      if (this.serialVersionOnStream < 1) {
         this.monetarySeparator = this.decimalSeparator;
         this.exponential = 'E';
      }

      if (this.serialVersionOnStream < 2) {
         this.padEscape = '*';
         this.plusSign = '+';
         this.exponentSeparator = String.valueOf(this.exponential);
      }

      if (this.serialVersionOnStream < 3) {
         this.requestedLocale = Locale.getDefault();
      }

      if (this.serialVersionOnStream < 4) {
         this.ulocale = ULocale.forLocale(this.requestedLocale);
      }

      if (this.serialVersionOnStream < 5) {
         this.monetaryGroupingSeparator = this.groupingSeparator;
      }

      if (this.serialVersionOnStream < 6) {
         if (this.currencySpcBeforeSym == null) {
            this.currencySpcBeforeSym = new String[3];
         }

         if (this.currencySpcAfterSym == null) {
            this.currencySpcAfterSym = new String[3];
         }

         this.initSpacingInfo(CurrencyData.CurrencySpacingInfo.DEFAULT);
      }

      if (this.serialVersionOnStream < 7) {
         if (this.minusString == null) {
            this.minusString = String.valueOf(this.minusSign);
         }

         if (this.plusString == null) {
            this.plusString = String.valueOf(this.plusSign);
         }
      }

      if (this.serialVersionOnStream < 8 && this.exponentMultiplicationSign == null) {
         this.exponentMultiplicationSign = "×";
      }

      if (this.serialVersionOnStream < 9) {
         if (this.digitStrings == null) {
            this.digitStrings = new String[10];
            if (this.digits != null && this.digits.length == 10) {
               this.zeroDigit = this.digits[0];

               for(int i = 0; i < 10; ++i) {
                  this.digitStrings[i] = String.valueOf(this.digits[i]);
               }
            } else {
               char digit = this.zeroDigit;
               if (this.digits == null) {
                  this.digits = new char[10];
               }

               for(int i = 0; i < 10; ++i) {
                  this.digits[i] = digit;
                  this.digitStrings[i] = String.valueOf(digit);
                  ++digit;
               }
            }
         }

         if (this.decimalSeparatorString == null) {
            this.decimalSeparatorString = String.valueOf(this.decimalSeparator);
         }

         if (this.groupingSeparatorString == null) {
            this.groupingSeparatorString = String.valueOf(this.groupingSeparator);
         }

         if (this.percentString == null) {
            this.percentString = String.valueOf(this.percent);
         }

         if (this.perMillString == null) {
            this.perMillString = String.valueOf(this.perMill);
         }

         if (this.monetarySeparatorString == null) {
            this.monetarySeparatorString = String.valueOf(this.monetarySeparator);
         }

         if (this.monetaryGroupingSeparatorString == null) {
            this.monetaryGroupingSeparatorString = String.valueOf(this.monetaryGroupingSeparator);
         }
      }

      if (this.serialVersionOnStream < 10) {
         this.approximatelyString = "~";
      }

      this.serialVersionOnStream = 10;
      this.currency = Currency.getInstance(this.intlCurrencySymbol);
      this.setDigitStrings(this.digitStrings);
   }

   public final ULocale getLocale(ULocale.Type type) {
      return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
   }

   final void setLocale(ULocale valid, ULocale actual) {
      if (valid == null != (actual == null)) {
         throw new IllegalArgumentException();
      } else {
         this.validLocale = valid;
         this.actualLocale = actual;
      }
   }

   private static final class DecFmtDataSink extends UResource.Sink {
      private String[] numberElements;

      public DecFmtDataSink(String[] numberElements) {
         this.numberElements = numberElements;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         UResource.Table symbolsTable = value.getTable();

         for(int j = 0; symbolsTable.getKeyAndValue(j, key, value); ++j) {
            for(int i = 0; i < DecimalFormatSymbols.SYMBOL_KEYS.length; ++i) {
               if (key.contentEquals(DecimalFormatSymbols.SYMBOL_KEYS[i])) {
                  if (this.numberElements[i] == null) {
                     this.numberElements[i] = value.toString();
                  }
                  break;
               }
            }
         }

      }
   }

   private static class CacheData {
      final ULocale validLocale;
      final String[] digits;
      final String[] numberElements;

      public CacheData(ULocale loc, String[] digits, String[] numberElements) {
         this.validLocale = loc;
         this.digits = digits;
         this.numberElements = numberElements;
      }
   }
}
