package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import scala.Option;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Map;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0015Uq!B\u001b7\u0011\u0003\te!B\"7\u0011\u0003!\u0005\"\u0002&\u0002\t\u0003Y\u0005\"\u0002'\u0002\t\u0003i\u0005B\u0002'\u0002\t\u0003!)P\u0002\u0004\u0005~\u0006\u0011Aq \u0005\n\u000b\u0003)!\u0011!Q\u0001\nECqAS\u0003\u0005\u0002\u0005)\u0019A\u0002\u0004\u0006\f\u0005\u0011QQ\u0002\u0005\n\u000b\u0003A!\u0011!Q\u0001\n\u0011DqA\u0013\u0005\u0005\u0002\u0005)yAB\u0005DmA\u0005\u0019\u0011\u0001.\u0005h\")1l\u0003C\u00019\")\u0001m\u0003C\u0003C\"9\u0011\u0011E\u0006\u0005\u0006\u0005\r\u0002bBA$\u0017\u0011\u0015\u0011\u0011\n\u0005\b\u0003SZA\u0011AA6\u0011\u001d\tyh\u0003C\u0001\u0003\u0003Cq!!)\f\t\u0003\t\u0019\u000bC\u0004\u0002<.!\t!!0\t\u000f\u0005]7\u0002\"\u0001\u0002Z\"9\u0011q_\u0006\u0005\u0002\u0005e\bbBA@\u0017\u0011\u0005!\u0011\u0002\u0005\b\u0003\u007fZA\u0011\u0001B\u0017\u0011\u001d\tyh\u0003C\u0001\u0005\u0017Bq!a \f\t\u0003\u0011)\u0007C\u0004\u0002\u0000-!\tA! \t\u000f\u0005}4\u0002\"\u0001\u0003\u0016\"9\u0011qP\u0006\u0005\u0002\tM\u0006b\u0002Bj\u0017\u0011\u0005!Q\u001b\u0005\b\u0005'\\A\u0011\u0001Bv\u0011\u001d\u0011\u0019n\u0003C\u0001\u0005\u007fDqAa5\f\t\u0003\u0019\u0019\u0002C\u0004\u0003T.!\taa\n\t\u000f\tM7\u0002\"\u0001\u0004<!9!1[\u0006\u0005\u0002\r=\u0003bBB4\u0017\u0011%1\u0011\u000e\u0005\b\u0007\u0003[A\u0011ABB\u0011\u001d\u00199j\u0003C\u0001\u00073Cqa!,\f\t\u0003\u0019y\u000bC\u0004\u0004>.!\taa0\t\u000f\r]7\u0002\"\u0001\u0004Z\"91q]\u0006\u0005\u0002\r%\bbBB|\u0017\u0011\u00051\u0011 \u0005\b\t\u001bYA\u0011\u0001C\b\u0011\u001d!\u0019d\u0003C\u0001\tkAq\u0001\"\u0016\f\t\u0013!9\u0006C\u0005\u0005h-\u0011\r\u0011\"\u0003\u0005j!9AqR\u0006\u0005\n\u0011E\u0005\"\u0003CP\u0017\t\u0007I\u0011\u0002CQ\u0011\u001d!\u0019l\u0003C\u0005\tkC\u0011\u0002b1\f\u0005\u0004%I\u0001\"2\t\u000f\u0011]7\u0002\"\u0003\u0005Z\u0006\t2kY1mC>\u0013'.Z2u\u001b\u0006\u0004\b/\u001a:\u000b\u0005]B\u0014!B:dC2\f'BA\u001d;\u0003\u0019iw\u000eZ;mK*\u00111\bP\u0001\bU\u0006\u001c7n]8o\u0015\tid(A\u0005gCN$XM\u001d=nY*\tq(A\u0002d_6\u001c\u0001\u0001\u0005\u0002C\u00035\taGA\tTG\u0006d\u0017m\u00142kK\u000e$X*\u00199qKJ\u001c\"!A#\u0011\u0005\u0019CU\"A$\u000b\u0003]J!!S$\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0011)\u0001\u0007%G>dwN\u001c\u0013d_2|g\u000eF\u0002O\tc\u00142aT)Z\r\u0011\u0001\u0016\u0001\u0001(\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005I;V\"A*\u000b\u0005Q+\u0016\u0001\u00026t_:T!A\u0016\u001e\u0002\u0011\u0011\fG/\u00192j]\u0012L!\u0001W*\u0003\u0015)\u001bxN\\'baB,'\u000f\u0005\u0002C\u0017M\u00111\"R\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003u\u0003\"A\u00120\n\u0005};%\u0001B+oSR\f\u0001\"\u00193e\u001b&D\u0018N\\\u000b\u0005E^\fI\u0001F\u0001d)\u0011!\u0007.!\u0001\u0011\u0005\u00154W\"A+\n\u0005\u001d,&\u0001D(cU\u0016\u001cG/T1qa\u0016\u0014\bbB5\u000e\u0003\u0003\u0005\u001dA[\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA6sk:\u0011A\u000e\u001d\t\u0003[\u001ek\u0011A\u001c\u0006\u0003_\u0002\u000ba\u0001\u0010:p_Rt\u0014BA9H\u0003\u0019\u0001&/\u001a3fM&\u00111\u000f\u001e\u0002\t\u001b\u0006t\u0017NZ3ti*\u0011\u0011o\u0012\t\u0003m^d\u0001\u0001B\u0003y\u001b\t\u0007\u0011P\u0001\u0004UCJ<W\r^\t\u0003uv\u0004\"AR>\n\u0005q<%a\u0002(pi\"Lgn\u001a\t\u0003\rzL!a`$\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u00045\t\t\u0011q\u0001\u0002\u0006\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\t-\u0014\u0018q\u0001\t\u0004m\u0006%AABA\u0006\u001b\t\u0007\u0011PA\u0006NSbLgnU8ve\u000e,\u0007fC\u0007\u0002\u0010\u0005U\u0011qCA\u000e\u0003;\u00012ARA\t\u0013\r\t\u0019b\u0012\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u00033\tq\b\u001e5jg\u0002\u001aX\u000f\u001d9peR\u0004\u0013N\u001c\u0011kC\u000e\\7o\u001c8.I\u0006$\u0018MY5oI\u0002J7\u000fI7pm&tw\r\t;pAQDW\rI'baB,'OQ;jY\u0012,'/A\u0003tS:\u001cW-\t\u0002\u0002 \u00051!GL\u00193]I\n1#\u00193e\u001b&D\u0018J\\!o]>$\u0018\r^5p]N,b!!\n\u00022\u0005mBCAA\u0014)\u0015!\u0017\u0011FA\u001a\u0011%\tYCDA\u0001\u0002\b\ti#\u0001\u0006fm&$WM\\2fIM\u0002Ba\u001b:\u00020A\u0019a/!\r\u0005\u000bat!\u0019A=\t\u0013\u0005Ub\"!AA\u0004\u0005]\u0012AC3wS\u0012,gnY3%iA!1N]A\u001d!\r1\u00181\b\u0003\u0007\u0003\u0017q!\u0019A=)\u00179\ty!!\u0006\u0002@\u0005m\u00111I\u0011\u0003\u0003\u0003\nA\"^:fA\u0005$G-T5y\u0013:\f#!!\u0012\u0002\u0007IrS'A\tgS:$W*\u001b=J]\u000ec\u0017m]:G_J,B!a\u0013\u0002dQ!\u0011QJA.a\u0011\ty%a\u0016\u0011\u000b-\f\t&!\u0016\n\u0007\u0005MCOA\u0003DY\u0006\u001c8\u000fE\u0002w\u0003/\"!\"!\u0017\u0010\u0003\u0003\u0005\tQ!\u0001z\u0005\ryF%\r\u0005\n\u0003;z\u0011\u0011!a\u0002\u0003?\n!\"\u001a<jI\u0016t7-\u001a\u00136!\u0011Y'/!\u0019\u0011\u0007Y\f\u0019\u0007\u0002\u0004\u0002f=\u0011\r!\u001f\u0002\u0002)\"Zq\"a\u0004\u0002\u0016\u0005]\u00111DA\u000f\u00035\u0019wN\\:ueV\u001cG\u000fV=qKV!\u0011QNA?)\u0011\ty'!\u001e\u0011\u0007\u0015\f\t(C\u0002\u0002tU\u0013\u0001BS1wCRK\b/\u001a\u0005\b\u0003o\u0002\u00029AA=\u0003\u0005i\u0007\u0003B6s\u0003w\u00022A^A?\t\u0019\t)\u0007\u0005b\u0001s\u0006I!/Z1e-\u0006dW/Z\u000b\u0005\u0003\u0007\u000bI\t\u0006\u0003\u0002\u0006\u0006EE\u0003BAD\u0003\u0017\u00032A^AE\t\u0019\t)'\u0005b\u0001s\"I\u0011QR\t\u0002\u0002\u0003\u000f\u0011qR\u0001\u000bKZLG-\u001a8dK\u00122\u0004\u0003B6s\u0003\u000fCq!a%\u0012\u0001\u0004\t)*\u0001\u0002kaB!\u0011qSAO\u001b\t\tIJC\u0002\u0002\u001cj\nAaY8sK&!\u0011qTAM\u0005)Q5o\u001c8QCJ\u001cXM]\u0001\u000be\u0016\fGMV1mk\u0016\u001cX\u0003BAS\u0003c#B!a*\u0002:R!\u0011\u0011VAZ!\u0015)\u00171VAX\u0013\r\ti+\u0016\u0002\u0010\u001b\u0006\u0004\b/\u001b8h\u0013R,'/\u0019;peB\u0019a/!-\u0005\r\u0005\u0015$C1\u0001z\u0011%\t)LEA\u0001\u0002\b\t9,\u0001\u0006fm&$WM\\2fI]\u0002Ba\u001b:\u00020\"9\u00111\u0013\nA\u0002\u0005U\u0015a\u0003;sK\u0016$vNV1mk\u0016,B!a0\u0002FR!\u0011\u0011YAg)\u0011\t\u0019-a2\u0011\u0007Y\f)\r\u0002\u0004\u0002fM\u0011\r!\u001f\u0005\n\u0003\u0013\u001c\u0012\u0011!a\u0002\u0003\u0017\f!\"\u001a<jI\u0016t7-\u001a\u00139!\u0011Y'/a1\t\u000f\u0005=7\u00031\u0001\u0002R\u0006\ta\u000e\u0005\u0003\u0002\u0018\u0006M\u0017\u0002BAk\u00033\u0013\u0001\u0002\u0016:fK:{G-Z\u0001\rG\u0006t7+\u001a:jC2L'0Z\u000b\u0005\u00037\fY\u000f\u0006\u0003\u0002^\u0006\r\bc\u0001$\u0002`&\u0019\u0011\u0011]$\u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u001d\u000b\u0002\u0002\u0003\u000f\u0011q]\u0001\u000bKZLG-\u001a8dK\u0012J\u0004\u0003B6s\u0003S\u00042A^Av\t\u0019\t)\u0007\u0006b\u0001s\"ZA#a\u0004\u0002\u0016\u0005=\u00181DAzC\t\t\t0\u0001\u001akC\u000e\\7o\u001c8.I\u0006$\u0018MY5oI\u0002:\u0018\u000e\u001c7!]>$\b%[7qY\u0016lWM\u001c;!i\"L7\u000fI5oAY\u001cd\u0006\r\u00181C\t\t)0\u0001\u00043]E\u0012d&M\u0001\u000fG\u0006tG)Z:fe&\fG.\u001b>f+\u0011\tYP!\u0002\u0015\t\u0005u\u0017Q \u0005\n\u0003\u007f,\u0012\u0011!a\u0002\u0005\u0003\t1\"\u001a<jI\u0016t7-\u001a\u00132aA!1N\u001dB\u0002!\r1(Q\u0001\u0003\u0007\u0003K*\"\u0019A=)\u0017U\ty!!\u0006\u0002p\u0006m\u00111_\u000b\u0005\u0005\u0017\u0011\t\u0002\u0006\u0003\u0003\u000e\teA\u0003\u0002B\b\u0005'\u00012A\u001eB\t\t\u0019\t)G\u0006b\u0001s\"I!Q\u0003\f\u0002\u0002\u0003\u000f!qC\u0001\fKZLG-\u001a8dK\u0012\n\u0014\u0007\u0005\u0003le\n=\u0001b\u0002B\u000e-\u0001\u0007!QD\u0001\u0004gJ\u001c\u0007\u0003\u0002B\u0010\u0005Si!A!\t\u000b\t\t\r\"QE\u0001\u0003S>T!Aa\n\u0002\t)\fg/Y\u0005\u0005\u0005W\u0011\tC\u0001\u0003GS2,W\u0003\u0002B\u0018\u0005k!BA!\r\u0003>Q!!1\u0007B\u001c!\r1(Q\u0007\u0003\u0007\u0003K:\"\u0019A=\t\u0013\ter#!AA\u0004\tm\u0012aC3wS\u0012,gnY3%cI\u0002Ba\u001b:\u00034!9!1D\fA\u0002\t}\u0002\u0003\u0002B!\u0005\u000fj!Aa\u0011\u000b\t\t\u0015#QE\u0001\u0004]\u0016$\u0018\u0002\u0002B%\u0005\u0007\u00121!\u0016*M+\u0011\u0011iEa\u0015\u0015\t\t=#1\f\u000b\u0005\u0005#\u0012)\u0006E\u0002w\u0005'\"a!!\u001a\u0019\u0005\u0004I\b\"\u0003B,1\u0005\u0005\t9\u0001B-\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\t-\u0014(\u0011\u000b\u0005\b\u0005;B\u0002\u0019\u0001B0\u0003\u001d\u0019wN\u001c;f]R\u00042a\u001bB1\u0013\r\u0011\u0019\u0007\u001e\u0002\u0007'R\u0014\u0018N\\4\u0016\t\t\u001d$Q\u000e\u000b\u0005\u0005S\u0012)\b\u0006\u0003\u0003l\t=\u0004c\u0001<\u0003n\u00111\u0011QM\rC\u0002eD\u0011B!\u001d\u001a\u0003\u0003\u0005\u001dAa\u001d\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0005WJ\u0014Y\u0007C\u0004\u0003\u001ce\u0001\rAa\u001e\u0011\t\t}!\u0011P\u0005\u0005\u0005w\u0012\tC\u0001\u0004SK\u0006$WM]\u000b\u0005\u0005\u007f\u0012)\t\u0006\u0003\u0003\u0002\n5E\u0003\u0002BB\u0005\u000f\u00032A\u001eBC\t\u0019\t)G\u0007b\u0001s\"I!\u0011\u0012\u000e\u0002\u0002\u0003\u000f!1R\u0001\fKZLG-\u001a8dK\u0012\nT\u0007\u0005\u0003le\n\r\u0005b\u0002B\u000e5\u0001\u0007!q\u0012\t\u0005\u0005?\u0011\t*\u0003\u0003\u0003\u0014\n\u0005\"aC%oaV$8\u000b\u001e:fC6,BAa&\u0003\u001eR!!\u0011\u0014BS)\u0011\u0011YJa(\u0011\u0007Y\u0014i\n\u0002\u0004\u0002fm\u0011\r!\u001f\u0005\n\u0005C[\u0012\u0011!a\u0002\u0005G\u000b1\"\u001a<jI\u0016t7-\u001a\u00132mA!1N\u001dBN\u0011\u001d\u0011Yb\u0007a\u0001\u0005O\u0003RA\u0012BU\u0005[K1Aa+H\u0005\u0015\t%O]1z!\r1%qV\u0005\u0004\u0005c;%\u0001\u0002\"zi\u0016,BA!.\u0003<RA!q\u0017Bb\u0005\u000b\u0014y\r\u0006\u0003\u0003:\nu\u0006c\u0001<\u0003<\u00121\u0011Q\r\u000fC\u0002eD\u0011Ba0\u001d\u0003\u0003\u0005\u001dA!1\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0005WJ\u0014I\fC\u0004\u0003\u001cq\u0001\rAa*\t\u000f\t\u001dG\u00041\u0001\u0003J\u00061qN\u001a4tKR\u00042A\u0012Bf\u0013\r\u0011im\u0012\u0002\u0004\u0013:$\bb\u0002Bi9\u0001\u0007!\u0011Z\u0001\u0004Y\u0016t\u0017aC;qI\u0006$XMV1mk\u0016,BAa6\u0003^R1!\u0011\u001cBs\u0005S$BAa7\u0003`B\u0019aO!8\u0005\r\u0005\u0015TD1\u0001z\u0011%\u0011\t/HA\u0001\u0002\b\u0011\u0019/A\u0006fm&$WM\\2fIEB\u0004\u0003B6s\u00057DqAa:\u001e\u0001\u0004\u0011Y.A\u0007wC2,X\rV8Va\u0012\fG/\u001a\u0005\b\u00057i\u0002\u0019\u0001B\u000f+\u0011\u0011iOa=\u0015\r\t=(1 B\u007f)\u0011\u0011\tP!>\u0011\u0007Y\u0014\u0019\u0010\u0002\u0004\u0002fy\u0011\r!\u001f\u0005\n\u0005ot\u0012\u0011!a\u0002\u0005s\f1\"\u001a<jI\u0016t7-\u001a\u00132sA!1N\u001dBy\u0011\u001d\u00119O\ba\u0001\u0005cDqAa\u0007\u001f\u0001\u0004\u0011y$\u0006\u0003\u0004\u0002\r\u001dACBB\u0002\u0007\u001f\u0019\t\u0002\u0006\u0003\u0004\u0006\r%\u0001c\u0001<\u0004\b\u00111\u0011QM\u0010C\u0002eD\u0011ba\u0003 \u0003\u0003\u0005\u001da!\u0004\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\r\t\u0005WJ\u001c)\u0001C\u0004\u0003h~\u0001\ra!\u0002\t\u000f\tus\u00041\u0001\u0003`U!1QCB\u000e)\u0019\u00199ba\t\u0004&Q!1\u0011DB\u000f!\r181\u0004\u0003\u0007\u0003K\u0002#\u0019A=\t\u0013\r}\u0001%!AA\u0004\r\u0005\u0012aC3wS\u0012,gnY3%eE\u0002Ba\u001b:\u0004\u001a!9!q\u001d\u0011A\u0002\re\u0001b\u0002B\u000eA\u0001\u0007!qO\u000b\u0005\u0007S\u0019y\u0003\u0006\u0004\u0004,\r]2\u0011\b\u000b\u0005\u0007[\u0019\t\u0004E\u0002w\u0007_!a!!\u001a\"\u0005\u0004I\b\"CB\u001aC\u0005\u0005\t9AB\u001b\u0003-)g/\u001b3f]\u000e,GE\r\u001a\u0011\t-\u00148Q\u0006\u0005\b\u0005O\f\u0003\u0019AB\u0017\u0011\u001d\u0011Y\"\ta\u0001\u0005\u001f+Ba!\u0010\u0004DQ11qHB&\u0007\u001b\"Ba!\u0011\u0004FA\u0019aoa\u0011\u0005\r\u0005\u0015$E1\u0001z\u0011%\u00199EIA\u0001\u0002\b\u0019I%A\u0006fm&$WM\\2fII\u001a\u0004\u0003B6s\u0007\u0003BqAa:#\u0001\u0004\u0019\t\u0005C\u0004\u0003\u001c\t\u0002\rAa*\u0016\t\rE3q\u000b\u000b\u000b\u0007'\u001ayf!\u0019\u0004d\r\u0015D\u0003BB+\u00073\u00022A^B,\t\u0019\t)g\tb\u0001s\"I11L\u0012\u0002\u0002\u0003\u000f1QL\u0001\fKZLG-\u001a8dK\u0012\u0012D\u0007\u0005\u0003le\u000eU\u0003b\u0002BtG\u0001\u00071Q\u000b\u0005\b\u00057\u0019\u0003\u0019\u0001BT\u0011\u001d\u00119m\ta\u0001\u0005\u0013DqA!5$\u0001\u0004\u0011I-A\bpE*,7\r\u001e*fC\u0012,'OR8s+\u0011\u0019Yg! \u0015\t\r54q\u0010\u000b\u0005\u0007_\u001a)\bE\u0002f\u0007cJ1aa\u001dV\u00051y%M[3diJ+\u0017\rZ3s\u0011%\u00199\bJA\u0001\u0002\b\u0019I(A\u0006fm&$WM\\2fII*\u0004\u0003B6s\u0007w\u00022A^B?\t\u0019\t)\u0007\nb\u0001s\"9!q\u001d\u0013A\u0002\rm\u0014AD<sSR,'oV5uQZKWm^\u000b\u0005\u0007\u000b\u001b)\n\u0006\u0003\u0004\b\u000e5\u0005cA3\u0004\n&\u001911R+\u0003\u0019=\u0013'.Z2u/JLG/\u001a:\t\u0013\r=U%!AA\u0004\rE\u0015aC3wS\u0012,gnY3%eY\u0002Ba\u001b:\u0004\u0014B\u0019ao!&\u0005\r\u0005\u0015TE1\u0001z\u000399(/\u001b;fe^KG\u000f\u001b+za\u0016,Baa'\u0004&R!1qQBO\u0011%\u0019yJJA\u0001\u0002\b\u0019\t+A\u0006fm&$WM\\2fII:\u0004\u0003B6s\u0007G\u00032A^BS\t\u0019\t)G\nb\u0001s\"Za%a\u0004\u0002\u0016\r%\u00161DA\"C\t\u0019Y+A\fSKBd\u0017mY3eA]LG\u000f\u001b\u0011xe&$XM\u001d$pe\u0006IqO]5uKJ4uN]\u000b\u0005\u0007c\u001bY\f\u0006\u0003\u0004\b\u000eM\u0006\"CB[O\u0005\u0005\t9AB\\\u0003-)g/\u001b3f]\u000e,GE\r\u001d\u0011\t-\u00148\u0011\u0018\t\u0004m\u000emFABA3O\t\u0007\u00110\u0001\u0004sK\u0006$WM]\u000b\u0005\u0007\u0003\u001cY\r\u0006\u0003\u0004p\r\r\u0007\"CBcQ\u0005\u0005\t9ABd\u0003-)g/\u001b3f]\u000e,GEM\u001d\u0011\t-\u00148\u0011\u001a\t\u0004m\u000e-GABA3Q\t\u0007\u0011\u0010K\u0006)\u0003\u001f\t)ba4\u0002\u001c\rM\u0017EABi\u0003]\u0011V\r\u001d7bG\u0016$\u0007e^5uQ\u0002\u0012X-\u00193fe\u001a{'/\t\u0002\u0004V\u0006\u0019!G\f\u001c\u0002\u0013I,\u0017\rZ3s\r>\u0014X\u0003BBn\u0007K$Baa\u001c\u0004^\"I1q\\\u0015\u0002\u0002\u0003\u000f1\u0011]\u0001\fKZLG-\u001a8dK\u0012\u001a\u0004\u0007\u0005\u0003le\u000e\r\bc\u0001<\u0004f\u00121\u0011QM\u0015C\u0002e\faB]3bI\u0016\u0014x+\u001b;i-&,w/\u0006\u0003\u0004l\u000eUH\u0003BB8\u0007[D\u0011ba<+\u0003\u0003\u0005\u001da!=\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3'\r\t\u0005WJ\u001c\u0019\u0010E\u0002w\u0007k$a!!\u001a+\u0005\u0004I\u0018\u0001D2p]Z,'\u000f\u001e,bYV,W\u0003BB~\t\u0003!Ba!@\u0005\nQ!1q C\u0002!\r1H\u0011\u0001\u0003\u0007\u0003KZ#\u0019A=\t\u0013\u0011\u00151&!AA\u0004\u0011\u001d\u0011aC3wS\u0012,gnY3%gI\u0002Ba\u001b:\u0004\u0000\"1A1B\u0016A\u0002u\f\u0011B\u001a:p[Z\u000bG.^3\u0002%\u001d,g.\u001a:bi\u0016T5o\u001c8TG\",W.Y\u000b\u0005\t#!9\u0003\u0006\u0003\u0005\u0014\u0011}\u0001\u0003\u0002C\u000b\t7i!\u0001b\u0006\u000b\u0007\u0011eQ+\u0001\u0006kg>t7o\u00195f[\u0006LA\u0001\"\b\u0005\u0018\tQ!j]8o'\u000eDW-\\1\t\u0013\u0011\u0005B&!AA\u0004\u0011\r\u0012aC3wS\u0012,gnY3%gM\u0002Ba\u001b:\u0005&A\u0019a\u000fb\n\u0005\r\u0005\u0015DF1\u0001zQ-a\u0013qBA\u000b\tW\tY\u0002b\f\"\u0005\u00115\u0012A\u000e&t_:\u001c6\r[3nC\u0002J7\u000f\t3faJ,7-\u0019;fI\u0002Jg\u000e\t4bm>\u0014\be\u001c4!\u0015N|gNR8s[\u0006$h+[:ji>\u0014\u0018E\u0001C\u0019\u0003\u0015\u0011dF\r\u00183\u0003]\t7mY3qi*\u001bxN\u001c$pe6\fGOV5tSR|'/\u0006\u0003\u00058\u0011\rC\u0003\u0002C\u001d\t\u000b\"2!\u0018C\u001e\u0011%!i$LA\u0001\u0002\b!y$A\u0006fm&$WM\\2fIM\"\u0004\u0003B6s\t\u0003\u00022A\u001eC\"\t\u0019\t)'\fb\u0001s\"9AqI\u0017A\u0002\u0011%\u0013a\u0002<jg&$xN\u001d\t\u0005\t\u0017\"\t&\u0004\u0002\u0005N)\u0019AqJ+\u0002%)\u001cxN\u001c$pe6\fGOV5tSR|'o]\u0005\u0005\t'\"iE\u0001\rKg>tgi\u001c:nCR4\u0016n]5u_J<&/\u00199qKJ\fq![:BeJ\f\u0017\u0010\u0006\u0003\u0002^\u0012e\u0003b\u0002C.]\u0001\u0007AQL\u0001\u0002GB\"Aq\fC2!\u0015Y\u0017\u0011\u000bC1!\r1H1\r\u0003\f\tK\"I&!A\u0001\u0002\u000b\u0005\u0011PA\u0002`II\n1!T!Q+\t!Y\u0007\u0005\u0004\u0005n\u0011MDQO\u0007\u0003\t_RA\u0001\"\u001d\u0003&\u0005!A.\u00198h\u0013\u0011\t\u0019\u0006b\u001c1\r\u0011]DQ\u0011CF!!!I\bb \u0005\u0004\u0012%UB\u0001C>\u0015\r!ihR\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002CA\tw\u00121!T1q!\r1HQ\u0011\u0003\u000b\t\u000f{\u0013\u0011!A\u0001\u0006\u0003I(aA0%gA\u0019a\u000fb#\u0005\u0015\u00115u&!A\u0001\u0002\u000b\u0005\u0011PA\u0002`IQ\n\u0011\"[:NCBd\u0015n[3\u0015\t\u0005uG1\u0013\u0005\b\t7\u0002\u0004\u0019\u0001CKa\u0011!9\nb'\u0011\u000b-\f\t\u0006\"'\u0011\u0007Y$Y\nB\u0006\u0005\u001e\u0012M\u0015\u0011!A\u0001\u0006\u0003I(aA0%k\u00051q\n\u0015+J\u001f:+\"\u0001b)\u0011\r\u00115D1\u000fCSa\u0011!9\u000bb,\u0011\u000b\u0019#I\u000b\",\n\u0007\u0011-vI\u0001\u0004PaRLwN\u001c\t\u0004m\u0012=FA\u0003CYc\u0005\u0005\t\u0011!B\u0001s\n\u0019q\f\n\u001c\u0002\u0017%\u001c(+\u001a4fe\u0016t7-\u001a\u000b\u0005\u0003;$9\fC\u0004\u0005\\I\u0002\r\u0001\"/1\t\u0011mFq\u0018\t\u0006W\u0006ECQ\u0018\t\u0004m\u0012}Fa\u0003Ca\to\u000b\t\u0011!A\u0003\u0002e\u00141a\u0018\u00138\u0003!IE+\u0012*B\u00052+UC\u0001Cd!\u0019!i\u0007b\u001d\u0005JB\"A1\u001aCj!\u0019!I\b\"4\u0005R&!Aq\u001aC>\u0005!IE/\u001a:bE2,\u0007c\u0001<\u0005T\u0012QAQ[\u001a\u0002\u0002\u0003\u0005)\u0011A=\u0003\u0007}#\u0003(\u0001\tjg\u000e{G\u000e\\3di&|g\u000eT5lKR!\u0011Q\u001cCn\u0011\u001d!Y\u0006\u000ea\u0001\t;\u0004D\u0001b8\u0005dB)1.!\u0015\u0005bB\u0019a\u000fb9\u0005\u0017\u0011\u0015H1\\A\u0001\u0002\u0003\u0015\t!\u001f\u0002\u0004?\u0012J$\u0003\u0002Cu3\u00124Q\u0001\u0015\u0001\u0001\tOD3bCA\b\u0003+!i/a\u0007\u0002t\u0006\u0012Aq^\u0001\u0002\u0012M\u001b\u0017\r\\1PE*,7\r^'baB,'\u000fI5tA\u0011,\u0007O]3dCR,G\r\t2fG\u0006,8/\u001a\u0011NC:Lg-Z:ug\u0002\n'/\u001a\u0011o_R\u00043/\u001e9q_J$X\r\u001a\u0011j]\u0002\u001a6-\u00197bg1\u0002\u0013p\\;![&<\u0007\u000e\u001e\u0011xC:$\b\u0005^8!kN,\u0007e\u00117bgN$\u0016mZ#yi\u0016t7/[8og\u0002\n7\u000fI1!e\u0016\u0004H.Y2f[\u0016tG\u000f\u0003\u0004\u0005t\u000e\u0001\r!U\u0001\u0002_R!Aq\u001fC~%\u0011!I\u0010Z-\u0007\u000bA\u000b\u0001\u0001b>\t\r\u0011MH\u00011\u0001e\u0005\u0015i\u0015\u000e_5o'\r)\u0011+W\u0001\u0007[\u0006\u0004\b/\u001a:\u0015\t\u0015\u0015Q\u0011\u0002\t\u0004\u000b\u000f)Q\"A\u0001\t\r\u0015\u0005q\u00011\u0001R\u0005Ey%M[3di6\u000b\u0007\u000f]3s\u001b&D\u0018N\\\n\u0004\u0011\u0011LF\u0003BC\t\u000b'\u00012!b\u0002\t\u0011\u0019)\tA\u0003a\u0001I\u0002"
)
public interface ScalaObjectMapper {
   static ObjectMapper $colon$colon(final ObjectMapper o) {
      return ScalaObjectMapper$.MODULE$.$colon$colon(o);
   }

   static JsonMapper $colon$colon(final JsonMapper o) {
      return ScalaObjectMapper$.MODULE$.$colon$colon(o);
   }

   void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP_$eq(final Class x$1);

   void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION_$eq(final Class x$1);

   void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE_$eq(final Class x$1);

   /** @deprecated */
   default ObjectMapper addMixin(final Manifest evidence$1, final Manifest evidence$2) {
      return ((ObjectMapper)this).addMixIn(.MODULE$.manifest(evidence$1).runtimeClass(), .MODULE$.manifest(evidence$2).runtimeClass());
   }

   /** @deprecated */
   default ObjectMapper addMixInAnnotations(final Manifest evidence$3, final Manifest evidence$4) {
      return ((ObjectMapper)this).addMixIn(.MODULE$.manifest(evidence$3).runtimeClass(), .MODULE$.manifest(evidence$4).runtimeClass());
   }

   /** @deprecated */
   default Class findMixInClassFor(final Manifest evidence$5) {
      return ((ObjectMapper)this).findMixInClassFor(.MODULE$.manifest(evidence$5).runtimeClass());
   }

   default JavaType constructType(final Manifest m) {
      Class clazz = m.runtimeClass();
      if (this.isArray(clazz)) {
         JavaType[] typeArguments = (JavaType[])m.typeArguments().map((x$1) -> this.constructType(x$1)).toArray(scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
         if (typeArguments.length != 1) {
            throw new IllegalArgumentException((new StringBuilder(53)).append("Need exactly 1 type parameter for array like types (").append(clazz.getName()).append(")").toString());
         } else {
            return ((ObjectMapper)this).getTypeFactory().constructArrayType(typeArguments[0]);
         }
      } else if (this.isMapLike(clazz)) {
         JavaType[] typeArguments = (JavaType[])m.typeArguments().map((x$2) -> this.constructType(x$2)).toArray(scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
         if (typeArguments.length != 2) {
            throw new IllegalArgumentException((new StringBuilder(52)).append("Need exactly 2 type parameters for map like types (").append(clazz.getName()).append(")").toString());
         } else {
            return ((ObjectMapper)this).getTypeFactory().constructMapLikeType(clazz, typeArguments[0], typeArguments[1]);
         }
      } else if (this.isReference(clazz)) {
         JavaType[] typeArguments = (JavaType[])m.typeArguments().map((x$3) -> this.constructType(x$3)).toArray(scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
         if (typeArguments.length != 1) {
            throw new IllegalArgumentException((new StringBuilder(52)).append("Need exactly 1 type parameter for reference types (").append(clazz.getName()).append(")").toString());
         } else {
            return ((ObjectMapper)this).getTypeFactory().constructReferenceType(clazz, typeArguments[0]);
         }
      } else if (this.isCollectionLike(clazz)) {
         JavaType[] typeArguments = (JavaType[])m.typeArguments().map((x$4) -> this.constructType(x$4)).toArray(scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
         if (typeArguments.length != 1) {
            throw new IllegalArgumentException((new StringBuilder(58)).append("Need exactly 1 type parameter for collection like types (").append(clazz.getName()).append(")").toString());
         } else {
            return ((ObjectMapper)this).getTypeFactory().constructCollectionLikeType(clazz, typeArguments[0]);
         }
      } else {
         JavaType[] typeArguments = (JavaType[])m.typeArguments().map((x$5) -> this.constructType(x$5)).toArray(scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
         return ((ObjectMapper)this).getTypeFactory().constructParametricType(clazz, typeArguments);
      }
   }

   default Object readValue(final JsonParser jp, final Manifest evidence$6) {
      return ((ObjectMapper)this).readValue(jp, this.constructType(evidence$6));
   }

   default MappingIterator readValues(final JsonParser jp, final Manifest evidence$7) {
      return ((ObjectMapper)this).readValues(jp, this.constructType(evidence$7));
   }

   default Object treeToValue(final TreeNode n, final Manifest evidence$8) {
      return ((ObjectMapper)this).treeToValue(n, .MODULE$.manifest(evidence$8).runtimeClass());
   }

   /** @deprecated */
   default boolean canSerialize(final Manifest evidence$9) {
      return ((ObjectMapper)this).canSerialize(.MODULE$.manifest(evidence$9).runtimeClass());
   }

   /** @deprecated */
   default boolean canDeserialize(final Manifest evidence$10) {
      return ((ObjectMapper)this).canDeserialize(this.constructType(evidence$10));
   }

   default Object readValue(final File src, final Manifest evidence$11) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$11));
   }

   default Object readValue(final URL src, final Manifest evidence$12) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$12));
   }

   default Object readValue(final String content, final Manifest evidence$13) {
      return ((ObjectMapper)this).readValue(content, this.constructType(evidence$13));
   }

   default Object readValue(final Reader src, final Manifest evidence$14) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$14));
   }

   default Object readValue(final InputStream src, final Manifest evidence$15) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$15));
   }

   default Object readValue(final byte[] src, final Manifest evidence$16) {
      return ((ObjectMapper)this).readValue(src, this.constructType(evidence$16));
   }

   default Object readValue(final byte[] src, final int offset, final int len, final Manifest evidence$17) {
      return ((ObjectMapper)this).readValue(src, offset, len, this.constructType(evidence$17));
   }

   default Object updateValue(final Object valueToUpdate, final File src, final Manifest evidence$18) {
      return this.objectReaderFor(valueToUpdate, evidence$18).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final URL src, final Manifest evidence$19) {
      return this.objectReaderFor(valueToUpdate, evidence$19).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final String content, final Manifest evidence$20) {
      return this.objectReaderFor(valueToUpdate, evidence$20).readValue(content);
   }

   default Object updateValue(final Object valueToUpdate, final Reader src, final Manifest evidence$21) {
      return this.objectReaderFor(valueToUpdate, evidence$21).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final InputStream src, final Manifest evidence$22) {
      return this.objectReaderFor(valueToUpdate, evidence$22).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final byte[] src, final Manifest evidence$23) {
      return this.objectReaderFor(valueToUpdate, evidence$23).readValue(src);
   }

   default Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final Manifest evidence$24) {
      return this.objectReaderFor(valueToUpdate, evidence$24).readValue(src, offset, len);
   }

   private ObjectReader objectReaderFor(final Object valueToUpdate, final Manifest evidence$25) {
      return ((ObjectMapper)this).readerForUpdating(valueToUpdate).forType(this.constructType(evidence$25));
   }

   default ObjectWriter writerWithView(final Manifest evidence$26) {
      return ((ObjectMapper)this).writerWithView(.MODULE$.manifest(evidence$26).runtimeClass());
   }

   /** @deprecated */
   default ObjectWriter writerWithType(final Manifest evidence$27) {
      return this.writerFor(evidence$27);
   }

   default ObjectWriter writerFor(final Manifest evidence$28) {
      return ((ObjectMapper)this).writerFor(this.constructType(evidence$28));
   }

   /** @deprecated */
   default ObjectReader reader(final Manifest evidence$29) {
      return ((ObjectMapper)this).reader(this.constructType(evidence$29));
   }

   default ObjectReader readerFor(final Manifest evidence$30) {
      return ((ObjectMapper)this).readerFor(this.constructType(evidence$30));
   }

   default ObjectReader readerWithView(final Manifest evidence$31) {
      return ((ObjectMapper)this).readerWithView(.MODULE$.manifest(evidence$31).runtimeClass());
   }

   default Object convertValue(final Object fromValue, final Manifest evidence$32) {
      return ((ObjectMapper)this).convertValue(fromValue, this.constructType(evidence$32));
   }

   /** @deprecated */
   default JsonSchema generateJsonSchema(final Manifest evidence$33) {
      return ((ObjectMapper)this).generateJsonSchema(.MODULE$.manifest(evidence$33).runtimeClass());
   }

   default void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper visitor, final Manifest evidence$34) {
      ((ObjectMapper)this).acceptJsonFormatVisitor(.MODULE$.manifest(evidence$34).runtimeClass(), visitor);
   }

   private boolean isArray(final Class c) {
      return c.isArray();
   }

   Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP();

   private boolean isMapLike(final Class c) {
      return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP().isAssignableFrom(c);
   }

   Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION();

   private boolean isReference(final Class c) {
      return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION().isAssignableFrom(c);
   }

   Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE();

   private boolean isCollectionLike(final Class c) {
      return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE().isAssignableFrom(c);
   }

   static void $init$(final ScalaObjectMapper $this) {
      $this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP_$eq(Map.class);
      $this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION_$eq(Option.class);
      $this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE_$eq(Iterable.class);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class Mixin extends JsonMapper implements ScalaObjectMapper {
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP;
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION;
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE;

      /** @deprecated */
      public final ObjectMapper addMixin(final Manifest evidence$1, final Manifest evidence$2) {
         return ScalaObjectMapper.super.addMixin(evidence$1, evidence$2);
      }

      /** @deprecated */
      public final ObjectMapper addMixInAnnotations(final Manifest evidence$3, final Manifest evidence$4) {
         return ScalaObjectMapper.super.addMixInAnnotations(evidence$3, evidence$4);
      }

      /** @deprecated */
      public final Class findMixInClassFor(final Manifest evidence$5) {
         return ScalaObjectMapper.super.findMixInClassFor(evidence$5);
      }

      public JavaType constructType(final Manifest m) {
         return ScalaObjectMapper.super.constructType(m);
      }

      public Object readValue(final JsonParser jp, final Manifest evidence$6) {
         return ScalaObjectMapper.super.readValue(jp, evidence$6);
      }

      public MappingIterator readValues(final JsonParser jp, final Manifest evidence$7) {
         return ScalaObjectMapper.super.readValues(jp, evidence$7);
      }

      public Object treeToValue(final TreeNode n, final Manifest evidence$8) {
         return ScalaObjectMapper.super.treeToValue(n, evidence$8);
      }

      /** @deprecated */
      public boolean canSerialize(final Manifest evidence$9) {
         return ScalaObjectMapper.super.canSerialize(evidence$9);
      }

      /** @deprecated */
      public boolean canDeserialize(final Manifest evidence$10) {
         return ScalaObjectMapper.super.canDeserialize(evidence$10);
      }

      public Object readValue(final File src, final Manifest evidence$11) {
         return ScalaObjectMapper.super.readValue(src, evidence$11);
      }

      public Object readValue(final URL src, final Manifest evidence$12) {
         return ScalaObjectMapper.super.readValue(src, evidence$12);
      }

      public Object readValue(final String content, final Manifest evidence$13) {
         return ScalaObjectMapper.super.readValue(content, evidence$13);
      }

      public Object readValue(final Reader src, final Manifest evidence$14) {
         return ScalaObjectMapper.super.readValue(src, evidence$14);
      }

      public Object readValue(final InputStream src, final Manifest evidence$15) {
         return ScalaObjectMapper.super.readValue(src, evidence$15);
      }

      public Object readValue(final byte[] src, final Manifest evidence$16) {
         return ScalaObjectMapper.super.readValue(src, evidence$16);
      }

      public Object readValue(final byte[] src, final int offset, final int len, final Manifest evidence$17) {
         return ScalaObjectMapper.super.readValue(src, offset, len, evidence$17);
      }

      public Object updateValue(final Object valueToUpdate, final File src, final Manifest evidence$18) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$18);
      }

      public Object updateValue(final Object valueToUpdate, final URL src, final Manifest evidence$19) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$19);
      }

      public Object updateValue(final Object valueToUpdate, final String content, final Manifest evidence$20) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, content, evidence$20);
      }

      public Object updateValue(final Object valueToUpdate, final Reader src, final Manifest evidence$21) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$21);
      }

      public Object updateValue(final Object valueToUpdate, final InputStream src, final Manifest evidence$22) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$22);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final Manifest evidence$23) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$23);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final Manifest evidence$24) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, offset, len, evidence$24);
      }

      public ObjectWriter writerWithView(final Manifest evidence$26) {
         return ScalaObjectMapper.super.writerWithView(evidence$26);
      }

      /** @deprecated */
      public ObjectWriter writerWithType(final Manifest evidence$27) {
         return ScalaObjectMapper.super.writerWithType(evidence$27);
      }

      public ObjectWriter writerFor(final Manifest evidence$28) {
         return ScalaObjectMapper.super.writerFor(evidence$28);
      }

      /** @deprecated */
      public ObjectReader reader(final Manifest evidence$29) {
         return ScalaObjectMapper.super.reader(evidence$29);
      }

      public ObjectReader readerFor(final Manifest evidence$30) {
         return ScalaObjectMapper.super.readerFor(evidence$30);
      }

      public ObjectReader readerWithView(final Manifest evidence$31) {
         return ScalaObjectMapper.super.readerWithView(evidence$31);
      }

      public Object convertValue(final Object fromValue, final Manifest evidence$32) {
         return ScalaObjectMapper.super.convertValue(fromValue, evidence$32);
      }

      /** @deprecated */
      public JsonSchema generateJsonSchema(final Manifest evidence$33) {
         return ScalaObjectMapper.super.generateJsonSchema(evidence$33);
      }

      public void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper visitor, final Manifest evidence$34) {
         ScalaObjectMapper.super.acceptJsonFormatVisitor(visitor, evidence$34);
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP;
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION;
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP = x$1;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION = x$1;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE = x$1;
      }

      public Mixin(final JsonMapper mapper) {
         super(mapper);
         ScalaObjectMapper.$init$(this);
         Statics.releaseFence();
      }
   }

   public static final class ObjectMapperMixin extends ObjectMapper implements ScalaObjectMapper {
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP;
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION;
      private Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE;

      /** @deprecated */
      public final ObjectMapper addMixin(final Manifest evidence$1, final Manifest evidence$2) {
         return ScalaObjectMapper.super.addMixin(evidence$1, evidence$2);
      }

      /** @deprecated */
      public final ObjectMapper addMixInAnnotations(final Manifest evidence$3, final Manifest evidence$4) {
         return ScalaObjectMapper.super.addMixInAnnotations(evidence$3, evidence$4);
      }

      /** @deprecated */
      public final Class findMixInClassFor(final Manifest evidence$5) {
         return ScalaObjectMapper.super.findMixInClassFor(evidence$5);
      }

      public JavaType constructType(final Manifest m) {
         return ScalaObjectMapper.super.constructType(m);
      }

      public Object readValue(final JsonParser jp, final Manifest evidence$6) {
         return ScalaObjectMapper.super.readValue(jp, evidence$6);
      }

      public MappingIterator readValues(final JsonParser jp, final Manifest evidence$7) {
         return ScalaObjectMapper.super.readValues(jp, evidence$7);
      }

      public Object treeToValue(final TreeNode n, final Manifest evidence$8) {
         return ScalaObjectMapper.super.treeToValue(n, evidence$8);
      }

      /** @deprecated */
      public boolean canSerialize(final Manifest evidence$9) {
         return ScalaObjectMapper.super.canSerialize(evidence$9);
      }

      /** @deprecated */
      public boolean canDeserialize(final Manifest evidence$10) {
         return ScalaObjectMapper.super.canDeserialize(evidence$10);
      }

      public Object readValue(final File src, final Manifest evidence$11) {
         return ScalaObjectMapper.super.readValue(src, evidence$11);
      }

      public Object readValue(final URL src, final Manifest evidence$12) {
         return ScalaObjectMapper.super.readValue(src, evidence$12);
      }

      public Object readValue(final String content, final Manifest evidence$13) {
         return ScalaObjectMapper.super.readValue(content, evidence$13);
      }

      public Object readValue(final Reader src, final Manifest evidence$14) {
         return ScalaObjectMapper.super.readValue(src, evidence$14);
      }

      public Object readValue(final InputStream src, final Manifest evidence$15) {
         return ScalaObjectMapper.super.readValue(src, evidence$15);
      }

      public Object readValue(final byte[] src, final Manifest evidence$16) {
         return ScalaObjectMapper.super.readValue(src, evidence$16);
      }

      public Object readValue(final byte[] src, final int offset, final int len, final Manifest evidence$17) {
         return ScalaObjectMapper.super.readValue(src, offset, len, evidence$17);
      }

      public Object updateValue(final Object valueToUpdate, final File src, final Manifest evidence$18) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$18);
      }

      public Object updateValue(final Object valueToUpdate, final URL src, final Manifest evidence$19) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$19);
      }

      public Object updateValue(final Object valueToUpdate, final String content, final Manifest evidence$20) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, content, evidence$20);
      }

      public Object updateValue(final Object valueToUpdate, final Reader src, final Manifest evidence$21) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$21);
      }

      public Object updateValue(final Object valueToUpdate, final InputStream src, final Manifest evidence$22) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$22);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final Manifest evidence$23) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, evidence$23);
      }

      public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final Manifest evidence$24) {
         return ScalaObjectMapper.super.updateValue(valueToUpdate, src, offset, len, evidence$24);
      }

      public ObjectWriter writerWithView(final Manifest evidence$26) {
         return ScalaObjectMapper.super.writerWithView(evidence$26);
      }

      /** @deprecated */
      public ObjectWriter writerWithType(final Manifest evidence$27) {
         return ScalaObjectMapper.super.writerWithType(evidence$27);
      }

      public ObjectWriter writerFor(final Manifest evidence$28) {
         return ScalaObjectMapper.super.writerFor(evidence$28);
      }

      /** @deprecated */
      public ObjectReader reader(final Manifest evidence$29) {
         return ScalaObjectMapper.super.reader(evidence$29);
      }

      public ObjectReader readerFor(final Manifest evidence$30) {
         return ScalaObjectMapper.super.readerFor(evidence$30);
      }

      public ObjectReader readerWithView(final Manifest evidence$31) {
         return ScalaObjectMapper.super.readerWithView(evidence$31);
      }

      public Object convertValue(final Object fromValue, final Manifest evidence$32) {
         return ScalaObjectMapper.super.convertValue(fromValue, evidence$32);
      }

      /** @deprecated */
      public JsonSchema generateJsonSchema(final Manifest evidence$33) {
         return ScalaObjectMapper.super.generateJsonSchema(evidence$33);
      }

      public void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper visitor, final Manifest evidence$34) {
         ScalaObjectMapper.super.acceptJsonFormatVisitor(visitor, evidence$34);
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP;
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION;
      }

      public Class com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE() {
         return this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$MAP = x$1;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$OPTION = x$1;
      }

      public final void com$fasterxml$jackson$module$scala$ScalaObjectMapper$_setter_$com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE_$eq(final Class x$1) {
         this.com$fasterxml$jackson$module$scala$ScalaObjectMapper$$ITERABLE = x$1;
      }

      public ObjectMapperMixin(final ObjectMapper mapper) {
         super(mapper);
         ScalaObjectMapper.$init$(this);
         Statics.releaseFence();
      }
   }
}
