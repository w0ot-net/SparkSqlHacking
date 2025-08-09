package org.apache.zookeeper.server.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CommandBase implements Command {
   private final String primaryName;
   private final Set names;
   private final boolean serverRequired;
   private final AuthRequest authRequest;

   protected CommandBase(List names) {
      this(names, true);
   }

   protected CommandBase(List names, boolean serverRequired) {
      this(names, serverRequired, (AuthRequest)null);
   }

   protected CommandBase(List names, boolean serverRequired, AuthRequest authRequest) {
      if (authRequest != null && !serverRequired) {
         throw new IllegalArgumentException("An active server is required for auth check");
      } else {
         this.primaryName = (String)names.get(0);
         this.names = new HashSet(names);
         this.serverRequired = serverRequired;
         this.authRequest = authRequest;
      }
   }

   public String getPrimaryName() {
      return this.primaryName;
   }

   public Set getNames() {
      return this.names;
   }

   public boolean isServerRequired() {
      return this.serverRequired;
   }

   public AuthRequest getAuthRequest() {
      return this.authRequest;
   }

   protected CommandResponse initializeResponse() {
      return new CommandResponse(this.primaryName);
   }
}
