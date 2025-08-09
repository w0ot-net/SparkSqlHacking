package org.apache.orc.protobuf;

import java.io.IOException;
import java.lang.Character.UnicodeBlock;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
   private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
   private static final String DEBUG_STRING_SILENT_MARKER = "\t ";
   private static final Parser PARSER = TextFormat.Parser.newBuilder().build();

   private TextFormat() {
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "TextFormat.printer().print(message, output)",
      imports = {"org.apache.orc.protobuf.TextFormat"}
   )
   public static void print(final MessageOrBuilder message, final Appendable output) throws IOException {
      printer().print(message, output);
   }

   /** @deprecated */
   @Deprecated
   public static void print(final UnknownFieldSet fields, final Appendable output) throws IOException {
      printer().print(fields, output);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "TextFormat.printer().escapingNonAscii(false).print(message, output)",
      imports = {"org.apache.orc.protobuf.TextFormat"}
   )
   public static void printUnicode(final MessageOrBuilder message, final Appendable output) throws IOException {
      printer().escapingNonAscii(false).print(message, output);
   }

   /** @deprecated */
   @Deprecated
   public static void printUnicode(final UnknownFieldSet fields, final Appendable output) throws IOException {
      printer().escapingNonAscii(false).print(fields, output);
   }

   public static String shortDebugString(final MessageOrBuilder message) {
      return printer().shortDebugString(message);
   }

   /** @deprecated */
   @Deprecated
   public static String shortDebugString(final Descriptors.FieldDescriptor field, final Object value) {
      return printer().shortDebugString(field, value);
   }

   /** @deprecated */
   @Deprecated
   public static String shortDebugString(final UnknownFieldSet fields) {
      return printer().shortDebugString(fields);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "TextFormat.printer().printToString(message)",
      imports = {"org.apache.orc.protobuf.TextFormat"}
   )
   public static String printToString(final MessageOrBuilder message) {
      return printer().printToString(message);
   }

   /** @deprecated */
   @Deprecated
   public static String printToString(final UnknownFieldSet fields) {
      return printer().printToString(fields);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "TextFormat.printer().escapingNonAscii(false).printToString(message)",
      imports = {"org.apache.orc.protobuf.TextFormat"}
   )
   public static String printToUnicodeString(final MessageOrBuilder message) {
      return printer().escapingNonAscii(false).printToString(message);
   }

   /** @deprecated */
   @Deprecated
   public static String printToUnicodeString(final UnknownFieldSet fields) {
      return printer().escapingNonAscii(false).printToString(fields);
   }

   /** @deprecated */
   @Deprecated
   public static void printField(final Descriptors.FieldDescriptor field, final Object value, final Appendable output) throws IOException {
      printer().printField(field, value, output);
   }

   /** @deprecated */
   @Deprecated
   public static String printFieldToString(final Descriptors.FieldDescriptor field, final Object value) {
      return printer().printFieldToString(field, value);
   }

   /** @deprecated */
   @Deprecated
   public static void printUnicodeFieldValue(final Descriptors.FieldDescriptor field, final Object value, final Appendable output) throws IOException {
      printer().escapingNonAscii(false).printFieldValue(field, value, output);
   }

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "TextFormat.printer().printFieldValue(field, value, output)",
      imports = {"org.apache.orc.protobuf.TextFormat"}
   )
   public static void printFieldValue(final Descriptors.FieldDescriptor field, final Object value, final Appendable output) throws IOException {
      printer().printFieldValue(field, value, output);
   }

   public static void printUnknownFieldValue(final int tag, final Object value, final Appendable output) throws IOException {
      printUnknownFieldValue(tag, value, multiLineOutput(output));
   }

   private static void printUnknownFieldValue(final int tag, final Object value, final TextGenerator generator) throws IOException {
      switch (WireFormat.getTagWireType(tag)) {
         case 0:
            generator.print(unsignedToString((Long)value));
            break;
         case 1:
            generator.print(String.format((Locale)null, "0x%016x", (Long)value));
            break;
         case 2:
            try {
               UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
               generator.print("{");
               generator.eol();
               generator.indent();
               TextFormat.Printer.printUnknownFields(message, generator);
               generator.outdent();
               generator.print("}");
            } catch (InvalidProtocolBufferException var4) {
               generator.print("\"");
               generator.print(escapeBytes((ByteString)value));
               generator.print("\"");
            }
            break;
         case 3:
            TextFormat.Printer.printUnknownFields((UnknownFieldSet)value, generator);
            break;
         case 4:
         default:
            throw new IllegalArgumentException("Bad tag: " + tag);
         case 5:
            generator.print(String.format((Locale)null, "0x%08x", (Integer)value));
      }

   }

   public static Printer printer() {
      return TextFormat.Printer.DEFAULT;
   }

   public static String unsignedToString(final int value) {
      return value >= 0 ? Integer.toString(value) : Long.toString((long)value & 4294967295L);
   }

   public static String unsignedToString(final long value) {
      return value >= 0L ? Long.toString(value) : BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
   }

   private static TextGenerator multiLineOutput(Appendable output) {
      return new TextGenerator(output, false);
   }

   private static TextGenerator singleLineOutput(Appendable output) {
      return new TextGenerator(output, true);
   }

   public static Parser getParser() {
      return PARSER;
   }

   public static void merge(final Readable input, final Message.Builder builder) throws IOException {
      PARSER.merge(input, builder);
   }

   public static void merge(final CharSequence input, final Message.Builder builder) throws ParseException {
      PARSER.merge(input, builder);
   }

   public static Message parse(final CharSequence input, final Class protoClass) throws ParseException {
      Message.Builder builder = ((Message)Internal.getDefaultInstance(protoClass)).newBuilderForType();
      merge(input, builder);
      T output = (T)builder.build();
      return output;
   }

   public static void merge(final Readable input, final ExtensionRegistry extensionRegistry, final Message.Builder builder) throws IOException {
      PARSER.merge(input, extensionRegistry, builder);
   }

   public static void merge(final CharSequence input, final ExtensionRegistry extensionRegistry, final Message.Builder builder) throws ParseException {
      PARSER.merge(input, extensionRegistry, builder);
   }

   public static Message parse(final CharSequence input, final ExtensionRegistry extensionRegistry, final Class protoClass) throws ParseException {
      Message.Builder builder = ((Message)Internal.getDefaultInstance(protoClass)).newBuilderForType();
      merge(input, extensionRegistry, builder);
      T output = (T)builder.build();
      return output;
   }

   public static String escapeBytes(ByteString input) {
      return TextFormatEscaper.escapeBytes(input);
   }

   public static String escapeBytes(byte[] input) {
      return TextFormatEscaper.escapeBytes(input);
   }

   public static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
      ByteString input = ByteString.copyFromUtf8(charString.toString());
      byte[] result = new byte[input.size()];
      int pos = 0;

      for(int i = 0; i < input.size(); ++i) {
         byte c = input.byteAt(i);
         if (c != 92) {
            result[pos++] = c;
         } else {
            if (i + 1 >= input.size()) {
               throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            }

            ++i;
            c = input.byteAt(i);
            if (isOctal(c)) {
               int code = digitValue(c);
               if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
                  ++i;
                  code = code * 8 + digitValue(input.byteAt(i));
               }

               if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
                  ++i;
                  code = code * 8 + digitValue(input.byteAt(i));
               }

               result[pos++] = (byte)code;
            } else {
               switch (c) {
                  case 34:
                     result[pos++] = 34;
                     break;
                  case 39:
                     result[pos++] = 39;
                     break;
                  case 63:
                     result[pos++] = 63;
                     break;
                  case 85:
                     ++i;
                     if (i + 7 >= input.size()) {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                     }

                     int codepoint = 0;

                     for(int offset = i; offset < i + 8; ++offset) {
                        byte b = input.byteAt(offset);
                        if (!isHex(b)) {
                           throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                        }

                        codepoint = codepoint << 4 | digitValue(b);
                     }

                     if (!Character.isValidCodePoint(codepoint)) {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input.substring(i, i + 8).toStringUtf8() + "' is not a valid code point value");
                     }

                     Character.UnicodeBlock unicodeBlock = UnicodeBlock.of(codepoint);
                     if (unicodeBlock != null && (unicodeBlock.equals(UnicodeBlock.LOW_SURROGATES) || unicodeBlock.equals(UnicodeBlock.HIGH_SURROGATES) || unicodeBlock.equals(UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES))) {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input.substring(i, i + 8).toStringUtf8() + "' refers to a surrogate code unit");
                     }

                     int[] codepoints = new int[1];
                     codepoints[0] = codepoint;
                     byte[] chUtf8 = (new String(codepoints, 0, 1)).getBytes(Internal.UTF_8);
                     System.arraycopy(chUtf8, 0, result, pos, chUtf8.length);
                     pos += chUtf8.length;
                     i += 7;
                     break;
                  case 92:
                     result[pos++] = 92;
                     break;
                  case 97:
                     result[pos++] = 7;
                     break;
                  case 98:
                     result[pos++] = 8;
                     break;
                  case 102:
                     result[pos++] = 12;
                     break;
                  case 110:
                     result[pos++] = 10;
                     break;
                  case 114:
                     result[pos++] = 13;
                     break;
                  case 116:
                     result[pos++] = 9;
                     break;
                  case 117:
                     ++i;
                     if (i + 3 >= input.size() || !isHex(input.byteAt(i)) || !isHex(input.byteAt(i + 1)) || !isHex(input.byteAt(i + 2)) || !isHex(input.byteAt(i + 3))) {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' with too few hex chars");
                     }

                     char ch = (char)(digitValue(input.byteAt(i)) << 12 | digitValue(input.byteAt(i + 1)) << 8 | digitValue(input.byteAt(i + 2)) << 4 | digitValue(input.byteAt(i + 3)));
                     if (ch >= '\ud800' && ch <= '\udfff') {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' refers to a surrogate");
                     }

                     byte[] chUtf8 = Character.toString(ch).getBytes(Internal.UTF_8);
                     System.arraycopy(chUtf8, 0, result, pos, chUtf8.length);
                     pos += chUtf8.length;
                     i += 3;
                     break;
                  case 118:
                     result[pos++] = 11;
                     break;
                  case 120:
                     int code = 0;
                     if (i + 1 >= input.size() || !isHex(input.byteAt(i + 1))) {
                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                     }

                     ++i;
                     code = digitValue(input.byteAt(i));
                     if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
                        ++i;
                        code = code * 16 + digitValue(input.byteAt(i));
                     }

                     result[pos++] = (byte)code;
                     break;
                  default:
                     throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)c + '\'');
               }
            }
         }
      }

      return result.length == pos ? ByteString.wrap(result) : ByteString.copyFrom(result, 0, pos);
   }

   static String escapeText(final String input) {
      return escapeBytes(ByteString.copyFromUtf8(input));
   }

   public static String escapeDoubleQuotesAndBackslashes(final String input) {
      return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input);
   }

   static String unescapeText(final String input) throws InvalidEscapeSequenceException {
      return unescapeBytes(input).toStringUtf8();
   }

   private static boolean isOctal(final byte c) {
      return 48 <= c && c <= 55;
   }

   private static boolean isHex(final byte c) {
      return 48 <= c && c <= 57 || 97 <= c && c <= 102 || 65 <= c && c <= 70;
   }

   private static int digitValue(final byte c) {
      if (48 <= c && c <= 57) {
         return c - 48;
      } else {
         return 97 <= c && c <= 122 ? c - 97 + 10 : c - 65 + 10;
      }
   }

   static int parseInt32(final String text) throws NumberFormatException {
      return (int)parseInteger(text, true, false);
   }

   static int parseUInt32(final String text) throws NumberFormatException {
      return (int)parseInteger(text, false, false);
   }

   static long parseInt64(final String text) throws NumberFormatException {
      return parseInteger(text, true, true);
   }

   static long parseUInt64(final String text) throws NumberFormatException {
      return parseInteger(text, false, true);
   }

   private static long parseInteger(final String text, final boolean isSigned, final boolean isLong) throws NumberFormatException {
      int pos = 0;
      boolean negative = false;
      if (text.startsWith("-", pos)) {
         if (!isSigned) {
            throw new NumberFormatException("Number must be positive: " + text);
         }

         ++pos;
         negative = true;
      }

      int radix = 10;
      if (text.startsWith("0x", pos)) {
         pos += 2;
         radix = 16;
      } else if (text.startsWith("0", pos)) {
         radix = 8;
      }

      String numberText = text.substring(pos);
      long result = 0L;
      if (numberText.length() < 16) {
         result = Long.parseLong(numberText, radix);
         if (negative) {
            result = -result;
         }

         if (!isLong) {
            if (isSigned) {
               if (result > 2147483647L || result < -2147483648L) {
                  throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
               }
            } else if (result >= 4294967296L || result < 0L) {
               throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
            }
         }
      } else {
         BigInteger bigValue = new BigInteger(numberText, radix);
         if (negative) {
            bigValue = bigValue.negate();
         }

         if (!isLong) {
            if (isSigned) {
               if (bigValue.bitLength() > 31) {
                  throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
               }
            } else if (bigValue.bitLength() > 32) {
               throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
            }
         } else if (isSigned) {
            if (bigValue.bitLength() > 63) {
               throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text);
            }
         } else if (bigValue.bitLength() > 64) {
            throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
         }

         result = bigValue.longValue();
      }

      return result;
   }

   public static final class Printer {
      private static final Printer DEFAULT = new Printer(true, TypeRegistry.getEmptyTypeRegistry());
      private final boolean escapeNonAscii;
      private final TypeRegistry typeRegistry;

      private Printer(boolean escapeNonAscii, TypeRegistry typeRegistry) {
         this.escapeNonAscii = escapeNonAscii;
         this.typeRegistry = typeRegistry;
      }

      public Printer escapingNonAscii(boolean escapeNonAscii) {
         return new Printer(escapeNonAscii, this.typeRegistry);
      }

      public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
         if (this.typeRegistry != TypeRegistry.getEmptyTypeRegistry()) {
            throw new IllegalArgumentException("Only one typeRegistry is allowed.");
         } else {
            return new Printer(this.escapeNonAscii, typeRegistry);
         }
      }

      public void print(final MessageOrBuilder message, final Appendable output) throws IOException {
         this.print(message, TextFormat.multiLineOutput(output));
      }

      public void print(final UnknownFieldSet fields, final Appendable output) throws IOException {
         printUnknownFields(fields, TextFormat.multiLineOutput(output));
      }

      private void print(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
         if (!message.getDescriptorForType().getFullName().equals("google.protobuf.Any") || !this.printAny(message, generator)) {
            this.printMessage(message, generator);
         }
      }

      private boolean printAny(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
         Descriptors.Descriptor messageType = message.getDescriptorForType();
         Descriptors.FieldDescriptor typeUrlField = messageType.findFieldByNumber(1);
         Descriptors.FieldDescriptor valueField = messageType.findFieldByNumber(2);
         if (typeUrlField != null && typeUrlField.getType() == Descriptors.FieldDescriptor.Type.STRING && valueField != null && valueField.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
            String typeUrl = (String)message.getField(typeUrlField);
            if (typeUrl.isEmpty()) {
               return false;
            } else {
               Object value = message.getField(valueField);
               Message.Builder contentBuilder = null;

               try {
                  Descriptors.Descriptor contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
                  if (contentType == null) {
                     return false;
                  }

                  contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
                  contentBuilder.mergeFrom((ByteString)value);
               } catch (InvalidProtocolBufferException var10) {
                  return false;
               }

               generator.print("[");
               generator.print(typeUrl);
               generator.print("] {");
               generator.eol();
               generator.indent();
               this.print((MessageOrBuilder)contentBuilder, (TextGenerator)generator);
               generator.outdent();
               generator.print("}");
               generator.eol();
               return true;
            }
         } else {
            return false;
         }
      }

      public String printFieldToString(final Descriptors.FieldDescriptor field, final Object value) {
         try {
            StringBuilder text = new StringBuilder();
            this.printField(field, value, (Appendable)text);
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      public void printField(final Descriptors.FieldDescriptor field, final Object value, final Appendable output) throws IOException {
         this.printField(field, value, TextFormat.multiLineOutput(output));
      }

      private void printField(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
         if (field.isMapField()) {
            List<MapEntryAdapter> adapters = new ArrayList();

            for(Object entry : (List)value) {
               adapters.add(new MapEntryAdapter(entry, field));
            }

            Collections.sort(adapters);

            for(MapEntryAdapter adapter : adapters) {
               this.printSingleField(field, adapter.getEntry(), generator);
            }
         } else if (field.isRepeated()) {
            for(Object element : (List)value) {
               this.printSingleField(field, element, generator);
            }
         } else {
            this.printSingleField(field, value, generator);
         }

      }

      public void printFieldValue(final Descriptors.FieldDescriptor field, final Object value, final Appendable output) throws IOException {
         this.printFieldValue(field, value, TextFormat.multiLineOutput(output));
      }

      private void printFieldValue(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
         switch (field.getType()) {
            case INT32:
            case SINT32:
            case SFIXED32:
               generator.print(((Integer)value).toString());
               break;
            case INT64:
            case SINT64:
            case SFIXED64:
               generator.print(((Long)value).toString());
               break;
            case BOOL:
               generator.print(((Boolean)value).toString());
               break;
            case FLOAT:
               generator.print(((Float)value).toString());
               break;
            case DOUBLE:
               generator.print(((Double)value).toString());
               break;
            case UINT32:
            case FIXED32:
               generator.print(TextFormat.unsignedToString((Integer)value));
               break;
            case UINT64:
            case FIXED64:
               generator.print(TextFormat.unsignedToString((Long)value));
               break;
            case STRING:
               generator.print("\"");
               generator.print(this.escapeNonAscii ? TextFormatEscaper.escapeText((String)value) : TextFormat.escapeDoubleQuotesAndBackslashes((String)value).replace("\n", "\\n"));
               generator.print("\"");
               break;
            case BYTES:
               generator.print("\"");
               if (value instanceof ByteString) {
                  generator.print(TextFormat.escapeBytes((ByteString)value));
               } else {
                  generator.print(TextFormat.escapeBytes((byte[])value));
               }

               generator.print("\"");
               break;
            case ENUM:
               generator.print(((Descriptors.EnumValueDescriptor)value).getName());
               break;
            case MESSAGE:
            case GROUP:
               this.print((MessageOrBuilder)value, generator);
         }

      }

      public String printToString(final MessageOrBuilder message) {
         try {
            StringBuilder text = new StringBuilder();
            this.print((MessageOrBuilder)message, (Appendable)text);
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      public String printToString(final UnknownFieldSet fields) {
         try {
            StringBuilder text = new StringBuilder();
            this.print((UnknownFieldSet)fields, (Appendable)text);
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      public String shortDebugString(final MessageOrBuilder message) {
         try {
            StringBuilder text = new StringBuilder();
            this.print(message, TextFormat.singleLineOutput(text));
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      public String shortDebugString(final Descriptors.FieldDescriptor field, final Object value) {
         try {
            StringBuilder text = new StringBuilder();
            this.printField(field, value, TextFormat.singleLineOutput(text));
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      public String shortDebugString(final UnknownFieldSet fields) {
         try {
            StringBuilder text = new StringBuilder();
            printUnknownFields(fields, TextFormat.singleLineOutput(text));
            return text.toString();
         } catch (IOException e) {
            throw new IllegalStateException(e);
         }
      }

      private static void printUnknownFieldValue(final int tag, final Object value, final TextGenerator generator) throws IOException {
         switch (WireFormat.getTagWireType(tag)) {
            case 0:
               generator.print(TextFormat.unsignedToString((Long)value));
               break;
            case 1:
               generator.print(String.format((Locale)null, "0x%016x", (Long)value));
               break;
            case 2:
               try {
                  UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
                  generator.print("{");
                  generator.eol();
                  generator.indent();
                  printUnknownFields(message, generator);
                  generator.outdent();
                  generator.print("}");
               } catch (InvalidProtocolBufferException var4) {
                  generator.print("\"");
                  generator.print(TextFormat.escapeBytes((ByteString)value));
                  generator.print("\"");
               }
               break;
            case 3:
               printUnknownFields((UnknownFieldSet)value, generator);
               break;
            case 4:
            default:
               throw new IllegalArgumentException("Bad tag: " + tag);
            case 5:
               generator.print(String.format((Locale)null, "0x%08x", (Integer)value));
         }

      }

      private void printMessage(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
         for(Map.Entry field : message.getAllFields().entrySet()) {
            this.printField((Descriptors.FieldDescriptor)field.getKey(), field.getValue(), generator);
         }

         printUnknownFields(message.getUnknownFields(), generator);
      }

      private void printSingleField(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
         if (field.isExtension()) {
            generator.print("[");
            if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType()) {
               generator.print(field.getMessageType().getFullName());
            } else {
               generator.print(field.getFullName());
            }

            generator.print("]");
         } else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
            generator.print(field.getMessageType().getName());
         } else {
            generator.print(field.getName());
         }

         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            generator.print(" {");
            generator.eol();
            generator.indent();
         } else {
            generator.print(": ");
         }

         this.printFieldValue(field, value, generator);
         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            generator.outdent();
            generator.print("}");
         }

         generator.eol();
      }

      private static void printUnknownFields(final UnknownFieldSet unknownFields, final TextGenerator generator) throws IOException {
         for(Map.Entry entry : unknownFields.asMap().entrySet()) {
            int number = (Integer)entry.getKey();
            UnknownFieldSet.Field field = (UnknownFieldSet.Field)entry.getValue();
            printUnknownField(number, 0, field.getVarintList(), generator);
            printUnknownField(number, 5, field.getFixed32List(), generator);
            printUnknownField(number, 1, field.getFixed64List(), generator);
            printUnknownField(number, 2, field.getLengthDelimitedList(), generator);

            for(UnknownFieldSet value : field.getGroupList()) {
               generator.print(((Integer)entry.getKey()).toString());
               generator.print(" {");
               generator.eol();
               generator.indent();
               printUnknownFields(value, generator);
               generator.outdent();
               generator.print("}");
               generator.eol();
            }
         }

      }

      private static void printUnknownField(final int number, final int wireType, final List values, final TextGenerator generator) throws IOException {
         for(Object value : values) {
            generator.print(String.valueOf(number));
            generator.print(": ");
            printUnknownFieldValue(wireType, value, generator);
            generator.eol();
         }

      }

      private static class MapEntryAdapter implements Comparable {
         private Object entry;
         private MapEntry mapEntry;
         private final Descriptors.FieldDescriptor.JavaType fieldType;

         MapEntryAdapter(Object entry, Descriptors.FieldDescriptor fieldDescriptor) {
            if (entry instanceof MapEntry) {
               this.mapEntry = (MapEntry)entry;
            } else {
               this.entry = entry;
            }

            this.fieldType = extractFieldType(fieldDescriptor);
         }

         private static Descriptors.FieldDescriptor.JavaType extractFieldType(Descriptors.FieldDescriptor fieldDescriptor) {
            return ((Descriptors.FieldDescriptor)fieldDescriptor.getMessageType().getFields().get(0)).getJavaType();
         }

         Object getKey() {
            return this.mapEntry != null ? this.mapEntry.getKey() : null;
         }

         Object getEntry() {
            return this.mapEntry != null ? this.mapEntry : this.entry;
         }

         public int compareTo(MapEntryAdapter b) {
            if (this.getKey() != null && b.getKey() != null) {
               switch (this.fieldType) {
                  case BOOLEAN:
                     return Boolean.valueOf((Boolean)this.getKey()).compareTo((Boolean)b.getKey());
                  case LONG:
                     return Long.valueOf((Long)this.getKey()).compareTo((Long)b.getKey());
                  case INT:
                     return Integer.valueOf((Integer)this.getKey()).compareTo((Integer)b.getKey());
                  case STRING:
                     String aString = (String)this.getKey();
                     String bString = (String)b.getKey();
                     if (aString == null && bString == null) {
                        return 0;
                     } else if (aString == null && bString != null) {
                        return -1;
                     } else {
                        if (aString != null && bString == null) {
                           return 1;
                        }

                        return aString.compareTo(bString);
                     }
                  default:
                     return 0;
               }
            } else {
               TextFormat.logger.info("Invalid key for map field.");
               return -1;
            }
         }
      }
   }

   private static final class TextGenerator {
      private final Appendable output;
      private final StringBuilder indent;
      private final boolean singleLineMode;
      private boolean atStartOfLine;

      private TextGenerator(final Appendable output, boolean singleLineMode) {
         this.indent = new StringBuilder();
         this.atStartOfLine = false;
         this.output = output;
         this.singleLineMode = singleLineMode;
      }

      public void indent() {
         this.indent.append("  ");
      }

      public void outdent() {
         int length = this.indent.length();
         if (length == 0) {
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
         } else {
            this.indent.setLength(length - 2);
         }
      }

      public void print(final CharSequence text) throws IOException {
         if (this.atStartOfLine) {
            this.atStartOfLine = false;
            this.output.append((CharSequence)(this.singleLineMode ? " " : this.indent));
         }

         this.output.append(text);
      }

      public void eol() throws IOException {
         if (!this.singleLineMode) {
            this.output.append("\n");
         }

         this.atStartOfLine = true;
      }
   }

   private static final class Tokenizer {
      private final CharSequence text;
      private final Matcher matcher;
      private String currentToken;
      private int pos;
      private int line;
      private int column;
      private int previousLine;
      private int previousColumn;
      private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
      private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
      private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
      private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
      private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
      private boolean containsSilentMarkerAfterCurrentToken;
      private boolean containsSilentMarkerAfterPrevToken;

      private Tokenizer(final CharSequence text) {
         this.pos = 0;
         this.line = 0;
         this.column = 0;
         this.previousLine = 0;
         this.previousColumn = 0;
         this.containsSilentMarkerAfterCurrentToken = false;
         this.containsSilentMarkerAfterPrevToken = false;
         this.text = text;
         this.matcher = WHITESPACE.matcher(text);
         this.skipWhitespace();
         this.nextToken();
      }

      int getPreviousLine() {
         return this.previousLine;
      }

      int getPreviousColumn() {
         return this.previousColumn;
      }

      int getLine() {
         return this.line;
      }

      int getColumn() {
         return this.column;
      }

      boolean getContainsSilentMarkerAfterCurrentToken() {
         return this.containsSilentMarkerAfterCurrentToken;
      }

      boolean getContainsSilentMarkerAfterPrevToken() {
         return this.containsSilentMarkerAfterPrevToken;
      }

      boolean atEnd() {
         return this.currentToken.length() == 0;
      }

      void nextToken() {
         this.previousLine = this.line;

         for(this.previousColumn = this.column; this.pos < this.matcher.regionStart(); ++this.pos) {
            if (this.text.charAt(this.pos) == '\n') {
               ++this.line;
               this.column = 0;
            } else {
               ++this.column;
            }
         }

         if (this.matcher.regionStart() == this.matcher.regionEnd()) {
            this.currentToken = "";
         } else {
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
               this.currentToken = this.matcher.group();
               this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            } else {
               this.currentToken = String.valueOf(this.text.charAt(this.pos));
               this.matcher.region(this.pos + 1, this.matcher.regionEnd());
            }

            this.skipWhitespace();
         }

      }

      private void skipWhitespace() {
         this.matcher.usePattern(WHITESPACE);
         if (this.matcher.lookingAt()) {
            this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
         }

      }

      boolean tryConsume(final String token) {
         if (this.currentToken.equals(token)) {
            this.nextToken();
            return true;
         } else {
            return false;
         }
      }

      void consume(final String token) throws ParseException {
         if (!this.tryConsume(token)) {
            throw this.parseException("Expected \"" + token + "\".");
         }
      }

      boolean lookingAtInteger() {
         if (this.currentToken.length() == 0) {
            return false;
         } else {
            char c = this.currentToken.charAt(0);
            return '0' <= c && c <= '9' || c == '-' || c == '+';
         }
      }

      boolean lookingAt(String text) {
         return this.currentToken.equals(text);
      }

      String consumeIdentifier() throws ParseException {
         for(int i = 0; i < this.currentToken.length(); ++i) {
            char c = this.currentToken.charAt(i);
            if (('a' > c || c > 'z') && ('A' > c || c > 'Z') && ('0' > c || c > '9') && c != '_' && c != '.') {
               throw this.parseException("Expected identifier. Found '" + this.currentToken + "'");
            }
         }

         String result = this.currentToken;
         this.nextToken();
         return result;
      }

      boolean tryConsumeIdentifier() {
         try {
            this.consumeIdentifier();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      int consumeInt32() throws ParseException {
         try {
            int result = TextFormat.parseInt32(this.currentToken);
            this.nextToken();
            return result;
         } catch (NumberFormatException e) {
            throw this.integerParseException(e);
         }
      }

      int consumeUInt32() throws ParseException {
         try {
            int result = TextFormat.parseUInt32(this.currentToken);
            this.nextToken();
            return result;
         } catch (NumberFormatException e) {
            throw this.integerParseException(e);
         }
      }

      long consumeInt64() throws ParseException {
         try {
            long result = TextFormat.parseInt64(this.currentToken);
            this.nextToken();
            return result;
         } catch (NumberFormatException e) {
            throw this.integerParseException(e);
         }
      }

      boolean tryConsumeInt64() {
         try {
            this.consumeInt64();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      long consumeUInt64() throws ParseException {
         try {
            long result = TextFormat.parseUInt64(this.currentToken);
            this.nextToken();
            return result;
         } catch (NumberFormatException e) {
            throw this.integerParseException(e);
         }
      }

      public boolean tryConsumeUInt64() {
         try {
            this.consumeUInt64();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      public double consumeDouble() throws ParseException {
         if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
            boolean negative = this.currentToken.startsWith("-");
            this.nextToken();
            return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
         } else if (this.currentToken.equalsIgnoreCase("nan")) {
            this.nextToken();
            return Double.NaN;
         } else {
            try {
               double result = Double.parseDouble(this.currentToken);
               this.nextToken();
               return result;
            } catch (NumberFormatException e) {
               throw this.floatParseException(e);
            }
         }
      }

      public boolean tryConsumeDouble() {
         try {
            this.consumeDouble();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      public float consumeFloat() throws ParseException {
         if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
            boolean negative = this.currentToken.startsWith("-");
            this.nextToken();
            return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
         } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
            this.nextToken();
            return Float.NaN;
         } else {
            try {
               float result = Float.parseFloat(this.currentToken);
               this.nextToken();
               return result;
            } catch (NumberFormatException e) {
               throw this.floatParseException(e);
            }
         }
      }

      public boolean tryConsumeFloat() {
         try {
            this.consumeFloat();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      public boolean consumeBoolean() throws ParseException {
         if (!this.currentToken.equals("true") && !this.currentToken.equals("True") && !this.currentToken.equals("t") && !this.currentToken.equals("1")) {
            if (!this.currentToken.equals("false") && !this.currentToken.equals("False") && !this.currentToken.equals("f") && !this.currentToken.equals("0")) {
               throw this.parseException("Expected \"true\" or \"false\". Found \"" + this.currentToken + "\".");
            } else {
               this.nextToken();
               return false;
            }
         } else {
            this.nextToken();
            return true;
         }
      }

      public String consumeString() throws ParseException {
         return this.consumeByteString().toStringUtf8();
      }

      @CanIgnoreReturnValue
      ByteString consumeByteString() throws ParseException {
         List<ByteString> list = new ArrayList();
         this.consumeByteString(list);

         while(this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
            this.consumeByteString(list);
         }

         return ByteString.copyFrom((Iterable)list);
      }

      boolean tryConsumeByteString() {
         try {
            this.consumeByteString();
            return true;
         } catch (ParseException var2) {
            return false;
         }
      }

      private void consumeByteString(List list) throws ParseException {
         char quote = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : 0;
         if (quote != '"' && quote != '\'') {
            throw this.parseException("Expected string.");
         } else if (this.currentToken.length() >= 2 && this.currentToken.charAt(this.currentToken.length() - 1) == quote) {
            try {
               String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
               ByteString result = TextFormat.unescapeBytes(escaped);
               this.nextToken();
               list.add(result);
            } catch (InvalidEscapeSequenceException e) {
               throw this.parseException(e.getMessage());
            }
         } else {
            throw this.parseException("String missing ending quote.");
         }
      }

      ParseException parseException(final String description) {
         return new ParseException(this.line + 1, this.column + 1, description);
      }

      ParseException parseExceptionPreviousToken(final String description) {
         return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
      }

      private ParseException integerParseException(final NumberFormatException e) {
         return this.parseException("Couldn't parse integer: " + e.getMessage());
      }

      private ParseException floatParseException(final NumberFormatException e) {
         return this.parseException("Couldn't parse number: " + e.getMessage());
      }
   }

   public static class ParseException extends IOException {
      private static final long serialVersionUID = 3196188060225107702L;
      private final int line;
      private final int column;

      public ParseException(final String message) {
         this(-1, -1, message);
      }

      public ParseException(final int line, final int column, final String message) {
         super(Integer.toString(line) + ":" + column + ": " + message);
         this.line = line;
         this.column = column;
      }

      public int getLine() {
         return this.line;
      }

      public int getColumn() {
         return this.column;
      }
   }

   public static class UnknownFieldParseException extends ParseException {
      private final String unknownField;

      public UnknownFieldParseException(final String message) {
         this(-1, -1, "", message);
      }

      public UnknownFieldParseException(final int line, final int column, final String unknownField, final String message) {
         super(line, column, message);
         this.unknownField = unknownField;
      }

      public String getUnknownField() {
         return this.unknownField;
      }
   }

   public static class Parser {
      private final TypeRegistry typeRegistry;
      private final boolean allowUnknownFields;
      private final boolean allowUnknownEnumValues;
      private final boolean allowUnknownExtensions;
      private final SingularOverwritePolicy singularOverwritePolicy;
      private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
      private final int recursionLimit;
      private static final int BUFFER_SIZE = 4096;

      private void detectSilentMarker(Tokenizer tokenizer, Descriptors.Descriptor immediateMessageType, String fieldName) {
      }

      private Parser(TypeRegistry typeRegistry, boolean allowUnknownFields, boolean allowUnknownEnumValues, boolean allowUnknownExtensions, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder parseInfoTreeBuilder, int recursionLimit) {
         this.typeRegistry = typeRegistry;
         this.allowUnknownFields = allowUnknownFields;
         this.allowUnknownEnumValues = allowUnknownEnumValues;
         this.allowUnknownExtensions = allowUnknownExtensions;
         this.singularOverwritePolicy = singularOverwritePolicy;
         this.parseInfoTreeBuilder = parseInfoTreeBuilder;
         this.recursionLimit = recursionLimit;
      }

      public static Builder newBuilder() {
         return new Builder();
      }

      public void merge(final Readable input, final Message.Builder builder) throws IOException {
         this.merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
      }

      public void merge(final CharSequence input, final Message.Builder builder) throws ParseException {
         this.merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
      }

      public void merge(final Readable input, final ExtensionRegistry extensionRegistry, final Message.Builder builder) throws IOException {
         this.merge((CharSequence)toStringBuilder(input), extensionRegistry, builder);
      }

      private static StringBuilder toStringBuilder(final Readable input) throws IOException {
         StringBuilder text = new StringBuilder();
         CharBuffer buffer = CharBuffer.allocate(4096);

         while(true) {
            int n = input.read(buffer);
            if (n == -1) {
               return text;
            }

            Java8Compatibility.flip(buffer);
            text.append(buffer, 0, n);
         }
      }

      private void checkUnknownFields(final List unknownFields) throws ParseException {
         if (!unknownFields.isEmpty()) {
            StringBuilder msg = new StringBuilder("Input contains unknown fields and/or extensions:");

            for(UnknownField field : unknownFields) {
               msg.append('\n').append(field.message);
            }

            if (this.allowUnknownFields) {
               TextFormat.logger.warning(msg.toString());
            } else {
               int firstErrorIndex = 0;
               if (this.allowUnknownExtensions) {
                  boolean allUnknownExtensions = true;

                  for(UnknownField field : unknownFields) {
                     if (field.type == TextFormat.Parser.UnknownField.Type.FIELD) {
                        allUnknownExtensions = false;
                        break;
                     }

                     ++firstErrorIndex;
                  }

                  if (allUnknownExtensions) {
                     TextFormat.logger.warning(msg.toString());
                     return;
                  }
               }

               String[] lineColumn = ((UnknownField)unknownFields.get(firstErrorIndex)).message.split(":");
               throw new ParseException(Integer.parseInt(lineColumn[0]), Integer.parseInt(lineColumn[1]), msg.toString());
            }
         }
      }

      public void merge(final CharSequence input, final ExtensionRegistry extensionRegistry, final Message.Builder builder) throws ParseException {
         Tokenizer tokenizer = new Tokenizer(input);
         MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
         List<UnknownField> unknownFields = new ArrayList();

         while(!tokenizer.atEnd()) {
            this.mergeField(tokenizer, extensionRegistry, target, unknownFields, this.recursionLimit);
         }

         this.checkUnknownFields(unknownFields);
      }

      private void mergeField(final Tokenizer tokenizer, final ExtensionRegistry extensionRegistry, final MessageReflection.MergeTarget target, List unknownFields, int recursionLimit) throws ParseException {
         this.mergeField(tokenizer, extensionRegistry, target, this.parseInfoTreeBuilder, unknownFields, recursionLimit);
      }

      private void mergeField(final Tokenizer tokenizer, final ExtensionRegistry extensionRegistry, final MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List unknownFields, int recursionLimit) throws ParseException {
         Descriptors.FieldDescriptor field = null;
         int startLine = tokenizer.getLine();
         int startColumn = tokenizer.getColumn();
         Descriptors.Descriptor type = target.getDescriptorForType();
         ExtensionRegistry.ExtensionInfo extension = null;
         if ("google.protobuf.Any".equals(type.getFullName()) && tokenizer.tryConsume("[")) {
            if (recursionLimit < 1) {
               throw tokenizer.parseException("Message is nested too deep");
            } else {
               this.mergeAnyFieldValue(tokenizer, extensionRegistry, target, parseTreeBuilder, unknownFields, type, recursionLimit - 1);
            }
         } else {
            String name;
            if (tokenizer.tryConsume("[")) {
               StringBuilder nameBuilder = new StringBuilder(tokenizer.consumeIdentifier());

               while(tokenizer.tryConsume(".")) {
                  nameBuilder.append('.');
                  nameBuilder.append(tokenizer.consumeIdentifier());
               }

               name = nameBuilder.toString();
               extension = target.findExtensionByName(extensionRegistry, name);
               if (extension == null) {
                  String message = tokenizer.getPreviousLine() + 1 + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + ".[" + name + "]";
                  unknownFields.add(new UnknownField(message, TextFormat.Parser.UnknownField.Type.EXTENSION));
               } else {
                  if (extension.descriptor.getContainingType() != type) {
                     throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" does not extend message type \"" + type.getFullName() + "\".");
                  }

                  field = extension.descriptor;
               }

               tokenizer.consume("]");
            } else {
               name = tokenizer.consumeIdentifier();
               field = type.findFieldByName(name);
               if (field == null) {
                  String lowerName = name.toLowerCase(Locale.US);
                  field = type.findFieldByName(lowerName);
                  if (field != null && field.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                     field = null;
                  }
               }

               if (field != null && field.getType() == Descriptors.FieldDescriptor.Type.GROUP && !field.getMessageType().getName().equals(name)) {
                  field = null;
               }

               if (field == null) {
                  String message = tokenizer.getPreviousLine() + 1 + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + "." + name;
                  unknownFields.add(new UnknownField(message, TextFormat.Parser.UnknownField.Type.FIELD));
               }
            }

            if (field == null) {
               this.detectSilentMarker(tokenizer, type, name);
               this.guessFieldTypeAndSkip(tokenizer, type, recursionLimit);
            } else {
               if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                  this.detectSilentMarker(tokenizer, type, field.getFullName());
                  tokenizer.tryConsume(":");
                  if (parseTreeBuilder != null) {
                     TextFormatParseInfoTree.Builder childParseTreeBuilder = parseTreeBuilder.getBuilderForSubMessageField(field);
                     this.consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, childParseTreeBuilder, unknownFields, recursionLimit);
                  } else {
                     this.consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields, recursionLimit);
                  }
               } else {
                  this.detectSilentMarker(tokenizer, type, field.getFullName());
                  tokenizer.consume(":");
                  this.consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields, recursionLimit);
               }

               if (parseTreeBuilder != null) {
                  parseTreeBuilder.setLocation(field, TextFormatParseLocation.create(startLine, startColumn));
               }

               if (!tokenizer.tryConsume(";")) {
                  tokenizer.tryConsume(",");
               }

            }
         }
      }

      private String consumeFullTypeName(Tokenizer tokenizer) throws ParseException {
         if (!tokenizer.tryConsume("[")) {
            return tokenizer.consumeIdentifier();
         } else {
            String name;
            for(name = tokenizer.consumeIdentifier(); tokenizer.tryConsume("."); name = name + "." + tokenizer.consumeIdentifier()) {
            }

            if (tokenizer.tryConsume("/")) {
               for(name = name + "/" + tokenizer.consumeIdentifier(); tokenizer.tryConsume("."); name = name + "." + tokenizer.consumeIdentifier()) {
               }
            }

            tokenizer.consume("]");
            return name;
         }
      }

      private void consumeFieldValues(final Tokenizer tokenizer, final ExtensionRegistry extensionRegistry, final MessageReflection.MergeTarget target, final Descriptors.FieldDescriptor field, final ExtensionRegistry.ExtensionInfo extension, final TextFormatParseInfoTree.Builder parseTreeBuilder, List unknownFields, int recursionLimit) throws ParseException {
         if (field.isRepeated() && tokenizer.tryConsume("[")) {
            if (!tokenizer.tryConsume("]")) {
               while(true) {
                  this.consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields, recursionLimit);
                  if (tokenizer.tryConsume("]")) {
                     break;
                  }

                  tokenizer.consume(",");
               }
            }
         } else {
            this.consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields, recursionLimit);
         }

      }

      private void consumeFieldValue(final Tokenizer tokenizer, final ExtensionRegistry extensionRegistry, final MessageReflection.MergeTarget target, final Descriptors.FieldDescriptor field, final ExtensionRegistry.ExtensionInfo extension, final TextFormatParseInfoTree.Builder parseTreeBuilder, List unknownFields, int recursionLimit) throws ParseException {
         if (this.singularOverwritePolicy == TextFormat.Parser.SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && !field.isRepeated()) {
            if (target.hasField(field)) {
               throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + field.getFullName() + "\" cannot be overwritten.");
            }

            if (field.getContainingOneof() != null && target.hasOneof(field.getContainingOneof())) {
               Descriptors.OneofDescriptor oneof = field.getContainingOneof();
               throw tokenizer.parseExceptionPreviousToken("Field \"" + field.getFullName() + "\" is specified along with field \"" + target.getOneofFieldDescriptor(oneof).getFullName() + "\", another member of oneof \"" + oneof.getName() + "\".");
            }
         }

         Object value = null;
         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            if (recursionLimit < 1) {
               throw tokenizer.parseException("Message is nested too deep");
            }

            String endToken;
            if (tokenizer.tryConsume("<")) {
               endToken = ">";
            } else {
               tokenizer.consume("{");
               endToken = "}";
            }

            Message defaultInstance = extension == null ? null : extension.defaultInstance;
            MessageReflection.MergeTarget subField = target.newMergeTargetForField(field, defaultInstance);

            while(!tokenizer.tryConsume(endToken)) {
               if (tokenizer.atEnd()) {
                  throw tokenizer.parseException("Expected \"" + endToken + "\".");
               }

               this.mergeField(tokenizer, extensionRegistry, subField, parseTreeBuilder, unknownFields, recursionLimit - 1);
            }

            value = subField.finish();
         } else {
            switch (field.getType()) {
               case INT32:
               case SINT32:
               case SFIXED32:
                  value = tokenizer.consumeInt32();
                  break;
               case INT64:
               case SINT64:
               case SFIXED64:
                  value = tokenizer.consumeInt64();
                  break;
               case BOOL:
                  value = tokenizer.consumeBoolean();
                  break;
               case FLOAT:
                  value = tokenizer.consumeFloat();
                  break;
               case DOUBLE:
                  value = tokenizer.consumeDouble();
                  break;
               case UINT32:
               case FIXED32:
                  value = tokenizer.consumeUInt32();
                  break;
               case UINT64:
               case FIXED64:
                  value = tokenizer.consumeUInt64();
                  break;
               case STRING:
                  value = tokenizer.consumeString();
                  break;
               case BYTES:
                  value = tokenizer.consumeByteString();
                  break;
               case ENUM:
                  Descriptors.EnumDescriptor enumType = field.getEnumType();
                  if (tokenizer.lookingAtInteger()) {
                     int number = tokenizer.consumeInt32();
                     value = enumType.findValueByNumber(number);
                     if (value == null) {
                        String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.';
                        if (this.allowUnknownEnumValues) {
                           TextFormat.logger.warning(unknownValueMsg);
                           return;
                        }

                        throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.');
                     }
                  } else {
                     String id = tokenizer.consumeIdentifier();
                     value = enumType.findValueByName(id);
                     if (value == null) {
                        String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\".";
                        if (this.allowUnknownEnumValues) {
                           TextFormat.logger.warning(unknownValueMsg);
                           return;
                        }

                        throw tokenizer.parseExceptionPreviousToken(unknownValueMsg);
                     }
                  }
                  break;
               case MESSAGE:
               case GROUP:
                  throw new RuntimeException("Can't get here.");
            }
         }

         if (field.isRepeated()) {
            target.addRepeatedField(field, value);
         } else {
            target.setField(field, value);
         }

      }

      private void mergeAnyFieldValue(final Tokenizer tokenizer, final ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, final TextFormatParseInfoTree.Builder parseTreeBuilder, List unknownFields, Descriptors.Descriptor anyDescriptor, int recursionLimit) throws ParseException {
         StringBuilder typeUrlBuilder = new StringBuilder();

         while(true) {
            typeUrlBuilder.append(tokenizer.consumeIdentifier());
            if (tokenizer.tryConsume("]")) {
               this.detectSilentMarker(tokenizer, anyDescriptor, typeUrlBuilder.toString());
               tokenizer.tryConsume(":");
               String anyEndToken;
               if (tokenizer.tryConsume("<")) {
                  anyEndToken = ">";
               } else {
                  tokenizer.consume("{");
                  anyEndToken = "}";
               }

               String typeUrl = typeUrlBuilder.toString();
               Descriptors.Descriptor contentType = null;

               try {
                  contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
               } catch (InvalidProtocolBufferException var14) {
                  throw tokenizer.parseException("Invalid valid type URL. Found: " + typeUrl);
               }

               if (contentType == null) {
                  throw tokenizer.parseException("Unable to parse Any of type: " + typeUrl + ". Please make sure that the TypeRegistry contains the descriptors for the given types.");
               } else {
                  Message.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
                  MessageReflection.BuilderAdapter contentTarget = new MessageReflection.BuilderAdapter(contentBuilder);

                  while(!tokenizer.tryConsume(anyEndToken)) {
                     this.mergeField(tokenizer, extensionRegistry, contentTarget, parseTreeBuilder, unknownFields, recursionLimit);
                  }

                  target.setField(anyDescriptor.findFieldByName("type_url"), typeUrlBuilder.toString());
                  target.setField(anyDescriptor.findFieldByName("value"), contentBuilder.build().toByteString());
                  return;
               }
            }

            if (tokenizer.tryConsume("/")) {
               typeUrlBuilder.append("/");
            } else {
               if (!tokenizer.tryConsume(".")) {
                  throw tokenizer.parseExceptionPreviousToken("Expected a valid type URL.");
               }

               typeUrlBuilder.append(".");
            }
         }
      }

      private void skipField(Tokenizer tokenizer, Descriptors.Descriptor type, int recursionLimit) throws ParseException {
         String name = this.consumeFullTypeName(tokenizer);
         this.detectSilentMarker(tokenizer, type, name);
         this.guessFieldTypeAndSkip(tokenizer, type, recursionLimit);
         if (!tokenizer.tryConsume(";")) {
            tokenizer.tryConsume(",");
         }

      }

      private void skipFieldMessage(Tokenizer tokenizer, Descriptors.Descriptor type, int recursionLimit) throws ParseException {
         String delimiter;
         if (tokenizer.tryConsume("<")) {
            delimiter = ">";
         } else {
            tokenizer.consume("{");
            delimiter = "}";
         }

         while(!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
            this.skipField(tokenizer, type, recursionLimit);
         }

         tokenizer.consume(delimiter);
      }

      private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
         if (!tokenizer.tryConsumeByteString() && !tokenizer.tryConsumeIdentifier() && !tokenizer.tryConsumeInt64() && !tokenizer.tryConsumeUInt64() && !tokenizer.tryConsumeDouble() && !tokenizer.tryConsumeFloat()) {
            throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
         }
      }

      private void guessFieldTypeAndSkip(Tokenizer tokenizer, Descriptors.Descriptor type, int recursionLimit) throws ParseException {
         boolean semicolonConsumed = tokenizer.tryConsume(":");
         if (tokenizer.lookingAt("[")) {
            this.skipFieldShortFormedRepeated(tokenizer, semicolonConsumed, type, recursionLimit);
         } else if (semicolonConsumed && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
            this.skipFieldValue(tokenizer);
         } else {
            if (recursionLimit < 1) {
               throw tokenizer.parseException("Message is nested too deep");
            }

            this.skipFieldMessage(tokenizer, type, recursionLimit - 1);
         }

      }

      private void skipFieldShortFormedRepeated(Tokenizer tokenizer, boolean scalarAllowed, Descriptors.Descriptor type, int recursionLimit) throws ParseException {
         if (tokenizer.tryConsume("[") && !tokenizer.tryConsume("]")) {
            while(true) {
               if (!tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
                  if (!scalarAllowed) {
                     throw tokenizer.parseException("Invalid repeated scalar field: missing \":\" before \"[\".");
                  }

                  this.skipFieldValue(tokenizer);
               } else {
                  if (recursionLimit < 1) {
                     throw tokenizer.parseException("Message is nested too deep");
                  }

                  this.skipFieldMessage(tokenizer, type, recursionLimit - 1);
               }

               if (tokenizer.tryConsume("]")) {
                  return;
               }

               tokenizer.consume(",");
            }
         }
      }

      public static enum SingularOverwritePolicy {
         ALLOW_SINGULAR_OVERWRITES,
         FORBID_SINGULAR_OVERWRITES;
      }

      public static class Builder {
         private boolean allowUnknownFields = false;
         private boolean allowUnknownEnumValues = false;
         private boolean allowUnknownExtensions = false;
         private SingularOverwritePolicy singularOverwritePolicy;
         private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
         private TypeRegistry typeRegistry;
         private int recursionLimit;

         public Builder() {
            this.singularOverwritePolicy = TextFormat.Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            this.parseInfoTreeBuilder = null;
            this.typeRegistry = TypeRegistry.getEmptyTypeRegistry();
            this.recursionLimit = 100;
         }

         public Builder setTypeRegistry(TypeRegistry typeRegistry) {
            this.typeRegistry = typeRegistry;
            return this;
         }

         public Builder setAllowUnknownFields(boolean allowUnknownFields) {
            this.allowUnknownFields = allowUnknownFields;
            return this;
         }

         public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) {
            this.allowUnknownExtensions = allowUnknownExtensions;
            return this;
         }

         public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
            this.singularOverwritePolicy = p;
            return this;
         }

         public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
            this.parseInfoTreeBuilder = parseInfoTreeBuilder;
            return this;
         }

         public Builder setRecursionLimit(int recursionLimit) {
            this.recursionLimit = recursionLimit;
            return this;
         }

         public Parser build() {
            return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder, this.recursionLimit);
         }
      }

      static final class UnknownField {
         final String message;
         final Type type;

         UnknownField(String message, Type type) {
            this.message = message;
            this.type = type;
         }

         static enum Type {
            FIELD,
            EXTENSION;
         }
      }
   }

   public static class InvalidEscapeSequenceException extends IOException {
      private static final long serialVersionUID = -8164033650142593304L;

      InvalidEscapeSequenceException(final String description) {
         super(description);
      }
   }
}
