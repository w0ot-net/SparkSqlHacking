package org.apache.derby.impl.sql.compile;

class Token {
   public int kind;
   public int beginLine;
   public int beginColumn;
   public int endLine;
   public int endColumn;
   public int beginOffset;
   public int endOffset;
   public String image;
   public Token next;
   public Token specialToken;

   public String toString() {
      return this.image;
   }

   public static Token newToken(int var0) {
      switch (var0) {
         default -> {
            return new Token();
         }
      }
   }
}
