package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.WelzlEncloser;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.SphereGenerator;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class SphericalPolygonsSet extends AbstractRegion {
   private List loops;

   public SphericalPolygonsSet(double tolerance) {
      super(tolerance);
   }

   public SphericalPolygonsSet(Vector3D pole, double tolerance) {
      super(new BSPTree((new Circle(pole, tolerance)).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null), tolerance);
   }

   public SphericalPolygonsSet(Vector3D center, Vector3D meridian, double outsideRadius, int n, double tolerance) {
      this(tolerance, createRegularPolygonVertices(center, meridian, outsideRadius, n));
   }

   public SphericalPolygonsSet(BSPTree tree, double tolerance) {
      super(tree, tolerance);
   }

   public SphericalPolygonsSet(Collection boundary, double tolerance) {
      super(boundary, tolerance);
   }

   public SphericalPolygonsSet(double hyperplaneThickness, S2Point... vertices) {
      super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
   }

   private static S2Point[] createRegularPolygonVertices(Vector3D center, Vector3D meridian, double outsideRadius, int n) {
      S2Point[] array = new S2Point[n];
      Rotation r0 = new Rotation(Vector3D.crossProduct(center, meridian), outsideRadius, RotationConvention.VECTOR_OPERATOR);
      array[0] = new S2Point(r0.applyTo(center));
      Rotation r = new Rotation(center, (Math.PI * 2D) / (double)n, RotationConvention.VECTOR_OPERATOR);

      for(int i = 1; i < n; ++i) {
         array[i] = new S2Point(r.applyTo(array[i - 1].getVector()));
      }

      return array;
   }

   private static BSPTree verticesToTree(double hyperplaneThickness, S2Point... vertices) {
      int n = vertices.length;
      if (n == 0) {
         return new BSPTree(Boolean.TRUE);
      } else {
         Vertex[] vArray = new Vertex[n];

         for(int i = 0; i < n; ++i) {
            vArray[i] = new Vertex(vertices[i]);
         }

         List<Edge> edges = new ArrayList(n);
         Vertex end = vArray[n - 1];

         for(int i = 0; i < n; ++i) {
            Vertex start = end;
            end = vArray[i];
            Circle circle = start.sharedCircleWith(end);
            if (circle == null) {
               circle = new Circle(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }

            edges.add(new Edge(start, end, Vector3D.angle(start.getLocation().getVector(), end.getLocation().getVector()), circle));

            for(Vertex vertex : vArray) {
               if (vertex != start && vertex != end && FastMath.abs(circle.getOffset((Point)vertex.getLocation())) <= hyperplaneThickness) {
                  vertex.bindWith(circle);
               }
            }
         }

         BSPTree<Sphere2D> tree = new BSPTree();
         insertEdges(hyperplaneThickness, tree, edges);
         return tree;
      }
   }

   private static void insertEdges(double hyperplaneThickness, BSPTree node, List edges) {
      int index = 0;
      Edge inserted = null;

      while(inserted == null && index < edges.size()) {
         inserted = (Edge)edges.get(index++);
         if (!node.insertCut(inserted.getCircle())) {
            inserted = null;
         }
      }

      if (inserted != null) {
         List<Edge> outsideList = new ArrayList();
         List<Edge> insideList = new ArrayList();

         for(Edge edge : edges) {
            if (edge != inserted) {
               edge.split(inserted.getCircle(), outsideList, insideList);
            }
         }

         if (!outsideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), outsideList);
         } else {
            node.getPlus().setAttribute(Boolean.FALSE);
         }

         if (!insideList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), insideList);
         } else {
            node.getMinus().setAttribute(Boolean.TRUE);
         }

      } else {
         BSPTree<Sphere2D> parent = node.getParent();
         if (parent != null && node != parent.getMinus()) {
            node.setAttribute(Boolean.FALSE);
         } else {
            node.setAttribute(Boolean.TRUE);
         }

      }
   }

   public SphericalPolygonsSet buildNew(BSPTree tree) {
      return new SphericalPolygonsSet(tree, this.getTolerance());
   }

   protected void computeGeometricalProperties() throws MathIllegalStateException {
      BSPTree<Sphere2D> tree = this.getTree(true);
      if (tree.getCut() == null) {
         if (tree.getCut() == null && (Boolean)tree.getAttribute()) {
            this.setSize(12.566370614359172);
            this.setBarycenter(new S2Point((double)0.0F, (double)0.0F));
         } else {
            this.setSize((double)0.0F);
            this.setBarycenter(S2Point.NaN);
         }
      } else {
         PropertiesComputer pc = new PropertiesComputer(this.getTolerance());
         tree.visit(pc);
         this.setSize(pc.getArea());
         this.setBarycenter(pc.getBarycenter());
      }

   }

   public List getBoundaryLoops() throws MathIllegalStateException {
      if (this.loops == null) {
         if (this.getTree(false).getCut() == null) {
            this.loops = Collections.emptyList();
         } else {
            BSPTree<Sphere2D> root = this.getTree(true);
            EdgesBuilder visitor = new EdgesBuilder(root, this.getTolerance());
            root.visit(visitor);
            List<Edge> edges = visitor.getEdges();

            Edge edge;
            for(this.loops = new ArrayList(); !edges.isEmpty(); edge = edge.getEnd().getOutgoing()) {
               edge = (Edge)edges.get(0);
               Vertex startVertex = edge.getStart();
               this.loops.add(startVertex);

               while(true) {
                  Iterator<Edge> iterator = edges.iterator();

                  while(iterator.hasNext()) {
                     if (iterator.next() == edge) {
                        iterator.remove();
                        break;
                     }
                  }

               }
            }
         }
      }

      return Collections.unmodifiableList(this.loops);
   }

   public EnclosingBall getEnclosingCap() {
      if (this.isEmpty()) {
         return new EnclosingBall(S2Point.PLUS_K, Double.NEGATIVE_INFINITY, new S2Point[0]);
      } else if (this.isFull()) {
         return new EnclosingBall(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);
      } else {
         BSPTree<Sphere2D> root = this.getTree(false);
         if (this.isEmpty(root.getMinus()) && this.isFull(root.getPlus())) {
            Circle circle = (Circle)root.getCut().getHyperplane();
            return new EnclosingBall((new S2Point(circle.getPole())).negate(), (Math.PI / 2D), new S2Point[0]);
         } else if (this.isFull(root.getMinus()) && this.isEmpty(root.getPlus())) {
            Circle circle = (Circle)root.getCut().getHyperplane();
            return new EnclosingBall(new S2Point(circle.getPole()), (Math.PI / 2D), new S2Point[0]);
         } else {
            List<Vector3D> points = this.getInsidePoints();

            for(Vertex loopStart : this.getBoundaryLoops()) {
               int count = 0;

               for(Vertex v = loopStart; count == 0 || v != loopStart; v = v.getOutgoing().getEnd()) {
                  ++count;
                  points.add(v.getLocation().getVector());
               }
            }

            SphereGenerator generator = new SphereGenerator();
            WelzlEncloser<Euclidean3D, Vector3D> encloser = new WelzlEncloser(this.getTolerance(), generator);
            EnclosingBall<Euclidean3D, Vector3D> enclosing3D = encloser.enclose(points);
            Vector3D[] support3D = (Vector3D[])enclosing3D.getSupport();
            double r = enclosing3D.getRadius();
            double h = ((Vector3D)enclosing3D.getCenter()).getNorm();
            if (h < this.getTolerance()) {
               EnclosingBall<Sphere2D, S2Point> enclosingS2 = new EnclosingBall(S2Point.PLUS_K, Double.POSITIVE_INFINITY, new S2Point[0]);

               for(Vector3D outsidePoint : this.getOutsidePoints()) {
                  S2Point outsideS2 = new S2Point(outsidePoint);
                  BoundaryProjection<Sphere2D> projection = this.projectToBoundary(outsideS2);
                  if (Math.PI - projection.getOffset() < enclosingS2.getRadius()) {
                     enclosingS2 = new EnclosingBall(outsideS2.negate(), Math.PI - projection.getOffset(), new S2Point[]{(S2Point)projection.getProjected()});
                  }
               }

               return enclosingS2;
            } else {
               S2Point[] support = new S2Point[support3D.length];

               for(int i = 0; i < support3D.length; ++i) {
                  support[i] = new S2Point(support3D[i]);
               }

               EnclosingBall<Sphere2D, S2Point> enclosingS2 = new EnclosingBall(new S2Point((Vector3D)enclosing3D.getCenter()), FastMath.acos(((double)1.0F + h * h - r * r) / ((double)2.0F * h)), support);
               return enclosingS2;
            }
         }
      }
   }

   private List getInsidePoints() {
      PropertiesComputer pc = new PropertiesComputer(this.getTolerance());
      this.getTree(true).visit(pc);
      return pc.getConvexCellsInsidePoints();
   }

   private List getOutsidePoints() {
      SphericalPolygonsSet complement = (SphericalPolygonsSet)(new RegionFactory()).getComplement(this);
      PropertiesComputer pc = new PropertiesComputer(this.getTolerance());
      complement.getTree(true).visit(pc);
      return pc.getConvexCellsInsidePoints();
   }
}
