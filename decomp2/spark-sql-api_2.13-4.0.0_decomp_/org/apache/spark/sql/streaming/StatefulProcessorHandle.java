package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.Encoder;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005mda\u0002\u0007\u000e!\u0003\r\n\u0001\u0007\u0005\u0006O\u00011\t\u0001\u000b\u0005\u0006O\u00011\t!\u0015\u0005\u00069\u00021\t!\u0018\u0005\u00069\u00021\t\u0001\u001b\u0005\u0006g\u00021\t\u0001\u001e\u0005\u0007g\u00021\t!!\u0004\t\u000f\u00055\u0002A\"\u0001\u00020!9\u0011q\u0007\u0001\u0007\u0002\u0005e\u0002bBA&\u0001\u0019\u0005\u0011Q\n\u0005\b\u0003#\u0002a\u0011AA*\u0011\u001d\t9\u0007\u0001D\u0001\u0003S\u0012qc\u0015;bi\u00164W\u000f\u001c)s_\u000e,7o]8s\u0011\u0006tG\r\\3\u000b\u00059y\u0011!C:ue\u0016\fW.\u001b8h\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001'\r\u0001\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0001*S\"A\u0011\u000b\u0005\t\u001a\u0013AA5p\u0015\u0005!\u0013\u0001\u00026bm\u0006L!AJ\u0011\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\u001b\u001d,GOV1mk\u0016\u001cF/\u0019;f+\tI\u0003\u0007\u0006\u0003+s\u0019c\u0005cA\u0016-]5\tQ\"\u0003\u0002.\u001b\tQa+\u00197vKN#\u0018\r^3\u0011\u0005=\u0002D\u0002\u0001\u0003\u0006c\u0005\u0011\rA\r\u0002\u0002)F\u00111G\u000e\t\u00035QJ!!N\u000e\u0003\u000f9{G\u000f[5oOB\u0011!dN\u0005\u0003qm\u00111!\u00118z\u0011\u0015Q\u0014\u00011\u0001<\u0003%\u0019H/\u0019;f\u001d\u0006lW\r\u0005\u0002=\u0007:\u0011Q(\u0011\t\u0003}mi\u0011a\u0010\u0006\u0003\u0001^\ta\u0001\u0010:p_Rt\u0014B\u0001\"\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011A)\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\t[\u0002\"B$\u0002\u0001\u0004A\u0015A\u0003<bY\u0016s7m\u001c3feB\u0019\u0011J\u0013\u0018\u000e\u0003=I!aS\b\u0003\u000f\u0015s7m\u001c3fe\")Q*\u0001a\u0001\u001d\u0006IA\u000f\u001e7D_:4\u0017n\u001a\t\u0003W=K!\u0001U\u0007\u0003\u0013Q#FjQ8oM&<WC\u0001*W)\r\u0019&l\u0017\u000b\u0003)^\u00032a\u000b\u0017V!\tyc\u000bB\u00032\u0005\t\u0007!\u0007C\u0004Y\u0005\u0005\u0005\t9A-\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002J\u0015VCQA\u000f\u0002A\u0002mBQ!\u0014\u0002A\u00029\u000bAbZ3u\u0019&\u001cHo\u0015;bi\u0016,\"AX2\u0015\t}#Wm\u001a\t\u0004W\u0001\u0014\u0017BA1\u000e\u0005%a\u0015n\u001d;Ti\u0006$X\r\u0005\u00020G\u0012)\u0011g\u0001b\u0001e!)!h\u0001a\u0001w!)qi\u0001a\u0001MB\u0019\u0011J\u00132\t\u000b5\u001b\u0001\u0019\u0001(\u0016\u0005%lGc\u00016reR\u00111N\u001c\t\u0004W\u0001d\u0007CA\u0018n\t\u0015\tDA1\u00013\u0011\u001dyG!!AA\u0004A\f!\"\u001a<jI\u0016t7-\u001a\u00133!\rI%\n\u001c\u0005\u0006u\u0011\u0001\ra\u000f\u0005\u0006\u001b\u0012\u0001\rAT\u0001\fO\u0016$X*\u00199Ti\u0006$X-F\u0002vuv$\u0002B^@\u0002\u0002\u0005\u001d\u00111\u0002\t\u0005W]LH0\u0003\u0002y\u001b\tAQ*\u00199Ti\u0006$X\r\u0005\u00020u\u0012)10\u0002b\u0001e\t\t1\n\u0005\u00020{\u0012)a0\u0002b\u0001e\t\ta\u000bC\u0003;\u000b\u0001\u00071\bC\u0004\u0002\u0004\u0015\u0001\r!!\u0002\u0002\u0015U\u001cXM]&fs\u0016s7\rE\u0002J\u0015fDaaR\u0003A\u0002\u0005%\u0001cA%Ky\")Q*\u0002a\u0001\u001dV1\u0011qBA\f\u00037!b!!\u0005\u0002*\u0005-BCBA\n\u0003;\t\u0019\u0003\u0005\u0004,o\u0006U\u0011\u0011\u0004\t\u0004_\u0005]A!B>\u0007\u0005\u0004\u0011\u0004cA\u0018\u0002\u001c\u0011)aP\u0002b\u0001e!I\u0011q\u0004\u0004\u0002\u0002\u0003\u000f\u0011\u0011E\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B%K\u0003+A\u0011\"!\n\u0007\u0003\u0003\u0005\u001d!a\n\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003J\u0015\u0006e\u0001\"\u0002\u001e\u0007\u0001\u0004Y\u0004\"B'\u0007\u0001\u0004q\u0015\u0001D4fiF+XM]=J]\u001a|GCAA\u0019!\rY\u00131G\u0005\u0004\u0003ki!!C)vKJL\u0018J\u001c4p\u00035\u0011XmZ5ti\u0016\u0014H+[7feR!\u00111HA!!\rQ\u0012QH\u0005\u0004\u0003\u007fY\"\u0001B+oSRDq!a\u0011\t\u0001\u0004\t)%A\tfqBL'/\u001f+j[\u0016\u001cH/Y7q\u001bN\u00042AGA$\u0013\r\tIe\u0007\u0002\u0005\u0019>tw-A\u0006eK2,G/\u001a+j[\u0016\u0014H\u0003BA\u001e\u0003\u001fBq!a\u0011\n\u0001\u0004\t)%\u0001\u0006mSN$H+[7feN$\"!!\u0016\u0011\r\u0005]\u0013\u0011MA#\u001d\u0011\tI&!\u0018\u000f\u0007y\nY&C\u0001\u001d\u0013\r\tyfG\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019'!\u001a\u0003\u0011%#XM]1u_JT1!a\u0018\u001c\u00039!W\r\\3uK&3W\t_5tiN$B!a\u000f\u0002l!)!h\u0003a\u0001w!\u001a\u0001!a\u001c\u0011\t\u0005E\u0014qO\u0007\u0003\u0003gR1!!\u001e\u0012\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003s\n\u0019H\u0001\u0005Fm>dg/\u001b8h\u0001"
)
public interface StatefulProcessorHandle extends Serializable {
   ValueState getValueState(final String stateName, final Encoder valEncoder, final TTLConfig ttlConfig);

   ValueState getValueState(final String stateName, final TTLConfig ttlConfig, final Encoder evidence$1);

   ListState getListState(final String stateName, final Encoder valEncoder, final TTLConfig ttlConfig);

   ListState getListState(final String stateName, final TTLConfig ttlConfig, final Encoder evidence$2);

   MapState getMapState(final String stateName, final Encoder userKeyEnc, final Encoder valEncoder, final TTLConfig ttlConfig);

   MapState getMapState(final String stateName, final TTLConfig ttlConfig, final Encoder evidence$3, final Encoder evidence$4);

   QueryInfo getQueryInfo();

   void registerTimer(final long expiryTimestampMs);

   void deleteTimer(final long expiryTimestampMs);

   Iterator listTimers();

   void deleteIfExists(final String stateName);
}
