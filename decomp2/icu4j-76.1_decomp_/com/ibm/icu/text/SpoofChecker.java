package com.ibm.icu.text;

import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.lang.UScript;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpoofChecker {
   public static final UnicodeSet INCLUSION;
   public static final UnicodeSet RECOMMENDED;
   public static final int SINGLE_SCRIPT_CONFUSABLE = 1;
   public static final int MIXED_SCRIPT_CONFUSABLE = 2;
   public static final int WHOLE_SCRIPT_CONFUSABLE = 4;
   public static final int CONFUSABLE = 7;
   /** @deprecated */
   @Deprecated
   public static final int ANY_CASE = 8;
   public static final int RESTRICTION_LEVEL = 16;
   /** @deprecated */
   @Deprecated
   public static final int SINGLE_SCRIPT = 16;
   public static final int INVISIBLE = 32;
   public static final int CHAR_LIMIT = 64;
   public static final int MIXED_NUMBERS = 128;
   public static final int HIDDEN_OVERLAY = 256;
   public static final int ALL_CHECKS = -1;
   static final UnicodeSet ASCII;
   private int fChecks;
   private SpoofData fSpoofData;
   private Set fAllowedLocales;
   private UnicodeSet fAllowedCharsSet;
   private RestrictionLevel fRestrictionLevel;
   private static Normalizer2 nfdNormalizer;

   private SpoofChecker() {
   }

   /** @deprecated */
   @Deprecated
   public RestrictionLevel getRestrictionLevel() {
      return this.fRestrictionLevel;
   }

   public int getChecks() {
      return this.fChecks;
   }

   public Set getAllowedLocales() {
      return Collections.unmodifiableSet(this.fAllowedLocales);
   }

   public Set getAllowedJavaLocales() {
      HashSet<Locale> locales = new HashSet(this.fAllowedLocales.size());

      for(ULocale uloc : this.fAllowedLocales) {
         locales.add(uloc.toLocale());
      }

      return locales;
   }

   public UnicodeSet getAllowedChars() {
      return this.fAllowedCharsSet;
   }

   public boolean failsChecks(String text, CheckResult checkResult) {
      int length = text.length();
      int result = 0;
      if (checkResult != null) {
         checkResult.position = 0;
         checkResult.numerics = null;
         checkResult.restrictionLevel = null;
      }

      if (0 != (this.fChecks & 16)) {
         RestrictionLevel textRestrictionLevel = this.getRestrictionLevel(text);
         if (textRestrictionLevel.compareTo(this.fRestrictionLevel) > 0) {
            result |= 16;
         }

         if (checkResult != null) {
            checkResult.restrictionLevel = textRestrictionLevel;
         }
      }

      if (0 != (this.fChecks & 128)) {
         UnicodeSet numerics = new UnicodeSet();
         this.getNumerics(text, numerics);
         if (numerics.size() > 1) {
            result |= 128;
         }

         if (checkResult != null) {
            checkResult.numerics = numerics;
         }
      }

      if (0 != (this.fChecks & 256)) {
         int index = this.findHiddenOverlay(text);
         if (index != -1) {
            result |= 256;
         }
      }

      if (0 != (this.fChecks & 64)) {
         int i = 0;

         while(i < length) {
            int c = Character.codePointAt(text, i);
            i = Character.offsetByCodePoints(text, i, 1);
            if (!this.fAllowedCharsSet.contains(c)) {
               result |= 64;
               break;
            }
         }
      }

      if (0 != (this.fChecks & 32)) {
         String nfdText = nfdNormalizer.normalize(text);
         int firstNonspacingMark = 0;
         boolean haveMultipleMarks = false;
         UnicodeSet marksSeenSoFar = new UnicodeSet();
         int i = 0;

         while(i < length) {
            int c = Character.codePointAt(nfdText, i);
            i = Character.offsetByCodePoints(nfdText, i, 1);
            if (Character.getType(c) != 6) {
               firstNonspacingMark = 0;
               if (haveMultipleMarks) {
                  marksSeenSoFar.clear();
                  haveMultipleMarks = false;
               }
            } else if (firstNonspacingMark == 0) {
               firstNonspacingMark = c;
            } else {
               if (!haveMultipleMarks) {
                  marksSeenSoFar.add(firstNonspacingMark);
                  haveMultipleMarks = true;
               }

               if (marksSeenSoFar.contains(c)) {
                  result |= 32;
                  break;
               }

               marksSeenSoFar.add(c);
            }
         }
      }

      if (checkResult != null) {
         checkResult.checks = result;
      }

      return 0 != result;
   }

   public boolean failsChecks(String text) {
      return this.failsChecks(text, (CheckResult)null);
   }

   public int areConfusable(String s1, String s2) {
      if ((this.fChecks & 7) == 0) {
         throw new IllegalArgumentException("No confusable checks are enabled.");
      } else {
         String s1Skeleton = this.getSkeleton(s1);
         String s2Skeleton = this.getSkeleton(s2);
         if (!s1Skeleton.equals(s2Skeleton)) {
            return 0;
         } else {
            ScriptSet s1RSS = new ScriptSet();
            this.getResolvedScriptSet(s1, s1RSS);
            ScriptSet s2RSS = new ScriptSet();
            this.getResolvedScriptSet(s2, s2RSS);
            int result = 0;
            if (s1RSS.intersects(s2RSS)) {
               result |= 1;
            } else {
               result |= 2;
               if (!s1RSS.isEmpty() && !s2RSS.isEmpty()) {
                  result |= 4;
               }
            }

            return result & this.fChecks;
         }
      }
   }

   public int areConfusable(int direction, CharSequence s1, CharSequence s2) {
      if ((this.fChecks & 7) == 0) {
         throw new IllegalArgumentException("No confusable checks are enabled.");
      } else {
         String s1Skeleton = this.getBidiSkeleton(direction, s1);
         String s2Skeleton = this.getBidiSkeleton(direction, s2);
         if (!s1Skeleton.equals(s2Skeleton)) {
            return 0;
         } else {
            ScriptSet s1RSS = new ScriptSet();
            this.getResolvedScriptSet(s1, s1RSS);
            ScriptSet s2RSS = new ScriptSet();
            this.getResolvedScriptSet(s2, s2RSS);
            int result = 0;
            if (s1RSS.intersects(s2RSS)) {
               result |= 1;
            } else {
               result |= 2;
               if (!s1RSS.isEmpty() && !s2RSS.isEmpty()) {
                  result |= 4;
               }
            }

            result &= this.fChecks;
            return result;
         }
      }
   }

   public String getBidiSkeleton(int direction, CharSequence str) {
      if (direction != 0 && direction != 1) {
         throw new IllegalArgumentException("direction should be DIRECTION_LEFT_TO_RIGHT or DIRECTION_RIGHT_TO_LEFT");
      } else {
         Bidi bidi = new Bidi(str.toString(), direction);
         return this.getSkeleton(bidi.writeReordered(3));
      }
   }

   public String getSkeleton(CharSequence str) {
      String nfdId = nfdNormalizer.normalize(str);
      int normalizedLen = nfdId.length();
      StringBuilder skelSB = new StringBuilder();
      int inputIndex = 0;

      while(inputIndex < normalizedLen) {
         int c = Character.codePointAt(nfdId, inputIndex);
         inputIndex += Character.charCount(c);
         if (!UCharacter.hasBinaryProperty(c, 5)) {
            this.fSpoofData.confusableLookup(c, skelSB);
         }
      }

      String skelStr = skelSB.toString();
      skelStr = nfdNormalizer.normalize(skelStr);
      return skelStr;
   }

   /** @deprecated */
   @Deprecated
   public String getSkeleton(int type, String id) {
      return this.getSkeleton(id);
   }

   public boolean equals(Object other) {
      if (!(other instanceof SpoofChecker)) {
         return false;
      } else {
         SpoofChecker otherSC = (SpoofChecker)other;
         if (this.fSpoofData != otherSC.fSpoofData && this.fSpoofData != null && !this.fSpoofData.equals(otherSC.fSpoofData)) {
            return false;
         } else if (this.fChecks != otherSC.fChecks) {
            return false;
         } else if (this.fAllowedLocales != otherSC.fAllowedLocales && this.fAllowedLocales != null && !this.fAllowedLocales.equals(otherSC.fAllowedLocales)) {
            return false;
         } else if (this.fAllowedCharsSet != otherSC.fAllowedCharsSet && this.fAllowedCharsSet != null && !this.fAllowedCharsSet.equals(otherSC.fAllowedCharsSet)) {
            return false;
         } else {
            return this.fRestrictionLevel == otherSC.fRestrictionLevel;
         }
      }
   }

   public int hashCode() {
      return this.fChecks ^ this.fSpoofData.hashCode() ^ this.fAllowedLocales.hashCode() ^ this.fAllowedCharsSet.hashCode() ^ this.fRestrictionLevel.ordinal();
   }

   private static void getAugmentedScriptSet(int codePoint, ScriptSet result) {
      result.clear();
      UScript.getScriptExtensions(codePoint, result);
      if (result.get(17)) {
         result.set(172);
         result.set(105);
         result.set(119);
      }

      if (result.get(20)) {
         result.set(105);
      }

      if (result.get(22)) {
         result.set(105);
      }

      if (result.get(18)) {
         result.set(119);
      }

      if (result.get(5)) {
         result.set(172);
      }

      if (result.get(0) || result.get(1)) {
         result.setAll();
      }

   }

   private void getResolvedScriptSet(CharSequence input, ScriptSet result) {
      this.getResolvedScriptSetWithout(input, 208, result);
   }

   private void getResolvedScriptSetWithout(CharSequence input, int script, ScriptSet result) {
      result.setAll();
      ScriptSet temp = new ScriptSet();
      int utf16Offset = 0;

      while(utf16Offset < input.length()) {
         int codePoint = Character.codePointAt(input, utf16Offset);
         utf16Offset += Character.charCount(codePoint);
         getAugmentedScriptSet(codePoint, temp);
         if (script == 208 || !temp.get(script)) {
            result.and(temp);
         }
      }

   }

   private void getNumerics(String input, UnicodeSet result) {
      result.clear();
      int utf16Offset = 0;

      while(utf16Offset < input.length()) {
         int codePoint = Character.codePointAt(input, utf16Offset);
         utf16Offset += Character.charCount(codePoint);
         if (UCharacter.getType(codePoint) == 9) {
            result.add(codePoint - UCharacter.getNumericValue(codePoint));
         }
      }

   }

   private RestrictionLevel getRestrictionLevel(String input) {
      if (!this.fAllowedCharsSet.containsAll(input)) {
         return SpoofChecker.RestrictionLevel.UNRESTRICTIVE;
      } else if (ASCII.containsAll(input)) {
         return SpoofChecker.RestrictionLevel.ASCII;
      } else {
         ScriptSet resolvedScriptSet = new ScriptSet();
         this.getResolvedScriptSet(input, resolvedScriptSet);
         if (!resolvedScriptSet.isEmpty()) {
            return SpoofChecker.RestrictionLevel.SINGLE_SCRIPT_RESTRICTIVE;
         } else {
            ScriptSet resolvedNoLatn = new ScriptSet();
            this.getResolvedScriptSetWithout(input, 25, resolvedNoLatn);
            if (!resolvedNoLatn.get(172) && !resolvedNoLatn.get(105) && !resolvedNoLatn.get(119)) {
               return !resolvedNoLatn.isEmpty() && !resolvedNoLatn.get(8) && !resolvedNoLatn.get(14) && !resolvedNoLatn.get(6) ? SpoofChecker.RestrictionLevel.MODERATELY_RESTRICTIVE : SpoofChecker.RestrictionLevel.MINIMALLY_RESTRICTIVE;
            } else {
               return SpoofChecker.RestrictionLevel.HIGHLY_RESTRICTIVE;
            }
         }
      }
   }

   int findHiddenOverlay(String input) {
      boolean sawLeadCharacter = false;
      StringBuilder sb = new StringBuilder();

      int cp;
      for(int i = 0; i < input.length(); i += UCharacter.charCount(cp)) {
         cp = input.codePointAt(i);
         if (sawLeadCharacter && cp == 775) {
            return i;
         }

         int combiningClass = UCharacter.getCombiningClass(cp);

         assert UCharacter.getCombiningClass(775) == 230;

         if (combiningClass == 0 || combiningClass == 230) {
            sawLeadCharacter = this.isIllegalCombiningDotLeadCharacter(cp, sb);
         }
      }

      return -1;
   }

   boolean isIllegalCombiningDotLeadCharacterNoLookup(int cp) {
      return cp == 105 || cp == 106 || cp == 305 || cp == 567 || cp == 108 || UCharacter.hasBinaryProperty(cp, 27);
   }

   boolean isIllegalCombiningDotLeadCharacter(int cp, StringBuilder sb) {
      if (this.isIllegalCombiningDotLeadCharacterNoLookup(cp)) {
         return true;
      } else {
         sb.setLength(0);
         this.fSpoofData.confusableLookup(cp, sb);
         int finalCp = UCharacter.codePointBefore((CharSequence)sb, sb.length());
         return finalCp != cp && this.isIllegalCombiningDotLeadCharacterNoLookup(finalCp);
      }
   }

   static {
      INCLUSION = (new UnicodeSet()).applyIntPropertyValue(28673, UCharacter.IdentifierType.INCLUSION.ordinal()).freeze();
      RECOMMENDED = (new UnicodeSet()).applyIntPropertyValue(28673, UCharacter.IdentifierType.RECOMMENDED.ordinal()).freeze();
      ASCII = (new UnicodeSet(0, 127)).freeze();
      nfdNormalizer = Normalizer2.getNFDInstance();
   }

   public static enum RestrictionLevel {
      ASCII,
      SINGLE_SCRIPT_RESTRICTIVE,
      HIGHLY_RESTRICTIVE,
      MODERATELY_RESTRICTIVE,
      MINIMALLY_RESTRICTIVE,
      UNRESTRICTIVE;
   }

   public static class Builder {
      int fChecks;
      SpoofData fSpoofData;
      final UnicodeSet fAllowedCharsSet = new UnicodeSet(0, 1114111);
      final Set fAllowedLocales = new LinkedHashSet();
      private RestrictionLevel fRestrictionLevel;

      public Builder() {
         this.fChecks = -1;
         this.fSpoofData = null;
         this.fRestrictionLevel = SpoofChecker.RestrictionLevel.HIGHLY_RESTRICTIVE;
      }

      public Builder(SpoofChecker src) {
         this.fChecks = src.fChecks;
         this.fSpoofData = src.fSpoofData;
         this.fAllowedCharsSet.set(src.fAllowedCharsSet);
         this.fAllowedLocales.addAll(src.fAllowedLocales);
         this.fRestrictionLevel = src.fRestrictionLevel;
      }

      public SpoofChecker build() {
         if (this.fSpoofData == null) {
            this.fSpoofData = SpoofChecker.SpoofData.getDefault();
         }

         SpoofChecker result = new SpoofChecker();
         result.fChecks = this.fChecks;
         result.fSpoofData = this.fSpoofData;
         result.fAllowedCharsSet = (UnicodeSet)this.fAllowedCharsSet.clone();
         result.fAllowedCharsSet.freeze();
         result.fAllowedLocales = new HashSet(this.fAllowedLocales);
         result.fRestrictionLevel = this.fRestrictionLevel;
         return result;
      }

      public Builder setData(Reader confusables) throws ParseException, IOException {
         this.fSpoofData = new SpoofData();
         SpoofChecker.Builder.ConfusabledataBuilder.buildConfusableData(confusables, this.fSpoofData);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Builder setData(Reader confusables, Reader confusablesWholeScript) throws ParseException, IOException {
         this.setData(confusables);
         return this;
      }

      public Builder setChecks(int checks) {
         if (0 != (checks & 0)) {
            throw new IllegalArgumentException("Bad Spoof Checks value.");
         } else {
            this.fChecks = checks & -1;
            return this;
         }
      }

      public Builder setAllowedLocales(Set locales) {
         this.fAllowedCharsSet.clear();

         for(ULocale locale : locales) {
            this.addScriptChars(locale, this.fAllowedCharsSet);
         }

         this.fAllowedLocales.clear();
         if (locales.size() == 0) {
            this.fAllowedCharsSet.add(0, 1114111);
            this.fChecks &= -65;
            return this;
         } else {
            UnicodeSet tempSet = new UnicodeSet();
            tempSet.applyIntPropertyValue(4106, 0);
            this.fAllowedCharsSet.addAll(tempSet);
            tempSet.applyIntPropertyValue(4106, 1);
            this.fAllowedCharsSet.addAll(tempSet);
            this.fAllowedLocales.clear();
            this.fAllowedLocales.addAll(locales);
            this.fChecks |= 64;
            return this;
         }
      }

      public Builder setAllowedJavaLocales(Set locales) {
         HashSet<ULocale> ulocales = new HashSet(locales.size());

         for(Locale locale : locales) {
            ulocales.add(ULocale.forLocale(locale));
         }

         return this.setAllowedLocales(ulocales);
      }

      private void addScriptChars(ULocale locale, UnicodeSet allowedChars) {
         int[] scripts = UScript.getCode(locale);
         if (scripts != null) {
            UnicodeSet tmpSet = new UnicodeSet();

            for(int i = 0; i < scripts.length; ++i) {
               tmpSet.applyIntPropertyValue(4106, scripts[i]);
               allowedChars.addAll(tmpSet);
            }
         }

      }

      public Builder setAllowedChars(UnicodeSet chars) {
         this.fAllowedCharsSet.set(chars);
         this.fAllowedLocales.clear();
         this.fChecks |= 64;
         return this;
      }

      public Builder setRestrictionLevel(RestrictionLevel restrictionLevel) {
         this.fRestrictionLevel = restrictionLevel;
         this.fChecks |= 144;
         return this;
      }

      private static class ConfusabledataBuilder {
         private Hashtable fTable = new Hashtable();
         private UnicodeSet fKeySet = new UnicodeSet();
         private StringBuffer fStringTable;
         private ArrayList fKeyVec = new ArrayList();
         private ArrayList fValueVec = new ArrayList();
         private SPUStringPool stringPool = new SPUStringPool();
         private Pattern fParseLine;
         private Pattern fParseHexNum;
         private int fLineNum;

         ConfusabledataBuilder() {
         }

         void build(Reader confusables, SpoofData dest) throws ParseException, IOException {
            StringBuffer fInput = new StringBuffer();
            LineNumberReader lnr = new LineNumberReader(confusables);

            while(true) {
               String line = lnr.readLine();
               if (line == null) {
                  this.fParseLine = Pattern.compile("(?m)^[ \\t]*([0-9A-Fa-f]+)[ \\t]+;[ \\t]*([0-9A-Fa-f]+(?:[ \\t]+[0-9A-Fa-f]+)*)[ \\t]*;\\s*(?:(SL)|(SA)|(ML)|(MA))[ \\t]*(?:#.*?)?$|^([ \\t]*(?:#.*?)?)$|^(.*?)$");
                  this.fParseHexNum = Pattern.compile("\\s*([0-9A-F]+)");
                  if (fInput.charAt(0) == '\ufeff') {
                     fInput.setCharAt(0, ' ');
                  }

                  Matcher matcher = this.fParseLine.matcher(fInput);

                  while(matcher.find()) {
                     ++this.fLineNum;
                     if (matcher.start(7) < 0) {
                        if (matcher.start(8) >= 0) {
                           throw new ParseException("Confusables, line " + this.fLineNum + ": Unrecognized Line: " + matcher.group(8), matcher.start(8));
                        }

                        int keyChar = Integer.parseInt(matcher.group(1), 16);
                        if (keyChar > 1114111) {
                           throw new ParseException("Confusables, line " + this.fLineNum + ": Bad code point: " + matcher.group(1), matcher.start(1));
                        }

                        Matcher m = this.fParseHexNum.matcher(matcher.group(2));
                        StringBuilder mapString = new StringBuilder();

                        while(m.find()) {
                           int c = Integer.parseInt(m.group(1), 16);
                           if (c > 1114111) {
                              throw new ParseException("Confusables, line " + this.fLineNum + ": Bad code point: " + Integer.toString(c, 16), matcher.start(2));
                           }

                           mapString.appendCodePoint(c);
                        }

                        assert mapString.length() >= 1;

                        SPUString smapString = this.stringPool.addString(mapString.toString());
                        this.fTable.put(keyChar, smapString);
                        this.fKeySet.add(keyChar);
                     }
                  }

                  this.stringPool.sort();
                  this.fStringTable = new StringBuffer();
                  int poolSize = this.stringPool.size();

                  for(int i = 0; i < poolSize; ++i) {
                     SPUString s = this.stringPool.getByIndex(i);
                     int strLen = s.fStr.length();
                     int strIndex = this.fStringTable.length();
                     if (strLen == 1) {
                        s.fCharOrStrTableIndex = s.fStr.charAt(0);
                     } else {
                        s.fCharOrStrTableIndex = strIndex;
                        this.fStringTable.append(s.fStr);
                     }
                  }

                  for(String keyCharStr : this.fKeySet) {
                     int keyChar = keyCharStr.codePointAt(0);
                     SPUString targetMapping = (SPUString)this.fTable.get(keyChar);

                     assert targetMapping != null;

                     if (targetMapping.fStr.length() > 256) {
                        throw new IllegalArgumentException("Confusable prototypes cannot be longer than 256 entries.");
                     }

                     int key = SpoofChecker.ConfusableDataUtils.codePointAndLengthToKey(keyChar, targetMapping.fStr.length());
                     int value = targetMapping.fCharOrStrTableIndex;
                     this.fKeyVec.add(key);
                     this.fValueVec.add(value);
                  }

                  int numKeys = this.fKeyVec.size();
                  dest.fCFUKeys = new int[numKeys];
                  int previousCodePoint = 0;

                  for(int var17 = 0; var17 < numKeys; ++var17) {
                     int key = (Integer)this.fKeyVec.get(var17);
                     int codePoint = SpoofChecker.ConfusableDataUtils.keyToCodePoint(key);

                     assert codePoint > previousCodePoint;

                     dest.fCFUKeys[var17] = key;
                     previousCodePoint = codePoint;
                  }

                  int numValues = this.fValueVec.size();

                  assert numKeys == numValues;

                  dest.fCFUValues = new short[numValues];
                  int var18 = 0;

                  for(int value : this.fValueVec) {
                     assert value < 65535;

                     dest.fCFUValues[var18++] = (short)value;
                  }

                  dest.fCFUStrings = this.fStringTable.toString();
                  return;
               }

               fInput.append(line);
               fInput.append('\n');
            }
         }

         public static void buildConfusableData(Reader confusables, SpoofData dest) throws IOException, ParseException {
            ConfusabledataBuilder builder = new ConfusabledataBuilder();
            builder.build(confusables, dest);
         }

         private static class SPUString {
            String fStr;
            int fCharOrStrTableIndex;

            SPUString(String s) {
               this.fStr = s;
               this.fCharOrStrTableIndex = 0;
            }
         }

         private static class SPUStringComparator implements Comparator {
            static final SPUStringComparator INSTANCE = new SPUStringComparator();

            public int compare(SPUString sL, SPUString sR) {
               int lenL = sL.fStr.length();
               int lenR = sR.fStr.length();
               if (lenL < lenR) {
                  return -1;
               } else {
                  return lenL > lenR ? 1 : sL.fStr.compareTo(sR.fStr);
               }
            }
         }

         private static class SPUStringPool {
            private Vector fVec = new Vector();
            private Hashtable fHash = new Hashtable();

            public SPUStringPool() {
            }

            public int size() {
               return this.fVec.size();
            }

            public SPUString getByIndex(int index) {
               SPUString retString = (SPUString)this.fVec.elementAt(index);
               return retString;
            }

            public SPUString addString(String src) {
               SPUString hashedString = (SPUString)this.fHash.get(src);
               if (hashedString == null) {
                  hashedString = new SPUString(src);
                  this.fHash.put(src, hashedString);
                  this.fVec.addElement(hashedString);
               }

               return hashedString;
            }

            public void sort() {
               Collections.sort(this.fVec, SpoofChecker.Builder.ConfusabledataBuilder.SPUStringComparator.INSTANCE);
            }
         }
      }
   }

   public static class CheckResult {
      public int checks = 0;
      /** @deprecated */
      @Deprecated
      public int position = 0;
      public UnicodeSet numerics;
      public RestrictionLevel restrictionLevel;

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("checks:");
         if (this.checks == 0) {
            sb.append(" none");
         } else if (this.checks == -1) {
            sb.append(" all");
         } else {
            if ((this.checks & 1) != 0) {
               sb.append(" SINGLE_SCRIPT_CONFUSABLE");
            }

            if ((this.checks & 2) != 0) {
               sb.append(" MIXED_SCRIPT_CONFUSABLE");
            }

            if ((this.checks & 4) != 0) {
               sb.append(" WHOLE_SCRIPT_CONFUSABLE");
            }

            if ((this.checks & 8) != 0) {
               sb.append(" ANY_CASE");
            }

            if ((this.checks & 16) != 0) {
               sb.append(" RESTRICTION_LEVEL");
            }

            if ((this.checks & 32) != 0) {
               sb.append(" INVISIBLE");
            }

            if ((this.checks & 64) != 0) {
               sb.append(" CHAR_LIMIT");
            }

            if ((this.checks & 128) != 0) {
               sb.append(" MIXED_NUMBERS");
            }
         }

         sb.append(", numerics: ").append(this.numerics.toPattern(false));
         sb.append(", position: ").append(this.position);
         sb.append(", restrictionLevel: ").append(this.restrictionLevel);
         return sb.toString();
      }
   }

   private static final class ConfusableDataUtils {
      public static final int FORMAT_VERSION = 2;

      public static final int keyToCodePoint(int key) {
         return key & 16777215;
      }

      public static final int keyToLength(int key) {
         return ((key & -16777216) >> 24) + 1;
      }

      public static final int codePointAndLengthToKey(int codePoint, int length) {
         assert (codePoint & 16777215) == codePoint;

         assert length <= 256;

         return codePoint | length - 1 << 24;
      }
   }

   private static class SpoofData {
      int[] fCFUKeys;
      short[] fCFUValues;
      String fCFUStrings;
      private static final int DATA_FORMAT = 1130788128;
      private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();

      public static SpoofData getDefault() {
         if (SpoofChecker.SpoofData.DefaultData.EXCEPTION != null) {
            throw new MissingResourceException("Could not load default confusables data: " + SpoofChecker.SpoofData.DefaultData.EXCEPTION.getMessage(), "SpoofChecker", "");
         } else {
            return SpoofChecker.SpoofData.DefaultData.INSTANCE;
         }
      }

      private SpoofData() {
      }

      private SpoofData(ByteBuffer bytes) throws IOException {
         ICUBinary.readHeader(bytes, 1130788128, IS_ACCEPTABLE);
         bytes.mark();
         this.readData(bytes);
      }

      public boolean equals(Object other) {
         if (!(other instanceof SpoofData)) {
            return false;
         } else {
            SpoofData otherData = (SpoofData)other;
            if (!Arrays.equals(this.fCFUKeys, otherData.fCFUKeys)) {
               return false;
            } else if (!Arrays.equals(this.fCFUValues, otherData.fCFUValues)) {
               return false;
            } else {
               return Utility.sameObjects(this.fCFUStrings, otherData.fCFUStrings) || this.fCFUStrings == null || this.fCFUStrings.equals(otherData.fCFUStrings);
            }
         }
      }

      public int hashCode() {
         return Arrays.hashCode(this.fCFUKeys) ^ Arrays.hashCode(this.fCFUValues) ^ this.fCFUStrings.hashCode();
      }

      private void readData(ByteBuffer bytes) throws IOException {
         int magic = bytes.getInt();
         if (magic != 944111087) {
            throw new IllegalArgumentException("Bad Spoof Check Data.");
         } else {
            int dataFormatVersion = bytes.getInt();
            int dataLength = bytes.getInt();
            int CFUKeysOffset = bytes.getInt();
            int CFUKeysSize = bytes.getInt();
            int CFUValuesOffset = bytes.getInt();
            int CFUValuesSize = bytes.getInt();
            int CFUStringTableOffset = bytes.getInt();
            int CFUStringTableSize = bytes.getInt();
            bytes.reset();
            ICUBinary.skipBytes(bytes, CFUKeysOffset);
            this.fCFUKeys = ICUBinary.getInts(bytes, CFUKeysSize, 0);
            bytes.reset();
            ICUBinary.skipBytes(bytes, CFUValuesOffset);
            this.fCFUValues = ICUBinary.getShorts(bytes, CFUValuesSize, 0);
            bytes.reset();
            ICUBinary.skipBytes(bytes, CFUStringTableOffset);
            this.fCFUStrings = ICUBinary.getString(bytes, CFUStringTableSize, 0);
         }
      }

      public void confusableLookup(int inChar, StringBuilder dest) {
         int lo = 0;
         int hi = this.length();

         do {
            int mid = (lo + hi) / 2;
            if (this.codePointAt(mid) > inChar) {
               hi = mid;
            } else {
               if (this.codePointAt(mid) >= inChar) {
                  lo = mid;
                  break;
               }

               lo = mid;
            }
         } while(hi - lo > 1);

         if (this.codePointAt(lo) != inChar) {
            dest.appendCodePoint(inChar);
         } else {
            this.appendValueTo(lo, dest);
         }
      }

      public int length() {
         return this.fCFUKeys.length;
      }

      public int codePointAt(int index) {
         return SpoofChecker.ConfusableDataUtils.keyToCodePoint(this.fCFUKeys[index]);
      }

      public void appendValueTo(int index, StringBuilder dest) {
         int stringLength = SpoofChecker.ConfusableDataUtils.keyToLength(this.fCFUKeys[index]);
         short value = this.fCFUValues[index];
         if (stringLength == 1) {
            dest.append((char)value);
         } else {
            dest.append(this.fCFUStrings, value, value + stringLength);
         }

      }

      private static final class IsAcceptable implements ICUBinary.Authenticate {
         private IsAcceptable() {
         }

         public boolean isDataVersionAcceptable(byte[] version) {
            return version[0] == 2 || version[1] != 0 || version[2] != 0 || version[3] != 0;
         }
      }

      private static final class DefaultData {
         private static SpoofData INSTANCE = null;
         private static IOException EXCEPTION = null;

         static {
            try {
               INSTANCE = new SpoofData(ICUBinary.getRequiredData("confusables.cfu"));
            } catch (IOException e) {
               EXCEPTION = e;
            }

         }
      }
   }

   static class ScriptSet extends BitSet {
      private static final long serialVersionUID = 1L;

      public void and(int script) {
         this.clear(0, script);
         this.clear(script + 1, 208);
      }

      public void setAll() {
         this.set(0, 208);
      }

      public boolean isFull() {
         return this.cardinality() == 208;
      }

      public void appendStringTo(StringBuilder sb) {
         sb.append("{ ");
         if (this.isEmpty()) {
            sb.append("- ");
         } else if (this.isFull()) {
            sb.append("* ");
         } else {
            for(int script = 0; script < 208; ++script) {
               if (this.get(script)) {
                  sb.append(UScript.getShortName(script));
                  sb.append(" ");
               }
            }
         }

         sb.append("}");
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("<ScriptSet ");
         this.appendStringTo(sb);
         sb.append(">");
         return sb.toString();
      }
   }
}
