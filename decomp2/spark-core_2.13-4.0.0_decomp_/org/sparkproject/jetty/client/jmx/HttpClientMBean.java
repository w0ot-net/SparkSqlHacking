package org.sparkproject.jetty.client.jmx;

import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.jmx.ObjectMBean;

public class HttpClientMBean extends ObjectMBean {
   public HttpClientMBean(Object managedObject) {
      super(managedObject);
   }

   public String getObjectContextBasis() {
      HttpClient httpClient = (HttpClient)this.getManagedObject();
      return httpClient.getName();
   }
}
