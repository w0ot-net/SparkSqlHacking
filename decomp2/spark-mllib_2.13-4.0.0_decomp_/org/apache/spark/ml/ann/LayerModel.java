package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3\u0001\"\u0002\u0004\u0011\u0002G\u0005a\u0001\u0005\u0005\bI\u0001\u0011\rQ\"\u0001&\u0011\u0015\t\u0004A\"\u00013\u0011\u0015i\u0004A\"\u0001?\u0011\u0015!\u0005A\"\u0001F\u0005)a\u0015-_3s\u001b>$W\r\u001c\u0006\u0003\u000f!\t1!\u00198o\u0015\tI!\"\u0001\u0002nY*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xmE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\"\u001d\tIrD\u0004\u0002\u001b=5\t1D\u0003\u0002\u001d;\u00051AH]8piz\u001a\u0001!C\u0001\u0015\u0013\t\u00013#A\u0004qC\u000e\\\u0017mZ3\n\u0005\t\u001a#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0011\u0014\u0003\u001d9X-[4iiN,\u0012A\n\t\u0004O1rS\"\u0001\u0015\u000b\u0005%R\u0013A\u00027j]\u0006dwMC\u0001,\u0003\u0019\u0011'/Z3{K&\u0011Q\u0006\u000b\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u0013_%\u0011\u0001g\u0005\u0002\u0007\t>,(\r\\3\u0002\t\u00154\u0018\r\u001c\u000b\u0004gYZ\u0004C\u0001\n5\u0013\t)4C\u0001\u0003V]&$\b\"B\u001c\u0003\u0001\u0004A\u0014\u0001\u00023bi\u0006\u00042aJ\u001d/\u0013\tQ\u0004FA\u0006EK:\u001cX-T1ue&D\b\"\u0002\u001f\u0003\u0001\u0004A\u0014AB8viB,H/\u0001\td_6\u0004X\u000f^3Qe\u00164H)\u001a7uCR!1gP!C\u0011\u0015\u00015\u00011\u00019\u0003\u0015!W\r\u001c;b\u0011\u0015a4\u00011\u00019\u0011\u0015\u00195\u00011\u00019\u0003%\u0001(/\u001a<EK2$\u0018-\u0001\u0003he\u0006$G\u0003B\u001aG\u000f&CQ\u0001\u0011\u0003A\u0002aBQ\u0001\u0013\u0003A\u0002a\nQ!\u001b8qkRDQA\u0013\u0003A\u0002\u0019\nqaY;n\u000fJ\fG\r"
)
public interface LayerModel extends Serializable {
   DenseVector weights();

   void eval(final DenseMatrix data, final DenseMatrix output);

   void computePrevDelta(final DenseMatrix delta, final DenseMatrix output, final DenseMatrix prevDelta);

   void grad(final DenseMatrix delta, final DenseMatrix input, final DenseVector cumGrad);
}
