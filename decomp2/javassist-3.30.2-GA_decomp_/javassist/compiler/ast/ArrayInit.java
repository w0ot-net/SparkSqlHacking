package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class ArrayInit extends ASTList {
   private static final long serialVersionUID = 1L;

   public ArrayInit(ASTree firstElement) {
      super(firstElement);
   }

   public int size() {
      int s = this.length();
      return s == 1 && this.head() == null ? 0 : s;
   }

   public void accept(Visitor v) throws CompileError {
      v.atArrayInit(this);
   }

   public String getTag() {
      return "array";
   }
}
