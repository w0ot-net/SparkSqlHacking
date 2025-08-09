package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TextIndexNormalization",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "tableLocator", "inlineTable"}
)
@JsonRootName("TextIndexNormalization")
@JsonPropertyOrder({"inField", "outField", "regexField", "recursive", "caseSensitive", "maxLevenshteinDistance", "wordSeparatorCharacterRE", "wordRE", "tokenize", "extensions", "tableLocator", "inlineTable"})
@Added(Version.PMML_4_2)
public class TextIndexNormalization extends PMMLObject implements HasExtensions, HasTable {
   @XmlAttribute(
      name = "inField"
   )
   @JsonProperty("inField")
   private String inField;
   @XmlAttribute(
      name = "outField"
   )
   @JsonProperty("outField")
   private String outField;
   @XmlAttribute(
      name = "regexField"
   )
   @JsonProperty("regexField")
   private String regexField;
   @XmlAttribute(
      name = "recursive"
   )
   @JsonProperty("recursive")
   private Boolean recursive;
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
      name = "TableLocator",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TableLocator")
   private TableLocator tableLocator;
   @XmlElement(
      name = "InlineTable",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("InlineTable")
   private InlineTable inlineTable;
   private static final Boolean DEFAULT_RECURSIVE = false;
   private static final long serialVersionUID = 67371272L;

   public TextIndexNormalization() {
   }

   @ValueConstructor
   public TextIndexNormalization(@Property("inlineTable") InlineTable inlineTable) {
      this.inlineTable = inlineTable;
   }

   public String getInField() {
      return this.inField == null ? "string" : this.inField;
   }

   public TextIndexNormalization setInField(@Property("inField") String inField) {
      this.inField = inField;
      return this;
   }

   public String getOutField() {
      return this.outField == null ? "stem" : this.outField;
   }

   public TextIndexNormalization setOutField(@Property("outField") String outField) {
      this.outField = outField;
      return this;
   }

   public String getRegexField() {
      return this.regexField == null ? "regex" : this.regexField;
   }

   public TextIndexNormalization setRegexField(@Property("regexField") String regexField) {
      this.regexField = regexField;
      return this;
   }

   public boolean isRecursive() {
      return this.recursive == null ? DEFAULT_RECURSIVE : this.recursive;
   }

   public TextIndexNormalization setRecursive(@Property("recursive") Boolean recursive) {
      this.recursive = recursive;
      return this;
   }

   public Boolean isCaseSensitive() {
      return this.caseSensitive;
   }

   public TextIndexNormalization setCaseSensitive(@Property("caseSensitive") Boolean caseSensitive) {
      this.caseSensitive = caseSensitive;
      return this;
   }

   public Integer getMaxLevenshteinDistance() {
      return this.maxLevenshteinDistance;
   }

   public TextIndexNormalization setMaxLevenshteinDistance(@Property("maxLevenshteinDistance") Integer maxLevenshteinDistance) {
      this.maxLevenshteinDistance = maxLevenshteinDistance;
      return this;
   }

   public String getWordSeparatorCharacterRE() {
      return this.wordSeparatorCharacterRE;
   }

   public TextIndexNormalization setWordSeparatorCharacterRE(@Property("wordSeparatorCharacterRE") String wordSeparatorCharacterRE) {
      this.wordSeparatorCharacterRE = wordSeparatorCharacterRE;
      return this;
   }

   public String getWordRE() {
      return this.wordRE;
   }

   public TextIndexNormalization setWordRE(@Property("wordRE") String wordRE) {
      this.wordRE = wordRE;
      return this;
   }

   public Boolean isTokenize() {
      return this.tokenize;
   }

   public TextIndexNormalization setTokenize(@Property("tokenize") Boolean tokenize) {
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

   public TextIndexNormalization addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public TableLocator getTableLocator() {
      return this.tableLocator;
   }

   public TextIndexNormalization setTableLocator(@Property("tableLocator") TableLocator tableLocator) {
      this.tableLocator = tableLocator;
      return this;
   }

   public InlineTable requireInlineTable() {
      if (this.inlineTable == null) {
         throw new MissingElementException(this, PMMLElements.TEXTINDEXNORMALIZATION_INLINETABLE);
      } else {
         return this.inlineTable;
      }
   }

   public InlineTable getInlineTable() {
      return this.inlineTable;
   }

   public TextIndexNormalization setInlineTable(@Property("inlineTable") InlineTable inlineTable) {
      this.inlineTable = inlineTable;
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
            status = PMMLObject.traverse(visitor, this.getTableLocator(), this.getInlineTable());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
