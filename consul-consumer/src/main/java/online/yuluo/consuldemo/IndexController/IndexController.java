package online.yuluo.consuldemo.IndexController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

	@Value("${spring.application.name}")
	private String ipAdress;

	@Value("${server.port}")
	private String port;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping(value = "/getMsg")
	public String getMsg() {
		return restTemplate.getForObject("http://consul-producer/api/index/getMsg", String.class);
	}

	@GetMapping(value = "/getInstances/{serviceId}")
	public String getInstancesInfo(@PathVariable String serviceId) {
		List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
		return JSON.toJSONString(instanceList);
	}
}