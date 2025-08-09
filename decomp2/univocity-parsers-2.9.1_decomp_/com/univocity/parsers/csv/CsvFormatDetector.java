package com.univocity.parsers.csv;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.input.InputAnalysisProcess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class CsvFormatDetector implements InputAnalysisProcess {
   private final int MAX_ROW_SAMPLES;
   private final char comment;
   private final char suggestedDelimiter;
   private final char normalizedNewLine;
   private final int whitespaceRangeStart;
   private char[] allowedDelimiters;
   private char[] delimiterPreference;
   private final char suggestedQuote;
   private final char suggestedQuoteEscape;

   public CsvFormatDetector(int maxRowSamples, CsvParserSettings settings, int whitespaceRangeStart) {
      this.MAX_ROW_SAMPLES = maxRowSamples;
      this.whitespaceRangeStart = whitespaceRangeStart;
      this.allowedDelimiters = settings.getDelimitersForDetection();
      if (this.allowedDelimiters != null && this.allowedDelimiters.length > 0) {
         this.suggestedDelimiter = this.allowedDelimiters[0];
         this.delimiterPreference = (char[])this.allowedDelimiters.clone();
         Arrays.sort(this.allowedDelimiters);
      } else {
         String delimiter = ((CsvFormat)settings.getFormat()).getDelimiterString();
         this.suggestedDelimiter = delimiter.length() > 1 ? 44 : ((CsvFormat)settings.getFormat()).getDelimiter();
         this.allowedDelimiters = new char[0];
         this.delimiterPreference = this.allowedDelimiters;
      }

      this.normalizedNewLine = ((CsvFormat)settings.getFormat()).getNormalizedNewline();
      this.comment = ((CsvFormat)settings.getFormat()).getComment();
      this.suggestedQuote = ((CsvFormat)settings.getFormat()).getQuote();
      this.suggestedQuoteEscape = ((CsvFormat)settings.getFormat()).getQuoteEscape();
   }

   protected Map calculateTotals(List symbolsPerRow) {
      Map<Character, Integer> out = new HashMap();

      for(Map rowStats : symbolsPerRow) {
         for(Map.Entry symbolStats : rowStats.entrySet()) {
            Character symbol = (Character)symbolStats.getKey();
            Integer count = (Integer)symbolStats.getValue();
            Integer total = (Integer)out.get(symbol);
            if (total == null) {
               total = 0;
            }

            out.put(symbol, total + count);
         }
      }

      return out;
   }

   public void execute(char[] characters, int length) {
      Set<Character> allSymbols = new HashSet();
      Map<Character, Integer> symbols = new HashMap();
      Map<Character, Integer> escape = new HashMap();
      List<Map<Character, Integer>> symbolsPerRow = new ArrayList();
      int doubleQuoteCount = 0;
      int singleQuoteCount = 0;
      char inQuote = 0;
      boolean afterNewLine = true;

      int i;
      label266:
      for(i = 0; i < length; ++i) {
         char ch = characters[i];
         if (afterNewLine && ch == this.comment) {
            do {
               ++i;
               if (i >= length) {
                  continue label266;
               }

               ch = characters[i];
            } while(ch != '\r' && ch != '\n' && ch != this.normalizedNewLine);

            if (ch == '\r' && i + 1 < characters.length && characters[i + 1] == '\n') {
               ++i;
            }
         } else if (ch != '"' && ch != '\'') {
            if (inQuote == 0) {
               afterNewLine = false;
               if (this.isSymbol(ch)) {
                  allSymbols.add(ch);
                  this.increment(symbols, ch);
               } else if ((ch == '\r' || ch == '\n' || ch == this.normalizedNewLine) && symbols.size() > 0) {
                  afterNewLine = true;
                  symbolsPerRow.add(symbols);
                  if (symbolsPerRow.size() == this.MAX_ROW_SAMPLES) {
                     break;
                  }

                  symbols = new HashMap();
               }
            }
         } else if (inQuote == ch) {
            if (ch == '"') {
               ++doubleQuoteCount;
            } else {
               ++singleQuoteCount;
            }

            if (i + 1 < length) {
               char next = characters[i + 1];
               if (Character.isLetterOrDigit(next) || next <= ' ' && this.whitespaceRangeStart < next && next != '\n' && next != '\r') {
                  char prev = characters[i - 1];
                  if (!Character.isLetterOrDigit(prev) && prev != '\n' && prev != '\r') {
                     this.increment(escape, prev);
                  }
               }
            }

            inQuote = 0;
         } else if (inQuote == 0) {
            char prev = 0;

            int j;
            for(j = i; prev <= ' '; prev = characters[j]) {
               --j;
               if (j < 0) {
                  break;
               }
            }

            if (j < 0 || !Character.isLetterOrDigit(prev)) {
               inQuote = ch;
            }
         }
      }

      if (symbols.size() > 0 && length < characters.length) {
         symbolsPerRow.add(symbols);
      }

      if (length >= characters.length && i >= length && symbolsPerRow.size() > 1) {
         symbolsPerRow.remove(symbolsPerRow.size() - 1);
      }

      Map<Character, Integer> totals = this.calculateTotals(symbolsPerRow);
      Map<Character, Integer> sums = new HashMap();
      Set<Character> toRemove = new HashSet();

      for(Map prev : symbolsPerRow) {
         for(Map current : symbolsPerRow) {
            for(Character symbol : allSymbols) {
               Integer previousCount = (Integer)prev.get(symbol);
               Integer currentCount = (Integer)current.get(symbol);
               if (previousCount == null && currentCount == null) {
                  toRemove.add(symbol);
               }

               if (previousCount != null && currentCount != null) {
                  this.increment(sums, symbol, Math.abs(previousCount - currentCount));
               }
            }
         }
      }

      if (toRemove.size() == sums.size()) {
         Map<Character, Integer> lineCount = new HashMap();

         for(int var23 = 0; var23 < symbolsPerRow.size(); ++var23) {
            for(Character symbolInRow : ((Map)symbolsPerRow.get(var23)).keySet()) {
               Integer count = (Integer)lineCount.get(symbolInRow);
               if (count == null) {
                  count = 0;
               }

               lineCount.put(symbolInRow, count + 1);
            }
         }

         Integer highestLineCount = null;

         for(Map.Entry e : lineCount.entrySet()) {
            if (highestLineCount == null || highestLineCount < (Integer)e.getValue()) {
               highestLineCount = (Integer)e.getValue();
            }
         }

         Character bestCandidate = null;

         for(Map.Entry e : lineCount.entrySet()) {
            if (((Integer)e.getValue()).equals(highestLineCount)) {
               if (bestCandidate != null) {
                  bestCandidate = null;
                  break;
               }

               bestCandidate = (Character)e.getKey();
            }
         }

         if (bestCandidate != null) {
            toRemove.remove(bestCandidate);
         }
      }

      sums.keySet().removeAll(toRemove);
      if (this.allowedDelimiters.length > 0) {
         Set<Character> toRetain = new HashSet();

         for(char c : this.allowedDelimiters) {
            toRetain.add(c);
         }

         sums.keySet().retainAll(toRetain);
      }

      char delimiter = this.pickDelimiter(sums, totals);
      char quote;
      if (doubleQuoteCount == 0 && singleQuoteCount == 0) {
         quote = this.suggestedQuote;
      } else {
         quote = (char)(doubleQuoteCount >= singleQuoteCount ? 34 : 39);
      }

      escape.remove(delimiter);
      char quoteEscape = doubleQuoteCount == 0 && singleQuoteCount == 0 ? this.suggestedQuoteEscape : this.max(escape, totals, quote);
      this.apply(delimiter, quote, quoteEscape);
   }

   protected char pickDelimiter(Map sums, Map totals) {
      char delimiterMax = this.max(sums, totals, this.suggestedDelimiter);
      char delimiterMin = this.min(sums, totals, this.suggestedDelimiter);
      if (delimiterMin == ' ' || delimiterMax == ' ') {
         boolean hasOtherDelimiters = false;

         for(Map.Entry e : sums.entrySet()) {
            if ((Integer)e.getValue() == 0 && (Character)e.getKey() != ' ') {
               hasOtherDelimiters = true;
               break;
            }
         }

         if (hasOtherDelimiters) {
            totals.remove(' ');
            delimiterMax = this.max(sums, totals, this.suggestedDelimiter);
            delimiterMin = this.min(sums, totals, this.suggestedDelimiter);
         }
      }

      char delimiter;
      if (delimiterMax != delimiterMin) {
         if ((Integer)sums.get(delimiterMin) == 0 && (Integer)sums.get(delimiterMax) != 0) {
            delimiter = delimiterMin;
         } else {
            char[] arr$ = this.delimiterPreference;
            int len$ = arr$.length;
            int i$ = 0;

            while(true) {
               if (i$ >= len$) {
                  if ((Integer)totals.get(delimiterMin) > (Integer)totals.get(delimiterMax)) {
                     delimiter = delimiterMin;
                  } else {
                     delimiter = delimiterMax;
                  }
                  break;
               }

               char c = arr$[i$];
               if (c == delimiterMin) {
                  delimiter = delimiterMin;
                  break;
               }

               if (c == delimiterMax) {
                  delimiter = delimiterMax;
                  break;
               }

               ++i$;
            }
         }
      } else {
         delimiter = delimiterMax;
      }

      return delimiter;
   }

   protected void increment(Map map, char symbol) {
      this.increment(map, symbol, 1);
   }

   protected void increment(Map map, char symbol, int incrementSize) {
      Integer count = (Integer)map.get(symbol);
      if (count == null) {
         count = 0;
      }

      map.put(symbol, count + incrementSize);
   }

   protected char min(Map map, Map totals, char defaultChar) {
      return this.getChar(map, totals, defaultChar, true);
   }

   protected char max(Map map, Map totals, char defaultChar) {
      return this.getChar(map, totals, defaultChar, false);
   }

   protected char getChar(Map map, Map totals, char defaultChar, boolean min) {
      int val = min ? Integer.MAX_VALUE : Integer.MIN_VALUE;

      for(Map.Entry e : map.entrySet()) {
         int sum = (Integer)e.getValue();
         if (min && sum <= val || !min && sum >= val) {
            char newChar = (Character)e.getKey();
            if (val != sum) {
               val = sum;
               defaultChar = newChar;
            } else {
               Integer currentTotal = (Integer)totals.get(defaultChar);
               Integer newTotal = (Integer)totals.get(newChar);
               if (currentTotal != null && newTotal != null) {
                  if (currentTotal.equals(newTotal)) {
                     int defIndex = ArgumentUtils.indexOf(this.delimiterPreference, defaultChar, 0);
                     int newIndex = ArgumentUtils.indexOf(this.delimiterPreference, newChar, 0);
                     if (defIndex != -1 && newIndex != -1) {
                        defaultChar = defIndex < newIndex ? defaultChar : newChar;
                     }
                  } else if (min && newTotal > currentTotal || !min && newTotal > currentTotal) {
                     defaultChar = newChar;
                  }
               } else if (this.isSymbol(newChar)) {
                  defaultChar = newChar;
               }
            }
         }
      }

      return defaultChar;
   }

   protected boolean isSymbol(char ch) {
      return this.isAllowedDelimiter(ch) || ch != this.comment && !Character.isLetterOrDigit(ch) && (ch == '\t' || ch >= ' ');
   }

   protected boolean isAllowedDelimiter(char ch) {
      return Arrays.binarySearch(this.allowedDelimiters, ch) >= 0;
   }

   protected abstract void apply(char var1, char var2, char var3);
}
