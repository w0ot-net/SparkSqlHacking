package org.sparkproject.jpmml.model.filters;

import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.VersionUtil;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

public class ImportFilter extends PMMLFilter {
   private boolean extensions;

   public ImportFilter() {
      this(true);
   }

   public ImportFilter(boolean extensions) {
      super(Version.PMML_4_4);
      this.extensions = true;
      this.setExtensions(extensions);
   }

   public ImportFilter(XMLReader reader) {
      this(reader, true);
   }

   public ImportFilter(XMLReader reader, boolean extensions) {
      super(reader, Version.PMML_4_4);
      this.extensions = true;
      this.setExtensions(extensions);
   }

   public String filterLocalName(String localName) {
      Version source = this.getSource();
      return "Trend".equals(localName) && source.compareTo(Version.PMML_4_0) == 0 ? "Trend_ExpoSmooth" : localName;
   }

   public Attributes filterAttributes(String localName, Attributes attributes) {
      Version source = this.getSource();
      if ("Apply".equals(localName)) {
         if (source.compareTo(Version.PMML_4_1) == 0) {
            attributes = renameAttribute(attributes, "mapMissingTo", "defaultValue");
         }

         if (source.compareTo(Version.PMML_4_4) <= 0) {
            String function = getAttribute(attributes, "function");
            if (function != null && function.startsWith("x-")) {
               Version functionVersion = VersionUtil.getVersion(function.substring("x-".length()));
               if (functionVersion != null && functionVersion.compareTo(Version.PMML_4_4) <= 0) {
                  attributes = setAttribute(attributes, "function", function.substring("x-".length()));
               }
            }
         }
      } else if ("MiningField".equals(localName)) {
         if (source.compareTo(Version.PMML_4_3) <= 0) {
            attributes = renameAttribute(attributes, "x-invalidValueReplacement", "invalidValueReplacement");
         }

         if (source.compareTo(Version.PMML_4_4) <= 0) {
            String missingValueTreatment = getAttribute(attributes, "missingValueTreatment");
            String invalidValueTreatment = getAttribute(attributes, "invalidValueTreatment");
            if (missingValueTreatment != null) {
               switch (missingValueTreatment) {
                  case "x-returnInvalid":
                     attributes = setAttribute(attributes, "missingValueTreatment", missingValueTreatment.substring("x-".length()));
               }
            }

            if (invalidValueTreatment != null) {
               switch (invalidValueTreatment) {
                  case "asIs":
                     attributes = setAttribute(attributes, "invalidValueTreatment", "asValue");
                  case "asValue":
               }
            }
         }
      } else if ("PMML".equals(localName)) {
         Version target = this.getTarget();
         if (this.getExtensions()) {
            attributes = renameAttribute(attributes, "version", "x-baseVersion");
         }

         attributes = setAttribute(attributes, "version", target.getVersion());
      } else if ("Segmentation".equals(localName)) {
         if (source.compareTo(Version.PMML_4_3) <= 0) {
            String multipleModelMethod = getAttribute(attributes, "multipleModelMethod");
            if (multipleModelMethod != null) {
               switch (multipleModelMethod) {
                  case "x-weightedMedian":
                  case "x-weightedSum":
                     attributes = setAttribute(attributes, "multipleModelMethod", multipleModelMethod.substring("x-".length()));
               }
            }

            attributes = renameAttribute(attributes, "x-missingPredictionTreatment", "missingPredictionTreatment");
            attributes = renameAttribute(attributes, "x-missingThreshold", "missingThreshold");
         }
      } else if ("TargetValue".equals(localName) && source.compareTo(Version.PMML_3_1) <= 0) {
         attributes = renameAttribute(attributes, "rawDataValue", "displayValue");
      }

      return attributes;
   }

   public boolean getExtensions() {
      return this.extensions;
   }

   private void setExtensions(boolean extensions) {
      this.extensions = extensions;
   }
}
