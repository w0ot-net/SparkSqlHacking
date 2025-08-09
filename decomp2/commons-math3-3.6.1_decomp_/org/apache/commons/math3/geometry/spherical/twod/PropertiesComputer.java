package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.util.FastMath;

class PropertiesComputer implements BSPTreeVisitor {
   private final double tolerance;
   private double summedArea;
   private Vector3D summedBarycenter;
   private final List convexCellsInsidePoints;

   PropertiesComputer(double tolerance) {
      this.tolerance = tolerance;
      this.summedArea = (double)0.0F;
      this.summedBarycenter = Vector3D.ZERO;
      this.convexCellsInsidePoints = new ArrayList();
   }

   public BSPTreeVisitor.Order visitOrder(BSPTree node) {
      return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
   }

   public void visitInternalNode(BSPTree node) {
   }

   public void visitLeafNode(BSPTree node) {
      if ((Boolean)node.getAttribute()) {
         SphericalPolygonsSet convex = new SphericalPolygonsSet(node.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, (Object)null), this.tolerance);
         List<Vertex> boundary = convex.getBoundaryLoops();
         if (boundary.size() != 1) {
            throw new MathInternalError();
         }

         double area = this.convexCellArea((Vertex)boundary.get(0));
         Vector3D barycenter = this.convexCellBarycenter((Vertex)boundary.get(0));
         this.convexCellsInsidePoints.add(barycenter);
         this.summedArea += area;
         this.summedBarycenter = new Vector3D((double)1.0F, this.summedBarycenter, area, barycenter);
      }

   }

   private double convexCellArea(Vertex start) {
      int n = 0;
      double sum = (double)0.0F;

      for(Edge e = start.getOutgoing(); n == 0 || e.getStart() != start; e = e.getEnd().getOutgoing()) {
         Vector3D previousPole = e.getCircle().getPole();
         Vector3D nextPole = e.getEnd().getOutgoing().getCircle().getPole();
         Vector3D point = e.getEnd().getLocation().getVector();
         double alpha = FastMath.atan2(Vector3D.dotProduct(nextPole, Vector3D.crossProduct(point, previousPole)), -Vector3D.dotProduct(nextPole, previousPole));
         if (alpha < (double)0.0F) {
            alpha += (Math.PI * 2D);
         }

         sum += alpha;
         ++n;
      }

      return sum - (double)(n - 2) * Math.PI;
   }

   private Vector3D convexCellBarycenter(Vertex start) {
      int n = 0;
      Vector3D sumB = Vector3D.ZERO;

      for(Edge e = start.getOutgoing(); n == 0 || e.getStart() != start; e = e.getEnd().getOutgoing()) {
         sumB = new Vector3D((double)1.0F, sumB, e.getLength(), e.getCircle().getPole());
         ++n;
      }

      return sumB.normalize();
   }

   public double getArea() {
      return this.summedArea;
   }

   public S2Point getBarycenter() {
      return this.summedBarycenter.getNormSq() == (double)0.0F ? S2Point.NaN : new S2Point(this.summedBarycenter);
   }

   public List getConvexCellsInsidePoints() {
      return this.convexCellsInsidePoints;
   }
}
