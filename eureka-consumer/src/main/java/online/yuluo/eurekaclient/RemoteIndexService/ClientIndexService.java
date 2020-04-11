package online.yuluo.eurekaclient.RemoteIndexService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("micro-client")
public interface ClientIndexService {

	@GetMapping(value = "/api/index/getMsg")
	public String getMsg();
}
