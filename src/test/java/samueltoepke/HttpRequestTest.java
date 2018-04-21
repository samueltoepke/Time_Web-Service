package samueltoepke;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// Adapted from: https://spring.io/guides/gs/testing-web/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
     * getTimeDefaultShouldReturnGmt(). REST test to make sure
     *   default gettime request returns time in GMT.
     *   
     * @author Samuel Lee Toepke
     * 
     * @version 1.0
     */
    @Test
    public void getTimeDefaultShouldReturnGmt() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/gettime", String.class)).contains("GMT");
    }

    /*
     * getTimeWithInputShouldReturnPst(). REST test to make sure
     *   input PST returns time PST.
     *   
     * @author Samuel Lee Toepke
     * 
     * @version 1.0
     */
    @Test
    public void getTimeWithInputShouldReturnPst() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/gettime?tz=PST", String.class)).contains("PST");
    }

    /*
     * getTimeWithBadInputShouldReturnError(). REST test to make sure
     *   an incorrect input returns an error.
     *   
     * @author Samuel Lee Toepke
     * 
     * @version 1.0
     */
    @Test
    public void getTimeWithBadInputShouldReturnError() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/gettime?tz=fizz", String.class)).contains("Bad Request");
    }
}