package cl.foxblue.news.jsonb.defaults;

import cl.foxblue.news.jsonb.fixture.BasicObject;
import org.eclipse.persistence.json.bind.internal.properties.MessageKeys;
import org.eclipse.persistence.json.bind.internal.properties.Messages;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author daniel
 */
public class NestedClassTest {

    private final Jsonb jsonb = JsonbBuilder.create();


    /**
     *
      3.7.2 Nested Classes
        Implementations MUST support the binding of public and protected nested classes.
     For deserialization operations, both nested and encapsulating classes MUST fulfill
     the same instantiation requirements as specified in 3.7.1.

     */




    @Test
    public void shouldSerializeNestedClass(){
        String serializedJson = jsonb.toJson(new NestedClass());
        assertThat(serializedJson, is("{\"id\":0}"));
    }

    @Test
    public void shouldDeserializeNestedClass(){
        String origin = "{\"id\":1}";
        NestedClass nestedInstance = jsonb.fromJson(origin, NestedClass.class);
        assertThat(nestedInstance.getId(),is(1));
    }

    @Test
    public void shouldSerializeMemberClass(){
        String serializedJson = jsonb.toJson(new MemberClass());
        assertThat(serializedJson, is("{\"id\":0}"));
    }

    /**
     * NOTE:
     * this test is failing, cause they are not considering the hidden parameter of the member classes.
     */
    @Test
    public void shouldDeserializeMemberClass(){
        String origin = "{\"id\":1}";
        MemberClass memberInstance = jsonb.fromJson(origin, MemberClass.class);
        assertThat(memberInstance.getId(),is(1));
    }



    /**
     * a simple nested class.
     */
    public static class NestedClass{
        private int id;

        public NestedClass() {
        }

        public int getId() { return id;}
        public void setId(int id) {this.id = id;}
    }



    /**
     * a simple nested class.
     */
    public class MemberClass{
        private int id;

        public MemberClass() {
        }

        public int getId() { return id;}
        public void setId(int id) {this.id = id;}
    }

}
