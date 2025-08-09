package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class SourceFileAttribute extends Attribute {
   private static CPUTF8 attributeName;
   private final CPUTF8 name;
   private int nameIndex;

   public static void setAttributeName(CPUTF8 cpUTF8Value) {
      attributeName = cpUTF8Value;
   }

   public SourceFileAttribute(CPUTF8 name) {
      super(attributeName);
      this.name = name;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         SourceFileAttribute other = (SourceFileAttribute)obj;
         return Objects.equals(this.name, other.name);
      }
   }

   protected int getLength() {
      return 2;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      return new ClassFileEntry[]{this.getAttributeName(), this.name};
   }

   public int hashCode() {
      int PRIME = 31;
      int result = super.hashCode();
      result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
      return result;
   }

   public boolean isSourceFileAttribute() {
      return true;
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.nameIndex = pool.indexOf(this.name);
   }

   public String toString() {
      return "SourceFile: " + this.name;
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeShort(this.nameIndex);
   }
}
