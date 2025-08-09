package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.io.File;
import scala.Option;
import scala.collection.immutable.Range;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ew!B!C\u0011\u00039e!B%C\u0011\u0003Q\u0005\"B)\u0002\t\u0003\u0011\u0006\"B*\u0002\t\u0003!\u0006\"\u0002?\u0002\t\u0003i\b\"CA\r\u0003E\u0005I\u0011AA\u000e\u0011\u001d\t\t$\u0001C\u0001\u0003g1a!a\u0015\u0002\u0003\u0005U\u0003BCA,\u000f\t\u0005\t\u0015!\u0003\u0002Z!1\u0011k\u0002C\u0001\u0003_Bq!a\u001e\b\t\u0003\tI\bC\u0005\u0002\f\u0006\t\t\u0011b\u0001\u0002\u000e\"9\u0011\u0011S\u0001\u0005\u0002\u0005M\u0005\"CA[\u0003E\u0005I\u0011AA\\\u0011%\tY,AI\u0001\n\u0003\t9\fC\u0005\u0002>\u0006\t\n\u0011\"\u0001\u00028\"I\u0011qX\u0001\u0012\u0002\u0013\u0005\u00111\u0004\u0005\b\u0003\u0003\fA\u0011AAb\u0011%\tI.AI\u0001\n\u0003\t9\fC\u0005\u0002\\\u0006\t\n\u0011\"\u0001\u00028\"I\u0011Q\\\u0001\u0012\u0002\u0013\u0005\u0011q\u0017\u0005\n\u0003?\f\u0011\u0013!C\u0001\u00037Aq!!9\u0002\t\u0003\t\u0019\u000fC\u0004\u0003\b\u0005!\u0019A!\u0003\u0007\r\tm\u0011a\u0001B\u000f\u0011)\u0011\t\u0004\u0007BC\u0002\u0013\u0005!1\u0007\u0005\u000b\u0005kA\"\u0011!Q\u0001\n\t5\u0002BB)\u0019\t\u0003\u00119\u0004C\u0005\u0003>a\t\t\u0011\"\u0011\u0003@!I!\u0011\t\r\u0002\u0002\u0013\u0005#1I\u0004\n\u0005\u001f\n\u0011\u0011!E\u0001\u0005#2\u0011Ba\u0007\u0002\u0003\u0003E\tAa\u0015\t\rE{B\u0011\u0001B+\u0011%\u00119fHA\u0001\n\u000b\u0011I\u0006C\u0005\u0003h}\t\t\u0011\"\u0002\u0003j!I!qJ\u0001\u0002\u0002\u0013\r!\u0011\u0010\u0005\t\u0005\u000b\u000bA\u0011\u0001\"\u0003\b\"A!QS\u0001\u0005\u0002\t\u00139\n\u0003\u0005\u0003$\u0006!\tA\u0011BS\u0011)\u0011i+AI\u0001\n\u0003\u0011%q\u0016\u0005\b\u0005g\u000bA\u0011\u0001B[\u0011\u001d\u00119/\u0001C\u0001\u0005SDqaa\u0003\u0002\t\u0003\u0019i\u0001C\u0004\u0004@\u0005!\ta!\u0011\t\u000f\r\r\u0014\u0001\"\u0001\u0004f!91qQ\u0001\u0005\u0002\r%\u0005bBBV\u0003\u0011\u00051Q\u0016\u0005\n\u0007\u0003\f\u0011\u0013!C\u0001\u0007\u0007Dqaa2\u0002\t\u0003\u0019I\rC\u0005\u0004T\u0006\t\n\u0011\"\u0001\u0004V\"I1\u0011\\\u0001\u0012\u0002\u0013\u00051Q\u001b\u0005\b\u00077\fA\u0011ABo\u0011%\u0019\u0019/AI\u0001\n\u0003\u0019)\u000eC\u0004\u0004f\u0006!\taa:\t\u000f\r\u0015\u0018\u0001\"\u0001\u0005\u001c!91Q]\u0001\u0005\u0002\u0011e\u0002bBBs\u0003\u0011\u0005AQ\n\u0005\b\tS\nA\u0011\u0001C6\u0011\u001d!I'\u0001C\u0001\t\u0007Cq\u0001\"\u001b\u0002\t\u0003!I\nC\u0004\u0005j\u0005!\t\u0001\",\t\u000f\u0011\r\u0017\u0001\"\u0003\u0005F\"IA\u0011Z\u0001C\u0002\u0013\u0005A1\u001a\u0005\t\t'\f\u0001\u0015!\u0003\u0005N\"QAQ[\u0001\t\u0006\u0004%\t\u0001b6\u0002\u000fA\f7m[1hK*\u00111\tR\u0001\u0007Y&t\u0017\r\\4\u000b\u0003\u0015\u000baA\u0019:fKj,7\u0001\u0001\t\u0003\u0011\u0006i\u0011A\u0011\u0002\ba\u0006\u001c7.Y4f'\t\t1\n\u0005\u0002M\u001f6\tQJC\u0001O\u0003\u0015\u00198-\u00197b\u0013\t\u0001VJ\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u000bA!\u0019=qsV!Q+\u001d;h)\u00111f\u000f\u001f>\u0015\u0005]S\u0006C\u0001'Y\u0013\tIVJ\u0001\u0003V]&$\b\"B*\u0004\u0001\bY\u0006#\u0002/`KB\u001chB\u0001%^\u0013\tq&)\u0001\u0005tG\u0006dW-\u00113e\u0013\t\u0001\u0017M\u0001\u0007J]Bc\u0017mY3J[Bd7'\u0003\u0002cG\n)QKR;oG*\u0011A\rR\u0001\bO\u0016tWM]5d!\t1w\r\u0004\u0001\u0005\u000b!\u001c!\u0019A5\u0003\u0003e\u000b\"A[7\u0011\u00051[\u0017B\u00017N\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u00148\n\u0005=l%aA!osB\u0011a-\u001d\u0003\u0006e\u000e\u0011\r!\u001b\u0002\u0002\u0003B\u0011a\r\u001e\u0003\u0006k\u000e\u0011\r!\u001b\u0002\u00021\")qo\u0001a\u0001a\u0006\t\u0011\rC\u0003z\u0007\u0001\u00071/A\u0001y\u0011\u0015Y8\u00011\u0001f\u0003\u0005I\u0018\u0001\u00037j]N\u0004\u0018mY3\u0015\u000fy\fI!a\u0003\u0002\u0010A!\u0001j`A\u0002\u0013\r\t\tA\u0011\u0002\f\t\u0016t7/\u001a,fGR|'\u000fE\u0002M\u0003\u000bI1!a\u0002N\u0005\u0019!u.\u001e2mK\"1q\u000f\u0002a\u0001\u0003\u0007Aq!!\u0004\u0005\u0001\u0004\t\u0019!A\u0001c\u0011%\t\t\u0002\u0002I\u0001\u0002\u0004\t\u0019\"\u0001\u0004mK:<G\u000f\u001b\t\u0004\u0019\u0006U\u0011bAA\f\u001b\n\u0019\u0011J\u001c;\u0002%1Lgn\u001d9bG\u0016$C-\u001a4bk2$HeM\u000b\u0003\u0003;QC!a\u0005\u0002 -\u0012\u0011\u0011\u0005\t\u0005\u0003G\ti#\u0004\u0002\u0002&)!\u0011qEA\u0015\u0003%)hn\u00195fG.,GMC\u0002\u0002,5\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\ty#!\n\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0003d_BLX\u0003BA\u001b\u0003w!B!a\u000e\u0002PQ!\u0011\u0011HA !\r1\u00171\b\u0003\u0007\u0003{1!\u0019A5\u0003\u0003QCq!!\u0011\u0007\u0001\b\t\u0019%A\u0004dC:\u001cu\u000e]=\u0011\r\u0005\u0015\u00131JA\u001d\u001b\t\t9EC\u0002\u0002J\t\u000bqa];qa>\u0014H/\u0003\u0003\u0002N\u0005\u001d#aB\"b]\u000e{\u0007/\u001f\u0005\b\u0003#2\u0001\u0019AA\u001d\u0003\u0005!(aC*ue&twM\r$jY\u0016\u001c\"aB&\u0002\u0003M\u0004B!a\u0017\u0002j9!\u0011QLA3!\r\ty&T\u0007\u0003\u0003CR1!a\u0019G\u0003\u0019a$o\\8u}%\u0019\u0011qM'\u0002\rA\u0013X\rZ3g\u0013\u0011\tY'!\u001c\u0003\rM#(/\u001b8h\u0015\r\t9'\u0014\u000b\u0005\u0003c\n)\bE\u0002\u0002t\u001di\u0011!\u0001\u0005\b\u0003/J\u0001\u0019AA-\u0003\u0019!xNR5mKV\u0011\u00111\u0010\t\u0005\u0003{\n9)\u0004\u0002\u0002\u0000)!\u0011\u0011QAB\u0003\tIwN\u0003\u0002\u0002\u0006\u0006!!.\u0019<b\u0013\u0011\tI)a \u0003\t\u0019KG.Z\u0001\f'R\u0014\u0018N\\43\r&dW\r\u0006\u0003\u0002r\u0005=\u0005bBA,\u0017\u0001\u0007\u0011\u0011L\u0001\bGN4(/Z1e)1\t)*a'\u0002 \u0006%\u0016QVAY!\u0015A\u0015qSA\u0002\u0013\r\tIJ\u0011\u0002\f\t\u0016t7/Z'biJL\u0007\u0010C\u0004\u0002\u001e2\u0001\r!a\u001f\u0002\t\u0019LG.\u001a\u0005\n\u0003Cc\u0001\u0013!a\u0001\u0003G\u000b\u0011b]3qCJ\fGo\u001c:\u0011\u00071\u000b)+C\u0002\u0002(6\u0013Aa\u00115be\"I\u00111\u0016\u0007\u0011\u0002\u0003\u0007\u00111U\u0001\u0006cV|G/\u001a\u0005\n\u0003_c\u0001\u0013!a\u0001\u0003G\u000ba!Z:dCB,\u0007\"CAZ\u0019A\u0005\t\u0019AA\n\u0003%\u00198.\u001b9MS:,7/A\tdgZ\u0014X-\u00193%I\u00164\u0017-\u001e7uII*\"!!/+\t\u0005\r\u0016qD\u0001\u0012GN4(/Z1eI\u0011,g-Y;mi\u0012\u001a\u0014!E2tmJ,\u0017\r\u001a\u0013eK\u001a\fW\u000f\u001c;%i\u0005\t2m\u001d<sK\u0006$G\u0005Z3gCVdG\u000fJ\u001b\u0002\u0011\r\u001cho\u001e:ji\u0016$RbVAc\u0003\u000f\f\t.a5\u0002V\u0006]\u0007bBAO#\u0001\u0007\u00111\u0010\u0005\b\u0003\u0013\f\u0002\u0019AAf\u0003\ri\u0017\r\u001e\t\u0006\u0011\u00065\u00171A\u0005\u0004\u0003\u001f\u0014%AB'biJL\u0007\u0010C\u0005\u0002\"F\u0001\n\u00111\u0001\u0002$\"I\u00111V\t\u0011\u0002\u0003\u0007\u00111\u0015\u0005\n\u0003_\u000b\u0002\u0013!a\u0001\u0003GC\u0011\"a-\u0012!\u0003\u0005\r!a\u0005\u0002%\r\u001cho\u001e:ji\u0016$C-\u001a4bk2$HeM\u0001\u0013GN4xO]5uK\u0012\"WMZ1vYR$C'\u0001\ndgZ<(/\u001b;fI\u0011,g-Y;mi\u0012*\u0014AE2tm^\u0014\u0018\u000e^3%I\u00164\u0017-\u001e7uIY\nq!\\7xe&$X-\u0006\u0003\u0002f\u0006}HCBAt\u0005\u0003\u0011\u0019\u0001F\u0002X\u0003SD\u0011\"a;\u0017\u0003\u0003\u0005\u001d!!<\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0002p\u0006]\u0018Q \b\u0005\u0003c\f)P\u0004\u0003\u0002`\u0005M\u0018\"\u0001(\n\u0005\u0005k\u0015\u0002BA}\u0003w\u0014qAT;nKJL7M\u0003\u0002B\u001bB\u0019a-a@\u0005\r\u0005ubC1\u0001j\u0011\u001d\tiJ\u0006a\u0001\u0003wBq!!3\u0017\u0001\u0004\u0011)\u0001E\u0003I\u0003\u001b\fi0\u0001\u000bSC:<W\rV8SC:<W-\u0012=uK:$WM\u001d\u000b\u0005\u0005\u0017\u0011\t\u0002\u0005\u0003\u0002F\t5\u0011\u0002\u0002B\b\u0003\u000f\u0012QBU1oO\u0016,\u0005\u0010^3oI\u0016\u0014\bb\u0002B\n/\u0001\u0007!QC\u0001\u0003e\u0016\u0004B!a<\u0003\u0018%!!\u0011DA~\u0005\u0015\u0011\u0016M\\4f\u0005AIeN[3di:+X.\u001a:jG>\u00038/\u0006\u0003\u0003 \t=2#\u0002\r\u0003\"\t\u001d\u0002c\u0001'\u0003$%\u0019!QE'\u0003\r\u0005s\u0017PV1m!\u0015A%\u0011\u0006B\u0017\u0013\r\u0011YC\u0011\u0002\u0014\u00136lW\u000f^1cY\u0016tU/\\3sS\u000e|\u0005o\u001d\t\u0004M\n=BABA\u001f1\t\u0007\u0011.\u0001\u0003sKB\u0014XC\u0001B\u0017\u0003\u0015\u0011X\r\u001d:!)\u0011\u0011IDa\u000f\u0011\u000b\u0005M\u0004D!\f\t\u000f\tE2\u00041\u0001\u0003.\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0014\u00051Q-];bYN$BA!\u0012\u0003LA\u0019AJa\u0012\n\u0007\t%SJA\u0004C_>dW-\u00198\t\u0011\t5S$!AA\u00025\f1\u0001\u001f\u00132\u0003AIeN[3di:+X.\u001a:jG>\u00038\u000fE\u0002\u0002t}\u0019\"aH&\u0015\u0005\tE\u0013A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:,BAa\u0017\u0003fQ!!q\bB/\u0011\u001d\u0011y&\ta\u0001\u0005C\nQ\u0001\n;iSN\u0004R!a\u001d\u0019\u0005G\u00022A\u001aB3\t\u0019\ti$\tb\u0001S\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\\\u000b\u0005\u0005W\u00129\b\u0006\u0003\u0003n\tED\u0003\u0002B#\u0005_B\u0001B!\u0014#\u0003\u0003\u0005\r!\u001c\u0005\b\u0005?\u0012\u0003\u0019\u0001B:!\u0015\t\u0019\b\u0007B;!\r1'q\u000f\u0003\u0007\u0003{\u0011#\u0019A5\u0016\t\tm$\u0011\u0011\u000b\u0005\u0005{\u0012\u0019\tE\u0003\u0002ta\u0011y\bE\u0002g\u0005\u0003#a!!\u0010$\u0005\u0004I\u0007b\u0002B\u0019G\u0001\u0007!qP\u0001\u0016e\u0016\fX/\u001b:f\u001d>tW)\u001c9us6\u000bGO]5y+\u0011\u0011II!%\u0015\u0007]\u0013Y\tC\u0004\u0002J\u0012\u0002\rA!$\u0011\u000b!\u000biMa$\u0011\u0007\u0019\u0014\t\n\u0002\u0004\u0003\u0014\u0012\u0012\r!\u001b\u0002\u0002-\u0006\u0019\"/Z9vSJ,7+];be\u0016l\u0015\r\u001e:jqV!!\u0011\u0014BQ)\r9&1\u0014\u0005\b\u0003\u0013,\u0003\u0019\u0001BO!\u0015A\u0015Q\u001aBP!\r1'\u0011\u0015\u0003\u0007\u0005'+#\u0019A5\u0002-I,\u0017/^5sKNKX.\\3ue&\u001cW*\u0019;sSb$Ra\u0016BT\u0005SCq!!3'\u0001\u0004\tY\rC\u0005\u0003,\u001a\u0002\n\u00111\u0001\u0002\u0004\u0005\u0019Ao\u001c7\u0002AI,\u0017/^5sKNKX.\\3ue&\u001cW*\u0019;sSb$C-\u001a4bk2$HEM\u000b\u0003\u0005cSC!a\u0001\u0002 \u0005)1M]8tgV!!q\u0017B`)\u0019\u0011ILa9\u0003fR1!1\u0018Bb\u0005'\u0004B\u0001S@\u0003>B\u0019aMa0\u0005\r\t\u0005\u0007F1\u0001j\u0005\t1\u0016\u0007C\u0004\u0003F\"\u0002\u001dAa2\u0002\tILgn\u001a\t\u0007\u0005\u0013\u0014yM!0\u000e\u0005\t-'b\u0001Bg\t\u0006!Q.\u0019;i\u0013\u0011\u0011\tNa3\u0003\tIKgn\u001a\u0005\b\u0005+D\u00039\u0001Bl\u0003\ri\u0017M\u001c\t\u0007\u00053\u0014yN!0\u000e\u0005\tm'b\u0001Bo\u001b\u00069!/\u001a4mK\u000e$\u0018\u0002\u0002Bq\u00057\u0014\u0001b\u00117bgN$\u0016m\u001a\u0005\u0007o\"\u0002\rAa/\t\u000f\u00055\u0001\u00061\u0001\u0003<\u0006)!/\u00198lgV!!1^B\u0001)\u0011\u0011ioa\u0001\u0015\t\t=(Q\u001f\t\u0006\u0019\nE\u00181A\u0005\u0004\u0005gl%!B!se\u0006L\b\"\u0003B|S\u0005\u0005\t9\u0001B}\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0003_\u0014YPa@\n\t\tu\u00181 \u0002\t\u001fJ$WM]5oOB\u0019am!\u0001\u0005\r\tM\u0015F1\u0001j\u0011\u0019I\u0018\u00061\u0001\u0004\u0006A)\u0001ja\u0002\u0003\u0000&\u00191\u0011\u0002\"\u0003\rY+7\r^8s\u0003=awn^3s)JL\u0017M\\4vY\u0006\u0014X\u0003BB\b\u0007/!Ba!\u0005\u0004:QA11CB\r\u0007G\u0019I\u0003E\u0003I\u0003/\u001b)\u0002E\u0002g\u0007/!a!!\u0010+\u0005\u0004I\u0007\"CB\u000eU\u0005\u0005\t9AB\u000f\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0005\u0013\u001cyb!\u0006\n\t\r\u0005\"1\u001a\u0002\t'\u0016l\u0017N]5oO\"I1Q\u0005\u0016\u0002\u0002\u0003\u000f1qE\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004C\u0002Bm\u0005?\u001c)\u0002C\u0005\u0004,)\n\t\u0011q\u0001\u0004.\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\r=2QGB\u000b\u001b\t\u0019\tDC\u0002\u00044\u0011\u000bqa\u001d;pe\u0006<W-\u0003\u0003\u00048\rE\"\u0001\u0002.fe>Dqaa\u000f+\u0001\u0004\u0019i$A\u0001Y!\u0015A\u0015QZB\u000b\u0003]\u0019HO]5di2LHj\\<feR\u0013\u0018.\u00198hk2\f'/\u0006\u0003\u0004D\r-C\u0003BB#\u0007?\"\u0002ba\u0012\u0004N\rM3\u0011\f\t\u0006\u0011\u0006]5\u0011\n\t\u0004M\u000e-CABA\u001fW\t\u0007\u0011\u000eC\u0005\u0004P-\n\t\u0011q\u0001\u0004R\u0005QQM^5eK:\u001cW\r\n\u001c\u0011\r\t%7qDB%\u0011%\u0019)fKA\u0001\u0002\b\u00199&\u0001\u0006fm&$WM\\2fI]\u0002bA!7\u0003`\u000e%\u0003\"CB.W\u0005\u0005\t9AB/\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0007\u0007_\u0019)d!\u0013\t\u000f\rm2\u00061\u0001\u0004bA)\u0001*!4\u0004J\u0005yQ\u000f\u001d9feR\u0013\u0018.\u00198hk2\f'/\u0006\u0003\u0004h\r=D\u0003BB5\u0007\u0007#\u0002ba\u001b\u0004r\r]4Q\u0010\t\u0006\u0011\u0006]5Q\u000e\t\u0004M\u000e=DABA\u001fY\t\u0007\u0011\u000eC\u0005\u0004t1\n\t\u0011q\u0001\u0004v\u0005QQM^5eK:\u001cW\rJ\u001d\u0011\r\t%7qDB7\u0011%\u0019I\bLA\u0001\u0002\b\u0019Y(A\u0006fm&$WM\\2fIE\u0002\u0004C\u0002Bm\u0005?\u001ci\u0007C\u0005\u0004\u00001\n\t\u0011q\u0001\u0004\u0002\u0006YQM^5eK:\u001cW\rJ\u00192!\u0019\u0019yc!\u000e\u0004n!911\b\u0017A\u0002\r\u0015\u0005#\u0002%\u0002N\u000e5\u0014aF:ue&\u001cG\u000f\\=VaB,'\u000f\u0016:jC:<W\u000f\\1s+\u0011\u0019Yia%\u0015\t\r55q\u0015\u000b\t\u0007\u001f\u001b)ja'\u0004\"B)\u0001*a&\u0004\u0012B\u0019ama%\u0005\r\u0005uRF1\u0001j\u0011%\u00199*LA\u0001\u0002\b\u0019I*A\u0006fm&$WM\\2fIE\u0012\u0004C\u0002Be\u0007?\u0019\t\nC\u0005\u0004\u001e6\n\t\u0011q\u0001\u0004 \u0006YQM^5eK:\u001cW\rJ\u00194!\u0019\u0011INa8\u0004\u0012\"I11U\u0017\u0002\u0002\u0003\u000f1QU\u0001\fKZLG-\u001a8dK\u0012\nD\u0007\u0005\u0004\u00040\rU2\u0011\u0013\u0005\b\u0007wi\u0003\u0019ABU!\u0015A\u0015QZBI\u0003!\u0001(/\u001b8d_6\u0004HCBBX\u0007k\u001b9\fE\u0002I\u0007cK1aa-C\u0005\r\u00016)\u0011\u0005\u0007s:\u0002\r!!&\t\u0013\ref\u0006%AA\u0002\rm\u0016!C2pm6\fGo\u00149u!\u0015a5QXAK\u0013\r\u0019y,\u0014\u0002\u0007\u001fB$\u0018n\u001c8\u0002%A\u0014\u0018N\\2p[B$C-\u001a4bk2$HEM\u000b\u0003\u0007\u000bTCaa/\u0002 \u0005)1oY1mKRA\u0011QSBf\u0007\u001b\u001c\t\u000e\u0003\u0004za\u0001\u0007\u0011Q\u0013\u0005\n\u0007\u001f\u0004\u0004\u0013!a\u0001\u0005\u000b\naaY3oi\u0016\u0014\b\"CBdaA\u0005\t\u0019\u0001B#\u0003=\u00198-\u00197fI\u0011,g-Y;mi\u0012\u0012TCABlU\u0011\u0011)%a\b\u0002\u001fM\u001c\u0017\r\\3%I\u00164\u0017-\u001e7uIM\n1aY8w)\u0019\t)ja8\u0004b\"1\u0011p\ra\u0001\u0003+C\u0011ba44!\u0003\u0005\rA!\u0012\u0002\u001b\r|g\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0003!\u0001\u0018\r\u001a*jO\"$X\u0003BBu\u0007c$baa;\u0005\u0014\u0011]A\u0003BBw\u0007g\u0004B\u0001S@\u0004pB\u0019am!=\u0005\r\u0005uRG1\u0001j\u0011\u001d\u0019)0\u000ea\u0002\u0007o\faaY1o!\u0006$\u0007#\u0003%\u0004z\u000e58Q`Bw\u0013\r\u0019YP\u0011\u0002\f\u0007\u0006t\u0007+\u00193SS\u001eDG\u000f\u0005\u0003\u0004\u0000\u00125a\u0002\u0002C\u0001\t\u0013qA\u0001b\u0001\u0005\b9!\u0011q\fC\u0003\u0013\u0005)\u0015BA\"E\u0013\r!YAQ\u0001\b\u001fB$\u0018n\u001c8t\u0013\u0011!y\u0001\"\u0005\u0003\u0017\u0011KW.\u001a8tS>t7/\r\u0006\u0004\t\u0017\u0011\u0005b\u0002C\u000bk\u0001\u00071Q^\u0001\u0002m\"9A\u0011D\u001bA\u0002\ru\u0018A\u00033j[\u0016t7/[8ogV!AQ\u0004C\u0013)!!y\u0002b\u000b\u0005.\u0011=B\u0003\u0002C\u0011\tO\u0001B\u0001S@\u0005$A\u0019a\r\"\n\u0005\r\u0005ubG1\u0001j\u0011\u001d\u0019)P\u000ea\u0002\tS\u0001\u0012\u0002SB}\tC\u0019i\u0010\"\t\t\u000f\u0011Ua\u00071\u0001\u0005\"!9A\u0011\u0004\u001cA\u0002\ru\bb\u0002C\u0019m\u0001\u0007A1G\u0001\u0005[>$W\r\u0005\u0003\u0004\u0000\u0012U\u0012\u0002\u0002C\u001c\t#\u0011!b\u00149u!\u0006$Wj\u001c3f+\u0011!Y\u0004b\u0011\u0015\r\u0011uB\u0011\nC&)\u0011!y\u0004\"\u0012\u0011\u000b!\u000b9\n\"\u0011\u0011\u0007\u0019$\u0019\u0005\u0002\u0004\u0002>]\u0012\r!\u001b\u0005\b\u0007k<\u00049\u0001C$!%A5\u0011 C \u0007{$y\u0004C\u0004\u0005\u0016]\u0002\r\u0001b\u0010\t\u000f\u0011eq\u00071\u0001\u0004~V!Aq\nC,)!!\t\u0006b\u0019\u0005f\u0011\u001dD\u0003\u0002C*\t3\u0002R\u0001SAL\t+\u00022A\u001aC,\t\u0019\ti\u0004\u000fb\u0001S\"91Q\u001f\u001dA\u0004\u0011m\u0003#\u0003%\u0004z\u0012MCQ\fC*!\u0011\u0019y\u0010b\u0018\n\t\u0011\u0005D\u0011\u0003\u0002\f\t&lWM\\:j_:\u001c(\u0007C\u0004\u0005\u0016a\u0002\r\u0001b\u0015\t\u000f\u0011e\u0001\b1\u0001\u0005^!9A\u0011\u0007\u001dA\u0002\u0011M\u0012a\u00029bI2+g\r^\u000b\u0005\t[\")\b\u0006\u0004\u0005p\u0011}D\u0011\u0011\u000b\u0005\tc\"9\b\u0005\u0003I\u007f\u0012M\u0004c\u00014\u0005v\u00111\u0011QH\u001dC\u0002%Dqa!>:\u0001\b!I\bE\u0005I\tw\"\th!@\u0005r%\u0019AQ\u0010\"\u0003\u0015\r\u000bg\u000eU1e\u0019\u00164G\u000fC\u0004\u0005\u0016e\u0002\r\u0001\"\u001d\t\u000f\u0011e\u0011\b1\u0001\u0004~V!AQ\u0011CG)!!9\tb%\u0005\u0016\u0012]E\u0003\u0002CE\t\u001f\u0003B\u0001S@\u0005\fB\u0019a\r\"$\u0005\r\u0005u\"H1\u0001j\u0011\u001d\u0019)P\u000fa\u0002\t#\u0003\u0012\u0002\u0013C>\t\u0013\u001bi\u0010\"#\t\u000f\u0011U!\b1\u0001\u0005\n\"9A\u0011\u0004\u001eA\u0002\ru\bb\u0002C\u0019u\u0001\u0007A1G\u000b\u0005\t7#\u0019\u000b\u0006\u0004\u0005\u001e\u0012%F1\u0016\u000b\u0005\t?#)\u000bE\u0003I\u0003/#\t\u000bE\u0002g\tG#a!!\u0010<\u0005\u0004I\u0007bBB{w\u0001\u000fAq\u0015\t\n\u0011\u0012mDqTB\u007f\t?Cq\u0001\"\u0006<\u0001\u0004!y\nC\u0004\u0005\u001am\u0002\ra!@\u0016\t\u0011=Fq\u0017\u000b\t\tc#i\fb0\u0005BR!A1\u0017C]!\u0015A\u0015q\u0013C[!\r1Gq\u0017\u0003\u0007\u0003{a$\u0019A5\t\u000f\rUH\bq\u0001\u0005<BI\u0001\nb\u001f\u00054\u0012uC1\u0017\u0005\b\t+a\u0004\u0019\u0001CZ\u0011\u001d!I\u0002\u0010a\u0001\t;Bq\u0001\"\r=\u0001\u0004!\u0019$A\u0005d_2,XN\u001c*N'R\u0019a\u0010b2\t\rel\u0004\u0019AAK\u0003\u0011\u0011\u0018M\u001c3\u0016\u0005\u00115gb\u0001%\u0005P&\u0019A\u0011\u001b\"\u0002\u0019I\fg\u000eZ8n\t>,(\r\\3\u0002\u000bI\fg\u000e\u001a\u0011\u0002\u0019U\u001c\u0018N\\4OCRLg/Z:\u0016\u0005\t\u0015\u0003"
)
public final class package {
   public static boolean usingNatives() {
      return package$.MODULE$.usingNatives();
   }

   public static randomDouble$ rand() {
      return package$.MODULE$.rand();
   }

   public static DenseMatrix padLeft(final DenseMatrix v, final Options.Dimensions2 dimensions, final Options.OptPadMode mode, final CanPadLeft canPad) {
      return package$.MODULE$.padLeft(v, dimensions, mode, canPad);
   }

   public static DenseMatrix padLeft(final DenseMatrix v, final Options.Dimensions1 dimensions, final CanPadLeft canPad) {
      return package$.MODULE$.padLeft(v, dimensions, canPad);
   }

   public static DenseVector padLeft(final DenseVector v, final Options.Dimensions1 dimensions, final Options.OptPadMode mode, final CanPadLeft canPad) {
      return package$.MODULE$.padLeft(v, dimensions, mode, canPad);
   }

   public static DenseVector padLeft(final DenseVector v, final Options.Dimensions1 dimensions, final CanPadLeft canPad) {
      return package$.MODULE$.padLeft(v, dimensions, canPad);
   }

   public static DenseMatrix padRight(final DenseMatrix v, final Options.Dimensions2 dimensions, final Options.OptPadMode mode, final CanPadRight canPad) {
      return package$.MODULE$.padRight(v, dimensions, mode, canPad);
   }

   public static DenseMatrix padRight(final DenseMatrix v, final Options.Dimensions1 dimensions, final CanPadRight canPad) {
      return package$.MODULE$.padRight(v, dimensions, canPad);
   }

   public static DenseVector padRight(final DenseVector v, final Options.Dimensions1 dimensions, final Options.OptPadMode mode, final CanPadRight canPad) {
      return package$.MODULE$.padRight(v, dimensions, mode, canPad);
   }

   public static DenseVector padRight(final DenseVector v, final Options.Dimensions1 dimensions, final CanPadRight canPad) {
      return package$.MODULE$.padRight(v, dimensions, canPad);
   }

   public static boolean cov$default$2() {
      return package$.MODULE$.cov$default$2();
   }

   public static DenseMatrix cov(final DenseMatrix x, final boolean center) {
      return package$.MODULE$.cov(x, center);
   }

   public static boolean scale$default$3() {
      return package$.MODULE$.scale$default$3();
   }

   public static boolean scale$default$2() {
      return package$.MODULE$.scale$default$2();
   }

   public static DenseMatrix scale(final DenseMatrix x, final boolean center, final boolean scale) {
      return package$.MODULE$.scale(x, center, scale);
   }

   public static Option princomp$default$2() {
      return package$.MODULE$.princomp$default$2();
   }

   public static PCA princomp(final DenseMatrix x, final Option covmatOpt) {
      return package$.MODULE$.princomp(x, covmatOpt);
   }

   public static DenseMatrix strictlyUpperTriangular(final Matrix X, final Semiring evidence$12, final ClassTag evidence$13, final Zero evidence$14) {
      return package$.MODULE$.strictlyUpperTriangular(X, evidence$12, evidence$13, evidence$14);
   }

   public static DenseMatrix upperTriangular(final Matrix X, final Semiring evidence$9, final ClassTag evidence$10, final Zero evidence$11) {
      return package$.MODULE$.upperTriangular(X, evidence$9, evidence$10, evidence$11);
   }

   public static DenseMatrix strictlyLowerTriangular(final Matrix X, final Semiring evidence$6, final ClassTag evidence$7, final Zero evidence$8) {
      return package$.MODULE$.strictlyLowerTriangular(X, evidence$6, evidence$7, evidence$8);
   }

   public static DenseMatrix lowerTriangular(final Matrix X, final Semiring evidence$3, final ClassTag evidence$4, final Zero evidence$5) {
      return package$.MODULE$.lowerTriangular(X, evidence$3, evidence$4, evidence$5);
   }

   public static double[] ranks(final Vector x, final Ordering evidence$2) {
      return package$.MODULE$.ranks(x, evidence$2);
   }

   public static DenseVector cross(final DenseVector a, final DenseVector b, final Ring ring, final ClassTag man) {
      return package$.MODULE$.cross(a, b, ring, man);
   }

   public static Object InjectNumericOps(final Object repr) {
      return package$.MODULE$.InjectNumericOps(repr);
   }

   public static Range RangeToRangeExtender(final Range re) {
      return package$.MODULE$.RangeToRangeExtender(re);
   }

   public static void mmwrite(final File file, final Matrix mat, final Numeric evidence$1) {
      package$.MODULE$.mmwrite(file, mat, evidence$1);
   }

   public static int csvwrite$default$6() {
      return package$.MODULE$.csvwrite$default$6();
   }

   public static char csvwrite$default$5() {
      return package$.MODULE$.csvwrite$default$5();
   }

   public static char csvwrite$default$4() {
      return package$.MODULE$.csvwrite$default$4();
   }

   public static char csvwrite$default$3() {
      return package$.MODULE$.csvwrite$default$3();
   }

   public static void csvwrite(final File file, final Matrix mat, final char separator, final char quote, final char escape, final int skipLines) {
      package$.MODULE$.csvwrite(file, mat, separator, quote, escape, skipLines);
   }

   public static int csvread$default$5() {
      return package$.MODULE$.csvread$default$5();
   }

   public static char csvread$default$4() {
      return package$.MODULE$.csvread$default$4();
   }

   public static char csvread$default$3() {
      return package$.MODULE$.csvread$default$3();
   }

   public static char csvread$default$2() {
      return package$.MODULE$.csvread$default$2();
   }

   public static DenseMatrix csvread(final File file, final char separator, final char quote, final char escape, final int skipLines) {
      return package$.MODULE$.csvread(file, separator, quote, escape, skipLines);
   }

   public static String2File String2File(final String s) {
      return package$.MODULE$.String2File(s);
   }

   public static Object copy(final Object t, final CanCopy canCopy) {
      return package$.MODULE$.copy(t, canCopy);
   }

   public static int linspace$default$3() {
      return package$.MODULE$.linspace$default$3();
   }

   public static DenseVector linspace(final double a, final double b, final int length) {
      return package$.MODULE$.linspace(a, b, length);
   }

   public static void axpy(final Object a, final Object x, final Object y, final UFunc.InPlaceImpl3 axpy) {
      package$.MODULE$.axpy(a, x, y, axpy);
   }

   public static class String2File {
      private final String s;

      public File toFile() {
         return new File(this.s);
      }

      public String2File(final String s) {
         this.s = s;
      }
   }

   public static final class InjectNumericOps implements ImmutableNumericOps {
      private final Object repr;

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public Object repr() {
         return this.repr;
      }

      public int hashCode() {
         return package.InjectNumericOps$.MODULE$.hashCode$extension(this.repr());
      }

      public boolean equals(final Object x$1) {
         return package.InjectNumericOps$.MODULE$.equals$extension(this.repr(), x$1);
      }

      public InjectNumericOps(final Object repr) {
         this.repr = repr;
         ImmutableNumericOps.$init$(this);
      }
   }

   public static class InjectNumericOps$ {
      public static final InjectNumericOps$ MODULE$ = new InjectNumericOps$();

      public final int hashCode$extension(final Object $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final Object $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof InjectNumericOps) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var10000;
         if (var3) {
            Object var5 = x$1 == null ? null : ((InjectNumericOps)x$1).repr();
            if (BoxesRunTime.equals($this, var5)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }
}
