package javolution.xml.stream;

import javolution.text.CharArray;

public interface XMLStreamReader extends XMLStreamConstants {
   Object getProperty(String var1) throws IllegalArgumentException;

   int next() throws XMLStreamException;

   void require(int var1, CharSequence var2, CharSequence var3) throws XMLStreamException;

   CharArray getElementText() throws XMLStreamException;

   int nextTag() throws XMLStreamException;

   boolean hasNext() throws XMLStreamException;

   void close() throws XMLStreamException;

   CharArray getNamespaceURI(CharSequence var1);

   boolean isStartElement();

   boolean isEndElement();

   boolean isCharacters();

   boolean isWhiteSpace();

   CharArray getAttributeValue(CharSequence var1, CharSequence var2);

   int getAttributeCount();

   CharArray getAttributeNamespace(int var1);

   CharArray getAttributeLocalName(int var1);

   CharArray getAttributePrefix(int var1);

   CharArray getAttributeType(int var1);

   CharArray getAttributeValue(int var1);

   boolean isAttributeSpecified(int var1);

   int getNamespaceCount();

   CharArray getNamespacePrefix(int var1);

   CharArray getNamespaceURI(int var1);

   NamespaceContext getNamespaceContext();

   int getEventType();

   CharArray getText();

   char[] getTextCharacters();

   int getTextCharacters(int var1, char[] var2, int var3, int var4) throws XMLStreamException;

   int getTextStart();

   int getTextLength();

   String getEncoding();

   boolean hasText();

   Location getLocation();

   CharArray getLocalName();

   boolean hasName();

   CharArray getNamespaceURI();

   CharArray getPrefix();

   CharArray getVersion();

   boolean isStandalone();

   boolean standaloneSet();

   CharArray getCharacterEncodingScheme();

   CharArray getPITarget();

   CharArray getPIData();
}
