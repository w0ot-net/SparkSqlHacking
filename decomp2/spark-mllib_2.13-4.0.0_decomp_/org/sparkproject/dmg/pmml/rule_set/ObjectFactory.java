package org.sparkproject.dmg.pmml.rule_set;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RuleSelectionMethod createRuleSelectionMethod() {
      return new RuleSelectionMethod();
   }

   public RuleSetModel createRuleSetModel() {
      return new RuleSetModel();
   }

   public RuleSet createRuleSet() {
      return new RuleSet();
   }

   public SimpleRule createSimpleRule() {
      return new SimpleRule();
   }

   public CompoundRule createCompoundRule() {
      return new CompoundRule();
   }
}
