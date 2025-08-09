package org.sparkproject.dmg.pmml.text;

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
import org.sparkproject.dmg.pmml.ModelExplanation;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Targets;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TextModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "textDictionary", "textCorpus", "documentTermMatrix", "textModelNormalization", "textModelSimiliarity", "modelVerification"}
)
@JsonRootName("TextModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "numberOfTerms", "numberOfDocuments", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "textDictionary", "textCorpus", "documentTermMatrix", "textModelNormalization", "textModelSimiliarity", "modelVerification"})
@Deprecated(Version.PMML_4_2)
public class TextModel extends Model implements HasExtensions {
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
      name = "numberOfTerms",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfTerms")
   private Integer numberOfTerms;
   @XmlAttribute(
      name = "numberOfDocuments",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfDocuments")
   private Integer numberOfDocuments;
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
   private Output output;
   @XmlElement(
      name = "ModelStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelStats")
   private ModelStats modelStats;
   @XmlElement(
      name = "ModelExplanation",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelExplanation")
   @Added(
      value = Version.PMML_4_0,
      removable = true
   )
   private ModelExplanation modelExplanation;
   @XmlElement(
      name = "Targets",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Targets")
   private Targets targets;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "TextDictionary",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TextDictionary")
   private TextDictionary textDictionary;
   @XmlElement(
      name = "TextCorpus",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TextCorpus")
   private TextCorpus textCorpus;
   @XmlElement(
      name = "DocumentTermMatrix",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("DocumentTermMatrix")
   private DocumentTermMatrix documentTermMatrix;
   @XmlElement(
      name = "TextModelNormalization",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TextModelNormalization")
   private TextModelNormalization textModelNormalization;
   @XmlElement(
      name = "TextModelSimiliarity",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TextModelSimiliarity")
   private TextModelSimiliarity textModelSimiliarity;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public TextModel() {
   }

   @ValueConstructor
   public TextModel(@Property("miningFunction") MiningFunction miningFunction, @Property("numberOfTerms") Integer numberOfTerms, @Property("numberOfDocuments") Integer numberOfDocuments, @Property("miningSchema") MiningSchema miningSchema, @Property("textDictionary") TextDictionary textDictionary, @Property("textCorpus") TextCorpus textCorpus, @Property("documentTermMatrix") DocumentTermMatrix documentTermMatrix) {
      this.miningFunction = miningFunction;
      this.numberOfTerms = numberOfTerms;
      this.numberOfDocuments = numberOfDocuments;
      this.miningSchema = miningSchema;
      this.textDictionary = textDictionary;
      this.textCorpus = textCorpus;
      this.documentTermMatrix = documentTermMatrix;
   }

   public String getModelName() {
      return this.modelName;
   }

   public TextModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TEXTMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public TextModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public TextModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Integer requireNumberOfTerms() {
      if (this.numberOfTerms == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TEXTMODEL_NUMBEROFTERMS);
      } else {
         return this.numberOfTerms;
      }
   }

   public Integer getNumberOfTerms() {
      return this.numberOfTerms;
   }

   public TextModel setNumberOfTerms(@Property("numberOfTerms") Integer numberOfTerms) {
      this.numberOfTerms = numberOfTerms;
      return this;
   }

   public Integer requireNumberOfDocuments() {
      if (this.numberOfDocuments == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TEXTMODEL_NUMBEROFDOCUMENTS);
      } else {
         return this.numberOfDocuments;
      }
   }

   public Integer getNumberOfDocuments() {
      return this.numberOfDocuments;
   }

   public TextModel setNumberOfDocuments(@Property("numberOfDocuments") Integer numberOfDocuments) {
      this.numberOfDocuments = numberOfDocuments;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public TextModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public TextModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public TextModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.TEXTMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public TextModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public TextModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public TextModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public TextModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public TextModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public TextModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public TextDictionary requireTextDictionary() {
      if (this.textDictionary == null) {
         throw new MissingElementException(this, PMMLElements.TEXTMODEL_TEXTDICTIONARY);
      } else {
         return this.textDictionary;
      }
   }

   public TextDictionary getTextDictionary() {
      return this.textDictionary;
   }

   public TextModel setTextDictionary(@Property("textDictionary") TextDictionary textDictionary) {
      this.textDictionary = textDictionary;
      return this;
   }

   public TextCorpus requireTextCorpus() {
      if (this.textCorpus == null) {
         throw new MissingElementException(this, PMMLElements.TEXTMODEL_TEXTCORPUS);
      } else {
         return this.textCorpus;
      }
   }

   public TextCorpus getTextCorpus() {
      return this.textCorpus;
   }

   public TextModel setTextCorpus(@Property("textCorpus") TextCorpus textCorpus) {
      this.textCorpus = textCorpus;
      return this;
   }

   public DocumentTermMatrix requireDocumentTermMatrix() {
      if (this.documentTermMatrix == null) {
         throw new MissingElementException(this, PMMLElements.TEXTMODEL_DOCUMENTTERMMATRIX);
      } else {
         return this.documentTermMatrix;
      }
   }

   public DocumentTermMatrix getDocumentTermMatrix() {
      return this.documentTermMatrix;
   }

   public TextModel setDocumentTermMatrix(@Property("documentTermMatrix") DocumentTermMatrix documentTermMatrix) {
      this.documentTermMatrix = documentTermMatrix;
      return this;
   }

   public TextModelNormalization getTextModelNormalization() {
      return this.textModelNormalization;
   }

   public TextModel setTextModelNormalization(@Property("textModelNormalization") TextModelNormalization textModelNormalization) {
      this.textModelNormalization = textModelNormalization;
      return this;
   }

   public TextModelSimiliarity getTextModelSimiliarity() {
      return this.textModelSimiliarity;
   }

   public TextModel setTextModelSimiliarity(@Property("textModelSimiliarity") TextModelSimiliarity textModelSimiliarity) {
      this.textModelSimiliarity = textModelSimiliarity;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public TextModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getTextDictionary(), this.getTextCorpus(), this.getDocumentTermMatrix(), this.getTextModelNormalization(), this.getTextModelSimiliarity(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
