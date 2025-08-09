package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public abstract class JavaTypeMapping {
   protected AbstractMemberMetaData mmd;
   protected FieldRole roleForMember;
   protected DatastoreMapping[] datastoreMappings;
   protected Table table;
   protected RDBMSStoreManager storeMgr;
   protected String type;
   protected JavaTypeMapping referenceMapping;
   protected int absFieldNumber;

   protected JavaTypeMapping() {
      this.roleForMember = FieldRole.ROLE_NONE;
      this.datastoreMappings = new DatastoreMapping[0];
      this.absFieldNumber = -1;
   }

   public void initialize(RDBMSStoreManager storeMgr, String type) {
      this.storeMgr = storeMgr;
      this.type = type;
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      this.storeMgr = table.getStoreManager();
      this.mmd = mmd;
      this.table = table;
      if (this.roleForMember == FieldRole.ROLE_ARRAY_ELEMENT) {
         this.type = mmd.getArray().getElementType();
      } else if (this.roleForMember == FieldRole.ROLE_COLLECTION_ELEMENT) {
         this.type = mmd.getCollection().getElementType();
      } else if (this.roleForMember == FieldRole.ROLE_MAP_KEY) {
         this.type = mmd.getMap().getKeyType();
      } else if (this.roleForMember == FieldRole.ROLE_MAP_VALUE) {
         this.type = mmd.getMap().getValueType();
      } else {
         this.type = mmd.getType().getName();
      }

   }

   public int hashCode() {
      return this.mmd != null && this.table != null ? this.mmd.hashCode() ^ this.table.hashCode() : super.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj != null && obj.getClass().equals(this.getClass())) {
         if (obj == this) {
            return true;
         } else {
            JavaTypeMapping other = (JavaTypeMapping)obj;
            return this.mmd.equals(other.mmd) && this.table.equals(other.table);
         }
      } else {
         return false;
      }
   }

   public void setMemberMetaData(AbstractMemberMetaData mmd) {
      this.mmd = mmd;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.mmd;
   }

   public RDBMSStoreManager getStoreManager() {
      return this.storeMgr;
   }

   public void setTable(Table table) {
      this.table = table;
   }

   public Table getTable() {
      return this.table;
   }

   public FieldRole getRoleForMember() {
      return this.roleForMember;
   }

   public void setRoleForMember(FieldRole role) {
      this.roleForMember = role;
   }

   protected int getAbsoluteFieldNumber() {
      if (this.absFieldNumber < 0 && this.mmd != null) {
         this.absFieldNumber = this.mmd.getAbsoluteFieldNumber();
      }

      return this.absFieldNumber;
   }

   public void setAbsFieldNumber(int num) {
      this.absFieldNumber = num;
   }

   public boolean isSerialised() {
      if (this.roleForMember == FieldRole.ROLE_COLLECTION_ELEMENT) {
         if (this.mmd == null) {
            return false;
         } else {
            return this.mmd.getCollection() != null ? this.mmd.getCollection().isSerializedElement() : false;
         }
      } else if (this.roleForMember == FieldRole.ROLE_ARRAY_ELEMENT) {
         if (this.mmd == null) {
            return false;
         } else {
            return this.mmd.getArray() != null ? this.mmd.getArray().isSerializedElement() : false;
         }
      } else if (this.roleForMember == FieldRole.ROLE_MAP_KEY) {
         if (this.mmd == null) {
            return false;
         } else {
            return this.mmd.getMap() != null ? this.mmd.getMap().isSerializedKey() : false;
         }
      } else if (this.roleForMember == FieldRole.ROLE_MAP_VALUE) {
         if (this.mmd == null) {
            return false;
         } else {
            return this.mmd.getMap() != null ? this.mmd.getMap().isSerializedValue() : false;
         }
      } else {
         return this.mmd != null ? this.mmd.isSerialized() : false;
      }
   }

   public boolean isNullable() {
      for(int i = 0; i < this.datastoreMappings.length; ++i) {
         if (!this.datastoreMappings[i].isNullable()) {
            return false;
         }
      }

      return true;
   }

   public boolean hasSimpleDatastoreRepresentation() {
      return true;
   }

   public boolean representableAsStringLiteralInStatement() {
      return true;
   }

   public DatastoreMapping[] getDatastoreMappings() {
      return this.datastoreMappings;
   }

   public DatastoreMapping getDatastoreMapping(int index) {
      return this.datastoreMappings[index];
   }

   public void addDatastoreMapping(DatastoreMapping datastoreMapping) {
      DatastoreMapping[] dm = this.datastoreMappings;
      this.datastoreMappings = new DatastoreMapping[this.datastoreMappings.length + 1];
      System.arraycopy(dm, 0, this.datastoreMappings, 0, dm.length);
      this.datastoreMappings[dm.length] = datastoreMapping;
   }

   public int getNumberOfDatastoreMappings() {
      return this.datastoreMappings.length;
   }

   public Object getValueForDatastoreMapping(NucleusContext nucleusCtx, int index, Object value) {
      return value;
   }

   public JavaTypeMapping getReferenceMapping() {
      return this.referenceMapping;
   }

   public void setReferenceMapping(JavaTypeMapping referenceMapping) {
      this.referenceMapping = referenceMapping;
   }

   public abstract Class getJavaType();

   public String getJavaTypeForDatastoreMapping(int index) {
      throw new UnsupportedOperationException("Datastore type mapping is not supported by: " + this.getClass());
   }

   public String getType() {
      return this.type;
   }

   public boolean includeInFetchStatement() {
      return true;
   }

   public boolean includeInUpdateStatement() {
      return true;
   }

   public boolean includeInInsertStatement() {
      return true;
   }

   protected String failureMessage(String method) {
      return Localiser.msg("041004", new Object[]{this.getClass().getName(), method});
   }

   public void setBoolean(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, boolean value) {
      throw (new NucleusException(this.failureMessage("setBoolean"))).setFatal();
   }

   public boolean getBoolean(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("setBoolean"))).setFatal();
   }

   public void setChar(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, char value) {
      throw (new NucleusException(this.failureMessage("setChar"))).setFatal();
   }

   public char getChar(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getChar"))).setFatal();
   }

   public void setByte(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, byte value) {
      throw (new NucleusException(this.failureMessage("setByte"))).setFatal();
   }

   public byte getByte(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getByte"))).setFatal();
   }

   public void setShort(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, short value) {
      throw (new NucleusException(this.failureMessage("setShort"))).setFatal();
   }

   public short getShort(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getShort"))).setFatal();
   }

   public void setInt(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, int value) {
      throw (new NucleusException(this.failureMessage("setInt"))).setFatal();
   }

   public int getInt(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getInt"))).setFatal();
   }

   public void setLong(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, long value) {
      throw (new NucleusException(this.failureMessage("setLong"))).setFatal();
   }

   public long getLong(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getLong"))).setFatal();
   }

   public void setFloat(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, float value) {
      throw (new NucleusException(this.failureMessage("setFloat"))).setFatal();
   }

   public float getFloat(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getFloat"))).setFatal();
   }

   public void setDouble(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, double value) {
      throw (new NucleusException(this.failureMessage("setDouble"))).setFatal();
   }

   public double getDouble(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getDouble"))).setFatal();
   }

   public void setString(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, String value) {
      throw (new NucleusException(this.failureMessage("setString"))).setFatal();
   }

   public String getString(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getString"))).setFatal();
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      throw (new NucleusException(this.failureMessage("setObject"))).setFatal();
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      throw (new NucleusException(this.failureMessage("setObject"))).setFatal();
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] exprIndex, ObjectProvider ownerOP, int ownerFieldNumber) {
      throw (new NucleusException(this.failureMessage("getObject"))).setFatal();
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] exprIndex) {
      throw (new NucleusException(this.failureMessage("getObject"))).setFatal();
   }

   protected static ColumnMetaData[] getColumnMetaDataForMember(AbstractMemberMetaData mmd, FieldRole role) {
      if (mmd == null) {
         return null;
      } else {
         ColumnMetaData[] colmds = null;
         if (role != FieldRole.ROLE_COLLECTION_ELEMENT && role != FieldRole.ROLE_ARRAY_ELEMENT) {
            if (role == FieldRole.ROLE_MAP_KEY) {
               if (mmd.getJoinMetaData() != null && mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getColumnMetaData() != null) {
                  colmds = mmd.getKeyMetaData().getColumnMetaData();
               }
            } else if (role == FieldRole.ROLE_MAP_VALUE) {
               if (mmd.getJoinMetaData() != null && mmd.getValueMetaData() != null && mmd.getValueMetaData().getColumnMetaData() != null) {
                  colmds = mmd.getValueMetaData().getColumnMetaData();
               }
            } else if (mmd.getColumnMetaData() != null && mmd.getColumnMetaData().length > 0) {
               colmds = mmd.getColumnMetaData();
            }
         } else if (mmd.getJoinMetaData() != null && mmd.getElementMetaData() != null && mmd.getElementMetaData().getColumnMetaData() != null) {
            colmds = mmd.getElementMetaData().getColumnMetaData();
         }

         return colmds;
      }
   }
}
