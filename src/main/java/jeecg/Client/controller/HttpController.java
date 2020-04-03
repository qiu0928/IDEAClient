package jeecg.Client.controller;

import jeecg.Client.Entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/htt")
public class HttpController {
    @GetMapping("/doGetControllerOne")
    public  String doGEtControllerOne(){
        return "123";
    }
    @GetMapping("/doGetControllerTwo")
    public  String doGEtControllerTwo(@RequestParam(name = "name")String name,@RequestParam(name = "age")Integer age){
        return "没想到["+name+age+"岁了！";
    }
    @PostMapping("/doPostControllerThree")
    public String  doPostControllerThree(@RequestBody User user,@RequestParam(name = "flag") Integer flag,@RequestParam(name = "meaning") String meaning){
        return  user.toString()+"**"+flag+"**"+meaning;
    }
}
