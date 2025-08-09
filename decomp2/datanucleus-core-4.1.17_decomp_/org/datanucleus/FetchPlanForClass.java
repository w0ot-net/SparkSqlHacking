package org.datanucleus;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FetchGroupMemberMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class FetchPlanForClass {
   final FetchPlan plan;
   final AbstractClassMetaData cmd;
   int[] memberNumbers;
   boolean dirty = true;
   private Map fetchGroupsByMemberNumber = null;

   public FetchPlanForClass(AbstractClassMetaData cmd, FetchPlan fetchPlan) {
      this.cmd = cmd;
      this.plan = fetchPlan;
   }

   public final FetchPlan getFetchPlan() {
      return this.plan;
   }

   public final AbstractClassMetaData getAbstractClassMetaData() {
      return this.cmd;
   }

   public String toString() {
      return this.cmd.getFullClassName() + "[members=" + StringUtils.intArrayToString(this.getMemberNumbers()) + "]";
   }

   void markDirty() {
      this.dirty = true;
      this.plan.invalidateCachedIsToCallPostLoadFetchPlan(this.cmd);
   }

   FetchPlanForClass getCopy(FetchPlan fp) {
      FetchPlanForClass fpCopy = new FetchPlanForClass(this.cmd, fp);
      if (this.memberNumbers != null) {
         fpCopy.memberNumbers = new int[this.memberNumbers.length];

         for(int i = 0; i < fpCopy.memberNumbers.length; ++i) {
            fpCopy.memberNumbers[i] = this.memberNumbers[i];
         }
      }

      fpCopy.dirty = this.dirty;
      return fpCopy;
   }

   public int getMaxRecursionDepthForMember(int memberNum) {
      Set<String> currentGroupNames = new HashSet(this.plan.getGroups());
      Set<FetchGroupMetaData> fetchGroupsContainingField = this.getFetchGroupsForMemberNumber(this.cmd.getFetchGroupMetaData((Collection)currentGroupNames), memberNum);
      int recursionDepth = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberNum).getRecursionDepth();
      if (recursionDepth == 0) {
         recursionDepth = 1;
      }

      String fieldName = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberNum).getName();

      for(FetchGroupMetaData fgmd : fetchGroupsContainingField) {
         Set<FetchGroupMemberMetaData> fgmmds = fgmd.getMembers();
         if (fgmmds != null) {
            for(FetchGroupMemberMetaData fgmmd : fgmmds) {
               if (fgmmd.getName().equals(fieldName) && fgmmd.getRecursionDepth() != 0) {
                  recursionDepth = fgmmd.getRecursionDepth();
               }
            }
         }
      }

      return recursionDepth;
   }

   public boolean hasMember(int memberNumber) {
      if (this.dirty) {
         BitSet fieldsNumber = this.getMemberNumbersByBitSet();
         return fieldsNumber.get(memberNumber);
      } else {
         if (this.memberNumbers != null) {
            for(int i = 0; i < this.memberNumbers.length; ++i) {
               if (this.memberNumbers[i] == memberNumber) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public int[] getMemberNumbers() {
      if (this.dirty) {
         this.dirty = false;
         BitSet fieldsNumber = this.getMemberNumbersByBitSet();
         int count = 0;

         for(int i = 0; i < fieldsNumber.length(); ++i) {
            if (fieldsNumber.get(i)) {
               ++count;
            }
         }

         this.memberNumbers = new int[count];
         int nextField = 0;

         for(int i = 0; i < fieldsNumber.length(); ++i) {
            if (fieldsNumber.get(i)) {
               this.memberNumbers[nextField++] = i;
            }
         }
      }

      return this.memberNumbers;
   }

   public BitSet getMemberNumbersByBitSet() {
      return this.getMemberNumbersByBitSet(this.cmd);
   }

   private BitSet getMemberNumbersByBitSet(AbstractClassMetaData cmd) {
      FetchPlanForClass fpc = this.plan.getFetchPlanForClass(cmd);
      BitSet bitSet = fpc.getMemberNumbersForFetchGroups(cmd.getFetchGroupMetaData());
      if (cmd.getPersistableSuperclass() != null) {
         AbstractClassMetaData superCmd = cmd.getSuperAbstractClassMetaData();
         FetchPlanForClass superFpc = this.plan.getFetchPlanForClass(superCmd);
         bitSet.or(superFpc.getMemberNumbersByBitSet(superCmd));
      } else {
         fpc.setAsNone(bitSet);
      }

      if (this.plan.dynamicGroups != null) {
         for(FetchGroup grp : this.plan.dynamicGroups) {
            if (grp.getType().getName().equals(cmd.getFullClassName())) {
               for(String memberName : grp.getMembers()) {
                  int fieldPos = cmd.getAbsolutePositionOfMember(memberName);
                  if (fieldPos >= 0) {
                     bitSet.set(fieldPos);
                  }
               }
            }
         }
      }

      return bitSet;
   }

   private BitSet getMemberNumbersForFetchGroups(Set fgmds) {
      BitSet memberNumbers = new BitSet(0);
      if (fgmds != null) {
         for(FetchGroupMetaData fgmd : fgmds) {
            if (this.plan.groups.contains(fgmd.getName())) {
               memberNumbers.or(this.getMemberNumbersForFetchGroup(fgmd));
            }
         }
      }

      if (this.plan.groups.contains("default")) {
         this.setAsDefault(memberNumbers);
      }

      if (this.plan.groups.contains("all")) {
         this.setAsAll(memberNumbers);
      }

      if (this.plan.groups.contains("none")) {
         this.setAsNone(memberNumbers);
      }

      return memberNumbers;
   }

   private BitSet getMemberNumbersForFetchGroup(FetchGroupMetaData fgmd) {
      BitSet memberNumbers = new BitSet(0);
      Set<FetchGroupMemberMetaData> subFGmmds = fgmd.getMembers();
      if (subFGmmds != null) {
         for(FetchGroupMemberMetaData subFGmmd : subFGmmds) {
            int fieldNumber = this.cmd.getAbsolutePositionOfMember(subFGmmd.getName());
            if (fieldNumber == -1) {
               String msg = Localiser.msg("006000", subFGmmd.getName(), fgmd.getName(), this.cmd.getFullClassName());
               NucleusLogger.PERSISTENCE.error(msg);
               throw (new NucleusUserException(msg)).setFatal();
            }

            memberNumbers.set(fieldNumber);
         }
      }

      Set<FetchGroupMetaData> subFGs = fgmd.getFetchGroups();
      if (subFGs != null) {
         for(FetchGroupMetaData subFgmd : subFGs) {
            String nestedGroupName = subFgmd.getName();
            if (nestedGroupName.equals("default")) {
               this.setAsDefault(memberNumbers);
            } else if (nestedGroupName.equals("all")) {
               this.setAsAll(memberNumbers);
            } else if (nestedGroupName.equals("none")) {
               this.setAsNone(memberNumbers);
            } else {
               FetchGroupMetaData nestedFGMD = this.cmd.getFetchGroupMetaData(nestedGroupName);
               if (nestedFGMD == null) {
                  throw (new NucleusUserException(Localiser.msg("006001", subFgmd.getName(), fgmd.getName(), this.cmd.getFullClassName()))).setFatal();
               }

               memberNumbers.or(this.getMemberNumbersForFetchGroup(nestedFGMD));
            }
         }
      }

      return memberNumbers;
   }

   private void setAsDefault(BitSet memberNums) {
      for(int i = 0; i < this.cmd.getDFGMemberPositions().length; ++i) {
         memberNums.set(this.cmd.getDFGMemberPositions()[i]);
      }

   }

   private void setAsAll(BitSet memberNums) {
      for(int i = 0; i < this.cmd.getNoOfManagedMembers(); ++i) {
         if (this.cmd.getMetaDataForManagedMemberAtRelativePosition(i).getPersistenceModifier() != FieldPersistenceModifier.NONE) {
            memberNums.set(this.cmd.getAbsoluteMemberPositionForRelativePosition(i));
         }
      }

   }

   private void setAsNone(BitSet memberNums) {
      for(int i = 0; i < this.cmd.getNoOfManagedMembers(); ++i) {
         AbstractMemberMetaData fmd = this.cmd.getMetaDataForMemberAtRelativePosition(i);
         if (fmd.isPrimaryKey()) {
            memberNums.set(fmd.getAbsoluteFieldNumber());
         }
      }

   }

   public boolean isToCallPostLoadFetchPlan(boolean[] loadedMembers) {
      BitSet cacheKey = new BitSet(loadedMembers.length);

      for(int i = 0; i < loadedMembers.length; ++i) {
         cacheKey.set(i, loadedMembers[i]);
      }

      Boolean result = this.plan.getCachedIsToCallPostLoadFetchPlan(this.cmd, cacheKey);
      if (result == null) {
         result = Boolean.FALSE;
         int[] fieldsInActualFetchPlan = this.getMemberNumbers();

         for(int i = 0; i < fieldsInActualFetchPlan.length; ++i) {
            int fieldNumber = fieldsInActualFetchPlan[i];
            String fieldName = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getFullFieldName();
            if (!loadedMembers[fieldNumber]) {
               if (this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).isDefaultFetchGroup() && this.plan.getGroups().contains("default")) {
                  result = Boolean.TRUE;
               } else {
                  if (this.cmd.hasFetchGroupWithPostLoad()) {
                     Integer fieldNumberInteger = fieldNumber;
                     Set<FetchGroupMetaData> fetchGroups = null;
                     if (this.fetchGroupsByMemberNumber != null) {
                        fetchGroups = (Set)this.fetchGroupsByMemberNumber.get(fieldNumberInteger);
                     }

                     if (fetchGroups == null) {
                        fetchGroups = this.getFetchGroupsForMemberNumber(this.cmd.getFetchGroupMetaData(), fieldNumber);
                        if (this.fetchGroupsByMemberNumber == null) {
                           this.fetchGroupsByMemberNumber = new HashMap();
                        }

                        this.fetchGroupsByMemberNumber.put(fieldNumberInteger, fetchGroups);
                     }

                     for(FetchGroupMetaData fgmd : fetchGroups) {
                        if (fgmd.getPostLoad()) {
                           result = Boolean.TRUE;
                        }
                     }
                  }

                  if (this.plan.dynamicGroups != null) {
                     Class cls = this.plan.clr.classForName(this.cmd.getFullClassName());

                     for(FetchGroup group : this.plan.dynamicGroups) {
                        Set groupMembers = group.getMembers();
                        if (group.getType().isAssignableFrom(cls) && groupMembers.contains(fieldName) && group.getPostLoad()) {
                           result = Boolean.TRUE;
                        }
                     }
                  }
               }
            }
         }

         if (result == null) {
            result = Boolean.FALSE;
         }

         this.plan.cacheIsToCallPostLoadFetchPlan(this.cmd, cacheKey, result);
      }

      return result;
   }

   private Set getFetchGroupsForMemberNumber(Set fgmds, int memberNum) {
      Set<FetchGroupMetaData> fetchGroups = new HashSet();
      if (fgmds != null) {
         for(FetchGroupMetaData fgmd : fgmds) {
            Set<FetchGroupMemberMetaData> subFGmmds = fgmd.getMembers();
            if (subFGmmds != null) {
               for(FetchGroupMemberMetaData subFGmmd : subFGmmds) {
                  if (subFGmmd.getName().equals(this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberNum).getName())) {
                     fetchGroups.add(fgmd);
                  }
               }
            }

            Set<FetchGroupMetaData> subFGmds = fgmd.getFetchGroups();
            if (subFGmds != null) {
               fetchGroups.addAll(this.getFetchGroupsForMemberNumber(subFGmds, memberNum));
            }
         }
      }

      return fetchGroups;
   }
}
