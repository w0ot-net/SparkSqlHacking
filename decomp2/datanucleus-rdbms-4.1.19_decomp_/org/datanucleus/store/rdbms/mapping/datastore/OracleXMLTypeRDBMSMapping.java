package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class OracleXMLTypeRDBMSMapping extends CharRDBMSMapping {
   public OracleXMLTypeRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   protected void initialize() {
      this.initTypeInfo();
   }

   public int getJDBCType() {
      return 2007;
   }

   public String getString(ResultSet rs, int param) {
      String value = null;

      try {
         OPAQUE o = (OPAQUE)rs.getObject(param);
         if (o != null) {
            value = XMLType.createXML(o).getStringVal();
         }

         if (this.getDatastoreAdapter().supportsOption("NullEqualsEmptyString") && value != null && value.equals(this.getDatastoreAdapter().getSurrogateForEmptyStrings())) {
            value = "";
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setString(PreparedStatement ps, int param, String value) {
      try {
         if (value == null) {
            if (this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setString(param, this.column.getDefaultValue().toString().trim());
            } else {
               ps.setNull(param, this.getJDBCType(), "SYS.XMLTYPE");
            }
         } else {
            ps.setString(param, value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      }
   }
}
