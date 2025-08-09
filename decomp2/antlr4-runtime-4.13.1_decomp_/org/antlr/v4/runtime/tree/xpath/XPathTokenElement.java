package org.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

public class XPathTokenElement extends XPathElement {
   protected int tokenType;

   public XPathTokenElement(String tokenName, int tokenType) {
      super(tokenName);
      this.tokenType = tokenType;
   }

   public Collection evaluate(ParseTree t) {
      List<ParseTree> nodes = new ArrayList();

      for(Tree c : Trees.getChildren(t)) {
         if (c instanceof TerminalNode) {
            TerminalNode tnode = (TerminalNode)c;
            if (tnode.getSymbol().getType() == this.tokenType && !this.invert || tnode.getSymbol().getType() != this.tokenType && this.invert) {
               nodes.add(tnode);
            }
         }
      }

      return nodes;
   }
}
