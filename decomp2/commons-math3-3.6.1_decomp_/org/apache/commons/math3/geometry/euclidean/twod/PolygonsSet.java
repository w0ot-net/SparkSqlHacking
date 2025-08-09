package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class PolygonsSet extends AbstractRegion {
   private static final double DEFAULT_TOLERANCE = 1.0E-10;
   private Vector2D[][] vertices;

   public PolygonsSet(double tolerance) {
      super(tolerance);
   }

   public PolygonsSet(BSPTree tree, double tolerance) {
      super(tree, tolerance);
   }

   public PolygonsSet(Collection boundary, double tolerance) {
      super(boundary, tolerance);
   }

   public PolygonsSet(double xMin, double xMax, double yMin, double yMax, double tolerance) {
      super((Hyperplane[])boxBoundary(xMin, xMax, yMin, yMax, tolerance), tolerance);
   }

   public PolygonsSet(double hyperplaneThickness, Vector2D... vertices) {
      super(verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
   }

   /** @deprecated */
   @Deprecated
   public PolygonsSet() {
      this(1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolygonsSet(BSPTree tree) {
      this(tree, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolygonsSet(Collection boundary) {
      this(boundary, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public PolygonsSet(double xMin, double xMax, double yMin, double yMax) {
      this(xMin, xMax, yMin, yMax, 1.0E-10);
   }

   private static Line[] boxBoundary(double xMin, double xMax, double yMin, double yMax, double tolerance) {
      if (!(xMin >= xMax - tolerance) && !(yMin >= yMax - tolerance)) {
         Vector2D minMin = new Vector2D(xMin, yMin);
         Vector2D minMax = new Vector2D(xMin, yMax);
         Vector2D maxMin = new Vector2D(xMax, yMin);
         Vector2D maxMax = new Vector2D(xMax, yMax);
         return new Line[]{new Line(minMin, maxMin, tolerance), new Line(maxMin, maxMax, tolerance), new Line(maxMax, minMax, tolerance), new Line(minMax, minMin, tolerance)};
      } else {
         return null;
      }
   }

   private static BSPTree verticesToTree(double hyperplaneThickness, Vector2D... vertices) {
      int n = vertices.length;
      if (n == 0) {
         return new BSPTree(Boolean.TRUE);
      } else {
         Vertex[] vArray = new Vertex[n];

         for(int i = 0; i < n; ++i) {
            vArray[i] = new Vertex(vertices[i]);
         }

         List<Edge> edges = new ArrayList(n);

         for(int i = 0; i < n; ++i) {
            Vertex start = vArray[i];
            Vertex end = vArray[(i + 1) % n];
            Line line = start.sharedLineWith(end);
            if (line == null) {
               line = new Line(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }

            edges.add(new Edge(start, end, line));

            for(Vertex vertex : vArray) {
               if (vertex != start && vertex != end && FastMath.abs(line.getOffset((Point)vertex.getLocation())) <= hyperplaneThickness) {
                  vertex.bindWith(line);
               }
            }
         }

         BSPTree<Euclidean2D> tree = new BSPTree();
         insertEdges(hyperplaneThickness, tree, edges);
         return tree;
      }
   }

   private static void insertEdges(double hyperplaneThickness, BSPTree node, List edges) {
      int index = 0;
      Edge inserted = null;

      while(inserted == null && index < edges.size()) {
         inserted = (Edge)edges.get(index++);
         if (inserted.getNode() == null) {
            if (node.insertCut(inserted.getLine())) {
               inserted.setNode(node);
            } else {
               inserted = null;
            }
         } else {
            inserted = null;
         }
      }

      if (inserted != null) {
         List<Edge> plusList = new ArrayList();
         List<Edge> minusList = new ArrayList();

         for(Edge edge : edges) {
            if (edge != inserted) {
               double startOffset = inserted.getLine().getOffset((Point)edge.getStart().getLocation());
               double endOffset = inserted.getLine().getOffset((Point)edge.getEnd().getLocation());
               Side startSide = FastMath.abs(startOffset) <= hyperplaneThickness ? Side.HYPER : (startOffset < (double)0.0F ? Side.MINUS : Side.PLUS);
               Side endSide = FastMath.abs(endOffset) <= hyperplaneThickness ? Side.HYPER : (endOffset < (double)0.0F ? Side.MINUS : Side.PLUS);
               switch (startSide) {
                  case PLUS:
                     if (endSide == Side.MINUS) {
                        Vertex splitPoint = edge.split(inserted.getLine());
                        minusList.add(splitPoint.getOutgoing());
                        plusList.add(splitPoint.getIncoming());
                     } else {
                        plusList.add(edge);
                     }
                     break;
                  case MINUS:
                     if (endSide == Side.PLUS) {
                        Vertex splitPoint = edge.split(inserted.getLine());
                        minusList.add(splitPoint.getIncoming());
                        plusList.add(splitPoint.getOutgoing());
                     } else {
                        minusList.add(edge);
                     }
                     break;
                  default:
                     if (endSide == Side.PLUS) {
                        plusList.add(edge);
                     } else if (endSide == Side.MINUS) {
                        minusList.add(edge);
                     }
               }
            }
         }

         if (!plusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getPlus(), plusList);
         } else {
            node.getPlus().setAttribute(Boolean.FALSE);
         }

         if (!minusList.isEmpty()) {
            insertEdges(hyperplaneThickness, node.getMinus(), minusList);
         } else {
            node.getMinus().setAttribute(Boolean.TRUE);
         }

      } else {
         BSPTree<Euclidean2D> parent = node.getParent();
         if (parent != null && node != parent.getMinus()) {
            node.setAttribute(Boolean.FALSE);
         } else {
            node.setAttribute(Boolean.TRUE);
         }

      }
   }

   public PolygonsSet buildNew(BSPTree tree) {
      return new PolygonsSet(tree, this.getTolerance());
   }

   protected void computeGeometricalProperties() {
      Vector2D[][] v = this.getVertices();
      if (v.length == 0) {
         BSPTree<Euclidean2D> tree = this.getTree(false);
         if (tree.getCut() == null && (Boolean)tree.getAttribute()) {
            this.setSize(Double.POSITIVE_INFINITY);
            this.setBarycenter(Vector2D.NaN);
         } else {
            this.setSize((double)0.0F);
            this.setBarycenter(new Vector2D((double)0.0F, (double)0.0F));
         }
      } else if (v[0][0] == null) {
         this.setSize(Double.POSITIVE_INFINITY);
         this.setBarycenter(Vector2D.NaN);
      } else {
         double sum = (double)0.0F;
         double sumX = (double)0.0F;
         double sumY = (double)0.0F;

         for(Vector2D[] loop : v) {
            double x1 = loop[loop.length - 1].getX();
            double y1 = loop[loop.length - 1].getY();

            for(Vector2D point : loop) {
               double x0 = x1;
               double y0 = y1;
               x1 = point.getX();
               y1 = point.getY();
               double factor = x0 * y1 - y0 * x1;
               sum += factor;
               sumX += factor * (x0 + x1);
               sumY += factor * (y0 + y1);
            }
         }

         if (sum < (double)0.0F) {
            this.setSize(Double.POSITIVE_INFINITY);
            this.setBarycenter(Vector2D.NaN);
         } else {
            this.setSize(sum / (double)2.0F);
            this.setBarycenter(new Vector2D(sumX / ((double)3.0F * sum), sumY / ((double)3.0F * sum)));
         }
      }

   }

   public Vector2D[][] getVertices() {
      if (this.vertices == null) {
         if (this.getTree(false).getCut() == null) {
            this.vertices = new Vector2D[0][];
         } else {
            SegmentsBuilder visitor = new SegmentsBuilder(this.getTolerance());
            this.getTree(true).visit(visitor);
            List<ConnectableSegment> segments = visitor.getSegments();
            int pending = segments.size();
            pending -= this.naturalFollowerConnections(segments);
            if (pending > 0) {
               pending -= this.splitEdgeConnections(segments);
            }

            if (pending > 0) {
               int var10000 = pending - this.closeVerticesConnections(segments);
            }

            ArrayList<List<Segment>> loops = new ArrayList();

            for(ConnectableSegment s = this.getUnprocessed(segments); s != null; s = this.getUnprocessed(segments)) {
               List<Segment> loop = this.followLoop(s);
               if (loop != null) {
                  if (((Segment)loop.get(0)).getStart() == null) {
                     loops.add(0, loop);
                  } else {
                     loops.add(loop);
                  }
               }
            }

            this.vertices = new Vector2D[loops.size()][];
            int i = 0;

            for(List loop : loops) {
               if (loop.size() >= 2 && (loop.size() != 2 || ((Segment)loop.get(0)).getStart() != null || ((Segment)loop.get(1)).getEnd() != null)) {
                  if (((Segment)loop.get(0)).getStart() == null) {
                     Vector2D[] array = new Vector2D[loop.size() + 2];
                     int j = 0;

                     for(Segment segment : loop) {
                        if (j == 0) {
                           double x = segment.getLine().toSubSpace((Point)segment.getEnd()).getX();
                           x -= FastMath.max((double)1.0F, FastMath.abs(x / (double)2.0F));
                           array[j++] = null;
                           array[j++] = segment.getLine().toSpace((Point)(new Vector1D(x)));
                        }

                        if (j < array.length - 1) {
                           array[j++] = segment.getEnd();
                        }

                        if (j == array.length - 1) {
                           double x = segment.getLine().toSubSpace((Point)segment.getStart()).getX();
                           x += FastMath.max((double)1.0F, FastMath.abs(x / (double)2.0F));
                           array[j++] = segment.getLine().toSpace((Point)(new Vector1D(x)));
                        }
                     }

                     this.vertices[i++] = array;
                  } else {
                     Vector2D[] array = new Vector2D[loop.size()];
                     int j = 0;

                     for(Segment segment : loop) {
                        array[j++] = segment.getStart();
                     }

                     this.vertices[i++] = array;
                  }
               } else {
                  Line line = ((Segment)loop.get(0)).getLine();
                  this.vertices[i++] = new Vector2D[]{null, line.toSpace((Point)(new Vector1D((double)-Float.MAX_VALUE))), line.toSpace((Point)(new Vector1D((double)Float.MAX_VALUE)))};
               }
            }
         }
      }

      return (Vector2D[][])this.vertices.clone();
   }

   private int naturalFollowerConnections(List segments) {
      int connected = 0;

      for(ConnectableSegment segment : segments) {
         if (segment.getNext() == null) {
            BSPTree<Euclidean2D> node = segment.getNode();
            BSPTree<Euclidean2D> end = segment.getEndNode();

            for(ConnectableSegment candidateNext : segments) {
               if (candidateNext.getPrevious() == null && candidateNext.getNode() == end && candidateNext.getStartNode() == node) {
                  segment.setNext(candidateNext);
                  candidateNext.setPrevious(segment);
                  ++connected;
                  break;
               }
            }
         }
      }

      return connected;
   }

   private int splitEdgeConnections(List segments) {
      int connected = 0;

      for(ConnectableSegment segment : segments) {
         if (segment.getNext() == null) {
            Hyperplane<Euclidean2D> hyperplane = segment.getNode().getCut().getHyperplane();
            BSPTree<Euclidean2D> end = segment.getEndNode();

            for(ConnectableSegment candidateNext : segments) {
               if (candidateNext.getPrevious() == null && candidateNext.getNode().getCut().getHyperplane() == hyperplane && candidateNext.getStartNode() == end) {
                  segment.setNext(candidateNext);
                  candidateNext.setPrevious(segment);
                  ++connected;
                  break;
               }
            }
         }
      }

      return connected;
   }

   private int closeVerticesConnections(List segments) {
      int connected = 0;

      for(ConnectableSegment segment : segments) {
         if (segment.getNext() == null && segment.getEnd() != null) {
            Vector2D end = segment.getEnd();
            ConnectableSegment selectedNext = null;
            double min = Double.POSITIVE_INFINITY;

            for(ConnectableSegment candidateNext : segments) {
               if (candidateNext.getPrevious() == null && candidateNext.getStart() != null) {
                  double distance = Vector2D.distance(end, candidateNext.getStart());
                  if (distance < min) {
                     selectedNext = candidateNext;
                     min = distance;
                  }
               }
            }

            if (min <= this.getTolerance()) {
               segment.setNext(selectedNext);
               selectedNext.setPrevious(segment);
               ++connected;
            }
         }
      }

      return connected;
   }

   private ConnectableSegment getUnprocessed(List segments) {
      for(ConnectableSegment segment : segments) {
         if (!segment.isProcessed()) {
            return segment;
         }
      }

      return null;
   }

   private List followLoop(ConnectableSegment defining) {
      List<Segment> loop = new ArrayList();
      loop.add(defining);
      defining.setProcessed(true);

      ConnectableSegment next;
      for(next = defining.getNext(); next != defining && next != null; next = next.getNext()) {
         loop.add(next);
         next.setProcessed(true);
      }

      if (next == null) {
         for(ConnectableSegment previous = defining.getPrevious(); previous != null; previous = previous.getPrevious()) {
            loop.add(0, previous);
            previous.setProcessed(true);
         }
      }

      this.filterSpuriousVertices(loop);
      return loop.size() == 2 && ((Segment)loop.get(0)).getStart() != null ? null : loop;
   }

   private void filterSpuriousVertices(List loop) {
      for(int i = 0; i < loop.size(); ++i) {
         Segment previous = (Segment)loop.get(i);
         int j = (i + 1) % loop.size();
         Segment next = (Segment)loop.get(j);
         if (next != null && Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), Precision.EPSILON)) {
            loop.set(j, new Segment(previous.getStart(), next.getEnd(), previous.getLine()));
            loop.remove(i--);
         }
      }

   }

   private static class Vertex {
      private final Vector2D location;
      private Edge incoming;
      private Edge outgoing;
      private final List lines;

      Vertex(Vector2D location) {
         this.location = location;
         this.incoming = null;
         this.outgoing = null;
         this.lines = new ArrayList();
      }

      public Vector2D getLocation() {
         return this.location;
      }

      public void bindWith(Line line) {
         this.lines.add(line);
      }

      public Line sharedLineWith(Vertex vertex) {
         for(Line line1 : this.lines) {
            for(Line line2 : vertex.lines) {
               if (line1 == line2) {
                  return line1;
               }
            }
         }

         return null;
      }

      public void setIncoming(Edge incoming) {
         this.incoming = incoming;
         this.bindWith(incoming.getLine());
      }

      public Edge getIncoming() {
         return this.incoming;
      }

      public void setOutgoing(Edge outgoing) {
         this.outgoing = outgoing;
         this.bindWith(outgoing.getLine());
      }

      public Edge getOutgoing() {
         return this.outgoing;
      }
   }

   private static class Edge {
      private final Vertex start;
      private final Vertex end;
      private final Line line;
      private BSPTree node;

      Edge(Vertex start, Vertex end, Line line) {
         this.start = start;
         this.end = end;
         this.line = line;
         this.node = null;
         start.setOutgoing(this);
         end.setIncoming(this);
      }

      public Vertex getStart() {
         return this.start;
      }

      public Vertex getEnd() {
         return this.end;
      }

      public Line getLine() {
         return this.line;
      }

      public void setNode(BSPTree node) {
         this.node = node;
      }

      public BSPTree getNode() {
         return this.node;
      }

      public Vertex split(Line splitLine) {
         Vertex splitVertex = new Vertex(this.line.intersection(splitLine));
         splitVertex.bindWith(splitLine);
         Edge startHalf = new Edge(this.start, splitVertex, this.line);
         Edge endHalf = new Edge(splitVertex, this.end, this.line);
         startHalf.node = this.node;
         endHalf.node = this.node;
         return splitVertex;
      }
   }

   private static class ConnectableSegment extends Segment {
      private final BSPTree node;
      private final BSPTree startNode;
      private final BSPTree endNode;
      private ConnectableSegment previous;
      private ConnectableSegment next;
      private boolean processed;

      ConnectableSegment(Vector2D start, Vector2D end, Line line, BSPTree node, BSPTree startNode, BSPTree endNode) {
         super(start, end, line);
         this.node = node;
         this.startNode = startNode;
         this.endNode = endNode;
         this.previous = null;
         this.next = null;
         this.processed = false;
      }

      public BSPTree getNode() {
         return this.node;
      }

      public BSPTree getStartNode() {
         return this.startNode;
      }

      public BSPTree getEndNode() {
         return this.endNode;
      }

      public ConnectableSegment getPrevious() {
         return this.previous;
      }

      public void setPrevious(ConnectableSegment previous) {
         this.previous = previous;
      }

      public ConnectableSegment getNext() {
         return this.next;
      }

      public void setNext(ConnectableSegment next) {
         this.next = next;
      }

      public void setProcessed(boolean processed) {
         this.processed = processed;
      }

      public boolean isProcessed() {
         return this.processed;
      }
   }

   private static class SegmentsBuilder implements BSPTreeVisitor {
      private final double tolerance;
      private final List segments;

      SegmentsBuilder(double tolerance) {
         this.tolerance = tolerance;
         this.segments = new ArrayList();
      }

      public BSPTreeVisitor.Order visitOrder(BSPTree node) {
         return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
      }

      public void visitInternalNode(BSPTree node) {
         BoundaryAttribute<Euclidean2D> attribute = (BoundaryAttribute)node.getAttribute();
         Iterable<BSPTree<Euclidean2D>> splitters = attribute.getSplitters();
         if (attribute.getPlusOutside() != null) {
            this.addContribution(attribute.getPlusOutside(), node, splitters, false);
         }

         if (attribute.getPlusInside() != null) {
            this.addContribution(attribute.getPlusInside(), node, splitters, true);
         }

      }

      public void visitLeafNode(BSPTree node) {
      }

      private void addContribution(SubHyperplane sub, BSPTree node, Iterable splitters, boolean reversed) {
         AbstractSubHyperplane<Euclidean2D, Euclidean1D> absSub = (AbstractSubHyperplane)sub;
         Line line = (Line)sub.getHyperplane();

         for(Interval i : ((IntervalsSet)absSub.getRemainingRegion()).asList()) {
            Vector2D startV = Double.isInfinite(i.getInf()) ? null : line.toSpace((Point)(new Vector1D(i.getInf())));
            Vector2D endV = Double.isInfinite(i.getSup()) ? null : line.toSpace((Point)(new Vector1D(i.getSup())));
            BSPTree<Euclidean2D> startN = this.selectClosest(startV, splitters);
            BSPTree<Euclidean2D> endN = this.selectClosest(endV, splitters);
            if (reversed) {
               this.segments.add(new ConnectableSegment(endV, startV, line.getReverse(), node, endN, startN));
            } else {
               this.segments.add(new ConnectableSegment(startV, endV, line, node, startN, endN));
            }
         }

      }

      private BSPTree selectClosest(Vector2D point, Iterable candidates) {
         BSPTree<Euclidean2D> selected = null;
         double min = Double.POSITIVE_INFINITY;

         for(BSPTree node : candidates) {
            double distance = FastMath.abs(node.getCut().getHyperplane().getOffset(point));
            if (distance < min) {
               selected = node;
               min = distance;
            }
         }

         return min <= this.tolerance ? selected : null;
      }

      public List getSegments() {
         return this.segments;
      }
   }
}
