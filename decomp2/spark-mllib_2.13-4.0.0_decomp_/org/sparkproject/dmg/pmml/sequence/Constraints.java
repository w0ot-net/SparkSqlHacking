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
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Constraints",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Constraints")
@JsonPropertyOrder({"minimumNumberOfItems", "maximumNumberOfItems", "minimumNumberOfAntecedentItems", "maximumNumberOfAntecedentItems", "minimumNumberOfConsequentItems", "maximumNumberOfConsequentItems", "minimumSupport", "minimumConfidence", "minimumLift", "minimumTotalSequenceTime", "maximumTotalSequenceTime", "minimumItemsetSeparationTime", "maximumItemsetSeparationTime", "minimumAntConsSeparationTime", "maximumAntConsSeparationTime", "extensions"})
@Added(Version.PMML_3_1)
public class Constraints extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "minimumNumberOfItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("minimumNumberOfItems")
   private Integer minimumNumberOfItems;
   @XmlAttribute(
      name = "maximumNumberOfItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maximumNumberOfItems")
   private Integer maximumNumberOfItems;
   @XmlAttribute(
      name = "minimumNumberOfAntecedentItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("minimumNumberOfAntecedentItems")
   private Integer minimumNumberOfAntecedentItems;
   @XmlAttribute(
      name = "maximumNumberOfAntecedentItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maximumNumberOfAntecedentItems")
   private Integer maximumNumberOfAntecedentItems;
   @XmlAttribute(
      name = "minimumNumberOfConsequentItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("minimumNumberOfConsequentItems")
   private Integer minimumNumberOfConsequentItems;
   @XmlAttribute(
      name = "maximumNumberOfConsequentItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("maximumNumberOfConsequentItems")
   private Integer maximumNumberOfConsequentItems;
   @XmlAttribute(
      name = "minimumSupport"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumSupport")
   private Number minimumSupport;
   @XmlAttribute(
      name = "minimumConfidence"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumConfidence")
   private Number minimumConfidence;
   @XmlAttribute(
      name = "minimumLift"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumLift")
   private Number minimumLift;
   @XmlAttribute(
      name = "minimumTotalSequenceTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumTotalSequenceTime")
   private Number minimumTotalSequenceTime;
   @XmlAttribute(
      name = "maximumTotalSequenceTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("maximumTotalSequenceTime")
   private Number maximumTotalSequenceTime;
   @XmlAttribute(
      name = "minimumItemsetSeparationTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumItemsetSeparationTime")
   private Number minimumItemsetSeparationTime;
   @XmlAttribute(
      name = "maximumItemsetSeparationTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("maximumItemsetSeparationTime")
   private Number maximumItemsetSeparationTime;
   @XmlAttribute(
      name = "minimumAntConsSeparationTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("minimumAntConsSeparationTime")
   private Number minimumAntConsSeparationTime;
   @XmlAttribute(
      name = "maximumAntConsSeparationTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("maximumAntConsSeparationTime")
   private Number maximumAntConsSeparationTime;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Integer DEFAULT_MINIMUM_NUMBER_OF_ITEMS = (new NonNegativeIntegerAdapter()).unmarshal("1");
   private static final Integer DEFAULT_MINIMUM_NUMBER_OF_ANTECEDENT_ITEMS = (new NonNegativeIntegerAdapter()).unmarshal("1");
   private static final Integer DEFAULT_MINIMUM_NUMBER_OF_CONSEQUENT_ITEMS = (new NonNegativeIntegerAdapter()).unmarshal("1");
   private static final Number DEFAULT_MINIMUM_SUPPORT = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_MINIMUM_CONFIDENCE = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_MINIMUM_LIFT = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_MINIMUM_TOTAL_SEQUENCE_TIME = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_MINIMUM_ITEMSET_SEPARATION_TIME = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_MINIMUM_ANT_CONS_SEPARATION_TIME = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public Integer getMinimumNumberOfItems() {
      return this.minimumNumberOfItems == null ? DEFAULT_MINIMUM_NUMBER_OF_ITEMS : this.minimumNumberOfItems;
   }

   public Constraints setMinimumNumberOfItems(@Property("minimumNumberOfItems") Integer minimumNumberOfItems) {
      this.minimumNumberOfItems = minimumNumberOfItems;
      return this;
   }

   public Integer getMaximumNumberOfItems() {
      return this.maximumNumberOfItems;
   }

   public Constraints setMaximumNumberOfItems(@Property("maximumNumberOfItems") Integer maximumNumberOfItems) {
      this.maximumNumberOfItems = maximumNumberOfItems;
      return this;
   }

   public Integer getMinimumNumberOfAntecedentItems() {
      return this.minimumNumberOfAntecedentItems == null ? DEFAULT_MINIMUM_NUMBER_OF_ANTECEDENT_ITEMS : this.minimumNumberOfAntecedentItems;
   }

   public Constraints setMinimumNumberOfAntecedentItems(@Property("minimumNumberOfAntecedentItems") Integer minimumNumberOfAntecedentItems) {
      this.minimumNumberOfAntecedentItems = minimumNumberOfAntecedentItems;
      return this;
   }

   public Integer getMaximumNumberOfAntecedentItems() {
      return this.maximumNumberOfAntecedentItems;
   }

   public Constraints setMaximumNumberOfAntecedentItems(@Property("maximumNumberOfAntecedentItems") Integer maximumNumberOfAntecedentItems) {
      this.maximumNumberOfAntecedentItems = maximumNumberOfAntecedentItems;
      return this;
   }

   public Integer getMinimumNumberOfConsequentItems() {
      return this.minimumNumberOfConsequentItems == null ? DEFAULT_MINIMUM_NUMBER_OF_CONSEQUENT_ITEMS : this.minimumNumberOfConsequentItems;
   }

   public Constraints setMinimumNumberOfConsequentItems(@Property("minimumNumberOfConsequentItems") Integer minimumNumberOfConsequentItems) {
      this.minimumNumberOfConsequentItems = minimumNumberOfConsequentItems;
      return this;
   }

   public Integer getMaximumNumberOfConsequentItems() {
      return this.maximumNumberOfConsequentItems;
   }

   public Constraints setMaximumNumberOfConsequentItems(@Property("maximumNumberOfConsequentItems") Integer maximumNumberOfConsequentItems) {
      this.maximumNumberOfConsequentItems = maximumNumberOfConsequentItems;
      return this;
   }

   public Number getMinimumSupport() {
      return this.minimumSupport == null ? DEFAULT_MINIMUM_SUPPORT : this.minimumSupport;
   }

   public Constraints setMinimumSupport(@Property("minimumSupport") Number minimumSupport) {
      this.minimumSupport = minimumSupport;
      return this;
   }

   public Number getMinimumConfidence() {
      return this.minimumConfidence == null ? DEFAULT_MINIMUM_CONFIDENCE : this.minimumConfidence;
   }

   public Constraints setMinimumConfidence(@Property("minimumConfidence") Number minimumConfidence) {
      this.minimumConfidence = minimumConfidence;
      return this;
   }

   public Number getMinimumLift() {
      return this.minimumLift == null ? DEFAULT_MINIMUM_LIFT : this.minimumLift;
   }

   public Constraints setMinimumLift(@Property("minimumLift") Number minimumLift) {
      this.minimumLift = minimumLift;
      return this;
   }

   public Number getMinimumTotalSequenceTime() {
      return this.minimumTotalSequenceTime == null ? DEFAULT_MINIMUM_TOTAL_SEQUENCE_TIME : this.minimumTotalSequenceTime;
   }

   public Constraints setMinimumTotalSequenceTime(@Property("minimumTotalSequenceTime") Number minimumTotalSequenceTime) {
      this.minimumTotalSequenceTime = minimumTotalSequenceTime;
      return this;
   }

   public Number getMaximumTotalSequenceTime() {
      return this.maximumTotalSequenceTime;
   }

   public Constraints setMaximumTotalSequenceTime(@Property("maximumTotalSequenceTime") Number maximumTotalSequenceTime) {
      this.maximumTotalSequenceTime = maximumTotalSequenceTime;
      return this;
   }

   public Number getMinimumItemsetSeparationTime() {
      return this.minimumItemsetSeparationTime == null ? DEFAULT_MINIMUM_ITEMSET_SEPARATION_TIME : this.minimumItemsetSeparationTime;
   }

   public Constraints setMinimumItemsetSeparationTime(@Property("minimumItemsetSeparationTime") Number minimumItemsetSeparationTime) {
      this.minimumItemsetSeparationTime = minimumItemsetSeparationTime;
      return this;
   }

   public Number getMaximumItemsetSeparationTime() {
      return this.maximumItemsetSeparationTime;
   }

   public Constraints setMaximumItemsetSeparationTime(@Property("maximumItemsetSeparationTime") Number maximumItemsetSeparationTime) {
      this.maximumItemsetSeparationTime = maximumItemsetSeparationTime;
      return this;
   }

   public Number getMinimumAntConsSeparationTime() {
      return this.minimumAntConsSeparationTime == null ? DEFAULT_MINIMUM_ANT_CONS_SEPARATION_TIME : this.minimumAntConsSeparationTime;
   }

   public Constraints setMinimumAntConsSeparationTime(@Property("minimumAntConsSeparationTime") Number minimumAntConsSeparationTime) {
      this.minimumAntConsSeparationTime = minimumAntConsSeparationTime;
      return this;
   }

   public Number getMaximumAntConsSeparationTime() {
      return this.maximumAntConsSeparationTime;
   }

   public Constraints setMaximumAntConsSeparationTime(@Property("maximumAntConsSeparationTime") Number maximumAntConsSeparationTime) {
      this.maximumAntConsSeparationTime = maximumAntConsSeparationTime;
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

   public Constraints addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
