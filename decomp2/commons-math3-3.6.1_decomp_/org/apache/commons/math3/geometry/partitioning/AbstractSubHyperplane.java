package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSubHyperplane implements SubHyperplane {
   private final Hyperplane hyperplane;
   private final Region remainingRegion;

   protected AbstractSubHyperplane(Hyperplane hyperplane, Region remainingRegion) {
      this.hyperplane = hyperplane;
      this.remainingRegion = remainingRegion;
   }

   protected abstract AbstractSubHyperplane buildNew(Hyperplane var1, Region var2);

   public AbstractSubHyperplane copySelf() {
      return this.buildNew(this.hyperplane.copySelf(), this.remainingRegion);
   }

   public Hyperplane getHyperplane() {
      return this.hyperplane;
   }

   public Region getRemainingRegion() {
      return this.remainingRegion;
   }

   public double getSize() {
      return this.remainingRegion.getSize();
   }

   public AbstractSubHyperplane reunite(SubHyperplane other) {
      AbstractSubHyperplane<S, T> o = (AbstractSubHyperplane)other;
      return this.buildNew(this.hyperplane, (new RegionFactory()).union(this.remainingRegion, o.remainingRegion));
   }

   public AbstractSubHyperplane applyTransform(Transform transform) {
      Hyperplane<S> tHyperplane = transform.apply(this.hyperplane);
      Map<BSPTree<T>, BSPTree<T>> map = new HashMap();
      BSPTree<T> tTree = this.recurseTransform(this.remainingRegion.getTree(false), tHyperplane, transform, map);

      for(Map.Entry entry : map.entrySet()) {
         if (((BSPTree)entry.getKey()).getCut() != null) {
            BoundaryAttribute<T> original = (BoundaryAttribute)((BSPTree)entry.getKey()).getAttribute();
            if (original != null) {
               BoundaryAttribute<T> transformed = (BoundaryAttribute)((BSPTree)entry.getValue()).getAttribute();

               for(BSPTree splitter : original.getSplitters()) {
                  transformed.getSplitters().add((BSPTree)map.get(splitter));
               }
            }
         }
      }

      return this.buildNew(tHyperplane, this.remainingRegion.buildNew(tTree));
   }

   private BSPTree recurseTransform(BSPTree node, Hyperplane transformed, Transform transform, Map map) {
      BSPTree<T> transformedNode;
      if (node.getCut() == null) {
         transformedNode = new BSPTree(node.getAttribute());
      } else {
         BoundaryAttribute<T> attribute = (BoundaryAttribute)node.getAttribute();
         if (attribute != null) {
            SubHyperplane<T> tPO = attribute.getPlusOutside() == null ? null : transform.apply(attribute.getPlusOutside(), this.hyperplane, transformed);
            SubHyperplane<T> tPI = attribute.getPlusInside() == null ? null : transform.apply(attribute.getPlusInside(), this.hyperplane, transformed);
            attribute = new BoundaryAttribute(tPO, tPI, new NodesSet());
         }

         transformedNode = new BSPTree(transform.apply(node.getCut(), this.hyperplane, transformed), this.recurseTransform(node.getPlus(), transformed, transform, map), this.recurseTransform(node.getMinus(), transformed, transform, map), attribute);
      }

      map.put(node, transformedNode);
      return transformedNode;
   }

   /** @deprecated */
   @Deprecated
   public Side side(Hyperplane hyper) {
      return this.split(hyper).getSide();
   }

   public abstract SubHyperplane.SplitSubHyperplane split(Hyperplane var1);

   public boolean isEmpty() {
      return this.remainingRegion.isEmpty();
   }
}
