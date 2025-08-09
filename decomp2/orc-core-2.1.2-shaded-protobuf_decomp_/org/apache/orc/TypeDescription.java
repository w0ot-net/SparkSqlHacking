package org.apache.orc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.impl.ParserUtils;
import org.apache.orc.impl.TypeUtils;
import org.jetbrains.annotations.NotNull;

public class TypeDescription implements Comparable, Serializable, Cloneable {
   private static final int MAX_PRECISION = 38;
   private static final int MAX_SCALE = 38;
   private static final int DEFAULT_PRECISION = 38;
   private static final int DEFAULT_SCALE = 10;
   public static final int MAX_DECIMAL64_PRECISION = 18;
   public static final long MAX_DECIMAL64 = 999999999999999999L;
   public static final long MIN_DECIMAL64 = -999999999999999999L;
   private static final int DEFAULT_LENGTH = 256;
   static final Pattern UNQUOTED_NAMES = Pattern.compile("^[a-zA-Z0-9_]+$");
   public static final String ENCRYPT_ATTRIBUTE = "encrypt";
   public static final String MASK_ATTRIBUTE = "mask";
   private int id = -1;
   private int maxId = -1;
   private TypeDescription parent;
   private final Category category;
   private final List children;
   private final List fieldNames;
   private final Map attributes = new HashMap();
   private int maxLength = 256;
   private int precision = 38;
   private int scale = 10;

   public int compareTo(TypeDescription other) {
      if (this == other) {
         return 0;
      } else if (other == null) {
         return -1;
      } else {
         int result = this.category.compareTo(other.category);
         if (result == 0) {
            switch (this.category) {
               case CHAR:
               case VARCHAR:
                  return this.maxLength - other.maxLength;
               case DECIMAL:
                  if (this.precision != other.precision) {
                     return this.precision - other.precision;
                  }

                  return this.scale - other.scale;
               case UNION:
               case LIST:
               case MAP:
                  if (this.children.size() != other.children.size()) {
                     return this.children.size() - other.children.size();
                  }

                  for(int c = 0; result == 0 && c < this.children.size(); ++c) {
                     result = ((TypeDescription)this.children.get(c)).compareTo((TypeDescription)other.children.get(c));
                  }
                  break;
               case STRUCT:
                  if (this.children.size() != other.children.size()) {
                     return this.children.size() - other.children.size();
                  }

                  for(int c = 0; result == 0 && c < this.children.size(); ++c) {
                     result = ((String)this.fieldNames.get(c)).compareTo((String)other.fieldNames.get(c));
                     if (result == 0) {
                        result = ((TypeDescription)this.children.get(c)).compareTo((TypeDescription)other.children.get(c));
                     }
                  }
            }
         }

         return result;
      }
   }

   public static TypeDescription createBoolean() {
      return new TypeDescription(TypeDescription.Category.BOOLEAN);
   }

   public static TypeDescription createByte() {
      return new TypeDescription(TypeDescription.Category.BYTE);
   }

   public static TypeDescription createShort() {
      return new TypeDescription(TypeDescription.Category.SHORT);
   }

   public static TypeDescription createInt() {
      return new TypeDescription(TypeDescription.Category.INT);
   }

   public static TypeDescription createLong() {
      return new TypeDescription(TypeDescription.Category.LONG);
   }

   public static TypeDescription createFloat() {
      return new TypeDescription(TypeDescription.Category.FLOAT);
   }

   public static TypeDescription createDouble() {
      return new TypeDescription(TypeDescription.Category.DOUBLE);
   }

   public static TypeDescription createString() {
      return new TypeDescription(TypeDescription.Category.STRING);
   }

   public static TypeDescription createDate() {
      return new TypeDescription(TypeDescription.Category.DATE);
   }

   public static TypeDescription createTimestamp() {
      return new TypeDescription(TypeDescription.Category.TIMESTAMP);
   }

   public static TypeDescription createTimestampInstant() {
      return new TypeDescription(TypeDescription.Category.TIMESTAMP_INSTANT);
   }

   public static TypeDescription createBinary() {
      return new TypeDescription(TypeDescription.Category.BINARY);
   }

   public static TypeDescription createDecimal() {
      return new TypeDescription(TypeDescription.Category.DECIMAL);
   }

   public static TypeDescription fromString(String typeName) {
      if (typeName == null) {
         return null;
      } else {
         ParserUtils.StringPosition source = new ParserUtils.StringPosition(typeName);
         TypeDescription result = ParserUtils.parseType(source);
         if (source.hasCharactersLeft()) {
            throw new IllegalArgumentException("Extra characters at " + String.valueOf(source));
         } else {
            return result;
         }
      }
   }

   public TypeDescription withPrecision(int precision) {
      if (this.category != TypeDescription.Category.DECIMAL) {
         throw new IllegalArgumentException("precision is only allowed on decimal and not " + this.category.name);
      } else if (precision >= 1 && precision <= 38 && this.scale <= precision) {
         this.precision = precision;
         return this;
      } else {
         throw new IllegalArgumentException("precision " + precision + " is out of range 1 .. " + this.scale);
      }
   }

   public TypeDescription withScale(int scale) {
      if (this.category != TypeDescription.Category.DECIMAL) {
         throw new IllegalArgumentException("scale is only allowed on decimal and not " + this.category.name);
      } else if (scale >= 0 && scale <= 38 && scale <= this.precision) {
         this.scale = scale;
         return this;
      } else {
         throw new IllegalArgumentException("scale is out of range at " + scale);
      }
   }

   public TypeDescription setAttribute(@NotNull String key, String value) {
      if (value == null) {
         this.attributes.remove(key);
      } else {
         this.attributes.put(key, value);
      }

      return this;
   }

   public TypeDescription removeAttribute(@NotNull String key) {
      this.attributes.remove(key);
      return this;
   }

   public static TypeDescription createVarchar() {
      return new TypeDescription(TypeDescription.Category.VARCHAR);
   }

   public static TypeDescription createChar() {
      return new TypeDescription(TypeDescription.Category.CHAR);
   }

   public TypeDescription withMaxLength(int maxLength) {
      if (this.category != TypeDescription.Category.VARCHAR && this.category != TypeDescription.Category.CHAR) {
         throw new IllegalArgumentException("maxLength is only allowed on char and varchar and not " + this.category.name);
      } else {
         this.maxLength = maxLength;
         return this;
      }
   }

   public static TypeDescription createList(TypeDescription childType) {
      TypeDescription result = new TypeDescription(TypeDescription.Category.LIST);
      result.children.add(childType);
      childType.parent = result;
      return result;
   }

   public static TypeDescription createMap(TypeDescription keyType, TypeDescription valueType) {
      TypeDescription result = new TypeDescription(TypeDescription.Category.MAP);
      result.children.add(keyType);
      result.children.add(valueType);
      keyType.parent = result;
      valueType.parent = result;
      return result;
   }

   public static TypeDescription createUnion() {
      return new TypeDescription(TypeDescription.Category.UNION);
   }

   public static TypeDescription createStruct() {
      return new TypeDescription(TypeDescription.Category.STRUCT);
   }

   public TypeDescription addUnionChild(TypeDescription child) {
      if (this.category != TypeDescription.Category.UNION) {
         throw new IllegalArgumentException("Can only add types to union type and not " + String.valueOf(this.category));
      } else {
         this.addChild(child);
         return this;
      }
   }

   public TypeDescription addField(String field, TypeDescription fieldType) {
      if (this.category != TypeDescription.Category.STRUCT) {
         throw new IllegalArgumentException("Can only add fields to struct type and not " + String.valueOf(this.category));
      } else {
         this.fieldNames.add(field);
         this.addChild(fieldType);
         return this;
      }
   }

   public int getId() {
      if (this.id == -1) {
         TypeDescription root;
         for(root = this; root.parent != null; root = root.parent) {
         }

         root.assignIds(0);
      }

      return this.id;
   }

   public TypeDescription clone() {
      TypeDescription result = new TypeDescription(this.category);
      result.maxLength = this.maxLength;
      result.precision = this.precision;
      result.scale = this.scale;
      if (this.fieldNames != null) {
         result.fieldNames.addAll(this.fieldNames);
      }

      if (this.children != null) {
         for(TypeDescription child : this.children) {
            TypeDescription clone = child.clone();
            clone.parent = result;
            result.children.add(clone);
         }
      }

      for(Map.Entry pair : this.attributes.entrySet()) {
         result.attributes.put((String)pair.getKey(), (String)pair.getValue());
      }

      return result;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.category.hashCode();
      if (this.children != null) {
         result = 31 * result + this.children.hashCode();
      }

      result = 31 * result + this.maxLength;
      result = 31 * result + this.precision;
      result = 31 * result + this.scale;
      return result;
   }

   public boolean equals(Object other) {
      return this.equals(other, true);
   }

   public boolean equals(Object other, boolean checkAttributes) {
      if (other != null && other instanceof TypeDescription castOther) {
         if (other == this) {
            return true;
         } else if (this.category == castOther.category && this.maxLength == castOther.maxLength && this.scale == castOther.scale && this.precision == castOther.precision) {
            if (checkAttributes) {
               List<String> attributeNames = this.getAttributeNames();
               if (castOther.getAttributeNames().size() != attributeNames.size()) {
                  return false;
               }

               for(String attribute : attributeNames) {
                  if (!this.getAttributeValue(attribute).equals(castOther.getAttributeValue(attribute))) {
                     return false;
                  }
               }
            }

            if (this.children != null) {
               if (this.children.size() != castOther.children.size()) {
                  return false;
               }

               for(int i = 0; i < this.children.size(); ++i) {
                  if (!((TypeDescription)this.children.get(i)).equals(castOther.children.get(i), checkAttributes)) {
                     return false;
                  }
               }
            }

            if (this.category == TypeDescription.Category.STRUCT) {
               for(int i = 0; i < this.fieldNames.size(); ++i) {
                  if (!((String)this.fieldNames.get(i)).equals(castOther.fieldNames.get(i))) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public int getMaximumId() {
      if (this.maxId == -1) {
         TypeDescription root;
         for(root = this; root.parent != null; root = root.parent) {
         }

         root.assignIds(0);
      }

      return this.maxId;
   }

   public VectorizedRowBatch createRowBatch(RowBatchVersion version, int size) {
      VectorizedRowBatch result;
      if (this.category == TypeDescription.Category.STRUCT) {
         result = new VectorizedRowBatch(this.children.size(), size);

         for(int i = 0; i < result.cols.length; ++i) {
            result.cols[i] = TypeUtils.createColumn((TypeDescription)this.children.get(i), version, size);
         }
      } else {
         result = new VectorizedRowBatch(1, size);
         result.cols[0] = TypeUtils.createColumn(this, version, size);
      }

      result.reset();
      return result;
   }

   public VectorizedRowBatch createRowBatchV2() {
      return this.createRowBatch(TypeDescription.RowBatchVersion.USE_DECIMAL64, 1024);
   }

   public VectorizedRowBatch createRowBatch(int maxSize) {
      return this.createRowBatch(TypeDescription.RowBatchVersion.ORIGINAL, maxSize);
   }

   public VectorizedRowBatch createRowBatch() {
      return this.createRowBatch(TypeDescription.RowBatchVersion.ORIGINAL, 1024);
   }

   public Category getCategory() {
      return this.category;
   }

   public int getMaxLength() {
      return this.maxLength;
   }

   public int getPrecision() {
      return this.precision;
   }

   public int getScale() {
      return this.scale;
   }

   public List getFieldNames() {
      return Collections.unmodifiableList(this.fieldNames);
   }

   public List getAttributeNames() {
      List<String> result = new ArrayList(this.attributes.keySet());
      Collections.sort(result);
      return result;
   }

   public String getAttributeValue(String attributeName) {
      return (String)this.attributes.get(attributeName);
   }

   public TypeDescription getParent() {
      return this.parent;
   }

   public List getChildren() {
      return this.children == null ? null : Collections.unmodifiableList(this.children);
   }

   private int assignIds(int startId) {
      this.id = startId++;
      if (this.children != null) {
         for(TypeDescription child : this.children) {
            startId = child.assignIds(startId);
         }
      }

      this.maxId = startId - 1;
      return startId;
   }

   public void addChild(TypeDescription child) {
      switch (this.category) {
         case LIST:
            if (this.children.size() >= 1) {
               throw new IllegalArgumentException("Can't add more children to list");
            }
         case MAP:
            if (this.children.size() >= 2) {
               throw new IllegalArgumentException("Can't add more children to map");
            }
         case UNION:
         case STRUCT:
            this.children.add(child);
            child.parent = this;
            return;
         default:
            throw new IllegalArgumentException("Can't add children to " + String.valueOf(this.category));
      }
   }

   public TypeDescription(Category category) {
      this.category = category;
      if (category.isPrimitive) {
         this.children = null;
      } else {
         this.children = new ArrayList();
      }

      if (category == TypeDescription.Category.STRUCT) {
         this.fieldNames = new ArrayList();
      } else {
         this.fieldNames = null;
      }

   }

   static void printFieldName(StringBuilder buffer, String name) {
      if (UNQUOTED_NAMES.matcher(name).matches()) {
         buffer.append(name);
      } else {
         buffer.append('`');
         buffer.append(name.replace("`", "``"));
         buffer.append('`');
      }

   }

   public void printToBuffer(StringBuilder buffer) {
      buffer.append(this.category.name);
      switch (this.category) {
         case CHAR:
         case VARCHAR:
            buffer.append('(');
            buffer.append(this.maxLength);
            buffer.append(')');
            break;
         case DECIMAL:
            buffer.append('(');
            buffer.append(this.precision);
            buffer.append(',');
            buffer.append(this.scale);
            buffer.append(')');
            break;
         case UNION:
         case LIST:
         case MAP:
            buffer.append('<');

            for(int i = 0; i < this.children.size(); ++i) {
               if (i != 0) {
                  buffer.append(',');
               }

               ((TypeDescription)this.children.get(i)).printToBuffer(buffer);
            }

            buffer.append('>');
            break;
         case STRUCT:
            buffer.append('<');

            for(int i = 0; i < this.children.size(); ++i) {
               if (i != 0) {
                  buffer.append(',');
               }

               printFieldName(buffer, (String)this.fieldNames.get(i));
               buffer.append(':');
               ((TypeDescription)this.children.get(i)).printToBuffer(buffer);
            }

            buffer.append('>');
      }

   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      this.printToBuffer(buffer);
      return buffer.toString();
   }

   private void printJsonToBuffer(String prefix, StringBuilder buffer, int indent) {
      for(int i = 0; i < indent; ++i) {
         buffer.append(' ');
      }

      buffer.append(prefix);
      buffer.append("{\"category\": \"");
      buffer.append(this.category.name);
      buffer.append("\", \"id\": ");
      buffer.append(this.getId());
      buffer.append(", \"max\": ");
      buffer.append(this.maxId);
      switch (this.category) {
         case CHAR:
         case VARCHAR:
            buffer.append(", \"length\": ");
            buffer.append(this.maxLength);
            break;
         case DECIMAL:
            buffer.append(", \"precision\": ");
            buffer.append(this.precision);
            buffer.append(", \"scale\": ");
            buffer.append(this.scale);
            break;
         case UNION:
         case LIST:
         case MAP:
            buffer.append(", \"children\": [");

            for(int i = 0; i < this.children.size(); ++i) {
               buffer.append('\n');
               ((TypeDescription)this.children.get(i)).printJsonToBuffer("", buffer, indent + 2);
               if (i != this.children.size() - 1) {
                  buffer.append(',');
               }
            }

            buffer.append("]");
            break;
         case STRUCT:
            buffer.append(", \"fields\": [");

            for(int i = 0; i < this.children.size(); ++i) {
               buffer.append('\n');
               buffer.append('{');
               ((TypeDescription)this.children.get(i)).printJsonToBuffer("\"" + (String)this.fieldNames.get(i) + "\": ", buffer, indent + 2);
               buffer.append('}');
               if (i != this.children.size() - 1) {
                  buffer.append(',');
               }
            }

            buffer.append(']');
      }

      buffer.append('}');
   }

   public String toJson() {
      StringBuilder buffer = new StringBuilder();
      this.printJsonToBuffer("", buffer, 0);
      return buffer.toString();
   }

   public TypeDescription findSubtype(int goal) {
      ParserUtils.TypeFinder result = new ParserUtils.TypeFinder(this);
      ParserUtils.findSubtype(this, goal, result);
      return result.current;
   }

   public TypeDescription findSubtype(String columnName) {
      return this.findSubtype(columnName, true);
   }

   public TypeDescription findSubtype(String columnName, boolean isSchemaEvolutionCaseAware) {
      ParserUtils.StringPosition source = new ParserUtils.StringPosition(columnName);
      TypeDescription result = ParserUtils.findSubtype(this, source, isSchemaEvolutionCaseAware);
      if (source.hasCharactersLeft()) {
         throw new IllegalArgumentException("Remaining text in parsing field name " + String.valueOf(source));
      } else {
         return result;
      }
   }

   public List findSubtypes(String columnNameList) {
      ParserUtils.StringPosition source = new ParserUtils.StringPosition(columnNameList);
      List<TypeDescription> result = ParserUtils.findSubtypeList(this, source);
      if (source.hasCharactersLeft()) {
         throw new IllegalArgumentException("Remaining text in parsing field name " + String.valueOf(source));
      } else {
         return result;
      }
   }

   public void annotateEncryption(String encryption, String masks) {
      ParserUtils.StringPosition source = new ParserUtils.StringPosition(encryption);
      ParserUtils.parseKeys(source, this);
      if (source.hasCharactersLeft()) {
         throw new IllegalArgumentException("Remaining text in parsing encryption keys " + String.valueOf(source));
      } else {
         source = new ParserUtils.StringPosition(masks);
         ParserUtils.parseMasks(source, this);
         if (source.hasCharactersLeft()) {
            throw new IllegalArgumentException("Remaining text in parsing encryption masks " + String.valueOf(source));
         }
      }
   }

   private int getChildIndex(TypeDescription child) {
      for(int i = this.children.size() - 1; i >= 0; --i) {
         if (this.children.get(i) == child) {
            return i;
         }
      }

      throw new IllegalArgumentException("Child not found");
   }

   private String getPartialName(TypeDescription child) {
      switch (this.category) {
         case UNION -> {
            return Integer.toString(this.getChildIndex(child));
         }
         case LIST -> {
            return "_elem";
         }
         case MAP -> {
            return this.getChildIndex(child) == 0 ? "_key" : "_value";
         }
         case STRUCT -> {
            return (String)this.fieldNames.get(this.getChildIndex(child));
         }
         default -> throw new IllegalArgumentException("Can't get the field name of a primitive type");
      }
   }

   public String getFullFieldName() {
      List<String> parts = new ArrayList(this.getId());
      TypeDescription current = this;
      TypeDescription parent = this.getParent();
      if (parent == null) {
         return Integer.toString(this.getId());
      } else {
         while(parent != null) {
            parts.add(parent.getPartialName(current));
            current = parent;
            parent = parent.getParent();
         }

         StringBuilder buffer = new StringBuilder();

         for(int part = parts.size() - 1; part >= 0; --part) {
            buffer.append((String)parts.get(part));
            if (part != 0) {
               buffer.append('.');
            }
         }

         return buffer.toString();
      }
   }

   public static enum Category {
      BOOLEAN("boolean", true),
      BYTE("tinyint", true),
      SHORT("smallint", true),
      INT("int", true),
      LONG("bigint", true),
      FLOAT("float", true),
      DOUBLE("double", true),
      STRING("string", true),
      DATE("date", true),
      TIMESTAMP("timestamp", true),
      BINARY("binary", true),
      DECIMAL("decimal", true),
      VARCHAR("varchar", true),
      CHAR("char", true),
      LIST("array", false),
      MAP("map", false),
      STRUCT("struct", false),
      UNION("uniontype", false),
      TIMESTAMP_INSTANT("timestamp with local time zone", true);

      final boolean isPrimitive;
      final String name;

      private Category(String name, boolean isPrimitive) {
         this.name = name;
         this.isPrimitive = isPrimitive;
      }

      public boolean isPrimitive() {
         return this.isPrimitive;
      }

      public String getName() {
         return this.name;
      }

      // $FF: synthetic method
      private static Category[] $values() {
         return new Category[]{BOOLEAN, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, STRING, DATE, TIMESTAMP, BINARY, DECIMAL, VARCHAR, CHAR, LIST, MAP, STRUCT, UNION, TIMESTAMP_INSTANT};
      }
   }

   public static enum RowBatchVersion {
      ORIGINAL,
      USE_DECIMAL64;

      // $FF: synthetic method
      private static RowBatchVersion[] $values() {
         return new RowBatchVersion[]{ORIGINAL, USE_DECIMAL64};
      }
   }
}
