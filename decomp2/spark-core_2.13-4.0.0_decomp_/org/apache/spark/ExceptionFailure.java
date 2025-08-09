package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\rEb\u0001B\u001f?\u0001\u0016C\u0001b\u0018\u0001\u0003\u0016\u0004%\t\u0001\u0019\u0005\tS\u0002\u0011\t\u0012)A\u0005C\"A!\u000e\u0001BK\u0002\u0013\u0005\u0001\r\u0003\u0005l\u0001\tE\t\u0015!\u0003b\u0011!a\u0007A!f\u0001\n\u0003i\u0007\u0002C=\u0001\u0005#\u0005\u000b\u0011\u00028\t\u0011i\u0004!Q3A\u0005\u0002\u0001D\u0001b\u001f\u0001\u0003\u0012\u0003\u0006I!\u0019\u0005\ty\u0002\u0011)\u0019!C\u0005{\"I\u0011\u0011\u0002\u0001\u0003\u0012\u0003\u0006IA \u0005\u000b\u0003\u0017\u0001!Q3A\u0005\u0002\u00055\u0001BCA\u0011\u0001\tE\t\u0015!\u0003\u0002\u0010!Y\u00111\u0005\u0001\u0003\u0002\u0004%\tAPA\u0013\u0011-\t)\u0006\u0001BA\u0002\u0013\u0005a(a\u0016\t\u0015\u0005}\u0002A!E!B\u0013\t9\u0003C\u0006\u0002d\u0001\u0011\t\u0019!C\u0001}\u0005\u0015\u0004bCA8\u0001\t\u0005\r\u0011\"\u0001?\u0003cB!\"!\u001e\u0001\u0005#\u0005\u000b\u0015BA4\u0011\u001d\t9\b\u0001C\u0001\u0003sB\u0001\"a\u001e\u0001\t\u0003q\u00141\u0014\u0005\t\u0003o\u0002A\u0011\u0001 \u00024\"A\u0011\u0011\u0018\u0001\u0005\u0002y\nY\f\u0003\u0005\u0002R\u0002!\tAPAj\u0011\u001d\t9\u000e\u0001C\u0001\u00033Da!!8\u0001\t\u0003\u0002\u0007bBAp\u0001\u0011%\u0011\u0011\u001d\u0005\n\u0003S\u0004\u0011\u0011!C\u0001\u0003WD\u0011\"!@\u0001#\u0003%\t!a@\t\u0013\tU\u0001!%A\u0005\u0002\u0005}\b\"\u0003B\f\u0001E\u0005I\u0011\u0001B\r\u0011%\u0011i\u0002AI\u0001\n\u0003\ty\u0010C\u0005\u0003 \u0001\t\n\u0011\"\u0001\u0003\"!I!Q\u0005\u0001\u0012\u0002\u0013\u0005!q\u0005\u0005\n\u0005W\u0001\u0011\u0013!C\u0001\u0005[A\u0011B!\r\u0001#\u0003%\tAa\r\t\u0011\t]\u0002a#A\u0005\u0002uD\u0011B!\u000f\u0001\u0013\u0003%\t!!\n\t\u0013\tm\u0002!#A\u0005\u0002\u0005\u0015\u0004\"\u0003B\u001f\u0001\u0005\u0005I\u0011\tB \u0011%\u0011)\u0005AA\u0001\n\u0003\u00119\u0005C\u0005\u0003P\u0001\t\t\u0011\"\u0001\u0003R!I!Q\u000b\u0001\u0002\u0002\u0013\u0005#q\u000b\u0005\n\u0005K\u0002\u0011\u0011!C\u0001\u0005OB\u0011Ba\u001b\u0001\u0003\u0003%\tE!\u001c\t\u0013\tE\u0004!!A\u0005B\tM\u0004\"\u0003B;\u0001\u0005\u0005I\u0011\tB<\u0011%\u0011I\bAA\u0001\n\u0003\u0012YhB\u0005\u0003\fz\n\t\u0011#\u0001\u0003\u000e\u001aAQHPA\u0001\u0012\u0003\u0011y\tC\u0004\u0002xE\"\tA!.\t\u0013\tU\u0014'!A\u0005F\t]\u0004\"\u0003B\\c\u0005\u0005I\u0011\u0011B]\u0011%\u0011I.MI\u0001\n\u0003\u00119\u0003C\u0005\u0003\\F\n\n\u0011\"\u0001\u0003^\"I!q^\u0019\u0012\u0002\u0013\u0005!1\u0007\u0005\n\u0005c\f\u0014\u0011!CA\u0005gD\u0011ba\u00042#\u0003%\tAa\n\t\u0013\rE\u0011'%A\u0005\u0002\rM\u0001\"CB\u0013cE\u0005I\u0011\u0001B\u001a\u0011%\u00199#MA\u0001\n\u0013\u0019IC\u0001\tFq\u000e,\u0007\u000f^5p]\u001a\u000b\u0017\u000e\\;sK*\u0011q\bQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0003\n\u000ba!\u00199bG\",'\"A\"\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u00011E\nU*\u0011\u0005\u001dSU\"\u0001%\u000b\u0003%\u000bQa]2bY\u0006L!a\u0013%\u0003\r\u0005s\u0017PU3g!\tie*D\u0001?\u0013\tyeH\u0001\tUCN\\g)Y5mK\u0012\u0014V-Y:p]B\u0011q)U\u0005\u0003%\"\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002U9:\u0011QK\u0017\b\u0003-fk\u0011a\u0016\u0006\u00031\u0012\u000ba\u0001\u0010:p_Rt\u0014\"A%\n\u0005mC\u0015a\u00029bG.\fw-Z\u0005\u0003;z\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0017%\u0002\u0013\rd\u0017m]:OC6,W#A1\u0011\u0005\t4gBA2e!\t1\u0006*\u0003\u0002f\u0011\u00061\u0001K]3eK\u001aL!a\u001a5\u0003\rM#(/\u001b8h\u0015\t)\u0007*\u0001\u0006dY\u0006\u001c8OT1nK\u0002\n1\u0002Z3tGJL\u0007\u000f^5p]\u0006aA-Z:de&\u0004H/[8oA\u0005Q1\u000f^1dWR\u0013\u0018mY3\u0016\u00039\u00042aR8r\u0013\t\u0001\bJA\u0003BeJ\f\u0017\u0010\u0005\u0002so6\t1O\u0003\u0002uk\u0006!A.\u00198h\u0015\u00051\u0018\u0001\u00026bm\u0006L!\u0001_:\u0003#M#\u0018mY6Ue\u0006\u001cW-\u00127f[\u0016tG/A\u0006ti\u0006\u001c7\u000e\u0016:bG\u0016\u0004\u0013A\u00044vY2\u001cF/Y2l)J\f7-Z\u0001\u0010MVdGn\u0015;bG.$&/Y2fA\u0005\u0001R\r_2faRLwN\\,sCB\u0004XM]\u000b\u0002}B!qi`A\u0002\u0013\r\t\t\u0001\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00075\u000b)!C\u0002\u0002\by\u0012Q\u0004\u00165s_^\f'\r\\3TKJL\u0017\r\\5{CRLwN\\,sCB\u0004XM]\u0001\u0012Kb\u001cW\r\u001d;j_:<&/\u00199qKJ\u0004\u0013\u0001D1dGVlW\u000b\u001d3bi\u0016\u001cXCAA\b!\u0015!\u0016\u0011CA\u000b\u0013\r\t\u0019B\u0018\u0002\u0004'\u0016\f\b\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005ma(A\u0005tG\",G-\u001e7fe&!\u0011qDA\r\u0005=\t5mY;nk2\f'\r\\3J]\u001a|\u0017!D1dGVlW\u000b\u001d3bi\u0016\u001c\b%\u0001\u0004bG\u000e,Xn]\u000b\u0003\u0003O\u0001R\u0001VA\t\u0003S\u0001d!a\u000b\u0002<\u0005E\u0003\u0003CA\u0017\u0003g\t9$a\u0014\u000e\u0005\u0005=\"bAA\u0019}\u0005!Q\u000f^5m\u0013\u0011\t)$a\f\u0003\u001b\u0005\u001b7-^7vY\u0006$xN\u001d,3!\u0011\tI$a\u000f\r\u0001\u0011Y\u0011QH\b\u0002\u0002\u0003\u0005)\u0011AA!\u0005\ryF%M\u0001\bC\u000e\u001cW/\\:!#\u0011\t\u0019%!\u0013\u0011\u0007\u001d\u000b)%C\u0002\u0002H!\u0013qAT8uQ&tw\rE\u0002H\u0003\u0017J1!!\u0014I\u0005\r\te.\u001f\t\u0005\u0003s\t\t\u0006B\u0006\u0002T=\t\t\u0011!A\u0003\u0002\u0005\u0005#aA0%e\u0005Q\u0011mY2v[N|F%Z9\u0015\t\u0005e\u0013q\f\t\u0004\u000f\u0006m\u0013bAA/\u0011\n!QK\\5u\u0011%\t\tGDA\u0001\u0002\u0004\t9#A\u0002yIE\n1\"\\3ue&\u001c\u0007+Z1lgV\u0011\u0011q\r\t\u0006)\u0006E\u0011\u0011\u000e\t\u0004\u000f\u0006-\u0014bAA7\u0011\n!Aj\u001c8h\u0003=iW\r\u001e:jGB+\u0017m[:`I\u0015\fH\u0003BA-\u0003gB\u0011\"!\u0019\u0012\u0003\u0003\u0005\r!a\u001a\u0002\u00195,GO]5d!\u0016\f7n\u001d\u0011\u0002\rqJg.\u001b;?)I\tY(! \u0002\u0000\u0005\u0005\u00151QAC\u0003\u000f\u000bI)!'\u0011\u00055\u0003\u0001\"B0\u0014\u0001\u0004\t\u0007\"\u00026\u0014\u0001\u0004\t\u0007\"\u00027\u0014\u0001\u0004q\u0007\"\u0002>\u0014\u0001\u0004\t\u0007\"\u0002?\u0014\u0001\u0004q\b\"CA\u0006'A\u0005\t\u0019AA\b\u0011%\t\u0019c\u0005I\u0001\u0002\u0004\tY\tE\u0003U\u0003#\ti\t\r\u0004\u0002\u0010\u0006M\u0015q\u0013\t\t\u0003[\t\u0019$!%\u0002\u0016B!\u0011\u0011HAJ\t1\ti$!#\u0002\u0002\u0003\u0005)\u0011AA!!\u0011\tI$a&\u0005\u0019\u0005M\u0013\u0011RA\u0001\u0002\u0003\u0015\t!!\u0011\t\u0013\u0005\r4\u0003%AA\u0002\u0005\u001dD\u0003CA>\u0003;\u000b9+!+\t\u000f\u0005}E\u00031\u0001\u0002\"\u0006\tQ\rE\u0002U\u0003GK1!!*_\u0005%!\u0006N]8xC\ndW\rC\u0004\u0002\fQ\u0001\r!a\u0004\t\u000f\u0005-F\u00031\u0001\u0002.\u0006i\u0001O]3tKJ4XmQ1vg\u0016\u00042aRAX\u0013\r\t\t\f\u0013\u0002\b\u0005>|G.Z1o)\u0019\tY(!.\u00028\"9\u0011qT\u000bA\u0002\u0005\u0005\u0006bBA\u0006+\u0001\u0007\u0011qB\u0001\u000bo&$\b.Q2dk6\u001cH\u0003BA>\u0003{Cq!a\t\u0017\u0001\u0004\ty\fE\u0003U\u0003#\t\t\r\r\u0004\u0002D\u0006\u001d\u0017Q\u001a\t\t\u0003[\t\u0019$!2\u0002LB!\u0011\u0011HAd\t1\tI-!0\u0002\u0002\u0003\u0005)\u0011AA!\u0005\ryFe\r\t\u0005\u0003s\ti\r\u0002\u0007\u0002P\u0006u\u0016\u0011!A\u0001\u0006\u0003\t\tEA\u0002`IQ\nqb^5uQ6+GO]5d!\u0016\f7n\u001d\u000b\u0005\u0003w\n)\u000eC\u0004\u0002d]\u0001\r!a\u001a\u0002\u0013\u0015D8-\u001a9uS>tWCAAn!\u00119u0!)\u0002\u001bQ|WI\u001d:peN#(/\u001b8h\u0003=)\u0007pY3qi&|gn\u0015;sS:<GcB1\u0002d\u0006\u0015\u0018q\u001d\u0005\u0006?j\u0001\r!\u0019\u0005\u0006Uj\u0001\r!\u0019\u0005\u0006Yj\u0001\rA\\\u0001\u0005G>\u0004\u0018\u0010\u0006\n\u0002|\u00055\u0018q^Ay\u0003g\f)0a>\u0002z\u0006m\bbB0\u001c!\u0003\u0005\r!\u0019\u0005\bUn\u0001\n\u00111\u0001b\u0011\u001da7\u0004%AA\u00029DqA_\u000e\u0011\u0002\u0003\u0007\u0011\rC\u0004}7A\u0005\t\u0019\u0001@\t\u0013\u0005-1\u0004%AA\u0002\u0005=\u0001\"CA\u00127A\u0005\t\u0019AAF\u0011%\t\u0019g\u0007I\u0001\u0002\u0004\t9'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\t\u0005!fA1\u0003\u0004-\u0012!Q\u0001\t\u0005\u0005\u000f\u0011\t\"\u0004\u0002\u0003\n)!!1\u0002B\u0007\u0003%)hn\u00195fG.,GMC\u0002\u0003\u0010!\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\u0019B!\u0003\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!1\u0004\u0016\u0004]\n\r\u0011AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\u0011\u0019CK\u0002\u007f\u0005\u0007\tabY8qs\u0012\"WMZ1vYR$c'\u0006\u0002\u0003*)\"\u0011q\u0002B\u0002\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"Aa\f+\t\u0005\u001d\"1A\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139+\t\u0011)D\u000b\u0003\u0002h\t\r\u0011!G3yG\u0016\u0004H/[8o/J\f\u0007\u000f]3sI\u0005\u001c7-Z:tIQ\nq\"Y2dk6\u001cH%Y2dKN\u001cHEN\u0001\u0015[\u0016$(/[2QK\u0006\\7\u000fJ1dG\u0016\u001c8\u000fJ\u001c\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\t\u0005E\u0002s\u0005\u0007J!aZ:\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\t%\u0003cA$\u0003L%\u0019!Q\n%\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005%#1\u000b\u0005\n\u0003CJ\u0013\u0011!a\u0001\u0005\u0013\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00053\u0002bAa\u0017\u0003b\u0005%SB\u0001B/\u0015\r\u0011y\u0006S\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B2\u0005;\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011Q\u0016B5\u0011%\t\tgKA\u0001\u0002\u0004\tI%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B!\u0005_B\u0011\"!\u0019-\u0003\u0003\u0005\rA!\u0013\u0002\u0011!\f7\u000f[\"pI\u0016$\"A!\u0013\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!\u0011\u0002\r\u0015\fX/\u00197t)\u0011\tiK! \t\u0013\u0005\u0005t&!AA\u0002\u0005%\u0003f\u0001\u0001\u0003\u0002B!!1\u0011BD\u001b\t\u0011)IC\u0002\u0003\u0010yJAA!#\u0003\u0006\naA)\u001a<fY>\u0004XM]!qS\u0006\u0001R\t_2faRLwN\u001c$bS2,(/\u001a\t\u0003\u001bF\u001aR!\rBI\u0005W\u0003\u0012Ca%\u0003\u001a\u0006\fg.\u0019@\u0002\u0010\tu\u0015qMA>\u001b\t\u0011)JC\u0002\u0003\u0018\"\u000bqA];oi&lW-\u0003\u0003\u0003\u001c\nU%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oqA)A+!\u0005\u0003 B2!\u0011\u0015BS\u0005S\u0003\u0002\"!\f\u00024\t\r&q\u0015\t\u0005\u0003s\u0011)\u000bB\u0006\u0002>E\n\t\u0011!A\u0003\u0002\u0005\u0005\u0003\u0003BA\u001d\u0005S#1\"a\u00152\u0003\u0003\u0005\tQ!\u0001\u0002BA!!Q\u0016BZ\u001b\t\u0011yKC\u0002\u00032V\f!![8\n\u0007u\u0013y\u000b\u0006\u0002\u0003\u000e\u0006)\u0011\r\u001d9msR\u0011\u00121\u0010B^\u0005{\u0013yL!1\u0003D\n\u0015'q\u0019Bl\u0011\u0015yF\u00071\u0001b\u0011\u0015QG\u00071\u0001b\u0011\u0015aG\u00071\u0001o\u0011\u0015QH\u00071\u0001b\u0011\u0015aH\u00071\u0001\u007f\u0011%\tY\u0001\u000eI\u0001\u0002\u0004\ty\u0001C\u0005\u0002$Q\u0002\n\u00111\u0001\u0003JB)A+!\u0005\u0003LB2!Q\u001aBi\u0005+\u0004\u0002\"!\f\u00024\t='1\u001b\t\u0005\u0003s\u0011\t\u000e\u0002\u0007\u0002>\t\u001d\u0017\u0011!A\u0001\u0006\u0003\t\t\u0005\u0005\u0003\u0002:\tUG\u0001DA*\u0005\u000f\f\t\u0011!A\u0003\u0002\u0005\u0005\u0003\"CA2iA\u0005\t\u0019AA4\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\t}'\u0006\u0002Bq\u0005\u0007\u0001R\u0001VA\t\u0005G\u0004dA!:\u0003j\n5\b\u0003CA\u0017\u0003g\u00119Oa;\u0011\t\u0005e\"\u0011\u001e\u0003\f\u0003{1\u0014\u0011!A\u0001\u0006\u0003\t\t\u0005\u0005\u0003\u0002:\t5HaCA*m\u0005\u0005\t\u0011!B\u0001\u0003\u0003\nq\"\u00199qYf$C-\u001a4bk2$H\u0005O\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011)pa\u0003\u0011\t\u001d{(q\u001f\t\u000f\u000f\ne\u0018-\u00198b}\u0006=!Q`A4\u0013\r\u0011Y\u0010\u0013\u0002\u0007)V\u0004H.\u001a\u001d\u0011\u000bQ\u000b\tBa@1\r\r\u00051QAB\u0005!!\ti#a\r\u0004\u0004\r\u001d\u0001\u0003BA\u001d\u0007\u000b!1\"!\u00109\u0003\u0003\u0005\tQ!\u0001\u0002BA!\u0011\u0011HB\u0005\t-\t\u0019\u0006OA\u0001\u0002\u0003\u0015\t!!\u0011\t\u0013\r5\u0001(!AA\u0002\u0005m\u0014a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:TCAB\u000bU\u0011\u00199Ba\u0001\u0011\u000bQ\u000b\tb!\u00071\r\rm1qDB\u0012!!\ti#a\r\u0004\u001e\r\u0005\u0002\u0003BA\u001d\u0007?!1\"!\u0010;\u0003\u0003\u0005\tQ!\u0001\u0002BA!\u0011\u0011HB\u0012\t-\t\u0019FOA\u0001\u0002\u0003\u0015\t!!\u0011\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019Y\u0003E\u0002s\u0007[I1aa\ft\u0005\u0019y%M[3di\u0002"
)
public class ExceptionFailure implements TaskFailedReason, Product, Serializable {
   private final String className;
   private final String description;
   private final StackTraceElement[] stackTrace;
   private final String fullStackTrace;
   private final Option org$apache$spark$ExceptionFailure$$exceptionWrapper;
   private final Seq accumUpdates;
   private Seq accums;
   private Seq metricPeaks;

   public static Seq $lessinit$greater$default$8() {
      return ExceptionFailure$.MODULE$.$lessinit$greater$default$8();
   }

   public static Seq $lessinit$greater$default$7() {
      return ExceptionFailure$.MODULE$.$lessinit$greater$default$7();
   }

   public static Seq $lessinit$greater$default$6() {
      return ExceptionFailure$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final ExceptionFailure x$0) {
      return ExceptionFailure$.MODULE$.unapply(x$0);
   }

   public static Seq apply$default$8() {
      return ExceptionFailure$.MODULE$.apply$default$8();
   }

   public static Seq apply$default$7() {
      return ExceptionFailure$.MODULE$.apply$default$7();
   }

   public static Seq apply$default$6() {
      return ExceptionFailure$.MODULE$.apply$default$6();
   }

   public static ExceptionFailure apply(final String className, final String description, final StackTraceElement[] stackTrace, final String fullStackTrace, final Option exceptionWrapper, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return ExceptionFailure$.MODULE$.apply(className, description, stackTrace, fullStackTrace, exceptionWrapper, accumUpdates, accums, metricPeaks);
   }

   public static Function1 tupled() {
      return ExceptionFailure$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExceptionFailure$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean countTowardsTaskFailures() {
      return TaskFailedReason.countTowardsTaskFailures$(this);
   }

   public Option exceptionWrapper$access$4() {
      return this.org$apache$spark$ExceptionFailure$$exceptionWrapper;
   }

   public Seq accums$access$6() {
      return this.accums;
   }

   public Seq metricPeaks$access$7() {
      return this.metricPeaks;
   }

   public String className() {
      return this.className;
   }

   public String description() {
      return this.description;
   }

   public StackTraceElement[] stackTrace() {
      return this.stackTrace;
   }

   public String fullStackTrace() {
      return this.fullStackTrace;
   }

   public Option org$apache$spark$ExceptionFailure$$exceptionWrapper() {
      return this.org$apache$spark$ExceptionFailure$$exceptionWrapper;
   }

   public Seq accumUpdates() {
      return this.accumUpdates;
   }

   public Seq accums() {
      return this.accums;
   }

   public void accums_$eq(final Seq x$1) {
      this.accums = x$1;
   }

   public Seq metricPeaks() {
      return this.metricPeaks;
   }

   public void metricPeaks_$eq(final Seq x$1) {
      this.metricPeaks = x$1;
   }

   public ExceptionFailure withAccums(final Seq accums) {
      this.accums_$eq(accums);
      return this;
   }

   public ExceptionFailure withMetricPeaks(final Seq metricPeaks) {
      this.metricPeaks_$eq(metricPeaks);
      return this;
   }

   public Option exception() {
      return this.org$apache$spark$ExceptionFailure$$exceptionWrapper().flatMap((w) -> .MODULE$.apply(w.exception()));
   }

   public String toErrorString() {
      return this.fullStackTrace() == null ? this.exceptionString(this.className(), this.description(), this.stackTrace()) : this.fullStackTrace();
   }

   private String exceptionString(final String className, final String description, final StackTraceElement[] stackTrace) {
      String desc = description == null ? "" : description;
      String st = stackTrace == null ? "" : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])stackTrace), (x$1) -> "        " + x$1, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("\n");
      return className + ": " + desc + "\n" + st;
   }

   public ExceptionFailure copy(final String className, final String description, final StackTraceElement[] stackTrace, final String fullStackTrace, final Option exceptionWrapper, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      return new ExceptionFailure(className, description, stackTrace, fullStackTrace, exceptionWrapper, accumUpdates, accums, metricPeaks);
   }

   public String copy$default$1() {
      return this.className();
   }

   public String copy$default$2() {
      return this.description();
   }

   public StackTraceElement[] copy$default$3() {
      return this.stackTrace();
   }

   public String copy$default$4() {
      return this.fullStackTrace();
   }

   public Option copy$default$5() {
      return this.org$apache$spark$ExceptionFailure$$exceptionWrapper();
   }

   public Seq copy$default$6() {
      return this.accumUpdates();
   }

   public Seq copy$default$7() {
      return this.accums();
   }

   public Seq copy$default$8() {
      return this.metricPeaks();
   }

   public String productPrefix() {
      return "ExceptionFailure";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.className();
         }
         case 1 -> {
            return this.description();
         }
         case 2 -> {
            return this.stackTrace();
         }
         case 3 -> {
            return this.fullStackTrace();
         }
         case 4 -> {
            return this.exceptionWrapper$access$4();
         }
         case 5 -> {
            return this.accumUpdates();
         }
         case 6 -> {
            return this.accums$access$6();
         }
         case 7 -> {
            return this.metricPeaks$access$7();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExceptionFailure;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "className";
         }
         case 1 -> {
            return "description";
         }
         case 2 -> {
            return "stackTrace";
         }
         case 3 -> {
            return "fullStackTrace";
         }
         case 4 -> {
            return "exceptionWrapper";
         }
         case 5 -> {
            return "accumUpdates";
         }
         case 6 -> {
            return "accums";
         }
         case 7 -> {
            return "metricPeaks";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var18;
      if (this != x$1) {
         label99: {
            if (x$1 instanceof ExceptionFailure) {
               label91: {
                  ExceptionFailure var4 = (ExceptionFailure)x$1;
                  String var10000 = this.className();
                  String var5 = var4.className();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label91;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label91;
                  }

                  var10000 = this.description();
                  String var6 = var4.description();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label91;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label91;
                  }

                  if (this.stackTrace() == var4.stackTrace()) {
                     label92: {
                        var10000 = this.fullStackTrace();
                        String var7 = var4.fullStackTrace();
                        if (var10000 == null) {
                           if (var7 != null) {
                              break label92;
                           }
                        } else if (!var10000.equals(var7)) {
                           break label92;
                        }

                        Option var14 = this.exceptionWrapper$access$4();
                        Option var8 = var4.exceptionWrapper$access$4();
                        if (var14 == null) {
                           if (var8 != null) {
                              break label92;
                           }
                        } else if (!var14.equals(var8)) {
                           break label92;
                        }

                        Seq var15 = this.accumUpdates();
                        Seq var9 = var4.accumUpdates();
                        if (var15 == null) {
                           if (var9 != null) {
                              break label92;
                           }
                        } else if (!var15.equals(var9)) {
                           break label92;
                        }

                        var15 = this.accums$access$6();
                        Seq var10 = var4.accums$access$6();
                        if (var15 == null) {
                           if (var10 != null) {
                              break label92;
                           }
                        } else if (!var15.equals(var10)) {
                           break label92;
                        }

                        var15 = this.metricPeaks$access$7();
                        Seq var11 = var4.metricPeaks$access$7();
                        if (var15 == null) {
                           if (var11 != null) {
                              break label92;
                           }
                        } else if (!var15.equals(var11)) {
                           break label92;
                        }

                        if (var4.canEqual(this)) {
                           break label99;
                        }
                     }
                  }
               }
            }

            var18 = false;
            return var18;
         }
      }

      var18 = true;
      return var18;
   }

   public ExceptionFailure(final String className, final String description, final StackTraceElement[] stackTrace, final String fullStackTrace, final Option exceptionWrapper, final Seq accumUpdates, final Seq accums, final Seq metricPeaks) {
      this.className = className;
      this.description = description;
      this.stackTrace = stackTrace;
      this.fullStackTrace = fullStackTrace;
      this.org$apache$spark$ExceptionFailure$$exceptionWrapper = exceptionWrapper;
      this.accumUpdates = accumUpdates;
      this.accums = accums;
      this.metricPeaks = metricPeaks;
      super();
      TaskFailedReason.$init$(this);
      Product.$init$(this);
   }

   public ExceptionFailure(final Throwable e, final Seq accumUpdates, final boolean preserveCause) {
      this(e.getClass().getName(), e.getMessage(), e.getStackTrace(), Utils$.MODULE$.exceptionString(e), (Option)(preserveCause ? new Some(new ThrowableSerializationWrapper(e)) : scala.None..MODULE$), accumUpdates, ExceptionFailure$.MODULE$.$lessinit$greater$default$7(), ExceptionFailure$.MODULE$.$lessinit$greater$default$8());
   }

   public ExceptionFailure(final Throwable e, final Seq accumUpdates) {
      this(e, accumUpdates, true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
