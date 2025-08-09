package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;

public class PolyhedronsSet extends AbstractRegion {
   private static final double DEFAULT_TOLERANCE = 1.0E-10;

   public PolyhedronsSet(double tolerance) {
      super(tolerance);
   }

   public PolyhedronsSet(BSPTree tree, double tolerance) {
      super(tree, tolerance);
   }

   public PolyhedronsSet(Collection boundary, double tolerance) {
      super(boundary, tolerance);
   }

   public PolyhedronsSet(List vertices, List facets, double tolerance) {
      super((Collection)buildBoundary(vertices, facets, tolerance), tolerance);
   }

   public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
      super(buildBoundary(xMin, xMax, yMin, yMax, zMin, zMax, tolerance), tolerance);
   }

   /** @deprecated */
   @Deprecated
   public PolyhedronsSet() {
      this(1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolyhedronsSet(BSPTree tree) {
      this(tree, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolyhedronsSet(Collection boundary) {
      this(boundary, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
      this(xMin, xMax, yMin, yMax, zMin, zMax, 1.0E-10);
   }

   private static BSPTree buildBoundary(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
      if (!(xMin >= xMax - tolerance) && !(yMin >= yMax - tolerance) && !(zMin >= zMax - tolerance)) {
         Plane pxMin = new Plane(new Vector3D(xMin, (double)0.0F, (double)0.0F), Vector3D.MINUS_I, tolerance);
         Plane pxMax = new Plane(new Vector3D(xMax, (double)0.0F, (double)0.0F), Vector3D.PLUS_I, tolerance);
         Plane pyMin = new Plane(new Vector3D((double)0.0F, yMin, (double)0.0F), Vector3D.MINUS_J, tolerance);
         Plane pyMax = new Plane(new Vector3D((double)0.0F, yMax, (double)0.0F), Vector3D.PLUS_J, tolerance);
         Plane pzMin = new Plane(new Vector3D((double)0.0F, (double)0.0F, zMin), Vector3D.MINUS_K, tolerance);
         Plane pzMax = new Plane(new Vector3D((double)0.0F, (double)0.0F, zMax), Vector3D.PLUS_K, tolerance);
         Region<Euclidean3D> boundary = (new RegionFactory()).buildConvex(pxMin, pxMax, pyMin, pyMax, pzMin, pzMax);
         return boundary.getTree(false);
      } else {
         return new BSPTree(Boolean.FALSE);
      }
   }

   private static List buildBoundary(List vertices, List facets, double tolerance) {
      for(int i = 0; i < vertices.size() - 1; ++i) {
         Vector3D vi = (Vector3D)vertices.get(i);

         for(int j = i + 1; j < vertices.size(); ++j) {
            if (Vector3D.distance(vi, (Vector3D)vertices.get(j)) <= tolerance) {
               throw new MathIllegalArgumentException(LocalizedFormats.CLOSE_VERTICES, new Object[]{vi.getX(), vi.getY(), vi.getZ()});
            }
         }
      }

      int[][] references = findReferences(vertices, facets);
      int[][] successors = successors(vertices, facets, references);

      for(int vA = 0; vA < vertices.size(); ++vA) {
         for(int vB : successors[vA]) {
            if (vB >= 0) {
               boolean found = false;

               for(int v : successors[vB]) {
                  found = found || v == vA;
               }

               if (!found) {
                  Vector3D start = (Vector3D)vertices.get(vA);
                  Vector3D end = (Vector3D)vertices.get(vB);
                  throw new MathIllegalArgumentException(LocalizedFormats.EDGE_CONNECTED_TO_ONE_FACET, new Object[]{start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ()});
               }
            }
         }
      }

      List<SubHyperplane<Euclidean3D>> boundary = new ArrayList();

      for(int[] facet : facets) {
         Plane plane = new Plane((Vector3D)vertices.get(facet[0]), (Vector3D)vertices.get(facet[1]), (Vector3D)vertices.get(facet[2]), tolerance);
         Vector2D[] two2Points = new Vector2D[facet.length];

         for(int i = 0; i < facet.length; ++i) {
            Vector3D v = (Vector3D)vertices.get(facet[i]);
            if (!plane.contains(v)) {
               throw new MathIllegalArgumentException(LocalizedFormats.OUT_OF_PLANE, new Object[]{v.getX(), v.getY(), v.getZ()});
            }

            two2Points[i] = plane.toSubSpace((Vector)v);
         }

         boundary.add(new SubPlane(plane, new PolygonsSet(tolerance, two2Points)));
      }

      return boundary;
   }

   private static int[][] findReferences(List vertices, List facets) {
      int[] nbFacets = new int[vertices.size()];
      int maxFacets = 0;

      for(int[] facet : facets) {
         if (facet.length < 3) {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 3, facet.length, true);
         }

         for(int index : facet) {
            maxFacets = FastMath.max(maxFacets, ++nbFacets[index]);
         }
      }

      int[][] references = new int[vertices.size()][maxFacets];

      for(int[] r : references) {
         Arrays.fill(r, -1);
      }

      for(int f = 0; f < facets.size(); ++f) {
         for(int v : (int[])facets.get(f)) {
            int k;
            for(k = 0; k < maxFacets && references[v][k] >= 0; ++k) {
            }

            references[v][k] = f;
         }
      }

      return references;
   }

   private static int[][] successors(List vertices, List facets, int[][] references) {
      int[][] successors = new int[vertices.size()][references[0].length];

      for(int[] s : successors) {
         Arrays.fill(s, -1);
      }

      for(int v = 0; v < vertices.size(); ++v) {
         for(int k = 0; k < successors[v].length && references[v][k] >= 0; ++k) {
            int[] facet = (int[])facets.get(references[v][k]);

            int i;
            for(i = 0; i < facet.length && facet[i] != v; ++i) {
            }

            successors[v][k] = facet[(i + 1) % facet.length];

            for(int l = 0; l < k; ++l) {
               if (successors[v][l] == successors[v][k]) {
                  Vector3D start = (Vector3D)vertices.get(v);
                  Vector3D end = (Vector3D)vertices.get(successors[v][k]);
                  throw new MathIllegalArgumentException(LocalizedFormats.FACET_ORIENTATION_MISMATCH, new Object[]{start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ()});
               }
            }
         }
      }

      return successors;
   }

   public PolyhedronsSet buildNew(BSPTree tree) {
      return new PolyhedronsSet(tree, this.getTolerance());
   }

   protected void computeGeometricalProperties() {
      this.getTree(true).visit(new FacetsContributionVisitor());
      if (this.getSize() < (double)0.0F) {
         this.setSize(Double.POSITIVE_INFINITY);
         this.setBarycenter(Vector3D.NaN);
      } else {
         this.setSize(this.getSize() / (double)3.0F);
         this.setBarycenter(new Vector3D((double)1.0F / ((double)4.0F * this.getSize()), (Vector3D)this.getBarycenter()));
      }

   }

   public SubHyperplane firstIntersection(Vector3D point, Line line) {
      return this.recurseFirstIntersection(this.getTree(true), point, line);
   }

   private SubHyperplane recurseFirstIntersection(BSPTree node, Vector3D point, Line line) {
      SubHyperplane<Euclidean3D> cut = node.getCut();
      if (cut == null) {
         return null;
      } else {
         BSPTree<Euclidean3D> minus = node.getMinus();
         BSPTree<Euclidean3D> plus = node.getPlus();
         Plane plane = (Plane)cut.getHyperplane();
         double offset = plane.getOffset((Point)point);
         boolean in = FastMath.abs(offset) < this.getTolerance();
         BSPTree<Euclidean3D> near;
         BSPTree<Euclidean3D> far;
         if (offset < (double)0.0F) {
            near = minus;
            far = plus;
         } else {
            near = plus;
            far = minus;
         }

         if (in) {
            SubHyperplane<Euclidean3D> facet = this.boundaryFacet(point, node);
            if (facet != null) {
               return facet;
            }
         }

         SubHyperplane<Euclidean3D> crossed = this.recurseFirstIntersection(near, point, line);
         if (crossed != null) {
            return crossed;
         } else {
            if (!in) {
               Vector3D hit3D = plane.intersection(line);
               if (hit3D != null && line.getAbscissa(hit3D) > line.getAbscissa(point)) {
                  SubHyperplane<Euclidean3D> facet = this.boundaryFacet(hit3D, node);
                  if (facet != null) {
                     return facet;
                  }
               }
            }

            return this.recurseFirstIntersection(far, point, line);
         }
      }
   }

   private SubHyperplane boundaryFacet(Vector3D point, BSPTree node) {
      Vector2D point2D = ((Plane)node.getCut().getHyperplane()).toSubSpace((Point)point);
      BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute)node.getAttribute();
      if (attribute.getPlusOutside() != null && ((SubPlane)attribute.getPlusOutside()).getRemainingRegion().checkPoint(point2D) == Region.Location.INSIDE) {
         return attribute.getPlusOutside();
      } else {
         return attribute.getPlusInside() != null && ((SubPlane)attribute.getPlusInside()).getRemainingRegion().checkPoint(point2D) == Region.Location.INSIDE ? attribute.getPlusInside() : null;
      }
   }

   public PolyhedronsSet rotate(Vector3D center, Rotation rotation) {
      return (PolyhedronsSet)this.applyTransform(new RotationTransform(center, rotation));
   }

   public PolyhedronsSet translate(Vector3D translation) {
      return (PolyhedronsSet)this.applyTransform(new TranslationTransform(translation));
   }

   private class FacetsContributionVisitor implements BSPTreeVisitor {
      FacetsContributionVisitor() {
         PolyhedronsSet.this.setSize((double)0.0F);
         PolyhedronsSet.this.setBarycenter(new Vector3D((double)0.0F, (double)0.0F, (double)0.0F));
      }

      public BSPTreeVisitor.Order visitOrder(BSPTree node) {
         return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
      }

      public void visitInternalNode(BSPTree node) {
         BoundaryAttribute<Euclidean3D> attribute = (BoundaryAttribute)node.getAttribute();
         if (attribute.getPlusOutside() != null) {
            this.addContribution(attribute.getPlusOutside(), false);
         }

         if (attribute.getPlusInside() != null) {
            this.addContribution(attribute.getPlusInside(), true);
         }

      }

      public void visitLeafNode(BSPTree node) {
      }

      private void addContribution(SubHyperplane facet, boolean reversed) {
         Region<Euclidean2D> polygon = ((SubPlane)facet).getRemainingRegion();
         double area = polygon.getSize();
         if (Double.isInfinite(area)) {
            PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
            PolyhedronsSet.this.setBarycenter(Vector3D.NaN);
         } else {
            Plane plane = (Plane)facet.getHyperplane();
            Vector3D facetB = plane.toSpace(polygon.getBarycenter());
            double scaled = area * facetB.dotProduct(plane.getNormal());
            if (reversed) {
               scaled = -scaled;
            }

            PolyhedronsSet.this.setSize(PolyhedronsSet.this.getSize() + scaled);
            PolyhedronsSet.this.setBarycenter(new Vector3D((double)1.0F, (Vector3D)PolyhedronsSet.this.getBarycenter(), scaled, facetB));
         }

      }
   }

   private static class RotationTransform implements Transform {
      private Vector3D center;
      private Rotation rotation;
      private Plane cachedOriginal;
      private Transform cachedTransform;

      RotationTransform(Vector3D center, Rotation rotation) {
         this.center = center;
         this.rotation = rotation;
      }

      public Vector3D apply(Point point) {
         Vector3D delta = ((Vector3D)point).subtract(this.center);
         return new Vector3D((double)1.0F, this.center, (double)1.0F, this.rotation.applyTo(delta));
      }

      public Plane apply(Hyperplane hyperplane) {
         return ((Plane)hyperplane).rotate(this.center, this.rotation);
      }

      public SubHyperplane apply(SubHyperplane sub, Hyperplane original, Hyperplane transformed) {
         if (original != this.cachedOriginal) {
            Plane oPlane = (Plane)original;
            Plane tPlane = (Plane)transformed;
            Vector3D p00 = oPlane.getOrigin();
            Vector3D p10 = oPlane.toSpace((Point)(new Vector2D((double)1.0F, (double)0.0F)));
            Vector3D p01 = oPlane.toSpace((Point)(new Vector2D((double)0.0F, (double)1.0F)));
            Vector2D tP00 = tPlane.toSubSpace((Point)this.apply((Point)p00));
            Vector2D tP10 = tPlane.toSubSpace((Point)this.apply((Point)p10));
            Vector2D tP01 = tPlane.toSubSpace((Point)this.apply((Point)p01));
            this.cachedOriginal = (Plane)original;
            this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(tP10.getX() - tP00.getX(), tP10.getY() - tP00.getY(), tP01.getX() - tP00.getX(), tP01.getY() - tP00.getY(), tP00.getX(), tP00.getY());
         }

         return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine)sub).applyTransform(this.cachedTransform);
      }
   }

   private static class TranslationTransform implements Transform {
      private Vector3D translation;
      private Plane cachedOriginal;
      private Transform cachedTransform;

      TranslationTransform(Vector3D translation) {
         this.translation = translation;
      }

      public Vector3D apply(Point point) {
         return new Vector3D((double)1.0F, (Vector3D)point, (double)1.0F, this.translation);
      }

      public Plane apply(Hyperplane hyperplane) {
         return ((Plane)hyperplane).translate(this.translation);
      }

      public SubHyperplane apply(SubHyperplane sub, Hyperplane original, Hyperplane transformed) {
         if (original != this.cachedOriginal) {
            Plane oPlane = (Plane)original;
            Plane tPlane = (Plane)transformed;
            Vector2D shift = tPlane.toSubSpace((Point)this.apply((Point)oPlane.getOrigin()));
            this.cachedOriginal = (Plane)original;
            this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform((double)1.0F, (double)0.0F, (double)0.0F, (double)1.0F, shift.getX(), shift.getY());
         }

         return ((org.apache.commons.math3.geometry.euclidean.twod.SubLine)sub).applyTransform(this.cachedTransform);
      }
   }
}
