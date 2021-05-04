package com.zhenmei.mambahua;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.http.HttpRequest;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.InputStream;

public class DownloadChildThread implements Runnable {
    public DownloadChildThread(String url, int page) {
        this.url = url;
        this.page = page;
    }

    private String url;
    private int page;

    private String path = "/Users/hhm/Documents/";


    @Override
    public void run() {

        System.out.println(url + "?p=" + page);
        Document document = HttpUtils.getHtmlDoc(url + "?p=" + page);
        Elements title = document.getElementsByTag("title");
        System.out.println("标题:" + title.get(0).text());
        String imgPath = document.getElementById("manga").attr("src");
        System.out.println("成功获取到图片地址:" + imgPath);
        InputStream inputStream = HttpRequest.get(imgPath).execute().bodyStream();
        FileWriter writer = new FileWriter(path + title.get(0).text() + File.separator + String.format("%03d", page) + ".png");
        writer.writeFromStream(inputStream);
    }


}
