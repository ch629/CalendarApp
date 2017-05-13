package calendar;

import org.junit.Test;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;
import uk.ac.brighton.uni.na3.utils.NetworkUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNetworkUtils extends BaseTest {
    @Test
    public void testGetRequest() {
        final boolean[] ok = {false};

        NetworkUtils.get("salt/root", SingleDataResponse.class)
                .ifOK(r -> ok[0] = true)
                .orElse(r -> ok[0] = false);

        assertThat(ok[0]).isTrue();
    }

    @Test
    public void testValidPostRequest() {
        final boolean[] ok = {false};

        NetworkUtils.post("test", new SingleDataResponse<>(1), Response.class)
                .ifOK(res -> ok[0] = true)
                .orElse(res -> ok[0] = false);

        assertThat(ok[0]).isTrue();
    }

    @Test
    public void test404PostRequest() {
        final boolean[] ok = {false};

        NetworkUtils.post("testing", null, Response.class)
                .ifOK(res -> ok[0] = true)
                .orElse(res -> ok[0] = false);

        assertThat(ok[0]).isFalse();
    }

    @Test
    public void testBadPostRequest() {
        final boolean[] ok = {false};

        NetworkUtils.post("test", new SingleDataResponse<>(2), Response.class)
                .ifOK(res -> ok[0] = true)
                .orElse(res -> ok[0] = false);

        assertThat(ok[0]).isFalse();
    }
}
