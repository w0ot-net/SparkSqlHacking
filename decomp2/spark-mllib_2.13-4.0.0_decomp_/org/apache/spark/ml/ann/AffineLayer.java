package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.util.Random;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3Q!\u0004\b\u0001\u001daA\u0001b\t\u0001\u0003\u0006\u0004%\t!\n\u0005\tS\u0001\u0011\t\u0011)A\u0005M!A!\u0006\u0001BC\u0002\u0013\u0005Q\u0005\u0003\u0005,\u0001\t\u0005\t\u0015!\u0003'\u0011\u0015a\u0003\u0001\"\u0001.\u0011\u001d\t\u0004A1A\u0005B\u0015BaA\r\u0001!\u0002\u00131\u0003\"B\u001a\u0001\t\u0003\"\u0004bB\u001c\u0001\u0005\u0004%\t\u0005\u000f\u0005\u0007y\u0001\u0001\u000b\u0011B\u001d\t\u000bu\u0002A\u0011\t \t\u000b=\u0003A\u0011\t)\u0003\u0017\u00053g-\u001b8f\u0019\u0006LXM\u001d\u0006\u0003\u001fA\t1!\u00198o\u0015\t\t\"#\u0001\u0002nY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xmE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\u0011\"\u001b\u0005q\u0011B\u0001\u0012\u000f\u0005\u0015a\u0015-_3s\u0003\u0015qW/\\%o\u0007\u0001)\u0012A\n\t\u00035\u001dJ!\u0001K\u000e\u0003\u0007%sG/\u0001\u0004ok6Le\u000eI\u0001\u0007]Vlw*\u001e;\u0002\u000f9,XnT;uA\u00051A(\u001b8jiz\"2AL\u00181!\t\u0001\u0003\u0001C\u0003$\u000b\u0001\u0007a\u0005C\u0003+\u000b\u0001\u0007a%\u0001\u0006xK&<\u0007\u000e^*ju\u0016\f1b^3jO\"$8+\u001b>fA\u0005iq-\u001a;PkR\u0004X\u000f^*ju\u0016$\"AJ\u001b\t\u000bYB\u0001\u0019\u0001\u0014\u0002\u0013%t\u0007/\u001e;TSj,\u0017aB5o!2\f7-Z\u000b\u0002sA\u0011!DO\u0005\u0003wm\u0011qAQ8pY\u0016\fg.\u0001\u0005j]Bc\u0017mY3!\u0003-\u0019'/Z1uK6{G-\u001a7\u0015\u0005}\u0012\u0005C\u0001\u0011A\u0013\t\teB\u0001\u0006MCf,'/T8eK2DQaQ\u0006A\u0002\u0011\u000bqa^3jO\"$8\u000fE\u0002F\u00152k\u0011A\u0012\u0006\u0003\u000f\"\u000ba\u0001\\5oC2<'\"A%\u0002\r\t\u0014X-\u001a>f\u0013\tYeIA\u0006EK:\u001cXMV3di>\u0014\bC\u0001\u000eN\u0013\tq5D\u0001\u0004E_V\u0014G.Z\u0001\nS:LG/T8eK2$2aP)S\u0011\u0015\u0019E\u00021\u0001E\u0011\u0015\u0019F\u00021\u0001U\u0003\u0019\u0011\u0018M\u001c3p[B\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0005kRLGNC\u0001Z\u0003\u0011Q\u0017M^1\n\u0005m3&A\u0002*b]\u0012|W\u000e"
)
public class AffineLayer implements Layer {
   private final int numIn;
   private final int numOut;
   private final int weightSize;
   private final boolean inPlace;

   public int numIn() {
      return this.numIn;
   }

   public int numOut() {
      return this.numOut;
   }

   public int weightSize() {
      return this.weightSize;
   }

   public int getOutputSize(final int inputSize) {
      return this.numOut();
   }

   public boolean inPlace() {
      return this.inPlace;
   }

   public LayerModel createModel(final DenseVector weights) {
      return new AffineLayerModel(weights, this);
   }

   public LayerModel initModel(final DenseVector weights, final Random random) {
      return AffineLayerModel$.MODULE$.apply(this, weights, random);
   }

   public AffineLayer(final int numIn, final int numOut) {
      this.numIn = numIn;
      this.numOut = numOut;
      this.weightSize = numIn * numOut + numOut;
      this.inPlace = false;
   }
}
