package org.sparkproject.dmg.pmml.tree;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field COMPLEXNODE_EXTENSIONS = ReflectionUtil.getField(ComplexNode.class, "extensions");
   Field COMPLEXNODE_PREDICATE = ReflectionUtil.getField(ComplexNode.class, "predicate");
   Field COMPLEXNODE_PARTITION = ReflectionUtil.getField(ComplexNode.class, "partition");
   Field COMPLEXNODE_SCOREDISTRIBUTIONS = ReflectionUtil.getField(ComplexNode.class, "scoreDistributions");
   Field COMPLEXNODE_NODES = ReflectionUtil.getField(ComplexNode.class, "nodes");
   Field COMPLEXNODE_EMBEDDEDMODEL = ReflectionUtil.getField(ComplexNode.class, "embeddedModel");
   Field DECISIONTREE_EXTENSIONS = ReflectionUtil.getField(DecisionTree.class, "extensions");
   Field DECISIONTREE_OUTPUT = ReflectionUtil.getField(DecisionTree.class, "output");
   Field DECISIONTREE_MODELSTATS = ReflectionUtil.getField(DecisionTree.class, "modelStats");
   Field DECISIONTREE_TARGETS = ReflectionUtil.getField(DecisionTree.class, "targets");
   Field DECISIONTREE_LOCALTRANSFORMATIONS = ReflectionUtil.getField(DecisionTree.class, "localTransformations");
   Field DECISIONTREE_RESULTFIELDS = ReflectionUtil.getField(DecisionTree.class, "resultFields");
   Field DECISIONTREE_NODE = ReflectionUtil.getField(DecisionTree.class, "node");
   Field TREEMODEL_EXTENSIONS = ReflectionUtil.getField(TreeModel.class, "extensions");
   Field TREEMODEL_MININGSCHEMA = ReflectionUtil.getField(TreeModel.class, "miningSchema");
   Field TREEMODEL_OUTPUT = ReflectionUtil.getField(TreeModel.class, "output");
   Field TREEMODEL_MODELSTATS = ReflectionUtil.getField(TreeModel.class, "modelStats");
   Field TREEMODEL_MODELEXPLANATION = ReflectionUtil.getField(TreeModel.class, "modelExplanation");
   Field TREEMODEL_TARGETS = ReflectionUtil.getField(TreeModel.class, "targets");
   Field TREEMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(TreeModel.class, "localTransformations");
   Field TREEMODEL_NODE = ReflectionUtil.getField(TreeModel.class, "node");
   Field TREEMODEL_MODELVERIFICATION = ReflectionUtil.getField(TreeModel.class, "modelVerification");
}
