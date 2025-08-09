package com.univocity.parsers.fixed;

import com.univocity.parsers.annotations.FixedWidth;
import com.univocity.parsers.annotations.HeaderTransformer;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.annotations.helpers.AnnotationRegistry;
import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.annotations.helpers.TransformedHeader;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.CommonSettings;
import com.univocity.parsers.common.NormalizedString;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FixedWidthFields implements Cloneable {
   private List fieldLengths;
   private List fieldsToIgnore;
   private List fieldNames;
   private List fieldAlignment;
   private List fieldPadding;
   private List paddingsToKeep;
   private boolean noNames;
   private int totalLength;

   public FixedWidthFields(LinkedHashMap fields) {
      this.fieldLengths = new ArrayList();
      this.fieldsToIgnore = new ArrayList();
      this.fieldNames = new ArrayList();
      this.fieldAlignment = new ArrayList();
      this.fieldPadding = new ArrayList();
      this.paddingsToKeep = new ArrayList();
      this.noNames = true;
      this.totalLength = 0;
      if (fields != null && !fields.isEmpty()) {
         for(Map.Entry entry : fields.entrySet()) {
            String fieldName = (String)entry.getKey();
            Integer fieldLength = (Integer)entry.getValue();
            this.addField(fieldName, fieldLength);
         }

      } else {
         throw new IllegalArgumentException("Map of fields and their lengths cannot be null/empty");
      }
   }

   public FixedWidthFields(String[] headers, int[] lengths) {
      this.fieldLengths = new ArrayList();
      this.fieldsToIgnore = new ArrayList();
      this.fieldNames = new ArrayList();
      this.fieldAlignment = new ArrayList();
      this.fieldPadding = new ArrayList();
      this.paddingsToKeep = new ArrayList();
      this.noNames = true;
      this.totalLength = 0;
      if (headers != null && headers.length != 0) {
         if (lengths != null && lengths.length != 0) {
            if (headers.length != lengths.length) {
               throw new IllegalArgumentException("Sequence of headers and their respective lengths must match. Got " + headers.length + " headers but " + lengths.length + " lengths");
            } else {
               for(int i = 0; i < headers.length; ++i) {
                  this.addField(headers[i], lengths[i]);
               }

            }
         } else {
            throw new IllegalArgumentException("Field lengths cannot be null/empty");
         }
      } else {
         throw new IllegalArgumentException("Headers cannot be null/empty");
      }
   }

   public FixedWidthFields(int... fieldLengths) {
      this.fieldLengths = new ArrayList();
      this.fieldsToIgnore = new ArrayList();
      this.fieldNames = new ArrayList();
      this.fieldAlignment = new ArrayList();
      this.fieldPadding = new ArrayList();
      this.paddingsToKeep = new ArrayList();
      this.noNames = true;
      this.totalLength = 0;

      for(int i = 0; i < fieldLengths.length; ++i) {
         this.addField(fieldLengths[i]);
      }

   }

   /** @deprecated */
   @Deprecated
   public FixedWidthFields(Class beanClass) {
      this(beanClass, MethodFilter.ONLY_SETTERS);
   }

   public static FixedWidthFields forParsing(Class beanClass) {
      return new FixedWidthFields(beanClass, MethodFilter.ONLY_SETTERS);
   }

   public static FixedWidthFields forWriting(Class beanClass) {
      return new FixedWidthFields(beanClass, MethodFilter.ONLY_GETTERS);
   }

   private FixedWidthFields(Class beanClass, MethodFilter methodFilter) {
      this.fieldLengths = new ArrayList();
      this.fieldsToIgnore = new ArrayList();
      this.fieldNames = new ArrayList();
      this.fieldAlignment = new ArrayList();
      this.fieldPadding = new ArrayList();
      this.paddingsToKeep = new ArrayList();
      this.noNames = true;
      this.totalLength = 0;
      if (beanClass == null) {
         throw new IllegalArgumentException("Class must not be null.");
      } else {
         List<TransformedHeader> fieldSequence = AnnotationHelper.getFieldSequence(beanClass, true, (HeaderTransformer)null, methodFilter);
         if (fieldSequence.isEmpty()) {
            throw new IllegalArgumentException("Can't derive fixed-width fields from class '" + beanClass.getName() + "'. No @Parsed annotations found.");
         } else {
            Set<String> fieldNamesWithoutConfig = new LinkedHashSet();

            for(TransformedHeader field : fieldSequence) {
               if (field != null) {
                  String fieldName = field.getHeaderName();
                  FixedWidth fw = (FixedWidth)AnnotationHelper.findAnnotation(field.getTarget(), FixedWidth.class);
                  if (fw != null) {
                     int length = (Integer)AnnotationRegistry.getValue(field.getTarget(), fw, "value", fw.value());
                     int from = (Integer)AnnotationRegistry.getValue(field.getTarget(), fw, "from", fw.from());
                     int to = (Integer)AnnotationRegistry.getValue(field.getTarget(), fw, "to", fw.to());
                     FieldAlignment alignment = (FieldAlignment)AnnotationRegistry.getValue(field.getTarget(), fw, "alignment", fw.alignment());
                     char padding = (Character)AnnotationRegistry.getValue(field.getTarget(), fw, "padding", fw.padding());
                     if (length != -1) {
                        if (from != -1 || to != -1) {
                           throw new IllegalArgumentException("Can't initialize fixed-width field from " + field.describe() + ". " + "Can't have field length (" + length + ") defined along with position from (" + from + ") and to (" + to + ")");
                        }

                        this.addField(fieldName, length, alignment, padding);
                     } else {
                        if (from == -1 || to == -1) {
                           throw new IllegalArgumentException("Can't initialize fixed-width field from " + field.describe() + "'. " + "Field length/position undefined defined");
                        }

                        this.addField(fieldName, from, to, alignment, padding);
                     }

                     boolean keepPadding = (Boolean)AnnotationRegistry.getValue(field.getTarget(), fw, "keepPadding", fw.keepPadding());
                     this.setKeepPaddingFlag(keepPadding, this.fieldLengths.size() - 1);
                  } else {
                     fieldNamesWithoutConfig.add(field.getTargetName());
                  }
               }
            }

            if (fieldNamesWithoutConfig.size() > 0) {
               throw new IllegalArgumentException("Can't derive fixed-width fields from class '" + beanClass.getName() + "'. " + "The following fields don't have a @FixedWidth annotation: " + fieldNamesWithoutConfig);
            }
         }
      }
   }

   public FixedWidthFields addField(int startPosition, int endPosition) {
      return this.addField((String)null, startPosition, endPosition, FieldAlignment.LEFT, '\u0000');
   }

   public FixedWidthFields addField(String name, int startPosition, int endPosition) {
      return this.addField(name, startPosition, endPosition, FieldAlignment.LEFT, '\u0000');
   }

   public FixedWidthFields addField(String name, int startPosition, int endPosition, char padding) {
      return this.addField(name, startPosition, endPosition, FieldAlignment.LEFT, padding);
   }

   public FixedWidthFields addField(String name, int startPosition, int endPosition, FieldAlignment alignment) {
      return this.addField(name, startPosition, endPosition, alignment, '\u0000');
   }

   public FixedWidthFields addField(int startPosition, int endPosition, FieldAlignment alignment) {
      return this.addField((String)null, startPosition, endPosition, alignment, '\u0000');
   }

   public FixedWidthFields addField(int startPosition, int endPosition, FieldAlignment alignment, char padding) {
      return this.addField((String)null, startPosition, endPosition, alignment, padding);
   }

   public FixedWidthFields addField(int startPosition, int endPosition, char padding) {
      return this.addField((String)null, startPosition, endPosition, FieldAlignment.LEFT, padding);
   }

   public FixedWidthFields addField(String name, int startPosition, int endPosition, FieldAlignment alignment, char padding) {
      int length = endPosition - startPosition;
      if (startPosition < this.totalLength) {
         throw new IllegalArgumentException("Start position '" + startPosition + "' overlaps with one or more fields");
      } else {
         if (startPosition > this.totalLength) {
            this.addField((String)null, startPosition - this.totalLength, FieldAlignment.LEFT, '\u0000');
            this.fieldsToIgnore.set(this.fieldsToIgnore.size() - 1, Boolean.TRUE);
         }

         return this.addField(name, length, alignment, padding);
      }
   }

   boolean[] getFieldsToIgnore() {
      boolean[] out = new boolean[this.fieldsToIgnore.size()];

      for(int i = 0; i < this.fieldsToIgnore.size(); ++i) {
         out[i] = (Boolean)this.fieldsToIgnore.get(i);
      }

      return out;
   }

   Boolean[] getKeepPaddingFlags() {
      return (Boolean[])this.paddingsToKeep.toArray(new Boolean[0]);
   }

   public FixedWidthFields addField(int length) {
      return this.addField((String)null, length, FieldAlignment.LEFT, '\u0000');
   }

   public FixedWidthFields addField(int length, FieldAlignment alignment) {
      return this.addField((String)null, length, alignment, '\u0000');
   }

   public FixedWidthFields addField(String name, int length) {
      return this.addField(name, length, FieldAlignment.LEFT, '\u0000');
   }

   public FixedWidthFields addField(String name, int length, FieldAlignment alignment) {
      return this.addField(name, length, alignment, '\u0000');
   }

   public FixedWidthFields addField(int length, char padding) {
      return this.addField((String)null, length, FieldAlignment.LEFT, padding);
   }

   public FixedWidthFields addField(int length, FieldAlignment alignment, char padding) {
      return this.addField((String)null, length, alignment, padding);
   }

   public FixedWidthFields addField(String name, int length, char padding) {
      return this.addField(name, length, FieldAlignment.LEFT, padding);
   }

   public FixedWidthFields addField(String name, int length, FieldAlignment alignment, char padding) {
      this.validateLength(name, length);
      this.fieldLengths.add(length);
      this.fieldsToIgnore.add(Boolean.FALSE);
      this.fieldNames.add(NormalizedString.valueOf(name));
      this.fieldPadding.add(padding);
      this.paddingsToKeep.add((Object)null);
      if (name != null) {
         this.noNames = false;
      }

      this.fieldAlignment.add(alignment);
      this.totalLength += length;
      return this;
   }

   private void validateLength(String name, int length) {
      if (length < 1) {
         if (name == null) {
            throw new IllegalArgumentException("Invalid field length: " + length + " for field at index " + this.fieldLengths.size());
         } else {
            throw new IllegalArgumentException("Invalid field length: " + length + " for field " + name);
         }
      }
   }

   public int getFieldsPerRecord() {
      return this.fieldLengths.size();
   }

   public NormalizedString[] getFieldNames() {
      return this.noNames ? null : (NormalizedString[])this.getSelectedElements(this.fieldNames).toArray(ArgumentUtils.EMPTY_NORMALIZED_STRING_ARRAY);
   }

   private List getSelectedElements(List elements) {
      List<T> out = new ArrayList();

      for(int i = 0; i < elements.size(); ++i) {
         if (!(Boolean)this.fieldsToIgnore.get(i)) {
            out.add(elements.get(i));
         }
      }

      return out;
   }

   public int[] getFieldLengths() {
      return ArgumentUtils.toIntArray(this.getSelectedElements(this.fieldLengths));
   }

   int[] getAllLengths() {
      return ArgumentUtils.toIntArray(this.fieldLengths);
   }

   public void setFieldLength(String name, int newLength) {
      if (name == null) {
         throw new IllegalArgumentException("Field name cannot be null");
      } else {
         int index = this.fieldNames.indexOf(name);
         if (index == -1) {
            throw new IllegalArgumentException("Cannot find field with name '" + name + '\'');
         } else {
            this.validateLength(name, newLength);
            this.fieldLengths.set(index, newLength);
         }
      }
   }

   public void setFieldLength(int position, int newLength) {
      this.validateIndex(position);
      this.validateLength("at index " + position, newLength);
      this.fieldLengths.set(position, newLength);
   }

   public void setAlignment(FieldAlignment alignment, int... positions) {
      for(int position : positions) {
         this.setAlignment(position, alignment);
      }

   }

   public void setAlignment(FieldAlignment alignment, String... names) {
      for(String name : names) {
         int position = this.indexOf(name);
         this.setAlignment(position, alignment);
      }

   }

   private void validateIndex(int position) {
      if (position < 0 && position >= this.fieldLengths.size()) {
         throw new IllegalArgumentException("No field defined at index " + position);
      }
   }

   public int indexOf(String fieldName) {
      if (this.noNames) {
         throw new IllegalArgumentException("No field names defined");
      } else if (fieldName != null && !fieldName.trim().isEmpty()) {
         NormalizedString normalizedFieldName = NormalizedString.valueOf(fieldName);
         int i = 0;

         for(NormalizedString name : this.fieldNames) {
            if (name.equals(normalizedFieldName)) {
               return i;
            }

            ++i;
         }

         return -1;
      } else {
         throw new IllegalArgumentException("Field name cannot be null/empty");
      }
   }

   private void setAlignment(int position, FieldAlignment alignment) {
      if (alignment == null) {
         throw new IllegalArgumentException("Alignment cannot be null");
      } else {
         this.validateIndex(position);
         this.fieldAlignment.set(position, alignment);
      }
   }

   public FieldAlignment getAlignment(int position) {
      this.validateIndex(position);
      return (FieldAlignment)this.fieldAlignment.get(position);
   }

   public FieldAlignment getAlignment(String fieldName) {
      int index = this.indexOf(fieldName);
      if (index == -1) {
         throw new IllegalArgumentException("Field '" + fieldName + "' does not exist. Available field names are: " + this.fieldNames);
      } else {
         return this.getAlignment(index);
      }
   }

   public FieldAlignment[] getFieldAlignments() {
      return (FieldAlignment[])this.fieldAlignment.toArray(new FieldAlignment[this.fieldAlignment.size()]);
   }

   public char[] getFieldPaddings() {
      return ArgumentUtils.toCharArray(this.fieldPadding);
   }

   char[] getFieldPaddings(FixedWidthFormat format) {
      char[] out = this.getFieldPaddings();

      for(int i = 0; i < out.length; ++i) {
         if (out[i] == 0) {
            out[i] = format.getPadding();
         }
      }

      return out;
   }

   public void setPadding(char padding, int... positions) {
      for(int position : positions) {
         this.setPadding(position, padding);
      }

   }

   public void setPadding(char padding, String... names) {
      for(String name : names) {
         int position = this.indexOf(name);
         this.setPadding(position, padding);
      }

   }

   private void setPadding(int position, char padding) {
      if (padding == 0) {
         throw new IllegalArgumentException("Cannot use the null character as padding");
      } else {
         this.validateIndex(position);
         this.fieldPadding.set(position, padding);
      }
   }

   public void keepPaddingOn(int position, int... positions) {
      this.setKeepPaddingFlag(true, position, positions);
   }

   public void keepPaddingOn(String name, String... names) {
      this.setKeepPaddingFlag(true, name, names);
   }

   public void stripPaddingFrom(int position, int... positions) {
      this.setKeepPaddingFlag(false, position, positions);
   }

   public void stripPaddingFrom(String name, String... names) {
      this.setKeepPaddingFlag(false, name, names);
   }

   private void setKeepPaddingFlag(boolean keep, int position, int... positions) {
      this.setPaddingToKeep(position, keep);

      for(int p : positions) {
         this.setPaddingToKeep(p, keep);
      }

   }

   private void setKeepPaddingFlag(boolean keep, String name, String... names) {
      int position = this.indexOf(name);
      this.setPaddingToKeep(position, keep);

      for(String n : names) {
         position = this.indexOf(n);
         this.setPaddingToKeep(position, keep);
      }

   }

   private void setPaddingToKeep(int position, boolean keepPaddingFlag) {
      this.validateIndex(position);
      this.paddingsToKeep.set(position, keepPaddingFlag);
   }

   public String toString() {
      StringBuilder out = new StringBuilder();
      int i = 0;

      for(Integer length : this.fieldLengths) {
         out.append("\n\t\t").append(i + 1).append('\t');
         if (i < this.fieldNames.size()) {
            out.append((CharSequence)this.fieldNames.get(i));
         }

         out.append(", length: ").append(length);
         out.append(", align: ").append(this.fieldAlignment.get(i));
         out.append(", padding: ").append(this.fieldPadding.get(i));
         out.append(", keepPadding: ").append(this.paddingsToKeep.get(i));
         ++i;
      }

      return out.toString();
   }

   static void setHeadersIfPossible(FixedWidthFields fieldLengths, CommonSettings settings) {
      if (fieldLengths != null && settings.getHeaders() == null) {
         NormalizedString[] headers = fieldLengths.getFieldNames();
         if (headers != null) {
            int[] lengths = fieldLengths.getFieldLengths();
            if (lengths.length == headers.length) {
               settings.setHeaders(NormalizedString.toArray(headers));
            }
         }
      }

   }

   protected FixedWidthFields clone() {
      try {
         FixedWidthFields out = (FixedWidthFields)super.clone();
         out.fieldLengths = new ArrayList(this.fieldLengths);
         out.fieldNames = new ArrayList(this.fieldNames);
         out.fieldAlignment = new ArrayList(this.fieldAlignment);
         out.fieldPadding = new ArrayList(this.fieldPadding);
         out.paddingsToKeep = new ArrayList(this.paddingsToKeep);
         return out;
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }
}
