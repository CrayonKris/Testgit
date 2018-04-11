package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.service.PropertieService;
import com.jt.common.vo.PicUploadResult;

@Controller
public class PicUploadController {
	@Autowired
	private PropertieService props;
	private static final Logger log=Logger.getLogger(PicUploadController.class);
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult picUpload(MultipartFile uploadFile){
		/*
		 * 1.拿到文件名，拿到文件扩展名，判断是否为合法扩展名
		 * 2.判断是否为木马
		 * 3.声成两个路径 图片的真实存放路径 网络访问的相对路径
		 * 4.图片存放目录 yyyy/mm/dd
		 * 5.不能使用文件原文件名称，重命名;计算方法不唯一
		 * current time +3位随机数  uuid+3位随机数
		 * 6.保存图片
		 * 7.生成一个picUpload的对象来封装数据
		 */
		PicUploadResult result = new PicUploadResult();
		try{
		String oldFileName = uploadFile.getOriginalFilename();
		String extFileName = oldFileName.substring(oldFileName.lastIndexOf("."));
		//文件名后缀判断,如果不是满足图片后缀的
		if(!extFileName.matches("^.*(jpg|gif|png|jpeg|)$")){
			result.setError(1);
		}
		//木马文件的判断
		BufferedImage bufImage = ImageIO.read(uploadFile.getInputStream());
		result.setHeight(bufImage.getHeight()+"");
		result.setWidth(bufImage.getWidth()+"");
		//生成有格式的路径
		String dir="/images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
		//"http://image.jt.com"
		String urlPrefix = props.IMAGE_BASE_URL+dir;//相对路径
		//绝对路径"c:/jt-upload/images/"+dir 属性解耦操作
		String path = props.REPOSITORY_PATH+dir;
		File _dir = new File(path);
		//判断是否存
		if(!_dir.exists()){//不存在直接创建
			_dir.mkdirs();//多级目录
		}
		//生成根据计算方法完成的文件名称重命名
		String fileName = System.currentTimeMillis()+""+
		RandomUtils.nextInt(100, 999)+extFileName;
		result.setUrl(urlPrefix+fileName);
		//保存到磁盘
		uploadFile.transferTo(new File(path+fileName));
		}catch(Exception e){
			log.error(e.getMessage());
			result.setError(1);
		}
		return result;
	}

}
