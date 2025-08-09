package org.apache.spark.ml.fpm;

import org.apache.spark.sql.Dataset;
import scala.collection.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=<a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$\u0001\tBgN|7-[1uS>t'+\u001e7fg*\u0011aaB\u0001\u0004MBl'B\u0001\u0005\n\u0003\tiGN\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h!\t\u0001\u0012!D\u0001\u0006\u0005A\t5o]8dS\u0006$\u0018n\u001c8Sk2,7o\u0005\u0002\u0002'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001f\u0005Ir-\u001a;BgN|7-[1uS>t'+\u001e7fg\u001a\u0013x.\u001c$Q+\tqb\bF\u0004 \u000fF[VL\u00196\u0015\u0005\u0001\"\u0004CA\u00112\u001d\t\u0011cF\u0004\u0002$Y9\u0011Ae\u000b\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\u000e\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011B\u0001\u0007\u000e\u0013\tQ1\"\u0003\u0002.\u0013\u0005\u00191/\u001d7\n\u0005=\u0002\u0014a\u00029bG.\fw-\u001a\u0006\u0003[%I!AM\u001a\u0003\u0013\u0011\u000bG/\u0019$sC6,'BA\u00181\u0011\u001d)4!!AA\u0004Y\n!\"\u001a<jI\u0016t7-\u001a\u00133!\r9$\bP\u0007\u0002q)\u0011\u0011(F\u0001\be\u00164G.Z2u\u0013\tY\u0004H\u0001\u0005DY\u0006\u001c8\u000fV1h!\tid\b\u0004\u0001\u0005\u000b}\u001a!\u0019\u0001!\u0003\u0003Q\u000b\"!\u0011#\u0011\u0005Q\u0011\u0015BA\"\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001F#\n\u0005\u0019+\"aA!os\")\u0001j\u0001a\u0001\u0013\u00069A-\u0019;bg\u0016$\bG\u0001&P!\rYEJT\u0007\u0002a%\u0011Q\n\r\u0002\b\t\u0006$\u0018m]3u!\tit\nB\u0005Q\u000f\u0006\u0005\t\u0011!B\u0001\u0001\n\u0019q\fJ\u001b\t\u000bI\u001b\u0001\u0019A*\u0002\u0011%$X-\\:D_2\u0004\"\u0001\u0016-\u000f\u0005U3\u0006C\u0001\u0014\u0016\u0013\t9V#\u0001\u0004Qe\u0016$WMZ\u0005\u00033j\u0013aa\u0015;sS:<'BA,\u0016\u0011\u0015a6\u00011\u0001T\u0003\u001d1'/Z9D_2DQAX\u0002A\u0002}\u000bQ\"\\5o\u0007>tg-\u001b3f]\u000e,\u0007C\u0001\u000ba\u0013\t\tWC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006G\u000e\u0001\r\u0001Z\u0001\fSR,WnU;qa>\u0014H\u000f\u0005\u0003fQrzV\"\u00014\u000b\u0005\u001d,\u0012AC2pY2,7\r^5p]&\u0011\u0011N\u001a\u0002\u0004\u001b\u0006\u0004\b\"B6\u0004\u0001\u0004a\u0017A\u00058v[R\u0013\u0018-\u001b8j]\u001e\u0014VmY8sIN\u0004\"\u0001F7\n\u00059,\"\u0001\u0002'p]\u001e\u0004"
)
public final class AssociationRules {
   public static Dataset getAssociationRulesFromFP(final Dataset dataset, final String itemsCol, final String freqCol, final double minConfidence, final Map itemSupport, final long numTrainingRecords, final ClassTag evidence$2) {
      return AssociationRules$.MODULE$.getAssociationRulesFromFP(dataset, itemsCol, freqCol, minConfidence, itemSupport, numTrainingRecords, evidence$2);
   }
}
