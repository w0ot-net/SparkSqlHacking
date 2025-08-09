package org.apache.ws.commons.schema;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.ws.commons.schema.utils.XmlSchemaObjectBase;

public abstract class XmlSchemaObject implements XmlSchemaObjectBase {
   int lineNumber;
   int linePosition;
   String sourceURI;
   private Map metaInfoMap;

   protected XmlSchemaObject() {
   }

   public void addMetaInfo(Object key, Object value) {
      if (this.metaInfoMap == null) {
         this.metaInfoMap = new LinkedHashMap();
      }

      this.metaInfoMap.put(key, value);
   }

   public boolean equals(Object what) {
      if (what == this) {
         return true;
      } else if (!(what instanceof XmlSchemaObject)) {
         return false;
      } else {
         XmlSchemaObject xso = (XmlSchemaObject)what;
         if (this.lineNumber != xso.lineNumber) {
            return false;
         } else if (this.linePosition != xso.linePosition) {
            return false;
         } else {
            if (this.sourceURI != null) {
               if (!this.sourceURI.equals(xso.sourceURI)) {
                  return false;
               }
            } else if (xso.sourceURI != null) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      return super.hashCode();
   }

   public int getLineNumber() {
      return this.lineNumber;
   }

   public int getLinePosition() {
      return this.linePosition;
   }

   public Map getMetaInfoMap() {
      return this.metaInfoMap;
   }

   public String getSourceURI() {
      return this.sourceURI;
   }

   public void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
   }

   public void setLinePosition(int linePosition) {
      this.linePosition = linePosition;
   }

   public void setMetaInfoMap(Map metaInfoMap) {
      this.metaInfoMap = metaInfoMap;
   }

   public void setSourceURI(String sourceURI) {
      this.sourceURI = sourceURI;
   }
}
