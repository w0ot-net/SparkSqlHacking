package org.datanucleus.api.jdo;

import javax.jdo.datastore.Sequence;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.NucleusSequence;

public class JDOSequence implements Sequence, NucleusSequence {
   protected NucleusSequence sequence;

   public JDOSequence(NucleusSequence seq) {
      this.sequence = seq;
   }

   public String getName() {
      return this.sequence.getName();
   }

   public void allocate(int additional) {
      this.sequence.allocate(additional);
   }

   public Object next() {
      try {
         return this.sequence.next();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public long nextValue() {
      try {
         return this.sequence.nextValue();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Object current() {
      try {
         return this.sequence.current();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public long currentValue() {
      try {
         return this.sequence.currentValue();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }
}
