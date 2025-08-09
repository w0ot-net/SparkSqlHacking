package org.sparkproject.dmg.pmml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.sparkproject.dmg.pmml.tree.ComplexNode;
import org.sparkproject.dmg.pmml.tree.Node;
import org.sparkproject.dmg.pmml.tree.NodeTransformer;
import org.sparkproject.dmg.pmml.tree.SimplifyingNodeTransformer;

public class NodeAdapter extends XmlAdapter {
   public static final ThreadLocal NODE_TRANSFORMER_PROVIDER = new ThreadLocal() {
      public NodeTransformer initialValue() {
         return SimplifyingNodeTransformer.INSTANCE;
      }
   };

   public Node unmarshal(ComplexNode value) {
      NodeTransformer nodeTransformer = (NodeTransformer)NODE_TRANSFORMER_PROVIDER.get();
      return (Node)(nodeTransformer != null ? nodeTransformer.fromComplexNode(value) : value);
   }

   public ComplexNode marshal(Node node) {
      NodeTransformer nodeTransformer = (NodeTransformer)NODE_TRANSFORMER_PROVIDER.get();
      return nodeTransformer != null ? nodeTransformer.toComplexNode(node) : node.toComplexNode();
   }
}
