package org.apache.spark.graphx;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.graphx.impl.ShippableVertexPartition;
import org.apache.spark.graphx.impl.ShippableVertexPartition$;
import org.apache.spark.graphx.impl.VertexPartitionBase;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\rmg!B\u0013'\u0003\u0003y\u0003\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B)\t\u0011U\u0003!\u0011!Q\u0001\nYCQ\u0001\u001b\u0001\u0005\u0002%DQA\u001d\u0001\u0007\u0014MDaA\u001f\u0001\u0007\u0002\u0019Z\bbBA\u0004\u0001\u0011E\u0013\u0011\u0002\u0005\b\u0003/\u0001A\u0011IA\r\u0011\u001d\ty\u0003\u0001D\u0001\u0003cA\u0001\"a\r\u0001\r\u00031\u0013Q\u0007\u0005\b\u0003+\u0002A\u0011IA,\u0011\u001d\t)\u0007\u0001D\u0001\u0003OBq!!\u001a\u0001\r\u0003\ti\bC\u0004\u0002\u0018\u00021\t!!'\t\u000f\u0005]\u0005A\"\u0001\u0002 \"9\u00111\u0015\u0001\u0007\u0002\u0005\u0015\u0006bBAR\u0001\u0019\u0005\u0011\u0011\u0016\u0005\b\u0003[\u0003a\u0011AAX\u0011\u001d\t\t\u000f\u0001D\u0001\u0003GDqA!\u0004\u0001\r\u0003\u0011y\u0001C\u0004\u00038\u00011\tA!\u000f\t\u000f\t\u0005\u0004A\"\u0001\u0003d!9!1\u0011\u0001\u0007\u0002\u0005E\u0002b\u0002BC\u0001\u0019\u0005!q\u0011\u0005\t\u00057\u0003a\u0011\u0001\u0014\u0003\u001e\"A!Q\u0017\u0001\u0007\u0002\u0019\u00129\f\u0003\u0005\u0003J\u00021\tA\nBf\u0011!\u0011)\u000f\u0001D\u0001M\t\u001dxa\u0002BxM!\u0005!\u0011\u001f\u0004\u0007K\u0019B\tAa=\t\r!lB\u0011AB\u0006\u0011\u001d\u0019i!\bC\u0001\u0007\u001fAqa!\u0004\u001e\t\u0003\u0019I\u0003C\u0004\u0004\u000eu!\ta!\u0015\t\u000f\ruT\u0004\"\u0001\u0004\u0000!A1\u0011V\u000f\u0005\u0002\u0019\u001aY\u000bC\u0005\u0004Lv\t\t\u0011\"\u0003\u0004N\nIa+\u001a:uKb\u0014F\t\u0012\u0006\u0003O!\naa\u001a:ba\"D(BA\u0015+\u0003\u0015\u0019\b/\u0019:l\u0015\tYC&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002[\u0005\u0019qN]4\u0004\u0001U\u0011\u0001gR\n\u0003\u0001E\u00022AM\u001b8\u001b\u0005\u0019$B\u0001\u001b)\u0003\r\u0011H\rZ\u0005\u0003mM\u00121A\u0015#E!\u0011A4(P#\u000e\u0003eR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\u0012a\u0001V;qY\u0016\u0014\u0004C\u0001 C\u001d\ty\u0004)D\u0001'\u0013\t\te%A\u0004qC\u000e\\\u0017mZ3\n\u0005\r#%\u0001\u0003,feR,\u00070\u00133\u000b\u0005\u00053\u0003C\u0001$H\u0019\u0001!Q\u0001\u0013\u0001C\u0002%\u0013!A\u0016#\u0012\u0005)k\u0005C\u0001\u001dL\u0013\ta\u0015HA\u0004O_RD\u0017N\\4\u0011\u0005ar\u0015BA(:\u0005\r\te._\u0001\u0003g\u000e\u0004\"AU*\u000e\u0003!J!\u0001\u0016\u0015\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\t\u0011,\u0007o\u001d\t\u0004/z\u000bgB\u0001-^\u001d\tIF,D\u0001[\u0015\tYf&\u0001\u0004=e>|GOP\u0005\u0002u%\u0011\u0011)O\u0005\u0003?\u0002\u00141aU3r\u0015\t\t\u0015\b\r\u0002cMB\u0019!kY3\n\u0005\u0011D#A\u0003#fa\u0016tG-\u001a8dsB\u0011aI\u001a\u0003\nO\n\t\t\u0011!A\u0003\u0002%\u00131a\u0018\u00132\u0003\u0019a\u0014N\\5u}Q\u0019!n\u001b7\u0011\u0007}\u0002Q\tC\u0003Q\u0007\u0001\u0007\u0011\u000bC\u0003V\u0007\u0001\u0007Q\u000eE\u0002X=:\u0004$a\\9\u0011\u0007I\u001b\u0007\u000f\u0005\u0002Gc\u0012Iq\r\\A\u0001\u0002\u0003\u0015\t!S\u0001\u0006m\u0012$\u0016mZ\u000b\u0002iB\u0019Q\u000f_#\u000e\u0003YT!a^\u001d\u0002\u000fI,g\r\\3di&\u0011\u0011P\u001e\u0002\t\u00072\f7o\u001d+bO\u0006i\u0001/\u0019:uSRLwN\\:S\t\u0012+\u0012\u0001 \t\u0004eUj\b\u0003\u0002@\u0002\u0004\u0015k\u0011a \u0006\u0004\u0003\u00031\u0013\u0001B5na2L1!!\u0002\u0000\u0005a\u0019\u0006.\u001b9qC\ndWMV3si\u0016D\b+\u0019:uSRLwN\\\u0001\u000eO\u0016$\b+\u0019:uSRLwN\\:\u0016\u0005\u0005-\u0001#\u0002\u001d\u0002\u000e\u0005E\u0011bAA\bs\t)\u0011I\u001d:bsB\u0019!+a\u0005\n\u0007\u0005U\u0001FA\u0005QCJ$\u0018\u000e^5p]\u000691m\\7qkR,GCBA\u000e\u0003C\t)\u0003\u0005\u0003X\u0003;9\u0014bAA\u0010A\nA\u0011\n^3sCR|'\u000fC\u0004\u0002$\u001d\u0001\r!!\u0005\u0002\tA\f'\u000f\u001e\u0005\b\u0003O9\u0001\u0019AA\u0015\u0003\u001d\u0019wN\u001c;fqR\u00042AUA\u0016\u0013\r\ti\u0003\u000b\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u0004sK&tG-\u001a=\u0015\u0003)\f1#\\1q-\u0016\u0014H/\u001a=QCJ$\u0018\u000e^5p]N,B!a\u000e\u0002@Q!\u0011\u0011HA%)\u0011\tY$a\u0011\u0011\t}\u0002\u0011Q\b\t\u0004\r\u0006}BABA!\u0013\t\u0007\u0011JA\u0002W\tJB\u0011\"!\u0012\n\u0003\u0003\u0005\u001d!a\u0012\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003vq\u0006u\u0002bBA&\u0013\u0001\u0007\u0011QJ\u0001\u0002MB1\u0001(a\u0014~\u0003'J1!!\u0015:\u0005%1UO\\2uS>t\u0017\u0007E\u0003\u007f\u0003\u0007\ti$\u0001\u0004gS2$XM\u001d\u000b\u0004U\u0006e\u0003bBA.\u0015\u0001\u0007\u0011QL\u0001\u0005aJ,G\r\u0005\u00049\u0003\u001f:\u0014q\f\t\u0004q\u0005\u0005\u0014bAA2s\t9!i\\8mK\u0006t\u0017!C7baZ\u000bG.^3t+\u0011\tI'!\u001d\u0015\t\u0005-\u0014\u0011\u0010\u000b\u0005\u0003[\n\u0019\b\u0005\u0003@\u0001\u0005=\u0004c\u0001$\u0002r\u00111\u0011\u0011I\u0006C\u0002%C\u0011\"!\u001e\f\u0003\u0003\u0005\u001d!a\u001e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0003vq\u0006=\u0004bBA&\u0017\u0001\u0007\u00111\u0010\t\u0007q\u0005=S)a\u001c\u0016\t\u0005}\u0014q\u0011\u000b\u0005\u0003\u0003\u000by\t\u0006\u0003\u0002\u0004\u0006%\u0005\u0003B \u0001\u0003\u000b\u00032ARAD\t\u0019\t\t\u0005\u0004b\u0001\u0013\"I\u00111\u0012\u0007\u0002\u0002\u0003\u000f\u0011QR\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B;y\u0003\u000bCq!a\u0013\r\u0001\u0004\t\t\nE\u00049\u0003'kT)!\"\n\u0007\u0005U\u0015HA\u0005Gk:\u001cG/[8oe\u0005)Q.\u001b8vgR\u0019!.a'\t\r\u0005uU\u00021\u00012\u0003\u0015yG\u000f[3s)\rQ\u0017\u0011\u0015\u0005\u0007\u0003;s\u0001\u0019\u00016\u0002\t\u0011LgM\u001a\u000b\u0004U\u0006\u001d\u0006BBAO\u001f\u0001\u0007\u0011\u0007F\u0002k\u0003WCa!!(\u0011\u0001\u0004Q\u0017a\u00037fMRT\u0016\u000e\u001d&pS:,b!!-\u0002H\u0006mF\u0003BAZ\u0003;$B!!.\u0002PR1\u0011qWA`\u0003\u0013\u0004Ba\u0010\u0001\u0002:B\u0019a)a/\u0005\r\u0005u\u0016C1\u0001J\u0005\r1Fi\r\u0005\n\u0003\u0003\f\u0012\u0011!a\u0002\u0003\u0007\f!\"\u001a<jI\u0016t7-\u001a\u00135!\u0011)\b0!2\u0011\u0007\u0019\u000b9\r\u0002\u0004\u0002BE\u0011\r!\u0013\u0005\n\u0003\u0017\f\u0012\u0011!a\u0002\u0003\u001b\f!\"\u001a<jI\u0016t7-\u001a\u00136!\u0011)\b0!/\t\u000f\u0005-\u0013\u00031\u0001\u0002RBI\u0001(a5>\u000b\u0006]\u0017\u0011X\u0005\u0004\u0003+L$!\u0003$v]\u000e$\u0018n\u001c84!\u0015A\u0014\u0011\\Ac\u0013\r\tY.\u000f\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005u\u0015\u00031\u0001\u0002`B!q\bAAc\u0003!aWM\u001a;K_&tWCBAs\u0003s\fy\u000f\u0006\u0003\u0002h\n\u001dA\u0003BAu\u0005\u0003!b!a;\u0002r\u0006m\b\u0003B \u0001\u0003[\u00042ARAx\t\u0019\tiL\u0005b\u0001\u0013\"I\u00111\u001f\n\u0002\u0002\u0003\u000f\u0011Q_\u0001\u000bKZLG-\u001a8dK\u00122\u0004\u0003B;y\u0003o\u00042ARA}\t\u0019\t\tE\u0005b\u0001\u0013\"I\u0011Q \n\u0002\u0002\u0003\u000f\u0011q`\u0001\u000bKZLG-\u001a8dK\u0012:\u0004\u0003B;y\u0003[Dq!a\u0013\u0013\u0001\u0004\u0011\u0019\u0001E\u00059\u0003'lTI!\u0002\u0002nB)\u0001(!7\u0002x\"9\u0011Q\u0014\nA\u0002\t%\u0001\u0003\u0002\u001a6\u0005\u0017\u0001R\u0001O\u001e>\u0003o\fA\"\u001b8oKJT\u0016\u000e\u001d&pS:,bA!\u0005\u0003&\tmA\u0003\u0002B\n\u0005g!BA!\u0006\u00030Q1!q\u0003B\u000f\u0005S\u0001Ba\u0010\u0001\u0003\u001aA\u0019aIa\u0007\u0005\r\u0005\u00053C1\u0001J\u0011%\u0011ybEA\u0001\u0002\b\u0011\t#\u0001\u0006fm&$WM\\2fIa\u0002B!\u001e=\u0003$A\u0019aI!\n\u0005\r\t\u001d2C1\u0001J\u0005\u0005)\u0006\"\u0003B\u0016'\u0005\u0005\t9\u0001B\u0017\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0005kb\u0014I\u0002C\u0004\u0002LM\u0001\rA!\r\u0011\u0013a\n\u0019.P#\u0003$\te\u0001bBAO'\u0001\u0007!Q\u0007\t\u0005\u007f\u0001\u0011\u0019#A\u0005j]:,'OS8j]V1!1\bB(\u0005\u000b\"BA!\u0010\u0003\\Q!!q\bB,)\u0019\u0011\tEa\u0012\u0003RA!q\b\u0001B\"!\r1%Q\t\u0003\u0007\u0003\u0003\"\"\u0019A%\t\u0013\t%C#!AA\u0004\t-\u0013aC3wS\u0012,gnY3%cA\u0002B!\u001e=\u0003NA\u0019aIa\u0014\u0005\r\t\u001dBC1\u0001J\u0011%\u0011\u0019\u0006FA\u0001\u0002\b\u0011)&A\u0006fm&$WM\\2fIE\n\u0004\u0003B;y\u0005\u0007Bq!a\u0013\u0015\u0001\u0004\u0011I\u0006E\u00059\u0003'lTI!\u0014\u0003D!9\u0011Q\u0014\u000bA\u0002\tu\u0003\u0003\u0002\u001a6\u0005?\u0002R\u0001O\u001e>\u0005\u001b\n1#Y4he\u0016<\u0017\r^3Vg&tw-\u00138eKb,BA!\u001a\u0003nQ1!q\rB;\u0005{\"BA!\u001b\u0003pA!q\b\u0001B6!\r1%Q\u000e\u0003\u0007\u0003\u0003*\"\u0019A%\t\u0013\tET#!AA\u0004\tM\u0014aC3wS\u0012,gnY3%cI\u0002B!\u001e=\u0003l!9!qO\u000bA\u0002\te\u0014\u0001C7fgN\fw-Z:\u0011\tI*$1\u0010\t\u0006qmj$1\u000e\u0005\b\u0005\u007f*\u0002\u0019\u0001BA\u0003)\u0011X\rZ;dK\u001a+hn\u0019\t\nq\u0005M%1\u000eB6\u0005W\nAC]3wKJ\u001cXMU8vi&tw\rV1cY\u0016\u001c\u0018!C<ji\",EmZ3t)\rQ'\u0011\u0012\u0005\b\u0005\u0017;\u0002\u0019\u0001BG\u0003\u0015)GmZ3ta\u0011\u0011yIa&\u0011\u000b}\u0012\tJ!&\n\u0007\tMeEA\u0004FI\u001e,'\u000b\u0012#\u0011\u0007\u0019\u00139\nB\u0006\u0003\u001a\n%\u0015\u0011!A\u0001\u0006\u0003I%aA0%e\u0005\tr/\u001b;i!\u0006\u0014H/\u001b;j_:\u001c(\u000b\u0012#\u0016\t\t}%q\u0015\u000b\u0005\u0005C\u0013y\u000b\u0006\u0003\u0003$\n%\u0006\u0003B \u0001\u0005K\u00032A\u0012BT\t\u0019\t\t\u0005\u0007b\u0001\u0013\"I!1\u0016\r\u0002\u0002\u0003\u000f!QV\u0001\fKZLG-\u001a8dK\u0012\n4\u0007\u0005\u0003vq\n\u0015\u0006B\u0002>\u0019\u0001\u0004\u0011\t\f\u0005\u00033k\tM\u0006#\u0002@\u0002\u0004\t\u0015\u0016AF<ji\"$\u0016M]4fiN#xN]1hK2+g/\u001a7\u0015\u0007)\u0014I\fC\u0004\u0003<f\u0001\rA!0\u0002%Q\f'oZ3u'R|'/Y4f\u0019\u00164X\r\u001c\t\u0005\u0005\u007f\u0013)-\u0004\u0002\u0003B*\u0019!1\u0019\u0015\u0002\u000fM$xN]1hK&!!q\u0019Ba\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003Q\u0019\b.\u001b9WKJ$X\r_!uiJL'-\u001e;fgR1!Q\u001aBo\u0005C\u0004BAM\u001b\u0003PB1\u0001h\u000fBi\u0005/\u00042A\u0010Bj\u0013\r\u0011)\u000e\u0012\u0002\f!\u0006\u0014H/\u001b;j_:LE\t\u0005\u0003\u007f\u00053,\u0015b\u0001Bn\u007f\n!b+\u001a:uKb\fE\u000f\u001e:jEV$XM\u00117pG.DqAa8\u001b\u0001\u0004\ty&A\u0004tQ&\u00048K]2\t\u000f\t\r(\u00041\u0001\u0002`\u000591\u000f[5q\tN$\u0018!D:iSB4VM\u001d;fq&#7\u000f\u0006\u0002\u0003jB!!'\u000eBv!\u0019A4H!5\u0003nB!\u0001(!\u0004>\u0003%1VM\u001d;fqJ#E\t\u0005\u0002@;M)QD!>\u0003|B\u0019\u0001Ha>\n\u0007\te\u0018H\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0005{\u001c9!\u0004\u0002\u0003\u0000*!1\u0011AB\u0002\u0003\tIwN\u0003\u0002\u0004\u0006\u0005!!.\u0019<b\u0013\u0011\u0019IAa@\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\tE\u0018!B1qa2LX\u0003BB\t\u00073!Baa\u0005\u0004\"Q!1QCB\u000e!\u0011y\u0004aa\u0006\u0011\u0007\u0019\u001bI\u0002B\u0003I?\t\u0007\u0011\nC\u0005\u0004\u001e}\t\t\u0011q\u0001\u0004 \u0005YQM^5eK:\u001cW\rJ\u00195!\u0011)\bpa\u0006\t\u000f\r\rr\u00041\u0001\u0004&\u0005Aa/\u001a:uS\u000e,7\u000f\u0005\u00033k\r\u001d\u0002#\u0002\u001d<{\r]Q\u0003BB\u0016\u0007g!\u0002b!\f\u0004<\r\u00053Q\n\u000b\u0005\u0007_\u0019)\u0004\u0005\u0003@\u0001\rE\u0002c\u0001$\u00044\u0011)\u0001\n\tb\u0001\u0013\"I1q\u0007\u0011\u0002\u0002\u0003\u000f1\u0011H\u0001\fKZLG-\u001a8dK\u0012\nT\u0007\u0005\u0003vq\u000eE\u0002bBB\u0012A\u0001\u00071Q\b\t\u0005eU\u001ay\u0004E\u00039wu\u001a\t\u0004C\u0004\u0003\f\u0002\u0002\raa\u00111\t\r\u00153\u0011\n\t\u0006\u007f\tE5q\t\t\u0004\r\u000e%CaCB&\u0007\u0003\n\t\u0011!A\u0003\u0002%\u00131a\u0018\u00134\u0011\u001d\u0019y\u0005\ta\u0001\u0007c\t!\u0002Z3gCVdGOV1m+\u0011\u0019\u0019fa\u0017\u0015\u0015\rU31MB5\u0007k\u001a9\b\u0006\u0003\u0004X\ru\u0003\u0003B \u0001\u00073\u00022ARB.\t\u0015A\u0015E1\u0001J\u0011%\u0019y&IA\u0001\u0002\b\u0019\t'A\u0006fm&$WM\\2fIE2\u0004\u0003B;y\u00073Bqaa\t\"\u0001\u0004\u0019)\u0007\u0005\u00033k\r\u001d\u0004#\u0002\u001d<{\re\u0003b\u0002BFC\u0001\u000711\u000e\u0019\u0005\u0007[\u001a\t\bE\u0003@\u0005#\u001by\u0007E\u0002G\u0007c\"1ba\u001d\u0004j\u0005\u0005\t\u0011!B\u0001\u0013\n\u0019q\f\n\u001b\t\u000f\r=\u0013\u00051\u0001\u0004Z!91\u0011P\u0011A\u0002\rm\u0014!C7fe\u001e,g)\u001e8d!%A\u00141SB-\u00073\u001aI&A\u0005ge>lW\tZ4fgV!1\u0011QBE)!\u0019\u0019i!%\u0004\u001e\u000e\u001dF\u0003BBC\u0007\u0017\u0003Ba\u0010\u0001\u0004\bB\u0019ai!#\u0005\u000b!\u0013#\u0019A%\t\u0013\r5%%!AA\u0004\r=\u0015aC3wS\u0012,gnY3%c]\u0002B!\u001e=\u0004\b\"9!1\u0012\u0012A\u0002\rM\u0005\u0007BBK\u00073\u0003Ra\u0010BI\u0007/\u00032ARBM\t-\u0019Yj!%\u0002\u0002\u0003\u0005)\u0011A%\u0003\u0007}#S\u0007C\u0004\u0004 \n\u0002\ra!)\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\rA41U\u0005\u0004\u0007KK$aA%oi\"91q\n\u0012A\u0002\r\u001d\u0015aE2sK\u0006$XMU8vi&tw\rV1cY\u0016\u001cHCBBW\u0007k\u001b\t\r\u0005\u00033k\r=\u0006c\u0001@\u00042&\u001911W@\u0003+I{W\u000f^5oOR\u000b'\r\\3QCJ$\u0018\u000e^5p]\"9!1R\u0012A\u0002\r]\u0006\u0007BB]\u0007{\u0003Ra\u0010BI\u0007w\u00032ARB_\t-\u0019yl!.\u0002\u0002\u0003\u0005)\u0011A%\u0003\u0007}#c\u0007C\u0004\u0004D\u000e\u0002\ra!2\u0002#Y,'\u000f^3y!\u0006\u0014H/\u001b;j_:,'\u000fE\u0002S\u0007\u000fL1a!3)\u0005-\u0001\u0016M\u001d;ji&|g.\u001a:\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r=\u0007\u0003BBi\u0007/l!aa5\u000b\t\rU71A\u0001\u0005Y\u0006tw-\u0003\u0003\u0004Z\u000eM'AB(cU\u0016\u001cG\u000f"
)
public abstract class VertexRDD extends RDD {
   public static VertexRDD fromEdges(final EdgeRDD edges, final int numPartitions, final Object defaultVal, final ClassTag evidence$17) {
      return VertexRDD$.MODULE$.fromEdges(edges, numPartitions, defaultVal, evidence$17);
   }

   public static VertexRDD apply(final RDD vertices, final EdgeRDD edges, final Object defaultVal, final Function2 mergeFunc, final ClassTag evidence$16) {
      return VertexRDD$.MODULE$.apply(vertices, edges, defaultVal, mergeFunc, evidence$16);
   }

   public static VertexRDD apply(final RDD vertices, final EdgeRDD edges, final Object defaultVal, final ClassTag evidence$15) {
      return VertexRDD$.MODULE$.apply(vertices, edges, defaultVal, evidence$15);
   }

   public static VertexRDD apply(final RDD vertices, final ClassTag evidence$14) {
      return VertexRDD$.MODULE$.apply(vertices, evidence$14);
   }

   public abstract ClassTag vdTag();

   public abstract RDD partitionsRDD();

   public Partition[] getPartitions() {
      return this.partitionsRDD().partitions();
   }

   public Iterator compute(final Partition part, final TaskContext context) {
      return ((VertexPartitionBase)this.firstParent(.MODULE$.apply(ShippableVertexPartition.class)).iterator(part, context).next()).iterator();
   }

   public abstract VertexRDD reindex();

   public abstract VertexRDD mapVertexPartitions(final Function1 f, final ClassTag evidence$1);

   public VertexRDD filter(final Function1 pred) {
      return this.mapVertexPartitions((x$1) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$1, this.vdTag()).filter(scala.Function..MODULE$.untupled(pred)), this.vdTag());
   }

   public abstract VertexRDD mapValues(final Function1 f, final ClassTag evidence$2);

   public abstract VertexRDD mapValues(final Function2 f, final ClassTag evidence$3);

   public abstract VertexRDD minus(final RDD other);

   public abstract VertexRDD minus(final VertexRDD other);

   public abstract VertexRDD diff(final RDD other);

   public abstract VertexRDD diff(final VertexRDD other);

   public abstract VertexRDD leftZipJoin(final VertexRDD other, final Function3 f, final ClassTag evidence$4, final ClassTag evidence$5);

   public abstract VertexRDD leftJoin(final RDD other, final Function3 f, final ClassTag evidence$6, final ClassTag evidence$7);

   public abstract VertexRDD innerZipJoin(final VertexRDD other, final Function3 f, final ClassTag evidence$8, final ClassTag evidence$9);

   public abstract VertexRDD innerJoin(final RDD other, final Function3 f, final ClassTag evidence$10, final ClassTag evidence$11);

   public abstract VertexRDD aggregateUsingIndex(final RDD messages, final Function2 reduceFunc, final ClassTag evidence$12);

   public abstract VertexRDD reverseRoutingTables();

   public abstract VertexRDD withEdges(final EdgeRDD edges);

   public abstract VertexRDD withPartitionsRDD(final RDD partitionsRDD, final ClassTag evidence$13);

   public abstract VertexRDD withTargetStorageLevel(final StorageLevel targetStorageLevel);

   public abstract RDD shipVertexAttributes(final boolean shipSrc, final boolean shipDst);

   public abstract RDD shipVertexIds();

   public VertexRDD(final SparkContext sc, final Seq deps) {
      super(sc, deps, .MODULE$.apply(Tuple2.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
