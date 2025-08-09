package org.snakeyaml.engine.v2.scanner;

import java.util.Iterator;
import org.snakeyaml.engine.v2.tokens.Token;

public interface Scanner extends Iterator {
   boolean checkToken(Token.ID... var1);

   default boolean checkToken(Token.ID choice) {
      return this.checkToken(choice);
   }

   Token peekToken();

   Token next();

   void resetDocumentIndex();
}
