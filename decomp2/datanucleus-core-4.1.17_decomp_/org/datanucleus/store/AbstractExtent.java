package org.datanucleus.store;

import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.Localiser;

public abstract class AbstractExtent implements Extent {
   protected final ExecutionContext ec;
   protected final Class candidateClass;
   protected final boolean subclasses;
   protected final AbstractClassMetaData cmd;

   public AbstractExtent(ExecutionContext ec, Class cls, boolean subclasses, AbstractClassMetaData cmd) {
      if (cls == null) {
         throw (new NucleusUserException(Localiser.msg("033000"))).setFatal();
      } else {
         this.cmd = cmd;
         if (cmd == null) {
            throw (new NucleusUserException(Localiser.msg("033001", cls.getName()))).setFatal();
         } else {
            this.ec = ec;
            this.candidateClass = cls;
            this.subclasses = subclasses;
         }
      }
   }

   public boolean hasSubclasses() {
      return this.subclasses;
   }

   public Class getCandidateClass() {
      return this.candidateClass;
   }

   public ExecutionContext getExecutionContext() {
      return this.ec;
   }

   public String toString() {
      return Localiser.msg("033002", this.candidateClass.getName(), "" + this.subclasses);
   }
}
