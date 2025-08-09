package org.apache.spark.graphx;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.graphx.lib.ConnectedComponents$;
import org.apache.spark.graphx.lib.PageRank$;
import org.apache.spark.graphx.lib.StronglyConnectedComponents$;
import org.apache.spark.graphx.lib.TriangleCount$;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015h\u0001\u0002\u0015*\u0001IB\u0001B\u0012\u0001\u0003\u0002\u0003\u0006Ia\u0012\u0005\t3\u0002\u0011\u0019\u0011)A\u00065\"A\u0001\r\u0001B\u0002B\u0003-\u0011\rC\u0003c\u0001\u0011\u00051\r\u0003\u0005j\u0001!\u0015\r\u0011\"\u0001k\u0011!\u0011\b\u0001#b\u0001\n\u0003Q\u0007\u0002\u0003;\u0001\u0011\u000b\u0007I\u0011A;\t\u0011u\u0004\u0001R1A\u0005\u0002UD\u0001b \u0001\t\u0006\u0004%\t!\u001e\u0005\b\u0003\u0007\u0001A\u0011BA\u0003\u0011\u001d\t\t\u0002\u0001C\u0001\u0003'Aq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u0002<\u0001!\t!!\u0010\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N!9\u0011q\n\u0001\u0005\u0002\u0005E\u0003bBAA\u0001\u0011\u0005\u00111\u0011\u0005\n\u0003\u0013\u0004\u0011\u0013!C\u0001\u0003\u0017D\u0011\"a<\u0001#\u0003%\t!!=\t\u000f\u0005}\b\u0001\"\u0001\u0003\u0002!9!1\u0001\u0001\u0005\u0002\t\u0015\u0001\"\u0003B\u0007\u0001E\u0005I\u0011\u0001B\b\u0011\u001d\u0011\u0019\u0002\u0001C\u0001\u0005+A\u0011B!\u0015\u0001#\u0003%\tAa\u0015\t\u0013\tm\u0003!%A\u0005\u0002\tu\u0003b\u0002B3\u0001\u0011\u0005!q\r\u0005\n\u0005s\u0002\u0011\u0013!C\u0001\u0005wBqAa \u0001\t\u0003\u0011\t\tC\u0005\u0003\f\u0002\t\n\u0011\"\u0001\u0003|!9!Q\u0012\u0001\u0005\u0002\t=\u0005\"\u0003BW\u0001E\u0005I\u0011\u0001B>\u0011\u001d\u0011y\u000b\u0001C\u0001\u0005cC\u0011B!/\u0001#\u0003%\tAa\u001f\t\u000f\tm\u0006\u0001\"\u0001\u0003>\"I!1\u0019\u0001\u0012\u0002\u0013\u0005!1\u0010\u0005\b\u0005w\u0003A\u0011\u0001Bc\u0011\u001d\u0011y\r\u0001C\u0001\u0005#DqAa4\u0001\t\u0003\u0011)\u000eC\u0004\u0003Z\u0002!\tAa7\t\u000f\t}\u0007\u0001\"\u0001\u0003b\nAqI]1qQ>\u00038O\u0003\u0002+W\u00051qM]1qQbT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\u0002\u0001+\r\u0019TjV\n\u0004\u0001QR\u0004CA\u001b9\u001b\u00051$\"A\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005e2$AB!osJ+g\r\u0005\u0002<\u0007:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fE\na\u0001\u0010:p_Rt\u0014\"A\u001c\n\u0005\t3\u0014a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0011\u001c\u0002\u000b\u001d\u0014\u0018\r\u001d5\u0011\t!K5JV\u0007\u0002S%\u0011!*\u000b\u0002\u0006\u000fJ\f\u0007\u000f\u001b\t\u0003\u00196c\u0001\u0001B\u0003O\u0001\t\u0007qJ\u0001\u0002W\tF\u0011\u0001k\u0015\t\u0003kEK!A\u0015\u001c\u0003\u000f9{G\u000f[5oOB\u0011Q\u0007V\u0005\u0003+Z\u00121!\u00118z!\tau\u000bB\u0003Y\u0001\t\u0007qJ\u0001\u0002F\t\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007ms6*D\u0001]\u0015\tif'A\u0004sK\u001adWm\u0019;\n\u0005}c&\u0001C\"mCN\u001cH+Y4\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002\\=Z\u000ba\u0001P5oSRtDC\u00013i)\r)gm\u001a\t\u0005\u0011\u0002Ye\u000bC\u0003Z\t\u0001\u000f!\fC\u0003a\t\u0001\u000f\u0011\rC\u0003G\t\u0001\u0007q)\u0001\u0005ok6,EmZ3t+\u0005Y\u0007CA\u001bm\u0013\tigG\u0001\u0003M_:<\u0007FA\u0003p!\t)\u0004/\u0003\u0002rm\tIAO]1og&,g\u000e^\u0001\f]Vlg+\u001a:uS\u000e,7\u000f\u000b\u0002\u0007_\u0006I\u0011N\u001c#fOJ,Wm]\u000b\u0002mB\u0019\u0001j^=\n\u0005aL#!\u0003,feR,\u0007P\u0015#E!\t)$0\u0003\u0002|m\t\u0019\u0011J\u001c;)\u0005\u001dy\u0017AC8vi\u0012+wM]3fg\"\u0012\u0001b\\\u0001\bI\u0016<'/Z3tQ\tIq.\u0001\u0006eK\u001e\u0014X-Z:S\t\u0012#2A^A\u0004\u0011\u001d\tIA\u0003a\u0001\u0003\u0017\tQ\"\u001a3hK\u0012K'/Z2uS>t\u0007c\u0001%\u0002\u000e%\u0019\u0011qB\u0015\u0003\u001b\u0015#w-\u001a#je\u0016\u001cG/[8o\u0003I\u0019w\u000e\u001c7fGRtU-[4iE>\u0014\u0018\nZ:\u0015\t\u0005U\u0011\u0011\u0006\t\u0005\u0011^\f9\u0002E\u00036\u00033\ti\"C\u0002\u0002\u001cY\u0012Q!\u0011:sCf\u0004B!a\b\u0002$9\u0019\u0001*!\t\n\u0005\tK\u0013\u0002BA\u0013\u0003O\u0011\u0001BV3si\u0016D\u0018\n\u001a\u0006\u0003\u0005&Bq!!\u0003\f\u0001\u0004\tY!\u0001\td_2dWm\u0019;OK&<\u0007NY8sgR!\u0011qFA\u001d!\u0011Au/!\r\u0011\u000bU\nI\"a\r\u0011\rU\n)$!\bL\u0013\r\t9D\u000e\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u0005%A\u00021\u0001\u0002\f\u0005a1m\u001c7mK\u000e$X\tZ4fgR!\u0011qHA%!\u0011Au/!\u0011\u0011\u000bU\nI\"a\u0011\u0011\t!\u000b)EV\u0005\u0004\u0003\u000fJ#\u0001B#eO\u0016Dq!!\u0003\u000e\u0001\u0004\tY!A\bsK6|g/Z*fY\u001a,EmZ3t)\u00059\u0015\u0001\u00046pS:4VM\u001d;jG\u0016\u001cX\u0003BA*\u0003C\"B!!\u0016\u0002pQ!\u0011qKA3)\r9\u0015\u0011\f\u0005\n\u00037z\u0011\u0011!a\u0002\u0003;\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0011Yf,a\u0018\u0011\u00071\u000b\t\u0007\u0002\u0004\u0002d=\u0011\ra\u0014\u0002\u0002+\"9\u0011qM\bA\u0002\u0005%\u0014aB7ba\u001a+hn\u0019\t\nk\u0005-\u0014QD&\u0002`-K1!!\u001c7\u0005%1UO\\2uS>t7\u0007C\u0004\u0002r=\u0001\r!a\u001d\u0002\u000bQ\f'\r\\3\u0011\r\u0005U\u00141PA@\u001b\t\t9HC\u0002\u0002z-\n1A\u001d3e\u0013\u0011\ti(a\u001e\u0003\u0007I#E\tE\u00046\u0003k\ti\"a\u0018\u0002\r\u0019LG\u000e^3s+\u0019\t))!%\u0002\u001eRA\u0011qQAQ\u0003[\u000by\fF\u0003H\u0003\u0013\u000b)\nC\u0005\u0002\fB\t\t\u0011q\u0001\u0002\u000e\u0006QQM^5eK:\u001cW\r\n\u001b\u0011\tms\u0016q\u0012\t\u0004\u0019\u0006EEABAJ!\t\u0007qJA\u0002W\tJB\u0011\"a&\u0011\u0003\u0003\u0005\u001d!!'\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0003\\=\u0006m\u0005c\u0001'\u0002\u001e\u00121\u0011q\u0014\tC\u0002=\u00131!\u0012#3\u0011\u001d\t\u0019\u000b\u0005a\u0001\u0003K\u000b!\u0002\u001d:faJ|7-Z:t!\u0019)\u0014qU$\u0002,&\u0019\u0011\u0011\u0016\u001c\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0002%J\u0003\u001f\u000bY\nC\u0005\u00020B\u0001\n\u00111\u0001\u00022\u0006)Q\r\u001d:fIB9Q'a*\u00024\u0006e\u0006c\u0002%\u00026\u0006=\u00151T\u0005\u0004\u0003oK#aC#eO\u0016$&/\u001b9mKR\u00042!NA^\u0013\r\tiL\u000e\u0002\b\u0005>|G.Z1o\u0011%\t\t\r\u0005I\u0001\u0002\u0004\t\u0019-A\u0003waJ,G\rE\u00056\u0003\u000b\fi\"a$\u0002:&\u0019\u0011q\u0019\u001c\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014\u0001\u00054jYR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0019\ti-a6\u0002\\V\u0011\u0011q\u001a\u0016\u0005\u0003#\fi\u000eE\u00046\u0003O\u000b\u0019.!/\u0011\u000f!\u000b),!6\u0002ZB\u0019A*a6\u0005\r\u0005M\u0015C1\u0001P!\ra\u00151\u001c\u0003\u0007\u0003?\u000b\"\u0019A(,\u0005\u0005}\u0007\u0003BAq\u0003Wl!!a9\u000b\t\u0005\u0015\u0018q]\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!;7\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003[\f\u0019OA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001CZ5mi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\r\u0005M\u00181`A\u007f+\t\t)P\u000b\u0003\u0002x\u0006u\u0007#C\u001b\u0002F\u0006u\u0011\u0011`A]!\ra\u00151 \u0003\u0007\u0003'\u0013\"\u0019A(\u0005\r\u0005}%C1\u0001P\u0003A\u0001\u0018nY6SC:$w.\u001c,feR,\u0007\u0010\u0006\u0002\u0002\u001e\u000592m\u001c8wKJ$Hk\\\"b]>t\u0017nY1m\u000b\u0012<Wm\u001d\u000b\u0004\u000f\n\u001d\u0001\"\u0003B\u0005)A\u0005\t\u0019\u0001B\u0006\u0003%iWM]4f\rVt7\r\u0005\u00046\u0003\u000b4fKV\u0001\"G>tg/\u001a:u)>\u001c\u0015M\\8oS\u000e\fG.\u00123hKN$C-\u001a4bk2$H%M\u000b\u0003\u0005#QCAa\u0003\u0002^\u00061\u0001O]3hK2,BAa\u0006\u0003&QA!\u0011\u0004B#\u0005\u0013\u0012i\u0005\u0006\u0005\u0003\u001c\t%\"q\u0006B )\r9%Q\u0004\u0005\n\u0005?1\u0012\u0011!a\u0002\u0005C\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0011YfLa\t\u0011\u00071\u0013)\u0003\u0002\u0004\u0003(Y\u0011\ra\u0014\u0002\u0002\u0003\"9!1\u0006\fA\u0002\t5\u0012!\u0002<qe><\u0007#C\u001b\u0002l\u0005u1Ja\tL\u0011\u001d\u0011\tD\u0006a\u0001\u0005g\tqa]3oI6\u001bx\rE\u00046\u0003O\u0013)Da\u000e\u0011\u000b!\u000b)l\u0013,\u0011\u000bm\u0012ID!\u0010\n\u0007\tmRI\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\u001d)\u0014QGA\u000f\u0005GAqA!\u0011\u0017\u0001\u0004\u0011\u0019%\u0001\u0005nKJ<W-T:h!%)\u0014Q\u0019B\u0012\u0005G\u0011\u0019\u0003C\u0004\u0003HY\u0001\rAa\t\u0002\u0015%t\u0017\u000e^5bY6\u001bx\r\u0003\u0005\u0003LY\u0001\n\u00111\u0001z\u00035i\u0017\r_%uKJ\fG/[8og\"I!q\n\f\u0011\u0002\u0003\u0007\u00111B\u0001\u0010C\u000e$\u0018N^3ESJ,7\r^5p]\u0006\u0001\u0002O]3hK2$C-\u001a4bk2$HEM\u000b\u0005\u0005+\u0012I&\u0006\u0002\u0003X)\u001a\u00110!8\u0005\r\t\u001drC1\u0001P\u0003A\u0001(/Z4fY\u0012\"WMZ1vYR$3'\u0006\u0003\u0003`\t\rTC\u0001B1U\u0011\tY!!8\u0005\r\t\u001d\u0002D1\u0001P\u0003!\u0001\u0018mZ3SC:\\GC\u0002B5\u0005c\u0012)\b\u0005\u0004I\u0013\n-$1\u000e\t\u0004k\t5\u0014b\u0001B8m\t1Ai\\;cY\u0016DqAa\u001d\u001a\u0001\u0004\u0011Y'A\u0002u_2D\u0011Ba\u001e\u001a!\u0003\u0005\rAa\u001b\u0002\u0013I,7/\u001a;Qe>\u0014\u0017A\u00059bO\u0016\u0014\u0016M\\6%I\u00164\u0017-\u001e7uII*\"A! +\t\t-\u0014Q\\\u0001\u0015a\u0016\u00148o\u001c8bY&TX\r\u001a)bO\u0016\u0014\u0016M\\6\u0015\u0011\t%$1\u0011BD\u0005\u0013CqA!\"\u001c\u0001\u0004\ti\"A\u0002te\u000eDqAa\u001d\u001c\u0001\u0004\u0011Y\u0007C\u0005\u0003xm\u0001\n\u00111\u0001\u0003l\u0005q\u0002/\u001a:t_:\fG.\u001b>fIB\u000bw-\u001a*b].$C-\u001a4bk2$HeM\u0001#gR\fG/[2QCJ\fG\u000e\\3m!\u0016\u00148o\u001c8bY&TX\r\u001a)bO\u0016\u0014\u0016M\\6\u0015\u0011\tE%1\u0015BT\u0005W\u0003b\u0001S%\u0003\u0014\n-\u0004\u0003\u0002BK\u0005?k!Aa&\u000b\t\te%1T\u0001\u0007Y&t\u0017\r\\4\u000b\u0007\tu5&\u0001\u0002nY&!!\u0011\u0015BL\u0005\u00191Vm\u0019;pe\"9!QU\u000fA\u0002\u0005]\u0011aB:pkJ\u001cWm\u001d\u0005\u0007\u0005Sk\u0002\u0019A=\u0002\u000f9,X.\u0013;fe\"I!qO\u000f\u0011\u0002\u0003\u0007!1N\u0001-gR\fG/[2QCJ\fG\u000e\\3m!\u0016\u00148o\u001c8bY&TX\r\u001a)bO\u0016\u0014\u0016M\\6%I\u00164\u0017-\u001e7uIM\n!d\u001d;bi&\u001c\u0007+\u001a:t_:\fG.\u001b>fIB\u000bw-\u001a*b].$\u0002B!\u001b\u00034\nU&q\u0017\u0005\b\u0005\u000b{\u0002\u0019AA\u000f\u0011\u0019\u0011Ik\ba\u0001s\"I!qO\u0010\u0011\u0002\u0003\u0007!1N\u0001%gR\fG/[2QKJ\u001cxN\\1mSj,G\rU1hKJ\u000bgn\u001b\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1\u000f^1uS\u000e\u0004\u0016mZ3SC:\\GC\u0002B5\u0005\u007f\u0013\t\r\u0003\u0004\u0003*\u0006\u0002\r!\u001f\u0005\n\u0005o\n\u0003\u0013!a\u0001\u0005W\n\u0001d\u001d;bi&\u001c\u0007+Y4f%\u0006t7\u000e\n3fM\u0006,H\u000e\u001e\u00133)!\u0011IGa2\u0003J\n-\u0007B\u0002BUG\u0001\u0007\u0011\u0010C\u0004\u0003x\r\u0002\rAa\u001b\t\u000f\t57\u00051\u0001\u0003j\u0005Y\u0001O]3QC\u001e,'+\u00198l\u0003M\u0019wN\u001c8fGR,GmQ8na>tWM\u001c;t)\t\u0011\u0019\u000eE\u0003I\u0013\u0006ua\u000b\u0006\u0003\u0003T\n]\u0007B\u0002B&K\u0001\u0007\u00110A\u0007ue&\fgn\u001a7f\u0007>,h\u000e\u001e\u000b\u0003\u0005;\u0004B\u0001S%z-\u0006Y2\u000f\u001e:p]\u001ed\u0017pQ8o]\u0016\u001cG/\u001a3D_6\u0004xN\\3oiN$BAa5\u0003d\"1!\u0011V\u0014A\u0002e\u0004"
)
public class GraphOps implements Serializable {
   private transient long numEdges;
   private transient long numVertices;
   private transient VertexRDD inDegrees;
   private transient VertexRDD outDegrees;
   private transient VertexRDD degrees;
   private final Graph graph;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private transient volatile byte bitmap$trans$0;

   private long numEdges$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.numEdges = this.graph.edges().count();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numEdges;
   }

   public long numEdges() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.numEdges$lzycompute() : this.numEdges;
   }

   private long numVertices$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.numVertices = this.graph.vertices().count();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numVertices;
   }

   public long numVertices() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.numVertices$lzycompute() : this.numVertices;
   }

   private VertexRDD inDegrees$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.inDegrees = (VertexRDD)this.degreesRDD(EdgeDirection$.MODULE$.In()).setName("GraphOps.inDegrees");
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inDegrees;
   }

   public VertexRDD inDegrees() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.inDegrees$lzycompute() : this.inDegrees;
   }

   private VertexRDD outDegrees$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.outDegrees = (VertexRDD)this.degreesRDD(EdgeDirection$.MODULE$.Out()).setName("GraphOps.outDegrees");
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.outDegrees;
   }

   public VertexRDD outDegrees() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.outDegrees$lzycompute() : this.outDegrees;
   }

   private VertexRDD degrees$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 16) == 0) {
            this.degrees = (VertexRDD)this.degreesRDD(EdgeDirection$.MODULE$.Either()).setName("GraphOps.degrees");
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.degrees;
   }

   public VertexRDD degrees() {
      return (byte)(this.bitmap$trans$0 & 16) == 0 ? this.degrees$lzycompute() : this.degrees;
   }

   private VertexRDD degreesRDD(final EdgeDirection edgeDirection) {
      EdgeDirection var2 = EdgeDirection$.MODULE$.In();
      if (edgeDirection == null) {
         if (var2 == null) {
            return this.graph.aggregateMessages((x$1) -> {
               $anonfun$degreesRDD$1(x$1);
               return BoxedUnit.UNIT;
            }, (JFunction2.mcIII.sp)(x$2, x$3) -> x$2 + x$3, TripletFields.None, .MODULE$.Int());
         }
      } else if (edgeDirection.equals(var2)) {
         return this.graph.aggregateMessages((x$1) -> {
            $anonfun$degreesRDD$1(x$1);
            return BoxedUnit.UNIT;
         }, (JFunction2.mcIII.sp)(x$2, x$3) -> x$2 + x$3, TripletFields.None, .MODULE$.Int());
      }

      EdgeDirection var3 = EdgeDirection$.MODULE$.Out();
      if (edgeDirection == null) {
         if (var3 == null) {
            return this.graph.aggregateMessages((x$4) -> {
               $anonfun$degreesRDD$3(x$4);
               return BoxedUnit.UNIT;
            }, (JFunction2.mcIII.sp)(x$5, x$6) -> x$5 + x$6, TripletFields.None, .MODULE$.Int());
         }
      } else if (edgeDirection.equals(var3)) {
         return this.graph.aggregateMessages((x$4) -> {
            $anonfun$degreesRDD$3(x$4);
            return BoxedUnit.UNIT;
         }, (JFunction2.mcIII.sp)(x$5, x$6) -> x$5 + x$6, TripletFields.None, .MODULE$.Int());
      }

      return this.graph.aggregateMessages((ctx) -> {
         $anonfun$degreesRDD$5(ctx);
         return BoxedUnit.UNIT;
      }, (JFunction2.mcIII.sp)(x$7, x$8) -> x$7 + x$8, TripletFields.None, .MODULE$.Int());
   }

   public VertexRDD collectNeighborIds(final EdgeDirection edgeDirection) {
      VertexRDD var10000;
      label39: {
         label42: {
            EdgeDirection var3 = EdgeDirection$.MODULE$.Either();
            if (edgeDirection == null) {
               if (var3 == null) {
                  break label42;
               }
            } else if (edgeDirection.equals(var3)) {
               break label42;
            }

            label43: {
               EdgeDirection var4 = EdgeDirection$.MODULE$.Out();
               if (edgeDirection == null) {
                  if (var4 == null) {
                     break label43;
                  }
               } else if (edgeDirection.equals(var4)) {
                  break label43;
               }

               EdgeDirection var5 = EdgeDirection$.MODULE$.In();
               if (edgeDirection == null) {
                  if (var5 != null) {
                     throw new SparkException("It doesn't make sense to collect neighbor ids without a direction. (EdgeDirection.Both is not supported; use EdgeDirection.Either instead.)");
                  }
               } else if (!edgeDirection.equals(var5)) {
                  throw new SparkException("It doesn't make sense to collect neighbor ids without a direction. (EdgeDirection.Both is not supported; use EdgeDirection.Either instead.)");
               }

               var10000 = this.graph.aggregateMessages((ctx) -> {
                  $anonfun$collectNeighborIds$5(ctx);
                  return BoxedUnit.UNIT;
               }, (x$13, x$14) -> (long[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.longArrayOps(x$13), x$14, .MODULE$.Long()), TripletFields.None, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)));
               break label39;
            }

            var10000 = this.graph.aggregateMessages((ctx) -> {
               $anonfun$collectNeighborIds$3(ctx);
               return BoxedUnit.UNIT;
            }, (x$11, x$12) -> (long[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.longArrayOps(x$11), x$12, .MODULE$.Long()), TripletFields.None, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)));
            break label39;
         }

         var10000 = this.graph.aggregateMessages((ctx) -> {
            $anonfun$collectNeighborIds$1(ctx);
            return BoxedUnit.UNIT;
         }, (x$9, x$10) -> (long[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.longArrayOps(x$9), x$10, .MODULE$.Long()), TripletFields.None, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)));
      }

      VertexRDD nbrs = var10000;
      return this.graph.vertices().leftZipJoin(nbrs, (vid, vdata, nbrsOpt) -> $anonfun$collectNeighborIds$7(BoxesRunTime.unboxToLong(vid), vdata, nbrsOpt), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)));
   }

   public VertexRDD collectNeighbors(final EdgeDirection edgeDirection) {
      VertexRDD var12;
      label57: {
         label59: {
            EdgeDirection var10000 = EdgeDirection$.MODULE$.Either();
            if (var10000 == null) {
               if (edgeDirection == null) {
                  break label59;
               }
            } else if (var10000.equals(edgeDirection)) {
               break label59;
            }

            label60: {
               var10000 = EdgeDirection$.MODULE$.In();
               if (var10000 == null) {
                  if (edgeDirection == null) {
                     break label60;
                  }
               } else if (var10000.equals(edgeDirection)) {
                  break label60;
               }

               label66: {
                  var10000 = EdgeDirection$.MODULE$.Out();
                  if (var10000 == null) {
                     if (edgeDirection == null) {
                        break label66;
                     }
                  } else if (var10000.equals(edgeDirection)) {
                     break label66;
                  }

                  var10000 = EdgeDirection$.MODULE$.Both();
                  if (var10000 == null) {
                     if (edgeDirection == null) {
                        throw new SparkException("collectEdges does not support EdgeDirection.Both. UseEdgeDirection.Either instead.");
                     }
                  } else if (var10000.equals(edgeDirection)) {
                     throw new SparkException("collectEdges does not support EdgeDirection.Both. UseEdgeDirection.Either instead.");
                  }

                  throw new MatchError(edgeDirection);
               }

               var12 = this.graph.aggregateMessages((ctx) -> {
                  $anonfun$collectNeighbors$5(ctx);
                  return BoxedUnit.UNIT;
               }, (a, b) -> (Tuple2[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])a), b, .MODULE$.apply(Tuple2.class)), TripletFields.Dst, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
               break label57;
            }

            var12 = this.graph.aggregateMessages((ctx) -> {
               $anonfun$collectNeighbors$3(ctx);
               return BoxedUnit.UNIT;
            }, (a, b) -> (Tuple2[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])a), b, .MODULE$.apply(Tuple2.class)), TripletFields.Src, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
            break label57;
         }

         var12 = this.graph.aggregateMessages((ctx) -> {
            $anonfun$collectNeighbors$1(ctx);
            return BoxedUnit.UNIT;
         }, (a, b) -> (Tuple2[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])a), b, .MODULE$.apply(Tuple2.class)), TripletFields.All, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
      }

      VertexRDD nbrs = var12;
      return this.graph.vertices().leftJoin(nbrs, (vid, vdata, nbrsOpt) -> $anonfun$collectNeighbors$7(BoxesRunTime.unboxToLong(vid), vdata, nbrsOpt), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
   }

   public VertexRDD collectEdges(final EdgeDirection edgeDirection) {
      EdgeDirection var10000 = EdgeDirection$.MODULE$.Either();
      if (var10000 == null) {
         if (edgeDirection == null) {
            return this.graph.aggregateMessages((ctx) -> {
               $anonfun$collectEdges$1(ctx);
               return BoxedUnit.UNIT;
            }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
         }
      } else if (var10000.equals(edgeDirection)) {
         return this.graph.aggregateMessages((ctx) -> {
            $anonfun$collectEdges$1(ctx);
            return BoxedUnit.UNIT;
         }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
      }

      var10000 = EdgeDirection$.MODULE$.In();
      if (var10000 == null) {
         if (edgeDirection == null) {
            return this.graph.aggregateMessages((ctx) -> {
               $anonfun$collectEdges$3(ctx);
               return BoxedUnit.UNIT;
            }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
         }
      } else if (var10000.equals(edgeDirection)) {
         return this.graph.aggregateMessages((ctx) -> {
            $anonfun$collectEdges$3(ctx);
            return BoxedUnit.UNIT;
         }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
      }

      var10000 = EdgeDirection$.MODULE$.Out();
      if (var10000 == null) {
         if (edgeDirection == null) {
            return this.graph.aggregateMessages((ctx) -> {
               $anonfun$collectEdges$5(ctx);
               return BoxedUnit.UNIT;
            }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
         }
      } else if (var10000.equals(edgeDirection)) {
         return this.graph.aggregateMessages((ctx) -> {
            $anonfun$collectEdges$5(ctx);
            return BoxedUnit.UNIT;
         }, (a, b) -> (Edge[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(a), b, .MODULE$.apply(Edge.class)), TripletFields.EdgeOnly, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Edge.class)));
      }

      var10000 = EdgeDirection$.MODULE$.Both();
      if (var10000 == null) {
         if (edgeDirection == null) {
            throw new SparkException("collectEdges does not support EdgeDirection.Both. UseEdgeDirection.Either instead.");
         }
      } else if (var10000.equals(edgeDirection)) {
         throw new SparkException("collectEdges does not support EdgeDirection.Both. UseEdgeDirection.Either instead.");
      }

      throw new MatchError(edgeDirection);
   }

   public Graph removeSelfEdges() {
      return this.graph.subgraph((e) -> BoxesRunTime.boxToBoolean($anonfun$removeSelfEdges$1(e)), this.graph.subgraph$default$2());
   }

   public Graph joinVertices(final RDD table, final Function3 mapFunc, final ClassTag evidence$3) {
      Function3 uf = (id, data, o) -> $anonfun$joinVertices$1(mapFunc, BoxesRunTime.unboxToLong(id), data, o);
      return this.graph.outerJoinVertices(table, uf, evidence$3, this.evidence$1, scala..less.colon.less..MODULE$.refl());
   }

   public Graph filter(final Function1 preprocess, final Function1 epred, final Function2 vpred, final ClassTag evidence$4, final ClassTag evidence$5) {
      return this.graph.mask(((Graph)preprocess.apply(this.graph)).subgraph(epred, vpred), evidence$4, evidence$5);
   }

   public Function1 filter$default$2() {
      return (x) -> BoxesRunTime.boxToBoolean($anonfun$filter$default$2$1(x));
   }

   public Function2 filter$default$3() {
      return (v, d) -> BoxesRunTime.boxToBoolean($anonfun$filter$default$3$1(BoxesRunTime.unboxToLong(v), d));
   }

   public long pickRandomVertex() {
      double probability = (double)50.0F / (double)Graph$.MODULE$.graphToGraphOps(this.graph, this.evidence$1, this.evidence$2).numVertices();
      boolean found = false;
      long retVal = BoxesRunTime.unboxToLong((Object)null);

      while(!found) {
         RDD selectedVertices = this.graph.vertices().flatMap((vidVvals) -> (Option)(scala.util.Random..MODULE$.nextDouble() < probability ? new Some(BoxesRunTime.boxToLong(vidVvals._1$mcJ$sp())) : scala.None..MODULE$), .MODULE$.apply(Long.TYPE));
         if (selectedVertices.count() > 0L) {
            found = true;
            long[] collectedVertices = (long[])selectedVertices.collect();
            retVal = collectedVertices[scala.util.Random..MODULE$.nextInt(collectedVertices.length)];
         }
      }

      return retVal;
   }

   public Graph convertToCanonicalEdges(final Function2 mergeFunc) {
      RDD newEdges = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.graph.edges().map((x0$1) -> x0$1.srcId() < x0$1.dstId() ? new Tuple2(new Tuple2.mcJJ.sp(x0$1.srcId(), x0$1.dstId()), x0$1.attr()) : new Tuple2(new Tuple2.mcJJ.sp(x0$1.dstId(), x0$1.srcId()), x0$1.attr()), .MODULE$.apply(Tuple2.class)), .MODULE$.apply(Tuple2.class), this.evidence$2, scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Long..MODULE$, scala.math.Ordering.Long..MODULE$)).reduceByKey(mergeFunc).map((e) -> new Edge(((Tuple2)e._1())._1$mcJ$sp(), ((Tuple2)e._1())._2$mcJ$sp(), e._2()), .MODULE$.apply(Edge.class));
      return Graph$.MODULE$.apply(this.graph.vertices(), newEdges, Graph$.MODULE$.apply$default$3(), Graph$.MODULE$.apply$default$4(), Graph$.MODULE$.apply$default$5(), this.evidence$1, this.evidence$2);
   }

   public Function2 convertToCanonicalEdges$default$1() {
      return (e1, e2) -> e1;
   }

   public Graph pregel(final Object initialMsg, final int maxIterations, final EdgeDirection activeDirection, final Function3 vprog, final Function1 sendMsg, final Function2 mergeMsg, final ClassTag evidence$6) {
      return Pregel$.MODULE$.apply(this.graph, initialMsg, maxIterations, activeDirection, vprog, sendMsg, mergeMsg, this.evidence$1, this.evidence$2, evidence$6);
   }

   public int pregel$default$2() {
      return Integer.MAX_VALUE;
   }

   public EdgeDirection pregel$default$3() {
      return EdgeDirection$.MODULE$.Either();
   }

   public Graph pageRank(final double tol, final double resetProb) {
      return PageRank$.MODULE$.runUntilConvergence(this.graph, tol, resetProb, this.evidence$1, this.evidence$2);
   }

   public double pageRank$default$2() {
      return 0.15;
   }

   public Graph personalizedPageRank(final long src, final double tol, final double resetProb) {
      return PageRank$.MODULE$.runUntilConvergenceWithOptions(this.graph, tol, resetProb, new Some(BoxesRunTime.boxToLong(src)), this.evidence$1, this.evidence$2);
   }

   public double personalizedPageRank$default$3() {
      return 0.15;
   }

   public Graph staticParallelPersonalizedPageRank(final long[] sources, final int numIter, final double resetProb) {
      return PageRank$.MODULE$.runParallelPersonalizedPageRank(this.graph, numIter, resetProb, sources, this.evidence$1, this.evidence$2);
   }

   public double staticParallelPersonalizedPageRank$default$3() {
      return 0.15;
   }

   public Graph staticPersonalizedPageRank(final long src, final int numIter, final double resetProb) {
      return PageRank$.MODULE$.runWithOptions(this.graph, numIter, resetProb, new Some(BoxesRunTime.boxToLong(src)), this.evidence$1, this.evidence$2);
   }

   public double staticPersonalizedPageRank$default$3() {
      return 0.15;
   }

   public Graph staticPageRank(final int numIter, final double resetProb) {
      return PageRank$.MODULE$.run(this.graph, numIter, resetProb, this.evidence$1, this.evidence$2);
   }

   public Graph staticPageRank(final int numIter, final double resetProb, final Graph prePageRank) {
      return PageRank$.MODULE$.runWithOptionsWithPreviousPageRank(this.graph, numIter, resetProb, scala.None..MODULE$, prePageRank, this.evidence$1, this.evidence$2);
   }

   public double staticPageRank$default$2() {
      return 0.15;
   }

   public Graph connectedComponents() {
      return ConnectedComponents$.MODULE$.run(this.graph, this.evidence$1, this.evidence$2);
   }

   public Graph connectedComponents(final int maxIterations) {
      return ConnectedComponents$.MODULE$.run(this.graph, maxIterations, this.evidence$1, this.evidence$2);
   }

   public Graph triangleCount() {
      return TriangleCount$.MODULE$.run(this.graph, this.evidence$1, this.evidence$2);
   }

   public Graph stronglyConnectedComponents(final int numIter) {
      return StronglyConnectedComponents$.MODULE$.run(this.graph, numIter, this.evidence$1, this.evidence$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$degreesRDD$1(final EdgeContext x$1) {
      x$1.sendToDst(BoxesRunTime.boxToInteger(1));
   }

   // $FF: synthetic method
   public static final void $anonfun$degreesRDD$3(final EdgeContext x$4) {
      x$4.sendToSrc(BoxesRunTime.boxToInteger(1));
   }

   // $FF: synthetic method
   public static final void $anonfun$degreesRDD$5(final EdgeContext ctx) {
      ctx.sendToSrc(BoxesRunTime.boxToInteger(1));
      ctx.sendToDst(BoxesRunTime.boxToInteger(1));
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighborIds$1(final EdgeContext ctx) {
      ctx.sendToSrc(new long[]{ctx.dstId()});
      ctx.sendToDst(new long[]{ctx.srcId()});
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighborIds$3(final EdgeContext ctx) {
      ctx.sendToSrc(new long[]{ctx.dstId()});
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighborIds$5(final EdgeContext ctx) {
      ctx.sendToDst(new long[]{ctx.srcId()});
   }

   // $FF: synthetic method
   public static final long[] $anonfun$collectNeighborIds$7(final long vid, final Object vdata, final Option nbrsOpt) {
      return (long[])nbrsOpt.getOrElse(() -> (long[])scala.Array..MODULE$.empty(.MODULE$.apply(Long.TYPE)));
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighbors$1(final EdgeContext ctx) {
      ctx.sendToSrc((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(ctx.dstId()), ctx.dstAttr())}));
      ctx.sendToDst((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(ctx.srcId()), ctx.srcAttr())}));
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighbors$3(final EdgeContext ctx) {
      ctx.sendToDst((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(ctx.srcId()), ctx.srcAttr())}));
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNeighbors$5(final EdgeContext ctx) {
      ctx.sendToSrc((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(ctx.dstId()), ctx.dstAttr())}));
   }

   // $FF: synthetic method
   public static final Tuple2[] $anonfun$collectNeighbors$7(final long vid, final Object vdata, final Option nbrsOpt) {
      return (Tuple2[])nbrsOpt.getOrElse(() -> (Tuple2[])scala.Array..MODULE$.empty(.MODULE$.apply(Tuple2.class)));
   }

   // $FF: synthetic method
   public static final void $anonfun$collectEdges$1(final EdgeContext ctx) {
      ctx.sendToSrc(new Edge[]{new Edge(ctx.srcId(), ctx.dstId(), ctx.attr())});
      ctx.sendToDst(new Edge[]{new Edge(ctx.srcId(), ctx.dstId(), ctx.attr())});
   }

   // $FF: synthetic method
   public static final void $anonfun$collectEdges$3(final EdgeContext ctx) {
      ctx.sendToDst(new Edge[]{new Edge(ctx.srcId(), ctx.dstId(), ctx.attr())});
   }

   // $FF: synthetic method
   public static final void $anonfun$collectEdges$5(final EdgeContext ctx) {
      ctx.sendToSrc(new Edge[]{new Edge(ctx.srcId(), ctx.dstId(), ctx.attr())});
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeSelfEdges$1(final EdgeTriplet e) {
      return e.srcId() != e.dstId();
   }

   // $FF: synthetic method
   public static final Object $anonfun$joinVertices$1(final Function3 mapFunc$1, final long id, final Object data, final Option o) {
      if (o instanceof Some var7) {
         Object u = var7.value();
         return mapFunc$1.apply(BoxesRunTime.boxToLong(id), data, u);
      } else if (scala.None..MODULE$.equals(o)) {
         return data;
      } else {
         throw new MatchError(o);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$default$2$1(final EdgeTriplet x) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$default$3$1(final long v, final Object d) {
      return true;
   }

   public GraphOps(final Graph graph, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.graph = graph;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
