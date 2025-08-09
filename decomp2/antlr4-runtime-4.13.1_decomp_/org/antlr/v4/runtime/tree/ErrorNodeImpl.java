package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.Token;

public class ErrorNodeImpl extends TerminalNodeImpl implements ErrorNode {
   public ErrorNodeImpl(Token token) {
      super(token);
   }

   public Object accept(ParseTreeVisitor visitor) {
      return visitor.visitErrorNode(this);
   }
}
