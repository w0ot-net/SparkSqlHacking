package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.ListMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class ColumnIndex implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnIndex");
   private static final TField NULL_PAGES_FIELD_DESC = new TField("null_pages", (byte)15, (short)1);
   private static final TField MIN_VALUES_FIELD_DESC = new TField("min_values", (byte)15, (short)2);
   private static final TField MAX_VALUES_FIELD_DESC = new TField("max_values", (byte)15, (short)3);
   private static final TField BOUNDARY_ORDER_FIELD_DESC = new TField("boundary_order", (byte)8, (short)4);
   private static final TField NULL_COUNTS_FIELD_DESC = new TField("null_counts", (byte)15, (short)5);
   private static final TField REPETITION_LEVEL_HISTOGRAMS_FIELD_DESC = new TField("repetition_level_histograms", (byte)15, (short)6);
   private static final TField DEFINITION_LEVEL_HISTOGRAMS_FIELD_DESC = new TField("definition_level_histograms", (byte)15, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnIndexStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnIndexTupleSchemeFactory();
   @Nullable
   public List null_pages;
   @Nullable
   public List min_values;
   @Nullable
   public List max_values;
   @Nullable
   public BoundaryOrder boundary_order;
   @Nullable
   public List null_counts;
   @Nullable
   public List repetition_level_histograms;
   @Nullable
   public List definition_level_histograms;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ColumnIndex() {
   }

   public ColumnIndex(List null_pages, List min_values, List max_values, BoundaryOrder boundary_order) {
      this();
      this.null_pages = null_pages;
      this.min_values = min_values;
      this.max_values = max_values;
      this.boundary_order = boundary_order;
   }

   public ColumnIndex(ColumnIndex other) {
      if (other.isSetNull_pages()) {
         List<Boolean> __this__null_pages = new ArrayList(other.null_pages);
         this.null_pages = __this__null_pages;
      }

      if (other.isSetMin_values()) {
         List<ByteBuffer> __this__min_values = new ArrayList(other.min_values);
         this.min_values = __this__min_values;
      }

      if (other.isSetMax_values()) {
         List<ByteBuffer> __this__max_values = new ArrayList(other.max_values);
         this.max_values = __this__max_values;
      }

      if (other.isSetBoundary_order()) {
         this.boundary_order = other.boundary_order;
      }

      if (other.isSetNull_counts()) {
         List<Long> __this__null_counts = new ArrayList(other.null_counts);
         this.null_counts = __this__null_counts;
      }

      if (other.isSetRepetition_level_histograms()) {
         List<Long> __this__repetition_level_histograms = new ArrayList(other.repetition_level_histograms);
         this.repetition_level_histograms = __this__repetition_level_histograms;
      }

      if (other.isSetDefinition_level_histograms()) {
         List<Long> __this__definition_level_histograms = new ArrayList(other.definition_level_histograms);
         this.definition_level_histograms = __this__definition_level_histograms;
      }

   }

   public ColumnIndex deepCopy() {
      return new ColumnIndex(this);
   }

   public void clear() {
      this.null_pages = null;
      this.min_values = null;
      this.max_values = null;
      this.boundary_order = null;
      this.null_counts = null;
      this.repetition_level_histograms = null;
      this.definition_level_histograms = null;
   }

   public int getNull_pagesSize() {
      return this.null_pages == null ? 0 : this.null_pages.size();
   }

   @Nullable
   public Iterator getNull_pagesIterator() {
      return this.null_pages == null ? null : this.null_pages.iterator();
   }

   public void addToNull_pages(boolean elem) {
      if (this.null_pages == null) {
         this.null_pages = new ArrayList();
      }

      this.null_pages.add(elem);
   }

   @Nullable
   public List getNull_pages() {
      return this.null_pages;
   }

   public ColumnIndex setNull_pages(@Nullable List null_pages) {
      this.null_pages = null_pages;
      return this;
   }

   public void unsetNull_pages() {
      this.null_pages = null;
   }

   public boolean isSetNull_pages() {
      return this.null_pages != null;
   }

   public void setNull_pagesIsSet(boolean value) {
      if (!value) {
         this.null_pages = null;
      }

   }

   public int getMin_valuesSize() {
      return this.min_values == null ? 0 : this.min_values.size();
   }

   @Nullable
   public Iterator getMin_valuesIterator() {
      return this.min_values == null ? null : this.min_values.iterator();
   }

   public void addToMin_values(ByteBuffer elem) {
      if (this.min_values == null) {
         this.min_values = new ArrayList();
      }

      this.min_values.add(elem);
   }

   @Nullable
   public List getMin_values() {
      return this.min_values;
   }

   public ColumnIndex setMin_values(@Nullable List min_values) {
      this.min_values = min_values;
      return this;
   }

   public void unsetMin_values() {
      this.min_values = null;
   }

   public boolean isSetMin_values() {
      return this.min_values != null;
   }

   public void setMin_valuesIsSet(boolean value) {
      if (!value) {
         this.min_values = null;
      }

   }

   public int getMax_valuesSize() {
      return this.max_values == null ? 0 : this.max_values.size();
   }

   @Nullable
   public Iterator getMax_valuesIterator() {
      return this.max_values == null ? null : this.max_values.iterator();
   }

   public void addToMax_values(ByteBuffer elem) {
      if (this.max_values == null) {
         this.max_values = new ArrayList();
      }

      this.max_values.add(elem);
   }

   @Nullable
   public List getMax_values() {
      return this.max_values;
   }

   public ColumnIndex setMax_values(@Nullable List max_values) {
      this.max_values = max_values;
      return this;
   }

   public void unsetMax_values() {
      this.max_values = null;
   }

   public boolean isSetMax_values() {
      return this.max_values != null;
   }

   public void setMax_valuesIsSet(boolean value) {
      if (!value) {
         this.max_values = null;
      }

   }

   @Nullable
   public BoundaryOrder getBoundary_order() {
      return this.boundary_order;
   }

   public ColumnIndex setBoundary_order(@Nullable BoundaryOrder boundary_order) {
      this.boundary_order = boundary_order;
      return this;
   }

   public void unsetBoundary_order() {
      this.boundary_order = null;
   }

   public boolean isSetBoundary_order() {
      return this.boundary_order != null;
   }

   public void setBoundary_orderIsSet(boolean value) {
      if (!value) {
         this.boundary_order = null;
      }

   }

   public int getNull_countsSize() {
      return this.null_counts == null ? 0 : this.null_counts.size();
   }

   @Nullable
   public Iterator getNull_countsIterator() {
      return this.null_counts == null ? null : this.null_counts.iterator();
   }

   public void addToNull_counts(long elem) {
      if (this.null_counts == null) {
         this.null_counts = new ArrayList();
      }

      this.null_counts.add(elem);
   }

   @Nullable
   public List getNull_counts() {
      return this.null_counts;
   }

   public ColumnIndex setNull_counts(@Nullable List null_counts) {
      this.null_counts = null_counts;
      return this;
   }

   public void unsetNull_counts() {
      this.null_counts = null;
   }

   public boolean isSetNull_counts() {
      return this.null_counts != null;
   }

   public void setNull_countsIsSet(boolean value) {
      if (!value) {
         this.null_counts = null;
      }

   }

   public int getRepetition_level_histogramsSize() {
      return this.repetition_level_histograms == null ? 0 : this.repetition_level_histograms.size();
   }

   @Nullable
   public Iterator getRepetition_level_histogramsIterator() {
      return this.repetition_level_histograms == null ? null : this.repetition_level_histograms.iterator();
   }

   public void addToRepetition_level_histograms(long elem) {
      if (this.repetition_level_histograms == null) {
         this.repetition_level_histograms = new ArrayList();
      }

      this.repetition_level_histograms.add(elem);
   }

   @Nullable
   public List getRepetition_level_histograms() {
      return this.repetition_level_histograms;
   }

   public ColumnIndex setRepetition_level_histograms(@Nullable List repetition_level_histograms) {
      this.repetition_level_histograms = repetition_level_histograms;
      return this;
   }

   public void unsetRepetition_level_histograms() {
      this.repetition_level_histograms = null;
   }

   public boolean isSetRepetition_level_histograms() {
      return this.repetition_level_histograms != null;
   }

   public void setRepetition_level_histogramsIsSet(boolean value) {
      if (!value) {
         this.repetition_level_histograms = null;
      }

   }

   public int getDefinition_level_histogramsSize() {
      return this.definition_level_histograms == null ? 0 : this.definition_level_histograms.size();
   }

   @Nullable
   public Iterator getDefinition_level_histogramsIterator() {
      return this.definition_level_histograms == null ? null : this.definition_level_histograms.iterator();
   }

   public void addToDefinition_level_histograms(long elem) {
      if (this.definition_level_histograms == null) {
         this.definition_level_histograms = new ArrayList();
      }

      this.definition_level_histograms.add(elem);
   }

   @Nullable
   public List getDefinition_level_histograms() {
      return this.definition_level_histograms;
   }

   public ColumnIndex setDefinition_level_histograms(@Nullable List definition_level_histograms) {
      this.definition_level_histograms = definition_level_histograms;
      return this;
   }

   public void unsetDefinition_level_histograms() {
      this.definition_level_histograms = null;
   }

   public boolean isSetDefinition_level_histograms() {
      return this.definition_level_histograms != null;
   }

   public void setDefinition_level_histogramsIsSet(boolean value) {
      if (!value) {
         this.definition_level_histograms = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NULL_PAGES:
            if (value == null) {
               this.unsetNull_pages();
            } else {
               this.setNull_pages((List)value);
            }
            break;
         case MIN_VALUES:
            if (value == null) {
               this.unsetMin_values();
            } else {
               this.setMin_values((List)value);
            }
            break;
         case MAX_VALUES:
            if (value == null) {
               this.unsetMax_values();
            } else {
               this.setMax_values((List)value);
            }
            break;
         case BOUNDARY_ORDER:
            if (value == null) {
               this.unsetBoundary_order();
            } else {
               this.setBoundary_order((BoundaryOrder)value);
            }
            break;
         case NULL_COUNTS:
            if (value == null) {
               this.unsetNull_counts();
            } else {
               this.setNull_counts((List)value);
            }
            break;
         case REPETITION_LEVEL_HISTOGRAMS:
            if (value == null) {
               this.unsetRepetition_level_histograms();
            } else {
               this.setRepetition_level_histograms((List)value);
            }
            break;
         case DEFINITION_LEVEL_HISTOGRAMS:
            if (value == null) {
               this.unsetDefinition_level_histograms();
            } else {
               this.setDefinition_level_histograms((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NULL_PAGES:
            return this.getNull_pages();
         case MIN_VALUES:
            return this.getMin_values();
         case MAX_VALUES:
            return this.getMax_values();
         case BOUNDARY_ORDER:
            return this.getBoundary_order();
         case NULL_COUNTS:
            return this.getNull_counts();
         case REPETITION_LEVEL_HISTOGRAMS:
            return this.getRepetition_level_histograms();
         case DEFINITION_LEVEL_HISTOGRAMS:
            return this.getDefinition_level_histograms();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NULL_PAGES:
               return this.isSetNull_pages();
            case MIN_VALUES:
               return this.isSetMin_values();
            case MAX_VALUES:
               return this.isSetMax_values();
            case BOUNDARY_ORDER:
               return this.isSetBoundary_order();
            case NULL_COUNTS:
               return this.isSetNull_counts();
            case REPETITION_LEVEL_HISTOGRAMS:
               return this.isSetRepetition_level_histograms();
            case DEFINITION_LEVEL_HISTOGRAMS:
               return this.isSetDefinition_level_histograms();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnIndex ? this.equals((ColumnIndex)that) : false;
   }

   public boolean equals(ColumnIndex that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_null_pages = this.isSetNull_pages();
         boolean that_present_null_pages = that.isSetNull_pages();
         if (this_present_null_pages || that_present_null_pages) {
            if (!this_present_null_pages || !that_present_null_pages) {
               return false;
            }

            if (!this.null_pages.equals(that.null_pages)) {
               return false;
            }
         }

         boolean this_present_min_values = this.isSetMin_values();
         boolean that_present_min_values = that.isSetMin_values();
         if (this_present_min_values || that_present_min_values) {
            if (!this_present_min_values || !that_present_min_values) {
               return false;
            }

            if (!this.min_values.equals(that.min_values)) {
               return false;
            }
         }

         boolean this_present_max_values = this.isSetMax_values();
         boolean that_present_max_values = that.isSetMax_values();
         if (this_present_max_values || that_present_max_values) {
            if (!this_present_max_values || !that_present_max_values) {
               return false;
            }

            if (!this.max_values.equals(that.max_values)) {
               return false;
            }
         }

         boolean this_present_boundary_order = this.isSetBoundary_order();
         boolean that_present_boundary_order = that.isSetBoundary_order();
         if (this_present_boundary_order || that_present_boundary_order) {
            if (!this_present_boundary_order || !that_present_boundary_order) {
               return false;
            }

            if (!this.boundary_order.equals(that.boundary_order)) {
               return false;
            }
         }

         boolean this_present_null_counts = this.isSetNull_counts();
         boolean that_present_null_counts = that.isSetNull_counts();
         if (this_present_null_counts || that_present_null_counts) {
            if (!this_present_null_counts || !that_present_null_counts) {
               return false;
            }

            if (!this.null_counts.equals(that.null_counts)) {
               return false;
            }
         }

         boolean this_present_repetition_level_histograms = this.isSetRepetition_level_histograms();
         boolean that_present_repetition_level_histograms = that.isSetRepetition_level_histograms();
         if (this_present_repetition_level_histograms || that_present_repetition_level_histograms) {
            if (!this_present_repetition_level_histograms || !that_present_repetition_level_histograms) {
               return false;
            }

            if (!this.repetition_level_histograms.equals(that.repetition_level_histograms)) {
               return false;
            }
         }

         boolean this_present_definition_level_histograms = this.isSetDefinition_level_histograms();
         boolean that_present_definition_level_histograms = that.isSetDefinition_level_histograms();
         if (this_present_definition_level_histograms || that_present_definition_level_histograms) {
            if (!this_present_definition_level_histograms || !that_present_definition_level_histograms) {
               return false;
            }

            if (!this.definition_level_histograms.equals(that.definition_level_histograms)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetNull_pages() ? 131071 : 524287);
      if (this.isSetNull_pages()) {
         hashCode = hashCode * 8191 + this.null_pages.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMin_values() ? 131071 : 524287);
      if (this.isSetMin_values()) {
         hashCode = hashCode * 8191 + this.min_values.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMax_values() ? 131071 : 524287);
      if (this.isSetMax_values()) {
         hashCode = hashCode * 8191 + this.max_values.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetBoundary_order() ? 131071 : 524287);
      if (this.isSetBoundary_order()) {
         hashCode = hashCode * 8191 + this.boundary_order.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetNull_counts() ? 131071 : 524287);
      if (this.isSetNull_counts()) {
         hashCode = hashCode * 8191 + this.null_counts.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRepetition_level_histograms() ? 131071 : 524287);
      if (this.isSetRepetition_level_histograms()) {
         hashCode = hashCode * 8191 + this.repetition_level_histograms.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDefinition_level_histograms() ? 131071 : 524287);
      if (this.isSetDefinition_level_histograms()) {
         hashCode = hashCode * 8191 + this.definition_level_histograms.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnIndex other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNull_pages(), other.isSetNull_pages());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNull_pages()) {
               lastComparison = TBaseHelper.compareTo(this.null_pages, other.null_pages);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMin_values(), other.isSetMin_values());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMin_values()) {
                  lastComparison = TBaseHelper.compareTo(this.min_values, other.min_values);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetMax_values(), other.isSetMax_values());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetMax_values()) {
                     lastComparison = TBaseHelper.compareTo(this.max_values, other.max_values);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetBoundary_order(), other.isSetBoundary_order());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetBoundary_order()) {
                        lastComparison = TBaseHelper.compareTo((Comparable)this.boundary_order, (Comparable)other.boundary_order);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetNull_counts(), other.isSetNull_counts());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetNull_counts()) {
                           lastComparison = TBaseHelper.compareTo(this.null_counts, other.null_counts);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetRepetition_level_histograms(), other.isSetRepetition_level_histograms());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetRepetition_level_histograms()) {
                              lastComparison = TBaseHelper.compareTo(this.repetition_level_histograms, other.repetition_level_histograms);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetDefinition_level_histograms(), other.isSetDefinition_level_histograms());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetDefinition_level_histograms()) {
                                 lastComparison = TBaseHelper.compareTo(this.definition_level_histograms, other.definition_level_histograms);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              return 0;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnIndex._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnIndex(");
      boolean first = true;
      sb.append("null_pages:");
      if (this.null_pages == null) {
         sb.append("null");
      } else {
         sb.append(this.null_pages);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("min_values:");
      if (this.min_values == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString((Collection)this.min_values, sb);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("max_values:");
      if (this.max_values == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString((Collection)this.max_values, sb);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("boundary_order:");
      if (this.boundary_order == null) {
         sb.append("null");
      } else {
         sb.append(this.boundary_order);
      }

      first = false;
      if (this.isSetNull_counts()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("null_counts:");
         if (this.null_counts == null) {
            sb.append("null");
         } else {
            sb.append(this.null_counts);
         }

         first = false;
      }

      if (this.isSetRepetition_level_histograms()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("repetition_level_histograms:");
         if (this.repetition_level_histograms == null) {
            sb.append("null");
         } else {
            sb.append(this.repetition_level_histograms);
         }

         first = false;
      }

      if (this.isSetDefinition_level_histograms()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("definition_level_histograms:");
         if (this.definition_level_histograms == null) {
            sb.append("null");
         } else {
            sb.append(this.definition_level_histograms);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.null_pages == null) {
         throw new TProtocolException("Required field 'null_pages' was not present! Struct: " + this.toString());
      } else if (this.min_values == null) {
         throw new TProtocolException("Required field 'min_values' was not present! Struct: " + this.toString());
      } else if (this.max_values == null) {
         throw new TProtocolException("Required field 'max_values' was not present! Struct: " + this.toString());
      } else if (this.boundary_order == null) {
         throw new TProtocolException("Required field 'boundary_order' was not present! Struct: " + this.toString());
      }
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{ColumnIndex._Fields.NULL_COUNTS, ColumnIndex._Fields.REPETITION_LEVEL_HISTOGRAMS, ColumnIndex._Fields.DEFINITION_LEVEL_HISTOGRAMS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ColumnIndex._Fields.NULL_PAGES, new FieldMetaData("null_pages", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)2))));
      tmpMap.put(ColumnIndex._Fields.MIN_VALUES, new FieldMetaData("min_values", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11, true))));
      tmpMap.put(ColumnIndex._Fields.MAX_VALUES, new FieldMetaData("max_values", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11, true))));
      tmpMap.put(ColumnIndex._Fields.BOUNDARY_ORDER, new FieldMetaData("boundary_order", (byte)1, new EnumMetaData((byte)-1, BoundaryOrder.class)));
      tmpMap.put(ColumnIndex._Fields.NULL_COUNTS, new FieldMetaData("null_counts", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      tmpMap.put(ColumnIndex._Fields.REPETITION_LEVEL_HISTOGRAMS, new FieldMetaData("repetition_level_histograms", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      tmpMap.put(ColumnIndex._Fields.DEFINITION_LEVEL_HISTOGRAMS, new FieldMetaData("definition_level_histograms", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnIndex.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NULL_PAGES((short)1, "null_pages"),
      MIN_VALUES((short)2, "min_values"),
      MAX_VALUES((short)3, "max_values"),
      BOUNDARY_ORDER((short)4, "boundary_order"),
      NULL_COUNTS((short)5, "null_counts"),
      REPETITION_LEVEL_HISTOGRAMS((short)6, "repetition_level_histograms"),
      DEFINITION_LEVEL_HISTOGRAMS((short)7, "definition_level_histograms");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NULL_PAGES;
            case 2:
               return MIN_VALUES;
            case 3:
               return MAX_VALUES;
            case 4:
               return BOUNDARY_ORDER;
            case 5:
               return NULL_COUNTS;
            case 6:
               return REPETITION_LEVEL_HISTOGRAMS;
            case 7:
               return DEFINITION_LEVEL_HISTOGRAMS;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class ColumnIndexStandardSchemeFactory implements SchemeFactory {
      private ColumnIndexStandardSchemeFactory() {
      }

      public ColumnIndexStandardScheme getScheme() {
         return new ColumnIndexStandardScheme();
      }
   }

   private static class ColumnIndexStandardScheme extends StandardScheme {
      private ColumnIndexStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnIndex struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list88 = iprot.readListBegin();
                  struct.null_pages = new ArrayList(_list88.size);

                  for(int _i90 = 0; _i90 < _list88.size; ++_i90) {
                     boolean _elem89 = iprot.readBool();
                     struct.null_pages.add(_elem89);
                  }

                  iprot.readListEnd();
                  struct.setNull_pagesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list91 = iprot.readListBegin();
                  struct.min_values = new ArrayList(_list91.size);

                  for(int _i93 = 0; _i93 < _list91.size; ++_i93) {
                     ByteBuffer _elem92 = iprot.readBinary();
                     struct.min_values.add(_elem92);
                  }

                  iprot.readListEnd();
                  struct.setMin_valuesIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list94 = iprot.readListBegin();
                  struct.max_values = new ArrayList(_list94.size);

                  for(int _i96 = 0; _i96 < _list94.size; ++_i96) {
                     ByteBuffer _elem95 = iprot.readBinary();
                     struct.max_values.add(_elem95);
                  }

                  iprot.readListEnd();
                  struct.setMax_valuesIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.boundary_order = BoundaryOrder.findByValue(iprot.readI32());
                     struct.setBoundary_orderIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list97 = iprot.readListBegin();
                  struct.null_counts = new ArrayList(_list97.size);

                  for(int _i99 = 0; _i99 < _list97.size; ++_i99) {
                     long _elem98 = iprot.readI64();
                     struct.null_counts.add(_elem98);
                  }

                  iprot.readListEnd();
                  struct.setNull_countsIsSet(true);
                  break;
               case 6:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list100 = iprot.readListBegin();
                  struct.repetition_level_histograms = new ArrayList(_list100.size);

                  for(int _i102 = 0; _i102 < _list100.size; ++_i102) {
                     long _elem101 = iprot.readI64();
                     struct.repetition_level_histograms.add(_elem101);
                  }

                  iprot.readListEnd();
                  struct.setRepetition_level_histogramsIsSet(true);
                  break;
               case 7:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list103 = iprot.readListBegin();
                  struct.definition_level_histograms = new ArrayList(_list103.size);

                  for(int _i105 = 0; _i105 < _list103.size; ++_i105) {
                     long _elem104 = iprot.readI64();
                     struct.definition_level_histograms.add(_elem104);
                  }

                  iprot.readListEnd();
                  struct.setDefinition_level_histogramsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ColumnIndex struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnIndex.STRUCT_DESC);
         if (struct.null_pages != null) {
            oprot.writeFieldBegin(ColumnIndex.NULL_PAGES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)2, struct.null_pages.size()));

            for(boolean _iter106 : struct.null_pages) {
               oprot.writeBool(_iter106);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.min_values != null) {
            oprot.writeFieldBegin(ColumnIndex.MIN_VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.min_values.size()));

            for(ByteBuffer _iter107 : struct.min_values) {
               oprot.writeBinary(_iter107);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.max_values != null) {
            oprot.writeFieldBegin(ColumnIndex.MAX_VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.max_values.size()));

            for(ByteBuffer _iter108 : struct.max_values) {
               oprot.writeBinary(_iter108);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.boundary_order != null) {
            oprot.writeFieldBegin(ColumnIndex.BOUNDARY_ORDER_FIELD_DESC);
            oprot.writeI32(struct.boundary_order.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.null_counts != null && struct.isSetNull_counts()) {
            oprot.writeFieldBegin(ColumnIndex.NULL_COUNTS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.null_counts.size()));

            for(long _iter109 : struct.null_counts) {
               oprot.writeI64(_iter109);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.repetition_level_histograms != null && struct.isSetRepetition_level_histograms()) {
            oprot.writeFieldBegin(ColumnIndex.REPETITION_LEVEL_HISTOGRAMS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.repetition_level_histograms.size()));

            for(long _iter110 : struct.repetition_level_histograms) {
               oprot.writeI64(_iter110);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.definition_level_histograms != null && struct.isSetDefinition_level_histograms()) {
            oprot.writeFieldBegin(ColumnIndex.DEFINITION_LEVEL_HISTOGRAMS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.definition_level_histograms.size()));

            for(long _iter111 : struct.definition_level_histograms) {
               oprot.writeI64(_iter111);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnIndexTupleSchemeFactory implements SchemeFactory {
      private ColumnIndexTupleSchemeFactory() {
      }

      public ColumnIndexTupleScheme getScheme() {
         return new ColumnIndexTupleScheme();
      }
   }

   private static class ColumnIndexTupleScheme extends TupleScheme {
      private ColumnIndexTupleScheme() {
      }

      public void write(TProtocol prot, ColumnIndex struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.null_pages.size());

         for(boolean _iter112 : struct.null_pages) {
            oprot.writeBool(_iter112);
         }

         oprot.writeI32(struct.min_values.size());

         for(ByteBuffer _iter113 : struct.min_values) {
            oprot.writeBinary(_iter113);
         }

         oprot.writeI32(struct.max_values.size());

         for(ByteBuffer _iter114 : struct.max_values) {
            oprot.writeBinary(_iter114);
         }

         oprot.writeI32(struct.boundary_order.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetNull_counts()) {
            optionals.set(0);
         }

         if (struct.isSetRepetition_level_histograms()) {
            optionals.set(1);
         }

         if (struct.isSetDefinition_level_histograms()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetNull_counts()) {
            oprot.writeI32(struct.null_counts.size());

            for(long _iter115 : struct.null_counts) {
               oprot.writeI64(_iter115);
            }
         }

         if (struct.isSetRepetition_level_histograms()) {
            oprot.writeI32(struct.repetition_level_histograms.size());

            for(long _iter116 : struct.repetition_level_histograms) {
               oprot.writeI64(_iter116);
            }
         }

         if (struct.isSetDefinition_level_histograms()) {
            oprot.writeI32(struct.definition_level_histograms.size());

            for(long _iter117 : struct.definition_level_histograms) {
               oprot.writeI64(_iter117);
            }
         }

      }

      public void read(TProtocol prot, ColumnIndex struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list118 = iprot.readListBegin((byte)2);
         struct.null_pages = new ArrayList(_list118.size);

         for(int _i120 = 0; _i120 < _list118.size; ++_i120) {
            boolean _elem119 = iprot.readBool();
            struct.null_pages.add(_elem119);
         }

         struct.setNull_pagesIsSet(true);
         _list118 = iprot.readListBegin((byte)11);
         struct.min_values = new ArrayList(_list118.size);

         for(int _i123 = 0; _i123 < _list118.size; ++_i123) {
            ByteBuffer _elem122 = iprot.readBinary();
            struct.min_values.add(_elem122);
         }

         struct.setMin_valuesIsSet(true);
         _list118 = iprot.readListBegin((byte)11);
         struct.max_values = new ArrayList(_list118.size);

         for(int _i126 = 0; _i126 < _list118.size; ++_i126) {
            ByteBuffer _elem125 = iprot.readBinary();
            struct.max_values.add(_elem125);
         }

         struct.setMax_valuesIsSet(true);
         struct.boundary_order = BoundaryOrder.findByValue(iprot.readI32());
         struct.setBoundary_orderIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            TList _list127 = iprot.readListBegin((byte)10);
            struct.null_counts = new ArrayList(_list127.size);

            for(int _i129 = 0; _i129 < _list127.size; ++_i129) {
               long _elem128 = iprot.readI64();
               struct.null_counts.add(_elem128);
            }

            struct.setNull_countsIsSet(true);
         }

         if (incoming.get(1)) {
            TList _list130 = iprot.readListBegin((byte)10);
            struct.repetition_level_histograms = new ArrayList(_list130.size);

            for(int _i132 = 0; _i132 < _list130.size; ++_i132) {
               long _elem131 = iprot.readI64();
               struct.repetition_level_histograms.add(_elem131);
            }

            struct.setRepetition_level_histogramsIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list133 = iprot.readListBegin((byte)10);
            struct.definition_level_histograms = new ArrayList(_list133.size);

            for(int _i135 = 0; _i135 < _list133.size; ++_i135) {
               long _elem134 = iprot.readI64();
               struct.definition_level_histograms.add(_elem134);
            }

            struct.setDefinition_level_histogramsIsSet(true);
         }

      }
   }
}
