package org.apache.derby.iapi.sql.dictionary;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Timestamp;
import java.util.List;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class TriggerDescriptor extends UniqueSQLObjectDescriptor implements Provider, Dependent, Formatable {
   public static final int SYSTRIGGERS_STATE_FIELD = 8;
   public static final int TRIGGER_EVENT_UPDATE = 1;
   public static final int TRIGGER_EVENT_DELETE = 2;
   public static final int TRIGGER_EVENT_INSERT = 4;
   private UUID id;
   private String name;
   private String oldReferencingName;
   private String newReferencingName;
   private String triggerDefinition;
   private SchemaDescriptor sd;
   private int eventMask;
   private boolean isBefore;
   private boolean isRow;
   private boolean referencingOld;
   private boolean referencingNew;
   private TableDescriptor td;
   private UUID actionSPSId;
   private SPSDescriptor actionSPS;
   private UUID whenSPSId;
   private SPSDescriptor whenSPS;
   private boolean isEnabled;
   private int[] referencedCols;
   private int[] referencedColsInTriggerAction;
   private Timestamp creationTimestamp;
   private UUID triggerSchemaId;
   private UUID triggerTableId;
   private String whenClauseText;

   public TriggerDescriptor() {
   }

   TriggerDescriptor(DataDictionary var1, SchemaDescriptor var2, UUID var3, String var4, int var5, boolean var6, boolean var7, boolean var8, TableDescriptor var9, UUID var10, UUID var11, Timestamp var12, int[] var13, int[] var14, String var15, boolean var16, boolean var17, String var18, String var19, String var20) {
      super(var1);
      this.id = var3;
      this.sd = var2;
      this.name = var4;
      this.eventMask = var5;
      this.isBefore = var6;
      this.isRow = var7;
      this.td = var9;
      this.actionSPSId = var11;
      this.whenSPSId = var10;
      this.isEnabled = var8;
      this.referencedCols = var13;
      this.setReferencedColsInTriggerAction(var14);
      this.creationTimestamp = DataTypeUtilities.clone(var12);
      this.triggerDefinition = var15;
      this.referencingOld = var16;
      this.referencingNew = var17;
      this.oldReferencingName = var18;
      this.newReferencingName = var19;
      this.whenClauseText = var20;
      this.triggerSchemaId = var2.getUUID();
      this.triggerTableId = var9.getUUID();
   }

   public UUID getUUID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public UUID getTableId() {
      return this.triggerTableId;
   }

   public SchemaDescriptor getSchemaDescriptor() throws StandardException {
      if (this.sd == null) {
         this.sd = this.getDataDictionary().getSchemaDescriptor(this.triggerSchemaId, (TransactionController)null);
      }

      return this.sd;
   }

   public boolean listensForEvent(int var1) {
      return (var1 & this.eventMask) == var1;
   }

   public int getTriggerEventMask() {
      return this.eventMask;
   }

   public Timestamp getCreationTimestamp() {
      return DataTypeUtilities.clone(this.creationTimestamp);
   }

   public boolean isBeforeTrigger() {
      return this.isBefore;
   }

   public boolean isRowTrigger() {
      return this.isRow;
   }

   public UUID getActionId() {
      return this.actionSPSId;
   }

   public SPSDescriptor getActionSPS(LanguageConnectionContext var1) throws StandardException {
      return this.getSPS(var1, false);
   }

   private SPSDescriptor getSPS(LanguageConnectionContext var1, boolean var2) throws StandardException {
      DataDictionary var3 = this.getDataDictionary();
      SPSDescriptor var4 = var2 ? this.whenSPS : this.actionSPS;
      UUID var5 = var2 ? this.whenSPSId : this.actionSPSId;
      String var6 = var2 ? this.whenClauseText : this.triggerDefinition;
      if (var4 == null) {
         var1.beginNestedTransaction(true);
         var4 = var3.getSPSDescriptor(var5);
         var1.commitNestedTransaction();
      }

      boolean var7 = var3.checkVersion(210, (String)null);
      boolean var8 = var7 ? this.referencedColsInTriggerAction != null : this.referencingOld || this.referencingNew;
      if ((!var4.isValid() || var4.getPreparedStatement() == null) && this.isRow && var8) {
         CompilerContext var9 = var1.pushCompilerContext(var3.getSchemaDescriptor(var4.getCompSchemaId(), (TransactionController)null));
         Parser var10 = var9.getParser();
         Visitable var11 = var2 ? var10.parseSearchCondition(var6) : var10.parseStatement(var6);
         var1.popCompilerContext(var9);
         int[] var12 = var3.examineTriggerNodeAndCols(var11, this.oldReferencingName, this.newReferencingName, var6, this.referencedCols, this.referencedColsInTriggerAction, 0, this.getTableDescriptor(), -1, false, (List)null);
         String var13 = var3.getTriggerActionString(var11, this.oldReferencingName, this.newReferencingName, var6, this.referencedCols, this.referencedColsInTriggerAction, 0, this.getTableDescriptor(), -1, false, (List)null, var12);
         if (var2) {
            var13 = "VALUES " + var13;
         }

         var4.setText(var13);
      }

      return var4;
   }

   public UUID getWhenClauseId() {
      return this.whenSPSId;
   }

   public String getWhenClauseText() {
      return this.whenClauseText;
   }

   public SPSDescriptor getWhenClauseSPS(LanguageConnectionContext var1) throws StandardException {
      return this.whenSPSId == null ? null : this.getSPS(var1, true);
   }

   public TableDescriptor getTableDescriptor() throws StandardException {
      if (this.td == null) {
         this.td = this.getDataDictionary().getTableDescriptor(this.triggerTableId);
      }

      return this.td;
   }

   public int[] getReferencedCols() {
      return ArrayUtil.copy(this.referencedCols);
   }

   public void setReferencedCols(int[] var1) {
      this.referencedCols = ArrayUtil.copy(var1);
   }

   public int[] getReferencedColsInTriggerAction() {
      return ArrayUtil.copy(this.referencedColsInTriggerAction);
   }

   public void setReferencedColsInTriggerAction(int[] var1) {
      this.referencedColsInTriggerAction = ArrayUtil.copy(var1);
   }

   public boolean isEnabled() {
      return this.isEnabled;
   }

   public void setEnabled() {
      this.isEnabled = true;
   }

   public void setDisabled() {
      this.isEnabled = false;
   }

   public boolean needsToFire(int var1, int[] var2) throws StandardException {
      if (!this.isEnabled) {
         return false;
      } else if (var1 == 1) {
         return (this.eventMask & 4) == this.eventMask;
      } else if (var1 == 4) {
         return (this.eventMask & 2) == this.eventMask;
      } else if (var1 == 2) {
         throw StandardException.newException("42Z08", new Object[]{this.getTableDescriptor().getQualifiedName(), this.name});
      } else {
         return (this.eventMask & 1) == this.eventMask && ConstraintDescriptor.doColumnsIntersect(var2, this.referencedCols);
      }
   }

   public String getTriggerDefinition() {
      return this.triggerDefinition;
   }

   public boolean getReferencingOld() {
      return this.referencingOld;
   }

   public boolean getReferencingNew() {
      return this.referencingNew;
   }

   public String getOldReferencingName() {
      return this.oldReferencingName;
   }

   public String getNewReferencingName() {
      return this.newReferencingName;
   }

   public String toString() {
      return "";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(320);
   }

   public String getObjectName() {
      return this.name;
   }

   public UUID getObjectID() {
      return this.id;
   }

   public String getClassType() {
      return "Trigger";
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      switch (var2) {
         case 1:
         case 6:
         case 9:
         case 13:
         case 34:
         case 43:
         case 45:
            DependencyManager var4 = this.getDataDictionary().getDependencyManager();
            throw StandardException.newException("X0Y25.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), "TRIGGER", this.name});
         default:
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      switch (var1) {
         case 14:
            DependencyManager var3 = this.getDataDictionary().getDependencyManager();
            var3.invalidateFor(this, 11, var2);
            break;
         case 44:
         case 47:
            this.drop(var2);
            var2.getLastActivation().addWarning(StandardException.newWarning("01502", new Object[]{this.getObjectName()}));
      }

   }

   public void drop(LanguageConnectionContext var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      DependencyManager var3 = this.getDataDictionary().getDependencyManager();
      TransactionController var4 = var1.getTransactionExecute();
      var3.invalidateFor(this, 27, var1);
      var2.dropTriggerDescriptor(this, var4);
      var3.clearDependencies(var1, this);
      SPSDescriptor var5 = var2.getSPSDescriptor(this.getActionId());
      var3.invalidateFor(var5, 27, var1);
      var3.clearDependencies(var1, var5);
      var2.dropSPSDescriptor(var5, var4);
      if (this.getWhenClauseId() != null) {
         var5 = var2.getSPSDescriptor(this.getWhenClauseId());
         var3.invalidateFor(var5, 27, var1);
         var3.clearDependencies(var1, var5);
         var2.dropSPSDescriptor(var5, var4);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.id = (UUID)var1.readObject();
      this.name = (String)var1.readObject();
      this.triggerSchemaId = (UUID)var1.readObject();
      this.triggerTableId = (UUID)var1.readObject();
      this.eventMask = var1.readInt();
      this.isBefore = var1.readBoolean();
      this.isRow = var1.readBoolean();
      this.isEnabled = var1.readBoolean();
      this.whenSPSId = (UUID)var1.readObject();
      this.actionSPSId = (UUID)var1.readObject();
      int var2 = var1.readInt();
      if (var2 != 0) {
         this.referencedCols = new int[var2];

         for(int var3 = 0; var3 < var2; ++var3) {
            this.referencedCols[var3] = var1.readInt();
         }
      }

      var2 = var1.readInt();
      if (var2 != 0) {
         this.referencedColsInTriggerAction = new int[var2];

         for(int var5 = 0; var5 < var2; ++var5) {
            this.referencedColsInTriggerAction[var5] = var1.readInt();
         }
      }

      this.triggerDefinition = (String)var1.readObject();
      this.referencingOld = var1.readBoolean();
      this.referencingNew = var1.readBoolean();
      this.oldReferencingName = (String)var1.readObject();
      this.newReferencingName = (String)var1.readObject();
      this.whenClauseText = (String)var1.readObject();
   }

   protected DataDictionary getDataDictionary() {
      DataDictionary var1 = super.getDataDictionary();
      if (var1 == null) {
         LanguageConnectionContext var2 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
         var1 = var2.getDataDictionary();
         this.setDataDictionary(var1);
      }

      return var1;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.id);
      var1.writeObject(this.name);
      var1.writeObject(this.triggerSchemaId);
      var1.writeObject(this.triggerTableId);
      var1.writeInt(this.eventMask);
      var1.writeBoolean(this.isBefore);
      var1.writeBoolean(this.isRow);
      var1.writeBoolean(this.isEnabled);
      var1.writeObject(this.whenSPSId);
      var1.writeObject(this.actionSPSId);
      if (this.referencedCols == null) {
         var1.writeInt(0);
      } else {
         var1.writeInt(this.referencedCols.length);

         for(int var2 = 0; var2 < this.referencedCols.length; ++var2) {
            var1.writeInt(this.referencedCols[var2]);
         }
      }

      if (this.referencedColsInTriggerAction == null) {
         var1.writeInt(0);
      } else {
         var1.writeInt(this.referencedColsInTriggerAction.length);

         for(int var3 = 0; var3 < this.referencedColsInTriggerAction.length; ++var3) {
            var1.writeInt(this.referencedColsInTriggerAction[var3]);
         }
      }

      var1.writeObject(this.triggerDefinition);
      var1.writeBoolean(this.referencingOld);
      var1.writeBoolean(this.referencingNew);
      var1.writeObject(this.oldReferencingName);
      var1.writeObject(this.newReferencingName);
      var1.writeObject(this.whenClauseText);
   }

   public int getTypeFormatId() {
      return 316;
   }

   public String getDescriptorType() {
      return "Trigger";
   }

   public String getDescriptorName() {
      return this.name;
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
