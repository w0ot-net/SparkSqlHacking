package org.apache.hadoop.hive.thrift;

import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenSelector;

public class DelegationTokenSelector extends AbstractDelegationTokenSelector {
   public DelegationTokenSelector() {
      super(DelegationTokenIdentifier.HIVE_DELEGATION_KIND);
   }
}
