package org.apache.avro.io.parsing;

import java.io.IOException;
import java.util.Arrays;
import org.apache.avro.AvroTypeException;

public class Parser {
   protected final ActionHandler symbolHandler;
   protected Symbol[] stack;
   protected int pos;

   public Parser(Symbol root, ActionHandler symbolHandler) {
      this.symbolHandler = symbolHandler;
      this.stack = new Symbol[5];
      this.stack[0] = root;
      this.pos = 1;
   }

   private void expandStack() {
      this.stack = (Symbol[])Arrays.copyOf(this.stack, this.stack.length + Math.max(this.stack.length, 1024));
   }

   public final Symbol advance(Symbol input) throws IOException {
      while(true) {
         Symbol top = this.stack[--this.pos];
         if (top == input) {
            return top;
         }

         Symbol.Kind k = top.kind;
         if (k == Symbol.Kind.IMPLICIT_ACTION) {
            Symbol result = this.symbolHandler.doAction(input, top);
            if (result != null) {
               return result;
            }
         } else {
            if (k == Symbol.Kind.TERMINAL) {
               String var10002 = String.valueOf(input);
               throw new AvroTypeException("Attempt to process a " + var10002 + " when a " + String.valueOf(top) + " was expected.");
            }

            if (k == Symbol.Kind.REPEATER && input == ((Symbol.Repeater)top).end) {
               return input;
            }

            this.pushProduction(top);
         }
      }
   }

   public final void processImplicitActions() throws IOException {
      while(true) {
         if (this.pos > 1) {
            Symbol top = this.stack[this.pos - 1];
            if (top.kind == Symbol.Kind.IMPLICIT_ACTION) {
               --this.pos;
               this.symbolHandler.doAction((Symbol)null, top);
               continue;
            }

            if (top.kind != Symbol.Kind.TERMINAL) {
               --this.pos;
               this.pushProduction(top);
               continue;
            }
         }

         return;
      }
   }

   public final void processTrailingImplicitActions() throws IOException {
      while(true) {
         if (this.pos >= 1) {
            Symbol top = this.stack[this.pos - 1];
            if (top.kind == Symbol.Kind.IMPLICIT_ACTION && ((Symbol.ImplicitAction)top).isTrailing) {
               --this.pos;
               this.symbolHandler.doAction((Symbol)null, top);
               continue;
            }
         }

         return;
      }
   }

   public final void pushProduction(Symbol sym) {
      Symbol[] p = sym.production;

      while(this.pos + p.length > this.stack.length) {
         this.expandStack();
      }

      System.arraycopy(p, 0, this.stack, this.pos, p.length);
      this.pos += p.length;
   }

   public Symbol popSymbol() {
      return this.stack[--this.pos];
   }

   public Symbol topSymbol() {
      return this.stack[this.pos - 1];
   }

   public void pushSymbol(Symbol sym) {
      if (this.pos == this.stack.length) {
         this.expandStack();
      }

      this.stack[this.pos++] = sym;
   }

   public int depth() {
      return this.pos;
   }

   public void reset() {
      this.pos = 1;
   }

   public interface ActionHandler {
      Symbol doAction(Symbol input, Symbol top) throws IOException;
   }
}
