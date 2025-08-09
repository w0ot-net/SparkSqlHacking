package org.snakeyaml.engine.v2.scanner;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

final class SimpleKey {
   private final int tokenNumber;
   private final boolean required;
   private final int index;
   private final int line;
   private final int column;
   private final Optional mark;

   public SimpleKey(int tokenNumber, boolean required, int index, int line, int column, Optional mark) {
      this.tokenNumber = tokenNumber;
      this.required = required;
      this.index = index;
      this.line = line;
      this.column = column;
      this.mark = mark;
   }

   public int getTokenNumber() {
      return this.tokenNumber;
   }

   public int getColumn() {
      return this.column;
   }

   public Optional getMark() {
      return this.mark;
   }

   public int getIndex() {
      return this.index;
   }

   public int getLine() {
      return this.line;
   }

   public boolean isRequired() {
      return this.required;
   }

   public String toString() {
      return "SimpleKey - tokenNumber=" + this.tokenNumber + " required=" + this.required + " index=" + this.index + " line=" + this.line + " column=" + this.column;
   }
}
