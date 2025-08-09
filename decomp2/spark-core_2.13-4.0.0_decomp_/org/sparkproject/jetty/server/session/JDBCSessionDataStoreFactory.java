package org.sparkproject.jetty.server.session;

public class JDBCSessionDataStoreFactory extends AbstractSessionDataStoreFactory {
   DatabaseAdaptor _adaptor;
   JDBCSessionDataStore.SessionTableSchema _schema;

   public SessionDataStore getSessionDataStore(SessionHandler handler) {
      JDBCSessionDataStore ds = new JDBCSessionDataStore();
      ds.setDatabaseAdaptor(this._adaptor);
      ds.setSessionTableSchema(this._schema);
      ds.setGracePeriodSec(this.getGracePeriodSec());
      ds.setSavePeriodSec(this.getSavePeriodSec());
      return ds;
   }

   public void setDatabaseAdaptor(DatabaseAdaptor adaptor) {
      this._adaptor = adaptor;
   }

   public void setSessionTableSchema(JDBCSessionDataStore.SessionTableSchema schema) {
      this._schema = schema;
   }
}
