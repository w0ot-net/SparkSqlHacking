package org.datanucleus.api.jdo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.jdo.FetchPlan;
import org.datanucleus.exceptions.NucleusException;

public class JDOFetchPlan implements FetchPlan, Serializable {
   private static final long serialVersionUID = 862722192165984633L;
   org.datanucleus.FetchPlan fp = null;

   public JDOFetchPlan(org.datanucleus.FetchPlan fp) {
      this.fp = fp;
   }

   public Set getGroups() {
      return this.fp.getGroups();
   }

   public FetchPlan addGroup(String group) {
      this.fp.addGroup(group);
      return this;
   }

   public FetchPlan clearGroups() {
      this.fp.clearGroups();
      return this;
   }

   public FetchPlan removeGroup(String group) {
      this.fp.removeGroup(group);
      return this;
   }

   public FetchPlan setGroup(String group) {
      this.fp.setGroup(group);
      return this;
   }

   public FetchPlan setGroups(Collection groups) {
      this.fp.setGroups(groups);
      return this;
   }

   public FetchPlan setGroups(String... groups) {
      this.fp.setGroups(groups);
      return this;
   }

   public int getFetchSize() {
      return this.fp.getFetchSize();
   }

   public FetchPlan setFetchSize(int size) {
      this.fp.setFetchSize(size);
      return this;
   }

   public int getMaxFetchDepth() {
      return this.fp.getMaxFetchDepth();
   }

   public FetchPlan setMaxFetchDepth(int depth) {
      try {
         this.fp.setMaxFetchDepth(depth);
         return this;
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public int getDetachmentOptions() {
      return this.fp.getDetachmentOptions();
   }

   public Class[] getDetachmentRootClasses() {
      return this.fp.getDetachmentRootClasses();
   }

   public Collection getDetachmentRoots() {
      return this.fp.getDetachmentRoots();
   }

   public FetchPlan setDetachmentOptions(int options) {
      try {
         this.fp.setDetachmentOptions(options);
         return this;
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public FetchPlan setDetachmentRootClasses(Class... rootClasses) {
      try {
         this.fp.setDetachmentRootClasses(rootClasses);
         return this;
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public FetchPlan setDetachmentRoots(Collection roots) {
      this.fp.setDetachmentRoots(roots);
      return this;
   }

   public org.datanucleus.FetchPlan getInternalFetchPlan() {
      return this.fp;
   }
}
