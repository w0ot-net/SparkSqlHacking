package org.apache.avro.io.parsing;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Resolver;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.internal.Accessor;

public class ResolvingGrammarGenerator extends ValidatingGrammarGenerator {
   private static EncoderFactory factory;

   public final Symbol generate(Schema writer, Schema reader) throws IOException {
      Resolver.Action r = Resolver.resolve(writer, reader);
      return Symbol.root(this.generate((Resolver.Action)r, (Map)(new HashMap())));
   }

   private Symbol generate(Resolver.Action action, Map seen) throws IOException {
      if (action instanceof Resolver.DoNothing) {
         return this.simpleGen(action.writer, seen);
      } else if (action instanceof Resolver.ErrorAction) {
         return Symbol.error(action.toString());
      } else if (action instanceof Resolver.Skip) {
         return Symbol.skipAction(this.simpleGen(action.writer, seen));
      } else if (action instanceof Resolver.Promote) {
         return Symbol.resolve(this.simpleGen(action.writer, seen), this.simpleGen(action.reader, seen));
      } else if (action instanceof Resolver.ReaderUnion) {
         Resolver.ReaderUnion ru = (Resolver.ReaderUnion)action;
         Symbol s = this.generate(ru.actualAction, seen);
         return Symbol.seq(Symbol.unionAdjustAction(ru.firstMatch, s), Symbol.UNION);
      } else if (action.writer.getType() == Schema.Type.ARRAY) {
         Symbol es = this.generate(((Resolver.Container)action).elementAction, seen);
         return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, es), Symbol.ARRAY_START);
      } else if (action.writer.getType() == Schema.Type.MAP) {
         Symbol es = this.generate(((Resolver.Container)action).elementAction, seen);
         return Symbol.seq(Symbol.repeat(Symbol.MAP_END, es, Symbol.STRING), Symbol.MAP_START);
      } else if (action.writer.getType() == Schema.Type.UNION) {
         if (((Resolver.WriterUnion)action).unionEquiv) {
            return this.simpleGen(action.reader, seen);
         } else {
            Resolver.Action[] branches = ((Resolver.WriterUnion)action).actions;
            Symbol[] symbols = new Symbol[branches.length];
            String[] labels = new String[branches.length];
            int i = 0;

            for(Resolver.Action branch : branches) {
               symbols[i] = this.generate(branch, seen);
               labels[i] = ((Schema)action.writer.getTypes().get(i)).getFullName();
               ++i;
            }

            return Symbol.seq(Symbol.alt(symbols, labels), Symbol.WRITER_UNION_ACTION);
         }
      } else if (action instanceof Resolver.EnumAdjust) {
         Resolver.EnumAdjust e = (Resolver.EnumAdjust)action;
         Object[] adjs = new Object[e.adjustments.length];

         for(int i = 0; i < adjs.length; ++i) {
            adjs[i] = 0 <= e.adjustments[i] ? e.adjustments[i] : "No match for " + (String)e.writer.getEnumSymbols().get(i);
         }

         return Symbol.seq(Symbol.enumAdjustAction(e.reader.getEnumSymbols().size(), adjs), Symbol.ENUM);
      } else if (!(action instanceof Resolver.RecordAdjust)) {
         throw new IllegalArgumentException("Unrecognized Resolver.Action: " + String.valueOf(action));
      } else {
         Symbol result = (Symbol)seen.get(action);
         if (result == null) {
            Resolver.RecordAdjust ra = (Resolver.RecordAdjust)action;
            int defaultCount = ra.readerOrder.length - ra.firstDefault;
            int count = 1 + ra.fieldActions.length + 3 * defaultCount;
            Symbol[] production = new Symbol[count];
            result = Symbol.seq(production);
            seen.put(action, result);
            --count;
            production[count] = Symbol.fieldOrderAction(ra.readerOrder);
            Resolver.Action[] actions = ra.fieldActions;

            for(Resolver.Action wfa : actions) {
               --count;
               production[count] = this.generate(wfa, seen);
            }

            for(int i = ra.firstDefault; i < ra.readerOrder.length; ++i) {
               Schema.Field rf = ra.readerOrder[i];
               byte[] bb = getBinary(rf.schema(), Accessor.defaultValue(rf));
               --count;
               production[count] = Symbol.defaultStartAction(bb);
               --count;
               production[count] = this.simpleGen(rf.schema(), seen);
               --count;
               production[count] = Symbol.DEFAULT_END_ACTION;
            }
         }

         return result;
      }
   }

   private Symbol simpleGen(Schema s, Map seen) {
      switch (s.getType()) {
         case NULL:
            return Symbol.NULL;
         case BOOLEAN:
            return Symbol.BOOLEAN;
         case INT:
            return Symbol.INT;
         case LONG:
            return Symbol.LONG;
         case FLOAT:
            return Symbol.FLOAT;
         case DOUBLE:
            return Symbol.DOUBLE;
         case BYTES:
            return Symbol.BYTES;
         case STRING:
            return Symbol.STRING;
         case FIXED:
            return Symbol.seq(Symbol.intCheckAction(s.getFixedSize()), Symbol.FIXED);
         case ENUM:
            return Symbol.seq(Symbol.enumAdjustAction(s.getEnumSymbols().size(), (Object[])null), Symbol.ENUM);
         case ARRAY:
            return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, this.simpleGen(s.getElementType(), seen)), Symbol.ARRAY_START);
         case MAP:
            return Symbol.seq(Symbol.repeat(Symbol.MAP_END, this.simpleGen(s.getValueType(), seen), Symbol.STRING), Symbol.MAP_START);
         case UNION:
            List<Schema> subs = s.getTypes();
            Symbol[] symbols = new Symbol[subs.size()];
            String[] labels = new String[subs.size()];
            int i = 0;

            for(Schema b : s.getTypes()) {
               symbols[i] = this.simpleGen(b, seen);
               labels[i++] = b.getFullName();
            }

            return Symbol.seq(Symbol.alt(symbols, labels), Symbol.UNION);
         case RECORD:
            Symbol result = (Symbol)seen.get(s);
            if (result == null) {
               Symbol[] production = new Symbol[s.getFields().size() + 1];
               result = Symbol.seq(production);
               seen.put(s, result);
               int i = production.length;
               --i;
               production[i] = Symbol.fieldOrderAction((Schema.Field[])s.getFields().toArray(new Schema.Field[0]));

               for(Schema.Field f : s.getFields()) {
                  --i;
                  production[i] = this.simpleGen(f.schema(), seen);
               }
            }

            return result;
         default:
            throw new IllegalArgumentException("Unexpected schema: " + String.valueOf(s));
      }
   }

   private static byte[] getBinary(Schema s, JsonNode n) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      Encoder e = factory.binaryEncoder(out, (BinaryEncoder)null);
      encode(e, s, n);
      e.flush();
      return out.toByteArray();
   }

   public static void encode(Encoder e, Schema s, JsonNode n) throws IOException {
      switch (s.getType()) {
         case NULL:
            if (!n.isNull()) {
               throw new AvroTypeException("Non-null default value for null type: " + String.valueOf(n));
            }

            e.writeNull();
            break;
         case BOOLEAN:
            if (!n.isBoolean()) {
               throw new AvroTypeException("Non-boolean default for boolean: " + String.valueOf(n));
            }

            e.writeBoolean(n.booleanValue());
            break;
         case INT:
            if (!n.isNumber()) {
               throw new AvroTypeException("Non-numeric default value for int: " + String.valueOf(n));
            }

            e.writeInt(n.intValue());
            break;
         case LONG:
            if (!n.isNumber()) {
               throw new AvroTypeException("Non-numeric default value for long: " + String.valueOf(n));
            }

            e.writeLong(n.longValue());
            break;
         case FLOAT:
            if (!n.isNumber()) {
               throw new AvroTypeException("Non-numeric default value for float: " + String.valueOf(n));
            }

            e.writeFloat((float)n.doubleValue());
            break;
         case DOUBLE:
            if (!n.isNumber()) {
               throw new AvroTypeException("Non-numeric default value for double: " + String.valueOf(n));
            }

            e.writeDouble(n.doubleValue());
            break;
         case BYTES:
            if (!n.isTextual()) {
               throw new AvroTypeException("Non-string default value for bytes: " + String.valueOf(n));
            }

            e.writeBytes(n.textValue().getBytes(StandardCharsets.ISO_8859_1));
            break;
         case STRING:
            if (!n.isTextual()) {
               throw new AvroTypeException("Non-string default value for string: " + String.valueOf(n));
            }

            e.writeString(n.textValue());
            break;
         case FIXED:
            if (!n.isTextual()) {
               throw new AvroTypeException("Non-string default value for fixed: " + String.valueOf(n));
            }

            byte[] bb = n.textValue().getBytes(StandardCharsets.ISO_8859_1);
            if (bb.length != s.getFixedSize()) {
               bb = Arrays.copyOf(bb, s.getFixedSize());
            }

            e.writeFixed(bb);
            break;
         case ENUM:
            e.writeEnum(s.getEnumOrdinal(n.textValue()));
            break;
         case ARRAY:
            e.writeArrayStart();
            e.setItemCount((long)n.size());
            Schema i = s.getElementType();

            for(JsonNode node : n) {
               e.startItem();
               encode(e, i, node);
            }

            e.writeArrayEnd();
            break;
         case MAP:
            e.writeMapStart();
            e.setItemCount((long)n.size());
            Schema v = s.getValueType();
            Iterator<String> it = n.fieldNames();

            while(it.hasNext()) {
               e.startItem();
               String key = (String)it.next();
               e.writeString(key);
               encode(e, v, n.get(key));
            }

            e.writeMapEnd();
            break;
         case UNION:
            int correctIndex = 0;

            List<Schema> innerTypes;
            for(innerTypes = s.getTypes(); correctIndex < innerTypes.size() && !isCompatible(((Schema)innerTypes.get(correctIndex)).getType(), n); ++correctIndex) {
            }

            if (correctIndex >= innerTypes.size()) {
               throw new AvroTypeException("Not compatible default value for union: " + String.valueOf(n));
            }

            e.writeIndex(correctIndex);
            encode(e, (Schema)innerTypes.get(correctIndex), n);
            break;
         case RECORD:
            for(Schema.Field f : s.getFields()) {
               String name = f.name();
               JsonNode v = n.get(name);
               if (v == null) {
                  v = Accessor.defaultValue(f);
               }

               if (v == null) {
                  throw new AvroTypeException("No default value for: " + name);
               }

               encode(e, f.schema(), v);
            }
      }

   }

   private static boolean isCompatible(Schema.Type stype, JsonNode value) {
      switch (stype) {
         case NULL:
            return value.isNull();
         case BOOLEAN:
            return value.isBoolean();
         case INT:
         case LONG:
         case FLOAT:
         case DOUBLE:
            return value.isNumber();
         case BYTES:
         case STRING:
         case FIXED:
            return value.isTextual();
         case ENUM:
         case ARRAY:
         case MAP:
         case UNION:
         case RECORD:
            return true;
         default:
            return true;
      }
   }

   static {
      Accessor.setAccessor(new Accessor.ResolvingGrammarGeneratorAccessor() {
         protected void encode(Encoder e, Schema s, JsonNode n) throws IOException {
            ResolvingGrammarGenerator.encode(e, s, n);
         }
      });
      factory = (new EncoderFactory()).configureBufferSize(32);
   }
}
