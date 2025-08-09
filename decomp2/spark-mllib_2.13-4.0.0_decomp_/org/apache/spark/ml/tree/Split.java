package org.apache.spark.ml.tree;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4qAC\u0006\u0011\u0002G\u0005b\u0003C\u0003*\u0001\u0019\u0005!\u0006\u0003\u0004/\u0001\u0019\u0005Qb\f\u0005\u0007]\u00011\t!D\u001e\t\r\u0015\u0003a\u0011A\u0006G\u000f\u0019!6\u0002#\u0001\f+\u001a1!b\u0003E\u0001\u0017YCQA\u0018\u0004\u0005\u0002}CQ\u0001\u0019\u0004\u0005\u0002\u0005DqA\u001c\u0004\u0002\u0002\u0013%qNA\u0003Ta2LGO\u0003\u0002\r\u001b\u0005!AO]3f\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0003=\u0019r!a\b\u0013\u000f\u0005\u0001\u001aS\"A\u0011\u000b\u0005\t*\u0012A\u0002\u001fs_>$h(C\u0001\u001b\u0013\t)\u0013$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dB#\u0001D*fe&\fG.\u001b>bE2,'BA\u0013\u001a\u000311W-\u0019;ve\u0016Le\u000eZ3y+\u0005Y\u0003C\u0001\r-\u0013\ti\u0013DA\u0002J]R\fAb\u001d5pk2$wi\u001c'fMR$\"\u0001M\u001a\u0011\u0005a\t\u0014B\u0001\u001a\u001a\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u000e\u0002A\u0002U\n\u0001BZ3biV\u0014Xm\u001d\t\u0003mej\u0011a\u000e\u0006\u0003q5\ta\u0001\\5oC2<\u0017B\u0001\u001e8\u0005\u00191Vm\u0019;peR\u0019\u0001\u0007\u0010 \t\u000bu\u001a\u0001\u0019A\u0016\u0002\u001b\tLgN\\3e\r\u0016\fG/\u001e:f\u0011\u0015y4\u00011\u0001A\u0003\u0019\u0019\b\u000f\\5ugB\u0019\u0001$Q\"\n\u0005\tK\"!B!se\u0006L\bC\u0001#\u0001\u001b\u0005Y\u0011!\u0002;p\u001f2$W#A$\u0011\u0005!sU\"A%\u000b\u0005)[\u0015!B7pI\u0016d'B\u0001\u0007M\u0015\tiu\"A\u0003nY2L'-\u0003\u0002\u000b\u0013&\u001a\u0001\u0001\u0015*\n\u0005E[!\u0001E\"bi\u0016<wN]5dC2\u001c\u0006\u000f\\5u\u0013\t\u00196BA\bD_:$\u0018N\\;pkN\u001c\u0006\u000f\\5u\u0003\u0015\u0019\u0006\u000f\\5u!\t!eaE\u0002\u0007/]\u0003\"\u0001W/\u000e\u0003eS!AW.\u0002\u0005%|'\"\u0001/\u0002\t)\fg/Y\u0005\u0003Oe\u000ba\u0001P5oSRtD#A+\u0002\u000f\u0019\u0014x.\\(mIR\u00191I\u00193\t\u000b\rD\u0001\u0019A$\u0002\u0011=dGm\u00159mSRDQ!\u001a\u0005A\u0002\u0019\f1cY1uK\u001e|'/[2bY\u001a+\u0017\r^;sKN\u0004BaZ6,W9\u0011\u0001.\u001b\t\u0003AeI!A[\r\u0002\rA\u0013X\rZ3g\u0013\taWNA\u0002NCBT!A[\r\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003A\u0004\"!\u001d;\u000e\u0003IT!a].\u0002\t1\fgnZ\u0005\u0003kJ\u0014aa\u00142kK\u000e$\b"
)
public interface Split extends Serializable {
   static Split fromOld(final org.apache.spark.mllib.tree.model.Split oldSplit, final Map categoricalFeatures) {
      return Split$.MODULE$.fromOld(oldSplit, categoricalFeatures);
   }

   int featureIndex();

   boolean shouldGoLeft(final Vector features);

   boolean shouldGoLeft(final int binnedFeature, final Split[] splits);

   org.apache.spark.mllib.tree.model.Split toOld();
}
