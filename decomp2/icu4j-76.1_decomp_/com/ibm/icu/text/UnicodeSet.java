package com.ibm.icu.text;

import com.ibm.icu.impl.BMPSet;
import com.ibm.icu.impl.CharacterPropertiesImpl;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.RuleCharacterIterator;
import com.ibm.icu.impl.SortedSetRelation;
import com.ibm.icu.impl.StringRange;
import com.ibm.icu.impl.UCaseProps;
import com.ibm.icu.impl.UCharacterProperty;
import com.ibm.icu.impl.UPropertyAliases;
import com.ibm.icu.impl.UnicodeSetStringSpan;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.CharSequences;
import com.ibm.icu.lang.CharacterProperties;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.lang.UScript;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.OutputInt;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.VersionInfo;
import java.io.IOException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class UnicodeSet extends UnicodeFilter implements Iterable, Comparable, Freezable {
   private static final SortedSet EMPTY_STRINGS = Collections.unmodifiableSortedSet(new TreeSet());
   public static final UnicodeSet EMPTY = (new UnicodeSet()).freeze();
   public static final UnicodeSet ALL_CODE_POINTS = (new UnicodeSet(0, 1114111)).freeze();
   private static XSymbolTable XSYMBOL_TABLE = null;
   private static final int LOW = 0;
   private static final int HIGH = 1114112;
   private static final int INITIAL_CAPACITY = 25;
   private static final int MAX_LENGTH = 1114113;
   public static final int MIN_VALUE = 0;
   public static final int MAX_VALUE = 1114111;
   private int len;
   private int[] list;
   private int[] rangeList;
   private int[] buffer;
   SortedSet strings;
   private String pat;
   private static final String ANY_ID = "ANY";
   private static final String ASCII_ID = "ASCII";
   private static final String ASSIGNED = "Assigned";
   private volatile BMPSet bmpSet;
   private volatile UnicodeSetStringSpan stringSpan;
   private static final int LAST0_START = 0;
   private static final int LAST1_RANGE = 1;
   private static final int LAST2_SET = 2;
   private static final int MODE0_NONE = 0;
   private static final int MODE1_INBRACKET = 1;
   private static final int MODE2_OUTBRACKET = 2;
   private static final int SETMODE0_NONE = 0;
   private static final int SETMODE1_UNICODESET = 1;
   private static final int SETMODE2_PROPERTYPAT = 2;
   private static final int SETMODE3_PREPARSED = 3;
   private static final int MAX_DEPTH = 100;
   private static final VersionInfo NO_VERSION = VersionInfo.getInstance(0, 0, 0, 0);
   public static final int IGNORE_SPACE = 1;
   /** @deprecated */
   @Deprecated
   public static final int CASE = 2;
   public static final int CASE_INSENSITIVE = 2;
   public static final int ADD_CASE_MAPPINGS = 4;
   public static final int SIMPLE_CASE_INSENSITIVE = 6;
   private static final int CASE_MASK = 6;

   public UnicodeSet() {
      this.strings = EMPTY_STRINGS;
      this.pat = null;
      this.list = new int[25];
      this.list[0] = 1114112;
      this.len = 1;
   }

   public UnicodeSet(UnicodeSet other) {
      this.strings = EMPTY_STRINGS;
      this.pat = null;
      this.set(other);
   }

   public UnicodeSet(int start, int end) {
      this();
      this.add(start, end);
   }

   public UnicodeSet(int... pairs) {
      this.strings = EMPTY_STRINGS;
      this.pat = null;
      if ((pairs.length & 1) != 0) {
         throw new IllegalArgumentException("Must have even number of integers");
      } else {
         this.list = new int[pairs.length + 1];
         this.len = this.list.length;
         int last = -1;

         int i;
         int limit;
         int var7;
         for(i = 0; i < pairs.length; this.list[var7] = limit) {
            int start = pairs[i];
            if (last >= start) {
               throw new IllegalArgumentException("Must be monotonically increasing.");
            }

            this.list[i++] = start;
            limit = pairs[i] + 1;
            if (start >= limit) {
               throw new IllegalArgumentException("Must be monotonically increasing.");
            }

            var7 = i++;
            last = limit;
         }

         this.list[i] = 1114112;
      }
   }

   public UnicodeSet(String pattern) {
      this();
      this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, 1);
   }

   public UnicodeSet(String pattern, boolean ignoreWhitespace) {
      this();
      this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, ignoreWhitespace ? 1 : 0);
   }

   public UnicodeSet(String pattern, int options) {
      this();
      this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, options);
   }

   public UnicodeSet(String pattern, ParsePosition pos, SymbolTable symbols) {
      this();
      this.applyPattern(pattern, pos, symbols, 1);
   }

   public UnicodeSet(String pattern, ParsePosition pos, SymbolTable symbols, int options) {
      this();
      this.applyPattern(pattern, pos, symbols, options);
   }

   public Object clone() {
      return this.isFrozen() ? this : new UnicodeSet(this);
   }

   public UnicodeSet set(int start, int end) {
      this.checkFrozen();
      this.clear();
      this.complement(start, end);
      return this;
   }

   public UnicodeSet set(UnicodeSet other) {
      this.checkFrozen();
      this.list = Arrays.copyOf(other.list, other.len);
      this.len = other.len;
      this.pat = other.pat;
      if (other.hasStrings()) {
         this.strings = new TreeSet(other.strings);
      } else {
         this.strings = EMPTY_STRINGS;
      }

      return this;
   }

   public final UnicodeSet applyPattern(String pattern) {
      this.checkFrozen();
      return this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, 1);
   }

   public UnicodeSet applyPattern(String pattern, boolean ignoreWhitespace) {
      this.checkFrozen();
      return this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, ignoreWhitespace ? 1 : 0);
   }

   public UnicodeSet applyPattern(String pattern, int options) {
      this.checkFrozen();
      return this.applyPattern(pattern, (ParsePosition)null, (SymbolTable)null, options);
   }

   public static boolean resemblesPattern(String pattern, int pos) {
      return pos + 1 < pattern.length() && pattern.charAt(pos) == '[' || resemblesPropertyPattern(pattern, pos);
   }

   private static void appendCodePoint(Appendable app, int c) {
      assert 0 <= c && c <= 1114111;

      try {
         if (c <= 65535) {
            app.append((char)c);
         } else {
            app.append(UTF16.getLeadSurrogate(c)).append(UTF16.getTrailSurrogate(c));
         }

      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   private static void append(Appendable app, CharSequence s) {
      try {
         app.append(s);
      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   private static Appendable _appendToPat(Appendable buf, String s, boolean escapeUnprintable) {
      int cp;
      for(int i = 0; i < s.length(); i += Character.charCount(cp)) {
         cp = s.codePointAt(i);
         _appendToPat(buf, cp, escapeUnprintable);
      }

      return buf;
   }

   private static Appendable _appendToPat(Appendable buf, int c, boolean escapeUnprintable) {
      try {
         if (escapeUnprintable) {
            if (Utility.isUnprintable(c)) {
               return Utility.escape(buf, c);
            }
         } else if (Utility.shouldAlwaysBeEscaped(c)) {
            return Utility.escape(buf, c);
         }

         switch (c) {
            case 36:
            case 38:
            case 45:
            case 58:
            case 91:
            case 92:
            case 93:
            case 94:
            case 123:
            case 125:
               buf.append('\\');
               break;
            default:
               if (PatternProps.isWhiteSpace(c)) {
                  buf.append('\\');
               }
         }

         appendCodePoint(buf, c);
         return buf;
      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   private static Appendable _appendToPat(Appendable result, int start, int end, boolean escapeUnprintable) {
      _appendToPat(result, start, escapeUnprintable);
      if (start != end) {
         if (start + 1 != end || start == 56319) {
            try {
               result.append('-');
            } catch (IOException e) {
               throw new ICUUncheckedIOException(e);
            }
         }

         _appendToPat(result, end, escapeUnprintable);
      }

      return result;
   }

   public String toPattern(boolean escapeUnprintable) {
      if (this.pat != null && !escapeUnprintable) {
         return this.pat;
      } else {
         StringBuilder result = new StringBuilder();
         return ((StringBuilder)this._toPattern(result, escapeUnprintable)).toString();
      }
   }

   private Appendable _toPattern(Appendable result, boolean escapeUnprintable) {
      if (this.pat == null) {
         return this.appendNewPattern(result, escapeUnprintable, true);
      } else {
         try {
            if (!escapeUnprintable) {
               result.append(this.pat);
               return result;
            } else {
               boolean oddNumberOfBackslashes = false;
               int i = 0;

               while(i < this.pat.length()) {
                  int c = this.pat.codePointAt(i);
                  i += Character.charCount(c);
                  if (Utility.isUnprintable(c)) {
                     Utility.escape(result, c);
                     oddNumberOfBackslashes = false;
                  } else if (!oddNumberOfBackslashes && c == 92) {
                     oddNumberOfBackslashes = true;
                  } else {
                     if (oddNumberOfBackslashes) {
                        result.append('\\');
                     }

                     appendCodePoint(result, c);
                     oddNumberOfBackslashes = false;
                  }
               }

               if (oddNumberOfBackslashes) {
                  result.append('\\');
               }

               return result;
            }
         } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
         }
      }
   }

   public StringBuffer _generatePattern(StringBuffer result, boolean escapeUnprintable) {
      return this._generatePattern(result, escapeUnprintable, true);
   }

   public StringBuffer _generatePattern(StringBuffer result, boolean escapeUnprintable, boolean includeStrings) {
      return (StringBuffer)this.appendNewPattern(result, escapeUnprintable, includeStrings);
   }

   private Appendable appendNewPattern(Appendable result, boolean escapeUnprintable, boolean includeStrings) {
      try {
         result.append('[');
         int i = 0;
         int limit = this.len & -2;
         if (this.len >= 4 && this.list[0] == 0 && limit == this.len && !this.hasStrings()) {
            result.append('^');
            i = 1;
            --limit;
         }

         while(i < limit) {
            int start = this.list[i];
            int end = this.list[i + 1] - 1;
            if (55296 <= end && end <= 56319) {
               int firstLead = i;

               do {
                  i += 2;
               } while(i < limit && this.list[i] <= 56319);

               int firstAfterLead;
               for(firstAfterLead = i; i < limit && (start = this.list[i]) <= 57343; i += 2) {
                  _appendToPat(result, start, this.list[i + 1] - 1, escapeUnprintable);
               }

               for(int j = firstLead; j < firstAfterLead; j += 2) {
                  _appendToPat(result, this.list[j], this.list[j + 1] - 1, escapeUnprintable);
               }
            } else {
               _appendToPat(result, start, end, escapeUnprintable);
               i += 2;
            }
         }

         if (includeStrings && this.hasStrings()) {
            for(String s : this.strings) {
               result.append('{');
               _appendToPat(result, s, escapeUnprintable);
               result.append('}');
            }
         }

         result.append(']');
         return result;
      } catch (IOException e) {
         throw new ICUUncheckedIOException(e);
      }
   }

   public int size() {
      int n = 0;
      int count = this.getRangeCount();

      for(int i = 0; i < count; ++i) {
         n += this.getRangeEnd(i) - this.getRangeStart(i) + 1;
      }

      return n + this.strings.size();
   }

   public boolean isEmpty() {
      return this.len == 1 && !this.hasStrings();
   }

   public boolean hasStrings() {
      return !this.strings.isEmpty();
   }

   public boolean matchesIndexValue(int v) {
      for(int i = 0; i < this.getRangeCount(); ++i) {
         int low = this.getRangeStart(i);
         int high = this.getRangeEnd(i);
         if ((low & -256) == (high & -256)) {
            if ((low & 255) <= v && v <= (high & 255)) {
               return true;
            }
         } else if ((low & 255) <= v || v <= (high & 255)) {
            return true;
         }
      }

      if (this.hasStrings()) {
         for(String s : this.strings) {
            if (!s.isEmpty()) {
               int c = UTF16.charAt((String)s, 0);
               if ((c & 255) == v) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
      if (offset[0] == limit) {
         if (this.contains(65535)) {
            return incremental ? 1 : 2;
         } else {
            return 0;
         }
      } else {
         if (this.hasStrings()) {
            boolean forward = offset[0] < limit;
            char firstChar = text.charAt(offset[0]);
            int highWaterLength = 0;

            for(String trial : this.strings) {
               if (!trial.isEmpty()) {
                  char c = trial.charAt(forward ? 0 : trial.length() - 1);
                  if (forward && c > firstChar) {
                     break;
                  }

                  if (c == firstChar) {
                     int length = matchRest(text, offset[0], limit, trial);
                     if (incremental) {
                        int maxLen = forward ? limit - offset[0] : offset[0] - limit;
                        if (length == maxLen) {
                           return 1;
                        }
                     }

                     if (length == trial.length()) {
                        if (length > highWaterLength) {
                           highWaterLength = length;
                        }

                        if (forward && length < highWaterLength) {
                           break;
                        }
                     }
                  }
               }
            }

            if (highWaterLength != 0) {
               offset[0] += forward ? highWaterLength : -highWaterLength;
               return 2;
            }
         }

         return super.matches(text, offset, limit, incremental);
      }
   }

   private static int matchRest(Replaceable text, int start, int limit, String s) {
      int slen = s.length();
      int maxLen;
      if (start < limit) {
         maxLen = limit - start;
         if (maxLen > slen) {
            maxLen = slen;
         }

         for(int i = 1; i < maxLen; ++i) {
            if (text.charAt(start + i) != s.charAt(i)) {
               return 0;
            }
         }
      } else {
         maxLen = start - limit;
         if (maxLen > slen) {
            maxLen = slen;
         }

         --slen;

         for(int i = 1; i < maxLen; ++i) {
            if (text.charAt(start - i) != s.charAt(slen - i)) {
               return 0;
            }
         }
      }

      return maxLen;
   }

   /** @deprecated */
   @Deprecated
   public int matchesAt(CharSequence text, int offset) {
      int lastLen = -1;
      if (this.hasStrings()) {
         char firstChar = text.charAt(offset);
         String trial = null;
         Iterator<String> it = this.strings.iterator();

         char firstStringChar;
         label34:
         do {
            if (!it.hasNext()) {
               while(true) {
                  firstStringChar = (char)matchesAt(text, offset, trial);
                  if (lastLen > firstStringChar) {
                     break label34;
                  }

                  lastLen = firstStringChar;
                  if (!it.hasNext()) {
                     break label34;
                  }

                  trial = (String)it.next();
               }
            }

            trial = (String)it.next();
            firstStringChar = trial.charAt(0);
         } while(firstStringChar < firstChar || firstStringChar <= firstChar);
      }

      if (lastLen < 2) {
         int cp = UTF16.charAt(text, offset);
         if (this.contains(cp)) {
            lastLen = UTF16.getCharCount(cp);
         }
      }

      return offset + lastLen;
   }

   private static int matchesAt(CharSequence text, int offsetInText, CharSequence substring) {
      int len = substring.length();
      int textLength = text.length();
      if (textLength + offsetInText > len) {
         return -1;
      } else {
         int i = 0;

         for(int j = offsetInText; i < len; ++j) {
            char pc = substring.charAt(i);
            char tc = text.charAt(j);
            if (pc != tc) {
               return -1;
            }

            ++i;
         }

         return i;
      }
   }

   public void addMatchSetTo(UnicodeSet toUnionTo) {
      toUnionTo.addAll(this);
   }

   public int indexOf(int c) {
      if (c >= 0 && c <= 1114111) {
         int i = 0;
         int n = 0;

         while(true) {
            int start = this.list[i++];
            if (c < start) {
               return -1;
            }

            int limit = this.list[i++];
            if (c < limit) {
               return n + c - start;
            }

            n += limit - start;
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)c, 6));
      }
   }

   public int charAt(int index) {
      if (index >= 0) {
         int len2 = this.len & -2;

         int count;
         for(int i = 0; i < len2; index -= count) {
            int start = this.list[i++];
            count = this.list[i++] - start;
            if (index < count) {
               return start + index;
            }
         }
      }

      return -1;
   }

   public UnicodeSet add(int start, int end) {
      this.checkFrozen();
      return this.add_unchecked(start, end);
   }

   public UnicodeSet addAll(int start, int end) {
      this.checkFrozen();
      return this.add_unchecked(start, end);
   }

   private UnicodeSet add_unchecked(int start, int end) {
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            if (start < end) {
               int limit = end + 1;
               if ((this.len & 1) != 0) {
                  int lastLimit = this.len == 1 ? -2 : this.list[this.len - 2];
                  if (lastLimit <= start) {
                     this.checkFrozen();
                     if (lastLimit == start) {
                        this.list[this.len - 2] = limit;
                        if (limit == 1114112) {
                           --this.len;
                        }
                     } else {
                        this.list[this.len - 1] = start;
                        if (limit < 1114112) {
                           this.ensureCapacity(this.len + 2);
                           this.list[this.len++] = limit;
                           this.list[this.len++] = 1114112;
                        } else {
                           this.ensureCapacity(this.len + 1);
                           this.list[this.len++] = 1114112;
                        }
                     }

                     this.pat = null;
                     return this;
                  }
               }

               this.add(this.range(start, end), 2, 0);
            } else if (start == end) {
               this.add(start);
            }

            return this;
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public final UnicodeSet add(int c) {
      this.checkFrozen();
      return this.add_unchecked(c);
   }

   private final UnicodeSet add_unchecked(int c) {
      if (c >= 0 && c <= 1114111) {
         int i = this.findCodePoint(c);
         if ((i & 1) != 0) {
            return this;
         } else {
            if (c == this.list[i] - 1) {
               this.list[i] = c;
               if (c == 1114111) {
                  this.ensureCapacity(this.len + 1);
                  this.list[this.len++] = 1114112;
               }

               if (i > 0 && c == this.list[i - 1]) {
                  System.arraycopy(this.list, i + 1, this.list, i - 1, this.len - i - 1);
                  this.len -= 2;
               }
            } else if (i > 0 && c == this.list[i - 1]) {
               int var10002 = this.list[i - 1]++;
            } else {
               if (this.len + 2 > this.list.length) {
                  int[] temp = new int[this.nextCapacity(this.len + 2)];
                  if (i != 0) {
                     System.arraycopy(this.list, 0, temp, 0, i);
                  }

                  System.arraycopy(this.list, i, temp, i + 2, this.len - i);
                  this.list = temp;
               } else {
                  System.arraycopy(this.list, i, this.list, i + 2, this.len - i);
               }

               this.list[i] = c;
               this.list[i + 1] = c + 1;
               this.len += 2;
            }

            this.pat = null;
            return this;
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)c, 6));
      }
   }

   public final UnicodeSet add(CharSequence s) {
      this.checkFrozen();
      int cp = getSingleCP(s);
      if (cp < 0) {
         String str = s.toString();
         if (!this.strings.contains(str)) {
            this.addString(str);
            this.pat = null;
         }
      } else {
         this.add_unchecked(cp, cp);
      }

      return this;
   }

   private void addString(CharSequence s) {
      if (this.strings == EMPTY_STRINGS) {
         this.strings = new TreeSet();
      }

      this.strings.add(s.toString());
   }

   private static int getSingleCP(CharSequence s) {
      if (s.length() == 1) {
         return s.charAt(0);
      } else {
         if (s.length() == 2) {
            int cp = Character.codePointAt(s, 0);
            if (cp > 65535) {
               return cp;
            }
         }

         return -1;
      }
   }

   public final UnicodeSet addAll(CharSequence s) {
      this.checkFrozen();

      int cp;
      for(int i = 0; i < s.length(); i += UTF16.getCharCount(cp)) {
         cp = UTF16.charAt(s, i);
         this.add_unchecked(cp, cp);
      }

      return this;
   }

   public final UnicodeSet retainAll(CharSequence s) {
      return this.retainAll(fromAll(s));
   }

   public final UnicodeSet complementAll(CharSequence s) {
      return this.complementAll(fromAll(s));
   }

   public final UnicodeSet removeAll(CharSequence s) {
      return this.removeAll(fromAll(s));
   }

   public final UnicodeSet removeAllStrings() {
      this.checkFrozen();
      if (this.hasStrings()) {
         this.strings.clear();
         this.pat = null;
      }

      return this;
   }

   public static UnicodeSet from(CharSequence s) {
      return (new UnicodeSet()).add(s);
   }

   public static UnicodeSet fromAll(CharSequence s) {
      return (new UnicodeSet()).addAll(s);
   }

   public UnicodeSet retain(int start, int end) {
      this.checkFrozen();
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            if (start <= end) {
               this.retain(this.range(start, end), 2, 0);
            } else {
               this.clear();
            }

            return this;
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public final UnicodeSet retain(int c) {
      return this.retain(c, c);
   }

   public final UnicodeSet retain(CharSequence cs) {
      int cp = getSingleCP(cs);
      if (cp < 0) {
         this.checkFrozen();
         String s = cs.toString();
         boolean isIn = this.strings.contains(s);
         if (isIn && this.getRangeCount() == 0 && this.size() == 1) {
            return this;
         }

         this.clear();
         if (isIn) {
            this.addString(s);
         }

         this.pat = null;
      } else {
         this.retain(cp, cp);
      }

      return this;
   }

   public UnicodeSet remove(int start, int end) {
      this.checkFrozen();
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            if (start <= end) {
               this.retain(this.range(start, end), 2, 2);
            }

            return this;
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public final UnicodeSet remove(int c) {
      return this.remove(c, c);
   }

   public final UnicodeSet remove(CharSequence s) {
      int cp = getSingleCP(s);
      if (cp < 0) {
         this.checkFrozen();
         String str = s.toString();
         if (this.strings.contains(str)) {
            this.strings.remove(str);
            this.pat = null;
         }
      } else {
         this.remove(cp, cp);
      }

      return this;
   }

   public UnicodeSet complement(int start, int end) {
      this.checkFrozen();
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            if (start <= end) {
               this.xor(this.range(start, end), 2, 0);
            }

            this.pat = null;
            return this;
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public final UnicodeSet complement(int c) {
      return this.complement(c, c);
   }

   public UnicodeSet complement() {
      this.checkFrozen();
      if (this.list[0] == 0) {
         System.arraycopy(this.list, 1, this.list, 0, this.len - 1);
         --this.len;
      } else {
         this.ensureCapacity(this.len + 1);
         System.arraycopy(this.list, 0, this.list, 1, this.len);
         this.list[0] = 0;
         ++this.len;
      }

      this.pat = null;
      return this;
   }

   public final UnicodeSet complement(CharSequence s) {
      this.checkFrozen();
      int cp = getSingleCP(s);
      if (cp < 0) {
         String s2 = s.toString();
         if (this.strings.contains(s2)) {
            this.strings.remove(s2);
         } else {
            this.addString(s2);
         }

         this.pat = null;
      } else {
         this.complement(cp, cp);
      }

      return this;
   }

   public boolean contains(int c) {
      if (c >= 0 && c <= 1114111) {
         if (this.bmpSet != null) {
            return this.bmpSet.contains(c);
         } else if (this.stringSpan != null) {
            return this.stringSpan.contains(c);
         } else {
            int i = this.findCodePoint(c);
            return (i & 1) != 0;
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)c, 6));
      }
   }

   private final int findCodePoint(int c) {
      if (c < this.list[0]) {
         return 0;
      } else if (this.len >= 2 && c >= this.list[this.len - 2]) {
         return this.len - 1;
      } else {
         int lo = 0;
         int hi = this.len - 1;

         while(true) {
            int i = lo + hi >>> 1;
            if (i == lo) {
               return hi;
            }

            if (c < this.list[i]) {
               hi = i;
            } else {
               lo = i;
            }
         }
      }
   }

   public boolean contains(int start, int end) {
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            int i = this.findCodePoint(start);
            return (i & 1) != 0 && end < this.list[i];
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public final boolean contains(CharSequence s) {
      int cp = getSingleCP(s);
      return cp < 0 ? this.strings.contains(s.toString()) : this.contains(cp);
   }

   public boolean containsAll(UnicodeSet b) {
      int[] listB = b.list;
      boolean needA = true;
      boolean needB = true;
      int aPtr = 0;
      int bPtr = 0;
      int aLen = this.len - 1;
      int bLen = b.len - 1;
      int startA = 0;
      int startB = 0;
      int limitA = 0;
      int limitB = 0;

      while(true) {
         if (needA) {
            if (aPtr >= aLen) {
               if (!needB || bPtr < bLen) {
                  return false;
               }
               break;
            }

            startA = this.list[aPtr++];
            limitA = this.list[aPtr++];
         }

         if (needB) {
            if (bPtr >= bLen) {
               break;
            }

            startB = listB[bPtr++];
            limitB = listB[bPtr++];
         }

         if (startB >= limitA) {
            needA = true;
            needB = false;
         } else {
            if (startB < startA || limitB > limitA) {
               return false;
            }

            needA = false;
            needB = true;
         }
      }

      if (!this.strings.containsAll(b.strings)) {
         return false;
      } else {
         return true;
      }
   }

   public boolean containsAll(String s) {
      int cp;
      for(int i = 0; i < s.length(); i += UTF16.getCharCount(cp)) {
         cp = UTF16.charAt(s, i);
         if (!this.contains(cp)) {
            if (!this.hasStrings()) {
               return false;
            }

            return this.containsAll(s, 0);
         }
      }

      return true;
   }

   private boolean containsAll(String s, int i) {
      if (i >= s.length()) {
         return true;
      } else {
         int cp = UTF16.charAt(s, i);
         if (this.contains(cp) && this.containsAll(s, i + UTF16.getCharCount(cp))) {
            return true;
         } else {
            for(String setStr : this.strings) {
               if (!setStr.isEmpty() && s.startsWith(setStr, i) && this.containsAll(s, i + setStr.length())) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public String getRegexEquivalent() {
      if (!this.hasStrings()) {
         return this.toString();
      } else {
         StringBuilder result = new StringBuilder("(?:");
         this.appendNewPattern(result, true, false);

         for(String s : this.strings) {
            result.append('|');
            _appendToPat(result, s, true);
         }

         return result.append(")").toString();
      }
   }

   public boolean containsNone(int start, int end) {
      if (start >= 0 && start <= 1114111) {
         if (end >= 0 && end <= 1114111) {
            int i = -1;

            do {
               ++i;
            } while(start >= this.list[i]);

            return (i & 1) == 0 && end < this.list[i];
         } else {
            throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)end, 6));
         }
      } else {
         throw new IllegalArgumentException("Invalid code point U+" + Utility.hex((long)start, 6));
      }
   }

   public boolean containsNone(UnicodeSet b) {
      int[] listB = b.list;
      boolean needA = true;
      boolean needB = true;
      int aPtr = 0;
      int bPtr = 0;
      int aLen = this.len - 1;
      int bLen = b.len - 1;
      int startA = 0;
      int startB = 0;
      int limitA = 0;
      int limitB = 0;

      while(true) {
         if (needA) {
            if (aPtr >= aLen) {
               break;
            }

            startA = this.list[aPtr++];
            limitA = this.list[aPtr++];
         }

         if (needB) {
            if (bPtr >= bLen) {
               break;
            }

            startB = listB[bPtr++];
            limitB = listB[bPtr++];
         }

         if (startB >= limitA) {
            needA = true;
            needB = false;
         } else {
            if (startA < limitB) {
               return false;
            }

            needA = false;
            needB = true;
         }
      }

      return SortedSetRelation.hasRelation(this.strings, 5, b.strings);
   }

   public boolean containsNone(CharSequence s) {
      return this.span(s, UnicodeSet.SpanCondition.NOT_CONTAINED) == s.length();
   }

   public final boolean containsSome(int start, int end) {
      return !this.containsNone(start, end);
   }

   public final boolean containsSome(UnicodeSet s) {
      return !this.containsNone(s);
   }

   public final boolean containsSome(CharSequence s) {
      return !this.containsNone(s);
   }

   public UnicodeSet addAll(UnicodeSet c) {
      this.checkFrozen();
      this.add(c.list, c.len, 0);
      if (c.hasStrings()) {
         if (this.strings == EMPTY_STRINGS) {
            this.strings = new TreeSet(c.strings);
         } else {
            this.strings.addAll(c.strings);
         }
      }

      return this;
   }

   public UnicodeSet retainAll(UnicodeSet c) {
      this.checkFrozen();
      this.retain(c.list, c.len, 0);
      if (this.hasStrings()) {
         if (!c.hasStrings()) {
            this.strings.clear();
         } else {
            this.strings.retainAll(c.strings);
         }
      }

      return this;
   }

   public UnicodeSet removeAll(UnicodeSet c) {
      this.checkFrozen();
      this.retain(c.list, c.len, 2);
      if (this.hasStrings() && c.hasStrings()) {
         this.strings.removeAll(c.strings);
      }

      return this;
   }

   public UnicodeSet complementAll(UnicodeSet c) {
      this.checkFrozen();
      this.xor(c.list, c.len, 0);
      if (c.hasStrings()) {
         if (this.strings == EMPTY_STRINGS) {
            this.strings = new TreeSet(c.strings);
         } else {
            SortedSetRelation.doOperation(this.strings, 5, c.strings);
         }
      }

      return this;
   }

   public UnicodeSet clear() {
      this.checkFrozen();
      this.list[0] = 1114112;
      this.len = 1;
      this.pat = null;
      if (this.hasStrings()) {
         this.strings.clear();
      }

      return this;
   }

   public int getRangeCount() {
      return this.len / 2;
   }

   public int getRangeStart(int index) {
      return this.list[index * 2];
   }

   public int getRangeEnd(int index) {
      return this.list[index * 2 + 1] - 1;
   }

   public UnicodeSet compact() {
      this.checkFrozen();
      if (this.len + 7 < this.list.length) {
         this.list = Arrays.copyOf(this.list, this.len);
      }

      this.rangeList = null;
      this.buffer = null;
      if (this.strings != EMPTY_STRINGS && this.strings.isEmpty()) {
         this.strings = EMPTY_STRINGS;
      }

      return this;
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (this == o) {
         return true;
      } else {
         try {
            UnicodeSet that = (UnicodeSet)o;
            if (this.len != that.len) {
               return false;
            } else {
               for(int i = 0; i < this.len; ++i) {
                  if (this.list[i] != that.list[i]) {
                     return false;
                  }
               }

               if (!this.strings.equals(that.strings)) {
                  return false;
               } else {
                  return true;
               }
            }
         } catch (Exception var4) {
            return false;
         }
      }
   }

   public int hashCode() {
      int result = this.len;

      for(int i = 0; i < this.len; ++i) {
         result *= 1000003;
         result += this.list[i];
      }

      return result;
   }

   public String toString() {
      return this.toPattern(true);
   }

   /** @deprecated */
   @Deprecated
   public UnicodeSet applyPattern(String pattern, ParsePosition pos, SymbolTable symbols, int options) {
      boolean parsePositionWasNull = pos == null;
      if (parsePositionWasNull) {
         pos = new ParsePosition(0);
      }

      StringBuilder rebuiltPat = new StringBuilder();
      RuleCharacterIterator chars = new RuleCharacterIterator(pattern, symbols, pos);
      this.applyPattern(chars, symbols, rebuiltPat, options, 0);
      if (chars.inVariable()) {
         syntaxError(chars, "Extra chars in variable value");
      }

      this.pat = rebuiltPat.toString();
      if (parsePositionWasNull) {
         int i = pos.getIndex();
         if ((options & 1) != 0) {
            i = PatternProps.skipWhiteSpace(pattern, i);
         }

         if (i != pattern.length()) {
            throw new IllegalArgumentException("Parse of \"" + pattern + "\" failed at " + i);
         }
      }

      return this;
   }

   private void applyPattern(RuleCharacterIterator chars, SymbolTable symbols, Appendable rebuiltPat, int options, int depth) {
      if (depth > 100) {
         syntaxError(chars, "Pattern nested too deeply");
      }

      int opts = 3;
      if ((options & 1) != 0) {
         opts |= 4;
      }

      StringBuilder patBuf = new StringBuilder();
      StringBuilder buf = null;
      boolean usePat = false;
      UnicodeSet scratch = null;
      RuleCharacterIterator.Position backup = null;
      int lastItem = 0;
      int lastChar = 0;
      int mode = 0;
      char op = 0;
      boolean invert = false;
      this.clear();
      String lastString = null;

      while(mode != 2 && !chars.atEnd()) {
         int c = 0;
         boolean literal = false;
         UnicodeSet nested = null;
         int setMode = 0;
         if (resemblesPropertyPattern(chars, opts)) {
            setMode = 2;
         } else {
            backup = chars.getPos(backup);
            c = chars.next(opts);
            literal = chars.isEscaped();
            if (c == 91 && !literal) {
               if (mode == 1) {
                  chars.setPos(backup);
                  setMode = 1;
               } else {
                  mode = 1;
                  patBuf.append('[');
                  backup = chars.getPos(backup);
                  c = chars.next(opts);
                  literal = chars.isEscaped();
                  if (c == 94 && !literal) {
                     invert = true;
                     patBuf.append('^');
                     backup = chars.getPos(backup);
                     c = chars.next(opts);
                     literal = chars.isEscaped();
                  }

                  if (c != 45) {
                     chars.setPos(backup);
                     continue;
                  }

                  literal = true;
               }
            } else if (symbols != null) {
               UnicodeMatcher m = symbols.lookupMatcher(c);
               if (m != null) {
                  try {
                     nested = (UnicodeSet)m;
                     setMode = 3;
                  } catch (ClassCastException var28) {
                     syntaxError(chars, "Syntax error");
                  }
               }
            }
         }

         if (setMode != 0) {
            if (lastItem == 1) {
               if (op != 0) {
                  syntaxError(chars, "Char expected after operator");
               }

               this.add_unchecked(lastChar, lastChar);
               _appendToPat(patBuf, lastChar, false);
               lastItem = 0;
               op = 0;
            }

            if (op == '-' || op == '&') {
               patBuf.append(op);
            }

            if (nested == null) {
               if (scratch == null) {
                  scratch = new UnicodeSet();
               }

               nested = scratch;
            }

            switch (setMode) {
               case 1:
                  nested.applyPattern(chars, symbols, patBuf, options, depth + 1);
                  break;
               case 2:
                  chars.skipIgnored(opts);
                  nested.applyPropertyPattern((RuleCharacterIterator)chars, (Appendable)patBuf, symbols);
                  break;
               case 3:
                  nested._toPattern(patBuf, false);
            }

            usePat = true;
            if (mode == 0) {
               this.set(nested);
               mode = 2;
               break;
            }

            switch (op) {
               case '\u0000':
                  this.addAll(nested);
                  break;
               case '&':
                  this.retainAll(nested);
                  break;
               case '-':
                  this.removeAll(nested);
            }

            op = 0;
            lastItem = 2;
         } else {
            if (mode == 0) {
               syntaxError(chars, "Missing '['");
            }

            if (!literal) {
               switch (c) {
                  case 36:
                     backup = chars.getPos(backup);
                     c = chars.next(opts);
                     literal = chars.isEscaped();
                     boolean anchor = c == 93 && !literal;
                     if (symbols == null && !anchor) {
                        c = 36;
                        chars.setPos(backup);
                     } else {
                        if (anchor && op == 0) {
                           if (lastItem == 1) {
                              this.add_unchecked(lastChar, lastChar);
                              _appendToPat(patBuf, lastChar, false);
                           }

                           this.add_unchecked(65535);
                           usePat = true;
                           patBuf.append('$').append(']');
                           mode = 2;
                           continue;
                        }

                        syntaxError(chars, "Unquoted '$'");
                     }
                     break;
                  case 38:
                     if (lastItem == 2 && op == 0) {
                        op = (char)c;
                        continue;
                     }

                     syntaxError(chars, "'&' not after set");
                     break;
                  case 45:
                     if (op == 0) {
                        if (lastItem != 0) {
                           op = (char)c;
                           continue;
                        }

                        if (lastString != null) {
                           op = (char)c;
                           continue;
                        }

                        this.add_unchecked(c, c);
                        c = chars.next(opts);
                        literal = chars.isEscaped();
                        if (c == 93 && !literal) {
                           patBuf.append("-]");
                           mode = 2;
                           continue;
                        }
                     }

                     syntaxError(chars, "'-' not after char, string, or set");
                     break;
                  case 93:
                     if (lastItem == 1) {
                        this.add_unchecked(lastChar, lastChar);
                        _appendToPat(patBuf, lastChar, false);
                     }

                     if (op == '-') {
                        this.add_unchecked(op, op);
                        patBuf.append(op);
                     } else if (op == '&') {
                        syntaxError(chars, "Trailing '&'");
                     }

                     patBuf.append(']');
                     mode = 2;
                     continue;
                  case 94:
                     syntaxError(chars, "'^' not after '['");
                     break;
                  case 123:
                     if (op != 0 && op != '-') {
                        syntaxError(chars, "Missing operand after operator");
                     }

                     if (lastItem == 1) {
                        this.add_unchecked(lastChar, lastChar);
                        _appendToPat(patBuf, lastChar, false);
                     }

                     lastItem = 0;
                     if (buf == null) {
                        buf = new StringBuilder();
                     } else {
                        buf.setLength(0);
                     }

                     boolean ok = false;

                     while(!chars.atEnd()) {
                        c = chars.next(opts);
                        literal = chars.isEscaped();
                        if (c == 125 && !literal) {
                           ok = true;
                           break;
                        }

                        appendCodePoint(buf, c);
                     }

                     if (!ok) {
                        syntaxError(chars, "Invalid multicharacter string");
                     }

                     String curString = buf.toString();
                     if (op == '-') {
                        int lastSingle = CharSequences.getSingleCodePoint(lastString == null ? "" : lastString);
                        int curSingle = CharSequences.getSingleCodePoint(curString);
                        if (lastSingle != Integer.MAX_VALUE && curSingle != Integer.MAX_VALUE) {
                           this.add(lastSingle, curSingle);
                        } else {
                           if (this.strings == EMPTY_STRINGS) {
                              this.strings = new TreeSet();
                           }

                           try {
                              StringRange.expand(lastString, curString, true, this.strings);
                           } catch (Exception e) {
                              syntaxError(chars, e.getMessage());
                           }
                        }

                        lastString = null;
                        op = 0;
                     } else {
                        this.add((CharSequence)curString);
                        lastString = curString;
                     }

                     patBuf.append('{');
                     _appendToPat(patBuf, curString, false);
                     patBuf.append('}');
                     continue;
               }
            }

            switch (lastItem) {
               case 0:
                  if (op == '-' && lastString != null) {
                     syntaxError(chars, "Invalid range");
                  }

                  lastItem = 1;
                  lastChar = c;
                  lastString = null;
                  break;
               case 1:
                  if (op == '-') {
                     if (lastString != null) {
                        syntaxError(chars, "Invalid range");
                     }

                     if (lastChar >= c) {
                        syntaxError(chars, "Invalid range");
                     }

                     this.add_unchecked(lastChar, c);
                     _appendToPat(patBuf, lastChar, false);
                     patBuf.append(op);
                     _appendToPat(patBuf, c, false);
                     lastItem = 0;
                     op = 0;
                  } else {
                     this.add_unchecked(lastChar, lastChar);
                     _appendToPat(patBuf, lastChar, false);
                     lastChar = c;
                  }
                  break;
               case 2:
                  if (op != 0) {
                     syntaxError(chars, "Set expected after operator");
                  }

                  lastChar = c;
                  lastItem = 1;
            }
         }
      }

      if (mode != 2) {
         syntaxError(chars, "Missing ']'");
      }

      chars.skipIgnored(opts);
      if ((options & 6) != 0) {
         this.closeOver(options);
      }

      if (invert) {
         this.complement().removeAllStrings();
      }

      if (usePat) {
         append(rebuiltPat, patBuf.toString());
      } else {
         this.appendNewPattern(rebuiltPat, false, true);
      }

   }

   private static void syntaxError(RuleCharacterIterator chars, String msg) {
      throw new IllegalArgumentException("Error: " + msg + " at \"" + Utility.escape(chars.toString()) + '"');
   }

   public Collection addAllTo(Collection target) {
      return addAllTo(this, (Collection)target);
   }

   public String[] addAllTo(String[] target) {
      return (String[])addAllTo(this, (Object[])target);
   }

   public static String[] toArray(UnicodeSet set) {
      return (String[])addAllTo(set, (Object[])(new String[set.size()]));
   }

   public UnicodeSet add(Iterable source) {
      return this.addAll(source);
   }

   public UnicodeSet addAll(Iterable source) {
      this.checkFrozen();

      for(Object o : source) {
         this.add((CharSequence)o.toString());
      }

      return this;
   }

   private int nextCapacity(int minCapacity) {
      if (minCapacity < 25) {
         return minCapacity + 25;
      } else if (minCapacity <= 2500) {
         return 5 * minCapacity;
      } else {
         int newCapacity = 2 * minCapacity;
         if (newCapacity > 1114113) {
            newCapacity = 1114113;
         }

         return newCapacity;
      }
   }

   private void ensureCapacity(int newLen) {
      if (newLen > 1114113) {
         newLen = 1114113;
      }

      if (newLen > this.list.length) {
         int newCapacity = this.nextCapacity(newLen);
         int[] temp = new int[newCapacity];
         System.arraycopy(this.list, 0, temp, 0, this.len);
         this.list = temp;
      }
   }

   private void ensureBufferCapacity(int newLen) {
      if (newLen > 1114113) {
         newLen = 1114113;
      }

      if (this.buffer == null || newLen > this.buffer.length) {
         int newCapacity = this.nextCapacity(newLen);
         this.buffer = new int[newCapacity];
      }
   }

   private int[] range(int start, int end) {
      if (this.rangeList == null) {
         this.rangeList = new int[]{start, end + 1, 1114112};
      } else {
         this.rangeList[0] = start;
         this.rangeList[1] = end + 1;
      }

      return this.rangeList;
   }

   private UnicodeSet xor(int[] other, int otherLen, int polarity) {
      this.ensureBufferCapacity(this.len + otherLen);
      int i = 0;
      int j = 0;
      int k = 0;
      int a = this.list[i++];
      int b;
      if (polarity != 1 && polarity != 2) {
         b = other[j++];
      } else {
         b = 0;
         if (other[j] == 0) {
            ++j;
            b = other[j];
         }
      }

      while(true) {
         while(a >= b) {
            if (b < a) {
               this.buffer[k++] = b;
               b = other[j++];
            } else {
               if (a == 1114112) {
                  this.buffer[k++] = 1114112;
                  this.len = k;
                  int[] temp = this.list;
                  this.list = this.buffer;
                  this.buffer = temp;
                  this.pat = null;
                  return this;
               }

               a = this.list[i++];
               b = other[j++];
            }
         }

         this.buffer[k++] = a;
         a = this.list[i++];
      }
   }

   private UnicodeSet add(int[] other, int otherLen, int polarity) {
      this.ensureBufferCapacity(this.len + otherLen);
      int i = 0;
      int j = 0;
      int k = 0;
      int a = this.list[i++];
      int b = other[j++];

      label95:
      while(true) {
         switch (polarity) {
            case 0:
               if (a < b) {
                  if (k > 0 && a <= this.buffer[k - 1]) {
                     int var18 = this.list[i];
                     --k;
                     a = max(var18, this.buffer[k]);
                  } else {
                     this.buffer[k++] = a;
                     a = this.list[i];
                  }

                  ++i;
                  polarity ^= 1;
               } else if (b < a) {
                  if (k > 0 && b <= this.buffer[k - 1]) {
                     int var17 = other[j];
                     --k;
                     b = max(var17, this.buffer[k]);
                  } else {
                     this.buffer[k++] = b;
                     b = other[j];
                  }

                  ++j;
                  polarity ^= 2;
               } else {
                  if (a == 1114112) {
                     break label95;
                  }

                  if (k > 0 && a <= this.buffer[k - 1]) {
                     int var10000 = this.list[i];
                     --k;
                     a = max(var10000, this.buffer[k]);
                  } else {
                     this.buffer[k++] = a;
                     a = this.list[i];
                  }

                  ++i;
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 1:
               if (a < b) {
                  this.buffer[k++] = a;
                  a = this.list[i++];
                  polarity ^= 1;
               } else {
                  if (b < a) {
                     b = other[j++];
                     polarity ^= 2;
                     continue;
                  }

                  if (a == 1114112) {
                     break label95;
                  }

                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 2:
               if (b < a) {
                  this.buffer[k++] = b;
                  b = other[j++];
                  polarity ^= 2;
               } else {
                  if (a < b) {
                     a = this.list[i++];
                     polarity ^= 1;
                     continue;
                  }

                  if (a == 1114112) {
                     break label95;
                  }

                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 3:
               if (b <= a) {
                  if (a == 1114112) {
                     break label95;
                  }

                  this.buffer[k++] = a;
               } else {
                  if (b == 1114112) {
                     break label95;
                  }

                  this.buffer[k++] = b;
               }

               a = this.list[i++];
               polarity ^= 1;
               b = other[j++];
               polarity ^= 2;
         }
      }

      this.buffer[k++] = 1114112;
      this.len = k;
      int[] temp = this.list;
      this.list = this.buffer;
      this.buffer = temp;
      this.pat = null;
      return this;
   }

   private UnicodeSet retain(int[] other, int otherLen, int polarity) {
      this.ensureBufferCapacity(this.len + otherLen);
      int i = 0;
      int j = 0;
      int k = 0;
      int a = this.list[i++];
      int b = other[j++];

      label64:
      while(true) {
         switch (polarity) {
            case 0:
               if (a < b) {
                  a = this.list[i++];
                  polarity ^= 1;
               } else {
                  if (b < a) {
                     b = other[j++];
                     polarity ^= 2;
                     continue;
                  }

                  if (a == 1114112) {
                     break label64;
                  }

                  this.buffer[k++] = a;
                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 1:
               if (a < b) {
                  a = this.list[i++];
                  polarity ^= 1;
               } else {
                  if (b < a) {
                     this.buffer[k++] = b;
                     b = other[j++];
                     polarity ^= 2;
                     continue;
                  }

                  if (a == 1114112) {
                     break label64;
                  }

                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 2:
               if (b < a) {
                  b = other[j++];
                  polarity ^= 2;
               } else {
                  if (a < b) {
                     this.buffer[k++] = a;
                     a = this.list[i++];
                     polarity ^= 1;
                     continue;
                  }

                  if (a == 1114112) {
                     break label64;
                  }

                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
               break;
            case 3:
               if (a < b) {
                  this.buffer[k++] = a;
                  a = this.list[i++];
                  polarity ^= 1;
               } else if (b < a) {
                  this.buffer[k++] = b;
                  b = other[j++];
                  polarity ^= 2;
               } else {
                  if (a == 1114112) {
                     break label64;
                  }

                  this.buffer[k++] = a;
                  a = this.list[i++];
                  polarity ^= 1;
                  b = other[j++];
                  polarity ^= 2;
               }
         }
      }

      this.buffer[k++] = 1114112;
      this.len = k;
      int[] temp = this.list;
      this.list = this.buffer;
      this.buffer = temp;
      this.pat = null;
      return this;
   }

   private static final int max(int a, int b) {
      return a > b ? a : b;
   }

   private void applyFilter(Filter filter, UnicodeSet inclusions) {
      this.clear();
      int startHasProperty = -1;
      int limitRange = inclusions.getRangeCount();

      for(int j = 0; j < limitRange; ++j) {
         int start = inclusions.getRangeStart(j);
         int end = inclusions.getRangeEnd(j);

         for(int ch = start; ch <= end; ++ch) {
            if (filter.contains(ch)) {
               if (startHasProperty < 0) {
                  startHasProperty = ch;
               }
            } else if (startHasProperty >= 0) {
               this.add_unchecked(startHasProperty, ch - 1);
               startHasProperty = -1;
            }
         }
      }

      if (startHasProperty >= 0) {
         this.add_unchecked(startHasProperty, 1114111);
      }

   }

   private static String mungeCharName(String source) {
      source = PatternProps.trimWhiteSpace(source);
      StringBuilder buf = null;

      for(int i = 0; i < source.length(); ++i) {
         char ch = source.charAt(i);
         if (PatternProps.isWhiteSpace(ch)) {
            if (buf == null) {
               buf = (new StringBuilder()).append(source, 0, i);
            } else if (buf.charAt(buf.length() - 1) == ' ') {
               continue;
            }

            ch = ' ';
         }

         if (buf != null) {
            buf.append(ch);
         }
      }

      return buf == null ? source : buf.toString();
   }

   public UnicodeSet applyIntPropertyValue(int prop, int value) {
      if (prop == 8192) {
         UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(prop);
         this.applyFilter(new GeneralCategoryMaskFilter(value), inclusions);
      } else if (prop == 28672) {
         UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(prop);
         this.applyFilter(new ScriptExtensionsFilter(value), inclusions);
      } else if (prop == 28673) {
         UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(prop);
         this.applyFilter(new IdentifierTypeFilter(value), inclusions);
      } else if (0 <= prop && prop < 76) {
         if (value != 0 && value != 1) {
            this.clear();
         } else {
            this.set(CharacterProperties.getBinaryPropertySet(prop));
            if (value == 0) {
               this.complement().removeAllStrings();
            }
         }
      } else {
         if (4096 > prop || prop >= 4123) {
            throw new IllegalArgumentException("unsupported property " + prop);
         }

         UnicodeSet inclusions = CharacterPropertiesImpl.getInclusionsForProperty(prop);
         this.applyFilter(new IntPropertyFilter(prop, value), inclusions);
      }

      return this;
   }

   public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias) {
      return this.applyPropertyAlias(propertyAlias, valueAlias, (SymbolTable)null);
   }

   public UnicodeSet applyPropertyAlias(String propertyAlias, String valueAlias, SymbolTable symbols) {
      this.checkFrozen();
      boolean invert = false;
      if (symbols != null && symbols instanceof XSymbolTable && ((XSymbolTable)symbols).applyPropertyAlias(propertyAlias, valueAlias, this)) {
         return this;
      } else if (XSYMBOL_TABLE != null && XSYMBOL_TABLE.applyPropertyAlias(propertyAlias, valueAlias, this)) {
         return this;
      } else {
         int p;
         int v;
         if (valueAlias.length() > 0) {
            p = UCharacter.getPropertyEnum(propertyAlias);
            if (p == 4101) {
               p = 8192;
            }

            if (p >= 0 && p < 76 || p >= 4096 && p < 4123 || p >= 8192 && p < 8193) {
               try {
                  v = UCharacter.getPropertyValueEnum(p, valueAlias);
               } catch (IllegalArgumentException e) {
                  if (p != 4098 && p != 4112 && p != 4113) {
                     throw e;
                  }

                  v = Integer.parseInt(PatternProps.trimWhiteSpace(valueAlias));
                  if (v < 0 || v > 255) {
                     throw e;
                  }
               }
            } else {
               switch (p) {
                  case 12288:
                     double value = Double.parseDouble(PatternProps.trimWhiteSpace(valueAlias));
                     this.applyFilter(new NumericValueFilter(value), CharacterPropertiesImpl.getInclusionsForProperty(p));
                     return this;
                  case 16384:
                     VersionInfo version = VersionInfo.getInstance(mungeCharName(valueAlias));
                     this.applyFilter(new VersionFilter(version), CharacterPropertiesImpl.getInclusionsForProperty(p));
                     return this;
                  case 16389:
                     String buf = mungeCharName(valueAlias);
                     int ch = UCharacter.getCharFromExtendedName(buf);
                     if (ch == -1) {
                        throw new IllegalArgumentException("Invalid character name");
                     }

                     this.clear();
                     this.add_unchecked(ch);
                     return this;
                  case 16395:
                     throw new IllegalArgumentException("Unicode_1_Name (na1) not supported");
                  case 28672:
                     v = UCharacter.getPropertyValueEnum(4106, valueAlias);
                     break;
                  case 28673:
                     v = UCharacter.getPropertyValueEnum(p, valueAlias);
                     break;
                  default:
                     throw new IllegalArgumentException("Unsupported property");
               }
            }
         } else {
            UPropertyAliases pnames = UPropertyAliases.INSTANCE;
            p = 8192;
            v = pnames.getPropertyValueEnum(p, propertyAlias);
            if (v == -1) {
               p = 4106;
               v = pnames.getPropertyValueEnum(p, propertyAlias);
               if (v == -1) {
                  p = pnames.getPropertyEnum(propertyAlias);
                  if (p == -1) {
                     p = -1;
                  }

                  if (p >= 0 && p < 76) {
                     v = 1;
                  } else {
                     if (p != -1) {
                        throw new IllegalArgumentException("Missing property value");
                     }

                     if (0 == UPropertyAliases.compare("ANY", propertyAlias)) {
                        this.set(0, 1114111);
                        return this;
                     }

                     if (0 == UPropertyAliases.compare("ASCII", propertyAlias)) {
                        this.set(0, 127);
                        return this;
                     }

                     if (0 != UPropertyAliases.compare("Assigned", propertyAlias)) {
                        throw new IllegalArgumentException("Invalid property alias: " + propertyAlias + "=" + valueAlias);
                     }

                     p = 8192;
                     v = 1;
                     invert = true;
                  }
               }
            }
         }

         this.applyIntPropertyValue(p, v);
         if (invert) {
            this.complement().removeAllStrings();
         }

         return this;
      }
   }

   private static boolean resemblesPropertyPattern(String pattern, int pos) {
      if (pos + 5 > pattern.length()) {
         return false;
      } else {
         return pattern.regionMatches(pos, "[:", 0, 2) || pattern.regionMatches(true, pos, "\\p", 0, 2) || pattern.regionMatches(pos, "\\N", 0, 2);
      }
   }

   private static boolean resemblesPropertyPattern(RuleCharacterIterator chars, int iterOpts) {
      boolean result = false;
      iterOpts &= -3;
      RuleCharacterIterator.Position pos = chars.getPos((RuleCharacterIterator.Position)null);
      int c = chars.next(iterOpts);
      if (c == 91 || c == 92) {
         int d = chars.next(iterOpts & -5);
         result = c == 91 ? d == 58 : d == 78 || d == 112 || d == 80;
      }

      chars.setPos(pos);
      return result;
   }

   private UnicodeSet applyPropertyPattern(String pattern, ParsePosition ppos, SymbolTable symbols) {
      int pos = ppos.getIndex();
      if (pos + 5 > pattern.length()) {
         return null;
      } else {
         boolean posix = false;
         boolean isName = false;
         boolean invert = false;
         if (pattern.regionMatches(pos, "[:", 0, 2)) {
            posix = true;
            pos = PatternProps.skipWhiteSpace(pattern, pos + 2);
            if (pos < pattern.length() && pattern.charAt(pos) == '^') {
               ++pos;
               invert = true;
            }
         } else {
            if (!pattern.regionMatches(true, pos, "\\p", 0, 2) && !pattern.regionMatches(pos, "\\N", 0, 2)) {
               return null;
            }

            char c = pattern.charAt(pos + 1);
            invert = c == 'P';
            isName = c == 'N';
            pos = PatternProps.skipWhiteSpace(pattern, pos + 2);
            if (pos == pattern.length() || pattern.charAt(pos++) != '{') {
               return null;
            }
         }

         int close = pattern.indexOf(posix ? ":]" : "}", pos);
         if (close < 0) {
            return null;
         } else {
            int equals = pattern.indexOf(61, pos);
            String propName;
            String valueName;
            if (equals >= 0 && equals < close && !isName) {
               propName = pattern.substring(pos, equals);
               valueName = pattern.substring(equals + 1, close);
            } else {
               propName = pattern.substring(pos, close);
               valueName = "";
               if (isName) {
                  valueName = propName;
                  propName = "na";
               }
            }

            this.applyPropertyAlias(propName, valueName, symbols);
            if (invert) {
               this.complement().removeAllStrings();
            }

            ppos.setIndex(close + (posix ? 2 : 1));
            return this;
         }
      }
   }

   private void applyPropertyPattern(RuleCharacterIterator chars, Appendable rebuiltPat, SymbolTable symbols) {
      String patStr = chars.getCurrentBuffer();
      int start = chars.getCurrentBufferPos();
      ParsePosition pos = new ParsePosition(start);
      this.applyPropertyPattern(patStr, pos, symbols);
      int length = pos.getIndex() - start;
      if (length == 0) {
         syntaxError(chars, "Invalid property pattern");
      }

      chars.jumpahead(length);
      append(rebuiltPat, patStr.substring(start, pos.getIndex()));
   }

   private static final void addCaseMapping(UnicodeSet set, int result, StringBuilder full) {
      if (result >= 0) {
         if (result > 31) {
            set.add(result);
         } else {
            set.add((CharSequence)full.toString());
            full.setLength(0);
         }
      }

   }

   UnicodeSet maybeOnlyCaseSensitive(UnicodeSet src) {
      if (src.size() < 30) {
         return src;
      } else {
         UnicodeSet sensitive = CharacterProperties.getBinaryPropertySet(34);
         return !src.hasStrings() && src.getRangeCount() <= sensitive.getRangeCount() ? ((UnicodeSet)src.clone()).retainAll(sensitive) : sensitive.cloneAsThawed().retainAll(src);
      }
   }

   private static final boolean scfString(CharSequence s, StringBuilder scf) {
      int length = s.length();

      int c;
      for(int i = 0; i < length; i += Character.charCount(c)) {
         c = Character.codePointAt(s, i);
         int scfChar = UCharacter.foldCase(c, 0);
         if (scfChar != c) {
            scf.setLength(0);
            scf.append(s, 0, i);

            while(true) {
               scf.appendCodePoint(scfChar);
               i += Character.charCount(c);
               if (i == length) {
                  return true;
               }

               c = Character.codePointAt(s, i);
               scfChar = UCharacter.foldCase(c, 0);
            }
         }
      }

      return false;
   }

   public UnicodeSet closeOver(int attribute) {
      this.checkFrozen();
      switch (attribute & 6) {
         case 0:
         case 1:
         case 3:
         case 5:
         default:
            break;
         case 2:
            this.closeOverCaseInsensitive(false);
            break;
         case 4:
            this.closeOverAddCaseMappings();
            break;
         case 6:
            this.closeOverCaseInsensitive(true);
      }

      return this;
   }

   private void closeOverCaseInsensitive(boolean simple) {
      UCaseProps csp = UCaseProps.INSTANCE;
      UnicodeSet foldSet = new UnicodeSet(this);
      if (!simple && foldSet.hasStrings()) {
         foldSet.strings.clear();
      }

      UnicodeSet codePoints = this.maybeOnlyCaseSensitive(this);
      int n = codePoints.getRangeCount();

      for(int i = 0; i < n; ++i) {
         int start = codePoints.getRangeStart(i);
         int end = codePoints.getRangeEnd(i);
         if (simple) {
            for(int cp = start; cp <= end; ++cp) {
               csp.addSimpleCaseClosure(cp, foldSet);
            }
         } else {
            for(int cp = start; cp <= end; ++cp) {
               csp.addCaseClosure(cp, foldSet);
            }
         }
      }

      if (this.hasStrings()) {
         StringBuilder sb = simple ? new StringBuilder() : null;

         for(String s : this.strings) {
            if (simple) {
               if (scfString(s, sb)) {
                  foldSet.remove(s).add((CharSequence)sb);
               }
            } else {
               String str = UCharacter.foldCase(s, 0);
               if (!csp.addStringCaseClosure(str, foldSet)) {
                  foldSet.add((CharSequence)str);
               }
            }
         }
      }

      this.set(foldSet);
   }

   private void closeOverAddCaseMappings() {
      UCaseProps csp = UCaseProps.INSTANCE;
      UnicodeSet foldSet = new UnicodeSet(this);
      UnicodeSet codePoints = this.maybeOnlyCaseSensitive(this);
      int n = codePoints.getRangeCount();
      StringBuilder full = new StringBuilder();

      for(int i = 0; i < n; ++i) {
         int start = codePoints.getRangeStart(i);
         int end = codePoints.getRangeEnd(i);

         for(int cp = start; cp <= end; ++cp) {
            int result = csp.toFullLower(cp, (UCaseProps.ContextIterator)null, full, 1);
            addCaseMapping(foldSet, result, full);
            result = csp.toFullTitle(cp, (UCaseProps.ContextIterator)null, full, 1);
            addCaseMapping(foldSet, result, full);
            result = csp.toFullUpper(cp, (UCaseProps.ContextIterator)null, full, 1);
            addCaseMapping(foldSet, result, full);
            result = csp.toFullFolding(cp, full, 0);
            addCaseMapping(foldSet, result, full);
         }
      }

      if (this.hasStrings()) {
         ULocale root = ULocale.ROOT;
         BreakIterator bi = BreakIterator.getWordInstance(root);

         for(String str : this.strings) {
            foldSet.add((CharSequence)UCharacter.toLowerCase(root, str));
            foldSet.add((CharSequence)UCharacter.toTitleCase(root, str, bi));
            foldSet.add((CharSequence)UCharacter.toUpperCase(root, str));
            foldSet.add((CharSequence)UCharacter.foldCase(str, 0));
         }
      }

      this.set(foldSet);
   }

   public boolean isFrozen() {
      return this.bmpSet != null || this.stringSpan != null;
   }

   public UnicodeSet freeze() {
      if (!this.isFrozen()) {
         this.compact();
         if (this.hasStrings()) {
            this.stringSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), 127);
         }

         if (this.stringSpan == null || !this.stringSpan.needsStringSpanUTF16()) {
            this.bmpSet = new BMPSet(this.list, this.len);
         }
      }

      return this;
   }

   public int span(CharSequence s, SpanCondition spanCondition) {
      return this.span(s, 0, spanCondition);
   }

   public int span(CharSequence s, int start, SpanCondition spanCondition) {
      int end = s.length();
      if (start < 0) {
         start = 0;
      } else if (start >= end) {
         return end;
      }

      if (this.bmpSet != null) {
         return this.bmpSet.span(s, start, spanCondition, (OutputInt)null);
      } else if (this.stringSpan != null) {
         return this.stringSpan.span(s, start, spanCondition);
      } else {
         if (this.hasStrings()) {
            int which = spanCondition == UnicodeSet.SpanCondition.NOT_CONTAINED ? 33 : 34;
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which);
            if (strSpan.needsStringSpanUTF16()) {
               return strSpan.span(s, start, spanCondition);
            }
         }

         return this.spanCodePointsAndCount(s, start, spanCondition, (OutputInt)null);
      }
   }

   /** @deprecated */
   @Deprecated
   public int spanAndCount(CharSequence s, int start, SpanCondition spanCondition, OutputInt outCount) {
      if (outCount == null) {
         throw new IllegalArgumentException("outCount must not be null");
      } else {
         int end = s.length();
         if (start < 0) {
            start = 0;
         } else if (start >= end) {
            return end;
         }

         if (this.stringSpan != null) {
            return this.stringSpan.spanAndCount(s, start, spanCondition, outCount);
         } else if (this.bmpSet != null) {
            return this.bmpSet.span(s, start, spanCondition, outCount);
         } else if (this.hasStrings()) {
            int which = spanCondition == UnicodeSet.SpanCondition.NOT_CONTAINED ? 33 : 34;
            which |= 64;
            UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which);
            return strSpan.spanAndCount(s, start, spanCondition, outCount);
         } else {
            return this.spanCodePointsAndCount(s, start, spanCondition, outCount);
         }
      }
   }

   private int spanCodePointsAndCount(CharSequence s, int start, SpanCondition spanCondition, OutputInt outCount) {
      boolean spanContained = spanCondition != UnicodeSet.SpanCondition.NOT_CONTAINED;
      int next = start;
      int length = s.length();
      int count = 0;

      do {
         int c = Character.codePointAt(s, next);
         if (spanContained != this.contains(c)) {
            break;
         }

         ++count;
         next += Character.charCount(c);
      } while(next < length);

      if (outCount != null) {
         outCount.value = count;
      }

      return next;
   }

   public int spanBack(CharSequence s, SpanCondition spanCondition) {
      return this.spanBack(s, s.length(), spanCondition);
   }

   public int spanBack(CharSequence s, int fromIndex, SpanCondition spanCondition) {
      if (fromIndex <= 0) {
         return 0;
      } else {
         if (fromIndex > s.length()) {
            fromIndex = s.length();
         }

         if (this.bmpSet != null) {
            return this.bmpSet.spanBack(s, fromIndex, spanCondition);
         } else if (this.stringSpan != null) {
            return this.stringSpan.spanBack(s, fromIndex, spanCondition);
         } else {
            if (this.hasStrings()) {
               int which = spanCondition == UnicodeSet.SpanCondition.NOT_CONTAINED ? 17 : 18;
               UnicodeSetStringSpan strSpan = new UnicodeSetStringSpan(this, new ArrayList(this.strings), which);
               if (strSpan.needsStringSpanUTF16()) {
                  return strSpan.spanBack(s, fromIndex, spanCondition);
               }
            }

            boolean spanContained = spanCondition != UnicodeSet.SpanCondition.NOT_CONTAINED;
            int prev = fromIndex;

            do {
               int c = Character.codePointBefore(s, prev);
               if (spanContained != this.contains(c)) {
                  break;
               }

               prev -= Character.charCount(c);
            } while(prev > 0);

            return prev;
         }
      }
   }

   public UnicodeSet cloneAsThawed() {
      UnicodeSet result = new UnicodeSet(this);

      assert !result.isFrozen();

      return result;
   }

   private void checkFrozen() {
      if (this.isFrozen()) {
         throw new UnsupportedOperationException("Attempt to modify frozen object");
      }
   }

   public Iterable ranges() {
      return new EntryRangeIterable();
   }

   public Iterator iterator() {
      return new UnicodeSetIterator2(this);
   }

   public boolean containsAll(Iterable collection) {
      for(CharSequence o : collection) {
         if (!this.contains(o)) {
            return false;
         }
      }

      return true;
   }

   public boolean containsNone(Iterable collection) {
      for(CharSequence o : collection) {
         if (this.contains(o)) {
            return false;
         }
      }

      return true;
   }

   public final boolean containsSome(Iterable collection) {
      return !this.containsNone(collection);
   }

   public UnicodeSet addAll(CharSequence... collection) {
      this.checkFrozen();

      for(CharSequence str : collection) {
         this.add(str);
      }

      return this;
   }

   public UnicodeSet removeAll(Iterable collection) {
      this.checkFrozen();

      for(CharSequence o : collection) {
         this.remove(o);
      }

      return this;
   }

   public UnicodeSet retainAll(Iterable collection) {
      this.checkFrozen();
      UnicodeSet toRetain = new UnicodeSet();
      toRetain.addAll(collection);
      this.retainAll(toRetain);
      return this;
   }

   public int compareTo(UnicodeSet o) {
      return this.compareTo(o, UnicodeSet.ComparisonStyle.SHORTER_FIRST);
   }

   public int compareTo(UnicodeSet o, ComparisonStyle style) {
      if (style != UnicodeSet.ComparisonStyle.LEXICOGRAPHIC) {
         int diff = this.size() - o.size();
         if (diff != 0) {
            return diff < 0 == (style == UnicodeSet.ComparisonStyle.SHORTER_FIRST) ? -1 : 1;
         }
      }

      int i;
      int result;
      for(i = 0; 0 == (result = this.list[i] - o.list[i]); ++i) {
         if (this.list[i] == 1114112) {
            return compare((Iterable)this.strings, (Iterable)o.strings);
         }
      }

      if (this.list[i] == 1114112) {
         if (!this.hasStrings()) {
            return 1;
         } else {
            String item = (String)this.strings.first();
            return compare(item, o.list[i]);
         }
      } else if (o.list[i] == 1114112) {
         if (!o.hasStrings()) {
            return -1;
         } else {
            String item = (String)o.strings.first();
            int compareResult = compare(item, this.list[i]);
            return compareResult > 0 ? -1 : (compareResult < 0 ? 1 : 0);
         }
      } else {
         return (i & 1) == 0 ? result : -result;
      }
   }

   public int compareTo(Iterable other) {
      return compare((Iterable)this, (Iterable)other);
   }

   public static int compare(CharSequence string, int codePoint) {
      return CharSequences.compare(string, codePoint);
   }

   public static int compare(int codePoint, CharSequence string) {
      return -CharSequences.compare(string, codePoint);
   }

   public static int compare(Iterable collection1, Iterable collection2) {
      return compare(collection1.iterator(), collection2.iterator());
   }

   /** @deprecated */
   @Deprecated
   public static int compare(Iterator first, Iterator other) {
      while(first.hasNext()) {
         if (!other.hasNext()) {
            return 1;
         }

         T item1 = (T)((Comparable)first.next());
         T item2 = (T)((Comparable)other.next());
         int result = item1.compareTo(item2);
         if (result != 0) {
            return result;
         }
      }

      return other.hasNext() ? -1 : 0;
   }

   public static int compare(Collection collection1, Collection collection2, ComparisonStyle style) {
      if (style != UnicodeSet.ComparisonStyle.LEXICOGRAPHIC) {
         int diff = collection1.size() - collection2.size();
         if (diff != 0) {
            return diff < 0 == (style == UnicodeSet.ComparisonStyle.SHORTER_FIRST) ? -1 : 1;
         }
      }

      return compare((Iterable)collection1, (Iterable)collection2);
   }

   public static Collection addAllTo(Iterable source, Collection target) {
      for(Object item : source) {
         target.add(item);
      }

      return target;
   }

   public static Object[] addAllTo(Iterable source, Object[] target) {
      int i = 0;

      for(Object item : source) {
         target[i++] = item;
      }

      return target;
   }

   public Collection strings() {
      return this.hasStrings() ? Collections.unmodifiableSortedSet(this.strings) : EMPTY_STRINGS;
   }

   /** @deprecated */
   @Deprecated
   public static int getSingleCodePoint(CharSequence s) {
      return CharSequences.getSingleCodePoint(s);
   }

   /** @deprecated */
   @Deprecated
   public UnicodeSet addBridges(UnicodeSet dontCare) {
      UnicodeSet notInInput = (new UnicodeSet(this)).complement().removeAllStrings();
      UnicodeSetIterator it = new UnicodeSetIterator(notInInput);

      while(it.nextRange()) {
         if (it.codepoint != 0 && it.codepointEnd != 1114111 && dontCare.contains(it.codepoint, it.codepointEnd)) {
            this.add(it.codepoint, it.codepointEnd);
         }
      }

      return this;
   }

   /** @deprecated */
   @Deprecated
   public int findIn(CharSequence value, int fromIndex, boolean findNot) {
      while(true) {
         if (fromIndex < value.length()) {
            int cp = UTF16.charAt(value, fromIndex);
            if (this.contains(cp) == findNot) {
               fromIndex += UTF16.getCharCount(cp);
               continue;
            }
         }

         return fromIndex;
      }
   }

   /** @deprecated */
   @Deprecated
   public int findLastIn(CharSequence value, int fromIndex, boolean findNot) {
      --fromIndex;

      while(fromIndex >= 0) {
         int cp = UTF16.charAt(value, fromIndex);
         if (this.contains(cp) != findNot) {
            break;
         }

         fromIndex -= UTF16.getCharCount(cp);
      }

      return fromIndex < 0 ? -1 : fromIndex;
   }

   /** @deprecated */
   @Deprecated
   public String stripFrom(CharSequence source, boolean matches) {
      StringBuilder result = new StringBuilder();

      int inside;
      for(int pos = 0; pos < source.length(); pos = this.findIn(source, inside, matches)) {
         inside = this.findIn(source, pos, !matches);
         result.append(source.subSequence(pos, inside));
      }

      return result.toString();
   }

   /** @deprecated */
   @Deprecated
   public static XSymbolTable getDefaultXSymbolTable() {
      return XSYMBOL_TABLE;
   }

   /** @deprecated */
   @Deprecated
   public static void setDefaultXSymbolTable(XSymbolTable xSymbolTable) {
      CharacterPropertiesImpl.clear();
      XSYMBOL_TABLE = xSymbolTable;
   }

   public Stream rangeStream() {
      return StreamSupport.stream(this.ranges().spliterator(), false);
   }

   public Stream stringStream() {
      return this.strings().stream();
   }

   public IntStream codePointStream() {
      return StreamSupport.intStream(new CodePointSpliterator(this), false);
   }

   public Stream stream() {
      return StreamSupport.stream(this.spliterator(), false);
   }

   public Iterable codePoints() {
      return new CodePointIterable(this);
   }

   private static final class NumericValueFilter implements Filter {
      double value;

      NumericValueFilter(double value) {
         this.value = value;
      }

      public boolean contains(int ch) {
         return UCharacter.getUnicodeNumericValue(ch) == this.value;
      }
   }

   private static final class GeneralCategoryMaskFilter implements Filter {
      int mask;

      GeneralCategoryMaskFilter(int mask) {
         this.mask = mask;
      }

      public boolean contains(int ch) {
         return (1 << UCharacter.getType(ch) & this.mask) != 0;
      }
   }

   private static final class IntPropertyFilter implements Filter {
      int prop;
      int value;

      IntPropertyFilter(int prop, int value) {
         this.prop = prop;
         this.value = value;
      }

      public boolean contains(int ch) {
         return UCharacter.getIntPropertyValue(ch, this.prop) == this.value;
      }
   }

   private static final class ScriptExtensionsFilter implements Filter {
      int script;

      ScriptExtensionsFilter(int script) {
         this.script = script;
      }

      public boolean contains(int c) {
         return UScript.hasScript(c, this.script);
      }
   }

   private static final class IdentifierTypeFilter implements Filter {
      int idType;

      IdentifierTypeFilter(int idType) {
         this.idType = idType;
      }

      public boolean contains(int c) {
         return UCharacterProperty.INSTANCE.hasIDType(c, this.idType);
      }
   }

   private static final class VersionFilter implements Filter {
      VersionInfo version;

      VersionFilter(VersionInfo version) {
         this.version = version;
      }

      public boolean contains(int ch) {
         VersionInfo v = UCharacter.getAge(ch);
         return !Utility.sameObjects(v, UnicodeSet.NO_VERSION) && v.compareTo(this.version) <= 0;
      }
   }

   public abstract static class XSymbolTable implements SymbolTable {
      public UnicodeMatcher lookupMatcher(int i) {
         return null;
      }

      public boolean applyPropertyAlias(String propertyName, String propertyValue, UnicodeSet result) {
         return false;
      }

      public char[] lookup(String s) {
         return null;
      }

      public String parseReference(String text, ParsePosition pos, int limit) {
         return null;
      }
   }

   public static class EntryRange {
      public int codepoint;
      public int codepointEnd;

      EntryRange() {
      }

      public String toString() {
         StringBuilder b = new StringBuilder();
         return (this.codepoint == this.codepointEnd ? (StringBuilder)UnicodeSet._appendToPat(b, this.codepoint, false) : (StringBuilder)UnicodeSet._appendToPat(((StringBuilder)UnicodeSet._appendToPat(b, this.codepoint, false)).append('-'), this.codepointEnd, false)).toString();
      }
   }

   private class EntryRangeIterable implements Iterable {
      private EntryRangeIterable() {
      }

      public Iterator iterator() {
         return UnicodeSet.this.new EntryRangeIterator();
      }
   }

   private class EntryRangeIterator implements Iterator {
      int pos;
      EntryRange result;

      private EntryRangeIterator() {
         this.result = new EntryRange();
      }

      public boolean hasNext() {
         return this.pos < UnicodeSet.this.len - 1;
      }

      public EntryRange next() {
         if (this.pos < UnicodeSet.this.len - 1) {
            this.result.codepoint = UnicodeSet.this.list[this.pos++];
            this.result.codepointEnd = UnicodeSet.this.list[this.pos++] - 1;
            return this.result;
         } else {
            throw new NoSuchElementException();
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private static class UnicodeSetIterator2 implements Iterator {
      private int[] sourceList;
      private int len;
      private int item;
      private int current;
      private int limit;
      private SortedSet sourceStrings;
      private Iterator stringIterator;
      private char[] buffer;

      UnicodeSetIterator2(UnicodeSet source) {
         this.len = source.len - 1;
         if (this.len > 0) {
            this.sourceStrings = source.strings;
            this.sourceList = source.list;
            this.current = this.sourceList[this.item++];
            this.limit = this.sourceList[this.item++];
         } else {
            this.stringIterator = source.strings.iterator();
            this.sourceList = null;
         }

      }

      public boolean hasNext() {
         return this.sourceList != null || this.stringIterator.hasNext();
      }

      public String next() {
         if (this.sourceList == null) {
            return (String)this.stringIterator.next();
         } else {
            int codepoint = this.current++;
            if (this.current >= this.limit) {
               if (this.item >= this.len) {
                  this.stringIterator = this.sourceStrings.iterator();
                  this.sourceList = null;
               } else {
                  this.current = this.sourceList[this.item++];
                  this.limit = this.sourceList[this.item++];
               }
            }

            if (codepoint <= 65535) {
               return String.valueOf((char)codepoint);
            } else {
               if (this.buffer == null) {
                  this.buffer = new char[2];
               }

               int offset = codepoint - 65536;
               this.buffer[0] = (char)((offset >>> 10) + '\ud800');
               this.buffer[1] = (char)((offset & 1023) + '\udc00');
               return String.valueOf(this.buffer);
            }
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   public static enum ComparisonStyle {
      SHORTER_FIRST,
      LEXICOGRAPHIC,
      LONGER_FIRST;
   }

   public static enum SpanCondition {
      NOT_CONTAINED,
      CONTAINED,
      SIMPLE,
      CONDITION_COUNT;
   }

   private class CodePointIterable implements Iterable {
      private final UnicodeSet unicodeSet;

      CodePointIterable(UnicodeSet unicodeSet) {
         this.unicodeSet = unicodeSet;
      }

      public Iterator iterator() {
         return UnicodeSet.this.new CodePointIterator(this.unicodeSet);
      }
   }

   private class CodePointIterator implements Iterator {
      private final CodePointIteratorInt cpi;

      CodePointIterator(UnicodeSet unicodeSet) {
         this.cpi = new CodePointIteratorInt(unicodeSet);
      }

      public boolean hasNext() {
         return this.cpi.hasNext();
      }

      public Integer next() {
         return this.cpi.next();
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private static class CodePointSpliterator implements Spliterator.OfInt {
      private static final int CHARACTERISTICS = 337;
      private final UnicodeSet unicodeSet;
      private final CodePointIteratorInt cpi;

      CodePointSpliterator(UnicodeSet unicodeSet) {
         this.unicodeSet = unicodeSet;
         this.cpi = new CodePointIteratorInt(unicodeSet);
      }

      public long estimateSize() {
         return (long)(this.unicodeSet.size() - this.unicodeSet.strings.size());
      }

      public int characteristics() {
         return this.unicodeSet.isFrozen() ? 1361 : 337;
      }

      public Spliterator.OfInt trySplit() {
         return null;
      }

      public boolean tryAdvance(IntConsumer action) {
         if (action == null) {
            throw new NullPointerException();
         } else if (this.cpi.hasNext()) {
            action.accept(this.cpi.next());
            return true;
         } else {
            return false;
         }
      }
   }

   private static class CodePointIteratorInt {
      private final int[] list;
      private final int lastRange;
      private int currentRange = 0;
      private int rangeStart;
      private int rangeLimit;

      public CodePointIteratorInt(UnicodeSet unicodeSet) {
         this.list = unicodeSet.list;
         this.lastRange = unicodeSet.len - 1;
         this.currentRange = 0;
         this.rangeStart = this.list[this.currentRange++];
         if (this.lastRange > 0) {
            this.rangeLimit = this.list[this.currentRange++];
         } else {
            this.rangeLimit = this.rangeStart;
         }

      }

      public boolean hasNext() {
         return this.rangeStart < this.rangeLimit || this.currentRange < this.lastRange;
      }

      public int next() {
         if (this.rangeStart >= this.rangeLimit) {
            if (this.currentRange >= this.lastRange) {
               throw new NoSuchElementException();
            }

            this.rangeStart = this.list[this.currentRange++];
            this.rangeLimit = this.list[this.currentRange++];
         }

         return this.rangeStart++;
      }
   }

   private interface Filter {
      boolean contains(int var1);
   }
}
