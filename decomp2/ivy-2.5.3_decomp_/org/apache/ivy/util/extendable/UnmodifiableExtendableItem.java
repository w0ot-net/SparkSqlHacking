package org.apache.ivy.util.extendable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableExtendableItem implements ExtendableItem {
   private final Map attributes = new HashMap();
   private final Map unmodifiableAttributesView;
   private final Map extraAttributes;
   private final Map unmodifiableExtraAttributesView;
   private final Map qualifiedExtraAttributes;
   private final Map unmodifiableQualifiedExtraAttributesView;

   public UnmodifiableExtendableItem(Map stdAttributes, Map extraAttributes) {
      this.unmodifiableAttributesView = Collections.unmodifiableMap(this.attributes);
      this.extraAttributes = new HashMap();
      this.unmodifiableExtraAttributesView = Collections.unmodifiableMap(this.extraAttributes);
      this.qualifiedExtraAttributes = new HashMap();
      this.unmodifiableQualifiedExtraAttributesView = Collections.unmodifiableMap(this.qualifiedExtraAttributes);
      if (stdAttributes != null) {
         this.attributes.putAll(stdAttributes);
      }

      if (extraAttributes != null) {
         for(Map.Entry extraAtt : extraAttributes.entrySet()) {
            this.setExtraAttribute((String)extraAtt.getKey(), (String)extraAtt.getValue());
         }
      }

   }

   public String getAttribute(String attName) {
      return (String)this.attributes.get(attName);
   }

   public String getExtraAttribute(String attName) {
      String v = (String)this.qualifiedExtraAttributes.get(attName);
      if (v == null) {
         v = (String)this.extraAttributes.get(attName);
      }

      return v;
   }

   protected void setExtraAttribute(String attName, String attValue) {
      this.qualifiedExtraAttributes.put(attName, attValue);
      int index = attName.indexOf(58);
      if (index != -1) {
         attName = attName.substring(index + 1);
      }

      this.extraAttributes.put(attName, attValue);
      this.attributes.put(attName, attValue);
   }

   protected void setStandardAttribute(String attName, String attValue) {
      this.attributes.put(attName, attValue);
   }

   public Map getAttributes() {
      return this.unmodifiableAttributesView;
   }

   public Map getExtraAttributes() {
      return this.unmodifiableExtraAttributesView;
   }

   public Map getQualifiedExtraAttributes() {
      return this.unmodifiableQualifiedExtraAttributesView;
   }
}
