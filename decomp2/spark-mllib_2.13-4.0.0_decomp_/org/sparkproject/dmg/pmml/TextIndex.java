package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TextIndex",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "textIndexNormalizations", "expression"}
)
@JsonRootName("TextIndex")
@JsonPropertyOrder({"textField", "localTermWeights", "caseSensitive", "maxLevenshteinDistance", "countHits", "wordSeparatorCharacterRE", "wordRE", "tokenize", "extensions", "textIndexNormalizations", "expression"})
@Added(Version.PMML_4_2)
public class TextIndex extends Expression implements HasExpression, HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "textField",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("textField")
   private String textField;
   @XmlAttribute(
      name = "localTermWeights"
   )
   @JsonProperty("localTermWeights")
   private LocalTermWeights localTermWeights;
   @XmlAttribute(
      name = "isCaseSensitive"
   )
   @JsonProperty("isCaseSensitive")
   private Boolean caseSensitive;
   @XmlAttribute(
      name = "maxLevenshteinDistance"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("maxLevenshteinDistance")
   private Integer maxLevenshteinDistance;
   @XmlAttribute(
      name = "countHits"
   )
   @JsonProperty("countHits")
   private CountHits countHits;
   @XmlAttribute(
      name = "wordSeparatorCharacterRE"
   )
   @JsonProperty("wordSeparatorCharacterRE")
   private String wordSeparatorCharacterRE;
   @XmlAttribute(
      name = "x-wordRE"
   )
   @JsonProperty("x-wordRE")
   @Added(Version.XPMML)
   @Since("1.5.10")
   private String wordRE;
   @XmlAttribute(
      name = "tokenize"
   )
   @JsonProperty("tokenize")
   private Boolean tokenize;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TextIndexNormalization",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TextIndexNormalization")
   @CollectionElementType(TextIndexNormalization.class)
   private List textIndexNormalizations;
   @XmlElements({@XmlElement(
   name = "Constant",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Constant.class
), @XmlElement(
   name = "FieldRef",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = FieldRef.class
), @XmlElement(
   name = "NormContinuous",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NormContinuous.class
), @XmlElement(
   name = "NormDiscrete",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NormDiscrete.class
), @XmlElement(
   name = "Discretize",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Discretize.class
), @XmlElement(
   name = "MapValues",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = MapValues.class
), @XmlElement(
   name = "TextIndex",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = TextIndex.class
), @XmlElement(
   name = "Apply",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Apply.class
), @XmlElement(
   name = "Aggregate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Aggregate.class
), @XmlElement(
   name = "Lag",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Lag.class
)})
   @JsonProperty("Expression")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "Constant",
   value = Constant.class
), @Type(
   name = "FieldRef",
   value = FieldRef.class
), @Type(
   name = "NormContinuous",
   value = NormContinuous.class
), @Type(
   name = "NormDiscrete",
   value = NormDiscrete.class
), @Type(
   name = "Discretize",
   value = Discretize.class
), @Type(
   name = "MapValues",
   value = MapValues.class
), @Type(
   name = "TextIndex",
   value = TextIndex.class
), @Type(
   name = "Apply",
   value = Apply.class
), @Type(
   name = "Aggregate",
   value = Aggregate.class
), @Type(
   name = "Lag",
   value = Lag.class
)})
   private Expression expression;
   private static final Boolean DEFAULT_CASE_SENSITIVE = false;
   private static final Integer DEFAULT_MAX_LEVENSHTEIN_DISTANCE = (new IntegerAdapter()).unmarshal("0");
   private static final Boolean DEFAULT_TOKENIZE = true;
   private static final long serialVersionUID = 67371272L;

   public TextIndex() {
   }

   @ValueConstructor
   public TextIndex(@Property("textField") String textField, @Property("expression") Expression expression) {
      this.textField = textField;
      this.expression = expression;
   }

   @AlternateValueConstructor
   public TextIndex(Field textField, Expression expression) {
      this(textField != null ? textField.requireName() : null, expression);
   }

   public String requireField() {
      return this.requireTextField();
   }

   public String requireTextField() {
      if (this.textField == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TEXTINDEX_TEXTFIELD);
      } else {
         return this.textField;
      }
   }

   public String getField() {
      return this.getTextField();
   }

   public TextIndex setField(String field) {
      return this.setTextField(field);
   }

   public String getTextField() {
      return this.textField;
   }

   public TextIndex setTextField(@Property("textField") String textField) {
      this.textField = textField;
      return this;
   }

   public LocalTermWeights getLocalTermWeights() {
      return this.localTermWeights == null ? TextIndex.LocalTermWeights.TERM_FREQUENCY : this.localTermWeights;
   }

   public TextIndex setLocalTermWeights(@Property("localTermWeights") LocalTermWeights localTermWeights) {
      this.localTermWeights = localTermWeights;
      return this;
   }

   public boolean isCaseSensitive() {
      return this.caseSensitive == null ? DEFAULT_CASE_SENSITIVE : this.caseSensitive;
   }

   public TextIndex setCaseSensitive(@Property("caseSensitive") Boolean caseSensitive) {
      this.caseSensitive = caseSensitive;
      return this;
   }

   public Integer getMaxLevenshteinDistance() {
      return this.maxLevenshteinDistance == null ? DEFAULT_MAX_LEVENSHTEIN_DISTANCE : this.maxLevenshteinDistance;
   }

   public TextIndex setMaxLevenshteinDistance(@Property("maxLevenshteinDistance") Integer maxLevenshteinDistance) {
      this.maxLevenshteinDistance = maxLevenshteinDistance;
      return this;
   }

   public CountHits getCountHits() {
      return this.countHits == null ? TextIndex.CountHits.ALL_HITS : this.countHits;
   }

   public TextIndex setCountHits(@Property("countHits") CountHits countHits) {
      this.countHits = countHits;
      return this;
   }

   public String getWordSeparatorCharacterRE() {
      return this.wordSeparatorCharacterRE == null ? "\\s+" : this.wordSeparatorCharacterRE;
   }

   public TextIndex setWordSeparatorCharacterRE(@Property("wordSeparatorCharacterRE") String wordSeparatorCharacterRE) {
      this.wordSeparatorCharacterRE = wordSeparatorCharacterRE;
      return this;
   }

   public String getWordRE() {
      return this.wordRE;
   }

   public TextIndex setWordRE(@Property("wordRE") String wordRE) {
      this.wordRE = wordRE;
      return this;
   }

   public boolean isTokenize() {
      return this.tokenize == null ? DEFAULT_TOKENIZE : this.tokenize;
   }

   public TextIndex setTokenize(@Property("tokenize") Boolean tokenize) {
      this.tokenize = tokenize;
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

   public TextIndex addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasTextIndexNormalizations() {
      return this.textIndexNormalizations != null && !this.textIndexNormalizations.isEmpty();
   }

   public List getTextIndexNormalizations() {
      if (this.textIndexNormalizations == null) {
         this.textIndexNormalizations = new ArrayList();
      }

      return this.textIndexNormalizations;
   }

   public TextIndex addTextIndexNormalizations(TextIndexNormalization... textIndexNormalizations) {
      this.getTextIndexNormalizations().addAll(Arrays.asList(textIndexNormalizations));
      return this;
   }

   public Expression requireExpression() {
      if (this.expression == null) {
         throw new MissingElementException(this, PMMLElements.TEXTINDEX_EXPRESSION);
      } else {
         return this.expression;
      }
   }

   public Expression getExpression() {
      return this.expression;
   }

   public TextIndex setExpression(@Property("expression") Expression expression) {
      this.expression = expression;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasTextIndexNormalizations()) {
            status = PMMLObject.traverse(visitor, this.getTextIndexNormalizations());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getExpression());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum CountHits implements StringValue {
      @XmlEnumValue("allHits")
      @JsonProperty("allHits")
      ALL_HITS("allHits"),
      @XmlEnumValue("bestHits")
      @JsonProperty("bestHits")
      BEST_HITS("bestHits");

      private final String value;

      private CountHits(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static CountHits fromValue(String v) {
         for(CountHits c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum LocalTermWeights implements StringValue {
      @XmlEnumValue("termFrequency")
      @JsonProperty("termFrequency")
      TERM_FREQUENCY("termFrequency"),
      @XmlEnumValue("binary")
      @JsonProperty("binary")
      BINARY("binary"),
      @XmlEnumValue("logarithmic")
      @JsonProperty("logarithmic")
      LOGARITHMIC("logarithmic"),
      @XmlEnumValue("augmentedNormalizedTermFrequency")
      @JsonProperty("augmentedNormalizedTermFrequency")
      AUGMENTED_NORMALIZED_TERM_FREQUENCY("augmentedNormalizedTermFrequency");

      private final String value;

      private LocalTermWeights(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static LocalTermWeights fromValue(String v) {
         for(LocalTermWeights c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
