package org.apache.avro.io.parsing;

import java.util.HashMap;
import java.util.Map;
import org.apache.avro.Schema;

public class JsonGrammarGenerator extends ValidatingGrammarGenerator {
   public Symbol generate(Schema schema) {
      return Symbol.root(this.generate(schema, new HashMap()));
   }

   public Symbol generate(Schema sc, Map seen) {
      switch (sc.getType()) {
         case NULL:
         case BOOLEAN:
         case INT:
         case LONG:
         case FLOAT:
         case DOUBLE:
         case STRING:
         case BYTES:
         case FIXED:
         case UNION:
            return super.generate(sc, seen);
         case ENUM:
            return Symbol.seq(Symbol.enumLabelsAction(sc.getEnumSymbols()), Symbol.ENUM);
         case ARRAY:
            return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, Symbol.ITEM_END, this.generate(sc.getElementType(), seen)), Symbol.ARRAY_START);
         case MAP:
            return Symbol.seq(Symbol.repeat(Symbol.MAP_END, Symbol.ITEM_END, this.generate(sc.getValueType(), seen), Symbol.MAP_KEY_MARKER, Symbol.STRING), Symbol.MAP_START);
         case RECORD:
            ValidatingGrammarGenerator.LitS wsc = new ValidatingGrammarGenerator.LitS(sc);
            Symbol rresult = (Symbol)seen.get(wsc);
            if (rresult == null) {
               Symbol[] production = new Symbol[sc.getFields().size() * 3 + 2];
               rresult = Symbol.seq(production);
               seen.put(wsc, rresult);
               int i = production.length;
               int n = 0;
               --i;
               production[i] = Symbol.RECORD_START;

               for(Schema.Field f : sc.getFields()) {
                  --i;
                  production[i] = Symbol.fieldAdjustAction(n, f.name(), f.aliases());
                  --i;
                  production[i] = this.generate(f.schema(), seen);
                  --i;
                  production[i] = Symbol.FIELD_END;
                  ++n;
               }

               --i;
               production[i] = Symbol.RECORD_END;
            }

            return rresult;
         default:
            throw new RuntimeException("Unexpected schema type");
      }
   }
}
