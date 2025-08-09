package org.apache.spark.graphx.impl;

import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.util.collection.BitSet;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001B\f\u0019\t\rBA\"\u0010\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\nyBA\"\u0011\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\n\tCA\"\u0012\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\n\u0019CQA\u0014\u0001\u0005\u0002=C\u0011\u0002\u0017\u0001A\u0002\u0003\u0005\u000b\u0015B-\t\u0013-\u0004\u0001\u0019!A!B\u0013I\u0006\"\u00037\u0001\u0001\u0004\u0005\t\u0015)\u0003n\u0011%\u0001\b\u00011A\u0001B\u0003&Q\u000eC\u0005r\u0001\u0001\u0007\t\u0011)Q\u0005S!I!\u000f\u0001a\u0001\u0002\u0003\u0006K!\u000b\u0005\ng\u0002\u0001\r\u0011!Q!\n]BQ\u0001\u001e\u0001\u0005\u0002UDq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u0002\u001a\u0001!\t!a\u0007\t\ri\u0004A\u0011IA\u0013\u0011\u0019a\b\u0001\"\u0011\u0002&!9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0002bBA\u0005\u0001\u0011\u0005\u0013q\u0005\u0005\b\u0003\u001b\u0001A\u0011IA\u0015\u0011\u001d\tY\u0003\u0001C!\u0003[Aq!a\r\u0001\t\u0003\n)\u0004C\u0004\u0002:\u0001!I!a\u000f\u0003-\u0005;wM]3hCRLgnZ#eO\u0016\u001cuN\u001c;fqRT!!\u0007\u000e\u0002\t%l\u0007\u000f\u001c\u0006\u00037q\taa\u001a:ba\"D(BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0004\u0001U!Ae\u000b\u001d<'\t\u0001Q\u0005E\u0003'O%:$(D\u0001\u001b\u0013\tA#DA\u0006FI\u001e,7i\u001c8uKb$\bC\u0001\u0016,\u0019\u0001!Q\u0001\f\u0001C\u00025\u0012!A\u0016#\u0012\u00059\"\u0004CA\u00183\u001b\u0005\u0001$\"A\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0002$a\u0002(pi\"Lgn\u001a\t\u0003_UJ!A\u000e\u0019\u0003\u0007\u0005s\u0017\u0010\u0005\u0002+q\u0011)\u0011\b\u0001b\u0001[\t\u0011Q\t\u0012\t\u0003Um\"Q\u0001\u0010\u0001C\u00025\u0012\u0011!Q\u0001>_J<G%\u00199bG\",Ge\u001d9be.$sM]1qQb$\u0013.\u001c9mI\u0005;wM]3hCRLgnZ#eO\u0016\u001cuN\u001c;fqR$C%\\3sO\u0016l5o\u001a\t\u0006_}R$HO\u0005\u0003\u0001B\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\u007f=\u0014x\rJ1qC\u000eDW\rJ:qCJ\\Ge\u001a:ba\"DH%[7qY\u0012\num\u001a:fO\u0006$\u0018N\\4FI\u001e,7i\u001c8uKb$H\u0005J1hOJ,w-\u0019;fgB\u0019qf\u0011\u001e\n\u0005\u0011\u0003$!B!se\u0006L\u0018aO8sO\u0012\n\u0007/Y2iK\u0012\u001a\b/\u0019:lI\u001d\u0014\u0018\r\u001d5yI%l\u0007\u000f\u001c\u0013BO\u001e\u0014XmZ1uS:<W\tZ4f\u0007>tG/\u001a=uI\u0011\u0012\u0017\u000e^:fiB\u0011q\tT\u0007\u0002\u0011*\u0011\u0011JS\u0001\u000bG>dG.Z2uS>t'BA&\u001d\u0003\u0011)H/\u001b7\n\u00055C%A\u0002\"jiN+G/\u0001\u0004=S:LGO\u0010\u000b\u0005!J#f\u000bE\u0003R\u0001%:$(D\u0001\u0019\u0011\u0015\u0019F\u00011\u0001?\u0003!iWM]4f\u001bN<\u0007\"B+\u0005\u0001\u0004\u0011\u0015AC1hOJ,w-\u0019;fg\")q\u000b\u0002a\u0001\r\u00061!-\u001b;tKR\faaX:sG&#\u0007C\u0001.i\u001d\tYfM\u0004\u0002]K:\u0011Q\f\u001a\b\u0003=\u000et!a\u00182\u000e\u0003\u0001T!!\u0019\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0013BA\u0010!\u0013\tib$\u0003\u0002\u001c9%\u0011qMG\u0001\ba\u0006\u001c7.Y4f\u0013\tI'N\u0001\u0005WKJ$X\r_%e\u0015\t9'$\u0001\u0004`IN$\u0018\nZ\u0001\f?2|7-\u00197Te\u000eLE\r\u0005\u00020]&\u0011q\u000e\r\u0002\u0004\u0013:$\u0018aC0m_\u000e\fG\u000eR:u\u0013\u0012\f\u0001bX:sG\u0006#HO]\u0001\t?\u0012\u001cH/\u0011;ue\u0006)q,\u0019;ue\u0006\u00191/\u001a;\u0015\u0017YL80`@\u0002\u0004\u0005\u001d\u00111\u0002\t\u0003_]L!\u0001\u001f\u0019\u0003\tUs\u0017\u000e\u001e\u0005\u0006u2\u0001\r!W\u0001\u0006gJ\u001c\u0017\n\u001a\u0005\u0006y2\u0001\r!W\u0001\u0006IN$\u0018\n\u001a\u0005\u0006}2\u0001\r!\\\u0001\u000bY>\u001c\u0017\r\\*sG&#\u0007BBA\u0001\u0019\u0001\u0007Q.\u0001\u0006m_\u000e\fG\u000eR:u\u0013\u0012Da!!\u0002\r\u0001\u0004I\u0013aB:sG\u0006#HO\u001d\u0005\u0007\u0003\u0013a\u0001\u0019A\u0015\u0002\u000f\u0011\u001cH/\u0011;ue\"1\u0011Q\u0002\u0007A\u0002]\nA!\u0019;ue\u0006Q1/\u001a;Te\u000e|e\u000e\\=\u0015\u000fY\f\u0019\"!\u0006\u0002\u0018!)!0\u0004a\u00013\")a0\u0004a\u0001[\"1\u0011QA\u0007A\u0002%\nqa]3u\t\u0016\u001cH\u000fF\u0005w\u0003;\ty\"!\t\u0002$!)AP\u0004a\u00013\"1\u0011\u0011\u0001\bA\u00025Da!!\u0003\u000f\u0001\u0004I\u0003BBA\u0007\u001d\u0001\u0007q'F\u0001Z+\u0005IS#A\u001c\u0002\u0013M,g\u000e\u001a+p'J\u001cGc\u0001<\u00020!1\u0011\u0011\u0007\u000bA\u0002i\n1!\\:h\u0003%\u0019XM\u001c3U_\u0012\u001bH\u000fF\u0002w\u0003oAa!!\r\u0016\u0001\u0004Q\u0014\u0001B:f]\u0012$RA^A\u001f\u0003\u0003Ba!a\u0010\u0017\u0001\u0004i\u0017a\u00027pG\u0006d\u0017\n\u001a\u0005\u0007\u0003c1\u0002\u0019\u0001\u001e)\u0007Y\t)\u0005E\u00020\u0003\u000fJ1!!\u00131\u0005\u0019Ig\u000e\\5oK\u0002"
)
public class AggregatingEdgeContext extends EdgeContext {
   public final Function2 org$apache$spark$graphx$impl$AggregatingEdgeContext$$mergeMsg;
   public final Object org$apache$spark$graphx$impl$AggregatingEdgeContext$$aggregates;
   public final BitSet org$apache$spark$graphx$impl$AggregatingEdgeContext$$bitset;
   private long _srcId;
   private long _dstId;
   private int _localSrcId;
   private int _localDstId;
   private Object _srcAttr;
   private Object _dstAttr;
   private Object _attr;

   public void set(final long srcId, final long dstId, final int localSrcId, final int localDstId, final Object srcAttr, final Object dstAttr, final Object attr) {
      this._srcId = srcId;
      this._dstId = dstId;
      this._localSrcId = localSrcId;
      this._localDstId = localDstId;
      this._srcAttr = srcAttr;
      this._dstAttr = dstAttr;
      this._attr = attr;
   }

   public void setSrcOnly(final long srcId, final int localSrcId, final Object srcAttr) {
      this._srcId = srcId;
      this._localSrcId = localSrcId;
      this._srcAttr = srcAttr;
   }

   public void setDest(final long dstId, final int localDstId, final Object dstAttr, final Object attr) {
      this._dstId = dstId;
      this._localDstId = localDstId;
      this._dstAttr = dstAttr;
      this._attr = attr;
   }

   public long srcId() {
      return this._srcId;
   }

   public long dstId() {
      return this._dstId;
   }

   public Object srcAttr() {
      return this._srcAttr;
   }

   public Object dstAttr() {
      return this._dstAttr;
   }

   public Object attr() {
      return this._attr;
   }

   public void sendToSrc(final Object msg) {
      this.send(this._localSrcId, msg);
   }

   public void sendToDst(final Object msg) {
      this.send(this._localDstId, msg);
   }

   private void send(final int localId, final Object msg) {
      if (this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$bitset.get(localId)) {
         .MODULE$.array_update(this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$aggregates, localId, this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$mergeMsg.apply(.MODULE$.array_apply(this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$aggregates, localId), msg));
      } else {
         .MODULE$.array_update(this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$aggregates, localId, msg);
         this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$bitset.set(localId);
      }
   }

   public AggregatingEdgeContext(final Function2 mergeMsg, final Object aggregates, final BitSet bitset) {
      this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$mergeMsg = mergeMsg;
      this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$aggregates = aggregates;
      this.org$apache$spark$graphx$impl$AggregatingEdgeContext$$bitset = bitset;
   }
}
