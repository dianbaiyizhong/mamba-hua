package com.zhenmei.mambahua;


import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DownloadThread implements Runnable {


    private String comicId;
    private String captureNum;

    private String baseUrl = "http://www.wuqimh.com/";

    public DownloadThread(String comicId, String captureNum) {
        this.comicId = comicId;
        this.captureNum = captureNum;
    }


    @Override
    public void run() {

        String url = baseUrl + comicId + "/" + captureNum + ".html";
        // 获取页数
        Document document = HttpUtils.getHtmlDoc(url);
        Elements els = document.select("#pageSelect> option");
        int pageCount = Integer.valueOf(els.get(els.size() - 1).attr("value"));


        // 将任务继续分配给子线程
        for (int i = 1; i <= pageCount; i++) {

            DownloadChildThread downloadChildThread = new DownloadChildThread(url, i);
            ThreadPool.getThreadPool().executor(downloadChildThread);

        }


    }


}
