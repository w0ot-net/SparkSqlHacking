package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.status.api.v1.MemoryMetrics;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015g!\u00024h\u0001%|\u0007\u0002\u0003;\u0001\u0005\u000b\u0007I\u0011\u0001<\t\u0013\u0005%\u0001A!A!\u0002\u00139\bBCA\u0006\u0001\t\u0005\t\u0015!\u0003\u0002\u000e!9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0001\u0002CA\u0010\u0001\u0001\u0007I\u0011\u0001<\t\u0013\u0005\u0005\u0002\u00011A\u0005\u0002\u0005\r\u0002bBA\u0018\u0001\u0001\u0006Ka\u001e\u0005\t\u0003c\u0001\u0001\u0019!C\u0001m\"I\u00111\u0007\u0001A\u0002\u0013\u0005\u0011Q\u0007\u0005\b\u0003s\u0001\u0001\u0015)\u0003x\u0011%\tY\u0004\u0001a\u0001\n\u0003\ti\u0004C\u0005\u0002F\u0001\u0001\r\u0011\"\u0001\u0002H!A\u00111\n\u0001!B\u0013\ty\u0004C\u0005\u0002N\u0001\u0001\r\u0011\"\u0001\u0002P!I\u0011q\u000b\u0001A\u0002\u0013\u0005\u0011\u0011\f\u0005\t\u0003;\u0002\u0001\u0015)\u0003\u0002R!I\u0011q\f\u0001C\u0002\u0013\u0005\u0011\u0011\r\u0005\t\u0003g\u0002\u0001\u0015!\u0003\u0002d!I\u0011Q\u000f\u0001A\u0002\u0013\u0005\u0011\u0011\r\u0005\n\u0003o\u0002\u0001\u0019!C\u0001\u0003sB\u0001\"! \u0001A\u0003&\u00111\r\u0005\t\u0003\u007f\u0002\u0001\u0019!C\u0001m\"I\u0011\u0011\u0011\u0001A\u0002\u0013\u0005\u00111\u0011\u0005\b\u0003\u000f\u0003\u0001\u0015)\u0003x\u0011%\tI\t\u0001a\u0001\n\u0003\ty\u0005C\u0005\u0002\f\u0002\u0001\r\u0011\"\u0001\u0002\u000e\"A\u0011\u0011\u0013\u0001!B\u0013\t\t\u0006C\u0005\u0002\u0014\u0002\u0001\r\u0011\"\u0001\u0002\u0016\"I\u0011q\u0013\u0001A\u0002\u0013\u0005\u0011\u0011\u0014\u0005\t\u0003;\u0003\u0001\u0015)\u0003\u0002\u000e!I\u0011q\u0014\u0001A\u0002\u0013\u0005\u0011Q\u0013\u0005\n\u0003C\u0003\u0001\u0019!C\u0001\u0003GC\u0001\"a*\u0001A\u0003&\u0011Q\u0002\u0005\n\u0003S\u0003\u0001\u0019!C\u0001\u0003\u001fB\u0011\"a+\u0001\u0001\u0004%\t!!,\t\u0011\u0005E\u0006\u0001)Q\u0005\u0003#B\u0011\"a-\u0001\u0001\u0004%\t!!&\t\u0013\u0005U\u0006\u00011A\u0005\u0002\u0005]\u0006\u0002CA^\u0001\u0001\u0006K!!\u0004\t\u0013\u0005u\u0006\u00011A\u0005\u0002\u0005=\u0003\"CA`\u0001\u0001\u0007I\u0011AAa\u0011!\t)\r\u0001Q!\n\u0005E\u0003\"CAd\u0001\u0001\u0007I\u0011AA(\u0011%\tI\r\u0001a\u0001\n\u0003\tY\r\u0003\u0005\u0002P\u0002\u0001\u000b\u0015BA)\u0011%\t\t\u000e\u0001a\u0001\n\u0003\ty\u0005C\u0005\u0002T\u0002\u0001\r\u0011\"\u0001\u0002V\"A\u0011\u0011\u001c\u0001!B\u0013\t\t\u0006C\u0005\u0002\\\u0002\u0001\r\u0011\"\u0001\u0002P!I\u0011Q\u001c\u0001A\u0002\u0013\u0005\u0011q\u001c\u0005\t\u0003G\u0004\u0001\u0015)\u0003\u0002R!I\u0011Q\u001d\u0001A\u0002\u0013\u0005\u0011Q\u0013\u0005\n\u0003O\u0004\u0001\u0019!C\u0001\u0003SD\u0001\"!<\u0001A\u0003&\u0011Q\u0002\u0005\n\u0003_\u0004\u0001\u0019!C\u0001\u0003+C\u0011\"!=\u0001\u0001\u0004%\t!a=\t\u0011\u0005]\b\u0001)Q\u0005\u0003\u001bA\u0011\"!?\u0001\u0001\u0004%\t!!&\t\u0013\u0005m\b\u00011A\u0005\u0002\u0005u\b\u0002\u0003B\u0001\u0001\u0001\u0006K!!\u0004\t\u0013\t\r\u0001\u00011A\u0005\u0002\u0005U\u0005\"\u0003B\u0003\u0001\u0001\u0007I\u0011\u0001B\u0004\u0011!\u0011Y\u0001\u0001Q!\n\u00055\u0001\"\u0003B\u0007\u0001\u0001\u0007I\u0011AAK\u0011%\u0011y\u0001\u0001a\u0001\n\u0003\u0011\t\u0002\u0003\u0005\u0003\u0016\u0001\u0001\u000b\u0015BA\u0007\u0011%\u00119\u0002\u0001a\u0001\n\u0003\ti\u0004C\u0005\u0003\u001a\u0001\u0001\r\u0011\"\u0001\u0003\u001c!A!q\u0004\u0001!B\u0013\ty\u0004C\u0005\u0003\"\u0001\u0001\r\u0011\"\u0001\u0003$!I!1\u0006\u0001A\u0002\u0013\u0005!Q\u0006\u0005\t\u0005c\u0001\u0001\u0015)\u0003\u0003&!I!1\u0007\u0001A\u0002\u0013\u0005!Q\u0007\u0005\n\u0005\u000f\u0002\u0001\u0019!C\u0001\u0005\u0013B\u0001B!\u0014\u0001A\u0003&!q\u0007\u0005\n\u0005\u001f\u0002\u0001\u0019!C\u0001\u0005kA\u0011B!\u0015\u0001\u0001\u0004%\tAa\u0015\t\u0011\t]\u0003\u0001)Q\u0005\u0005oA\u0011B!\u0017\u0001\u0001\u0004%\tAa\u0017\t\u0013\t-\u0004\u00011A\u0005\u0002\t5\u0004\u0002\u0003B9\u0001\u0001\u0006KA!\u0018\t\u0013\tM\u0004\u00011A\u0005\u0002\u0005U\u0005\"\u0003B;\u0001\u0001\u0007I\u0011\u0001B<\u0011!\u0011Y\b\u0001Q!\n\u00055\u0001\"\u0003B?\u0001\u0001\u0007I\u0011AAK\u0011%\u0011y\b\u0001a\u0001\n\u0003\u0011\t\t\u0003\u0005\u0003\u0006\u0002\u0001\u000b\u0015BA\u0007\u0011%\u00119\t\u0001a\u0001\n\u0003\t)\nC\u0005\u0003\n\u0002\u0001\r\u0011\"\u0001\u0003\f\"A!q\u0012\u0001!B\u0013\ti\u0001C\u0005\u0003\u0012\u0002\u0001\r\u0011\"\u0001\u0002\u0016\"I!1\u0013\u0001A\u0002\u0013\u0005!Q\u0013\u0005\t\u00053\u0003\u0001\u0015)\u0003\u0002\u000e!I!1\u0014\u0001A\u0002\u0013\u0005\u0011q\n\u0005\n\u0005;\u0003\u0001\u0019!C\u0001\u0005?C\u0001Ba)\u0001A\u0003&\u0011\u0011\u000b\u0005\b\u0005K\u0003A\u0011AA\u001f\u0011%\u00119\u000b\u0001b\u0001\n\u0003\u0011I\u000b\u0003\u0005\u00038\u0002\u0001\u000b\u0011\u0002BV\u0011\u0019\u0011I\f\u0001C\u0001m\"9!1\u0018\u0001\u0005R\tu&\u0001\u0004'jm\u0016,\u00050Z2vi>\u0014(B\u00015j\u0003\u0019\u0019H/\u0019;vg*\u0011!n[\u0001\u0006gB\f'o\u001b\u0006\u0003Y6\fa!\u00199bG\",'\"\u00018\u0002\u0007=\u0014xm\u0005\u0002\u0001aB\u0011\u0011O]\u0007\u0002O&\u00111o\u001a\u0002\u000b\u0019&4X-\u00128uSRL\u0018AC3yK\u000e,Ho\u001c:JI\u000e\u0001Q#A<\u0011\u0007a\f\u0019A\u0004\u0002z\u007fB\u0011!0`\u0007\u0002w*\u0011A0^\u0001\u0007yI|w\u000e\u001e \u000b\u0003y\fQa]2bY\u0006L1!!\u0001~\u0003\u0019\u0001&/\u001a3fM&!\u0011QAA\u0004\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011A?\u0002\u0017\u0015DXmY;u_JLE\rI\u0001\t?\u0006$G\rV5nKB!\u0011qBA\t\u001b\u0005i\u0018bAA\n{\n!Aj\u001c8h\u0003\u0019a\u0014N\\5u}Q1\u0011\u0011DA\u000e\u0003;\u0001\"!\u001d\u0001\t\u000bQ$\u0001\u0019A<\t\u000f\u0005-A\u00011\u0001\u0002\u000e\u0005A\u0001n\\:u!>\u0014H/\u0001\u0007i_N$\bk\u001c:u?\u0012*\u0017\u000f\u0006\u0003\u0002&\u0005-\u0002\u0003BA\b\u0003OI1!!\u000b~\u0005\u0011)f.\u001b;\t\u0011\u00055b!!AA\u0002]\f1\u0001\u001f\u00132\u0003%Awn\u001d;Q_J$\b%\u0001\u0003i_N$\u0018\u0001\u00035pgR|F%Z9\u0015\t\u0005\u0015\u0012q\u0007\u0005\t\u0003[I\u0011\u0011!a\u0001o\u0006)\u0001n\\:uA\u0005A\u0011n]!di&4X-\u0006\u0002\u0002@A!\u0011qBA!\u0013\r\t\u0019% \u0002\b\u0005>|G.Z1o\u00031I7/Q2uSZ,w\fJ3r)\u0011\t)#!\u0013\t\u0013\u00055B\"!AA\u0002\u0005}\u0012!C5t\u0003\u000e$\u0018N^3!\u0003)!x\u000e^1m\u0007>\u0014Xm]\u000b\u0003\u0003#\u0002B!a\u0004\u0002T%\u0019\u0011QK?\u0003\u0007%sG/\u0001\bu_R\fGnQ8sKN|F%Z9\u0015\t\u0005\u0015\u00121\f\u0005\n\u0003[y\u0011\u0011!a\u0001\u0003#\n1\u0002^8uC2\u001cuN]3tA\u00059\u0011\r\u001a3US6,WCAA2!\u0011\t)'a\u001c\u000e\u0005\u0005\u001d$\u0002BA5\u0003W\nA!\u001e;jY*\u0011\u0011QN\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002r\u0005\u001d$\u0001\u0002#bi\u0016\f\u0001\"\u00193e)&lW\rI\u0001\u000be\u0016lwN^3US6,\u0017A\u0004:f[>4X\rV5nK~#S-\u001d\u000b\u0005\u0003K\tY\bC\u0005\u0002.Q\t\t\u00111\u0001\u0002d\u0005Y!/Z7pm\u0016$\u0016.\\3!\u00031\u0011X-\\8wKJ+\u0017m]8o\u0003A\u0011X-\\8wKJ+\u0017m]8o?\u0012*\u0017\u000f\u0006\u0003\u0002&\u0005\u0015\u0005\u0002CA\u0017/\u0005\u0005\t\u0019A<\u0002\u001bI,Wn\u001c<f%\u0016\f7o\u001c8!\u0003%\u0011H\r\u001a\"m_\u000e\\7/A\u0007sI\u0012\u0014En\\2lg~#S-\u001d\u000b\u0005\u0003K\ty\tC\u0005\u0002.i\t\t\u00111\u0001\u0002R\u0005Q!\u000f\u001a3CY>\u001c7n\u001d\u0011\u0002\u00155,Wn\u001c:z+N,G-\u0006\u0002\u0002\u000e\u0005qQ.Z7pef,6/\u001a3`I\u0015\fH\u0003BA\u0013\u00037C\u0011\"!\f\u001e\u0003\u0003\u0005\r!!\u0004\u0002\u00175,Wn\u001c:z+N,G\rI\u0001\tI&\u001c8.V:fI\u0006aA-[:l+N,Gm\u0018\u0013fcR!\u0011QEAS\u0011%\ti\u0003IA\u0001\u0002\u0004\ti!A\u0005eSN\\Wk]3eA\u0005AQ.\u0019=UCN\\7/\u0001\u0007nCb$\u0016m]6t?\u0012*\u0017\u000f\u0006\u0003\u0002&\u0005=\u0006\"CA\u0017G\u0005\u0005\t\u0019AA)\u0003%i\u0017\r\u001f+bg.\u001c\b%A\u0005nCblU-\\8ss\u0006iQ.\u0019=NK6|'/_0%KF$B!!\n\u0002:\"I\u0011Q\u0006\u0014\u0002\u0002\u0003\u0007\u0011QB\u0001\u000b[\u0006DX*Z7pef\u0004\u0013A\u0003;pi\u0006dG+Y:lg\u0006qAo\u001c;bYR\u000b7o[:`I\u0015\fH\u0003BA\u0013\u0003\u0007D\u0011\"!\f*\u0003\u0003\u0005\r!!\u0015\u0002\u0017Q|G/\u00197UCN\\7\u000fI\u0001\fC\u000e$\u0018N^3UCN\\7/A\bbGRLg/\u001a+bg.\u001cx\fJ3r)\u0011\t)#!4\t\u0013\u00055B&!AA\u0002\u0005E\u0013\u0001D1di&4X\rV1tWN\u0004\u0013AD2p[BdW\r^3e)\u0006\u001c8n]\u0001\u0013G>l\u0007\u000f\\3uK\u0012$\u0016m]6t?\u0012*\u0017\u000f\u0006\u0003\u0002&\u0005]\u0007\"CA\u0017_\u0005\u0005\t\u0019AA)\u0003=\u0019w.\u001c9mKR,G\rV1tWN\u0004\u0013a\u00034bS2,G\rV1tWN\fqBZ1jY\u0016$G+Y:lg~#S-\u001d\u000b\u0005\u0003K\t\t\u000fC\u0005\u0002.I\n\t\u00111\u0001\u0002R\u0005aa-Y5mK\u0012$\u0016m]6tA\u0005iAo\u001c;bY\u0012+(/\u0019;j_:\f\u0011\u0003^8uC2$UO]1uS>tw\fJ3r)\u0011\t)#a;\t\u0013\u00055R'!AA\u0002\u00055\u0011A\u0004;pi\u0006dG)\u001e:bi&|g\u000eI\u0001\fi>$\u0018\r\\$d)&lW-A\bu_R\fGnR2US6,w\fJ3r)\u0011\t)#!>\t\u0013\u00055\u0002(!AA\u0002\u00055\u0011\u0001\u0004;pi\u0006dwi\u0019+j[\u0016\u0004\u0013a\u0004;pi\u0006d\u0017J\u001c9vi\nKH/Z:\u0002'Q|G/\u00197J]B,HOQ=uKN|F%Z9\u0015\t\u0005\u0015\u0012q \u0005\n\u0003[Y\u0014\u0011!a\u0001\u0003\u001b\t\u0001\u0003^8uC2Le\u000e];u\u0005f$Xm\u001d\u0011\u0002!Q|G/\u00197TQV4g\r\\3SK\u0006$\u0017\u0001\u0006;pi\u0006d7\u000b[;gM2,'+Z1e?\u0012*\u0017\u000f\u0006\u0003\u0002&\t%\u0001\"CA\u0017}\u0005\u0005\t\u0019AA\u0007\u0003E!x\u000e^1m'\",hM\u001a7f%\u0016\fG\rI\u0001\u0012i>$\u0018\r\\*ik\u001a4G.Z,sSR,\u0017!\u0006;pi\u0006d7\u000b[;gM2,wK]5uK~#S-\u001d\u000b\u0005\u0003K\u0011\u0019\u0002C\u0005\u0002.\u0005\u000b\t\u00111\u0001\u0002\u000e\u0005\u0011Bo\u001c;bYNCWO\u001a4mK^\u0013\u0018\u000e^3!\u0003)I7/\u0012=dYV$W\rZ\u0001\u000fSN,\u0005p\u00197vI\u0016$w\fJ3r)\u0011\t)C!\b\t\u0013\u00055B)!AA\u0002\u0005}\u0012aC5t\u000bb\u001cG.\u001e3fI\u0002\n\u0001#\u001a=dYV$W\rZ%o'R\fw-Z:\u0016\u0005\t\u0015\u0002#\u0002=\u0003(\u0005E\u0013\u0002\u0002B\u0015\u0003\u000f\u00111aU3u\u0003Q)\u0007p\u00197vI\u0016$\u0017J\\*uC\u001e,7o\u0018\u0013fcR!\u0011Q\u0005B\u0018\u0011%\ticRA\u0001\u0002\u0004\u0011)#A\tfq\u000edW\u000fZ3e\u0013:\u001cF/Y4fg\u0002\nA\"\u001a=fGV$xN\u001d'pON,\"Aa\u000e\u0011\r\te\"1I<x\u001b\t\u0011YD\u0003\u0003\u0003>\t}\u0012!C5n[V$\u0018M\u00197f\u0015\r\u0011\t%`\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B#\u0005w\u00111!T1q\u0003A)\u00070Z2vi>\u0014Hj\\4t?\u0012*\u0017\u000f\u0006\u0003\u0002&\t-\u0003\"CA\u0017\u0015\u0006\u0005\t\u0019\u0001B\u001c\u00035)\u00070Z2vi>\u0014Hj\\4tA\u0005Q\u0011\r\u001e;sS\n,H/Z:\u0002\u001d\u0005$HO]5ckR,7o\u0018\u0013fcR!\u0011Q\u0005B+\u0011%\ti#TA\u0001\u0002\u0004\u00119$A\u0006biR\u0014\u0018NY;uKN\u0004\u0013!\u0003:fg>,(oY3t+\t\u0011i\u0006E\u0004\u0003:\t\rsOa\u0018\u0011\t\t\u0005$qM\u0007\u0003\u0005GR1A!\u001aj\u0003!\u0011Xm]8ve\u000e,\u0017\u0002\u0002B5\u0005G\u00121CU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:\fQB]3t_V\u00148-Z:`I\u0015\fH\u0003BA\u0013\u0005_B\u0011\"!\fQ\u0003\u0003\u0005\rA!\u0018\u0002\u0015I,7o\\;sG\u0016\u001c\b%A\u0006u_R\fGn\u00148IK\u0006\u0004\u0018a\u0004;pi\u0006dwJ\u001c%fCB|F%Z9\u0015\t\u0005\u0015\"\u0011\u0010\u0005\n\u0003[\u0019\u0016\u0011!a\u0001\u0003\u001b\tA\u0002^8uC2|e\u000eS3ba\u0002\nA\u0002^8uC2|eM\u001a%fCB\f\u0001\u0003^8uC2|eM\u001a%fCB|F%Z9\u0015\t\u0005\u0015\"1\u0011\u0005\n\u0003[1\u0016\u0011!a\u0001\u0003\u001b\tQ\u0002^8uC2|eM\u001a%fCB\u0004\u0013AC;tK\u0012|e\u000eS3ba\u0006qQo]3e\u001f:DU-\u00199`I\u0015\fH\u0003BA\u0013\u0005\u001bC\u0011\"!\fZ\u0003\u0003\u0005\r!!\u0004\u0002\u0017U\u001cX\rZ(o\u0011\u0016\f\u0007\u000fI\u0001\fkN,Gm\u00144g\u0011\u0016\f\u0007/A\bvg\u0016$wJ\u001a4IK\u0006\u0004x\fJ3r)\u0011\t)Ca&\t\u0013\u00055B,!AA\u0002\u00055\u0011\u0001D;tK\u0012|eM\u001a%fCB\u0004\u0013!\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0006)\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133`I\u0015\fH\u0003BA\u0013\u0005CC\u0011\"!\f`\u0003\u0003\u0005\r!!\u0015\u0002%I,7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE\rI\u0001\u000eQ\u0006\u001cX*Z7pefLeNZ8\u0002'A,\u0017m[#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0016\u0005\t-\u0006\u0003\u0002BW\u0005gk!Aa,\u000b\u0007\tE\u0016.\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\u0011\u0011)La,\u0003\u001f\u0015CXmY;u_JlU\r\u001e:jGN\fA\u0003]3bW\u0016CXmY;u_JlU\r\u001e:jGN\u0004\u0013\u0001\u00035pgRt\u0017-\\3\u0002\u0011\u0011|W\u000b\u001d3bi\u0016$\"Aa0\u0011\t\u0005=!\u0011Y\u0005\u0004\u0005\u0007l(aA!os\u0002"
)
public class LiveExecutor extends LiveEntity {
   private final String executorId;
   private String hostPort;
   private String host;
   private boolean isActive;
   private int totalCores;
   private final Date addTime;
   private Date removeTime;
   private String removeReason;
   private int rddBlocks;
   private long memoryUsed;
   private long diskUsed;
   private int maxTasks;
   private long maxMemory;
   private int totalTasks;
   private int activeTasks;
   private int completedTasks;
   private int failedTasks;
   private long totalDuration;
   private long totalGcTime;
   private long totalInputBytes;
   private long totalShuffleRead;
   private long totalShuffleWrite;
   private boolean isExcluded;
   private Set excludedInStages;
   private Map executorLogs;
   private Map attributes;
   private Map resources;
   private long totalOnHeap;
   private long totalOffHeap;
   private long usedOnHeap;
   private long usedOffHeap;
   private int resourceProfileId;
   private final ExecutorMetrics peakExecutorMetrics;

   public String executorId() {
      return this.executorId;
   }

   public String hostPort() {
      return this.hostPort;
   }

   public void hostPort_$eq(final String x$1) {
      this.hostPort = x$1;
   }

   public String host() {
      return this.host;
   }

   public void host_$eq(final String x$1) {
      this.host = x$1;
   }

   public boolean isActive() {
      return this.isActive;
   }

   public void isActive_$eq(final boolean x$1) {
      this.isActive = x$1;
   }

   public int totalCores() {
      return this.totalCores;
   }

   public void totalCores_$eq(final int x$1) {
      this.totalCores = x$1;
   }

   public Date addTime() {
      return this.addTime;
   }

   public Date removeTime() {
      return this.removeTime;
   }

   public void removeTime_$eq(final Date x$1) {
      this.removeTime = x$1;
   }

   public String removeReason() {
      return this.removeReason;
   }

   public void removeReason_$eq(final String x$1) {
      this.removeReason = x$1;
   }

   public int rddBlocks() {
      return this.rddBlocks;
   }

   public void rddBlocks_$eq(final int x$1) {
      this.rddBlocks = x$1;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public void memoryUsed_$eq(final long x$1) {
      this.memoryUsed = x$1;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public void diskUsed_$eq(final long x$1) {
      this.diskUsed = x$1;
   }

   public int maxTasks() {
      return this.maxTasks;
   }

   public void maxTasks_$eq(final int x$1) {
      this.maxTasks = x$1;
   }

   public long maxMemory() {
      return this.maxMemory;
   }

   public void maxMemory_$eq(final long x$1) {
      this.maxMemory = x$1;
   }

   public int totalTasks() {
      return this.totalTasks;
   }

   public void totalTasks_$eq(final int x$1) {
      this.totalTasks = x$1;
   }

   public int activeTasks() {
      return this.activeTasks;
   }

   public void activeTasks_$eq(final int x$1) {
      this.activeTasks = x$1;
   }

   public int completedTasks() {
      return this.completedTasks;
   }

   public void completedTasks_$eq(final int x$1) {
      this.completedTasks = x$1;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public void failedTasks_$eq(final int x$1) {
      this.failedTasks = x$1;
   }

   public long totalDuration() {
      return this.totalDuration;
   }

   public void totalDuration_$eq(final long x$1) {
      this.totalDuration = x$1;
   }

   public long totalGcTime() {
      return this.totalGcTime;
   }

   public void totalGcTime_$eq(final long x$1) {
      this.totalGcTime = x$1;
   }

   public long totalInputBytes() {
      return this.totalInputBytes;
   }

   public void totalInputBytes_$eq(final long x$1) {
      this.totalInputBytes = x$1;
   }

   public long totalShuffleRead() {
      return this.totalShuffleRead;
   }

   public void totalShuffleRead_$eq(final long x$1) {
      this.totalShuffleRead = x$1;
   }

   public long totalShuffleWrite() {
      return this.totalShuffleWrite;
   }

   public void totalShuffleWrite_$eq(final long x$1) {
      this.totalShuffleWrite = x$1;
   }

   public boolean isExcluded() {
      return this.isExcluded;
   }

   public void isExcluded_$eq(final boolean x$1) {
      this.isExcluded = x$1;
   }

   public Set excludedInStages() {
      return this.excludedInStages;
   }

   public void excludedInStages_$eq(final Set x$1) {
      this.excludedInStages = x$1;
   }

   public Map executorLogs() {
      return this.executorLogs;
   }

   public void executorLogs_$eq(final Map x$1) {
      this.executorLogs = x$1;
   }

   public Map attributes() {
      return this.attributes;
   }

   public void attributes_$eq(final Map x$1) {
      this.attributes = x$1;
   }

   public Map resources() {
      return this.resources;
   }

   public void resources_$eq(final Map x$1) {
      this.resources = x$1;
   }

   public long totalOnHeap() {
      return this.totalOnHeap;
   }

   public void totalOnHeap_$eq(final long x$1) {
      this.totalOnHeap = x$1;
   }

   public long totalOffHeap() {
      return this.totalOffHeap;
   }

   public void totalOffHeap_$eq(final long x$1) {
      this.totalOffHeap = x$1;
   }

   public long usedOnHeap() {
      return this.usedOnHeap;
   }

   public void usedOnHeap_$eq(final long x$1) {
      this.usedOnHeap = x$1;
   }

   public long usedOffHeap() {
      return this.usedOffHeap;
   }

   public void usedOffHeap_$eq(final long x$1) {
      this.usedOffHeap = x$1;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public void resourceProfileId_$eq(final int x$1) {
      this.resourceProfileId = x$1;
   }

   public boolean hasMemoryInfo() {
      return this.totalOnHeap() >= 0L;
   }

   public ExecutorMetrics peakExecutorMetrics() {
      return this.peakExecutorMetrics;
   }

   public String hostname() {
      return this.host() != null ? this.host() : (String)Utils$.MODULE$.parseHostPort(this.hostPort())._1();
   }

   public Object doUpdate() {
      Option memoryMetrics = (Option)(this.totalOnHeap() >= 0L ? new Some(new MemoryMetrics(this.usedOnHeap(), this.usedOffHeap(), this.totalOnHeap(), this.totalOffHeap())) : .MODULE$);
      ExecutorSummary info = new ExecutorSummary(this.executorId(), this.hostPort() != null ? this.hostPort() : this.host(), this.isActive(), this.rddBlocks(), this.memoryUsed(), this.diskUsed(), this.totalCores(), this.maxTasks(), this.activeTasks(), this.failedTasks(), this.completedTasks(), this.totalTasks(), this.totalDuration(), this.totalGcTime(), this.totalInputBytes(), this.totalShuffleRead(), this.totalShuffleWrite(), this.isExcluded(), this.maxMemory(), this.addTime(), scala.Option..MODULE$.apply(this.removeTime()), scala.Option..MODULE$.apply(this.removeReason()), this.executorLogs(), memoryMetrics, this.excludedInStages(), (new Some(this.peakExecutorMetrics())).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$doUpdate$2(x$1))), this.attributes(), this.resources(), this.resourceProfileId(), this.isExcluded(), this.excludedInStages());
      return new ExecutorSummaryWrapper(info);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doUpdate$2(final ExecutorMetrics x$1) {
      return x$1.isSet();
   }

   public LiveExecutor(final String executorId, final long _addTime) {
      this.executorId = executorId;
      this.hostPort = null;
      this.host = null;
      this.isActive = true;
      this.totalCores = 0;
      this.addTime = new Date(_addTime);
      this.removeTime = null;
      this.removeReason = null;
      this.rddBlocks = 0;
      this.memoryUsed = 0L;
      this.diskUsed = 0L;
      this.maxTasks = 0;
      this.maxMemory = 0L;
      this.totalTasks = 0;
      this.activeTasks = 0;
      this.completedTasks = 0;
      this.failedTasks = 0;
      this.totalDuration = 0L;
      this.totalGcTime = 0L;
      this.totalInputBytes = 0L;
      this.totalShuffleRead = 0L;
      this.totalShuffleWrite = 0L;
      this.isExcluded = false;
      this.excludedInStages = (Set)scala.collection.immutable.TreeSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.math.Ordering.Int..MODULE$);
      this.executorLogs = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.attributes = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.resources = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.totalOnHeap = -1L;
      this.totalOffHeap = 0L;
      this.usedOnHeap = 0L;
      this.usedOffHeap = 0L;
      this.resourceProfileId = ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
      this.peakExecutorMetrics = new ExecutorMetrics();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
