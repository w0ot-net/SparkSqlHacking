package org.sparkproject.dmg.pmml.tree;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field COMPLEXNODE_ID = ReflectionUtil.getField(ComplexNode.class, "id");
   Field COMPLEXNODE_SCORE = ReflectionUtil.getField(ComplexNode.class, "score");
   Field COMPLEXNODE_RECORDCOUNT = ReflectionUtil.getField(ComplexNode.class, "recordCount");
   Field COMPLEXNODE_DEFAULTCHILD = ReflectionUtil.getField(ComplexNode.class, "defaultChild");
   Field DECISIONTREE_MODELNAME = ReflectionUtil.getField(DecisionTree.class, "modelName");
   Field DECISIONTREE_MININGFUNCTION = ReflectionUtil.getField(DecisionTree.class, "miningFunction");
   Field DECISIONTREE_ALGORITHMNAME = ReflectionUtil.getField(DecisionTree.class, "algorithmName");
   Field DECISIONTREE_MISSINGVALUESTRATEGY = ReflectionUtil.getField(DecisionTree.class, "missingValueStrategy");
   Field DECISIONTREE_MISSINGVALUEPENALTY = ReflectionUtil.getField(DecisionTree.class, "missingValuePenalty");
   Field DECISIONTREE_NOTRUECHILDSTRATEGY = ReflectionUtil.getField(DecisionTree.class, "noTrueChildStrategy");
   Field DECISIONTREE_SPLITCHARACTERISTIC = ReflectionUtil.getField(DecisionTree.class, "splitCharacteristic");
   Field TREEMODEL_MODELNAME = ReflectionUtil.getField(TreeModel.class, "modelName");
   Field TREEMODEL_MININGFUNCTION = ReflectionUtil.getField(TreeModel.class, "miningFunction");
   Field TREEMODEL_ALGORITHMNAME = ReflectionUtil.getField(TreeModel.class, "algorithmName");
   Field TREEMODEL_MISSINGVALUESTRATEGY = ReflectionUtil.getField(TreeModel.class, "missingValueStrategy");
   Field TREEMODEL_MISSINGVALUEPENALTY = ReflectionUtil.getField(TreeModel.class, "missingValuePenalty");
   Field TREEMODEL_NOTRUECHILDSTRATEGY = ReflectionUtil.getField(TreeModel.class, "noTrueChildStrategy");
   Field TREEMODEL_SPLITCHARACTERISTIC = ReflectionUtil.getField(TreeModel.class, "splitCharacteristic");
   Field TREEMODEL_SCORABLE = ReflectionUtil.getField(TreeModel.class, "scorable");
   Field TREEMODEL_MATHCONTEXT = ReflectionUtil.getField(TreeModel.class, "mathContext");
}
