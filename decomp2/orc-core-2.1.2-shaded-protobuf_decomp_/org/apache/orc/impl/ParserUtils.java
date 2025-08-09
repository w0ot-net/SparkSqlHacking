package org.apache.orc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.TypeDescription;

public class ParserUtils {
   private static final TypeDescription.Category[] TYPE_DESCRIPTION_CATEGORY_VALUES = TypeDescription.Category.values();
   private static final Pattern INTEGER_PATTERN = Pattern.compile("^[0-9]+$");

   static TypeDescription.Category parseCategory(StringPosition source) {
      StringBuilder word = new StringBuilder();

      boolean hadSpace;
      for(hadSpace = true; source.position < source.length; ++source.position) {
         char ch = source.value.charAt(source.position);
         if (Character.isLetter(ch)) {
            word.append(Character.toLowerCase(ch));
            hadSpace = false;
         } else {
            if (ch != ' ') {
               break;
            }

            if (!hadSpace) {
               hadSpace = true;
               word.append(ch);
            }
         }
      }

      String catString = word.toString();
      if (hadSpace) {
         catString = catString.trim();
      }

      if (!catString.isEmpty()) {
         for(TypeDescription.Category cat : TYPE_DESCRIPTION_CATEGORY_VALUES) {
            if (cat.getName().equals(catString)) {
               return cat;
            }
         }
      }

      throw new IllegalArgumentException("Can't parse category at " + String.valueOf(source));
   }

   static int parseInt(StringPosition source) {
      int start = source.position;

      int result;
      for(result = 0; source.position < source.length; ++source.position) {
         char ch = source.value.charAt(source.position);
         if (!Character.isDigit(ch)) {
            break;
         }

         result = result * 10 + (ch - 48);
      }

      if (source.position == start) {
         throw new IllegalArgumentException("Missing integer at " + String.valueOf(source));
      } else {
         return result;
      }
   }

   public static String parseName(StringPosition source) {
      if (source.position == source.length) {
         throw new IllegalArgumentException("Missing name at " + String.valueOf(source));
      } else {
         int start = source.position;
         if (source.value.charAt(source.position) == '`') {
            ++source.position;
            StringBuilder buffer = new StringBuilder();
            boolean closed = false;

            while(source.position < source.length) {
               char ch = source.value.charAt(source.position);
               ++source.position;
               if (ch == '`') {
                  if (source.position >= source.length || source.value.charAt(source.position) != '`') {
                     closed = true;
                     break;
                  }

                  ++source.position;
                  buffer.append('`');
               } else {
                  buffer.append(ch);
               }
            }

            if (!closed) {
               source.position = start;
               throw new IllegalArgumentException("Unmatched quote at " + String.valueOf(source));
            } else if (buffer.length() == 0) {
               throw new IllegalArgumentException("Empty quoted field name at " + String.valueOf(source));
            } else {
               return buffer.toString();
            }
         } else {
            while(source.position < source.length) {
               char ch = source.value.charAt(source.position);
               if (!Character.isLetterOrDigit(ch) && ch != '_') {
                  break;
               }

               ++source.position;
            }

            if (source.position == start) {
               throw new IllegalArgumentException("Missing name at " + String.valueOf(source));
            } else {
               return source.value.substring(start, source.position);
            }
         }
      }
   }

   static void requireChar(StringPosition source, char required) {
      if (source.position < source.length && source.value.charAt(source.position) == required) {
         ++source.position;
      } else {
         throw new IllegalArgumentException("Missing required char '" + required + "' at " + String.valueOf(source));
      }
   }

   private static boolean consumeChar(StringPosition source, char ch) {
      boolean result = source.position < source.length && source.value.charAt(source.position) == ch;
      if (result) {
         ++source.position;
      }

      return result;
   }

   private static void parseUnion(TypeDescription type, StringPosition source) {
      requireChar(source, '<');

      do {
         type.addUnionChild(parseType(source));
      } while(consumeChar(source, ','));

      requireChar(source, '>');
   }

   private static void parseStruct(TypeDescription type, StringPosition source) {
      requireChar(source, '<');
      boolean needComma = false;

      while(!consumeChar(source, '>')) {
         if (needComma) {
            requireChar(source, ',');
         } else {
            needComma = true;
         }

         String fieldName = parseName(source);
         requireChar(source, ':');
         type.addField(fieldName, parseType(source));
      }

   }

   public static TypeDescription parseType(StringPosition source) {
      TypeDescription result = new TypeDescription(parseCategory(source));
      switch (result.getCategory()) {
         case BINARY:
         case BOOLEAN:
         case BYTE:
         case DATE:
         case DOUBLE:
         case FLOAT:
         case INT:
         case LONG:
         case SHORT:
         case STRING:
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            break;
         case CHAR:
         case VARCHAR:
            requireChar(source, '(');
            result.withMaxLength(parseInt(source));
            requireChar(source, ')');
            break;
         case DECIMAL:
            requireChar(source, '(');
            int precision = parseInt(source);
            requireChar(source, ',');
            result.withScale(parseInt(source));
            result.withPrecision(precision);
            requireChar(source, ')');
            break;
         case LIST:
            requireChar(source, '<');
            TypeDescription child = parseType(source);
            result.addChild(child);
            requireChar(source, '>');
            break;
         case MAP:
            requireChar(source, '<');
            TypeDescription keyType = parseType(source);
            result.addChild(keyType);
            requireChar(source, ',');
            TypeDescription valueType = parseType(source);
            result.addChild(valueType);
            requireChar(source, '>');
            break;
         case UNION:
            parseUnion(result, source);
            break;
         case STRUCT:
            parseStruct(result, source);
            break;
         default:
            String var10002 = String.valueOf(result.getCategory());
            throw new IllegalArgumentException("Unknown type " + var10002 + " at " + String.valueOf(source));
      }

      return result;
   }

   private static List splitName(StringPosition source) {
      List<String> result = new ArrayList();

      do {
         result.add(parseName(source));
      } while(consumeChar(source, '.'));

      return result;
   }

   public static TypeDescription findSubtype(TypeDescription schema, StringPosition source) {
      return findSubtype(schema, source, true);
   }

   public static TypeDescription findSubtype(TypeDescription schema, StringPosition source, boolean isSchemaEvolutionCaseAware) {
      TypeFinder result = new TypeFinder(removeAcid(schema));
      findColumn(result.current, (StringPosition)source, isSchemaEvolutionCaseAware, result);
      return result.current;
   }

   private static TypeDescription removeAcid(TypeDescription schema) {
      return SchemaEvolution.checkAcidSchema(schema) ? SchemaEvolution.getBaseRow(schema) : schema;
   }

   private static int findCaseInsensitive(List list, String goal) {
      for(int i = 0; i < list.size(); ++i) {
         if (((String)list.get(i)).equalsIgnoreCase(goal)) {
            return i;
         }
      }

      return -1;
   }

   public static void findSubtype(TypeDescription schema, int goal, TypeVisitor visitor) {
      TypeDescription current = schema;
      int id = schema.getId();
      if (goal >= id && goal <= schema.getMaximumId()) {
         for(; id != goal; id = current.getId()) {
            List<TypeDescription> children = current.getChildren();

            for(int i = 0; i < children.size(); ++i) {
               TypeDescription child = (TypeDescription)children.get(i);
               if (goal <= child.getMaximumId()) {
                  current = child;
                  visitor.visit(child, i);
                  break;
               }
            }
         }

      } else {
         throw new IllegalArgumentException("Unknown type id " + goal + " in " + schema.toJson());
      }
   }

   public static void findColumn(TypeDescription schema, StringPosition source, boolean isSchemaEvolutionCaseAware, TypeVisitor visitor) {
      findColumn(schema, splitName(source), isSchemaEvolutionCaseAware, visitor);
   }

   public static void findColumn(TypeDescription schema, List names, boolean isSchemaEvolutionCaseAware, TypeVisitor visitor) {
      if (names.size() == 1 && INTEGER_PATTERN.matcher((CharSequence)names.get(0)).matches()) {
         findSubtype(schema, Integer.parseInt((String)names.get(0)), visitor);
      } else {
         TypeDescription current = schema;

         while(names.size() > 0) {
            String first = (String)names.remove(0);
            int posn;
            switch (current.getCategory()) {
               case LIST:
                  if (first.equals("_elem")) {
                     posn = 0;
                  } else {
                     posn = -1;
                  }
                  break;
               case MAP:
                  if (first.equals("_key")) {
                     posn = 0;
                  } else if (first.equals("_value")) {
                     posn = 1;
                  } else {
                     posn = -1;
                  }
                  break;
               case UNION:
                  try {
                     posn = Integer.parseInt(first);
                     if (posn >= 0 && posn < current.getChildren().size()) {
                        break;
                     }

                     throw new NumberFormatException("off end of union");
                  } catch (NumberFormatException e) {
                     throw new IllegalArgumentException("Field " + first + "not found in " + String.valueOf(current), e);
                  }
               case STRUCT:
                  posn = isSchemaEvolutionCaseAware ? current.getFieldNames().indexOf(first) : findCaseInsensitive(current.getFieldNames(), first);
                  break;
               default:
                  posn = -1;
            }

            if (posn < 0) {
               throw new IllegalArgumentException("Field " + first + " not found in " + String.valueOf(current));
            }

            current = (TypeDescription)current.getChildren().get(posn);
            visitor.visit(current, posn);
         }

      }
   }

   public static ColumnVector[] findColumnVectors(TypeDescription schema, StringPosition source, boolean isCaseSensitive, VectorizedRowBatch batch) {
      List<String> names = splitName(source);
      TypeDescription schemaToUse = removeAcid(schema);
      ColumnVector[] columnVectors = SchemaEvolution.checkAcidSchema(schema) ? ((StructColumnVector)batch.cols[batch.cols.length - 1]).fields : batch.cols;
      ColumnFinder result = new ColumnFinder(schemaToUse, columnVectors, names.size());
      findColumn(schemaToUse, (List)names, isCaseSensitive, result);
      return result.result;
   }

   public static List findSubtypeList(TypeDescription schema, StringPosition source) {
      List<TypeDescription> result = new ArrayList();
      if (source.hasCharactersLeft()) {
         do {
            result.add(findSubtype(schema, source));
         } while(consumeChar(source, ','));
      }

      return result;
   }

   public static void parseKeys(StringPosition source, TypeDescription schema) {
      if (source.hasCharactersLeft()) {
         do {
            String keyName = parseName(source);
            requireChar(source, ':');

            for(TypeDescription field : findSubtypeList(schema, source)) {
               String prev = field.getAttributeValue("encrypt");
               if (prev != null && !prev.equals(keyName)) {
                  throw new IllegalArgumentException("Conflicting encryption keys " + keyName + " and " + prev);
               }

               field.setAttribute("encrypt", keyName);
            }
         } while(consumeChar(source, ';'));
      }

   }

   public static void parseMasks(StringPosition source, TypeDescription schema) {
      if (source.hasCharactersLeft()) {
         do {
            int start = source.position;
            parseName(source);

            while(consumeChar(source, ',')) {
               parseName(source);
            }

            String maskString = source.fromPosition(start);
            requireChar(source, ':');

            for(TypeDescription field : findSubtypeList(schema, source)) {
               String prev = field.getAttributeValue("mask");
               if (prev != null && !prev.equals(maskString)) {
                  throw new IllegalArgumentException("Conflicting encryption masks " + maskString + " and " + prev);
               }

               field.setAttribute("mask", maskString);
            }
         } while(consumeChar(source, ';'));
      }

   }

   public static MaskDescriptionImpl buildMaskDescription(String value) {
      StringPosition source = new StringPosition(value);
      String maskName = parseName(source);
      List<String> params = new ArrayList();

      while(consumeChar(source, ',')) {
         params.add(parseName(source));
      }

      return new MaskDescriptionImpl(maskName, (String[])params.toArray(new String[0]));
   }

   public static class TypeFinder implements TypeVisitor {
      public TypeDescription current;

      public TypeFinder(TypeDescription schema) {
         this.current = schema;
      }

      public void visit(TypeDescription type, int posn) {
         this.current = type;
      }
   }

   static class ColumnFinder implements TypeVisitor {
      private ColumnVector[] top;
      private ColumnVector current;
      private final ColumnVector[] result;
      private int resultIdx;

      ColumnFinder(TypeDescription schema, ColumnVector[] columnVectors, int levels) {
         this.current = null;
         this.resultIdx = 0;
         if (schema.getCategory() == TypeDescription.Category.STRUCT) {
            this.top = columnVectors;
            this.result = new ColumnVector[levels];
         } else {
            this.result = new ColumnVector[levels + 1];
            this.current = columnVectors[0];
            this.top = null;
            this.addResult(this.current);
         }

      }

      ColumnFinder(TypeDescription schema, VectorizedRowBatch vectorizedRowBatch, int levels) {
         this(schema, vectorizedRowBatch.cols, levels);
      }

      private void addResult(ColumnVector vector) {
         this.result[this.resultIdx] = vector;
         ++this.resultIdx;
      }

      public void visit(TypeDescription type, int posn) {
         if (this.current == null) {
            this.current = this.top[posn];
            this.top = null;
         } else {
            this.current = this.navigate(this.current, posn);
         }

         this.addResult(this.current);
      }

      private ColumnVector navigate(ColumnVector parent, int posn) {
         if (parent instanceof ListColumnVector) {
            return ((ListColumnVector)parent).child;
         } else if (parent instanceof StructColumnVector) {
            return ((StructColumnVector)parent).fields[posn];
         } else if (parent instanceof UnionColumnVector) {
            return ((UnionColumnVector)parent).fields[posn];
         } else if (parent instanceof MapColumnVector) {
            MapColumnVector m = (MapColumnVector)parent;
            return posn == 0 ? m.keys : m.values;
         } else {
            throw new IllegalArgumentException("Unknown complex column vector " + String.valueOf(parent.getClass()));
         }
      }
   }

   public static class StringPosition {
      final String value;
      int position;
      final int length;

      public StringPosition(String value) {
         this.value = value == null ? "" : value;
         this.position = 0;
         this.length = this.value.length();
      }

      public String toString() {
         String var10000 = this.value.substring(0, this.position);
         return "'" + var10000 + "^" + this.value.substring(this.position) + "'";
      }

      public String fromPosition(int start) {
         return this.value.substring(start, this.position);
      }

      public boolean hasCharactersLeft() {
         return this.position != this.length;
      }
   }

   public interface TypeVisitor {
      void visit(TypeDescription var1, int var2);
   }
}
