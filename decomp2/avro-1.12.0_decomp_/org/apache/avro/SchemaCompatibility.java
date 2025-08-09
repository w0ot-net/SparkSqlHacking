package org.apache.avro;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaCompatibility {
   private static final Logger LOG = LoggerFactory.getLogger(SchemaCompatibility.class);
   public static final String READER_WRITER_COMPATIBLE_MESSAGE = "Reader schema can always successfully decode data written using the writer schema.";

   private SchemaCompatibility() {
   }

   public static SchemaPairCompatibility checkReaderWriterCompatibility(final Schema reader, final Schema writer) {
      SchemaCompatibilityResult compatibility = (new ReaderWriterCompatibilityChecker()).getCompatibility(reader, writer);
      String message;
      switch (compatibility.getCompatibility().ordinal()) {
         case 0:
            message = "Reader schema can always successfully decode data written using the writer schema.";
            break;
         case 1:
            message = String.format("Data encoded using writer schema:%n%s%nwill or may fail to decode using reader schema:%n%s%n", writer.toString(true), reader.toString(true));
            break;
         default:
            throw new AvroRuntimeException("Unknown compatibility: " + String.valueOf(compatibility));
      }

      return new SchemaPairCompatibility(compatibility, reader, writer, message);
   }

   public static boolean schemaNameEquals(final Schema reader, final Schema writer) {
      return objectsEqual(reader.getName(), writer.getName()) ? true : reader.getAliases().contains(writer.getFullName());
   }

   public static Schema.Field lookupWriterField(final Schema writerSchema, final Schema.Field readerField) {
      assert writerSchema.getType() == Schema.Type.RECORD;

      List<Schema.Field> writerFields = new ArrayList();
      Schema.Field direct = writerSchema.getField(readerField.name());
      if (direct != null) {
         writerFields.add(direct);
      }

      for(String readerFieldAliasName : readerField.aliases()) {
         Schema.Field writerField = writerSchema.getField(readerFieldAliasName);
         if (writerField != null) {
            writerFields.add(writerField);
         }
      }

      switch (writerFields.size()) {
         case 0:
            return null;
         case 1:
            return (Schema.Field)writerFields.get(0);
         default:
            throw new AvroRuntimeException(String.format("Reader record field %s matches multiple fields in writer record schema %s", readerField, writerSchema));
      }
   }

   private static boolean objectsEqual(Object obj1, Object obj2) {
      return Objects.equals(obj1, obj2);
   }

   private static List asList(Deque deque) {
      List<String> list = new ArrayList(deque);
      Collections.reverse(list);
      return Collections.unmodifiableList(list);
   }

   private static final class ReaderWriter {
      private final Schema mReader;
      private final Schema mWriter;

      public ReaderWriter(final Schema reader, final Schema writer) {
         this.mReader = reader;
         this.mWriter = writer;
      }

      public int hashCode() {
         return System.identityHashCode(this.mReader) ^ System.identityHashCode(this.mWriter);
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof ReaderWriter)) {
            return false;
         } else {
            ReaderWriter that = (ReaderWriter)obj;
            return this.mReader == that.mReader && this.mWriter == that.mWriter;
         }
      }

      public String toString() {
         return String.format("ReaderWriter{reader:%s, writer:%s}", this.mReader, this.mWriter);
      }
   }

   private static final class ReaderWriterCompatibilityChecker {
      private static final String ROOT_REFERENCE_TOKEN = "";
      private final Map mMemoizeMap = new HashMap();

      public SchemaCompatibilityResult getCompatibility(final Schema reader, final Schema writer) {
         Deque<String> location = new ArrayDeque();
         return this.getCompatibility("", reader, writer, location);
      }

      private SchemaCompatibilityResult getCompatibility(String referenceToken, final Schema reader, final Schema writer, final Deque location) {
         location.addFirst(referenceToken);
         SchemaCompatibility.LOG.debug("Checking compatibility of reader {} with writer {}", reader, writer);
         ReaderWriter pair = new ReaderWriter(reader, writer);
         SchemaCompatibilityResult result = (SchemaCompatibilityResult)this.mMemoizeMap.get(pair);
         if (result != null) {
            if (result.getCompatibility() == SchemaCompatibility.SchemaCompatibilityType.RECURSION_IN_PROGRESS) {
               result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
            }
         } else {
            this.mMemoizeMap.put(pair, SchemaCompatibility.SchemaCompatibilityResult.recursionInProgress());
            result = this.calculateCompatibility(reader, writer, location);
            this.mMemoizeMap.put(pair, result);
         }

         location.removeFirst();
         return result;
      }

      private SchemaCompatibilityResult calculateCompatibility(final Schema reader, final Schema writer, final Deque location) {
         assert reader != null;

         assert writer != null;

         SchemaCompatibilityResult result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
         if (reader.getType() == writer.getType()) {
            switch (reader.getType()) {
               case NULL:
               case BOOLEAN:
               case INT:
               case LONG:
               case FLOAT:
               case DOUBLE:
               case BYTES:
               case STRING:
                  return result;
               case ARRAY:
                  return result.mergedWith(this.getCompatibility("items", reader.getElementType(), writer.getElementType(), location));
               case MAP:
                  return result.mergedWith(this.getCompatibility("values", reader.getValueType(), writer.getValueType(), location));
               case FIXED:
                  result = result.mergedWith(this.checkSchemaNames(reader, writer, location));
                  return result.mergedWith(this.checkFixedSize(reader, writer, location));
               case ENUM:
                  result = result.mergedWith(this.checkSchemaNames(reader, writer, location));
                  return result.mergedWith(this.checkReaderEnumContainsAllWriterEnumSymbols(reader, writer, location));
               case RECORD:
                  result = result.mergedWith(this.checkSchemaNames(reader, writer, location));
                  return result.mergedWith(this.checkReaderWriterRecordFields(reader, writer, location));
               case UNION:
                  int i = 0;

                  for(Schema writerBranch : writer.getTypes()) {
                     location.addFirst(Integer.toString(i));
                     SchemaCompatibilityResult compatibility = this.getCompatibility(reader, writerBranch);
                     if (compatibility.getCompatibility() == SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE) {
                        String message = String.format("reader union lacking writer type: %s", writerBranch.getType());
                        result = result.mergedWith(SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.MISSING_UNION_BRANCH, reader, writer, message, SchemaCompatibility.asList(location)));
                     }

                     location.removeFirst();
                     ++i;
                  }

                  return result;
               default:
                  throw new AvroRuntimeException("Unknown schema type: " + String.valueOf(reader.getType()));
            }
         } else if (writer.getType() == Schema.Type.UNION) {
            int index = 0;

            for(Schema s : writer.getTypes()) {
               result = result.mergedWith(this.getCompatibility(Integer.toString(index), reader, s, location));
               ++index;
            }

            return result;
         } else {
            switch (reader.getType()) {
               case NULL:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case BOOLEAN:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case INT:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case LONG:
                  return writer.getType() == Schema.Type.INT ? result : result.mergedWith(this.typeMismatch(reader, writer, location));
               case FLOAT:
                  return writer.getType() != Schema.Type.INT && writer.getType() != Schema.Type.LONG ? result.mergedWith(this.typeMismatch(reader, writer, location)) : result;
               case DOUBLE:
                  return writer.getType() != Schema.Type.INT && writer.getType() != Schema.Type.LONG && writer.getType() != Schema.Type.FLOAT ? result.mergedWith(this.typeMismatch(reader, writer, location)) : result;
               case BYTES:
                  return writer.getType() == Schema.Type.STRING ? result : result.mergedWith(this.typeMismatch(reader, writer, location));
               case STRING:
                  return writer.getType() == Schema.Type.BYTES ? result : result.mergedWith(this.typeMismatch(reader, writer, location));
               case ARRAY:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case MAP:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case FIXED:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case ENUM:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case RECORD:
                  return result.mergedWith(this.typeMismatch(reader, writer, location));
               case UNION:
                  for(Schema readerBranch : reader.getTypes()) {
                     SchemaCompatibilityResult compatibility = this.getCompatibility(readerBranch, writer);
                     if (compatibility.getCompatibility() == SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE) {
                        return result;
                     }
                  }

                  String message = String.format("reader union lacking writer type: %s", writer.getType());
                  return result.mergedWith(SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.MISSING_UNION_BRANCH, reader, writer, message, SchemaCompatibility.asList(location)));
               default:
                  throw new AvroRuntimeException("Unknown schema type: " + String.valueOf(reader.getType()));
            }
         }
      }

      private SchemaCompatibilityResult checkReaderWriterRecordFields(final Schema reader, final Schema writer, final Deque location) {
         SchemaCompatibilityResult result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
         location.addFirst("fields");

         for(Schema.Field readerField : reader.getFields()) {
            location.addFirst(Integer.toString(readerField.pos()));
            Schema.Field writerField = SchemaCompatibility.lookupWriterField(writer, readerField);
            if (writerField == null) {
               if (!readerField.hasDefaultValue()) {
                  if (readerField.schema().getType() == Schema.Type.ENUM && readerField.schema().getEnumDefault() != null) {
                     result = result.mergedWith(this.getCompatibility("type", readerField.schema(), writer, location));
                  } else {
                     result = result.mergedWith(SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.READER_FIELD_MISSING_DEFAULT_VALUE, reader, writer, readerField.name(), SchemaCompatibility.asList(location)));
                  }
               }
            } else {
               result = result.mergedWith(this.getCompatibility("type", readerField.schema(), writerField.schema(), location));
            }

            location.removeFirst();
         }

         location.removeFirst();
         return result;
      }

      private SchemaCompatibilityResult checkReaderEnumContainsAllWriterEnumSymbols(final Schema reader, final Schema writer, final Deque location) {
         SchemaCompatibilityResult result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
         location.addFirst("symbols");
         Set<String> symbols = new TreeSet(writer.getEnumSymbols());
         symbols.removeAll(reader.getEnumSymbols());
         if (!symbols.isEmpty()) {
            if (reader.getEnumDefault() != null && reader.getEnumSymbols().contains(reader.getEnumDefault())) {
               symbols.clear();
               result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
            } else {
               result = SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.MISSING_ENUM_SYMBOLS, reader, writer, symbols.toString(), SchemaCompatibility.asList(location));
            }
         }

         location.removeFirst();
         return result;
      }

      private SchemaCompatibilityResult checkFixedSize(final Schema reader, final Schema writer, final Deque location) {
         SchemaCompatibilityResult result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
         location.addFirst("size");
         int actual = reader.getFixedSize();
         int expected = writer.getFixedSize();
         if (actual != expected) {
            String message = String.format("expected: %d, found: %d", expected, actual);
            result = SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.FIXED_SIZE_MISMATCH, reader, writer, message, SchemaCompatibility.asList(location));
         }

         location.removeFirst();
         return result;
      }

      private SchemaCompatibilityResult checkSchemaNames(final Schema reader, final Schema writer, final Deque location) {
         SchemaCompatibilityResult result = SchemaCompatibility.SchemaCompatibilityResult.compatible();
         location.addFirst("name");
         if (!SchemaCompatibility.schemaNameEquals(reader, writer)) {
            String message = String.format("expected: %s", writer.getFullName());
            result = SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.NAME_MISMATCH, reader, writer, message, SchemaCompatibility.asList(location));
         }

         location.removeFirst();
         return result;
      }

      private SchemaCompatibilityResult typeMismatch(final Schema reader, final Schema writer, final Deque location) {
         String message = String.format("reader type: %s not compatible with writer type: %s", reader.getType(), writer.getType());
         return SchemaCompatibility.SchemaCompatibilityResult.incompatible(SchemaCompatibility.SchemaIncompatibilityType.TYPE_MISMATCH, reader, writer, message, SchemaCompatibility.asList(location));
      }
   }

   public static enum SchemaCompatibilityType {
      COMPATIBLE,
      INCOMPATIBLE,
      RECURSION_IN_PROGRESS;

      // $FF: synthetic method
      private static SchemaCompatibilityType[] $values() {
         return new SchemaCompatibilityType[]{COMPATIBLE, INCOMPATIBLE, RECURSION_IN_PROGRESS};
      }
   }

   public static enum SchemaIncompatibilityType {
      NAME_MISMATCH,
      FIXED_SIZE_MISMATCH,
      MISSING_ENUM_SYMBOLS,
      READER_FIELD_MISSING_DEFAULT_VALUE,
      TYPE_MISMATCH,
      MISSING_UNION_BRANCH;

      // $FF: synthetic method
      private static SchemaIncompatibilityType[] $values() {
         return new SchemaIncompatibilityType[]{NAME_MISMATCH, FIXED_SIZE_MISMATCH, MISSING_ENUM_SYMBOLS, READER_FIELD_MISSING_DEFAULT_VALUE, TYPE_MISMATCH, MISSING_UNION_BRANCH};
      }
   }

   public static final class SchemaCompatibilityResult {
      private final SchemaCompatibilityType mCompatibilityType;
      private final List mIncompatibilities;
      private static final SchemaCompatibilityResult COMPATIBLE;
      private static final SchemaCompatibilityResult RECURSION_IN_PROGRESS;

      public SchemaCompatibilityResult mergedWith(SchemaCompatibilityResult toMerge) {
         List<Incompatibility> mergedIncompatibilities = new ArrayList(this.mIncompatibilities);
         mergedIncompatibilities.addAll(toMerge.getIncompatibilities());
         SchemaCompatibilityType compatibilityType = this.mCompatibilityType == SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE ? toMerge.mCompatibilityType : SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE;
         return new SchemaCompatibilityResult(compatibilityType, mergedIncompatibilities);
      }

      private SchemaCompatibilityResult(SchemaCompatibilityType compatibilityType, List incompatibilities) {
         this.mCompatibilityType = compatibilityType;
         this.mIncompatibilities = incompatibilities;
      }

      public static SchemaCompatibilityResult compatible() {
         return COMPATIBLE;
      }

      public static SchemaCompatibilityResult recursionInProgress() {
         return RECURSION_IN_PROGRESS;
      }

      public static SchemaCompatibilityResult incompatible(SchemaIncompatibilityType incompatibilityType, Schema readerFragment, Schema writerFragment, String message, List location) {
         Incompatibility incompatibility = new Incompatibility(incompatibilityType, readerFragment, writerFragment, message, location);
         return new SchemaCompatibilityResult(SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE, Collections.singletonList(incompatibility));
      }

      public SchemaCompatibilityType getCompatibility() {
         return this.mCompatibilityType;
      }

      public List getIncompatibilities() {
         return this.mIncompatibilities;
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (this.mCompatibilityType == null ? 0 : this.mCompatibilityType.hashCode());
         result = 31 * result + (this.mIncompatibilities == null ? 0 : this.mIncompatibilities.hashCode());
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            SchemaCompatibilityResult other = (SchemaCompatibilityResult)obj;
            if (this.mIncompatibilities == null) {
               if (other.mIncompatibilities != null) {
                  return false;
               }
            } else if (!this.mIncompatibilities.equals(other.mIncompatibilities)) {
               return false;
            }

            return this.mCompatibilityType == other.mCompatibilityType;
         }
      }

      public String toString() {
         return String.format("SchemaCompatibilityResult{compatibility:%s, incompatibilities:%s}", this.mCompatibilityType, this.mIncompatibilities);
      }

      static {
         COMPATIBLE = new SchemaCompatibilityResult(SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE, Collections.emptyList());
         RECURSION_IN_PROGRESS = new SchemaCompatibilityResult(SchemaCompatibility.SchemaCompatibilityType.RECURSION_IN_PROGRESS, Collections.emptyList());
      }
   }

   public static final class Incompatibility {
      private final SchemaIncompatibilityType mType;
      private final Schema mReaderFragment;
      private final Schema mWriterFragment;
      private final String mMessage;
      private final List mLocation;

      Incompatibility(SchemaIncompatibilityType type, Schema readerFragment, Schema writerFragment, String message, List location) {
         this.mType = type;
         this.mReaderFragment = readerFragment;
         this.mWriterFragment = writerFragment;
         this.mMessage = message;
         this.mLocation = location;
      }

      public SchemaIncompatibilityType getType() {
         return this.mType;
      }

      public Schema getReaderFragment() {
         return this.mReaderFragment;
      }

      public Schema getWriterFragment() {
         return this.mWriterFragment;
      }

      public String getMessage() {
         return this.mMessage;
      }

      public String getLocation() {
         StringBuilder s = new StringBuilder("/");
         boolean first = true;

         for(String coordinate : this.mLocation.subList(1, this.mLocation.size())) {
            if (first) {
               first = false;
            } else {
               s.append('/');
            }

            s.append(coordinate.replace("~", "~0").replace("/", "~1"));
         }

         return s.toString();
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (this.mType == null ? 0 : this.mType.hashCode());
         result = 31 * result + (this.mReaderFragment == null ? 0 : this.mReaderFragment.hashCode());
         result = 31 * result + (this.mWriterFragment == null ? 0 : this.mWriterFragment.hashCode());
         result = 31 * result + (this.mMessage == null ? 0 : this.mMessage.hashCode());
         result = 31 * result + (this.mLocation == null ? 0 : this.mLocation.hashCode());
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            Incompatibility other = (Incompatibility)obj;
            if (this.mType != other.mType) {
               return false;
            } else {
               if (this.mReaderFragment == null) {
                  if (other.mReaderFragment != null) {
                     return false;
                  }
               } else if (!this.mReaderFragment.equals(other.mReaderFragment)) {
                  return false;
               }

               if (this.mWriterFragment == null) {
                  if (other.mWriterFragment != null) {
                     return false;
                  }
               } else if (!this.mWriterFragment.equals(other.mWriterFragment)) {
                  return false;
               }

               if (this.mMessage == null) {
                  if (other.mMessage != null) {
                     return false;
                  }
               } else if (!this.mMessage.equals(other.mMessage)) {
                  return false;
               }

               if (this.mLocation == null) {
                  return other.mLocation == null;
               } else {
                  return this.mLocation.equals(other.mLocation);
               }
            }
         }
      }

      public String toString() {
         return String.format("Incompatibility{type:%s, location:%s, message:%s, reader:%s, writer:%s}", this.mType, this.getLocation(), this.mMessage, this.mReaderFragment, this.mWriterFragment);
      }
   }

   public static final class SchemaPairCompatibility {
      private final SchemaCompatibilityResult mResult;
      private final Schema mReader;
      private final Schema mWriter;
      private final String mDescription;

      public SchemaPairCompatibility(SchemaCompatibilityResult result, Schema reader, Schema writer, String description) {
         this.mResult = result;
         this.mReader = reader;
         this.mWriter = writer;
         this.mDescription = description;
      }

      public SchemaCompatibilityType getType() {
         return this.mResult.getCompatibility();
      }

      public SchemaCompatibilityResult getResult() {
         return this.mResult;
      }

      public Schema getReader() {
         return this.mReader;
      }

      public Schema getWriter() {
         return this.mWriter;
      }

      public String getDescription() {
         return this.mDescription;
      }

      public String toString() {
         return String.format("SchemaPairCompatibility{result:%s, readerSchema:%s, writerSchema:%s, description:%s}", this.mResult, this.mReader, this.mWriter, this.mDescription);
      }

      public boolean equals(Object other) {
         if (!(other instanceof SchemaPairCompatibility)) {
            return false;
         } else {
            SchemaPairCompatibility result = (SchemaPairCompatibility)other;
            return SchemaCompatibility.objectsEqual(result.mResult, this.mResult) && SchemaCompatibility.objectsEqual(result.mReader, this.mReader) && SchemaCompatibility.objectsEqual(result.mWriter, this.mWriter) && SchemaCompatibility.objectsEqual(result.mDescription, this.mDescription);
         }
      }

      public int hashCode() {
         return Arrays.hashCode(new Object[]{this.mResult, this.mReader, this.mWriter, this.mDescription});
      }
   }
}
