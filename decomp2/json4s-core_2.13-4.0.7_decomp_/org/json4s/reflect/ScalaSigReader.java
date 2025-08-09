package org.json4s.reflect;

import org.json4s.scalap.scalasig.ClassSymbol;
import org.json4s.scalap.scalasig.MethodSymbol;
import org.json4s.scalap.scalasig.ScalaSig;
import scala.Option;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\rMt!\u0002\u0014(\u0011\u0003qc!\u0002\u0019(\u0011\u0003\t\u0004\"\u0002\u001d\u0002\t\u0003I\u0004B\u0002\u001e\u0002A\u0003%1\b\u0003\u0004a\u0003\u0001\u0006I!\u0019\u0005\u0006o\u0006!\t\u0001\u001f\u0005\u0007o\u0006!\t!!\n\t\r]\fA\u0011AA$\u0011\u00199\u0018\u0001\"\u0001\u0002b!9\u0011QO\u0001\u0005\u0002\u0005]\u0004bBAK\u0003\u0011\u0005\u0011q\u0013\u0005\b\u0003+\u000bA\u0011AA[\u0011\u001d\ty-\u0001C\u0001\u0003#Dq!a4\u0002\t\u0003\ty\u000eC\u0004\u0002p\u0006!\t!!=\t\u000f\t\u0005\u0011\u0001\"\u0001\u0003\u0004!9!\u0011B\u0001\u0005\u0002\t-\u0001b\u0002B\u000b\u0003\u0011%!q\u0003\u0005\b\u0005+\tA\u0011\u0002B\u0014\u0011\u001d\u0011i#\u0001C\u0001\u0005_AqA!\f\u0002\t\u0003\u0011)\u0005C\u0004\u0003X\u0005!IA!\u0017\t\u000f\t-\u0014\u0001\"\u0003\u0003n!A!QV\u0001!\n\u0013\u0011y\u000bC\u0004\u00034\u0006!\tA!.\t\u0011\t\u0015\u0017\u0001)C\u0005\u0005\u000fD\u0011B!6\u0002\u0005\u0004%\tAa6\t\u0011\tu\u0017\u0001)A\u0005\u00053D\u0011Ba8\u0002\u0005\u0004%\tAa6\t\u0011\t\u0005\u0018\u0001)A\u0005\u00053D\u0011Ba9\u0002\u0005\u0004%\tA!:\t\u0011\t]\u0018\u0001)A\u0005\u0005ODqA!?\u0002\t\u0003\u0011Y\u0010C\u0005\u0004(\u0005\t\n\u0011\"\u0001\u0004*!I1qH\u0001\u0012\u0002\u0013\u00051\u0011\t\u0005\b\u0007\u000b\nA\u0011AB$\u0011%\u0019Y&AI\u0001\n\u0003\u0019i\u0006C\u0004\u0004b\u0005!Iaa\u0019\u0002\u001dM\u001b\u0017\r\\1TS\u001e\u0014V-\u00193fe*\u0011\u0001&K\u0001\be\u00164G.Z2u\u0015\tQ3&\u0001\u0004kg>tGg\u001d\u0006\u0002Y\u0005\u0019qN]4\u0004\u0001A\u0011q&A\u0007\u0002O\tq1kY1mCNKwMU3bI\u0016\u00148CA\u00013!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012AL\u0001\u000eY>\u001c\u0017\r\u001c)bi\"lU-\\8\u0011\t=bd(S\u0005\u0003{\u001d\u0012A!T3n_B\u0011qH\u0012\b\u0003\u0001\u0012\u0003\"!\u0011\u001b\u000e\u0003\tS!aQ\u0017\u0002\rq\u0012xn\u001c;?\u0013\t)E'\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000f\"\u0013aa\u0015;sS:<'BA#5!\r\u0019$\nT\u0005\u0003\u0017R\u0012aa\u00149uS>t\u0007GA'X!\rq5+V\u0007\u0002\u001f*\u0011\u0001+U\u0001\u0005Y\u0006twMC\u0001S\u0003\u0011Q\u0017M^1\n\u0005Q{%!B\"mCN\u001c\bC\u0001,X\u0019\u0001!\u0011\u0002W\u0002\u0002\u0002\u0003\u0005)\u0011A-\u0003\u0007}#\u0013'\u0005\u0002[;B\u00111gW\u0005\u00039R\u0012qAT8uQ&tw\r\u0005\u00024=&\u0011q\f\u000e\u0002\u0004\u0003:L\u0018A\u0004:f[>$X\rU1uQ6+Wn\u001c\t\u0005_q\u0012\u0017\u000f\u0005\u00034Gz*\u0017B\u000135\u0005\u0019!V\u000f\u001d7feA\u0019am\u001b8\u000f\u0005\u001dLgBA!i\u0013\u0005)\u0014B\u000165\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\u0011%#XM]1cY\u0016T!A\u001b\u001b\u0011\u00059{\u0017B\u00019P\u0005-\u0019E.Y:t\u0019>\fG-\u001a:\u0011\u0007MR%\u000f\r\u0002tkB\u0019aj\u0015;\u0011\u0005Y+H!\u0003<\u0005\u0003\u0003\u0005\tQ!\u0001Z\u0005\ryFEM\u0001\u0010e\u0016\fGmQ8ogR\u0014Xo\u0019;peRA\u0011p`A\u0002\u0003#\tY\u0002\r\u0002{{B\u0019qh\u001f?\n\u0005QC\u0005C\u0001,~\t%qX!!A\u0001\u0002\u000b\u0005\u0011LA\u0002`IQBa!!\u0001\u0006\u0001\u0004q\u0014aB1sO:\u000bW.\u001a\u0005\b\u0003\u000b)\u0001\u0019AA\u0004\u0003\u0015\u0019G.\u0019>{a\u0011\tI!!\u0004\u0011\t}Z\u00181\u0002\t\u0004-\u00065AaCA\b\u0003\u0007\t\t\u0011!A\u0003\u0002e\u00131a\u0018\u00134\u0011\u001d\t\u0019\"\u0002a\u0001\u0003+\tA\u0002^=qK\u0006\u0013x-\u00138eKb\u00042aMA\f\u0013\r\tI\u0002\u000e\u0002\u0004\u0013:$\bbBA\u000f\u000b\u0001\u0007\u0011qD\u0001\tCJ<g*Y7fgB!a-!\t?\u0013\r\t\u0019#\u001c\u0002\u0005\u0019&\u001cH\u000f\u0006\u0006\u0002(\u0005E\u00121GA \u0003\u000b\u0002D!!\u000b\u0002.A!qh_A\u0016!\r1\u0016Q\u0006\u0003\u000b\u0003_1\u0011\u0011!A\u0001\u0006\u0003I&aA0%m!1\u0011\u0011\u0001\u0004A\u0002yBq!!\u0002\u0007\u0001\u0004\t)\u0004\r\u0003\u00028\u0005m\u0002\u0003B |\u0003s\u00012AVA\u001e\t-\ti$a\r\u0002\u0002\u0003\u0005)\u0011A-\u0003\u0007}#S\u0007C\u0004\u0002B\u0019\u0001\r!a\u0011\u0002\u001dQL\b/Z!sO&sG-\u001a=fgB)a-!\t\u0002\u0016!9\u0011Q\u0004\u0004A\u0002\u0005}ACCA%\u0003'\n)&!\u0018\u0002`A\"\u00111JA(!\u0011y40!\u0014\u0011\u0007Y\u000by\u0005\u0002\u0006\u0002R\u001d\t\t\u0011!A\u0003\u0002e\u00131a\u0018\u00138\u0011\u0019\t\ta\u0002a\u0001}!9\u0011QA\u0004A\u0002\u0005]\u0003cA\u0018\u0002Z%\u0019\u00111L\u0014\u0003\u0013M\u001b\u0017\r\\1UsB,\u0007bBA\n\u000f\u0001\u0007\u0011Q\u0003\u0005\b\u0003;9\u0001\u0019AA\u0010))\t\u0019'!\u001c\u0002p\u0005E\u00141\u000f\u0019\u0005\u0003K\nI\u0007\u0005\u0003@w\u0006\u001d\u0004c\u0001,\u0002j\u0011Q\u00111\u000e\u0005\u0002\u0002\u0003\u0005)\u0011A-\u0003\u0007}#\u0003\b\u0003\u0004\u0002\u0002!\u0001\rA\u0010\u0005\b\u0003\u000bA\u0001\u0019AA,\u0011\u001d\t\t\u0005\u0003a\u0001\u0003\u0007Bq!!\b\t\u0001\u0004\ty\"A\u0005sK\u0006$g)[3mIRA\u0011\u0011PAB\u0003\u000f\u000b\u0019\n\r\u0003\u0002|\u0005}\u0004\u0003B |\u0003{\u00022AVA@\t)\t\t)CA\u0001\u0002\u0003\u0015\t!\u0017\u0002\u0005?\u0012\n\u0004\u0007\u0003\u0004\u0002\u0006&\u0001\rAP\u0001\u0005]\u0006lW\rC\u0004\u0002\u0006%\u0001\r!!#1\t\u0005-\u0015q\u0012\t\u0005\u007fm\fi\tE\u0002W\u0003\u001f#1\"!%\u0002\b\u0006\u0005\t\u0011!B\u00013\n\u0019q\fJ\u001d\t\u000f\u0005M\u0011\u00021\u0001\u0002\u0016\u0005Ia-\u001b8e\u00072\f7o\u001d\u000b\u0005\u00033\u000bI\u000b\u0005\u0003\u0002\u001c\u0006\u0015VBAAO\u0015\u0011\ty*!)\u0002\u0011M\u001c\u0017\r\\1tS\u001eT1!a)*\u0003\u0019\u00198-\u00197ba&!\u0011qUAO\u0005-\u0019E.Y:t'fl'm\u001c7\t\u000f\u0005\u0015!\u00021\u0001\u0002,B\"\u0011QVAY!\u0011y40a,\u0011\u0007Y\u000b\t\fB\u0006\u00024\u0006%\u0016\u0011!A\u0001\u0006\u0003I&\u0001B0%cI\"b!a.\u0002:\u0006\r\u0007\u0003B\u001aK\u00033Cq!a/\f\u0001\u0004\ti,A\u0002tS\u001e\u0004B!a'\u0002@&!\u0011\u0011YAO\u0005!\u00196-\u00197b'&<\u0007bBA\u0003\u0017\u0001\u0007\u0011Q\u0019\u0019\u0005\u0003\u000f\fY\r\u0005\u0003@w\u0006%\u0007c\u0001,\u0002L\u0012Y\u0011QZAb\u0003\u0003\u0005\tQ!\u0001Z\u0005\u0011yF%M\u001a\u0002'\u0019Lg\u000eZ\"p[B\fg.[8o\u001f\nTWm\u0019;\u0015\t\u0005e\u00151\u001b\u0005\b\u0003\u000ba\u0001\u0019AAka\u0011\t9.a7\u0011\t}Z\u0018\u0011\u001c\t\u0004-\u0006mGaCAo\u0003'\f\t\u0011!A\u0003\u0002e\u0013Aa\u0018\u00132iQ1\u0011qWAq\u0003GDq!a/\u000e\u0001\u0004\ti\fC\u0004\u0002\u00065\u0001\r!!:1\t\u0005\u001d\u00181\u001e\t\u0005\u007fm\fI\u000fE\u0002W\u0003W$1\"!<\u0002d\u0006\u0005\t\u0011!B\u00013\n!q\fJ\u00196\u0003=1\u0017N\u001c3D_:\u001cHO];di>\u0014HCBAz\u0003w\fy\u0010\u0005\u00034\u0015\u0006U\b\u0003BAN\u0003oLA!!?\u0002\u001e\naQ*\u001a;i_\u0012\u001c\u00160\u001c2pY\"9\u0011Q \bA\u0002\u0005e\u0015!A2\t\u000f\u0005ua\u00021\u0001\u0002 \u0005Ia-\u001b8e\u0003B\u0004H.\u001f\u000b\u0007\u0003g\u0014)Aa\u0002\t\u000f\u0005ux\u00021\u0001\u0002\u001a\"9\u0011QD\bA\u0002\u0005}\u0011A\u00034j]\u00124\u0015.\u001a7egR!!Q\u0002B\n!\u00151'qBA{\u0013\r\u0011\t\"\u001c\u0002\u0004'\u0016\f\bbBA\u007f!\u0001\u0007\u0011\u0011T\u0001\nM&tGMR5fY\u0012$b!a=\u0003\u001a\t\u0015\u0002bBA\u0003#\u0001\u0007!1\u0004\u0019\u0005\u0005;\u0011\t\u0003\u0005\u0003@w\n}\u0001c\u0001,\u0003\"\u0011Y!1\u0005B\r\u0003\u0003\u0005\tQ!\u0001Z\u0005\u0011yF%\r\u001c\t\r\u0005\u0015\u0015\u00031\u0001?)\u0019\t\u0019P!\u000b\u0003,!9\u0011Q \nA\u0002\u0005e\u0005BBAC%\u0001\u0007a(A\u0006gS:$\u0017I]4UsB,G\u0003\u0003B\u0019\u0005w\u0011yDa\u00111\t\tM\"q\u0007\t\u0005\u007fm\u0014)\u0004E\u0002W\u0005o!!B!\u000f\u0014\u0003\u0003\u0005\tQ!\u0001Z\u0005\u0011yF%M\u001c\t\u000f\tu2\u00031\u0001\u0002v\u0006\t1\u000fC\u0004\u0003BM\u0001\r!!\u0006\u0002\r\u0005\u0014x-\u00133y\u0011\u001d\t\u0019b\u0005a\u0001\u0003+!\u0002Ba\u0012\u0003R\tM#Q\u000b\u0019\u0005\u0005\u0013\u0012i\u0005\u0005\u0003@w\n-\u0003c\u0001,\u0003N\u0011Q!q\n\u000b\u0002\u0002\u0003\u0005)\u0011A-\u0003\t}#\u0013\u0007\u000f\u0005\b\u0005{!\u0002\u0019AA{\u0011\u001d\u0011\t\u0005\u0006a\u0001\u0003+Aq!!\u0011\u0015\u0001\u0004\t\u0019%A\ngS:$\u0017I]4UsB,gi\u001c:GS\u0016dG\r\u0006\u0004\u0003\\\t\u0015$q\r\u0019\u0005\u0005;\u0012\t\u0007\u0005\u0003@w\n}\u0003c\u0001,\u0003b\u0011Q!1M\u000b\u0002\u0002\u0003\u0005)\u0011A-\u0003\t}#\u0013'\u000f\u0005\b\u0005{)\u0002\u0019AA{\u0011\u001d\u0011I'\u0006a\u0001\u0003+\t!\u0002^=qK\u0006\u0013x-\u00133y\u0003\u001d!xn\u00117bgN$BAa\u001c\u0003&B\"!\u0011\u000fB;!\u0011q5Ka\u001d\u0011\u0007Y\u0013)\bB\u0006\u0003xY\t\t\u0011!A\u0003\u0002\te$AA02#\r\u0011Y(\u0018\n\u0012\u0005{\u0012\t)!\u0006\u0003\b\n5%1\u0013BM\u0005?\u0013dA\u0002B@\u0001\u0001\u0011YH\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u00024\u0005\u0007K1A!\"5\u0005\u0015\u0019\u0006n\u001c:u!\r\u0019$\u0011R\u0005\u0004\u0005\u0017#$\u0001\u0002'p]\u001e\u00042a\rBH\u0013\r\u0011\t\n\u000e\u0002\b\u0005>|G.Z1o!\r\u0019$QS\u0005\u0004\u0005/#$!\u0002$m_\u0006$\bcA\u001a\u0003\u001c&\u0019!Q\u0014\u001b\u0003\r\u0011{WO\u00197f!\r\u0019$\u0011U\u0005\u0004\u0005G#$\u0001\u0002\"zi\u0016DqA!\u0010\u0017\u0001\u0004\u00119\u000b\u0005\u0003\u0002\u001c\n%\u0016\u0002\u0002BV\u0003;\u0013aaU=nE>d\u0017aC5t!JLW.\u001b;jm\u0016$BA!$\u00032\"9!QH\fA\u0002\t\u001d\u0016\u0001\u00044j]\u0012\u001c6-\u00197b'&<G\u0003\u0002B\\\u0005s\u0003Ba\r&\u0002>\"9\u0011Q\u0001\rA\u0002\tm\u0006\u0007\u0002B_\u0005\u0003\u0004BaP>\u0003@B\u0019aK!1\u0005\u0017\t\r'\u0011XA\u0001\u0002\u0003\u0015\t!\u0017\u0002\u0005?\u0012\u0012\u0004'\u0001\u000eqCJ\u001cXm\u00117bgN4\u0015\u000e\\3Ge>l')\u001f;f\u0007>$W\r\u0006\u0003\u00038\n%\u0007bBA\u00033\u0001\u0007!1\u001a\u0019\u0005\u0005\u001b\u0014\t\u000e\u0005\u0003@w\n=\u0007c\u0001,\u0003R\u0012Y!1\u001bBe\u0003\u0003\u0005\tQ!\u0001Z\u0005\u0011yFEM\u0019\u0002\u001f5{G-\u001e7f\r&,G\u000e\u001a(b[\u0016,\"A!7\u0011\u00079\u0013Y.\u0003\u0002H\u001f\u0006\u0001Rj\u001c3vY\u00164\u0015.\u001a7e\u001d\u0006lW\rI\u0001\u000f\u001fV$XM\u001d$jK2$g*Y7f\u0003=yU\u000f^3s\r&,G\u000e\u001a(b[\u0016\u0004\u0013\u0001D\"mCN\u001cHj\\1eKJ\u001cXC\u0001Bt!\u0015\u0011IOa=o\u001b\t\u0011YO\u0003\u0003\u0003n\n=\u0018!C5n[V$\u0018M\u00197f\u0015\r\u0011\t\u0010N\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B{\u0005W\u0014aAV3di>\u0014\u0018!D\"mCN\u001cHj\\1eKJ\u001c\b%\u0001\u0006d_6\u0004\u0018M\\5p]N$\u0002B!@\u0004\u001a\ru11\u0005\t\u0005g)\u0013y\u0010\r\u0003\u0004\u0002\r\u001d\u0001CB\u001ad\u0007\u0007\u0019\t\u0002\u0005\u0003O'\u000e\u0015\u0001c\u0001,\u0004\b\u0011Y1\u0011BB\u0006\u0003\u0003\u0005\tQ!\u0001Z\u0005\u0011yFE\r\u001a\t\u0013\r5\u0001%!A\u0001\u0002\r=\u0011\u0001\u0003\u0013b]>tg-\u001e8\f\u0001A!1GSB\n!\rq5QC\u0005\u0004\u0007/y%AB(cU\u0016\u001cG\u000f\u0003\u0004\u0004\u001c\u0001\u0002\rAP\u0001\u0002i\"I1q\u0004\u0011\u0011\u0002\u0003\u00071\u0011E\u0001\nG>l\u0007/\u00198j_:\u00042a\r&3\u0011!\u0019)\u0003\tI\u0001\u0002\u0004)\u0017\u0001D2mCN\u001cHj\\1eKJ\u001c\u0018\u0001F2p[B\fg.[8og\u0012\"WMZ1vYR$#'\u0006\u0002\u0004,)\"1\u0011EB\u0017W\t\u0019y\u0003\u0005\u0003\u00042\rmRBAB\u001a\u0015\u0011\u0019)da\u000e\u0002\u0013Ut7\r[3dW\u0016$'bAB\u001di\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\ru21\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001F2p[B\fg.[8og\u0012\"WMZ1vYR$3'\u0006\u0002\u0004D)\u001aQm!\f\u0002\u0019I,7o\u001c7wK\u000ec\u0017m]:\u0016\t\r%3\u0011\u000b\u000b\u0007\u0007\u0017\u001a9f!\u0017\u0011\tMR5Q\n\t\u0005\u007fm\u001cy\u0005E\u0002W\u0007#\"qaa\u0015$\u0005\u0004\u0019)FA\u0001Y#\tQ&\u0007\u0003\u0004\u0002~\u000e\u0002\rA\u0010\u0005\t\u0007K\u0019\u0003\u0013!a\u0001K\u00061\"/Z:pYZ,7\t\\1tg\u0012\"WMZ1vYR$#'\u0006\u0003\u0004B\r}CaBB*I\t\u00071QK\u0001\u0013e\u0016\u001cx\u000e\u001c<f\u00072\f7o]\"bG\",G-\u0006\u0003\u0004f\r5DCBB4\u0007_\u001a\t\b\u0005\u00034\u0015\u000e%\u0004\u0003B |\u0007W\u00022AVB7\t\u001d\u0019\u0019&\nb\u0001\u0007+Ba!!@&\u0001\u0004q\u0004BBB\u0013K\u0001\u0007Q\r"
)
public final class ScalaSigReader {
   public static Iterable resolveClass$default$2() {
      return ScalaSigReader$.MODULE$.resolveClass$default$2();
   }

   public static Option resolveClass(final String c, final Iterable classLoaders) {
      return ScalaSigReader$.MODULE$.resolveClass(c, classLoaders);
   }

   public static Iterable companions$default$3() {
      return ScalaSigReader$.MODULE$.companions$default$3();
   }

   public static Option companions$default$2() {
      return ScalaSigReader$.MODULE$.companions$default$2();
   }

   public static Option companions(final String t, final Option companion, final Iterable classLoaders) {
      return ScalaSigReader$.MODULE$.companions(t, companion, classLoaders);
   }

   public static Vector ClassLoaders() {
      return ScalaSigReader$.MODULE$.ClassLoaders();
   }

   public static String OuterFieldName() {
      return ScalaSigReader$.MODULE$.OuterFieldName();
   }

   public static String ModuleFieldName() {
      return ScalaSigReader$.MODULE$.ModuleFieldName();
   }

   public static Option findScalaSig(final Class clazz) {
      return ScalaSigReader$.MODULE$.findScalaSig(clazz);
   }

   public static Class findArgType(final MethodSymbol s, final int argIdx, final List typeArgIndexes) {
      return ScalaSigReader$.MODULE$.findArgType(s, argIdx, typeArgIndexes);
   }

   public static Class findArgType(final MethodSymbol s, final int argIdx, final int typeArgIndex) {
      return ScalaSigReader$.MODULE$.findArgType(s, argIdx, typeArgIndex);
   }

   public static Seq findFields(final ClassSymbol c) {
      return ScalaSigReader$.MODULE$.findFields(c);
   }

   public static Option findApply(final ClassSymbol c, final List argNames) {
      return ScalaSigReader$.MODULE$.findApply(c, argNames);
   }

   public static Option findConstructor(final ClassSymbol c, final List argNames) {
      return ScalaSigReader$.MODULE$.findConstructor(c, argNames);
   }

   public static Option findCompanionObject(final ScalaSig sig, final Class clazz) {
      return ScalaSigReader$.MODULE$.findCompanionObject(sig, clazz);
   }

   public static ClassSymbol findCompanionObject(final Class clazz) {
      return ScalaSigReader$.MODULE$.findCompanionObject(clazz);
   }

   public static Option findClass(final ScalaSig sig, final Class clazz) {
      return ScalaSigReader$.MODULE$.findClass(sig, clazz);
   }

   public static ClassSymbol findClass(final Class clazz) {
      return ScalaSigReader$.MODULE$.findClass(clazz);
   }

   public static Class readField(final String name, final Class clazz, final int typeArgIndex) {
      return ScalaSigReader$.MODULE$.readField(name, clazz, typeArgIndex);
   }

   public static Class readConstructor(final String argName, final ScalaType clazz, final List typeArgIndexes, final List argNames) {
      return ScalaSigReader$.MODULE$.readConstructor(argName, clazz, typeArgIndexes, argNames);
   }

   public static Class readConstructor(final String argName, final ScalaType clazz, final int typeArgIndex, final List argNames) {
      return ScalaSigReader$.MODULE$.readConstructor(argName, clazz, typeArgIndex, argNames);
   }

   public static Class readConstructor(final String argName, final Class clazz, final List typeArgIndexes, final List argNames) {
      return ScalaSigReader$.MODULE$.readConstructor(argName, clazz, typeArgIndexes, argNames);
   }

   public static Class readConstructor(final String argName, final Class clazz, final int typeArgIndex, final List argNames) {
      return ScalaSigReader$.MODULE$.readConstructor(argName, clazz, typeArgIndex, argNames);
   }
}
