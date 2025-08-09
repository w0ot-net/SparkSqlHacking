package org.antlr.v4.runtime.tree;

public abstract class AbstractParseTreeVisitor implements ParseTreeVisitor {
   public Object visit(ParseTree tree) {
      return tree.accept(this);
   }

   public Object visitChildren(RuleNode node) {
      T result = (T)this.defaultResult();
      int n = node.getChildCount();

      for(int i = 0; i < n && this.shouldVisitNextChild(node, result); ++i) {
         ParseTree c = node.getChild(i);
         T childResult = (T)c.accept(this);
         result = (T)this.aggregateResult(result, childResult);
      }

      return result;
   }

   public Object visitTerminal(TerminalNode node) {
      return this.defaultResult();
   }

   public Object visitErrorNode(ErrorNode node) {
      return this.defaultResult();
   }

   protected Object defaultResult() {
      return null;
   }

   protected Object aggregateResult(Object aggregate, Object nextResult) {
      return nextResult;
   }

   protected boolean shouldVisitNextChild(RuleNode node, Object currentResult) {
      return true;
   }
}
