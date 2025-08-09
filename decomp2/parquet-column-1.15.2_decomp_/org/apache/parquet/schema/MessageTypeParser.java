package org.apache.parquet.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageTypeParser {
   private static final Logger LOG = LoggerFactory.getLogger(MessageTypeParser.class);

   private MessageTypeParser() {
   }

   public static MessageType parseMessageType(String input) {
      return parse(input);
   }

   private static MessageType parse(String schemaString) {
      Tokenizer st = new Tokenizer(schemaString, " ;{}()\n\t");
      Types.MessageTypeBuilder builder = Types.buildMessage();
      String t = st.nextToken();
      check(t, "message", "start with 'message'", st);
      String name = st.nextToken();
      addGroupTypeFields(st.nextToken(), st, builder);
      return builder.named(name);
   }

   private static void addGroupTypeFields(String t, Tokenizer st, Types.GroupBuilder builder) {
      check(t, "{", "start of message", st);

      while(!(t = st.nextToken()).equals("}")) {
         addType(t, st, builder);
      }

   }

   private static void addType(String t, Tokenizer st, Types.GroupBuilder builder) {
      Type.Repetition repetition = asRepetition(t, st);
      String type = st.nextToken();
      if ("group".equalsIgnoreCase(type)) {
         addGroupType(st, repetition, builder);
      } else {
         addPrimitiveType(st, asPrimitive(type, st), repetition, builder);
      }

   }

   private static void addGroupType(Tokenizer st, Type.Repetition r, Types.GroupBuilder builder) {
      Types.GroupBuilder<?> childBuilder = builder.group(r);
      String name = st.nextToken();
      String t = st.nextToken();
      OriginalType originalType = null;
      if (t.equalsIgnoreCase("(")) {
         originalType = OriginalType.valueOf(st.nextToken());
         childBuilder.as(originalType);
         check(st.nextToken(), ")", "original type ended by )", st);
         t = st.nextToken();
      }

      if (t.equals("=")) {
         childBuilder.id(Integer.parseInt(st.nextToken()));
         t = st.nextToken();
      }

      try {
         addGroupTypeFields(t, st, childBuilder);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("problem reading type: type = group, name = " + name + ", original type = " + originalType, e);
      }

      childBuilder.named(name);
   }

   private static void addPrimitiveType(Tokenizer st, PrimitiveType.PrimitiveTypeName type, Type.Repetition r, Types.GroupBuilder builder) {
      Types.PrimitiveBuilder<?> childBuilder = builder.primitive(type, r);
      if (type == PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
         String t = st.nextToken();
         if (!t.equalsIgnoreCase("(")) {
            throw new IllegalArgumentException("expecting (length) for field of type fixed_len_byte_array");
         }

         childBuilder.length(Integer.parseInt(st.nextToken()));
         check(st.nextToken(), ")", "type length ended by )", st);
      }

      String name = st.nextToken();
      String t = st.nextToken();
      OriginalType originalType = null;
      if (t.equalsIgnoreCase("(")) {
         t = st.nextToken();
         if (!isLogicalType(t)) {
            originalType = OriginalType.valueOf(t);
            childBuilder.as(originalType);
            if (OriginalType.DECIMAL == originalType) {
               t = st.nextToken();
               if (t.equalsIgnoreCase("(")) {
                  childBuilder.precision(Integer.parseInt(st.nextToken()));
                  t = st.nextToken();
                  if (t.equalsIgnoreCase(",")) {
                     childBuilder.scale(Integer.parseInt(st.nextToken()));
                     t = st.nextToken();
                  }

                  check(t, ")", "decimal type ended by )", st);
                  t = st.nextToken();
               }
            } else {
               t = st.nextToken();
            }
         } else {
            LogicalTypeAnnotation.LogicalTypeToken logicalType = LogicalTypeAnnotation.LogicalTypeToken.valueOf(t);
            t = st.nextToken();
            List<String> tokens = new ArrayList();
            if ("(".equals(t)) {
               for(; !")".equals(t); t = st.nextToken()) {
                  if (!",".equals(t) && !"(".equals(t) && !")".equals(t)) {
                     tokens.add(t);
                  }
               }

               t = st.nextToken();
            }

            LogicalTypeAnnotation logicalTypeAnnotation = logicalType.fromString(tokens);
            childBuilder.as(logicalTypeAnnotation);
         }

         check(t, ")", "logical type ended by )", st);
         t = st.nextToken();
      }

      if (t.equals("=")) {
         childBuilder.id(Integer.parseInt(st.nextToken()));
         t = st.nextToken();
      }

      check(t, ";", "field ended by ';'", st);

      try {
         childBuilder.named(name);
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("problem reading type: type = " + type + ", name = " + name + ", original type = " + originalType, e);
      }
   }

   private static boolean isLogicalType(String t) {
      return Arrays.stream(LogicalTypeAnnotation.LogicalTypeToken.values()).anyMatch((type) -> type.name().equals(t));
   }

   private static PrimitiveType.PrimitiveTypeName asPrimitive(String t, Tokenizer st) {
      try {
         return PrimitiveType.PrimitiveTypeName.valueOf(t.toUpperCase(Locale.ENGLISH));
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("expected one of " + Arrays.toString(PrimitiveType.PrimitiveTypeName.values()) + " got " + t + " at " + st.getLocationString(), e);
      }
   }

   private static Type.Repetition asRepetition(String t, Tokenizer st) {
      try {
         return Type.Repetition.valueOf(t.toUpperCase(Locale.ENGLISH));
      } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("expected one of " + Arrays.toString(Type.Repetition.values()) + " got " + t + " at " + st.getLocationString(), e);
      }
   }

   private static void check(String t, String expected, String message, Tokenizer tokenizer) {
      if (!t.equalsIgnoreCase(expected)) {
         throw new IllegalArgumentException(message + ": expected '" + expected + "' but got '" + t + "' at " + tokenizer.getLocationString());
      }
   }

   private static class Tokenizer {
      private StringTokenizer st;
      private int line = 0;
      private StringBuilder currentLine = new StringBuilder();

      public Tokenizer(String schemaString, String string) {
         this.st = new StringTokenizer(schemaString, " ,;{}()\n\t=", true);
      }

      public String nextToken() {
         while(true) {
            if (this.st.hasMoreTokens()) {
               String t = this.st.nextToken();
               if (t.equals("\n")) {
                  ++this.line;
                  this.currentLine.setLength(0);
               } else {
                  this.currentLine.append(t);
               }

               if (this.isWhitespace(t)) {
                  continue;
               }

               return t;
            }

            throw new IllegalArgumentException("unexpected end of schema");
         }
      }

      private boolean isWhitespace(String t) {
         return t.equals(" ") || t.equals("\t") || t.equals("\n");
      }

      public String getLocationString() {
         return "line " + this.line + ": " + this.currentLine.toString();
      }
   }
}
