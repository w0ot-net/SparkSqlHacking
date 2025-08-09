package org.apache.arrow.vector.complex.reader;

public interface FieldReader extends BaseReader.StructReader, BaseReader.ListReader, BaseReader.MapReader, BaseReader.ScalarReader, BaseReader.RepeatedStructReader, BaseReader.RepeatedListReader, BaseReader.RepeatedMapReader {
}
