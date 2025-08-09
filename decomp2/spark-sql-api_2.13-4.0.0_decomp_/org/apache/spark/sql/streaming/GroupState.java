package org.apache.spark.sql.streaming;

import java.sql.Date;
import java.util.NoSuchElementException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.plans.logical.LogicalGroupState;
import scala.Option;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-daB\b\u0011!\u0003\r\na\u0007\u0005\u0006q\u00011\t!\u000f\u0005\u0006{\u00011\tA\u0010\u0005\u0006#\u00021\tA\u0015\u0005\u0006-\u00021\ta\u0016\u0005\u0006;\u00021\tA\u0018\u0005\u0006?\u00021\t!\u000f\u0005\u0006A\u00021\t!\u0019\u0005\u0006A\u00021\t!\u001e\u0005\b\u0003\u0013\u0001a\u0011AA\u0006\u0011\u001d\tI\u0001\u0001D\u0001\u0003;Aq!!\u0003\u0001\r\u0003\ti\u0003C\u0004\u0002\n\u00011\t!a\u0011\t\u000f\u0005E\u0003A\"\u0001\u0002T!9\u00111\f\u0001\u0007\u0002\u0005M#AC$s_V\u00048\u000b^1uK*\u0011\u0011CE\u0001\ngR\u0014X-Y7j]\u001eT!a\u0005\u000b\u0002\u0007M\fHN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001)\"\u0001H\u0018\u0014\u0007\u0001i2\u0005\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VM\u001a\t\u0004I-jS\"A\u0013\u000b\u0005\u0019:\u0013a\u00027pO&\u001c\u0017\r\u001c\u0006\u0003Q%\nQ\u0001\u001d7b]NT!A\u000b\n\u0002\u0011\r\fG/\u00197zgRL!\u0001L\u0013\u0003#1{w-[2bY\u001e\u0013x.\u001e9Ti\u0006$X\r\u0005\u0002/_1\u0001A!\u0002\u0019\u0001\u0005\u0004\t$!A*\u0012\u0005I*\u0004C\u0001\u00104\u0013\t!tDA\u0004O_RD\u0017N\\4\u0011\u0005y1\u0014BA\u001c \u0005\r\te._\u0001\u0007KbL7\u000f^:\u0016\u0003i\u0002\"AH\u001e\n\u0005qz\"a\u0002\"p_2,\u0017M\\\u0001\u0004O\u0016$X#A\u0017)\u0007\t\u0001u\nE\u0002\u001f\u0003\u000eK!AQ\u0010\u0003\rQD'o\\<t!\t!EJ\u0004\u0002F\u0015:\u0011a)S\u0007\u0002\u000f*\u0011\u0001JG\u0001\u0007yI|w\u000e\u001e \n\u0003\u0001J!aS\u0010\u0002\u000fA\f7m[1hK&\u0011QJ\u0014\u0002\u0017\u001d>\u001cVo\u00195FY\u0016lWM\u001c;Fq\u000e,\u0007\u000f^5p]*\u00111jH\u0011\u0002!\u0006Ir\u000f[3oAM$\u0018\r^3!I>,7\u000f\t8pi\u0002*\u00070[:u\u0003%9W\r^(qi&|g.F\u0001T!\rqB+L\u0005\u0003+~\u0011aa\u00149uS>t\u0017AB;qI\u0006$X\r\u0006\u0002Y7B\u0011a$W\u0005\u00035~\u0011A!\u00168ji\")A\f\u0002a\u0001[\u0005Aa.Z<Ti\u0006$X-\u0001\u0004sK6|g/\u001a\u000b\u00021\u0006Y\u0001.Y:US6,GmT;u\u0003I\u0019X\r\u001e+j[\u0016|W\u000f\u001e#ve\u0006$\u0018n\u001c8\u0015\u0005a\u0013\u0007\"B2\b\u0001\u0004!\u0017A\u00033ve\u0006$\u0018n\u001c8NgB\u0011a$Z\u0005\u0003M~\u0011A\u0001T8oO\"\u001aq\u0001\u001b7\u0011\u0007y\t\u0015\u000e\u0005\u0002EU&\u00111N\u0014\u0002\u0019\u00132dWmZ1m\u0003J<W/\\3oi\u0016C8-\u001a9uS>t\u0017%A7\u0002?%4\u0007e\n3ve\u0006$\u0018n\u001c8Ng\u001e\u0002\u0013n\u001d\u0011o_R\u0004\u0003o\\:ji&4X\rK\u0002\b_N\u00042AH!q!\t!\u0015/\u0003\u0002s\u001d\niRK\\:vaB|'\u000f^3e\u001fB,'/\u0019;j_:,\u0005pY3qi&|g.I\u0001u\u0003=Kg\r\t9s_\u000e,7o]5oO\u0002\"\u0018.\\3!i&lWm\\;uA!\f7\u000f\t8pi\u0002\u0012W-\u001a8!K:\f'\r\\3eA%t\u0007eW7bar4G.\u0019;NCBlvI]8vaN<\u0016\u000e\u001e5Ti\u0006$X\r\u0006\u0002Ym\")q\u000f\u0003a\u0001q\u0006AA-\u001e:bi&|g\u000e\u0005\u0002z{:\u0011!p\u001f\t\u0003\r~I!\u0001`\u0010\u0002\rA\u0013X\rZ3g\u0013\tqxP\u0001\u0004TiJLgn\u001a\u0006\u0003y~AC\u0001\u00035\u0002\u0004\u0005\u0012\u0011QA\u0001&S\u001a\u0004s\u0005Z;sCRLwN\\\u0014!SN\u0004cn\u001c;!C\u00022\u0018\r\\5eA\u0011,(/\u0019;j_:D3\u0001C8t\u0003M\u0019X\r\u001e+j[\u0016|W\u000f\u001e+j[\u0016\u001cH/Y7q)\rA\u0016Q\u0002\u0005\u0007\u0003\u001fI\u0001\u0019\u00013\u0002\u0017QLW.Z:uC6\u0004Xj\u001d\u0015\u0005\u0013!\f\u0019\"\t\u0002\u0002\u0016\u0005A\u0016N\u001a\u0011(i&lWm\u001d;b[Bl5o\n\u0011jg\u0002rw\u000e\u001e\u0011q_NLG/\u001b<fA=\u0014\b\u0005\\3tg\u0002\"\b.\u00198!i\",\u0007eY;se\u0016tG\u000fI<bi\u0016\u0014X.\u0019:lA%t\u0007%\u0019\u0011tiJ,\u0017-\\5oO\u0002\nX/\u001a:zQ\u0011Iq.!\u0007\"\u0005\u0005m\u0011AS5gA\u00154XM\u001c;!i&lW\r\t;j[\u0016|W\u000f\u001e\u0011iCN\u0004cn\u001c;!E\u0016,g\u000eI3oC\ndW\r\u001a\u0011j]\u0002ZV.\u00199}M2\fG/T1q;\u001e\u0013x.\u001e9t/&$\bn\u0015;bi\u0016$R\u0001WA\u0010\u0003CAa!a\u0004\u000b\u0001\u0004!\u0007BBA\u0012\u0015\u0001\u0007\u00010\u0001\nbI\u0012LG/[8oC2$UO]1uS>t\u0007\u0006\u0002\u0006i\u0003O\t#!!\u000b\u0002s&4\u0007eJ1eI&$\u0018n\u001c8bY\u0012+(/\u0019;j_:<\u0003%[:!S:4\u0018\r\\5eA=\u0014\b\u0005\u001e5fA\u0019Lg.\u00197!i&lWm\\;uAQLW.Z:uC6\u0004\b%[:!Y\u0016\u001c8\u000f\t;iC:\u0004C\u000f[3!GV\u0014(/\u001a8uA]\fG/\u001a:nCJ\\\u0007%\u001b8!C\u0002\u001aHO]3b[&tw\rI9vKJL\b\u0006\u0002\u0006p\u00033!2\u0001WA\u0018\u0011\u001d\t\td\u0003a\u0001\u0003g\t\u0011\u0002^5nKN$\u0018-\u001c9\u0011\t\u0005U\u0012QH\u0007\u0003\u0003oQ1aEA\u001d\u0015\t\tY$\u0001\u0003kCZ\f\u0017\u0002BA \u0003o\u0011A\u0001R1uK\"\"1b\\A\r)\u0015A\u0016QIA$\u0011\u001d\t\t\u0004\u0004a\u0001\u0003gAa!a\t\r\u0001\u0004A\b\u0006\u0002\u0007i\u0003\u0017\n#!!\u0014\u0002E%4\u0007eJ1eI&$\u0018n\u001c8bY\u0012+(/\u0019;j_:<\u0003%[:!S:4\u0018\r\\5eQ\u0011aq.!\u0007\u0002+\u001d,GoQ;se\u0016tGoV1uKJl\u0017M]6NgR\tA\r\u000b\u0003\u000e_\u0006]\u0013EAA-\u0003\u0011Kg\rI<bi\u0016\u0014X.\u0019:lA!\f7\u000f\t8pi\u0002\u0012W-\u001a8!g\u0016$\bEY3g_J,\u0007%\u001b8!76\f\u0007\u000f 4mCRl\u0015\r]/He>,\bo],ji\"\u001cF/\u0019;f\u0003i9W\r^\"veJ,g\u000e\u001e)s_\u000e,7o]5oORKW.Z'tQ\r\u0001\u0011q\f\t\u0005\u0003C\n9'\u0004\u0002\u0002d)\u0019\u0011Q\r\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002j\u0005\r$\u0001C#w_24\u0018N\\4"
)
public interface GroupState extends LogicalGroupState {
   boolean exists();

   Object get() throws NoSuchElementException;

   Option getOption();

   void update(final Object newState);

   void remove();

   boolean hasTimedOut();

   void setTimeoutDuration(final long durationMs) throws IllegalArgumentException, UnsupportedOperationException;

   void setTimeoutDuration(final String duration) throws IllegalArgumentException, UnsupportedOperationException;

   void setTimeoutTimestamp(final long timestampMs) throws IllegalArgumentException, UnsupportedOperationException;

   void setTimeoutTimestamp(final long timestampMs, final String additionalDuration) throws IllegalArgumentException, UnsupportedOperationException;

   void setTimeoutTimestamp(final Date timestamp) throws UnsupportedOperationException;

   void setTimeoutTimestamp(final Date timestamp, final String additionalDuration) throws IllegalArgumentException, UnsupportedOperationException;

   long getCurrentWatermarkMs() throws UnsupportedOperationException;

   long getCurrentProcessingTimeMs();
}
