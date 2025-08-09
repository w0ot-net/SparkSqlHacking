package org.sparkproject.jpmml.model.visitors;

import java.io.Serializable;
import org.xml.sax.Locator;

class SimpleLocator implements Locator, Serializable {
   private String publicId = null;
   private String systemId = null;
   private int lineNumber = -1;
   private int columnNumber = -1;

   private SimpleLocator() {
   }

   public SimpleLocator(Locator locator) {
      this.setPublicId(locator.getPublicId());
      this.setSystemId(locator.getSystemId());
      this.setLineNumber(locator.getLineNumber());
      this.setColumnNumber(locator.getColumnNumber());
   }

   public String getPublicId() {
      return this.publicId;
   }

   private void setPublicId(String publicId) {
      this.publicId = publicId;
   }

   public String getSystemId() {
      return this.systemId;
   }

   private void setSystemId(String systemId) {
      this.systemId = systemId;
   }

   public int getLineNumber() {
      return this.lineNumber;
   }

   private void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
   }

   public int getColumnNumber() {
      return this.columnNumber;
   }

   private void setColumnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
   }
}
