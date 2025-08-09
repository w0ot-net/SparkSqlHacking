package scala.reflect.runtime;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import scala.Function0;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;

@ScalaSignature(
   bytes = "\u0006\u0005\rehAC&M!\u0003\r\tA\u0014*\u0004t\")Q\f\u0001C\u0001?\"A1\r\u0001EC\u0002\u0013%A\rC\u0003r\u0001\u0011E#\u000f\u0003\u0005w\u0001!\u0015\r\u0011\"\u0003e\u0011\u0019\t\u0019\u0001\u0001C)e\"Q\u0011q\u0001\u0001\t\u0006\u0004%I!!\u0003\t\u000f\u0005-\u0002\u0001\"\u0011\u0002.!9\u0011q\u0006\u0001\u0005B\u0005E\u0002bBA\u001c\u0001\u0011\u0005\u0013\u0011\b\u0005\b\u0003'\u0002A\u0011IA+\u0011%\ti\nAI\u0001\n\u0003\ty\nC\u0005\u00026\u0002\t\n\u0011\"\u0001\u00028\"9\u00111\u0018\u0001\u0005B\u0005u\u0006\"CAi\u0001E\u0005I\u0011AAP\u0011%\t\u0019\u000eAI\u0001\n\u0003\t9\fC\u0004\u0002V\u0002!\t&a6\u0007\u0013\u0005}\u0007\u0001%A\u0002\u0002\u0005\u0005\b\"B/\u0012\t\u0003y\u0006bBAr#\u0011\u0005\u0013Q\u001d\u0005\b\u0003o\fBQIA}\u0011\u001d\tY0\u0005C!\u0003{D\u0011Ba\u0006\u0012\u0001\u0004&IA!\u0007\t\u0013\t\r\u0012\u00031Q\u0005\n\t\u0015\u0002\"\u0003B\u0016#\u0001\u0007K\u0011\u0002B\u0017\u0011%\u0011\t$\u0005a!\n\u0013\u0011\u0019\u0004C\u0004\u00038E!\tE!\u000f\t\u000f\t\u0005\u0013\u0003\"\u0011\u0003D!9!QI\t\u0005\u0006\t\u001d\u0003b\u0002B4#\u0011\u0015#\u0011\u000e\u0005\b\u0005[\nB\u0011\tB8\u0011\u001d\u0011Y(\u0005C!\u0005{BqA!#\u0012\t\u0003\u0012i\bC\u0004\u0003\fF!\tE!\u0007\t\u000f\t5\u0015\u0003\"\u0011\u0003~!9!qR\t\u0005B\tE\u0005b\u0002BL#\u0011\u0005#Q\u0010\u0005\b\u00053\u000bB\u0011\tBN\u0011\u001d\u0011y*\u0005C!\u00057CqA!)\u0012\t#\u0012\u0019\u000bC\u0004\u0003@F!\tF!1\t\u000f\t=\u0017\u0003\"\u0015\u0003R\"9!\u0011]\t\u0005R\t\r\bb\u0002Bv#\u0011E#Q\u001e\u0005\b\u0005w\fB\u0011\u000bB\u007f\u0011\u001d\u0019Y!\u0005C)\u0007\u001bAqa!\u0007\u0012\t#\u001aY\u0002C\u0004\u0004(E!\tf!\u000b\t\u000f\r]\u0012\u0003\"\u0015\u0004:!91\u0011I\t\u0005R\r\r\u0003bBB&#\u0011E3Q\n\u0005\b\u0007S\nB\u0011KB6\u00119\u00199(\u0005I\u0001\u0004\u0003\u0005I\u0011BA}\u0007sBaba\u001f\u0012!\u0003\r\t\u0011!C\u0005\u0003{\u001ci\b\u0003\b\u0004\u0000E\u0001\n1!A\u0001\n\u0013\u0019\ti!\"\t\u001d\r\u001d\u0015\u0003%A\u0002\u0002\u0003%IAa\u001c\u0004\n\"q11R\t\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003~\r5\u0005BDBH#A\u0005\u0019\u0011!A\u0005\n\tu4\u0011\u0013\u0005\u000f\u0007'\u000b\u0002\u0013aA\u0001\u0002\u0013%!\u0011DBK\u00119\u00199*\u0005I\u0001\u0004\u0003\u0005I\u0011\u0002B?\u00073Caba(\u0012!\u0003\r\t\u0011!C\u0005\u0007C\u001b)\u000b\u0003\b\u0004(F\u0001\n1!A\u0001\n\u0013\u0011ih!+\t\u001d\r-\u0016\u0003%A\u0002\u0002\u0003%IAa'\u0004.\"q1qV\t\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003\u001c\u000eEf!CB/\u0001A\u0005\u0019\u0013AB0\r%\u0019\u0019\f\u0001I\u0001$\u0003\u0019)LB\u0005\u00048\u0002\u0001\n1%\u0001\u0004:\u001aI11\u0018\u0001\u0011\u0002\u0007\u00051Q\u0018\u0005\u0006;\u000e#\ta\u0018\u0005\u000b\u0007\u000b\u001c\u0005R1A\u0005\n\r\u001d\u0007bBBk\u0007\u0012\u0005#Q\u0010\u0005\u000f\u0007/\u001c\u0005\u0013aA\u0001\u0002\u0013%!QPBm\r%\u0019Y\u000e\u0001I\u0001$\u0003\u0019iNB\u0005\u0004b\u0002\u0001\n1%\u0001\u0004d\"q1q\u001d\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0004j\u000eE(aE*z]\u000eD'o\u001c8ju\u0016$7+_7c_2\u001c(BA'O\u0003\u001d\u0011XO\u001c;j[\u0016T!a\u0014)\u0002\u000fI,g\r\\3di*\t\u0011+A\u0003tG\u0006d\u0017mE\u0002\u0001'^\u0003\"\u0001V+\u000e\u0003AK!A\u0016)\u0003\r\u0005s\u0017PU3g!\tA6,D\u0001Z\u0015\tQf*\u0001\u0005j]R,'O\\1m\u0013\ta\u0016LA\u0004Ts6\u0014w\u000e\\:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\u0019\t\u0003)\u0006L!A\u0019)\u0003\tUs\u0017\u000e^\u0001\nCR|W.[2JIN,\u0012!\u001a\t\u0003M>l\u0011a\u001a\u0006\u0003Q&\fa!\u0019;p[&\u001c'B\u00016l\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003Y6\fA!\u001e;jY*\ta.\u0001\u0003kCZ\f\u0017B\u00019h\u00055\tEo\\7jG&sG/Z4fe\u00061a.\u001a=u\u0013\u0012$\u0012a\u001d\t\u0003)RL!!\u001e)\u0003\u0007%sG/\u0001\u000bbi>l\u0017nY#ySN$XM\u001c;jC2LEm\u001d\u0015\u0007\ta\\HP`@\u0011\u0005QK\u0018B\u0001>Q\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005i\u0018!J$m_\n\fG\u000eI3ySN$XM\u001c;jC2\u0004\u0013\nR:!]>\u0004Cn\u001c8hKJ\u0004So]3e\u0003\u0015\u0019\u0018N\\2fC\t\t\t!\u0001\u00043]E\u0012d&M\u0001\u0012]\u0016DH/\u0012=jgR,g\u000e^5bY&#\u0007FB\u0003ywrtx0A\b`e\u0016\u001cWO]:j_:$\u0016M\u00197f+\t\tY\u0001\u0005\u0004\u0002\u000e\u0005=\u0011QC\u0007\u0002\u0001%!\u0011\u0011CA\n\u0005I!\u0006N]3bI2{7-\u00197Ti>\u0014\u0018mZ3\n\u0007\u0005EA\nE\u0004\u0002\u0018\u0005\u0005\u0012QE:\u000e\u0005\u0005e!\u0002BA\u000e\u0003;\t\u0011\"[7nkR\f'\r\\3\u000b\u0007\u0005}\u0001+\u0001\u0006d_2dWm\u0019;j_:LA!a\t\u0002\u001a\t\u0019Q*\u00199\u0011\t\u00055\u0011qE\u0005\u0004\u0003SY&AB*z[\n|G.\u0001\bsK\u000e,(o]5p]R\u000b'\r\\3\u0016\u0005\u0005U\u0011A\u0005:fGV\u00148/[8o)\u0006\u0014G.Z0%KF$2\u0001YA\u001a\u0011\u001d\t)\u0004\u0003a\u0001\u0003+\tQA^1mk\u0016\fAcY8o]\u0016\u001cG/T8ek2,Gk\\\"mCN\u001cHCBA\u001e\u0003\u007f\tIE\u0004\u0003\u0002>\u0005}B\u0002\u0001\u0005\b\u0003\u0003J\u0001\u0019AA\"\u0003\u0005i\u0007\u0003BA\u0007\u0003\u000bJ1!a\u0012\\\u00051iu\u000eZ;mKNKXNY8m\u0011\u001d\tY%\u0003a\u0001\u0003\u001b\n1\"\\8ek2,7\t\\1tgB!\u0011QBA(\u0013\r\t\tf\u0017\u0002\f\u00072\f7o]*z[\n|G.A\toK^4%/Z3UKJl7+_7c_2$\"\"a\u0016\u0002^\u0005-\u0014\u0011PAB!\u0011\ti!!\u0017\n\u0007\u0005m3L\u0001\bGe\u0016,G+\u001a:n'fl'm\u001c7\t\u000f\u0005}#\u00021\u0001\u0002b\u0005!a.Y7f!\u0011\ti!a\u0019\n\t\u0005\u0015\u0014q\r\u0002\t)\u0016\u0014XNT1nK&\u0019\u0011\u0011N-\u0003\u000b9\u000bW.Z:\t\u0011\u0005U\"\u0002\"a\u0001\u0003[\u0002R\u0001VA8\u0003gJ1!!\u001dQ\u0005!a$-\u001f8b[\u0016t\u0004c\u0001+\u0002v%\u0019\u0011q\u000f)\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002|)\u0001\n\u00111\u0001\u0002~\u0005)a\r\\1hgB\u0019A+a \n\u0007\u0005\u0005\u0005K\u0001\u0003M_:<\u0007\"CAC\u0015A\u0005\t\u0019AAD\u0003\u0019y'/[4j]B!\u0011\u0011RAL\u001d\u0011\tY)a%\u0011\u0007\u00055\u0005+\u0004\u0002\u0002\u0010*\u0019\u0011\u0011\u00130\u0002\rq\u0012xn\u001c;?\u0013\r\t)\nU\u0001\u0007!J,G-\u001a4\n\t\u0005e\u00151\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005U\u0005+A\u000eoK^4%/Z3UKJl7+_7c_2$C-\u001a4bk2$HeM\u000b\u0003\u0003CSC!! \u0002$.\u0012\u0011Q\u0015\t\u0005\u0003O\u000b\t,\u0004\u0002\u0002**!\u00111VAW\u0003%)hn\u00195fG.,GMC\u0002\u00020B\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019,!+\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000eoK^4%/Z3UKJl7+_7c_2$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003sSC!a\"\u0002$\u0006\tb.Z<Ge\u0016,G+\u001f9f'fl'm\u001c7\u0015\u0011\u0005}\u0016QYAg\u0003\u001f\u0004B!!\u0004\u0002B&\u0019\u00111Y.\u0003\u001d\u0019\u0013X-\u001a+za\u0016\u001c\u00160\u001c2pY\"9\u0011qL\u0007A\u0002\u0005\u001d\u0007\u0003BA\u0007\u0003\u0013LA!a3\u0002h\tAA+\u001f9f\u001d\u0006lW\rC\u0005\u0002|5\u0001\n\u00111\u0001\u0002~!I\u0011QQ\u0007\u0011\u0002\u0003\u0007\u0011qQ\u0001\u001c]\u0016<hI]3f)f\u0004XmU=nE>dG\u0005Z3gCVdG\u000f\n\u001a\u000279,wO\u0012:fKRK\b/Z*z[\n|G\u000e\n3fM\u0006,H\u000e\u001e\u00134\u00031i\u0017m[3O_NKXNY8m+\t\tI\u000e\u0005\u0003\u0002\u000e\u0005m\u0017bAAo7\nAaj\\*z[\n|GN\u0001\nTs:\u001c\u0007N]8oSj,GmU=nE>d7cA\t\u0002&\u0005a\u0011n\u001d+ie\u0016\fGm]1gKR!\u0011q]Aw!\r!\u0016\u0011^\u0005\u0004\u0003W\u0004&a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003_\u001c\u0002\u0019AAy\u0003\u001d\u0001XO\u001d9pg\u0016\u0004B!!\u0004\u0002t&\u0019\u0011Q_.\u0003\u0013MKXNY8m\u001fB\u001c\u0018!\u00049sSZ\fG/Z,ji\"Lg.\u0006\u0002\u0002&\u0005Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t+\t\ty\u0010\u0005\u0004\u0003\u0002\t\u001d!Q\u0002\b\u0004)\n\r\u0011b\u0001B\u0003!\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B\u0005\u0005\u0017\u0011A\u0001T5ti*\u0019!Q\u0001)\u0011\t\u00055!qB\u0005\u0005\u0005#\u0011\u0019B\u0001\bB]:|G/\u0019;j_:LeNZ8\n\u0007\tU\u0011LA\bB]:|G/\u0019;j_:LeNZ8t\u00031y\u0016N\\5uS\u0006d\u0017N_3e+\t\t9\u000fK\u0002\u0017\u0005;\u00012\u0001\u0016B\u0010\u0013\r\u0011\t\u0003\u0015\u0002\tm>d\u0017\r^5mK\u0006\u0001r,\u001b8ji&\fG.\u001b>fI~#S-\u001d\u000b\u0004A\n\u001d\u0002\"\u0003B\u0015/\u0005\u0005\t\u0019AAt\u0003\rAH%M\u0001\u0014?&t\u0017\u000e^5bY&T\u0018\r^5p]6\u000b7o[\u000b\u0003\u0003{B3\u0001\u0007B\u000f\u0003]y\u0016N\\5uS\u0006d\u0017N_1uS>tW*Y:l?\u0012*\u0017\u000fF\u0002a\u0005kA\u0011B!\u000b\u001a\u0003\u0003\u0005\r!! \u0002%5\f'o\u001b$mC\u001e\u001c8i\\7qY\u0016$X\r\u001a\u000b\u0005\u0005w\u0011i$D\u0001\u0012\u0011\u001d\u0011yD\u0007a\u0001\u0003{\nA!\\1tW\u0006\u0001R.\u0019:l\u00032d7i\\7qY\u0016$X\r\u001a\u000b\u0003\u0005w\tadZ5m'ft7\r\u001b:p]&TX\rZ%g\u001d>$H\u000b\u001b:fC\u0012\u001c\u0018MZ3\u0016\t\t%#Q\n\u000b\u0005\u0005\u0017\u0012I\u0006\u0005\u0003\u0002>\t5Ca\u0002B(9\t\u0007!\u0011\u000b\u0002\u0002)F!!1KA:!\r!&QK\u0005\u0004\u0005/\u0002&a\u0002(pi\"Lgn\u001a\u0005\t\u00057bB\u00111\u0001\u0003^\u0005!!m\u001c3z!\u0015!\u0016q\u000eB&Q\ra\"\u0011\r\t\u0004)\n\r\u0014b\u0001B3!\n1\u0011N\u001c7j]\u0016\fqaZ3u\r2\fw\r\u0006\u0003\u0002~\t-\u0004b\u0002B ;\u0001\u0007\u0011QP\u0001\bm\u0006d\u0017\u000e\u001a+p+\t\u0011\t\b\u0005\u0003\u0002\u000e\tM\u0014\u0002\u0002B;\u0005o\u0012a\u0001U3sS>$\u0017b\u0001B=3\nY1+_7c_2$\u0016M\u00197f\u0003\u0011IgNZ8\u0016\u0005\t}\u0004\u0003BA\u0007\u0005\u0003KAAa!\u0003\u0006\n!A+\u001f9f\u0013\r\u00119)\u0017\u0002\u0006)f\u0004Xm]\u0001\be\u0006<\u0018J\u001c4p\u0003\u0019)\u00070[:ug\u0006iA/\u001f9f'&<g.\u0019;ve\u0016\fq\u0002^=qKNKwM\\1ukJ,\u0017J\u001c\u000b\u0005\u0005\u007f\u0012\u0019\nC\u0004\u0003\u0016\u000e\u0002\rAa \u0002\tMLG/Z\u0001\u0010if\u0004XmQ8ogR\u0014Xo\u0019;pe\u0006QA/\u001f9f!\u0006\u0014\u0018-\\:\u0016\u0005\tu\u0005C\u0002B\u0001\u0005\u000f\t)#\u0001\tv]N\fg-\u001a+za\u0016\u0004\u0016M]1ng\u0006A2M]3bi\u0016\f%m\u001d;sC\u000e$H+\u001f9f'fl'm\u001c7\u0015\u0011\t\u0015&1\u0016BW\u0005w\u0003B!!\u0004\u0003(&\u0019!\u0011V.\u0003%\u0005\u00137\u000f\u001e:bGR$\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0005\b\u0003?:\u0003\u0019AAd\u0011\u001d\u0011yk\na\u0001\u0005c\u000b1\u0001]8t!\u0011\tiAa-\n\t\tU&q\u0017\u0002\t!>\u001c\u0018\u000e^5p]&\u0019!\u0011X-\u0003\u0013A{7/\u001b;j_:\u001c\bb\u0002B_O\u0001\u0007\u0011QP\u0001\t]\u0016<h\t\\1hg\u0006)2M]3bi\u0016\fE.[1t)f\u0004XmU=nE>dG\u0003\u0003Bb\u0005\u0013\u0014YM!4\u0011\t\u00055!QY\u0005\u0004\u0005\u000f\\&aD!mS\u0006\u001cH+\u001f9f'fl'm\u001c7\t\u000f\u0005}\u0003\u00061\u0001\u0002H\"9!q\u0016\u0015A\u0002\tE\u0006b\u0002B_Q\u0001\u0007\u0011QP\u0001\u0017GJ,\u0017\r^3UsB,7k[8mK6\u001c\u00160\u001c2pYRQ!1\u001bBm\u00057\u0014iNa8\u0011\t\u00055!Q[\u0005\u0004\u0005/\\&A\u0003+za\u0016\u001c6n\u001c7f[\"9\u0011qL\u0015A\u0002\u0005\u001d\u0007BBACS\u0001\u00071\u000bC\u0004\u00030&\u0002\rA!-\t\u000f\tu\u0016\u00061\u0001\u0002~\u0005\t2M]3bi\u0016\u001cE.Y:t'fl'm\u001c7\u0015\u0011\u00055#Q\u001dBt\u0005SDq!a\u0018+\u0001\u0004\t9\rC\u0004\u00030*\u0002\rA!-\t\u000f\tu&\u00061\u0001\u0002~\u000592M]3bi\u0016lu\u000eZ;mK\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\t\u0005_\u0014)Pa>\u0003zB!\u0011Q\u0002By\u0013\r\u0011\u0019p\u0017\u0002\u0012\u001b>$W\u000f\\3DY\u0006\u001c8oU=nE>d\u0007bBA0W\u0001\u0007\u0011q\u0019\u0005\b\u0005_[\u0003\u0019\u0001BY\u0011\u001d\u0011il\u000ba\u0001\u0003{\n\u0001d\u0019:fCR,\u0007+Y2lC\u001e,7\t\\1tgNKXNY8m)!\u0011yp!\u0002\u0004\b\r%\u0001\u0003BA\u0007\u0007\u0003I1aa\u0001\\\u0005I\u0001\u0016mY6bO\u0016\u001cE.Y:t'fl'm\u001c7\t\u000f\u0005}C\u00061\u0001\u0002H\"9!q\u0016\u0017A\u0002\tE\u0006b\u0002B_Y\u0001\u0007\u0011QP\u0001\u001cGJ,\u0017\r^3SK\u001aLg.Z7f]R\u001cE.Y:t'fl'm\u001c7\u0015\r\r=1QCB\f!\u0011\tia!\u0005\n\u0007\rM1LA\u000bSK\u001aLg.Z7f]R\u001cE.Y:t'fl'm\u001c7\t\u000f\t=V\u00061\u0001\u00032\"9!QX\u0017A\u0002\u0005u\u0014AH2sK\u0006$X\rU1dW\u0006<Wm\u00142kK\u000e$8\t\\1tgNKXNY8m)\u0019\u0019iba\t\u0004&A!\u0011QBB\u0010\u0013\r\u0019\tc\u0017\u0002\u0019!\u0006\u001c7.Y4f\u001f\nTWm\u0019;DY\u0006\u001c8oU=nE>d\u0007b\u0002BX]\u0001\u0007!\u0011\u0017\u0005\b\u0005{s\u0003\u0019AA?\u0003I\u0019'/Z1uK6+G\u000f[8e'fl'm\u001c7\u0015\u0011\r-2\u0011GB\u001a\u0007k\u0001B!!\u0004\u0004.%\u00191qF.\u0003\u00195+G\u000f[8e'fl'm\u001c7\t\u000f\u0005}s\u00061\u0001\u0002b!9!qV\u0018A\u0002\tE\u0006b\u0002B__\u0001\u0007\u0011QP\u0001\u0013GJ,\u0017\r^3N_\u0012,H.Z*z[\n|G\u000e\u0006\u0005\u0002D\rm2QHB \u0011\u001d\ty\u0006\ra\u0001\u0003CBqAa,1\u0001\u0004\u0011\t\fC\u0004\u0003>B\u0002\r!! \u0002'\r\u0014X-\u0019;f!\u0006\u001c7.Y4f'fl'm\u001c7\u0015\u0011\u0005\r3QIB$\u0007\u0013Bq!a\u00182\u0001\u0004\t\t\u0007C\u0004\u00030F\u0002\rA!-\t\u000f\tu\u0016\u00071\u0001\u0002~\u0005Q2M]3bi\u00164\u0016\r\\;f!\u0006\u0014\u0018-\\3uKJ\u001c\u00160\u001c2pYRA1qJB2\u0007K\u001a9G\u0005\u0004\u0004R\rU31\f\u0004\u0007\u0007'\u0012\u0004aa\u0014\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t\u000551qK\u0005\u0004\u00073Z&A\u0003+fe6\u001c\u00160\u001c2pYB\u0019\u0011Q\u0002!\u0003-MKhn\u00195s_:L'0\u001a3UKJl7+_7c_2\u001cR\u0001QA\u0013\u0007C\u00022!!\u0004\u0012\u0011\u001d\tyF\ra\u0001\u0003CBqAa,3\u0001\u0004\u0011\t\fC\u0004\u0003>J\u0002\r!! \u0002/\r\u0014X-\u0019;f-\u0006dW/Z'f[\n,'oU=nE>dG\u0003CB7\u0007c\u001a\u0019h!\u001e\u0013\r\r=4QKB.\r\u0019\u0019\u0019f\r\u0001\u0004n!9\u0011qL\u001aA\u0002\u0005\u0005\u0004b\u0002BXg\u0001\u0007!\u0011\u0017\u0005\b\u0005{\u001b\u0004\u0019AA?\u0003M\u0019X\u000f]3sIA\u0014\u0018N^1uK^KG\u000f[5o\u0013\u0011\t90a\n\u0002#M,\b/\u001a:%C:tw\u000e^1uS>t7/\u0003\u0003\u0002|\u0006\u001d\u0012!D:va\u0016\u0014HeZ3u\r2\fw\r\u0006\u0003\u0002~\r\r\u0005b\u0002B m\u0001\u0007\u0011QP\u0005\u0005\u0005O\n9#A\u0007tkB,'\u000f\n<bY&$Gk\\\u0005\u0005\u0005[\n9#\u0001\u0006tkB,'\u000fJ5oM>LAAa\u001f\u0002(\u0005i1/\u001e9fe\u0012\u0012\u0018m^%oM>LAA!#\u0002(\u0005a1/\u001e9fe\u0012*\u00070[:ug&!!1RA\u0014\u0003M\u0019X\u000f]3sIQL\b/Z*jO:\fG/\u001e:f\u0013\u0011\u0011iia'\n\u0007\ru5L\u0001\u000bTs6\u0014w\u000e\\\"p]R,\u0007\u0010^!qS&k\u0007\u000f\\\u0001\u0016gV\u0004XM\u001d\u0013usB,7+[4oCR,(/Z%o)\u0011\u0011yha)\t\u000f\tUE\b1\u0001\u0003\u0000%!!qRBN\u0003U\u0019X\u000f]3sIQL\b/Z\"p]N$(/^2u_JLAAa&\u0002(\u0005\u00012/\u001e9fe\u0012\"\u0018\u0010]3QCJ\fWn]\u0005\u0005\u00053\u000b9#\u0001\ftkB,'\u000fJ;og\u00064W\rV=qKB\u000b'/Y7t\u0013\u0011\u0011y*a\n\u00031MKhn\u00195s_:L'0\u001a3NKRDw\u000eZ*z[\n|GnE\u0003B\u0007W\u0019YF\u0001\rTs:\u001c\u0007N]8oSj,G-T8ek2,7+_7c_2\u001cRAQA\"\u00077\u0012acU=oG\"\u0014xN\\5{K\u0012$\u0016\u0010]3Ts6\u0014w\u000e\\\n\u0006\u0007\u000e}6\u0011\r\t\u0005\u0003\u001b\u0019\t-C\u0002\u0004Dn\u0013!\u0002V=qKNKXNY8m\u0003\u001d!\b/\u001a'pG.,\"a!3\u0011\t\r-7\u0011[\u0007\u0003\u0007\u001bT1aa4n\u0003\u0011a\u0017M\\4\n\t\rM7Q\u001a\u0002\u0007\u001f\nTWm\u0019;\u0002\u0015Q\u0004Xm\u0018\u0013uS6,7/\u0001\ttkB,'\u000f\n;qK~#C/[7fg&!1Q[Ba\u0005]\u0019\u0016P\\2ie>t\u0017N_3e\u00072\f7o]*z[\n|GnE\u0003I\u0003\u001b\u001ay\u000eE\u0002\u0002\u000e\r\u0013QdU=oG\"\u0014xN\\5{K\u0012lu\u000eZ;mK\u000ec\u0017m]:Ts6\u0014w\u000e\\\n\u0006\u0013\n=8Q\u001d\t\u0004\u0003\u001bA\u0015AG:va\u0016\u0014HeY8o]\u0016\u001cG/T8ek2,Gk\\\"mCN\u001cHCBBv\u0007[\u001cyO\u0004\u0003\u0002>\r5\bbBA!\u0015\u0002\u0007\u00111\t\u0005\b\u0003\u0017R\u0005\u0019AA'\u0013\r\t9d\u0017\t\u0005\u0007k\u001c90D\u0001M\u0013\r\u0011I\b\u0014"
)
public interface SynchronizedSymbols extends Symbols {
   // $FF: synthetic method
   Symbols.ModuleSymbol scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass);

   // $FF: synthetic method
   static AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds$(final SynchronizedSymbols $this) {
      return $this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds();
   }

   default AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds() {
      return new AtomicInteger(0);
   }

   // $FF: synthetic method
   static int nextId$(final SynchronizedSymbols $this) {
      return $this.nextId();
   }

   default int nextId() {
      return this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds().incrementAndGet();
   }

   // $FF: synthetic method
   static AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds$(final SynchronizedSymbols $this) {
      return $this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds();
   }

   /** @deprecated */
   default AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds() {
      return new AtomicInteger(0);
   }

   // $FF: synthetic method
   static int nextExistentialId$(final SynchronizedSymbols $this) {
      return $this.nextExistentialId();
   }

   /** @deprecated */
   default int nextExistentialId() {
      return this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds().incrementAndGet();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$(final SynchronizedSymbols $this) {
      return $this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> .MODULE$.empty();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static Map recursionTable$(final SynchronizedSymbols $this) {
      return $this.recursionTable();
   }

   default Map recursionTable() {
      return (Map)this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable().get();
   }

   // $FF: synthetic method
   static void recursionTable_$eq$(final SynchronizedSymbols $this, final Map value) {
      $this.recursionTable_$eq(value);
   }

   default void recursionTable_$eq(final Map value) {
      this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable().set(value);
   }

   // $FF: synthetic method
   static Symbols.ModuleSymbol connectModuleToClass$(final SynchronizedSymbols $this, final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass) {
      return $this.connectModuleToClass(m, moduleClass);
   }

   default Symbols.ModuleSymbol connectModuleToClass(final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         return this.scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(m, moduleClass);
      } else {
         Symbols.ModuleSymbol var10000;
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            var10000 = this.scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(m, moduleClass);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   static Symbols.FreeTermSymbol newFreeTermSymbol$(final SynchronizedSymbols $this, final Names.TermName name, final Function0 value, final long flags, final String origin) {
      return $this.newFreeTermSymbol(name, value, flags, origin);
   }

   default Symbols.FreeTermSymbol newFreeTermSymbol(final Names.TermName name, final Function0 value, final long flags, final String origin) {
      return (Symbols.FreeTermSymbol)((SymbolTable)this.new SynchronizedTermSymbol(name, value, origin) {
         private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
            return super.privateWithin();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
            return super.annotations();
         }

         // $FF: synthetic method
         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
            return super.getFlag(mask);
         }

         // $FF: synthetic method
         public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
            return super.validTo();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
            return super.info();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
            return super.rawInfo();
         }

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
            return super.exists();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
            return super.typeSignature();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
            return super.typeSignatureIn(site);
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
            return super.typeConstructor();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
            return super.typeParams();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
            return super.unsafeTypeParams();
         }

         public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
            return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
         }

         public final Symbols.Symbol privateWithin() {
            return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
         }

         public List annotations() {
            return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
         }

         public SynchronizedSymbol markFlagsCompleted(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
         }

         public SynchronizedSymbol markAllCompleted() {
            return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
         }

         public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
            return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
         }

         public final long getFlag(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
         }

         public int validTo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
         }

         public Types.Type info() {
            return SynchronizedSymbols.SynchronizedSymbol.super.info();
         }

         public Types.Type rawInfo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
         }

         public boolean exists() {
            return SynchronizedSymbols.SynchronizedSymbol.super.exists();
         }

         public Types.Type typeSignature() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
         }

         public Types.Type typeSignatureIn(final Types.Type site) {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
         }

         public Types.Type typeConstructor() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
         }

         public List typeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
         }

         public List unsafeTypeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
         }

         public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
         }

         public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
         }

         public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
         }

         public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
         }

         public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
         }

         public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
         }

         public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
         }

         public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
         }

         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
         }

         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
         }

         // $FF: synthetic method
         public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedSymbols.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedSymbols.this;
               SynchronizedSymbols.SynchronizedSymbol.$init$(this);
            }
         }
      }).initFlags(flags);
   }

   // $FF: synthetic method
   static long newFreeTermSymbol$default$3$(final SynchronizedSymbols $this) {
      return $this.newFreeTermSymbol$default$3();
   }

   default long newFreeTermSymbol$default$3() {
      return 0L;
   }

   // $FF: synthetic method
   static String newFreeTermSymbol$default$4$(final SynchronizedSymbols $this) {
      return $this.newFreeTermSymbol$default$4();
   }

   default String newFreeTermSymbol$default$4() {
      return null;
   }

   // $FF: synthetic method
   static Symbols.FreeTypeSymbol newFreeTypeSymbol$(final SynchronizedSymbols $this, final Names.TypeName name, final long flags, final String origin) {
      return $this.newFreeTypeSymbol(name, flags, origin);
   }

   default Symbols.FreeTypeSymbol newFreeTypeSymbol(final Names.TypeName name, final long flags, final String origin) {
      return (Symbols.FreeTypeSymbol)((SymbolTable)this.new SynchronizedTypeSymbol(name, origin) {
         private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
         private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
            return super.tpe_$times();
         }

         public Types.Type tpe_$times() {
            return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
         }

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
            return super.privateWithin();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
            return super.annotations();
         }

         // $FF: synthetic method
         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
            return super.getFlag(mask);
         }

         // $FF: synthetic method
         public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
            return super.validTo();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
            return super.info();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
            return super.rawInfo();
         }

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
            return super.exists();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
            return super.typeSignature();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
            return super.typeSignatureIn(site);
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
            return super.typeConstructor();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
            return super.typeParams();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
            return super.unsafeTypeParams();
         }

         public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
            return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
         }

         public final Symbols.Symbol privateWithin() {
            return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
         }

         public List annotations() {
            return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
         }

         public SynchronizedSymbol markFlagsCompleted(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
         }

         public SynchronizedSymbol markAllCompleted() {
            return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
         }

         public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
            return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
         }

         public final long getFlag(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
         }

         public int validTo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
         }

         public Types.Type info() {
            return SynchronizedSymbols.SynchronizedSymbol.super.info();
         }

         public Types.Type rawInfo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
         }

         public boolean exists() {
            return SynchronizedSymbols.SynchronizedSymbol.super.exists();
         }

         public Types.Type typeSignature() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
         }

         public Types.Type typeSignatureIn(final Types.Type site) {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
         }

         public Types.Type typeConstructor() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
         }

         public List typeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
         }

         public List unsafeTypeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
         }

         public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
         }

         public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
         }

         public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
         }

         public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
         }

         public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
         }

         public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
         }

         public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
         }

         public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
         }

         private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
         }

         public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
            return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
         }

         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
         }

         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
         }

         // $FF: synthetic method
         public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedSymbols.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedSymbols.this;
               SynchronizedSymbols.SynchronizedSymbol.$init$(this);
            }
         }
      }).initFlags(flags);
   }

   // $FF: synthetic method
   static long newFreeTypeSymbol$default$2$(final SynchronizedSymbols $this) {
      return $this.newFreeTypeSymbol$default$2();
   }

   default long newFreeTypeSymbol$default$2() {
      return 0L;
   }

   // $FF: synthetic method
   static String newFreeTypeSymbol$default$3$(final SynchronizedSymbols $this) {
      return $this.newFreeTypeSymbol$default$3();
   }

   default String newFreeTypeSymbol$default$3() {
      return null;
   }

   // $FF: synthetic method
   static Symbols.NoSymbol makeNoSymbol$(final SynchronizedSymbols $this) {
      return $this.makeNoSymbol();
   }

   default Symbols.NoSymbol makeNoSymbol() {
      return (SymbolTable)this.new SynchronizedSymbol() {
         private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
            return super.privateWithin();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
            return super.annotations();
         }

         // $FF: synthetic method
         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
            return super.getFlag(mask);
         }

         // $FF: synthetic method
         public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
            return super.validTo();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
            return super.info();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
            return super.rawInfo();
         }

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
            return super.exists();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
            return super.typeSignature();
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
            return super.typeSignatureIn(site);
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
            return super.typeConstructor();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
            return super.typeParams();
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
            return super.unsafeTypeParams();
         }

         public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
            return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
         }

         public final Symbols.Symbol privateWithin() {
            return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
         }

         public List annotations() {
            return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
         }

         public SynchronizedSymbol markFlagsCompleted(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
         }

         public SynchronizedSymbol markAllCompleted() {
            return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
         }

         public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
            return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
         }

         public final long getFlag(final long mask) {
            return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
         }

         public int validTo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
         }

         public Types.Type info() {
            return SynchronizedSymbols.SynchronizedSymbol.super.info();
         }

         public Types.Type rawInfo() {
            return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
         }

         public boolean exists() {
            return SynchronizedSymbols.SynchronizedSymbol.super.exists();
         }

         public Types.Type typeSignature() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
         }

         public Types.Type typeSignatureIn(final Types.Type site) {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
         }

         public Types.Type typeConstructor() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
         }

         public List typeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
         }

         public List unsafeTypeParams() {
            return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
         }

         public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
         }

         public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
         }

         public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
         }

         public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
         }

         public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
         }

         public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
         }

         public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
         }

         public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
         }

         public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
         }

         public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
            return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
         }

         public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
         }

         public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
            return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
         }

         public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
            this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
         }

         // $FF: synthetic method
         public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedSymbols.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedSymbols.this;
               SynchronizedSymbols.SynchronizedSymbol.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static Symbols.ModuleSymbol $anonfun$connectModuleToClass$1(final SynchronizedSymbols $this, final Symbols.ModuleSymbol m$1, final Symbols.ClassSymbol moduleClass$1) {
      return $this.scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(m$1, moduleClass$1);
   }

   static void $init$(final SynchronizedSymbols $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface SynchronizedSymbol {
      // $FF: synthetic method
      Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin();

      // $FF: synthetic method
      List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations();

      // $FF: synthetic method
      long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask);

      // $FF: synthetic method
      int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();

      // $FF: synthetic method
      boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists();

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site);

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor();

      // $FF: synthetic method
      List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams();

      // $FF: synthetic method
      List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams();

      default boolean isThreadsafe(final Symbols.SymbolOps purpose) {
         if (((SymbolTable)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse()) {
            return false;
         } else if (this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized()) {
            return true;
         } else {
            return purpose.isFlagRelated() && (this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() & purpose.mask() & 290463694721097407L) == 0L;
         }
      }

      default Symbols.Symbol privateWithin() {
         if (!((SymbolTable)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse() && !this.isThreadsafe(this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer().AllOps())) {
            ((Symbols.Symbol)this).initialize();
         }

         return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin();
      }

      default List annotations() {
         if (!((SymbolTable)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse() && !this.isThreadsafe(this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer().AllOps())) {
            ((Symbols.Symbol)this).initialize();
         }

         return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations();
      }

      boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized();

      void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1);

      long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask();

      void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1);

      default SynchronizedSymbol markFlagsCompleted(final long mask) {
         this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() & ~mask);
         return this;
      }

      default SynchronizedSymbol markAllCompleted() {
         this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(0L);
         this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(true);
         return this;
      }

      default Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return body.apply();
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var10000 = (Gil)body.apply();
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var10000;
            }
         }
      }

      default long getFlag(final long mask) {
         if (!((SymbolTable)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse() && !this.isThreadsafe(this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer().FlagOps(mask))) {
            ((Symbols.Symbol)this).initialize();
         }

         return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(mask);
      }

      default int validTo() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default Types.Type info() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default Types.Type rawInfo() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default boolean exists() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default Types.Type typeSignature() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default Types.Type typeSignatureIn(final Types.Type site) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(site);
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(site);
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default Types.Type typeConstructor() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor();
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor();
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default List typeParams() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return $anonfun$typeParams$1(this);
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = $anonfun$typeParams$1(this);
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default List unsafeTypeParams() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return $anonfun$unsafeTypeParams$1(this);
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = $anonfun$unsafeTypeParams$1(this);
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
         return (Symbols.AbstractTypeSymbol)(new SynchronizedTypeSymbol(pos, name) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
         return (Symbols.AliasTypeSymbol)(new SynchronizedTypeSymbol(pos, name) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
         return (Symbols.TypeSkolem)(new SynchronizedTypeSymbol(pos, name, origin) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
         return (Symbols.ClassSymbol)(new SynchronizedClassSymbol(pos, name) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
         return (Symbols.ModuleClassSymbol)(new SynchronizedModuleClassSymbol(pos, name) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
         return (Symbols.PackageClassSymbol)(new SynchronizedModuleClassSymbol(pos, name) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
         return (Symbols.RefinementClassSymbol)(new SynchronizedClassSymbol(pos) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
         return (Symbols.PackageObjectClassSymbol)(new SynchronizedClassSymbol(pos) {
            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times() {
               return super.tpe_$times();
            }

            public Types.Type tpe_$times() {
               return SynchronizedSymbols.SynchronizedTypeSymbol.super.tpe_$times();
            }

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            private Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock = new Object();
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
               return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock;
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
         return (Symbols.MethodSymbol)(new SynchronizedMethodSymbol(pos, name) {
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
         return (Symbols.ModuleSymbol)(new SynchronizedTermSymbol(pos, name) {
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
         return this.createModuleSymbol(name, pos, newFlags);
      }

      default Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
         return (Symbols.TermSymbol)(new SynchronizedTermSymbol(pos, name) {
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      default Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
         return (Symbols.TermSymbol)(new SynchronizedTermSymbol(pos, name) {
            private volatile boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            private volatile long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            // $FF: synthetic field
            private final SynchronizedSymbol $outer;

            // $FF: synthetic method
            public Symbols.Symbol scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$privateWithin() {
               return super.privateWithin();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$annotations() {
               return super.annotations();
            }

            // $FF: synthetic method
            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$getFlag(final long mask) {
               return super.getFlag(mask);
            }

            // $FF: synthetic method
            public int scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo() {
               return super.validTo();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info() {
               return super.info();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo() {
               return super.rawInfo();
            }

            // $FF: synthetic method
            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists() {
               return super.exists();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature() {
               return super.typeSignature();
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(final Types.Type site) {
               return super.typeSignatureIn(site);
            }

            // $FF: synthetic method
            public Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor() {
               return super.typeConstructor();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams() {
               return super.typeParams();
            }

            // $FF: synthetic method
            public List scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams() {
               return super.unsafeTypeParams();
            }

            public boolean isThreadsafe(final Symbols.SymbolOps purpose) {
               return SynchronizedSymbols.SynchronizedSymbol.super.isThreadsafe(purpose);
            }

            public final Symbols.Symbol privateWithin() {
               return SynchronizedSymbols.SynchronizedSymbol.super.privateWithin();
            }

            public List annotations() {
               return SynchronizedSymbols.SynchronizedSymbol.super.annotations();
            }

            public SynchronizedSymbol markFlagsCompleted(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.markFlagsCompleted(mask);
            }

            public SynchronizedSymbol markAllCompleted() {
               return SynchronizedSymbols.SynchronizedSymbol.super.markAllCompleted();
            }

            public final Object gilSynchronizedIfNotThreadsafe(final Function0 body) {
               return SynchronizedSymbols.SynchronizedSymbol.super.gilSynchronizedIfNotThreadsafe(body);
            }

            public final long getFlag(final long mask) {
               return SynchronizedSymbols.SynchronizedSymbol.super.getFlag(mask);
            }

            public int validTo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.validTo();
            }

            public Types.Type info() {
               return SynchronizedSymbols.SynchronizedSymbol.super.info();
            }

            public Types.Type rawInfo() {
               return SynchronizedSymbols.SynchronizedSymbol.super.rawInfo();
            }

            public boolean exists() {
               return SynchronizedSymbols.SynchronizedSymbol.super.exists();
            }

            public Types.Type typeSignature() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignature();
            }

            public Types.Type typeSignatureIn(final Types.Type site) {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeSignatureIn(site);
            }

            public Types.Type typeConstructor() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeConstructor();
            }

            public List typeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.typeParams();
            }

            public List unsafeTypeParams() {
               return SynchronizedSymbols.SynchronizedSymbol.super.unsafeTypeParams();
            }

            public Symbols.AbstractTypeSymbol createAbstractTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAbstractTypeSymbol(name, pos, newFlags);
            }

            public Symbols.AliasTypeSymbol createAliasTypeSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createAliasTypeSymbol(name, pos, newFlags);
            }

            public Symbols.TypeSkolem createTypeSkolemSymbol(final Names.TypeName name, final Object origin, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createTypeSkolemSymbol(name, origin, pos, newFlags);
            }

            public Symbols.ClassSymbol createClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createClassSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleClassSymbol createModuleClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleClassSymbol(name, pos, newFlags);
            }

            public Symbols.PackageClassSymbol createPackageClassSymbol(final Names.TypeName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageClassSymbol(name, pos, newFlags);
            }

            public Symbols.RefinementClassSymbol createRefinementClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createRefinementClassSymbol(pos, newFlags);
            }

            public Symbols.PackageObjectClassSymbol createPackageObjectClassSymbol(final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageObjectClassSymbol(pos, newFlags);
            }

            public Symbols.MethodSymbol createMethodSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createMethodSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createModuleSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createModuleSymbol(name, pos, newFlags);
            }

            public Symbols.ModuleSymbol createPackageSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createPackageSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueParameterSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueParameterSymbol(name, pos, newFlags);
            }

            public Symbols.TermSymbol createValueMemberSymbol(final Names.TermName name, final Position pos, final long newFlags) {
               return SynchronizedSymbols.SynchronizedSymbol.super.createValueMemberSymbol(name, pos, newFlags);
            }

            public boolean scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(final boolean x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized = x$1;
            }

            public long scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask() {
               return this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask;
            }

            public void scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(final long x$1) {
               this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask = x$1;
            }

            // $FF: synthetic method
            public SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer() {
               return this.$outer.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
            }

            public {
               if (SynchronizedSymbol.this == null) {
                  throw null;
               } else {
                  this.$outer = SynchronizedSymbol.this;
                  SynchronizedSymbols.SynchronizedSymbol.$init$(this);
               }
            }
         }).initFlags(newFlags);
      }

      // $FF: synthetic method
      SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();

      // $FF: synthetic method
      static int $anonfun$validTo$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$validTo();
      }

      // $FF: synthetic method
      static Types.Type $anonfun$info$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$info();
      }

      // $FF: synthetic method
      static Types.Type $anonfun$rawInfo$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$rawInfo();
      }

      // $FF: synthetic method
      static boolean $anonfun$exists$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$exists();
      }

      // $FF: synthetic method
      static Types.Type $anonfun$typeSignature$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignature();
      }

      // $FF: synthetic method
      static Types.Type $anonfun$typeSignatureIn$1(final SynchronizedSymbol $this, final Types.Type site$1) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeSignatureIn(site$1);
      }

      // $FF: synthetic method
      static Types.Type $anonfun$typeConstructor$1(final SynchronizedSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeConstructor();
      }

      private List completeTypeParams$1() {
         if (((Symbols.Symbol)this).isMonomorphicType()) {
            return scala.collection.immutable.Nil..MODULE$;
         } else {
            this.rawInfo().load((Symbols.Symbol)this);
            if (this.validTo() == 0) {
               this.rawInfo().load((Symbols.Symbol)this);
            }

            return this.rawInfo().typeParams();
         }
      }

      // $FF: synthetic method
      static List $anonfun$typeParams$1(final SynchronizedSymbol $this) {
         if (((SymbolTable)$this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse()) {
            return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$typeParams();
         } else {
            return $this.validTo() != 0 ? $this.rawInfo().typeParams() : $this.completeTypeParams$1();
         }
      }

      // $FF: synthetic method
      static List $anonfun$unsafeTypeParams$1(final SynchronizedSymbol $this) {
         if (((SymbolTable)$this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer()).isCompilerUniverse()) {
            return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$super$unsafeTypeParams();
         } else {
            return (List)(((Symbols.Symbol)$this).isMonomorphicType() ? scala.collection.immutable.Nil..MODULE$ : $this.rawInfo().typeParams());
         }
      }

      static void $init$(final SynchronizedSymbol $this) {
         $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initialized_$eq(false);
         $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$_initializationMask_$eq(290463694721097407L);
      }
   }

   public interface SynchronizedTypeSymbol extends SynchronizedSymbol {
      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times();

      // $FF: synthetic method
      static Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock$(final SynchronizedTypeSymbol $this) {
         return $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock();
      }

      default Object scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock() {
         return new Object();
      }

      default Types.Type tpe_$times() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedSymbol$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronizedIfNotThreadsafe_gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronizedIfNotThreadsafe_gilSynchronized_this).isCompilerUniverse()) {
               return $anonfun$tpe_$times$1(this);
            } else {
               try {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = $anonfun$tpe_$times$1(this);
               } finally {
                  gilSynchronizedIfNotThreadsafe_gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      // $FF: synthetic method
      SynchronizedSymbols scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$$outer();

      // $FF: synthetic method
      static Types.Type $anonfun$tpe_$times$1(final SynchronizedTypeSymbol $this) {
         synchronized($this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$tpeLock()){}

         Types.Type var2;
         try {
            var2 = $this.scala$reflect$runtime$SynchronizedSymbols$SynchronizedTypeSymbol$$super$tpe_$times();
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      static void $init$(final SynchronizedTypeSymbol $this) {
      }
   }

   public interface SynchronizedClassSymbol extends SynchronizedTypeSymbol {
   }

   public interface SynchronizedMethodSymbol extends SynchronizedTermSymbol {
   }

   public interface SynchronizedModuleClassSymbol extends SynchronizedClassSymbol {
   }

   public interface SynchronizedModuleSymbol extends SynchronizedTermSymbol {
   }

   public interface SynchronizedTermSymbol extends SynchronizedSymbol {
   }
}
