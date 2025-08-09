package org.apache.derby.osgi;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.AutoloadedDriver;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class EmbeddedActivator implements BundleActivator {
   public void start(BundleContext var1) {
      new AutoloadedDriver();
   }

   public void stop(BundleContext var1) {
      try {
         DriverManager.getConnection("jdbc:derby:;shutdown=true");
      } catch (SQLException var3) {
      }

   }
}
