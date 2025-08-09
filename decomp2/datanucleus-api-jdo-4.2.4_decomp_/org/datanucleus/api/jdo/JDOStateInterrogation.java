package org.datanucleus.api.jdo;

import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.spi.StateInterrogation;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.identity.SingleFieldId;

public class JDOStateInterrogation implements StateInterrogation {
   public Object getObjectId(Object pc) {
      try {
         Object id = ((Persistable)pc).dnGetObjectId();
         return id != null && id instanceof SingleFieldId ? NucleusJDOHelper.getSingleFieldIdentityForDataNucleusIdentity((SingleFieldId)id, pc.getClass()) : id;
      } catch (NucleusException ne) {
         throw new JDOUserException("Exception thrown getting object id", ne);
      }
   }

   public PersistenceManager getPersistenceManager(Object pc) {
      ExecutionContextReference ec = ((Persistable)pc).dnGetExecutionContext();
      return ec != null ? (PersistenceManager)ec.getOwner() : null;
   }

   public Object getTransactionalObjectId(Object pc) {
      Object id = ((Persistable)pc).dnGetTransactionalObjectId();
      return id != null && id instanceof SingleFieldId ? NucleusJDOHelper.getSingleFieldIdentityForDataNucleusIdentity((SingleFieldId)id, pc.getClass()) : id;
   }

   public Object getVersion(Object pc) {
      return ((Persistable)pc).dnGetVersion();
   }

   public Boolean isDeleted(Object pc) {
      return ((Persistable)pc).dnIsDeleted();
   }

   public Boolean isDetached(Object pc) {
      return ((Persistable)pc).dnIsDetached();
   }

   public Boolean isDirty(Object pc) {
      return ((Persistable)pc).dnIsDirty();
   }

   public Boolean isNew(Object pc) {
      return ((Persistable)pc).dnIsNew();
   }

   public Boolean isPersistent(Object pc) {
      return ((Persistable)pc).dnIsPersistent();
   }

   public Boolean isTransactional(Object pc) {
      return ((Persistable)pc).dnIsTransactional();
   }

   public boolean makeDirty(Object pc, String fieldName) {
      ((Persistable)pc).dnMakeDirty(fieldName);
      return true;
   }
}
