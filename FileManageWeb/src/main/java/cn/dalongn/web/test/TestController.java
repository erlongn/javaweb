package cn.dalongn.web.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import cn.dalongn.web.constants.WebViewDefalut;
import cn.dalongn.web.uitls.FileUtiles;

@Controller
@RequestMapping("/test") // 指定唯一一个*.do请求关联到该Controller
public class TestController {

	@Autowired
	private GenericManageableCaptchaService captchaService;

	@RequestMapping(value = "one", method = { RequestMethod.POST, RequestMethod.GET })
	public String one(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.setAttribute("username", "username3");

		ServletContext servletContext = request.getServletContext();
		servletContext.setAttribute("username", "username4");

		// 获取url 表单数据 get post 只有获取的方法，没有设置的方法
		System.out.println(request.getParameter("username"));

		// 请求转发 设置和获取
		request.setAttribute("username", "username2");
		System.out.println(request.getAttribute("username"));

		return "redirect:/test/two";
	}

	@RequestMapping(value = "one1", method = { RequestMethod.POST, RequestMethod.GET })
	public String one1(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.setAttribute("username", "username3");

		ServletContext servletContext = request.getServletContext();
		servletContext.setAttribute("username", "username4");

		// 获取url 表单数据 get post 只有获取的方法，没有设置的方法
		System.out.println(request.getParameter("username"));

		// 请求转发 设置和获取
		request.setAttribute("username", "username2");
		System.out.println(request.getAttribute("username"));

		return "index";
	}

	@RequestMapping(value = "two", method = { RequestMethod.POST, RequestMethod.GET })
	public String two(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "大龙");
		modelMap.put("key", map);
		return WebViewDefalut.STRING_JSON;
	}

	@RequestMapping(value = "three", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object three() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "大龙");
		return map;
	}

	@RequestMapping(value = "four", method = { RequestMethod.POST, RequestMethod.GET })
	public String four(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		modelMap.put("file", FileUtiles.toByteArray("C:\\Users\\DaLon\\Desktop\\大龙.txt"));
		modelMap.put("name", "大龙.txt");
		return WebViewDefalut.DOWNLOAD_FILE;
	}

	@RequestMapping(value = "jcaptcha", method = { RequestMethod.POST, RequestMethod.GET })
	public void jcaptcha(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws IOException {

		try {
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String captchaId = request.getSession().getId();

			BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());
			// Boolean isResponseCorrect =
			// captchaService.validateResponseForID(request.getSession().getId(),
			// "");

			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentType("image/jpeg");

			ImageIO.write(challenge, "jpeg", jpegOutputStream);
			byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

			ServletOutputStream respOs = response.getOutputStream();
			respOs.write(captchaChallengeAsJpeg);
			respOs.flush();
			respOs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "five", method = { RequestMethod.POST, RequestMethod.GET })
	public String five(String content, String name, Integer age, ModelMap modelMap) throws IOException {

		modelMap.put("name", name);
		modelMap.put("age", age);
		modelMap.put("content", content);

		return WebViewDefalut.STRING_JSON;
	}

	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	public String addUser(@RequestParam("file") CommonsMultipartFile[] files,
			@RequestParam("file1") CommonsMultipartFile[] files1, HttpServletRequest request, String content,
			String name, Integer age, ModelMap modelMap, String[] aihaoes) {

		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					CommonsMultipartFile commonsMultipartFile = files[i];
					String myFileName = files[i].getOriginalFilename();
					System.out.println(myFileName);

					// 重命名上传后的文件名
					// 定义上传路径
					// String path = "G:/" + new Date().getTime() + myFileName;
					// File localFile = new File(path);
					// commonsMultipartFile.transferTo(localFile);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}

		for (int i = 0; i < files1.length; i++) {
			if (!files1[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					CommonsMultipartFile commonsMultipartFile = files1[i];
					String myFileName = files1[i].getOriginalFilename();
					System.out.println(myFileName);

					// 重命名上传后的文件名
					// 定义上传路径
					// String path = "G:/" + new Date().getTime() + myFileName;
					// File localFile = new File(path);
					// commonsMultipartFile.transferTo(localFile);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}
		modelMap.put("name", name);
		modelMap.put("age", age);
		modelMap.put("content", content);
		modelMap.put("aihaoes", aihaoes);

		return WebViewDefalut.STRING_JSON;
	}

	@RequestMapping(value = "/upload2", method = { RequestMethod.POST })
	public String upload2(HttpServletRequest request, HttpServletResponse response, String content, String name,
			Integer age, ModelMap modelMap, String[] aihaoes) throws IllegalStateException, IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {

				// 取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());

				if (files != null) {
					for (MultipartFile file : files) {

						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							System.out.println(myFileName);

							// 记录上传过程起始时的时间，用来计算上传时间
							int pre = (int) System.currentTimeMillis();

							// 定义上传路径
							// String path = "G:/" + new Date().getTime() +
							// myFileName;
							// File localFile = new File(path);
							// file.transferTo(localFile);

							// 记录上传该文件后的时间
							int finaltime = (int) System.currentTimeMillis();
							System.out.println(finaltime - pre);
						}
					}
				}
			}

		}
		modelMap.put("name", name);
		modelMap.put("age", age);
		modelMap.put("content", content);
		modelMap.put("aihaoes", aihaoes);

		return WebViewDefalut.STRING_JSON;
	}

	@RequestMapping(value = "getAndPost", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object getAndPost(String name, Integer age, String me, String[] shuzu) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		map.put("me", me);
		map.put("shuzu", shuzu);
		return map;
	}

	@RequestMapping(value = "getAndPostRedirect", method = { RequestMethod.POST, RequestMethod.GET })
	public String getAndPostRedirect(String name, Integer age, String me, String[] shuzu) {
		System.out.println(name);
		System.out.println(age);
		System.out.println(me);
		System.out.println(shuzu);
		return "redirect:/test/two";
	}

	@RequestMapping(value = "getCrossDomain", method = { RequestMethod.POST, RequestMethod.GET })
	public String getCrossDomain(String me, String name, Integer age, String[] shuzu, ModelMap modelMap,
			String dalongCallBack) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		map.put("me", me);
		map.put("shuzu", shuzu);

		modelMap.put("method", dalongCallBack);
		modelMap.put("args", map);

		return WebViewDefalut.CROSS_DOMAIN;
	}

	@RequestMapping(value = "postJson", method = { RequestMethod.POST })
	@ResponseBody
	public Object postJson(String[] me, @RequestBody Person person) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("urlme", me);
		map.put("name", person.getName());
		map.put("age", person.getAge());
		map.put("me", person.getMe());
		return map;
	}
}

class Person {
	private String name;
	private Integer age;
	private String[] me;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String[] getMe() {
		return me;
	}

	public void setMe(String[] me) {
		this.me = me;
	}
}
