package org.apache.spark.graphx;

import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A!\u0004\b\u0001/!)1\u0006\u0001C\u0001Y!I\u0011\u0007\u0001a\u0001\u0002\u0004%\tA\r\u0005\ng\u0001\u0001\r\u00111A\u0005\u0002QB\u0011B\u000f\u0001A\u0002\u0003\u0005\u000b\u0015\u0002\u0018\t\u0013m\u0002\u0001\u0019!a\u0001\n\u0003\u0011\u0004\"\u0003\u001f\u0001\u0001\u0004\u0005\r\u0011\"\u0001>\u0011%y\u0004\u00011A\u0001B\u0003&a\u0006\u0003\u0004A\u0001\u0011E\u0001#\u0011\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006%\u0002!\te\u0015\u0005\u0006?\u0002!\t\u0001\u0019\u0002\f\u000b\u0012<W\r\u0016:ja2,GO\u0003\u0002\u0010!\u00051qM]1qQbT!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\u0002\u0001+\rArfH\n\u0003\u0001e\u00012AG\u000e\u001e\u001b\u0005q\u0011B\u0001\u000f\u000f\u0005\u0011)EmZ3\u0011\u0005yyB\u0002\u0001\u0003\u0006A\u0001\u0011\r!\t\u0002\u0003\u000b\u0012\u000b\"A\t\u0015\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\u000f9{G\u000f[5oOB\u00111%K\u0005\u0003U\u0011\u00121!\u00118z\u0003\u0019a\u0014N\\5u}Q\tQ\u0006\u0005\u0003\u001b\u00019j\u0002C\u0001\u00100\t\u0015\u0001\u0004A1\u0001\"\u0005\t1F)A\u0004te\u000e\fE\u000f\u001e:\u0016\u00039\n1b\u001d:d\u0003R$(o\u0018\u0013fcR\u0011Q\u0007\u000f\t\u0003GYJ!a\u000e\u0013\u0003\tUs\u0017\u000e\u001e\u0005\bs\r\t\t\u00111\u0001/\u0003\rAH%M\u0001\tgJ\u001c\u0017\t\u001e;sA\u00059Am\u001d;BiR\u0014\u0018a\u00033ti\u0006#HO]0%KF$\"!\u000e \t\u000fe2\u0011\u0011!a\u0001]\u0005AAm\u001d;BiR\u0014\b%A\u0002tKR$\"!\f\"\t\u000b\rC\u0001\u0019A\r\u0002\u000b=$\b.\u001a:\u0002\u001f=$\b.\u001a:WKJ$X\r_!uiJ$\"A\f$\t\u000b\u001dK\u0001\u0019\u0001%\u0002\u0007YLG\r\u0005\u0002J\u0019:\u0011!DS\u0005\u0003\u0017:\tq\u0001]1dW\u0006<W-\u0003\u0002N\u001d\nAa+\u001a:uKbLEM\u0003\u0002L\u001d\u0005Qa/\u001a:uKb\fE\u000f\u001e:\u0015\u00059\n\u0006\"B$\u000b\u0001\u0004A\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\u0003\"!\u0016/\u000f\u0005YS\u0006CA,%\u001b\u0005A&BA-\u0017\u0003\u0019a$o\\8u}%\u00111\fJ\u0001\u0007!J,G-\u001a4\n\u0005us&AB*ue&twM\u0003\u0002\\I\u00059Ao\u001c+va2,W#A1\u0011\u000b\r\u0012G\rZ\u000f\n\u0005\r$#A\u0002+va2,7\u0007\u0005\u0003$K\"s\u0013B\u00014%\u0005\u0019!V\u000f\u001d7fe\u0001"
)
public class EdgeTriplet extends Edge {
   private Object srcAttr;
   private Object dstAttr;

   public Object srcAttr() {
      return this.srcAttr;
   }

   public void srcAttr_$eq(final Object x$1) {
      this.srcAttr = x$1;
   }

   public Object dstAttr() {
      return this.dstAttr;
   }

   public void dstAttr_$eq(final Object x$1) {
      this.dstAttr = x$1;
   }

   public EdgeTriplet set(final Edge other) {
      this.srcId_$eq(other.srcId());
      this.dstId_$eq(other.dstId());
      this.attr_$eq(other.attr());
      return this;
   }

   public Object otherVertexAttr(final long vid) {
      if (this.srcId() == vid) {
         return this.dstAttr();
      } else {
         .MODULE$.assert(this.dstId() == vid);
         return this.srcAttr();
      }
   }

   public Object vertexAttr(final long vid) {
      if (this.srcId() == vid) {
         return this.srcAttr();
      } else {
         .MODULE$.assert(this.dstId() == vid);
         return this.dstAttr();
      }
   }

   public String toString() {
      return (new Tuple3(new Tuple2(BoxesRunTime.boxToLong(this.srcId()), this.srcAttr()), new Tuple2(BoxesRunTime.boxToLong(this.dstId()), this.dstAttr()), this.attr())).toString();
   }

   public Tuple3 toTuple() {
      return new Tuple3(new Tuple2(BoxesRunTime.boxToLong(this.srcId()), this.srcAttr()), new Tuple2(BoxesRunTime.boxToLong(this.dstId()), this.dstAttr()), this.attr());
   }

   public EdgeTriplet() {
      super(Edge$.MODULE$.$lessinit$greater$default$1(), Edge$.MODULE$.$lessinit$greater$default$2(), Edge$.MODULE$.$lessinit$greater$default$3());
   }
}
