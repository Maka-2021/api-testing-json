package java;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BasicEndpointTests
{
    @Test
    public void getAllPosts(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getAPost(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        given().queryParam("id", "11").
                when().
                get(endpoint).
                then().log().body();

    }

    @Test
    public void createPost(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        String body = """
                {
                    "userId": 10,
                    "id":101
                    "title": "Africa/Guinea",
                    "body": "2021-08-13T"
                }\s
                """;
        var response =
                given().
                        body(body).
                        when().
                        post(endpoint).
                        then();

        System.out.println("\nPost are created!!!\n");

        response.log().body();

    }

    @Test
    public void updatePost(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        String body = """
                {
                "id" : 101
                "title" : "foo",
                "body" : "bar",
                }
                 """;
        var response =
                given().
                        body(body).
                        when().
                        put(endpoint).
                        then();

        System.out.println("\nPost are updated!!!\n");

        response.log().body();

    }

    @Test
    public void patchingPost(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        String body = """
                {
                "title" : "foo"
                }
                 """;
        var response =
                given().
                        body(body).
                        when().
                        patch(endpoint).
                        then();

        System.out.println("\nPost are patched!!!\n");

        response.log().body();

    }

    @Test
    public void verifyStatusCode(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        given().queryParam("id", "15").
                when().
                get(endpoint).
                then().log().body().
                assertThat().statusCode(200);

    }

    @Test
    public void verifyBody(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        var response = given().queryParam("id", "10").
                when().
                get(endpoint).
                    then().
                        assertThat().statusCode(200).
                        body("userId", everyItem(notNullValue())).
                        body("title", everyItem(notNullValue())).
                        body("body", everyItem(notNullValue())).
                        body("id", everyItem(notNullValue()));

        response.log().body();
    }

    @Test
    public void verifyHeaders(){
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        given().queryParam("id", "10").
                when().
                get(endpoint).
                then().log().headers().
                    assertThat().statusCode(200).
                    header("Content-Type", equalTo("application/json; charset=utf-8")).
                    header("Connection", equalTo("keep-alive")).
                    header("Transfer-Encoding", equalTo("chunked")).
                    header("Content-Encoding", equalTo("gzip")).
                    header("vary", equalTo("Origin, Accept-Encoding")).
                    header("cache-control", equalTo("max-age=43200")).
                    header("x-content-type-options", equalTo("nosniff")).
                    header("Content-Encoding", equalTo("gzip")).
                    header("Server", equalTo("cloudflare"));
    }

}
