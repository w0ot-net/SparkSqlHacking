package org.apache.spark.sql.hive.client;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.LinkedHashMap;
import org.apache.spark.sql.catalyst.analysis.NoSuchPartitionException;
import org.apache.spark.sql.catalyst.analysis.NoSuchPermanentFunctionException;
import org.apache.spark.sql.catalyst.analysis.NoSuchTableException;
import org.apache.spark.sql.catalyst.catalog.CatalogDatabase;
import org.apache.spark.sql.catalyst.catalog.CatalogFunction;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.types.StructType;
import scala.Function0;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r]g\u0001\u0003\u001e<!\u0003\r\t!P$\t\u000b9\u0003A\u0011\u0001)\t\u000bQ\u0003a\u0011A+\t\u000by\u0003a\u0011A0\t\u000b=\u0004a\u0011\u00019\t\u000bQ\u0004a\u0011A;\t\r}\u0004a\u0011AA\u0001\u0011\u001d\t9\u0002\u0001D\u0001\u00033Aq!!\b\u0001\r\u0003\ty\u0002C\u0004\u0002$\u00011\t!!\n\t\u000f\u0005\r\u0002A\"\u0001\u0002,!9\u00111\u0007\u0001\u0007\u0002\u0005U\u0002bBA(\u0001\u0019\u0005\u0011\u0011\u000b\u0005\b\u0003/\u0002a\u0011AA-\u0011\u001d\t)\u0007\u0001D\u0001\u0003OBq!!\u001d\u0001\r\u0003\t\u0019\bC\u0004\u0002x\u00011\t!!\u001f\t\u000f\u0005\u0005\u0005\u0001\"\u0002\u0002\u0004\"9\u0011q\u0012\u0001\u0007\u0002\u0005E\u0005bBAO\u0001\u0011\u0015\u0011q\u0014\u0005\b\u0003W\u0003a\u0011AAW\u0011\u001d\t)\f\u0001D\u0001\u0003oCq!!1\u0001\r\u0003\t\u0019\rC\u0004\u0002N\u00021\t!a4\t\u000f\u0005u\u0007\u0001\"\u0002\u0002`\"9\u0011Q\u001c\u0001\u0007\u0002\u0005\r\bbBAv\u0001\u0019\u0005\u0011Q\u001e\u0005\b\u0003{\u0004a\u0011AA\u0000\u0011\u001d\u0011I\u0002\u0001D\u0001\u00057AqAa\t\u0001\r\u0003\u0011)\u0003C\u0004\u00030\u00011\tA!\r\t\u000f\tU\u0002A\"\u0001\u00038!9!\u0011\n\u0001\u0007\u0002\t-\u0003b\u0002BD\u0001\u0019\u0005!\u0011\u0012\u0005\b\u0005+\u0003a\u0011\u0001BL\u0011\u001d\u0011\t\u000b\u0001C\u0003\u0005GCqA!,\u0001\r\u0003\u0011y\u000bC\u0005\u0003:\u0002\t\n\u0011\"\u0001\u0003<\"9!\u0011\u001b\u0001\u0005\u0006\tM\u0007b\u0002Bi\u0001\u0019\u0005!Q\u001c\u0005\b\u0005G\u0004a\u0011\u0001Bs\u0011\u001d\u0011i\u000f\u0001D\u0001\u0005_Dqaa\u0002\u0001\r\u0003\u0019I\u0001C\u0004\u00040\u00011\ta!\r\t\u000f\rm\u0002A\"\u0001\u0004>!911\u000b\u0001\u0007\u0002\rU\u0003bBB2\u0001\u0019\u00051Q\r\u0005\b\u0007W\u0002a\u0011AB7\u0011\u001d\u0019I\b\u0001D\u0001\u0007wBqa!!\u0001\t\u000b\u0019\u0019\tC\u0004\u0004\n\u00021\taa#\t\u000f\rM\u0005\u0001\"\u0002\u0004\u0016\"911\u0014\u0001\u0007\u0002\ru\u0005bBBR\u0001\u0019\u00051Q\u0015\u0005\b\u0007W\u0003a\u0011ABW\u0011\u001d\u0019\t\f\u0001D\u0001\u0007gCaa!5\u0001\r\u0003\u0001\u0006bBBj\u0001\u0019\u00051Q\u001b\u0002\u000b\u0011&4Xm\u00117jK:$(B\u0001\u001f>\u0003\u0019\u0019G.[3oi*\u0011ahP\u0001\u0005Q&4XM\u0003\u0002A\u0003\u0006\u00191/\u001d7\u000b\u0005\t\u001b\u0015!B:qCJ\\'B\u0001#F\u0003\u0019\t\u0007/Y2iK*\ta)A\u0002pe\u001e\u001c\"\u0001\u0001%\u0011\u0005%cU\"\u0001&\u000b\u0003-\u000bQa]2bY\u0006L!!\u0014&\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A)\u0011\u0005%\u0013\u0016BA*K\u0005\u0011)f.\u001b;\u0002\u000fY,'o]5p]V\ta\u000b\u0005\u0002X7:\u0011\u0001,W\u0007\u0002w%\u0011!lO\u0001\ba\u0006\u001c7.Y4f\u0013\taVLA\u0006ISZ,g+\u001a:tS>t'B\u0001.<\u0003\u001d9W\r^\"p]\u001a$2\u0001Y6n!\t\t\u0007N\u0004\u0002cMB\u00111MS\u0007\u0002I*\u0011QmT\u0001\u0007yI|w\u000e\u001e \n\u0005\u001dT\u0015A\u0002)sK\u0012,g-\u0003\u0002jU\n11\u000b\u001e:j]\u001eT!a\u001a&\t\u000b1\u001c\u0001\u0019\u00011\u0002\u0007-,\u0017\u0010C\u0003o\u0007\u0001\u0007\u0001-\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X-\u0001\u0005hKR\u001cF/\u0019;f+\u0005\t\bCA%s\u0013\t\u0019(JA\u0002B]f\f!B];o'Fd\u0007*\u001b<f)\t1h\u0010E\u0002xw\u0002t!\u0001\u001f>\u000f\u0005\rL\u0018\"A&\n\u0005iS\u0015B\u0001?~\u0005\r\u0019V-\u001d\u0006\u00035*CQ\u0001Q\u0003A\u0002\u0001\faa]3u\u001fV$HcA)\u0002\u0004!9\u0011Q\u0001\u0004A\u0002\u0005\u001d\u0011AB:ue\u0016\fW\u000e\u0005\u0003\u0002\n\u0005MQBAA\u0006\u0015\u0011\ti!a\u0004\u0002\u0005%|'BAA\t\u0003\u0011Q\u0017M^1\n\t\u0005U\u00111\u0002\u0002\f!JLg\u000e^*ue\u0016\fW.A\u0004tKRLeNZ8\u0015\u0007E\u000bY\u0002C\u0004\u0002\u0006\u001d\u0001\r!a\u0002\u0002\u0011M,G/\u0012:s_J$2!UA\u0011\u0011\u001d\t)\u0001\u0003a\u0001\u0003\u000f\t!\u0002\\5tiR\u000b'\r\\3t)\r1\u0018q\u0005\u0005\u0007\u0003SI\u0001\u0019\u00011\u0002\r\u0011\u0014g*Y7f)\u00151\u0018QFA\u0018\u0011\u0019\tIC\u0003a\u0001A\"1\u0011\u0011\u0007\u0006A\u0002\u0001\fq\u0001]1ui\u0016\u0014h.\u0001\tmSN$H+\u00192mKN\u0014\u0015\u0010V=qKR9a/a\u000e\u0002:\u0005m\u0002BBA\u0015\u0017\u0001\u0007\u0001\r\u0003\u0004\u00022-\u0001\r\u0001\u0019\u0005\b\u0003{Y\u0001\u0019AA \u0003%!\u0018M\u00197f)f\u0004X\r\u0005\u0003\u0002B\u0005-SBAA\"\u0015\u0011\t)%a\u0012\u0002\u000f\r\fG/\u00197pO*\u0019\u0011\u0011J \u0002\u0011\r\fG/\u00197zgRLA!!\u0014\u0002D\t\u00012)\u0019;bY><G+\u00192mKRK\b/Z\u0001\u0013g\u0016$8)\u001e:sK:$H)\u0019;bE\u0006\u001cX\rF\u0002R\u0003'Ba!!\u0016\r\u0001\u0004\u0001\u0017\u0001\u00043bi\u0006\u0014\u0017m]3OC6,\u0017aC4fi\u0012\u000bG/\u00192bg\u0016$B!a\u0017\u0002bA!\u0011\u0011IA/\u0013\u0011\ty&a\u0011\u0003\u001f\r\u000bG/\u00197pO\u0012\u000bG/\u00192bg\u0016Da!a\u0019\u000e\u0001\u0004\u0001\u0017\u0001\u00028b[\u0016\fa\u0002Z1uC\n\f7/Z#ySN$8\u000f\u0006\u0003\u0002j\u0005=\u0004cA%\u0002l%\u0019\u0011Q\u000e&\u0003\u000f\t{w\u000e\\3b]\"1\u0011\u0011\u0006\bA\u0002\u0001\fQ\u0002\\5ti\u0012\u000bG/\u00192bg\u0016\u001cHc\u0001<\u0002v!1\u0011\u0011G\bA\u0002\u0001\f1\u0002^1cY\u0016,\u00050[:ugR1\u0011\u0011NA>\u0003{Ba!!\u000b\u0011\u0001\u0004\u0001\u0007BBA@!\u0001\u0007\u0001-A\u0005uC\ndWMT1nK\u0006Aq-\u001a;UC\ndW\r\u0006\u0004\u0002\u0006\u0006-\u0015Q\u0012\t\u0005\u0003\u0003\n9)\u0003\u0003\u0002\n\u0006\r#\u0001D\"bi\u0006dwn\u001a+bE2,\u0007BBA\u0015#\u0001\u0007\u0001\r\u0003\u0004\u0002\u0000E\u0001\r\u0001Y\u0001\u000fO\u0016$H+\u00192mK>\u0003H/[8o)\u0019\t\u0019*!'\u0002\u001cB)\u0011*!&\u0002\u0006&\u0019\u0011q\u0013&\u0003\r=\u0003H/[8o\u0011\u0019\tIC\u0005a\u0001A\"1\u0011q\u0010\nA\u0002\u0001\fqbZ3u%\u0006<\b*\u001b<f)\u0006\u0014G.\u001a\u000b\u0007\u0003C\u000b9+!+\u0011\u0007a\u000b\u0019+C\u0002\u0002&n\u0012ABU1x\u0011&4X\rV1cY\u0016Da!!\u000b\u0014\u0001\u0004\u0001\u0007BBA@'\u0001\u0007\u0001-A\u000bhKR\u0014\u0016m\u001e%jm\u0016$\u0016M\u00197f\u001fB$\u0018n\u001c8\u0015\r\u0005=\u0016\u0011WAZ!\u0015I\u0015QSAQ\u0011\u0019\tI\u0003\u0006a\u0001A\"1\u0011q\u0010\u000bA\u0002\u0001\fqbZ3u)\u0006\u0014G.Z:Cs:\u000bW.\u001a\u000b\u0007\u0003s\u000bY,!0\u0011\t]\\\u0018Q\u0011\u0005\u0007\u0003S)\u0002\u0019\u00011\t\r\u0005}V\u00031\u0001w\u0003)!\u0018M\u00197f\u001d\u0006lWm]\u0001\fGJ,\u0017\r^3UC\ndW\rF\u0003R\u0003\u000b\fI\rC\u0004\u0002HZ\u0001\r!!\"\u0002\u000bQ\f'\r\\3\t\u000f\u0005-g\u00031\u0001\u0002j\u0005q\u0011n\u001a8pe\u0016Le-\u0012=jgR\u001c\u0018!\u00033s_B$\u0016M\u00197f)%\t\u0016\u0011[Aj\u0003+\fI\u000e\u0003\u0004\u0002*]\u0001\r\u0001\u0019\u0005\u0007\u0003\u007f:\u0002\u0019\u00011\t\u000f\u0005]w\u00031\u0001\u0002j\u0005\t\u0012n\u001a8pe\u0016LeMT8u\u000bbL7\u000f^:\t\u000f\u0005mw\u00031\u0001\u0002j\u0005)\u0001/\u001e:hK\u0006Q\u0011\r\u001c;feR\u000b'\r\\3\u0015\u0007E\u000b\t\u000fC\u0004\u0002Hb\u0001\r!!\"\u0015\u000fE\u000b)/a:\u0002j\"1\u0011\u0011F\rA\u0002\u0001Da!a \u001a\u0001\u0004\u0001\u0007bBAd3\u0001\u0007\u0011QQ\u0001\u0010C2$XM\u001d+bE2,\u0007K]8qgR)\u0011+a<\u0002t\"9\u0011\u0011\u001f\u000eA\u0002\u0005\u0005\u0016\u0001\u0004:bo\"Kg/\u001a+bE2,\u0007bBA{5\u0001\u0007\u0011q_\u0001\t]\u0016<\bK]8qgB)\u0011-!?aA&\u0019\u00111 6\u0003\u00075\u000b\u0007/\u0001\u000bbYR,'\u000fV1cY\u0016$\u0015\r^1TG\",W.\u0019\u000b\n#\n\u0005!1\u0001B\u0003\u0005+Aa!!\u000b\u001c\u0001\u0004\u0001\u0007BBA@7\u0001\u0007\u0001\rC\u0004\u0003\bm\u0001\rA!\u0003\u0002\u001b9,w\u000fR1uCN\u001b\u0007.Z7b!\u0011\u0011YA!\u0005\u000e\u0005\t5!b\u0001B\b\u007f\u0005)A/\u001f9fg&!!1\u0003B\u0007\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0005/Y\u0002\u0019AA|\u0003-\u00198\r[3nCB\u0013x\u000e]:\u0002\u001d\r\u0014X-\u0019;f\t\u0006$\u0018MY1tKR)\u0011K!\b\u0003\"!9!q\u0004\u000fA\u0002\u0005m\u0013\u0001\u00033bi\u0006\u0014\u0017m]3\t\u000f\u0005-G\u00041\u0001\u0002j\u0005aAM]8q\t\u0006$\u0018MY1tKR9\u0011Ka\n\u0003*\t-\u0002BBA2;\u0001\u0007\u0001\rC\u0004\u0002Xv\u0001\r!!\u001b\t\u000f\t5R\u00041\u0001\u0002j\u000591-Y:dC\u0012,\u0017!D1mi\u0016\u0014H)\u0019;bE\u0006\u001cX\rF\u0002R\u0005gAqAa\b\u001f\u0001\u0004\tY&\u0001\tde\u0016\fG/\u001a)beRLG/[8ogR9\u0011K!\u000f\u0003<\t\u001d\u0003bBAd?\u0001\u0007\u0011Q\u0011\u0005\b\u0005{y\u0002\u0019\u0001B \u0003\u0015\u0001\u0018M\u001d;t!\u001198P!\u0011\u0011\t\u0005\u0005#1I\u0005\u0005\u0005\u000b\n\u0019EA\u000bDCR\fGn\\4UC\ndW\rU1si&$\u0018n\u001c8\t\u000f\u0005-w\u00041\u0001\u0002j\u0005qAM]8q!\u0006\u0014H/\u001b;j_:\u001cH#D)\u0003N\tE#1\u000bB@\u0005\u0003\u0013\u0019\t\u0003\u0004\u0003P\u0001\u0002\r\u0001Y\u0001\u0003I\nDa!a2!\u0001\u0004\u0001\u0007b\u0002B+A\u0001\u0007!qK\u0001\u0006gB,7m\u001d\t\u0005on\u0014I\u0006\u0005\u0003\u0003\\\ted\u0002\u0002B/\u0005krAAa\u0018\u0003t9!!\u0011\rB9\u001d\u0011\u0011\u0019Ga\u001c\u000f\t\t\u0015$Q\u000e\b\u0005\u0005O\u0012YGD\u0002d\u0005SJ\u0011AR\u0005\u0003\t\u0016K!AQ\"\n\u0005\u0001\u000b\u0015bAA%\u007f%!\u0011QIA$\u0013\u0011\u00119(a\u0011\u0002\u0019\r\u000bG/\u00197pORK\b/Z:\n\t\tm$Q\u0010\u0002\u0013)\u0006\u0014G.\u001a)beRLG/[8o'B,7M\u0003\u0003\u0003x\u0005\r\u0003bBAlA\u0001\u0007\u0011\u0011\u000e\u0005\b\u00037\u0004\u0003\u0019AA5\u0011\u001d\u0011)\t\ta\u0001\u0003S\n!B]3uC&tG)\u0019;b\u0003A\u0011XM\\1nKB\u000b'\u000f^5uS>t7\u000fF\u0005R\u0005\u0017\u0013iIa$\u0003\u0012\"1!qJ\u0011A\u0002\u0001Da!a2\"\u0001\u0004\u0001\u0007b\u0002B+C\u0001\u0007!q\u000b\u0005\b\u0005'\u000b\u0003\u0019\u0001B,\u0003!qWm^*qK\u000e\u001c\u0018aD1mi\u0016\u0014\b+\u0019:uSRLwN\\:\u0015\u000fE\u0013IJa'\u0003\u001e\"1!q\n\u0012A\u0002\u0001Da!a2#\u0001\u0004\u0001\u0007b\u0002BPE\u0001\u0007!qH\u0001\t]\u0016<\b+\u0019:ug\u0006aq-\u001a;QCJ$\u0018\u000e^5p]RA!\u0011\tBS\u0005O\u0013I\u000b\u0003\u0004\u0002*\r\u0002\r\u0001\u0019\u0005\u0007\u0003\u007f\u001a\u0003\u0019\u00011\t\u000f\t-6\u00051\u0001\u0003Z\u0005!1\u000f]3d\u0003E9W\r\u001e)beRLG/[8o\u001d\u0006lWm\u001d\u000b\u0006m\nE&1\u0017\u0005\b\u0003\u000f$\u0003\u0019AAC\u0011%\u0011)\f\nI\u0001\u0002\u0004\u00119,A\u0006qCJ$\u0018.\u00197Ta\u0016\u001c\u0007#B%\u0002\u0016\ne\u0013aG4fiB\u000b'\u000f^5uS>tg*Y7fg\u0012\"WMZ1vYR$#'\u0006\u0002\u0003>*\"!q\u0017B`W\t\u0011\t\r\u0005\u0003\u0003D\n5WB\u0001Bc\u0015\u0011\u00119M!3\u0002\u0013Ut7\r[3dW\u0016$'b\u0001Bf\u0015\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t='Q\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AE4fiB\u000b'\u000f^5uS>tw\n\u001d;j_:$\u0002B!6\u0003X\ne'1\u001c\t\u0006\u0013\u0006U%\u0011\t\u0005\u0007\u0005\u001f2\u0003\u0019\u00011\t\r\u0005\u001dg\u00051\u0001a\u0011\u001d\u0011YK\na\u0001\u00053\"bA!6\u0003`\n\u0005\bbBAdO\u0001\u0007\u0011\u0011\u0015\u0005\b\u0005W;\u0003\u0019\u0001B-\u000359W\r\u001e)beRLG/[8ogRA!q\bBt\u0005S\u0014Y\u000f\u0003\u0004\u0003P!\u0002\r\u0001\u0019\u0005\u0007\u0003\u000fD\u0003\u0019\u00011\t\u000f\tU\u0006\u00061\u0001\u00038\u0006)r-\u001a;QCJ$\u0018\u000e^5p]N\u0014\u0015PR5mi\u0016\u0014HC\u0002B \u0005c\u0014)\u0010C\u0004\u0003t&\u0002\r!!)\u0002\u0019\r\fG/\u00197pOR\u000b'\r\\3\t\u000f\t]\u0018\u00061\u0001\u0003z\u0006Q\u0001O]3eS\u000e\fG/Z:\u0011\t]\\(1 \t\u0005\u0005{\u001c\u0019!\u0004\u0002\u0003\u0000*!1\u0011AA$\u0003-)\u0007\u0010\u001d:fgNLwN\\:\n\t\r\u0015!q \u0002\u000b\u000bb\u0004(/Z:tS>t\u0017!\u00047pC\u0012\u0004\u0016M\u001d;ji&|g\u000eF\bR\u0007\u0017\u0019ya!\u0005\u0004\u0014\r\r2qEB\u0016\u0011\u0019\u0019iA\u000ba\u0001A\u0006AAn\\1e!\u0006$\b\u000e\u0003\u0004\u0002*)\u0002\r\u0001\u0019\u0005\u0007\u0003\u007fR\u0003\u0019\u00011\t\u000f\rU!\u00061\u0001\u0004\u0018\u0005A\u0001/\u0019:u'B,7\r\u0005\u0004\u0004\u001a\r}\u0001\rY\u0007\u0003\u00077QAa!\b\u0002\u0010\u0005!Q\u000f^5m\u0013\u0011\u0019\tca\u0007\u0003\u001b1Kgn[3e\u0011\u0006\u001c\b.T1q\u0011\u001d\u0019)C\u000ba\u0001\u0003S\nqA]3qY\u0006\u001cW\rC\u0004\u0004*)\u0002\r!!\u001b\u0002#%t\u0007.\u001a:jiR\u000b'\r\\3Ta\u0016\u001c7\u000fC\u0004\u0004.)\u0002\r!!\u001b\u0002\u0015%\u001c8K]2M_\u000e\fG.A\u0005m_\u0006$G+\u00192mKRI\u0011ka\r\u00046\r]2\u0011\b\u0005\u0007\u0007\u001bY\u0003\u0019\u00011\t\r\u0005}4\u00061\u0001a\u0011\u001d\u0019)c\u000ba\u0001\u0003SBqa!\f,\u0001\u0004\tI'A\u000bm_\u0006$G)\u001f8b[&\u001c\u0007+\u0019:uSRLwN\\:\u0015\u001bE\u001byd!\u0011\u0004D\r\u00153qIB%\u0011\u0019\u0019i\u0001\fa\u0001A\"1\u0011\u0011\u0006\u0017A\u0002\u0001Da!a -\u0001\u0004\u0001\u0007bBB\u000bY\u0001\u00071q\u0003\u0005\b\u0007Ka\u0003\u0019AA5\u0011\u001d\u0019Y\u0005\fa\u0001\u0007\u001b\nQA\\;n\tB\u00032!SB(\u0013\r\u0019\tF\u0013\u0002\u0004\u0013:$\u0018AD2sK\u0006$XMR;oGRLwN\u001c\u000b\u0006#\u000e]3\u0011\f\u0005\u0007\u0005\u001fj\u0003\u0019\u00011\t\u000f\rmS\u00061\u0001\u0004^\u0005!a-\u001e8d!\u0011\t\tea\u0018\n\t\r\u0005\u00141\t\u0002\u0010\u0007\u0006$\u0018\r\\8h\rVt7\r^5p]\u0006aAM]8q\rVt7\r^5p]R)\u0011ka\u001a\u0004j!1!q\n\u0018A\u0002\u0001Da!a\u0019/\u0001\u0004\u0001\u0017A\u0004:f]\u0006lWMR;oGRLwN\u001c\u000b\b#\u000e=4\u0011OB;\u0011\u0019\u0011ye\fa\u0001A\"111O\u0018A\u0002\u0001\fqa\u001c7e\u001d\u0006lW\r\u0003\u0004\u0004x=\u0002\r\u0001Y\u0001\b]\u0016<h*Y7f\u00035\tG\u000e^3s\rVt7\r^5p]R)\u0011k! \u0004\u0000!1!q\n\u0019A\u0002\u0001Dqaa\u00171\u0001\u0004\u0019i&A\u0006hKR4UO\\2uS>tGCBB/\u0007\u000b\u001b9\t\u0003\u0004\u0003PE\u0002\r\u0001\u0019\u0005\u0007\u0003G\n\u0004\u0019\u00011\u0002#\u001d,GOR;oGRLwN\\(qi&|g\u000e\u0006\u0004\u0004\u000e\u000e=5\u0011\u0013\t\u0006\u0013\u0006U5Q\f\u0005\u0007\u0005\u001f\u0012\u0004\u0019\u00011\t\r\u0005\r$\u00071\u0001a\u000391WO\\2uS>tW\t_5tiN$b!!\u001b\u0004\u0018\u000ee\u0005B\u0002B(g\u0001\u0007\u0001\r\u0003\u0004\u0002dM\u0002\r\u0001Y\u0001\u000eY&\u001cHOR;oGRLwN\\:\u0015\u000bY\u001cyj!)\t\r\t=C\u00071\u0001a\u0011\u0019\t\t\u0004\u000ea\u0001A\u00061\u0011\r\u001a3KCJ$2!UBT\u0011\u0019\u0019I+\u000ea\u0001A\u0006!\u0001/\u0019;i\u0003)qWm^*fgNLwN\u001c\u000b\u0003\u0007_\u0003\"\u0001\u0017\u0001\u0002\u001b]LG\u000f\u001b%jm\u0016\u001cF/\u0019;f+\u0011\u0019)la/\u0015\t\r]6q\u0019\t\u0005\u0007s\u001bY\f\u0004\u0001\u0005\u000f\ruvG1\u0001\u0004@\n\t\u0011)E\u0002\u0004BF\u00042!SBb\u0013\r\u0019)M\u0013\u0002\b\u001d>$\b.\u001b8h\u0011!\u0019Im\u000eCA\u0002\r-\u0017!\u00014\u0011\u000b%\u001bima.\n\u0007\r='J\u0001\u0005=Eft\u0017-\\3?\u0003\u0015\u0011Xm]3u\u0003!)8/\u001a:OC6,W#\u00011"
)
public interface HiveClient {
   package.HiveVersion version();

   String getConf(final String key, final String defaultValue);

   Object getState();

   Seq runSqlHive(final String sql);

   void setOut(final PrintStream stream);

   void setInfo(final PrintStream stream);

   void setError(final PrintStream stream);

   Seq listTables(final String dbName);

   Seq listTables(final String dbName, final String pattern);

   Seq listTablesByType(final String dbName, final String pattern, final CatalogTableType tableType);

   void setCurrentDatabase(final String databaseName);

   CatalogDatabase getDatabase(final String name);

   boolean databaseExists(final String dbName);

   Seq listDatabases(final String pattern);

   boolean tableExists(final String dbName, final String tableName);

   // $FF: synthetic method
   static CatalogTable getTable$(final HiveClient $this, final String dbName, final String tableName) {
      return $this.getTable(dbName, tableName);
   }

   default CatalogTable getTable(final String dbName, final String tableName) {
      return (CatalogTable)this.getTableOption(dbName, tableName).getOrElse(() -> {
         throw new NoSuchTableException(dbName, tableName);
      });
   }

   Option getTableOption(final String dbName, final String tableName);

   // $FF: synthetic method
   static RawHiveTable getRawHiveTable$(final HiveClient $this, final String dbName, final String tableName) {
      return $this.getRawHiveTable(dbName, tableName);
   }

   default RawHiveTable getRawHiveTable(final String dbName, final String tableName) {
      return (RawHiveTable)this.getRawHiveTableOption(dbName, tableName).getOrElse(() -> {
         throw new NoSuchTableException(dbName, tableName);
      });
   }

   Option getRawHiveTableOption(final String dbName, final String tableName);

   Seq getTablesByName(final String dbName, final Seq tableNames);

   void createTable(final CatalogTable table, final boolean ignoreIfExists);

   void dropTable(final String dbName, final String tableName, final boolean ignoreIfNotExists, final boolean purge);

   // $FF: synthetic method
   static void alterTable$(final HiveClient $this, final CatalogTable table) {
      $this.alterTable(table);
   }

   default void alterTable(final CatalogTable table) {
      this.alterTable(table.database(), table.identifier().table(), table);
   }

   void alterTable(final String dbName, final String tableName, final CatalogTable table);

   void alterTableProps(final RawHiveTable rawHiveTable, final Map newProps);

   void alterTableDataSchema(final String dbName, final String tableName, final StructType newDataSchema, final Map schemaProps);

   void createDatabase(final CatalogDatabase database, final boolean ignoreIfExists);

   void dropDatabase(final String name, final boolean ignoreIfNotExists, final boolean cascade);

   void alterDatabase(final CatalogDatabase database);

   void createPartitions(final CatalogTable table, final Seq parts, final boolean ignoreIfExists);

   void dropPartitions(final String db, final String table, final Seq specs, final boolean ignoreIfNotExists, final boolean purge, final boolean retainData);

   void renamePartitions(final String db, final String table, final Seq specs, final Seq newSpecs);

   void alterPartitions(final String db, final String table, final Seq newParts);

   // $FF: synthetic method
   static CatalogTablePartition getPartition$(final HiveClient $this, final String dbName, final String tableName, final Map spec) {
      return $this.getPartition(dbName, tableName, spec);
   }

   default CatalogTablePartition getPartition(final String dbName, final String tableName, final Map spec) {
      return (CatalogTablePartition)this.getPartitionOption(dbName, tableName, spec).getOrElse(() -> {
         throw new NoSuchPartitionException(dbName, tableName, spec);
      });
   }

   Seq getPartitionNames(final CatalogTable table, final Option partialSpec);

   // $FF: synthetic method
   static Option getPartitionNames$default$2$(final HiveClient $this) {
      return $this.getPartitionNames$default$2();
   }

   default Option getPartitionNames$default$2() {
      return .MODULE$;
   }

   // $FF: synthetic method
   static Option getPartitionOption$(final HiveClient $this, final String db, final String table, final Map spec) {
      return $this.getPartitionOption(db, table, spec);
   }

   default Option getPartitionOption(final String db, final String table, final Map spec) {
      return this.getPartitionOption(this.getRawHiveTable(db, table), spec);
   }

   Option getPartitionOption(final RawHiveTable table, final Map spec);

   Seq getPartitions(final String db, final String table, final Option partialSpec);

   Seq getPartitionsByFilter(final RawHiveTable catalogTable, final Seq predicates);

   void loadPartition(final String loadPath, final String dbName, final String tableName, final LinkedHashMap partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSrcLocal);

   void loadTable(final String loadPath, final String tableName, final boolean replace, final boolean isSrcLocal);

   void loadDynamicPartitions(final String loadPath, final String dbName, final String tableName, final LinkedHashMap partSpec, final boolean replace, final int numDP);

   void createFunction(final String db, final CatalogFunction func);

   void dropFunction(final String db, final String name);

   void renameFunction(final String db, final String oldName, final String newName);

   void alterFunction(final String db, final CatalogFunction func);

   // $FF: synthetic method
   static CatalogFunction getFunction$(final HiveClient $this, final String db, final String name) {
      return $this.getFunction(db, name);
   }

   default CatalogFunction getFunction(final String db, final String name) {
      return (CatalogFunction)this.getFunctionOption(db, name).getOrElse(() -> {
         throw new NoSuchPermanentFunctionException(db, name);
      });
   }

   Option getFunctionOption(final String db, final String name);

   // $FF: synthetic method
   static boolean functionExists$(final HiveClient $this, final String db, final String name) {
      return $this.functionExists(db, name);
   }

   default boolean functionExists(final String db, final String name) {
      return this.getFunctionOption(db, name).isDefined();
   }

   Seq listFunctions(final String db, final String pattern);

   void addJar(final String path);

   HiveClient newSession();

   Object withHiveState(final Function0 f);

   void reset();

   String userName();

   static void $init$(final HiveClient $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
