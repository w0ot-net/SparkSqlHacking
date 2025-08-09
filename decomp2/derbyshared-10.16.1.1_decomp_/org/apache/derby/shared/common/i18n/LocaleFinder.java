package org.apache.derby.shared.common.i18n;

import java.text.DateFormat;
import java.util.Locale;
import org.apache.derby.shared.common.error.StandardException;

public interface LocaleFinder {
   Locale getCurrentLocale() throws StandardException;

   DateFormat getDateFormat() throws StandardException;

   DateFormat getTimeFormat() throws StandardException;

   DateFormat getTimestampFormat() throws StandardException;
}
