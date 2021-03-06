package com.cy.wx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.wx.message.mass.UArticle;

import comm.InitUtil;

public class MaterialUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitUtil.initAccessToken();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddMedia() {
		String url = "http://b.hiphotos.baidu.com/baike/g%3D0%3Bw%3D268/sign=92e00c9b8f5494ee97220a125ac8d2c8/29381f30e924b899c83ff41c6d061d950a7bf697.jpg";
		Assert.assertNotNull(MaterialUtil.addMedia(InitUtil.accessToken, "image", url));
	}
	
	@Test
	public void testGetMedia(){
		String mediaId = "06fg_Zf-O8uoSAufmaApcLTuiHpesYRkAYsP1V2ISgahJRV28glD04-HwP63-gre";
		Assert.assertNotNull(MaterialUtil.getMedia(InitUtil.accessToken, mediaId, "D:/test"));
	}
	
	@Test
	public void testAddNews(){
		List<UArticle> uList = new ArrayList<UArticle>();
		UArticle uArticle = new UArticle();
		uArticle.setThumb_media_id("ohBGsOcqzyMpbKzLN8DFKYv0Xjw9nIkcKTedtHDWCwc"); // 永久的media_id
		uArticle.setAuthor("boolink3");
		uArticle.setTitle("双川恩图3");
		uArticle.setContent_source_url("http://www.baidu.com");
		uArticle.setContent("content3");
		uArticle.setDigest("digest3");
		uArticle.setShow_cover_pic("1");
		uList.add(uArticle);
		
//		UArticle uArticle02 = new UArticle();
//		uArticle02.setThumb_media_id("qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p"); // 永久的
//		uArticle02.setAuthor("boolink2");
//		uArticle02.setTitle("双川恩图2");
//		uArticle02.setContent_source_url("");
//		uArticle02.setContent("content2");
//		uArticle02.setDigest("digest");
//		uArticle02.setShow_cover_pic("1");
//		uList.add(uArticle02);
		Map<String,List<UArticle>> map = new HashMap<String,List<UArticle>>();
		map.put("articles", uList);
		System.out.println(JSONObject.fromObject(map).toString());
		Assert.assertNotNull(MaterialUtil.addNews(InitUtil.accessToken, JSONObject.fromObject(map).toString()));
	}

	@Test
	public void testAddMaterial(){
		//String url = "http://b.hiphotos.baidu.com/baike/g%3D0%3Bw%3D268/sign=92e00c9b8f5494ee97220a125ac8d2c8/29381f30e924b899c83ff41c6d061d950a7bf697.jpg";
		String url = "http://pic35.nipic.com/20131115/6704106_153707247000_2.jpg";
		//String url = "http://v.youku.com/v_show/id_XMTI3MTQ1OTA1Ng";
		String jsonData = "{'description':{'title':'testTitle11','introduction':'testDes11'}}";
		//String jsonData = "{'title':'testTitle2','introduction':'testDes2'}";
		Assert.assertNotNull(MaterialUtil.addMaterial(InitUtil.accessToken, url, jsonData));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetMaterial(){
		List<UArticle> list = new ArrayList<UArticle>();
		//String mediaId = "ohBGsOcqzyMpbKzLN8DFKYFhRhUIO_X0SalUH_qiijU"; // 图文
		String mediaId = "ohBGsOcqzyMpbKzLN8DFKYv0Xjw9nIkcKTedtHDWCwc"; // 图片
		String downloadPath = "D:/Download/";
		JSONObject jsonObj = MaterialUtil.getMaterial(InitUtil.accessToken, mediaId, downloadPath);
		System.out.println(jsonObj);
		if(jsonObj != null){
			if(jsonObj.containsKey("news_item")){ // 图文
				list = JSONArray.toList(jsonObj.getJSONArray("news_item"), new UArticle(), new JsonConfig());
			}else if(jsonObj.containsKey("down_url")){ // 视频
				String title = jsonObj.getString("title");
				String description = jsonObj.getString("description");
				String downUrl = jsonObj.getString("down_url");
			}else{
				// 下载在 downloadPath 目录下
			}
		}
		
	}
	
	@Test
	public void testDelMaterial(){
		String mediaId = "ohBGsOcqzyMpbKzLN8DFKYJ7ZiaxPkLFa9JBi80M_x0"; // 永久素材medialId
		Assert.assertTrue(MaterialUtil.delMaterial(InitUtil.accessToken, mediaId));
	}
	
	@Test
	public void testUpdateMaterial(){
		List<UArticle> uList = new ArrayList<UArticle>();
		UArticle uArticle = new UArticle();
		uArticle.setThumb_media_id("ohBGsOcqzyMpbKzLN8DFKYv0Xjw9nIkcKTedtHDWCwc"); // 永久的media_id
		uArticle.setAuthor("water"); // 长度字符max 8
		uArticle.setTitle("氺之易"); // 长度字符max 64
		uArticle.setContent_source_url("http://rillmi.com");
		uArticle.setContent("向水流易");
		uArticle.setDigest("心语"); // 长度字符max 120
		uArticle.setShow_cover_pic("1");
		uList.add(uArticle);
		
//		UArticle uArticle02 = new UArticle();
//		uArticle02.setThumb_media_id("qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p"); // 永久的
//		uArticle02.setAuthor("boolink2");
//		uArticle02.setTitle("双川恩图2");
//		uArticle02.setContent_source_url("");
//		uArticle02.setContent("content2");
//		uArticle02.setDigest("digest");
//		uArticle02.setShow_cover_pic("1");
//		uList.add(uArticle02);
		Map<String, Object> map = new HashMap<String, Object>();
		String mediaId = "ohBGsOcqzyMpbKzLN8DFKf747FeLNhxl4gK85QTW-pM";
		int index = 0; // 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
		map.put("media_id", mediaId);
		map.put("index", index);
		map.put("articles", uArticle);
		System.out.println(JSONObject.fromObject(map).toString());
		Assert.assertTrue(MaterialUtil.updateNews(InitUtil.accessToken, JSONObject.fromObject(map).toString()));
		// 界面上查看可能不会立即显示 应该是微信缓存的缘故
	}
	
	@Test
	public void testGetMaterialCount(){
		JSONObject jsonObj = MaterialUtil.getMaterialCount(InitUtil.accessToken);
		Assert.assertTrue(jsonObj.containsKey("voice_count"));
		System.out.println(jsonObj.toString());
		if(jsonObj != null){
			if(jsonObj.containsKey("voice_count")){
				jsonObj.getInt("voice_count"); // 语音总数量
				jsonObj.getInt("video_count"); // 视频总数量
				jsonObj.getInt("image_count"); // 图片总数量
				jsonObj.getInt("news_count");  // 图文总数量
			}
		}
	}
	
	@Test
	public void testBatchGetMaterial(){
		String type = "video"; // type 图片(image)、视频(video)、语音 (voice)、图文(news)
		int offset = 0;
		int count = 1;
		JSONObject jsonObj = MaterialUtil.batchGetMaterial(InitUtil.accessToken, type, offset, count);
		// 数据格式详细参照：http://mp.weixin.qq.com/wiki/12/2108cd7aafff7f388f41f37efa710204.html->素材管理->获取素材列表
		System.out.println(jsonObj);
	}
}
