import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class PostService {

    final HttpClient client;
    final URI root = URI.create("https://jsonplaceholder.typicode.com/");

    public PostService() {
        client = HttpClient.newBuilder().build();
    }

    public Optional<PostDTO> getPost(int id) {
        var uri = getPostsEndPoint(id);
        var req = HttpRequest.newBuilder().uri(uri).build();

        try {
            var response = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
                return Optional.empty();
            }
            var post = new ObjectMapper().readValue(response.body(), PostDTO.class);
            return Optional.of(post);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
    public URI getPostsEndPoint(int id) {
        return URI.create(root + "posts/" + id);
    }
    
}