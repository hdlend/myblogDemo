package com.hdlend.myblog.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileUpload {
	
	@RequestMapping(value="/uploadFileVew")  
	public String uploadFile() {
		
		return "thymeleaf/admin/uploadFile";
	}
	
	@RequestMapping(value="fileUpload",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> singleFileUpload(@RequestParam("file") MultipartFile file) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		 /* if(file.isEmpty()){
			  map.put("status", "false");
			  return map;
	        }*/
	        String fileName = file.getOriginalFilename();
	        //System.getProperty("user.dir") 项目路径
	        String path = System.getProperty("user.dir") + "/uploadFile" ;
	        File dest = new File(path + "/" + fileName);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
	            dest.getParentFile().mkdir();
	        }
	        try {
	            file.transferTo(dest); //保存文件
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	            map.put("status", "false");
	        } catch (IOException e) {
	            e.printStackTrace();
	            map.put("status", "false");
	        }

		return map;
	}
	
	public static void main(String[] args) {
		System.out.println("当前程序所在目录：" + System.getProperty("user.dir")); // 当前程序所在目录
	}
}
