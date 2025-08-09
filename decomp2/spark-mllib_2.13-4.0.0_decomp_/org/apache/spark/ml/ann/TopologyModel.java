package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001\"\u0003\u0006\u0011\u0002G\u0005!\u0002\u0006\u0005\bQ\u0001\u0011\rQ\"\u0001*\u0011\u001d\u0001\u0004A1A\u0007\u0002EBq!\u000f\u0001C\u0002\u001b\u0005!\bC\u0003@\u0001\u0019\u0005\u0001\tC\u0003T\u0001\u0019\u0005A\u000bC\u0003X\u0001\u0019\u0005\u0001\fC\u0003[\u0001\u0019\u00051\fC\u0003_\u0001\u0019\u0005qLA\u0007U_B|Gn\\4z\u001b>$W\r\u001c\u0006\u0003\u00171\t1!\u00198o\u0015\tia\"\u0001\u0002nY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xmE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007C\u0001\u000f&\u001d\ti2E\u0004\u0002\u001fE5\tqD\u0003\u0002!C\u00051AH]8piz\u001a\u0001!C\u0001\u0019\u0013\t!s#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019:#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0013\u0018\u0003\u001d9X-[4iiN,\u0012A\u000b\t\u0003W9j\u0011\u0001\f\u0006\u0003[1\ta\u0001\\5oC2<\u0017BA\u0018-\u0005\u00191Vm\u0019;pe\u00061A.Y=feN,\u0012A\r\t\u0004-M*\u0014B\u0001\u001b\u0018\u0005\u0015\t%O]1z!\t1t'D\u0001\u000b\u0013\tA$BA\u0003MCf,'/A\u0006mCf,'/T8eK2\u001cX#A\u001e\u0011\u0007Y\u0019D\b\u0005\u00027{%\u0011aH\u0003\u0002\u000b\u0019\u0006LXM]'pI\u0016d\u0017a\u00024pe^\f'\u000f\u001a\u000b\u0004\u00032s\u0005c\u0001\f4\u0005B\u00191iR%\u000e\u0003\u0011S!!L#\u000b\u0003\u0019\u000baA\u0019:fKj,\u0017B\u0001%E\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005YQ\u0015BA&\u0018\u0005\u0019!u.\u001e2mK\")Q\n\u0002a\u0001\u0005\u0006!A-\u0019;b\u0011\u0015yE\u00011\u0001Q\u0003AIgn\u00197vI\u0016d\u0015m\u001d;MCf,'\u000f\u0005\u0002\u0017#&\u0011!k\u0006\u0002\b\u0005>|G.Z1o\u0003\u001d\u0001(/\u001a3jGR$\"AK+\t\u000bY+\u0001\u0019\u0001\u0016\u0002\u0011\u0019,\u0017\r^;sKN\f!\u0002\u001d:fI&\u001cGOU1x)\tQ\u0013\fC\u0003W\r\u0001\u0007!&\u0001\fsC^\u0014\u0004K]8cC\nLG.\u001b;z\u0013:\u0004F.Y2f)\tQC\fC\u0003^\u000f\u0001\u0007!&A\u0007sC^\u0004&/\u001a3jGRLwN\\\u0001\u0010G>l\u0007/\u001e;f\u000fJ\fG-[3oiR)\u0011\nY1dK\")Q\n\u0003a\u0001\u0005\")!\r\u0003a\u0001\u0005\u00061A/\u0019:hKRDQ\u0001\u001a\u0005A\u0002)\n1bY;n\u000fJ\fG-[3oi\")a\r\u0003a\u0001O\u0006I!\r\\8dWNK'0\u001a\t\u0003-!L!![\f\u0003\u0007%sG\u000f"
)
public interface TopologyModel extends Serializable {
   Vector weights();

   Layer[] layers();

   LayerModel[] layerModels();

   DenseMatrix[] forward(final DenseMatrix data, final boolean includeLastLayer);

   Vector predict(final Vector features);

   Vector predictRaw(final Vector features);

   Vector raw2ProbabilityInPlace(final Vector rawPrediction);

   double computeGradient(final DenseMatrix data, final DenseMatrix target, final Vector cumGradient, final int blockSize);
}
