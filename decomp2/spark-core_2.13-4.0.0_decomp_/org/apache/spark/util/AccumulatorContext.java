package org.apache.spark.util;

import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ErAB\t\u0013\u0011\u0003!\"D\u0002\u0004\u001d%!\u0005A#\b\u0005\u0006U\u0005!\t\u0001\f\u0005\b[\u0005\u0011\r\u0011\"\u0003/\u0011\u0019Y\u0015\u0001)A\u0005_!1a+\u0001Q\u0001\n]Ca!X\u0001!\u0002\u0013q\u0006BB1\u0002A\u0003%a\fC\u0003c\u0003\u0011\u00051\rC\u0003e\u0003\u0011\u0005Q\rC\u0003j\u0003\u0011\u0005!\u000eC\u0003y\u0003\u0011\u0005\u0011\u0010C\u0003}\u0003\u0011\u0005Q\u0010C\u0004\u0002\u0016\u0005!\t!a\u0006\t\u000f\u0005e\u0011\u0001\"\u0001\u0002\u001c!Q\u00111E\u0001C\u0002\u0013\u0005A#!\n\t\u0011\u0005=\u0012\u0001)A\u0005\u0003O\t!#Q2dk6,H.\u0019;pe\u000e{g\u000e^3yi*\u00111\u0003F\u0001\u0005kRLGN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h!\tY\u0012!D\u0001\u0013\u0005I\t5mY;nk2\fGo\u001c:D_:$X\r\u001f;\u0014\u0007\u0005qB\u0005\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003E\u0001\u0004B]f\u0014VM\u001a\t\u0003K!j\u0011A\n\u0006\u0003OQ\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003S\u0019\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005Q\u0012!C8sS\u001eLg.\u00197t+\u0005y\u0003\u0003\u0002\u00197qmj\u0011!\r\u0006\u0003eM\n!bY8oGV\u0014(/\u001a8u\u0015\t\u0019BGC\u00016\u0003\u0011Q\u0017M^1\n\u0005]\n$!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB\u0011q$O\u0005\u0003u\u0001\u0012A\u0001T8oOB\u0019A(Q\"\u000e\u0003uR!AP \u0002\u0007I,gM\u0003\u0002Ai\u0005!A.\u00198h\u0013\t\u0011UHA\u0007XK\u0006\\'+\u001a4fe\u0016t7-\u001a\u0019\u0004\t&#\u0006\u0003B\u000eF\u000fNK!A\u0012\n\u0003\u001b\u0005\u001b7-^7vY\u0006$xN\u001d,3!\tA\u0015\n\u0004\u0001\u0005\u0013)#\u0011\u0011!A\u0001\u0006\u0003a%aA0%c\u0005QqN]5hS:\fGn\u001d\u0011\u0012\u00055\u0003\u0006CA\u0010O\u0013\ty\u0005EA\u0004O_RD\u0017N\\4\u0011\u0005}\t\u0016B\u0001*!\u0005\r\te.\u001f\t\u0003\u0011R#\u0011\"\u0016\u0003\u0002\u0002\u0003\u0005)\u0011\u0001'\u0003\u0007}##'\u0001\u0004oKb$\u0018\n\u001a\t\u00031nk\u0011!\u0017\u0006\u00035F\na!\u0019;p[&\u001c\u0017B\u0001/Z\u0005)\tEo\\7jG2{gnZ\u0001\u000fg>lWm\u00144NS:,8o\u00148f!\ryr\fO\u0005\u0003A\u0002\u0012AaU8nK\u0006Q1o\\7f\u001f\u001aTVM]8\u0002\u000b9,w/\u00133\u0015\u0003a\n\u0011B\\;n\u0003\u000e\u001cW/\\:\u0016\u0003\u0019\u0004\"aH4\n\u0005!\u0004#aA%oi\u0006A!/Z4jgR,'\u000f\u0006\u0002l]B\u0011q\u0004\\\u0005\u0003[\u0002\u0012A!\u00168ji\")qN\u0003a\u0001a\u0006\t\u0011\rM\u0002rgZ\u0004BaG#skB\u0011\u0001j\u001d\u0003\ni:\f\t\u0011!A\u0003\u00021\u00131a\u0018\u00134!\tAe\u000fB\u0005x]\u0006\u0005\t\u0011!B\u0001\u0019\n\u0019q\f\n\u001b\u0002\rI,Wn\u001c<f)\tY'\u0010C\u0003|\u0017\u0001\u0007\u0001(\u0001\u0002jI\u0006\u0019q-\u001a;\u0015\u0007y\f\u0019\u0002\u0005\u0003 \u007f\u0006\r\u0011bAA\u0001A\t1q\n\u001d;j_:\u0004d!!\u0002\u0002\n\u0005=\u0001CB\u000eF\u0003\u000f\ti\u0001E\u0002I\u0003\u0013!!\"a\u0003\r\u0003\u0003\u0005\tQ!\u0001M\u0005\ryFe\u000e\t\u0004\u0011\u0006=AACA\t\u0019\u0005\u0005\t\u0011!B\u0001\u0019\n\u0019q\f\n\u001d\t\u000bmd\u0001\u0019\u0001\u001d\u0002\u000b\rdW-\u0019:\u0015\u0003-\fA\"\u001b8uKJtw\n\u001d;j_:$B!!\b\u0002 A\u0019qd )\t\u000f\u0005\u0005b\u00021\u0001\u0002\u001e\u0005)a/\u00197vK\u0006!2+\u0015'`\u0003\u000e\u001bU+T0J\t\u0016sE+\u0013$J\u000bJ+\"!a\n\u0011\t\u0005%\u00121F\u0007\u0002\u007f%\u0019\u0011QF \u0003\rM#(/\u001b8h\u0003U\u0019\u0016\u000bT0B\u0007\u000e+VjX%E\u000b:#\u0016JR%F%\u0002\u0002"
)
public final class AccumulatorContext {
   public static Option internOption(final Option value) {
      return AccumulatorContext$.MODULE$.internOption(value);
   }

   public static void clear() {
      AccumulatorContext$.MODULE$.clear();
   }

   public static Option get(final long id) {
      return AccumulatorContext$.MODULE$.get(id);
   }

   public static void remove(final long id) {
      AccumulatorContext$.MODULE$.remove(id);
   }

   public static void register(final AccumulatorV2 a) {
      AccumulatorContext$.MODULE$.register(a);
   }

   public static int numAccums() {
      return AccumulatorContext$.MODULE$.numAccums();
   }

   public static long newId() {
      return AccumulatorContext$.MODULE$.newId();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return AccumulatorContext$.MODULE$.LogStringContext(sc);
   }
}
