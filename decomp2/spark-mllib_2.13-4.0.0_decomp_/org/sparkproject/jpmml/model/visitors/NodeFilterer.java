package org.sparkproject.jpmml.model.visitors;

import java.util.List;
import java.util.ListIterator;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.tree.ComplexNode;
import org.sparkproject.dmg.pmml.tree.DecisionTree;
import org.sparkproject.dmg.pmml.tree.Node;
import org.sparkproject.dmg.pmml.tree.TreeModel;

public abstract class NodeFilterer extends AbstractVisitor {
   public abstract Node filter(Node var1);

   public void filterAll(List nodes) {
      ListIterator<Node> it = nodes.listIterator();

      while(it.hasNext()) {
         it.set(this.filter((Node)it.next()));
      }

   }

   public VisitorAction visit(DecisionTree decisionTree) {
      decisionTree.setNode(this.filter(decisionTree.getNode()));
      return super.visit(decisionTree.getNode());
   }

   public VisitorAction visit(ComplexNode complexNode) {
      return super.visit(complexNode);
   }

   public VisitorAction visit(Node node) {
      if (node.hasNodes()) {
         this.filterAll(node.getNodes());
      }

      return super.visit(node);
   }

   public VisitorAction visit(TreeModel treeModel) {
      treeModel.setNode(this.filter(treeModel.getNode()));
      return super.visit(treeModel);
   }
}
