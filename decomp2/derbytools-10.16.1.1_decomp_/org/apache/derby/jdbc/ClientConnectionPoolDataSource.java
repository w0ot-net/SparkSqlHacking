package org.apache.derby.jdbc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import javax.sql.PooledConnection;
import org.apache.derby.client.ClientConnectionPoolDataSourceInterface;
import org.apache.derby.shared.common.i18n.MessageUtil;

public class ClientConnectionPoolDataSource extends ClientDataSource implements ClientConnectionPoolDataSourceInterface {
   private static final long serialVersionUID = -539234282156481377L;
   private static final MessageUtil msgUtil = new MessageUtil("org.apache.derby.loc.client.clientmessages");
   public static final String className__ = "org.apache.derby.jdbc.ClientConnectionPoolDataSource";
   private int maxStatements = 0;

   public PooledConnection getPooledConnection() throws SQLException {
      return this.getPooledConnectionMinion();
   }

   public PooledConnection getPooledConnection(String var1, String var2) throws SQLException {
      return this.getPooledConnectionMinion(var1, var2);
   }

   public void setMaxStatements(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(msgUtil.getTextMessage("J134", new Object[]{var1}));
      } else {
         this.maxStatements = var1;
      }
   }

   public int getMaxStatements() {
      return this.maxStatements;
   }

   public int maxStatementsToPool() {
      return this.maxStatements;
   }

   private final void validateState() {
      if (this.maxStatements < 0) {
         throw new IllegalArgumentException(msgUtil.getTextMessage("J134", new Object[]{this.maxStatements}));
      }
   }

   private void readObject(ObjectInputStream var1) throws ClassNotFoundException, IOException {
      var1.defaultReadObject();
      this.validateState();
   }
}
