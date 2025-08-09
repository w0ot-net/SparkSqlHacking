package org.apache.orc.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class FieldSet {
   private static final int DEFAULT_FIELD_MAP_ARRAY_SIZE = 16;
   private final SmallSortedMap fields;
   private boolean isImmutable;
   private boolean hasLazyField;
   private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);

   private FieldSet() {
      this.fields = SmallSortedMap.newFieldMap(16);
   }

   private FieldSet(final boolean dummy) {
      this(SmallSortedMap.newFieldMap(0));
      this.makeImmutable();
   }

   private FieldSet(SmallSortedMap fields) {
      this.fields = fields;
      this.makeImmutable();
   }

   public static FieldSet newFieldSet() {
      return new FieldSet();
   }

   public static FieldSet emptySet() {
      return DEFAULT_INSTANCE;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   boolean isEmpty() {
      return this.fields.isEmpty();
   }

   public void makeImmutable() {
      if (!this.isImmutable) {
         for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
            Map.Entry<T, Object> entry = this.fields.getArrayEntryAt(i);
            if (entry.getValue() instanceof GeneratedMessageLite) {
               ((GeneratedMessageLite)entry.getValue()).makeImmutable();
            }
         }

         this.fields.makeImmutable();
         this.isImmutable = true;
      }
   }

   public boolean isImmutable() {
      return this.isImmutable;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof FieldSet)) {
         return false;
      } else {
         FieldSet<?> other = (FieldSet)o;
         return this.fields.equals(other.fields);
      }
   }

   public int hashCode() {
      return this.fields.hashCode();
   }

   public FieldSet clone() {
      FieldSet<T> clone = newFieldSet();

      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         Map.Entry<T, Object> entry = this.fields.getArrayEntryAt(i);
         clone.setField((FieldDescriptorLite)entry.getKey(), entry.getValue());
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         clone.setField((FieldDescriptorLite)entry.getKey(), entry.getValue());
      }

      clone.hasLazyField = this.hasLazyField;
      return clone;
   }

   public void clear() {
      this.fields.clear();
      this.hasLazyField = false;
   }

   public Map getAllFields() {
      if (this.hasLazyField) {
         SmallSortedMap<T, Object> result = cloneAllFieldsMap(this.fields, false);
         if (this.fields.isImmutable()) {
            result.makeImmutable();
         }

         return result;
      } else {
         return (Map)(this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields));
      }
   }

   private static SmallSortedMap cloneAllFieldsMap(SmallSortedMap fields, boolean copyList) {
      SmallSortedMap<T, Object> result = SmallSortedMap.newFieldMap(16);

      for(int i = 0; i < fields.getNumArrayEntries(); ++i) {
         cloneFieldEntry(result, fields.getArrayEntryAt(i), copyList);
      }

      for(Map.Entry entry : fields.getOverflowEntries()) {
         cloneFieldEntry(result, entry, copyList);
      }

      return result;
   }

   private static void cloneFieldEntry(Map map, Map.Entry entry, boolean copyList) {
      T key = (T)((FieldDescriptorLite)entry.getKey());
      Object value = entry.getValue();
      if (value instanceof LazyField) {
         map.put(key, ((LazyField)value).getValue());
      } else if (copyList && value instanceof List) {
         map.put(key, new ArrayList((List)value));
      } else {
         map.put(key, value);
      }

   }

   public Iterator iterator() {
      return (Iterator)(this.hasLazyField ? new LazyField.LazyIterator(this.fields.entrySet().iterator()) : this.fields.entrySet().iterator());
   }

   Iterator descendingIterator() {
      return (Iterator)(this.hasLazyField ? new LazyField.LazyIterator(this.fields.descendingEntrySet().iterator()) : this.fields.descendingEntrySet().iterator());
   }

   public boolean hasField(final FieldDescriptorLite descriptor) {
      if (descriptor.isRepeated()) {
         throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
      } else {
         return this.fields.get(descriptor) != null;
      }
   }

   public Object getField(final FieldDescriptorLite descriptor) {
      Object o = this.fields.get(descriptor);
      return o instanceof LazyField ? ((LazyField)o).getValue() : o;
   }

   public void setField(final FieldDescriptorLite descriptor, Object value) {
      if (descriptor.isRepeated()) {
         if (!(value instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
         }

         List newList = new ArrayList();
         newList.addAll((List)value);

         for(Object element : newList) {
            this.verifyType(descriptor, element);
         }

         value = newList;
      } else {
         this.verifyType(descriptor, value);
      }

      if (value instanceof LazyField) {
         this.hasLazyField = true;
      }

      this.fields.put((Comparable)descriptor, value);
   }

   public void clearField(final FieldDescriptorLite descriptor) {
      this.fields.remove(descriptor);
      if (this.fields.isEmpty()) {
         this.hasLazyField = false;
      }

   }

   public int getRepeatedFieldCount(final FieldDescriptorLite descriptor) {
      if (!descriptor.isRepeated()) {
         throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
      } else {
         Object value = this.getField(descriptor);
         return value == null ? 0 : ((List)value).size();
      }
   }

   public Object getRepeatedField(final FieldDescriptorLite descriptor, final int index) {
      if (!descriptor.isRepeated()) {
         throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
      } else {
         Object value = this.getField(descriptor);
         if (value == null) {
            throw new IndexOutOfBoundsException();
         } else {
            return ((List)value).get(index);
         }
      }
   }

   public void setRepeatedField(final FieldDescriptorLite descriptor, final int index, final Object value) {
      if (!descriptor.isRepeated()) {
         throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
      } else {
         Object list = this.getField(descriptor);
         if (list == null) {
            throw new IndexOutOfBoundsException();
         } else {
            this.verifyType(descriptor, value);
            ((List)list).set(index, value);
         }
      }
   }

   public void addRepeatedField(final FieldDescriptorLite descriptor, final Object value) {
      if (!descriptor.isRepeated()) {
         throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
      } else {
         this.verifyType(descriptor, value);
         Object existingValue = this.getField(descriptor);
         List<Object> list;
         if (existingValue == null) {
            list = new ArrayList();
            this.fields.put((Comparable)descriptor, list);
         } else {
            list = (List)existingValue;
         }

         list.add(value);
      }
   }

   private void verifyType(final FieldDescriptorLite descriptor, final Object value) {
      if (!isValidType(descriptor.getLiteType(), value)) {
         throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", descriptor.getNumber(), descriptor.getLiteType().getJavaType(), value.getClass().getName()));
      }
   }

   private static boolean isValidType(final WireFormat.FieldType type, final Object value) {
      Internal.checkNotNull(value);
      switch (type.getJavaType()) {
         case INT:
            return value instanceof Integer;
         case LONG:
            return value instanceof Long;
         case FLOAT:
            return value instanceof Float;
         case DOUBLE:
            return value instanceof Double;
         case BOOLEAN:
            return value instanceof Boolean;
         case STRING:
            return value instanceof String;
         case BYTE_STRING:
            return value instanceof ByteString || value instanceof byte[];
         case ENUM:
            return value instanceof Integer || value instanceof Internal.EnumLite;
         case MESSAGE:
            return value instanceof MessageLite || value instanceof LazyField;
         default:
            return false;
      }
   }

   public boolean isInitialized() {
      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         if (!isInitialized(this.fields.getArrayEntryAt(i))) {
            return false;
         }
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         if (!isInitialized(entry)) {
            return false;
         }
      }

      return true;
   }

   private static boolean isInitialized(final Map.Entry entry) {
      T descriptor = (T)((FieldDescriptorLite)entry.getKey());
      if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
         if (!descriptor.isRepeated()) {
            return isMessageFieldValueInitialized(entry.getValue());
         }

         for(Object element : (List)entry.getValue()) {
            if (!isMessageFieldValueInitialized(element)) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean isMessageFieldValueInitialized(Object value) {
      if (value instanceof MessageLiteOrBuilder) {
         return ((MessageLiteOrBuilder)value).isInitialized();
      } else if (value instanceof LazyField) {
         return true;
      } else {
         throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      }
   }

   static int getWireFormatForFieldType(final WireFormat.FieldType type, boolean isPacked) {
      return isPacked ? 2 : type.getWireType();
   }

   public void mergeFrom(final FieldSet other) {
      for(int i = 0; i < other.fields.getNumArrayEntries(); ++i) {
         this.mergeFromField(other.fields.getArrayEntryAt(i));
      }

      for(Map.Entry entry : other.fields.getOverflowEntries()) {
         this.mergeFromField(entry);
      }

   }

   private static Object cloneIfMutable(Object value) {
      if (value instanceof byte[]) {
         byte[] bytes = (byte[])value;
         byte[] copy = new byte[bytes.length];
         System.arraycopy(bytes, 0, copy, 0, bytes.length);
         return copy;
      } else {
         return value;
      }
   }

   private void mergeFromField(final Map.Entry entry) {
      T descriptor = (T)((FieldDescriptorLite)entry.getKey());
      Object otherValue = entry.getValue();
      if (otherValue instanceof LazyField) {
         otherValue = ((LazyField)otherValue).getValue();
      }

      if (descriptor.isRepeated()) {
         Object value = this.getField(descriptor);
         if (value == null) {
            value = new ArrayList();
         }

         for(Object element : (List)otherValue) {
            ((List)value).add(cloneIfMutable(element));
         }

         this.fields.put((Comparable)descriptor, value);
      } else if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
         Object value = this.getField(descriptor);
         if (value == null) {
            this.fields.put((Comparable)descriptor, cloneIfMutable(otherValue));
         } else {
            Object var8 = descriptor.internalMergeFrom(((MessageLite)value).toBuilder(), (MessageLite)otherValue).build();
            this.fields.put((Comparable)descriptor, var8);
         }
      } else {
         this.fields.put((Comparable)descriptor, cloneIfMutable(otherValue));
      }

   }

   public static Object readPrimitiveField(CodedInputStream input, final WireFormat.FieldType type, boolean checkUtf8) throws IOException {
      return checkUtf8 ? WireFormat.readPrimitiveField(input, type, WireFormat.Utf8Validation.STRICT) : WireFormat.readPrimitiveField(input, type, WireFormat.Utf8Validation.LOOSE);
   }

   public void writeTo(final CodedOutputStream output) throws IOException {
      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         Map.Entry<T, Object> entry = this.fields.getArrayEntryAt(i);
         writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), output);
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), output);
      }

   }

   public void writeMessageSetTo(final CodedOutputStream output) throws IOException {
      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         this.writeMessageSetTo(this.fields.getArrayEntryAt(i), output);
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         this.writeMessageSetTo(entry, output);
      }

   }

   private void writeMessageSetTo(final Map.Entry entry, final CodedOutputStream output) throws IOException {
      T descriptor = (T)((FieldDescriptorLite)entry.getKey());
      if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !descriptor.isRepeated() && !descriptor.isPacked()) {
         Object value = entry.getValue();
         if (value instanceof LazyField) {
            ByteString valueBytes = ((LazyField)value).toByteString();
            output.writeRawMessageSetExtension(((FieldDescriptorLite)entry.getKey()).getNumber(), valueBytes);
         } else {
            output.writeMessageSetExtension(((FieldDescriptorLite)entry.getKey()).getNumber(), (MessageLite)value);
         }
      } else {
         writeField(descriptor, entry.getValue(), output);
      }

   }

   static void writeElement(final CodedOutputStream output, final WireFormat.FieldType type, final int number, final Object value) throws IOException {
      if (type == WireFormat.FieldType.GROUP) {
         output.writeGroup(number, (MessageLite)value);
      } else {
         output.writeTag(number, getWireFormatForFieldType(type, false));
         writeElementNoTag(output, type, value);
      }

   }

   static void writeElementNoTag(final CodedOutputStream output, final WireFormat.FieldType type, final Object value) throws IOException {
      switch (type) {
         case DOUBLE:
            output.writeDoubleNoTag((Double)value);
            break;
         case FLOAT:
            output.writeFloatNoTag((Float)value);
            break;
         case INT64:
            output.writeInt64NoTag((Long)value);
            break;
         case UINT64:
            output.writeUInt64NoTag((Long)value);
            break;
         case INT32:
            output.writeInt32NoTag((Integer)value);
            break;
         case FIXED64:
            output.writeFixed64NoTag((Long)value);
            break;
         case FIXED32:
            output.writeFixed32NoTag((Integer)value);
            break;
         case BOOL:
            output.writeBoolNoTag((Boolean)value);
            break;
         case GROUP:
            output.writeGroupNoTag((MessageLite)value);
            break;
         case MESSAGE:
            output.writeMessageNoTag((MessageLite)value);
            break;
         case STRING:
            if (value instanceof ByteString) {
               output.writeBytesNoTag((ByteString)value);
            } else {
               output.writeStringNoTag((String)value);
            }
            break;
         case BYTES:
            if (value instanceof ByteString) {
               output.writeBytesNoTag((ByteString)value);
            } else {
               output.writeByteArrayNoTag((byte[])value);
            }
            break;
         case UINT32:
            output.writeUInt32NoTag((Integer)value);
            break;
         case SFIXED32:
            output.writeSFixed32NoTag((Integer)value);
            break;
         case SFIXED64:
            output.writeSFixed64NoTag((Long)value);
            break;
         case SINT32:
            output.writeSInt32NoTag((Integer)value);
            break;
         case SINT64:
            output.writeSInt64NoTag((Long)value);
            break;
         case ENUM:
            if (value instanceof Internal.EnumLite) {
               output.writeEnumNoTag(((Internal.EnumLite)value).getNumber());
            } else {
               output.writeEnumNoTag((Integer)value);
            }
      }

   }

   public static void writeField(final FieldDescriptorLite descriptor, final Object value, final CodedOutputStream output) throws IOException {
      WireFormat.FieldType type = descriptor.getLiteType();
      int number = descriptor.getNumber();
      if (descriptor.isRepeated()) {
         List<?> valueList = (List)value;
         if (descriptor.isPacked()) {
            if (valueList.isEmpty()) {
               return;
            }

            output.writeTag(number, 2);
            int dataSize = 0;

            for(Object element : valueList) {
               dataSize += computeElementSizeNoTag(type, element);
            }

            output.writeUInt32NoTag(dataSize);

            for(Object element : valueList) {
               writeElementNoTag(output, type, element);
            }
         } else {
            for(Object element : valueList) {
               writeElement(output, type, number, element);
            }
         }
      } else if (value instanceof LazyField) {
         writeElement(output, type, number, ((LazyField)value).getValue());
      } else {
         writeElement(output, type, number, value);
      }

   }

   public int getSerializedSize() {
      int size = 0;

      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         Map.Entry<T, Object> entry = this.fields.getArrayEntryAt(i);
         size += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue());
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         size += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue());
      }

      return size;
   }

   public int getMessageSetSerializedSize() {
      int size = 0;

      for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
         size += this.getMessageSetSerializedSize(this.fields.getArrayEntryAt(i));
      }

      for(Map.Entry entry : this.fields.getOverflowEntries()) {
         size += this.getMessageSetSerializedSize(entry);
      }

      return size;
   }

   private int getMessageSetSerializedSize(final Map.Entry entry) {
      T descriptor = (T)((FieldDescriptorLite)entry.getKey());
      Object value = entry.getValue();
      if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !descriptor.isRepeated() && !descriptor.isPacked()) {
         return value instanceof LazyField ? CodedOutputStream.computeLazyFieldMessageSetExtensionSize(((FieldDescriptorLite)entry.getKey()).getNumber(), (LazyField)value) : CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)entry.getKey()).getNumber(), (MessageLite)value);
      } else {
         return computeFieldSize(descriptor, value);
      }
   }

   static int computeElementSize(final WireFormat.FieldType type, final int number, final Object value) {
      int tagSize = CodedOutputStream.computeTagSize(number);
      if (type == WireFormat.FieldType.GROUP) {
         tagSize *= 2;
      }

      return tagSize + computeElementSizeNoTag(type, value);
   }

   static int computeElementSizeNoTag(final WireFormat.FieldType type, final Object value) {
      switch (type) {
         case DOUBLE:
            return CodedOutputStream.computeDoubleSizeNoTag((Double)value);
         case FLOAT:
            return CodedOutputStream.computeFloatSizeNoTag((Float)value);
         case INT64:
            return CodedOutputStream.computeInt64SizeNoTag((Long)value);
         case UINT64:
            return CodedOutputStream.computeUInt64SizeNoTag((Long)value);
         case INT32:
            return CodedOutputStream.computeInt32SizeNoTag((Integer)value);
         case FIXED64:
            return CodedOutputStream.computeFixed64SizeNoTag((Long)value);
         case FIXED32:
            return CodedOutputStream.computeFixed32SizeNoTag((Integer)value);
         case BOOL:
            return CodedOutputStream.computeBoolSizeNoTag((Boolean)value);
         case GROUP:
            return CodedOutputStream.computeGroupSizeNoTag((MessageLite)value);
         case MESSAGE:
            if (value instanceof LazyField) {
               return CodedOutputStream.computeLazyFieldSizeNoTag((LazyField)value);
            }

            return CodedOutputStream.computeMessageSizeNoTag((MessageLite)value);
         case STRING:
            if (value instanceof ByteString) {
               return CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
            }

            return CodedOutputStream.computeStringSizeNoTag((String)value);
         case BYTES:
            if (value instanceof ByteString) {
               return CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
            }

            return CodedOutputStream.computeByteArraySizeNoTag((byte[])value);
         case UINT32:
            return CodedOutputStream.computeUInt32SizeNoTag((Integer)value);
         case SFIXED32:
            return CodedOutputStream.computeSFixed32SizeNoTag((Integer)value);
         case SFIXED64:
            return CodedOutputStream.computeSFixed64SizeNoTag((Long)value);
         case SINT32:
            return CodedOutputStream.computeSInt32SizeNoTag((Integer)value);
         case SINT64:
            return CodedOutputStream.computeSInt64SizeNoTag((Long)value);
         case ENUM:
            if (value instanceof Internal.EnumLite) {
               return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)value).getNumber());
            }

            return CodedOutputStream.computeEnumSizeNoTag((Integer)value);
         default:
            throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      }
   }

   public static int computeFieldSize(final FieldDescriptorLite descriptor, final Object value) {
      WireFormat.FieldType type = descriptor.getLiteType();
      int number = descriptor.getNumber();
      if (!descriptor.isRepeated()) {
         return computeElementSize(type, number, value);
      } else {
         List<?> valueList = (List)value;
         if (descriptor.isPacked()) {
            if (valueList.isEmpty()) {
               return 0;
            } else {
               int dataSize = 0;

               for(Object element : valueList) {
                  dataSize += computeElementSizeNoTag(type, element);
               }

               return dataSize + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(dataSize);
            }
         } else {
            int size = 0;

            for(Object element : valueList) {
               size += computeElementSize(type, number, element);
            }

            return size;
         }
      }
   }

   static final class Builder {
      private SmallSortedMap fields;
      private boolean hasLazyField;
      private boolean isMutable;
      private boolean hasNestedBuilders;

      private Builder() {
         this(SmallSortedMap.newFieldMap(16));
      }

      private Builder(SmallSortedMap fields) {
         this.fields = fields;
         this.isMutable = true;
      }

      public FieldSet build() {
         return this.buildImpl(false);
      }

      public FieldSet buildPartial() {
         return this.buildImpl(true);
      }

      private FieldSet buildImpl(boolean partial) {
         if (this.fields.isEmpty()) {
            return FieldSet.emptySet();
         } else {
            this.isMutable = false;
            SmallSortedMap<T, Object> fieldsForBuild = this.fields;
            if (this.hasNestedBuilders) {
               fieldsForBuild = FieldSet.cloneAllFieldsMap(this.fields, false);
               replaceBuilders(fieldsForBuild, partial);
            }

            FieldSet<T> fieldSet = new FieldSet(fieldsForBuild);
            fieldSet.hasLazyField = this.hasLazyField;
            return fieldSet;
         }
      }

      private static void replaceBuilders(SmallSortedMap fieldMap, boolean partial) {
         for(int i = 0; i < fieldMap.getNumArrayEntries(); ++i) {
            replaceBuilders(fieldMap.getArrayEntryAt(i), partial);
         }

         for(Map.Entry entry : fieldMap.getOverflowEntries()) {
            replaceBuilders(entry, partial);
         }

      }

      private static void replaceBuilders(Map.Entry entry, boolean partial) {
         entry.setValue(replaceBuilders((FieldDescriptorLite)entry.getKey(), entry.getValue(), partial));
      }

      private static Object replaceBuilders(FieldDescriptorLite descriptor, Object value, boolean partial) {
         if (value == null) {
            return value;
         } else if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            if (descriptor.isRepeated()) {
               if (!(value instanceof List)) {
                  throw new IllegalStateException("Repeated field should contains a List but actually contains type: " + value.getClass());
               } else {
                  List<Object> list = (List)value;

                  for(int i = 0; i < list.size(); ++i) {
                     Object oldElement = list.get(i);
                     Object newElement = replaceBuilder(oldElement, partial);
                     if (newElement != oldElement) {
                        if (list == value) {
                           list = new ArrayList(list);
                        }

                        list.set(i, newElement);
                     }
                  }

                  return list;
               }
            } else {
               return replaceBuilder(value, partial);
            }
         } else {
            return value;
         }
      }

      private static Object replaceBuilder(Object value, boolean partial) {
         if (!(value instanceof MessageLite.Builder)) {
            return value;
         } else {
            MessageLite.Builder builder = (MessageLite.Builder)value;
            return partial ? builder.buildPartial() : builder.build();
         }
      }

      public static Builder fromFieldSet(FieldSet fieldSet) {
         Builder<T> builder = new Builder(FieldSet.cloneAllFieldsMap(fieldSet.fields, true));
         builder.hasLazyField = fieldSet.hasLazyField;
         return builder;
      }

      public Map getAllFields() {
         if (this.hasLazyField) {
            SmallSortedMap<T, Object> result = FieldSet.cloneAllFieldsMap(this.fields, false);
            if (this.fields.isImmutable()) {
               result.makeImmutable();
            } else {
               replaceBuilders(result, true);
            }

            return result;
         } else {
            return (Map)(this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields));
         }
      }

      public boolean hasField(final FieldDescriptorLite descriptor) {
         if (descriptor.isRepeated()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
         } else {
            return this.fields.get(descriptor) != null;
         }
      }

      public Object getField(final FieldDescriptorLite descriptor) {
         Object value = this.getFieldAllowBuilders(descriptor);
         return replaceBuilders(descriptor, value, true);
      }

      Object getFieldAllowBuilders(final FieldDescriptorLite descriptor) {
         Object o = this.fields.get(descriptor);
         return o instanceof LazyField ? ((LazyField)o).getValue() : o;
      }

      private void ensureIsMutable() {
         if (!this.isMutable) {
            this.fields = FieldSet.cloneAllFieldsMap(this.fields, true);
            this.isMutable = true;
         }

      }

      public void setField(final FieldDescriptorLite descriptor, Object value) {
         this.ensureIsMutable();
         if (descriptor.isRepeated()) {
            if (!(value instanceof List)) {
               throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }

            List newList = new ArrayList((List)value);

            for(Object element : newList) {
               this.verifyType(descriptor, element);
               this.hasNestedBuilders = this.hasNestedBuilders || element instanceof MessageLite.Builder;
            }

            value = newList;
         } else {
            this.verifyType(descriptor, value);
         }

         if (value instanceof LazyField) {
            this.hasLazyField = true;
         }

         this.hasNestedBuilders = this.hasNestedBuilders || value instanceof MessageLite.Builder;
         this.fields.put((Comparable)descriptor, value);
      }

      public void clearField(final FieldDescriptorLite descriptor) {
         this.ensureIsMutable();
         this.fields.remove(descriptor);
         if (this.fields.isEmpty()) {
            this.hasLazyField = false;
         }

      }

      public int getRepeatedFieldCount(final FieldDescriptorLite descriptor) {
         if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedFieldCount() can only be called on repeated fields.");
         } else {
            Object value = this.getFieldAllowBuilders(descriptor);
            return value == null ? 0 : ((List)value).size();
         }
      }

      public Object getRepeatedField(final FieldDescriptorLite descriptor, final int index) {
         if (this.hasNestedBuilders) {
            this.ensureIsMutable();
         }

         Object value = this.getRepeatedFieldAllowBuilders(descriptor, index);
         return replaceBuilder(value, true);
      }

      Object getRepeatedFieldAllowBuilders(final FieldDescriptorLite descriptor, final int index) {
         if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
         } else {
            Object value = this.getFieldAllowBuilders(descriptor);
            if (value == null) {
               throw new IndexOutOfBoundsException();
            } else {
               return ((List)value).get(index);
            }
         }
      }

      public void setRepeatedField(final FieldDescriptorLite descriptor, final int index, final Object value) {
         this.ensureIsMutable();
         if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
         } else {
            this.hasNestedBuilders = this.hasNestedBuilders || value instanceof MessageLite.Builder;
            Object list = this.getFieldAllowBuilders(descriptor);
            if (list == null) {
               throw new IndexOutOfBoundsException();
            } else {
               this.verifyType(descriptor, value);
               ((List)list).set(index, value);
            }
         }
      }

      public void addRepeatedField(final FieldDescriptorLite descriptor, final Object value) {
         this.ensureIsMutable();
         if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
         } else {
            this.hasNestedBuilders = this.hasNestedBuilders || value instanceof MessageLite.Builder;
            this.verifyType(descriptor, value);
            Object existingValue = this.getFieldAllowBuilders(descriptor);
            List<Object> list;
            if (existingValue == null) {
               list = new ArrayList();
               this.fields.put((Comparable)descriptor, list);
            } else {
               list = (List)existingValue;
            }

            list.add(value);
         }
      }

      private void verifyType(final FieldDescriptorLite descriptor, final Object value) {
         if (!FieldSet.isValidType(descriptor.getLiteType(), value)) {
            if (descriptor.getLiteType().getJavaType() != WireFormat.JavaType.MESSAGE || !(value instanceof MessageLite.Builder)) {
               throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", descriptor.getNumber(), descriptor.getLiteType().getJavaType(), value.getClass().getName()));
            }
         }
      }

      public boolean isInitialized() {
         for(int i = 0; i < this.fields.getNumArrayEntries(); ++i) {
            if (!FieldSet.isInitialized(this.fields.getArrayEntryAt(i))) {
               return false;
            }
         }

         for(Map.Entry entry : this.fields.getOverflowEntries()) {
            if (!FieldSet.isInitialized(entry)) {
               return false;
            }
         }

         return true;
      }

      public void mergeFrom(final FieldSet other) {
         this.ensureIsMutable();

         for(int i = 0; i < other.fields.getNumArrayEntries(); ++i) {
            this.mergeFromField(other.fields.getArrayEntryAt(i));
         }

         for(Map.Entry entry : other.fields.getOverflowEntries()) {
            this.mergeFromField(entry);
         }

      }

      private void mergeFromField(final Map.Entry entry) {
         T descriptor = (T)((FieldDescriptorLite)entry.getKey());
         Object otherValue = entry.getValue();
         if (otherValue instanceof LazyField) {
            otherValue = ((LazyField)otherValue).getValue();
         }

         if (descriptor.isRepeated()) {
            List<Object> value = (List)this.getFieldAllowBuilders(descriptor);
            if (value == null) {
               value = new ArrayList();
               this.fields.put((Comparable)descriptor, value);
            }

            for(Object element : (List)otherValue) {
               value.add(FieldSet.cloneIfMutable(element));
            }
         } else if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            Object value = this.getFieldAllowBuilders(descriptor);
            if (value == null) {
               this.fields.put((Comparable)descriptor, FieldSet.cloneIfMutable(otherValue));
            } else if (value instanceof MessageLite.Builder) {
               descriptor.internalMergeFrom((MessageLite.Builder)value, (MessageLite)otherValue);
            } else {
               Object var8 = descriptor.internalMergeFrom(((MessageLite)value).toBuilder(), (MessageLite)otherValue).build();
               this.fields.put((Comparable)descriptor, var8);
            }
         } else {
            this.fields.put((Comparable)descriptor, FieldSet.cloneIfMutable(otherValue));
         }

      }
   }

   public interface FieldDescriptorLite extends Comparable {
      int getNumber();

      WireFormat.FieldType getLiteType();

      WireFormat.JavaType getLiteJavaType();

      boolean isRepeated();

      boolean isPacked();

      Internal.EnumLiteMap getEnumType();

      MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from);
   }
}
