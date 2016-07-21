package cl.foxblue.news.jsonb;

import cl.foxblue.news.jsonb.fixture.BasicObject;
import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.stream.JsonParsingException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author daniel
 */
public class BasicMappingTest {

    private Jsonb jsonb;

    @Before
    public void setUp() throws Exception {
        jsonb = JsonbBuilder.create();
    }


    @Test
    public void shouldSerializeBasicObjects(){
        String serializedJson = jsonb.toJson(new BasicObject());
        assertThat(serializedJson, is("{\"id\":0}"));
    }

    @Test
    public void shouldDeserializeBasicObject(){
        String origin = "{\"id\":1}";
        BasicObject basicObject = jsonb.fromJson(origin, BasicObject.class);
        assertThat(basicObject.getId(),is(1));
    }

    @Test(expected = JsonParsingException.class)
    public void failureOnDeserealizationOfMalformed(){
        String malformedJson = "{id:1}";
        jsonb.fromJson(malformedJson, BasicObject.class); //should throw an exception
    }



}
