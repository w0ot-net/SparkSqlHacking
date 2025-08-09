package org.sparkproject.dmg.pmml.sequence;

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
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.dmg.pmml.association.Item;
import org.sparkproject.dmg.pmml.association.Itemset;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SequenceModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "modelStats", "localTransformations", "constraints", "items", "itemsets", "setPredicates", "sequences", "sequenceRules"}
)
@JsonRootName("SequenceModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "numberOfTransactions", "maxNumberOfItemsPerTransaction", "avgNumberOfItemsPerTransaction", "numberOfTransactionGroups", "maxNumberOfTAsPerTAGroup", "avgNumberOfTAsPerTAGroup", "minimumSupport", "minimumConfidence", "lengthLimit", "numberOfItems", "numberOfSets", "numberOfSequences", "numberOfRules", "timeWindowWidth", "minimumTime", "maximumTime", "scorable", "mathContext", "extensions", "miningSchema", "modelStats", "localTransformations", "constraints", "items", "itemsets", "setPredicates", "sequences", "sequenceRules"})
public class SequenceModel extends Model implements HasExtensions {
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
      name = "numberOfTransactions"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfTransactions")
   private Integer numberOfTransactions;
   @XmlAttribute(
      name = "maxNumberOfItemsPerTransaction"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maxNumberOfItemsPerTransaction")
   private Integer maxNumberOfItemsPerTransaction;
   @XmlAttribute(
      name = "avgNumberOfItemsPerTransaction"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("avgNumberOfItemsPerTransaction")
   private Number avgNumberOfItemsPerTransaction;
   @XmlAttribute(
      name = "numberOfTransactionGroups"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfTransactionGroups")
   @Added(Version.PMML_3_1)
   private Integer numberOfTransactionGroups;
   @XmlAttribute(
      name = "maxNumberOfTAsPerTAGroup"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maxNumberOfTAsPerTAGroup")
   @Added(Version.PMML_3_1)
   private Integer maxNumberOfTAsPerTAGroup;
   @XmlAttribute(
      name = "avgNumberOfTAsPerTAGroup"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("avgNumberOfTAsPerTAGroup")
   @Added(Version.PMML_3_1)
   private Number avgNumberOfTAsPerTAGroup;
   @XmlAttribute(
      name = "minimumSupport",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumSupport")
   @Removed(Version.PMML_3_1)
   private Number minimumSupport;
   @XmlAttribute(
      name = "minimumConfidence",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumConfidence")
   @Removed(Version.PMML_3_1)
   private Number minimumConfidence;
   @XmlAttribute(
      name = "lengthLimit"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("lengthLimit")
   @Removed(Version.PMML_3_1)
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
   @Removed(Version.PMML_3_1)
   private Integer numberOfItems;
   @XmlAttribute(
      name = "numberOfSets",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfSets")
   @Removed(Version.PMML_3_1)
   private Integer numberOfSets;
   @XmlAttribute(
      name = "numberOfSequences",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfSequences")
   @Removed(Version.PMML_3_1)
   private Integer numberOfSequences;
   @XmlAttribute(
      name = "numberOfRules",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfRules")
   @Removed(Version.PMML_3_1)
   private Integer numberOfRules;
   @XmlAttribute(
      name = "timeWindowWidth"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("timeWindowWidth")
   @Removed(Version.PMML_3_1)
   private Integer timeWindowWidth;
   @XmlAttribute(
      name = "minimumTime"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("minimumTime")
   @Removed(Version.PMML_3_1)
   private Integer minimumTime;
   @XmlAttribute(
      name = "maximumTime"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("maximumTime")
   @Removed(Version.PMML_3_1)
   private Integer maximumTime;
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
      name = "Constraints",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Constraints")
   @Added(Version.PMML_3_1)
   private Constraints constraints;
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
      name = "SetPredicate",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SetPredicate")
   @CollectionElementType(SetPredicate.class)
   private List setPredicates;
   @XmlElement(
      name = "Sequence",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Sequence")
   @CollectionElementType(Sequence.class)
   private List sequences;
   @XmlElement(
      name = "SequenceRule",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SequenceRule")
   @CollectionElementType(SequenceRule.class)
   private List sequenceRules;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public SequenceModel() {
   }

   @ValueConstructor
   public SequenceModel(@Property("miningFunction") MiningFunction miningFunction, @Property("minimumSupport") Number minimumSupport, @Property("minimumConfidence") Number minimumConfidence, @Property("numberOfItems") Integer numberOfItems, @Property("numberOfSets") Integer numberOfSets, @Property("numberOfSequences") Integer numberOfSequences, @Property("numberOfRules") Integer numberOfRules, @Property("miningSchema") MiningSchema miningSchema, @Property("sequences") List sequences) {
      this.miningFunction = miningFunction;
      this.minimumSupport = minimumSupport;
      this.minimumConfidence = minimumConfidence;
      this.numberOfItems = numberOfItems;
      this.numberOfSets = numberOfSets;
      this.numberOfSequences = numberOfSequences;
      this.numberOfRules = numberOfRules;
      this.miningSchema = miningSchema;
      this.sequences = sequences;
   }

   public String getModelName() {
      return this.modelName;
   }

   public SequenceModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public SequenceModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public SequenceModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Integer getNumberOfTransactions() {
      return this.numberOfTransactions;
   }

   public SequenceModel setNumberOfTransactions(@Property("numberOfTransactions") Integer numberOfTransactions) {
      this.numberOfTransactions = numberOfTransactions;
      return this;
   }

   public Integer getMaxNumberOfItemsPerTransaction() {
      return this.maxNumberOfItemsPerTransaction;
   }

   public SequenceModel setMaxNumberOfItemsPerTransaction(@Property("maxNumberOfItemsPerTransaction") Integer maxNumberOfItemsPerTransaction) {
      this.maxNumberOfItemsPerTransaction = maxNumberOfItemsPerTransaction;
      return this;
   }

   public Number getAvgNumberOfItemsPerTransaction() {
      return this.avgNumberOfItemsPerTransaction;
   }

   public SequenceModel setAvgNumberOfItemsPerTransaction(@Property("avgNumberOfItemsPerTransaction") Number avgNumberOfItemsPerTransaction) {
      this.avgNumberOfItemsPerTransaction = avgNumberOfItemsPerTransaction;
      return this;
   }

   public Integer getNumberOfTransactionGroups() {
      return this.numberOfTransactionGroups;
   }

   public SequenceModel setNumberOfTransactionGroups(@Property("numberOfTransactionGroups") Integer numberOfTransactionGroups) {
      this.numberOfTransactionGroups = numberOfTransactionGroups;
      return this;
   }

   public Integer getMaxNumberOfTAsPerTAGroup() {
      return this.maxNumberOfTAsPerTAGroup;
   }

   public SequenceModel setMaxNumberOfTAsPerTAGroup(@Property("maxNumberOfTAsPerTAGroup") Integer maxNumberOfTAsPerTAGroup) {
      this.maxNumberOfTAsPerTAGroup = maxNumberOfTAsPerTAGroup;
      return this;
   }

   public Number getAvgNumberOfTAsPerTAGroup() {
      return this.avgNumberOfTAsPerTAGroup;
   }

   public SequenceModel setAvgNumberOfTAsPerTAGroup(@Property("avgNumberOfTAsPerTAGroup") Number avgNumberOfTAsPerTAGroup) {
      this.avgNumberOfTAsPerTAGroup = avgNumberOfTAsPerTAGroup;
      return this;
   }

   public Number requireMinimumSupport() {
      if (this.minimumSupport == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_MINIMUMSUPPORT);
      } else {
         return this.minimumSupport;
      }
   }

   public Number getMinimumSupport() {
      return this.minimumSupport;
   }

   public SequenceModel setMinimumSupport(@Property("minimumSupport") Number minimumSupport) {
      this.minimumSupport = minimumSupport;
      return this;
   }

   public Number requireMinimumConfidence() {
      if (this.minimumConfidence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_MINIMUMCONFIDENCE);
      } else {
         return this.minimumConfidence;
      }
   }

   public Number getMinimumConfidence() {
      return this.minimumConfidence;
   }

   public SequenceModel setMinimumConfidence(@Property("minimumConfidence") Number minimumConfidence) {
      this.minimumConfidence = minimumConfidence;
      return this;
   }

   public Integer getLengthLimit() {
      return this.lengthLimit;
   }

   public SequenceModel setLengthLimit(@Property("lengthLimit") Integer lengthLimit) {
      this.lengthLimit = lengthLimit;
      return this;
   }

   public Integer requireNumberOfItems() {
      if (this.numberOfItems == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_NUMBEROFITEMS);
      } else {
         return this.numberOfItems;
      }
   }

   public Integer getNumberOfItems() {
      return this.numberOfItems;
   }

   public SequenceModel setNumberOfItems(@Property("numberOfItems") Integer numberOfItems) {
      this.numberOfItems = numberOfItems;
      return this;
   }

   public Integer requireNumberOfSets() {
      if (this.numberOfSets == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_NUMBEROFSETS);
      } else {
         return this.numberOfSets;
      }
   }

   public Integer getNumberOfSets() {
      return this.numberOfSets;
   }

   public SequenceModel setNumberOfSets(@Property("numberOfSets") Integer numberOfSets) {
      this.numberOfSets = numberOfSets;
      return this;
   }

   public Integer requireNumberOfSequences() {
      if (this.numberOfSequences == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_NUMBEROFSEQUENCES);
      } else {
         return this.numberOfSequences;
      }
   }

   public Integer getNumberOfSequences() {
      return this.numberOfSequences;
   }

   public SequenceModel setNumberOfSequences(@Property("numberOfSequences") Integer numberOfSequences) {
      this.numberOfSequences = numberOfSequences;
      return this;
   }

   public Integer requireNumberOfRules() {
      if (this.numberOfRules == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCEMODEL_NUMBEROFRULES);
      } else {
         return this.numberOfRules;
      }
   }

   public Integer getNumberOfRules() {
      return this.numberOfRules;
   }

   public SequenceModel setNumberOfRules(@Property("numberOfRules") Integer numberOfRules) {
      this.numberOfRules = numberOfRules;
      return this;
   }

   public Integer getTimeWindowWidth() {
      return this.timeWindowWidth;
   }

   public SequenceModel setTimeWindowWidth(@Property("timeWindowWidth") Integer timeWindowWidth) {
      this.timeWindowWidth = timeWindowWidth;
      return this;
   }

   public Integer getMinimumTime() {
      return this.minimumTime;
   }

   public SequenceModel setMinimumTime(@Property("minimumTime") Integer minimumTime) {
      this.minimumTime = minimumTime;
      return this;
   }

   public Integer getMaximumTime() {
      return this.maximumTime;
   }

   public SequenceModel setMaximumTime(@Property("maximumTime") Integer maximumTime) {
      this.maximumTime = maximumTime;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public SequenceModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public SequenceModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public SequenceModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.SEQUENCEMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public SequenceModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public SequenceModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public SequenceModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public Constraints getConstraints() {
      return this.constraints;
   }

   public SequenceModel setConstraints(@Property("constraints") Constraints constraints) {
      this.constraints = constraints;
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

   public SequenceModel addItems(Item... items) {
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

   public SequenceModel addItemsets(Itemset... itemsets) {
      this.getItemsets().addAll(Arrays.asList(itemsets));
      return this;
   }

   public boolean hasSetPredicates() {
      return this.setPredicates != null && !this.setPredicates.isEmpty();
   }

   public List getSetPredicates() {
      if (this.setPredicates == null) {
         this.setPredicates = new ArrayList();
      }

      return this.setPredicates;
   }

   public SequenceModel addSetPredicates(SetPredicate... setPredicates) {
      this.getSetPredicates().addAll(Arrays.asList(setPredicates));
      return this;
   }

   public boolean hasSequences() {
      return this.sequences != null && !this.sequences.isEmpty();
   }

   public List requireSequences() {
      if (this.sequences != null && !this.sequences.isEmpty()) {
         return this.sequences;
      } else {
         throw new MissingElementException(this, PMMLElements.SEQUENCEMODEL_SEQUENCES);
      }
   }

   public List getSequences() {
      if (this.sequences == null) {
         this.sequences = new ArrayList();
      }

      return this.sequences;
   }

   public SequenceModel addSequences(Sequence... sequences) {
      this.getSequences().addAll(Arrays.asList(sequences));
      return this;
   }

   public boolean hasSequenceRules() {
      return this.sequenceRules != null && !this.sequenceRules.isEmpty();
   }

   public List getSequenceRules() {
      if (this.sequenceRules == null) {
         this.sequenceRules = new ArrayList();
      }

      return this.sequenceRules;
   }

   public SequenceModel addSequenceRules(SequenceRule... sequenceRules) {
      this.getSequenceRules().addAll(Arrays.asList(sequenceRules));
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getModelStats(), this.getLocalTransformations(), this.getConstraints());
         }

         if (status == VisitorAction.CONTINUE && this.hasItems()) {
            status = PMMLObject.traverse(visitor, this.getItems());
         }

         if (status == VisitorAction.CONTINUE && this.hasItemsets()) {
            status = PMMLObject.traverse(visitor, this.getItemsets());
         }

         if (status == VisitorAction.CONTINUE && this.hasSetPredicates()) {
            status = PMMLObject.traverse(visitor, this.getSetPredicates());
         }

         if (status == VisitorAction.CONTINUE && this.hasSequences()) {
            status = PMMLObject.traverse(visitor, this.getSequences());
         }

         if (status == VisitorAction.CONTINUE && this.hasSequenceRules()) {
            status = PMMLObject.traverse(visitor, this.getSequenceRules());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
