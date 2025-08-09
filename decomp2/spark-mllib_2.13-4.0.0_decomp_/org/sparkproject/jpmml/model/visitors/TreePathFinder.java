package org.sparkproject.jpmml.model.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.tree.Node;

public class TreePathFinder extends AbstractVisitor implements Resettable {
   private Map paths = new HashMap();

   public void reset() {
      this.paths.clear();
   }

   public VisitorAction visit(Node node) {
      if (!node.hasNodes()) {
         this.process(node);
      }

      return super.visit(node);
   }

   private void process(Node node) {
      List<Node> path = new ArrayList();
      path.add(node);

      for(PMMLObject parent : this.getParents()) {
         if (!(parent instanceof Node)) {
            break;
         }

         path.add((Node)parent);
      }

      Collections.reverse(path);
      this.paths.put(node, path);
   }

   public Map getPaths() {
      return Collections.unmodifiableMap(this.paths);
   }
}
