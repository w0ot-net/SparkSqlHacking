package org.apache.spark.util.collection;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194Q\u0001C\u0005\u0001\u001bMA\u0001B\r\u0001\u0003\u0004\u0003\u0006Ya\r\u0005\u0006s\u0001!\tA\u000f\u0005\u0006}\u0001!\te\u0010\u0005\u0006\u000f\u0002!\t\u0005\u0013\u0005\u0006#\u0002!\tE\u0015\u0005\u00067\u0002!\t\u0005\u0018\u0005\u0006G\u0002!\t\u0005\u001a\u0002\u0016\u0017Z\u000b%O]1z'>\u0014H\u000fR1uC\u001a{'/\\1u\u0015\tQ1\"\u0001\u0006d_2dWm\u0019;j_:T!\u0001D\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sOV\u0019Ac\u0007\u0017\u0014\u0005\u0001)\u0002\u0003\u0002\f\u00183!j\u0011!C\u0005\u00031%\u0011abU8si\u0012\u000bG/\u0019$pe6\fG\u000f\u0005\u0002\u001b71\u0001A!\u0002\u000f\u0001\u0005\u0004q\"!A&\u0004\u0001E\u0011q$\n\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001c%\u0003\u0002(C\t\u0019\u0011I\\=\u0011\u0007\u0001J3&\u0003\u0002+C\t)\u0011I\u001d:bsB\u0011!\u0004\f\u0003\u0006[\u0001\u0011\rA\f\u0002\u0002)F\u0011qd\f\t\u0003AAJ!!M\u0011\u0003\r\u0005s\u0017PU3g\u0003))g/\u001b3f]\u000e,G%\r\t\u0004i]ZS\"A\u001b\u000b\u0005Y\n\u0013a\u0002:fM2,7\r^\u0005\u0003qU\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003m\"\"\u0001P\u001f\u0011\tY\u0001\u0011d\u000b\u0005\u0006e\t\u0001\u001daM\u0001\u0007O\u0016$8*Z=\u0015\u0007e\u0001%\tC\u0003B\u0007\u0001\u0007\u0001&\u0001\u0003eCR\f\u0007\"B\"\u0004\u0001\u0004!\u0015a\u00019pgB\u0011\u0001%R\u0005\u0003\r\u0006\u00121!\u00138u\u0003\u0011\u0019x/\u00199\u0015\t%cUj\u0014\t\u0003A)K!aS\u0011\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u0003\u0012\u0001\r\u0001\u000b\u0005\u0006\u001d\u0012\u0001\r\u0001R\u0001\u0005a>\u001c\b\u0007C\u0003Q\t\u0001\u0007A)\u0001\u0003q_N\f\u0014aC2paf,E.Z7f]R$R!S*V/fCQ\u0001V\u0003A\u0002!\n1a\u001d:d\u0011\u00151V\u00011\u0001E\u0003\u0019\u0019(o\u0019)pg\")\u0001,\u0002a\u0001Q\u0005\u0019Am\u001d;\t\u000bi+\u0001\u0019\u0001#\u0002\r\u0011\u001cH\u000fU8t\u0003%\u0019w\u000e]=SC:<W\r\u0006\u0004J;z{\u0006-\u0019\u0005\u0006)\u001a\u0001\r\u0001\u000b\u0005\u0006-\u001a\u0001\r\u0001\u0012\u0005\u00061\u001a\u0001\r\u0001\u000b\u0005\u00065\u001a\u0001\r\u0001\u0012\u0005\u0006E\u001a\u0001\r\u0001R\u0001\u0007Y\u0016tw\r\u001e5\u0002\u0011\u0005dGn\\2bi\u0016$\"\u0001K3\t\u000b\t<\u0001\u0019\u0001#"
)
public class KVArraySortDataFormat extends SortDataFormat {
   private final ClassTag evidence$1;

   public Object getKey(final Object[] data, final int pos) {
      return data[2 * pos];
   }

   public void swap(final Object[] data, final int pos0, final int pos1) {
      Object tmpKey = data[2 * pos0];
      Object tmpVal = data[2 * pos0 + 1];
      data[2 * pos0] = data[2 * pos1];
      data[2 * pos0 + 1] = data[2 * pos1 + 1];
      data[2 * pos1] = tmpKey;
      data[2 * pos1 + 1] = tmpVal;
   }

   public void copyElement(final Object[] src, final int srcPos, final Object[] dst, final int dstPos) {
      dst[2 * dstPos] = src[2 * srcPos];
      dst[2 * dstPos + 1] = src[2 * srcPos + 1];
   }

   public void copyRange(final Object[] src, final int srcPos, final Object[] dst, final int dstPos, final int length) {
      System.arraycopy(src, 2 * srcPos, dst, 2 * dstPos, 2 * length);
   }

   public Object[] allocate(final int length) {
      return this.evidence$1.newArray(2 * length);
   }

   public KVArraySortDataFormat(final ClassTag evidence$1) {
      this.evidence$1 = evidence$1;
   }
}
