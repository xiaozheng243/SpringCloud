package online.yuluo.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/consumer")
public class IndexController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${eureka.instance.instance-id}")
	private String ipAdress;

	@Value("${server.port}")
	private String port;

//	private String directUrl = "http://MICRO-CLIENT/api/index/getMsg";

//	private String msg = "Hello," + ipAdress + ":" + port;

	@GetMapping(value = "/getMsg")
	public String getMsg() {
		return restTemplate.getForObject("http://localhost:8001/api/index/getMsg", String.class);
	}

}
