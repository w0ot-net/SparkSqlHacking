package org.datanucleus.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ReferentialStateManagerImpl extends StateManagerImpl {
   private List insertionNotifyList = null;
   private Map fieldsToBeUpdatedAfterObjectInsertion = null;

   public ReferentialStateManagerImpl(ExecutionContext ec, AbstractClassMetaData cmd) {
      super(ec, cmd);
   }

   public void connect(ExecutionContext ec, AbstractClassMetaData cmd) {
      super.connect(ec, cmd);
      this.fieldsToBeUpdatedAfterObjectInsertion = null;
      this.insertionNotifyList = null;
   }

   public void disconnect() {
      this.fieldsToBeUpdatedAfterObjectInsertion = null;
      this.insertionNotifyList = null;
      super.disconnect();
   }

   public void changeActivityState(ActivityState activityState) {
      this.activity = activityState;
      if (activityState == ActivityState.INSERTING_CALLBACKS && this.insertionNotifyList != null) {
         synchronized(this.insertionNotifyList) {
            for(ReferentialStateManagerImpl notifySM : this.insertionNotifyList) {
               notifySM.insertionCompleted(this);
            }
         }

         this.insertionNotifyList.clear();
         this.insertionNotifyList = null;
      }

   }

   public void updateFieldAfterInsert(Object pc, int fieldNumber) {
      ReferentialStateManagerImpl otherSM = (ReferentialStateManagerImpl)this.myEC.findObjectProvider(pc);
      if (otherSM.insertionNotifyList == null) {
         otherSM.insertionNotifyList = Collections.synchronizedList(new ArrayList(1));
      }

      otherSM.insertionNotifyList.add(this);
      if (this.fieldsToBeUpdatedAfterObjectInsertion == null) {
         this.fieldsToBeUpdatedAfterObjectInsertion = new HashMap(1);
      }

      FieldContainer cont = (FieldContainer)this.fieldsToBeUpdatedAfterObjectInsertion.get(otherSM);
      if (cont == null) {
         cont = new FieldContainer(fieldNumber);
      } else {
         cont.set(fieldNumber);
      }

      this.fieldsToBeUpdatedAfterObjectInsertion.put(otherSM, cont);
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("026021", this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getFullFieldName(), StringUtils.toJVMIDString(this.myPC), StringUtils.toJVMIDString(pc)));
      }

   }

   void insertionCompleted(ReferentialStateManagerImpl op) {
      if (this.fieldsToBeUpdatedAfterObjectInsertion != null) {
         FieldContainer fldCont = (FieldContainer)this.fieldsToBeUpdatedAfterObjectInsertion.get(op);
         if (fldCont != null) {
            this.dirty = true;
            int[] fieldsToUpdate = fldCont.getFields();

            for(int i = 0; i < fieldsToUpdate.length; ++i) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("026022", this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldsToUpdate[i]).getFullFieldName(), IdentityUtils.getPersistableIdentityForId(this.myID), StringUtils.toJVMIDString(op.getObject())));
               }

               this.dirtyFields[fieldsToUpdate[i]] = true;
            }

            this.fieldsToBeUpdatedAfterObjectInsertion.remove(op);
            if (this.fieldsToBeUpdatedAfterObjectInsertion.isEmpty()) {
               this.fieldsToBeUpdatedAfterObjectInsertion = null;
            }

            try {
               this.flags |= 16384;
               this.flush();
            } finally {
               this.flags &= -16385;
            }
         }

      }
   }

   private class FieldContainer {
      boolean[] fieldsToUpdate;

      public FieldContainer(int fieldNumber) {
         this.fieldsToUpdate = new boolean[ReferentialStateManagerImpl.this.cmd.getAllMemberPositions().length];
         this.fieldsToUpdate[fieldNumber] = true;
      }

      public void set(int fieldNumber) {
         this.fieldsToUpdate[fieldNumber] = true;
      }

      public int[] getFields() {
         return ClassUtils.getFlagsSetTo(this.fieldsToUpdate, true);
      }
   }
}
