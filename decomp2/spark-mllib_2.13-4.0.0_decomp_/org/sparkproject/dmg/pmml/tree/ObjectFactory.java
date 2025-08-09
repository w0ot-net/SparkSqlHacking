package org.sparkproject.dmg.pmml.tree;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TreeModel createTreeModel() {
      return new TreeModel();
   }

   public DecisionTree createDecisionTree() {
      return new DecisionTree();
   }

   public ComplexNode createComplexNode() {
      return new ComplexNode();
   }
}
