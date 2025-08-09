package org.apache.commons.math3.geometry.spherical.twod;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.util.MathUtils;

public class Edge {
   private final Vertex start;
   private Vertex end;
   private final double length;
   private final Circle circle;

   Edge(Vertex start, Vertex end, double length, Circle circle) {
      this.start = start;
      this.end = end;
      this.length = length;
      this.circle = circle;
      start.setOutgoing(this);
      end.setIncoming(this);
   }

   public Vertex getStart() {
      return this.start;
   }

   public Vertex getEnd() {
      return this.end;
   }

   public double getLength() {
      return this.length;
   }

   public Circle getCircle() {
      return this.circle;
   }

   public Vector3D getPointAt(double alpha) {
      return this.circle.getPointAt(alpha + this.circle.getPhase(this.start.getLocation().getVector()));
   }

   void setNextEdge(Edge next) {
      this.end = next.getStart();
      this.end.setIncoming(this);
      this.end.bindWith(this.getCircle());
   }

   void split(Circle splitCircle, List outsideList, List insideList) {
      double edgeStart = this.circle.getPhase(this.start.getLocation().getVector());
      Arc arc = this.circle.getInsideArc(splitCircle);
      double arcRelativeStart = MathUtils.normalizeAngle(arc.getInf(), edgeStart + Math.PI) - edgeStart;
      double arcRelativeEnd = arcRelativeStart + arc.getSize();
      double unwrappedEnd = arcRelativeEnd - (Math.PI * 2D);
      double tolerance = this.circle.getTolerance();
      Vertex previousVertex = this.start;
      if (unwrappedEnd >= this.length - tolerance) {
         insideList.add(this);
      } else {
         double alreadyManagedLength = (double)0.0F;
         if (unwrappedEnd >= (double)0.0F) {
            previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + unwrappedEnd))), unwrappedEnd, insideList, splitCircle);
            alreadyManagedLength = unwrappedEnd;
         }

         if (arcRelativeStart >= this.length - tolerance) {
            if (unwrappedEnd >= (double)0.0F) {
               this.addSubEdge(previousVertex, this.end, this.length - alreadyManagedLength, outsideList, splitCircle);
            } else {
               outsideList.add(this);
            }
         } else {
            previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - alreadyManagedLength, outsideList, splitCircle);
            if (arcRelativeEnd >= this.length - tolerance) {
               this.addSubEdge(previousVertex, this.end, this.length - arcRelativeStart, insideList, splitCircle);
            } else {
               previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - arcRelativeStart, insideList, splitCircle);
               this.addSubEdge(previousVertex, this.end, this.length - arcRelativeStart, outsideList, splitCircle);
            }
         }
      }

   }

   private Vertex addSubEdge(Vertex subStart, Vertex subEnd, double subLength, List list, Circle splitCircle) {
      if (subLength <= this.circle.getTolerance()) {
         return subStart;
      } else {
         subEnd.bindWith(splitCircle);
         Edge edge = new Edge(subStart, subEnd, subLength, this.circle);
         list.add(edge);
         return subEnd;
      }
   }
}
