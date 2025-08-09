package org.supercsv.util;

import java.io.Serializable;
import java.util.List;

public class CsvContext implements Serializable {
   private static final long serialVersionUID = 1L;
   private int lineNumber;
   private int rowNumber;
   private int columnNumber;
   private List rowSource;

   public CsvContext(int lineNumber, int rowNumber, int columnNumber) {
      this.lineNumber = lineNumber;
      this.rowNumber = rowNumber;
      this.columnNumber = columnNumber;
   }

   public int getLineNumber() {
      return this.lineNumber;
   }

   public void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
   }

   public int getRowNumber() {
      return this.rowNumber;
   }

   public void setRowNumber(int rowNumber) {
      this.rowNumber = rowNumber;
   }

   public int getColumnNumber() {
      return this.columnNumber;
   }

   public void setColumnNumber(int columnNumber) {
      this.columnNumber = columnNumber;
   }

   public List getRowSource() {
      return this.rowSource;
   }

   public void setRowSource(List rowSource) {
      this.rowSource = rowSource;
   }

   public String toString() {
      return String.format("{lineNo=%d, rowNo=%d, columnNo=%d, rowSource=%s}", this.lineNumber, this.rowNumber, this.columnNumber, this.rowSource);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.columnNumber;
      result = 31 * result + this.rowNumber;
      result = 31 * result + this.lineNumber;
      result = 31 * result + (this.rowSource == null ? 0 : this.rowSource.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         CsvContext other = (CsvContext)obj;
         if (this.columnNumber != other.columnNumber) {
            return false;
         } else if (this.rowNumber != other.rowNumber) {
            return false;
         } else if (this.lineNumber != other.lineNumber) {
            return false;
         } else {
            if (this.rowSource == null) {
               if (other.rowSource != null) {
                  return false;
               }
            } else if (!this.rowSource.equals(other.rowSource)) {
               return false;
            }

            return true;
         }
      }
   }
}
