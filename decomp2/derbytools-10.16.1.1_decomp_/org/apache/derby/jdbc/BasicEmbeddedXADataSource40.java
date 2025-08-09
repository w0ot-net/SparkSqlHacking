package org.apache.derby.jdbc;

import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import org.apache.derby.iapi.jdbc.EmbeddedXADataSourceInterface;
import org.apache.derby.iapi.jdbc.ResourceAdapter;
import org.apache.derby.impl.jdbc.EmbedXAConnection;

public class BasicEmbeddedXADataSource40 extends BasicEmbeddedDataSource40 implements EmbeddedXADataSourceInterface, XADataSource {
   private static final long serialVersionUID = -5715798975598379739L;
   private transient ResourceAdapter ra;

   public final XAConnection getXAConnection() throws SQLException {
      if (this.ra == null || !this.ra.isActive()) {
         this.ra = setupResourceAdapter(this, this.ra, (String)null, (String)null, false);
      }

      return this.createXAConnection(this.ra, this.getUser(), this.getPassword(), false);
   }

   public final XAConnection getXAConnection(String var1, String var2) throws SQLException {
      if (this.ra == null || !this.ra.isActive()) {
         this.ra = setupResourceAdapter(this, this.ra, var1, var2, true);
      }

      return this.createXAConnection(this.ra, var1, var2, true);
   }

   protected void update() {
      this.ra = null;
      super.update();
   }

   private XAConnection createXAConnection(ResourceAdapter var1, String var2, String var3, boolean var4) throws SQLException {
      this.findDriver();
      return new EmbedXAConnection(this, var1, var2, var3, var4);
   }

   public ResourceAdapter getResourceAdapter() {
      return this.ra;
   }
}
