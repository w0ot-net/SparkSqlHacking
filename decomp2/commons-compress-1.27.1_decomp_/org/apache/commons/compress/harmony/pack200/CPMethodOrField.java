package org.apache.commons.compress.harmony.pack200;

public class CPMethodOrField extends ConstantPoolEntry implements Comparable {
   private final CPClass className;
   private final CPNameAndType nameAndType;
   private int indexInClass = -1;
   private int indexInClassForConstructor = -1;

   public CPMethodOrField(CPClass className, CPNameAndType nameAndType) {
      this.className = className;
      this.nameAndType = nameAndType;
   }

   public int compareTo(Object obj) {
      if (obj instanceof CPMethodOrField) {
         CPMethodOrField mof = (CPMethodOrField)obj;
         int compareName = this.className.compareTo(mof.className);
         return compareName == 0 ? this.nameAndType.compareTo(mof.nameAndType) : compareName;
      } else {
         return 0;
      }
   }

   public int getClassIndex() {
      return this.className.getIndex();
   }

   public CPClass getClassName() {
      return this.className;
   }

   public CPNameAndType getDesc() {
      return this.nameAndType;
   }

   public int getDescIndex() {
      return this.nameAndType.getIndex();
   }

   public int getIndexInClass() {
      return this.indexInClass;
   }

   public int getIndexInClassForConstructor() {
      return this.indexInClassForConstructor;
   }

   public void setIndexInClass(int index) {
      this.indexInClass = index;
   }

   public void setIndexInClassForConstructor(int index) {
      this.indexInClassForConstructor = index;
   }

   public String toString() {
      return this.className + ": " + this.nameAndType;
   }
}
