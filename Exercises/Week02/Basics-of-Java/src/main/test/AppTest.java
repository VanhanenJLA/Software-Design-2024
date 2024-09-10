import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void should_form_uri_for_posts_api() {
        // Given
        PostService postService = new PostService();
        int postId = 1;
        // When
        var uri = postService.getPostsEndPoint(postId);
        // Then
        assertEquals("https://jsonplaceholder.typicode.com/posts/1", uri.toString());
    }
}
