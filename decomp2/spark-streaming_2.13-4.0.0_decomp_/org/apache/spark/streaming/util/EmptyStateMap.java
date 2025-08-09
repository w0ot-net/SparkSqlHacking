package org.apache.spark.streaming.util;

import scala.Option;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Q!\u0003\u0006\u0001\u0019QAQ\u0001\f\u0001\u0005\u00025BQa\f\u0001\u0005BABQ!\u0010\u0001\u0005ByBQa\u0011\u0001\u0005B\u0011CQA\u0016\u0001\u0005B]CQ\u0001\u0017\u0001\u0005BeCQA\u0017\u0001\u0005BmCQ!\u0018\u0001\u0005By\u0013Q\"R7qif\u001cF/\u0019;f\u001b\u0006\u0004(BA\u0006\r\u0003\u0011)H/\u001b7\u000b\u00055q\u0011!C:ue\u0016\fW.\u001b8h\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<WcA\u000b\u001dUM\u0011\u0001A\u0006\t\u0005/aQ\u0012&D\u0001\u000b\u0013\tI\"B\u0001\u0005Ti\u0006$X-T1q!\tYB\u0004\u0004\u0001\u0005\u000bu\u0001!\u0019A\u0010\u0003\u0003-\u001b\u0001!\u0005\u0002!MA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t9aj\u001c;iS:<\u0007CA\u0011(\u0013\tA#EA\u0002B]f\u0004\"a\u0007\u0016\u0005\u000b-\u0002!\u0019A\u0010\u0003\u0003M\u000ba\u0001P5oSRtD#\u0001\u0018\u0011\t]\u0001!$K\u0001\u0004aV$H\u0003B\u00195ma\u0002\"!\t\u001a\n\u0005M\u0012#\u0001B+oSRDQ!\u000e\u0002A\u0002i\t1a[3z\u0011\u00159$\u00011\u0001*\u0003\u001d\u0019Xm]:j_:DQ!\u000f\u0002A\u0002i\n!\"\u001e9eCR,G+[7f!\t\t3(\u0003\u0002=E\t!Aj\u001c8h\u0003\r9W\r\u001e\u000b\u0003\u007f\t\u00032!\t!*\u0013\t\t%E\u0001\u0004PaRLwN\u001c\u0005\u0006k\r\u0001\rAG\u0001\nO\u0016$()\u001f+j[\u0016$\"!\u0012+\u0011\u0007\u0019s\u0015K\u0004\u0002H\u0019:\u0011\u0001jS\u0007\u0002\u0013*\u0011!JH\u0001\u0007yI|w\u000e\u001e \n\u0003\rJ!!\u0014\u0012\u0002\u000fA\f7m[1hK&\u0011q\n\u0015\u0002\t\u0013R,'/\u0019;pe*\u0011QJ\t\t\u0006CIS\u0012FO\u0005\u0003'\n\u0012a\u0001V;qY\u0016\u001c\u0004\"B+\u0005\u0001\u0004Q\u0014!\u0005;ie\u0016\u001c\b.\u00169eCR,G\rV5nK\u00061q-\u001a;BY2$\u0012!R\u0001\u0005G>\u0004\u0018\u0010F\u0001\u0017\u0003\u0019\u0011X-\\8wKR\u0011\u0011\u0007\u0018\u0005\u0006k\u001d\u0001\rAG\u0001\u000ei>$UMY;h'R\u0014\u0018N\\4\u0015\u0003}\u0003\"\u0001\u00193\u000f\u0005\u0005\u0014\u0007C\u0001%#\u0013\t\u0019'%\u0001\u0004Qe\u0016$WMZ\u0005\u0003K\u001a\u0014aa\u0015;sS:<'BA2#\u0001"
)
public class EmptyStateMap extends StateMap {
   public void put(final Object key, final Object session, final long updateTime) {
      throw new UnsupportedOperationException("put() should not be called on an EmptyStateMap");
   }

   public Option get(final Object key) {
      return .MODULE$;
   }

   public Iterator getByTime(final long threshUpdatedTime) {
      return scala.package..MODULE$.Iterator().empty();
   }

   public Iterator getAll() {
      return scala.package..MODULE$.Iterator().empty();
   }

   public StateMap copy() {
      return this;
   }

   public void remove(final Object key) {
   }

   public String toDebugString() {
      return "";
   }
}
