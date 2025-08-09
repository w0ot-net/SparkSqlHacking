package org.apache.hadoop.hive.thrift;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;

public class DelegationTokenIdentifier extends AbstractDelegationTokenIdentifier {
   public static final Text HIVE_DELEGATION_KIND = new Text("HIVE_DELEGATION_TOKEN");

   public DelegationTokenIdentifier() {
   }

   public DelegationTokenIdentifier(Text owner, Text renewer, Text realUser) {
      super(owner, renewer, realUser);
   }

   public Text getKind() {
      return HIVE_DELEGATION_KIND;
   }
}
