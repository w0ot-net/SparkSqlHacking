package org.sparkproject.dmg.pmml.association;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public AssociationModel createAssociationModel() {
      return new AssociationModel();
   }

   public Item createItem() {
      return new Item();
   }

   public Itemset createItemset() {
      return new Itemset();
   }

   public ItemRef createItemRef() {
      return new ItemRef();
   }

   public AssociationRule createAssociationRule() {
      return new AssociationRule();
   }
}
