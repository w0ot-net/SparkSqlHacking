package org.sparkproject.jetty.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuotedQualityCSV extends QuotedCSV implements Iterable {
   private static final Logger LOG = LoggerFactory.getLogger(QuotedQualityCSV.class);
   public static ToIntFunction MOST_SPECIFIC_MIME_ORDERING = (s) -> {
      if ("*/*".equals(s)) {
         return 0;
      } else if (s.endsWith("/*")) {
         return 1;
      } else {
         return s.indexOf(59) < 0 ? 2 : 3;
      }
   };
   private final List _qualities;
   private QualityValue _lastQuality;
   private boolean _sorted;
   private final ToIntFunction _secondaryOrdering;

   public QuotedQualityCSV() {
      this((ToIntFunction)null);
   }

   public QuotedQualityCSV(String[] preferredOrder) {
      this((ToIntFunction)((s) -> {
         for(int i = 0; i < preferredOrder.length; ++i) {
            if (preferredOrder[i].equals(s)) {
               return preferredOrder.length - i;
            }
         }

         if ("*".equals(s)) {
            return preferredOrder.length;
         } else {
            return 0;
         }
      }));
   }

   public QuotedQualityCSV(ToIntFunction secondaryOrdering) {
      super();
      this._qualities = new ArrayList();
      this._sorted = false;
      this._secondaryOrdering = secondaryOrdering == null ? (s) -> 0 : secondaryOrdering;
   }

   protected void parsedValueAndParams(StringBuffer buffer) {
      super.parsedValueAndParams(buffer);
      this._lastQuality = new QualityValue(this._lastQuality._quality, buffer.toString(), this._lastQuality._index);
      this._qualities.set(this._lastQuality._index, this._lastQuality);
   }

   protected void parsedValue(StringBuffer buffer) {
      super.parsedValue(buffer);
      this._sorted = false;
      this._lastQuality = new QualityValue((double)1.0F, buffer.toString(), this._qualities.size());
      this._qualities.add(this._lastQuality);
   }

   protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue) {
      this._sorted = false;
      if (paramName < 0) {
         if (buffer.charAt(buffer.length() - 1) == ';') {
            buffer.setLength(buffer.length() - 1);
         }
      } else if (paramValue >= 0 && buffer.charAt(paramName) == 'q' && paramValue > paramName && buffer.length() >= paramName && buffer.charAt(paramName + 1) == '=') {
         double q;
         try {
            q = this._keepQuotes && buffer.charAt(paramValue) == '"' ? Double.valueOf(buffer.substring(paramValue + 1, buffer.length() - 1)) : Double.valueOf(buffer.substring(paramValue));
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
            q = (double)0.0F;
         }

         buffer.setLength(Math.max(0, paramName - 1));
         if (q != (double)1.0F) {
            this._lastQuality = new QualityValue(q, buffer.toString(), this._lastQuality._index);
            this._qualities.set(this._lastQuality._index, this._lastQuality);
         }
      }

   }

   public List getValues() {
      if (!this._sorted) {
         this.sort();
      }

      return this._values;
   }

   public Iterator iterator() {
      if (!this._sorted) {
         this.sort();
      }

      return this._values.iterator();
   }

   protected void sort() {
      this._values.clear();
      this._qualities.stream().filter((qv) -> qv._quality != (double)0.0F).sorted().map((rec$) -> ((QualityValue)rec$).getValue()).collect(Collectors.toCollection(() -> this._values));
      this._sorted = true;
   }

   private class QualityValue implements Comparable {
      private final double _quality;
      private final String _value;
      private final int _index;

      private QualityValue(double quality, String value, int index) {
         this._quality = quality;
         this._value = value;
         this._index = index;
      }

      public int hashCode() {
         return Double.hashCode(this._quality) ^ Objects.hash(new Object[]{this._value, this._index});
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof QualityValue)) {
            return false;
         } else {
            QualityValue qv = (QualityValue)obj;
            return this._quality == qv._quality && Objects.equals(this._value, qv._value) && Objects.equals(this._index, qv._index);
         }
      }

      private String getValue() {
         return this._value;
      }

      public int compareTo(QualityValue o) {
         int compare = Double.compare(o._quality, this._quality);
         if (compare == 0) {
            compare = Integer.compare(QuotedQualityCSV.this._secondaryOrdering.applyAsInt(o._value), QuotedQualityCSV.this._secondaryOrdering.applyAsInt(this._value));
            if (compare == 0) {
               compare = -Integer.compare(o._index, this._index);
            }
         }

         return compare;
      }

      public String toString() {
         return String.format("%s@%x[%s,q=%f,i=%d]", this.getClass().getSimpleName(), this.hashCode(), this._value, this._quality, this._index);
      }
   }
}
