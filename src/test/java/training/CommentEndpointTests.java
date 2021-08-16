package training;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CommentEndpointTests
{
    @Test
    public void getAllData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getDataByFName(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        given().
              queryParam("FName", "Hildagarde Kubach").
        when().
              get(endpoint).
        then().
              log().body();
    }

    @Test
    public void getDataById(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        var response = given().
                queryParam("id", "10").
                when().
                get(endpoint).
                then();

                response.log().body();
    }

    @Test
    public void getDataPagination(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        var response = given().
                when().
                get(endpoint+"?page=2&_limit=10").
                then();

        response.log().body();
    }

    @Test
    public void createData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        String body = """
                {
                "id": 26,
                "Email": "mngo.sylla@gmail.com",
                "FName": "Mango Sylla",
                "PostId": "1002"
                }
                """;
        var response = given().body(body).
                when().
                post(endpoint).then();

        response.log().body();

    }

    @Test
    public void updateData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        String body = """
                {
                "id": 1,
                "Email": "mingo.four@gmail.com",
                "FName": "Mingo Four"
                }
                """;
        var response =
                    given().body(body).
                                    when().
                                        put(endpoint)
                                                    .then();

        response.log().body();

    }

    @Test
    public void patchData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        String body = """
                {
                "id": 1
                }
                """;
        var response =
                given().body(body).
                        when().
                        patch(endpoint)
                        .then();

        response.log().body();

    }

    @Test
    public void deleteData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        String body = """
                {
                "id": 1
                }
                """;
        var response =
                given().body(body).
                        when().
                        delete(endpoint)
                        .then();

        response.log().body();

    }

    @Test
    public void getStatusCode(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        var response = given().
                queryParam("id", "15").
                when().
                get(endpoint).
                then().assertThat().statusCode(200);

        response.log().body();
    }

    @Test
    public void getDataBody(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        given().
                queryParam("id", "15").
                when().
                get(endpoint).
                then().log().body().
                assertThat().statusCode(200).
                body("id", everyItem(notNullValue())).
                body("Email", everyItem(notNullValue())).
                body("FName", everyItem(notNullValue())).
                body("PostId", everyItem(notNullValue()));
    }

    @Test
    public void getDataHeaders(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        given().
                queryParam("id", "15").
                when().
                get(endpoint).
                then().log().headers().
                assertThat().statusCode(200).
                header("Content-Type", equalTo("application/json; charset=utf-8")).
                header("Content-Length", equalTo("118")).
                header("Cache-Control", equalTo("no-cache")).
                header("Pragma", equalTo("no-cache")).
                header("X-Content-Type-Options", equalTo("nosniff")).
                header("Access-Control-Allow-Credentials", equalTo("true"));

    }

    @Test
    public void serializedData(){
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        PostData postData = new PostData(
                15,
                "apyne3o@sourceforge.net",
                "Will MacCafferky",
                "591"
        );

        var response = given().body(postData).when().post(endpoint).then();

        response.log().body();

    }

    @Test
    public void deserializedData()
    {
        String endpoint = "https://retoolapi.dev/cI5RBd/data";

        ObjectMapper mapper = new ObjectMapper();
        PostData jsonData = new PostData(
                    15,
                    "apyne3o@sourceforge.net",
                    "Will MacCafferky",
                    "591"
        );

        try
        {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var expectedJsonData = mapper.writeValueAsString(jsonData.toString());

            PostData actualJsonData =
                    given().
                            queryParam("id", 15).
                            when().
                            get(endpoint).as(PostData.class);

            assertThat(actualJsonData, samePropertyValuesAs(expectedJsonData));

        }
        catch (IOException exception)
        {
            exception.getMessage();
        }

    }
}
