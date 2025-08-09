package org.apache.ivy.core.module.descriptor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExtraInfoHolder {
   private String name;
   private Map attributes = new LinkedHashMap();
   private String content;
   private List nestedExtraInfoHolder = new ArrayList();

   public ExtraInfoHolder() {
   }

   public ExtraInfoHolder(String name, String content) {
      this.name = name;
      this.content = content;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public void setAttributes(Map attributes) {
      this.attributes = attributes;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public List getNestedExtraInfoHolder() {
      return this.nestedExtraInfoHolder;
   }

   public void setNestedExtraInfoHolder(List nestedExtraInfoHolder) {
      this.nestedExtraInfoHolder = nestedExtraInfoHolder;
   }
}
