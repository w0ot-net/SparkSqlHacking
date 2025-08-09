package com.ibm.icu.lang;

import com.ibm.icu.impl.UCharacterProperty;
import com.ibm.icu.util.ULocale;
import java.util.BitSet;
import java.util.Locale;

public final class UScript {
   public static final int INVALID_CODE = -1;
   public static final int COMMON = 0;
   public static final int INHERITED = 1;
   public static final int ARABIC = 2;
   public static final int ARMENIAN = 3;
   public static final int BENGALI = 4;
   public static final int BOPOMOFO = 5;
   public static final int CHEROKEE = 6;
   public static final int COPTIC = 7;
   public static final int CYRILLIC = 8;
   public static final int DESERET = 9;
   public static final int DEVANAGARI = 10;
   public static final int ETHIOPIC = 11;
   public static final int GEORGIAN = 12;
   public static final int GOTHIC = 13;
   public static final int GREEK = 14;
   public static final int GUJARATI = 15;
   public static final int GURMUKHI = 16;
   public static final int HAN = 17;
   public static final int HANGUL = 18;
   public static final int HEBREW = 19;
   public static final int HIRAGANA = 20;
   public static final int KANNADA = 21;
   public static final int KATAKANA = 22;
   public static final int KHMER = 23;
   public static final int LAO = 24;
   public static final int LATIN = 25;
   public static final int MALAYALAM = 26;
   public static final int MONGOLIAN = 27;
   public static final int MYANMAR = 28;
   public static final int OGHAM = 29;
   public static final int OLD_ITALIC = 30;
   public static final int ORIYA = 31;
   public static final int RUNIC = 32;
   public static final int SINHALA = 33;
   public static final int SYRIAC = 34;
   public static final int TAMIL = 35;
   public static final int TELUGU = 36;
   public static final int THAANA = 37;
   public static final int THAI = 38;
   public static final int TIBETAN = 39;
   public static final int CANADIAN_ABORIGINAL = 40;
   public static final int UCAS = 40;
   public static final int YI = 41;
   public static final int TAGALOG = 42;
   public static final int HANUNOO = 43;
   public static final int BUHID = 44;
   public static final int TAGBANWA = 45;
   public static final int BRAILLE = 46;
   public static final int CYPRIOT = 47;
   public static final int LIMBU = 48;
   public static final int LINEAR_B = 49;
   public static final int OSMANYA = 50;
   public static final int SHAVIAN = 51;
   public static final int TAI_LE = 52;
   public static final int UGARITIC = 53;
   public static final int KATAKANA_OR_HIRAGANA = 54;
   public static final int BUGINESE = 55;
   public static final int GLAGOLITIC = 56;
   public static final int KHAROSHTHI = 57;
   public static final int SYLOTI_NAGRI = 58;
   public static final int NEW_TAI_LUE = 59;
   public static final int TIFINAGH = 60;
   public static final int OLD_PERSIAN = 61;
   public static final int BALINESE = 62;
   public static final int BATAK = 63;
   public static final int BLISSYMBOLS = 64;
   public static final int BRAHMI = 65;
   public static final int CHAM = 66;
   public static final int CIRTH = 67;
   public static final int OLD_CHURCH_SLAVONIC_CYRILLIC = 68;
   public static final int DEMOTIC_EGYPTIAN = 69;
   public static final int HIERATIC_EGYPTIAN = 70;
   public static final int EGYPTIAN_HIEROGLYPHS = 71;
   public static final int KHUTSURI = 72;
   public static final int SIMPLIFIED_HAN = 73;
   public static final int TRADITIONAL_HAN = 74;
   public static final int PAHAWH_HMONG = 75;
   public static final int OLD_HUNGARIAN = 76;
   public static final int HARAPPAN_INDUS = 77;
   public static final int JAVANESE = 78;
   public static final int KAYAH_LI = 79;
   public static final int LATIN_FRAKTUR = 80;
   public static final int LATIN_GAELIC = 81;
   public static final int LEPCHA = 82;
   public static final int LINEAR_A = 83;
   public static final int MANDAIC = 84;
   public static final int MANDAEAN = 84;
   public static final int MAYAN_HIEROGLYPHS = 85;
   public static final int MEROITIC_HIEROGLYPHS = 86;
   public static final int MEROITIC = 86;
   public static final int NKO = 87;
   public static final int ORKHON = 88;
   public static final int OLD_PERMIC = 89;
   public static final int PHAGS_PA = 90;
   public static final int PHOENICIAN = 91;
   public static final int MIAO = 92;
   public static final int PHONETIC_POLLARD = 92;
   public static final int RONGORONGO = 93;
   public static final int SARATI = 94;
   public static final int ESTRANGELO_SYRIAC = 95;
   public static final int WESTERN_SYRIAC = 96;
   public static final int EASTERN_SYRIAC = 97;
   public static final int TENGWAR = 98;
   public static final int VAI = 99;
   public static final int VISIBLE_SPEECH = 100;
   public static final int CUNEIFORM = 101;
   public static final int UNWRITTEN_LANGUAGES = 102;
   public static final int UNKNOWN = 103;
   public static final int CARIAN = 104;
   public static final int JAPANESE = 105;
   public static final int LANNA = 106;
   public static final int LYCIAN = 107;
   public static final int LYDIAN = 108;
   public static final int OL_CHIKI = 109;
   public static final int REJANG = 110;
   public static final int SAURASHTRA = 111;
   public static final int SIGN_WRITING = 112;
   public static final int SUNDANESE = 113;
   public static final int MOON = 114;
   public static final int MEITEI_MAYEK = 115;
   public static final int IMPERIAL_ARAMAIC = 116;
   public static final int AVESTAN = 117;
   public static final int CHAKMA = 118;
   public static final int KOREAN = 119;
   public static final int KAITHI = 120;
   public static final int MANICHAEAN = 121;
   public static final int INSCRIPTIONAL_PAHLAVI = 122;
   public static final int PSALTER_PAHLAVI = 123;
   public static final int BOOK_PAHLAVI = 124;
   public static final int INSCRIPTIONAL_PARTHIAN = 125;
   public static final int SAMARITAN = 126;
   public static final int TAI_VIET = 127;
   public static final int MATHEMATICAL_NOTATION = 128;
   public static final int SYMBOLS = 129;
   public static final int BAMUM = 130;
   public static final int LISU = 131;
   public static final int NAKHI_GEBA = 132;
   public static final int OLD_SOUTH_ARABIAN = 133;
   public static final int BASSA_VAH = 134;
   public static final int DUPLOYAN = 135;
   /** @deprecated */
   @Deprecated
   public static final int DUPLOYAN_SHORTAND = 135;
   public static final int ELBASAN = 136;
   public static final int GRANTHA = 137;
   public static final int KPELLE = 138;
   public static final int LOMA = 139;
   public static final int MENDE = 140;
   public static final int MEROITIC_CURSIVE = 141;
   public static final int OLD_NORTH_ARABIAN = 142;
   public static final int NABATAEAN = 143;
   public static final int PALMYRENE = 144;
   public static final int KHUDAWADI = 145;
   public static final int SINDHI = 145;
   public static final int WARANG_CITI = 146;
   public static final int AFAKA = 147;
   public static final int JURCHEN = 148;
   public static final int MRO = 149;
   public static final int NUSHU = 150;
   public static final int SHARADA = 151;
   public static final int SORA_SOMPENG = 152;
   public static final int TAKRI = 153;
   public static final int TANGUT = 154;
   public static final int WOLEAI = 155;
   public static final int ANATOLIAN_HIEROGLYPHS = 156;
   public static final int KHOJKI = 157;
   public static final int TIRHUTA = 158;
   public static final int CAUCASIAN_ALBANIAN = 159;
   public static final int MAHAJANI = 160;
   public static final int AHOM = 161;
   public static final int HATRAN = 162;
   public static final int MODI = 163;
   public static final int MULTANI = 164;
   public static final int PAU_CIN_HAU = 165;
   public static final int SIDDHAM = 166;
   public static final int ADLAM = 167;
   public static final int BHAIKSUKI = 168;
   public static final int MARCHEN = 169;
   public static final int NEWA = 170;
   public static final int OSAGE = 171;
   public static final int HAN_WITH_BOPOMOFO = 172;
   public static final int JAMO = 173;
   public static final int SYMBOLS_EMOJI = 174;
   public static final int MASARAM_GONDI = 175;
   public static final int SOYOMBO = 176;
   public static final int ZANABAZAR_SQUARE = 177;
   public static final int DOGRA = 178;
   public static final int GUNJALA_GONDI = 179;
   public static final int MAKASAR = 180;
   public static final int MEDEFAIDRIN = 181;
   public static final int HANIFI_ROHINGYA = 182;
   public static final int SOGDIAN = 183;
   public static final int OLD_SOGDIAN = 184;
   public static final int ELYMAIC = 185;
   public static final int NYIAKENG_PUACHUE_HMONG = 186;
   public static final int NANDINAGARI = 187;
   public static final int WANCHO = 188;
   public static final int CHORASMIAN = 189;
   public static final int DIVES_AKURU = 190;
   public static final int KHITAN_SMALL_SCRIPT = 191;
   public static final int YEZIDI = 192;
   public static final int CYPRO_MINOAN = 193;
   public static final int OLD_UYGHUR = 194;
   public static final int TANGSA = 195;
   public static final int TOTO = 196;
   public static final int VITHKUQI = 197;
   public static final int KAWI = 198;
   public static final int NAG_MUNDARI = 199;
   public static final int ARABIC_NASTALIQ = 200;
   public static final int GARAY = 201;
   public static final int GURUNG_KHEMA = 202;
   public static final int KIRAT_RAI = 203;
   public static final int OL_ONAL = 204;
   public static final int SUNUWAR = 205;
   public static final int TODHRI = 206;
   public static final int TULU_TIGALARI = 207;
   /** @deprecated */
   @Deprecated
   public static final int CODE_LIMIT = 208;
   private static final ScriptUsage[] usageValues = UScript.ScriptUsage.values();

   private static int[] getCodesFromLocale(ULocale locale) {
      String lang = locale.getLanguage();
      if (lang.equals("ja")) {
         return new int[]{22, 20, 17};
      } else if (lang.equals("ko")) {
         return new int[]{18, 17};
      } else {
         String script = locale.getScript();
         if (lang.equals("zh") && script.equals("Hant")) {
            return new int[]{17, 5};
         } else {
            if (script.length() != 0) {
               int scriptCode = getCodeFromName(script);
               if (scriptCode != -1) {
                  if (scriptCode == 73 || scriptCode == 74) {
                     scriptCode = 17;
                  }

                  return new int[]{scriptCode};
               }
            }

            return null;
         }
      }
   }

   private static int[] findCodeFromLocale(ULocale locale) {
      int[] result = getCodesFromLocale(locale);
      if (result != null) {
         return result;
      } else {
         ULocale likely = ULocale.addLikelySubtags(locale);
         return getCodesFromLocale(likely);
      }
   }

   public static final int[] getCode(Locale locale) {
      return findCodeFromLocale(ULocale.forLocale(locale));
   }

   public static final int[] getCode(ULocale locale) {
      return findCodeFromLocale(locale);
   }

   public static final int[] getCode(String nameOrAbbrOrLocale) {
      boolean triedCode = false;
      int lastSepPos = nameOrAbbrOrLocale.indexOf(95);
      if (lastSepPos < 0) {
         lastSepPos = nameOrAbbrOrLocale.indexOf(45);
      }

      if (lastSepPos < 0 || lastSepPos == 3 && nameOrAbbrOrLocale.length() > 8 || lastSepPos == 7 && nameOrAbbrOrLocale.length() == 11) {
         int propNum = UCharacter.getPropertyValueEnumNoThrow(4106, nameOrAbbrOrLocale);
         if (propNum != -1) {
            return new int[]{propNum};
         }

         triedCode = true;
      }

      int[] scripts = findCodeFromLocale(new ULocale(nameOrAbbrOrLocale));
      if (scripts != null) {
         return scripts;
      } else {
         if (!triedCode) {
            int propNum = UCharacter.getPropertyValueEnumNoThrow(4106, nameOrAbbrOrLocale);
            if (propNum != -1) {
               return new int[]{propNum};
            }
         }

         return null;
      }
   }

   public static final int getCodeFromName(String nameOrAbbr) {
      int propNum = UCharacter.getPropertyValueEnumNoThrow(4106, nameOrAbbr);
      return propNum == -1 ? -1 : propNum;
   }

   public static final int getScript(int codepoint) {
      if (codepoint >= 0 & codepoint <= 1114111) {
         int scriptX = UCharacterProperty.INSTANCE.getAdditional(codepoint, 0) & 4095;
         int codeOrIndex = scriptX & 1023;
         if (scriptX < 1024) {
            return codeOrIndex;
         } else if (scriptX < 2048) {
            return 0;
         } else {
            return scriptX < 3072 ? 1 : UCharacterProperty.INSTANCE.m_scriptExtensions_[codeOrIndex];
         }
      } else {
         throw new IllegalArgumentException(Integer.toString(codepoint));
      }
   }

   public static final boolean hasScript(int c, int sc) {
      int scriptX = UCharacterProperty.INSTANCE.getAdditional(c, 0) & 4095;
      int codeOrIndex = scriptX & 1023;
      if (scriptX < 1024) {
         return sc == codeOrIndex;
      } else {
         char[] scriptExtensions = UCharacterProperty.INSTANCE.m_scriptExtensions_;
         int scx = codeOrIndex;
         if (scriptX >= 3072) {
            scx = scriptExtensions[codeOrIndex + 1];
         }

         if (sc > 32767) {
            return false;
         } else {
            while(sc > scriptExtensions[scx]) {
               ++scx;
            }

            return sc == (scriptExtensions[scx] & 32767);
         }
      }
   }

   public static final int getScriptExtensions(int c, BitSet set) {
      set.clear();
      int scriptX = UCharacterProperty.INSTANCE.getAdditional(c, 0) & 4095;
      int codeOrIndex = scriptX & 1023;
      if (scriptX < 1024) {
         set.set(codeOrIndex);
         return codeOrIndex;
      } else {
         char[] scriptExtensions = UCharacterProperty.INSTANCE.m_scriptExtensions_;
         int scx = codeOrIndex;
         if (scriptX >= 3072) {
            scx = scriptExtensions[codeOrIndex + 1];
         }

         int length = 0;

         int sx;
         do {
            sx = scriptExtensions[scx++];
            set.set(sx & 32767);
            ++length;
         } while(sx < 32768);

         return -length;
      }
   }

   public static final String getName(int scriptCode) {
      return UCharacter.getPropertyValueName(4106, scriptCode, 1);
   }

   public static final String getShortName(int scriptCode) {
      return UCharacter.getPropertyValueName(4106, scriptCode, 0);
   }

   public static final String getSampleString(int script) {
      int sampleChar = UScript.ScriptMetadata.getScriptProps(script) & 2097151;
      return sampleChar != 0 ? (new StringBuilder()).appendCodePoint(sampleChar).toString() : "";
   }

   public static final ScriptUsage getUsage(int script) {
      return usageValues[UScript.ScriptMetadata.getScriptProps(script) >> 21 & 7];
   }

   public static final boolean isRightToLeft(int script) {
      return (UScript.ScriptMetadata.getScriptProps(script) & 16777216) != 0;
   }

   public static final boolean breaksBetweenLetters(int script) {
      return (UScript.ScriptMetadata.getScriptProps(script) & 33554432) != 0;
   }

   public static final boolean isCased(int script) {
      return (UScript.ScriptMetadata.getScriptProps(script) & 67108864) != 0;
   }

   private UScript() {
   }

   private static final class ScriptMetadata {
      private static final int UNKNOWN = 2097152;
      private static final int EXCLUSION = 4194304;
      private static final int LIMITED_USE = 6291456;
      private static final int RECOMMENDED = 10485760;
      private static final int RTL = 16777216;
      private static final int LB_LETTERS = 33554432;
      private static final int CASED = 67108864;
      private static final int[] SCRIPT_PROPS = new int[]{10485824, 10486536, 27264552, 77595953, 10488213, 44052741, 73405380, 71304162, 77595695, 71369748, 10488069, 10490528, 10490067, 4260656, 77595561, 10488469, 10488341, 44063575, 10529792, 27264464, 44052555, 10488981, 44052651, 44046208, 44043941, 77594700, 10489109, 4200486, 44044288, 4200079, 4260608, 10488597, 4200096, 10489221, 23070480, 10488725, 10488853, 27264908, 44043799, 10489664, 6296768, 39887496, 4200195, 4200227, 4200259, 4200291, 2107406, 21039104, 6297856, 4259840, 4260992, 4260944, 39852368, 4260736, 0, 4200960, 71314432, 21039616, 6334464, 39852416, 6303024, 4260768, 6298373, 6298560, 0, 4263941, 6334976, 0, 0, 0, 0, 4272467, 0, 44063575, 44063575, 4287260, 88149153, 0, 6334852, 6334730, 0, 0, 6298624, 4261447, 23070784, 0, 21039488, 23070666, 21040128, 4260715, 4237376, 21039360, 6385408, 0, 0, 0, 0, 0, 0, 6333769, 0, 4268032, 0, 2162128, 4260512, 44052555, 39852576, 4260480, 21039392, 6298714, 4237616, 6334594, 4315216, 6298499, 0, 6335424, 21039168, 21039872, 6361347, 10529792, 4264067, 21039832, 21039968, 21040015, 0, 21039936, 20973568, 39889536, 0, 0, 6334112, 6333648, 0, 21039712, 4287206, 4308000, 4261120, 4264725, 0, 0, 21096450, 21039520, 21039765, 21039254, 21039219, 4264638, 71375028, 0, 0, 4287055, 37859780, 4264323, 4264144, 4265600, 37847593, 0, 4277248, 4264456, 4265092, 4261175, 4264274, 37820183, 21039348, 4265486, 4264591, 4266688, 4265358, 90302729, 4267022, 4267122, 6362130, 73467061, 44063575, 10490130, 0, 4267280, 4266588, 4266507, 4265995, 6364529, 4267749, 71396928, 23137554, 21040962, 21040921, 21041137, 6414600, 4266446, 6415073, 21041087, 4266252, 37850213, 21040776, 4272101, 21041020, 4287164, 4317840, 71370114, 37822235, 4318438, 0, 88149341, 4284700, 4287813, 4318672, 4266948, 4261314, 4264850};

      private static final int getScriptProps(int script) {
         return 0 <= script && script < SCRIPT_PROPS.length ? SCRIPT_PROPS[script] : 0;
      }
   }

   public static enum ScriptUsage {
      NOT_ENCODED,
      UNKNOWN,
      EXCLUDED,
      LIMITED_USE,
      ASPIRATIONAL,
      RECOMMENDED;
   }
}
