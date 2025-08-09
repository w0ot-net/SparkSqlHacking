package org.sparkproject.dmg.pmml.baseline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TestDistributions",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "baseline", "alternate"}
)
@JsonRootName("TestDistributions")
@JsonPropertyOrder({"field", "testStatistic", "resetValue", "windowSize", "weightField", "normalizationScheme", "extensions", "baseline", "alternate"})
@Added(Version.PMML_4_1)
public class TestDistributions extends PMMLObject implements HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "testStatistic",
      required = true
   )
   @JsonProperty("testStatistic")
   private TestStatistic testStatistic;
   @XmlAttribute(
      name = "resetValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("resetValue")
   private Number resetValue;
   @XmlAttribute(
      name = "windowSize"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("windowSize")
   private Integer windowSize;
   @XmlAttribute(
      name = "weightField"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("weightField")
   private String weightField;
   @XmlAttribute(
      name = "normalizationScheme"
   )
   @JsonProperty("normalizationScheme")
   private String normalizationScheme;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Baseline",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Baseline")
   private Baseline baseline;
   @XmlElement(
      name = "Alternate",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Alternate")
   private Alternate alternate;
   private static final Number DEFAULT_RESET_VALUE = (new RealNumberAdapter()).unmarshal("0.0");
   private static final Integer DEFAULT_WINDOW_SIZE = (new IntegerAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public TestDistributions() {
   }

   @ValueConstructor
   public TestDistributions(@Property("field") String field, @Property("testStatistic") TestStatistic testStatistic, @Property("baseline") Baseline baseline) {
      this.field = field;
      this.testStatistic = testStatistic;
      this.baseline = baseline;
   }

   @AlternateValueConstructor
   public TestDistributions(Field field, TestStatistic testStatistic, Baseline baseline) {
      this(field != null ? field.requireName() : null, testStatistic, baseline);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TESTDISTRIBUTIONS_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public TestDistributions setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public TestStatistic requireTestStatistic() {
      if (this.testStatistic == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TESTDISTRIBUTIONS_TESTSTATISTIC);
      } else {
         return this.testStatistic;
      }
   }

   public TestStatistic getTestStatistic() {
      return this.testStatistic;
   }

   public TestDistributions setTestStatistic(@Property("testStatistic") TestStatistic testStatistic) {
      this.testStatistic = testStatistic;
      return this;
   }

   public Number getResetValue() {
      return this.resetValue == null ? DEFAULT_RESET_VALUE : this.resetValue;
   }

   public TestDistributions setResetValue(@Property("resetValue") Number resetValue) {
      this.resetValue = resetValue;
      return this;
   }

   public Integer getWindowSize() {
      return this.windowSize == null ? DEFAULT_WINDOW_SIZE : this.windowSize;
   }

   public TestDistributions setWindowSize(@Property("windowSize") Integer windowSize) {
      this.windowSize = windowSize;
      return this;
   }

   public String getWeightField() {
      return this.weightField;
   }

   public TestDistributions setWeightField(@Property("weightField") String weightField) {
      this.weightField = weightField;
      return this;
   }

   public String getNormalizationScheme() {
      return this.normalizationScheme;
   }

   public TestDistributions setNormalizationScheme(@Property("normalizationScheme") String normalizationScheme) {
      this.normalizationScheme = normalizationScheme;
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

   public TestDistributions addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Baseline requireBaseline() {
      if (this.baseline == null) {
         throw new MissingElementException(this, PMMLElements.TESTDISTRIBUTIONS_BASELINE);
      } else {
         return this.baseline;
      }
   }

   public Baseline getBaseline() {
      return this.baseline;
   }

   public TestDistributions setBaseline(@Property("baseline") Baseline baseline) {
      this.baseline = baseline;
      return this;
   }

   public Alternate getAlternate() {
      return this.alternate;
   }

   public TestDistributions setAlternate(@Property("alternate") Alternate alternate) {
      this.alternate = alternate;
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
            status = PMMLObject.traverse(visitor, this.getBaseline(), this.getAlternate());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum TestStatistic implements StringValue {
      @XmlEnumValue("zValue")
      @JsonProperty("zValue")
      Z_VALUE("zValue"),
      @XmlEnumValue("chiSquareIndependence")
      @JsonProperty("chiSquareIndependence")
      CHI_SQUARE_INDEPENDENCE("chiSquareIndependence"),
      @XmlEnumValue("chiSquareDistribution")
      @JsonProperty("chiSquareDistribution")
      CHI_SQUARE_DISTRIBUTION("chiSquareDistribution"),
      @JsonProperty("CUSUM")
      CUSUM("CUSUM"),
      @XmlEnumValue("scalarProduct")
      @JsonProperty("scalarProduct")
      SCALAR_PRODUCT("scalarProduct");

      private final String value;

      private TestStatistic(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static TestStatistic fromValue(String v) {
         for(TestStatistic c : values()) {
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
