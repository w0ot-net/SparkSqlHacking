package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InnerClassesAttribute extends Attribute {
   private static CPUTF8 attributeName;
   private final List innerClasses = new ArrayList();
   private final List nestedClassFileEntries = new ArrayList();

   public static void setAttributeName(CPUTF8 cpUTF8Value) {
      attributeName = cpUTF8Value;
   }

   public InnerClassesAttribute(String name) {
      super(attributeName);
      this.nestedClassFileEntries.add(this.getAttributeName());
   }

   public void addInnerClassesEntry(CPClass innerClass, CPClass outerClass, CPUTF8 innerName, int flags) {
      if (innerClass != null) {
         this.nestedClassFileEntries.add(innerClass);
      }

      if (outerClass != null) {
         this.nestedClassFileEntries.add(outerClass);
      }

      if (innerName != null) {
         this.nestedClassFileEntries.add(innerName);
      }

      this.addInnerClassesEntry(new InnerClassesEntry(innerClass, outerClass, innerName, flags));
   }

   private void addInnerClassesEntry(InnerClassesEntry innerClassesEntry) {
      this.innerClasses.add(innerClassesEntry);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         InnerClassesAttribute other = (InnerClassesAttribute)obj;
         if (this.getAttributeName() == null) {
            if (other.getAttributeName() != null) {
               return false;
            }
         } else if (!this.getAttributeName().equals(other.getAttributeName())) {
            return false;
         }

         return true;
      }
   }

   protected int getLength() {
      return 2 + 8 * this.innerClasses.size();
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      return (ClassFileEntry[])this.nestedClassFileEntries.toArray(NONE);
   }

   public int hashCode() {
      int PRIME = 31;
      int result = super.hashCode();
      result = 31 * result + (this.getAttributeName() == null ? 0 : this.getAttributeName().hashCode());
      return result;
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);

      for(InnerClassesEntry entry : this.innerClasses) {
         entry.resolve(pool);
      }

   }

   public String toString() {
      return "InnerClasses: " + this.getAttributeName();
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeShort(this.innerClasses.size());

      for(InnerClassesEntry entry : this.innerClasses) {
         entry.write(dos);
      }

   }

   private static final class InnerClassesEntry {
      CPClass innerClassInfo;
      CPClass outerClassInfo;
      CPUTF8 innerClassName;
      int innerClassInfoIndex = -1;
      int outerClassInfoIndex = -1;
      int innerNameIndex = -1;
      int innerClassAccessFlags = -1;

      InnerClassesEntry(CPClass innerClass, CPClass outerClass, CPUTF8 innerName, int flags) {
         this.innerClassInfo = innerClass;
         this.outerClassInfo = outerClass;
         this.innerClassName = innerName;
         this.innerClassAccessFlags = flags;
      }

      public void resolve(ClassConstantPool pool) {
         if (this.innerClassInfo != null) {
            this.innerClassInfo.resolve(pool);
            this.innerClassInfoIndex = pool.indexOf(this.innerClassInfo);
         } else {
            this.innerClassInfoIndex = 0;
         }

         if (this.innerClassName != null) {
            this.innerClassName.resolve(pool);
            this.innerNameIndex = pool.indexOf(this.innerClassName);
         } else {
            this.innerNameIndex = 0;
         }

         if (this.outerClassInfo != null) {
            this.outerClassInfo.resolve(pool);
            this.outerClassInfoIndex = pool.indexOf(this.outerClassInfo);
         } else {
            this.outerClassInfoIndex = 0;
         }

      }

      public void write(DataOutputStream dos) throws IOException {
         dos.writeShort(this.innerClassInfoIndex);
         dos.writeShort(this.outerClassInfoIndex);
         dos.writeShort(this.innerNameIndex);
         dos.writeShort(this.innerClassAccessFlags);
      }
   }
}
