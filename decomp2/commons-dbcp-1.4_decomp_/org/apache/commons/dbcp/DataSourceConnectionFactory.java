package org.apache.commons.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DataSourceConnectionFactory implements ConnectionFactory {
   protected String _uname;
   protected String _passwd;
   protected DataSource _source;

   public DataSourceConnectionFactory(DataSource source) {
      this(source, (String)null, (String)null);
   }

   public DataSourceConnectionFactory(DataSource source, String uname, String passwd) {
      this._uname = null;
      this._passwd = null;
      this._source = null;
      this._source = source;
      this._uname = uname;
      this._passwd = passwd;
   }

   public Connection createConnection() throws SQLException {
      return null == this._uname && null == this._passwd ? this._source.getConnection() : this._source.getConnection(this._uname, this._passwd);
   }
}
