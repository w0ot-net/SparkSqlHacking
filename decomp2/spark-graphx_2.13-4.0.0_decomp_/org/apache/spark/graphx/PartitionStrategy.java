package org.apache.spark.graphx;

import java.io.Serializable;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mfaB\u001c9!\u0003\r\n!\u0011\u0005\u0006)\u00021\t!V\u0004\u0006MbB\ta\u001a\u0004\u0006oaB\t\u0001\u001b\u0005\u0006a\u000e!\t!]\u0004\u0006e\u000eA\ti\u001d\u0004\u0006k\u000eA\tI\u001e\u0005\u0006a\u001a!\ta\u001f\u0005\u0006)\u001a!\t\u0005 \u0005\n\u0003\u00031\u0011\u0011!C!\u0003\u0007A\u0011\"!\u0005\u0007\u0003\u0003%\t!a\u0005\t\u0013\u0005ma!!A\u0005\u0002\u0005u\u0001\"CA\u0015\r\u0005\u0005I\u0011IA\u0016\u0011%\tIDBA\u0001\n\u0003\tY\u0004C\u0005\u0002F\u0019\t\t\u0011\"\u0011\u0002H!I\u0011\u0011\n\u0004\u0002\u0002\u0013\u0005\u00131\n\u0005\n\u0003\u001b2\u0011\u0011!C\u0005\u0003\u001f:q!a\u0016\u0004\u0011\u0003\u000bIFB\u0004\u0002\\\rA\t)!\u0018\t\rA\u0014B\u0011AA0\u0011\u0019!&\u0003\"\u0011\u0002b!I\u0011\u0011\u0001\n\u0002\u0002\u0013\u0005\u00131\u0001\u0005\n\u0003#\u0011\u0012\u0011!C\u0001\u0003'A\u0011\"a\u0007\u0013\u0003\u0003%\t!!\u001b\t\u0013\u0005%\"#!A\u0005B\u0005-\u0002\"CA\u001d%\u0005\u0005I\u0011AA7\u0011%\t)EEA\u0001\n\u0003\n9\u0005C\u0005\u0002JI\t\t\u0011\"\u0011\u0002L!I\u0011Q\n\n\u0002\u0002\u0013%\u0011qJ\u0004\b\u0003c\u001a\u0001\u0012QA:\r\u001d\t)h\u0001EA\u0003oBa\u0001\u001d\u0010\u0005\u0002\u0005e\u0004B\u0002+\u001f\t\u0003\nY\bC\u0005\u0002\u0002y\t\t\u0011\"\u0011\u0002\u0004!I\u0011\u0011\u0003\u0010\u0002\u0002\u0013\u0005\u00111\u0003\u0005\n\u00037q\u0012\u0011!C\u0001\u0003\u0007C\u0011\"!\u000b\u001f\u0003\u0003%\t%a\u000b\t\u0013\u0005eb$!A\u0005\u0002\u0005\u001d\u0005\"CA#=\u0005\u0005I\u0011IA$\u0011%\tIEHA\u0001\n\u0003\nY\u0005C\u0005\u0002Ny\t\t\u0011\"\u0003\u0002P\u001d9\u00111R\u0002\t\u0002\u00065eaBAH\u0007!\u0005\u0015\u0011\u0013\u0005\u0007a*\"\t!a%\t\rQSC\u0011IAK\u0011%\t\tAKA\u0001\n\u0003\n\u0019\u0001C\u0005\u0002\u0012)\n\t\u0011\"\u0001\u0002\u0014!I\u00111\u0004\u0016\u0002\u0002\u0013\u0005\u0011Q\u0014\u0005\n\u0003SQ\u0013\u0011!C!\u0003WA\u0011\"!\u000f+\u0003\u0003%\t!!)\t\u0013\u0005\u0015#&!A\u0005B\u0005\u001d\u0003\"CA%U\u0005\u0005I\u0011IA&\u0011%\tiEKA\u0001\n\u0013\ty\u0005C\u0004\u0002&\u000e!\t!a*\t\u0013\u000553!!A\u0005\n\u0005=#!\u0005)beRLG/[8o'R\u0014\u0018\r^3hs*\u0011\u0011HO\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005mb\u0014!B:qCJ\\'BA\u001f?\u0003\u0019\t\u0007/Y2iK*\tq(A\u0002pe\u001e\u001c\u0001aE\u0002\u0001\u0005\"\u0003\"a\u0011$\u000e\u0003\u0011S\u0011!R\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000f\u0012\u0013a!\u00118z%\u00164\u0007CA%R\u001d\tQuJ\u0004\u0002L\u001d6\tAJ\u0003\u0002N\u0001\u00061AH]8pizJ\u0011!R\u0005\u0003!\u0012\u000bq\u0001]1dW\u0006<W-\u0003\u0002S'\na1+\u001a:jC2L'0\u00192mK*\u0011\u0001\u000bR\u0001\rO\u0016$\b+\u0019:uSRLwN\u001c\u000b\u0005-v\u0013G\r\u0005\u0002X5:\u0011\u0001,W\u0007\u0002q%\u0011\u0001\u000bO\u0005\u00037r\u00131\u0002U1si&$\u0018n\u001c8J\t*\u0011\u0001\u000b\u000f\u0005\u0006=\u0006\u0001\raX\u0001\u0004gJ\u001c\u0007CA,a\u0013\t\tGL\u0001\u0005WKJ$X\r_%e\u0011\u0015\u0019\u0017\u00011\u0001`\u0003\r!7\u000f\u001e\u0005\u0006K\u0006\u0001\rAV\u0001\t]Vl\u0007+\u0019:ug\u0006\t\u0002+\u0019:uSRLwN\\*ue\u0006$XmZ=\u0011\u0005a\u001b1cA\u0002CSB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\u0003S>T\u0011A\\\u0001\u0005U\u00064\u0018-\u0003\u0002SW\u00061A(\u001b8jiz\"\u0012aZ\u0001\u0010\u000b\u0012<W\rU1si&$\u0018n\u001c83\tB\u0011AOB\u0007\u0002\u0007\tyQ\tZ4f!\u0006\u0014H/\u001b;j_:\u0014DiE\u0003\u0007\u0005^D\b\n\u0005\u0002Y\u0001A\u00111)_\u0005\u0003u\u0012\u0013q\u0001\u0015:pIV\u001cG\u000fF\u0001t)\u00111VP`@\t\u000byC\u0001\u0019A0\t\u000b\rD\u0001\u0019A0\t\u000b\u0015D\u0001\u0019\u0001,\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t)\u0001\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tY!\\\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0010\u0005%!AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0016A\u00191)a\u0006\n\u0007\u0005eAIA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002 \u0005\u0015\u0002cA\"\u0002\"%\u0019\u00111\u0005#\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002(-\t\t\u00111\u0001\u0002\u0016\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\f\u0011\r\u0005=\u0012QGA\u0010\u001b\t\t\tDC\u0002\u00024\u0011\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9$!\r\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003{\t\u0019\u0005E\u0002D\u0003\u007fI1!!\u0011E\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\n\u000e\u0003\u0003\u0005\r!a\b\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0006\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0002\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0003\u0003BA\u0004\u0003'JA!!\u0016\u0002\n\t1qJ\u00196fGR\fq\"\u00123hKB\u000b'\u000f^5uS>t\u0017\u0007\u0012\t\u0003iJ\u0011q\"\u00123hKB\u000b'\u000f^5uS>t\u0017\u0007R\n\u0006%\t;\b\u0010\u0013\u000b\u0003\u00033\"rAVA2\u0003K\n9\u0007C\u0003_)\u0001\u0007q\fC\u0003d)\u0001\u0007q\fC\u0003f)\u0001\u0007a\u000b\u0006\u0003\u0002 \u0005-\u0004\"CA\u0014/\u0005\u0005\t\u0019AA\u000b)\u0011\ti$a\u001c\t\u0013\u0005\u001d\u0012$!AA\u0002\u0005}\u0011a\u0004*b]\u0012|WNV3si\u0016D8)\u001e;\u0011\u0005Qt\"a\u0004*b]\u0012|WNV3si\u0016D8)\u001e;\u0014\u000by\u0011u\u000f\u001f%\u0015\u0005\u0005MDc\u0002,\u0002~\u0005}\u0014\u0011\u0011\u0005\u0006=\u0002\u0002\ra\u0018\u0005\u0006G\u0002\u0002\ra\u0018\u0005\u0006K\u0002\u0002\rA\u0016\u000b\u0005\u0003?\t)\tC\u0005\u0002(\r\n\t\u00111\u0001\u0002\u0016Q!\u0011QHAE\u0011%\t9#JA\u0001\u0002\u0004\ty\"\u0001\rDC:|g.[2bYJ\u000bg\u000eZ8n-\u0016\u0014H/\u001a=DkR\u0004\"\u0001\u001e\u0016\u00031\r\u000bgn\u001c8jG\u0006d'+\u00198e_64VM\u001d;fq\u000e+HoE\u0003+\u0005^D\b\n\u0006\u0002\u0002\u000eR9a+a&\u0002\u001a\u0006m\u0005\"\u00020-\u0001\u0004y\u0006\"B2-\u0001\u0004y\u0006\"B3-\u0001\u00041F\u0003BA\u0010\u0003?C\u0011\"a\n0\u0003\u0003\u0005\r!!\u0006\u0015\t\u0005u\u00121\u0015\u0005\n\u0003O\t\u0014\u0011!a\u0001\u0003?\t!B\u001a:p[N#(/\u001b8h)\r9\u0018\u0011\u0016\u0005\b\u0003W+\u0004\u0019AAW\u0003\u0005\u0019\b\u0003BAX\u0003osA!!-\u00024B\u00111\nR\u0005\u0004\u0003k#\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0010\u0005e&bAA[\t\u0002"
)
public interface PartitionStrategy extends Serializable {
   static PartitionStrategy fromString(final String s) {
      return PartitionStrategy$.MODULE$.fromString(s);
   }

   int getPartition(final long src, final long dst, final int numParts);

   public static class EdgePartition2D$ implements PartitionStrategy, Product {
      public static final EdgePartition2D$ MODULE$ = new EdgePartition2D$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int getPartition(final long src, final long dst, final int numParts) {
         int ceilSqrtNumParts = (int).MODULE$.ceil(.MODULE$.sqrt((double)numParts));
         long mixingPrime = 1125899906842597L;
         if (numParts == ceilSqrtNumParts * ceilSqrtNumParts) {
            int col = (int)(.MODULE$.abs(src * mixingPrime) % (long)ceilSqrtNumParts);
            int row = (int)(.MODULE$.abs(dst * mixingPrime) % (long)ceilSqrtNumParts);
            return (col * ceilSqrtNumParts + row) % numParts;
         } else {
            int rows = (numParts + ceilSqrtNumParts - 1) / ceilSqrtNumParts;
            int lastColRows = numParts - rows * (ceilSqrtNumParts - 1);
            int col = (int)(.MODULE$.abs(src * mixingPrime) % (long)numParts / (long)rows);
            int row = (int)(.MODULE$.abs(dst * mixingPrime) % (long)(col < ceilSqrtNumParts - 1 ? rows : lastColRows));
            return col * rows + row;
         }
      }

      public String productPrefix() {
         return "EdgePartition2D";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EdgePartition2D$;
      }

      public int hashCode() {
         return 549318815;
      }

      public String toString() {
         return "EdgePartition2D";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EdgePartition2D$.class);
      }
   }

   public static class EdgePartition1D$ implements PartitionStrategy, Product {
      public static final EdgePartition1D$ MODULE$ = new EdgePartition1D$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int getPartition(final long src, final long dst, final int numParts) {
         long mixingPrime = 1125899906842597L;
         return (int)(.MODULE$.abs(src * mixingPrime) % (long)numParts);
      }

      public String productPrefix() {
         return "EdgePartition1D";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EdgePartition1D$;
      }

      public int hashCode() {
         return 549318784;
      }

      public String toString() {
         return "EdgePartition1D";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EdgePartition1D$.class);
      }
   }

   public static class RandomVertexCut$ implements PartitionStrategy, Product {
      public static final RandomVertexCut$ MODULE$ = new RandomVertexCut$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int getPartition(final long src, final long dst, final int numParts) {
         return .MODULE$.abs((new Tuple2.mcJJ.sp(src, dst)).hashCode()) % numParts;
      }

      public String productPrefix() {
         return "RandomVertexCut";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RandomVertexCut$;
      }

      public int hashCode() {
         return -1836784549;
      }

      public String toString() {
         return "RandomVertexCut";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RandomVertexCut$.class);
      }
   }

   public static class CanonicalRandomVertexCut$ implements PartitionStrategy, Product {
      public static final CanonicalRandomVertexCut$ MODULE$ = new CanonicalRandomVertexCut$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int getPartition(final long src, final long dst, final int numParts) {
         return src < dst ? .MODULE$.abs((new Tuple2.mcJJ.sp(src, dst)).hashCode()) % numParts : .MODULE$.abs((new Tuple2.mcJJ.sp(dst, src)).hashCode()) % numParts;
      }

      public String productPrefix() {
         return "CanonicalRandomVertexCut";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof CanonicalRandomVertexCut$;
      }

      public int hashCode() {
         return -2009178873;
      }

      public String toString() {
         return "CanonicalRandomVertexCut";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CanonicalRandomVertexCut$.class);
      }
   }
}
