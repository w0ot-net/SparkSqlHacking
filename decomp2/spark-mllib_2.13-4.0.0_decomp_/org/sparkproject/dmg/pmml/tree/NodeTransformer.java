package org.sparkproject.dmg.pmml.tree;

public interface NodeTransformer {
   Node fromComplexNode(ComplexNode var1);

   ComplexNode toComplexNode(Node var1);
}
