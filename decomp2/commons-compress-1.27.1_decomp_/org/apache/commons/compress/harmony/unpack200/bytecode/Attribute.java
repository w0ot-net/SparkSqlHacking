package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public abstract class Attribute extends ClassFileEntry {
   protected final CPUTF8 attributeName;
   private int attributeNameIndex;

   public Attribute(CPUTF8 attributeName) {
      this.attributeName = attributeName;
   }

   protected void doWrite(DataOutputStream dos) throws IOException {
      dos.writeShort(this.attributeNameIndex);
      dos.writeInt(this.getLength());
      this.writeBody(dos);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Attribute other = (Attribute)obj;
         return Objects.equals(this.attributeName, other.attributeName);
      }
   }

   protected CPUTF8 getAttributeName() {
      return this.attributeName;
   }

   protected abstract int getLength();

   protected int getLengthIncludingHeader() {
      return this.getLength() + 2 + 4;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      return new ClassFileEntry[]{this.getAttributeName()};
   }

   public boolean hasBCIRenumbering() {
      return false;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.attributeName});
   }

   public boolean isSourceFileAttribute() {
      return false;
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.attributeNameIndex = pool.indexOf(this.attributeName);
   }

   protected abstract void writeBody(DataOutputStream var1) throws IOException;
}
