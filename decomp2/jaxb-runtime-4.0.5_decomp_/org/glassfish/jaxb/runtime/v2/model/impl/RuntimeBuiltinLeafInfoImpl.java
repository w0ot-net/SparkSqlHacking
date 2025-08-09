package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.ByteArrayDataSource;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.xml.bind.MarshalException;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeBuiltinLeafInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.output.Pcdata;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Base64Data;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.glassfish.jaxb.runtime.v2.util.ByteArrayOutputStreamEx;
import org.glassfish.jaxb.runtime.v2.util.DataSourceSource;
import org.xml.sax.SAXException;

public abstract class RuntimeBuiltinLeafInfoImpl extends BuiltinLeafInfoImpl implements RuntimeBuiltinLeafInfo, Transducer {
   private static final Logger logger = org.glassfish.jaxb.core.Utils.getClassLogger();
   public static final Map LEAVES = new HashMap();
   public static final RuntimeBuiltinLeafInfoImpl STRING;
   private static final String DATE = "date";
   public static final List builtinBeanInfos;
   public static final String MAP_ANYURI_TO_URI = "mapAnyUriToUri";
   public static final String USE_OLD_GMONTH_MAPPING = "jaxb.ri.useOldGmonthMapping";
   private static final Map xmlGregorianCalendarFormatString;
   private static final Map xmlGregorianCalendarFieldRef;

   private RuntimeBuiltinLeafInfoImpl(Class type, QName... typeNames) {
      super(type, typeNames);
      LEAVES.put(type, this);
   }

   public final Class getClazz() {
      return (Class)this.getType();
   }

   public final Transducer getTransducer() {
      return this;
   }

   public boolean useNamespace() {
      return false;
   }

   public void declareNamespace(Object o, XMLSerializer w) throws AccessorException {
   }

   public QName getTypeName(Object instance) {
      return null;
   }

   private static QName createXS(String typeName) {
      return new QName("http://www.w3.org/2001/XMLSchema", typeName);
   }

   private static byte[] decodeBase64(CharSequence text) {
      if (text instanceof Base64Data) {
         Base64Data base64Data = (Base64Data)text;
         return base64Data.getExact();
      } else {
         return DatatypeConverterImpl._parseBase64Binary(text.toString());
      }
   }

   private static void checkXmlGregorianCalendarFieldRef(QName type, XMLGregorianCalendar cal) throws MarshalException {
      StringBuilder buf = new StringBuilder();
      int bitField = (Integer)xmlGregorianCalendarFieldRef.get(type);
      int l = 1;
      int pos = 0;

      while(bitField != 0) {
         int bit = bitField & 1;
         bitField >>>= 4;
         ++pos;
         if (bit == 1) {
            switch (pos) {
               case 1:
                  if (cal.getSecond() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_SEC);
                  }
                  break;
               case 2:
                  if (cal.getMinute() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_MIN);
                  }
                  break;
               case 3:
                  if (cal.getHour() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_HR);
                  }
                  break;
               case 4:
                  if (cal.getDay() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_DAY);
                  }
                  break;
               case 5:
                  if (cal.getMonth() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_MONTH);
                  }
                  break;
               case 6:
                  if (cal.getYear() == Integer.MIN_VALUE) {
                     buf.append("  ").append(Messages.XMLGREGORIANCALENDAR_YEAR);
                  }
               case 7:
            }
         }
      }

      if (buf.length() > 0) {
         String var10002 = Messages.XMLGREGORIANCALENDAR_INVALID.format(type.getLocalPart());
         throw new MarshalException(var10002 + buf);
      }
   }

   static {
      String MAP_ANYURI_TO_URI_VALUE = (String)AccessController.doPrivileged(new PrivilegedAction() {
         public String run() {
            return System.getProperty("mapAnyUriToUri");
         }
      });
      QName[] qnames = MAP_ANYURI_TO_URI_VALUE == null ? new QName[]{createXS("string"), createXS("anySimpleType"), createXS("normalizedString"), createXS("anyURI"), createXS("token"), createXS("language"), createXS("Name"), createXS("NCName"), createXS("NMTOKEN"), createXS("ENTITY")} : new QName[]{createXS("string"), createXS("anySimpleType"), createXS("normalizedString"), createXS("token"), createXS("language"), createXS("Name"), createXS("NCName"), createXS("NMTOKEN"), createXS("ENTITY")};
      STRING = new StringImplImpl(String.class, qnames);
      ArrayList<RuntimeBuiltinLeafInfoImpl<?>> secondaryList = new ArrayList();
      secondaryList.add(new StringImpl(Character.class, new QName[]{createXS("unsignedShort")}) {
         public Character parse(CharSequence text) {
            return (char)DatatypeConverterImpl._parseInt(text);
         }

         public String print(Character v) {
            return Integer.toString(v);
         }
      });
      secondaryList.add(new StringImpl(Calendar.class, new QName[]{DatatypeConstants.DATETIME}) {
         public Calendar parse(CharSequence text) {
            return DatatypeConverterImpl._parseDateTime(text.toString());
         }

         public String print(Calendar v) {
            return DatatypeConverterImpl._printDateTime(v);
         }
      });
      secondaryList.add(new StringImpl(GregorianCalendar.class, new QName[]{DatatypeConstants.DATETIME}) {
         public GregorianCalendar parse(CharSequence text) {
            return DatatypeConverterImpl._parseDateTime(text.toString());
         }

         public String print(GregorianCalendar v) {
            return DatatypeConverterImpl._printDateTime(v);
         }
      });
      secondaryList.add(new StringImpl(Date.class, new QName[]{DatatypeConstants.DATETIME}) {
         public Date parse(CharSequence text) {
            return DatatypeConverterImpl._parseDateTime(text.toString()).getTime();
         }

         public String print(Date v) {
            XMLSerializer xs = XMLSerializer.getInstance();
            QName type = xs.getSchemaType();
            GregorianCalendar cal = new GregorianCalendar(0, 0, 0);
            cal.setTime(v);
            return type != null && "http://www.w3.org/2001/XMLSchema".equals(type.getNamespaceURI()) && "date".equals(type.getLocalPart()) ? DatatypeConverterImpl._printDate(cal) : DatatypeConverterImpl._printDateTime(cal);
         }
      });
      secondaryList.add(new StringImpl(File.class, new QName[]{createXS("string")}) {
         public File parse(CharSequence text) {
            return new File(WhiteSpaceProcessor.trim(text).toString());
         }

         public String print(File v) {
            return v.getPath();
         }
      });
      secondaryList.add(new StringImpl(URL.class, new QName[]{createXS("anyURI")}) {
         public URL parse(CharSequence text) throws SAXException {
            TODO.checkSpec("JSR222 Issue #42");

            try {
               return new URL(WhiteSpaceProcessor.trim(text).toString());
            } catch (MalformedURLException e) {
               UnmarshallingContext.getInstance().handleError((Exception)e);
               return null;
            }
         }

         public String print(URL v) {
            return v.toExternalForm();
         }
      });
      if (MAP_ANYURI_TO_URI_VALUE == null) {
         secondaryList.add(new StringImpl(URI.class, new QName[]{createXS("string")}) {
            public URI parse(CharSequence text) throws SAXException {
               try {
                  return new URI(text.toString());
               } catch (URISyntaxException e) {
                  UnmarshallingContext.getInstance().handleError((Exception)e);
                  return null;
               }
            }

            public String print(URI v) {
               return v.toString();
            }
         });
      }

      secondaryList.add(new StringImpl(Class.class, new QName[]{createXS("string")}) {
         public Class parse(CharSequence text) throws SAXException {
            TODO.checkSpec("JSR222 Issue #42");

            try {
               String name = WhiteSpaceProcessor.trim(text).toString();
               ClassLoader cl = UnmarshallingContext.getInstance().classLoader;
               if (cl == null) {
                  cl = Thread.currentThread().getContextClassLoader();
               }

               return cl != null ? cl.loadClass(name) : Class.forName(name);
            } catch (ClassNotFoundException e) {
               UnmarshallingContext.getInstance().handleError((Exception)e);
               return null;
            }
         }

         public String print(Class v) {
            return v.getName();
         }
      });
      secondaryList.add(new PcdataImpl(Image.class, new QName[]{createXS("base64Binary")}) {
         public Image parse(CharSequence text) throws SAXException {
            try {
               InputStream is;
               if (text instanceof Base64Data) {
                  is = ((Base64Data)text).getInputStream();
               } else {
                  is = new ByteArrayInputStream(RuntimeBuiltinLeafInfoImpl.decodeBase64(text));
               }

               BufferedImage var3;
               try {
                  var3 = ImageIO.read(is);
               } finally {
                  is.close();
               }

               return var3;
            } catch (IOException e) {
               UnmarshallingContext.getInstance().handleError((Exception)e);
               return null;
            }
         }

         private BufferedImage convertToBufferedImage(Image image) throws IOException {
            if (image instanceof BufferedImage) {
               return (BufferedImage)image;
            } else {
               MediaTracker tracker = new MediaTracker(new Component() {
               });
               tracker.addImage(image, 0);

               try {
                  tracker.waitForAll();
               } catch (InterruptedException e) {
                  throw new IOException(e.getMessage());
               }

               BufferedImage bufImage = new BufferedImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), 2);
               Graphics g = bufImage.createGraphics();
               g.drawImage(image, 0, 0, (ImageObserver)null);
               return bufImage;
            }
         }

         public Base64Data print(Image v) {
            ByteArrayOutputStreamEx imageData = new ByteArrayOutputStreamEx();
            XMLSerializer xs = XMLSerializer.getInstance();
            String mimeType = xs.getXMIMEContentType();
            if (mimeType == null || mimeType.startsWith("image/*")) {
               mimeType = "image/png";
            }

            try {
               Iterator<ImageWriter> itr = ImageIO.getImageWritersByMIMEType(mimeType);
               if (!itr.hasNext()) {
                  xs.handleEvent(new ValidationEventImpl(1, Messages.NO_IMAGE_WRITER.format(mimeType), xs.getCurrentLocation((String)null)));
                  throw new RuntimeException("no encoder for MIME type " + mimeType);
               }

               ImageWriter w = (ImageWriter)itr.next();
               ImageOutputStream os = ImageIO.createImageOutputStream(imageData);
               w.setOutput(os);
               w.write(this.convertToBufferedImage(v));
               os.close();
               w.dispose();
            } catch (IOException e) {
               xs.handleError(e);
               throw new RuntimeException(e);
            }

            Base64Data bd = new Base64Data();
            imageData.set(bd, mimeType);
            return bd;
         }
      });
      secondaryList.add(new PcdataImpl(DataHandler.class, new QName[]{createXS("base64Binary")}) {
         public DataHandler parse(CharSequence text) {
            return text instanceof Base64Data ? ((Base64Data)text).getDataHandler() : new DataHandler(new ByteArrayDataSource(RuntimeBuiltinLeafInfoImpl.decodeBase64(text), UnmarshallingContext.getInstance().getXMIMEContentType()));
         }

         public Base64Data print(DataHandler v) {
            Base64Data bd = new Base64Data();
            bd.set(v);
            return bd;
         }
      });
      secondaryList.add(new PcdataImpl(Source.class, new QName[]{createXS("base64Binary")}) {
         public Source parse(CharSequence text) throws SAXException {
            try {
               return text instanceof Base64Data ? new DataSourceSource(((Base64Data)text).getDataHandler()) : new DataSourceSource(new ByteArrayDataSource(RuntimeBuiltinLeafInfoImpl.decodeBase64(text), UnmarshallingContext.getInstance().getXMIMEContentType()));
            } catch (MimeTypeParseException e) {
               UnmarshallingContext.getInstance().handleError((Exception)e);
               return null;
            }
         }

         public Base64Data print(Source v) {
            XMLSerializer xs = XMLSerializer.getInstance();
            Base64Data bd = new Base64Data();
            String contentType = xs.getXMIMEContentType();
            MimeType mt = null;
            if (contentType != null) {
               try {
                  mt = new MimeType(contentType);
               } catch (MimeTypeParseException e) {
                  xs.handleError(e);
               }
            }

            if (v instanceof DataSourceSource) {
               DataSource ds = ((DataSourceSource)v).getDataSource();
               String dsct = ds.getContentType();
               if (dsct != null && (contentType == null || contentType.equals(dsct))) {
                  bd.set(new DataHandler(ds));
                  return bd;
               }
            }

            String charset = null;
            if (mt != null) {
               charset = mt.getParameter("charset");
            }

            if (charset == null) {
               charset = "UTF-8";
            }

            try {
               ByteArrayOutputStreamEx baos = new ByteArrayOutputStreamEx();
               Transformer tr = xs.getIdentityTransformer();
               String defaultEncoding = tr.getOutputProperty("encoding");
               tr.setOutputProperty("encoding", charset);
               tr.transform(v, new StreamResult(new OutputStreamWriter(baos, charset)));
               tr.setOutputProperty("encoding", defaultEncoding);
               baos.set(bd, "application/xml; charset=" + charset);
               return bd;
            } catch (TransformerException e) {
               xs.handleError(e);
            } catch (UnsupportedEncodingException e) {
               xs.handleError(e);
            }

            bd.set(new byte[0], "application/xml");
            return bd;
         }
      });
      secondaryList.add(new StringImpl(XMLGregorianCalendar.class, new QName[]{createXS("anySimpleType"), DatatypeConstants.DATE, DatatypeConstants.DATETIME, DatatypeConstants.TIME, DatatypeConstants.GMONTH, DatatypeConstants.GDAY, DatatypeConstants.GYEAR, DatatypeConstants.GYEARMONTH, DatatypeConstants.GMONTHDAY}) {
         public String print(XMLGregorianCalendar cal) {
            XMLSerializer xs = XMLSerializer.getInstance();
            QName type = xs.getSchemaType();
            if (type != null) {
               try {
                  RuntimeBuiltinLeafInfoImpl.checkXmlGregorianCalendarFieldRef(type, cal);
                  String format = (String)RuntimeBuiltinLeafInfoImpl.xmlGregorianCalendarFormatString.get(type);
                  if (format != null) {
                     return this.format(format, cal);
                  }
               } catch (MarshalException e) {
                  xs.handleEvent(new ValidationEventImpl(0, e.getMessage(), xs.getCurrentLocation((String)null)));
                  return "";
               }
            }

            return cal.toXMLFormat();
         }

         public XMLGregorianCalendar parse(CharSequence lexical) throws SAXException {
            try {
               return DatatypeConverterImpl.getDatatypeFactory().newXMLGregorianCalendar(lexical.toString().trim());
            } catch (Exception e) {
               UnmarshallingContext.getInstance().handleError(e);
               return null;
            }
         }

         private String format(String format, XMLGregorianCalendar value) {
            StringBuilder buf = new StringBuilder();
            int fidx = 0;
            int flen = format.length();

            while(fidx < flen) {
               char fch = format.charAt(fidx++);
               if (fch != '%') {
                  buf.append(fch);
               } else {
                  switch (format.charAt(fidx++)) {
                     case 'D':
                        this.printNumber(buf, value.getDay(), 2);
                        break;
                     case 'M':
                        this.printNumber(buf, value.getMonth(), 2);
                        break;
                     case 'Y':
                        this.printNumber(buf, value.getEonAndYear(), 4);
                        break;
                     case 'h':
                        this.printNumber(buf, value.getHour(), 2);
                        break;
                     case 'm':
                        this.printNumber(buf, value.getMinute(), 2);
                        break;
                     case 's':
                        this.printNumber(buf, value.getSecond(), 2);
                        if (value.getFractionalSecond() != null) {
                           String frac = value.getFractionalSecond().toPlainString();
                           buf.append(frac.substring(1));
                        }
                        break;
                     case 'z':
                        int offset = value.getTimezone();
                        if (offset == 0) {
                           buf.append('Z');
                        } else if (offset != Integer.MIN_VALUE) {
                           if (offset < 0) {
                              buf.append('-');
                              offset *= -1;
                           } else {
                              buf.append('+');
                           }

                           this.printNumber(buf, offset / 60, 2);
                           buf.append(':');
                           this.printNumber(buf, offset % 60, 2);
                        }
                        break;
                     default:
                        throw new InternalError();
                  }
               }
            }

            return buf.toString();
         }

         private void printNumber(StringBuilder out, BigInteger number, int nDigits) {
            String s = number.toString();

            for(int i = s.length(); i < nDigits; ++i) {
               out.append('0');
            }

            out.append(s);
         }

         private void printNumber(StringBuilder out, int number, int nDigits) {
            String s = String.valueOf(number);

            for(int i = s.length(); i < nDigits; ++i) {
               out.append('0');
            }

            out.append(s);
         }

         public QName getTypeName(XMLGregorianCalendar cal) {
            return cal.getXMLSchemaType();
         }
      });
      ArrayList<RuntimeBuiltinLeafInfoImpl<?>> primaryList = new ArrayList();
      primaryList.add(STRING);
      primaryList.add(new StringImpl(Boolean.class, new QName[]{createXS("boolean")}) {
         public Boolean parse(CharSequence text) {
            return DatatypeConverterImpl._parseBoolean(text);
         }

         public String print(Boolean v) {
            return v.toString();
         }
      });
      primaryList.add(new PcdataImpl(byte[].class, new QName[]{createXS("base64Binary"), createXS("hexBinary")}) {
         public byte[] parse(CharSequence text) {
            return RuntimeBuiltinLeafInfoImpl.decodeBase64(text);
         }

         public Base64Data print(byte[] v) {
            XMLSerializer w = XMLSerializer.getInstance();
            Base64Data bd = new Base64Data();
            String mimeType = w.getXMIMEContentType();
            bd.set(v, mimeType);
            return bd;
         }
      });
      primaryList.add(new StringImpl(Byte.class, new QName[]{createXS("byte")}) {
         public Byte parse(CharSequence text) {
            return DatatypeConverterImpl._parseByte(text);
         }

         public String print(Byte v) {
            return DatatypeConverterImpl._printByte(v);
         }
      });
      primaryList.add(new StringImpl(Short.class, new QName[]{createXS("short"), createXS("unsignedByte")}) {
         public Short parse(CharSequence text) {
            return DatatypeConverterImpl._parseShort(text);
         }

         public String print(Short v) {
            return DatatypeConverterImpl._printShort(v);
         }
      });
      primaryList.add(new StringImpl(Integer.class, new QName[]{createXS("int"), createXS("unsignedShort")}) {
         public Integer parse(CharSequence text) {
            return DatatypeConverterImpl._parseInt(text);
         }

         public String print(Integer v) {
            return DatatypeConverterImpl._printInt(v);
         }
      });
      primaryList.add(new StringImpl(Long.class, new QName[]{createXS("long"), createXS("unsignedInt")}) {
         public Long parse(CharSequence text) {
            return DatatypeConverterImpl._parseLong(text);
         }

         public String print(Long v) {
            return DatatypeConverterImpl._printLong(v);
         }
      });
      primaryList.add(new StringImpl(Float.class, new QName[]{createXS("float")}) {
         public Float parse(CharSequence text) {
            return DatatypeConverterImpl._parseFloat(text.toString());
         }

         public String print(Float v) {
            return DatatypeConverterImpl._printFloat(v);
         }
      });
      primaryList.add(new StringImpl(Double.class, new QName[]{createXS("double")}) {
         public Double parse(CharSequence text) {
            return DatatypeConverterImpl._parseDouble(text);
         }

         public String print(Double v) {
            return DatatypeConverterImpl._printDouble(v);
         }
      });
      primaryList.add(new StringImpl(BigInteger.class, new QName[]{createXS("integer"), createXS("positiveInteger"), createXS("negativeInteger"), createXS("nonPositiveInteger"), createXS("nonNegativeInteger"), createXS("unsignedLong")}) {
         public BigInteger parse(CharSequence text) {
            return DatatypeConverterImpl._parseInteger(text);
         }

         public String print(BigInteger v) {
            return DatatypeConverterImpl._printInteger(v);
         }
      });
      primaryList.add(new StringImpl(BigDecimal.class, new QName[]{createXS("decimal")}) {
         public BigDecimal parse(CharSequence text) {
            return DatatypeConverterImpl._parseDecimal(text.toString());
         }

         public String print(BigDecimal v) {
            return DatatypeConverterImpl._printDecimal(v);
         }
      });
      primaryList.add(new StringImpl(QName.class, new QName[]{createXS("QName")}) {
         public QName parse(CharSequence text) throws SAXException {
            try {
               return DatatypeConverterImpl._parseQName(text.toString(), UnmarshallingContext.getInstance());
            } catch (IllegalArgumentException e) {
               UnmarshallingContext.getInstance().handleError((Exception)e);
               return null;
            }
         }

         public String print(QName v) {
            return DatatypeConverterImpl._printQName(v, XMLSerializer.getInstance().getNamespaceContext());
         }

         public boolean useNamespace() {
            return true;
         }

         public void declareNamespace(QName v, XMLSerializer w) {
            w.getNamespaceContext().declareNamespace(v.getNamespaceURI(), v.getPrefix(), false);
         }
      });
      if (MAP_ANYURI_TO_URI_VALUE != null) {
         primaryList.add(new StringImpl(URI.class, new QName[]{createXS("anyURI")}) {
            public URI parse(CharSequence text) throws SAXException {
               try {
                  return new URI(text.toString());
               } catch (URISyntaxException e) {
                  UnmarshallingContext.getInstance().handleError((Exception)e);
                  return null;
               }
            }

            public String print(URI v) {
               return v.toString();
            }
         });
      }

      primaryList.add(new StringImpl(Duration.class, new QName[]{createXS("duration")}) {
         public String print(Duration duration) {
            return duration.toString();
         }

         public Duration parse(CharSequence lexical) {
            TODO.checkSpec("JSR222 Issue #42");
            return DatatypeConverterImpl.getDatatypeFactory().newDuration(lexical.toString());
         }
      });
      primaryList.add(new StringImpl(Void.class, new QName[0]) {
         public String print(Void value) {
            return "";
         }

         public Void parse(CharSequence lexical) {
            return null;
         }
      });
      List<RuntimeBuiltinLeafInfoImpl<?>> l = new ArrayList(secondaryList.size() + primaryList.size() + 1);
      l.addAll(secondaryList);

      try {
         l.add(new UUIDImpl());
      } catch (LinkageError var6) {
      }

      l.addAll(primaryList);
      builtinBeanInfos = Collections.unmodifiableList(l);
      xmlGregorianCalendarFormatString = new HashMap();
      Map<QName, String> m = xmlGregorianCalendarFormatString;
      m.put(DatatypeConstants.DATETIME, "%Y-%M-%DT%h:%m:%s%z");
      m.put(DatatypeConstants.DATE, "%Y-%M-%D%z");
      m.put(DatatypeConstants.TIME, "%h:%m:%s%z");
      String oldGmonthMappingProperty = (String)AccessController.doPrivileged(new PrivilegedAction() {
         public String run() {
            return System.getProperty("jaxb.ri.useOldGmonthMapping");
         }
      });
      if (oldGmonthMappingProperty == null) {
         m.put(DatatypeConstants.GMONTH, "--%M%z");
      } else {
         if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Old GMonth mapping used.");
         }

         m.put(DatatypeConstants.GMONTH, "--%M--%z");
      }

      m.put(DatatypeConstants.GDAY, "---%D%z");
      m.put(DatatypeConstants.GYEAR, "%Y%z");
      m.put(DatatypeConstants.GYEARMONTH, "%Y-%M%z");
      m.put(DatatypeConstants.GMONTHDAY, "--%M-%D%z");
      xmlGregorianCalendarFieldRef = new HashMap();
      m = xmlGregorianCalendarFieldRef;
      m.put(DatatypeConstants.DATETIME, 17895697);
      m.put(DatatypeConstants.DATE, 17895424);
      m.put(DatatypeConstants.TIME, 16777489);
      m.put(DatatypeConstants.GDAY, 16781312);
      m.put(DatatypeConstants.GMONTH, 16842752);
      m.put(DatatypeConstants.GYEAR, 17825792);
      m.put(DatatypeConstants.GYEARMONTH, 17891328);
      m.put(DatatypeConstants.GMONTHDAY, 16846848);
   }

   private abstract static class StringImpl extends RuntimeBuiltinLeafInfoImpl {
      protected StringImpl(Class type, QName... typeNames) {
         super(type, typeNames);
      }

      public abstract String print(Object var1) throws AccessorException;

      public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         w.text(this.print(o), fieldName);
      }

      public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         w.leafElement(tagName, this.print(o), fieldName);
      }
   }

   private abstract static class PcdataImpl extends RuntimeBuiltinLeafInfoImpl {
      protected PcdataImpl(Class type, QName... typeNames) {
         super(type, typeNames);
      }

      public abstract Pcdata print(Object var1) throws AccessorException;

      public final void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         w.text(this.print(o), fieldName);
      }

      public final void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
         w.leafElement(tagName, this.print(o), fieldName);
      }
   }

   private static class UUIDImpl extends StringImpl {
      public UUIDImpl() {
         super(UUID.class, RuntimeBuiltinLeafInfoImpl.createXS("string"));
      }

      public UUID parse(CharSequence text) throws SAXException {
         TODO.checkSpec("JSR222 Issue #42");

         try {
            return UUID.fromString(WhiteSpaceProcessor.trim(text).toString());
         } catch (IllegalArgumentException e) {
            UnmarshallingContext.getInstance().handleError((Exception)e);
            return null;
         }
      }

      public String print(UUID v) {
         return v.toString();
      }
   }

   private static class StringImplImpl extends StringImpl {
      public StringImplImpl(Class type, QName[] typeNames) {
         super(type, typeNames);
      }

      public String parse(CharSequence text) {
         return text.toString();
      }

      public String print(String s) {
         return s;
      }

      public final void writeText(XMLSerializer w, String o, String fieldName) throws IOException, SAXException, XMLStreamException {
         w.text(o, fieldName);
      }

      public final void writeLeafElement(XMLSerializer w, Name tagName, String o, String fieldName) throws IOException, SAXException, XMLStreamException {
         w.leafElement(tagName, o, fieldName);
      }
   }
}
