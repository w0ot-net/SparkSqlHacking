package org.apache.derby.impl.services.daemon;

import org.apache.derby.iapi.services.daemon.Serviceable;

class ServiceRecord {
   final Serviceable client;
   private final boolean onDemandOnly;
   final boolean subscriber;
   private boolean serviceRequest;

   ServiceRecord(Serviceable var1, boolean var2, boolean var3) {
      this.client = var1;
      this.onDemandOnly = var2;
      this.subscriber = var3;
   }

   final void serviced() {
      this.serviceRequest = false;
   }

   final boolean needImmediateService() {
      return this.serviceRequest;
   }

   final boolean needService() {
      return this.serviceRequest || !this.onDemandOnly;
   }

   final void called() {
      this.serviceRequest = true;
   }
}
