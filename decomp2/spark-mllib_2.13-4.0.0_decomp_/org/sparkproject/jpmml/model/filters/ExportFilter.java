package org.sparkproject.jpmml.model.filters;

import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.VersionUtil;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

public class ExportFilter extends PMMLFilter {
   public ExportFilter(Version target) {
      super(target);
   }

   public ExportFilter(XMLReader reader, Version target) {
      super(reader, target);
   }

   public String filterLocalName(String localName) {
      Version target = this.getTarget();
      return "Trend_ExpoSmooth".equals(localName) && target.compareTo(Version.PMML_4_0) == 0 ? "Trend" : localName;
   }

   public Attributes filterAttributes(String localName, Attributes attributes) {
      Version target = this.getTarget();
      if ("Apply".equals(localName)) {
         if (target.compareTo(Version.PMML_4_1) == 0 && hasAttribute(attributes, "defaultValue")) {
            if (hasAttribute(attributes, "mapMissingTo")) {
               throw new IllegalStateException();
            }

            attributes = renameAttribute(attributes, "defaultValue", "mapMissingTo");
         }

         if (target.compareTo(Version.PMML_4_4) < 0) {
            String function = getAttribute(attributes, "function");
            Version functionVersion = VersionUtil.getVersion(function);
            if (functionVersion != null && functionVersion.compareTo(target) > 0) {
               attributes = setAttribute(attributes, "function", "x-" + function);
            }
         }
      } else if ("MiningField".equals(localName)) {
         if (target.compareTo(Version.PMML_4_3) <= 0) {
            String missingValueTreatment = getAttribute(attributes, "missingValueTreatment");
            String invalidValueTreatment = getAttribute(attributes, "invalidValueTreatment");
            attributes = renameAttribute(attributes, "invalidValueReplacement", "x-invalidValueReplacement");
            if (missingValueTreatment != null) {
               switch (missingValueTreatment) {
                  case "returnInvalid":
                     attributes = setAttribute(attributes, "missingValueTreatment", "x-" + missingValueTreatment);
               }
            }

            if (invalidValueTreatment != null) {
               switch (invalidValueTreatment) {
                  case "asValue":
                     attributes = setAttribute(attributes, "invalidValueTreatment", "asIs");
               }
            }
         }
      } else if ("PMML".equals(localName)) {
         if (hasAttribute(attributes, "x-baseVersion")) {
            attributes = removeAttribute(attributes, "x-baseVersion");
         }

         attributes = setAttribute(attributes, "version", target.getVersion());
      } else if ("Segmentation".equals(localName)) {
         if (target.compareTo(Version.PMML_4_3) <= 0) {
            String multipleModelMethod = getAttribute(attributes, "multipleModelMethod");
            attributes = renameAttribute(attributes, "missingPredictionTreatment", "x-missingPredictionTreatment");
            attributes = renameAttribute(attributes, "missingThreshold", "x-missingThreshold");
            if (multipleModelMethod != null) {
               switch (multipleModelMethod) {
                  case "weightedMedian":
                  case "weightedSum":
                     attributes = setAttribute(attributes, "multipleModelMethod", "x-" + multipleModelMethod);
               }
            }
         }
      } else if ("TargetValue".equals(localName) && target.compareTo(Version.PMML_3_1) <= 0 && hasAttribute(attributes, "displayValue")) {
         if (hasAttribute(attributes, "rawDataValue")) {
            throw new IllegalStateException();
         }

         attributes = renameAttribute(attributes, "displayValue", "rawDataValue");
      }

      return attributes;
   }
}
