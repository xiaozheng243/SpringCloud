package online.yuluo.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

	@Value("${eureka.instance.instance-id}")
	private String ipAdress;

	@Value("${server.port}")
	private String port;

	@GetMapping(value = "/getMsg")
	public String getMsg() {
		return "Hello," + ipAdress + ":" + port;
	}

}
