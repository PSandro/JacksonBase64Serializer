import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FooSerializerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private static String FOO_JSON_BASE64 = "\"eyJmaWVsZDEiOiJmb28iLCJmaWVsZDIiOjQyfQ==\"";

    @Before
    public void setup() throws IOException {
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new FooBeanSerializerModifier());
        this.mapper.registerModule(module);
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    public void testFooSerialize() throws IOException {
        final Foo foo = new Foo();
        final String base64Json = this.mapper.writeValueAsString(foo);

        assertNotNull(base64Json);
        assertEquals(FOO_JSON_BASE64, base64Json);
    }

}
