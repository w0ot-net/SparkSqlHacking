package javolution.xml.stream;

public interface XMLStreamWriter {
   void writeStartElement(CharSequence var1) throws XMLStreamException;

   void writeStartElement(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeStartElement(CharSequence var1, CharSequence var2, CharSequence var3) throws XMLStreamException;

   void writeEmptyElement(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeEmptyElement(CharSequence var1, CharSequence var2, CharSequence var3) throws XMLStreamException;

   void writeEmptyElement(CharSequence var1) throws XMLStreamException;

   void writeEndElement() throws XMLStreamException;

   void writeEndDocument() throws XMLStreamException;

   void close() throws XMLStreamException;

   void flush() throws XMLStreamException;

   void writeAttribute(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeAttribute(CharSequence var1, CharSequence var2, CharSequence var3, CharSequence var4) throws XMLStreamException;

   void writeAttribute(CharSequence var1, CharSequence var2, CharSequence var3) throws XMLStreamException;

   void writeNamespace(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeDefaultNamespace(CharSequence var1) throws XMLStreamException;

   void writeComment(CharSequence var1) throws XMLStreamException;

   void writeProcessingInstruction(CharSequence var1) throws XMLStreamException;

   void writeProcessingInstruction(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeCData(CharSequence var1) throws XMLStreamException;

   void writeDTD(CharSequence var1) throws XMLStreamException;

   void writeEntityRef(CharSequence var1) throws XMLStreamException;

   void writeStartDocument() throws XMLStreamException;

   void writeStartDocument(CharSequence var1) throws XMLStreamException;

   void writeStartDocument(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void writeCharacters(CharSequence var1) throws XMLStreamException;

   void writeCharacters(char[] var1, int var2, int var3) throws XMLStreamException;

   CharSequence getPrefix(CharSequence var1) throws XMLStreamException;

   void setPrefix(CharSequence var1, CharSequence var2) throws XMLStreamException;

   void setDefaultNamespace(CharSequence var1) throws XMLStreamException;

   Object getProperty(String var1) throws IllegalArgumentException;
}
