package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.processor.CustomMatcher;
import java.util.Arrays;
import java.util.Comparator;

public abstract class AbstractInputValueSwitch extends AbstractProcessorSwitch {
   private int columnIndex;
   private NormalizedString columnName;
   private Switch[] switches;
   private Switch defaultSwitch;
   private String[] headers;
   private int[] indexes;
   private static final Comparator caseSensitiveComparator = new Comparator() {
      public int compare(String o1, String o2) {
         return o1 != o2 && (o1 == null || !o1.equals(o2)) ? 1 : 0;
      }
   };
   private static final Comparator caseInsensitiveComparator = new Comparator() {
      public int compare(String o1, String o2) {
         return o1 != o2 && (o1 == null || !o1.equalsIgnoreCase(o2)) ? 1 : 0;
      }
   };
   private Comparator comparator;

   public AbstractInputValueSwitch() {
      this(0);
   }

   public AbstractInputValueSwitch(int columnIndex) {
      this.columnIndex = -1;
      this.columnName = null;
      this.switches = new Switch[0];
      this.defaultSwitch = null;
      this.comparator = caseInsensitiveComparator;
      if (columnIndex < 0) {
         throw new IllegalArgumentException("Column index must be positive");
      } else {
         this.columnIndex = columnIndex;
      }
   }

   public AbstractInputValueSwitch(String columnName) {
      this.columnIndex = -1;
      this.columnName = null;
      this.switches = new Switch[0];
      this.defaultSwitch = null;
      this.comparator = caseInsensitiveComparator;
      if (columnName != null && !columnName.trim().isEmpty()) {
         this.columnName = NormalizedString.valueOf(columnName);
      } else {
         throw new IllegalArgumentException("Column name cannot be blank");
      }
   }

   public void setCaseSensitive(boolean caseSensitive) {
      this.comparator = caseSensitive ? caseSensitiveComparator : caseInsensitiveComparator;
   }

   public void setComparator(Comparator comparator) {
      if (comparator == null) {
         throw new IllegalArgumentException("Comparator must not be null");
      } else {
         this.comparator = comparator;
      }
   }

   public void setDefaultSwitch(Processor processor, String... headersToUse) {
      this.defaultSwitch = new Switch(processor, headersToUse, (int[])null, (String)null, (CustomMatcher)null);
   }

   public void setDefaultSwitch(Processor processor) {
      this.defaultSwitch = new Switch(processor, (String[])null, (int[])null, (String)null, (CustomMatcher)null);
   }

   public void setDefaultSwitch(Processor processor, int... indexesToUse) {
      this.defaultSwitch = new Switch(processor, (String[])null, indexesToUse, (String)null, (CustomMatcher)null);
   }

   public boolean hasDefaultSwitch() {
      return this.defaultSwitch != null;
   }

   public void addSwitchForValue(String value, Processor processor) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, (String[])null, (int[])null, value, (CustomMatcher)null);
   }

   public void addSwitchForValue(String value, Processor processor, String... headersToUse) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, headersToUse, (int[])null, value, (CustomMatcher)null);
   }

   public void addSwitchForValue(CustomMatcher matcher, Processor processor) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, (String[])null, (int[])null, (String)null, matcher);
   }

   public void addSwitchForValue(CustomMatcher matcher, Processor processor, String... headersToUse) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, headersToUse, (int[])null, (String)null, matcher);
   }

   public void addSwitchForValue(String value, Processor processor, int... indexesToUse) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, (String[])null, indexesToUse, value, (CustomMatcher)null);
   }

   public void addSwitchForValue(CustomMatcher matcher, Processor processor, int... indexesToUse) {
      this.switches = (Switch[])Arrays.copyOf(this.switches, this.switches.length + 1);
      this.switches[this.switches.length - 1] = new Switch(processor, (String[])null, indexesToUse, (String)null, matcher);
   }

   public String[] getHeaders() {
      return this.headers;
   }

   public int[] getIndexes() {
      return this.indexes;
   }

   protected final Processor switchRowProcessor(String[] row, Context context) {
      if (this.columnIndex == -1) {
         NormalizedString[] headers = NormalizedString.toIdentifierGroupArray(context.headers());
         if (headers == null) {
            throw new DataProcessingException("Unable to determine position of column named '" + this.columnName + "' as no headers have been defined nor extracted from the input");
         }

         this.columnIndex = ArgumentUtils.indexOf(headers, this.columnName);
         if (this.columnIndex == -1) {
            throw new DataProcessingException("Unable to determine position of column named '" + this.columnName + "' as it does not exist in the headers. Available headers are " + Arrays.toString(headers));
         }
      }

      if (this.columnIndex < row.length) {
         String valueToMatch = row[this.columnIndex];

         for(int i = 0; i < this.switches.length; ++i) {
            Switch s = this.switches[i];
            if (s.matcher != null && s.matcher.matches(valueToMatch) || this.comparator.compare(valueToMatch, s.value) == 0) {
               this.headers = s.headers;
               this.indexes = s.indexes;
               return s.processor;
            }
         }
      }

      if (this.defaultSwitch != null) {
         this.headers = this.defaultSwitch.headers;
         this.indexes = this.defaultSwitch.indexes;
         return this.defaultSwitch.processor;
      } else {
         this.headers = null;
         this.indexes = null;
         throw new DataProcessingException("Unable to process input row. No switches activated and no default switch defined.", this.columnIndex, row, (Throwable)null);
      }
   }

   private static class Switch {
      final Processor processor;
      final String[] headers;
      final int[] indexes;
      final String value;
      final CustomMatcher matcher;

      Switch(Processor processor, String[] headers, int[] indexes, String value, CustomMatcher matcher) {
         this.processor = processor;
         this.headers = headers != null && headers.length != 0 ? headers : null;
         this.indexes = indexes != null && indexes.length != 0 ? indexes : null;
         this.value = value == null ? null : value.intern();
         this.matcher = matcher;
      }

      public String toString() {
         return "Switch{processor=" + this.processor + ", headers=" + Arrays.toString(this.headers) + ", indexes=" + Arrays.toString(this.indexes) + ", value='" + this.value + '\'' + ", matcher=" + this.matcher + '}';
      }
   }
}
