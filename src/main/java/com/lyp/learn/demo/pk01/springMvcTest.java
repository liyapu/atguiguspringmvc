package com.lyp.learn.demo.pk01;


import com.lyp.learn.demo.pk01.beans.Student;
import com.lyp.learn.demo.pk01.crud.entities.Employee;
import com.lyp.learn.demo.pk01.exception.UserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @SessionAttributes 注解标注在类上
 * 会把类文件中，所有放在请求域中的 匹配 value 或者 types 的，
 *  value : 指定请求域中的属性的key
 *  types : 指定 属性值的类型
 * 放在session域中一份
 */
@SessionAttributes(value = {"studentZhangSan"},types = {String.class})
@RequestMapping(value = "/smtest")
@Controller
public class springMvcTest {

    private static final String SUCCESS = "success";
    private static final String SUCCESS2 = "success2";
    private static final String ERROR = "error";


    @RequestMapping(value = "/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("testRequestMapping ....");
        return SUCCESS;
    }

    @RequestMapping(value = "testMethodParams",params = {"userName","age != 10"})
    public String testMethodParams(){
        System.out.println("testMethodParams ....");
        return SUCCESS;
    }

    @RequestMapping(value = "/testPathVariable/{userName}/list")
    public String testPathVariable(@PathVariable("userName") String un){
        System.out.println("testPathVariable  userName = " + un);
        return SUCCESS;
    }

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "userName") String un,
                                   @RequestParam(value = "age",required = false) Integer age,
                                   @RequestParam(value = "address",defaultValue = "商丘") String address){
        System.out.println("testRequestParam userName=" + un + ", age = " + age + ",address = " + address);
        return SUCCESS;
    }

    @RequestMapping("/testPojo")
    public String testPojo(Student student){
        System.out.println(student);
        return SUCCESS;
    }


    @RequestMapping("/testServletApi")
    public String testServletApi(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session){
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        return SUCCESS;
    }

    /**
     * 目标返回值类型 可以是 ModelAndView 类型的
     * 其中可以包含视图和模型信息
     * sprignMVC会把ModelAndView的 model 中的数据放入到 request 域对象中
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView = new ModelAndView(SUCCESS2);

        //添加模型数据到 modelAndView中
        modelAndView.addObject("time",new Date());
        return modelAndView;
    }


    /**
     * 目标方法可以添加 Map 类型(实际上也可以是 Model 类型 或者 ModelMap类型)的参数
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("names",Arrays.asList("Tom","Mike","Jerry"));
        return SUCCESS2;
    }


    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object> map){
        Student student = new Student();
        student.setName("张三");
        student.setAge(10);

        map.put("studentZhangSan",student);
        map.put("strType","字符串类型的测试");
        return SUCCESS2;
    }


    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false) Integer id,Map<String,Object> map){
        System.out.println("ModelAttribute ... getUser.....");
        if(id != null){
            //模拟从数据库查询信息
            Student student = new Student(1,"张三","123456",10);
            System.out.println("从数据库获取的对象 ：" + student);
            map.put("student",student);
        }
    }

    /**
     * 修改student 属性，但是有些属性不能修改，比如 注册时间，密码等
     * 所以此对象需要先从数据库查出来，把前台没有传过来的值补上
     *
     * 注意：在@ModelAtribute 修饰的方法中，放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致
     * @param student
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(Student student){
        System.out.println("testModelAttribute student:" + student);
        return SUCCESS;
    }


    @RequestMapping("/testHelloView")
    public String testHelloView(){
        System.out.println(" test Hello View ");
        return "helloView";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("testForward");
        return "forward:/WEB-INF/views/success2.jsp";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testReditect");
        // WEB-INF下的页面，由于安全问题，直接重定向不能访问到
       // return "redirect:/WEB-INF/views/success2.jsp";
        return "redirect:/index.jsp";
    }


    /**
     * 使用 ResponseEntity 进行下载文件
     * @return
     */
    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        String filename = "test.txt";
        InputStream in = servletContext.getResourceAsStream("/files/"+filename);
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename="+filename);
        HttpStatus status = HttpStatus.OK;

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body,headers,status);
        return responseEntity;

    }

//    @Autowired
//    private ResourceBundleMessageSource messageSource;
    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
        System.out.println("i18n......");
        //String val = messageSource.getMessage("userName",null,locale);
        String val = null;
        System.out.println(val);
        return "i18n";
    }


    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam(value = "desc") String desc, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc : " + desc);
        if(file.getSize() > 0L && !file.isEmpty()){
            System.out.println("file.getOriginalFilename() :" + file.getOriginalFilename());
            System.out.println("file.getName():" + file.getName());
            System.out.println("file.getContentType():" + file.getContentType());
            file.transferTo(new File("D:\\temp\\" + file.getOriginalFilename()));
        }
        return SUCCESS;
    }

//
//    @ExceptionHandler({ArithmeticException.class})
//    public String handleArithmeticException(Exception ex){
//        System.out.println("出错了 ： handleArithmeticException 在处理");
//        System.out.println("具体异常信息：：：："+ex);
//        return ERROR;
//    }


//    @ExceptionHandler
//    public String handleRuntime(Exception ex){
//        System.out.println("出错了 runtime  ： handleRuntime 在处理");
//        System.out.println("具体异常信息：：：："+ex);
//        return ERROR;
//    }


    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(Integer i){
        System.out.println("i is  = " + i);
        System.out.println("10/i = " + 10/i) ;
        return SUCCESS;
    }

    @ResponseStatus(value = HttpStatus.GONE,reason = "用户名错误,方法级别")
    @RequestMapping("/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(Integer i){
        if(i == 13){
            throw  new UserNameException();
        }
        System.out.println("testResponseStatusExceptionResolver .....");
        return SUCCESS;
    }

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(Integer i){
        int [] arr = new int[5];
        System.out.println("testSimpleMappingExceptionResolver .....");
        if(i == 15){
            throw  new RuntimeException();
        }
        System.out.println(arr[i]);
        System.out.println("i is  = " + i);
        System.out.println("10/i = " + 10/i) ;

        return SUCCESS;
    }


}
