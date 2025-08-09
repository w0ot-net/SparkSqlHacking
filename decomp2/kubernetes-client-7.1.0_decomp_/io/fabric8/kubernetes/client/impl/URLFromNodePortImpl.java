package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeAddress;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.NodeStatus;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.ServiceToURLProvider;
import io.fabric8.kubernetes.client.ServiceToURLProvider.ServiceToUrlImplPriority;
import io.fabric8.kubernetes.client.utils.internal.URLFromServiceUtil;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLFromNodePortImpl implements ServiceToURLProvider {
   public static final Logger logger = LoggerFactory.getLogger(URLFromNodePortImpl.class);

   public String getURL(Service service, String portName, String namespace, KubernetesClient client) {
      ServicePort port = URLFromServiceUtil.getServicePortByName(service, portName);
      String serviceProto = port.getProtocol();
      NodePortUrlComponents urlComponents = null;
      Integer nodePort = port.getNodePort();
      if (nodePort != null) {
         try {
            NodeList nodeList = (NodeList)client.nodes().list();
            if (nodeList != null && nodeList.getItems() != null) {
               for(Node item : nodeList.getItems()) {
                  urlComponents = this.getUrlComponentsFromNodeList(item.getStatus(), nodePort);
                  if (urlComponents != null) {
                     break;
                  }
               }
            }
         } catch (KubernetesClientException exception) {
            logger.warn("Could not find a node!", exception);
         }
      }

      return urlComponents != null ? (serviceProto + "://" + urlComponents.getClusterIP() + ":" + urlComponents.getPortNumber()).toLowerCase(Locale.ROOT) : null;
   }

   private NodePortUrlComponents getUrlComponentsFromNodeList(NodeStatus nodeStatus, Integer nodePort) {
      if (nodeStatus != null) {
         for(NodeAddress address : nodeStatus.getAddresses()) {
            String ip = address.getAddress();
            if (!ip.isEmpty()) {
               return new NodePortUrlComponents(ip, nodePort);
            }
         }
      }

      return null;
   }

   public int getPriority() {
      return ServiceToUrlImplPriority.SECOND.getValue();
   }

   private class NodePortUrlComponents {
      private String clusterIP;
      private Integer portNumber;

      public String getClusterIP() {
         return this.clusterIP;
      }

      public Integer getPortNumber() {
         return this.portNumber;
      }

      public NodePortUrlComponents(String clusterIP, Integer portNumber) {
         this.clusterIP = clusterIP;
         this.portNumber = portNumber;
      }
   }
}
