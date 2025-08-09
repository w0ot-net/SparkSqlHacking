package org.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

public class XPathRuleElement extends XPathElement {
   protected int ruleIndex;

   public XPathRuleElement(String ruleName, int ruleIndex) {
      super(ruleName);
      this.ruleIndex = ruleIndex;
   }

   public Collection evaluate(ParseTree t) {
      List<ParseTree> nodes = new ArrayList();

      for(Tree c : Trees.getChildren(t)) {
         if (c instanceof ParserRuleContext) {
            ParserRuleContext ctx = (ParserRuleContext)c;
            if (ctx.getRuleIndex() == this.ruleIndex && !this.invert || ctx.getRuleIndex() != this.ruleIndex && this.invert) {
               nodes.add(ctx);
            }
         }
      }

      return nodes;
   }
}
