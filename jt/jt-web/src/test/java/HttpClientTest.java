import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void httpClient(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url="https://www.jd.com";
		HttpGet get = new HttpGet();
		
		
	}
}
