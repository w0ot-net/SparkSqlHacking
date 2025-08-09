package org.apache.curator.ensemble.fixed;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Strings;

public class FixedEnsembleProvider implements EnsembleProvider {
   private final AtomicReference connectionString;
   private final boolean updateServerListEnabled;

   public FixedEnsembleProvider(String connectionString) {
      this(connectionString, true);
   }

   public FixedEnsembleProvider(String connectionString, boolean updateServerListEnabled) {
      this.connectionString = new AtomicReference();
      this.updateServerListEnabled = updateServerListEnabled;
      Preconditions.checkArgument(!Strings.isNullOrEmpty(connectionString), "connectionString cannot be null or empty");
      this.connectionString.set(connectionString);
   }

   public void start() throws Exception {
   }

   public void close() throws IOException {
   }

   public void setConnectionString(String connectionString) {
      this.connectionString.set(connectionString);
   }

   public String getConnectionString() {
      return (String)this.connectionString.get();
   }

   public boolean updateServerListEnabled() {
      return this.updateServerListEnabled;
   }
}
