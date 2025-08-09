package org.sparkproject.dmg.pmml.tree;

public class SimplifyingNodeTransformer implements NodeTransformer {
   public static final SimplifyingNodeTransformer INSTANCE = new SimplifyingNodeTransformer();

   public Node fromComplexNode(ComplexNode complexNode) {
      return this.simplify(complexNode);
   }

   public ComplexNode toComplexNode(Node node) {
      return node.toComplexNode();
   }

   public Node simplify(Node node) {
      if (!node.hasExtensions() && node.getPartition() == null && node.getEmbeddedModel() == null) {
         if (node.hasScoreDistributions()) {
            return new ClassifierNode(node);
         } else {
            Number recordCount = node.getRecordCount();
            if (node.hasNodes()) {
               return (Node)(recordCount != null ? new CountingBranchNode(node) : new BranchNode(node));
            } else {
               return (Node)(recordCount != null ? new CountingLeafNode(node) : new LeafNode(node));
            }
         }
      } else {
         return node;
      }
   }
}
