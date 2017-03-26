package pl.qus.xenoamp.musicbrainz.util;

import org.jdom2.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Helper code for the jdom2 API.
 */
public final class JDomUtils {

    private JDomUtils() {
    }

    /**
     * @param e {@link Element}
     * @param childName name of child node
     * @param defaultValue
     * @return a child node value as a string or default value if doesn't exist or other error.
     */
    public @Nullable static String getChildValueAsString(@Nonnull final Element e, @Nonnull final String childName, @Nullable final String defaultValue) {
        if (e.getChild(childName, e.getNamespace()) != null) {
            return e.getChild(childName, e.getNamespace()).getValue();
        }

        return defaultValue;
    }

    /**
     * @param e {@link Element}
     * @param childName name of child node
     * @return a child value as a string or null if null or other error.
     */
    public @Nullable static String getChildValueAsString(@Nonnull final Element e, @Nonnull final String childName) {
        return getChildValueAsString(e, childName, null);
    }

    /**
     * @param e {@link Element}
     * @param childName name of child
     * @param defaultValue default value if error or does not exist
     * @return a child node value as an integer, or default value for any error.
     */
    public static int getChildValueAsInteger(final Element e, final String childName, final int defaultValue) {
        try {
            final String strVal = getChildValueAsString(e, childName);

            return Integer.parseInt(strVal);
        } catch (final RuntimeException exception) {
            return defaultValue;
        }
    }
}
