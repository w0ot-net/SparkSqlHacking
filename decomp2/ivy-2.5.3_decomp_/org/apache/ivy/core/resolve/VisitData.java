package org.apache.ivy.core.resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitData {
   private IvyNode node;
   private Map visitNodes = new HashMap();

   public VisitData(IvyNode node) {
      this.node = node;
   }

   public void addVisitNode(VisitNode node) {
      String rootModuleConf = node.getRootModuleConf();
      this.getVisitNodes(rootModuleConf).add(node);
   }

   public List getVisitNodes(String rootModuleConf) {
      List<VisitNode> visits = (List)this.visitNodes.get(rootModuleConf);
      if (visits == null) {
         visits = new ArrayList();
         this.visitNodes.put(rootModuleConf, visits);
      }

      return visits;
   }

   public IvyNode getNode() {
      return this.node;
   }

   public void setNode(IvyNode node) {
      this.node = node;
   }

   public void addVisitNodes(String rootModuleConf, List visitNodes) {
      this.getVisitNodes(rootModuleConf).addAll(visitNodes);
   }
}
