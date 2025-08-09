package org.apache.avro.io.parsing;

import java.io.IOException;

public class SkipParser extends Parser {
   private final SkipHandler skipHandler;

   public SkipParser(Symbol root, Parser.ActionHandler symbolHandler, SkipHandler skipHandler) throws IOException {
      super(root, symbolHandler);
      this.skipHandler = skipHandler;
   }

   public final void skipTo(int target) throws IOException {
      while(target < this.pos) {
         Symbol top = this.stack[this.pos - 1];
         if (top.kind != Symbol.Kind.TERMINAL) {
            if (top.kind != Symbol.Kind.IMPLICIT_ACTION && top.kind != Symbol.Kind.EXPLICIT_ACTION) {
               --this.pos;
               this.pushProduction(top);
            } else {
               this.skipHandler.skipAction();
            }
         } else {
            this.skipHandler.skipTopSymbol();
         }
      }

   }

   public final void skipRepeater() throws IOException {
      int target = this.pos;
      Symbol repeater = this.stack[--this.pos];

      assert repeater.kind == Symbol.Kind.REPEATER;

      this.pushProduction(repeater);
      this.skipTo(target);
   }

   public final void skipSymbol(Symbol symToSkip) throws IOException {
      int target = this.pos;
      this.pushSymbol(symToSkip);
      this.skipTo(target);
   }

   public interface SkipHandler {
      void skipAction() throws IOException;

      void skipTopSymbol() throws IOException;
   }
}
