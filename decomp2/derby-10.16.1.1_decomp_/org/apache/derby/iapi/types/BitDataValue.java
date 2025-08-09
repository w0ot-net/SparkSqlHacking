package org.apache.derby.iapi.types;

import java.sql.Blob;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.shared.common.error.StandardException;

public interface BitDataValue extends ConcatableDataValue, StreamStorable {
   BitDataValue concatenate(BitDataValue var1, BitDataValue var2, BitDataValue var3) throws StandardException;

   void setValue(Blob var1) throws StandardException;
}
