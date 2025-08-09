package com.ibm.icu.impl;

import com.ibm.icu.text.TimeZoneNames;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TZDBTimeZoneNames extends TimeZoneNames {
   private static final long serialVersionUID = 1L;
   private static final ConcurrentHashMap TZDB_NAMES_MAP = new ConcurrentHashMap();
   private static volatile TextTrieMap TZDB_NAMES_TRIE = null;
   private static final ICUResourceBundle ZONESTRINGS;
   private ULocale _locale;
   private transient volatile String _region;

   public TZDBTimeZoneNames(ULocale loc) {
      this._locale = loc;
   }

   public Set getAvailableMetaZoneIDs() {
      return TimeZoneNamesImpl._getAvailableMetaZoneIDs();
   }

   public Set getAvailableMetaZoneIDs(String tzID) {
      return TimeZoneNamesImpl._getAvailableMetaZoneIDs(tzID);
   }

   public String getMetaZoneID(String tzID, long date) {
      return TimeZoneNamesImpl._getMetaZoneID(tzID, date);
   }

   public String getReferenceZoneID(String mzID, String region) {
      return TimeZoneNamesImpl._getReferenceZoneID(mzID, region);
   }

   public String getMetaZoneDisplayName(String mzID, TimeZoneNames.NameType type) {
      return mzID != null && mzID.length() != 0 && (type == TimeZoneNames.NameType.SHORT_STANDARD || type == TimeZoneNames.NameType.SHORT_DAYLIGHT) ? getMetaZoneNames(mzID).getName(type) : null;
   }

   public String getTimeZoneDisplayName(String tzID, TimeZoneNames.NameType type) {
      return null;
   }

   public Collection find(CharSequence text, int start, EnumSet nameTypes) {
      if (text != null && text.length() != 0 && start >= 0 && start < text.length()) {
         prepareFind();
         TZDBNameSearchHandler handler = new TZDBNameSearchHandler(nameTypes, this.getTargetRegion());
         TZDB_NAMES_TRIE.find(text, start, handler);
         return handler.getMatches();
      } else {
         throw new IllegalArgumentException("bad input text or range");
      }
   }

   private static TZDBNames getMetaZoneNames(String mzID) {
      TZDBNames names = (TZDBNames)TZDB_NAMES_MAP.get(mzID);
      if (names == null) {
         names = TZDBTimeZoneNames.TZDBNames.getInstance(ZONESTRINGS, "meta:" + mzID);
         mzID = mzID.intern();
         TZDBNames tmpNames = (TZDBNames)TZDB_NAMES_MAP.putIfAbsent(mzID, names);
         names = tmpNames == null ? names : tmpNames;
      }

      return names;
   }

   private static void prepareFind() {
      if (TZDB_NAMES_TRIE == null) {
         synchronized(TZDBTimeZoneNames.class) {
            if (TZDB_NAMES_TRIE == null) {
               TextTrieMap<TZDBNameInfo> trie = new TextTrieMap(true);

               for(String mzID : TimeZoneNamesImpl._getAvailableMetaZoneIDs()) {
                  TZDBNames names = getMetaZoneNames(mzID);
                  String std = names.getName(TimeZoneNames.NameType.SHORT_STANDARD);
                  String dst = names.getName(TimeZoneNames.NameType.SHORT_DAYLIGHT);
                  if (std != null || dst != null) {
                     String[] parseRegions = names.getParseRegions();
                     mzID = mzID.intern();
                     boolean ambiguousType = std != null && dst != null && std.equals(dst);
                     if (std != null) {
                        TZDBNameInfo stdInf = new TZDBNameInfo(mzID, TimeZoneNames.NameType.SHORT_STANDARD, ambiguousType, parseRegions);
                        trie.put(std, stdInf);
                     }

                     if (dst != null) {
                        TZDBNameInfo dstInf = new TZDBNameInfo(mzID, TimeZoneNames.NameType.SHORT_DAYLIGHT, ambiguousType, parseRegions);
                        trie.put(dst, dstInf);
                     }
                  }
               }

               TZDB_NAMES_TRIE = trie;
            }
         }
      }

   }

   private String getTargetRegion() {
      if (this._region == null) {
         String region = this._locale.getCountry();
         if (region.length() == 0) {
            ULocale tmp = ULocale.addLikelySubtags(this._locale);
            region = tmp.getCountry();
            if (region.length() == 0) {
               region = "001";
            }
         }

         this._region = region;
      }

      return this._region;
   }

   static {
      UResourceBundle bundle = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/zone", "tzdbNames");
      ZONESTRINGS = (ICUResourceBundle)bundle.get("zoneStrings");
   }

   private static class TZDBNames {
      public static final TZDBNames EMPTY_TZDBNAMES = new TZDBNames((String[])null, (String[])null);
      private String[] _names;
      private String[] _parseRegions;
      private static final String[] KEYS = new String[]{"ss", "sd"};

      private TZDBNames(String[] names, String[] parseRegions) {
         this._names = names;
         this._parseRegions = parseRegions;
      }

      static TZDBNames getInstance(ICUResourceBundle zoneStrings, String key) {
         if (zoneStrings != null && key != null && key.length() != 0) {
            ICUResourceBundle table = null;

            try {
               table = (ICUResourceBundle)zoneStrings.get(key);
            } catch (MissingResourceException var9) {
               return EMPTY_TZDBNAMES;
            }

            boolean isEmpty = true;
            String[] names = new String[KEYS.length];

            for(int i = 0; i < names.length; ++i) {
               try {
                  names[i] = table.getString(KEYS[i]);
                  isEmpty = false;
               } catch (MissingResourceException var8) {
                  names[i] = null;
               }
            }

            if (isEmpty) {
               return EMPTY_TZDBNAMES;
            } else {
               String[] parseRegions = null;

               try {
                  ICUResourceBundle regionsRes = (ICUResourceBundle)table.get("parseRegions");
                  if (regionsRes.getType() == 0) {
                     parseRegions = new String[]{regionsRes.getString()};
                  } else if (regionsRes.getType() == 8) {
                     parseRegions = regionsRes.getStringArray();
                  }
               } catch (MissingResourceException var7) {
               }

               return new TZDBNames(names, parseRegions);
            }
         } else {
            return EMPTY_TZDBNAMES;
         }
      }

      String getName(TimeZoneNames.NameType type) {
         if (this._names == null) {
            return null;
         } else {
            String name = null;
            switch (type) {
               case SHORT_STANDARD:
                  name = this._names[0];
                  break;
               case SHORT_DAYLIGHT:
                  name = this._names[1];
            }

            return name;
         }
      }

      String[] getParseRegions() {
         return this._parseRegions;
      }
   }

   private static class TZDBNameInfo {
      final String mzID;
      final TimeZoneNames.NameType type;
      final boolean ambiguousType;
      final String[] parseRegions;

      TZDBNameInfo(String mzID, TimeZoneNames.NameType type, boolean ambiguousType, String[] parseRegions) {
         this.mzID = mzID;
         this.type = type;
         this.ambiguousType = ambiguousType;
         this.parseRegions = parseRegions;
      }
   }

   private static class TZDBNameSearchHandler implements TextTrieMap.ResultHandler {
      private EnumSet _nameTypes;
      private Collection _matches;
      private String _region;

      TZDBNameSearchHandler(EnumSet nameTypes, String region) {
         this._nameTypes = nameTypes;

         assert region != null;

         this._region = region;
      }

      public boolean handlePrefixMatch(int matchLength, Iterator values) {
         TZDBNameInfo match = null;
         TZDBNameInfo defaultRegionMatch = null;

         while(values.hasNext()) {
            TZDBNameInfo ninfo = (TZDBNameInfo)values.next();
            if (this._nameTypes == null || this._nameTypes.contains(ninfo.type)) {
               if (ninfo.parseRegions == null) {
                  if (defaultRegionMatch == null) {
                     defaultRegionMatch = ninfo;
                     match = ninfo;
                  }
               } else {
                  boolean matchRegion = false;

                  for(String region : ninfo.parseRegions) {
                     if (this._region.equals(region)) {
                        match = ninfo;
                        matchRegion = true;
                        break;
                     }
                  }

                  if (matchRegion) {
                     break;
                  }

                  if (match == null) {
                     match = ninfo;
                  }
               }
            }
         }

         if (match != null) {
            TimeZoneNames.NameType ntype = match.type;
            if (match.ambiguousType && (ntype == TimeZoneNames.NameType.SHORT_STANDARD || ntype == TimeZoneNames.NameType.SHORT_DAYLIGHT) && this._nameTypes.contains(TimeZoneNames.NameType.SHORT_STANDARD) && this._nameTypes.contains(TimeZoneNames.NameType.SHORT_DAYLIGHT)) {
               ntype = TimeZoneNames.NameType.SHORT_GENERIC;
            }

            TimeZoneNames.MatchInfo minfo = new TimeZoneNames.MatchInfo(ntype, (String)null, match.mzID, matchLength);
            if (this._matches == null) {
               this._matches = new LinkedList();
            }

            this._matches.add(minfo);
         }

         return true;
      }

      public Collection getMatches() {
         return (Collection)(this._matches == null ? Collections.emptyList() : this._matches);
      }
   }
}
