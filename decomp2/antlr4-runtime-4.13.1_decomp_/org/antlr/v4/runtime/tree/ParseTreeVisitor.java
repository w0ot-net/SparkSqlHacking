package org.antlr.v4.runtime.tree;

public interface ParseTreeVisitor {
   Object visit(ParseTree var1);

   Object visitChildren(RuleNode var1);

   Object visitTerminal(TerminalNode var1);

   Object visitErrorNode(ErrorNode var1);
}
