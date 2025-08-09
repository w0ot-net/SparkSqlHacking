package org.datanucleus.api.jdo;

import java.io.Serializable;
import java.util.Set;
import javax.jdo.FetchGroup;
import javax.jdo.JDOUserException;
import org.datanucleus.exceptions.NucleusUserException;

public class JDOFetchGroup implements FetchGroup, Serializable {
   private static final long serialVersionUID = 8496393232964294401L;
   org.datanucleus.FetchGroup fg = null;

   public JDOFetchGroup(org.datanucleus.FetchGroup fg) {
      this.fg = fg;
   }

   public org.datanucleus.FetchGroup getInternalFetchGroup() {
      return this.fg;
   }

   public String getName() {
      return this.fg.getName();
   }

   public Class getType() {
      return this.fg.getType();
   }

   public FetchGroup setPostLoad(boolean postLoad) {
      this.assertUnmodifiable();
      this.fg.setPostLoad(postLoad);
      return this;
   }

   public boolean getPostLoad() {
      return this.fg.getPostLoad();
   }

   public int getRecursionDepth(String memberName) {
      return this.fg.getRecursionDepth(memberName);
   }

   public FetchGroup setRecursionDepth(String memberName, int recursionDepth) {
      this.assertUnmodifiable();
      this.fg.setRecursionDepth(memberName, recursionDepth);
      return this;
   }

   public FetchGroup setUnmodifiable() {
      this.fg.setUnmodifiable();
      return this;
   }

   public boolean isUnmodifiable() {
      return this.fg.isUnmodifiable();
   }

   public FetchGroup addCategory(String categoryName) {
      this.assertUnmodifiable();
      this.fg.addCategory(categoryName);
      return this;
   }

   public FetchGroup removeCategory(String categoryName) {
      this.assertUnmodifiable();
      this.fg.removeCategory(categoryName);
      return this;
   }

   public Set getMembers() {
      return this.fg.getMembers();
   }

   public FetchGroup addMember(String memberName) {
      this.assertUnmodifiable();

      try {
         this.fg.addMember(memberName);
         return this;
      } catch (NucleusUserException nue) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(nue);
      }
   }

   public FetchGroup removeMember(String memberName) {
      this.assertUnmodifiable();

      try {
         this.fg.removeMember(memberName);
         return this;
      } catch (NucleusUserException nue) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(nue);
      }
   }

   public FetchGroup addMembers(String... members) {
      this.assertUnmodifiable();

      try {
         this.fg.addMembers(members);
         return this;
      } catch (NucleusUserException nue) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(nue);
      }
   }

   public FetchGroup removeMembers(String... members) {
      this.assertUnmodifiable();

      try {
         this.fg.removeMembers(members);
         return this;
      } catch (NucleusUserException nue) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(nue);
      }
   }

   private void assertUnmodifiable() {
      if (this.fg.isUnmodifiable()) {
         throw new JDOUserException("FetchGroup is unmodifiable!");
      }
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof FetchGroup)) {
         return false;
      } else {
         FetchGroup other = (FetchGroup)obj;
         return other.getName().equals(this.getName()) && other.getType() == this.getType();
      }
   }

   public int hashCode() {
      return this.fg.hashCode();
   }

   public String toString() {
      return this.fg.toString();
   }
}
