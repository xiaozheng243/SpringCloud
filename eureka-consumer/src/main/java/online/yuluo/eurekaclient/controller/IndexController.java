package online.yuluo.eurekaclient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import online.yuluo.eurekaclient.RemoteIndexService.ClientIndexService;

@RestController
@RequestMapping(value = "/api/consumer")
public class IndexController {

	Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private ClientIndexService remoteService;

	@GetMapping(value = "/getMsg")
	public String getMsg() {
		return restTemplate.getForObject("http://MICRO-CLIENT/api/index/getMsg", String.class);
	}

	@GetMapping(value = "/getMsg/{serviceId}")
	public String getInstanceMsg(@PathVariable String serviceId) {
		List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
		if (instanceList.isEmpty()) {
			logger.info("");
			// throw
		}
		ServiceInstance serviceInstance = instanceList.get(0);
		return restTemplate.getForObject(
				"http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/api/index/getMsg",
				String.class);
	}

	@GetMapping(value = "/getInstances/{serviceId}")
	public String getInstancesInfo(@PathVariable String serviceId) {
		List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
		return JSON.toJSONString(instanceList);
	}

	@GetMapping(value = "/getRemoteMsg")
	public String remoteIndexController() {
		return remoteService.getMsg();
	}
}
