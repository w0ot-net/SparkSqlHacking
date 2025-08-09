package org.sparkproject.dmg.pmml.association;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ASSOCIATIONMODEL_EXTENSIONS = ReflectionUtil.getField(AssociationModel.class, "extensions");
   Field ASSOCIATIONMODEL_MININGSCHEMA = ReflectionUtil.getField(AssociationModel.class, "miningSchema");
   Field ASSOCIATIONMODEL_OUTPUT = ReflectionUtil.getField(AssociationModel.class, "output");
   Field ASSOCIATIONMODEL_MODELSTATS = ReflectionUtil.getField(AssociationModel.class, "modelStats");
   Field ASSOCIATIONMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(AssociationModel.class, "localTransformations");
   Field ASSOCIATIONMODEL_ITEMS = ReflectionUtil.getField(AssociationModel.class, "items");
   Field ASSOCIATIONMODEL_ITEMSETS = ReflectionUtil.getField(AssociationModel.class, "itemsets");
   Field ASSOCIATIONMODEL_ASSOCIATIONRULES = ReflectionUtil.getField(AssociationModel.class, "associationRules");
   Field ASSOCIATIONMODEL_MODELVERIFICATION = ReflectionUtil.getField(AssociationModel.class, "modelVerification");
   Field ASSOCIATIONRULE_EXTENSIONS = ReflectionUtil.getField(AssociationRule.class, "extensions");
   Field ITEM_EXTENSIONS = ReflectionUtil.getField(Item.class, "extensions");
   Field ITEMREF_EXTENSIONS = ReflectionUtil.getField(ItemRef.class, "extensions");
   Field ITEMSET_EXTENSIONS = ReflectionUtil.getField(Itemset.class, "extensions");
   Field ITEMSET_ITEMREFS = ReflectionUtil.getField(Itemset.class, "itemRefs");
}
