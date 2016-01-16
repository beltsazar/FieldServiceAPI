package org.marcuse.fieldservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
public class EchoController {

    @RequestMapping("/echo")
    public Echo greeting(@RequestParam(value="name", defaultValue="Echooo!!!") String echo,
    		@RequestParam(value="times", defaultValue="1") int times,
    		@RequestParam(value="delay", defaultValue="0") int delay) {
    	try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new Echo (echo, times);
    }
}
