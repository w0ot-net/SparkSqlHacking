package org.sparkproject.dmg.pmml.association;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field ASSOCIATIONMODEL_MODELNAME = ReflectionUtil.getField(AssociationModel.class, "modelName");
   Field ASSOCIATIONMODEL_MININGFUNCTION = ReflectionUtil.getField(AssociationModel.class, "miningFunction");
   Field ASSOCIATIONMODEL_ALGORITHMNAME = ReflectionUtil.getField(AssociationModel.class, "algorithmName");
   Field ASSOCIATIONMODEL_NUMBEROFTRANSACTIONS = ReflectionUtil.getField(AssociationModel.class, "numberOfTransactions");
   Field ASSOCIATIONMODEL_MAXNUMBEROFITEMSPERTA = ReflectionUtil.getField(AssociationModel.class, "maxNumberOfItemsPerTA");
   Field ASSOCIATIONMODEL_AVGNUMBEROFITEMSPERTA = ReflectionUtil.getField(AssociationModel.class, "avgNumberOfItemsPerTA");
   Field ASSOCIATIONMODEL_MINIMUMSUPPORT = ReflectionUtil.getField(AssociationModel.class, "minimumSupport");
   Field ASSOCIATIONMODEL_MINIMUMCONFIDENCE = ReflectionUtil.getField(AssociationModel.class, "minimumConfidence");
   Field ASSOCIATIONMODEL_LENGTHLIMIT = ReflectionUtil.getField(AssociationModel.class, "lengthLimit");
   Field ASSOCIATIONMODEL_NUMBEROFITEMS = ReflectionUtil.getField(AssociationModel.class, "numberOfItems");
   Field ASSOCIATIONMODEL_NUMBEROFITEMSETS = ReflectionUtil.getField(AssociationModel.class, "numberOfItemsets");
   Field ASSOCIATIONMODEL_NUMBEROFRULES = ReflectionUtil.getField(AssociationModel.class, "numberOfRules");
   Field ASSOCIATIONMODEL_SCORABLE = ReflectionUtil.getField(AssociationModel.class, "scorable");
   Field ASSOCIATIONMODEL_MATHCONTEXT = ReflectionUtil.getField(AssociationModel.class, "mathContext");
   Field ASSOCIATIONRULE_ANTECEDENT = ReflectionUtil.getField(AssociationRule.class, "antecedent");
   Field ASSOCIATIONRULE_CONSEQUENT = ReflectionUtil.getField(AssociationRule.class, "consequent");
   Field ASSOCIATIONRULE_SUPPORT = ReflectionUtil.getField(AssociationRule.class, "support");
   Field ASSOCIATIONRULE_CONFIDENCE = ReflectionUtil.getField(AssociationRule.class, "confidence");
   Field ASSOCIATIONRULE_LIFT = ReflectionUtil.getField(AssociationRule.class, "lift");
   Field ASSOCIATIONRULE_LEVERAGE = ReflectionUtil.getField(AssociationRule.class, "leverage");
   Field ASSOCIATIONRULE_AFFINITY = ReflectionUtil.getField(AssociationRule.class, "affinity");
   Field ASSOCIATIONRULE_ID = ReflectionUtil.getField(AssociationRule.class, "id");
   Field ITEM_ID = ReflectionUtil.getField(Item.class, "id");
   Field ITEM_VALUE = ReflectionUtil.getField(Item.class, "value");
   Field ITEM_FIELD = ReflectionUtil.getField(Item.class, "field");
   Field ITEM_CATEGORY = ReflectionUtil.getField(Item.class, "category");
   Field ITEM_MAPPEDVALUE = ReflectionUtil.getField(Item.class, "mappedValue");
   Field ITEM_WEIGHT = ReflectionUtil.getField(Item.class, "weight");
   Field ITEMREF_ITEMREF = ReflectionUtil.getField(ItemRef.class, "itemRef");
   Field ITEMSET_ID = ReflectionUtil.getField(Itemset.class, "id");
   Field ITEMSET_SUPPORT = ReflectionUtil.getField(Itemset.class, "support");
   Field ITEMSET_NUMBEROFITEMS = ReflectionUtil.getField(Itemset.class, "numberOfItems");
}
