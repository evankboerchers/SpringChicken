package test.analytics;



import static org.junit.jupiter.api.Assertions.*;

import com.springchicken.analytics.KeyValuePair;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TimeZone;


public class KeyValuePairTest
{
    private static final String KEY_STRING = "key";

    @Test
    public void testNull()
    {
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, (Object)null);
        assertEquals("key=\"null\"", kvp.toString());
    }

    @Test
    public void testPrimitives()
    {
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, 1);
        assertEquals("key=\"1\"", kvp.toString(), "Primitive 'int' should be boxed");

        kvp = new KeyValuePair(KEY_STRING, 1.2345e3);
        assertEquals("key=\"1234.5\"", kvp.toString(), "Primitive 'double' should be boxed");
    }

    @Test
    public void testDate()
    {
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, new Date(1479164861000L));

        final TimeZone orgTz = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("MST"));
        assertEquals("key=\"2016-11-14T16:07:41MST\"", kvp.toString());
        TimeZone.setDefault(orgTz);
    }

    @Test
    public void testStrings()
    {
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, "Value with hole");
        assertEquals("key=\"Value with hole\"", kvp.toString());

        kvp = new KeyValuePair(KEY_STRING, "Value with \"hole\"");
        assertEquals("key=\"Value with [\\quote]hole[\\quote]\"", kvp.toString());

        kvp = new KeyValuePair(KEY_STRING, "Value with \"hole\"\n"
                + "and second line");
        assertEquals("key=\"Value with [\\quote]hole[\\quote]\nand second line\"", kvp.toString());
    }

    @Test
    public void testObject()
    {
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, new Boo());
        assertEquals("key=\"Boo is an object\"", kvp.toString());
    }

    @Test
    public void testDelayedConversion()
    {
        Boo booStub = new Boo();
        KeyValuePair kvp = new KeyValuePair(KEY_STRING, booStub);

        assertFalse(booStub.wasConverted(), "Premature conversion");
        assertNotNull(kvp.toString());
        assertTrue(booStub.wasConverted(), "KeyValuePair should convert");
    }

    // Mockito can stub, but can't verify toString()
    // This is the reason for wasConverted() method
    static class Boo
    {
        private boolean converted = false;

        @Override
        public String toString()
        {
            converted = true;
            return "Boo is an object";
        }

        public boolean wasConverted()
        {
            return converted;
        }
    }
}
