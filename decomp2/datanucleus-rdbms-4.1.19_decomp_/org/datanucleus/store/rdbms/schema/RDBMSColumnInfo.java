package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.schema.ListStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class RDBMSColumnInfo implements ListStoreSchemaData {
   protected String tableCat;
   protected String tableSchem;
   protected String tableName;
   protected String columnName;
   protected short dataType;
   protected String typeName;
   protected int columnSize;
   protected int decimalDigits;
   protected int numPrecRadix;
   protected int nullable;
   protected String remarks;
   protected String columnDef;
   protected int charOctetLength;
   protected int ordinalPosition;
   protected String isNullable;
   private int hash = 0;
   RDBMSTableInfo tableInfo;

   public RDBMSColumnInfo(ResultSet rs) {
      try {
         this.tableCat = rs.getString(1);
         this.tableSchem = rs.getString(2);
         this.tableName = rs.getString(3);
         this.columnName = rs.getString(4);
         this.dataType = rs.getShort(5);
         this.typeName = rs.getString(6);
         this.columnSize = rs.getInt(7);
         this.decimalDigits = rs.getInt(9);
         this.numPrecRadix = rs.getInt(10);
         this.nullable = rs.getInt(11);
         this.remarks = rs.getString(12);
         this.columnDef = rs.getString(13);
         this.charOctetLength = rs.getInt(16);
         this.ordinalPosition = rs.getInt(17);
         this.isNullable = rs.getString(18);
      } catch (SQLException e) {
         throw (new NucleusDataStoreException("Can't read JDBC metadata from result set", e)).setFatal();
      }
   }

   public void setParent(StoreSchemaData parent) {
      this.tableInfo = (RDBMSTableInfo)parent;
   }

   public StoreSchemaData getParent() {
      return this.tableInfo;
   }

   public void setDecimalDigits(int digits) {
      this.decimalDigits = digits;
   }

   public void setDataType(short type) {
      this.dataType = type;
   }

   public void setColumnSize(int size) {
      this.columnSize = size;
   }

   public void setColumnDef(String def) {
      this.columnDef = def;
   }

   public int getDecimalDigits() {
      return this.decimalDigits;
   }

   public String getIsNullable() {
      return this.isNullable;
   }

   public int getNullable() {
      return this.nullable;
   }

   public int getColumnSize() {
      return this.columnSize;
   }

   public short getDataType() {
      return this.dataType;
   }

   public int getNumPrecRadix() {
      return this.numPrecRadix;
   }

   public int getCharOctetLength() {
      return this.charOctetLength;
   }

   public int getOrdinalPosition() {
      return this.ordinalPosition;
   }

   public String getColumnDef() {
      return this.columnDef;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public String getColumnName() {
      return this.columnName;
   }

   public String getTableCat() {
      return this.tableCat;
   }

   public String getTableSchem() {
      return this.tableSchem;
   }

   public void addProperty(String name, Object value) {
      throw new UnsupportedOperationException("SQLTypeInfo doesnt support properties");
   }

   public Object getProperty(String name) {
      throw new UnsupportedOperationException("SQLTypeInfo doesnt support properties");
   }

   public void addChild(StoreSchemaData child) {
   }

   public void clearChildren() {
   }

   public StoreSchemaData getChild(int position) {
      return null;
   }

   public List getChildren() {
      return null;
   }

   public int getNumberOfChildren() {
      return 0;
   }

   public final boolean equals(Object obj) {
      if (!(obj instanceof RDBMSColumnInfo)) {
         return false;
      } else {
         boolean var10000;
         label37: {
            RDBMSColumnInfo other = (RDBMSColumnInfo)obj;
            if (this.tableCat == null) {
               if (other.tableCat != null) {
                  break label37;
               }
            } else if (!this.tableCat.equals(other.tableCat)) {
               break label37;
            }

            if (this.tableSchem == null) {
               if (other.tableSchem != null) {
                  break label37;
               }
            } else if (!this.tableSchem.equals(other.tableSchem)) {
               break label37;
            }

            if (this.tableName.equals(other.tableName) && this.columnName.equals(other.columnName)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }

   public final int hashCode() {
      if (this.hash == 0) {
         this.hash = (this.tableCat == null ? 0 : this.tableCat.hashCode()) ^ (this.tableSchem == null ? 0 : this.tableSchem.hashCode()) ^ this.tableName.hashCode() ^ this.columnName.hashCode();
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("RDBMSColumnInfo : ");
      str.append("  tableCat        = " + this.tableCat + "\n");
      str.append("  tableSchem      = " + this.tableSchem + "\n");
      str.append("  tableName       = " + this.tableName + "\n");
      str.append("  columnName      = " + this.columnName + "\n");
      str.append("  dataType        = " + this.dataType + "\n");
      str.append("  typeName        = " + this.typeName + "\n");
      str.append("  columnSize      = " + this.columnSize + "\n");
      str.append("  decimalDigits   = " + this.decimalDigits + "\n");
      str.append("  numPrecRadix    = " + this.numPrecRadix + "\n");
      str.append("  nullable        = " + this.nullable + "\n");
      str.append("  remarks         = " + this.remarks + "\n");
      str.append("  columnDef       = " + this.columnDef + "\n");
      str.append("  charOctetLength = " + this.charOctetLength + "\n");
      str.append("  ordinalPosition = " + this.ordinalPosition + "\n");
      str.append("  isNullable      = " + this.isNullable + "\n");
      return str.toString();
   }
}
