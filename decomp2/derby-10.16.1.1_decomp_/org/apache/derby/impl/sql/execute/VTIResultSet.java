package org.apache.derby.impl.sql.execute;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.transaction.TransactionControl;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.types.VariableSizeDataValue;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.AwareVTI;
import org.apache.derby.vti.DeferModification;
import org.apache.derby.vti.IFastPath;
import org.apache.derby.vti.IQualifyable;
import org.apache.derby.vti.Pushable;
import org.apache.derby.vti.RestrictedVTI;
import org.apache.derby.vti.Restriction;
import org.apache.derby.vti.VTIContext;
import org.apache.derby.vti.VTIEnvironment;
import org.w3c.dom.Element;

class VTIResultSet extends NoPutResultSetImpl implements CursorResultSet, VTIEnvironment {
   public int rowsReturned;
   public String javaClassName;
   private GeneratedMethod constructor;
   private PreparedStatement userPS;
   private ResultSet userVTI;
   private final ExecRow allocatedRow;
   private FormatableBitSet referencedColumns;
   private boolean version2;
   private boolean reuseablePs;
   private boolean isTarget;
   private final FormatableHashtable compileTimeConstants;
   private boolean pushedProjection;
   private IFastPath fastPath;
   private Qualifier[][] pushedQualifiers;
   private boolean[] runtimeNullableColumn;
   private boolean isDerbyStyleTableFunction;
   private final TypeDescriptor returnType;
   private DataTypeDescriptor[] returnColumnTypes;
   private String[] vtiProjection;
   private Restriction vtiRestriction;
   private String vtiSchema;
   private String vtiName;
   private int scanIsolationLevel = 0;

   VTIResultSet(Activation var1, int var2, int var3, GeneratedMethod var4, String var5, Qualifier[][] var6, int var7, boolean var8, boolean var9, int var10, boolean var11, int var12, double var13, double var15, boolean var17, int var18, int var19, int var20, String var21, String var22) throws StandardException {
      super(var1, var3, var13, var15);
      this.constructor = var4;
      this.javaClassName = var5;
      this.version2 = var8;
      this.reuseablePs = var9;
      this.isTarget = var11;
      this.pushedQualifiers = var6;
      this.scanIsolationLevel = var12;
      this.isDerbyStyleTableFunction = var17;
      this.vtiSchema = var21;
      this.vtiName = var22;
      ExecPreparedStatement var23 = var1.getPreparedStatement();
      this.allocatedRow = ((ExecRowBuilder)var23.getSavedObject(var2)).build(var1.getExecutionFactory());
      this.returnType = var18 == -1 ? null : (TypeDescriptor)var1.getPreparedStatement().getSavedObject(var18);
      this.vtiProjection = var19 == -1 ? null : (String[])var1.getPreparedStatement().getSavedObject(var19);
      this.vtiRestriction = var20 == -1 ? null : (Restriction)var1.getPreparedStatement().getSavedObject(var20);
      if (var7 != -1) {
         this.referencedColumns = (FormatableBitSet)var1.getPreparedStatement().getSavedObject(var7);
      }

      this.compileTimeConstants = (FormatableHashtable)var1.getPreparedStatement().getSavedObject(var10);
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.isOpen = true;
      ++this.numOpens;

      try {
         if (this.version2) {
            this.userPS = (PreparedStatement)this.constructor.invoke(this.activation);
            if (this.userPS instanceof Pushable) {
               Pushable var4 = (Pushable)this.userPS;
               if (this.referencedColumns != null) {
                  this.pushedProjection = var4.pushProjection(this, this.getProjectedColList());
               }
            }

            if (this.userPS instanceof IQualifyable) {
               IQualifyable var5 = (IQualifyable)this.userPS;
               var5.setQualifiers(this, this.pushedQualifiers);
            }

            this.fastPath = this.userPS instanceof IFastPath ? (IFastPath)this.userPS : null;
            if (this.isTarget && this.userPS instanceof DeferModification && this.activation.getConstantAction() instanceof UpdatableVTIConstantAction) {
               UpdatableVTIConstantAction var6 = (UpdatableVTIConstantAction)this.activation.getConstantAction();
               ((DeferModification)this.userPS).modificationNotify(var6.statementType, var6.deferred);
            }

            if (this.fastPath == null || !this.fastPath.executeAsFastPath()) {
               this.userVTI = this.userPS.executeQuery();
            }

            if (this.isTarget) {
               this.activation.setTargetVTI(this.userVTI);
            }
         } else {
            this.userVTI = (ResultSet)this.constructor.invoke(this.activation);
            if (this.userVTI instanceof RestrictedVTI) {
               RestrictedVTI var1 = (RestrictedVTI)this.userVTI;
               var1.initScan(this.vtiProjection, this.cloneRestriction(this.activation));
            }

            if (this.userVTI instanceof AwareVTI) {
               AwareVTI var3 = (AwareVTI)this.userVTI;
               var3.setContext(new VTIContext(this.vtiSchema, this.vtiName, this.activation.getLanguageConnectionContext().getStatementContext().getStatementText()));
            }
         }

         this.setNullableColumnList();
      } catch (Throwable var2) {
         throw StandardException.unexpectedUserException(var2);
      }

      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private Restriction cloneRestriction(Activation var1) throws StandardException {
      return this.vtiRestriction == null ? null : this.cloneRestriction(var1, this.vtiRestriction);
   }

   private Restriction cloneRestriction(Activation var1, Restriction var2) throws StandardException {
      if (var2 instanceof Restriction.AND var9) {
         return new Restriction.AND(this.cloneRestriction(var1, var9.getLeftChild()), this.cloneRestriction(var1, var9.getRightChild()));
      } else if (var2 instanceof Restriction.OR var8) {
         return new Restriction.OR(this.cloneRestriction(var1, var8.getLeftChild()), this.cloneRestriction(var1, var8.getRightChild()));
      } else if (var2 instanceof Restriction.ColumnQualifier var3) {
         Object var4 = var3.getConstantOperand();
         Object var5;
         if (var4 == null) {
            var5 = null;
         } else if (var4 instanceof int[]) {
            int var6 = ((int[])var4)[0];
            ParameterValueSet var7 = var1.getParameterValueSet();
            var5 = var7.getParameter(var6).getObject();
         } else {
            var5 = var4;
         }

         return new Restriction.ColumnQualifier(var3.getColumnName(), var3.getComparisonOperator(), var5);
      } else {
         throw StandardException.newException("0A000.S", new Object[]{var2.getClass().getName()});
      }
   }

   private boolean[] setNullableColumnList() throws SQLException, StandardException {
      if (this.runtimeNullableColumn != null) {
         return this.runtimeNullableColumn;
      } else if (!this.isDerbyStyleTableFunction) {
         if (this.userVTI == null) {
            return null;
         } else {
            ResultSetMetaData var4 = this.userVTI.getMetaData();
            boolean[] var5 = new boolean[var4.getColumnCount() + 1];

            for(int var3 = 1; var3 < var5.length; ++var3) {
               var5[var3] = var4.isNullable(var3) != 0;
            }

            return this.runtimeNullableColumn = var5;
         }
      } else {
         int var1 = this.getAllocatedRow().nColumns() + 1;
         this.runtimeNullableColumn = new boolean[var1];

         for(int var2 = 0; var2 < var1; ++var2) {
            this.runtimeNullableColumn[var2] = true;
         }

         return this.runtimeNullableColumn;
      }
   }

   public void reopenCore() throws StandardException {
      if (this.reuseablePs) {
         if (this.userVTI != null) {
            try {
               this.userVTI.close();
               this.userVTI = this.userPS.executeQuery();
               if (this.isTarget) {
                  this.activation.setTargetVTI(this.userVTI);
               }
            } catch (SQLException var2) {
               throw StandardException.unexpectedUserException(var2);
            }
         }
      } else {
         this.close();
         this.openCore();
      }

   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            try {
               if (this.userVTI == null && this.fastPath != null) {
                  var1 = this.getAllocatedRow();
                  int var2 = this.fastPath.nextRow(var1.getRowArray());
                  if (var2 != 0) {
                     if (var2 == -1) {
                        var1 = null;
                     } else if (var2 == 1) {
                        this.userVTI = this.userPS.executeQuery();
                     }
                  }
               }

               if (this.userVTI != null) {
                  if (!this.userVTI.next()) {
                     if (null != this.fastPath) {
                        this.fastPath.rowsDone();
                     }

                     var1 = null;
                  } else {
                     var1 = this.getAllocatedRow();
                     this.populateFromResultSet(var1);
                     if (this.fastPath != null) {
                        this.fastPath.currentRow(this.userVTI, var1.getRowArray());
                     }

                     SQLWarning var4 = this.userVTI.getWarnings();
                     if (var4 != null) {
                        this.addWarning(var4);
                     }
                  }
               }
            } catch (Throwable var3) {
               throw StandardException.unexpectedUserException(var3);
            }
         }

         this.setCurrentRow(var1);
         if (var1 != null) {
            ++this.rowsReturned;
            ++this.rowsSeen;
         }

         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var1;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         if (this.userVTI != null) {
            try {
               this.userVTI.close();
            } catch (SQLException var14) {
               throw StandardException.unexpectedUserException(var14);
            } finally {
               this.userVTI = null;
            }
         }

         if (this.userPS != null && !this.reuseablePs) {
            try {
               this.userPS.close();
            } catch (SQLException var12) {
               throw StandardException.unexpectedUserException(var12);
            } finally {
               this.userPS = null;
            }
         }

         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      if (this.userPS != null && !this.reuseablePs) {
         try {
            this.userPS.close();
            this.userPS = null;
         } catch (SQLException var2) {
            throw StandardException.unexpectedUserException(var2);
         }
      }

      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var2;
   }

   public RowLocation getRowLocation() {
      return null;
   }

   public ExecRow getCurrentRow() {
      return null;
   }

   GeneratedMethod getVTIConstructor() {
      return this.constructor;
   }

   boolean isReuseablePs() {
      return this.reuseablePs;
   }

   private ExecRow getAllocatedRow() throws StandardException {
      return this.allocatedRow;
   }

   private int[] getProjectedColList() {
      FormatableBitSet var1 = this.referencedColumns;
      int var2 = var1.size();
      int var3 = 0;

      for(int var4 = 0; var4 < var2; ++var4) {
         if (var1.isSet(var4)) {
            ++var3;
         }
      }

      int[] var7 = new int[var3];
      int var5 = 0;

      for(int var6 = 0; var6 < var2; ++var6) {
         if (var1.isSet(var6)) {
            var7[var5++] = var6 + 1;
         }
      }

      return var7;
   }

   public void populateFromResultSet(ExecRow var1) throws StandardException {
      try {
         DataTypeDescriptor[] var2 = null;
         if (this.isDerbyStyleTableFunction) {
            var2 = this.getReturnColumnTypes();
         }

         boolean[] var3 = this.setNullableColumnList();
         DataValueDescriptor[] var4 = var1.getRowArray();
         int var5 = 1;

         for(int var6 = 0; var6 < var4.length; ++var6) {
            if (this.referencedColumns != null && !this.referencedColumns.get(var6)) {
               if (!this.pushedProjection) {
                  ++var5;
               }
            } else {
               var4[var6].setValueFromResultSet(this.userVTI, var5, var3[var5]);
               ++var5;
               if (this.isDerbyStyleTableFunction) {
                  DataTypeDescriptor var7 = var2[var6];
                  DataValueDescriptor var8 = var4[var6];
                  this.cast(var7, var8);
               }
            }
         }

      } catch (StandardException var9) {
         throw var9;
      } catch (Throwable var10) {
         throw StandardException.unexpectedUserException(var10);
      }
   }

   public final int getScanIsolationLevel() {
      return this.scanIsolationLevel;
   }

   public final boolean isCompileTime() {
      return false;
   }

   public final String getOriginalSQL() {
      return this.activation.getPreparedStatement().getSource();
   }

   public final int getStatementIsolationLevel() {
      return TransactionControl.jdbcIsolationLevel(this.getScanIsolationLevel());
   }

   public final void setSharedState(String var1, Serializable var2) {
      if (var1 != null) {
         if (var2 == null) {
            this.compileTimeConstants.remove(var1);
         } else {
            this.compileTimeConstants.put(var1, var2);
         }

      }
   }

   public Object getSharedState(String var1) {
      return var1 != null && this.compileTimeConstants != null ? this.compileTimeConstants.get(var1) : null;
   }

   private DataTypeDescriptor[] getReturnColumnTypes() throws StandardException {
      if (this.returnColumnTypes == null) {
         TypeDescriptor[] var1 = this.returnType.getRowTypes();
         int var2 = var1.length;
         this.returnColumnTypes = new DataTypeDescriptor[var2];

         for(int var3 = 0; var3 < var2; ++var3) {
            this.returnColumnTypes[var3] = DataTypeDescriptor.getType(var1[var3]);
         }
      }

      return this.returnColumnTypes;
   }

   private void cast(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      TypeId var3 = var1.getTypeId();
      if (!var3.isBlobTypeId() && !var3.isClobTypeId()) {
         if (var3.isLongVarcharTypeId()) {
            this.castLongvarchar(var1, var2);
         } else if (var3.isLongVarbinaryTypeId()) {
            this.castLongvarbinary(var1, var2);
         } else if (var3.isDecimalTypeId()) {
            this.castDecimal(var1, var2);
         } else {
            Object var4 = var2.getObject();
            var2.setObjectForCast(var4, true, var3.getCorrespondingJavaTypeName());
            if (var3.variableLength()) {
               VariableSizeDataValue var5 = (VariableSizeDataValue)var2;
               int var6;
               if (var3.isNumericTypeId()) {
                  var6 = var1.getPrecision();
               } else {
                  var6 = var1.getMaximumWidth();
               }

               var5.setWidth(var6, var1.getScale(), false);
            }
         }
      }

   }

   private void castLongvarchar(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2.getLength() > 32700) {
         var2.setValue(var2.getString().substring(0, 32700));
      }

   }

   private void castLongvarbinary(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2.getLength() > 32700) {
         byte[] var3 = var2.getBytes();
         byte[] var4 = new byte[32700];
         System.arraycopy(var3, 0, var4, 0, 32700);
         var2.setValue(var4);
      }

   }

   private void castDecimal(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      VariableSizeDataValue var3 = (VariableSizeDataValue)var2;
      var3.setWidth(var1.getPrecision(), var1.getScale(), false);
   }

   public Element toXML(Element var1, String var2) throws Exception {
      Element var3 = super.toXML(var1, var2);
      var3.setAttribute("javaClassName", this.javaClassName);
      return var3;
   }
}
