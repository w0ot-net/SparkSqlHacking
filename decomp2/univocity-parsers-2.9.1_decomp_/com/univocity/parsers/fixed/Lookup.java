package com.univocity.parsers.fixed;

import com.univocity.parsers.common.ColumnMap;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.ParserOutput;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ParsingContextWrapper;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordFactory;
import com.univocity.parsers.common.record.RecordMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class Lookup {
   final char[] value;
   final int[] lengths;
   final FieldAlignment[] alignments;
   final boolean[] ignore;
   final Boolean[] keepPaddingFlags;
   final char[] paddings;
   final NormalizedString[] fieldNames;
   final char wildcard;
   Context context;

   Lookup(String value, FixedWidthFields config, FixedWidthFormat format) {
      this.value = value.toCharArray();
      this.lengths = config.getAllLengths();
      this.alignments = config.getFieldAlignments();
      this.fieldNames = config.getFieldNames();
      this.paddings = config.getFieldPaddings(format);
      this.wildcard = format.getLookupWildcard();
      this.ignore = config.getFieldsToIgnore();
      this.keepPaddingFlags = config.getKeepPaddingFlags();
   }

   void initializeLookupContext(ParsingContext context, NormalizedString[] headersToUse) {
      final String[] headers = NormalizedString.toArray(headersToUse);
      this.context = new ParsingContextWrapper(context) {
         RecordFactory recordFactory;
         final ColumnMap columnMap = new ColumnMap(this, (ParserOutput)null);

         public String[] headers() {
            return headers;
         }

         public int indexOf(String header) {
            return this.columnMap.indexOf(header);
         }

         public int indexOf(Enum header) {
            return this.columnMap.indexOf(header);
         }

         public Record toRecord(String[] row) {
            if (this.recordFactory == null) {
               this.recordFactory = new RecordFactory(this);
            }

            return this.recordFactory.newRecord(row);
         }

         public RecordMetaData recordMetaData() {
            if (this.recordFactory == null) {
               this.recordFactory = new RecordFactory(this);
            }

            return this.recordFactory.getRecordMetaData();
         }
      };
   }

   boolean matches(char[] lookup) {
      if (this.value.length > lookup.length) {
         return false;
      } else {
         for(int i = 0; i < this.value.length; ++i) {
            char ch = this.value[i];
            if (ch != this.wildcard && ch != lookup[i]) {
               return false;
            }
         }

         return true;
      }
   }

   static void registerLookahead(String lookup, FixedWidthFields lengths, Map map) {
      registerLookup("ahead", lookup, lengths, map);
   }

   static void registerLookbehind(String lookup, FixedWidthFields lengths, Map map) {
      registerLookup("behind", lookup, lengths, map);
   }

   private static void registerLookup(String direction, String lookup, FixedWidthFields lengths, Map map) {
      if (lookup == null) {
         throw new IllegalArgumentException("Look" + direction + " value cannot be null");
      } else if (lengths == null) {
         throw new IllegalArgumentException("Lengths of fields associated to look" + direction + " value '" + lookup + "' cannot be null");
      } else {
         map.put(lookup, lengths);
      }
   }

   static Lookup[] getLookupFormats(Map map, FixedWidthFormat format) {
      if (map.isEmpty()) {
         return null;
      } else {
         Lookup[] out = new Lookup[map.size()];
         int i = 0;

         for(Map.Entry e : map.entrySet()) {
            out[i++] = new Lookup((String)e.getKey(), (FixedWidthFields)e.getValue(), format);
         }

         Arrays.sort(out, new Comparator() {
            public int compare(Lookup o1, Lookup o2) {
               return o1.value.length < o2.value.length ? 1 : (o1.value.length == o2.value.length ? 0 : -1);
            }
         });
         return out;
      }
   }

   static int calculateMaxLookupLength(Lookup[]... lookupArrays) {
      int max = 0;

      for(Lookup[] lookups : lookupArrays) {
         if (lookups != null) {
            for(Lookup lookup : lookups) {
               if (max < lookup.value.length) {
                  max = lookup.value.length;
               }
            }
         }
      }

      return max;
   }

   static int[] calculateMaxFieldLengths(FixedWidthFields fieldLengths, Map lookaheadFormats, Map lookbehindFormats) {
      List<int[]> allLengths = new ArrayList();
      if (fieldLengths != null) {
         allLengths.add(fieldLengths.getFieldLengths());
      }

      for(FixedWidthFields lengths : lookaheadFormats.values()) {
         allLengths.add(lengths.getFieldLengths());
      }

      for(FixedWidthFields lengths : lookbehindFormats.values()) {
         allLengths.add(lengths.getFieldLengths());
      }

      if (allLengths.isEmpty()) {
         throw new IllegalStateException("Cannot determine field lengths to use.");
      } else {
         int lastColumn = -1;

         for(int[] lengths : allLengths) {
            if (lastColumn < lengths.length) {
               lastColumn = lengths.length;
            }
         }

         int[] out = new int[lastColumn];
         Arrays.fill(out, 0);

         for(int[] lengths : allLengths) {
            for(int i = 0; i < lastColumn; ++i) {
               if (i < lengths.length) {
                  int length = lengths[i];
                  if (out[i] < length) {
                     out[i] = length;
                  }
               }
            }
         }

         return out;
      }
   }
}
