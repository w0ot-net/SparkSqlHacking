package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CPMember extends ClassFileEntry {
   List attributes;
   short flags;
   CPUTF8 name;
   transient int nameIndex;
   protected final CPUTF8 descriptor;
   transient int descriptorIndex;

   public CPMember(CPUTF8 name, CPUTF8 descriptor, long flags, List attributes) {
      this.name = (CPUTF8)Objects.requireNonNull(name, "name");
      this.descriptor = (CPUTF8)Objects.requireNonNull(descriptor, "descriptor");
      this.flags = (short)((int)flags);
      this.attributes = attributes == null ? Collections.EMPTY_LIST : attributes;
   }

   protected void doWrite(DataOutputStream dos) throws IOException {
      dos.writeShort(this.flags);
      dos.writeShort(this.nameIndex);
      dos.writeShort(this.descriptorIndex);
      int attributeCount = this.attributes.size();
      dos.writeShort(attributeCount);

      for(int i = 0; i < attributeCount; ++i) {
         Attribute attribute = (Attribute)this.attributes.get(i);
         attribute.doWrite(dos);
      }

   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         CPMember other = (CPMember)obj;
         return Objects.equals(this.attributes, other.attributes) && Objects.equals(this.descriptor, other.descriptor) && this.flags == other.flags && Objects.equals(this.name, other.name);
      }
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      int attributeCount = this.attributes.size();
      ClassFileEntry[] entries = new ClassFileEntry[attributeCount + 2];
      entries[0] = this.name;
      entries[1] = this.descriptor;

      for(int i = 0; i < attributeCount; ++i) {
         entries[i + 2] = (ClassFileEntry)this.attributes.get(i);
      }

      return entries;
   }

   public int hashCode() {
      int PRIME = 31;
      int result = 1;
      result = 31 * result + this.attributes.hashCode();
      result = 31 * result + this.descriptor.hashCode();
      result = 31 * result + this.flags;
      result = 31 * result + this.name.hashCode();
      return result;
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.nameIndex = pool.indexOf(this.name);
      this.descriptorIndex = pool.indexOf(this.descriptor);
      this.attributes.forEach((attribute) -> attribute.resolve(pool));
   }

   public String toString() {
      return "CPMember: " + this.name + "(" + this.descriptor + ")";
   }
}
