package org.apache.avro.io.parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.avro.Schema;

public abstract class Symbol {
   public final Kind kind;
   public final Symbol[] production;
   public static final Symbol NULL = new Terminal("null");
   public static final Symbol BOOLEAN = new Terminal("boolean");
   public static final Symbol INT = new Terminal("int");
   public static final Symbol LONG = new Terminal("long");
   public static final Symbol FLOAT = new Terminal("float");
   public static final Symbol DOUBLE = new Terminal("double");
   public static final Symbol STRING = new Terminal("string");
   public static final Symbol BYTES = new Terminal("bytes");
   public static final Symbol FIXED = new Terminal("fixed");
   public static final Symbol ENUM = new Terminal("enum");
   public static final Symbol UNION = new Terminal("union");
   public static final Symbol ARRAY_START = new Terminal("array-start");
   public static final Symbol ARRAY_END = new Terminal("array-end");
   public static final Symbol MAP_START = new Terminal("map-start");
   public static final Symbol MAP_END = new Terminal("map-end");
   public static final Symbol ITEM_END = new Terminal("item-end");
   public static final Symbol WRITER_UNION_ACTION = writerUnionAction();
   public static final Symbol FIELD_ACTION = new Terminal("field-action");
   public static final Symbol RECORD_START = new ImplicitAction(false);
   public static final Symbol RECORD_END = new ImplicitAction(true);
   public static final Symbol UNION_END = new ImplicitAction(true);
   public static final Symbol FIELD_END = new ImplicitAction(true);
   public static final Symbol DEFAULT_END_ACTION = new ImplicitAction(true);
   public static final Symbol MAP_KEY_MARKER = new Terminal("map-key-marker");

   protected Symbol(Kind kind) {
      this(kind, (Symbol[])null);
   }

   protected Symbol(Kind kind, Symbol[] production) {
      this.production = production;
      this.kind = kind;
   }

   static Symbol root(Symbol... symbols) {
      return new Root(symbols);
   }

   static Symbol seq(Symbol... production) {
      return new Sequence(production);
   }

   static Symbol repeat(Symbol endSymbol, Symbol... symsToRepeat) {
      return new Repeater(endSymbol, symsToRepeat);
   }

   static Symbol alt(Symbol[] symbols, String[] labels) {
      return new Alternative(symbols, labels);
   }

   static Symbol error(String e) {
      return new ErrorAction(e);
   }

   static Symbol resolve(Symbol w, Symbol r) {
      return new ResolvingAction(w, r);
   }

   public Symbol flatten(Map map, Map map2) {
      return this;
   }

   public int flattenedSize() {
      return 1;
   }

   static void flatten(Symbol[] in, int start, Symbol[] out, int skip, Map map, Map map2) {
      int i = start;

      for(int j = skip; i < in.length; ++i) {
         Symbol s = in[i].flatten(map, map2);
         if (!(s instanceof Sequence)) {
            out[j++] = s;
         } else {
            Symbol[] p = s.production;
            List<Fixup> l = (List)map2.get(s);
            if (l == null) {
               System.arraycopy(p, 0, out, j, p.length);

               for(List fixups : map2.values()) {
                  copyFixups(fixups, out, j, p);
               }
            } else {
               l.add(new Fixup(out, j));
            }

            j += p.length;
         }
      }

   }

   private static void copyFixups(List fixups, Symbol[] out, int outPos, Symbol[] toCopy) {
      int i = 0;

      for(int n = fixups.size(); i < n; ++i) {
         Fixup fixup = (Fixup)fixups.get(i);
         if (fixup.symbols == toCopy) {
            fixups.add(new Fixup(out, fixup.pos + outPos));
         }
      }

   }

   protected static int flattenedSize(Symbol[] symbols, int start) {
      int result = 0;

      for(int i = start; i < symbols.length; ++i) {
         if (symbols[i] instanceof Sequence) {
            Sequence s = (Sequence)symbols[i];
            result += s.flattenedSize();
         } else {
            ++result;
         }
      }

      return result;
   }

   public static boolean hasErrors(Symbol symbol) {
      return hasErrors(symbol, new HashSet());
   }

   private static boolean hasErrors(Symbol symbol, Set visited) {
      if (visited.contains(symbol)) {
         return false;
      } else {
         visited.add(symbol);
         switch (symbol.kind.ordinal()) {
            case 0:
               return false;
            case 1:
            case 2:
               return hasErrors(symbol, symbol.production, visited);
            case 3:
               Repeater r = (Repeater)symbol;
               return hasErrors(r.end, visited) || hasErrors(symbol, r.production, visited);
            case 4:
               return hasErrors(symbol, ((Alternative)symbol).symbols, visited);
            case 5:
               if (symbol instanceof ErrorAction) {
                  return true;
               } else {
                  if (symbol instanceof UnionAdjustAction) {
                     return hasErrors(((UnionAdjustAction)symbol).symToParse, visited);
                  }

                  return false;
               }
            case 6:
               return false;
            default:
               throw new RuntimeException("unknown symbol kind: " + String.valueOf(symbol.kind));
         }
      }
   }

   private static boolean hasErrors(Symbol root, Symbol[] symbols, Set visited) {
      if (null != symbols) {
         for(Symbol s : symbols) {
            if (s != root && hasErrors(s, visited)) {
               return true;
            }
         }
      }

      return false;
   }

   public static IntCheckAction intCheckAction(int size) {
      return new IntCheckAction(size);
   }

   public static EnumAdjustAction enumAdjustAction(int rsymCount, Object[] adj) {
      return new EnumAdjustAction(rsymCount, adj);
   }

   public static WriterUnionAction writerUnionAction() {
      return new WriterUnionAction();
   }

   public static SkipAction skipAction(Symbol symToSkip) {
      return new SkipAction(symToSkip);
   }

   public static FieldAdjustAction fieldAdjustAction(int rindex, String fname, Set aliases) {
      return new FieldAdjustAction(rindex, fname, aliases);
   }

   public static FieldOrderAction fieldOrderAction(Schema.Field[] fields) {
      return new FieldOrderAction(fields);
   }

   public static DefaultStartAction defaultStartAction(byte[] contents) {
      return new DefaultStartAction(contents);
   }

   public static UnionAdjustAction unionAdjustAction(int rindex, Symbol sym) {
      return new UnionAdjustAction(rindex, sym);
   }

   public static EnumLabelsAction enumLabelsAction(List symbols) {
      return new EnumLabelsAction(symbols);
   }

   public static enum Kind {
      TERMINAL,
      ROOT,
      SEQUENCE,
      REPEATER,
      ALTERNATIVE,
      IMPLICIT_ACTION,
      EXPLICIT_ACTION;

      // $FF: synthetic method
      private static Kind[] $values() {
         return new Kind[]{TERMINAL, ROOT, SEQUENCE, REPEATER, ALTERNATIVE, IMPLICIT_ACTION, EXPLICIT_ACTION};
      }
   }

   private static class Fixup {
      public final Symbol[] symbols;
      public final int pos;

      public Fixup(Symbol[] symbols, int pos) {
         this.symbols = symbols;
         this.pos = pos;
      }
   }

   private static class Terminal extends Symbol {
      private final String printName;

      public Terminal(String printName) {
         super(Symbol.Kind.TERMINAL);
         this.printName = printName;
      }

      public String toString() {
         return this.printName;
      }
   }

   public static class ImplicitAction extends Symbol {
      public final boolean isTrailing;

      private ImplicitAction() {
         this(false);
      }

      private ImplicitAction(boolean isTrailing) {
         super(Symbol.Kind.IMPLICIT_ACTION);
         this.isTrailing = isTrailing;
      }
   }

   protected static class Root extends Symbol {
      private Root(Symbol... symbols) {
         super(Symbol.Kind.ROOT, makeProduction(symbols));
         this.production[0] = this;
      }

      private static Symbol[] makeProduction(Symbol[] symbols) {
         Symbol[] result = new Symbol[flattenedSize(symbols, 0) + 1];
         flatten(symbols, 0, result, 1, new HashMap(), new HashMap());
         return result;
      }
   }

   protected static class Sequence extends Symbol implements Iterable {
      private Sequence(Symbol[] productions) {
         super(Symbol.Kind.SEQUENCE, productions);
      }

      public Symbol get(int index) {
         return this.production[index];
      }

      public int size() {
         return this.production.length;
      }

      public Iterator iterator() {
         return new Iterator() {
            private int pos;

            {
               this.pos = Sequence.this.production.length;
            }

            public boolean hasNext() {
               return 0 < this.pos;
            }

            public Symbol next() {
               if (0 < this.pos) {
                  return Sequence.this.production[--this.pos];
               } else {
                  throw new NoSuchElementException();
               }
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      }

      public Sequence flatten(Map map, Map map2) {
         Sequence result = (Sequence)map.get(this);
         if (result == null) {
            result = new Sequence(new Symbol[this.flattenedSize()]);
            map.put(this, result);
            List<Fixup> l = new ArrayList();
            map2.put(result, l);
            flatten(this.production, 0, result.production, 0, map, map2);

            for(Fixup f : l) {
               System.arraycopy(result.production, 0, f.symbols, f.pos, result.production.length);
            }

            map2.remove(result);
         }

         return result;
      }

      public final int flattenedSize() {
         return flattenedSize(this.production, 0);
      }
   }

   public static class Repeater extends Symbol {
      public final Symbol end;

      private Repeater(Symbol end, Symbol... sequenceToRepeat) {
         super(Symbol.Kind.REPEATER, makeProduction(sequenceToRepeat));
         this.end = end;
         this.production[0] = this;
      }

      private static Symbol[] makeProduction(Symbol[] p) {
         Symbol[] result = new Symbol[p.length + 1];
         System.arraycopy(p, 0, result, 1, p.length);
         return result;
      }

      public Repeater flatten(Map map, Map map2) {
         Repeater result = new Repeater(this.end, new Symbol[flattenedSize(this.production, 1)]);
         flatten(this.production, 1, result.production, 1, map, map2);
         return result;
      }
   }

   public static class Alternative extends Symbol {
      public final Symbol[] symbols;
      public final String[] labels;

      private Alternative(Symbol[] symbols, String[] labels) {
         super(Symbol.Kind.ALTERNATIVE);
         this.symbols = symbols;
         this.labels = labels;
      }

      public Symbol getSymbol(int index) {
         return this.symbols[index];
      }

      public String getLabel(int index) {
         return this.labels[index];
      }

      public int size() {
         return this.symbols.length;
      }

      public int findLabel(String label) {
         if (label != null) {
            for(int i = 0; i < this.labels.length; ++i) {
               if (label.equals(this.labels[i])) {
                  return i;
               }
            }
         }

         return -1;
      }

      public Alternative flatten(Map map, Map map2) {
         Symbol[] ss = new Symbol[this.symbols.length];

         for(int i = 0; i < ss.length; ++i) {
            ss[i] = this.symbols[i].flatten(map, map2);
         }

         return new Alternative(ss, this.labels);
      }
   }

   public static class ErrorAction extends ImplicitAction {
      public final String msg;

      private ErrorAction(String msg) {
         this.msg = msg;
      }
   }

   public static class IntCheckAction extends Symbol {
      public final int size;

      /** @deprecated */
      @Deprecated
      public IntCheckAction(int size) {
         super(Symbol.Kind.EXPLICIT_ACTION);
         this.size = size;
      }
   }

   public static class EnumAdjustAction extends IntCheckAction {
      public final boolean noAdjustments;
      public final Object[] adjustments;

      /** @deprecated */
      @Deprecated
      public EnumAdjustAction(int rsymCount, Object[] adjustments) {
         super(rsymCount);
         this.adjustments = adjustments;
         boolean noAdj = true;
         if (adjustments != null) {
            int count = Math.min(rsymCount, adjustments.length);
            noAdj = adjustments.length <= rsymCount;

            for(int i = 0; noAdj && i < count; ++i) {
               noAdj &= adjustments[i] instanceof Integer && i == (Integer)adjustments[i];
            }
         }

         this.noAdjustments = noAdj;
      }
   }

   public static class WriterUnionAction extends ImplicitAction {
      private WriterUnionAction() {
      }
   }

   public static class ResolvingAction extends ImplicitAction {
      public final Symbol writer;
      public final Symbol reader;

      private ResolvingAction(Symbol writer, Symbol reader) {
         this.writer = writer;
         this.reader = reader;
      }

      public ResolvingAction flatten(Map map, Map map2) {
         return new ResolvingAction(this.writer.flatten(map, map2), this.reader.flatten(map, map2));
      }
   }

   public static class SkipAction extends ImplicitAction {
      public final Symbol symToSkip;

      /** @deprecated */
      @Deprecated
      public SkipAction(Symbol symToSkip) {
         super(true);
         this.symToSkip = symToSkip;
      }

      public SkipAction flatten(Map map, Map map2) {
         return new SkipAction(this.symToSkip.flatten(map, map2));
      }
   }

   public static class FieldAdjustAction extends ImplicitAction {
      public final int rindex;
      public final String fname;
      public final Set aliases;

      /** @deprecated */
      @Deprecated
      public FieldAdjustAction(int rindex, String fname, Set aliases) {
         this.rindex = rindex;
         this.fname = fname;
         this.aliases = aliases;
      }
   }

   public static final class FieldOrderAction extends ImplicitAction {
      public final boolean noReorder;
      public final Schema.Field[] fields;

      /** @deprecated */
      @Deprecated
      public FieldOrderAction(Schema.Field[] fields) {
         this.fields = fields;
         boolean noReorder = true;

         for(int i = 0; noReorder && i < fields.length; ++i) {
            noReorder &= i == fields[i].pos();
         }

         this.noReorder = noReorder;
      }
   }

   public static class DefaultStartAction extends ImplicitAction {
      public final byte[] contents;

      /** @deprecated */
      @Deprecated
      public DefaultStartAction(byte[] contents) {
         this.contents = contents;
      }
   }

   public static class UnionAdjustAction extends ImplicitAction {
      public final int rindex;
      public final Symbol symToParse;

      /** @deprecated */
      @Deprecated
      public UnionAdjustAction(int rindex, Symbol symToParse) {
         this.rindex = rindex;
         this.symToParse = symToParse;
      }

      public UnionAdjustAction flatten(Map map, Map map2) {
         return new UnionAdjustAction(this.rindex, this.symToParse.flatten(map, map2));
      }
   }

   public static class EnumLabelsAction extends IntCheckAction {
      public final List symbols;

      /** @deprecated */
      @Deprecated
      public EnumLabelsAction(List symbols) {
         super(symbols.size());
         this.symbols = symbols;
      }

      public String getLabel(int n) {
         return (String)this.symbols.get(n);
      }

      public int findLabel(String l) {
         if (l != null) {
            for(int i = 0; i < this.symbols.size(); ++i) {
               if (l.equals(this.symbols.get(i))) {
                  return i;
               }
            }
         }

         return -1;
      }
   }
}
