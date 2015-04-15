package example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Main {
	@RequestMapping
	@ResponseBody
	public  String name() {
		return "hello";
	}

    public static void main(String[] args) {
    }

}