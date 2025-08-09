package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;

class EdgesBuilder implements BSPTreeVisitor {
   private final BSPTree root;
   private final double tolerance;
   private final Map edgeToNode;
   private final Map nodeToEdgesList;

   EdgesBuilder(BSPTree root, double tolerance) {
      this.root = root;
      this.tolerance = tolerance;
      this.edgeToNode = new IdentityHashMap();
      this.nodeToEdgesList = new IdentityHashMap();
   }

   public BSPTreeVisitor.Order visitOrder(BSPTree node) {
      return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
   }

   public void visitInternalNode(BSPTree node) {
      this.nodeToEdgesList.put(node, new ArrayList());
      BoundaryAttribute<Sphere2D> attribute = (BoundaryAttribute)node.getAttribute();
      if (attribute.getPlusOutside() != null) {
         this.addContribution((SubCircle)attribute.getPlusOutside(), false, node);
      }

      if (attribute.getPlusInside() != null) {
         this.addContribution((SubCircle)attribute.getPlusInside(), true, node);
      }

   }

   public void visitLeafNode(BSPTree node) {
   }

   private void addContribution(SubCircle sub, boolean reversed, BSPTree node) {
      Circle circle = (Circle)sub.getHyperplane();

      for(Arc a : ((ArcsSet)sub.getRemainingRegion()).asList()) {
         Vertex start = new Vertex(circle.toSpace(new S1Point(a.getInf())));
         Vertex end = new Vertex(circle.toSpace(new S1Point(a.getSup())));
         start.bindWith(circle);
         end.bindWith(circle);
         Edge edge;
         if (reversed) {
            edge = new Edge(end, start, a.getSize(), circle.getReverse());
         } else {
            edge = new Edge(start, end, a.getSize(), circle);
         }

         this.edgeToNode.put(edge, node);
         ((List)this.nodeToEdgesList.get(node)).add(edge);
      }

   }

   private Edge getFollowingEdge(Edge previous) throws MathIllegalStateException {
      S2Point point = previous.getEnd().getLocation();
      List<BSPTree<Sphere2D>> candidates = this.root.getCloseCuts(point, this.tolerance);
      double closest = this.tolerance;
      Edge following = null;

      for(BSPTree node : candidates) {
         for(Edge edge : (List)this.nodeToEdgesList.get(node)) {
            if (edge != previous && edge.getStart().getIncoming() == null) {
               Vector3D edgeStart = edge.getStart().getLocation().getVector();
               double gap = Vector3D.angle(point.getVector(), edgeStart);
               if (gap <= closest) {
                  closest = gap;
                  following = edge;
               }
            }
         }
      }

      if (following == null) {
         Vector3D previousStart = previous.getStart().getLocation().getVector();
         if (Vector3D.angle(point.getVector(), previousStart) <= this.tolerance) {
            return previous;
         } else {
            throw new MathIllegalStateException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]);
         }
      } else {
         return following;
      }
   }

   public List getEdges() throws MathIllegalStateException {
      for(Edge previous : this.edgeToNode.keySet()) {
         previous.setNextEdge(this.getFollowingEdge(previous));
      }

      return new ArrayList(this.edgeToNode.keySet());
   }
}
