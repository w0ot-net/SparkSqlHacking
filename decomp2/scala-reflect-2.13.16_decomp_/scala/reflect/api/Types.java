package scala.reflect.api;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019-c\u0001DA4\u0003S\u0002\n1!\u0001\u0002x\u0019\r\u0003bBAA\u0001\u0011\u0005\u00111\u0011\u0003\b\u0003\u0017\u0003!\u0011AAG\u0011%\u0011Y\r\u0001b\u0001\u000e\u0003\u0011\u0019\u0001C\u0005\u0003N\u0002\u0011\rQ\"\u0001\u0003\u0004\u00199\u0011q\u0014\u0001\u0002\u0002\u0005\u0005\u0006bBAR\u000b\u0011\u0005\u0011Q\u0015\u0005\b\u0003O+a\u0011AAU\u0011\u001d\t),\u0002D\u0001\u0003SCq!a.\u0006\r\u0003\tI\fC\u0004\u0002^\u00161\t!a8\t\u000f\u0005\rXA\"\u0001\u0002f\"9\u0011q_\u0003\u0007\u0002\u0005\u0015\bbBA}\u000b\u0019\u0005\u00111 \u0005\b\u0003\u007f,a\u0011AAs\u0011\u001d\u0011\t!\u0002D\u0001\u0005\u0007AqAa\u0002\u0006\r\u0003\u0011I\u0001C\u0004\u0003\u0012\u00151\tAa\u0001\t\u000f\tMQA\"\u0001\u0003\u0004!9!1D\u0003\u0007\u0002\t\r\u0001b\u0002B\u000f\u000b\u0019\u0005!q\u0004\u0005\b\u0005K)a\u0011\u0001B\u0014\u0011\u001d\u0011Y#\u0002D\u0001\u0005[AqA!\r\u0006\r\u0003\u0011\u0019\u0004C\u0004\u0003D\u00151\tA!\u0012\t\u000f\t-SA\"\u0001\u0003N!9!QK\u0003\u0007\u0002\t\r\u0001b\u0002B,\u000b\u0019\u0005!1\u0001\u0005\b\u00053*a\u0011\u0001B\u0002\u0011\u001d\u0011Y&\u0002D\u0001\u0005;BqA!\u0019\u0006\r\u0003\u0011\u0019\u0007C\u0004\u0003n\u00151\tAa\u0019\t\u000f\t=TA\"\u0001\u00034!9!\u0011O\u0003\u0007\u0002\t\r\u0001b\u0002B:\u000b\u0019\u0005!1\u0001\u0005\b\u0005k*a\u0011\u0001B<\u0011\u001d\u0011\u0019)\u0002D\u0001\u0005\u000bCqAa$\u0006\r\u0003\u0011\t\nC\u0004\u0003\u0018\u00161\tA!'\t\u000f\t\u0015VA\"\u0001\u0003(\"9!QV\u0003\u0007\u0002\t=\u0006b\u0002B_\u000b\u0019\u0005!q\u0018\u0005\b\u0005\u0007,a\u0011\u0001Bc\t\u001d\u0011y\r\u0001B\u0001\u0005#4\u0011B!7\u0001!\u0003\r\nAa7\u0005\u000f\tu\u0007A!\u0001\u0003`\"I!q\u001e\u0001C\u0002\u001b\u0005!\u0011\u001f\u0004\b\u0005k\u0004\u0011\u0011\u0001B|\u0011\u001d\t\u0019k\fC\u0001\u0005sDqAa?0\r\u0003\u0011i\u0010C\u0004\u0004\u0006=\"\taa\u0002\u0007\u0017\t\u001d\b\u0001%A\u0012\u0002\t%(1\u001e\u0005\b\u0005\u0013\u001cd\u0011AAU\t\u001d\u0019\t\u0003\u0001B\u0001\u0007GA\u0011b!\r\u0001\u0005\u00045\taa\r\u0007\u000f\r]\u0002!!\u0001\u0004:!9\u00111U\u001c\u0005\u0002\rm\u0002b\u0002B~o\u0019\u00051Q\b\u0005\b\u0007\u000b9D\u0011AB%\r-\u0019Y\u0003\u0001I\u0001$\u0003\u0019ica\f\t\u000f\tE3H\"\u0001\u0003\u0004!9!\u0011Z\u001e\u0007\u0002\u0005%FaBB-\u0001\t\u000511\f\u0005\n\u0007[\u0002!\u0019!D\u0001\u0007_2qaa\u001d\u0001\u0003\u0003\u0019)\bC\u0004\u0002$\u0002#\taa\u001e\t\u000f\tm\bI\"\u0001\u0004z!91Q\u0001!\u0005\u0002\r\u0005eaCB2\u0001A\u0005\u0019\u0013AB3\u0007WBqaa\u001aE\r\u0003\u0011\u0019\u0001C\u0004\u0004j\u00113\tAa\u0001\u0005\u000f\rE\u0005A!\u0001\u0004\u0014\"I1q\u0016\u0001C\u0002\u001b\u00051\u0011\u0017\u0004\b\u0007k\u0003\u0011\u0011AB\\\u0011\u001d\t\u0019+\u0013C\u0001\u0007sCqAa?J\r\u0003\u0019Y\fC\u0004\u0004\u0006%#\ta!1\u0007\u0017\rm\u0005\u0001%A\u0012\u0002\ru5Q\u0016\u0005\b\u0007?ke\u0011ABQ\t\u001d\u0019y\r\u0001B\u0001\u0007#D\u0011b!9\u0001\u0005\u00045\taa9\u0007\u000f\r\u001d\b!!\u0001\u0004j\"9\u00111U)\u0005\u0002\r-\bb\u0002B~#\u001a\u00051Q\u001e\u0005\b\u0007\u000b\tF\u0011AB}\r-\u0019I\u000e\u0001I\u0001$\u0003\u0019Yna8\t\u000f\tESK\"\u0001\u0003\u0004!9!\u0011Z+\u0007\u0002\u0005%\u0006bBBo+\u001a\u0005!Q\f\u0003\b\t\u0017\u0001!\u0011\u0001C\u0007\r%!)\u0002\u0001I\u0001$\u0003!9\u0002B\u0004\u0005\u001a\u0001\u0011\t\u0001b\u0007\t\u0013\u00115\u0002A1A\u0007\u0002\u0011=ba\u0002C\u001a\u0001\u0005\u0005AQ\u0007\u0005\b\u0003GkF\u0011\u0001C\u001c\u0011\u001d\u0011Y0\u0018D\u0001\tsAqa!\u0002^\t\u0003!9\u0005C\u0004\u0004\u0006u#\t\u0001b\u0016\u0007\u0017\u0011\r\u0002\u0001%A\u0012\u0002\u0011\u0015B\u0011\u0006\u0005\b\tO\u0011g\u0011\u0001B/\u0011\u001d\t9P\u0019D\u0001\u0003K$q\u0001\"\u001a\u0001\u0005\u0003!9\u0007C\u0005\u0005v\u0001\u0011\rQ\"\u0001\u0005x\u00199A1\u0010\u0001\u0002\u0002\u0011u\u0004bBARO\u0012\u0005Aq\u0010\u0005\b\u0005w<g\u0011\u0001CA\u0011\u001d\u0019)a\u001aC\u0001\t\u001331\u0002b\u001c\u0001!\u0003\r\n\u0001\"\u001d\u0005t!9AqE6\u0007\u0002\tu\u0003bBA|W\u001a\u0005\u0011Q\u001d\u0005\b\u0003k[g\u0011AAU\t\u001d!Y\n\u0001B\u0001\t;C\u0011\u0002\",\u0001\u0005\u00045\t\u0001b,\u0007\u000f\u0011M\u0006!!\u0001\u00056\"9\u00111U9\u0005\u0002\u0011]\u0006b\u0002B~c\u001a\u0005A\u0011\u0018\u0005\b\u0007\u000b\tH\u0011\u0001Ca\r-!)\u000b\u0001I\u0001$\u0003!9\u000bb+\t\u000f\u0011%VO\"\u0001\u00034!9!\u0011O;\u0007\u0002\t\rAa\u0002Ci\u0001\t\u0005A1\u001b\u0005\n\tC\u0004!\u0019!D\u0001\tG4q\u0001b:\u0001\u0003\u0003!I\u000fC\u0004\u0002$j$\t\u0001b;\t\u000f\tm(P\"\u0001\u0005n\"91Q\u0001>\u0005\u0002\u0011Eha\u0003Cn\u0001A\u0005\u0019\u0013\u0001Co\t?DqA!\u001d\u007f\r\u0003\u0011\u0019\u0001B\u0004\u0005\u0000\u0002\u0011\t!\"\u0001\t\u0013\u0015=\u0001A1A\u0007\u0002\u0015EaaBC\u000b\u0001\u0005\u0005Qq\u0003\u0005\t\u0003G\u000b)\u0001\"\u0001\u0006\u001a!A!1`A\u0003\r\u0003)Y\u0002\u0003\u0005\u0004\u0006\u0005\u0015A\u0011AC\u0010\r-)I\u0001\u0001I\u0001$\u0003)Y!\"\u0004\t\u0011\t=\u0014Q\u0002D\u0001\u0005gA\u0001B!\u001d\u0002\u000e\u0019\u0005!1\u0001\u0003\b\u000b_\u0001!\u0011AC\u0019\u0011%)\u0019\u0005\u0001b\u0001\u000e\u0003))EB\u0004\u0006J\u0001\t\t!b\u0013\t\u0011\u0005\r\u0016q\u0003C\u0001\u000b\u001bB\u0001Ba?\u0002\u0018\u0019\u0005Qq\n\u0005\t\u0007\u000b\t9\u0002\"\u0001\u0006T\u0019YQ\u0011\b\u0001\u0011\u0002G\u0005Q1HC!\u0011!)i$a\b\u0007\u0002\tM\u0002\u0002CC \u0003?1\tAa\u0001\u0005\u000f\u0015\r\u0004A!\u0001\u0006f!IQ1\u0011\u0001C\u0002\u001b\u0005QQ\u0011\u0004\b\u000b\u0013\u0003\u0011\u0011ACF\u0011!\t\u0019+!\u000b\u0005\u0002\u00155\u0005\u0002\u0003B~\u0003S1\t!b$\t\u0011\r\u0015\u0011\u0011\u0006C\u0001\u000b/31\"\"\u001c\u0001!\u0003\r\n!b\u001c\u0006\u0002\"AQ\u0011OA\u0019\r\u0003)\u0019\b\u0003\u0005\u0006@\u0005Eb\u0011\u0001B\u0002\t\u001d)9\u000b\u0001B\u0001\u000bSC\u0011\"b/\u0001\u0005\u00045\t!\"0\u0007\u000f\u0015\u0005\u0007!!\u0001\u0006D\"A\u00111UA\u001e\t\u0003))\r\u0003\u0005\u0003|\u0006mb\u0011ACd\u0011!\u0019)!a\u000f\u0005\u0002\u0015-gaCCY\u0001A\u0005\u0019\u0013ACZ\u000bsC\u0001\"\".\u0002D\u0019\u0005!1\u0001\u0005\t\u000bo\u000b\u0019E\"\u0001\u0003\u0004!IQ1\u001c\u0001C\u0002\u001b\u0005!1\u0001\u0003\b\u000b;\u0004!\u0011ACp\u0011%)\t\u0010\u0001b\u0001\u000e\u0003)\u0019PB\u0004\u0006x\u0002\t\t!\"?\t\u0011\u0005\r\u0016q\nC\u0001\u000bwD\u0001Ba?\u0002P\u0019\u0005QQ \u0005\t\u0007\u000b\ty\u0005\"\u0001\u0007\u0004\u0019YQq\u001d\u0001\u0011\u0002G\u0005Q\u0011^Cx\u0011!)Y/a\u0016\u0007\u0002\u00155\bb\u0002D\t\u0001\u0019\u0005a1\u0003\u0005\b\r3\u0001a\u0011\u0001D\u000e\u0011\u001d1\t\u0003\u0001D\u0001\rGAqA\"\t\u0001\r\u00031Y\u0003C\u0004\u0007\"\u00011\tAb\u000e\t\u000f\u0019\u0005\u0002A\"\u0001\u0007>\t)A+\u001f9fg*!\u00111NA7\u0003\r\t\u0007/\u001b\u0006\u0005\u0003_\n\t(A\u0004sK\u001adWm\u0019;\u000b\u0005\u0005M\u0014!B:dC2\f7\u0001A\n\u0004\u0001\u0005e\u0004\u0003BA>\u0003{j!!!\u001d\n\t\u0005}\u0014\u0011\u000f\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\t\t)\t\u0005\u0003\u0002|\u0005\u001d\u0015\u0002BAE\u0003c\u0012A!\u00168ji\n!A+\u001f9f#\u0011\ty)!&\u0011\t\u0005m\u0014\u0011S\u0005\u0005\u0003'\u000b\tH\u0001\u0003Ok2d'CBAL\u0003s\nYJ\u0002\u0004\u0002\u001a\u0002\u0001\u0011Q\u0013\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004\u0003;+Q\"\u0001\u0001\u0003\u000fQK\b/Z!qSN\u0019Q!!\u001f\u0002\rqJg.\u001b;?)\t\tY*\u0001\u0006uKJl7+_7c_2,\"!a+\u0011\t\u0005u\u0015QV\u0005\u0005\u0003_\u000b\tL\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0005\u0003g\u000bIGA\u0004Ts6\u0014w\u000e\\:\u0002\u0015QL\b/Z*z[\n|G.A\u0006eK\u000ed\u0017M]1uS>tG\u0003BAV\u0003wCq!!0\n\u0001\u0004\ty,\u0001\u0003oC6,\u0007\u0003BAO\u0003\u0003LA!a1\u0002F\n!a*Y7f\u0013\u0011\t9-!\u001b\u0003\u000b9\u000bW.Z:)\u0017%\tY-!5\u0002T\u0006]\u0017\u0011\u001c\t\u0005\u0003w\ni-\u0003\u0003\u0002P\u0006E$A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAAk\u0003I)8/\u001a\u0011aI\u0016\u001cG\u000e\u0019\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0005\u0005m\u0017A\u0002\u001a/cEr\u0003'\u0001\u0003eK\u000edG\u0003BAV\u0003CDq!!0\u000b\u0001\u0004\ty,\u0001\u0007eK\u000ed\u0017M]1uS>t7/\u0006\u0002\u0002hB!\u0011QTAu\u0013\u0011\tY/!<\u0003\u00175+WNY3s'\u000e|\u0007/Z\u0005\u0005\u0003_\fIG\u0001\u0004TG>\u0004Xm\u001d\u0015\f\u0017\u0005-\u0017\u0011[Az\u0003/\fI.\t\u0002\u0002v\u0006\u0019Ro]3!A\u0012,7\r\\:aA%t7\u000f^3bI\u0006)A-Z2mg\u00061Q.Z7cKJ$B!a+\u0002~\"9\u0011QX\u0007A\u0002\u0005}\u0016aB7f[\n,'o]\u0001\nG>l\u0007/\u00198j_:,\"A!\u0002\u0011\u0007\u0005u%!A\u0007uC.,7\u000fV=qK\u0006\u0013xm]\u000b\u0003\u0005\u0017\u0001B!a\u001f\u0003\u000e%!!qBA9\u0005\u001d\u0011un\u001c7fC:\fq\u0002^=qK\u000e{gn\u001d;sk\u000e$xN]\u0001\n]>\u0014X.\u00197ju\u0016D3BEAf\u0003#\u00149\"a6\u0002Z\u0006\u0012!\u0011D\u0001%kN,\u0007\u0005\u00193fC2L\u0017m\u001d1!_J\u0004\u0003-\u001a;b\u000bb\u0004\u0018M\u001c3aA%t7\u000f^3bI\u0006IQ\r^1FqB\fg\u000eZ\u0001\u0011I1,7o\u001d\u0013d_2|g\u000e\n7fgN$BAa\u0003\u0003\"!9!1\u0005\u000bA\u0002\t\u0015\u0011\u0001\u0002;iCR\fQc^3bW~#C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000f\u0006\u0003\u0003\f\t%\u0002b\u0002B\u0012+\u0001\u0007!QA\u0001\rI\u0015\fHeY8m_:$S-\u001d\u000b\u0005\u0005\u0017\u0011y\u0003C\u0004\u0003$Y\u0001\rA!\u0002\u0002\u0017\t\f7/Z\"mCN\u001cXm]\u000b\u0003\u0005k\u0001bAa\u000e\u0003>\u0005-f\u0002BA>\u0005sIAAa\u000f\u0002r\u00059\u0001/Y2lC\u001e,\u0017\u0002\u0002B \u0005\u0003\u0012A\u0001T5ti*!!1HA9\u0003!\u0011\u0017m]3UsB,G\u0003\u0002B\u0003\u0005\u000fBqA!\u0013\u0019\u0001\u0004\tY+A\u0003dY\u0006T(0\u0001\u0006bgN+WM\u001c$s_6$bA!\u0002\u0003P\tM\u0003b\u0002B)3\u0001\u0007!QA\u0001\u0004aJ,\u0007b\u0002B%3\u0001\u0007\u00111V\u0001\bKJ\f7/\u001e:f\u0003\u00159\u0018\u000eZ3o\u0003\u001d!W-\u00197jCN\f\u0001\u0002^=qK\u0006\u0013xm]\u000b\u0003\u0005?\u0002bAa\u000e\u0003>\t\u0015\u0011a\u00029be\u0006l7o]\u000b\u0003\u0005K\u0002bAa\u000e\u0003>\tU\u0002f\u0003\u0010\u0002L\u0006E'\u0011NAl\u00033\f#Aa\u001b\u00021U\u001cX\r\t1qCJ\fW\u000eT5tiN\u0004\u0007%\u001b8ti\u0016\fG-\u0001\u0006qCJ\fW\u000eT5tiN\f!\u0002^=qKB\u000b'/Y7t\u0003)\u0011Xm];miRK\b/Z\u0001\u0010M&t\u0017\r\u001c*fgVdG\u000fV=qK\u00061qN]#mg\u0016$BA!\u0002\u0003z!A!1P\u0012\u0005\u0002\u0004\u0011i(A\u0002bYR\u0004b!a\u001f\u0003\u0000\t\u0015\u0011\u0002\u0002BA\u0003c\u0012\u0001\u0002\u00102z]\u0006lWMP\u0001\u0012gV\u00147\u000f^5ukR,7+_7c_2\u001cHC\u0002B\u0003\u0005\u000f\u0013Y\tC\u0004\u0003\n\u0012\u0002\rA!\u000e\u0002\t\u0019\u0014x.\u001c\u0005\b\u0005\u001b#\u0003\u0019\u0001B\u001b\u0003\t!x.A\btk\n\u001cH/\u001b;vi\u0016$\u0016\u0010]3t)\u0019\u0011)Aa%\u0003\u0016\"9!\u0011R\u0013A\u0002\tU\u0002b\u0002BGK\u0001\u0007!qL\u0001\u0004[\u0006\u0004H\u0003\u0002B\u0003\u00057CqA!('\u0001\u0004\u0011y*A\u0001g!!\tYH!)\u0003\u0006\t\u0015\u0011\u0002\u0002BR\u0003c\u0012\u0011BR;oGRLwN\\\u0019\u0002\u000f\u0019|'/Z1dQR!\u0011Q\u0011BU\u0011\u001d\u0011ij\na\u0001\u0005W\u0003\u0002\"a\u001f\u0003\"\n\u0015\u0011QQ\u0001\u0005M&tG\r\u0006\u0003\u00032\n]\u0006CBA>\u0005g\u0013)!\u0003\u0003\u00036\u0006E$AB(qi&|g\u000eC\u0004\u0003:\"\u0002\rAa/\u0002\u0003A\u0004\u0002\"a\u001f\u0003\"\n\u0015!1B\u0001\u0007KbL7\u000f^:\u0015\t\t-!\u0011\u0019\u0005\b\u0005sK\u0003\u0019\u0001B^\u0003!\u0019wN\u001c;bS:\u001cH\u0003\u0002B\u0006\u0005\u000fDqA!3+\u0001\u0004\tY+A\u0002ts6\faAT8UsB,\u0017\u0001\u0003(p!J,g-\u001b=\u0003\u001bMKgn\u001a7fi>tG+\u001f9f#\u0011\tyIa5\u0013\r\tU'q\u001bB\u0003\r\u0019\tI\n\u0001\u0001\u0003TB\u0019\u0011Q\u0014\u0017\u0003!MKgn\u001a7fi>tG+\u001f9f\u0003BL7c\u0001\u0017\u0002z\tAA\u000b[5t)f\u0004X-\u0005\u0003\u0002\u0010\n\u0005(C\u0002Br\u0005K\u0014iO\u0002\u0004\u0002\u001a\u0002\u0001!\u0011\u001d\t\u0004\u0003;\u001b$a\u0003+iSN$\u0016\u0010]3Ba&\u001c2aMAN!\r\ti*\f\t\u0004\u0003;[\u0013\u0001\u0003+iSN$\u0016\u0010]3\u0016\u0005\tM\bcAAO_\t\tB\u000b[5t)f\u0004X-\u0012=ue\u0006\u001cGo\u001c:\u0014\u0007=\nI\b\u0006\u0002\u0003t\u00069QO\\1qa2LH\u0003\u0002B\u0000\u0007\u0003\u0001b!a\u001f\u00034\u0006-\u0006bBB\u0002c\u0001\u0007!1^\u0001\u0004iB,\u0017!B1qa2LH\u0003BB\u0005\u00073!BA!\u0002\u0004\f!91Q\u0002\u001aA\u0004\r=\u0011!\u0002;pW\u0016t\u0007\u0003BAO\u0007#IAaa\u0005\u0004\u0016\tY1i\\7qCR$vn[3o\u0013\u0011\u00199\"!\u001b\u0003\u0013%sG/\u001a:oC2\u001c\bb\u0002Bee\u0001\u0007\u00111\u0016\u0015\fe\u0005-\u0017\u0011[B\u000f\u0003/\fI.\t\u0002\u0004 \u0005yRo]3!A&tG/\u001a:oC2tC\u000f[5t)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0003\u0015MKgn\u001a7f)f\u0004X-\u0005\u0003\u0002\u0010\u000e\u0015\"CBB\u0014\u0007S\u0011iO\u0002\u0004\u0002\u001a\u0002\u00011Q\u0005\t\u0004\u0003;[$!D*j]\u001edW\rV=qK\u0006\u0003\u0018nE\u0002<\u00037\u00032!!(6\u0003)\u0019\u0016N\\4mKRK\b/Z\u000b\u0003\u0007k\u00012!!(8\u0005M\u0019\u0016N\\4mKRK\b/Z#yiJ\f7\r^8s'\r9\u0014\u0011\u0010\u000b\u0003\u0007k!Baa\u0010\u0004HA1\u00111\u0010BZ\u0007\u0003\u0002\u0002\"a\u001f\u0004D\t\u0015\u00111V\u0005\u0005\u0007\u000b\n\tH\u0001\u0004UkBdWM\r\u0005\b\u0007\u0007I\u0004\u0019AB\u0018)\u0019\u0019Yea\u0014\u0004RQ!!QAB'\u0011\u001d\u0019iA\u000fa\u0002\u0007\u001fAqA!\u0015;\u0001\u0004\u0011)\u0001C\u0004\u0003Jj\u0002\r!a+)\u0017i\nY-!5\u0004V\u0005]\u0017\u0011\\\u0011\u0003\u0007/\nQ(^:fA\u0001\u001cE.Y:t'fl'm\u001c7/i\"L7\u000f\u0015:fM&D\b\rI8sA\u0001Lg\u000e^3s]\u0006dgf]5oO2,G+\u001f9fA\u0002Jgn\u001d;fC\u0012\u0014\u0011bU;qKJ$\u0016\u0010]3\u0012\t\u0005=5Q\f\n\u0007\u0007?\u001a\tG!<\u0007\r\u0005e\u0005\u0001AB/!\r\ti\n\u0012\u0002\r'V\u0004XM\u001d+za\u0016\f\u0005/[\n\u0004\t\u0006m\u0015a\u0002;iSN$\b/Z\u0001\tgV\u0004XM\u001d;qKB\u0019\u0011Q\u0014 \u0002\u0013M+\b/\u001a:UsB,WCAB9!\r\ti\n\u0011\u0002\u0013'V\u0004XM\u001d+za\u0016,\u0005\u0010\u001e:bGR|'oE\u0002A\u0003s\"\"a!\u001d\u0015\t\rm4q\u0010\t\u0007\u0003w\u0012\u0019l! \u0011\u0011\u0005m41\tB\u0003\u0005\u000bAqaa\u0001C\u0001\u0004\u0019Y\u0007\u0006\u0004\u0004\u0004\u000e\u001d5\u0011\u0012\u000b\u0005\u0005\u000b\u0019)\tC\u0004\u0004\u000e\r\u0003\u001daa\u0004\t\u000f\r\u001d4\t1\u0001\u0003\u0006!91\u0011N\"A\u0002\t\u0015\u0001fC\"\u0002L\u0006E7QRAl\u00033\f#aa$\u0002{U\u001cX\r\t1DY\u0006\u001c8oU=nE>dgf];qKJ\u0004&/\u001a4jq\u0002\u0004sN\u001d\u0011aS:$XM\u001d8bY:\u001aX\u000f]3s)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0003\u0019\r{gn\u001d;b]R$\u0016\u0010]3\u0012\t\u0005=5Q\u0013\n\u0007\u0007/\u001bIJ!<\u0007\r\u0005e\u0005\u0001ABK!\r\ti*\u0014\u0002\u0010\u0007>t7\u000f^1oiRK\b/Z!qSN\u0019Q*a'\u0002\u000bY\fG.^3\u0016\u0005\r\r\u0006\u0003BAO\u0007KKAaa*\u0004*\nA1i\u001c8ti\u0006tG/\u0003\u0003\u0004,\u0006%$!C\"p]N$\u0018M\u001c;t!\r\tijR\u0001\r\u0007>t7\u000f^1oiRK\b/Z\u000b\u0003\u0007g\u00032!!(J\u0005U\u0019uN\\:uC:$H+\u001f9f\u000bb$(/Y2u_J\u001c2!SA=)\t\u0019\u0019\f\u0006\u0003\u0004>\u000e}\u0006CBA>\u0005g\u001b\u0019\u000bC\u0004\u0004\u0004-\u0003\ra!,\u0015\t\r\r7q\u0019\u000b\u0005\u0007[\u001b)\rC\u0004\u0004\u000e1\u0003\u001daa\u0004\t\u000f\r}E\n1\u0001\u0004$\"ZA*a3\u0002R\u000e-\u0017q[AmC\t\u0019i-\u0001\u001avg\u0016\u0004\u0003M^1mk\u0016tC\u000f]3aA=\u0014\b\u0005Y5oi\u0016\u0014h.\u00197/G>t7\u000f^1oiRK\b/\u001a1!S:\u001cH/Z1e\u0005\u001d!\u0016\u0010]3SK\u001a\fB!a$\u0004TJ11Q[Bl\u0005\u000b1a!!'\u0001\u0001\rM\u0007cAAO+\nQA+\u001f9f%\u00164\u0017\t]5\u0014\u0007U\u000bY*\u0001\u0003be\u001e\u001c\bcAAO\u001f\u00069A+\u001f9f%\u00164WCABs!\r\ti*\u0015\u0002\u0011)f\u0004XMU3g\u000bb$(/Y2u_J\u001c2!UA=)\t\u0019)\u000f\u0006\u0003\u0004p\u000e]\bCBA>\u0005g\u001b\t\u0010\u0005\u0006\u0002|\rM(QAAV\u0005?JAa!>\u0002r\t1A+\u001e9mKNBqaa\u0001T\u0001\u0004\u0019y\u000e\u0006\u0005\u0004|\u000e}H\u0011\u0001C\u0002)\u0011\u0011)a!@\t\u000f\r5A\u000bq\u0001\u0004\u0010!9!\u0011\u000b+A\u0002\t\u0015\u0001b\u0002Be)\u0002\u0007\u00111\u0016\u0005\b\u0007;$\u0006\u0019\u0001B0Q-!\u00161ZAi\t\u000f\t9.!7\"\u0005\u0011%\u0011AH;tK\u0002\u0002\u0017N\u001c;fe:\fGN\f;za\u0016\u0014VM\u001a1!S:\u001cH/Z1e\u00051\u0019u.\u001c9pk:$G+\u001f9f#\u0011\ty\tb\u0004\u0013\r\u0011EA1\u0003B\u0003\r\u0019\tI\n\u0001\u0001\u0005\u0010A\u0019\u0011Q\u0014.\u0003\u001f\r{W\u000e]8v]\u0012$\u0016\u0010]3Ba&\u001c2AWA=\u0005-\u0011VMZ5oK\u0012$\u0016\u0010]3\u0012\t\u0005=EQ\u0004\n\u0007\t?!\t\u0003b\u000b\u0007\r\u0005e\u0005\u0001\u0001C\u000f!\r\tiJ\u0019\u0002\u000f%\u00164\u0017N\\3e)f\u0004X-\u00119j'\r\u0011\u00171T\u0001\ba\u0006\u0014XM\u001c;t!\r\tij\u0017\t\u0004\u0003;K\u0016a\u0003*fM&tW\r\u001a+za\u0016,\"\u0001\"\r\u0011\u0007\u0005uUL\u0001\u000bSK\u001aLg.\u001a3UsB,W\t\u001f;sC\u000e$xN]\n\u0004;\u0006eDC\u0001C\u0019)\u0011!Y\u0004\"\u0012\u0011\r\u0005m$1\u0017C\u001f!!\tYha\u0011\u0003`\u0011}\u0002\u0003BAO\t\u0003JA\u0001b\u0011\u0002n\n)1kY8qK\"911A0A\u0002\u0011%BC\u0002C%\t\u001b\"y\u0005\u0006\u0003\u0005*\u0011-\u0003bBB\u0007A\u0002\u000f1q\u0002\u0005\b\tO\u0001\u0007\u0019\u0001B0\u0011\u001d\t9\u0010\u0019a\u0001\t\u007fA3\u0002YAf\u0003#$\u0019&a6\u0002Z\u0006\u0012AQK\u0001#kN,\u0007\u0005Y5oi\u0016\u0014h.\u00197/e\u00164\u0017N\\3e)f\u0004X\r\u0019\u0011j]N$X-\u00193\u0015\u0011\u0011eCQ\fC0\tC\"B\u0001\"\u000b\u0005\\!91QB1A\u0004\r=\u0001b\u0002C\u0014C\u0002\u0007!q\f\u0005\b\u0003o\f\u0007\u0019\u0001C \u0011\u001d\u0011I%\u0019a\u0001\u0003WC3\"YAf\u0003#$\u0019&a6\u0002Z\ni1\t\\1tg&sgm\u001c+za\u0016\fB!a$\u0005jI1A1\u000eC7\tW1a!!'\u0001\u0001\u0011%\u0004cAAOW\n\u00012\t\\1tg&sgm\u001c+za\u0016\f\u0005/[\n\u0004W\u0006m\u0005cAAOK\u0006i1\t\\1tg&sgm\u001c+za\u0016,\"\u0001\"\u001f\u0011\u0007\u0005uuM\u0001\fDY\u0006\u001c8/\u00138g_RK\b/Z#yiJ\f7\r^8s'\r9\u0017\u0011\u0010\u000b\u0003\ts\"B\u0001b!\u0005\bB1\u00111\u0010BZ\t\u000b\u0003\"\"a\u001f\u0004t\n}CqHAV\u0011\u001d\u0019\u0019!\u001ba\u0001\tg\"\u0002\u0002b#\u0005\u0010\u0012EE1\u0013\u000b\u0005\tg\"i\tC\u0004\u0004\u000e)\u0004\u001daa\u0004\t\u000f\u0011\u001d\"\u000e1\u0001\u0003`!9\u0011q\u001f6A\u0002\u0011}\u0002bBA[U\u0002\u0007\u00111\u0016\u0015\fU\u0006-\u0017\u0011\u001bCL\u0003/\fI.\t\u0002\u0005\u001a\u0006!So]3!A&tG/\u001a:oC2t3\r\\1tg&sgm\u001c+za\u0016\u0004\u0007%\u001b8ti\u0016\fGM\u0001\u0006NKRDw\u000e\u001a+za\u0016\fB!a$\u0005 J1A\u0011\u0015CR\u0005\u000b1a!!'\u0001\u0001\u0011}\u0005cAAOk\niQ*\u001a;i_\u0012$\u0016\u0010]3Ba&\u001c2!^AN\u0003\u0019\u0001\u0018M]1ngB\u0019\u0011QT8\u0002\u00155+G\u000f[8e)f\u0004X-\u0006\u0002\u00052B\u0019\u0011QT9\u0003'5+G\u000f[8e)f\u0004X-\u0012=ue\u0006\u001cGo\u001c:\u0014\u0007E\fI\b\u0006\u0002\u00052R!A1\u0018C`!\u0019\tYHa-\u0005>BA\u00111PB\"\u0005k\u0011)\u0001C\u0004\u0004\u0004M\u0004\r\u0001b+\u0015\r\u0011\rGq\u0019Ce)\u0011!Y\u000b\"2\t\u000f\r5A\u000fq\u0001\u0004\u0010!9A\u0011\u0016;A\u0002\tU\u0002b\u0002B9i\u0002\u0007!Q\u0001\u0015\fi\u0006-\u0017\u0011\u001bCg\u0003/\fI.\t\u0002\u0005P\u0006\tSo]3!A&tG/\u001a:oC2tS.\u001a;i_\u0012$\u0016\u0010]3aA%t7\u000f^3bI\n\tb*\u001e7mCJLX*\u001a;i_\u0012$\u0016\u0010]3\u0012\t\u0005=EQ\u001b\n\u0007\t/$IN!\u0002\u0007\r\u0005e\u0005\u0001\u0001Ck!\r\tiJ \u0002\u0015\u001dVdG.\u0019:z\u001b\u0016$\bn\u001c3UsB,\u0017\t]5\u0014\u0007y\fY\nE\u0002\u0002\u001eb\f\u0011CT;mY\u0006\u0014\u00180T3uQ>$G+\u001f9f+\t!)\u000fE\u0002\u0002\u001ej\u0014!DT;mY\u0006\u0014\u00180T3uQ>$G+\u001f9f\u000bb$(/Y2u_J\u001c2A_A=)\t!)\u000f\u0006\u0003\u00032\u0012=\bbBB\u0002y\u0002\u0007Aq\u001c\u000b\u0005\tg$9\u0010\u0006\u0003\u0005`\u0012U\bbBB\u0007{\u0002\u000f1q\u0002\u0005\b\u0005cj\b\u0019\u0001B\u0003Q-i\u00181ZAi\tw\f9.!7\"\u0005\u0011u\u0018\u0001K;tK\u0002\u0002\u0017N\u001c;fe:\fGN\f8vY2\f'/_'fi\"|G\rV=qK\u0002\u0004\u0013N\\:uK\u0006$'\u0001\u0003)pYf$\u0016\u0010]3\u0012\t\u0005=U1\u0001\n\u0007\u000b\u000b)9A!\u0002\u0007\r\u0005e\u0005\u0001AC\u0002!\u0011\ti*!\u0004\u0003\u0017A{G.\u001f+za\u0016\f\u0005/[\n\u0005\u0003\u001b\tY\n\u0005\u0003\u0002\u001e\u0006\u0005\u0011\u0001\u0003)pYf$\u0016\u0010]3\u0016\u0005\u0015M\u0001\u0003BAO\u0003\u000b\u0011\u0011\u0003U8msRK\b/Z#yiJ\f7\r^8s'\u0011\t)!!\u001f\u0015\u0005\u0015MA\u0003\u0002C^\u000b;A\u0001ba\u0001\u0002\n\u0001\u0007QQ\u0002\u000b\u0007\u000bC))#b\n\u0015\t\u00155Q1\u0005\u0005\t\u0007\u001b\tY\u0001q\u0001\u0004\u0010!A!qNA\u0006\u0001\u0004\u0011)\u0004\u0003\u0005\u0003r\u0005-\u0001\u0019\u0001B\u0003Q1\tY!a3\u0002R\u0016-\u0012q[AmC\t)i#A\u0010vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018q_2LH+\u001f9fA\u0002Jgn\u001d;fC\u0012\u0014q\"\u0012=jgR,g\u000e^5bYRK\b/Z\t\u0005\u0003\u001f+\u0019D\u0005\u0004\u00066\u0015]\"Q\u0001\u0004\u0007\u00033\u0003\u0001!b\r\u0011\t\u0005u\u0015q\u0004\u0002\u0013\u000bbL7\u000f^3oi&\fG\u000eV=qK\u0006\u0003\u0018n\u0005\u0003\u0002 \u0005m\u0015AC9vC:$\u0018NZ5fI\u0006QQO\u001c3fe2L\u0018N\\4\u0011\t\u0005u\u00151C\u0001\u0010\u000bbL7\u000f^3oi&\fG\u000eV=qKV\u0011Qq\t\t\u0005\u0003;\u000b9B\u0001\rFq&\u001cH/\u001a8uS\u0006dG+\u001f9f\u000bb$(/Y2u_J\u001cB!a\u0006\u0002zQ\u0011Qq\t\u000b\u0005\tw+\t\u0006\u0003\u0005\u0004\u0004\u0005m\u0001\u0019AC!)\u0019))&\"\u0017\u0006\\Q!Q\u0011IC,\u0011!\u0019i!!\bA\u0004\r=\u0001\u0002CC\u001f\u0003;\u0001\rA!\u000e\t\u0011\u0015}\u0012Q\u0004a\u0001\u0005\u000bAC\"!\b\u0002L\u0006EWqLAl\u00033\f#!\"\u0019\u0002MU\u001cX\r\t1j]R,'O\\1m]\u0015D\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016\u0004\u0007%\u001b8ti\u0016\fGMA\u0007B]:|G/\u0019;fIRK\b/Z\t\u0005\u0003\u001f+9G\u0005\u0004\u0006j\u0015-$Q\u0001\u0004\u0007\u00033\u0003\u0001!b\u001a\u0011\t\u0005u\u0015\u0011\u0007\u0002\u0011\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3Ba&\u001cB!!\r\u0002\u001c\u0006Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t+\t))\b\u0005\u0004\u00038\tuRq\u000f\t\u0005\u0003;+I(\u0003\u0003\u0006|\u0015u$AC!o]>$\u0018\r^5p]&!QqPA5\u0005-\teN\\8uCRLwN\\:\u0011\t\u0005u\u0015QE\u0001\u000e\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3\u0016\u0005\u0015\u001d\u0005\u0003BAO\u0003S\u0011a#\u00118o_R\fG/\u001a3UsB,W\t\u001f;sC\u000e$xN]\n\u0005\u0003S\tI\b\u0006\u0002\u0006\bR!Q\u0011SCK!\u0019\tYHa-\u0006\u0014BA\u00111PB\"\u000bk\u0012)\u0001\u0003\u0005\u0004\u0004\u00055\u0002\u0019ACA)\u0019)I*\"(\u0006 R!Q\u0011QCN\u0011!\u0019i!a\fA\u0004\r=\u0001\u0002CC9\u0003_\u0001\r!\"\u001e\t\u0011\u0015}\u0012q\u0006a\u0001\u0005\u000bAC\"a\f\u0002L\u0006EW1UAl\u00033\f#!\"*\u0002IU\u001cX\r\t1j]R,'O\\1m]\u0005tgn\u001c;bi\u0016$G+\u001f9fA\u0002Jgn\u001d;fC\u0012\u0014!\u0002V=qK\n{WO\u001c3t#\u0011\ty)b+\u0013\r\u00155Vq\u0016B\u0003\r\u0019\tI\n\u0001\u0001\u0006,B!\u0011QTA\"\u00055!\u0016\u0010]3C_VtGm]!qSN!\u00111IAN\u0003\taw.\u0001\u0002iSB!\u0011QTA\u001c\u0003)!\u0016\u0010]3C_VtGm]\u000b\u0003\u000b\u007f\u0003B!!(\u0002<\t\u0019B+\u001f9f\u0005>,h\u000eZ:FqR\u0014\u0018m\u0019;peN!\u00111HA=)\t)y\f\u0006\u0003\u0004|\u0015%\u0007\u0002CB\u0002\u0003\u007f\u0001\r!\"/\u0015\r\u00155W\u0011[Cj)\u0011)I,b4\t\u0011\r5\u0011\u0011\ta\u0002\u0007\u001fA\u0001\"\".\u0002B\u0001\u0007!Q\u0001\u0005\t\u000bo\u000b\t\u00051\u0001\u0003\u0006!b\u0011\u0011IAf\u0003#,9.a6\u0002Z\u0006\u0012Q\u0011\\\u0001\"kN,\u0007\u0005Y5oi\u0016\u0014h.\u00197/if\u0004XMQ8v]\u0012\u001c\b\rI5ogR,\u0017\rZ\u0001\r/&dGmY1sIRK\b/\u001a\u0002\u0014\u0005>,h\u000eZ3e/&dGmY1sIRK\b/Z\t\u0005\u0003\u001f+\tO\u0005\u0004\u0006d\u0016\u0015(Q\u0001\u0004\u0007\u00033\u0003\u0001!\"9\u0011\t\u0005u\u0015q\u000b\u0002\u0017\u0005>,h\u000eZ3e/&dGmY1sIRK\b/Z!qSN!\u0011qKAN\u0003\u0019\u0011w.\u001e8egV\u0011Q\u0011\u0018\t\u0005\u0003;\u000bY%A\nC_VtG-\u001a3XS2$7-\u0019:e)f\u0004X-\u0006\u0002\u0006vB!\u0011QTA(\u0005q\u0011u.\u001e8eK\u0012<\u0016\u000e\u001c3dCJ$G+\u001f9f\u000bb$(/Y2u_J\u001cB!a\u0014\u0002zQ\u0011QQ\u001f\u000b\u0005\u000b\u007f4\t\u0001\u0005\u0004\u0002|\tMV\u0011\u0018\u0005\t\u0007\u0007\t\u0019\u00061\u0001\u0006pR!aQ\u0001D\u0005)\u0011)yOb\u0002\t\u0011\r5\u0011Q\u000ba\u0002\u0007\u001fA\u0001\"b;\u0002V\u0001\u0007Q\u0011\u0018\u0015\r\u0003+\nY-!5\u0007\u000e\u0005]\u0017\u0011\\\u0011\u0003\r\u001f\t!&^:fA\u0001Lg\u000e^3s]\u0006dgFY8v]\u0012,GmV5mI\u000e\f'\u000f\u001a+za\u0016\u0004\u0007%\u001b8ti\u0016\fG-A\u0002mk\n$BA!\u0002\u0007\u0016!AaqCA.\u0001\u0004\u0011y&\u0001\u0002yg\u0006\u0019q\r\u001c2\u0015\t\t\u0015aQ\u0004\u0005\t\r?\ti\u00061\u0001\u0003`\u0005\u0011Ao]\u0001\fCB\u0004H.[3e)f\u0004X\r\u0006\u0004\u0003\u0006\u0019\u0015b\u0011\u0006\u0005\t\rO\ty\u00061\u0001\u0003\u0006\u0005)A/_2p]\"A1Q\\A0\u0001\u0004\u0011y\u0006\u0006\u0004\u0003\u0006\u00195bq\u0006\u0005\t\rO\t\t\u00071\u0001\u0003\u0006!A1Q\\A1\u0001\u00041\t\u0004\u0005\u0004\u0002|\u0019M\"QA\u0005\u0005\rk\t\tH\u0001\u0006=e\u0016\u0004X-\u0019;fIz\"bA!\u0002\u0007:\u0019m\u0002\u0002\u0003Be\u0003G\u0002\r!a+\t\u0011\ru\u00171\ra\u0001\u0005?\"bA!\u0002\u0007@\u0019\u0005\u0003\u0002\u0003Be\u0003K\u0002\r!a+\t\u0011\ru\u0017Q\ra\u0001\rc\u0001BA\"\u0012\u0007H5\u0011\u0011\u0011N\u0005\u0005\r\u0013\nIG\u0001\u0005V]&4XM]:f\u0001"
)
public interface Types {
   TypeApi NoType();

   TypeApi NoPrefix();

   ThisTypeExtractor ThisType();

   SingleTypeExtractor SingleType();

   SuperTypeExtractor SuperType();

   ConstantTypeExtractor ConstantType();

   TypeRefExtractor TypeRef();

   RefinedTypeExtractor RefinedType();

   ClassInfoTypeExtractor ClassInfoType();

   MethodTypeExtractor MethodType();

   NullaryMethodTypeExtractor NullaryMethodType();

   PolyTypeExtractor PolyType();

   ExistentialTypeExtractor ExistentialType();

   AnnotatedTypeExtractor AnnotatedType();

   TypeBoundsExtractor TypeBounds();

   TypeApi WildcardType();

   BoundedWildcardTypeExtractor BoundedWildcardType();

   TypeApi lub(final List xs);

   TypeApi glb(final List ts);

   TypeApi appliedType(final TypeApi tycon, final List args);

   TypeApi appliedType(final TypeApi tycon, final Seq args);

   TypeApi appliedType(final Symbols.SymbolApi sym, final List args);

   TypeApi appliedType(final Symbols.SymbolApi sym, final Seq args);

   static void $init$(final Types $this) {
   }

   public abstract class TypeApi {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Symbols.SymbolApi termSymbol();

      public abstract Symbols.SymbolApi typeSymbol();

      /** @deprecated */
      public abstract Symbols.SymbolApi declaration(final Names.NameApi name);

      public abstract Symbols.SymbolApi decl(final Names.NameApi name);

      /** @deprecated */
      public abstract Scopes.MemberScopeApi declarations();

      public abstract Scopes.MemberScopeApi decls();

      public abstract Symbols.SymbolApi member(final Names.NameApi name);

      public abstract Scopes.MemberScopeApi members();

      public abstract TypeApi companion();

      public abstract boolean takesTypeArgs();

      public abstract TypeApi typeConstructor();

      /** @deprecated */
      public abstract TypeApi normalize();

      public abstract TypeApi etaExpand();

      public abstract boolean $less$colon$less(final TypeApi that);

      public abstract boolean weak_$less$colon$less(final TypeApi that);

      public abstract boolean $eq$colon$eq(final TypeApi that);

      public abstract List baseClasses();

      public abstract TypeApi baseType(final Symbols.SymbolApi clazz);

      public abstract TypeApi asSeenFrom(final TypeApi pre, final Symbols.SymbolApi clazz);

      public abstract TypeApi erasure();

      public abstract TypeApi widen();

      public abstract TypeApi dealias();

      public abstract List typeArgs();

      /** @deprecated */
      public abstract List paramss();

      public abstract List paramLists();

      public abstract List typeParams();

      public abstract TypeApi resultType();

      public abstract TypeApi finalResultType();

      public abstract TypeApi orElse(final Function0 alt);

      public abstract TypeApi substituteSymbols(final List from, final List to);

      public abstract TypeApi substituteTypes(final List from, final List to);

      public abstract TypeApi map(final Function1 f);

      public abstract void foreach(final Function1 f);

      public abstract Option find(final Function1 p);

      public abstract boolean exists(final Function1 p);

      public abstract boolean contains(final Symbols.SymbolApi sym);

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$TypeApi$$$outer() {
         return this.$outer;
      }

      public TypeApi() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class ThisTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final ThisTypeApi tpe);

      /** @deprecated */
      public TypeApi apply(final Symbols.SymbolApi sym, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$ThisTypeExtractor$$$outer().internal().thisType(sym);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$ThisTypeExtractor$$$outer() {
         return this.$outer;
      }

      public ThisTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class SingleTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final SingleTypeApi tpe);

      /** @deprecated */
      public TypeApi apply(final TypeApi pre, final Symbols.SymbolApi sym, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$SingleTypeExtractor$$$outer().internal().singleType(pre, sym);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$SingleTypeExtractor$$$outer() {
         return this.$outer;
      }

      public SingleTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class SuperTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final SuperTypeApi tpe);

      /** @deprecated */
      public TypeApi apply(final TypeApi thistpe, final TypeApi supertpe, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$SuperTypeExtractor$$$outer().internal().superType(thistpe, supertpe);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$SuperTypeExtractor$$$outer() {
         return this.$outer;
      }

      public SuperTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class ConstantTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final ConstantTypeApi tpe);

      /** @deprecated */
      public ConstantTypeApi apply(final Constants.ConstantApi value, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$ConstantTypeExtractor$$$outer().internal().constantType(value);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$ConstantTypeExtractor$$$outer() {
         return this.$outer;
      }

      public ConstantTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class TypeRefExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final TypeRefApi tpe);

      /** @deprecated */
      public TypeApi apply(final TypeApi pre, final Symbols.SymbolApi sym, final List args, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$TypeRefExtractor$$$outer().internal().typeRef(pre, sym, args);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$TypeRefExtractor$$$outer() {
         return this.$outer;
      }

      public TypeRefExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class RefinedTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final RefinedTypeApi tpe);

      /** @deprecated */
      public RefinedTypeApi apply(final List parents, final Scopes.ScopeApi decls, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$RefinedTypeExtractor$$$outer().internal().refinedType(parents, decls);
      }

      /** @deprecated */
      public RefinedTypeApi apply(final List parents, final Scopes.ScopeApi decls, final Symbols.SymbolApi clazz, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$RefinedTypeExtractor$$$outer().internal().refinedType(parents, decls, clazz);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$RefinedTypeExtractor$$$outer() {
         return this.$outer;
      }

      public RefinedTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class ClassInfoTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final ClassInfoTypeApi tpe);

      /** @deprecated */
      public ClassInfoTypeApi apply(final List parents, final Scopes.ScopeApi decls, final Symbols.SymbolApi typeSymbol, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$ClassInfoTypeExtractor$$$outer().internal().classInfoType(parents, decls, typeSymbol);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$ClassInfoTypeExtractor$$$outer() {
         return this.$outer;
      }

      public ClassInfoTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class MethodTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final MethodTypeApi tpe);

      /** @deprecated */
      public MethodTypeApi apply(final List params, final TypeApi resultType, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$MethodTypeExtractor$$$outer().internal().methodType(params, resultType);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$MethodTypeExtractor$$$outer() {
         return this.$outer;
      }

      public MethodTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class NullaryMethodTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final NullaryMethodTypeApi tpe);

      /** @deprecated */
      public NullaryMethodTypeApi apply(final TypeApi resultType, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$NullaryMethodTypeExtractor$$$outer().internal().nullaryMethodType(resultType);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$NullaryMethodTypeExtractor$$$outer() {
         return this.$outer;
      }

      public NullaryMethodTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class PolyTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final PolyTypeApi tpe);

      /** @deprecated */
      public PolyTypeApi apply(final List typeParams, final TypeApi resultType, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$PolyTypeExtractor$$$outer().internal().polyType(typeParams, resultType);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$PolyTypeExtractor$$$outer() {
         return this.$outer;
      }

      public PolyTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class ExistentialTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final ExistentialTypeApi tpe);

      /** @deprecated */
      public ExistentialTypeApi apply(final List quantified, final TypeApi underlying, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$ExistentialTypeExtractor$$$outer().internal().existentialType(quantified, underlying);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$ExistentialTypeExtractor$$$outer() {
         return this.$outer;
      }

      public ExistentialTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class AnnotatedTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final AnnotatedTypeApi tpe);

      /** @deprecated */
      public AnnotatedTypeApi apply(final List annotations, final TypeApi underlying, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$AnnotatedTypeExtractor$$$outer().internal().annotatedType(annotations, underlying);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$AnnotatedTypeExtractor$$$outer() {
         return this.$outer;
      }

      public AnnotatedTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class TypeBoundsExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final TypeBoundsApi tpe);

      /** @deprecated */
      public TypeBoundsApi apply(final TypeApi lo, final TypeApi hi, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$TypeBoundsExtractor$$$outer().internal().typeBounds(lo, hi);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$TypeBoundsExtractor$$$outer() {
         return this.$outer;
      }

      public TypeBoundsExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public abstract class BoundedWildcardTypeExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public abstract Option unapply(final BoundedWildcardTypeApi tpe);

      /** @deprecated */
      public BoundedWildcardTypeApi apply(final TypeBoundsApi bounds, final Internals.CompatToken token) {
         return this.scala$reflect$api$Types$BoundedWildcardTypeExtractor$$$outer().internal().boundedWildcardType(bounds);
      }

      // $FF: synthetic method
      public Universe scala$reflect$api$Types$BoundedWildcardTypeExtractor$$$outer() {
         return this.$outer;
      }

      public BoundedWildcardTypeExtractor() {
         if (Types.this == null) {
            throw null;
         } else {
            this.$outer = Types.this;
            super();
         }
      }
   }

   public interface AnnotatedTypeApi {
      List annotations();

      TypeApi underlying();
   }

   public interface BoundedWildcardTypeApi {
      TypeBoundsApi bounds();
   }

   public interface ClassInfoTypeApi {
      List parents();

      Scopes.MemberScopeApi decls();

      Symbols.SymbolApi typeSymbol();
   }

   public interface CompoundTypeApi {
   }

   public interface ConstantTypeApi {
      Constants.ConstantApi value();
   }

   public interface ExistentialTypeApi {
      List quantified();

      TypeApi underlying();
   }

   public interface MethodTypeApi {
      List params();

      TypeApi resultType();
   }

   public interface NullaryMethodTypeApi {
      TypeApi resultType();
   }

   public interface PolyTypeApi {
      List typeParams();

      TypeApi resultType();
   }

   public interface RefinedTypeApi {
      List parents();

      Scopes.MemberScopeApi decls();
   }

   public interface SingleTypeApi {
      TypeApi pre();

      Symbols.SymbolApi sym();
   }

   public interface SingletonTypeApi {
   }

   public interface SuperTypeApi {
      TypeApi thistpe();

      TypeApi supertpe();
   }

   public interface ThisTypeApi {
      Symbols.SymbolApi sym();
   }

   public interface TypeBoundsApi {
      TypeApi lo();

      TypeApi hi();
   }

   public interface TypeRefApi {
      TypeApi pre();

      Symbols.SymbolApi sym();

      List args();
   }
}
