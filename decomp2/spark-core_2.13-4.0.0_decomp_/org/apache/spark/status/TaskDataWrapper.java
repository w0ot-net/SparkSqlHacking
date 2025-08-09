package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import org.apache.spark.status.api.v1.AccumulableInfo;
import org.apache.spark.status.api.v1.InputMetrics;
import org.apache.spark.status.api.v1.OutputMetrics;
import org.apache.spark.status.api.v1.ShufflePushReadMetrics;
import org.apache.spark.status.api.v1.ShuffleReadMetrics;
import org.apache.spark.status.api.v1.ShuffleWriteMetrics;
import org.apache.spark.status.api.v1.TaskData;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Option;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115d!\u0002<x\u0001e|\bBCA\u0007\u0001\t\u0015\r\u0011\"\u0001\u0002\u0012!Q\u0011q\n\u0001\u0003\u0002\u0003\u0006I!a\u0005\t\u0015\u0005E\u0003A!b\u0001\n\u0003\t\u0019\u0006\u0003\u0006\u0002d\u0001\u0011\t\u0011)A\u0005\u0003+B!\"!\u001a\u0001\u0005\u000b\u0007I\u0011AA*\u0011)\ti\u0007\u0001B\u0001B\u0003%\u0011Q\u000b\u0005\u000b\u0003_\u0002!Q1A\u0005\u0002\u0005M\u0003BCA<\u0001\t\u0005\t\u0015!\u0003\u0002V!Q\u0011\u0011\u0010\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\u0005\u001d\u0005A!A!\u0002\u0013\ti\b\u0003\u0006\u0002\n\u0002\u0011)\u0019!C\u0001\u0003wB!\"a#\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\ti\t\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0003+\u0003!\u0011!Q\u0001\n\u0005u\u0004BCAL\u0001\t\u0015\r\u0011\"\u0001\u0002\u001a\"Q\u0011q\u0017\u0001\u0003\u0002\u0003\u0006I!a'\t\u0015\u0005e\u0006A!b\u0001\n\u0003\tI\n\u0003\u0006\u0002B\u0002\u0011\t\u0011)A\u0005\u00037C\u0011\u0002\u001f\u0001\u0003\u0006\u0004%\t!!'\t\u0015\u0005%\u0007A!A!\u0002\u0013\tY\n\u0003\u0006\u0002L\u0002\u0011)\u0019!C\u0001\u00033C!\"a5\u0001\u0005\u0003\u0005\u000b\u0011BAN\u0011)\t)\u000e\u0001BC\u0002\u0013\u0005\u0011q\u001b\u0005\u000b\u0003?\u0004!\u0011!Q\u0001\n\u0005e\u0007BCAq\u0001\t\u0015\r\u0011\"\u0001\u0002d\"Q!\u0011\u0001\u0001\u0003\u0002\u0003\u0006I!!:\t\u0015\t\r\u0001A!b\u0001\n\u0003\u0011)\u0001\u0003\u0006\u0003\u000e\u0001\u0011\t\u0011)A\u0005\u0005\u000fA!Ba\u0004\u0001\u0005\u000b\u0007I\u0011AAl\u0011)\u0011\t\u0002\u0001B\u0001B\u0003%\u0011\u0011\u001c\u0005\u000b\u0005'\u0001!Q1A\u0005\u0002\u0005m\u0004B\u0003B\u000e\u0001\t\u0005\t\u0015!\u0003\u0002~!Q!Q\u0004\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\t\u0015\u0002A!A!\u0002\u0013\ti\b\u0003\u0006\u0003(\u0001\u0011)\u0019!C\u0001\u0003wB!Ba\f\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\u0011\t\u0004\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0005s\u0001!\u0011!Q\u0001\n\u0005u\u0004B\u0003B\u001e\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q!1\t\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\t\u0015\u0003A!b\u0001\n\u0003\tY\b\u0003\u0006\u0003N\u0001\u0011\t\u0011)A\u0005\u0003{B!Ba\u0014\u0001\u0005\u000b\u0007I\u0011AA>\u0011)\u00119\u0006\u0001B\u0001B\u0003%\u0011Q\u0010\u0005\u000b\u00053\u0002!Q1A\u0005\u0002\u0005m\u0004B\u0003B1\u0001\t\u0005\t\u0015!\u0003\u0002~!Q!1\r\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\t-\u0004A!A!\u0002\u0013\ti\b\u0003\u0006\u0003n\u0001\u0011)\u0019!C\u0001\u0003wB!B!\u001e\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\u00119\b\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0005\u007f\u0002!\u0011!Q\u0001\n\u0005u\u0004B\u0003BA\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q!\u0011\u0012\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\t-\u0005A!b\u0001\n\u0003\tY\b\u0003\u0006\u0003\u0014\u0002\u0011\t\u0011)A\u0005\u0003{B!B!&\u0001\u0005\u000b\u0007I\u0011AA>\u0011)\u0011i\n\u0001B\u0001B\u0003%\u0011Q\u0010\u0005\u000b\u0005?\u0003!Q1A\u0005\u0002\u0005m\u0004B\u0003BT\u0001\t\u0005\t\u0015!\u0003\u0002~!Q!\u0011\u0016\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\tE\u0006A!A!\u0002\u0013\ti\b\u0003\u0006\u00034\u0002\u0011)\u0019!C\u0001\u0003wB!Ba/\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\u0011i\f\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0005\u000b\u0004!\u0011!Q\u0001\n\u0005u\u0004B\u0003Bd\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q!q\u001a\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\tE\u0007A!b\u0001\n\u0003\tY\b\u0003\u0006\u0003T\u0002\u0011\t\u0011)A\u0005\u0003{B!B!6\u0001\u0005\u000b\u0007I\u0011AA>\u0011)\u0011i\u000e\u0001B\u0001B\u0003%\u0011Q\u0010\u0005\u000b\u0005?\u0004!Q1A\u0005\u0002\u0005m\u0004B\u0003Bt\u0001\t\u0005\t\u0015!\u0003\u0002~!Q!\u0011\u001e\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\tE\bA!A!\u0002\u0013\ti\b\u0003\u0006\u0003t\u0002\u0011)\u0019!C\u0001\u0003wB!Ba?\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\u0011i\u0010\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0007\u000b\u0001!\u0011!Q\u0001\n\u0005u\u0004BCB\u0004\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q1q\u0002\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\rE\u0001A!b\u0001\n\u0003\tY\b\u0003\u0006\u0004\u001a\u0001\u0011\t\u0011)A\u0005\u0003{B!ba\u0007\u0001\u0005\u000b\u0007I\u0011AA>\u0011)\u0019\u0019\u0003\u0001B\u0001B\u0003%\u0011Q\u0010\u0005\u000b\u0007K\u0001!Q1A\u0005\u0002\u0005m\u0004BCB\u0017\u0001\t\u0005\t\u0015!\u0003\u0002~!Q1q\u0006\u0001\u0003\u0006\u0004%\t!a\u001f\t\u0015\r]\u0002A!A!\u0002\u0013\ti\b\u0003\u0006\u0004:\u0001\u0011)\u0019!C\u0001\u0003wB!b!\u0011\u0001\u0005\u0003\u0005\u000b\u0011BA?\u0011)\u0019\u0019\u0005\u0001BC\u0002\u0013\u0005\u00111\u0010\u0005\u000b\u0007\u0017\u0002!\u0011!Q\u0001\n\u0005u\u0004BCB'\u0001\t\u0015\r\u0011\"\u0001\u0002|!Q1Q\u000b\u0001\u0003\u0002\u0003\u0006I!! \t\u0015\r]\u0003A!b\u0001\n\u0003\tY\b\u0003\u0006\u0004`\u0001\u0011\t\u0011)A\u0005\u0003{B!b!\u0019\u0001\u0005\u000b\u0007I\u0011AA*\u0011)\u0019\u0019\u0007\u0001B\u0001B\u0003%\u0011Q\u000b\u0005\u000b\u0007K\u0002!Q1A\u0005\u0002\u0005M\u0003BCB4\u0001\t\u0005\t\u0015!\u0003\u0002V!91\u0011\u000e\u0001\u0005\u0002\r-\u0004bBBl\u0001\u0011%1\u0011\u001c\u0005\b\u0007?\u0004A\u0011ABq\u0011\u001d\ti\u0005\u0001C\u0005\u0007SDq\u0001b\u0003\u0001\t\u0003\tY\bC\u0004\u0005\u0016\u0001!\t!a\u001f\t\u000f\u0011}\u0001\u0001\"\u0003\u0002\u001a\"9A\u0011\u0006\u0001\u0005\n\u0005m\u0004b\u0002C\u001a\u0001\u0011%\u00111\u0010\u0005\b\t{\u0001A\u0011BAM\u0011\u001d!9\u0005\u0001C\u0005\u0003w:!\u0002\"\u0015x\u0003\u0003E\t!\u001fC*\r%1x/!A\t\u0002e$)\u0006C\u0004\u0004jM$\t\u0001b\u0016\t\u0013\u0011e3/%A\u0005\u0002\u0011m#a\u0004+bg.$\u0015\r^1Xe\u0006\u0004\b/\u001a:\u000b\u0005aL\u0018AB:uCR,8O\u0003\u0002{w\u0006)1\u000f]1sW*\u0011A0`\u0001\u0007CB\f7\r[3\u000b\u0003y\f1a\u001c:h'\r\u0001\u0011\u0011\u0001\t\u0005\u0003\u0007\tI!\u0004\u0002\u0002\u0006)\u0011\u0011qA\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003\u0017\t)A\u0001\u0004B]f\u0014VMZ\u0001\u0007i\u0006\u001c8.\u00133\u0004\u0001U\u0011\u00111\u0003\t\u0005\u0003+\ty\"\u0004\u0002\u0002\u0018)!\u0011\u0011DA\u000e\u0003\u0011a\u0017M\\4\u000b\u0005\u0005u\u0011\u0001\u00026bm\u0006LA!!\t\u0002\u0018\t!Aj\u001c8hQ\u001d\t\u0011QEA%\u0003\u0017RC!a\n\u00028A!\u0011\u0011FA\u001a\u001b\t\tYC\u0003\u0003\u0002.\u0005=\u0012aB6wgR|'/\u001a\u0006\u0004\u0003cI\u0018\u0001B;uS2LA!!\u000e\u0002,\t91JV%oI\u0016D8FAA\u001d!\u0011\tY$!\u0012\u000e\u0005\u0005u\"\u0002BA \u0003\u0003\nA!\\3uC*!\u00111IA\u0003\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000f\niD\u0001\u0004hKR$XM]\u0001\u0007a\u0006\u0014XM\u001c;\"\u0005\u00055\u0013!B:uC\u001e,\u0017a\u0002;bg.LE\rI\u0001\u0006S:$W\r_\u000b\u0003\u0003+\u0002B!a\u0001\u0002X%!\u0011\u0011LA\u0003\u0005\rIe\u000e\u001e\u0015\f\u0007\u0005\u0015\u0012QLA0\u0003\u0013\nY%A\u0003wC2,X-\t\u0002\u0002b\u0005\u0019\u0011\u000e\u001a=\u0002\r%tG-\u001a=!\u0003\u001d\tG\u000f^3naRD3\"BA\u0013\u0003;\nI'!\u0013\u0002L\u0005\u0012\u00111N\u0001\u0004CR$\u0018\u0001C1ui\u0016l\u0007\u000f\u001e\u0011\u0002\u0017A\f'\u000f^5uS>t\u0017\n\u001a\u0015\f\u000f\u0005\u0015\u0012QLA:\u0003\u0013\nY%\t\u0002\u0002v\u00051\u0001/\u0019:uS\u0012\fA\u0002]1si&$\u0018n\u001c8JI\u0002\n!\u0002\\1v]\u000eDG+[7f+\t\ti\b\u0005\u0003\u0002\u0004\u0005}\u0014\u0002BA\u0011\u0003\u000bA3\"CA\u0013\u0003;\n\u0019)!\u0013\u0002L\u0005\u0012\u0011QQ\u0001\u0003YR\f1\u0002\\1v]\u000eDG+[7fA\u0005\u0001\"/Z:vYR4U\r^2i'R\f'\u000f^\u0001\u0012e\u0016\u001cX\u000f\u001c;GKR\u001c\u0007n\u0015;beR\u0004\u0013\u0001\u00033ve\u0006$\u0018n\u001c8)\u00175\t)#!\u0018\u0002\u0012\u0006%\u00131J\u0011\u0003\u0003'\u000b1\u0001Z;s\u0003%!WO]1uS>t\u0007%\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012,\"!a'\u0011\t\u0005u\u00151\u0016\b\u0005\u0003?\u000b9\u000b\u0005\u0003\u0002\"\u0006\u0015QBAAR\u0015\u0011\t)+a\u0004\u0002\rq\u0012xn\u001c;?\u0013\u0011\tI+!\u0002\u0002\rA\u0013X\rZ3g\u0013\u0011\ti+a,\u0003\rM#(/\u001b8h\u0015\u0011\tI+!\u0002)\u0017=\t)#!\u0018\u00024\u0006%\u00131J\u0011\u0003\u0003k\u000b1!\u001a=f\u0003-)\u00070Z2vi>\u0014\u0018\n\u001a\u0011\u0002\t!|7\u000f\u001e\u0015\f#\u0005\u0015\u0012QLA_\u0003\u0013\nY%\t\u0002\u0002@\u0006\u0019\u0001n\u001d;\u0002\u000b!|7\u000f\u001e\u0011)\u0017M\t)#!\u0018\u0002F\u0006%\u00131J\u0011\u0003\u0003\u000f\f1a\u001d;b\u0003\u001d\u0019H/\u0019;vg\u0002\nA\u0002^1tW2{7-\u00197jifD3\"FA\u0013\u0003;\ny-!\u0013\u0002L\u0005\u0012\u0011\u0011[\u0001\u0004Y>\u001c\u0017!\u0004;bg.dunY1mSRL\b%A\u0006ta\u0016\u001cW\u000f\\1uSZ,WCAAm!\u0011\t\u0019!a7\n\t\u0005u\u0017Q\u0001\u0002\b\u0005>|G.Z1o\u00031\u0019\b/Z2vY\u0006$\u0018N^3!\u0003I\t7mY;nk2\fGo\u001c:Va\u0012\fG/Z:\u0016\u0005\u0005\u0015\bCBAt\u0003[\f\t0\u0004\u0002\u0002j*!\u00111^A\u0003\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003_\fIOA\u0002TKF\u0004B!a=\u0002~6\u0011\u0011Q\u001f\u0006\u0005\u0003o\fI0\u0001\u0002wc)\u0019\u00111`<\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002\u0000\u0006U(aD!dGVlW\u000f\\1cY\u0016LeNZ8\u0002'\u0005\u001c7-^7vY\u0006$xN]+qI\u0006$Xm\u001d\u0011\u0002\u0019\u0015\u0014(o\u001c:NKN\u001c\u0018mZ3\u0016\u0005\t\u001d\u0001CBA\u0002\u0005\u0013\tY*\u0003\u0003\u0003\f\u0005\u0015!AB(qi&|g.A\u0007feJ|'/T3tg\u0006<W\rI\u0001\u000bQ\u0006\u001cX*\u001a;sS\u000e\u001c\u0018a\u00035bg6+GO]5dg\u0002\nq#\u001a=fGV$xN\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016$\u0016.\\3)\u0017}\t)#!\u0018\u0003\u0018\u0005%\u00131J\u0011\u0003\u00053\t1\u0001Z3t\u0003a)\u00070Z2vi>\u0014H)Z:fe&\fG.\u001b>f)&lW\rI\u0001\u001bKb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.\u001a\u0015\fC\u0005\u0015\u0012Q\fB\u0011\u0003\u0013\nY%\t\u0002\u0003$\u0005\u0019Am\u0019;\u00027\u0015DXmY;u_J$Um]3sS\u0006d\u0017N_3DaV$\u0016.\\3!\u0003=)\u00070Z2vi>\u0014(+\u001e8US6,\u0007fC\u0012\u0002&\u0005u#1FA%\u0003\u0017\n#A!\f\u0002\u0007\u0015\u0014H/\u0001\tfq\u0016\u001cW\u000f^8s%VtG+[7fA\u0005yQ\r_3dkR|'o\u00119v)&lW\rK\u0006&\u0003K\tiF!\u000e\u0002J\u0005-\u0013E\u0001B\u001c\u0003\r)7\r^\u0001\u0011Kb,7-\u001e;pe\u000e\u0003X\u000fV5nK\u0002\n!B]3tk2$8+\u001b>fQ-9\u0013QEA/\u0005\u007f\tI%a\u0013\"\u0005\t\u0005\u0013A\u0001:t\u0003-\u0011Xm];miNK'0\u001a\u0011\u0002\u0013)4XnR2US6,\u0007fC\u0015\u0002&\u0005u#\u0011JA%\u0003\u0017\n#Aa\u0013\u0002\u0005\u001d\u001c\u0017A\u00036w[\u001e\u001bG+[7fA\u00059\"/Z:vYR\u001cVM]5bY&T\u0018\r^5p]RKW.\u001a\u0015\fW\u0005\u0015\u0012Q\fB*\u0003\u0013\nY%\t\u0002\u0003V\u0005\u0019!o\u001d;\u00021I,7/\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8US6,\u0007%\u0001\nnK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$\u0007fC\u0017\u0002&\u0005u#QLA%\u0003\u0017\n#Aa\u0018\u0002\u00075\u00147/A\nnK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$\u0007%\u0001\teSN\\')\u001f;fgN\u0003\u0018\u000e\u001c7fI\"Zq&!\n\u0002^\t\u001d\u0014\u0011JA&C\t\u0011I'A\u0002eEN\f\u0011\u0003Z5tW\nKH/Z:Ta&dG.\u001a3!\u0003M\u0001X-Y6Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:zQ-\t\u0014QEA/\u0005c\nI%a\u0013\"\u0005\tM\u0014a\u00019f[\u0006!\u0002/Z1l\u000bb,7-\u001e;j_:lU-\\8ss\u0002\na\"\u001b8qkR\u0014\u0015\u0010^3t%\u0016\fG\rK\u00064\u0003K\tiFa\u001f\u0002J\u0005-\u0013E\u0001B?\u0003\tI7/A\bj]B,HOQ=uKN\u0014V-\u00193!\u0003AIg\u000e];u%\u0016\u001cwN\u001d3t%\u0016\fG\rK\u00066\u0003K\tiF!\"\u0002J\u0005-\u0013E\u0001BD\u0003\tI'/A\tj]B,HOU3d_J$7OU3bI\u0002\n!c\\;uaV$()\u001f;fg^\u0013\u0018\u000e\u001e;f]\"Zq'!\n\u0002^\t=\u0015\u0011JA&C\t\u0011\t*\u0001\u0002pg\u0006\u0019r.\u001e;qkR\u0014\u0015\u0010^3t/JLG\u000f^3oA\u0005!r.\u001e;qkR\u0014VmY8sIN<&/\u001b;uK:D3\"OA\u0013\u0003;\u0012I*!\u0013\u0002L\u0005\u0012!1T\u0001\u0003_J\fQc\\;uaV$(+Z2pe\u0012\u001cxK]5ui\u0016t\u0007%\u0001\u000etQV4g\r\\3SK6|G/\u001a\"m_\u000e\\7OR3uG\",G\rK\u0006<\u0003K\tiFa)\u0002J\u0005-\u0013E\u0001BS\u0003\u0011\u0019(O\u00197\u00027MDWO\u001a4mKJ+Wn\u001c;f\u00052|7m[:GKR\u001c\u0007.\u001a3!\u0003e\u0019\b.\u001e4gY\u0016dunY1m\u00052|7m[:GKR\u001c\u0007.\u001a3)\u0017u\n)#!\u0018\u0003.\u0006%\u00131J\u0011\u0003\u0005_\u000bAa\u001d7cY\u0006Q2\u000f[;gM2,Gj\\2bY\ncwnY6t\r\u0016$8\r[3eA\u0005!2\u000f[;gM2,g)\u001a;dQ^\u000b\u0017\u000e\u001e+j[\u0016D3bPA\u0013\u0003;\u00129,!\u0013\u0002L\u0005\u0012!\u0011X\u0001\u0004gJ$\u0018!F:ik\u001a4G.\u001a$fi\u000eDw+Y5u)&lW\rI\u0001\u0017g\",hM\u001a7f%\u0016lw\u000e^3CsR,7OU3bI\"Z\u0011)!\n\u0002^\t\u0005\u0017\u0011JA&C\t\u0011\u0019-\u0001\u0003te\nL\u0018aF:ik\u001a4G.\u001a*f[>$XMQ=uKN\u0014V-\u00193!\u0003q\u0019\b.\u001e4gY\u0016\u0014V-\\8uK\nKH/Z:SK\u0006$Gk\u001c#jg.D3bQA\u0013\u0003;\u0012Y-!\u0013\u0002L\u0005\u0012!QZ\u0001\u0005gJ\u0014G-A\u000ftQV4g\r\\3SK6|G/\u001a\"zi\u0016\u001c(+Z1e)>$\u0015n]6!\u0003U\u0019\b.\u001e4gY\u0016dunY1m\u0005f$Xm\u001d*fC\u0012\fac\u001d5vM\u001adW\rT8dC2\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u0013g\",hM\u001a7f%\u0016\u001cwN\u001d3t%\u0016\fG\rK\u0006H\u0003K\tiF!7\u0002J\u0005-\u0013E\u0001Bn\u0003\r\u0019(O]\u0001\u0014g\",hM\u001a7f%\u0016\u001cwN\u001d3t%\u0016\fG\rI\u0001 g\",hM\u001a7f\u0007>\u0014(/\u001e9u\u001b\u0016\u0014x-\u001a3CY>\u001c7n\u00115v].\u001c\bfC%\u0002&\u0005u#1]A%\u0003\u0017\n#A!:\u0002\rM\u00048-\u001c2d\u0003\u0001\u001a\b.\u001e4gY\u0016\u001cuN\u001d:vaRlUM]4fI\ncwnY6DQVt7n\u001d\u0011\u0002?MDWO\u001a4mK6+'oZ3e\r\u0016$8\r\u001b$bY2\u0014\u0017mY6D_VtG\u000fK\u0006L\u0003K\tiF!<\u0002J\u0005-\u0013E\u0001Bx\u0003\u0019\u0019\b/\u001c4gG\u0006\u00013\u000f[;gM2,W*\u001a:hK\u00124U\r^2i\r\u0006dGNY1dW\u000e{WO\u001c;!\u0003\u0001\u001a\b.\u001e4gY\u0016lUM]4fIJ+Wn\u001c;f\u00052|7m[:GKR\u001c\u0007.\u001a3)\u00175\u000b)#!\u0018\u0003x\u0006%\u00131J\u0011\u0003\u0005s\fQa\u001d9ne\n\f\u0011e\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u0014En\\2lg\u001a+Go\u00195fI\u0002\nqd\u001d5vM\u001adW-T3sO\u0016$Gj\\2bY\ncwnY6t\r\u0016$8\r[3eQ-y\u0015QEA/\u0007\u0003\tI%a\u0013\"\u0005\r\r\u0011!B:q[2\u0014\u0017\u0001I:ik\u001a4G.Z'fe\u001e,G\rT8dC2\u0014En\\2lg\u001a+Go\u00195fI\u0002\n\u0001e\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u001c\u0005.\u001e8lg\u001a+Go\u00195fI\"Z\u0011+!\n\u0002^\r-\u0011\u0011JA&C\t\u0019i!A\u0003ta6\u00148-A\u0011tQV4g\r\\3NKJ<W\r\u001a*f[>$Xm\u00115v].\u001ch)\u001a;dQ\u0016$\u0007%A\u0010tQV4g\r\\3NKJ<W\r\u001a'pG\u0006d7\t[;oWN4U\r^2iK\u0012D3bUA\u0013\u0003;\u001a)\"!\u0013\u0002L\u0005\u00121qC\u0001\u0006gBlGnY\u0001!g\",hM\u001a7f\u001b\u0016\u0014x-\u001a3M_\u000e\fGn\u00115v].\u001ch)\u001a;dQ\u0016$\u0007%\u0001\u000ftQV4g\r\\3NKJ<W\r\u001a*f[>$XMQ=uKN\u0014V-\u00193)\u0017U\u000b)#!\u0018\u0004 \u0005%\u00131J\u0011\u0003\u0007C\tQa\u001d9neJ\fQd\u001d5vM\u001adW-T3sO\u0016$'+Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u001cg\",hM\u001a7f\u001b\u0016\u0014x-\u001a3M_\u000e\fGNQ=uKN\u0014V-\u00193)\u0017]\u000b)#!\u0018\u0004*\u0005%\u00131J\u0011\u0003\u0007W\tQa\u001d9nYJ\fAd\u001d5vM\u001adW-T3sO\u0016$Gj\\2bY\nKH/Z:SK\u0006$\u0007%A\rtQV4g\r\\3SK6|G/\u001a*fcN$UO]1uS>t\u0007fC-\u0002&\u0005u31GA%\u0003\u0017\n#a!\u000e\u0002\tM\u0014(\u000fZ\u0001\u001bg\",hM\u001a7f%\u0016lw\u000e^3SKF\u001cH)\u001e:bi&|g\u000eI\u0001\u001fg\",hM\u001a7f\u001b\u0016\u0014x-\u001a3SK6|G/\u001a*fc\u0012+(/\u0019;j_:D3bWA\u0013\u0003;\u001ai$!\u0013\u0002L\u0005\u00121qH\u0001\u0007gBl'O\u001d3\u0002?MDWO\u001a4mK6+'oZ3e%\u0016lw\u000e^3SKF$UO]1uS>t\u0007%A\ntQV4g\r\\3CsR,7o\u0016:jiR,g\u000eK\u0006^\u0003K\tifa\u0012\u0002J\u0005-\u0013EAB%\u0003\r\u0019xo]\u0001\u0015g\",hM\u001a7f\u0005f$Xm],sSR$XM\u001c\u0011\u0002!MDWO\u001a4mK^\u0013\u0018\u000e^3US6,\u0007fC0\u0002&\u0005u3\u0011KA%\u0003\u0017\n#aa\u0015\u0002\u0007M<H/A\ttQV4g\r\\3Xe&$X\rV5nK\u0002\nQc\u001d5vM\u001adWMU3d_J$7o\u0016:jiR,g\u000eK\u0006b\u0003K\tifa\u0017\u0002J\u0005-\u0013EAB/\u0003\r\u0019xO]\u0001\u0017g\",hM\u001a7f%\u0016\u001cwN\u001d3t/JLG\u000f^3oA\u000591\u000f^1hK&#\u0017\u0001C:uC\u001e,\u0017\n\u001a\u0011\u0002\u001dM$\u0018mZ3BiR,W\u000e\u001d;JI\u0006y1\u000f^1hK\u0006#H/Z7qi&#\u0007%\u0001\u0004=S:LGO\u0010\u000bi\u0007[\u001a\tha\u001d\u0004v\r]4\u0011PB>\u0007{\u001ayh!!\u0004\u0004\u000e\u00155qQBE\u0007\u0017\u001biia$\u0004\u0012\u000eM5QSBL\u00073\u001bYj!(\u0004 \u000e\u000561UBS\u0007O\u001bIka+\u0004.\u000e=6\u0011WBZ\u0007k\u001b9l!/\u0004<\u000eu6qXBa\u0007\u0007\u001c)ma2\u0004J\u000e-7QZBh\u0007#\u001c\u0019n!6\u0011\u0007\r=\u0004!D\u0001x\u0011\u001d\tia\u001aa\u0001\u0003'Aq!!\u0015h\u0001\u0004\t)\u0006C\u0004\u0002f\u001d\u0004\r!!\u0016\t\u0013\u0005=t\r%AA\u0002\u0005U\u0003bBA=O\u0002\u0007\u0011Q\u0010\u0005\b\u0003\u0013;\u0007\u0019AA?\u0011\u001d\tii\u001aa\u0001\u0003{Bq!a&h\u0001\u0004\tY\nC\u0004\u0002:\u001e\u0004\r!a'\t\ra<\u0007\u0019AAN\u0011\u001d\tYm\u001aa\u0001\u00037Cq!!6h\u0001\u0004\tI\u000eC\u0004\u0002b\u001e\u0004\r!!:\t\u000f\t\rq\r1\u0001\u0003\b!9!qB4A\u0002\u0005e\u0007b\u0002B\nO\u0002\u0007\u0011Q\u0010\u0005\b\u0005;9\u0007\u0019AA?\u0011\u001d\u00119c\u001aa\u0001\u0003{BqA!\rh\u0001\u0004\ti\bC\u0004\u0003<\u001d\u0004\r!! \t\u000f\t\u0015s\r1\u0001\u0002~!9!qJ4A\u0002\u0005u\u0004b\u0002B-O\u0002\u0007\u0011Q\u0010\u0005\b\u0005G:\u0007\u0019AA?\u0011\u001d\u0011ig\u001aa\u0001\u0003{BqAa\u001eh\u0001\u0004\ti\bC\u0004\u0003\u0002\u001e\u0004\r!! \t\u000f\t-u\r1\u0001\u0002~!9!QS4A\u0002\u0005u\u0004b\u0002BPO\u0002\u0007\u0011Q\u0010\u0005\b\u0005S;\u0007\u0019AA?\u0011\u001d\u0011\u0019l\u001aa\u0001\u0003{BqA!0h\u0001\u0004\ti\bC\u0004\u0003H\u001e\u0004\r!! \t\u000f\tEw\r1\u0001\u0002~!9!Q[4A\u0002\u0005u\u0004b\u0002BpO\u0002\u0007\u0011Q\u0010\u0005\b\u0005S<\u0007\u0019AA?\u0011\u001d\u0011\u0019p\u001aa\u0001\u0003{BqA!@h\u0001\u0004\ti\bC\u0004\u0004\b\u001d\u0004\r!! \t\u000f\rEq\r1\u0001\u0002~!911D4A\u0002\u0005u\u0004bBB\u0013O\u0002\u0007\u0011Q\u0010\u0005\b\u0007_9\u0007\u0019AA?\u0011\u001d\u0019Id\u001aa\u0001\u0003{Bqaa\u0011h\u0001\u0004\ti\bC\u0004\u0004N\u001d\u0004\r!! \t\u000f\r]s\r1\u0001\u0002~!91\u0011M4A\u0002\u0005U\u0003bBB3O\u0002\u0007\u0011QK\u0001\u000fO\u0016$X*\u001a;sS\u000e4\u0016\r\\;f)\u0011\tiha7\t\u000f\ru\u0007\u000e1\u0001\u0002~\u00051Q.\u001a;sS\u000e\fQ\u0001^8Ba&,\"aa9\u0011\t\u0005M8Q]\u0005\u0005\u0007O\f)P\u0001\u0005UCN\\G)\u0019;b+\t\u0019Y\u000f\u0005\u0004\u0002\u0004\r5\u0018QK\u0005\u0005\u0007_\f)AA\u0003BeJ\f\u0017\u0010K\u0002k\u0007g\u0004Ba!>\u0005\u00065\u00111q\u001f\u0006\u0005\u0003\u0007\u001aIP\u0003\u0003\u0004|\u000eu\u0018a\u00026bG.\u001cxN\u001c\u0006\u0005\u0007\u007f$\t!A\u0005gCN$XM\u001d=nY*\u0011A1A\u0001\u0004G>l\u0017\u0002\u0002C\u0004\u0007o\u0014!BS:p]&;gn\u001c:fQ\u001dQ\u0017qEA/\u0003\u0017\nab]2iK\u0012,H.\u001a:EK2\f\u0017\u0010K\u0002l\u0007gD3b[A\u0014\u0003;\"\t\"!\u0013\u0002L\u0005\u0012A1C\u0001\u0004I2L\u0018!E4fiRLgn\u001a*fgVdG\u000fV5nK\"\u001aAna=)\u00171\f9#!\u0018\u0005\u001c\u0005%\u00131J\u0011\u0003\t;\t1a\u001a:u\u00031\t7mY;nk2\fGo\u001c:tQ\ri71\u001f\u0015\f[\u0006\u001d\u0012Q\fC\u0013\u0003\u0013\nY%\t\u0002\u0005(\u0005\u0019\u0011mY2\u0002#MDWO\u001a4mKR{G/\u00197SK\u0006$7\u000fK\u0002o\u0007gD3B\\A\u0014\u0003;\"y#!\u0013\u0002L\u0005\u0012A\u0011G\u0001\u0005gR\u0014\u00170\u0001\ntQV4g\r\\3U_R\fGN\u00117pG.\u001c\bfA8\u0004t\"Zq.a\n\u0002^\u0011e\u0012\u0011JA&C\t!Y$\u0001\u0003ti\nd\u0017!B3se>\u0014\bf\u00019\u0004t\"Z\u0001/a\n\u0002^\u0011\r\u0013\u0011JA&C\t!)%A\u0002feJ\fabY8na2,G/[8o)&lW\rK\u0002r\u0007gD3\"]A\u0014\u0003;\"i%!\u0013\u0002L\u0005\u0012AqJ\u0001\u0003GR\fq\u0002V1tW\u0012\u000bG/Y,sCB\u0004XM\u001d\t\u0004\u0007_\u001a8cA:\u0002\u0002Q\u0011A1K\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0011u#\u0006BA+\t?Z#\u0001\"\u0019\u0011\t\u0011\rD\u0011N\u0007\u0003\tKRA\u0001b\u001a\u0002B\u0005IQO\\2iK\u000e\\W\rZ\u0005\u0005\tW\")GA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class TaskDataWrapper {
   private final Long taskId;
   private final int index;
   private final int attempt;
   private final int partitionId;
   private final long launchTime;
   private final long resultFetchStart;
   private final long duration;
   private final String executorId;
   private final String host;
   private final String status;
   private final String taskLocality;
   private final boolean speculative;
   private final Seq accumulatorUpdates;
   private final Option errorMessage;
   private final boolean hasMetrics;
   private final long executorDeserializeTime;
   private final long executorDeserializeCpuTime;
   private final long executorRunTime;
   private final long executorCpuTime;
   private final long resultSize;
   private final long jvmGcTime;
   private final long resultSerializationTime;
   private final long memoryBytesSpilled;
   private final long diskBytesSpilled;
   private final long peakExecutionMemory;
   private final long inputBytesRead;
   private final long inputRecordsRead;
   private final long outputBytesWritten;
   private final long outputRecordsWritten;
   private final long shuffleRemoteBlocksFetched;
   private final long shuffleLocalBlocksFetched;
   private final long shuffleFetchWaitTime;
   private final long shuffleRemoteBytesRead;
   private final long shuffleRemoteBytesReadToDisk;
   private final long shuffleLocalBytesRead;
   private final long shuffleRecordsRead;
   private final long shuffleCorruptMergedBlockChunks;
   private final long shuffleMergedFetchFallbackCount;
   private final long shuffleMergedRemoteBlocksFetched;
   private final long shuffleMergedLocalBlocksFetched;
   private final long shuffleMergedRemoteChunksFetched;
   private final long shuffleMergedLocalChunksFetched;
   private final long shuffleMergedRemoteBytesRead;
   private final long shuffleMergedLocalBytesRead;
   private final long shuffleRemoteReqsDuration;
   private final long shuffleMergedRemoteReqDuration;
   private final long shuffleBytesWritten;
   private final long shuffleWriteTime;
   private final long shuffleRecordsWritten;
   private final int stageId;
   private final int stageAttemptId;

   public static int $lessinit$greater$default$4() {
      return TaskDataWrapper$.MODULE$.$lessinit$greater$default$4();
   }

   @KVIndex(
      parent = "stage"
   )
   public Long taskId() {
      return this.taskId;
   }

   @KVIndex(
      value = "idx",
      parent = "stage"
   )
   public int index() {
      return this.index;
   }

   @KVIndex(
      value = "att",
      parent = "stage"
   )
   public int attempt() {
      return this.attempt;
   }

   @KVIndex(
      value = "partid",
      parent = "stage"
   )
   public int partitionId() {
      return this.partitionId;
   }

   @KVIndex(
      value = "lt",
      parent = "stage"
   )
   public long launchTime() {
      return this.launchTime;
   }

   public long resultFetchStart() {
      return this.resultFetchStart;
   }

   @KVIndex(
      value = "dur",
      parent = "stage"
   )
   public long duration() {
      return this.duration;
   }

   @KVIndex(
      value = "exe",
      parent = "stage"
   )
   public String executorId() {
      return this.executorId;
   }

   @KVIndex(
      value = "hst",
      parent = "stage"
   )
   public String host() {
      return this.host;
   }

   @KVIndex(
      value = "sta",
      parent = "stage"
   )
   public String status() {
      return this.status;
   }

   @KVIndex(
      value = "loc",
      parent = "stage"
   )
   public String taskLocality() {
      return this.taskLocality;
   }

   public boolean speculative() {
      return this.speculative;
   }

   public Seq accumulatorUpdates() {
      return this.accumulatorUpdates;
   }

   public Option errorMessage() {
      return this.errorMessage;
   }

   public boolean hasMetrics() {
      return this.hasMetrics;
   }

   @KVIndex(
      value = "des",
      parent = "stage"
   )
   public long executorDeserializeTime() {
      return this.executorDeserializeTime;
   }

   @KVIndex(
      value = "dct",
      parent = "stage"
   )
   public long executorDeserializeCpuTime() {
      return this.executorDeserializeCpuTime;
   }

   @KVIndex(
      value = "ert",
      parent = "stage"
   )
   public long executorRunTime() {
      return this.executorRunTime;
   }

   @KVIndex(
      value = "ect",
      parent = "stage"
   )
   public long executorCpuTime() {
      return this.executorCpuTime;
   }

   @KVIndex(
      value = "rs",
      parent = "stage"
   )
   public long resultSize() {
      return this.resultSize;
   }

   @KVIndex(
      value = "gc",
      parent = "stage"
   )
   public long jvmGcTime() {
      return this.jvmGcTime;
   }

   @KVIndex(
      value = "rst",
      parent = "stage"
   )
   public long resultSerializationTime() {
      return this.resultSerializationTime;
   }

   @KVIndex(
      value = "mbs",
      parent = "stage"
   )
   public long memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   @KVIndex(
      value = "dbs",
      parent = "stage"
   )
   public long diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   @KVIndex(
      value = "pem",
      parent = "stage"
   )
   public long peakExecutionMemory() {
      return this.peakExecutionMemory;
   }

   @KVIndex(
      value = "is",
      parent = "stage"
   )
   public long inputBytesRead() {
      return this.inputBytesRead;
   }

   @KVIndex(
      value = "ir",
      parent = "stage"
   )
   public long inputRecordsRead() {
      return this.inputRecordsRead;
   }

   @KVIndex(
      value = "os",
      parent = "stage"
   )
   public long outputBytesWritten() {
      return this.outputBytesWritten;
   }

   @KVIndex(
      value = "or",
      parent = "stage"
   )
   public long outputRecordsWritten() {
      return this.outputRecordsWritten;
   }

   @KVIndex(
      value = "srbl",
      parent = "stage"
   )
   public long shuffleRemoteBlocksFetched() {
      return this.shuffleRemoteBlocksFetched;
   }

   @KVIndex(
      value = "slbl",
      parent = "stage"
   )
   public long shuffleLocalBlocksFetched() {
      return this.shuffleLocalBlocksFetched;
   }

   @KVIndex(
      value = "srt",
      parent = "stage"
   )
   public long shuffleFetchWaitTime() {
      return this.shuffleFetchWaitTime;
   }

   @KVIndex(
      value = "srby",
      parent = "stage"
   )
   public long shuffleRemoteBytesRead() {
      return this.shuffleRemoteBytesRead;
   }

   @KVIndex(
      value = "srbd",
      parent = "stage"
   )
   public long shuffleRemoteBytesReadToDisk() {
      return this.shuffleRemoteBytesReadToDisk;
   }

   public long shuffleLocalBytesRead() {
      return this.shuffleLocalBytesRead;
   }

   @KVIndex(
      value = "srr",
      parent = "stage"
   )
   public long shuffleRecordsRead() {
      return this.shuffleRecordsRead;
   }

   @KVIndex(
      value = "spcmbc",
      parent = "stage"
   )
   public long shuffleCorruptMergedBlockChunks() {
      return this.shuffleCorruptMergedBlockChunks;
   }

   @KVIndex(
      value = "spmffc",
      parent = "stage"
   )
   public long shuffleMergedFetchFallbackCount() {
      return this.shuffleMergedFetchFallbackCount;
   }

   @KVIndex(
      value = "spmrb",
      parent = "stage"
   )
   public long shuffleMergedRemoteBlocksFetched() {
      return this.shuffleMergedRemoteBlocksFetched;
   }

   @KVIndex(
      value = "spmlb",
      parent = "stage"
   )
   public long shuffleMergedLocalBlocksFetched() {
      return this.shuffleMergedLocalBlocksFetched;
   }

   @KVIndex(
      value = "spmrc",
      parent = "stage"
   )
   public long shuffleMergedRemoteChunksFetched() {
      return this.shuffleMergedRemoteChunksFetched;
   }

   @KVIndex(
      value = "spmlc",
      parent = "stage"
   )
   public long shuffleMergedLocalChunksFetched() {
      return this.shuffleMergedLocalChunksFetched;
   }

   @KVIndex(
      value = "spmrr",
      parent = "stage"
   )
   public long shuffleMergedRemoteBytesRead() {
      return this.shuffleMergedRemoteBytesRead;
   }

   @KVIndex(
      value = "spmlr",
      parent = "stage"
   )
   public long shuffleMergedLocalBytesRead() {
      return this.shuffleMergedLocalBytesRead;
   }

   @KVIndex(
      value = "srrd",
      parent = "stage"
   )
   public long shuffleRemoteReqsDuration() {
      return this.shuffleRemoteReqsDuration;
   }

   @KVIndex(
      value = "spmrrd",
      parent = "stage"
   )
   public long shuffleMergedRemoteReqDuration() {
      return this.shuffleMergedRemoteReqDuration;
   }

   @KVIndex(
      value = "sws",
      parent = "stage"
   )
   public long shuffleBytesWritten() {
      return this.shuffleBytesWritten;
   }

   @KVIndex(
      value = "swt",
      parent = "stage"
   )
   public long shuffleWriteTime() {
      return this.shuffleWriteTime;
   }

   @KVIndex(
      value = "swr",
      parent = "stage"
   )
   public long shuffleRecordsWritten() {
      return this.shuffleRecordsWritten;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   private long getMetricValue(final long metric) {
      String var10000 = this.status();
      String var3 = "SUCCESS";
      if (var10000 == null) {
         if (var3 != null) {
            return .MODULE$.abs(metric + 1L);
         }
      } else if (!var10000.equals(var3)) {
         return .MODULE$.abs(metric + 1L);
      }

      return metric;
   }

   public TaskData toApi() {
      Option metrics = (Option)(this.hasMetrics() ? new Some(new TaskMetrics(this.getMetricValue(this.executorDeserializeTime()), this.getMetricValue(this.executorDeserializeCpuTime()), this.getMetricValue(this.executorRunTime()), this.getMetricValue(this.executorCpuTime()), this.getMetricValue(this.resultSize()), this.getMetricValue(this.jvmGcTime()), this.getMetricValue(this.resultSerializationTime()), this.getMetricValue(this.memoryBytesSpilled()), this.getMetricValue(this.diskBytesSpilled()), this.getMetricValue(this.peakExecutionMemory()), new InputMetrics(this.getMetricValue(this.inputBytesRead()), this.getMetricValue(this.inputRecordsRead())), new OutputMetrics(this.getMetricValue(this.outputBytesWritten()), this.getMetricValue(this.outputRecordsWritten())), new ShuffleReadMetrics(this.getMetricValue(this.shuffleRemoteBlocksFetched()), this.getMetricValue(this.shuffleLocalBlocksFetched()), this.getMetricValue(this.shuffleFetchWaitTime()), this.getMetricValue(this.shuffleRemoteBytesRead()), this.getMetricValue(this.shuffleRemoteBytesReadToDisk()), this.getMetricValue(this.shuffleLocalBytesRead()), this.getMetricValue(this.shuffleRecordsRead()), this.getMetricValue(this.shuffleRemoteReqsDuration()), new ShufflePushReadMetrics(this.getMetricValue(this.shuffleCorruptMergedBlockChunks()), this.getMetricValue(this.shuffleMergedFetchFallbackCount()), this.getMetricValue(this.shuffleMergedRemoteBlocksFetched()), this.getMetricValue(this.shuffleMergedLocalBlocksFetched()), this.getMetricValue(this.shuffleMergedRemoteChunksFetched()), this.getMetricValue(this.shuffleMergedLocalChunksFetched()), this.getMetricValue(this.shuffleMergedRemoteBytesRead()), this.getMetricValue(this.shuffleMergedLocalBytesRead()), this.getMetricValue(this.shuffleMergedRemoteReqDuration()))), new ShuffleWriteMetrics(this.getMetricValue(this.shuffleBytesWritten()), this.getMetricValue(this.shuffleWriteTime()), this.getMetricValue(this.shuffleRecordsWritten())))) : scala.None..MODULE$);
      return new TaskData(scala.Predef..MODULE$.Long2long(this.taskId()), this.index(), this.attempt(), this.partitionId(), new Date(this.launchTime()), (Option)(this.resultFetchStart() > 0L ? new Some(new Date(this.resultFetchStart())) : scala.None..MODULE$), (Option)(this.duration() > 0L ? new Some(BoxesRunTime.boxToLong(this.duration())) : scala.None..MODULE$), this.executorId(), this.host(), this.status(), this.taskLocality(), this.speculative(), this.accumulatorUpdates(), this.errorMessage(), metrics, (Map)null, 0L, 0L);
   }

   @JsonIgnore
   @KVIndex("stage")
   private int[] stage() {
      return new int[]{this.stageId(), this.stageAttemptId()};
   }

   @JsonIgnore
   @KVIndex(
      value = "dly",
      parent = "stage"
   )
   public long schedulerDelay() {
      return this.hasMetrics() ? AppStatusUtils$.MODULE$.schedulerDelay(this.launchTime(), this.resultFetchStart(), this.duration(), this.getMetricValue(this.executorDeserializeTime()), this.getMetricValue(this.resultSerializationTime()), this.getMetricValue(this.executorRunTime())) : -1L;
   }

   @JsonIgnore
   @KVIndex(
      value = "grt",
      parent = "stage"
   )
   public long gettingResultTime() {
      return this.hasMetrics() ? AppStatusUtils$.MODULE$.gettingResultTime(this.launchTime(), this.resultFetchStart(), this.duration()) : -1L;
   }

   @JsonIgnore
   @KVIndex(
      value = "acc",
      parent = "stage"
   )
   private String accumulators() {
      if (this.accumulatorUpdates().nonEmpty()) {
         AccumulableInfo acc = (AccumulableInfo)this.accumulatorUpdates().head();
         String var10000 = acc.name();
         return var10000 + ":" + acc.value();
      } else {
         return "";
      }
   }

   @JsonIgnore
   @KVIndex(
      value = "stby",
      parent = "stage"
   )
   private long shuffleTotalReads() {
      return this.hasMetrics() ? this.shuffleLocalBytesRead() + this.shuffleRemoteBytesRead() : -1L;
   }

   @JsonIgnore
   @KVIndex(
      value = "stbl",
      parent = "stage"
   )
   private long shuffleTotalBlocks() {
      return this.hasMetrics() ? this.shuffleLocalBlocksFetched() + this.shuffleRemoteBlocksFetched() : -1L;
   }

   @JsonIgnore
   @KVIndex(
      value = "err",
      parent = "stage"
   )
   private String error() {
      return this.errorMessage().isDefined() ? (String)this.errorMessage().get() : "";
   }

   @JsonIgnore
   @KVIndex(
      value = "ct",
      parent = "stage"
   )
   private long completionTime() {
      return this.launchTime() + this.duration();
   }

   public TaskDataWrapper(final Long taskId, final int index, final int attempt, final int partitionId, final long launchTime, final long resultFetchStart, final long duration, final String executorId, final String host, final String status, final String taskLocality, final boolean speculative, final Seq accumulatorUpdates, final Option errorMessage, final boolean hasMetrics, final long executorDeserializeTime, final long executorDeserializeCpuTime, final long executorRunTime, final long executorCpuTime, final long resultSize, final long jvmGcTime, final long resultSerializationTime, final long memoryBytesSpilled, final long diskBytesSpilled, final long peakExecutionMemory, final long inputBytesRead, final long inputRecordsRead, final long outputBytesWritten, final long outputRecordsWritten, final long shuffleRemoteBlocksFetched, final long shuffleLocalBlocksFetched, final long shuffleFetchWaitTime, final long shuffleRemoteBytesRead, final long shuffleRemoteBytesReadToDisk, final long shuffleLocalBytesRead, final long shuffleRecordsRead, final long shuffleCorruptMergedBlockChunks, final long shuffleMergedFetchFallbackCount, final long shuffleMergedRemoteBlocksFetched, final long shuffleMergedLocalBlocksFetched, final long shuffleMergedRemoteChunksFetched, final long shuffleMergedLocalChunksFetched, final long shuffleMergedRemoteBytesRead, final long shuffleMergedLocalBytesRead, final long shuffleRemoteReqsDuration, final long shuffleMergedRemoteReqDuration, final long shuffleBytesWritten, final long shuffleWriteTime, final long shuffleRecordsWritten, final int stageId, final int stageAttemptId) {
      this.taskId = taskId;
      this.index = index;
      this.attempt = attempt;
      this.partitionId = partitionId;
      this.launchTime = launchTime;
      this.resultFetchStart = resultFetchStart;
      this.duration = duration;
      this.executorId = executorId;
      this.host = host;
      this.status = status;
      this.taskLocality = taskLocality;
      this.speculative = speculative;
      this.accumulatorUpdates = accumulatorUpdates;
      this.errorMessage = errorMessage;
      this.hasMetrics = hasMetrics;
      this.executorDeserializeTime = executorDeserializeTime;
      this.executorDeserializeCpuTime = executorDeserializeCpuTime;
      this.executorRunTime = executorRunTime;
      this.executorCpuTime = executorCpuTime;
      this.resultSize = resultSize;
      this.jvmGcTime = jvmGcTime;
      this.resultSerializationTime = resultSerializationTime;
      this.memoryBytesSpilled = memoryBytesSpilled;
      this.diskBytesSpilled = diskBytesSpilled;
      this.peakExecutionMemory = peakExecutionMemory;
      this.inputBytesRead = inputBytesRead;
      this.inputRecordsRead = inputRecordsRead;
      this.outputBytesWritten = outputBytesWritten;
      this.outputRecordsWritten = outputRecordsWritten;
      this.shuffleRemoteBlocksFetched = shuffleRemoteBlocksFetched;
      this.shuffleLocalBlocksFetched = shuffleLocalBlocksFetched;
      this.shuffleFetchWaitTime = shuffleFetchWaitTime;
      this.shuffleRemoteBytesRead = shuffleRemoteBytesRead;
      this.shuffleRemoteBytesReadToDisk = shuffleRemoteBytesReadToDisk;
      this.shuffleLocalBytesRead = shuffleLocalBytesRead;
      this.shuffleRecordsRead = shuffleRecordsRead;
      this.shuffleCorruptMergedBlockChunks = shuffleCorruptMergedBlockChunks;
      this.shuffleMergedFetchFallbackCount = shuffleMergedFetchFallbackCount;
      this.shuffleMergedRemoteBlocksFetched = shuffleMergedRemoteBlocksFetched;
      this.shuffleMergedLocalBlocksFetched = shuffleMergedLocalBlocksFetched;
      this.shuffleMergedRemoteChunksFetched = shuffleMergedRemoteChunksFetched;
      this.shuffleMergedLocalChunksFetched = shuffleMergedLocalChunksFetched;
      this.shuffleMergedRemoteBytesRead = shuffleMergedRemoteBytesRead;
      this.shuffleMergedLocalBytesRead = shuffleMergedLocalBytesRead;
      this.shuffleRemoteReqsDuration = shuffleRemoteReqsDuration;
      this.shuffleMergedRemoteReqDuration = shuffleMergedRemoteReqDuration;
      this.shuffleBytesWritten = shuffleBytesWritten;
      this.shuffleWriteTime = shuffleWriteTime;
      this.shuffleRecordsWritten = shuffleRecordsWritten;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
   }
}
