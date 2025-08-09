package org.sparkproject.dmg.pmml.association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.MathContext;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "AssociationModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "localTransformations", "items", "itemsets", "associationRules", "modelVerification"}
)
@JsonRootName("AssociationModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "numberOfTransactions", "maxNumberOfItemsPerTA", "avgNumberOfItemsPerTA", "minimumSupport", "minimumConfidence", "lengthLimit", "numberOfItems", "numberOfItemsets", "numberOfRules", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "localTransformations", "items", "itemsets", "associationRules", "modelVerification"})
public class AssociationModel extends Model implements HasExtensions {
   @XmlAttribute(
      name = "modelName"
   )
   @JsonProperty("modelName")
   private String modelName;
   @XmlAttribute(
      name = "functionName",
      required = true
   )
   @JsonProperty("functionName")
   private MiningFunction miningFunction;
   @XmlAttribute(
      name = "algorithmName"
   )
   @JsonProperty("algorithmName")
   private String algorithmName;
   @XmlAttribute(
      name = "numberOfTransactions",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfTransactions")
   private Integer numberOfTransactions;
   @XmlAttribute(
      name = "maxNumberOfItemsPerTA"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maxNumberOfItemsPerTA")
   private Integer maxNumberOfItemsPerTA;
   @XmlAttribute(
      name = "avgNumberOfItemsPerTA"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("avgNumberOfItemsPerTA")
   private Number avgNumberOfItemsPerTA;
   @XmlAttribute(
      name = "minimumSupport",
      required = true
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("minimumSupport")
   private Number minimumSupport;
   @XmlAttribute(
      name = "minimumConfidence",
      required = true
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("minimumConfidence")
   private Number minimumConfidence;
   @XmlAttribute(
      name = "lengthLimit"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("lengthLimit")
   private Integer lengthLimit;
   @XmlAttribute(
      name = "numberOfItems",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfItems")
   @CollectionSize(
      value = "items",
      operator = CollectionSize.Operator.GREATER_OR_EQUAL
   )
   private Integer numberOfItems;
   @XmlAttribute(
      name = "numberOfItemsets",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfItemsets")
   @CollectionSize("itemsets")
   private Integer numberOfItemsets;
   @XmlAttribute(
      name = "numberOfRules",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfRules")
   @CollectionSize("associationRules")
   private Integer numberOfRules;
   @XmlAttribute(
      name = "isScorable"
   )
   @JsonProperty("isScorable")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Boolean scorable;
   @XmlAttribute(
      name = "x-mathContext"
   )
   @JsonProperty("x-mathContext")
   @Added(
      value = Version.XPMML,
      removable = true
   )
   @Since("1.3.7")
   private MathContext mathContext;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "MiningSchema",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("MiningSchema")
   private MiningSchema miningSchema;
   @XmlElement(
      name = "Output",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Output")
   @Added(Version.PMML_4_0)
   private Output output;
   @XmlElement(
      name = "ModelStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelStats")
   private ModelStats modelStats;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "Item",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Item")
   @CollectionElementType(Item.class)
   private List items;
   @XmlElement(
      name = "Itemset",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Itemset")
   @CollectionElementType(Itemset.class)
   private List itemsets;
   @XmlElement(
      name = "AssociationRule",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("AssociationRule")
   @CollectionElementType(AssociationRule.class)
   private List associationRules;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public AssociationModel() {
   }

   @ValueConstructor
   public AssociationModel(@Property("miningFunction") MiningFunction miningFunction, @Property("numberOfTransactions") Integer numberOfTransactions, @Property("minimumSupport") Number minimumSupport, @Property("minimumConfidence") Number minimumConfidence, @Property("numberOfItems") Integer numberOfItems, @Property("numberOfItemsets") Integer numberOfItemsets, @Property("numberOfRules") Integer numberOfRules, @Property("miningSchema") MiningSchema miningSchema) {
      this.miningFunction = miningFunction;
      this.numberOfTransactions = numberOfTransactions;
      this.minimumSupport = minimumSupport;
      this.minimumConfidence = minimumConfidence;
      this.numberOfItems = numberOfItems;
      this.numberOfItemsets = numberOfItemsets;
      this.numberOfRules = numberOfRules;
      this.miningSchema = miningSchema;
   }

   public String getModelName() {
      return this.modelName;
   }

   public AssociationModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public AssociationModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public AssociationModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Integer requireNumberOfTransactions() {
      if (this.numberOfTransactions == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_NUMBEROFTRANSACTIONS);
      } else {
         return this.numberOfTransactions;
      }
   }

   public Integer getNumberOfTransactions() {
      return this.numberOfTransactions;
   }

   public AssociationModel setNumberOfTransactions(@Property("numberOfTransactions") Integer numberOfTransactions) {
      this.numberOfTransactions = numberOfTransactions;
      return this;
   }

   public Integer getMaxNumberOfItemsPerTA() {
      return this.maxNumberOfItemsPerTA;
   }

   public AssociationModel setMaxNumberOfItemsPerTA(@Property("maxNumberOfItemsPerTA") Integer maxNumberOfItemsPerTA) {
      this.maxNumberOfItemsPerTA = maxNumberOfItemsPerTA;
      return this;
   }

   public Number getAvgNumberOfItemsPerTA() {
      return this.avgNumberOfItemsPerTA;
   }

   public AssociationModel setAvgNumberOfItemsPerTA(@Property("avgNumberOfItemsPerTA") Number avgNumberOfItemsPerTA) {
      this.avgNumberOfItemsPerTA = avgNumberOfItemsPerTA;
      return this;
   }

   public Number requireMinimumSupport() {
      if (this.minimumSupport == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_MINIMUMSUPPORT);
      } else {
         return this.minimumSupport;
      }
   }

   public Number getMinimumSupport() {
      return this.minimumSupport;
   }

   public AssociationModel setMinimumSupport(@Property("minimumSupport") Number minimumSupport) {
      this.minimumSupport = minimumSupport;
      return this;
   }

   public Number requireMinimumConfidence() {
      if (this.minimumConfidence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_MINIMUMCONFIDENCE);
      } else {
         return this.minimumConfidence;
      }
   }

   public Number getMinimumConfidence() {
      return this.minimumConfidence;
   }

   public AssociationModel setMinimumConfidence(@Property("minimumConfidence") Number minimumConfidence) {
      this.minimumConfidence = minimumConfidence;
      return this;
   }

   public Integer getLengthLimit() {
      return this.lengthLimit;
   }

   public AssociationModel setLengthLimit(@Property("lengthLimit") Integer lengthLimit) {
      this.lengthLimit = lengthLimit;
      return this;
   }

   public Integer requireNumberOfItems() {
      if (this.numberOfItems == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_NUMBEROFITEMS);
      } else {
         return this.numberOfItems;
      }
   }

   public Integer getNumberOfItems() {
      return this.numberOfItems;
   }

   public AssociationModel setNumberOfItems(@Property("numberOfItems") Integer numberOfItems) {
      this.numberOfItems = numberOfItems;
      return this;
   }

   public Integer requireNumberOfItemsets() {
      if (this.numberOfItemsets == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_NUMBEROFITEMSETS);
      } else {
         return this.numberOfItemsets;
      }
   }

   public Integer getNumberOfItemsets() {
      return this.numberOfItemsets;
   }

   public AssociationModel setNumberOfItemsets(@Property("numberOfItemsets") Integer numberOfItemsets) {
      this.numberOfItemsets = numberOfItemsets;
      return this;
   }

   public Integer requireNumberOfRules() {
      if (this.numberOfRules == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONMODEL_NUMBEROFRULES);
      } else {
         return this.numberOfRules;
      }
   }

   public Integer getNumberOfRules() {
      return this.numberOfRules;
   }

   public AssociationModel setNumberOfRules(@Property("numberOfRules") Integer numberOfRules) {
      this.numberOfRules = numberOfRules;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public AssociationModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public AssociationModel setMathContext(@Property("mathContext") MathContext mathContext) {
      this.mathContext = mathContext;
      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public AssociationModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.ASSOCIATIONMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public AssociationModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public AssociationModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public AssociationModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public AssociationModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public List getItems() {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      return this.items;
   }

   public AssociationModel addItems(Item... items) {
      this.getItems().addAll(Arrays.asList(items));
      return this;
   }

   public boolean hasItemsets() {
      return this.itemsets != null && !this.itemsets.isEmpty();
   }

   public List getItemsets() {
      if (this.itemsets == null) {
         this.itemsets = new ArrayList();
      }

      return this.itemsets;
   }

   public AssociationModel addItemsets(Itemset... itemsets) {
      this.getItemsets().addAll(Arrays.asList(itemsets));
      return this;
   }

   public boolean hasAssociationRules() {
      return this.associationRules != null && !this.associationRules.isEmpty();
   }

   public List getAssociationRules() {
      if (this.associationRules == null) {
         this.associationRules = new ArrayList();
      }

      return this.associationRules;
   }

   public AssociationModel addAssociationRules(AssociationRule... associationRules) {
      this.getAssociationRules().addAll(Arrays.asList(associationRules));
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public AssociationModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
      this.modelVerification = modelVerification;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getLocalTransformations());
         }

         if (status == VisitorAction.CONTINUE && this.hasItems()) {
            status = PMMLObject.traverse(visitor, this.getItems());
         }

         if (status == VisitorAction.CONTINUE && this.hasItemsets()) {
            status = PMMLObject.traverse(visitor, this.getItemsets());
         }

         if (status == VisitorAction.CONTINUE && this.hasAssociationRules()) {
            status = PMMLObject.traverse(visitor, this.getAssociationRules());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
