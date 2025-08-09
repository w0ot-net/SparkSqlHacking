package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;

public interface ParseTree extends SyntaxTree {
   ParseTree getParent();

   ParseTree getChild(int var1);

   void setParent(RuleContext var1);

   Object accept(ParseTreeVisitor var1);

   String getText();

   String toStringTree(Parser var1);
}
