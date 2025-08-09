package org.antlr.v4.runtime.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import org.antlr.v4.runtime.misc.IntegerStack;

public class IterativeParseTreeWalker extends ParseTreeWalker {
   public void walk(ParseTreeListener listener, ParseTree t) {
      Deque<ParseTree> nodeStack = new ArrayDeque();
      IntegerStack indexStack = new IntegerStack();
      ParseTree currentNode = t;
      int currentIndex = 0;

      while(currentNode != null) {
         if (currentNode instanceof ErrorNode) {
            listener.visitErrorNode((ErrorNode)currentNode);
         } else if (currentNode instanceof TerminalNode) {
            listener.visitTerminal((TerminalNode)currentNode);
         } else {
            RuleNode r = (RuleNode)currentNode;
            this.enterRule(listener, r);
         }

         if (currentNode.getChildCount() > 0) {
            nodeStack.push(currentNode);
            indexStack.push(currentIndex);
            currentIndex = 0;
            currentNode = currentNode.getChild(0);
         } else {
            while(true) {
               if (currentNode instanceof RuleNode) {
                  this.exitRule(listener, (RuleNode)currentNode);
               }

               if (nodeStack.isEmpty()) {
                  currentNode = null;
                  currentIndex = 0;
                  break;
               }

               ParseTree var10000 = (ParseTree)nodeStack.peek();
               ++currentIndex;
               currentNode = var10000.getChild(currentIndex);
               if (currentNode != null) {
                  break;
               }

               currentNode = (ParseTree)nodeStack.pop();
               currentIndex = indexStack.pop();
               if (currentNode == null) {
                  break;
               }
            }
         }
      }

   }
}
