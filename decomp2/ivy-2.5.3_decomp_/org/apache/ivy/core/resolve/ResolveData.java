package org.apache.ivy.core.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.event.EventManager;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.apache.ivy.util.Message;

public class ResolveData {
   private ResolveEngine engine;
   private Map visitData;
   private ConfigurationResolveReport report;
   private ResolveOptions options;
   private VisitNode currentVisitNode;
   private ResolvedModuleRevision currentResolvedModuleRevision;

   public ResolveData(ResolveData data, boolean validate) {
      this(data.engine, (new ResolveOptions(data.options)).setValidate(validate), data.report, data.visitData);
      this.setCurrentVisitNode(data.currentVisitNode);
      this.setCurrentResolvedModuleRevision(data.currentResolvedModuleRevision);
   }

   public ResolveData(ResolveEngine engine, ResolveOptions options) {
      this(engine, options, (ConfigurationResolveReport)null, new LinkedHashMap());
   }

   public ResolveData(ResolveEngine engine, ResolveOptions options, ConfigurationResolveReport report) {
      this(engine, options, report, new LinkedHashMap());
   }

   public ResolveData(ResolveEngine engine, ResolveOptions options, ConfigurationResolveReport report, Map visitData) {
      this.currentVisitNode = null;
      this.engine = engine;
      this.report = report;
      this.visitData = visitData;
      this.options = options;
   }

   public ConfigurationResolveReport getReport() {
      return this.report;
   }

   public IvyNode getNode(ModuleRevisionId mrid) {
      VisitData visitData = this.getVisitData(mrid);
      return visitData == null ? null : visitData.getNode();
   }

   public Collection getNodes() {
      Collection<IvyNode> nodes = new ArrayList();

      for(VisitData vdata : this.visitData.values()) {
         nodes.add(vdata.getNode());
      }

      return nodes;
   }

   public Collection getNodeIds() {
      return this.visitData.keySet();
   }

   public VisitData getVisitData(ModuleRevisionId mrid) {
      VisitData result = (VisitData)this.visitData.get(mrid);
      if (result == null) {
         for(Map.Entry entry : this.visitData.entrySet()) {
            ModuleRevisionId current = (ModuleRevisionId)entry.getKey();
            if (isSubMap(mrid.getAttributes(), current.getAttributes())) {
               result = (VisitData)entry.getValue();
               break;
            }
         }
      }

      return result;
   }

   private static boolean isSubMap(Map map1, Map map2) {
      int map1Size = map1.size();
      int map2Size = map2.size();
      if (map1Size == map2Size) {
         return map1.equals(map2);
      } else {
         Map<K, V> smallest = map1Size < map2Size ? map1 : map2;
         Map<K, V> largest = map1Size < map2Size ? map2 : map1;

         for(Map.Entry entry : smallest.entrySet()) {
            if (!largest.containsKey(entry.getKey())) {
               return false;
            }

            Object map1Value = smallest.get(entry.getKey());
            Object map2Value = largest.get(entry.getKey());
            if (!isEqual(map1Value, map2Value)) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean isEqual(Object obj1, Object obj2) {
      return obj1 == obj2 || obj1 != null && obj2 != null && obj1.equals(obj2);
   }

   public VisitNode getCurrentVisitNode() {
      return this.currentVisitNode;
   }

   void setCurrentVisitNode(VisitNode currentVisitNode) {
      this.currentVisitNode = currentVisitNode;
   }

   public void register(VisitNode node) {
      this.register(node.getId(), node);
   }

   public void register(ModuleRevisionId mrid, VisitNode node) {
      VisitData visitData = this.getVisitData(mrid);
      if (visitData == null) {
         visitData = new VisitData(node.getNode());
         visitData.addVisitNode(node);
         this.visitData.put(mrid, visitData);
      } else {
         visitData.setNode(node.getNode());
         visitData.addVisitNode(node);
      }

   }

   void replaceNode(ModuleRevisionId mrid, IvyNode node, String rootModuleConf) {
      VisitData visitData = this.getVisitData(mrid);
      if (visitData == null) {
         throw new IllegalArgumentException("impossible to replace node for id " + mrid + ". No registered node found.");
      } else {
         VisitData keptVisitData = this.getVisitData(node.getId());
         if (keptVisitData == null) {
            throw new IllegalArgumentException("impossible to replace node with " + node + ". No registered node found for " + node.getId() + ".");
         } else {
            this.visitData.put(mrid, keptVisitData);
            keptVisitData.addVisitNodes(rootModuleConf, visitData.getVisitNodes(rootModuleConf));
            this.report.updateDependency(mrid, node);
         }
      }
   }

   public void setReport(ConfigurationResolveReport report) {
      this.report = report;
   }

   public Date getDate() {
      return this.options.getDate();
   }

   public boolean isValidate() {
      return this.options.isValidate();
   }

   public boolean isTransitive() {
      return this.options.isTransitive();
   }

   public ResolveOptions getOptions() {
      return this.options;
   }

   public ResolveEngineSettings getSettings() {
      return this.engine.getSettings();
   }

   public EventManager getEventManager() {
      return this.engine.getEventManager();
   }

   public ResolveEngine getEngine() {
      return this.engine;
   }

   void blacklist(IvyNode node) {
      Iterator<Map.Entry<ModuleRevisionId, VisitData>> iter = this.visitData.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry<ModuleRevisionId, VisitData> entry = (Map.Entry)iter.next();
         if (((VisitData)entry.getValue()).getNode() == node && !node.getResolvedId().equals(entry.getKey())) {
            iter.remove();
         }
      }

   }

   public boolean isBlacklisted(String rootModuleConf, ModuleRevisionId mrid) {
      IvyNode node = this.getNode(mrid);
      return node != null && node.isBlacklisted(rootModuleConf);
   }

   public DependencyDescriptor mediate(DependencyDescriptor dd) {
      DependencyDescriptor originalDD = dd;
      dd = this.getEngine().mediate(dd, this.getOptions());
      VisitNode current = this.getCurrentVisitNode();
      if (current != null) {
         List<VisitNode> dependers = new ArrayList(current.getPath());
         dependers.remove(dependers.size() - 1);
         Collections.reverse(dependers);

         for(VisitNode n : dependers) {
            ModuleDescriptor md = n.getDescriptor();
            if (md != null) {
               dd = md.mediate(dd);
            }
         }
      }

      if (originalDD != dd) {
         Message.verbose("dependency descriptor has been mediated: " + originalDD + " => " + dd);
      }

      return dd;
   }

   public void setCurrentResolvedModuleRevision(ResolvedModuleRevision mr) {
      this.currentResolvedModuleRevision = mr;
   }

   public ResolvedModuleRevision getCurrentResolvedModuleRevision() {
      return this.currentResolvedModuleRevision;
   }
}
