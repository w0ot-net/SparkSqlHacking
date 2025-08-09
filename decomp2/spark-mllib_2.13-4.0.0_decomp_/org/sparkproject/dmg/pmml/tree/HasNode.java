package org.sparkproject.dmg.pmml.tree;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasNode {
   TreeModel.MissingValueStrategy getMissingValueStrategy();

   PMMLObject setMissingValueStrategy(TreeModel.MissingValueStrategy var1);

   Number getMissingValuePenalty();

   PMMLObject setMissingValuePenalty(Number var1);

   TreeModel.NoTrueChildStrategy getNoTrueChildStrategy();

   PMMLObject setNoTrueChildStrategy(TreeModel.NoTrueChildStrategy var1);

   TreeModel.SplitCharacteristic getSplitCharacteristic();

   PMMLObject setSplitCharacteristic(TreeModel.SplitCharacteristic var1);

   Node getNode();

   PMMLObject setNode(Node var1);
}
